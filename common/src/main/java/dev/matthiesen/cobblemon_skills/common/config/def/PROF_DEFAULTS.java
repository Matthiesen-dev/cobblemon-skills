package dev.matthiesen.cobblemon_skills.common.config.def;

import com.electronwill.nightconfig.core.Config;

import java.util.List;

public final class PROF_DEFAULTS {

    private static final List<ProfessionTierEntry> DEFAULT_ARCHEOLOGY_TIERS = List.of(
            new ProfessionTierEntry("Novice", 10, List.of(
                    new RewardsEntry(20),
                    new RewardsEntry("minecraft:iron_ingot", 3)
            )),
            new ProfessionTierEntry("Apprentice", 20, List.of(
                    new RewardsEntry(40),
                    new RewardsEntry("minecraft:gold_ingot", 2)
            )),
            new ProfessionTierEntry("Adept", 30, List.of(
                    new RewardsEntry(60),
                    new RewardsEntry("minecraft:diamond", 1)
            )),
            new ProfessionTierEntry("Expert", 40, List.of(
                    new RewardsEntry(80),
                    new RewardsEntry("cobblemon:tumblestone", 4)
            )),
            new ProfessionTierEntry("Master", 50, List.of(
                    new RewardsEntry(100),
                    new RewardsEntry("cobblemon:sky_tumblestone", 2)
            )),
            new ProfessionTierEntry("Grandmaster", 60, List.of(
                    new RewardsEntry(120),
                    new RewardsEntry("cobblemon:black_tumblestone", 3)
            )),
            new ProfessionTierEntry("Legendary", 70, List.of(
                    new RewardsEntry(150),
                    new RewardsEntry("cobblemon:fossilized_drake", 1)
            )),
            new ProfessionTierEntry("Mythical", 80, List.of(
                    new RewardsEntry(200),
                    new RewardsEntry("cobblemon:old_amber_fossil", 1)
            )),
            new ProfessionTierEntry("Divine", 90, List.of(
                    new RewardsEntry(250),
                    new RewardsEntry("cobblemon:relic_coin", 16)
            )),
            new ProfessionTierEntry("Transcendent", 100, List.of(
                    new RewardsEntry(500),
                    new RewardsEntry("cobblemon:relic_coin", 32),
                    new RewardsEntry("tellraw @a [\"\",{\"text\":\"{player}\",\"bold\":true,\"color\":\"aqua\"},{\"text\":\" has transcended in Archeology!\",\"color\":\"gold\"}]")
            ))
    );

    private static final List<ProfessionTierEntry> DEFAULT_BOTANY_TIERS = List.of(
            new ProfessionTierEntry("Novice", 10, List.of(
                    new RewardsEntry(20),
                    new RewardsEntry("minecraft:wheat_seeds", 5)
            )),
            new ProfessionTierEntry("Apprentice", 20, List.of(
                    new RewardsEntry(40),
                    new RewardsEntry("minecraft:beetroot_seeds", 5)
            )),
            new ProfessionTierEntry("Adept", 30, List.of(
                    new RewardsEntry(60),
                    new RewardsEntry("minecraft:melon_seeds", 5)
            )),
            new ProfessionTierEntry("Expert", 40, List.of(
                    new RewardsEntry(80),
                    new RewardsEntry("minecraft:pumpkin_seeds", 5)
            )),
            new ProfessionTierEntry("Master", 50, List.of(
                    new RewardsEntry(100),
                    new RewardsEntry("minecraft:nether_wart", 5)
            )),
            new ProfessionTierEntry("Grandmaster", 60, List.of(
                    new RewardsEntry(120),
                    new RewardsEntry("minecraft:glow_berries", 5),
                    new RewardsEntry("cobblemon:red_apricorn_seed", 2)
            )),
            new ProfessionTierEntry("Legendary", 70, List.of(
                    new RewardsEntry(150),
                    new RewardsEntry("cobblemon:blue_apricorn_seed", 2),
                    new RewardsEntry("cobblemon:green_apricorn_seed", 2)
            )),
            new ProfessionTierEntry("Mythical", 80, List.of(
                    new RewardsEntry(200),
                    new RewardsEntry("cobblemon:yellow_apricorn_seed", 2),
                    new RewardsEntry("cobblemon:pink_apricorn_seed", 2)
            )),
            new ProfessionTierEntry("Divine", 90, List.of(
                    new RewardsEntry(250),
                    new RewardsEntry("cobblemon:black_apricorn_seed", 2),
                    new RewardsEntry("cobblemon:white_apricorn_seed", 2)
            )),
            new ProfessionTierEntry("Transcendent", 100, List.of(
                    new RewardsEntry(500),
                    new RewardsEntry("cobblemon:galarica_nuts", 2),
                    new RewardsEntry("tellraw @a [\"\",{\"text\":\"{player}\",\"bold\":true,\"color\":\"aqua\"},{\"text\":\" has transcended in Botany!\",\"color\":\"gold\"}]")
            ))
    );

