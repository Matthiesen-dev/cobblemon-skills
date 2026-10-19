package dev.matthiesen.cobblemon_skills.common.config.professions;

import com.electronwill.nightconfig.core.Config;
import dev.matthiesen.cobblemon_skills.common.config.def.PROF_DEFAULTS;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

public final class BreedingConfig extends AbstractProfession {

    // Experience
    public ModConfigSpec.DoubleValue experience_eggCollectedBaseXp;
    public ModConfigSpec.DoubleValue experience_eggHatchedBaseXp;
    public ModConfigSpec.DoubleValue experience_eggHatchedCycleBonusXp;

    // Rewards
    public ModConfigSpec.DoubleValue rewards_baseShinyChange;
    public ModConfigSpec.DoubleValue rewards_shinyLevelBonusMultiplier;
    public ModConfigSpec.DoubleValue rewards_shinyMaxPercentage;
    public ModConfigSpec.IntValue rewards_friendshipBonusMax;
    public ModConfigSpec.IntValue rewards_friendshipBonusLevelDivider;

    @Override
    public List<Config> getDefaultProfessionRewards() {
        return PROF_DEFAULTS.getDefaultBreedingTiers();
    }

    public BreedingConfig(ModConfigSpec.Builder builder) {
        super(builder, "Breeding");

        builder.comment("Breeding Experience Settings").push("experience");
        experience_eggCollectedBaseXp = builder.comment("Base experience gained from collecting an egg")
                .defineInRange("eggCollectedBaseXp", 100.0, 0.0, Double.MAX_VALUE);
        experience_eggHatchedBaseXp = builder.comment("Base experience gained from hatching an egg")
                .defineInRange("eggHatchedBaseXp", 200.0, 0.0, Double.MAX_VALUE);
        experience_eggHatchedCycleBonusXp = builder.comment("Bonus experience gained for each cycle of hatching an egg")
                .defineInRange("eggHatchedCycleBonusXp", 4.0, 0.0, Double.MAX_VALUE);
        builder.pop(); // experience

        builder.comment("Breeding Rewards Settings").push("rewards");
        rewards_baseShinyChange = builder.comment("The base shiny chance for breeding profession")
                .defineInRange("baseShinyChange", 0.01, 0.0, 1.0);
        rewards_shinyLevelBonusMultiplier = builder.comment("The bonus multiplier for shiny chance based on breeding level")
                .defineInRange("shinyLevelBonusMultiplier", 0.001, 0.0, 1.0);
        rewards_shinyMaxPercentage = builder.comment("The maximum shiny percentage for breeding profession")
                .defineInRange("shinyMaxPercentage", 0.10, 0.0, 1.0);
        rewards_friendshipBonusMax = builder.comment("The maximum friendship bonus for breeding profession")
                .defineInRange("friendshipBonusMax", 20, 0, 100);
        rewards_friendshipBonusLevelDivider = builder.comment("The level divider for friendship bonus in breeding profession")
                .defineInRange("friendshipBonusLevelDivider", 5, 1, 100);
        builder.pop(); // rewards
    }
}