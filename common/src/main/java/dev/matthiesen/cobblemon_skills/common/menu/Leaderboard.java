package dev.matthiesen.cobblemon_skills.common.menu;

import dev.matthiesen.cobblemon_skills.common.config.CobblemonSkillsConfig;
import dev.matthiesen.cobblemon_skills.common.data.*;
import dev.matthiesen.cobblemon_skills.common.menu.util.ProfileUtils;
import dev.matthiesen.cobblemon_skills.common.registry.PermissionsRegistry;
import dev.matthiesen.cobblemon_skills.common.runtime.TextUtils;
import dev.matthiesen.matthiesen_core.common.api.server_menu.ServerUIManager;
import dev.matthiesen.matthiesen_core.common.api.server_menu.button.*;
import dev.matthiesen.matthiesen_core.common.api.server_menu.button.linked.*;
import dev.matthiesen.matthiesen_core.common.api.server_menu.helpers.PaginationHelper;
import dev.matthiesen.matthiesen_core.common.api.server_menu.page.*;
import dev.matthiesen.matthiesen_core.common.api.server_menu.template.types.ChestTemplate;
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

            Map<String, String> placeholders = Map.of(
                    "%rank%", String.valueOf(i + 1),
                    "%player%", displayName,
                    "%highestLevel%", String.valueOf(highestLevel),
                    "%topProfession%", getTopProfessionName(profile)
            );

            Button button = ServerButton.builder()
                    .display(new ItemBuilder(Items.PAPER)
                            .hideAdditional()
                            .setCustomName(TextUtils.parse(CobblemonSkillsConfig.TEXT_CONFIG.gui_leaderboard_rank.get(), placeholders))
                            .addLore(TextUtils.parseList(CobblemonSkillsConfig.TEXT_CONFIG.gui_leaderboard_lore.get(), placeholders))
                            .build())
                    .onClick((action) -> PlayerProfile.open(player, uuid))
                    .build();
            leaderboardButtons.add(button);
        }

        Button frame = ServerButton.builder().display(new ItemBuilder(Items.GRAY_STAINED_GLASS_PANE).hideAdditional().setCustomName(Component.literal(" ")).build()).build();
        Button backButton = ServerButton.builder()
                .display(new ItemBuilder(Items.OAK_DOOR)
                        .hideAdditional()
                        .setCustomName(TextUtils.parse(CobblemonSkillsConfig.TEXT_CONFIG.gui_buttons_backButton.get()))
                        .build())
                .onClick((action) -> MainMenu.open(player))
                .build();

        LinkedPageButton previous = LinkedPageButton.builder()
                .display(new ItemBuilder(Items.ARROW)
                        .hideAdditional()
                        .setCustomName(TextUtils.parse(CobblemonSkillsConfig.TEXT_CONFIG.gui_buttons_navigationPrevious.get()))
                        .build())
                .linkType(LinkType.Previous)
                .build();

        LinkedPageButton next = LinkedPageButton.builder()
                .display(new ItemBuilder(Items.ARROW)
                        .hideAdditional()
                        .setCustomName(TextUtils.parse(CobblemonSkillsConfig.TEXT_CONFIG.gui_buttons_navigationNext.get()))
                        .build())
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
        page.setTitle(TextUtils.parse(CobblemonSkillsConfig.TEXT_CONFIG.gui_leaderboard_title.get()));
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
            player.sendSystemMessage(TextUtils.parse(CobblemonSkillsConfig.TEXT_CONFIG.errors_menuNoPermission.get()));
            return;
        }
        ServerUIManager.openUIForcefully(player, new Leaderboard(player).getPage());
    }
}