    private static final List<ProfessionTierEntry> DEFAULT_COOKING_TIERS = List.of(
            new ProfessionTierEntry("Novice", 10, List.of(
                    new RewardsEntry(20),
                    new RewardsEntry("minecraft:cooked_beef", 4)
            )),
            new ProfessionTierEntry("Apprentice", 20, List.of(
                    new RewardsEntry(40),
                    new RewardsEntry("minecraft:cooked_chicken", 8)
            )),
            new ProfessionTierEntry("Adept", 30, List.of(
                    new RewardsEntry(60),
                    new RewardsEntry("minecraft:cooked_porkchop", 8)
            )),
            new ProfessionTierEntry("Expert", 40, List.of(
                    new RewardsEntry(80),
                    new RewardsEntry("minecraft:cooked_mutton", 16)
            )),
            new ProfessionTierEntry("Master", 50, List.of(
                    new RewardsEntry(100),
                    new RewardsEntry("minecraft:cooked_cod", 16)
            )),
            new ProfessionTierEntry("Grandmaster", 60, List.of(
                    new RewardsEntry(120),
                    new RewardsEntry("minecraft:cooked_salmon", 16)
            )),
            new ProfessionTierEntry("Legendary", 70, List.of(
                    new RewardsEntry(150),
                    new RewardsEntry("minecraft:golden_carrot", 16)
            )),
            new ProfessionTierEntry("Mythical", 80, List.of(
                    new RewardsEntry(200),
                    new RewardsEntry("minecraft:enchanted_golden_apple", 1)
            )),
            new ProfessionTierEntry("Divine", 90, List.of(
                    new RewardsEntry(250),
                    new RewardsEntry("minecraft:enchanted_golden_apple", 3)
            )),
            new ProfessionTierEntry("Transcendent", 100, List.of(
                    new RewardsEntry(500),
                    new RewardsEntry("minecraft:enchanted_golden_apple", 6),
                    new RewardsEntry("tellraw @a [\"\",{\"text\":\"{player}\",\"bold\":true,\"color\":\"aqua\"},{\"text\":\" has transcended in Cooking!\",\"color\":\"gold\"}]")
            ))
    );

