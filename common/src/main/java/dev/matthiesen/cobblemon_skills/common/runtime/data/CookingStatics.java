package dev.matthiesen.cobblemon_skills.common.runtime.data;

import com.cobblemon.mod.common.CobblemonItems;
import dev.matthiesen.cobblemon_skills.common.config.CobblemonSkillsConfig;
import dev.matthiesen.cobblemon_skills.common.config.ServerConfig;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.Map;
import java.util.function.Supplier;

public final class CookingStatics {
    private static ServerConfig getConfig() {
        return CobblemonSkillsConfig.SERVER_CONFIG;
    }

    private static final Supplier<Integer> COOKING_BASE_XP = () -> getConfig().cooking_baseXp.getAsInt();
    private static final Supplier<Integer> COOKING_TIER_1_XP = () -> getConfig().cooking_tier1Xp.getAsInt();
    private static final Supplier<Integer> COOKING_TIER_2_XP = () -> getConfig().cooking_tier2Xp.getAsInt();
    private static final Supplier<Integer> COOKING_TIER_3_XP = () -> getConfig().cooking_tier3Xp.getAsInt();
    private static final Supplier<Integer> COOKING_TIER_4_XP = () -> getConfig().cooking_tier4Xp.getAsInt();

    public static final Map<Item, Supplier<Integer>> ITEMS = Map.<Item, Supplier<Integer>>ofEntries(
            Map.entry(CobblemonItems.ABILITY_CAPSULE, COOKING_TIER_3_XP),
            Map.entry(CobblemonItems.ANTIDOTE, COOKING_BASE_XP),
            Map.entry(CobblemonItems.AWAKENING, COOKING_BASE_XP),
            Map.entry(CobblemonItems.BURN_HEAL, COOKING_BASE_XP),
            Map.entry(CobblemonItems.CALCIUM, COOKING_TIER_2_XP),
            Map.entry(CobblemonItems.CARBOS, COOKING_TIER_2_XP),
            Map.entry(CobblemonItems.ELIXIR, COOKING_TIER_1_XP),
            Map.entry(CobblemonItems.ETHER, COOKING_TIER_1_XP),
            Map.entry(CobblemonItems.FULL_RESTORE, COOKING_TIER_3_XP),
            Map.entry(CobblemonItems.HP_UP, COOKING_TIER_2_XP),
            Map.entry(CobblemonItems.HYPER_POTION, COOKING_TIER_2_XP),
            Map.entry(CobblemonItems.ICE_HEAL, COOKING_BASE_XP),
            Map.entry(CobblemonItems.IRON, COOKING_TIER_2_XP),
            Map.entry(CobblemonItems.FULL_HEAL, COOKING_TIER_3_XP),
            Map.entry(CobblemonItems.MAX_ELIXIR, COOKING_TIER_3_XP),
            Map.entry(CobblemonItems.MAX_POTION, COOKING_TIER_3_XP),
            Map.entry(CobblemonItems.MAX_ETHER, COOKING_TIER_3_XP),
            Map.entry(CobblemonItems.MEDICINAL_BREW, COOKING_TIER_1_XP),
            Map.entry(CobblemonItems.PARALYZE_HEAL, COOKING_BASE_XP),
            Map.entry(CobblemonItems.POTION, COOKING_BASE_XP),
            Map.entry(CobblemonItems.PP_MAX, COOKING_TIER_2_XP),
            Map.entry(CobblemonItems.PP_UP, COOKING_TIER_2_XP),
            Map.entry(CobblemonItems.PROTEIN, COOKING_TIER_2_XP),
            Map.entry(CobblemonItems.SUPER_POTION, COOKING_TIER_2_XP),
            Map.entry(CobblemonItems.THROAT_SPRAY, COOKING_TIER_1_XP),
            Map.entry(CobblemonItems.ZINC, COOKING_TIER_2_XP),
            Map.entry(Items.BEETROOT_SOUP, COOKING_BASE_XP),
            Map.entry(CobblemonItems.BERRY_SWEET, COOKING_BASE_XP),
            Map.entry(CobblemonItems.BIG_MALASADA, COOKING_TIER_1_XP),
            Map.entry(CobblemonItems.APRIJUICE_BLACK, COOKING_BASE_XP),
            Map.entry(CobblemonItems.APRIJUICE_BLUE, COOKING_BASE_XP),
            Map.entry(CobblemonItems.APRIJUICE_GREEN, COOKING_BASE_XP),
            Map.entry(CobblemonItems.APRIJUICE_PINK, COOKING_BASE_XP),
            Map.entry(CobblemonItems.APRIJUICE_RED, COOKING_BASE_XP),
            Map.entry(CobblemonItems.APRIJUICE_WHITE, COOKING_BASE_XP),
            Map.entry(CobblemonItems.APRIJUICE_YELLOW, COOKING_BASE_XP),
            Map.entry(CobblemonItems.BRITTLE_CANDY, COOKING_BASE_XP),
            Map.entry(Items.CAKE, COOKING_TIER_1_XP),
            Map.entry(CobblemonItems.CANDIED_APPLE, COOKING_BASE_XP),
            Map.entry(CobblemonItems.CANDIED_BERRY, COOKING_BASE_XP),
            Map.entry(CobblemonItems.CASTELIACONE, COOKING_BASE_XP),
            Map.entry(CobblemonItems.CLEVER_MOCHI, COOKING_BASE_XP),
            Map.entry(CobblemonItems.CLOVER_SWEET, COOKING_BASE_XP),
            Map.entry(CobblemonItems.COURAGE_CANDY, COOKING_BASE_XP),
            Map.entry(CobblemonItems.DIRE_HIT, COOKING_BASE_XP),
            Map.entry(CobblemonItems.EXPERIENCE_CANDY_XS, COOKING_BASE_XP),
            Map.entry(CobblemonItems.EXPERIENCE_CANDY_S, COOKING_TIER_1_XP),
            Map.entry(CobblemonItems.EXPERIENCE_CANDY_M, COOKING_TIER_2_XP),
            Map.entry(CobblemonItems.EXPERIENCE_CANDY_L, COOKING_TIER_3_XP),
            Map.entry(CobblemonItems.EXPERIENCE_CANDY_XL, COOKING_TIER_4_XP),
            Map.entry(CobblemonItems.FLOWER_SWEET, COOKING_BASE_XP),
            Map.entry(CobblemonItems.FRESH_START_MOCHI, COOKING_BASE_XP),
            Map.entry(CobblemonItems.GENIUS_MOCHI, COOKING_BASE_XP),
            Map.entry(CobblemonItems.GUARD_SPEC, COOKING_BASE_XP),
            Map.entry(CobblemonItems.HEALTH_CANDY, COOKING_BASE_XP),
            Map.entry(CobblemonItems.HEALTH_MOCHI, COOKING_BASE_XP),
            Map.entry(CobblemonItems.JUBILIFE_MUFFIN, COOKING_BASE_XP),
            Map.entry(CobblemonItems.LAVA_COOKIE, COOKING_BASE_XP),
            Map.entry(CobblemonItems.LEEK_AND_POTATO_STEW, COOKING_BASE_XP),
            Map.entry(CobblemonItems.LOVE_SWEET, COOKING_BASE_XP),
            Map.entry(CobblemonItems.LUMIOSE_GALETTE, COOKING_BASE_XP),
            Map.entry(CobblemonItems.MAX_REVIVE, COOKING_TIER_3_XP),
            Map.entry(CobblemonItems.MIGHTY_CANDY, COOKING_BASE_XP),
            Map.entry(CobblemonItems.MUSCLE_MOCHI, COOKING_BASE_XP),
            Map.entry(Items.MUSHROOM_STEW, COOKING_BASE_XP),
            Map.entry(CobblemonItems.NUMB_CANDY, COOKING_BASE_XP),
            Map.entry(CobblemonItems.OLD_GATEAU, COOKING_BASE_XP),
            Map.entry(CobblemonItems.OPEN_FACED_SANDWICH, COOKING_BASE_XP),
            Map.entry(CobblemonItems.PEWTER_CRUNCHIES, COOKING_BASE_XP),
            Map.entry(CobblemonItems.POKE_BAIT, COOKING_BASE_XP),
            Map.entry(CobblemonItems.POKE_SNACK, COOKING_TIER_4_XP),
            Map.entry(CobblemonItems.PONIGIRI, COOKING_BASE_XP),
            Map.entry(CobblemonItems.POTATO_MOCHI, COOKING_BASE_XP),
            Map.entry(Items.PUMPKIN_PIE, COOKING_BASE_XP),
            Map.entry(CobblemonItems.QUICK_CANDY, COOKING_BASE_XP),
            Map.entry(Items.RABBIT_STEW, COOKING_BASE_XP),
            Map.entry(CobblemonItems.RAGE_CANDY_BAR, COOKING_BASE_XP),
            Map.entry(CobblemonItems.RESIST_MOCHI, COOKING_BASE_XP),
            Map.entry(CobblemonItems.REVIVE, COOKING_TIER_3_XP),
            Map.entry(CobblemonItems.RIBBON_SWEET, COOKING_BASE_XP),
            Map.entry(CobblemonItems.SICKLY_CANDY, COOKING_BASE_XP),
            Map.entry(CobblemonItems.SINISTER_TEA, COOKING_BASE_XP),
            Map.entry(CobblemonItems.SLOW_CANDY, COOKING_BASE_XP),
            Map.entry(CobblemonItems.SMART_CANDY, COOKING_BASE_XP),
            Map.entry(CobblemonItems.SMOKED_TAIL_CURRY, COOKING_BASE_XP),
            Map.entry(CobblemonItems.STAR_SWEET, COOKING_BASE_XP),
            Map.entry(CobblemonItems.STRAWBERRY_SWEET, COOKING_BASE_XP),
            Map.entry(Items.SUSPICIOUS_STEW, COOKING_BASE_XP),
            Map.entry(CobblemonItems.SWEET_HEART, COOKING_BASE_XP),
            Map.entry(CobblemonItems.SWIFT_MOCHI, COOKING_BASE_XP),
            Map.entry(CobblemonItems.TOUGH_CANDY, COOKING_BASE_XP),
            Map.entry(CobblemonItems.VIVICHOKE_DIP, COOKING_BASE_XP),
            Map.entry(CobblemonItems.WEAK_CANDY, COOKING_BASE_XP),
            Map.entry(CobblemonItems.WHIPPED_DREAM, COOKING_BASE_XP),
            Map.entry(CobblemonItems.X_ACCURACY, COOKING_BASE_XP),
            Map.entry(CobblemonItems.X_ATTACK, COOKING_BASE_XP),
            Map.entry(CobblemonItems.X_DEFENSE, COOKING_BASE_XP),
            Map.entry(CobblemonItems.X_SP_ATK, COOKING_BASE_XP),
            Map.entry(CobblemonItems.X_SP_DEF, COOKING_BASE_XP),
            Map.entry(CobblemonItems.X_SPEED, COOKING_BASE_XP)
    );
}
