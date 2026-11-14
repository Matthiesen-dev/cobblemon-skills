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

    private static LiveProfessionConfig getOrComputeProfession(Profession profession, AbstractProfession professionDef) {
        return PROFESSION_CACHE.computeIfAbsent(profession, p -> {
            String displayName = professionDef.professionName.get();
            List<ProfessionTierEntry> tiers = professionDef.professionRewards.get()
                    .stream()
                    .map(ProfessionTierEntry::deserialize)
                    .toList();
            return new LiveProfessionConfig(profession, displayName, tiers);
        });
    }

    public static LiveProfessionConfig getArcheologyConfig() {
        return getOrComputeProfession(Profession.ARCHEOLOGY, Professions.ARCHEOLOGY_CONFIG);
    }

    public static LiveProfessionConfig getBotanyConfig() {
        return getOrComputeProfession(Profession.BOTANY, Professions.BOTANY_CONFIG);
    }

    public static LiveProfessionConfig getCookingConfig() {
        return getOrComputeProfession(Profession.COOKING, Professions.COOKING_CONFIG);
    }

    public static LiveProfessionConfig getFishingConfig() {
        return getOrComputeProfession(Profession.FISHING, Professions.FISHING_CONFIG);
    }

    public static LiveProfessionConfig getCatchingConfig() {
        return getOrComputeProfession(Profession.CATCHING, Professions.CATCHING_CONFIG);
    }

    public static LiveProfessionConfig getTrainingConfig() {
        return getOrComputeProfession(Profession.TRAINING, Professions.TRAINING_CONFIG);
    }

    public static LiveProfessionConfig getBreedingConfig() {
        return getOrComputeProfession(Profession.BREEDING, Professions.BREEDING_CONFIG);
    }
}
