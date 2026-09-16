package dev.matthiesen.cobblemon_skills.common.runtime.data;

import com.cobblemon.mod.common.CobblemonBlocks;
import com.cobblemon.mod.common.CobblemonItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.Map;

public final class ArcheologyStatics {
    private static final Integer ORE_BLOCK_XP = 400;
    private static final Integer MISC_ORE_BLOCK_XP = 600;
    private static final Integer DEEPSLATE_ORE_BLOCK_XP = 800;
    private static final Integer TUMBLESTONE_CLUSTER_SMALL_XP = 100;
    private static final Integer TUMBLESTONE_CLUSTER_MEDIUM_XP = 120;
    private static final Integer TUMBLESTONE_CLUSTER_LARGE_XP = 140;
    private static final Integer TUMBLESTONE_CLUSTER_FULL_XP = 180;
    private static final Integer TYPE_CLUSTER_XP = 200;
    private static final Integer RELIC_COIN_SACK_XP = 900;
    private static final Integer RELIC_COIN_POUCH_XP = 600;
    private static final Integer COMMON_ARCHEOLOGY_ITEM_XP = 200;
    private static final Integer UNCOMMON_ARCHEOLOGY_ITEM_XP = 400;
    private static final Integer RARE_ARCHEOLOGY_ITEM_XP = 600;

    public static final Map<Block, Integer> BLOCKS = Map.<Block, Integer>ofEntries(
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

    public static final Map<Item, Integer> ITEMS = Map.ofEntries(
            Map.entry(CobblemonItems.LEAF_STONE, COMMON_ARCHEOLOGY_ITEM_XP),
            Map.entry(CobblemonItems.MIRROR_HERB, COMMON_ARCHEOLOGY_ITEM_XP),
            Map.entry(CobblemonItems.BIG_ROOT, COMMON_ARCHEOLOGY_ITEM_XP),
            Map.entry(CobblemonItems.ENERGY_ROOT, COMMON_ARCHEOLOGY_ITEM_XP),
            Map.entry(CobblemonItems.WATER_STONE, COMMON_ARCHEOLOGY_ITEM_XP),
            Map.entry(CobblemonItems.POWER_HERB, COMMON_ARCHEOLOGY_ITEM_XP),
            Map.entry(CobblemonItems.WHITE_HERB, COMMON_ARCHEOLOGY_ITEM_XP),
            Map.entry(CobblemonItems.MENTAL_HERB, COMMON_ARCHEOLOGY_ITEM_XP),
            Map.entry(CobblemonItems.SUN_STONE, COMMON_ARCHEOLOGY_ITEM_XP),
            Map.entry(CobblemonItems.ICE_STONE, COMMON_ARCHEOLOGY_ITEM_XP),
            Map.entry(CobblemonItems.NEVER_MELT_ICE, COMMON_ARCHEOLOGY_ITEM_XP),
            Map.entry(CobblemonItems.HEAT_ROCK, UNCOMMON_ARCHEOLOGY_ITEM_XP),
            Map.entry(CobblemonItems.EVERSTONE, UNCOMMON_ARCHEOLOGY_ITEM_XP),
            Map.entry(CobblemonItems.CHARCOAL, UNCOMMON_ARCHEOLOGY_ITEM_XP),
            Map.entry(CobblemonItems.PEAT_BLOCK, UNCOMMON_ARCHEOLOGY_ITEM_XP),
            Map.entry(CobblemonItems.VIVICHOKE_SEEDS, UNCOMMON_ARCHEOLOGY_ITEM_XP),
            Map.entry(CobblemonItems.DAMP_ROCK, UNCOMMON_ARCHEOLOGY_ITEM_XP),
            Map.entry(CobblemonItems.PRISM_SCALE, UNCOMMON_ARCHEOLOGY_ITEM_XP),
            Map.entry(CobblemonItems.DRAGON_FANG, UNCOMMON_ARCHEOLOGY_ITEM_XP),
            Map.entry(CobblemonItems.KINGS_ROCK, UNCOMMON_ARCHEOLOGY_ITEM_XP),
            Map.entry(CobblemonItems.SMOOTH_ROCK, UNCOMMON_ARCHEOLOGY_ITEM_XP),
            Map.entry(CobblemonItems.BLACK_AUGURITE, UNCOMMON_ARCHEOLOGY_ITEM_XP),
            Map.entry(CobblemonItems.ICY_ROCK, UNCOMMON_ARCHEOLOGY_ITEM_XP),
            Map.entry(CobblemonItems.RAZOR_CLAW, UNCOMMON_ARCHEOLOGY_ITEM_XP),
            Map.entry(CobblemonItems.DEEP_SEA_TOOTH, UNCOMMON_ARCHEOLOGY_ITEM_XP),
            Map.entry(CobblemonItems.DEEP_SEA_SCALE, UNCOMMON_ARCHEOLOGY_ITEM_XP),
            Map.entry(CobblemonItems.ABSORB_BULB, UNCOMMON_ARCHEOLOGY_ITEM_XP),
            Map.entry(CobblemonItems.FAIRY_FEATHER, UNCOMMON_ARCHEOLOGY_ITEM_XP),
            Map.entry(CobblemonItems.ARMOR_FOSSIL, RARE_ARCHEOLOGY_ITEM_XP),
            Map.entry(CobblemonItems.CLAW_FOSSIL, RARE_ARCHEOLOGY_ITEM_XP),
            Map.entry(CobblemonItems.COVER_FOSSIL, RARE_ARCHEOLOGY_ITEM_XP),
            Map.entry(CobblemonItems.DOME_FOSSIL, RARE_ARCHEOLOGY_ITEM_XP),
            Map.entry(CobblemonItems.HELIX_FOSSIL, RARE_ARCHEOLOGY_ITEM_XP),
            Map.entry(CobblemonItems.JAW_FOSSIL, RARE_ARCHEOLOGY_ITEM_XP),
            Map.entry(CobblemonItems.OLD_AMBER_FOSSIL, RARE_ARCHEOLOGY_ITEM_XP),
            Map.entry(CobblemonItems.PLUME_FOSSIL, RARE_ARCHEOLOGY_ITEM_XP),
            Map.entry(CobblemonItems.ROOT_FOSSIL, RARE_ARCHEOLOGY_ITEM_XP),
            Map.entry(CobblemonItems.SAIL_FOSSIL, RARE_ARCHEOLOGY_ITEM_XP),
            Map.entry(CobblemonItems.SKULL_FOSSIL, RARE_ARCHEOLOGY_ITEM_XP),
            Map.entry(CobblemonItems.FOSSILIZED_BIRD, RARE_ARCHEOLOGY_ITEM_XP),
            Map.entry(CobblemonItems.FOSSILIZED_DINO, RARE_ARCHEOLOGY_ITEM_XP),
            Map.entry(CobblemonItems.FOSSILIZED_FISH, RARE_ARCHEOLOGY_ITEM_XP),
            Map.entry(CobblemonItems.FOSSILIZED_DRAKE, RARE_ARCHEOLOGY_ITEM_XP)
    );
}
