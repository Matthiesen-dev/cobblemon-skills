package dev.matthiesen.cobblemon_skills.common.runtime;

import com.cobblemon.mod.common.CobblemonBlocks;
import net.minecraft.world.level.block.Block;

import java.util.Map;

public final class ExperienceMaps {
    private ExperienceMaps() {}

    private static final Integer ORE_BLOCK_XP = 1800;
    private static final Integer DEEPSLATE_ORE_BLOCK_XP = 2600;
    private static final Integer MISC_ORE_BLOCK_XP = 2200;
    private static final Integer TUMBLESTONE_CLUSTER_SMALL_XP = 100;
    private static final Integer TUMBLESTONE_CLUSTER_MEDIUM_XP = 120;
    private static final Integer TUMBLESTONE_CLUSTER_LARGE_XP = 140;
    private static final Integer TUMBLESTONE_CLUSTER_FULL_XP = 180;
    private static final Integer TYPE_CLUSTER_XP = 200;
    private static final Integer RELIC_COIN_SACK_XP = 900;
    private static final Integer RELIC_COIN_POUCH_XP = 600;
    private static final Integer COBBLEMON_WOODCUTTING_XP = 80;
    private static final Integer COBBLEMON_WOODCUTTING_EXTRA_XP = 120;
    private static final Integer COBBLEMON_FARMING_XP = 150;
    private static final Integer COBBLEMON_BERRY_FARMING_XP = 200;

