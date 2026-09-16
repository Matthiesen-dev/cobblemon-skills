package dev.matthiesen.cobblemon_skills.common.runtime;

import com.cobblemon.mod.common.api.battles.model.actor.BattleActor;
import com.cobblemon.mod.common.api.events.battles.BattleVictoryEvent;
import com.cobblemon.mod.common.api.events.pokeball.PokemonCatchRateEvent;
import com.cobblemon.mod.common.api.events.pokemon.*;
import com.cobblemon.mod.common.api.pokemon.experience.BattleExperienceSource;
import com.cobblemon.mod.common.entity.pokeball.EmptyPokeBallEntity;
import com.cobblemon.mod.common.util.PlayerExtensionsKt;
import dev.matthiesen.cobblemon_skills.common.data.SavedPlayerProfessionData;
import dev.matthiesen.cobblemon_skills.common.data.PlayerProfile;
import dev.matthiesen.cobblemon_skills.common.data.Profession;
import dev.matthiesen.cobblemon_skills.common.data.ProfessionProgress;
import dev.matthiesen.matthiesen_core.common.api.events.server.ServerEvent;
import dev.matthiesen.matthiesen_core.common.utility.SoundsPlayer;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetSubtitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitlesAnimationPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;

import java.util.List;
import java.util.UUID;

public final class ProfessionManager {
    private static final ProfessionManager INSTANCE = new ProfessionManager();

    private ProfessionManager() {}

    public static void onServerTick(ServerEvent.EndTick event) {
        // TODO: Implement any periodic updates or checks related to professions here, if needed in the future.
    }

    public static void onPokemonCatchRateCalculation(PokemonCatchRateEvent event) {
        if (event.getThrower() instanceof ServerPlayer player) {
            var captureLevel = INSTANCE.getPlayerProfile(player).getProgress(Profession.CATCHING).level();
            var currentCatchRate = event.getCatchRate();
            event.setCatchRate(currentCatchRate * INSTANCE.getCaptureRateBonusMultiplier(captureLevel));
        }
    }

    public static void onPokemonCaptured(PokemonCapturedEvent event) {
        boolean isCriticalCapture = event.getPokeBallEntity().getCaptureState() == EmptyPokeBallEntity.CaptureState.CRITICAL;
        INSTANCE.awardProfessionExperience(
                event.getPlayer(),
                Profession.CATCHING,
                INSTANCE.getCaptureSkillExperience(event.getPokemon().getLevel(), isCriticalCapture)
        );
    }

    public static void onCollectEgg(CollectEggEvent event) {
        INSTANCE.awardProfessionExperience(
                event.getPlayer(),
                Profession.BREEDING,
                INSTANCE.getBreedingEggCollected()
        );
    }

    public static void onHatchEggPre(HatchEggEvent.Pre event) {
        var breedingLevel = INSTANCE.getPlayerProfile(event.getPlayer()).getProgress(Profession.BREEDING).level();
        int currentFriendShip = event.getEgg().getFriendship() != null ? event.getEgg().getFriendship() : 0;
        var egg = event.getEgg();
        egg.setFriendship(currentFriendShip + INSTANCE.getBreedingFriendshipBonus(breedingLevel));
    }

    public static void onHatchEggPost(HatchEggEvent.Post event) {
        INSTANCE.awardProfessionExperience(
                event.getPlayer(),
                Profession.BREEDING,
                INSTANCE.getBreedingEggHatched(event.getPokemon().getSpecies().getEggCycles())
        );
    }

    public static void onExperienceGainedPre(ExperienceGainedEvent.Pre event) {
        if (event.getSource() instanceof BattleExperienceSource) {
            ServerPlayer owner = event.getPokemon().getOwnerPlayer();
            if (owner != null) {
                var trainingLevel = INSTANCE.getPlayerProfile(owner).getProgress(Profession.TRAINING).level();
                var bonus = INSTANCE.trainingSkillBonusExperience(trainingLevel, event.getExperience());
                if (bonus > 0) {
                    event.setExperience(event.getExperience() + bonus);
                }
            }
        }
    }

    public static void onExperienceGainedPost(ExperienceGainedEvent.Post event) {
        if (event.getSource() instanceof BattleExperienceSource) {
            ServerPlayer owner = event.getPokemon().getOwnerPlayer();
            if (owner != null) {
                INSTANCE.awardProfessionExperience(
                        owner,
                        Profession.TRAINING,
                        INSTANCE.trainingSkillExperienceFromBattle(event.getExperience())
                );
            }
        }
    }

    public static void onLevelUp(LevelUpEvent event) {
        ServerPlayer owner = event.getPokemon().getOwnerPlayer();
        var skillExperience = INSTANCE.getTrainingSkillExperienceFromLevelUp(event.getOldLevel(), event.getNewLevel());
        if (skillExperience > 0.0) {
            INSTANCE.awardProfessionExperience(
                    owner,
                    Profession.TRAINING,
                    skillExperience
            );
        }
    }

