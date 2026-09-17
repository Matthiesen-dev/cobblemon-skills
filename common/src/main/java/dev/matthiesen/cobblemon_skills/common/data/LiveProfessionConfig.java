package dev.matthiesen.cobblemon_skills.common.data;

import dev.matthiesen.cobblemon_skills.common.config.def.ProfessionTierEntry;

import java.util.List;

public record LiveProfessionConfig(
        Profession profession,
        String displayName,
        List<ProfessionTierEntry> tiers
) {
}
