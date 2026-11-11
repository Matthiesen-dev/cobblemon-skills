package dev.matthiesen.cobblemon_skills.common.menu.util;

import dev.matthiesen.cobblemon_skills.common.config.CobblemonSkillsConfig;
import dev.matthiesen.cobblemon_skills.common.registry.PermissionsRegistry;
import dev.matthiesen.cobblemon_skills.common.runtime.TextUtils;
import dev.matthiesen.matthiesen_core.common.core.data.ServerUser;
import net.minecraft.server.level.ServerPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class ProfileUtils {
    private static final Map<UUID, String> displayNameCache = new HashMap<>();

    public static boolean checkPermission(ServerPlayer player, UUID targetUuid) {
        if (player.getUUID() != targetUuid && !PermissionsRegistry.checkPermission(player, PermissionsRegistry.GUI_PROFILE_OTHER_PERMISSION)) {
            player.sendSystemMessage(TextUtils.parse(CobblemonSkillsConfig.TEXT_CONFIG.errors_menuNoPermission.get()));
            return false;
        }
        if (!PermissionsRegistry.checkPermission(player, PermissionsRegistry.GUI_PROFILE_PERMISSION)) {
            player.sendSystemMessage(TextUtils.parse(CobblemonSkillsConfig.TEXT_CONFIG.errors_menuNoPermission.get()));
            return false;
        }
        return true;
    }

    public static String getDisplayName(UUID uuid) {
        if (displayNameCache.containsKey(uuid)) {
            return displayNameCache.get(uuid);
        }
        ServerUser user = new ServerUser(uuid);
        var onlinePlayer = user.getOnlinePlayer();
        if (onlinePlayer != null) {
            String displayName = onlinePlayer.getScoreboardName();
            displayNameCache.put(uuid, displayName);
            return displayName;
        }
        String username = user.getUsername();
        displayNameCache.put(uuid, username);
        return username;
    }
}
