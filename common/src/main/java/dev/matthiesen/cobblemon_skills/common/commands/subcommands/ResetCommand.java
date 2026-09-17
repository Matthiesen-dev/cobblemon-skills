package dev.matthiesen.cobblemon_skills.common.commands.subcommands;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.matthiesen.cobblemon_skills.common.CobblemonSkillsCommon;
import dev.matthiesen.cobblemon_skills.common.data.SavedPlayerProfessionData;
import dev.matthiesen.cobblemon_skills.common.registry.PermissionsRegistry;
import dev.matthiesen.matthiesen_core.common.utility.commands.CommandBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public final class ResetCommand {
    private static final CommandBuilder CMD_SELF = new CommandBuilder("self")
            .requires(src -> PermissionsRegistry.checkPermission(src, PermissionsRegistry.COMMAND_RESET_SELF_PERMISSION))
            .executes(ResetCommand::resetSelf);

    private static final CommandBuilder CMD_PLAYER = new CommandBuilder("player")
            .requires(src -> PermissionsRegistry.checkPermission(src, PermissionsRegistry.COMMAND_RESET_PLAYER_PERMISSION))
            .argument("target", EntityArgument.player(), arg -> arg
                    .executes(ResetCommand::resetPlayer)
            );

    private static final CommandBuilder CMD_ALL = new CommandBuilder("all")
            .requires(src -> PermissionsRegistry.checkPermission(src, PermissionsRegistry.COMMAND_RESET_ALL_PERMISSION))
            .executes(ResetCommand::resetAll);

    public static final CommandBuilder CMD = new CommandBuilder("reset")
            .requires(src -> PermissionsRegistry.checkPermission(src, PermissionsRegistry.COMMAND_RESET_PERMISSION))
            .then(CMD_SELF)
            .then(CMD_PLAYER)
            .then(CMD_ALL);

    public static int resetSelf(CommandContext<CommandSourceStack> ctx) {
        try {
            ServerPlayer player = ctx.getSource().getPlayerOrException();
            boolean playerReset = SavedPlayerProfessionData.resetPlayer(player.getUUID());
            if (playerReset) {
                ctx.getSource().sendSystemMessage(Component.literal("Your Cobblemon Skills data has been reset.").withStyle(ChatFormatting.GREEN));
            } else {
                ctx.getSource().sendFailure(Component.literal("No Cobblemon Skills data found for your player profile.").withStyle(ChatFormatting.RED));
            }
            return 1;
        } catch (Exception e) {
            CobblemonSkillsCommon.INSTANCE.createErrorLog("Failed to reset the Cobblemon Skills data for player: " + ctx.getSource().getTextName(), e);
            ctx.getSource().sendFailure(Component.literal("An error occurred while trying to reset your Cobblemon Skills data. Please contact an administrator.").withStyle(ChatFormatting.RED));
            return 0;
        }
    }

    public static int resetPlayer(CommandContext<CommandSourceStack> ctx) {
        try {
            ServerPlayer targetPlayer = EntityArgument.getPlayer(ctx, "target");
            boolean playerReset = SavedPlayerProfessionData.resetPlayer(targetPlayer.getUUID());
            if (playerReset) {
                ctx.getSource().sendSystemMessage(Component.literal("Cobblemon Skills data for player "+ targetPlayer.getName().getString() + " has been reset.").withStyle(ChatFormatting.GREEN));
                targetPlayer.sendSystemMessage(Component.literal("Your Cobblemon Skills data has been reset by an administrator.").withStyle(ChatFormatting.GREEN));
            } else {
                ctx.getSource().sendFailure(Component.literal("No Cobblemon Skills data found for player "+ targetPlayer.getName().getString() + ".").withStyle(ChatFormatting.RED));
            }
            return 1;
        } catch (CommandSyntaxException e) {
            CobblemonSkillsCommon.INSTANCE.createErrorLog("Failed to reset the Cobblemon Skills data for player: " + ctx.getSource().getTextName(), e);
            ctx.getSource().sendFailure(Component.literal("An error occurred while trying to reset the Cobblemon Skills data for the target player. Please contact an administrator.").withStyle(ChatFormatting.RED));
            return 0;
        }
    }

    public static int resetAll(CommandContext<CommandSourceStack> ctx) {
        try {
            int count = SavedPlayerProfessionData.resetAll();
            ctx.getSource().sendSystemMessage(Component.literal("Cobblemon Skills data for " +count + " players has been reset.").withStyle(ChatFormatting.GREEN));
            return 1;
        } catch (Exception e) {
            CobblemonSkillsCommon.INSTANCE.createErrorLog("Failed to reset all Cobblemon Skills data for players: " + ctx.getSource().getTextName(), e);
            ctx.getSource().sendFailure(Component.literal("An error occurred while trying to reset all Cobblemon Skills data. Please contact an administrator.").withStyle(ChatFormatting.RED));
            return 0;
        }
    }
}
