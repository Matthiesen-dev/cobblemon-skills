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
import dev.matthiesen.cobblemon_skills.common.config.def.ProfessionTierEntry;
import dev.matthiesen.cobblemon_skills.common.data.Profession;
import dev.matthiesen.cobblemon_skills.common.data.SavedPlayerProfessionData;
import dev.matthiesen.matthiesen_core.common.utility.item.ItemBuilder;
import dev.matthiesen.matthiesen_core.common.utility.player_data.ServerUser;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;
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

            String statusString = "Status: " + (redeemed ? "Redeemed" : unlocked ? "Ready" : "Locked");
            ChatFormatting statusFormatting = redeemed ? ChatFormatting.GOLD : unlocked ? ChatFormatting.GREEN : ChatFormatting.RED;
            if (isViewingOwnProfile) {
                builder.addLore(new Component[]{
                        Component.literal("Requirement: " + tier.levelRequirement).withStyle(ChatFormatting.GRAY),
                        Component.literal(statusString).withStyle(statusFormatting),
                        Component.literal("Rewards: " + tier.rewards.size() + " queued").withStyle(ChatFormatting.DARK_GRAY)
                });
            } else {
                builder.addLore(new Component[]{
                        Component.literal("Requirement: " + tier.levelRequirement).withStyle(ChatFormatting.GRAY),
                        Component.literal(statusString).withStyle(statusFormatting)
                });
            }

            GooeyButton.Builder buttonBuilder = GooeyButton.builder()
                    .display(builder.build());

            if (isViewingOwnProfile) {
                buttonBuilder.onClick((action) -> {
                    if (unlocked && !redeemed) {
                        ServerPlayer sender = action.getPlayer();
                        try {
                            tier.redeemRewards(sender);
                            sender.sendSystemMessage(Component.literal("You have successfully redeemed the rewards for " + tier.displayName + " in " + config.displayName() + "."));
                        } catch (RuntimeException e) {
                            sender.sendSystemMessage(Component.literal("Failed to redeem the rewards for " + tier.displayName + " in " + config.displayName() + "."));
                        }
                    } else if (!unlocked) {
                        ServerPlayer sender = action.getPlayer();
                        sender.sendSystemMessage(Component.literal("You have not unlocked this reward yet. Level up your " + config.displayName() + " profession to unlock it."));
                    } else {
                        ServerPlayer sender = action.getPlayer();
                        sender.sendSystemMessage(Component.literal("You have already redeemed this reward."));
                    }
                });
            }

            Button button = buttonBuilder.build();
            tierButtons.add(button);
        }

        if (tierButtons.isEmpty()) {
            tierButtons.add(GooeyButton.builder()
                    .display(new ItemBuilder(Items.BARRIER)
                            .hideAdditional()
                            .setCustomName(Component.literal("No rewards configured"))
                            .addLore(new Component[]{Component.literal("Add tiers in the config to populate this page.")})
                            .build())
                    .build());
        }

        Button frame = GooeyButton.builder()
                .display(new ItemBuilder(Items.GRAY_STAINED_GLASS_PANE).hideAdditional().setCustomName(Component.literal(" ")).build())
                .build();

        Button profileButton = GooeyButton.builder()
                .display(new ItemBuilder(Items.OAK_DOOR)
                        .hideAdditional()
                        .setCustomName(Component.literal("Back to Profile"))
                        .build())
                .onClick((action) -> PlayerProfile.open(viewer, targetUuid))
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
                .set(49, profileButton)
                .set(53, next)
                .fill(frame)
                .build();

        LinkedPage page = PaginationHelper.createPagesFromPlaceholders(template, tierButtons, null);
        page.setTitle(Component.literal(resolveTargetName() + " - " + config.displayName() + " | Lvl " + progress.level()));
        return page;
    }

    private String resolveTargetName() {
        ServerUser user = new ServerUser(targetUuid);
        var onlinePlayer = user.getOnlinePlayer();
        if (onlinePlayer != null) {
            return onlinePlayer.getScoreboardName();
        }
        return user.getUsername();
    }

    public static void open(ServerPlayer player, UUID targetUuid, Profession profession) {
        UIManager.openUIForcefully(player, new PlayerProfessionEntry(player, targetUuid, profession).getPage());
    }

    public static void open(ServerPlayer player, Profession profession) {
        open(player, player.getUUID(), profession);
    }
}
