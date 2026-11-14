package dev.matthiesen.cobblemon_skills.common.runtime;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.pokemon.Pokemon;
import dev.matthiesen.cobblemon_skills.common.config.CobblemonSkillsConfig;

public final class ExperienceHelpers {
    private ExperienceHelpers() {}

    public static double getFossilRevivalExperience(Pokemon pokemon) {
        boolean isShiny = pokemon.getShiny();
        double baseExperience = CobblemonSkillsConfig.Professions.ARCHEOLOGY_CONFIG.experience_fossilRevivalBaseXp.getAsDouble();
        if (isShiny) {
            baseExperience += CobblemonSkillsConfig.Professions.ARCHEOLOGY_CONFIG.experience_fossilRevivalShinyBonusXp.getAsDouble();
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

        double baseXp = CobblemonSkillsConfig.Professions.FISHING_CONFIG.experience_catchBaseXp.getAsDouble();
        double levelMultiplier = CobblemonSkillsConfig.Professions.FISHING_CONFIG.experience_catchLevelXpMultiplier.getAsDouble();
        double ultraBeastBonusXp = CobblemonSkillsConfig.Professions.FISHING_CONFIG.experience_catchUltraBeastBonusXp.getAsDouble();
        double mythicalBonusXp = CobblemonSkillsConfig.Professions.FISHING_CONFIG.experience_catchMythicalBonusXp.getAsDouble();
        double legendaryBonusXp = CobblemonSkillsConfig.Professions.FISHING_CONFIG.experience_catchLegendaryBonusXp.getAsDouble();
        double shinyBonusXp = CobblemonSkillsConfig.Professions.FISHING_CONFIG.experience_catchShinyBonusXp.getAsDouble();
        double baseExperience = baseXp + Math.max(1, level) * levelMultiplier;

        if (isUltraBeast) {
            baseExperience += ultraBeastBonusXp;
        }
        if (isMythical) {
            baseExperience += mythicalBonusXp;
        }
        if (isLegendary) {
            baseExperience += legendaryBonusXp;
        }
        if (isShiny) {
            baseExperience += shinyBonusXp;
        }
        return baseExperience;
    }

    public static double getCaptureSkillExperience(int pokemonLevel, boolean criticalCapture) {
        double baseXp = CobblemonSkillsConfig.Professions.CATCHING_CONFIG.experience_catchBaseXp.getAsDouble();
        double levelMultiplier = CobblemonSkillsConfig.Professions.CATCHING_CONFIG.experience_catchLevelXpMultiplier.getAsDouble();
        double criticalBonusXp = CobblemonSkillsConfig.Professions.CATCHING_CONFIG.experience_catchCriticalBonusXp.getAsDouble();
        double base = baseXp + Math.max(1, pokemonLevel) * levelMultiplier;
        return criticalCapture ? base + criticalBonusXp : base;
    }

    public static double getBreedingEggHatched(int eggCycles) {
        double baseXp = CobblemonSkillsConfig.Professions.BREEDING_CONFIG.experience_eggHatchedBaseXp.getAsDouble();
        double cycleBonusXp = CobblemonSkillsConfig.Professions.BREEDING_CONFIG.experience_eggHatchedCycleBonusXp.getAsDouble();
        return baseXp + Math.max(0, eggCycles) * cycleBonusXp;
    }

    public static double getTrainingSkillExperienceFromLevelUp(int oldLevel, int newLevel) {
        double multiplier = CobblemonSkillsConfig.Professions.TRAINING_CONFIG.experience_levelUpXpMultiplier.getAsDouble();
        return Math.max(0, newLevel - oldLevel) * multiplier;
    }
}
