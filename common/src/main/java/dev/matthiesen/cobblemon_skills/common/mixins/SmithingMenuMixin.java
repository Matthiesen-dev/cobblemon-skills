package dev.matthiesen.cobblemon_skills.common.mixins;

import dev.matthiesen.cobblemon_skills.common.runtime.ProfessionManager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.SmithingMenu;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SmithingMenu.class)
public class SmithingMenuMixin {
    @Inject(method = "onTake", at = @At("TAIL"))
    private void cobblemon_skills$onTake(Player player, ItemStack itemStack, CallbackInfo ci) {
        ProfessionManager.onSmithingTableTake(player, itemStack);
    }
}
