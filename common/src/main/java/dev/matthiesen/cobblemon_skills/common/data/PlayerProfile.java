package dev.matthiesen.cobblemon_skills.common.data;

import net.minecraft.nbt.CompoundTag;

import java.util.EnumMap;

public final class PlayerProfile {
    private final EnumMap<Profession, ProfessionProgress> professionProgressMap = new EnumMap<>(Profession.class);

    public PlayerProfile() {
        for (Profession profession : Profession.values()) {
            professionProgressMap.put(profession, new ProfessionProgress());
        }
    }

    public ProfessionProgress getProgress(Profession profession) {
        return professionProgressMap.get(profession);
    }

    public CompoundTag toCompoundTag() {
        CompoundTag tag = new CompoundTag();

        for (Profession profession : Profession.values()) {
            tag.put(profession.getNbtTag(), professionProgressMap.get(profession).toCompoundTag());
        }

        return tag;
    }

    public static PlayerProfile fromCompoundTag(CompoundTag tag) {
        PlayerProfile profile = new PlayerProfile();

        for (Profession profession : Profession.values()) {
            if (tag.contains(profession.getNbtTag())) {
                profile.professionProgressMap.put(
                        profession,
                        ProfessionProgress.fromCompoundTag(tag.getCompound(profession.getNbtTag()))
                );
            }
        }

        return profile;
    }
}
