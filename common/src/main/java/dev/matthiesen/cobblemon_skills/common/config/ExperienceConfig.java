package dev.matthiesen.cobblemon_skills.common.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class ExperienceConfig {

    // Archeology
    public ModConfigSpec.DoubleValue archeology_fossilRevivalBaseXp;
    public ModConfigSpec.DoubleValue archeology_fossilRevivalShinyBonusXp;
    public ModConfigSpec.IntValue archeology_oreBlockXp;
    public ModConfigSpec.IntValue archeology_deepslateOreBlockXp;
    public ModConfigSpec.IntValue archeology_miscOreBlockXp;
    public ModConfigSpec.IntValue archeology_tumblestoneClusterSmallXp;
    public ModConfigSpec.IntValue archeology_tumblestoneClusterMediumXp;
    public ModConfigSpec.IntValue archeology_tumblestoneClusterLargeXp;
    public ModConfigSpec.IntValue archeology_tumblestoneClusterFullXp;
    public ModConfigSpec.IntValue archeology_typeClusterXp;
    public ModConfigSpec.IntValue archeology_relicCoinSackXp;
    public ModConfigSpec.IntValue archeology_relicCoinPouchXp;
    public ModConfigSpec.IntValue archeology_commonItemXp;
    public ModConfigSpec.IntValue archeology_uncommonItemXp;
    public ModConfigSpec.IntValue archeology_rareItemXp;

    // Botany
    public ModConfigSpec.DoubleValue botany_apricornHarvestXp;
    public ModConfigSpec.DoubleValue botany_berryHarvestXp;
    public ModConfigSpec.IntValue botany_woodcuttingBaseXp;
    public ModConfigSpec.IntValue botany_woodcuttingTier1Xp;
    public ModConfigSpec.IntValue botany_farmingBaseXp;
    public ModConfigSpec.IntValue botany_farmingTier1Xp;

    // Cooking
    public ModConfigSpec.IntValue cooking_baseXp;
    public ModConfigSpec.IntValue cooking_tier1Xp;
    public ModConfigSpec.IntValue cooking_tier2Xp;
    public ModConfigSpec.IntValue cooking_tier3Xp;
    public ModConfigSpec.IntValue cooking_tier4Xp;

    // Fishing
    public ModConfigSpec.DoubleValue fishing_statsXpMultiplier;
    public ModConfigSpec.DoubleValue fishing_catchBaseXp;
    public ModConfigSpec.DoubleValue fishing_catchLevelXpMultiplier;
    public ModConfigSpec.DoubleValue fishing_catchUltraBeastBonusXp;
    public ModConfigSpec.DoubleValue fishing_catchMythicalBonusXp;
    public ModConfigSpec.DoubleValue fishing_catchLegendaryBonusXp;
    public ModConfigSpec.DoubleValue fishing_catchShinyBonusXp;

    // Catching
    public ModConfigSpec.DoubleValue catching_catchBaseXp;
    public ModConfigSpec.DoubleValue catching_catchLevelXpMultiplier;
    public ModConfigSpec.DoubleValue catching_catchCriticalBonusXp;

    // Training
    public ModConfigSpec.DoubleValue training_baseXp;
    public ModConfigSpec.DoubleValue training_leftoversXp;
    public ModConfigSpec.DoubleValue training_levelUpXpMultiplier;

    // Breeding
    public ModConfigSpec.DoubleValue breeding_eggCollectedBaseXp;
    public ModConfigSpec.DoubleValue breeding_eggHatchedBaseXp;
    public ModConfigSpec.DoubleValue breeding_eggHatchedCycleBonusXp;

    public ExperienceConfig(ModConfigSpec.Builder builder) {
        builder.comment("Server Configuration for Cobblemon Skills Mod").push("server");

        builder.comment("Archeology Profession Configuration").push("archeology");
        archeology_fossilRevivalBaseXp = builder.comment("Base experience gained from reviving a fossil")
                .defineInRange("fossilRevivalBaseXp", 500.0, 0.0, Double.MAX_VALUE);
        archeology_fossilRevivalShinyBonusXp = builder.comment("Bonus experience gained from reviving a shiny fossil Pokémon")
                .defineInRange("fossilRevivalShinyBonusXp", 400.0, 0.0, Double.MAX_VALUE);
        archeology_oreBlockXp = builder.comment("Experience gained from mining an ore block")
                .defineInRange("oreBlockXp", 400, 0, Integer.MAX_VALUE);
        archeology_deepslateOreBlockXp = builder.comment("Experience gained from mining a deepslate ore block")
                .defineInRange("deepslateOreBlockXp", 500, 0, Integer.MAX_VALUE);
        archeology_miscOreBlockXp = builder.comment("Experience gained from mining a miscellaneous ore block")
                .defineInRange("miscOreBlockXp", 300, 0, Integer.MAX_VALUE);
        archeology_tumblestoneClusterSmallXp = builder.comment("Experience gained from mining a small tumblestone cluster")
                .defineInRange("tumblestoneClusterSmallXp", 100, 0, Integer.MAX_VALUE);
        archeology_tumblestoneClusterMediumXp = builder.comment("Experience gained from mining a medium tumblestone cluster")
                .defineInRange("tumblestoneClusterMediumXp", 200, 0, Integer.MAX_VALUE);
        archeology_tumblestoneClusterLargeXp = builder.comment("Experience gained from mining a large tumblestone cluster")
                .defineInRange("tumblestoneClusterLargeXp", 300, 0, Integer.MAX_VALUE);
        archeology_tumblestoneClusterFullXp = builder.comment("Experience gained from mining a full tumblestone cluster")
                .defineInRange("tumblestoneClusterFullXp", 400, 0, Integer.MAX_VALUE);
        archeology_typeClusterXp = builder.comment("Experience gained from mining a type cluster")
                .defineInRange("typeClusterXp", 500, 0, Integer.MAX_VALUE);
        archeology_relicCoinSackXp = builder.comment("Experience gained from mining a relic coin sack")
                .defineInRange("relicCoinSackXp", 600, 0, Integer.MAX_VALUE);
        archeology_relicCoinPouchXp = builder.comment("Experience gained from mining a relic coin pouch")
                .defineInRange("relicCoinPouchXp", 700, 0, Integer.MAX_VALUE);
        archeology_commonItemXp = builder.comment("Experience gained from mining a common archeology item")
                .defineInRange("commonItemXp", 200, 0, Integer.MAX_VALUE);
        archeology_uncommonItemXp = builder.comment("Experience gained from mining an uncommon archeology item")
                .defineInRange("uncommonItemXp", 400, 0, Integer.MAX_VALUE);
        archeology_rareItemXp = builder.comment("Experience gained from mining a rare archeology item")
                .defineInRange("rareItemXp", 600, 0, Integer.MAX_VALUE);
        builder.pop(); // Pop "archeology" category

        builder.comment("Botany Profession Configuration").push("botany");
        botany_apricornHarvestXp = builder.comment("Experience gained from harvesting an Apricorn")
                .defineInRange("apricornHarvestXp", 150.0, 0.0, Double.MAX_VALUE);
        botany_berryHarvestXp = builder.comment("Experience gained from harvesting a Berry")
                .defineInRange("berryHarvestXp", 200.0, 0.0, Double.MAX_VALUE);
        botany_woodcuttingBaseXp = builder.comment("Base experience gained from woodcutting")
                .defineInRange("woodcuttingBaseXp", 100, 0, Integer.MAX_VALUE);
        botany_woodcuttingTier1Xp = builder.comment("Experience gained from woodcutting a Tier 1 tree")
                .defineInRange("woodcuttingTier1Xp", 150, 0, Integer.MAX_VALUE);
        botany_farmingBaseXp = builder.comment("Base experience gained from farming")
                .defineInRange("farmingBaseXp", 150, 0, Integer.MAX_VALUE);
        botany_farmingTier1Xp = builder.comment("Experience gained from farming a Tier 1 crop")
                .defineInRange("farmingTier1Xp", 200, 0, Integer.MAX_VALUE);
        builder.pop(); // Pop "botany" category

        builder.comment("Cooking Profession Configuration").push("cooking");
        cooking_baseXp = builder.comment("Base experience gained from cooking")
                    .defineInRange("baseXp", 100, 0, Integer.MAX_VALUE);
        cooking_tier1Xp = builder.comment("Experience gained from cooking a Tier 1 dish")
                .defineInRange("tier1Xp", 150, 0, Integer.MAX_VALUE);
        cooking_tier2Xp = builder.comment("Experience gained from cooking a Tier 2 dish")
                .defineInRange("tier2Xp", 200, 0, Integer.MAX_VALUE);
        cooking_tier3Xp = builder.comment("Experience gained from cooking a Tier 3 dish")
                .defineInRange("tier3Xp", 250, 0, Integer.MAX_VALUE);
        cooking_tier4Xp = builder.comment("Experience gained from cooking a Tier 4 dish")
                .defineInRange("tier4Xp", 300, 0, Integer.MAX_VALUE);
        builder.pop(); // Pop "cooking" category

        builder.comment("Fishing Profession Configuration").push("fishing");
        fishing_statsXpMultiplier = builder.comment("Multiplier for experience gained from fishing stats")
                .defineInRange("statsXpMultiplier", 150.0, 0.0, Double.MAX_VALUE);
        fishing_catchBaseXp = builder.comment("Base experience gained from catching a Pokémon while fishing")
                .defineInRange("catchBaseXp", 200.0, 0.0, Double.MAX_VALUE);
        fishing_catchLevelXpMultiplier = builder.comment("Multiplier for experience gained based on the level of the Pokémon caught while fishing")
                .defineInRange("catchLevelXpMultiplier", 10.0, 0.0, Double.MAX_VALUE);
        fishing_catchUltraBeastBonusXp = builder.comment("Bonus experience gained from catching an Ultra Beast while fishing")
                .defineInRange("catchUltraBeastBonusXp", 100.0, 0.0, Double.MAX_VALUE);
        fishing_catchMythicalBonusXp = builder.comment("Bonus experience gained from catching a Mythical Pokémon while fishing")
                .defineInRange("catchMythicalBonusXp", 200.0, 0.0, Double.MAX_VALUE);
        fishing_catchLegendaryBonusXp = builder.comment("Bonus experience gained from catching a Legendary Pokémon while fishing")
                .defineInRange("catchLegendaryBonusXp", 300.0, 0.0, Double.MAX_VALUE);
        fishing_catchShinyBonusXp = builder.comment("Bonus experience gained from catching a Shiny Pokémon while fishing")
                .defineInRange("catchShinyBonusXp", 400.0, 0.0, Double.MAX_VALUE);
        builder.pop(); // Pop "fishing" category

        builder.comment("Catching Profession Configuration").push("catching");
        catching_catchBaseXp = builder.comment("Base experience gained from catching a Pokémon")
                .defineInRange("catchBaseXp", 200.0, 0.0, Double.MAX_VALUE);
        catching_catchLevelXpMultiplier = builder.comment("Multiplier for experience gained based on the level of the Pokémon caught")
                .defineInRange("catchLevelXpMultiplier", 8.0, 0.0, Double.MAX_VALUE);
        catching_catchCriticalBonusXp = builder.comment("Bonus experience gained from catching a Pokémon with a critical capture")
                .defineInRange("catchCriticalBonusXp", 150.0, 0.0, Double.MAX_VALUE);
        builder.pop(); // Pop "catching" category

        builder.comment("Training Profession Configuration").push("training");
        training_baseXp = builder.comment("Base experience gained from training a Pokémon")
                .defineInRange("baseXp", 300.0, 0.0, Double.MAX_VALUE);
        training_leftoversXp = builder.comment("Experience gained from using Leftovers on a Pokémon")
                .defineInRange("leftoversXp", 100.0, 0.0, Double.MAX_VALUE);
        training_levelUpXpMultiplier = builder.comment("Multiplier for experience gained when a Pokémon levels up")
                .defineInRange("levelUpXpMultiplier", 100.0, 0.0, Double.MAX_VALUE);
        builder.pop(); // Pop "training" category

        builder.comment("Breeding Profession Configuration").push("breeding");
        breeding_eggCollectedBaseXp = builder.comment("Base experience gained from collecting an egg")
                .defineInRange("eggCollectedBaseXp", 100.0, 0.0, Double.MAX_VALUE);
        breeding_eggHatchedBaseXp = builder.comment("Base experience gained from hatching an egg")
                .defineInRange("eggHatchedBaseXp", 200.0, 0.0, Double.MAX_VALUE);
        breeding_eggHatchedCycleBonusXp = builder.comment("Bonus experience gained for each cycle of hatching an egg")
                .defineInRange("eggHatchedCycleBonusXp", 4.0, 0.0, Double.MAX_VALUE);
        builder.pop(); // Pop "breeding" category

        builder.pop(); // Pop "server" category
    }
}
