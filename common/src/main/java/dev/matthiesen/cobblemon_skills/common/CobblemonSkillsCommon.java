package dev.matthiesen.cobblemon_skills.common;

import com.cobblemon.mod.common.api.Priority;
import com.cobblemon.mod.common.api.events.CobblemonEvents;
import dev.matthiesen.cobblemon_skills.common.runtime.ProfessionManager;
import dev.matthiesen.libs.faststats.Token;
import dev.matthiesen.matthiesen_core.common.AbstractCommonMod;
import dev.matthiesen.matthiesen_core.common.api.events.PlatformEvents;
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

        PlatformEvents.SERVER_END_TICK.subscribe(ProfessionManager::onServerTick);

        // TODO: Archeology

        // TODO: Botany

        // TODO: Fishing

        // Catching
        CobblemonEvents.POKEMON_CATCH_RATE.subscribe(Priority.LOWEST, ProfessionManager::onPokemonCatchRateCalculation);
        CobblemonEvents.POKEMON_CAPTURED.subscribe(Priority.LOWEST, ProfessionManager::onPokemonCaptured);

        // Breeding
        CobblemonEvents.COLLECT_EGG.subscribe(Priority.LOWEST, ProfessionManager::onCollectEgg);
        CobblemonEvents.HATCH_EGG_PRE.subscribe(Priority.LOWEST, ProfessionManager::onHatchEggPre);
        CobblemonEvents.HATCH_EGG_POST.subscribe(Priority.LOWEST, ProfessionManager::onHatchEggPost);

        // Training
        CobblemonEvents.EXPERIENCE_GAINED_EVENT_PRE.subscribe(Priority.LOWEST, ProfessionManager::onExperienceGainedPre);
        CobblemonEvents.EXPERIENCE_GAINED_EVENT_POST.subscribe(Priority.LOWEST, ProfessionManager::onExperienceGainedPost);
        CobblemonEvents.LEVEL_UP_EVENT.subscribe(Priority.LOWEST, ProfessionManager::onLevelUp);
        CobblemonEvents.BATTLE_VICTORY.subscribe(Priority.LOWEST, ProfessionManager::onBattleVictory);

        createInfoLog("Initialized");
    }
}
