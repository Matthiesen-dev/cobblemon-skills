package dev.matthiesen.cobblemon_skills.common.config.professions;

import com.electronwill.nightconfig.core.Config;
import dev.matthiesen.cobblemon_skills.common.config.def.PROF_DEFAULTS;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

public final class FishingConfig extends AbstractProfession {

    // Experience
    public ModConfigSpec.DoubleValue experience_statsXpMultiplier;
    public ModConfigSpec.DoubleValue experience_catchBaseXp;
    public ModConfigSpec.DoubleValue experience_catchLevelXpMultiplier;
    public ModConfigSpec.DoubleValue experience_catchUltraBeastBonusXp;
    public ModConfigSpec.DoubleValue experience_catchMythicalBonusXp;
    public ModConfigSpec.DoubleValue experience_catchLegendaryBonusXp;
    public ModConfigSpec.DoubleValue experience_catchShinyBonusXp;
    public ModConfigSpec.IntValue experience_craftAncientRodXp;
    public ModConfigSpec.IntValue experience_craftFishingRodXp;

    // Rewards
    public ModConfigSpec.BooleanValue rewards_randomLuckOfTheSea;
    public ModConfigSpec.BooleanValue rewards_randomUnbreaking;
    public ModConfigSpec.BooleanValue rewards_randomMending;
    public ModConfigSpec.BooleanValue rewards_randomLure;
    public ModConfigSpec.IntValue rewards_luckOfTheSeaTierOneMinLvl;
    public ModConfigSpec.IntValue rewards_luckOfTheSeaTierTwoMinLvl;
    public ModConfigSpec.IntValue rewards_luckOfTheSeaTierThreeMinLvl;
    public ModConfigSpec.IntValue rewards_unbreakingTierOneMinLvl;
    public ModConfigSpec.IntValue rewards_unbreakingTierTwoMinLvl;
    public ModConfigSpec.IntValue rewards_unbreakingTierThreeMinLvl;
    public ModConfigSpec.IntValue rewards_mendingMinLvl;
    public ModConfigSpec.IntValue rewards_lureTierOneMinLvl;
    public ModConfigSpec.IntValue rewards_lureTierTwoMinLvl;
    public ModConfigSpec.IntValue rewards_lureTierThreeMinLvl;
    public ModConfigSpec.DoubleValue rewards_enchantmentChance;

    @Override
    public List<Config> getDefaultProfessionRewards() {
        return PROF_DEFAULTS.getDefaultFishingTiers();
    }


