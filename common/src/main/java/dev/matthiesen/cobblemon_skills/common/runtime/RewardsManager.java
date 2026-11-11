package dev.matthiesen.cobblemon_skills.common.runtime;

import com.cobblemon.mod.common.pokemon.Pokemon;
import com.cobblemon.mod.common.util.PlayerExtensionsKt;
import dev.matthiesen.cobblemon_skills.common.CobblemonSkillsCommon;
import dev.matthiesen.cobblemon_skills.common.config.CobblemonSkillsConfig;
import dev.matthiesen.cobblemon_skills.common.data.*;
import dev.matthiesen.matthiesen_core.common.api.events.server.PlayerEvent;
import dev.matthiesen.matthiesen_core.common.utility.SoundsPlayer;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetSubtitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitlesAnimationPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.Map;

public final class RewardsManager {
    public static int awardProfessionExperience(ServerPlayer player, Profession profession, double experience) {
        PlayerProfile profile = SavedPlayerProfessionData.get(player);
        ProfessionProgress progress = profile.getProgress(profession);
        boolean alreadyMaxLevel = progress.level() >= ProfessionProgress.getMaxLevel();
        int levelsGained = progress.addExperience(experience);
        SavedPlayerProfessionData.put(player, profile);
        if (alreadyMaxLevel && levelsGained == 0) {
            return 0;
        }
        sendExperienceProgress(player, profession, experience, progress);
        if (levelsGained > 0) {
            playLevelUpSound(player, levelsGained);
            sendLevelUpTitle(player, profession, progress.level());

            Map<String, String> placeholders = Map.of(
                    "%level%", String.valueOf(progress.level()),
                    "%profession%", profession.getConfig().displayName()
            );
            String messageTemplate = CobblemonSkillsConfig.TEXT_CONFIG.reward_levelUp_chatMessage.get();
            Component message = TextUtils.parse(messageTemplate, placeholders);
            player.sendSystemMessage(message);
        }
        return levelsGained;
    }

    public static void handleFishingSmithingTableRewards(ServerPlayer serverPlayer, ItemStack itemStack) {
        PlayerProfile profile = SavedPlayerProfessionData.get(serverPlayer);
        int fishingLevel = profile.getProgress(Profession.FISHING).level();
        var rewardsConfig = CobblemonSkillsConfig.Professions.FISHING_CONFIG;
        boolean randomLuckOfTheSea = rewardsConfig.rewards_randomLuckOfTheSea.get();
        boolean randomUnbreaking = rewardsConfig.rewards_randomUnbreaking.get();
        boolean randomMending = rewardsConfig.rewards_randomMending.get();
        boolean randomLure = rewardsConfig.rewards_randomLure.get();
        int maxLuckOfTheSeaTier = Fishing.getMaxTier(
                fishingLevel,
                rewardsConfig.rewards_luckOfTheSeaTierOneMinLvl.get(),
                rewardsConfig.rewards_luckOfTheSeaTierTwoMinLvl.get(),
                rewardsConfig.rewards_luckOfTheSeaTierThreeMinLvl.get()
        );
        int maxUnbreakingTier = Fishing.getMaxTier(
                fishingLevel,
                rewardsConfig.rewards_unbreakingTierOneMinLvl.get(),
                rewardsConfig.rewards_unbreakingTierTwoMinLvl.get(),
                rewardsConfig.rewards_unbreakingTierThreeMinLvl.get()
        );
        int maxLureTier = Fishing.getMaxTier(
                fishingLevel,
                rewardsConfig.rewards_lureTierOneMinLvl.get(),
                rewardsConfig.rewards_lureTierTwoMinLvl.get(),
                rewardsConfig.rewards_lureTierThreeMinLvl.get()
        );
        boolean isMendingEligible = fishingLevel >= rewardsConfig.rewards_mendingMinLvl.get();
        Fishing.applyEnchantmentsToItemStack(
                itemStack,
                randomLuckOfTheSea, randomUnbreaking, randomMending, randomLure,
                maxLuckOfTheSeaTier, maxUnbreakingTier, maxLureTier, isMendingEligible
        );
    }

