package dev.matthiesen.cobblemon_skills.common.runtime;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.pokemon.Pokemon;
import dev.matthiesen.cobblemon_skills.common.runtime.data.ArcheologyStatics;
import dev.matthiesen.cobblemon_skills.common.runtime.data.BotanyStatics;
import dev.matthiesen.cobblemon_skills.common.runtime.data.CookingStatics;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public final class ExperienceManager {
    private ExperienceManager() {}

    public static boolean isCobblemonArcheologyItem(ItemStack item) {
        return ArcheologyStatics.ITEMS.containsKey(item.getItem());
    }

    public static int getCobblemonArcheologyItemExperience(ItemStack item) {
        return ArcheologyStatics.ITEMS.getOrDefault(item.getItem(), 0);
    }

    public static boolean isCobblemonCookingItem(Item item) {
        return CookingStatics.ITEMS.containsKey(item);
    }

    public static int getCobblemonCookingItemExperience(Item item) {
        return CookingStatics.ITEMS.getOrDefault(item, 0);
    }

    public static boolean isArcheologyBlock(Block block) {
        return ArcheologyStatics.BLOCKS.containsKey(block);
    }

    public static boolean isBotanyBlock(Block block) {
        return BotanyStatics.BLOCKS.containsKey(block);
    }

    public static int getArcheologyBlockExperience(Block block) {
        return ArcheologyStatics.BLOCKS.getOrDefault(block, 0);
    }

    public static int getBotanyBlockExperience(Block block) {
        return BotanyStatics.BLOCKS.getOrDefault(block, 0);
    }

    public static double getLeftoversExperience() {
        return 100.0; // Base XP for getting Leftovers
    }

    public static double getApricornHarvestExperience() {
        return 150.0; // Base XP for harvesting an Apricorn
    }

    public static double getBerryHarvestExperience() {
        return 200.0; // Base XP for harvesting a Berry
    }

    public static double getFishingExperience(int fishingCaught) {
        return fishingCaught * 250.0;
    }

    public static double getFossilRevivalExperience(Pokemon pokemon) {
        boolean isShiny = pokemon.getShiny();
        double baseExperience = 500.0; // Base XP for reviving a fossil Pokémon
        if (isShiny) {
            baseExperience += 400.0; // Bonus for shiny Pokémon
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

        double baseExperience = 200.0 + Math.max(1, level) * 10.0;
        if (isUltraBeast) {
            baseExperience += 100.0; // Bonus for ultra beast Pokémon
        }
        if (isMythical) {
            baseExperience += 200.0; // Bonus for mythical Pokémon
        }
        if (isLegendary) {
            baseExperience += 300.0; // Bonus for legendary Pokémon
        }
        if (isShiny) {
            baseExperience += 400.0; // Bonus for shiny Pokémon
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
        double base = 200.0 + Math.max(1, pokemonLevel) * 8.0;
        return criticalCapture ? base + 150.0 : base;
    }

    public static double getBattleExperience() {
        return 300.0; // Base XP for winning a battle
    }

    public static double getBreedingEggCollected() {
        return 250.0; // Base XP for collecting an egg
    }

    public static int getBreedingFriendshipBonus(int breedingLevel) {
        return Math.min(20, breedingLevel / 5); // Base friendship bonus for hatching an egg, increases with breeding level
    }

    public static double getBreedingEggHatched(int eggCycles) {
        return 200.0 + Math.max(0, eggCycles) * 4.0; // Base XP for hatching an egg plus bonus based on egg cycles
    }

    public static double getTrainingSkillExperienceFromLevelUp(int oldLevel, int newLevel) {
        return Math.max(0, newLevel - oldLevel) * 300.0;
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
