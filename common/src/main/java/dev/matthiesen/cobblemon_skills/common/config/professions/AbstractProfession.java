package dev.matthiesen.cobblemon_skills.common.config.professions;

import com.electronwill.nightconfig.core.Config;
import dev.matthiesen.cobblemon_skills.common.config.def.ProfessionTierEntry;
import dev.matthiesen.cobblemon_skills.common.config.def.RewardsEntry;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;
import java.util.stream.Stream;

public abstract class AbstractProfession {
    public ModConfigSpec.ConfigValue<String> professionName;
    public ModConfigSpec.ConfigValue<List<? extends Config>> professionRewards;

    public abstract List<Config> getDefaultProfessionRewards();

    public static List<Config> getDefaultConfigTiers(String label) {
        return Stream.of(
                        new ProfessionTierEntry("Novice", 10, List.of(
                                new RewardsEntry(20),
                                new RewardsEntry("minecraft:iron_ingot", 3)
                        )),
                        new ProfessionTierEntry("Apprentice", 20, List.of(
                                new RewardsEntry(40),
                                new RewardsEntry("minecraft:gold_ingot", 2)
                        )),
                        new ProfessionTierEntry("Adept", 30, List.of(
                                new RewardsEntry(60),
                                new RewardsEntry("minecraft:diamond", 1)
                        )),
                        new ProfessionTierEntry("Expert", 40, List.of(
                                new RewardsEntry(80),
                                new RewardsEntry("cobblemon:tumblestone", 4)
                        )),
                        new ProfessionTierEntry("Master", 50, List.of(
                                new RewardsEntry(100),
                                new RewardsEntry("cobblemon:sky_tumblestone", 2)
                        )),
                        new ProfessionTierEntry("Grandmaster", 60, List.of(
                                new RewardsEntry(120),
                                new RewardsEntry("cobblemon:black_tumblestone", 3)
                        )),
                        new ProfessionTierEntry("Legendary", 70, List.of(
                                new RewardsEntry(150),
                                new RewardsEntry("cobblemon:fossilized_drake", 1)
                        )),
                        new ProfessionTierEntry("Mythical", 80, List.of(
                                new RewardsEntry(200),
                                new RewardsEntry("cobblemon:old_amber_fossil", 1)
                        )),
                        new ProfessionTierEntry("Divine", 90, List.of(
                                new RewardsEntry(250),
                                new RewardsEntry("cobblemon:relic_coin", 16)
                        )),
                        new ProfessionTierEntry("Transcendent", 100, List.of(
                                new RewardsEntry(500),
                                new RewardsEntry("cobblemon:relic_coin", 32),
                                new RewardsEntry("tellraw @a [\"\",{\"text\":\"{player}\",\"bold\":true,\"color\":\"aqua\"},{\"text\":\" has transcended in " + label + "!\",\"color\":\"gold\"}]")
                        ))
                )
                .map(ProfessionTierEntry::serialize)
                .toList();
    }

    public AbstractProfession(ModConfigSpec.Builder builder, String professionName) {
        this.professionName = builder.comment("Name of the profession")
                .define("professionName", professionName);
        professionRewards = builder.comment("Rewards configuration for the profession")
                .defineListAllowEmpty(
                        List.of("professionRewards"),
                        getDefaultProfessionRewards(),
                        null,
                        o -> o instanceof Config && ProfessionTierEntry.isValid(o)
                );
    }
}
