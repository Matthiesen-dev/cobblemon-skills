package dev.matthiesen.cobblemon_skills.common.mixins;

import com.cobblemon.mod.common.block.campfirepot.CookingPotResultSlot;
import dev.matthiesen.cobblemon_skills.common.runtime.ProfessionManager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CookingPotResultSlot.class)
public class CookingPotResultSlotMixin {
    @Inject(method = "onTake", at = @At("TAIL"))
    private void cobblemon_skills$onTake(Player player, ItemStack stack, CallbackInfo ci) {
        ProfessionManager.onCookingPotTake(player, stack);
    }
}
