package dev.matthiesen.cobblemon_skills.common.config.def;

import com.electronwill.nightconfig.core.Config;

public final class RewardsEntry {
    public TYPE type;

    // TYPE: ITEM
    public String item;
    public int amount;
    // TYPE: EXPERIENCE
    public double experience;
    // TYPE: COMMAND
    public String command;

    public RewardsEntry(String item, int amount) {
        this.type = TYPE.ITEM;
        this.item = item;
        this.amount = amount;
    }

    public RewardsEntry(double experience) {
        this.type = TYPE.EXPERIENCE;
        this.experience = experience;
    }

    public RewardsEntry(String command) {
        this.type = TYPE.COMMAND;
        this.command = command;
    }

    public static RewardsEntry deserialize(Config config) {
        TYPE type = config.getEnum("type", TYPE.class);
        switch (type) {
            case ITEM -> {
                String item = config.get("item");
                int amount = config.getInt("amount");
                return new RewardsEntry(item, amount);
            }
            case EXPERIENCE -> {
                double experience = config.getInt("experience");
                return new RewardsEntry(experience);
            }
            case COMMAND -> {
                String command = config.get("command");
                return new RewardsEntry(command);

            }
            default -> throw new IllegalArgumentException("Unknown type: " + type);
        }
    }

    public static boolean isValid(Config config) {
        if (!config.contains("type")) {
            return false;
        }
        TYPE type = config.getEnum("type", TYPE.class);
        switch (type) {
            case ITEM -> {
                return config.contains("item") && config.contains("amount");
            }
            case EXPERIENCE -> {
                return config.contains("experience");
            }
            case COMMAND -> {
                return config.contains("command");
            }
            default -> throw new IllegalArgumentException("Unknown type: " + type);
        }
    }

    public Config serialize() {
        Config config = Config.inMemory();
        config.set("type", this.type);
        switch (this.type) {
            case ITEM -> {
                config.set("item", this.item);
                config.set("amount", this.amount);
            }
            case EXPERIENCE -> config.set("experience", this.experience);
            case COMMAND -> config.set("command", this.command);
        }
        return config;
    }

    public enum TYPE {
        ITEM,
        EXPERIENCE,
        COMMAND
    }
}
