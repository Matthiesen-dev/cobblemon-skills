package dev.matthiesen.cobblemon_skills.common.menu;

import dev.matthiesen.cobblemon_skills.common.config.CobblemonSkillsConfig;
import dev.matthiesen.cobblemon_skills.common.data.*;
import dev.matthiesen.cobblemon_skills.common.menu.util.ProfileUtils;
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
import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class PlayerProfile {
    private final ServerPlayer viewer;
    private final UUID targetUuid;

    public PlayerProfile(ServerPlayer viewer, UUID targetUuid) {
        this.viewer = viewer;
        this.targetUuid = targetUuid;
    }

    public Page getPage() {
        PlaceholderButton placeholder = new PlaceholderButton();
        String displayName = ProfileUtils.getDisplayName(targetUuid);
        dev.matthiesen.cobblemon_skills.common.data.PlayerProfile profile = SavedPlayerProfessionData.get(targetUuid);

        List<Button> professionButtons = new ArrayList<>();
        for (Profession profession : Profession.values()) {
            ProfessionProgress progress = profile.getProgress(profession);

            Map<String, String> placeholders = Map.of(
                    "%profession%", profession.getConfig().displayName(),
                    "%level%", String.valueOf(progress.level()),
                    "%experience%", String.valueOf(progress.experience()),
                    "%nextLevelExp%", String.valueOf(ProfessionProgress.experienceRequiredForNextLevel(progress.level()))
            );

            ItemBuilder builder = new ItemBuilder(Items.BOOK)
                    .hideAdditional()
                    .setCustomName(TextUtils.parse(profession.getConfig().displayName()))
                    .addLore(TextUtils.parseList(CobblemonSkillsConfig.TEXT_CONFIG.gui_playerProfile_lore.get(), placeholders));

            Button button = ServerButton.builder()
                    .display(builder.build())
                    .onClick((action) -> PlayerProfessionEntry.open(viewer, targetUuid, profession))
                    .build();
            professionButtons.add(button);
        }

        Button frame = ServerButton.builder()
                .display(new ItemBuilder(Items.GRAY_STAINED_GLASS_PANE)
                        .hideAdditional()
                        .setCustomName(Component.literal(" "))
                        .build())
                .build();

        Button backButton = ServerButton.builder()
                .display(new ItemBuilder(Items.OAK_DOOR)
                        .hideAdditional()
                        .setCustomName(TextUtils.parse(CobblemonSkillsConfig.TEXT_CONFIG.gui_buttons_backButton.get()))
                        .build())
                .onClick((action) -> MainMenu.open(viewer))
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

        LinkedPage page = PaginationHelper.createPagesFromPlaceholders(template, professionButtons, null);

        Map<String, String> placeholders = Map.of(
                "%player%", displayName
        );

        page.setTitle(TextUtils.parse(CobblemonSkillsConfig.TEXT_CONFIG.gui_playerProfile_title.get(), placeholders));
        return page;
    }

    public static void open(ServerPlayer player, UUID targetUuid) {
        if (!ProfileUtils.checkPermission(player, targetUuid)) {
            return;
        }
        ServerUIManager.openUIForcefully(player, new PlayerProfile(player, targetUuid).getPage());
    }

    public static void open(ServerPlayer player) {
        open(player, player.getUUID());
    }
}
