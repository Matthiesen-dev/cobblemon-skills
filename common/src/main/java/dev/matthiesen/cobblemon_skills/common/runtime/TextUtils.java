package dev.matthiesen.cobblemon_skills.common.runtime;

import dev.matthiesen.cobblemon_skills.common.CobblemonSkillsCommon;
import dev.matthiesen.cobblemon_skills.common.config.CobblemonSkillsConfig;
import dev.matthiesen.matthiesen_core.common.api.text_parsers.TextParser;
import net.minecraft.network.chat.Component;

import java.util.List;
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

    public static Component[] parseList(List<? extends String> textList, Map<String, String> placeholders) {
        TextParser parser = getTextParser();
        return textList.stream()
                .map(text -> parser.parse(text, placeholders))
                .toArray(Component[]::new);
    }

    public static Component[] parseList(List<? extends String> textList) {
        TextParser parser = getTextParser();
        return textList.stream()
                .map(parser::parse)
                .toArray(Component[]::new);
    }
}
