package dev.matthiesen.cobblemon_skills.common.config;

import com.electronwill.nightconfig.core.Config;
import dev.matthiesen.cobblemon_skills.common.config.def.PROF_DEFAULTS;
import dev.matthiesen.cobblemon_skills.common.config.def.ProfessionTierEntry;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

public final class ProfessionsConfig {

    // General
    public ModConfigSpec.IntValue professionMaxLevel;
    public ModConfigSpec.IntValue professionBaseExpPerLevel;
    public ModConfigSpec.IntValue professionExpLevelMultiplier;

    // Archeology
    public ModConfigSpec.ConfigValue<String> archeology_professionName;
    public ModConfigSpec.ConfigValue<List<? extends Config>> archeology_professionRewards;

    // Botany
    public ModConfigSpec.ConfigValue<String> botany_professionName;
    public ModConfigSpec.ConfigValue<List<? extends Config>> botany_professionRewards;

    // Cooking
    public ModConfigSpec.ConfigValue<String> cooking_professionName;
    public ModConfigSpec.ConfigValue<List<? extends Config>> cooking_professionRewards;

    // Fishing
    public ModConfigSpec.ConfigValue<String> fishing_professionName;
    public ModConfigSpec.ConfigValue<List<? extends Config>> fishing_professionRewards;

    // Catching
    public ModConfigSpec.ConfigValue<String> catching_professionName;
    public ModConfigSpec.ConfigValue<List<? extends Config>> catching_professionRewards;

    // Training
    public ModConfigSpec.ConfigValue<String> training_professionName;
    public ModConfigSpec.ConfigValue<List<? extends Config>> training_professionRewards;

    // Breeding
    public ModConfigSpec.ConfigValue<String> breeding_professionName;
    public ModConfigSpec.ConfigValue<List<? extends Config>> breeding_professionRewards;

    public ProfessionsConfig(ModConfigSpec.Builder builder) {
        builder.comment("Professions configuration for Cobblemon Skills").push("professions");

        professionMaxLevel = builder.comment("Maximum level for all professions")
                .defineInRange("professionMaxLevel", 100, 1, Integer.MAX_VALUE);
        professionBaseExpPerLevel = builder.comment("Base experience required for each level")
                .defineInRange("professionBaseExpPerLevel", 1020, 1, Integer.MAX_VALUE);
        professionExpLevelMultiplier = builder.comment("Multiplier for experience required per level")
                .defineInRange("professionExpLevelMultiplier", 20, 1, Integer.MAX_VALUE);

        builder.comment("Archeology Profession Configuration").push("archeology");
        archeology_professionName = builder.comment("Name of the Archeology profession")
                .define("professionName", "Archeology");
        archeology_professionRewards = builder.comment("Rewards configuration for the Archeology profession")
                .defineListAllowEmpty(
                        List.of("professionRewards"),
                        PROF_DEFAULTS::getDefaultArcheologyTiers,
                        null,
                        o -> o instanceof Config && ProfessionTierEntry.isValid(o)
                );
        builder.pop(); // pop "archeology"

        builder.comment("Botany Profession Configuration").push("botany");
        botany_professionName = builder.comment("Name of the Botany profession")
                .define("professionName", "Botany");
        botany_professionRewards = builder.comment("Rewards configuration for the Botany profession")
                .defineListAllowEmpty(
                        List.of("professionRewards"),
                        PROF_DEFAULTS::getDefaultBotanyTiers,
                        null,
                        o -> o instanceof Config && ProfessionTierEntry.isValid(o)
                );
        builder.pop(); // pop "botany"

        builder.comment("Cooking Profession Configuration").push("cooking");
        cooking_professionName = builder.comment("Name of the Cooking profession")
                .define("professionName", "Cooking");
        cooking_professionRewards = builder.comment("Rewards configuration for the Cooking profession")
                .defineListAllowEmpty(
                        List.of("professionRewards"),
                        PROF_DEFAULTS::getDefaultCookingTiers,
                        null,
                        o -> o instanceof Config && ProfessionTierEntry.isValid(o)
                );
        builder.pop(); // pop "cooking"

        builder.comment("Fishing Profession Configuration").push("fishing");
        fishing_professionName = builder.comment("Name of the Fishing profession")
                .define("professionName", "Fishing");
        fishing_professionRewards = builder.comment("Rewards configuration for the Fishing profession")
                .defineListAllowEmpty(
                        List.of("professionRewards"),
                        PROF_DEFAULTS::getDefaultFishingTiers,
                        null,
                        o -> o instanceof Config && ProfessionTierEntry.isValid(o)
                );
        builder.pop(); // pop "fishing"

        builder.comment("Catching Profession Configuration").push("catching");
        catching_professionName = builder.comment("Name of the Catching profession")
                .define("professionName", "Catching");
        catching_professionRewards = builder.comment("Rewards configuration for the Catching profession")
                .defineListAllowEmpty(
                        List.of("professionRewards"),
                        PROF_DEFAULTS::getDefaultCatchingTiers,
                        null,
                        o -> o instanceof Config && ProfessionTierEntry.isValid(o)
                );
        builder.pop(); // pop "catching"

        builder.comment("Training Profession Configuration").push("training");
        training_professionName = builder.comment("Name of the Training profession")
                .define("professionName", "Training");
        training_professionRewards = builder.comment("Rewards configuration for the Training profession")
                .defineListAllowEmpty(
                        List.of("professionRewards"),
                        PROF_DEFAULTS::getDefaultTrainingTiers,
                        null,
                        o -> o instanceof Config && ProfessionTierEntry.isValid(o)
                );
        builder.pop(); // pop "training"

        builder.comment("Breeding Profession Configuration").push("breeding");
        breeding_professionName = builder.comment("Name of the Breeding profession")
                .define("professionName", "Breeding");
        breeding_professionRewards = builder.comment("Rewards configuration for the Breeding profession")
                .defineListAllowEmpty(
                        List.of("professionRewards"),
                        PROF_DEFAULTS::getDefaultBreedingTiers,
                        null,
                        o -> o instanceof Config && ProfessionTierEntry.isValid(o)
                );
        builder.pop(); // pop "breeding"

        builder.pop(); // pop "professions"
    }
}
