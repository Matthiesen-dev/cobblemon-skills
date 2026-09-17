package dev.matthiesen.cobblemon_skills.common.data;

import dev.matthiesen.cobblemon_skills.common.config.CobblemonSkillsConfig;

import java.util.function.Supplier;

public enum Profession {
    ARCHEOLOGY("Archeology", CobblemonSkillsConfig::getArcheologyConfig),
    BOTANY("Botany", CobblemonSkillsConfig::getBotanyConfig),
    COOKING("Cooking", CobblemonSkillsConfig::getCookingConfig),
    FISHING("Fishing", CobblemonSkillsConfig::getFishingConfig),
    CATCHING("Catching", CobblemonSkillsConfig::getCatchingConfig),
    TRAINING("Training", CobblemonSkillsConfig::getTrainingConfig),
    BREEDING("Breeding", CobblemonSkillsConfig::getBreedingConfig);

    private final Supplier<LiveProfessionConfig> config;
    private final String NBTTag;

    Profession(String NBTTag, Supplier<LiveProfessionConfig> configSupplier) {
        this.NBTTag = NBTTag;
        this.config = configSupplier;
    }

    public LiveProfessionConfig getConfig() {
        return config.get();
    }

    public String getNBTTag() {
        return NBTTag;
    }
}
