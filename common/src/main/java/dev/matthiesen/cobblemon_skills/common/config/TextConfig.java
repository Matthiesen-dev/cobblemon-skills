package dev.matthiesen.cobblemon_skills.common.config;

import dev.matthiesen.matthiesen_core.common.api.text_parsers.BuiltInTextParsers;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

public final class TextConfig {

    // General
    public ModConfigSpec.EnumValue<BuiltInTextParsers> text_parser;

    //// GUI Configuration

    // GUI Messages
    public ModConfigSpec.ConfigValue<String> gui_messages_rewardRedeemed;
    public ModConfigSpec.ConfigValue<String> gui_messages_rewardLocked;

    // GUI Buttons
    public ModConfigSpec.ConfigValue<String> gui_buttons_backButton;
    public ModConfigSpec.ConfigValue<String> gui_buttons_navigationNext;
    public ModConfigSpec.ConfigValue<String> gui_buttons_navigationPrevious;

    // Leaderboard
    public ModConfigSpec.ConfigValue<String> gui_leaderboard_title;
    public ModConfigSpec.ConfigValue<String> gui_leaderboard_rank;
    public ModConfigSpec.ConfigValue<List<? extends String>> gui_leaderboard_lore;

    // Main Menu
    public ModConfigSpec.ConfigValue<String> gui_mainMenu_title;
    public ModConfigSpec.ConfigValue<String> gui_mainMenu_yourProfileButton;
    public ModConfigSpec.ConfigValue<List<? extends String>> gui_mainMenu_yourProfileButtonLore;
    public ModConfigSpec.ConfigValue<String> gui_mainMenu_leaderboardButton;
    public ModConfigSpec.ConfigValue<List<? extends String>> gui_mainMenu_leaderboardButtonLore;

    // Player Profile
    public ModConfigSpec.ConfigValue<String> gui_playerProfile_title;
    public ModConfigSpec.ConfigValue<List<? extends String>> gui_playerProfile_lore;

    // Profession Entry
    public ModConfigSpec.ConfigValue<String> gui_professionEntry_title;
    public ModConfigSpec.ConfigValue<String> gui_professionEntry_status_redeemed;
    public ModConfigSpec.ConfigValue<String> gui_professionEntry_status_ready;
    public ModConfigSpec.ConfigValue<String> gui_professionEntry_status_locked;
    public ModConfigSpec.ConfigValue<List<? extends String>> gui_professionEntry_viewingOwnProfileLore;
    public ModConfigSpec.ConfigValue<List<? extends String>> gui_professionEntry_viewingOtherProfileLore;
    public ModConfigSpec.ConfigValue<String> gui_professionEntry_noRewardsConfigured;
    public ModConfigSpec.ConfigValue<List<? extends String>> gui_professionEntry_noRewardsConfiguredLore;

    //// Command Messages

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

    //// Error Messages

    public ModConfigSpec.ConfigValue<String> errors_menuError;
    public ModConfigSpec.ConfigValue<String> errors_addExpError;
    public ModConfigSpec.ConfigValue<String> errors_setLevelError;
    public ModConfigSpec.ConfigValue<String> errors_setLevelExceedMaxLevel;
    public ModConfigSpec.ConfigValue<String> errors_reloadError;
    public ModConfigSpec.ConfigValue<String> errors_reset_selfError;
    public ModConfigSpec.ConfigValue<String> errors_reset_playerError;
    public ModConfigSpec.ConfigValue<String> errors_reset_allError;
    public ModConfigSpec.ConfigValue<String> errors_menuNoPermission;
    public ModConfigSpec.ConfigValue<String> errors_rewardAlreadyRedeemed;
    public ModConfigSpec.ConfigValue<String> errors_rewardRedeemFailed;

