package dev.matthiesen.cobblemon_skills.common.config.professions;

import com.electronwill.nightconfig.core.Config;
import dev.matthiesen.cobblemon_skills.common.config.def.PROF_DEFAULTS;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

public final class CookingConfig extends AbstractProfession {

    // Experience
    public ModConfigSpec.IntValue experience_baseXp;
    public ModConfigSpec.IntValue experience_tier1Xp;
    public ModConfigSpec.IntValue experience_tier2Xp;
    public ModConfigSpec.IntValue experience_tier3Xp;
    public ModConfigSpec.IntValue experience_tier4Xp;

    // Rewards
    public ModConfigSpec.BooleanValue rewards_randomExtraPlayerExp;
    public ModConfigSpec.IntValue rewards_randomExtraPlayerExpMinLvl;
    public ModConfigSpec.IntValue rewards_randomExtraPlayerExpBaseAmount;
    public ModConfigSpec.IntValue rewards_randomExtraPlayerExpLevelMultiplier;

    @Override
    public List<Config> getDefaultProfessionRewards() {
        return PROF_DEFAULTS.getDefaultCookingTiers();
    }

    public CookingConfig(ModConfigSpec.Builder builder) {
        super(builder, "Cooking");

        builder.comment("Cooking Experience Settings").push("experience");
        experience_baseXp = builder.comment("Base experience gained from cooking")
                .defineInRange("baseXp", 100, 0, Integer.MAX_VALUE);
        experience_tier1Xp = builder.comment("Experience gained from cooking a Tier 1 dish")
                .defineInRange("tier1Xp", 150, 0, Integer.MAX_VALUE);
        experience_tier2Xp = builder.comment("Experience gained from cooking a Tier 2 dish")
                .defineInRange("tier2Xp", 200, 0, Integer.MAX_VALUE);
        experience_tier3Xp = builder.comment("Experience gained from cooking a Tier 3 dish")
                .defineInRange("tier3Xp", 250, 0, Integer.MAX_VALUE);
        experience_tier4Xp = builder.comment("Experience gained from cooking a Tier 4 dish")
                .defineInRange("tier4Xp", 300, 0, Integer.MAX_VALUE);
        builder.pop(); // experience

        builder.comment("Cooking Rewards Settings").push("rewards");
        rewards_randomExtraPlayerExp = builder.comment("Whether extra player experience is randomly applied to cooking rewards")
                .define("randomExtraPlayerExp", true);
        rewards_randomExtraPlayerExpMinLvl = builder.comment("The minimum level required for extra player experience in cooking profession")
                .defineInRange("randomExtraPlayerExpMinLvl", 10, 0, Integer.MAX_VALUE);
        rewards_randomExtraPlayerExpBaseAmount = builder.comment("The base amount of extra player experience applied to cooking rewards")
                .defineInRange("randomExtraPlayerExpBaseAmount", 10, 0, Integer.MAX_VALUE);
        rewards_randomExtraPlayerExpLevelMultiplier = builder.comment("The multiplier for extra player experience based on cooking level")
                .defineInRange("randomExtraPlayerExpLevelMultiplier", 2, 0, Integer.MAX_VALUE);
        builder.pop(); // rewards
    }
}
