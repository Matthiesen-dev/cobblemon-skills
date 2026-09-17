package dev.matthiesen.cobblemon_skills.common.runtime;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.pokemon.Pokemon;
import dev.matthiesen.cobblemon_skills.common.config.CobblemonSkillsConfig;
import dev.matthiesen.cobblemon_skills.common.config.ExperienceConfig;
import dev.matthiesen.cobblemon_skills.common.runtime.data.ArcheologyStatics;
import dev.matthiesen.cobblemon_skills.common.runtime.data.BotanyStatics;
import dev.matthiesen.cobblemon_skills.common.runtime.data.CookingStatics;
import dev.matthiesen.cobblemon_skills.common.runtime.data.FishingStatics;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public final class ExperienceManager {
    private ExperienceManager() {}

    private static ExperienceConfig getConfig() {
        return CobblemonSkillsConfig.EXPERIENCE_CONFIG;
    }

    public static boolean isCobblemonArcheologyItem(ItemStack item) {
        return ArcheologyStatics.ITEMS.containsKey(item.getItem());
    }

    public static int getCobblemonArcheologyItemExperience(ItemStack item) {
        return ArcheologyStatics.ITEMS.getOrDefault(item.getItem(), () -> 0).get();
    }

    public static boolean isCobblemonFishingItem(ItemStack item) {
        return FishingStatics.ITEMS.containsKey(item.getItem());
    }

    public static int getCobblemonFishingItemExperience(ItemStack item) {
        return FishingStatics.ITEMS.getOrDefault(item.getItem(), () -> 0).get();
    }

    public static boolean isCobblemonCookingItem(Item item) {
        return CookingStatics.ITEMS.containsKey(item);
    }

    public static int getCobblemonCookingItemExperience(Item item) {
        return CookingStatics.ITEMS.getOrDefault(item, () -> 0).get();
    }

    public static boolean isArcheologyBlock(Block block) {
        return ArcheologyStatics.BLOCKS.containsKey(block);
    }

    public static boolean isBotanyBlock(Block block) {
        return BotanyStatics.BLOCKS.containsKey(block);
    }

    public static int getArcheologyBlockExperience(Block block) {
        return ArcheologyStatics.BLOCKS.getOrDefault(block, () -> 0).get();
    }

    public static int getBotanyBlockExperience(Block block) {
        return BotanyStatics.BLOCKS.getOrDefault(block, () -> 0).get();
    }

    public static double getLeftoversExperience() {
        return getConfig().training_leftoversXp.getAsDouble();
    }

    public static double getApricornHarvestExperience() {
        return getConfig().botany_apricornHarvestXp.getAsDouble();
    }

    public static double getBerryHarvestExperience() {
        return getConfig().botany_berryHarvestXp.getAsDouble();
    }

    public static double getFishingExperience(int fishingCaught) {
        return fishingCaught * getConfig().fishing_statsXpMultiplier.getAsDouble();
    }

    public static double getFossilRevivalExperience(Pokemon pokemon) {
        boolean isShiny = pokemon.getShiny();
        double baseExperience = getConfig().archeology_fossilRevivalBaseXp.getAsDouble();
        if (isShiny) {
            baseExperience += getConfig().archeology_fossilRevivalShinyBonusXp.getAsDouble();
        }
        return baseExperience;
    }

    public static double getFishingExperienceFromPokemon(PokemonEntity pokemonEntity) {
        Pokemon pokemon = pokemonEntity.getPokemon();
        int level = pokemon.getLevel();
        boolean isUltraBeast = pokemon.isUltraBeast();
        boolean isMythical = pokemon.isMythical();
        boolean isLegendary = pokemon.isLegendary();
        boolean isShiny = pokemon.getShiny();

        double baseExperience = getConfig().fishing_catchBaseXp.getAsDouble() + Math.max(1, level) * getConfig().fishing_catchLevelXpMultiplier.getAsDouble();
        if (isUltraBeast) {
            baseExperience += getConfig().fishing_catchUltraBeastBonusXp.getAsDouble();
        }
        if (isMythical) {
            baseExperience += getConfig().fishing_catchMythicalBonusXp.getAsDouble();
        }
        if (isLegendary) {
            baseExperience += getConfig().fishing_catchLegendaryBonusXp.getAsDouble();
        }
        if (isShiny) {
            baseExperience += getConfig().fishing_catchShinyBonusXp.getAsDouble();
        }
        return baseExperience;
    }

    public static double captureCatchRateBonusPercent(int level) {
        return Math.min(10.0, level * 0.10);
    }

    public static float getCaptureRateBonusMultiplier(int level) {
        return (float) (1.0 + (captureCatchRateBonusPercent(level) / 100.0));
    }

    public static double getCaptureSkillExperience(int pokemonLevel, boolean criticalCapture) {
        double base = getConfig().catching_catchBaseXp.getAsDouble() + Math.max(1, pokemonLevel) * getConfig().catching_catchLevelXpMultiplier.getAsDouble();
        return criticalCapture ? base + getConfig().catching_catchCriticalBonusXp.getAsDouble() : base;
    }

    public static double getBattleExperience() {
        return getConfig().training_baseXp.getAsDouble();
    }

    public static double getBreedingEggCollected() {
        return getConfig().breeding_eggCollectedBaseXp.getAsDouble();
    }

    public static int getBreedingFriendshipBonus(int breedingLevel) {
        return Math.min(20, breedingLevel / 5); // Base friendship bonus for hatching an egg, increases with breeding level
    }

    public static double getBreedingEggHatched(int eggCycles) {
        return getConfig().breeding_eggHatchedBaseXp.getAsDouble() + Math.max(0, eggCycles) * getConfig().breeding_eggHatchedCycleBonusXp.getAsDouble();
    }

    public static double getTrainingSkillExperienceFromLevelUp(int oldLevel, int newLevel) {
        return Math.max(0, newLevel - oldLevel) * getConfig().training_levelUpXpMultiplier.getAsDouble();
    }

    public static double trainingSkillExperienceFromBattle(double battleExperience) {
        if (battleExperience <= 0) {
            return 0.0;
        }
        return Math.max(20.0, battleExperience * 0.20);
    }

    public static double trainingBattleExperienceBonusPercent(int level) {
        return Math.min(25.0, level * 0.25);
    }

    public static int trainingSkillBonusExperience(int level, int baseExperience) {
        return (int) Math.floor(baseExperience * (trainingBattleExperienceBonusPercent(level) / 100.0));
    }
}
