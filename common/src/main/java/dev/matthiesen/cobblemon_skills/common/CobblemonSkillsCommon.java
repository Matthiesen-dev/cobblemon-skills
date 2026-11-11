package dev.matthiesen.cobblemon_skills.common;

import dev.faststats.Token;
import com.cobblemon.mod.common.api.Priority;
import com.cobblemon.mod.common.api.events.CobblemonEvents;
import dev.matthiesen.cobblemon_skills.common.commands.RootCommand;
import dev.matthiesen.cobblemon_skills.common.config.CobblemonSkillsConfig;
import dev.matthiesen.cobblemon_skills.common.data.AwardExperience;
import dev.matthiesen.cobblemon_skills.common.registry.PermissionsRegistry;
import dev.matthiesen.cobblemon_skills.common.runtime.ProfessionManager;
import dev.matthiesen.matthiesen_core.common.AbstractCommonMod;
import dev.matthiesen.matthiesen_core.common.api.events.PlatformEvents;
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

        CobblemonSkillsConfig.register(this);

        PermissionsRegistry.init();
        getCommandsRegistryManager().registerCommand(RootCommand.CMD);

        // Skills Events
        Events.AWARD_EXPERIENCE.subscribe(AwardExperience::handle);

        // Platform Events
        PlatformEvents.SERVER_END_TICK.subscribe(ProfessionManager::onServerTick);
        PlatformEvents.PLAYER_BLOCK_BREAK.subscribe(ProfessionManager::onBlockBreak);

        // Cobblemon Events
        CobblemonEvents.FOSSIL_REVIVED.subscribe(Priority.LOWEST, ProfessionManager::onFossilRevived);
        CobblemonEvents.BOBBER_SPAWN_POKEMON_POST.subscribe(Priority.LOWEST, ProfessionManager::onFishingBobberSpawn);
        CobblemonEvents.APRICORN_HARVESTED.subscribe(Priority.LOWEST, ProfessionManager::onApricornHarvest);
        CobblemonEvents.BERRY_HARVEST.subscribe(Priority.LOWEST, ProfessionManager::onBerryHarvest);
        CobblemonEvents.POKEMON_CATCH_RATE.subscribe(Priority.LOWEST, ProfessionManager::onPokemonCatchRateCalculation);
        CobblemonEvents.POKEMON_CAPTURED.subscribe(Priority.LOWEST, ProfessionManager::onPokemonCaptured);
        CobblemonEvents.COLLECT_EGG.subscribe(Priority.LOWEST, ProfessionManager::onCollectEgg);
        CobblemonEvents.HATCH_EGG_PRE.subscribe(Priority.LOWEST, ProfessionManager::onHatchEggPre);
        CobblemonEvents.HATCH_EGG_POST.subscribe(Priority.LOWEST, ProfessionManager::onHatchEggPost);
        CobblemonEvents.EXPERIENCE_GAINED_EVENT_PRE.subscribe(Priority.LOWEST, ProfessionManager::onExperienceGainedPre);
        CobblemonEvents.EXPERIENCE_GAINED_EVENT_POST.subscribe(Priority.LOWEST, ProfessionManager::onExperienceGainedPost);
        CobblemonEvents.LEVEL_UP_EVENT.subscribe(Priority.LOWEST, ProfessionManager::onLevelUp);
        CobblemonEvents.LEFTOVERS_CREATED.subscribe(Priority.LOWEST, ProfessionManager::onLeftoversCreated);
        CobblemonEvents.BATTLE_VICTORY.subscribe(Priority.LOWEST, ProfessionManager::onBattleVictory);
        CobblemonEvents.SHINY_CHANCE_CALCULATION.subscribe(Priority.LOWEST, ProfessionManager::onShinyCalculation);

        createInfoLog("Initialized");
    }
}
