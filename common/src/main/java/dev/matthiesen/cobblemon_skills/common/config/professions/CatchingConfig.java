package dev.matthiesen.cobblemon_skills.common.config.professions;

import com.electronwill.nightconfig.core.Config;
import dev.matthiesen.cobblemon_skills.common.config.def.PROF_DEFAULTS;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

public final class CatchingConfig extends AbstractProfession {

    // Experience
    public ModConfigSpec.DoubleValue experience_catchBaseXp;
    public ModConfigSpec.DoubleValue experience_catchLevelXpMultiplier;
    public ModConfigSpec.DoubleValue experience_catchCriticalBonusXp;

    // Rewards
    public ModConfigSpec.BooleanValue rewards_enableShinyCalculationBoosting;
    public ModConfigSpec.DoubleValue rewards_shinyCalculationPerLevelMultiplier;
    public ModConfigSpec.DoubleValue rewards_shinyCalculationMaxMultiplier;
    public ModConfigSpec.DoubleValue rewards_captureRateBonusPercentMax;
    public ModConfigSpec.DoubleValue rewards_captureRateLevelMultiplier;

    @Override
    public List<Config> getDefaultProfessionRewards() {
        return PROF_DEFAULTS.getDefaultCatchingTiers();
    }

    public CatchingConfig(ModConfigSpec.Builder builder) {
        super(builder, "Catching");

        builder.comment("Catching Experience Settings").push("experience");
        experience_catchBaseXp = builder.comment("Base experience gained from catching a Pokémon")
                .defineInRange("catchBaseXp", 200.0, 0.0, Double.MAX_VALUE);
        experience_catchLevelXpMultiplier = builder.comment("Multiplier for experience gained based on the level of the Pokémon caught")
                .defineInRange("catchLevelXpMultiplier", 8.0, 0.0, Double.MAX_VALUE);
        experience_catchCriticalBonusXp = builder.comment("Bonus experience gained from catching a Pokémon with a critical capture")
                .defineInRange("catchCriticalBonusXp", 150.0, 0.0, Double.MAX_VALUE);
        builder.pop(); // experience

        builder.comment("Catching Rewards Settings").push("rewards");
        rewards_enableShinyCalculationBoosting = builder.comment("Whether shiny calculation boosting is enabled in catching profession")
                .define("enableShinyCalculationBoosting", true);
        rewards_shinyCalculationMaxMultiplier = builder.comment("The maximum multiplier for shiny calculation in catching profession")
                .defineInRange("shinyCalculationMaxMultiplier", 10.0, 0.0, 10.0);
        rewards_shinyCalculationPerLevelMultiplier = builder.comment("The multiplier for shiny calculation per level in catching profession")
                .defineInRange("shinyCalculationPerLevelMultiplier", 0.01, 0.0, 1.0);
        rewards_captureRateBonusPercentMax = builder.comment("The maximum bonus percent for capture rate in catching profession")
                .defineInRange("captureRateBonusPercentMax", 10.0, 0.0, 100.0);
        rewards_captureRateLevelMultiplier = builder.comment("The bonus multiplier for capture rate based on catching level")
                .defineInRange("captureRateLevelMultiplier", 0.10, 0.0, 1.0);
        builder.pop(); // rewards
    }
}
