package dev.matthiesen.cobblemon_skills.common.config.professions;

import com.electronwill.nightconfig.core.Config;
import dev.matthiesen.cobblemon_skills.common.config.def.PROF_DEFAULTS;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

public final class BotanyConfig extends AbstractProfession {

    // Experience
    public ModConfigSpec.DoubleValue experience_apricornHarvestXp;
    public ModConfigSpec.DoubleValue experience_berryHarvestXp;
    public ModConfigSpec.IntValue experience_woodcuttingBaseXp;
    public ModConfigSpec.IntValue experience_woodcuttingTier1Xp;
    public ModConfigSpec.IntValue experience_farmingBaseXp;
    public ModConfigSpec.IntValue experience_farmingTier1Xp;

    // Rewards
    public ModConfigSpec.BooleanValue rewards_randomExtraDrops;
    public ModConfigSpec.IntValue rewards_randomExtraDropsMinLvl;
    public ModConfigSpec.DoubleValue rewards_randomExtraDropsChance;

    @Override
    public List<Config> getDefaultProfessionRewards() {
        return PROF_DEFAULTS.getDefaultBotanyTiers();
    }

    public BotanyConfig(ModConfigSpec.Builder builder) {
        super(builder, "Botany");
        
        builder.comment("Botany Experience Settings").push("experience");
        experience_apricornHarvestXp = builder.comment("Experience gained from harvesting an Apricorn")
                .defineInRange("apricornHarvestXp", 150.0, 0.0, Double.MAX_VALUE);
        experience_berryHarvestXp = builder.comment("Experience gained from harvesting a Berry")
                .defineInRange("berryHarvestXp", 200.0, 0.0, Double.MAX_VALUE);
        experience_woodcuttingBaseXp = builder.comment("Base experience gained from woodcutting")
                .defineInRange("woodcuttingBaseXp", 100, 0, Integer.MAX_VALUE);
        experience_woodcuttingTier1Xp = builder.comment("Experience gained from woodcutting a Tier 1 tree")
                .defineInRange("woodcuttingTier1Xp", 150, 0, Integer.MAX_VALUE);
        experience_farmingBaseXp = builder.comment("Base experience gained from farming")
                .defineInRange("farmingBaseXp", 150, 0, Integer.MAX_VALUE);
        experience_farmingTier1Xp = builder.comment("Experience gained from farming a Tier 1 crop")
                .defineInRange("farmingTier1Xp", 200, 0, Integer.MAX_VALUE);
        builder.pop(); // experience

        builder.comment("Botany Rewards Settings").push("rewards");
        rewards_randomExtraDrops = builder.comment("Whether extra drops are randomly applied to block breaking rewards in botany profession")
                .define("randomExtraDrops", true);
        rewards_randomExtraDropsMinLvl = builder.comment("The minimum level required for extra drops in botany profession")
                .defineInRange("randomExtraDropsMinLvl", 10, 0, Integer.MAX_VALUE);
        rewards_randomExtraDropsChance = builder.comment("The chance for extra drops to be applied to block breaking rewards in botany profession")
                .defineInRange("randomExtraDropsChance", 0.25, 0.0, 1.0);
        builder.pop(); // rewards
    }
}
