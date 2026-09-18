package dev.matthiesen.cobblemon_skills.common.data;

import dev.matthiesen.cobblemon_skills.common.config.def.ProfessionTierEntry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public final class PlayerProfile {
    private static final String NBT_REDEEMED_REWARDS = "redeemedRewards";
    private final EnumMap<Profession, ProfessionProgress> professionProgressMap = new EnumMap<>(Profession.class);
    private final Map<String, Boolean> redeemableRewards = new HashMap<>();

    public PlayerProfile() {
        for (Profession profession : Profession.values()) {
            professionProgressMap.put(profession, new ProfessionProgress());
            for (ProfessionTierEntry tier : profession.getConfig().tiers()) {
                redeemableRewards.put(tier.toId(profession.getNbtTag()), false);
            }
        }
    }

    public ProfessionProgress getProgress(Profession profession) {
        return professionProgressMap.get(profession);
    }

    public boolean isRewardRedeemed(String rewardId) {
        return redeemableRewards.getOrDefault(rewardId, false);
    }

    public boolean isRewardRedeemed(Profession profession, ProfessionTierEntry tier) {
        return isRewardRedeemed(tier.toId(profession.getNbtTag()));
    }

    public void setRewardRedeemed(String rewardId, boolean redeemed) {
        redeemableRewards.put(rewardId, redeemed);
    }

    public boolean redeemTierReward(ServerPlayer player, Profession profession, ProfessionTierEntry tier) {
        String rewardId = tier.toId(profession.getNbtTag());
        if (isRewardRedeemed(rewardId)) {
            return false;
        }
        tier.redeemRewards(player);
        setRewardRedeemed(rewardId, true);
        return true;
    }

    public CompoundTag toCompoundTag() {
        CompoundTag tag = new CompoundTag();

        for (Profession profession : Profession.values()) {
            tag.put(profession.getNbtTag(), professionProgressMap.get(profession).toCompoundTag());
        }

        CompoundTag redeemableRewardsTag = new CompoundTag();
        for (Map.Entry<String, Boolean> entry : redeemableRewards.entrySet()) {
            redeemableRewardsTag.putBoolean(entry.getKey(), entry.getValue());
        }
        tag.put(NBT_REDEEMED_REWARDS, redeemableRewardsTag);

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

        if (tag.contains(NBT_REDEEMED_REWARDS)) {
            CompoundTag redeemedRewardsTag = tag.getCompound(NBT_REDEEMED_REWARDS);
            for (String key : redeemedRewardsTag.getAllKeys()) {
                profile.redeemableRewards.put(key, redeemedRewardsTag.getBoolean(key));
            }
        }

        return profile;
    }
}
