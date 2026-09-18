package dev.matthiesen.cobblemon_skills.common.config.def;

import com.electronwill.nightconfig.core.Config;
import net.minecraft.server.level.ServerPlayer;

import java.util.List;

public final class ProfessionTierEntry {
    public String displayName;
    public int levelRequirement;
    public List<RewardsEntry> rewards;

    public ProfessionTierEntry(String displayName, int levelRequirement, List<RewardsEntry> rewards) {
        this.displayName = displayName;
        this.levelRequirement = levelRequirement;
        this.rewards = rewards;
    }

    public ProfessionTierEntry(String displayName, int levelRequirement) {
        this(displayName, levelRequirement, List.of());
    }

    public static ProfessionTierEntry deserialize(Config config) {
        String displayName = config.get("displayName");
        int levelRequirement = config.getInt("levelRequirement");
        var rewards = config.get("rewards");
        if (rewards instanceof List<?> rewardsList) {
            var deserializedRewards = rewardsList.stream()
                    .filter(item -> item instanceof Config)
                    .map(item -> RewardsEntry.deserialize((Config) item))
                    .toList();
            return new ProfessionTierEntry(displayName, levelRequirement, deserializedRewards);
        }

        return new ProfessionTierEntry(displayName, levelRequirement);
    }

    public static boolean isValid(Object obj) {
        if (!(obj instanceof Config config)) {
            return false;
        }

        if (!config.contains("displayName") || !config.contains("levelRequirement")) {
            return false;
        }

        var rewards = config.get(List.of("rewards"));
        if (rewards == null) {
            return true; // No rewards are valid, as rewards are optional
        }

        if (!(rewards instanceof List<?>)) {
            return false;
        }

        if (rewards instanceof List<?> rewardsList) {
            for (Object rewardObj : rewardsList) {
                if (!(rewardObj instanceof Config rewardConfig) || !RewardsEntry.isValid(rewardConfig)) {
                    return false;
                }
            }
        }

        return true;
    }

    public void redeemRewards(ServerPlayer player) throws RuntimeException {
        this.rewards.forEach(reward -> {
            try {
                reward.redeem(player);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public String toId(String prefix) {
        return prefix + "_" + displayName.toLowerCase().replace(" ", "_") + "_" + levelRequirement;
    }

    public Config serialize() {
        Config config = Config.inMemory();
        config.set("displayName", this.displayName);
        config.set("levelRequirement", this.levelRequirement);
        config.set("rewards", this.rewards.stream().map(RewardsEntry::serialize).toList());
        return config;
    }
}
