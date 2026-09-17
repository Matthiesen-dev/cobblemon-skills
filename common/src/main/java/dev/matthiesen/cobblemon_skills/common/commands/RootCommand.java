package dev.matthiesen.cobblemon_skills.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.matthiesen.cobblemon_skills.common.CobblemonSkillsCommon;
import dev.matthiesen.cobblemon_skills.common.menu.MainMenu;
import dev.matthiesen.cobblemon_skills.common.registry.PermissionsRegistry;
import dev.matthiesen.matthiesen_core.common.api.command.CoreCommand;
import dev.matthiesen.matthiesen_core.common.utility.commands.CommandBuilder;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public final class RootCommand implements CoreCommand {
    public static final RootCommand CMD = new RootCommand();

    @Override
    public void register(CommandDispatcher<CommandSourceStack> commandDispatcher, CommandBuildContext commandBuildContext, Commands.CommandSelection commandSelection) {
        var rootCommand = new CommandBuilder("cobbleskills")
                .requires(src -> PermissionsRegistry.checkPermission(src, PermissionsRegistry.COMMAND_ROOT_PERMISSION))
                .executes(this::action)
                .build();

        commandDispatcher.register(rootCommand);
    }

    public int action(CommandContext<CommandSourceStack> ctx) {
        try {
            ServerPlayer player = ctx.getSource().getPlayerOrException();
            MainMenu.open(player);
            return 1;
        } catch (CommandSyntaxException e) {
            CobblemonSkillsCommon.INSTANCE.createErrorLog("Failed to open the Cobblemon Skills menu for player: " + ctx.getSource().getTextName(), e);
            ctx.getSource().sendFailure(Component.literal("An error occurred while trying to open the Cobblemon Skills menu. Please contact an administrator."));
            return 0;
        }
    }
}
