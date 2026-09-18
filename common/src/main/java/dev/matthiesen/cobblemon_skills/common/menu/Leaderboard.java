package dev.matthiesen.cobblemon_skills.common.menu;

import ca.landonjw.gooeylibs2.api.UIManager;
import ca.landonjw.gooeylibs2.api.button.Button;
import ca.landonjw.gooeylibs2.api.button.GooeyButton;
import ca.landonjw.gooeylibs2.api.button.PlaceholderButton;
import ca.landonjw.gooeylibs2.api.button.linked.LinkType;
import ca.landonjw.gooeylibs2.api.button.linked.LinkedPageButton;
import ca.landonjw.gooeylibs2.api.helpers.PaginationHelper;
import ca.landonjw.gooeylibs2.api.page.LinkedPage;
import ca.landonjw.gooeylibs2.api.page.Page;
import ca.landonjw.gooeylibs2.api.template.types.ChestTemplate;
import dev.matthiesen.cobblemon_skills.common.data.Profession;
import dev.matthiesen.cobblemon_skills.common.data.SavedPlayerProfessionData;
import dev.matthiesen.cobblemon_skills.common.menu.util.ProfileUtils;
import dev.matthiesen.cobblemon_skills.common.registry.PermissionsRegistry;
import dev.matthiesen.matthiesen_core.common.utility.item.ItemBuilder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class Leaderboard {
    private final ServerPlayer player;

    public Leaderboard(ServerPlayer player) {
        this.player = player;
    }

    public Page getPage() {
        PlaceholderButton placeholder = new PlaceholderButton();
        List<Button> leaderboardButtons = new ArrayList<>();

        List<Map.Entry<UUID, dev.matthiesen.cobblemon_skills.common.data.PlayerProfile>> rankedProfiles = new ArrayList<>(SavedPlayerProfessionData.getPlayerProfiles());
        rankedProfiles.sort(Comparator.comparingInt((Map.Entry<UUID, dev.matthiesen.cobblemon_skills.common.data.PlayerProfile> entry) -> getHighestLevel(entry.getValue())).reversed());

        for (int i = 0; i < rankedProfiles.size(); i++) {
            Map.Entry<UUID, dev.matthiesen.cobblemon_skills.common.data.PlayerProfile> entry = rankedProfiles.get(i);
            UUID uuid = entry.getKey();
            dev.matthiesen.cobblemon_skills.common.data.PlayerProfile profile = entry.getValue();
            int highestLevel = getHighestLevel(profile);

            String displayName = ProfileUtils.getDisplayName(uuid);
            Button button = GooeyButton.builder()
                    .display(new ItemBuilder(Items.PAPER)
                            .hideAdditional()
                            .setCustomName(Component.literal((i + 1) + ". " + displayName))
                            .addLore(new Component[]{
                                    Component.literal("Highest Profession Level: " + highestLevel),
                                    Component.literal("Top profession: " + getTopProfessionName(profile))
                            })
                            .build())
                    .onClick((action) -> PlayerProfile.open(player, uuid))
                    .build();
            leaderboardButtons.add(button);
        }

        Button frame = GooeyButton.builder().display(new ItemBuilder(Items.GRAY_STAINED_GLASS_PANE).hideAdditional().setCustomName(Component.literal(" ")).build()).build();
        Button backButton = GooeyButton.builder()
                .display(new ItemBuilder(Items.OAK_DOOR)
                        .hideAdditional()
                        .setCustomName(Component.literal("Back"))
                        .build())
                .onClick((action) -> MainMenu.open(player))
                .build();

        LinkedPageButton previous = LinkedPageButton.builder()
                .display(new ItemBuilder(Items.ARROW).hideAdditional().setCustomName(Component.literal("Previous")).build())
                .linkType(LinkType.Previous)
                .build();

        LinkedPageButton next = LinkedPageButton.builder()
                .display(new ItemBuilder(Items.ARROW).hideAdditional().setCustomName(Component.literal("Next")).build())
                .linkType(LinkType.Next)
                .build();

        ChestTemplate template = ChestTemplate.builder(6)
                .rectangle(0, 0, 5, 8, placeholder)
                .set(45, previous)
                .set(49, backButton)
                .set(53, next)
                .fill(frame)
                .build();

        LinkedPage page = PaginationHelper.createPagesFromPlaceholders(template, leaderboardButtons, null);
        page.setTitle(Component.literal("Cobblemon Skills Leaderboard"));
        return page;
    }

    private int getHighestLevel(dev.matthiesen.cobblemon_skills.common.data.PlayerProfile profile) {
        int highest = 0;
        for (Profession profession : Profession.values()) {
            highest = Math.max(highest, profile.getProgress(profession).level());
        }
        return highest;
    }

    private String getTopProfessionName(dev.matthiesen.cobblemon_skills.common.data.PlayerProfile profile) {
        Profession topProfession = Profession.values()[0];
        int highestLevel = profile.getProgress(topProfession).level();

        for (Profession profession : Profession.values()) {
            int level = profile.getProgress(profession).level();
            if (level > highestLevel) {
                highestLevel = level;
                topProfession = profession;
            }
        }

        return topProfession.getConfig().displayName();
    }

    public static void open(ServerPlayer player) {
        if (!PermissionsRegistry.checkPermission(player, PermissionsRegistry.GUI_LEADERBOARD_PERMISSION)) {
            player.sendSystemMessage(Component.literal("You do not have permission to access the leaderboard."));
            return;
        }
        UIManager.openUIForcefully(player, new Leaderboard(player).getPage());
    }
}
