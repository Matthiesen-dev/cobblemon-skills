package dev.matthiesen.cobblemon_skills.common.data;

public enum Profession {
    ARCHEOLOGY("Archeology"),
    BOTANY("Botany"),
    COOKING("Cooking"),
    FISHING("Fishing"),
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