    public static void handleBlockBreakRewards(PlayerEvent.BlockBreakEvent event, Profession profession) {
        if (!(event.player() instanceof ServerPlayer player) || !(event.world() instanceof ServerLevel world)) {
            return;
        }
        PlayerProfile profile = SavedPlayerProfessionData.get(player);
        ItemStack mainHandItem = player.getMainHandItem();
        if (!isTool(mainHandItem)) {
            return; // Only apply extra drops for tools, not weapons or other items
        }
        if (isEligibleForExtraDrops(profile, profession) && Math.random() < getExtraDropsChance(profession)) {
            Block.getDrops(event.state(), world, event.pos(), event.blockEntity(), player, mainHandItem)
                    .forEach(drop -> Block.popResource(world, event.pos(), drop));
        }
    }

    private static boolean isTool(ItemStack item) {
        List<TagKey<Item>> toolTags = List.of(ItemTags.PICKAXES, ItemTags.SHOVELS, ItemTags.AXES, ItemTags.HOES);
        return toolTags.stream().anyMatch(item::is);
    }

    public static void handleCookingRewards(ServerPlayer player) {
        PlayerProfile profile = SavedPlayerProfessionData.get(player);
        ProfessionProgress progress = profile.getProgress(Profession.COOKING);
        int level = progress.level();
        var rewardsConfig = CobblemonSkillsConfig.Professions.COOKING_CONFIG;
        if (!rewardsConfig.rewards_randomExtraPlayerExp.getAsBoolean()
                || level < rewardsConfig.rewards_randomExtraPlayerExpMinLvl.get()
        ) return;
        int baseAmount = rewardsConfig.rewards_randomExtraPlayerExpBaseAmount.get();
        int levelMultiplier = rewardsConfig.rewards_randomExtraPlayerExpLevelMultiplier.get();
        int playerExperienceToAward = baseAmount + (level * levelMultiplier);
        player.giveExperiencePoints(playerExperienceToAward);
    }

    public static void handleArcheologyBrush(ServerPlayer serverPlayer, ItemStack item) {
        PlayerProfile profile = SavedPlayerProfessionData.get(serverPlayer);
        if (isEligibleForExtraDrops(profile, Profession.ARCHEOLOGY) && Math.random() < getExtraDropsChance(Profession.ARCHEOLOGY)) {
            ItemStack extra = item.copy();
            extra.setCount(1);
            PlayerExtensionsKt.giveOrDropItemStack(serverPlayer, extra, true);
        }
    }

    private static boolean isEligibleForExtraDrops(PlayerProfile profile, Profession profession) {
        if (profession == Profession.ARCHEOLOGY) {
            var rewardsConfig = CobblemonSkillsConfig.Professions.ARCHEOLOGY_CONFIG;
            return rewardsConfig.rewards_randomExtraDrops.getAsBoolean()
                    && profile.getProgress(Profession.ARCHEOLOGY).level() >= rewardsConfig.rewards_randomExtraDropsMinLvl.get();
        } else if (profession == Profession.BOTANY) {
            var rewardsConfig = CobblemonSkillsConfig.Professions.BOTANY_CONFIG;
            return rewardsConfig.rewards_randomExtraDrops.getAsBoolean()
                    && profile.getProgress(Profession.BOTANY).level() >= rewardsConfig.rewards_randomExtraDropsMinLvl.get();
        }
        return false;
    }

    private static double getExtraDropsChance(Profession profession) {
        if (profession == Profession.ARCHEOLOGY) {
            return CobblemonSkillsConfig.Professions.ARCHEOLOGY_CONFIG.rewards_randomExtraDropsChance.getAsDouble();
        } else if (profession == Profession.BOTANY) {
            return CobblemonSkillsConfig.Professions.BOTANY_CONFIG.rewards_randomExtraDropsChance.getAsDouble();
        }
        return 0.0;
    }

