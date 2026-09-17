package dev.matthiesen.cobblemon_skills.common.config;

import com.electronwill.nightconfig.core.Config;
import dev.matthiesen.cobblemon_skills.common.config.def.ProfessionTierEntry;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

public final class ProfessionsConfig {

    // Archeology
    public ModConfigSpec.ConfigValue<List<? extends Config>> archeology_professionRewards;

    private static final List<ProfessionTierEntry> DEFAULT_ARCHEOLOGY_TIERS = List.of(
            new ProfessionTierEntry("Novice", 10),
            new ProfessionTierEntry("Apprentice", 20),
            new ProfessionTierEntry("Adept", 30),
            new ProfessionTierEntry("Expert", 40),
            new ProfessionTierEntry("Master", 50),
            new ProfessionTierEntry("Grandmaster", 60),
            new ProfessionTierEntry("Legendary", 70),
            new ProfessionTierEntry("Mythical", 80),
            new ProfessionTierEntry("Divine", 90),
            new ProfessionTierEntry("Transcendent", 100)
    );

    public static List<Config> getDefaultArcheologyTiers() {
        return DEFAULT_ARCHEOLOGY_TIERS.stream()
                .map(ProfessionTierEntry::serialize)
                .toList();
    }

    // Botany
    public ModConfigSpec.ConfigValue<List<? extends Config>> botany_professionRewards;

    private static final List<ProfessionTierEntry> DEFAULT_BOTANY_TIERS = List.of(
            new ProfessionTierEntry("Novice", 10),
            new ProfessionTierEntry("Apprentice", 20),
            new ProfessionTierEntry("Adept", 30),
            new ProfessionTierEntry("Expert", 40),
            new ProfessionTierEntry("Master", 50),
            new ProfessionTierEntry("Grandmaster", 60),
            new ProfessionTierEntry("Legendary", 70),
            new ProfessionTierEntry("Mythical", 80),
            new ProfessionTierEntry("Divine", 90),
            new ProfessionTierEntry("Transcendent", 100)
    );

    public static List<Config> getDefaultBotanyTiers() {
        return DEFAULT_BOTANY_TIERS.stream()
                .map(ProfessionTierEntry::serialize)
                .toList();
    }

    // Cooking
    public ModConfigSpec.ConfigValue<List<? extends Config>> cooking_professionRewards;

    private static final List<ProfessionTierEntry> DEFAULT_COOKING_TIERS = List.of(
            new ProfessionTierEntry("Novice", 10),
            new ProfessionTierEntry("Apprentice", 20),
            new ProfessionTierEntry("Adept", 30),
            new ProfessionTierEntry("Expert", 40),
            new ProfessionTierEntry("Master", 50),
            new ProfessionTierEntry("Grandmaster", 60),
            new ProfessionTierEntry("Legendary", 70),
            new ProfessionTierEntry("Mythical", 80),
            new ProfessionTierEntry("Divine", 90),
            new ProfessionTierEntry("Transcendent", 100)
    );

    public static List<Config> getDefaultCookingTiers() {
        return DEFAULT_COOKING_TIERS.stream()
                .map(ProfessionTierEntry::serialize)
                .toList();
    }

    // Fishing
    public ModConfigSpec.ConfigValue<List<? extends Config>> fishing_professionRewards;

    private static final List<ProfessionTierEntry> DEFAULT_FISHING_TIERS = List.of(
            new ProfessionTierEntry("Novice", 10),
            new ProfessionTierEntry("Apprentice", 20),
            new ProfessionTierEntry("Adept", 30),
            new ProfessionTierEntry("Expert", 40),
            new ProfessionTierEntry("Master", 50),
            new ProfessionTierEntry("Grandmaster", 60),
            new ProfessionTierEntry("Legendary", 70),
            new ProfessionTierEntry("Mythical", 80),
            new ProfessionTierEntry("Divine", 90),
            new ProfessionTierEntry("Transcendent", 100)
    );

    public static List<Config> getDefaultFishingTiers() {
        return DEFAULT_FISHING_TIERS.stream()
                .map(ProfessionTierEntry::serialize)
                .toList();
    }

    // Catching
    public ModConfigSpec.ConfigValue<List<? extends Config>> catching_professionRewards;

    private static final List<ProfessionTierEntry> DEFAULT_CATCHING_TIERS = List.of(
            new ProfessionTierEntry("Novice", 10),
            new ProfessionTierEntry("Apprentice", 20),
            new ProfessionTierEntry("Adept", 30),
            new ProfessionTierEntry("Expert", 40),
            new ProfessionTierEntry("Master", 50),
            new ProfessionTierEntry("Grandmaster", 60),
            new ProfessionTierEntry("Legendary", 70),
            new ProfessionTierEntry("Mythical", 80),
            new ProfessionTierEntry("Divine", 90),
            new ProfessionTierEntry("Transcendent", 100)
    );

