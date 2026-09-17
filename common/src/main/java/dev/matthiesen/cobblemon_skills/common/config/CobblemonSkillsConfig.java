package dev.matthiesen.cobblemon_skills.common.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public final class CobblemonSkillsConfig {
    public static final ExperienceConfig EXPERIENCE_CONFIG;
    public static final ModConfigSpec EXPERIENCE_SPEC;

    static {
        Pair<ExperienceConfig, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(ExperienceConfig::new);
        EXPERIENCE_CONFIG = specPair.getLeft();
        EXPERIENCE_SPEC = specPair.getRight();
    }
}
