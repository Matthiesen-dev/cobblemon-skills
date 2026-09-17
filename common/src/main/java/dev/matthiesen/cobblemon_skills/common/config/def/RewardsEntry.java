package dev.matthiesen.cobblemon_skills.common.config.def;

import com.electronwill.nightconfig.core.Config;
import dev.matthiesen.matthiesen_core.common.utility.commands.RunSlashCommand;
import dev.matthiesen.matthiesen_core.common.utility.item.ItemDecoder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Items;

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

    public void redeem(ServerPlayer player) throws Exception {
        switch (this.type) {
            case ITEM -> redeemItem(player);
            case EXPERIENCE -> redeemExperience(player);
            case COMMAND -> redeemCommand(player);
        }
    }

    private void redeemItem(ServerPlayer player) throws Exception {
        if (this.item != null && !this.item.isEmpty()) {
            var itemStack = ItemDecoder.stringToItem(this.item, Items.AIR).getDefaultInstance();
            if (itemStack.is(Items.AIR)) {
                throw new Exception("Invalid item: " + this.item);
            }
            itemStack.setCount(this.amount);
            if (!player.getInventory().add(itemStack)) {
                throw new Exception("Failed to add item to inventory: " + this.item);
            }
        } else {
            throw new Exception("Item is null or empty");
        }
    }

    private void redeemExperience(ServerPlayer player) throws Exception {
        if (this.experience > 0) {
            player.giveExperiencePoints((int) this.experience);
        } else {
            throw new Exception("Experience is invalid: " + this.experience);
        }
    }

    private void redeemCommand(ServerPlayer player) throws Exception {
        try {
            if (this.command != null && !this.command.isEmpty()) {
                var command = this.command.replace("{player}", player.getScoreboardName());
                RunSlashCommand.asServer(command);
            }
        } catch (Exception e) {
            throw new Exception("Failed to run command: " + this.command, e);
        }
    }

    public enum TYPE {
        ITEM,
        EXPERIENCE,
        COMMAND
    }
}
