package dev.matthiesen.cobblemon_skills.common.runtime.data;

import com.cobblemon.mod.common.CobblemonBlocks;
import net.minecraft.world.level.block.Block;

import java.util.Map;

public final class BotanyStatics {
    private static final Integer COBBLEMON_WOODCUTTING_XP = 80;
    private static final Integer COBBLEMON_WOODCUTTING_EXTRA_XP = 120;
    private static final Integer COBBLEMON_FARMING_XP = 150;
    private static final Integer COBBLEMON_BERRY_FARMING_XP = 200;

    public static final Map<Block, Integer> BLOCKS = Map.<Block, Integer>ofEntries(
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
}
