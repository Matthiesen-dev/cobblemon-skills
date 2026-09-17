package dev.matthiesen.cobblemon_skills.common.config;

import dev.matthiesen.cobblemon_skills.common.config.def.ProfessionTierEntry;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public final class CobblemonSkillsConfig {
    public static final ExperienceConfig EXPERIENCE_CONFIG;
    public static final ModConfigSpec EXPERIENCE_SPEC;

    public static final RewardsConfig REWARDS_CONFIG;
    public static final ModConfigSpec REWARDS_SPEC;

    public static final ProfessionsConfig PROFESSIONS_CONFIG;
    public static final ModConfigSpec PROFESSIONS_SPEC;

    static {
        Pair<ExperienceConfig, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(ExperienceConfig::new);
        EXPERIENCE_CONFIG = specPair.getLeft();
        EXPERIENCE_SPEC = specPair.getRight();

        Pair<RewardsConfig, ModConfigSpec> rewardsSpecPair = new ModConfigSpec.Builder().configure(RewardsConfig::new);
        REWARDS_CONFIG = rewardsSpecPair.getLeft();
        REWARDS_SPEC = rewardsSpecPair.getRight();

        Pair<ProfessionsConfig, ModConfigSpec> professionsSpecPair = new ModConfigSpec.Builder().configure(ProfessionsConfig::new);
        PROFESSIONS_CONFIG = professionsSpecPair.getLeft();
        PROFESSIONS_SPEC = professionsSpecPair.getRight();
    }

    public static LiveProfessionConfig getArcheologyConfig() {
        List<ProfessionTierEntry> tiers = PROFESSIONS_CONFIG.archeology_professionRewards.get()
                .stream()
                .map(ProfessionTierEntry::deserialize)
                .toList();
        return new LiveProfessionConfig(tiers);
    }

    public static LiveProfessionConfig getBotanyConfig() {
        List<ProfessionTierEntry> tiers = PROFESSIONS_CONFIG.botany_professionRewards.get()
                .stream()
                .map(ProfessionTierEntry::deserialize)
                .toList();
        return new LiveProfessionConfig(tiers);
    }

    public static LiveProfessionConfig getCookingConfig() {
        List<ProfessionTierEntry> tiers = PROFESSIONS_CONFIG.cooking_professionRewards.get()
                .stream()
                .map(ProfessionTierEntry::deserialize)
                .toList();
        return new LiveProfessionConfig(tiers);
    }

    public static LiveProfessionConfig getFishingConfig() {
        List<ProfessionTierEntry> tiers = PROFESSIONS_CONFIG.fishing_professionRewards.get()
                .stream()
                .map(ProfessionTierEntry::deserialize)
                .toList();
        return new LiveProfessionConfig(tiers);
    }

    public static LiveProfessionConfig getCatchingConfig() {
        List<ProfessionTierEntry> tiers = PROFESSIONS_CONFIG.catching_professionRewards.get()
                .stream()
                .map(ProfessionTierEntry::deserialize)
                .toList();
        return new LiveProfessionConfig(tiers);
    }

    public static LiveProfessionConfig getTrainingConfig() {
        List<ProfessionTierEntry> tiers = PROFESSIONS_CONFIG.training_professionRewards.get()
                .stream()
                .map(ProfessionTierEntry::deserialize)
                .toList();
        return new LiveProfessionConfig(tiers);
    }

    public static LiveProfessionConfig getBreedingConfig() {
        List<ProfessionTierEntry> tiers = PROFESSIONS_CONFIG.breeding_professionRewards.get()
                .stream()
                .map(ProfessionTierEntry::deserialize)
                .toList();
        return new LiveProfessionConfig(tiers);
    }

    public record LiveProfessionConfig(List<ProfessionTierEntry> tiers) {}
}
