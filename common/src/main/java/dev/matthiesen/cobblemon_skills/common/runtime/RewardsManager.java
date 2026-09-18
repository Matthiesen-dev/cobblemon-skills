package dev.matthiesen.cobblemon_skills.common.runtime;

import com.cobblemon.mod.common.pokemon.Pokemon;
import com.cobblemon.mod.common.util.PlayerExtensionsKt;
import dev.matthiesen.cobblemon_skills.common.CobblemonSkillsCommon;
import dev.matthiesen.cobblemon_skills.common.config.CobblemonSkillsConfig;
import dev.matthiesen.cobblemon_skills.common.config.RewardsConfig;
import dev.matthiesen.cobblemon_skills.common.data.PlayerProfile;
import dev.matthiesen.cobblemon_skills.common.data.Profession;
import dev.matthiesen.cobblemon_skills.common.data.ProfessionProgress;
import dev.matthiesen.cobblemon_skills.common.data.SavedPlayerProfessionData;
import dev.matthiesen.cobblemon_skills.common.platform.BlockBreakEvent;
import dev.matthiesen.matthiesen_core.common.utility.SoundsPlayer;
import net.minecraft.ChatFormatting;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;

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
            player.sendSystemMessage(Component.literal("Congratulations! Your " + profession.getConfig().displayName() + " profession has leveled up to level " + progress.level() + "!"));
        }
        return levelsGained;
    }

    public static void handleFishingSmithingTableRewards(ServerPlayer serverPlayer, ItemStack itemStack) {
        PlayerProfile profile = SavedPlayerProfessionData.get(serverPlayer);
        int fishingLevel = profile.getProgress(Profession.FISHING).level();
        boolean randomLuckOfTheSea = getRewardsConfig().fishing_randomLuckOfTheSea.get();
        boolean randomUnbreaking = getRewardsConfig().fishing_randomUnbreaking.get();
        boolean randomMending = getRewardsConfig().fishing_randomMending.get();
        boolean randomLure = getRewardsConfig().fishing_randomLure.get();
        int maxLuckOfTheSeaTier = Fishing.getMaxTier(
                fishingLevel,
                getRewardsConfig().fishing_luckOfTheSeaTierOneMinLvl.get(),
                getRewardsConfig().fishing_luckOfTheSeaTierTwoMinLvl.get(),
                getRewardsConfig().fishing_luckOfTheSeaTierThreeMinLvl.get()
        );
        int maxUnbreakingTier = Fishing.getMaxTier(
                fishingLevel,
                getRewardsConfig().fishing_unbreakingTierOneMinLvl.get(),
                getRewardsConfig().fishing_unbreakingTierTwoMinLvl.get(),
                getRewardsConfig().fishing_unbreakingTierThreeMinLvl.get()
        );
        int maxLureTier = Fishing.getMaxTier(
                fishingLevel,
                getRewardsConfig().fishing_lureTierOneMinLvl.get(),
                getRewardsConfig().fishing_lureTierTwoMinLvl.get(),
                getRewardsConfig().fishing_lureTierThreeMinLvl.get()
        );
        boolean isMendingEligible = fishingLevel >= getRewardsConfig().fishing_mendingMinLvl.get();
        Fishing.applyEnchantmentsToItemStack(
                itemStack,
                randomLuckOfTheSea, randomUnbreaking, randomMending, randomLure,
                maxLuckOfTheSeaTier, maxUnbreakingTier, maxLureTier, isMendingEligible
        );
    }

    public static void handleBlockBreakRewards(BlockBreakEvent event, Profession profession) {
        if (!(event.player() instanceof ServerPlayer player) || !(event.world() instanceof ServerLevel world)) {
            return;
        }
        PlayerProfile profile = SavedPlayerProfessionData.get(player);
        if (isEligibleForExtraDrops(profile, profession) && Math.random() < getExtraDropsChance(profession)) {
            Block.getDrops(event.state(), world, event.pos(), event.blockEntity(), player, event.player().getMainHandItem())
                    .forEach(drop -> Block.popResource(world, event.pos(), drop));
        }
    }

    public static void handleCookingRewards(ServerPlayer player) {
        PlayerProfile profile = SavedPlayerProfessionData.get(player);
        ProfessionProgress progress = profile.getProgress(Profession.COOKING);
        int level = progress.level();
        if (!getRewardsConfig().cooking_randomExtraPlayerExp.getAsBoolean()
                || level < getRewardsConfig().cooking_randomExtraPlayerExpMinLvl.get()
        ) return;
        int baseAmount = getRewardsConfig().cooking_randomExtraPlayerExpBaseAmount.get();
        int levelMultiplier = getRewardsConfig().cooking_randomExtraPlayerExpLevelMultiplier.get();
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
            return getRewardsConfig().archeology_randomExtraDrops.getAsBoolean()
                    && profile.getProgress(Profession.ARCHEOLOGY).level() >= getRewardsConfig().archeology_randomExtraDropsMinLvl.get();
        } else if (profession == Profession.BOTANY) {
            return getRewardsConfig().botany_randomExtraDrops.getAsBoolean()
                    && profile.getProgress(Profession.BOTANY).level() >= getRewardsConfig().botany_randomExtraDropsMinLvl.get();
        }
        return false;
    }

    private static double getExtraDropsChance(Profession profession) {
        if (profession == Profession.ARCHEOLOGY) {
            return getRewardsConfig().archeology_randomExtraDropsChance.getAsDouble();
        } else if (profession == Profession.BOTANY) {
            return getRewardsConfig().botany_randomExtraDropsChance.getAsDouble();
        }
        return 0.0;
    }

    public static double captureCatchRateBonusPercent(int level) {
        return Math.min(
                getRewardsConfig().catching_captureRateBonusPercentMax.getAsDouble(),
                level * getRewardsConfig().catching_captureRateLevelMultiplier.getAsDouble()
        );
    }

    public static float getCaptureRateBonusMultiplier(int level) {
        return (float) (1.0 + (captureCatchRateBonusPercent(level) / 100.0));
    }

    public static int getBreedingFriendshipBonus(int breedingLevel) {
        return Math.min(
                getRewardsConfig().breeding_friendshipBonusMax.getAsInt(),
                breedingLevel / getRewardsConfig().breeding_friendshipBonusLevelDivider.getAsInt()
        );
    }

    public static double trainingSkillExperienceFromBattle(double battleExperience) {
        if (battleExperience <= 0) {
            return 0.0;
        }
        return Math.max(
                getRewardsConfig().training_experienceFromBattleMax.getAsDouble(),
                battleExperience * getRewardsConfig().training_experienceFromBattleExpMultiplier.getAsDouble()
        );
    }

    public static double trainingBattleExperienceBonusPercent(int level) {
        return Math.min(
                getRewardsConfig().training_levelUpBonusExpPercentageMax.getAsDouble(),
                level * getRewardsConfig().training_levelUpBonusExpPercentageLevelMultiplier.getAsDouble()
        );
    }

    public static int trainingSkillBonusExperience(int level, int baseExperience) {
        return (int) Math.floor(baseExperience * (trainingBattleExperienceBonusPercent(level) / 100.0));
    }

    public static boolean shouldMakeEggShiny(int breedingLevel, boolean currentShinyStatus) {
        if (currentShinyStatus) {
            return true;
        }
        double baseShinyChance = getRewardsConfig().breeding_baseShinyChange.getAsDouble();
        double breedingBonus = breedingLevel * getRewardsConfig().breeding_shinyLevelBonusMultiplier.getAsDouble();
        double totalShinyChance = baseShinyChance + breedingBonus;
        totalShinyChance = Math.min(totalShinyChance, getRewardsConfig().breeding_shinyMaxPercentage.getAsDouble());
        return Math.random() < totalShinyChance;
    }

    public static Float onShinyCalculationModify(Float rate, ServerPlayer player, @SuppressWarnings("unused") Pokemon pokemon) {
        if (player == null || !getRewardsConfig().catching_enableShinyCalculationBoosting.getAsBoolean()) {
            return rate;
        }
        var catchingLevel = SavedPlayerProfessionData.get(player).getProgress(Profession.CATCHING).level();
        double doubleMaxMultiplier = getRewardsConfig().catching_shinyCalculationMaxMultiplier.getAsDouble();
        float maxMultiplier = (float) doubleMaxMultiplier;
        float newCatchMultiplier = (float) Math.min(
                getRewardsConfig().catching_shinyCalculationPerLevelMultiplier.getAsDouble() * catchingLevel,
                maxMultiplier
        );
        return Math.max(rate / newCatchMultiplier, 1);
    }

    private static RewardsConfig getRewardsConfig() {
        return CobblemonSkillsConfig.REWARDS_CONFIG;
    }

    private static Holder<Enchantment> getEnchantment(ResourceKey<Enchantment> enchantment) {
        RegistryAccess registryAccess = CobblemonSkillsCommon.INSTANCE.getCommonUtils().getServer().registryAccess();
        return registryAccess.registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(enchantment);
    }

    private static void sendLevelUpTitle(ServerPlayer player, Profession profession, int level) {
        Component title = Component.literal("Leveled Up!").withStyle(ChatFormatting.GOLD, ChatFormatting.BOLD);
        Component subtitle = Component.literal("Your " + profession.getConfig().displayName() + " profession is now level " + level + "!").withStyle(ChatFormatting.YELLOW);
        player.connection.send(new ClientboundSetTitlesAnimationPacket(10, 50, 15));
        player.connection.send(new ClientboundSetTitleTextPacket(title));
        player.connection.send(new ClientboundSetSubtitleTextPacket(subtitle));
    }

    private static void sendExperienceProgress(ServerPlayer player, Profession profession, double experience, ProfessionProgress progress) {
        int nextLevelExperience = ProfessionProgress.experienceRequiredForNextLevel(progress.level());
        String message = String.format(
                "%s +%.0f XP | %s",
                profession.getConfig().displayName(),
                experience,
                nextLevelExperience <= 0
                        ? "Lvl " + progress.level() + " (Max)"
                        : String.format("Lvl %d | %.0f/%d", progress.level(), progress.experience(), nextLevelExperience)
        );
        player.sendSystemMessage(Component.literal(message), true);
    }

    private static void playLevelUpSound(ServerPlayer player, int level) {
        float pitch = Math.min(1.0F, 1.0F + (level - 1) * 0.08F);
        new SoundsPlayer(SoundEvents.PLAYER_LEVELUP).setPitch(pitch).play(player);
    }

    private static class Fishing {
        private static double getFishingEnchantmentChance() {
            return getRewardsConfig().fishing_enchantmentChance.get();
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
