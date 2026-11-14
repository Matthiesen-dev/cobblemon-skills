package dev.matthiesen.cobblemon_skills.common.config;

import dev.matthiesen.cobblemon_skills.common.CobblemonSkillsCommon;
import dev.matthiesen.cobblemon_skills.common.config.def.ProfessionTierEntry;
import dev.matthiesen.cobblemon_skills.common.config.professions.*;
import dev.matthiesen.cobblemon_skills.common.data.*;
import dev.matthiesen.matthiesen_core.common.AbstractCommonMod;
import dev.matthiesen.matthiesen_core.common.api.platform.loader.ModConfigType;
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

    public static final TextConfig TEXT_CONFIG;
    public static final ModConfigSpec TEXT_SPEC;

    public static final class Professions {
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

        public static String professionConfig(String name) {
            return CobblemonSkillsCommon.INSTANCE.modConfigPath("professions/" + name);
        }

        public static void register(AbstractCommonMod mod) {
            String modId = mod.getModId();

            mod.registerModConfig(modId, ModConfigType.COMMON, ARCHEOLOGY_SPEC, professionConfig("archeology"));
            mod.registerModConfig(modId, ModConfigType.COMMON, BOTANY_SPEC, professionConfig("botany"));
            mod.registerModConfig(modId, ModConfigType.COMMON, BREEDING_SPEC, professionConfig("breeding"));
            mod.registerModConfig(modId, ModConfigType.COMMON, CATCHING_SPEC, professionConfig("catching"));
            mod.registerModConfig(modId, ModConfigType.COMMON, COOKING_SPEC, professionConfig("cooking"));
            mod.registerModConfig(modId, ModConfigType.COMMON, FISHING_SPEC, professionConfig("fishing"));
            mod.registerModConfig(modId, ModConfigType.COMMON, TRAINING_SPEC, professionConfig("training"));
        }
    }

    static {
        Pair<ServerConfig, ModConfigSpec> serverSpecPair = new ModConfigSpec.Builder().configure(ServerConfig::new);
        SERVER_CONFIG = serverSpecPair.getLeft();
        SERVER_SPEC = serverSpecPair.getRight();

        Pair<PermissionsConfig, ModConfigSpec> permissionsSpecPair = new ModConfigSpec.Builder().configure(PermissionsConfig::new);
        PERMISSIONS_CONFIG = permissionsSpecPair.getLeft();
        PERMISSIONS_SPEC = permissionsSpecPair.getRight();

        Pair<TextConfig, ModConfigSpec> textSpecPair = new ModConfigSpec.Builder().configure(TextConfig::new);
        TEXT_CONFIG = textSpecPair.getLeft();
        TEXT_SPEC = textSpecPair.getRight();
    }

    public static void register(AbstractCommonMod mod) {
        String modId = mod.getModId();

        mod.registerModConfig(modId, ModConfigType.COMMON, SERVER_SPEC, mod.modConfigPath("server"));
        mod.registerModConfig(modId, ModConfigType.COMMON, TEXT_SPEC, mod.modConfigPath("text"));
        mod.registerModConfig(modId, ModConfigType.STARTUP, PERMISSIONS_SPEC, mod.modConfigPath("permissions"));
        Professions.register(mod);
    }

    public static void popCache() {
        PROFESSION_CACHE.clear();
    }

    public static LiveProfessionConfig getArcheologyConfig() {
        return PROFESSION_CACHE.computeIfAbsent(Profession.ARCHEOLOGY, p -> {
            String displayName = Professions.ARCHEOLOGY_CONFIG.professionName.get();
            List<ProfessionTierEntry> tiers = Professions.ARCHEOLOGY_CONFIG.professionRewards.get()
                    .stream()
                    .map(ProfessionTierEntry::deserialize)
                    .toList();
            return new LiveProfessionConfig(Profession.ARCHEOLOGY, displayName, tiers);
        });
    }

    public static LiveProfessionConfig getBotanyConfig() {
        return PROFESSION_CACHE.computeIfAbsent(Profession.BOTANY, p -> {
            String displayName = Professions.BOTANY_CONFIG.professionName.get();
            List<ProfessionTierEntry> tiers = Professions.BOTANY_CONFIG.professionRewards.get()
                    .stream()
                    .map(ProfessionTierEntry::deserialize)
                    .toList();
            return new LiveProfessionConfig(Profession.BOTANY, displayName, tiers);
        });
    }

    public static LiveProfessionConfig getCookingConfig() {
        return PROFESSION_CACHE.computeIfAbsent(Profession.COOKING, p -> {
            String displayName = Professions.COOKING_CONFIG.professionName.get();
            List<ProfessionTierEntry> tiers = Professions.COOKING_CONFIG.professionRewards.get()
                    .stream()
                    .map(ProfessionTierEntry::deserialize)
                    .toList();
            return new LiveProfessionConfig(Profession.COOKING, displayName, tiers);
        });
    }

    public static LiveProfessionConfig getFishingConfig() {
        return PROFESSION_CACHE.computeIfAbsent(Profession.FISHING, p -> {
            String displayName = Professions.FISHING_CONFIG.professionName.get();
            List<ProfessionTierEntry> tiers = Professions.FISHING_CONFIG.professionRewards.get()
                    .stream()
                    .map(ProfessionTierEntry::deserialize)
                    .toList();
            return new LiveProfessionConfig(Profession.FISHING, displayName, tiers);
        });
    }

    public static LiveProfessionConfig getCatchingConfig() {
        return PROFESSION_CACHE.computeIfAbsent(Profession.CATCHING, p -> {
            String displayName = Professions.CATCHING_CONFIG.professionName.get();
            List<ProfessionTierEntry> tiers = Professions.CATCHING_CONFIG.professionRewards.get()
                    .stream()
                    .map(ProfessionTierEntry::deserialize)
                    .toList();
            return new LiveProfessionConfig(Profession.CATCHING, displayName, tiers);
        });
    }

    public static LiveProfessionConfig getTrainingConfig() {
        return PROFESSION_CACHE.computeIfAbsent(Profession.TRAINING, p -> {
            String displayName = Professions.TRAINING_CONFIG.professionName.get();
            List<ProfessionTierEntry> tiers = Professions.TRAINING_CONFIG.professionRewards.get()
                    .stream()
                    .map(ProfessionTierEntry::deserialize)
                    .toList();
            return new LiveProfessionConfig(Profession.TRAINING, displayName, tiers);
        });
    }

    public static LiveProfessionConfig getBreedingConfig() {
        return PROFESSION_CACHE.computeIfAbsent(Profession.BREEDING, p -> {
            String displayName = Professions.BREEDING_CONFIG.professionName.get();
            List<ProfessionTierEntry> tiers = Professions.BREEDING_CONFIG.professionRewards.get()
                    .stream()
                    .map(ProfessionTierEntry::deserialize)
                    .toList();
            return new LiveProfessionConfig(Profession.BREEDING, displayName, tiers);
        });
    }
}
