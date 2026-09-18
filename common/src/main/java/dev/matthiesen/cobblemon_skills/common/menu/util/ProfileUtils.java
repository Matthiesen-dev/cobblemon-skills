package dev.matthiesen.cobblemon_skills.common.menu.util;

import dev.matthiesen.cobblemon_skills.common.registry.PermissionsRegistry;
import dev.matthiesen.matthiesen_core.common.utility.player_data.ServerUser;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class ProfileUtils {
    private static final Map<UUID, String> displayNameCache = new HashMap<>();

    public static boolean checkPermission(ServerPlayer player, UUID targetUuid) {
        if (player.getUUID() != targetUuid && !PermissionsRegistry.checkPermission(player, PermissionsRegistry.GUI_PROFILE_OTHER_PERMISSION)) {
            player.sendSystemMessage(Component.literal("You do not have permission to access other's profile."));
            return false;
        }
        if (!PermissionsRegistry.checkPermission(player, PermissionsRegistry.GUI_PROFILE_PERMISSION)) {
            player.sendSystemMessage(Component.literal("You do not have permission to access your profile."));
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
