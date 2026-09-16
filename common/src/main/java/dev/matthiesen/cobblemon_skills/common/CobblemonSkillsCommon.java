package dev.matthiesen.cobblemon_skills.common;

import dev.matthiesen.libs.faststats.Token;
import dev.matthiesen.matthiesen_core.common.AbstractCommonMod;
import org.jetbrains.annotations.NotNull;

public final class CobblemonSkillsCommon extends AbstractCommonMod {
    public static final String MOD_ID = "cobblemon_skills";
    public static final String MOD_NAME = "Cobblemon Skills";
    public static @Token final String METRICS_TOKEN = "b3e8303bd1bab88edbdb97c598b825d4";
    public static final CobblemonSkillsCommon INSTANCE = new CobblemonSkillsCommon();

    public CobblemonSkillsCommon() {
        super(MOD_ID, MOD_NAME);
    }

    @Override
    public @Token @NotNull String getMetricsToken() {
        return METRICS_TOKEN;
    }

    public void initialize() {
        super.initialize();

        createInfoLog("Initialized");
    }
}
