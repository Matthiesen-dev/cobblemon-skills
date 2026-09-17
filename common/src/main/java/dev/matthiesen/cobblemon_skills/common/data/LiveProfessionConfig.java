package dev.matthiesen.cobblemon_skills.common.data;

import dev.matthiesen.cobblemon_skills.common.config.def.ProfessionTierEntry;
import dev.matthiesen.matthiesen_core.common.utility.item.ItemBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record LiveProfessionConfig(
        Profession profession,
        String displayName,
        List<ProfessionTierEntry> tiers
) {
    public ItemStack getProfessionItem(ServerPlayer player) {
        PlayerProfile profile = SavedPlayerProfessionData.get(player);
        ProfessionProgress progress = profile.getProgress(profession);
        Component[] lore = new Component[]{
                Component.literal("Level: " + progress.level()),
                Component.literal("Experience: " + progress.experience() + "/" + ProfessionProgress.experienceRequiredForNextLevel(progress.level()))
        };

        return new ItemBuilder(Items.BOOK)
                .hideAdditional()
                .setCustomName(Component.literal(displayName))
                .addLore(lore)
                .build();
    }

    public Map<String, ItemStack> getRewardTierItemsMap(ServerPlayer player) {
        PlayerProfile profile = SavedPlayerProfessionData.get(player);
        return tiers.stream()
                .collect(
                        Collectors.toMap(
                                tier -> tier.toId(profession.getNbtTag()),
                                tier -> {
                                    ItemBuilder itemBuilder = new ItemBuilder(Items.NAME_TAG)
                                            .hideAdditional()
                                            .setCustomName(Component.literal(tier.displayName));
                                    boolean redeemed = profile.isRewardRedeemed(profession, tier);
                                    int currentLevel = profile.getProgress(profession).level();
                                    Component[] lore = new Component[]{
                                            Component.literal("Level Requirement: ").withStyle(ChatFormatting.GRAY)
                                                    .append(Component.literal(String.valueOf(tier.levelRequirement)).withStyle(currentLevel >= tier.levelRequirement ? ChatFormatting.GREEN : ChatFormatting.RED)),
                                            Component.literal("Redeemed: ").withStyle(ChatFormatting.GRAY)
                                                    .append(Component.literal(redeemed ? "Yes" : "No").withStyle(redeemed ? ChatFormatting.GREEN : ChatFormatting.RED))
                                    };
                                    itemBuilder.addLore(lore);
                                    return itemBuilder.build();
                                }
                        )
                );
    }
}