    public static void onBattleVictory(BattleVictoryEvent event) {
        List<UUID> winners = event.getWinners().stream().map(BattleActor::getUuid).toList();
        winners.forEach(uuid -> {
            ServerPlayer player = PlayerExtensionsKt.getPlayer(uuid);
            if (player != null) {
                INSTANCE.awardProfessionExperience(
                        player,
                        Profession.TRAINING,
                        300.0
                );
            }
        });
    }

    public void awardProfessionExperience(ServerPlayer player, Profession profession, double experience) {
        PlayerProfile profile = getPlayerProfile(player);
        ProfessionProgress progress = profile.getProgress(profession);
        boolean alreadyMaxLevel = progress.level() >= ProfessionProgress.MAX_LEVEL;

        int levelsGained = progress.addExperience(experience);
        save(player, profile);

        if (alreadyMaxLevel && levelsGained == 0) {
            return;
        }

        sendExperienceProgress(player, profession, experience, progress);

        if (levelsGained > 0) {
            playLevelUpSound(player, levelsGained);
            sendLevelUpTitle(player, profession, progress.level());
            player.sendSystemMessage(Component.literal("Congratulations! Your " + profession.getLabel() + " profession has leveled up to level " + progress.level() + "!"));
        }
    }

    private void sendLevelUpTitle(ServerPlayer player, Profession profession, int level) {
        Component title = Component.literal("Leveled Up!").withStyle(ChatFormatting.GOLD, ChatFormatting.BOLD);
        Component subtitle = Component.literal("Your " + profession.getLabel() + " profession is now level " + level + "!").withStyle(ChatFormatting.YELLOW);

        player.connection.send(new ClientboundSetTitlesAnimationPacket(10, 50, 15));
        player.connection.send(new ClientboundSetTitleTextPacket(title));
        player.connection.send(new ClientboundSetSubtitleTextPacket(subtitle));
    }

    private void sendExperienceProgress(ServerPlayer player, Profession profession, double experience, ProfessionProgress progress) {
        int nextLevelExperience = ProfessionProgress.experienceRequiredForNextLevel(progress.level());
        String message = String.format(
                "%s +%.0f XP | %s",
                profession.getLabel(),
                experience,
                nextLevelExperience <= 0
                        ? "Lvl " + progress.level() + " (Max)"
                        : String.format("Lvl %d | %.0f/%d", progress.level(), progress.experience(), nextLevelExperience)
        );
        player.sendSystemMessage(Component.literal(message), true);
    }

    private void playLevelUpSound(ServerPlayer player, int level) {
        float pitch = Math.min(1.0F, 1.0F + (level - 1) * 0.08F);
        new SoundsPlayer(SoundEvents.PLAYER_LEVELUP).setPitch(pitch).play(player);
    }

    public PlayerProfile getPlayerProfile(ServerPlayer player) {
        return SavedPlayerProfessionData.get(player.getUUID());
    }

    public void save(ServerPlayer player, PlayerProfile profile) {
        SavedPlayerProfessionData.put(player.getUUID(), profile);
    }

    public double captureCatchRateBonusPercent(int level) {
        return Math.min(10.0, level * 0.10);
    }

    public float getCaptureRateBonusMultiplier(int level) {
        return (float) (1.0 + (captureCatchRateBonusPercent(level) / 100.0));
    }

    public double getCaptureSkillExperience(int pokemonLevel, boolean criticalCapture) {
        double base = 200.0 + Math.max(1, pokemonLevel) * 8.0;
        return criticalCapture ? base + 150.0 : base;
    }

    private double getBreedingEggCollected() {
        return 250.0; // Base XP for collecting an egg
    }

    private int getBreedingFriendshipBonus(int breedingLevel) {
        return Math.min(20, breedingLevel / 5); // Base friendship bonus for hatching an egg, increases with breeding level
    }

    private double getBreedingEggHatched(int eggCycles) {
        return 200.0 + Math.max(0, eggCycles) * 4.0; // Base XP for hatching an egg plus bonus based on egg cycles
    }

    private double getTrainingSkillExperienceFromLevelUp(int oldLevel, int newLevel) {
        return Math.max(0, newLevel - oldLevel) * 300.0;
    }

    private double trainingSkillExperienceFromBattle(double battleExperience) {
        if (battleExperience <= 0) {
            return 0.0;
        }
        return Math.max(20.0, battleExperience * 0.20);
    }

    public double trainingBattleExperienceBonusPercent(int level) {
        return Math.min(25.0, level * 0.25);
    }

    private int trainingSkillBonusExperience(int level, int baseExperience) {
        return (int) Math.floor(baseExperience * (trainingBattleExperienceBonusPercent(level) / 100.0));
    }
}
