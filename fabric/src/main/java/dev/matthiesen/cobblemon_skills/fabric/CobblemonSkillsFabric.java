package dev.matthiesen.cobblemon_skills.fabric;

import dev.matthiesen.cobblemon_skills.common.CobblemonSkillsCommon;
import net.fabricmc.api.ModInitializer;

public final class CobblemonSkillsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        var instance = CobblemonSkillsCommon.INSTANCE;
        instance.createInfoLog("Loading for Fabric Mod Loader");
        instance.initialize();
    }
}
