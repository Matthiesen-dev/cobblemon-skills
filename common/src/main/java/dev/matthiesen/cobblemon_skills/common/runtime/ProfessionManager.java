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
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.cobblemon.mod.common.util.PlayerExtensionsKt;
import dev.matthiesen.cobblemon_skills.common.data.SavedPlayerProfessionData;
import dev.matthiesen.cobblemon_skills.common.data.Profession;
import dev.matthiesen.cobblemon_skills.common.platform.BlockBreakEvent;
import dev.matthiesen.matthiesen_core.common.api.events.server.ServerEvent;
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
        event.addModificationFunction(ProfessionManager::onShinyCalculationModify);
    }

    private static Float onShinyCalculationModify(Float rate, ServerPlayer player, Pokemon pokemon) {
        var catchingLevel = SavedPlayerProfessionData.get(player).getProgress(Profession.CATCHING).level();
        float maxMultiplier = 10.0F; // Maximum multiplier for the catch rate bonus
        float newCatchMultiplier = Math.min(0.01F * catchingLevel, maxMultiplier); // Ensure the multiplier doesn't exceed the maximum
        return Math.max(rate / newCatchMultiplier, 1);
    }

    private static void syncFishingStats(ServerPlayer player) {
        int currentFishCaught = player.getStats().getValue(Stats.CUSTOM.get(Stats.FISH_CAUGHT));
        Integer lastFishCaught = playerLastFishCaughtStats.put(player.getUUID(), currentFishCaught);
        if (lastFishCaught == null || currentFishCaught <= lastFishCaught) {
            return;
        }

        int diff = currentFishCaught - lastFishCaught;
        AwardManager.awardProfessionExperience(
                player,
                Profession.FISHING,
                ExperienceManager.getFishingExperience(diff)
        );
    }

    public static void onBrewingStandTake(Player player, ItemStack itemStack) {
        if (player instanceof ServerPlayer serverPlayer && ExperienceManager.isCobblemonCookingItem(itemStack.getItem())) {
            AwardManager.awardProfessionExperience(
                    serverPlayer,
                    Profession.COOKING,
                    ExperienceManager.getCobblemonCookingItemExperience(itemStack.getItem())
            );
        }
    }

    public static void onFossilRevived(FossilRevivedEvent event) {
        if (event.getPlayer() instanceof ServerPlayer serverPlayer) {
            AwardManager.awardProfessionExperience(
                    serverPlayer,
                    Profession.ARCHEOLOGY,
                    ExperienceManager.getFossilRevivalExperience(event.getPokemon())
            );
        }
    }

    public static void onCookingPotTake(Player player, ItemStack itemStack) {
        if (player instanceof ServerPlayer serverPlayer && ExperienceManager.isCobblemonCookingItem(itemStack.getItem())) {
            AwardManager.awardProfessionExperience(
                    serverPlayer,
                    Profession.COOKING,
                    ExperienceManager.getCobblemonCookingItemExperience(itemStack.getItem())
            );
        }
    }

    public static void onFurnaceBlastingSmokingTake(Player player, ItemStack itemStack) {
        if (player instanceof ServerPlayer serverPlayer && ExperienceManager.isCobblemonCookingItem(itemStack.getItem())) {
            AwardManager.awardProfessionExperience(
                    serverPlayer,
                    Profession.COOKING,
                    ExperienceManager.getCobblemonCookingItemExperience(itemStack.getItem())
            );
        }
    }

    public static void onApricornHarvest(ApricornHarvestEvent event) {
        if (event.getPlayer() instanceof ServerPlayer serverPlayer) {
            AwardManager.awardProfessionExperience(
                    serverPlayer,
                    Profession.BOTANY,
                    ExperienceManager.getApricornHarvestExperience()
            );
        }
    }

    public static void onBerryHarvest(BerryHarvestEvent event) {
        if (event.getPlayer() instanceof ServerPlayer serverPlayer) {
            AwardManager.awardProfessionExperience(
                    serverPlayer,
                    Profession.BOTANY,
                    ExperienceManager.getBerryHarvestExperience()
            );
        }
    }

    public static void onFishingBobberSpawn(BobberSpawnPokemonEvent.Post event) {
        if (event.getBobber().getPlayerOwner() instanceof ServerPlayer serverPlayer) {
            AwardManager.awardProfessionExperience(
                    serverPlayer,
                    Profession.FISHING,
                    ExperienceManager.getFishingExperienceFromPokemon(event.getPokemon())
            );
        }
    }

    public static void onLeftoversCreated(LeftoversCreatedEvent event) {
        if (event.getPlayerEntity() instanceof ServerPlayer serverPlayer) {
            AwardManager.awardProfessionExperience(
                    serverPlayer,
                    Profession.TRAINING,
                    ExperienceManager.getLeftoversExperience()
            );
        }
    }

    public static void onPokemonCatchRateCalculation(PokemonCatchRateEvent event) {
        if (event.getThrower() instanceof ServerPlayer player) {
            var captureLevel = SavedPlayerProfessionData.get(player).getProgress(Profession.CATCHING).level();
            var currentCatchRate = event.getCatchRate();
            event.setCatchRate(currentCatchRate * ExperienceManager.getCaptureRateBonusMultiplier(captureLevel));
        }
    }

    public static void onPokemonCaptured(PokemonCapturedEvent event) {
        boolean isCriticalCapture = event.getPokeBallEntity().getCaptureState() == EmptyPokeBallEntity.CaptureState.CRITICAL;
        AwardManager.awardProfessionExperience(
                event.getPlayer(),
                Profession.CATCHING,
                ExperienceManager.getCaptureSkillExperience(event.getPokemon().getLevel(), isCriticalCapture)
        );
    }

    public static void onCollectEgg(CollectEggEvent event) {
        AwardManager.awardProfessionExperience(
                event.getPlayer(),
                Profession.BREEDING,
                ExperienceManager.getBreedingEggCollected()
        );
    }

    public static void onHatchEggPre(HatchEggEvent.Pre event) {
        var breedingLevel = SavedPlayerProfessionData.get(event.getPlayer()).getProgress(Profession.BREEDING).level();
        int currentFriendShip = event.getEgg().getFriendship() != null ? event.getEgg().getFriendship() : 0;
        var egg = event.getEgg();
        boolean currentShinyStatus = Boolean.TRUE.equals(egg.getShiny());
        egg.setShiny(shouldMakeEggShiny(breedingLevel, currentShinyStatus));
        egg.setFriendship(currentFriendShip + ExperienceManager.getBreedingFriendshipBonus(breedingLevel));
    }

    private static boolean shouldMakeEggShiny(int breedingLevel, boolean currentShinyStatus) {
        if (currentShinyStatus) {
            return true; // If the egg is already shiny, keep it shiny.
        }

        float baseShinyChance = 0.01f; // Base shiny chance (1%)
        float breedingBonus = breedingLevel * 0.001f; // Each breeding level adds 0.1% to the shiny chance
        float totalShinyChance = baseShinyChance + breedingBonus;

        // Ensure the shiny chance does not exceed a certain cap (e.g., 10%)
        totalShinyChance = Math.min(totalShinyChance, 0.10f);

        return Math.random() < totalShinyChance;
    }

    public static void onHatchEggPost(HatchEggEvent.Post event) {
        AwardManager.awardProfessionExperience(
                event.getPlayer(),
                Profession.BREEDING,
                ExperienceManager.getBreedingEggHatched(event.getPokemon().getSpecies().getEggCycles())
        );
    }

    public static void onExperienceGainedPre(ExperienceGainedEvent.Pre event) {
        if (event.getSource() instanceof BattleExperienceSource) {
            ServerPlayer owner = event.getPokemon().getOwnerPlayer();
            if (owner != null) {
                var trainingLevel = SavedPlayerProfessionData.get(owner).getProgress(Profession.TRAINING).level();
                var bonus = ExperienceManager.trainingSkillBonusExperience(trainingLevel, event.getExperience());
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
                AwardManager.awardProfessionExperience(
                        owner,
                        Profession.TRAINING,
                        ExperienceManager.trainingSkillExperienceFromBattle(event.getExperience())
                );
            }
        }
    }

    public static void onLevelUp(LevelUpEvent event) {
        ServerPlayer owner = event.getPokemon().getOwnerPlayer();
        if (owner != null) {
            var skillExperience = ExperienceManager.getTrainingSkillExperienceFromLevelUp(event.getOldLevel(), event.getNewLevel());
            if (skillExperience > 0.0) {
                AwardManager.awardProfessionExperience(
                        owner,
                        Profession.TRAINING,
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
                AwardManager.awardProfessionExperience(
                        player,
                        Profession.TRAINING,
                        ExperienceManager.getBattleExperience()
                );
            }
        });
    }

    public static void onBlockBreak(BlockBreakEvent event) {
        if (!(event.world() instanceof ServerLevel) || !(event.player() instanceof ServerPlayer serverPlayer)) {
            return;
        }

        if (ExperienceManager.isArcheologyBlock(event.state().getBlock())) {
            AwardManager.awardProfessionExperience(
                    serverPlayer,
                    Profession.ARCHEOLOGY,
                    ExperienceManager.getArcheologyBlockExperience(event.state().getBlock())
            );
        } else if (ExperienceManager.isBotanyBlock(event.state().getBlock())) {
            AwardManager.awardProfessionExperience(
                    serverPlayer,
                    Profession.BOTANY,
                    ExperienceManager.getBotanyBlockExperience(event.state().getBlock())
            );
        }
    }

    public static void onBrushableBlockDrop(ServerPlayer serverPlayer, ItemStack item) {
        if (ExperienceManager.isCobblemonArcheologyItem(item)) {
            AwardManager.awardProfessionExperience(
                    serverPlayer,
                    Profession.ARCHEOLOGY,
                    ExperienceManager.getCobblemonArcheologyItemExperience(item)
            );
        }
    }
}
