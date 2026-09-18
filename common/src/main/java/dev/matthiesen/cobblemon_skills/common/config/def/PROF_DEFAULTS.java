package dev.matthiesen.cobblemon_skills.common.config.def;

import com.electronwill.nightconfig.core.Config;

import java.util.List;

public final class PROF_DEFAULTS {

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

    public static List<Config> getDefaultArcheologyTiers() {
        return DEFAULT_ARCHEOLOGY_TIERS.stream()
                .map(ProfessionTierEntry::serialize)
                .toList();
    }

    public static List<Config> getDefaultBotanyTiers() {
        return DEFAULT_BOTANY_TIERS.stream()
                .map(ProfessionTierEntry::serialize)
                .toList();
    }

    public static List<Config> getDefaultCookingTiers() {
        return DEFAULT_COOKING_TIERS.stream()
                .map(ProfessionTierEntry::serialize)
                .toList();
    }

    public static List<Config> getDefaultFishingTiers() {
        return DEFAULT_FISHING_TIERS.stream()
                .map(ProfessionTierEntry::serialize)
                .toList();
    }

    public static List<Config> getDefaultCatchingTiers() {
        return DEFAULT_CATCHING_TIERS.stream()
                .map(ProfessionTierEntry::serialize)
                .toList();
    }

    public static List<Config> getDefaultTrainingTiers() {
        return DEFAULT_TRAINING_TIERS.stream()
                .map(ProfessionTierEntry::serialize)
                .toList();
    }

    public static List<Config> getDefaultBreedingTiers() {
        return DEFAULT_BREEDING_TIERS.stream()
                .map(ProfessionTierEntry::serialize)
                .toList();
    }
}