    public static double captureCatchRateBonusPercent(int level) {
        var rewardsConfig = CobblemonSkillsConfig.Professions.CATCHING_CONFIG;
        return Math.min(
                rewardsConfig.rewards_captureRateBonusPercentMax.getAsDouble(),
                level * rewardsConfig.rewards_captureRateLevelMultiplier.getAsDouble()
        );
    }

    public static float getCaptureRateBonusMultiplier(int level) {
        return (float) (1.0 + (captureCatchRateBonusPercent(level) / 100.0));
    }

    public static int getBreedingFriendshipBonus(int breedingLevel) {
        var rewardsConfig = CobblemonSkillsConfig.Professions.BREEDING_CONFIG;
        return Math.min(
                rewardsConfig.rewards_friendshipBonusMax.getAsInt(),
                breedingLevel / rewardsConfig.rewards_friendshipBonusLevelDivider.getAsInt()
        );
    }

    public static double trainingSkillExperienceFromBattle(double battleExperience) {
        if (battleExperience <= 0) {
            return 0.0;
        }
        var rewardsConfig = CobblemonSkillsConfig.Professions.TRAINING_CONFIG;
        return Math.max(
                rewardsConfig.rewards_experienceFromBattleMax.getAsDouble(),
                battleExperience * rewardsConfig.rewards_experienceFromBattleExpMultiplier.getAsDouble()
        );
    }

    public static double trainingBattleExperienceBonusPercent(int level) {
        var rewardsConfig = CobblemonSkillsConfig.Professions.TRAINING_CONFIG;
        return Math.min(
                rewardsConfig.rewards_levelUpBonusExpPercentageMax.getAsDouble(),
                level * rewardsConfig.rewards_levelUpBonusExpPercentageLevelMultiplier.getAsDouble()
        );
    }

    public static int trainingSkillBonusExperience(int level, int baseExperience) {
        return (int) Math.floor(baseExperience * (trainingBattleExperienceBonusPercent(level) / 100.0));
    }

    public static boolean shouldMakeEggShiny(int breedingLevel, boolean currentShinyStatus) {
        if (currentShinyStatus) {
            return true;
        }
        var rewardsConfig = CobblemonSkillsConfig.Professions.BREEDING_CONFIG;
        double baseShinyChance = rewardsConfig.rewards_baseShinyChange.getAsDouble();
        double breedingBonus = breedingLevel * rewardsConfig.rewards_shinyLevelBonusMultiplier.getAsDouble();
        double totalShinyChance = baseShinyChance + breedingBonus;
        totalShinyChance = Math.min(totalShinyChance, rewardsConfig.rewards_shinyMaxPercentage.getAsDouble());
        return Math.random() < totalShinyChance;
    }

    public static Float onShinyCalculationModify(Float rate, ServerPlayer player, @SuppressWarnings("unused") Pokemon pokemon) {
        var rewardsConfig = CobblemonSkillsConfig.Professions.CATCHING_CONFIG;
        if (player == null || !rewardsConfig.rewards_enableShinyCalculationBoosting.getAsBoolean()) {
            return rate;
        }
        var catchingLevel = SavedPlayerProfessionData.get(player).getProgress(Profession.CATCHING).level();
        double doubleMaxMultiplier = rewardsConfig.rewards_shinyCalculationMaxMultiplier.getAsDouble();
        float maxMultiplier = (float) doubleMaxMultiplier;
        float newCatchMultiplier = (float) Math.min(
                rewardsConfig.rewards_shinyCalculationPerLevelMultiplier.getAsDouble() * catchingLevel,
                maxMultiplier
        );
        return Math.max(rate / newCatchMultiplier, 1);
    }

    private static Holder<Enchantment> getEnchantment(ResourceKey<Enchantment> enchantment) {
        RegistryAccess registryAccess = CobblemonSkillsCommon.INSTANCE.getCommonUtils().getServer().registryAccess();
        return registryAccess.registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(enchantment);
    }

