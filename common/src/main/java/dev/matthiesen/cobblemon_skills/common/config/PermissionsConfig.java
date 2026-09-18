package dev.matthiesen.cobblemon_skills.common.config;

import dev.matthiesen.matthiesen_core.common.api.permissions.PermissionLevel;
import net.neoforged.neoforge.common.ModConfigSpec;

public final class PermissionsConfig {

    // Command Permissions
    public ModConfigSpec.EnumValue<PermissionLevel> command_cobbleskills;
    public ModConfigSpec.EnumValue<PermissionLevel> command_cobbleskills_reset;
    public ModConfigSpec.EnumValue<PermissionLevel> command_cobbleskills_reset_self;
    public ModConfigSpec.EnumValue<PermissionLevel> command_cobbleskills_reset_player;
    public ModConfigSpec.EnumValue<PermissionLevel> command_cobbleskills_reset_all;
    public ModConfigSpec.EnumValue<PermissionLevel> command_cobbleskills_leaderboard;
    public ModConfigSpec.EnumValue<PermissionLevel> command_cobbleskills_profile;
    public ModConfigSpec.EnumValue<PermissionLevel> command_cobbleskills_profession;
    public ModConfigSpec.EnumValue<PermissionLevel> command_cobbleskills_admin;
    public ModConfigSpec.EnumValue<PermissionLevel> command_cobbleskills_admin_addExp;
    public ModConfigSpec.EnumValue<PermissionLevel> command_cobbleskills_admin_setLevel;
    public ModConfigSpec.EnumValue<PermissionLevel> command_cobbleskills_admin_reload;

    // GUI Permissions
    public ModConfigSpec.EnumValue<PermissionLevel> gui_main_menu;
    public ModConfigSpec.EnumValue<PermissionLevel> gui_leaderboard;
    public ModConfigSpec.EnumValue<PermissionLevel> gui_profile;
    public ModConfigSpec.EnumValue<PermissionLevel> gui_profile_other;

    public PermissionsConfig(ModConfigSpec.Builder builder) {
        builder.push("permissions");

        builder.push("command");
        command_cobbleskills = builder
                .comment("The permission level required to use the /cobbleskills command.")
                .defineEnum("cobbleskills", PermissionLevel.NONE);
        command_cobbleskills_reset = builder
                .comment("The permission level required to use the /cobbleskills reset command.")
                .defineEnum("cobbleskills.reset", PermissionLevel.NONE);
        command_cobbleskills_reset_self = builder
                .comment("The permission level required to use the /cobbleskills reset self command.")
                .defineEnum("cobbleskills.reset.self", PermissionLevel.NONE);
        command_cobbleskills_reset_player = builder
                .comment("The permission level required to use the /cobbleskills reset player command.")
                .defineEnum("cobbleskills.reset.player", PermissionLevel.MULTIPLAYER_MANAGEMENT);
        command_cobbleskills_reset_all = builder
                .comment("The permission level required to use the /cobbleskills reset all command.")
                .defineEnum("cobbleskills.reset.all", PermissionLevel.ALL_COMMANDS);
        command_cobbleskills_leaderboard = builder
                .comment("The permission level required to use the /cobbleskills leaderboard command.")
                .defineEnum("cobbleskills.leaderboard", PermissionLevel.NONE);
        command_cobbleskills_profile = builder
                .comment("The permission level required to use the /cobbleskills profile command.")
                .defineEnum("cobbleskills.profile", PermissionLevel.NONE);
        command_cobbleskills_profession = builder
                .comment("The permission level required to use the /cobbleskills profession command.")
                .defineEnum("cobbleskills.profession", PermissionLevel.NONE);
        command_cobbleskills_admin = builder
                .comment("The permission level required to use the /cobbleskills admin command.")
                .defineEnum("cobbleskills.admin", PermissionLevel.ALL_COMMANDS);
        command_cobbleskills_admin_addExp = builder
                .comment("The permission level required to use the /cobbleskills admin addExp command.")
                .defineEnum("cobbleskills.admin.add-exp", PermissionLevel.ALL_COMMANDS);
        command_cobbleskills_admin_setLevel = builder
                .comment("The permission level required to use the /cobbleskills admin setLevel command.")
                .defineEnum("cobbleskills.admin.set-level", PermissionLevel.ALL_COMMANDS);
        builder.pop(); // pop "command"

        builder.comment("Permissions for accessing the GUI components of Cobblemon Skills").push("gui");
        gui_main_menu = builder
                .comment("The permission level required to access the main menu GUI.")
                .defineEnum("gui.main_menu", PermissionLevel.NONE);
        gui_leaderboard = builder
                .comment("The permission level required to access the leaderboard GUI.")
                .defineEnum("gui.leaderboard", PermissionLevel.NONE);
        gui_profile = builder
                .comment("The permission level required to access the profile GUI.")
                .defineEnum("gui.profile", PermissionLevel.NONE);
        gui_profile_other = builder
                .comment("The permission level required to access another player's profile GUI.")
                .defineEnum("gui.profile_other", PermissionLevel.NONE);
        builder.pop(); // pop "gui"

        builder.pop(); // pop "permissions"
    }
}
