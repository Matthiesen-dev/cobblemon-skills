package dev.matthiesen.cobblemon_skills.common.config;

import dev.matthiesen.cobblemon_skills.common.config.def.ProfessionTierEntry;
import dev.matthiesen.cobblemon_skills.common.config.professions.*;
import dev.matthiesen.cobblemon_skills.common.data.LiveProfessionConfig;
import dev.matthiesen.cobblemon_skills.common.data.Profession;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class CobblemonSkillsConfig {
    private static final Map<Profession, LiveProfessionConfig> PROFESSION_CACHE = new HashMap<>();

    public static final ServerConfig SERVER_CONFIG;
    public static final ModConfigSpec SERVER_SPEC;

    public static final PermissionsConfig PERMISSIONS_CONFIG;
    public static final ModConfigSpec PERMISSIONS_SPEC;

    public static final ArcheologyConfig ARCHEOLOGY_CONFIG;
    public static final ModConfigSpec ARCHEOLOGY_SPEC;

    public static final BotanyConfig BOTANY_CONFIG;
    public static final ModConfigSpec BOTANY_SPEC;

    public static final BreedingConfig BREEDING_CONFIG;
    public static final ModConfigSpec BREEDING_SPEC;

    public static final CatchingConfig CATCHING_CONFIG;
    public static final ModConfigSpec CATCHING_SPEC;

    public static final CookingConfig COOKING_CONFIG;
    public static final ModConfigSpec COOKING_SPEC;

    public static final FishingConfig FISHING_CONFIG;
    public static final ModConfigSpec FISHING_SPEC;

    public static final TrainingConfig TRAINING_CONFIG;
    public static final ModConfigSpec TRAINING_SPEC;

    static {
        Pair<ServerConfig, ModConfigSpec> serverSpecPair = new ModConfigSpec.Builder().configure(ServerConfig::new);
        SERVER_CONFIG = serverSpecPair.getLeft();
        SERVER_SPEC = serverSpecPair.getRight();

        Pair<PermissionsConfig, ModConfigSpec> permissionsSpecPair = new ModConfigSpec.Builder().configure(PermissionsConfig::new);
        PERMISSIONS_CONFIG = permissionsSpecPair.getLeft();
        PERMISSIONS_SPEC = permissionsSpecPair.getRight();

        Pair<ArcheologyConfig, ModConfigSpec> archeologySpecPair = new ModConfigSpec.Builder().configure(ArcheologyConfig::new);
        ARCHEOLOGY_CONFIG = archeologySpecPair.getLeft();
        ARCHEOLOGY_SPEC = archeologySpecPair.getRight();

        Pair<BotanyConfig, ModConfigSpec> botanySpecPair = new ModConfigSpec.Builder().configure(BotanyConfig::new);
        BOTANY_CONFIG = botanySpecPair.getLeft();
        BOTANY_SPEC = botanySpecPair.getRight();

        Pair<BreedingConfig, ModConfigSpec> breedingSpecPair = new ModConfigSpec.Builder().configure(BreedingConfig::new);
        BREEDING_CONFIG = breedingSpecPair.getLeft();
        BREEDING_SPEC = breedingSpecPair.getRight();

        Pair<CatchingConfig, ModConfigSpec> catchingSpecPair = new ModConfigSpec.Builder().configure(CatchingConfig::new);
        CATCHING_CONFIG = catchingSpecPair.getLeft();
        CATCHING_SPEC = catchingSpecPair.getRight();

        Pair<CookingConfig, ModConfigSpec> cookingSpecPair = new ModConfigSpec.Builder().configure(CookingConfig::new);
        COOKING_CONFIG = cookingSpecPair.getLeft();
        COOKING_SPEC = cookingSpecPair.getRight();

        Pair<FishingConfig, ModConfigSpec> fishingSpecPair = new ModConfigSpec.Builder().configure(FishingConfig::new);
        FISHING_CONFIG = fishingSpecPair.getLeft();
        FISHING_SPEC = fishingSpecPair.getRight();

        Pair<TrainingConfig, ModConfigSpec> trainingSpecPair = new ModConfigSpec.Builder().configure(TrainingConfig::new);
        TRAINING_CONFIG = trainingSpecPair.getLeft();
        TRAINING_SPEC = trainingSpecPair.getRight();
    }

    public static void popCache() {
        PROFESSION_CACHE.clear();
    }

    public static LiveProfessionConfig getArcheologyConfig() {
        if (PROFESSION_CACHE.containsKey(Profession.ARCHEOLOGY)) {
            return PROFESSION_CACHE.get(Profession.ARCHEOLOGY);
        }
        String displayName = ARCHEOLOGY_CONFIG.professionName.get();
        List<ProfessionTierEntry> tiers = ARCHEOLOGY_CONFIG.professionRewards.get()
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
        String displayName = BOTANY_CONFIG.professionName.get();
        List<ProfessionTierEntry> tiers = BOTANY_CONFIG.professionRewards.get()
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
        String displayName = COOKING_CONFIG.professionName.get();
        List<ProfessionTierEntry> tiers = COOKING_CONFIG.professionRewards.get()
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
        String displayName = FISHING_CONFIG.professionName.get();
        List<ProfessionTierEntry> tiers = FISHING_CONFIG.professionRewards.get()
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
        String displayName = CATCHING_CONFIG.professionName.get();
        List<ProfessionTierEntry> tiers = CATCHING_CONFIG.professionRewards.get()
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
        String displayName = TRAINING_CONFIG.professionName.get();
        List<ProfessionTierEntry> tiers = TRAINING_CONFIG.professionRewards.get()
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
        String displayName = BREEDING_CONFIG.professionName.get();
        List<ProfessionTierEntry> tiers = BREEDING_CONFIG.professionRewards.get()
                .stream()
                .map(ProfessionTierEntry::deserialize)
                .toList();
        LiveProfessionConfig config = new LiveProfessionConfig(Profession.BREEDING, displayName, tiers);
        PROFESSION_CACHE.put(Profession.BREEDING, config);
        return config;
    }
}
