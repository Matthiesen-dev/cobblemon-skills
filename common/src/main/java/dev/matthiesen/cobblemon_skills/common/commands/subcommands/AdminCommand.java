package dev.matthiesen.cobblemon_skills.common.commands.subcommands;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.matthiesen.cobblemon_skills.common.CobblemonSkillsCommon;
import dev.matthiesen.cobblemon_skills.common.config.CobblemonSkillsConfig;
import dev.matthiesen.cobblemon_skills.common.data.PlayerProfile;
import dev.matthiesen.cobblemon_skills.common.data.Profession;
import dev.matthiesen.cobblemon_skills.common.data.ProfessionProgress;
import dev.matthiesen.cobblemon_skills.common.data.SavedPlayerProfessionData;
import dev.matthiesen.cobblemon_skills.common.registry.PermissionsRegistry;
import dev.matthiesen.cobblemon_skills.common.runtime.AwardManager;
import dev.matthiesen.matthiesen_core.common.utility.commands.CommandBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

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
                            .then(Commands.argument("level", IntegerArgumentType.integer(0, ProfessionProgress.getMaxLevel()))
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

            int levelsGained = AwardManager.awardProfessionExperience(targetPlayer, profession, amount);
            ctx.getSource().sendSystemMessage(Component.literal("Added " + amount + " experience to player " + targetPlayer.getName().getString() + " for profession " + profession.getNbtTag() + ". Levels gained: " + levelsGained).withStyle(ChatFormatting.GREEN));

            return 1;
        } catch (CommandSyntaxException e) {
            CobblemonSkillsCommon.INSTANCE.createErrorLog("Failed to add experience to player: " + ctx.getSource().getTextName(), e);
            ctx.getSource().sendFailure(Component.literal("An error occurred while trying to add experience to the player. Please contact an administrator.").withStyle(ChatFormatting.RED));
            return 0;
        }
    }

    public static int setLevel(CommandContext<CommandSourceStack> ctx) {
        try {
            ServerPlayer targetPlayer = EntityArgument.getPlayer(ctx, "player");
            String professionName = StringArgumentType.getString(ctx, "profession");
            Profession profession = Profession.fromNbtTag(professionName);
            int level = IntegerArgumentType.getInteger(ctx, "level");

            PlayerProfile playerProfile = SavedPlayerProfessionData.get(targetPlayer);
            playerProfile.getProgress(profession).setLevel(level);

            ctx.getSource().sendSystemMessage(Component.literal("Set level of player " + targetPlayer.getName().getString() + " for profession " + profession.getNbtTag() + " to " + level).withStyle(ChatFormatting.GREEN));
            targetPlayer.sendSystemMessage(Component.literal("Your level for profession " + profession.getNbtTag() + " has been set to " + level + " by an administrator.").withStyle(ChatFormatting.GREEN));

            return 1;
        } catch (CommandSyntaxException e) {
            CobblemonSkillsCommon.INSTANCE.createErrorLog("Failed to set level for player: " + ctx.getSource().getTextName(), e);
            ctx.getSource().sendFailure(Component.literal("An error occurred while trying to set the level for the player. Please contact an administrator.").withStyle(ChatFormatting.RED));
            return 0;
        }
    }

    public static int reload(CommandContext<CommandSourceStack> ctx) {
        try {
            CobblemonSkillsConfig.popCache();
            ctx.getSource().sendSystemMessage(Component.literal("Cobblemon Skills configuration reloaded successfully.").withStyle(ChatFormatting.GREEN));
            return 1;
        } catch (Exception e) {
            CobblemonSkillsCommon.INSTANCE.createErrorLog("Failed to reload configuration: " + ctx.getSource().getTextName(), e);
            ctx.getSource().sendFailure(Component.literal("An error occurred while trying to reload the configuration. Please contact an administrator.").withStyle(ChatFormatting.RED));
            return 0;
        }
    }
}
