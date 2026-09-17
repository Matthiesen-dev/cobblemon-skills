package dev.matthiesen.cobblemon_skills.common.config;

import dev.matthiesen.matthiesen_core.common.api.permissions.PermissionLevel;
import net.neoforged.neoforge.common.ModConfigSpec;

public final class PermissionsConfig {
    public ModConfigSpec.EnumValue<PermissionLevel> command_cobbleskills;

    public PermissionsConfig(ModConfigSpec.Builder builder) {
        builder.push("permissions");

        command_cobbleskills = builder
                .comment("The permission level required to use the /cobbleskills command.")
                .defineEnum("command.cobbleskills", PermissionLevel.NONE);

        builder.pop();
    }
}
