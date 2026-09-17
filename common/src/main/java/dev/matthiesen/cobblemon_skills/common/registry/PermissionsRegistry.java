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