    private static void sendLevelUpTitle(ServerPlayer player, Profession profession, int level) {

        Map<String, String> placeholders = Map.of(
                "%level%", String.valueOf(level),
                "%profession%", profession.getConfig().displayName()
        );
        String titleTemplate = CobblemonSkillsConfig.TEXT_CONFIG.reward_levelUp_title.get();
        String subTitleTemplate = CobblemonSkillsConfig.TEXT_CONFIG.reward_levelUp_subTitle.get();

        Component title = TextUtils.parse(titleTemplate, placeholders);
        Component subtitle = TextUtils.parse(subTitleTemplate, placeholders);

        player.connection.send(new ClientboundSetTitlesAnimationPacket(10, 50, 15));
        player.connection.send(new ClientboundSetTitleTextPacket(title));
        player.connection.send(new ClientboundSetSubtitleTextPacket(subtitle));
    }

    private static void sendExperienceProgress(ServerPlayer player, Profession profession, double experience, ProfessionProgress progress) {
        int nextLevelExperience = ProfessionProgress.experienceRequiredForNextLevel(progress.level());

        Map<String, String> placeholders = Map.of(
                "%level%", String.valueOf(progress.level()),
                "%profession%", profession.getConfig().displayName(),
                "%experience%", String.format("%.0f", experience),
                "%nextLevelExperience%", nextLevelExperience <= 0
                        ? String.format(CobblemonSkillsConfig.TEXT_CONFIG.reward_experienceProgress_maxed.get(), progress.level())
                        : String.format(CobblemonSkillsConfig.TEXT_CONFIG.reward_experienceProgress_progress.get(), progress.level(), progress.experience(), nextLevelExperience)
        );

        String messageTemplate = CobblemonSkillsConfig.TEXT_CONFIG.reward_experienceProgress_chatMessage.get();
        Component message = TextUtils.parse(messageTemplate, placeholders);
        player.sendSystemMessage(message, true);
    }

    private static void playLevelUpSound(ServerPlayer player, int level) {
        float pitch = Math.min(1.0F, 1.0F + (level - 1) * 0.08F);
        new SoundsPlayer(SoundEvents.PLAYER_LEVELUP).setPitch(pitch).play(player);
    }

    private static class Fishing {
        private static double getFishingEnchantmentChance() {
            return CobblemonSkillsConfig.Professions.FISHING_CONFIG.rewards_enchantmentChance.get();
        }

        private static void applyEnchantmentsToItemStack(
                ItemStack itemStack,
                boolean randomLuckOfTheSea,
                boolean randomUnbreaking,
                boolean randomMending,
                boolean randomLure,
                int maxLuckOfTheSeaTier,
                int maxUnbreakingTier,
                int maxLureTier,
                boolean isMendingEligible
        ) {
            // Apply enchantments to the itemStack based on the maximum tiers and eligibility
            if (randomLuckOfTheSea && maxLuckOfTheSeaTier > 0 && shouldApplyEnchantment(getFishingEnchantmentChance())) {
                itemStack.enchant(getEnchantment(Enchantments.LUCK_OF_THE_SEA), maxLuckOfTheSeaTier);
            }
            if (randomUnbreaking && maxUnbreakingTier > 0 && shouldApplyEnchantment(getFishingEnchantmentChance())) {
                itemStack.enchant(getEnchantment(Enchantments.UNBREAKING), maxUnbreakingTier);
            }
            if (randomLure && maxLureTier > 0 && shouldApplyEnchantment(getFishingEnchantmentChance())) {
                itemStack.enchant(getEnchantment(Enchantments.LURE), maxLureTier);
            }
            if (randomMending && isMendingEligible && shouldApplyEnchantment(getFishingEnchantmentChance())) {
                itemStack.enchant(getEnchantment(Enchantments.MENDING), 1);
            }
        }

        private static boolean shouldApplyEnchantment(double chance) {
            return Math.random() < chance;
        }

        private static int getMaxTier(
                int level,
                int tierOneMinLvl,
                int tierTwoMinLvl,
                int tierThreeMinLvl
        ) {
            if (level >= tierThreeMinLvl) {
                return 3;
            } else if (level >= tierTwoMinLvl) {
                return 2;
            } else if (level >= tierOneMinLvl) {
                return 1;
            } else {
                return 0; // No tier unlocked
            }
        }
    }

}
