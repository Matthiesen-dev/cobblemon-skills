package dev.matthiesen.cobblemon_skills.common;

import dev.faststats.Token;
import dev.matthiesen.cobblemon_skills.common.commands.RootCommand;
import dev.matthiesen.cobblemon_skills.common.config.CobblemonSkillsConfig;
import dev.matthiesen.cobblemon_skills.common.data.AwardExperience;
import dev.matthiesen.cobblemon_skills.common.registry.PermissionsRegistry;
import dev.matthiesen.cobblemon_skills.common.runtime.ProfessionEventManager;
import dev.matthiesen.matthiesen_core.common.AbstractCommonMod;
import dev.matthiesen.matthiesen_core.common.api.events.observable.results.ResultEventObservable;
import org.jetbrains.annotations.NotNull;

public final class CobblemonSkillsCommon extends AbstractCommonMod {
    public static final String MOD_ID = "cobblemon_skills";
    public static final String MOD_NAME = "Cobblemon Skills";
    public static @Token final String METRICS_TOKEN = "b3e8303bd1bab88edbdb97c598b825d4";
    public static final CobblemonSkillsCommon INSTANCE = new CobblemonSkillsCommon();

    public static class Events {
        public static final ResultEventObservable<AwardExperience, Integer> AWARD_EXPERIENCE = new ResultEventObservable<>();
    }

    public CobblemonSkillsCommon() {
        super(MOD_ID, MOD_NAME);
    }

    @Override
    public @Token @NotNull String getMetricsToken() {
        return METRICS_TOKEN;
    }

    public void initialize() {
        super.initialize();

        // Register configs
        CobblemonSkillsConfig.register(this);

        // Register Permissions
        PermissionsRegistry.init();

        // Register commands
        getCommandsRegistryManager().registerCommand(RootCommand.CMD);

        // Register event listeners
        Events.AWARD_EXPERIENCE.subscribe(AwardExperience::handle);
        ProfessionEventManager.registerEventListeners();

        createInfoLog("Initialized");
    }
}
