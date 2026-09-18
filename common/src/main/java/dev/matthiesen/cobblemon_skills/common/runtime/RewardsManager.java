package dev.matthiesen.cobblemon_skills.common.runtime;

import com.cobblemon.mod.common.pokemon.Pokemon;
import dev.matthiesen.cobblemon_skills.common.CobblemonSkillsCommon;
import dev.matthiesen.cobblemon_skills.common.config.CobblemonSkillsConfig;
import dev.matthiesen.cobblemon_skills.common.config.RewardsConfig;
import dev.matthiesen.cobblemon_skills.common.data.PlayerProfile;
import dev.matthiesen.cobblemon_skills.common.data.Profession;
import dev.matthiesen.cobblemon_skills.common.data.SavedPlayerProfessionData;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

public final class RewardsManager {
    private static RewardsConfig getRewardsConfig() {
        return CobblemonSkillsConfig.REWARDS_CONFIG;
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

    private static Holder<Enchantment> getEnchantment(ResourceKey<Enchantment> enchantment) {
        RegistryAccess registryAccess = CobblemonSkillsCommon.INSTANCE.getCommonUtils().getServer().registryAccess();
        return registryAccess.registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(enchantment);
    }

    public static class Fishing {
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
