package dev.matthiesen.cobblemon_skills.common.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class RewardsConfig {

    // Archeology

    // Botany

    // Cooking

    // Fishing

    // Catching
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
        builder.pop(); // Pop "fishing" category

        builder.comment("Catching Profession Configuration").push("catching");
        catching_shinyCalculationMaxMultiplier = builder.comment("The maximum multiplier for shiny calculation in catching profession")
                .defineInRange("shinyCalculationMaxMultiplier", 10.0, 0.0, 10.0);
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
