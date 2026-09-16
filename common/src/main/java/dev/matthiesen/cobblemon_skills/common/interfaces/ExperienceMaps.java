package dev.matthiesen.cobblemon_skills.common.interfaces;

import com.cobblemon.mod.common.CobblemonBlocks;
import net.minecraft.world.level.block.Block;

import java.util.Map;

public final class ExperienceMaps {
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

    private static final Map<Block, Integer> COBBLEMON_BOTANY_BLOCKS = Map.ofEntries(
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
            Map.entry(CobblemonBlocks.MEDICINAL_LEEK, COBBLEMON_FARMING_XP)
    );
}
