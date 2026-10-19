package dev.matthiesen.cobblemon_skills.common.runtime;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.pokemon.Pokemon;
import dev.matthiesen.cobblemon_skills.common.config.CobblemonSkillsConfig;
import dev.matthiesen.cobblemon_skills.common.runtime.data.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public final class ExperienceManager {
    private ExperienceManager() {}

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
        return CobblemonSkillsConfig.TRAINING_CONFIG.experience_leftoversXp.getAsDouble();
    }

    public static double getApricornHarvestExperience() {
        return CobblemonSkillsConfig.BOTANY_CONFIG.experience_apricornHarvestXp.getAsDouble();
    }

    public static double getBerryHarvestExperience() {
        return CobblemonSkillsConfig.BOTANY_CONFIG.experience_berryHarvestXp.getAsDouble();
    }

    public static double getFishingExperience(int fishingCaught) {
        return fishingCaught * CobblemonSkillsConfig.FISHING_CONFIG.experience_statsXpMultiplier.getAsDouble();
    }

    public static double getFossilRevivalExperience(Pokemon pokemon) {
        boolean isShiny = pokemon.getShiny();
        double baseExperience = CobblemonSkillsConfig.ARCHEOLOGY_CONFIG.experience_fossilRevivalBaseXp.getAsDouble();
        if (isShiny) {
            baseExperience += CobblemonSkillsConfig.ARCHEOLOGY_CONFIG.experience_fossilRevivalShinyBonusXp.getAsDouble();
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
        var fishingConfig = CobblemonSkillsConfig.FISHING_CONFIG;
        double baseExperience = fishingConfig.experience_catchBaseXp.getAsDouble() + Math.max(1, level) * fishingConfig.experience_catchLevelXpMultiplier.getAsDouble();
        if (isUltraBeast) {
            baseExperience += fishingConfig.experience_catchUltraBeastBonusXp.getAsDouble();
        }
        if (isMythical) {
            baseExperience += fishingConfig.experience_catchMythicalBonusXp.getAsDouble();
        }
        if (isLegendary) {
            baseExperience += fishingConfig.experience_catchLegendaryBonusXp.getAsDouble();
        }
        if (isShiny) {
            baseExperience += fishingConfig.experience_catchShinyBonusXp.getAsDouble();
        }
        return baseExperience;
    }

    public static double getCaptureSkillExperience(int pokemonLevel, boolean criticalCapture) {
        var catchingConfig = CobblemonSkillsConfig.CATCHING_CONFIG;
        double base = catchingConfig.experience_catchBaseXp.getAsDouble() + Math.max(1, pokemonLevel) * catchingConfig.experience_catchLevelXpMultiplier.getAsDouble();
        return criticalCapture ? base + catchingConfig.experience_catchCriticalBonusXp.getAsDouble() : base;
    }

    public static double getBattleExperience() {
        var trainingConfig = CobblemonSkillsConfig.TRAINING_CONFIG;
        return trainingConfig.experience_baseXp.getAsDouble();
    }

    public static double getBreedingEggCollected() {
        var breedingConfig = CobblemonSkillsConfig.BREEDING_CONFIG;
        return breedingConfig.experience_eggCollectedBaseXp.getAsDouble();
    }

    public static double getBreedingEggHatched(int eggCycles) {
        var breedingConfig = CobblemonSkillsConfig.BREEDING_CONFIG;
        return breedingConfig.experience_eggHatchedBaseXp.getAsDouble() + Math.max(0, eggCycles) * breedingConfig.experience_eggHatchedCycleBonusXp.getAsDouble();
    }

    public static double getTrainingSkillExperienceFromLevelUp(int oldLevel, int newLevel) {
        var trainingConfig = CobblemonSkillsConfig.TRAINING_CONFIG;
        return Math.max(0, newLevel - oldLevel) * trainingConfig.experience_levelUpXpMultiplier.getAsDouble();
    }
}
