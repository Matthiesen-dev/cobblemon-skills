package dev.matthiesen.cobblemon_skills.common.data;

import net.minecraft.nbt.CompoundTag;

public final class ProfessionProgress {
    public static final int MAX_LEVEL = 100;
    public static final int BASE_EXPERIENCE_PER_LEVEL = 1020;
    private static final String NBT_LEVEL = "level";
    private static final String NBT_EXPERIENCE = "experience";
    private int level;
    private double experience;

    private static int getLevelMultiplier() {
        return 20;
    }

    public int level() {
        return level;
    }

    public double experience() {
        return experience;
    }

    public void setLevel(int level) {
        this.level = Math.clamp(level, 0, MAX_LEVEL);
        this.experience = 0.0;
    }

    public int addExperience(double amount) {
        if (level >= MAX_LEVEL) {
            level = MAX_LEVEL;
            experience = 0.0;
            return 0;
        }

        experience += amount;
        int levelsGained = 0;

        while (level < MAX_LEVEL && experience >= experienceRequiredForNextLevel(level)) {
            experience -= experienceRequiredForNextLevel(level);
            level++;
            levelsGained++;
        }

        if (level >= MAX_LEVEL) {
            level = MAX_LEVEL;
            experience = 0.0;
        }

        return levelsGained;
    }

    public static int experienceRequiredForNextLevel(int level) {
        if (level >= MAX_LEVEL) {
            return 0;
        }
        return BASE_EXPERIENCE_PER_LEVEL + level * getLevelMultiplier();
    }

    public double totalExperience() {
        return (10.0 * level * level) + (BASE_EXPERIENCE_PER_LEVEL * 1.0 * level) + experience;
    }

    public CompoundTag toCompoundTag() {
        CompoundTag tag = new CompoundTag();
        tag.putInt(NBT_LEVEL, level);
        tag.putDouble(NBT_EXPERIENCE, experience);
        return tag;
    }

    public static ProfessionProgress fromCompoundTag(CompoundTag tag) {
        ProfessionProgress progress = new ProfessionProgress();
        progress.level = Math.clamp(tag.getInt(NBT_LEVEL), 0, MAX_LEVEL);
        progress.experience = progress.level >= MAX_LEVEL ? 0.0 : Math.max(0.0, tag.getDouble(NBT_EXPERIENCE));
        return progress;
    }
}
