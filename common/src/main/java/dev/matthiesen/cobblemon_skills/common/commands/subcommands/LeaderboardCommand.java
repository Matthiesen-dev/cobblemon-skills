package dev.matthiesen.cobblemon_skills.common.commands.subcommands;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.matthiesen.cobblemon_skills.common.config.CobblemonSkillsConfig;
import dev.matthiesen.cobblemon_skills.common.menu.MainMenu;
import dev.matthiesen.cobblemon_skills.common.registry.PermissionsRegistry;
import dev.matthiesen.cobblemon_skills.common.runtime.TextUtils;
import dev.matthiesen.matthiesen_core.common.utility.commands.CommandBuilder;
import net.minecraft.commands.CommandSourceStack;

public final class LeaderboardCommand {
    public static final CommandBuilder CMD = new CommandBuilder("leaderboard")
            .requires(src -> PermissionsRegistry.checkPermission(src, PermissionsRegistry.COMMAND_LEADERBOARD_PERMISSION))
            .executes(LeaderboardCommand::action);

    public static int action(CommandContext<CommandSourceStack> ctx) {
        try {
            var player = ctx.getSource().getPlayerOrException();
            MainMenu.open(player);
            return 1;
        } catch (CommandSyntaxException e) {
            ctx.getSource().sendFailure(TextUtils.parse(CobblemonSkillsConfig.TEXT_CONFIG.errors_menuError.get()));
            return 0;
        }
    }
}
