package dev.matthiesen.cobblemon_skills.common.menu;

import dev.matthiesen.cobblemon_skills.common.config.CobblemonSkillsConfig;
import dev.matthiesen.cobblemon_skills.common.registry.PermissionsRegistry;
import dev.matthiesen.cobblemon_skills.common.runtime.TextUtils;
import dev.matthiesen.matthiesen_core.common.api.server_menu.ServerUIManager;
import dev.matthiesen.matthiesen_core.common.api.server_menu.button.*;
import dev.matthiesen.matthiesen_core.common.api.server_menu.helpers.PaginationHelper;
import dev.matthiesen.matthiesen_core.common.api.server_menu.page.Page;
import dev.matthiesen.matthiesen_core.common.api.server_menu.template.types.ChestTemplate;
import dev.matthiesen.matthiesen_core.common.utility.item.ItemBuilder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Items;

import java.util.List;

public final class MainMenu {
    private final ServerPlayer player;

    public MainMenu(ServerPlayer player) {
        this.player = player;
    }

    public Page getPage() {
        PlaceholderButton placeholder = new PlaceholderButton();

        Button profileButton = ServerButton.builder()
                .display(new ItemBuilder(Items.BOOK)
                        .hideAdditional()
                        .setCustomName(TextUtils.parse(CobblemonSkillsConfig.TEXT_CONFIG.gui_mainMenu_yourProfileButton.get()))
                        .addLore(TextUtils.parseList(CobblemonSkillsConfig.TEXT_CONFIG.gui_mainMenu_yourProfileButtonLore.get()))
                        .build())
                .onClick((action) -> PlayerProfile.open(player))
                .build();

        Button leaderboardButton = ServerButton.builder()
                .display(new ItemBuilder(Items.DIAMOND)
                        .hideAdditional()
                        .setCustomName(TextUtils.parse(CobblemonSkillsConfig.TEXT_CONFIG.gui_mainMenu_leaderboardButton.get()))
                        .addLore(TextUtils.parseList(CobblemonSkillsConfig.TEXT_CONFIG.gui_mainMenu_leaderboardButtonLore.get()))
                        .build())
                .onClick((action) -> Leaderboard.open(player))
                .build();

        ChestTemplate template = ChestTemplate.builder(5)
                .rectangle(0, 0, 4, 8, placeholder)
                .set(2, 2, profileButton)
                .set(2, 4, leaderboardButton)
                .build();

        var page = PaginationHelper.createPagesFromPlaceholders(template, List.of(profileButton, leaderboardButton), null);
        page.setTitle(TextUtils.parse(CobblemonSkillsConfig.TEXT_CONFIG.gui_mainMenu_title.get()));
        return page;
    }

    public static void open(ServerPlayer player) {
        if (!PermissionsRegistry.checkPermission(player, PermissionsRegistry.GUI_MAIN_MENU_PERMISSION)) {
            player.sendSystemMessage(TextUtils.parse(CobblemonSkillsConfig.TEXT_CONFIG.errors_menuNoPermission.get()));
            return;
        }
        ServerUIManager.openUIForcefully(player, new MainMenu(player).getPage());
    }
}
