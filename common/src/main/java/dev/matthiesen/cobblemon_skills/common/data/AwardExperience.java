package dev.matthiesen.cobblemon_skills.common.data;

import dev.matthiesen.cobblemon_skills.common.config.CobblemonSkillsConfig;
import dev.matthiesen.cobblemon_skills.common.runtime.TextUtils;
import dev.matthiesen.matthiesen_core.common.utility.SoundsPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetSubtitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitlesAnimationPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;

import java.util.Map;

public record AwardExperience(ServerPlayer player, Profession profession, double experience) {
    public static Integer handle(AwardExperience event) {
        ServerPlayer player = event.player();
        Profession profession = event.profession();
        double experience = event.experience();

        PlayerProfile profile = SavedPlayerProfessionData.get(player);
        ProfessionProgress progress = profile.getProgress(profession);

        boolean alreadyMaxLevel = progress.level() >= ProfessionProgress.getMaxLevel();
        int levelsGained = progress.addExperience(experience);

        SavedPlayerProfessionData.put(player, profile);
        if (alreadyMaxLevel && levelsGained == 0)
            return 0;

        if (levelsGained > 0) {
            playLevelUpSound(player, progress.level());
            sendLevelUpTitle(player, profession, progress.level());

            Map<String, String> placeholders = Map.of(
                    "%level%", String.valueOf(progress.level()),
                    "%profession%", profession.getConfig().displayName()
            );

            String messageTemplate = CobblemonSkillsConfig.TEXT_CONFIG.reward_levelUp_chatMessage.get();
            Component message = TextUtils.parse(messageTemplate, placeholders);
            player.sendSystemMessage(message);
        }

        sendExperienceProgress(player, profession, experience, progress);
        return levelsGained;
    }

    private static void sendExperienceProgress(ServerPlayer player, Profession profession, double experience, ProfessionProgress progress) {
        int nextLevelExperience = ProfessionProgress.experienceRequiredForNextLevel(progress.level());

        Map<String, String> placeholders = Map.of(
                "%level%", String.valueOf(progress.level()),
                "%profession%", profession.getConfig().displayName(),
                "%experience%", String.format("%.0f", experience),
                "%nextLevelExperience%", nextLevelExperience <= 0
                        ? String.format(CobblemonSkillsConfig.TEXT_CONFIG.reward_experienceProgress_maxed.get(), progress.level())
                        : String.format(CobblemonSkillsConfig.TEXT_CONFIG.reward_experienceProgress_progress.get(), progress.level(), progress.experience(), nextLevelExperience)
        );

        String messageTemplate = CobblemonSkillsConfig.TEXT_CONFIG.reward_experienceProgress_chatMessage.get();
        Component message = TextUtils.parse(messageTemplate, placeholders);
        player.sendSystemMessage(message, true);
    }

    private static void playLevelUpSound(ServerPlayer player, int level) {
        float pitch = Math.min(1.0F, 1.0F + (level - 1) * 0.08F);
        new SoundsPlayer(SoundEvents.PLAYER_LEVELUP).setPitch(pitch).play(player);
    }

    private static void sendLevelUpTitle(ServerPlayer player, Profession profession, int level) {
        Map<String, String> placeholders = Map.of(
                "%level%", String.valueOf(level),
                "%profession%", profession.getConfig().displayName()
        );
        String titleTemplate = CobblemonSkillsConfig.TEXT_CONFIG.reward_levelUp_title.get();
        String subTitleTemplate = CobblemonSkillsConfig.TEXT_CONFIG.reward_levelUp_subTitle.get();
        Component title = TextUtils.parse(titleTemplate, placeholders);
        Component subtitle = TextUtils.parse(subTitleTemplate, placeholders);

        sendClientBoundTitlePackets(player, title, subtitle);
    }

    private static void sendClientBoundTitlePackets(ServerPlayer player, Component title, Component subtitle) {
        player.connection.send(new ClientboundSetTitlesAnimationPacket(10, 50, 15));
        player.connection.send(new ClientboundSetTitleTextPacket(title));
        player.connection.send(new ClientboundSetSubtitleTextPacket(subtitle));
    }
}
