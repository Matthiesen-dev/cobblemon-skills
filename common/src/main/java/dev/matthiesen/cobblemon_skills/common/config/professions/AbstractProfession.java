package dev.matthiesen.cobblemon_skills.common.config.professions;

import com.electronwill.nightconfig.core.Config;
import dev.matthiesen.cobblemon_skills.common.config.def.ProfessionTierEntry;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

public abstract class AbstractProfession {
    public ModConfigSpec.ConfigValue<String> professionName;
    public ModConfigSpec.ConfigValue<List<? extends Config>> professionRewards;

    public abstract List<Config> getDefaultProfessionRewards();

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
