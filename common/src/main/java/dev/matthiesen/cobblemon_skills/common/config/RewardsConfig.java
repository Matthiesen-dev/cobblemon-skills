package dev.matthiesen.cobblemon_skills.common.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class RewardsConfig {

    // Archeology

    // Botany

    // Cooking

    // Fishing
    public ModConfigSpec.BooleanValue fishing_randomLuckOfTheSea;
    public ModConfigSpec.BooleanValue fishing_randomUnbreaking;
    public ModConfigSpec.BooleanValue fishing_randomMending;
    public ModConfigSpec.BooleanValue fishing_randomLure;
    public ModConfigSpec.IntValue fishing_luckOfTheSeaTierOneMinLvl;
    public ModConfigSpec.IntValue fishing_luckOfTheSeaTierTwoMinLvl;
    public ModConfigSpec.IntValue fishing_luckOfTheSeaTierThreeMinLvl;
    public ModConfigSpec.IntValue fishing_unbreakingTierOneMinLvl;
    public ModConfigSpec.IntValue fishing_unbreakingTierTwoMinLvl;
    public ModConfigSpec.IntValue fishing_unbreakingTierThreeMinLvl;
    public ModConfigSpec.IntValue fishing_mendingMinLvl;
    public ModConfigSpec.IntValue fishing_lureTierOneMinLvl;
    public ModConfigSpec.IntValue fishing_lureTierTwoMinLvl;
    public ModConfigSpec.IntValue fishing_lureTierThreeMinLvl;
    public ModConfigSpec.DoubleValue fishing_enchantmentChance;

    // Catching
    public ModConfigSpec.BooleanValue catching_enableShinyCalculationBoosting;
    public ModConfigSpec.DoubleValue catching_shinyCalculationPerLevelMultiplier;
    public ModConfigSpec.DoubleValue catching_shinyCalculationMaxMultiplier;
    public ModConfigSpec.DoubleValue catching_captureRateBonusPercentMax;
    public ModConfigSpec.DoubleValue catching_captureRateLevelMultiplier;

    // Training
    public ModConfigSpec.DoubleValue training_experienceFromBattleMax;
    public ModConfigSpec.DoubleValue training_experienceFromBattleExpMultiplier;
    public ModConfigSpec.DoubleValue training_levelUpBonusExpPercentageMax;
    public ModConfigSpec.DoubleValue training_levelUpBonusExpPercentageLevelMultiplier;

    // Breeding
    public ModConfigSpec.DoubleValue breeding_baseShinyChange;
    public ModConfigSpec.DoubleValue breeding_shinyLevelBonusMultiplier;
    public ModConfigSpec.DoubleValue breeding_shinyMaxPercentage;
    public ModConfigSpec.IntValue breeding_friendshipBonusMax;
    public ModConfigSpec.IntValue breeding_friendshipBonusLevelDivider;

    public RewardsConfig(ModConfigSpec.Builder builder) {
        builder.comment("Rewards Configuration for Cobblemon Skills Mod").push("rewards");

        builder.comment("Archeology Profession Configuration").push("archeology");
        builder.pop(); // Pop "archeology" category

        builder.comment("Botany Profession Configuration").push("botany");
        builder.pop(); // Pop "botany" category

        builder.comment("Cooking Profession Configuration").push("cooking");
        builder.pop(); // Pop "cooking" category

        builder.comment("Fishing Profession Configuration").push("fishing");
        fishing_randomLuckOfTheSea = builder.comment("Whether the Luck of the Sea enchantment is randomly applied to fishing rewards")
                .define("randomLuckOfTheSea", true);
        fishing_randomUnbreaking = builder.comment("Whether the Unbreaking enchantment is randomly applied to fishing rewards")
                .define("randomUnbreaking", true);
        fishing_randomMending = builder.comment("Whether the Mending enchantment is randomly applied to fishing rewards")
                .define("randomMending", true);
        fishing_randomLure = builder.comment("Whether the Lure enchantment is randomly applied to fishing rewards")
                .define("randomLure", true);
        fishing_luckOfTheSeaTierOneMinLvl = builder.comment("The minimum level required for Luck of the Sea Tier 1 in fishing profession")
                .defineInRange("luckOfTheSeaTierOneMinLvl", 10, 0, Integer.MAX_VALUE);
        fishing_luckOfTheSeaTierTwoMinLvl = builder.comment("The minimum level required for Luck of the Sea Tier 2 in fishing profession")
                .defineInRange("luckOfTheSeaTierTwoMinLvl", 30, 0, Integer.MAX_VALUE);
        fishing_luckOfTheSeaTierThreeMinLvl = builder.comment("The minimum level required for Luck of the Sea Tier 3 in fishing profession")
                .defineInRange("luckOfTheSeaTierThreeMinLvl", 50, 0, Integer.MAX_VALUE);
        fishing_unbreakingTierOneMinLvl = builder.comment("The minimum level required for Unbreaking Tier 1 in fishing profession")
                .defineInRange("unbreakingTierOneMinLvl", 20, 0, Integer.MAX_VALUE);
        fishing_unbreakingTierTwoMinLvl = builder.comment("The minimum level required for Unbreaking Tier 2 in fishing profession")
                .defineInRange("unbreakingTierTwoMinLvl", 40, 0, Integer.MAX_VALUE);
        fishing_unbreakingTierThreeMinLvl = builder.comment("The minimum level required for Unbreaking Tier 3 in fishing profession")
                .defineInRange("unbreakingTierThreeMinLvl", 60, 0, Integer.MAX_VALUE);
        fishing_mendingMinLvl = builder.comment("The minimum level required for Mending enchantment in fishing profession")
                .defineInRange("mendingMinLvl", 70, 0, Integer.MAX_VALUE);
        fishing_lureTierOneMinLvl = builder.comment("The minimum level required for Lure Tier 1 in fishing profession")
                .defineInRange("lureTierOneMinLvl", 15, 0, Integer.MAX_VALUE);
        fishing_lureTierTwoMinLvl = builder.comment("The minimum level required for Lure Tier 2 in fishing profession")
                .defineInRange("lureTierTwoMinLvl", 35, 0, Integer.MAX_VALUE);
        fishing_lureTierThreeMinLvl = builder.comment("The minimum level required for Lure Tier 3 in fishing profession")
                .defineInRange("lureTierThreeMinLvl", 55, 0, Integer.MAX_VALUE);
        fishing_enchantmentChance = builder.comment("The chance for an enchantment to be applied to fishing rewards")
                .defineInRange("enchantmentChance", 0.25, 0.0, 1.0);
        builder.pop(); // Pop "fishing" category

        builder.comment("Catching Profession Configuration").push("catching");
        catching_enableShinyCalculationBoosting = builder.comment("Whether shiny calculation boosting is enabled in catching profession")
                .define("enableShinyCalculationBoosting", true);
        catching_shinyCalculationMaxMultiplier = builder.comment("The maximum multiplier for shiny calculation in catching profession")
                .defineInRange("shinyCalculationMaxMultiplier", 10.0, 0.0, 10.0);
        catching_shinyCalculationPerLevelMultiplier = builder.comment("The multiplier for shiny calculation per level in catching profession")
                .defineInRange("shinyCalculationPerLevelMultiplier", 0.01, 0.0, 1.0);
        catching_captureRateBonusPercentMax = builder.comment("The maximum bonus percent for capture rate in catching profession")
                .defineInRange("captureRateBonusPercentMax", 10.0, 0.0, 100.0);
        catching_captureRateLevelMultiplier = builder.comment("The bonus multiplier for capture rate based on catching level")
                .defineInRange("captureRateLevelMultiplier", 0.10, 0.0, 1.0);
        builder.pop(); // Pop "catching" category

        builder.comment("Training Profession Configuration").push("training");
        training_experienceFromBattleMax = builder.comment("The maximum experience from battle in training profession")
                .defineInRange("experienceFromBattleMax", 20.0, 0.0, 1000.0);
        training_experienceFromBattleExpMultiplier = builder.comment("The experience multiplier from battle based on training level")
                .defineInRange("experienceFromBattleExpMultiplier", 0.20, 0.0, 10.0);
        training_levelUpBonusExpPercentageMax = builder.comment("The maximum bonus experience percentage for level up in training profession")
                .defineInRange("levelUpBonusExpPercentageMax", 25.0, 0.0, 100.0);
        training_levelUpBonusExpPercentageLevelMultiplier = builder.comment("The bonus experience percentage multiplier for level up based on training level")
                .defineInRange("levelUpBonusExpPercentageLevelMultiplier", 0.25, 0.0, 1.0);
        builder.pop(); // Pop "training" category

        builder.comment("Breeding Profession Configuration").push("breeding");
        breeding_baseShinyChange = builder.comment("The base shiny chance for breeding profession")
                .defineInRange("baseShinyChange", 0.01, 0.0, 1.0);
        breeding_shinyLevelBonusMultiplier = builder.comment("The bonus multiplier for shiny chance based on breeding level")
                .defineInRange("shinyLevelBonusMultiplier", 0.001, 0.0, 1.0);
        breeding_shinyMaxPercentage = builder.comment("The maximum shiny percentage for breeding profession")
                .defineInRange("shinyMaxPercentage", 0.10, 0.0, 1.0);
        breeding_friendshipBonusMax = builder.comment("The maximum friendship bonus for breeding profession")
                .defineInRange("friendshipBonusMax", 20, 0, 100);
        breeding_friendshipBonusLevelDivider = builder.comment("The level divider for friendship bonus in breeding profession")
                .defineInRange("friendshipBonusLevelDivider", 5, 1, 100);
        builder.pop(); // Pop "breeding" category

        builder.pop(); // Pop "rewards" category
    }
}
