package dev.matthiesen.cobblemon_skills.common.data;

import dev.matthiesen.cobblemon_skills.common.CobblemonSkillsCommon;
import dev.matthiesen.cobblemon_skills.common.interfaces.PlayerProfile;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public final class SavedPlayerProfessionData extends SavedData {
    private static final String DATA_KEY = CobblemonSkillsCommon.MOD_ID + "_player_data";
    private static final String NBT_PLAYER_PROFILES = "playerProfiles";
    private static final String NBT_UUID = "uuid";
    private static final String NBT_PROFILE = "profile";
    private final Map<UUID, PlayerProfile> playerProfiles = new HashMap<>();

    public static SavedPlayerProfessionData getInstance() {
        MinecraftServer server = CobblemonSkillsCommon.INSTANCE.getCommonUtils().getServer();
        return server.overworld().getDataStorage().computeIfAbsent(FACTORY, DATA_KEY);
    }

    public static int resetAll() {
        SavedPlayerProfessionData instance = getInstance();
        int count = instance.playerProfiles.size();
        instance.playerProfiles.clear();
        instance.setDirty();
        return count;
    }

    public static boolean resetPlayer(UUID playerUUID) {
        SavedPlayerProfessionData instance = getInstance();
        if (instance.playerProfiles.containsKey(playerUUID)) {
            instance.playerProfiles.remove(playerUUID);
            instance.setDirty();
            return true;
        }
        return false;
    }

    public static Set<Map.Entry<UUID, PlayerProfile>> getPlayerProfiles() {
        return Set.copyOf(getInstance().playerProfiles.entrySet());
    }

    public static void put(UUID playerUUID, PlayerProfile profile) {
        SavedPlayerProfessionData instance = getInstance();
        instance.playerProfiles.put(playerUUID, profile);
        instance.setDirty();
    }

    public static PlayerProfile get(UUID playerUUID) {
        SavedPlayerProfessionData instance = getInstance();
        PlayerProfile profile = instance.playerProfiles.get(playerUUID);
        if (profile == null) {
            profile = new PlayerProfile();
            instance.playerProfiles.put(playerUUID, profile);
            instance.setDirty();
        }
        return profile;
    }

    public static final SavedData.Factory<SavedPlayerProfessionData> FACTORY = new SavedData.Factory<>(
            SavedPlayerProfessionData::new,
            SavedPlayerProfessionData::load,
            null
    );

    public static SavedPlayerProfessionData load(CompoundTag tag, HolderLookup.Provider provider) {
        SavedPlayerProfessionData playerData = new SavedPlayerProfessionData();

        ListTag playerProfilesList = tag.getList(NBT_PLAYER_PROFILES, Tag.TAG_COMPOUND);
        for (int i = 0; i < playerProfilesList.size(); i++) {
            CompoundTag playerTag = playerProfilesList.getCompound(i);
            UUID uuid = playerTag.getUUID(NBT_UUID);
            PlayerProfile profile = PlayerProfile.fromCompoundTag(playerTag.getCompound(NBT_PROFILE));
            playerData.playerProfiles.put(uuid, profile);
        }

        return playerData;
    }

    @Override
    public @NotNull CompoundTag save(CompoundTag compoundTag, HolderLookup.Provider provider) {
        ListTag playerProfilesList = new ListTag();

        for (Map.Entry<UUID, PlayerProfile> entry : playerProfiles.entrySet()) {
            CompoundTag playerTag = new CompoundTag();
            playerTag.putUUID(NBT_UUID, entry.getKey());
            playerTag.put(NBT_PROFILE, entry.getValue().toCompoundTag());
            playerProfilesList.add(playerTag);
        }
        compoundTag.put(NBT_PLAYER_PROFILES, playerProfilesList);
        return compoundTag;
    }
}
