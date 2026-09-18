package dev.matthiesen.cobblemon_skills.common.runtime;

import dev.matthiesen.cobblemon_skills.common.data.PlayerProfile;
import dev.matthiesen.cobblemon_skills.common.data.Profession;
import dev.matthiesen.cobblemon_skills.common.data.ProfessionProgress;
import dev.matthiesen.cobblemon_skills.common.data.SavedPlayerProfessionData;
import dev.matthiesen.matthiesen_core.common.utility.SoundsPlayer;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetSubtitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitlesAnimationPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;

public final class AwardManager {
    public static int awardProfessionExperience(ServerPlayer player, Profession profession, double experience) {
        PlayerProfile profile = SavedPlayerProfessionData.get(player);
        ProfessionProgress progress = profile.getProgress(profession);
        boolean alreadyMaxLevel = progress.level() >= ProfessionProgress.getMaxLevel();
        int levelsGained = progress.addExperience(experience);
        SavedPlayerProfessionData.put(player, profile);
        if (alreadyMaxLevel && levelsGained == 0) {
            return 0;
        }
        sendExperienceProgress(player, profession, experience, progress);
        if (levelsGained > 0) {
            playLevelUpSound(player, levelsGained);
            sendLevelUpTitle(player, profession, progress.level());
            player.sendSystemMessage(Component.literal("Congratulations! Your " + profession.getConfig().displayName() + " profession has leveled up to level " + progress.level() + "!"));
        }
        return levelsGained;
    }

    private static void sendLevelUpTitle(ServerPlayer player, Profession profession, int level) {
        Component title = Component.literal("Leveled Up!").withStyle(ChatFormatting.GOLD, ChatFormatting.BOLD);
        Component subtitle = Component.literal("Your " + profession.getConfig().displayName() + " profession is now level " + level + "!").withStyle(ChatFormatting.YELLOW);
        player.connection.send(new ClientboundSetTitlesAnimationPacket(10, 50, 15));
        player.connection.send(new ClientboundSetTitleTextPacket(title));
        player.connection.send(new ClientboundSetSubtitleTextPacket(subtitle));
    }

    private static void sendExperienceProgress(ServerPlayer player, Profession profession, double experience, ProfessionProgress progress) {
        int nextLevelExperience = ProfessionProgress.experienceRequiredForNextLevel(progress.level());
        String message = String.format(
                "%s +%.0f XP | %s",
                profession.getConfig().displayName(),
                experience,
                nextLevelExperience <= 0
                        ? "Lvl " + progress.level() + " (Max)"
                        : String.format("Lvl %d | %.0f/%d", progress.level(), progress.experience(), nextLevelExperience)
        );
        player.sendSystemMessage(Component.literal(message), true);
    }

    private static void playLevelUpSound(ServerPlayer player, int level) {
        float pitch = Math.min(1.0F, 1.0F + (level - 1) * 0.08F);
        new SoundsPlayer(SoundEvents.PLAYER_LEVELUP).setPitch(pitch).play(player);
    }
}
