package dev.matthiesen.cobblemon_skills.common.data;

import dev.matthiesen.cobblemon_skills.common.config.CobblemonSkillsConfig;
import net.minecraft.nbt.CompoundTag;

public final class ProfessionProgress {
    private static final String NBT_LEVEL = "level";
    private static final String NBT_EXPERIENCE = "experience";
    private int level;
    private double experience;

    public static int getMaxLevel() {
        return CobblemonSkillsConfig.PROFESSIONS_CONFIG.professionMaxLevel.getAsInt();
    }

    public static int getExperiencePerLevel() {
        return CobblemonSkillsConfig.PROFESSIONS_CONFIG.professionBaseExpPerLevel.getAsInt();
    }

    private static int getLevelMultiplier() {
        return CobblemonSkillsConfig.PROFESSIONS_CONFIG.professionExpLevelMultiplier.getAsInt();
    }

    public int level() {
        return level;
    }

    public double experience() {
        return experience;
    }

    public void setLevel(int level) {
        this.level = Math.clamp(level, 0, getMaxLevel());
        this.experience = 0.0;
    }

    public int addExperience(double amount) {
        if (level >= getMaxLevel()) {
            level = getMaxLevel();
            experience = 0.0;
            return 0;
        }

        experience += amount;
        int levelsGained = 0;

        while (level < getMaxLevel() && experience >= experienceRequiredForNextLevel(level)) {
            experience -= experienceRequiredForNextLevel(level);
            level++;
            levelsGained++;
        }

        if (level >= getMaxLevel()) {
            level = getMaxLevel();
            experience = 0.0;
        }

        return levelsGained;
    }

    public static int experienceRequiredForNextLevel(int level) {
        if (level >= getMaxLevel()) {
            return 0;
        }
        return getExperiencePerLevel() + level * getLevelMultiplier();
    }

    public double totalExperience() {
        return (10.0 * level * level) + (getExperiencePerLevel() * 1.0 * level) + experience;
    }

    public CompoundTag toCompoundTag() {
        CompoundTag tag = new CompoundTag();
        tag.putInt(NBT_LEVEL, level);
        tag.putDouble(NBT_EXPERIENCE, experience);
        return tag;
    }

    public static ProfessionProgress fromCompoundTag(CompoundTag tag) {
        ProfessionProgress progress = new ProfessionProgress();
        progress.level = Math.clamp(tag.getInt(NBT_LEVEL), 0, getMaxLevel());
        progress.experience = progress.level >= getMaxLevel() ? 0.0 : Math.max(0.0, tag.getDouble(NBT_EXPERIENCE));
        return progress;
    }
}
