package dev.matthiesen.cobblemon_skills.common.platform;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public record BlockBreakEvent(
        Level world,
        Player player,
        BlockPos pos,
        BlockState state,
        BlockEntity blockEntity
) {
}
