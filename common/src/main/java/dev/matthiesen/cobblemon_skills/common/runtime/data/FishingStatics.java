package dev.matthiesen.cobblemon_skills.common.runtime.data;

import com.cobblemon.mod.common.CobblemonItems;
import dev.matthiesen.cobblemon_skills.common.config.CobblemonSkillsConfig;
import dev.matthiesen.cobblemon_skills.common.config.ExperienceConfig;
import net.minecraft.world.item.Item;

import java.util.Map;
import java.util.function.Supplier;

public final class FishingStatics {
    private static ExperienceConfig getConfig() {
        return CobblemonSkillsConfig.EXPERIENCE_CONFIG;
    }

    private static final Supplier<Integer> ANCIENT_ROD_CRAFT_XP = () -> getConfig().fishing_craftAncientRodXp.getAsInt();
    private static final Supplier<Integer> FISHING_ROD_CRAFT_XP = () -> getConfig().fishing_craftFishingRodXp.getAsInt();

    public static final Map<Item, Supplier<Integer>> ITEMS = Map.ofEntries(
            Map.entry(CobblemonItems.ANCIENT_AZURE_ROD, ANCIENT_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.ANCIENT_CITRINE_ROD, ANCIENT_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.ANCIENT_FEATHER_ROD, ANCIENT_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.ANCIENT_GIGATON_ROD, ANCIENT_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.ANCIENT_GREAT_ROD, ANCIENT_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.ANCIENT_HEAVY_ROD, ANCIENT_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.ANCIENT_IVORY_ROD, ANCIENT_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.ANCIENT_JET_ROD, ANCIENT_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.ANCIENT_LEADEN_ROD, ANCIENT_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.ANCIENT_ORIGIN_ROD, ANCIENT_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.ANCIENT_POKE_ROD, ANCIENT_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.ANCIENT_ROSEATE_ROD, ANCIENT_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.ANCIENT_SLATE_ROD, ANCIENT_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.ANCIENT_ULTRA_ROD, ANCIENT_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.ANCIENT_VERDANT_ROD, ANCIENT_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.ANCIENT_WING_ROD, ANCIENT_ROD_CRAFT_XP),

            Map.entry(CobblemonItems.AZURE_ROD, FISHING_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.BEAST_ROD, FISHING_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.CHERISH_ROD, FISHING_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.CITRINE_ROD, FISHING_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.DIVE_ROD, FISHING_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.DREAM_ROD, FISHING_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.DUSK_ROD, FISHING_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.FAST_ROD, FISHING_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.FRIEND_ROD, FISHING_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.GREAT_ROD, FISHING_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.HEAL_ROD, FISHING_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.HEAVY_ROD, FISHING_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.LEVEL_ROD, FISHING_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.LOVE_ROD, FISHING_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.LURE_ROD, FISHING_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.LUXURY_ROD, FISHING_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.MASTER_ROD, FISHING_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.MOON_ROD, FISHING_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.NEST_ROD, FISHING_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.NET_ROD, FISHING_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.PARK_ROD, FISHING_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.POKE_ROD, FISHING_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.PREMIER_ROD, FISHING_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.QUICK_ROD, FISHING_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.REPEAT_ROD, FISHING_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.ROSEATE_ROD, FISHING_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.SAFARI_ROD, FISHING_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.SLATE_ROD, FISHING_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.SPORT_ROD, FISHING_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.TIMER_ROD, FISHING_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.ULTRA_ROD, FISHING_ROD_CRAFT_XP),
            Map.entry(CobblemonItems.VERDANT_ROD, FISHING_ROD_CRAFT_XP)
    );
}