    private static final Map<Block, Integer> COBBLEMON_ARCHEOLOGY_BLOCKS = Map.<Block, Integer>ofEntries(
            // Ores
            Map.entry(CobblemonBlocks.DAWN_STONE_ORE, ORE_BLOCK_XP),
            Map.entry(CobblemonBlocks.DUSK_STONE_ORE, ORE_BLOCK_XP),
            Map.entry(CobblemonBlocks.FIRE_STONE_ORE, ORE_BLOCK_XP),
            Map.entry(CobblemonBlocks.ICE_STONE_ORE, ORE_BLOCK_XP),
            Map.entry(CobblemonBlocks.LEAF_STONE_ORE, ORE_BLOCK_XP),
            Map.entry(CobblemonBlocks.MOON_STONE_ORE, ORE_BLOCK_XP),
            Map.entry(CobblemonBlocks.SHINY_STONE_ORE, ORE_BLOCK_XP),
            Map.entry(CobblemonBlocks.SUN_STONE_ORE, ORE_BLOCK_XP),
            Map.entry(CobblemonBlocks.THUNDER_STONE_ORE, ORE_BLOCK_XP),
            Map.entry(CobblemonBlocks.WATER_STONE_ORE, ORE_BLOCK_XP),
            Map.entry(CobblemonBlocks.DEEPSLATE_DAWN_STONE_ORE, DEEPSLATE_ORE_BLOCK_XP),
            Map.entry(CobblemonBlocks.DEEPSLATE_DUSK_STONE_ORE, DEEPSLATE_ORE_BLOCK_XP),
            Map.entry(CobblemonBlocks.DEEPSLATE_FIRE_STONE_ORE, DEEPSLATE_ORE_BLOCK_XP),
            Map.entry(CobblemonBlocks.DEEPSLATE_ICE_STONE_ORE, DEEPSLATE_ORE_BLOCK_XP),
            Map.entry(CobblemonBlocks.DEEPSLATE_LEAF_STONE_ORE, DEEPSLATE_ORE_BLOCK_XP),
            Map.entry(CobblemonBlocks.DEEPSLATE_MOON_STONE_ORE, DEEPSLATE_ORE_BLOCK_XP),
            Map.entry(CobblemonBlocks.DEEPSLATE_SHINY_STONE_ORE, DEEPSLATE_ORE_BLOCK_XP),
            Map.entry(CobblemonBlocks.DEEPSLATE_SUN_STONE_ORE, DEEPSLATE_ORE_BLOCK_XP),
            Map.entry(CobblemonBlocks.DEEPSLATE_THUNDER_STONE_ORE, DEEPSLATE_ORE_BLOCK_XP),
            Map.entry(CobblemonBlocks.DEEPSLATE_WATER_STONE_ORE, DEEPSLATE_ORE_BLOCK_XP),
            Map.entry(CobblemonBlocks.DRIPSTONE_MOON_STONE_ORE, MISC_ORE_BLOCK_XP),
            Map.entry(CobblemonBlocks.NETHER_FIRE_STONE_ORE, MISC_ORE_BLOCK_XP),
            Map.entry(CobblemonBlocks.TERRACOTTA_SUN_STONE_ORE, MISC_ORE_BLOCK_XP),

            // Tumblestone Clusters
            Map.entry(CobblemonBlocks.SMALL_BUDDING_TUMBLESTONE, TUMBLESTONE_CLUSTER_SMALL_XP),
            Map.entry(CobblemonBlocks.SMALL_BUDDING_SKY_TUMBLESTONE, TUMBLESTONE_CLUSTER_SMALL_XP),
            Map.entry(CobblemonBlocks.SMALL_BUDDING_BLACK_TUMBLESTONE, TUMBLESTONE_CLUSTER_SMALL_XP),
            Map.entry(CobblemonBlocks.MEDIUM_BUDDING_TUMBLESTONE, TUMBLESTONE_CLUSTER_MEDIUM_XP),
            Map.entry(CobblemonBlocks.MEDIUM_BUDDING_SKY_TUMBLESTONE, TUMBLESTONE_CLUSTER_MEDIUM_XP),
            Map.entry(CobblemonBlocks.MEDIUM_BUDDING_BLACK_TUMBLESTONE, TUMBLESTONE_CLUSTER_MEDIUM_XP),
            Map.entry(CobblemonBlocks.LARGE_BUDDING_TUMBLESTONE, TUMBLESTONE_CLUSTER_LARGE_XP),
            Map.entry(CobblemonBlocks.LARGE_BUDDING_SKY_TUMBLESTONE, TUMBLESTONE_CLUSTER_LARGE_XP),
            Map.entry(CobblemonBlocks.LARGE_BUDDING_BLACK_TUMBLESTONE, TUMBLESTONE_CLUSTER_LARGE_XP),
            Map.entry(CobblemonBlocks.TUMBLESTONE_CLUSTER, TUMBLESTONE_CLUSTER_FULL_XP),
            Map.entry(CobblemonBlocks.SKY_TUMBLESTONE_CLUSTER, TUMBLESTONE_CLUSTER_FULL_XP),
            Map.entry(CobblemonBlocks.BLACK_TUMBLESTONE_CLUSTER, TUMBLESTONE_CLUSTER_FULL_XP),

            // Type Gem Clusters
            Map.entry(CobblemonBlocks.TYPE_GEM_CLUSTER_DARK, TYPE_CLUSTER_XP),
            Map.entry(CobblemonBlocks.TYPE_GEM_CLUSTER_DRAGON, TYPE_CLUSTER_XP),
            Map.entry(CobblemonBlocks.TYPE_GEM_CLUSTER_ELECTRIC, TYPE_CLUSTER_XP),
            Map.entry(CobblemonBlocks.TYPE_GEM_CLUSTER_FAIRY, TYPE_CLUSTER_XP),
            Map.entry(CobblemonBlocks.TYPE_GEM_CLUSTER_FIGHTING, TYPE_CLUSTER_XP),
            Map.entry(CobblemonBlocks.TYPE_GEM_CLUSTER_FIRE, TYPE_CLUSTER_XP),
            Map.entry(CobblemonBlocks.TYPE_GEM_CLUSTER_FLYING, TYPE_CLUSTER_XP),
            Map.entry(CobblemonBlocks.TYPE_GEM_CLUSTER_GHOST, TYPE_CLUSTER_XP),
            Map.entry(CobblemonBlocks.TYPE_GEM_CLUSTER_GRASS, TYPE_CLUSTER_XP),
            Map.entry(CobblemonBlocks.TYPE_GEM_CLUSTER_GROUND, TYPE_CLUSTER_XP),
            Map.entry(CobblemonBlocks.TYPE_GEM_CLUSTER_ICE, TYPE_CLUSTER_XP),
            Map.entry(CobblemonBlocks.TYPE_GEM_CLUSTER_NORMAL, TYPE_CLUSTER_XP),
            Map.entry(CobblemonBlocks.TYPE_GEM_CLUSTER_POISON, TYPE_CLUSTER_XP),
            Map.entry(CobblemonBlocks.TYPE_GEM_CLUSTER_PSYCHIC, TYPE_CLUSTER_XP),
            Map.entry(CobblemonBlocks.TYPE_GEM_CLUSTER_ROCK, TYPE_CLUSTER_XP),
            Map.entry(CobblemonBlocks.TYPE_GEM_CLUSTER_STEEL, TYPE_CLUSTER_XP),
            Map.entry(CobblemonBlocks.TYPE_GEM_CLUSTER_WATER, TYPE_CLUSTER_XP),

            // Miscellaneous
            Map.entry(CobblemonBlocks.RELIC_COIN_SACK, RELIC_COIN_SACK_XP),
            Map.entry(CobblemonBlocks.RELIC_COIN_POUCH, RELIC_COIN_POUCH_XP)
    );

