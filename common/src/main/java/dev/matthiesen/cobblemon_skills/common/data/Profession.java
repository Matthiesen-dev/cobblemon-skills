package dev.matthiesen.cobblemon_skills.common.data;

import dev.matthiesen.cobblemon_skills.common.CobblemonSkillsCommon;
import dev.matthiesen.cobblemon_skills.common.config.CobblemonSkillsConfig;
import dev.matthiesen.cobblemon_skills.common.config.professions.AbstractProfession;
import net.minecraft.server.level.ServerPlayer;

import static dev.matthiesen.cobblemon_skills.common.config.CobblemonSkillsConfig.Professions;

public enum Profession {
    ARCHEOLOGY("Archeology", Professions.ARCHEOLOGY_CONFIG),
    BOTANY("Botany", Professions.BOTANY_CONFIG),
    COOKING("Cooking", Professions.COOKING_CONFIG),
    FISHING("Fishing", Professions.FISHING_CONFIG),
    CATCHING("Catching", Professions.CATCHING_CONFIG),
    TRAINING("Training", Professions.TRAINING_CONFIG),
    BREEDING("Breeding", Professions.BREEDING_CONFIG);

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
