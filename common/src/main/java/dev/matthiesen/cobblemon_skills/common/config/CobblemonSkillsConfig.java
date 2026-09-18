package dev.matthiesen.cobblemon_skills.common.config;

import dev.matthiesen.cobblemon_skills.common.config.def.ProfessionTierEntry;
import dev.matthiesen.cobblemon_skills.common.data.LiveProfessionConfig;
import dev.matthiesen.cobblemon_skills.common.data.Profession;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class CobblemonSkillsConfig {
    private static final Map<Profession, LiveProfessionConfig> PROFESSION_CACHE = new HashMap<>();

    public static final ExperienceConfig EXPERIENCE_CONFIG;
    public static final ModConfigSpec EXPERIENCE_SPEC;

    public static final RewardsConfig REWARDS_CONFIG;
    public static final ModConfigSpec REWARDS_SPEC;

    public static final ProfessionsConfig PROFESSIONS_CONFIG;
    public static final ModConfigSpec PROFESSIONS_SPEC;

    public static final PermissionsConfig PERMISSIONS_CONFIG;
    public static final ModConfigSpec PERMISSIONS_SPEC;

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

        Pair<PermissionsConfig, ModConfigSpec> permissionsSpecPair = new ModConfigSpec.Builder().configure(PermissionsConfig::new);
        PERMISSIONS_CONFIG = permissionsSpecPair.getLeft();
        PERMISSIONS_SPEC = permissionsSpecPair.getRight();
    }

    public static void popCache() {
        PROFESSION_CACHE.clear();
    }

    public static LiveProfessionConfig getArcheologyConfig() {
        if (PROFESSION_CACHE.containsKey(Profession.ARCHEOLOGY)) {
            return PROFESSION_CACHE.get(Profession.ARCHEOLOGY);
        }
        String displayName = PROFESSIONS_CONFIG.archeology_professionName.get();
        List<ProfessionTierEntry> tiers = PROFESSIONS_CONFIG.archeology_professionRewards.get()
                .stream()
                .map(ProfessionTierEntry::deserialize)
                .toList();
        LiveProfessionConfig config = new LiveProfessionConfig(Profession.ARCHEOLOGY, displayName, tiers);
        PROFESSION_CACHE.put(Profession.ARCHEOLOGY, config);
        return config;
    }

    public static LiveProfessionConfig getBotanyConfig() {
        if (PROFESSION_CACHE.containsKey(Profession.BOTANY)) {
            return PROFESSION_CACHE.get(Profession.BOTANY);
        }
        String displayName = PROFESSIONS_CONFIG.botany_professionName.get();
        List<ProfessionTierEntry> tiers = PROFESSIONS_CONFIG.botany_professionRewards.get()
                .stream()
                .map(ProfessionTierEntry::deserialize)
                .toList();
        LiveProfessionConfig config = new LiveProfessionConfig(Profession.BOTANY, displayName, tiers);
        PROFESSION_CACHE.put(Profession.BOTANY, config);
        return config;
    }

    public static LiveProfessionConfig getCookingConfig() {
        if (PROFESSION_CACHE.containsKey(Profession.COOKING)) {
            return PROFESSION_CACHE.get(Profession.COOKING);
        }
        String displayName = PROFESSIONS_CONFIG.cooking_professionName.get();
        List<ProfessionTierEntry> tiers = PROFESSIONS_CONFIG.cooking_professionRewards.get()
                .stream()
                .map(ProfessionTierEntry::deserialize)
                .toList();
        LiveProfessionConfig config = new LiveProfessionConfig(Profession.COOKING, displayName, tiers);
        PROFESSION_CACHE.put(Profession.COOKING, config);
        return config;
    }

    public static LiveProfessionConfig getFishingConfig() {
        if (PROFESSION_CACHE.containsKey(Profession.FISHING)) {
            return PROFESSION_CACHE.get(Profession.FISHING);
        }
        String displayName = PROFESSIONS_CONFIG.fishing_professionName.get();
        List<ProfessionTierEntry> tiers = PROFESSIONS_CONFIG.fishing_professionRewards.get()
                .stream()
                .map(ProfessionTierEntry::deserialize)
                .toList();
        LiveProfessionConfig config = new LiveProfessionConfig(Profession.FISHING, displayName, tiers);
        PROFESSION_CACHE.put(Profession.FISHING, config);
        return config;
    }

    public static LiveProfessionConfig getCatchingConfig() {
        if (PROFESSION_CACHE.containsKey(Profession.CATCHING)) {
            return PROFESSION_CACHE.get(Profession.CATCHING);
        }
        String displayName = PROFESSIONS_CONFIG.catching_professionName.get();
        List<ProfessionTierEntry> tiers = PROFESSIONS_CONFIG.catching_professionRewards.get()
                .stream()
                .map(ProfessionTierEntry::deserialize)
                .toList();
        LiveProfessionConfig config = new LiveProfessionConfig(Profession.CATCHING, displayName, tiers);
        PROFESSION_CACHE.put(Profession.CATCHING, config);
        return config;
    }

    public static LiveProfessionConfig getTrainingConfig() {
        if (PROFESSION_CACHE.containsKey(Profession.TRAINING)) {
            return PROFESSION_CACHE.get(Profession.TRAINING);
        }
        String displayName = PROFESSIONS_CONFIG.training_professionName.get();
        List<ProfessionTierEntry> tiers = PROFESSIONS_CONFIG.training_professionRewards.get()
                .stream()
                .map(ProfessionTierEntry::deserialize)
                .toList();
        LiveProfessionConfig config = new LiveProfessionConfig(Profession.TRAINING, displayName, tiers);
        PROFESSION_CACHE.put(Profession.TRAINING, config);
        return config;
    }

    public static LiveProfessionConfig getBreedingConfig() {
        if (PROFESSION_CACHE.containsKey(Profession.BREEDING)) {
            return PROFESSION_CACHE.get(Profession.BREEDING);
        }
        String displayName = PROFESSIONS_CONFIG.breeding_professionName.get();
        List<ProfessionTierEntry> tiers = PROFESSIONS_CONFIG.breeding_professionRewards.get()
                .stream()
                .map(ProfessionTierEntry::deserialize)
                .toList();
        LiveProfessionConfig config = new LiveProfessionConfig(Profession.BREEDING, displayName, tiers);
        PROFESSION_CACHE.put(Profession.BREEDING, config);
        return config;
    }
}
