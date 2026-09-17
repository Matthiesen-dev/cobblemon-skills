package dev.matthiesen.cobblemon_skills.common.config;

import com.electronwill.nightconfig.core.Config;
import dev.matthiesen.cobblemon_skills.common.config.def.ProfessionTierEntry;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

public final class ProfessionsConfig {

    // Archeology
    public ModConfigSpec.ConfigValue<String> archeology_professionName;
    public ModConfigSpec.ConfigValue<List<? extends Config>> archeology_professionRewards;

    private static final List<ProfessionTierEntry> DEFAULT_ARCHEOLOGY_TIERS = List.of(
            new ProfessionTierEntry("Novice", 10, List.of()),
            new ProfessionTierEntry("Apprentice", 20, List.of()),
            new ProfessionTierEntry("Adept", 30, List.of()),
            new ProfessionTierEntry("Expert", 40, List.of()),
            new ProfessionTierEntry("Master", 50, List.of()),
            new ProfessionTierEntry("Grandmaster", 60, List.of()),
            new ProfessionTierEntry("Legendary", 70, List.of()),
            new ProfessionTierEntry("Mythical", 80, List.of()),
            new ProfessionTierEntry("Divine", 90, List.of()),
            new ProfessionTierEntry("Transcendent", 100, List.of())
    );

    public static List<Config> getDefaultArcheologyTiers() {
        return DEFAULT_ARCHEOLOGY_TIERS.stream()
                .map(ProfessionTierEntry::serialize)
                .toList();
    }

    // Botany
    public ModConfigSpec.ConfigValue<String> botany_professionName;
    public ModConfigSpec.ConfigValue<List<? extends Config>> botany_professionRewards;

    private static final List<ProfessionTierEntry> DEFAULT_BOTANY_TIERS = List.of(
            new ProfessionTierEntry("Novice", 10, List.of()),
            new ProfessionTierEntry("Apprentice", 20, List.of()),
            new ProfessionTierEntry("Adept", 30, List.of()),
            new ProfessionTierEntry("Expert", 40, List.of()),
            new ProfessionTierEntry("Master", 50, List.of()),
            new ProfessionTierEntry("Grandmaster", 60, List.of()),
            new ProfessionTierEntry("Legendary", 70, List.of()),
            new ProfessionTierEntry("Mythical", 80, List.of()),
            new ProfessionTierEntry("Divine", 90, List.of()),
            new ProfessionTierEntry("Transcendent", 100, List.of())
    );

    public static List<Config> getDefaultBotanyTiers() {
        return DEFAULT_BOTANY_TIERS.stream()
                .map(ProfessionTierEntry::serialize)
                .toList();
    }

    // Cooking
    public ModConfigSpec.ConfigValue<String> cooking_professionName;
    public ModConfigSpec.ConfigValue<List<? extends Config>> cooking_professionRewards;

    private static final List<ProfessionTierEntry> DEFAULT_COOKING_TIERS = List.of(
            new ProfessionTierEntry("Novice", 10, List.of()),
            new ProfessionTierEntry("Apprentice", 20, List.of()),
            new ProfessionTierEntry("Adept", 30, List.of()),
            new ProfessionTierEntry("Expert", 40, List.of()),
            new ProfessionTierEntry("Master", 50, List.of()),
            new ProfessionTierEntry("Grandmaster", 60, List.of()),
            new ProfessionTierEntry("Legendary", 70, List.of()),
            new ProfessionTierEntry("Mythical", 80, List.of()),
            new ProfessionTierEntry("Divine", 90, List.of()),
            new ProfessionTierEntry("Transcendent", 100, List.of())
    );

    public static List<Config> getDefaultCookingTiers() {
        return DEFAULT_COOKING_TIERS.stream()
                .map(ProfessionTierEntry::serialize)
                .toList();
    }

    // Fishing
    public ModConfigSpec.ConfigValue<String> fishing_professionName;
    public ModConfigSpec.ConfigValue<List<? extends Config>> fishing_professionRewards;

    private static final List<ProfessionTierEntry> DEFAULT_FISHING_TIERS = List.of(
            new ProfessionTierEntry("Novice", 10, List.of()),
            new ProfessionTierEntry("Apprentice", 20, List.of()),
            new ProfessionTierEntry("Adept", 30, List.of()),
            new ProfessionTierEntry("Expert", 40, List.of()),
            new ProfessionTierEntry("Master", 50, List.of()),
            new ProfessionTierEntry("Grandmaster", 60, List.of()),
            new ProfessionTierEntry("Legendary", 70, List.of()),
            new ProfessionTierEntry("Mythical", 80, List.of()),
            new ProfessionTierEntry("Divine", 90, List.of()),
            new ProfessionTierEntry("Transcendent", 100, List.of())
    );

