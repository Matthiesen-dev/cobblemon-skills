package dev.matthiesen.cobblemon_skills.common.data;

import dev.matthiesen.cobblemon_skills.common.config.CobblemonSkillsConfig;
import net.minecraft.nbt.CompoundTag;

public final class ProfessionProgress {
    private static final String NBT_LEVEL = "level";
    private static final String NBT_EXPERIENCE = "experience";
    private static final int ZERO_INT = 0;
    private static final double ZERO_DOUBLE = 0.0;
    private static final double SCALE_FACTOR = 10.0;
    private static final double BASE_EXP_FACTOR = 1.0;

    private int level;
    private double experience;

    public static int getMaxLevel() {
        return CobblemonSkillsConfig.SERVER_CONFIG.professionMaxLevel.getAsInt();
    }

    public static int getExperiencePerLevel() {
        return CobblemonSkillsConfig.SERVER_CONFIG.professionBaseExpPerLevel.getAsInt();
    }

    private static int getLevelMultiplier() {
        return CobblemonSkillsConfig.SERVER_CONFIG.professionExpLevelMultiplier.getAsInt();
    }

    public int level() {
        return level;
    }

    public double experience() {
        return experience;
    }

    public void setLevel(int level) {
        this.level = Math.clamp(level, ZERO_INT, getMaxLevel());
        this.experience = ZERO_DOUBLE;
    }

    public int addExperience(double amount) {
        if (level >= getMaxLevel()) {
            level = getMaxLevel();
            experience = ZERO_DOUBLE;
            return ZERO_INT;
        }

        experience += amount;
        int levelsGained = ZERO_INT;

        while (level < getMaxLevel() && experience >= experienceRequiredForNextLevel(level)) {
            experience -= experienceRequiredForNextLevel(level);
            level++;
            levelsGained++;
        }

        if (level >= getMaxLevel()) {
            level = getMaxLevel();
            experience = ZERO_DOUBLE;
        }

        return levelsGained;
    }

    public static int experienceRequiredForNextLevel(int level) {
        if (level >= getMaxLevel()) {
            return ZERO_INT;
        }
        return getExperiencePerLevel() + level * getLevelMultiplier();
    }

    public double totalExperience() {
        return (SCALE_FACTOR * level * level) + (getExperiencePerLevel() * BASE_EXP_FACTOR * level) + experience;
    }

    public CompoundTag toCompoundTag() {
        CompoundTag tag = new CompoundTag();
        tag.putInt(NBT_LEVEL, level);
        tag.putDouble(NBT_EXPERIENCE, experience);
        return tag;
    }

    public static ProfessionProgress fromCompoundTag(CompoundTag tag) {
        ProfessionProgress progress = new ProfessionProgress();
        progress.level = Math.clamp(tag.getInt(NBT_LEVEL), ZERO_INT, getMaxLevel());
        progress.experience = progress.level >= getMaxLevel() ? ZERO_DOUBLE : Math.max(ZERO_DOUBLE, tag.getDouble(NBT_EXPERIENCE));
        return progress;
    }
}
