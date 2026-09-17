package dev.matthiesen.cobblemon_skills.common.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public final class CobblemonSkillsConfig {
    public static final ExperienceConfig EXPERIENCE_CONFIG;
    public static final ModConfigSpec EXPERIENCE_SPEC;

    public static final RewardsConfig REWARDS_CONFIG;
    public static final ModConfigSpec REWARDS_SPEC;

    static {
        Pair<ExperienceConfig, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(ExperienceConfig::new);
        EXPERIENCE_CONFIG = specPair.getLeft();
        EXPERIENCE_SPEC = specPair.getRight();

        Pair<RewardsConfig, ModConfigSpec> rewardsSpecPair = new ModConfigSpec.Builder().configure(RewardsConfig::new);
        REWARDS_CONFIG = rewardsSpecPair.getLeft();
        REWARDS_SPEC = rewardsSpecPair.getRight();
    }
}
