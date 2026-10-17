package dev.matthiesen.cobblemon_skills.common.menu;

import dev.matthiesen.cobblemon_skills.common.registry.PermissionsRegistry;
import dev.matthiesen.matthiesen_core.common.api.server_menu.ServerUIManager;
import dev.matthiesen.matthiesen_core.common.api.server_menu.button.Button;
import dev.matthiesen.matthiesen_core.common.api.server_menu.button.PlaceholderButton;
import dev.matthiesen.matthiesen_core.common.api.server_menu.button.ServerButton;
import dev.matthiesen.matthiesen_core.common.api.server_menu.helpers.PaginationHelper;
import dev.matthiesen.matthiesen_core.common.api.server_menu.page.Page;
import dev.matthiesen.matthiesen_core.common.api.server_menu.template.types.ChestTemplate;
import dev.matthiesen.matthiesen_core.common.utility.item.ItemBuilder;
import net.minecraft.network.chat.Component;
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
                        .setCustomName(Component.literal("Your Profile"))
                        .addLore(new Component[]{
                                Component.literal("View your profession progress"),
                                Component.literal("and unlock status")
                        })
                        .build())
                .onClick((action) -> PlayerProfile.open(player))
                .build();

        Button leaderboardButton = ServerButton.builder()
                .display(new ItemBuilder(Items.DIAMOND)
                        .hideAdditional()
                        .setCustomName(Component.literal("Leaderboard"))
                        .addLore(new Component[]{
                                Component.literal("See the strongest players"),
                                Component.literal("in the server")})
                        .build())
                .onClick((action) -> Leaderboard.open(player))
                .build();

        ChestTemplate template = ChestTemplate.builder(5)
                .rectangle(0, 0, 4, 8, placeholder)
                .set(2, 2, profileButton)
                .set(2, 4, leaderboardButton)
                .build();

        var page = PaginationHelper.createPagesFromPlaceholders(template, List.of(profileButton, leaderboardButton), null);
        page.setTitle(Component.literal("Cobblemon Skills"));
        return page;
    }

    public static void open(ServerPlayer player) {
        if (!PermissionsRegistry.checkPermission(player, PermissionsRegistry.GUI_MAIN_MENU_PERMISSION)) {
            player.sendSystemMessage(Component.literal("You do not have permission to access the main menu."));
            return;
        }
        ServerUIManager.openUIForcefully(player, new MainMenu(player).getPage());
    }
}
