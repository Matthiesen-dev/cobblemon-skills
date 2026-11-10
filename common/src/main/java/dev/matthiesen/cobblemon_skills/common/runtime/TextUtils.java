package dev.matthiesen.cobblemon_skills.common.runtime;

import dev.matthiesen.cobblemon_skills.common.CobblemonSkillsCommon;
import dev.matthiesen.cobblemon_skills.common.config.CobblemonSkillsConfig;
import dev.matthiesen.matthiesen_core.common.api.text_parsers.TextParser;
import net.minecraft.network.chat.Component;

import java.util.Map;

public final class TextUtils {
    private static TextParser getTextParser() {
        return CobblemonSkillsCommon.INSTANCE.getTextParserManager().getTextParser(
                CobblemonSkillsConfig.TEXT_CONFIG.text_parser.get()
        );
    }

    public static Component parse(String text, Map<String, String> placeholders) {
        TextParser parser = getTextParser();
        return parser.parse(text, placeholders);
    }

    public static Component parse(String text) {
        TextParser parser = getTextParser();
        return parser.parse(text);
    }
}
