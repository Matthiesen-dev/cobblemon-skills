package dev.matthiesen.cobblemon_skills.neoforge;

import dev.matthiesen.cobblemon_skills.common.CobblemonSkillsCommon;
import dev.matthiesen.cobblemon_skills.common.runtime.ProfessionManager;
import net.minecraft.world.level.Level;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.level.BlockEvent;

@Mod(CobblemonSkillsCommon.MOD_ID)
public final class CobblemonSkillsNeoForge {
    public static final CobblemonSkillsCommon INSTANCE = CobblemonSkillsCommon.INSTANCE;

    public CobblemonSkillsNeoForge() {
        INSTANCE.createInfoLog("Loading for NeoForge Mod Loader");
        INSTANCE.initialize();

        NeoForge.EVENT_BUS.addListener(this::onBlockBreak);
    }

    private void onBlockBreak(BlockEvent.BreakEvent event) {
        if (event.isCanceled() || !(event.getLevel() instanceof Level level)) {
            return;
        }

        ProfessionManager.onBlockBreak(
                level,
                event.getPlayer(),
                event.getPos(),
                event.getState(),
                event.getLevel().getBlockEntity(event.getPos())
        );
    }
}