    public static List<Config> getDefaultFishingTiers() {
        return DEFAULT_FISHING_TIERS.stream()
                .map(ProfessionTierEntry::serialize)
                .toList();
    }

    // Catching
    public ModConfigSpec.ConfigValue<String> catching_professionName;
    public ModConfigSpec.ConfigValue<List<? extends Config>> catching_professionRewards;

    private static final List<ProfessionTierEntry> DEFAULT_CATCHING_TIERS = List.of(
            new ProfessionTierEntry("Novice", 10, List.of()),
            new ProfessionTierEntry("Apprentice", 20, List.of()),
            new ProfessionTierEntry("Adept", 30, List.of()),
            new ProfessionTierEntry("Expert", 40, List.of()),
            new ProfessionTierEntry("Master", 50, List.of()),
            new ProfessionTierEntry("Grandmaster", 60, List.of()),
            new ProfessionTierEntry("Legendary", 70, List.of()),
            new ProfessionTierEntry("Mythical", 80, List.of()),
            new ProfessionTierEntry("Divine", 90, List.of()),
            new ProfessionTierEntry("Transcendent", 100, List.of())
    );

    public static List<Config> getDefaultCatchingTiers() {
        return DEFAULT_CATCHING_TIERS.stream()
                .map(ProfessionTierEntry::serialize)
                .toList();
    }

    // Training
    public ModConfigSpec.ConfigValue<String> training_professionName;
    public ModConfigSpec.ConfigValue<List<? extends Config>> training_professionRewards;

    private static final List<ProfessionTierEntry> DEFAULT_TRAINING_TIERS = List.of(
            new ProfessionTierEntry("Novice", 10, List.of()),
            new ProfessionTierEntry("Apprentice", 20, List.of()),
            new ProfessionTierEntry("Adept", 30, List.of()),
            new ProfessionTierEntry("Expert", 40, List.of()),
            new ProfessionTierEntry("Master", 50, List.of()),
            new ProfessionTierEntry("Grandmaster", 60, List.of()),
            new ProfessionTierEntry("Legendary", 70, List.of()),
            new ProfessionTierEntry("Mythical", 80, List.of()),
            new ProfessionTierEntry("Divine", 90, List.of()),
            new ProfessionTierEntry("Transcendent", 100, List.of())
    );

    public static List<Config> getDefaultTrainingTiers() {
        return DEFAULT_TRAINING_TIERS.stream()
                .map(ProfessionTierEntry::serialize)
                .toList();
    }

    // Breeding
    public ModConfigSpec.ConfigValue<String> breeding_professionName;
    public ModConfigSpec.ConfigValue<List<? extends Config>> breeding_professionRewards;

    private static final List<ProfessionTierEntry> DEFAULT_BREEDING_TIERS = List.of(
            new ProfessionTierEntry("Novice", 10, List.of()),
            new ProfessionTierEntry("Apprentice", 20, List.of()),
            new ProfessionTierEntry("Adept", 30, List.of()),
            new ProfessionTierEntry("Expert", 40, List.of()),
            new ProfessionTierEntry("Master", 50, List.of()),
            new ProfessionTierEntry("Grandmaster", 60, List.of()),
            new ProfessionTierEntry("Legendary", 70, List.of()),
            new ProfessionTierEntry("Mythical", 80, List.of()),
            new ProfessionTierEntry("Divine", 90, List.of()),
            new ProfessionTierEntry("Transcendent", 100, List.of())
    );

    public static List<Config> getDefaultBreedingTiers() {
        return DEFAULT_BREEDING_TIERS.stream()
                .map(ProfessionTierEntry::serialize)
                .toList();
    }

    public ProfessionsConfig(ModConfigSpec.Builder builder) {
        builder.comment("Professions configuration for Cobblemon Skills").push("professions");

        builder.comment("Archeology Profession Configuration").push("archeology");
        archeology_professionName = builder.comment("Name of the Archeology profession")
                .define("professionName", "Archeology");
        archeology_professionRewards = builder.comment("Rewards configuration for the Archeology profession")
                .defineListAllowEmpty(
                        List.of("professionRewards"),
                        ProfessionsConfig::getDefaultArcheologyTiers,
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
                        ProfessionsConfig::getDefaultBotanyTiers,
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
                        ProfessionsConfig::getDefaultCookingTiers,
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
                        ProfessionsConfig::getDefaultFishingTiers,
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
                        ProfessionsConfig::getDefaultCatchingTiers,
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
                        ProfessionsConfig::getDefaultTrainingTiers,
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
                        ProfessionsConfig::getDefaultBreedingTiers,
                        null,
                        o -> o instanceof Config && ProfessionTierEntry.isValid(o)
                );
        builder.pop(); // pop "breeding"

        builder.pop(); // pop "professions"
    }
}
