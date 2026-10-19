package dev.matthiesen.cobblemon_skills.common.config.professions;

import com.electronwill.nightconfig.core.Config;
import dev.matthiesen.cobblemon_skills.common.config.def.PROF_DEFAULTS;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

public final class TrainingConfig extends AbstractProfession {

    // Experience
    public ModConfigSpec.DoubleValue experience_baseXp;
    public ModConfigSpec.DoubleValue experience_leftoversXp;
    public ModConfigSpec.DoubleValue experience_levelUpXpMultiplier;

    // Rewards
    public ModConfigSpec.DoubleValue rewards_experienceFromBattleMax;
    public ModConfigSpec.DoubleValue rewards_experienceFromBattleExpMultiplier;
    public ModConfigSpec.DoubleValue rewards_levelUpBonusExpPercentageMax;
    public ModConfigSpec.DoubleValue rewards_levelUpBonusExpPercentageLevelMultiplier;

    @Override
    public List<Config> getDefaultProfessionRewards() {
        return PROF_DEFAULTS.getDefaultTrainingTiers();
    }

    public TrainingConfig(ModConfigSpec.Builder builder) {
        super(builder, "Training");

        builder.comment("Training Experience Settings").push("experience");
        experience_baseXp = builder.comment("Base experience gained from training a Pokémon")
                .defineInRange("baseXp", 300.0, 0.0, Double.MAX_VALUE);
        experience_leftoversXp = builder.comment("Experience gained from using Leftovers on a Pokémon")
                .defineInRange("leftoversXp", 100.0, 0.0, Double.MAX_VALUE);
        experience_levelUpXpMultiplier = builder.comment("Multiplier for experience gained when a Pokémon levels up")
                .defineInRange("levelUpXpMultiplier", 100.0, 0.0, Double.MAX_VALUE);
        builder.pop(); // experience

        builder.comment("Training Rewards Settings").push("rewards");
        rewards_experienceFromBattleMax = builder.comment("The maximum experience from battle in training profession")
                .defineInRange("experienceFromBattleMax", 20.0, 0.0, 1000.0);
        rewards_experienceFromBattleExpMultiplier = builder.comment("The experience multiplier from battle based on training level")
                .defineInRange("experienceFromBattleExpMultiplier", 0.20, 0.0, 10.0);
        rewards_levelUpBonusExpPercentageMax = builder.comment("The maximum bonus experience percentage for level up in training profession")
                .defineInRange("levelUpBonusExpPercentageMax", 25.0, 0.0, 100.0);
        rewards_levelUpBonusExpPercentageLevelMultiplier = builder.comment("The bonus experience percentage multiplier for level up based on training level")
                .defineInRange("levelUpBonusExpPercentageLevelMultiplier", 0.25, 0.0, 1.0);
        builder.pop(); // rewards
    }
}
