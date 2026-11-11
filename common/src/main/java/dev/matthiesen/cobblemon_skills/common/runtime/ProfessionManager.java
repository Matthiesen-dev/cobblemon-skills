package dev.matthiesen.cobblemon_skills.common.runtime;

import com.cobblemon.mod.common.api.battles.model.actor.BattleActor;
import com.cobblemon.mod.common.api.events.battles.BattleVictoryEvent;
import com.cobblemon.mod.common.api.events.berry.BerryHarvestEvent;
import com.cobblemon.mod.common.api.events.farming.ApricornHarvestEvent;
import com.cobblemon.mod.common.api.events.fishing.BobberSpawnPokemonEvent;
import com.cobblemon.mod.common.api.events.item.LeftoversCreatedEvent;
import com.cobblemon.mod.common.api.events.pokeball.PokemonCatchRateEvent;
import com.cobblemon.mod.common.api.events.pokemon.*;
import com.cobblemon.mod.common.api.pokemon.experience.BattleExperienceSource;
import com.cobblemon.mod.common.entity.pokeball.EmptyPokeBallEntity;
import com.cobblemon.mod.common.util.PlayerExtensionsKt;
import dev.matthiesen.cobblemon_skills.common.data.*;
import dev.matthiesen.matthiesen_core.common.api.events.server.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class ProfessionManager {
    private static final Map<UUID, Integer> playerLastFishCaughtStats = new HashMap<>();

    private ProfessionManager() {}

    public static void onServerTick(ServerEvent.EndTick event) {
        for (ServerPlayer player : event.server().getPlayerList().getPlayers()) {
            syncFishingStats(player);
        }
    }

    public static void onShinyCalculation(ShinyChanceCalculationEvent event) {
        event.addModificationFunction(RewardsManager::onShinyCalculationModify);
    }

    private static void syncFishingStats(ServerPlayer player) {
        int currentFishCaught = player.getStats().getValue(Stats.CUSTOM.get(Stats.FISH_CAUGHT));
        Integer lastFishCaught = playerLastFishCaughtStats.put(player.getUUID(), currentFishCaught);
        if (lastFishCaught == null || currentFishCaught <= lastFishCaught) {
            return;
        }
        int diff = currentFishCaught - lastFishCaught;
        Profession.FISHING.awardExperience(player, ExperienceManager.getFishingExperience(diff));
    }

    public static void onBrewingStandTake(Player player, ItemStack itemStack) {
        if (player instanceof ServerPlayer serverPlayer && ExperienceManager.isCobblemonCookingItem(itemStack.getItem())) {
            Profession.COOKING.awardExperience(
                    serverPlayer,
                    ExperienceManager.getCobblemonCookingItemExperience(itemStack.getItem())
            );
            RewardsManager.handleCookingRewards(serverPlayer);
        }
    }

    public static void onFossilRevived(FossilRevivedEvent event) {
        if (event.getPlayer() instanceof ServerPlayer serverPlayer) {
            Profession.ARCHEOLOGY.awardExperience(
                    serverPlayer,
                    ExperienceManager.getFossilRevivalExperience(event.getPokemon())
            );
        }
    }

    public static void onCookingPotTake(Player player, ItemStack itemStack) {
        if (player instanceof ServerPlayer serverPlayer && ExperienceManager.isCobblemonCookingItem(itemStack.getItem())) {
            Profession.COOKING.awardExperience(
                    serverPlayer,
                    ExperienceManager.getCobblemonCookingItemExperience(itemStack.getItem())
            );
            RewardsManager.handleCookingRewards(serverPlayer);
        }
    }

    public static void onSmithingTableTake(Player player, ItemStack itemStack) {
        if (player instanceof ServerPlayer serverPlayer && ExperienceManager.isCobblemonFishingItem(itemStack)) {
            Profession.FISHING.awardExperience(
                    serverPlayer,
                    ExperienceManager.getCobblemonFishingItemExperience(itemStack)
            );
            RewardsManager.handleFishingSmithingTableRewards(serverPlayer, itemStack);
        }
    }

    public static void onFurnaceBlastingSmokingTake(Player player, ItemStack itemStack) {
        if (player instanceof ServerPlayer serverPlayer && ExperienceManager.isCobblemonCookingItem(itemStack.getItem())) {
            Profession.COOKING.awardExperience(
                    serverPlayer,
                    ExperienceManager.getCobblemonCookingItemExperience(itemStack.getItem())
            );
            RewardsManager.handleCookingRewards(serverPlayer);
        }
    }

    public static void onApricornHarvest(ApricornHarvestEvent event) {
        if (event.getPlayer() instanceof ServerPlayer serverPlayer) {
            Profession.BOTANY.awardExperience(
                    serverPlayer,
                    ExperienceManager.getApricornHarvestExperience()
            );
        }
    }

    public static void onBerryHarvest(BerryHarvestEvent event) {
        if (event.getPlayer() instanceof ServerPlayer serverPlayer) {
            Profession.BOTANY.awardExperience(
                    serverPlayer,
                    ExperienceManager.getBerryHarvestExperience()
            );
        }
    }

    public static void onFishingBobberSpawn(BobberSpawnPokemonEvent.Post event) {
        if (event.getBobber().getPlayerOwner() instanceof ServerPlayer serverPlayer) {
            Profession.FISHING.awardExperience(
                    serverPlayer,
                    ExperienceManager.getFishingExperienceFromPokemon(event.getPokemon())
            );
        }
    }

    public static void onLeftoversCreated(LeftoversCreatedEvent event) {
        if (event.getPlayerEntity() instanceof ServerPlayer serverPlayer) {
            Profession.TRAINING.awardExperience(
                    serverPlayer,
                    ExperienceManager.getLeftoversExperience()
            );
        }
    }

    public static void onPokemonCatchRateCalculation(PokemonCatchRateEvent event) {
        if (event.getThrower() instanceof ServerPlayer player) {
            var captureLevel = SavedPlayerProfessionData.get(player).getProgress(Profession.CATCHING).level();
            var currentCatchRate = event.getCatchRate();
            event.setCatchRate(currentCatchRate * RewardsManager.getCaptureRateBonusMultiplier(captureLevel));
        }
    }

    public static void onPokemonCaptured(PokemonCapturedEvent event) {
        boolean isCriticalCapture = event.getPokeBallEntity().getCaptureState() == EmptyPokeBallEntity.CaptureState.CRITICAL;
        Profession.CATCHING.awardExperience(
                event.getPlayer(),
                ExperienceManager.getCaptureSkillExperience(event.getPokemon().getLevel(), isCriticalCapture)
        );
    }

    public static void onCollectEgg(CollectEggEvent event) {
        Profession.BREEDING.awardExperience(
                event.getPlayer(),
                ExperienceManager.getBreedingEggCollected()
        );
    }

    public static void onHatchEggPre(HatchEggEvent.Pre event) {
        var breedingLevel = SavedPlayerProfessionData.get(event.getPlayer()).getProgress(Profession.BREEDING).level();
        int currentFriendShip = event.getEgg().getFriendship() != null ? event.getEgg().getFriendship() : 0;
        var egg = event.getEgg();
        boolean currentShinyStatus = Boolean.TRUE.equals(egg.getShiny());
        egg.setShiny(RewardsManager.shouldMakeEggShiny(breedingLevel, currentShinyStatus));
        egg.setFriendship(currentFriendShip + RewardsManager.getBreedingFriendshipBonus(breedingLevel));
    }

    public static void onHatchEggPost(HatchEggEvent.Post event) {
        Profession.BREEDING.awardExperience(
                event.getPlayer(),
                ExperienceManager.getBreedingEggHatched(event.getPokemon().getSpecies().getEggCycles())
        );
    }

    public static void onExperienceGainedPre(ExperienceGainedEvent.Pre event) {
        if (event.getSource() instanceof BattleExperienceSource) {
            ServerPlayer owner = event.getPokemon().getOwnerPlayer();
            if (owner != null) {
                var trainingLevel = SavedPlayerProfessionData.get(owner).getProgress(Profession.TRAINING).level();
                var bonus = RewardsManager.trainingSkillBonusExperience(trainingLevel, event.getExperience());
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
                Profession.TRAINING.awardExperience(
                        owner,
                        RewardsManager.trainingSkillExperienceFromBattle(event.getExperience())
                );
            }
        }
    }

    public static void onLevelUp(LevelUpEvent event) {
        ServerPlayer owner = event.getPokemon().getOwnerPlayer();
        if (owner != null) {
            var skillExperience = ExperienceManager.getTrainingSkillExperienceFromLevelUp(event.getOldLevel(), event.getNewLevel());
            if (skillExperience > 0.0) {
                Profession.TRAINING.awardExperience(
                        owner,
                        skillExperience
                );
            }
        }
    }

    public static void onBattleVictory(BattleVictoryEvent event) {
        List<UUID> winners = event.getWinners().stream().map(BattleActor::getUuid).toList();
        winners.forEach(uuid -> {
            ServerPlayer player = PlayerExtensionsKt.getPlayer(uuid);
            if (player != null) {
                Profession.TRAINING.awardExperience(
                        player,
                        ExperienceManager.getBattleExperience()
                );
            }
        });
    }

    public static void onBlockBreak(PlayerEvent.BlockBreakEvent event) {
        if (!(event.world() instanceof ServerLevel) || !(event.player() instanceof ServerPlayer serverPlayer)) {
            return;
        }
        if (ExperienceManager.isArcheologyBlock(event.state().getBlock())) {
            Profession.ARCHEOLOGY.awardExperience(
                    serverPlayer,
                    ExperienceManager.getArcheologyBlockExperience(event.state().getBlock())
            );
            RewardsManager.handleBlockBreakRewards(event, Profession.ARCHEOLOGY);
        } else if (ExperienceManager.isBotanyBlock(event.state().getBlock())) {
            Profession.BOTANY.awardExperience(
                    serverPlayer,
                    ExperienceManager.getBotanyBlockExperience(event.state().getBlock())
            );
            RewardsManager.handleBlockBreakRewards(event, Profession.BOTANY);
        }
    }

    public static void onBrushableBlockDrop(ServerPlayer serverPlayer, ItemStack item) {
        if (ExperienceManager.isCobblemonArcheologyItem(item)) {
            Profession.ARCHEOLOGY.awardExperience(
                    serverPlayer,
                    ExperienceManager.getCobblemonArcheologyItemExperience(item)
            );
            RewardsManager.handleArcheologyBrush(serverPlayer, item);
        }
    }
}
