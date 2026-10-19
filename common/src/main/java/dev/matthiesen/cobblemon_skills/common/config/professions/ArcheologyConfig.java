package dev.matthiesen.cobblemon_skills.common.config.professions;

import com.electronwill.nightconfig.core.Config;
import dev.matthiesen.cobblemon_skills.common.config.def.PROF_DEFAULTS;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

public final class ArcheologyConfig extends AbstractProfession {

    // Experience
    public ModConfigSpec.DoubleValue experience_fossilRevivalBaseXp;
    public ModConfigSpec.DoubleValue experience_fossilRevivalShinyBonusXp;
    public ModConfigSpec.IntValue experience_oreBlockXp;
    public ModConfigSpec.IntValue experience_deepslateOreBlockXp;
    public ModConfigSpec.IntValue experience_miscOreBlockXp;
    public ModConfigSpec.IntValue experience_tumblestoneClusterSmallXp;
    public ModConfigSpec.IntValue experience_tumblestoneClusterMediumXp;
    public ModConfigSpec.IntValue experience_tumblestoneClusterLargeXp;
    public ModConfigSpec.IntValue experience_tumblestoneClusterFullXp;
    public ModConfigSpec.IntValue experience_typeClusterXp;
    public ModConfigSpec.IntValue experience_relicCoinSackXp;
    public ModConfigSpec.IntValue experience_relicCoinPouchXp;
    public ModConfigSpec.IntValue experience_commonItemXp;
    public ModConfigSpec.IntValue experience_uncommonItemXp;
    public ModConfigSpec.IntValue experience_rareItemXp;

    // Rewards
    public ModConfigSpec.BooleanValue rewards_randomExtraDrops;
    public ModConfigSpec.IntValue rewards_randomExtraDropsMinLvl;
    public ModConfigSpec.DoubleValue rewards_randomExtraDropsChance;

    @Override
    public List<Config> getDefaultProfessionRewards() {
        return PROF_DEFAULTS.getDefaultArcheologyTiers();
    }

    public ArcheologyConfig(ModConfigSpec.Builder builder) {
        super(builder, "Archeology");

        builder.comment("Archeology Experience Settings").push("experience");
        experience_fossilRevivalBaseXp = builder.comment("Base experience gained from reviving a fossil")
                .defineInRange("fossilRevivalBaseXp", 500.0, 0.0, Double.MAX_VALUE);
        experience_fossilRevivalShinyBonusXp = builder.comment("Bonus experience gained from reviving a shiny fossil Pokémon")
                .defineInRange("fossilRevivalShinyBonusXp", 400.0, 0.0, Double.MAX_VALUE);
        experience_oreBlockXp = builder.comment("Experience gained from mining an ore block")
                .defineInRange("oreBlockXp", 400, 0, Integer.MAX_VALUE);
        experience_deepslateOreBlockXp = builder.comment("Experience gained from mining a deepslate ore block")
                .defineInRange("deepslateOreBlockXp", 500, 0, Integer.MAX_VALUE);
        experience_miscOreBlockXp = builder.comment("Experience gained from mining a miscellaneous ore block")
                .defineInRange("miscOreBlockXp", 300, 0, Integer.MAX_VALUE);
        experience_tumblestoneClusterSmallXp = builder.comment("Experience gained from mining a small tumblestone cluster")
                .defineInRange("tumblestoneClusterSmallXp", 100, 0, Integer.MAX_VALUE);
        experience_tumblestoneClusterMediumXp = builder.comment("Experience gained from mining a medium tumblestone cluster")
                .defineInRange("tumblestoneClusterMediumXp", 200, 0, Integer.MAX_VALUE);
        experience_tumblestoneClusterLargeXp = builder.comment("Experience gained from mining a large tumblestone cluster")
                .defineInRange("tumblestoneClusterLargeXp", 300, 0, Integer.MAX_VALUE);
        experience_tumblestoneClusterFullXp = builder.comment("Experience gained from mining a full tumblestone cluster")
                .defineInRange("tumblestoneClusterFullXp", 400, 0, Integer.MAX_VALUE);
        experience_typeClusterXp = builder.comment("Experience gained from mining a type cluster")
                .defineInRange("typeClusterXp", 500, 0, Integer.MAX_VALUE);
        experience_relicCoinSackXp = builder.comment("Experience gained from mining a relic coin sack")
                .defineInRange("relicCoinSackXp", 600, 0, Integer.MAX_VALUE);
        experience_relicCoinPouchXp = builder.comment("Experience gained from mining a relic coin pouch")
                .defineInRange("relicCoinPouchXp", 700, 0, Integer.MAX_VALUE);
        experience_commonItemXp = builder.comment("Experience gained from mining a common archeology item")
                .defineInRange("commonItemXp", 200, 0, Integer.MAX_VALUE);
        experience_uncommonItemXp = builder.comment("Experience gained from mining an uncommon archeology item")
                .defineInRange("uncommonItemXp", 400, 0, Integer.MAX_VALUE);
        experience_rareItemXp = builder.comment("Experience gained from mining a rare archeology item")
                .defineInRange("rareItemXp", 600, 0, Integer.MAX_VALUE);
        builder.pop(); // experience

        builder.comment("Archeology Rewards Settings").push("rewards");
        rewards_randomExtraDrops = builder.comment("Whether extra drops are randomly applied to block breaking rewards in archeology profession")
                .define("randomExtraDrops", true);
        rewards_randomExtraDropsMinLvl = builder.comment("The minimum level required for extra drops in archeology profession")
                .defineInRange("randomExtraDropsMinLvl", 10, 0, Integer.MAX_VALUE);
        rewards_randomExtraDropsChance = builder.comment("The chance for extra drops to be applied to block breaking rewards in archeology profession")
                .defineInRange("randomExtraDropsChance", 0.25, 0.0, 1.0);
        builder.pop(); // rewards
    }
}
