package dev.matthiesen.cobblemon_skills.common.commands.subcommands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.matthiesen.cobblemon_skills.common.data.Profession;
import dev.matthiesen.cobblemon_skills.common.menu.PlayerProfessionEntry;
import dev.matthiesen.cobblemon_skills.common.registry.PermissionsRegistry;
import dev.matthiesen.matthiesen_core.common.utility.commands.CommandBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public final class ProfessionCommand {
    public static final CommandBuilder CMD = new CommandBuilder("profession")
            .requires(src -> PermissionsRegistry.checkPermission(src, PermissionsRegistry.COMMAND_PROFESSION_PERMISSION))
            .argument("profession", StringArgumentType.word(), arg -> arg
                    .suggests((ctx, builder) -> {
                        var professions = Profession.values();
                        for (var profession : professions) {
                            builder.suggest(profession.getNbtTag());
                        }
                        return builder.buildFuture();
                    })
                    .executes(ProfessionCommand::action)
            );

    public static int action(CommandContext<CommandSourceStack> ctx) {
        try {
            ServerPlayer player = ctx.getSource().getPlayerOrException();
            String professionName = StringArgumentType.getString(ctx, "profession");
            Profession profession = Profession.fromNbtTag(professionName);
            PlayerProfessionEntry.open(player, profession);
            return 1;
        } catch (CommandSyntaxException e) {
            ctx.getSource().sendFailure(Component.literal("An error occurred while trying to open the Cobblemon Skills menu. Please contact an administrator."));
            return 0;
        }
    }
}
