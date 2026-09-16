package dev.matthiesen.cobblemon_skills.fabric;

import dev.matthiesen.cobblemon_skills.common.CobblemonSkillsCommon;
import dev.matthiesen.cobblemon_skills.common.platform.BlockBreakEvent;
import dev.matthiesen.cobblemon_skills.common.platform.CSPlatformEvents;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;

public final class CobblemonSkillsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        var instance = CobblemonSkillsCommon.INSTANCE;
        instance.createInfoLog("Loading for Fabric Mod Loader");
        instance.initialize();

        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) ->
                CSPlatformEvents.BLOCK_BREAK.emit(new BlockBreakEvent(
                world,
                player,
                pos,
                state,
                blockEntity
        )));
    }
}
