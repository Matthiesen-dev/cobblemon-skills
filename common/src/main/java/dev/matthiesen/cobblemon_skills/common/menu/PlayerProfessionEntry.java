package dev.matthiesen.cobblemon_skills.common.menu;

import dev.matthiesen.cobblemon_skills.common.CobblemonSkillsCommon;
import dev.matthiesen.cobblemon_skills.common.config.CobblemonSkillsConfig;
import dev.matthiesen.cobblemon_skills.common.config.def.ProfessionTierEntry;
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
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class PlayerProfessionEntry {
    private final ServerPlayer viewer;
    private final UUID targetUuid;
    private final Profession profession;

    public PlayerProfessionEntry(ServerPlayer viewer, UUID targetUuid, Profession profession) {
        this.viewer = viewer;
        this.targetUuid = targetUuid;
        this.profession = profession;
    }

    public Page getPage() {
        PlaceholderButton placeholder = new PlaceholderButton();
        dev.matthiesen.cobblemon_skills.common.data.PlayerProfile profile = SavedPlayerProfessionData.get(targetUuid);
        var config = profession.getConfig();
        var progress = profile.getProgress(profession);
        boolean isViewingOwnProfile = viewer.getUUID().equals(targetUuid);

        List<Button> tierButtons = new ArrayList<>();
        for (ProfessionTierEntry tier : config.tiers()) {
            boolean redeemed = profile.isRewardRedeemed(profession, tier);
            boolean unlocked = progress.level() >= tier.levelRequirement;

            ItemBuilder builder = new ItemBuilder(unlocked ? Items.EMERALD : Items.BARRIER)
                    .hideAdditional()
                    .setCustomName(Component.literal(tier.displayName).withStyle(unlocked ? ChatFormatting.GREEN : ChatFormatting.GRAY));

            Map<String, String> tierPlaceholders = Map.of(
                    "%profession%", config.displayName(),
                    "%tier%", tier.displayName,
                    "%levelRequirement%", String.valueOf(tier.levelRequirement),
                    "%status%", redeemed
                            ? CobblemonSkillsConfig.TEXT_CONFIG.gui_professionEntry_status_redeemed.get()
                            : unlocked
                                ? CobblemonSkillsConfig.TEXT_CONFIG.gui_professionEntry_status_ready.get()
                                : CobblemonSkillsConfig.TEXT_CONFIG.gui_professionEntry_status_locked.get(),
                    "%rewardCount%", String.valueOf(tier.rewards.size())
            );

            if (isViewingOwnProfile) {
                builder.addLore(TextUtils.parseList(CobblemonSkillsConfig.TEXT_CONFIG.gui_professionEntry_viewingOwnProfileLore.get(), tierPlaceholders));
            } else {
                builder.addLore(TextUtils.parseList(CobblemonSkillsConfig.TEXT_CONFIG.gui_professionEntry_viewingOtherProfileLore.get(), tierPlaceholders));
            }

            ServerButton.Builder buttonBuilder = ServerButton.builder()
                    .display(builder.build());

            if (isViewingOwnProfile) {
                buttonBuilder.onClick((action) -> {
                    Map<String, String> placeholders = Map.of(
                            "%profession%", config.displayName(),
                            "%tier%", tier.displayName
                    );
                    if (unlocked && !redeemed) {
                        ServerPlayer sender = action.getPlayer();
                        try {
                            if (profile.redeemTierReward(sender, profession, tier)) {
                                String messageTemplate = CobblemonSkillsConfig.TEXT_CONFIG.gui_messages_rewardRedeemed.get();
                                sender.sendSystemMessage(TextUtils.parse(messageTemplate, placeholders));
                                open(sender, profession); // Refresh the page to reflect the redeemed status
                            } else {
                                sender.sendSystemMessage(TextUtils.parse(CobblemonSkillsConfig.TEXT_CONFIG.errors_rewardAlreadyRedeemed.get()));
                            }
                        } catch (RuntimeException e) {
                            CobblemonSkillsCommon.INSTANCE.createErrorLog("Failed to redeem rewards for " + tier.displayName + " in " + config.displayName() + " for player " + sender.getScoreboardName(), e);
                            String messageTemplate = CobblemonSkillsConfig.TEXT_CONFIG.errors_rewardRedeemFailed.get();
                            sender.sendSystemMessage(TextUtils.parse(messageTemplate, placeholders));
                        }
                    } else if (!unlocked) {
                        ServerPlayer sender = action.getPlayer();
                        String messageTemplate = CobblemonSkillsConfig.TEXT_CONFIG.gui_messages_rewardLocked.get();
                        sender.sendSystemMessage(TextUtils.parse(messageTemplate, placeholders));
                    } else {
                        ServerPlayer sender = action.getPlayer();
                        sender.sendSystemMessage(TextUtils.parse(CobblemonSkillsConfig.TEXT_CONFIG.errors_rewardAlreadyRedeemed.get()));
                    }
                });
            }

            Button button = buttonBuilder.build();
            tierButtons.add(button);
        }

        if (tierButtons.isEmpty()) {
            tierButtons.add(ServerButton.builder()
                    .display(new ItemBuilder(Items.BARRIER)
                            .hideAdditional()
                            .setCustomName(TextUtils.parse(CobblemonSkillsConfig.TEXT_CONFIG.gui_professionEntry_noRewardsConfigured.get()))
                            .addLore(TextUtils.parseList(CobblemonSkillsConfig.TEXT_CONFIG.gui_professionEntry_noRewardsConfiguredLore.get()))
                            .build())
                    .build());
        }

        Button frame = ServerButton.builder()
                .display(new ItemBuilder(Items.GRAY_STAINED_GLASS_PANE)
                        .hideAdditional()
                        .setCustomName(Component.literal(" "))
                        .build())
                .build();

        Button profileButton = ServerButton.builder()
                .display(new ItemBuilder(Items.OAK_DOOR)
                        .hideAdditional()
                        .setCustomName(TextUtils.parse(CobblemonSkillsConfig.TEXT_CONFIG.gui_buttons_backButton.get()))
                        .build())
                .onClick((action) -> PlayerProfile.open(viewer, targetUuid))
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
                .set(49, profileButton)
                .set(53, next)
                .fill(frame)
                .build();

        LinkedPage page = PaginationHelper.createPagesFromPlaceholders(template, tierButtons, null);

        Map<String, String> placeholders = Map.of(
                "%player%", ProfileUtils.getDisplayName(targetUuid),
                "%profession%", config.displayName(),
                "%level%", String.valueOf(progress.level())
        );

        page.setTitle(TextUtils.parse(CobblemonSkillsConfig.TEXT_CONFIG.gui_professionEntry_title.get(), placeholders));
        return page;
    }

    public static void open(ServerPlayer player, UUID targetUuid, Profession profession) {
        if (!ProfileUtils.checkPermission(player, targetUuid)) {
            return;
        }
        ServerUIManager.openUIForcefully(player, new PlayerProfessionEntry(player, targetUuid, profession).getPage());
    }

    public static void open(ServerPlayer player, Profession profession) {
        open(player, player.getUUID(), profession);
    }
}