    public FishingConfig(ModConfigSpec.Builder builder) {
        super(builder, "Fishing");

        builder.comment("Fishing Experience Settings").push("experience");
        experience_statsXpMultiplier = builder.comment("Multiplier for experience gained from fishing stats")
                .defineInRange("statsXpMultiplier", 150.0, 0.0, Double.MAX_VALUE);
        experience_catchBaseXp = builder.comment("Base experience gained from catching a Pokémon while fishing")
                .defineInRange("catchBaseXp", 200.0, 0.0, Double.MAX_VALUE);
        experience_catchLevelXpMultiplier = builder.comment("Multiplier for experience gained based on the level of the Pokémon caught while fishing")
                .defineInRange("catchLevelXpMultiplier", 10.0, 0.0, Double.MAX_VALUE);
        experience_catchUltraBeastBonusXp = builder.comment("Bonus experience gained from catching an Ultra Beast while fishing")
                .defineInRange("catchUltraBeastBonusXp", 100.0, 0.0, Double.MAX_VALUE);
        experience_catchMythicalBonusXp = builder.comment("Bonus experience gained from catching a Mythical Pokémon while fishing")
                .defineInRange("catchMythicalBonusXp", 200.0, 0.0, Double.MAX_VALUE);
        experience_catchLegendaryBonusXp = builder.comment("Bonus experience gained from catching a Legendary Pokémon while fishing")
                .defineInRange("catchLegendaryBonusXp", 300.0, 0.0, Double.MAX_VALUE);
        experience_catchShinyBonusXp = builder.comment("Bonus experience gained from catching a Shiny Pokémon while fishing")
                .defineInRange("catchShinyBonusXp", 400.0, 0.0, Double.MAX_VALUE);
        experience_craftAncientRodXp = builder.comment("Experience gained from crafting an Ancient Rod")
                .defineInRange("craftAncientRodXp", 900, 0, Integer.MAX_VALUE);
        experience_craftFishingRodXp = builder.comment("Experience gained from crafting a Fishing Rod")
                .defineInRange("craftFishingRodXp", 700, 0, Integer.MAX_VALUE);
        builder.pop(); // experience

        builder.comment("Fishing Rewards Settings").push("rewards");
        rewards_randomLuckOfTheSea = builder.comment("Whether the Luck of the Sea enchantment is randomly applied to fishing rewards")
                .define("randomLuckOfTheSea", true);
        rewards_randomUnbreaking = builder.comment("Whether the Unbreaking enchantment is randomly applied to fishing rewards")
                .define("randomUnbreaking", true);
        rewards_randomMending = builder.comment("Whether the Mending enchantment is randomly applied to fishing rewards")
                .define("randomMending", true);
        rewards_randomLure = builder.comment("Whether the Lure enchantment is randomly applied to fishing rewards")
                .define("randomLure", true);
        rewards_luckOfTheSeaTierOneMinLvl = builder.comment("The minimum level required for Luck of the Sea Tier 1 in fishing profession")
                .defineInRange("luckOfTheSeaTierOneMinLvl", 10, 0, Integer.MAX_VALUE);
        rewards_luckOfTheSeaTierTwoMinLvl = builder.comment("The minimum level required for Luck of the Sea Tier 2 in fishing profession")
                .defineInRange("luckOfTheSeaTierTwoMinLvl", 30, 0, Integer.MAX_VALUE);
        rewards_luckOfTheSeaTierThreeMinLvl = builder.comment("The minimum level required for Luck of the Sea Tier 3 in fishing profession")
                .defineInRange("luckOfTheSeaTierThreeMinLvl", 50, 0, Integer.MAX_VALUE);
        rewards_unbreakingTierOneMinLvl = builder.comment("The minimum level required for Unbreaking Tier 1 in fishing profession")
                .defineInRange("unbreakingTierOneMinLvl", 20, 0, Integer.MAX_VALUE);
        rewards_unbreakingTierTwoMinLvl = builder.comment("The minimum level required for Unbreaking Tier 2 in fishing profession")
                .defineInRange("unbreakingTierTwoMinLvl", 40, 0, Integer.MAX_VALUE);
        rewards_unbreakingTierThreeMinLvl = builder.comment("The minimum level required for Unbreaking Tier 3 in fishing profession")
                .defineInRange("unbreakingTierThreeMinLvl", 60, 0, Integer.MAX_VALUE);
        rewards_mendingMinLvl = builder.comment("The minimum level required for Mending enchantment in fishing profession")
                .defineInRange("mendingMinLvl", 70, 0, Integer.MAX_VALUE);
        rewards_lureTierOneMinLvl = builder.comment("The minimum level required for Lure Tier 1 in fishing profession")
                .defineInRange("lureTierOneMinLvl", 15, 0, Integer.MAX_VALUE);
        rewards_lureTierTwoMinLvl = builder.comment("The minimum level required for Lure Tier 2 in fishing profession")
                .defineInRange("lureTierTwoMinLvl", 35, 0, Integer.MAX_VALUE);
        rewards_lureTierThreeMinLvl = builder.comment("The minimum level required for Lure Tier 3 in fishing profession")
                .defineInRange("lureTierThreeMinLvl", 55, 0, Integer.MAX_VALUE);
        rewards_enchantmentChance = builder.comment("The chance for an enchantment to be applied to fishing rewards")
                .defineInRange("enchantmentChance", 0.25, 0.0, 1.0);
        builder.pop(); // rewards
    }
}