    private static final Map<Block, Integer> COBBLEMON_BOTANY_BLOCKS = Map.<Block, Integer>ofEntries(
            Map.entry(CobblemonBlocks.APRICORN_LOG, COBBLEMON_WOODCUTTING_XP),
            Map.entry(CobblemonBlocks.APRICORN_LEAVES, COBBLEMON_WOODCUTTING_XP),
            Map.entry(CobblemonBlocks.SACCHARINE_LEAVES, COBBLEMON_WOODCUTTING_XP),
            Map.entry(CobblemonBlocks.SACCHARINE_LOG, COBBLEMON_WOODCUTTING_XP),
            Map.entry(CobblemonBlocks.SACCHARINE_LOG_SLATHERED, COBBLEMON_WOODCUTTING_EXTRA_XP),
            Map.entry(CobblemonBlocks.BLACK_APRICORN, COBBLEMON_FARMING_XP),
            Map.entry(CobblemonBlocks.BLUE_APRICORN, COBBLEMON_FARMING_XP),
            Map.entry(CobblemonBlocks.GREEN_APRICORN, COBBLEMON_FARMING_XP),
            Map.entry(CobblemonBlocks.PINK_APRICORN, COBBLEMON_FARMING_XP),
            Map.entry(CobblemonBlocks.RED_APRICORN, COBBLEMON_FARMING_XP),
            Map.entry(CobblemonBlocks.WHITE_APRICORN, COBBLEMON_FARMING_XP),
            Map.entry(CobblemonBlocks.YELLOW_APRICORN, COBBLEMON_FARMING_XP),
            Map.entry(CobblemonBlocks.HEARTY_GRAINS, COBBLEMON_FARMING_XP),
            Map.entry(CobblemonBlocks.MEDICINAL_LEEK, COBBLEMON_FARMING_XP),
            Map.entry(CobblemonBlocks.BLUE_MINT, COBBLEMON_FARMING_XP),
            Map.entry(CobblemonBlocks.CYAN_MINT, COBBLEMON_FARMING_XP),
            Map.entry(CobblemonBlocks.GREEN_MINT, COBBLEMON_FARMING_XP),
            Map.entry(CobblemonBlocks.PINK_MINT, COBBLEMON_FARMING_XP),
//            Map.entry(CobblemonBlocks.RED_MINT, COBBLEMON_FARMING_XP), // TODO: Red mint currently is "private" by Cobblemon (see https://gitlab.com/cable-mc/cobblemon/-/merge_requests/2419)
            Map.entry(CobblemonBlocks.WHITE_MINT, COBBLEMON_FARMING_XP),
            Map.entry(CobblemonBlocks.GALARICA_NUT_BUSH, COBBLEMON_FARMING_XP),
            Map.entry(CobblemonBlocks.BIG_ROOT, COBBLEMON_FARMING_XP),
            Map.entry(CobblemonBlocks.REVIVAL_HERB, COBBLEMON_FARMING_XP),
            Map.entry(CobblemonBlocks.ENERGY_ROOT, COBBLEMON_FARMING_XP),

            // Berries
            Map.entry(CobblemonBlocks.AGUAV_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.APICOT_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.ASPEAR_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.BABIRI_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.BELUE_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.BLUK_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.CHARTI_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.CHERI_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.CHESTO_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.CHILAN_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.CHOPLE_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.COBA_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.COLBUR_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.CORNN_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.DURIN_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.ENIGMA_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.FIGY_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.GANLON_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.GREPA_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.HABAN_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.HONDEW_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.JABOCA_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.KELPSY_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.LANSAT_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.LEPPA_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.LIECHI_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.LUM_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.MARANGA_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.MICLE_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.NANAB_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.NOMEL_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.OCCA_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.ORAN_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.PASSHO_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.PAYAPA_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.PERSIM_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.PETAYA_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.PINAP_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.POMEG_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.QUALOT_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.RAZZ_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.RINDO_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.ROWAP_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.SALAC_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.SITRUS_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.STARF_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.TAMATO_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.TANGA_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.WATMEL_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.WIKI_BERRY, COBBLEMON_BERRY_FARMING_XP),
            Map.entry(CobblemonBlocks.YACHE_BERRY, COBBLEMON_BERRY_FARMING_XP)
    );

    public static double captureCatchRateBonusPercent(int level) {
        return Math.min(10.0, level * 0.10);
    }

    public static float getCaptureRateBonusMultiplier(int level) {
        return (float) (1.0 + (captureCatchRateBonusPercent(level) / 100.0));
    }

    public static double getCaptureSkillExperience(int pokemonLevel, boolean criticalCapture) {
        double base = 200.0 + Math.max(1, pokemonLevel) * 8.0;
        return criticalCapture ? base + 150.0 : base;
    }

    public static double getBreedingEggCollected() {
        return 250.0; // Base XP for collecting an egg
    }

    public static int getBreedingFriendshipBonus(int breedingLevel) {
        return Math.min(20, breedingLevel / 5); // Base friendship bonus for hatching an egg, increases with breeding level
    }

    public static double getBreedingEggHatched(int eggCycles) {
        return 200.0 + Math.max(0, eggCycles) * 4.0; // Base XP for hatching an egg plus bonus based on egg cycles
    }

    public static double getTrainingSkillExperienceFromLevelUp(int oldLevel, int newLevel) {
        return Math.max(0, newLevel - oldLevel) * 300.0;
    }

    public static double trainingSkillExperienceFromBattle(double battleExperience) {
        if (battleExperience <= 0) {
            return 0.0;
        }
        return Math.max(20.0, battleExperience * 0.20);
    }

    public static double trainingBattleExperienceBonusPercent(int level) {
        return Math.min(25.0, level * 0.25);
    }

    public static int trainingSkillBonusExperience(int level, int baseExperience) {
        return (int) Math.floor(baseExperience * (trainingBattleExperienceBonusPercent(level) / 100.0));
    }
}