    public static List<Config> getDefaultCatchingTiers() {
        return DEFAULT_CATCHING_TIERS.stream()
                .map(ProfessionTierEntry::serialize)
                .toList();
    }

    // Training
    public ModConfigSpec.ConfigValue<List<? extends Config>> training_professionRewards;

    private static final List<ProfessionTierEntry> DEFAULT_TRAINING_TIERS = List.of(
            new ProfessionTierEntry("Novice", 10),
            new ProfessionTierEntry("Apprentice", 20),
            new ProfessionTierEntry("Adept", 30),
            new ProfessionTierEntry("Expert", 40),
            new ProfessionTierEntry("Master", 50),
            new ProfessionTierEntry("Grandmaster", 60),
            new ProfessionTierEntry("Legendary", 70),
            new ProfessionTierEntry("Mythical", 80),
            new ProfessionTierEntry("Divine", 90),
            new ProfessionTierEntry("Transcendent", 100)
    );

    public static List<Config> getDefaultTrainingTiers() {
        return DEFAULT_TRAINING_TIERS.stream()
                .map(ProfessionTierEntry::serialize)
                .toList();
    }

    // Breeding
    public ModConfigSpec.ConfigValue<List<? extends Config>> breeding_professionRewards;

    private static final List<ProfessionTierEntry> DEFAULT_BREEDING_TIERS = List.of(
            new ProfessionTierEntry("Novice", 10),
            new ProfessionTierEntry("Apprentice", 20),
            new ProfessionTierEntry("Adept", 30),
            new ProfessionTierEntry("Expert", 40),
            new ProfessionTierEntry("Master", 50),
            new ProfessionTierEntry("Grandmaster", 60),
            new ProfessionTierEntry("Legendary", 70),
            new ProfessionTierEntry("Mythical", 80),
            new ProfessionTierEntry("Divine", 90),
            new ProfessionTierEntry("Transcendent", 100)
    );

    public static List<Config> getDefaultBreedingTiers() {
        return DEFAULT_BREEDING_TIERS.stream()
                .map(ProfessionTierEntry::serialize)
                .toList();
    }

    public ProfessionsConfig(ModConfigSpec.Builder builder) {
        builder.comment("Professions configuration for Cobblemon Skills").push("professions");

        builder.comment("Archeology Profession Configuration").push("archeology");
        archeology_professionRewards = builder.comment("Rewards configuration for the Archeology profession")
                .defineListAllowEmpty(
                        List.of("professionRewards"),
                        ProfessionsConfig::getDefaultArcheologyTiers,
                        null,
                        o -> o instanceof Config && ProfessionTierEntry.isValid(o)
                );
        builder.pop(); // pop "archeology"

        builder.comment("Botany Profession Configuration").push("botany");
        botany_professionRewards = builder.comment("Rewards configuration for the Botany profession")
                .defineListAllowEmpty(
                        List.of("professionRewards"),
                        ProfessionsConfig::getDefaultBotanyTiers,
                        null,
                        o -> o instanceof Config && ProfessionTierEntry.isValid(o)
                );
        builder.pop(); // pop "botany"

        builder.comment("Cooking Profession Configuration").push("cooking");
        cooking_professionRewards = builder.comment("Rewards configuration for the Cooking profession")
                .defineListAllowEmpty(
                        List.of("professionRewards"),
                        ProfessionsConfig::getDefaultCookingTiers,
                        null,
                        o -> o instanceof Config && ProfessionTierEntry.isValid(o)
                );
        builder.pop(); // pop "cooking"

        builder.comment("Fishing Profession Configuration").push("fishing");
        fishing_professionRewards = builder.comment("Rewards configuration for the Fishing profession")
                .defineListAllowEmpty(
                        List.of("professionRewards"),
                        ProfessionsConfig::getDefaultFishingTiers,
                        null,
                        o -> o instanceof Config && ProfessionTierEntry.isValid(o)
                );
        builder.pop(); // pop "fishing"

        builder.comment("Catching Profession Configuration").push("catching");
        catching_professionRewards = builder.comment("Rewards configuration for the Catching profession")
                .defineListAllowEmpty(
                        List.of("professionRewards"),
                        ProfessionsConfig::getDefaultCatchingTiers,
                        null,
                        o -> o instanceof Config && ProfessionTierEntry.isValid(o)
                );
        builder.pop(); // pop "catching"

        builder.comment("Training Profession Configuration").push("training");
        training_professionRewards = builder.comment("Rewards configuration for the Training profession")
                .defineListAllowEmpty(
                        List.of("professionRewards"),
                        ProfessionsConfig::getDefaultTrainingTiers,
                        null,
                        o -> o instanceof Config && ProfessionTierEntry.isValid(o)
                );
        builder.pop(); // pop "training"

        builder.comment("Breeding Profession Configuration").push("breeding");
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