    private static final List<ProfessionTierEntry> DEFAULT_FISHING_TIERS = List.of(
            new ProfessionTierEntry("Novice", 10, List.of(
                    new RewardsEntry(20),
                    new RewardsEntry("minecraft:cod", 4)
            )),
            new ProfessionTierEntry("Apprentice", 20, List.of(
                    new RewardsEntry(40),
                    new RewardsEntry("minecraft:salmon", 4)
            )),
            new ProfessionTierEntry("Adept", 30, List.of(
                    new RewardsEntry(60),
                    new RewardsEntry("minecraft:tropical_fish", 4)
            )),
            new ProfessionTierEntry("Expert", 40, List.of(
                    new RewardsEntry(80),
                    new RewardsEntry("minecraft:pufferfish", 4)
            )),
            new ProfessionTierEntry("Master", 50, List.of(
                    new RewardsEntry(100),
                    new RewardsEntry("minecraft:nautilus_shell", 2)
            )),
            new ProfessionTierEntry("Grandmaster", 60, List.of(
                    new RewardsEntry(120),
                    new RewardsEntry("minecraft:heart_of_the_sea", 1)
            )),
            new ProfessionTierEntry("Legendary", 70, List.of(
                    new RewardsEntry(150),
                    new RewardsEntry("minecraft:trident", 1)
            )),
            new ProfessionTierEntry("Mythical", 80, List.of(
                    new RewardsEntry(200),
                    new RewardsEntry("minecraft:conduit", 1)
            )),
            new ProfessionTierEntry("Divine", 90, List.of(
                    new RewardsEntry(250),
                    new RewardsEntry("minecraft:heart_of_the_sea", 2)
            )),
            new ProfessionTierEntry("Transcendent", 100, List.of(
                    new RewardsEntry(500),
                    new RewardsEntry("cobblemon:master_rod", 1),
                    new RewardsEntry("tellraw @a [\"\",{\"text\":\"{player}\",\"bold\":true,\"color\":\"aqua\"},{\"text\":\" has transcended in Fishing!\",\"color\":\"gold\"}]")
            ))
    );

    private static final List<ProfessionTierEntry> DEFAULT_CATCHING_TIERS = List.of(
            new ProfessionTierEntry("Novice", 10, List.of(
                    new RewardsEntry(20),
                    new RewardsEntry("cobblemon:poke_ball", 8)
            )),
            new ProfessionTierEntry("Apprentice", 20, List.of(
                    new RewardsEntry(40),
                    new RewardsEntry("cobblemon:great_ball", 8)
            )),
            new ProfessionTierEntry("Adept", 30, List.of(
                    new RewardsEntry(60),
                    new RewardsEntry("cobblemon:ultra_ball", 8)
            )),
            new ProfessionTierEntry("Expert", 40, List.of(
                    new RewardsEntry(80),
                    new RewardsEntry("cobblemon:master_ball", 1)
            )),
            new ProfessionTierEntry("Master", 50, List.of(
                    new RewardsEntry(100),
                    new RewardsEntry("cobblemon:ultra_ball", 16),
                    new RewardsEntry("cobblemon:timer_ball", 8)
            )),
            new ProfessionTierEntry("Grandmaster", 60, List.of(
                    new RewardsEntry(120),
                    new RewardsEntry("cobblemon:dusk_ball", 8),
                    new RewardsEntry("cobblemon:quick_ball", 8)
            )),
            new ProfessionTierEntry("Legendary", 70, List.of(
                    new RewardsEntry(150),
                    new RewardsEntry("cobblemon:repeat_ball", 8),
                    new RewardsEntry("cobblemon:net_ball", 8)
            )),
            new ProfessionTierEntry("Mythical", 80, List.of(
                    new RewardsEntry(200),
                    new RewardsEntry("cobblemon:luxury_ball", 8),
                    new RewardsEntry("cobblemon:dive_ball", 8)
            )),
            new ProfessionTierEntry("Divine", 90, List.of(
                    new RewardsEntry(250),
                    new RewardsEntry("cobblemon:beast_ball", 8),
                    new RewardsEntry("cobblemon:heal_ball", 8)
            )),
            new ProfessionTierEntry("Transcendent", 100, List.of(
                    new RewardsEntry(500),
                    new RewardsEntry("cobblemon:master_ball", 2),
                    new RewardsEntry("tellraw @a [\"\",{\"text\":\"{player}\",\"bold\":true,\"color\":\"aqua\"},{\"text\":\" has transcended in Catching!\",\"color\":\"gold\"}]")
            ))
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
