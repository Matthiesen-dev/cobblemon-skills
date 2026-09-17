package dev.matthiesen.cobblemon_skills.common.menu;

import ca.landonjw.gooeylibs2.api.UIManager;
import ca.landonjw.gooeylibs2.api.button.Button;
import ca.landonjw.gooeylibs2.api.button.GooeyButton;
import ca.landonjw.gooeylibs2.api.button.PlaceholderButton;
import ca.landonjw.gooeylibs2.api.helpers.PaginationHelper;
import ca.landonjw.gooeylibs2.api.page.Page;
import ca.landonjw.gooeylibs2.api.template.types.ChestTemplate;
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

        Button profileButton = GooeyButton.builder()
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

        Button leaderboardButton = GooeyButton.builder()
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
        UIManager.openUIForcefully(player, new MainMenu(player).getPage());
    }
}
