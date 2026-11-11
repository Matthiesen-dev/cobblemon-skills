package dev.matthiesen.cobblemon_skills.common.commands.subcommands;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.matthiesen.cobblemon_skills.common.CobblemonSkillsCommon;
import dev.matthiesen.cobblemon_skills.common.config.CobblemonSkillsConfig;
import dev.matthiesen.cobblemon_skills.common.data.*;
import dev.matthiesen.cobblemon_skills.common.registry.PermissionsRegistry;
import dev.matthiesen.cobblemon_skills.common.runtime.*;
import dev.matthiesen.matthiesen_core.common.utility.commands.CommandBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;

import java.util.Map;

public final class AdminCommand {
    private static final CommandBuilder CMD_ADD_EXP = new CommandBuilder("addExp")
            .requires(src -> PermissionsRegistry.checkPermission(src, PermissionsRegistry.COMMAND_ADMIN_ADD_EXP_PERMISSION))
            .argument("player", EntityArgument.player(), arg -> arg
                    .then(Commands.argument("profession", StringArgumentType.string())
                            .suggests((ctx, builder) -> {
                                var professions = Profession.values();
                                for (var profession : professions) {
                                    builder.suggest(profession.getNbtTag());
                                }
                                return builder.buildFuture();
                            })
                            .then(Commands.argument("amount", DoubleArgumentType.doubleArg(0.0))
                                    .executes(AdminCommand::addExp)
                            )
                    )
            );
    private static final CommandBuilder CMD_SET_LEVEL = new CommandBuilder("setLevel")
            .requires(src -> PermissionsRegistry.checkPermission(src, PermissionsRegistry.COMMAND_ADMIN_SET_LEVEL_PERMISSION))
            .argument("player", EntityArgument.player(), arg -> arg
                    .then(Commands.argument("profession", StringArgumentType.string())
                            .suggests((ctx, builder) -> {
                                var professions = Profession.values();
                                for (var profession : professions) {
                                    builder.suggest(profession.getNbtTag());
                                }
                                return builder.buildFuture();
                            })
                            .then(Commands.argument("level", IntegerArgumentType.integer(0))
                                    .executes(AdminCommand::setLevel)
                            )
                    )
            );
    private static final CommandBuilder CMD_RELOAD = new CommandBuilder("reload")
            .requires(src -> PermissionsRegistry.checkPermission(src, PermissionsRegistry.COMMAND_ADMIN_RELOAD_PERMISSION))
            .executes(AdminCommand::reload);

    public static final CommandBuilder CMD = new CommandBuilder("admin")
            .requires(src -> PermissionsRegistry.checkPermission(src, PermissionsRegistry.COMMAND_ADMIN_PERMISSION))
            .then(CMD_ADD_EXP)
            .then(CMD_SET_LEVEL)
            .then(CMD_RELOAD);

    public static int addExp(CommandContext<CommandSourceStack> ctx) {
        try {
            ServerPlayer targetPlayer = EntityArgument.getPlayer(ctx, "player");
            String professionName = StringArgumentType.getString(ctx, "profession");
            Profession profession = Profession.fromNbtTag(professionName);
            double amount = DoubleArgumentType.getDouble(ctx, "amount");

            Integer levelsGained = profession.awardExperience(targetPlayer, amount);

            Map<String, String> placeholders = Map.of(
                    "%amount%", String.valueOf(amount),
                    "%player%", targetPlayer.getName().getString(),
                    "%profession%", profession.getNbtTag(),
                    "%levelsGained%", String.valueOf(levelsGained)
            );

            String messageTemplate = CobblemonSkillsConfig.TEXT_CONFIG.commands_addExpSuccess.get();
            Component message = TextUtils.parse(messageTemplate, placeholders);
            ctx.getSource().sendSystemMessage(message.copy().withStyle(ChatFormatting.GREEN));

            return 1;
        } catch (CommandSyntaxException e) {
            CobblemonSkillsCommon.INSTANCE.createErrorLog("Failed to add experience to player: " + ctx.getSource().getTextName(), e);
            ctx.getSource().sendFailure(TextUtils.parse(CobblemonSkillsConfig.TEXT_CONFIG.errors_addExpError.get()));
            return 0;
        }
    }

    public static int setLevel(CommandContext<CommandSourceStack> ctx) {
        try {
            ServerPlayer targetPlayer = EntityArgument.getPlayer(ctx, "player");
            String professionName = StringArgumentType.getString(ctx, "profession");
            Profession profession = Profession.fromNbtTag(professionName);
            int level = IntegerArgumentType.getInteger(ctx, "level");
            if (level > ProfessionProgress.getMaxLevel()) {
                Map<String, String> placeholders = Map.of(
                        "%maxLevel%", String.valueOf(ProfessionProgress.getMaxLevel())
                );
                String messageTemplate = CobblemonSkillsConfig.TEXT_CONFIG.errors_setLevelExceedMaxLevel.get();
                ctx.getSource().sendFailure(TextUtils.parse(messageTemplate, placeholders));
                return 0;
            }

            PlayerProfile playerProfile = SavedPlayerProfessionData.get(targetPlayer);
            playerProfile.getProgress(profession).setLevel(level);

            Map<String, String> placeholders = Map.of(
                    "%player%", targetPlayer.getName().getString(),
                    "%profession%", profession.getNbtTag(),
                    "%level%", String.valueOf(level)
            );

            String targetMessageTemplate = CobblemonSkillsConfig.TEXT_CONFIG.commands_setLevelTargetSuccess.get();
            String sourceMessageTemplate = CobblemonSkillsConfig.TEXT_CONFIG.commands_setLevelSourceSuccess.get();

            MutableComponent targetMessage = TextUtils.parse(targetMessageTemplate, placeholders).copy();
            MutableComponent sourceMessage = TextUtils.parse(sourceMessageTemplate, placeholders).copy();

            ctx.getSource().sendSystemMessage(sourceMessage.withStyle(ChatFormatting.GREEN));
            targetPlayer.sendSystemMessage(targetMessage.withStyle(ChatFormatting.GREEN));

            return 1;
        } catch (CommandSyntaxException e) {
            CobblemonSkillsCommon.INSTANCE.createErrorLog("Failed to set level for player: " + ctx.getSource().getTextName(), e);
            ctx.getSource().sendFailure(TextUtils.parse(CobblemonSkillsConfig.TEXT_CONFIG.errors_setLevelError.get()));
            return 0;
        }
    }

    public static int reload(CommandContext<CommandSourceStack> ctx) {
        try {
            CobblemonSkillsConfig.popCache();
            ctx.getSource().sendSystemMessage(TextUtils.parse(CobblemonSkillsConfig.TEXT_CONFIG.commands_reloadSuccess.get()));
            return 1;
        } catch (Exception e) {
            CobblemonSkillsCommon.INSTANCE.createErrorLog("Failed to reload configuration: " + ctx.getSource().getTextName(), e);
            ctx.getSource().sendFailure(TextUtils.parse(CobblemonSkillsConfig.TEXT_CONFIG.errors_reloadError.get()));
            return 0;
        }
    }
}
