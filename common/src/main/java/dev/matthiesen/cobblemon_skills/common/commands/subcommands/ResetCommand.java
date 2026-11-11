package dev.matthiesen.cobblemon_skills.common.commands.subcommands;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.matthiesen.cobblemon_skills.common.CobblemonSkillsCommon;
import dev.matthiesen.cobblemon_skills.common.config.CobblemonSkillsConfig;
import dev.matthiesen.cobblemon_skills.common.data.SavedPlayerProfessionData;
import dev.matthiesen.cobblemon_skills.common.registry.PermissionsRegistry;
import dev.matthiesen.cobblemon_skills.common.runtime.TextUtils;
import dev.matthiesen.matthiesen_core.common.utility.commands.CommandBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.Map;

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
                ctx.getSource().sendSystemMessage(TextUtils.parse(CobblemonSkillsConfig.TEXT_CONFIG.commands_reset_selfSuccess.get()));
            } else {
                ctx.getSource().sendFailure(TextUtils.parse(CobblemonSkillsConfig.TEXT_CONFIG.commands_reset_selfNoData.get()));
            }
            return 1;
        } catch (Exception e) {
            CobblemonSkillsCommon.INSTANCE.createErrorLog("Failed to reset the Cobblemon Skills data for player: " + ctx.getSource().getTextName(), e);
            ctx.getSource().sendFailure(TextUtils.parse(CobblemonSkillsConfig.TEXT_CONFIG.errors_reset_selfError.get()));
            return 0;
        }
    }

    public static int resetPlayer(CommandContext<CommandSourceStack> ctx) {
        try {
            ServerPlayer targetPlayer = EntityArgument.getPlayer(ctx, "target");
            boolean playerReset = SavedPlayerProfessionData.resetPlayer(targetPlayer.getUUID());

            Map<String, String> placeholders = Map.of(
                    "%target%", targetPlayer.getName().getString()
            );

            if (playerReset) {
                String messageTemplate = CobblemonSkillsConfig.TEXT_CONFIG.commands_reset_playerSuccess.get();
                Component message = TextUtils.parse(messageTemplate, placeholders);
                ctx.getSource().sendSystemMessage(message.copy().withStyle(ChatFormatting.GREEN));

                String targetMessageTemplate = CobblemonSkillsConfig.TEXT_CONFIG.commands_reset_playerSuccessTarget.get();
                Component targetMessage = TextUtils.parse(targetMessageTemplate, placeholders);
                targetPlayer.sendSystemMessage(targetMessage.copy().withStyle(ChatFormatting.GREEN));
            } else {
                String messageTemplate = CobblemonSkillsConfig.TEXT_CONFIG.commands_reset_playerNoData.get();
                Component message = TextUtils.parse(messageTemplate, placeholders);
                ctx.getSource().sendFailure(message);
            }
            return 1;
        } catch (CommandSyntaxException e) {
            CobblemonSkillsCommon.INSTANCE.createErrorLog("Failed to reset the Cobblemon Skills data for player: " + ctx.getSource().getTextName(), e);
            ctx.getSource().sendFailure(TextUtils.parse(CobblemonSkillsConfig.TEXT_CONFIG.errors_reset_playerError.get()));
            return 0;
        }
    }

    public static int resetAll(CommandContext<CommandSourceStack> ctx) {
        try {
            int count = SavedPlayerProfessionData.resetAll();

            Map<String, String> placeholders = Map.of(
                    "%count%", String.valueOf(count)
            );

            String messageTemplate = CobblemonSkillsConfig.TEXT_CONFIG.commands_reset_allSuccess.get();
            Component message = TextUtils.parse(messageTemplate, placeholders);
            ctx.getSource().sendSystemMessage(message.copy().withStyle(ChatFormatting.GREEN));
            return 1;
        } catch (Exception e) {
            CobblemonSkillsCommon.INSTANCE.createErrorLog("Failed to reset all Cobblemon Skills data for players: " + ctx.getSource().getTextName(), e);
            ctx.getSource().sendFailure(TextUtils.parse(CobblemonSkillsConfig.TEXT_CONFIG.errors_reset_allError.get()));
            return 0;
        }
    }
}
