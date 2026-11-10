package dev.matthiesen.cobblemon_skills.common.config;

import dev.matthiesen.matthiesen_core.common.api.text_parsers.BuiltInTextParsers;
import net.neoforged.neoforge.common.ModConfigSpec;

public final class TextConfig {

    // General
    public ModConfigSpec.EnumValue<BuiltInTextParsers> text_parser;

    // Command Messages
    public ModConfigSpec.ConfigValue<String> commands_addExpSuccess;
    public ModConfigSpec.ConfigValue<String> commands_setLevelSourceSuccess;
    public ModConfigSpec.ConfigValue<String> commands_setLevelTargetSuccess;
    public ModConfigSpec.ConfigValue<String> commands_reloadSuccess;
    public ModConfigSpec.ConfigValue<String> commands_reset_selfSuccess;
    public ModConfigSpec.ConfigValue<String> commands_reset_selfNoData;
    public ModConfigSpec.ConfigValue<String> commands_reset_playerSuccess;
    public ModConfigSpec.ConfigValue<String> commands_reset_playerSuccessTarget;
    public ModConfigSpec.ConfigValue<String> commands_reset_playerNoData;
    public ModConfigSpec.ConfigValue<String> commands_reset_allSuccess;

    // Error Messages
    public ModConfigSpec.ConfigValue<String> errors_menuError;
    public ModConfigSpec.ConfigValue<String> errors_addExpError;
    public ModConfigSpec.ConfigValue<String> errors_setLevelError;
    public ModConfigSpec.ConfigValue<String> errors_setLevelExceedMaxLevel;
    public ModConfigSpec.ConfigValue<String> errors_reloadError;
    public ModConfigSpec.ConfigValue<String> errors_reset_selfError;
    public ModConfigSpec.ConfigValue<String> errors_reset_playerError;
    public ModConfigSpec.ConfigValue<String> errors_reset_allError;
    public ModConfigSpec.ConfigValue<String> errors_menuNoPermission;

    public TextConfig(ModConfigSpec.Builder builder) {
        builder.comment("Text Configuration").push("text");

        text_parser = builder.comment("The text parser to use for parsing text in the mod.")
                .defineEnum("text_parser", BuiltInTextParsers.VANILLA);

        builder.comment("Command Messages").push("commands");
        commands_addExpSuccess = builder.comment("The message displayed when experience is successfully added to a player.")
                .define("commands_addExpSuccess", "Added %amount% experience to player %player% for profession %profession%. Levels gained: %levelsGained%");
        commands_setLevelSourceSuccess = builder.comment("The message displayed to the command source when a player's level is successfully set.")
                .define("commands_setLevelSourceSuccess", "Set level of player %player% for profession %profession% to %level%");
        commands_setLevelTargetSuccess = builder.comment("The message displayed to the target player when their level is successfully set by an administrator.")
                .define("commands_setLevelTargetSuccess", "Your level for profession %profession% has been set to %level% by an administrator.");
        commands_reloadSuccess = builder.comment("The message displayed when the configuration is successfully reloaded.")
                .define("commands_reloadSuccess", "Cobblemon Skills configuration reloaded successfully.");
        commands_reset_selfSuccess = builder.comment("The message displayed when a player's data is successfully reset.")
                .define("commands_reset_selfSuccess", "Your Cobblemon Skills data has been reset.");
        commands_reset_selfNoData = builder.comment("The message displayed when a player's data is attempted to be reset but no data exists.")
                .define("commands_reset_selfNoData", "No Cobblemon Skills data found for your player profile.");
        commands_reset_playerSuccess = builder.comment("The message displayed when a player's data is successfully reset by an administrator.")
                .define("commands_reset_playerSuccess", "Cobblemon Skills data for player %target% has been reset.");
        commands_reset_playerSuccessTarget = builder.comment("The message displayed to the target player when their data is successfully reset by an administrator.")
                .define("commands_reset_playerSuccessTarget", "Your Cobblemon Skills data has been reset by an administrator.");
        commands_reset_playerNoData = builder.comment("The message displayed when a player's data is attempted to be reset by an administrator but no data exists.")
                .define("commands_reset_playerNoData", "No Cobblemon Skills data found for player %target%.");
        commands_reset_allSuccess = builder.comment("The message displayed when all players' data is successfully reset by an administrator.")
                .define("commands_reset_allSuccess", "Cobblemon Skills data for %count% players has been reset.");
        builder.pop(); // pop "commands"

        builder.comment("Error Messages").push("errors");
        errors_menuError = builder.comment("The error message displayed when a menu fails to open.")
                .define("errors_menuError", "An error occurred while trying to open the Cobblemon Skills menu. Please contact an administrator.");
        errors_addExpError = builder.comment("The error message displayed when adding experience fails.")
                .define("errors_addExpError", "An error occurred while trying to add experience to the player. Please contact an administrator.");
        errors_setLevelError = builder.comment("The error message displayed when setting a level fails.")
                .define("errors_setLevelError", "An error occurred while trying to set the level for the player. Please contact an administrator.");
        errors_setLevelExceedMaxLevel = builder.comment("The error message displayed when setting a level exceeds the maximum allowed level.")
                .define("errors_setLevelExceedMaxLevel", "The specified level exceeds the maximum allowed level of %maxLevel%.");
        errors_reloadError = builder.comment("The error message displayed when reloading the configuration fails.")
                .define("errors_reloadError", "An error occurred while trying to reload the configuration. Please contact an administrator.");
        errors_reset_selfError = builder.comment("The error message displayed when resetting a player's data fails.")
                .define("errors_reset_selfError", "An error occurred while trying to reset your data. Please contact an administrator.");
        errors_reset_playerError = builder.comment("The error message displayed when resetting a player's data by an administrator fails.")
                .define("errors_reset_playerError", "An error occurred while trying to reset the data for player %target%. Please contact an administrator.");
        errors_reset_allError = builder.comment("The error message displayed when resetting all players' data by an administrator fails.")
                .define("errors_reset_allError", "An error occurred while trying to reset the data for all players. Please contact an administrator.");
        errors_menuNoPermission = builder.comment("The error message displayed when a player tries to access a menu they don't have permission for.")
                .define("errors_menuNoPermission", "You do not have permission to access this menu.");
        builder.pop(); // pop "errors"

        builder.pop(); // pop "text"
    }
}
