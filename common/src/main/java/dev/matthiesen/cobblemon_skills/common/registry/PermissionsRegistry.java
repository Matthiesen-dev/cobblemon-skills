package dev.matthiesen.cobblemon_skills.common.registry;

import dev.matthiesen.cobblemon_skills.common.CobblemonSkillsCommon;
import dev.matthiesen.cobblemon_skills.common.config.CobblemonSkillsConfig;
import dev.matthiesen.matthiesen_core.common.api.permissions.Permission;
import dev.matthiesen.matthiesen_core.common.api.permissions.PermissionLevel;
import dev.matthiesen.matthiesen_core.common.utility.AbstractPermission;
import net.minecraft.commands.CommandSourceStack;

public final class PermissionsRegistry {
    public static Permission COMMAND_ROOT_PERMISSION = register(
            "command.cobbleskills",
            CobblemonSkillsConfig.PERMISSIONS_CONFIG.command_cobbleskills.get()
    );
    public static Permission COMMAND_RESET_PERMISSION = register(
            "command.cobbleskills.reset",
            CobblemonSkillsConfig.PERMISSIONS_CONFIG.command_cobbleskills_reset.get()
    );
    public static Permission COMMAND_RESET_SELF_PERMISSION = register(
            "command.cobbleskills.reset.self",
            CobblemonSkillsConfig.PERMISSIONS_CONFIG.command_cobbleskills_reset_self.get()
    );
    public static Permission COMMAND_RESET_PLAYER_PERMISSION = register(
            "command.cobbleskills.reset.player",
            CobblemonSkillsConfig.PERMISSIONS_CONFIG.command_cobbleskills_reset_player.get()
    );
    public static Permission COMMAND_RESET_ALL_PERMISSION = register(
            "command.cobbleskills.reset.all",
            CobblemonSkillsConfig.PERMISSIONS_CONFIG.command_cobbleskills_reset_all.get()
    );
    public static Permission COMMAND_LEADERBOARD_PERMISSION = register(
            "command.cobbleskills.leaderboard",
            CobblemonSkillsConfig.PERMISSIONS_CONFIG.command_cobbleskills_leaderboard.get()
    );
    public static Permission COMMAND_PROFILE_PERMISSION = register(
            "command.cobbleskills.profile",
            CobblemonSkillsConfig.PERMISSIONS_CONFIG.command_cobbleskills_profile.get()
    );
    public static Permission COMMAND_PROFESSION_PERMISSION = register(
            "command.cobbleskills.profession",
            CobblemonSkillsConfig.PERMISSIONS_CONFIG.command_cobbleskills_profession.get()
    );
    public static Permission COMMAND_ADMIN_PERMISSION = register(
            "command.cobbleskills.admin",
            CobblemonSkillsConfig.PERMISSIONS_CONFIG.command_cobbleskills_admin.get()
    );
    public static Permission COMMAND_ADMIN_ADD_EXP_PERMISSION = register(
            "command.cobbleskills.admin.add-exp",
            CobblemonSkillsConfig.PERMISSIONS_CONFIG.command_cobbleskills_admin_addExp.get()
    );
    public static Permission COMMAND_ADMIN_SET_LEVEL_PERMISSION = register(
            "command.cobbleskills.admin.set-level",
            CobblemonSkillsConfig.PERMISSIONS_CONFIG.command_cobbleskills_admin_setLevel.get()
    );

    public static void init() {}

    public static boolean checkPermission(CommandSourceStack source, Permission permission) {
        return CobblemonSkillsCommon.INSTANCE.getPermissionsManager().getPermissionValidator().hasPermission(source, permission);
    }

    @SuppressWarnings("SameParameterValue")
    private static Permission register(String node, PermissionLevel level) {
        var newPermission = modPermission(node, level);
        CobblemonSkillsCommon.INSTANCE.getPermissionsManager().registerPermission(newPermission);
        return newPermission;
    }

    private static Permission modPermission(String node, PermissionLevel level) {
        return new AbstractPermission(node, level) {
            @Override
            protected String getModId() {
                return CobblemonSkillsCommon.MOD_ID;
            }

            @Override
            protected String getPermissionNamespace() {
                return "CobblemonSkills";
            }
        };
    }
}
