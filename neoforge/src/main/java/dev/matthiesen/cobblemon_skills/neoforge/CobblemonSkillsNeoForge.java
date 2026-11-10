package dev.matthiesen.cobblemon_skills.neoforge;

import dev.matthiesen.cobblemon_skills.common.CobblemonSkillsCommon;
import net.neoforged.fml.common.Mod;

@Mod(CobblemonSkillsCommon.MOD_ID)
public final class CobblemonSkillsNeoForge {
    public static final CobblemonSkillsCommon INSTANCE = CobblemonSkillsCommon.INSTANCE;

    public CobblemonSkillsNeoForge() {
        INSTANCE.createInfoLog("Loading for NeoForge Mod Loader");
        INSTANCE.initialize();
    }
}
