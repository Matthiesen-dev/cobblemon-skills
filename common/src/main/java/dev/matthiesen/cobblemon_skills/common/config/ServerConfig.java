package dev.matthiesen.cobblemon_skills.common.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class ServerConfig {

    // General
    public ModConfigSpec.IntValue professionMaxLevel;
    public ModConfigSpec.IntValue professionBaseExpPerLevel;
    public ModConfigSpec.IntValue professionExpLevelMultiplier;

    public ServerConfig(ModConfigSpec.Builder builder) {
        builder.comment("Server configuration for Cobblemon Skills").push("server");

        professionMaxLevel = builder.comment("Maximum level for all professions")
                .defineInRange("professionMaxLevel", 100, 1, Integer.MAX_VALUE);
        professionBaseExpPerLevel = builder.comment("Base experience required for each level")
                .defineInRange("professionBaseExpPerLevel", 1020, 1, Integer.MAX_VALUE);
        professionExpLevelMultiplier = builder.comment("Multiplier for experience required per level")
                .defineInRange("professionExpLevelMultiplier", 20, 1, Integer.MAX_VALUE);

        builder.pop(); // pop "server"
    }
}
