package dev.matthiesen.cobblemon_skills.common.data;

import dev.matthiesen.cobblemon_skills.common.CobblemonSkillsCommon;
import dev.matthiesen.cobblemon_skills.common.config.CobblemonSkillsConfig;
import dev.matthiesen.cobblemon_skills.common.config.professions.*;
import net.minecraft.server.level.ServerPlayer;

import static dev.matthiesen.cobblemon_skills.common.config.CobblemonSkillsConfig.Professions;

public enum Profession {
    ARCHEOLOGY(ArcheologyConfig.NAME, Professions.ARCHEOLOGY_CONFIG),
    BOTANY(BotanyConfig.NAME, Professions.BOTANY_CONFIG),
    COOKING(CookingConfig.NAME, Professions.COOKING_CONFIG),
    FISHING(FishingConfig.NAME, Professions.FISHING_CONFIG),
    CATCHING(CatchingConfig.NAME, Professions.CATCHING_CONFIG),
    TRAINING(TrainingConfig.NAME, Professions.TRAINING_CONFIG),
    BREEDING(BreedingConfig.NAME, Professions.BREEDING_CONFIG);

    private final AbstractProfession config;
    private final String NbtTag;

    Profession(String NbtTag, AbstractProfession config) {
        this.NbtTag = NbtTag;
        this.config = config;
    }

    public LiveProfessionConfig getConfig() {
        return CobblemonSkillsConfig.getProfessionConfig(this, this.config);
    }

    public Integer awardExperience(ServerPlayer player, double experience) {
        return CobblemonSkillsCommon.Events.AWARD_EXPERIENCE.emit(new AwardExperience(player, this, experience));
    }

    public String getNbtTag() {
        return NbtTag;
    }

    public static Profession fromNbtTag(String nbtTag) {
        for (Profession profession : values()) {
            if (profession.getNbtTag().equals(nbtTag)) {
                return profession;
            }
        }
        throw new IllegalArgumentException("No profession found with NBT tag: " + nbtTag);
    }
}