    public TextConfig(ModConfigSpec.Builder builder) {
        builder.comment("Text Configuration").push("text");

        text_parser = builder.comment("The text parser to use for parsing text in the mod.")
                .defineEnum("text_parser", BuiltInTextParsers.VANILLA);

        builder.comment("GUI Configuration").push("gui");

        builder.comment("Messages displayed in the GUI").push("messages");
        gui_messages_rewardLocked = builder.comment("The message displayed when a reward is locked and cannot be redeemed.")
                .define("gui_messages_rewardLocked", "You have not unlocked this reward yet. Level up your %profession% profession to unlock it.");
        gui_messages_rewardRedeemed = builder.comment("The message displayed when a reward has already been redeemed.")
                .define("gui_messages_rewardRedeemed", "You have successfully redeemed the rewards for %tier% in %profession%.");
        builder.pop(); // pop "messages"

        builder.comment("Buttons displayed in the GUI").push("buttons");
        gui_buttons_backButton = builder.comment("The text displayed on the back button in the GUI.")
                .define("gui_buttons_backButton", "Back");
        gui_buttons_navigationNext = builder.comment("The text displayed on the next page button in the GUI.")
                .define("gui_buttons_navigationNext", "Next");
        gui_buttons_navigationPrevious = builder.comment("The text displayed on the previous page button in the GUI.")
                .define("gui_buttons_navigationPrevious", "Previous");
        builder.pop(); // pop "buttons"

        builder.comment("Leaderboard Configuration").push("leaderboard");
        gui_leaderboard_title = builder.comment("The title displayed on the leaderboard GUI.")
                .define("gui_leaderboard_title", "Cobblemon Skills Leaderboard");
        gui_leaderboard_rank = builder.comment("The format for displaying the rank of a player on the leaderboard.")
                .define("gui_leaderboard_rank", "%rank%. %player%");
        gui_leaderboard_lore = builder.comment("The lore displayed on the leaderboard GUI.")
                .defineList("gui_leaderboard_lore", List.of(
                        "Highest Profession Level: %highestLevel%",
                        "Top Profession: %topProfession%"
                ), () -> "", o -> o instanceof String);
        builder.pop(); // pop "leaderboard"

        builder.comment("Main Menu Configuration").push("mainMenu");
        gui_mainMenu_title = builder.comment("The title displayed on the main menu GUI.")
                .define("gui_mainMenu_title", "Cobblemon Skills");
        gui_mainMenu_yourProfileButton = builder.comment("The text displayed on the 'Your Profile' button in the main menu GUI.")
                .define("gui_mainMenu_yourProfileButton", "Your Profile");
        gui_mainMenu_yourProfileButtonLore = builder.comment("The lore displayed on the 'Your Profile' button in the main menu GUI.")
                .defineList("gui_mainMenu_yourProfileButtonLore", List.of(
                        "View your profession progress",
                        "and unlock status"
                ), () -> "", o -> o instanceof String);
        gui_mainMenu_leaderboardButton = builder.comment("The text displayed on the 'Leaderboard' button in the main menu GUI.")
                .define("gui_mainMenu_leaderboardButton", "Leaderboard");
        gui_mainMenu_leaderboardButtonLore = builder.comment("The lore displayed on the 'Leaderboard' button in the main menu GUI.")
                .defineList("gui_mainMenu_leaderboardButtonLore", List.of(
                        "See the strongest players",
                        "in the server"
                ), () -> "", o -> o instanceof String);
        builder.pop(); // pop "mainMenu"

        builder.comment("Player Profile Configuration").push("playerProfile");
        gui_playerProfile_title = builder.comment("The title displayed on the player profile GUI.")
                .define("gui_playerProfile_title", "%player%'s Profile");
        gui_playerProfile_lore = builder.comment("The lore displayed on the player profile GUI.")
                .defineList("gui_playerProfile_lore", List.of(
                        "Level: %level%",
                        "Experience: %experience%/%nextLevelExp%"
                ), () -> "", o -> o instanceof String);
        builder.pop(); // pop "playerProfile"

        builder.comment("Profession Entry Configuration").push("professionEntry");
        gui_professionEntry_title = builder.comment("The title displayed on the profession entry GUI.")
                .define("gui_professionEntry_title", "%player% - %profession% | Lvl %level%");
        gui_professionEntry_status_redeemed = builder.comment("The text displayed for a redeemed reward in the profession entry GUI.")
                .define("gui_professionEntry_status_redeemed", "Redeemed");
        gui_professionEntry_status_ready = builder.comment("The text displayed for a ready-to-redeem reward in the profession entry GUI.")
                .define("gui_professionEntry_status_ready", "Ready");
        gui_professionEntry_status_locked = builder.comment("The text displayed for a locked reward in the profession entry GUI.")
                .define("gui_professionEntry_status_locked", "Locked");
        gui_professionEntry_viewingOwnProfileLore = builder.comment("The lore displayed on the profession entry GUI when viewing your own profile.")
                .defineList("gui_professionEntry_viewingOwnProfileLore", List.of(
                        "Requirement: %levelRequirement%",
                        "Status: %status%",
                        "Reward Count: %rewardCount%"
                ), () -> "", o -> o instanceof String);
        gui_professionEntry_viewingOtherProfileLore = builder.comment("The lore displayed on the profession entry GUI when viewing another player's profile.")
                .defineList("gui_professionEntry_viewingOtherProfileLore", List.of(
                        "Requirement: %levelRequirement%"
                ), () -> "", o -> o instanceof String);
        gui_professionEntry_noRewardsConfigured = builder.comment("The text displayed when a profession has no rewards configured.")
                .define("gui_professionEntry_noRewardsConfigured", "No rewards configured.");
        gui_professionEntry_noRewardsConfiguredLore = builder.comment("The lore displayed when a profession has no rewards configured.")
                .defineList("gui_professionEntry_noRewardsConfiguredLore", List.of(
                        "This profession currently has no rewards configured.",
                        "Please ask an Admin to add some."
                ), () -> "", o -> o instanceof String);
        builder.pop(); // pop "professionEntry"

        builder.pop(); // pop "gui"

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
        errors_rewardAlreadyRedeemed = builder.comment("The error message displayed when a player tries to redeem a reward they have already redeemed.")
                .define("errors_rewardAlreadyRedeemed", "You have already redeemed this reward.");
        errors_rewardRedeemFailed = builder.comment("The error message displayed when a player tries to redeem a reward but the redemption fails.")
                .define("errors_rewardRedeemFailed", "Failed to redeem the rewards for %tier% in %profession%. Please contact an administrator.");
        builder.pop(); // pop "errors"

        builder.pop(); // pop "text"
    }
}
