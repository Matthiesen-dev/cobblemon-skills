package dev.matthiesen.cobblemon_skills.common.data;

// TODO: Archeology
// TODO: Botany
// TODO: Fishing
public enum Profession {
    ARCHEOLOGY("Archeology"), // Fossils, Gems, Crystals, etc.
    BOTANY("Botany"), // Plants, Berries, Herbs, etc.
    COOKING("Cooking"),
    FISHING("Fishing"), // Cobblemon Fishing
    CATCHING("Catching"),
    TRAINING("Training"),
    BREEDING("Breeding");

    private final String label;

    Profession(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
