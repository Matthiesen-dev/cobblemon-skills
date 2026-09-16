package dev.matthiesen.cobblemon_skills.common.mixins;

import com.llamalad7.mixinextras.sugar.Local;
import dev.matthiesen.cobblemon_skills.common.runtime.ProfessionManager;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BrushableBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BrushableBlockEntity.class)
public class BrushableBlockEntityMixin {
    @Inject(
            method = "dropContent",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"
            )
    )
    private void onDropArchaeologyItem(
            Player player,
            CallbackInfo ci,
            @Local(name = "itemEntity") ItemEntity itemEntity
    ) {
        if (player instanceof ServerPlayer serverPlayer) {
            ProfessionManager.onBrushableBlockDrop(serverPlayer, itemEntity.getItem());
        }
    }
}
