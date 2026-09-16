package dev.matthiesen.cobblemon_skills.common.interfaces;

public enum Profession {
    ARCHEOLOGY("Archeology"), // Fossils, Gems, Crystals, etc.
    BOTANY("Botany"), // Plants, Berries, Herbs, etc.
    COOKING("Cooking"), // Cobblemon Cooking, Recipes, etc.
    FISHING("Fishing"), // Cobblemon Fishing
    CATCHING("Catching"), // Catching Cobblemon, Crafting specific Pokeballs, etc.
    TRAINING("Training"), // Leveling up Cobblemon, Battling, etc.
    BREEDING("Breeding"); // Breeding Cobblemon, Egg Hatching, etc.

    private final String label;

    Profession(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
