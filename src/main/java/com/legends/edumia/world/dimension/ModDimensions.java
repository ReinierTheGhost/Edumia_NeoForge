package com.legends.edumia.world.dimension;


import com.legends.edumia.Edumia;
import com.legends.edumia.resources.persistent_datas.PlayerData;
import com.legends.edumia.utils.LoggerUtil;
import com.legends.edumia.world.chunkgen.EdumiaChunkGenerator;
import com.legends.edumia.world.chunkgen.map.EdumiaHeightMap;
import com.legends.edumia.world.map.EdumiaMapConfigs;
import com.legends.edumia.world.map.EdumiaMapUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector2i;
import org.joml.Vector3i;


public class ModDimensions {
    public static final Vector3i ME_SPAWN_LOCATION = new Vector3i(1555, 90, 1348);
    public static final String PATH = "edumia";

    public static final ResourceKey<LevelStem> DIMENSION_KEY =
            ResourceKey.create(Registries.LEVEL_STEM, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, PATH));

    public static ResourceKey<Level> WORLD_KEY =
            ResourceKey.create(Registries.DIMENSION, DIMENSION_KEY.location());

    public static void register() {
//        Registry.register(Registries.CHUNK_GENERATOR, Identifier.of(Edumia.MOD_ID, PATH), EdumiaChunkGenerator.CODEC);
        WORLD_KEY = ResourceKey.create(Registries.DIMENSION, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, PATH));

        LoggerUtil.logDebugMsg("Registering ModDimensions for " + Edumia.MOD_ID);
    }

    public static Vector3i getDimensionHeight(int x, int z){
        EdumiaHeightMap.getHeight(x, z);
        int height = (int) (1 + EdumiaChunkGenerator.DIRT_HEIGHT + EdumiaHeightMap.getHeight(x, z));
        return new Vector3i(x, height, z);
    }
    public static void teleportPlayerToEdumia(Player player) {
        Vector2i coordinates = EdumiaMapUtils.getInstance().getWorldCoordinateFromInitialMap(ME_SPAWN_LOCATION.x, ME_SPAWN_LOCATION.z);
        int height = (int) (1 + EdumiaChunkGenerator.DIRT_HEIGHT + EdumiaHeightMap.getHeight(coordinates.x, coordinates.y));
        Vector3i targetCoords = new Vector3i(coordinates.x, height, coordinates.y);
        teleportPlayerToEdumia(player, targetCoords);
    }

    public static void teleportPlayerToEdumia(Player player, Vector3i coordinates){
        if(!player.level().isClientSide()) {
            ResourceKey<Level> registryKey = WORLD_KEY;
            ServerLevel serverWorld = (ServerLevel) player.level();
            if (serverWorld != null) {
                serverWorld = serverWorld.getServer().getLevel(registryKey);

                player.stopSleeping();

                ((ServerPlayer) player).teleportTo(serverWorld, coordinates.x , coordinates.y, coordinates.z, 0, 0);
                player.moveTo(coordinates.x, coordinates.y, coordinates.z);
            }
        }
    }

    public static void teleportPlayerToEdumia(Player player, Vec3 coordinates, boolean setSpawnPoint, boolean welcomeNeeded){
        if(!player.level().isClientSide()) {
            ResourceKey<Level> registryKey = WORLD_KEY;
            ServerLevel serverWorld = (ServerLevel) player.level();
            if (serverWorld != null) {
                serverWorld = serverWorld.getServer().getLevel(registryKey);

                player.stopSleeping();

                ((ServerPlayer) player).teleportTo(serverWorld, coordinates.x, coordinates.y, coordinates.z, 0, 0);
                player.moveTo(coordinates.x, coordinates.y, coordinates.z);
                if (setSpawnPoint)
                    ((ServerPlayer) player).setRespawnPosition(registryKey, new BlockPos((int) coordinates.x, (int) coordinates.y, (int) coordinates.z),
                            player.getYRot(), true,true);

//                PlayerData data
            }
        }
    }

    public static int getHighestYAtXZ(int x, int z) {
        return (int) EdumiaHeightMap.getHeight(x, z);
    }

    public static Vector3i getSpawnCoordinate(){
        double worldIteration = Math.pow(2, EdumiaMapConfigs.MAP_ITERATION);
        int x = (int)((ME_SPAWN_LOCATION.x * worldIteration));
        int z = (int)((ME_SPAWN_LOCATION.z * worldIteration));

        return new Vector3i(x, ME_SPAWN_LOCATION.y, z);
    }

    public static boolean isInEdumia(Level world){
        return world.dimension().location().equals(WORLD_KEY.location());
    }

    public static boolean isInOverworld(Level world){
        return world.dimension().location().equals(Level.OVERWORLD.location());
    }

    public static Component getDisplayName(ResourceKey<Level> dimensionWorldKey) {
        ResourceLocation dimensionName = dimensionWorldKey.location();
        String key = String.format("dimension.%s.%s", dimensionName.getNamespace(), dimensionName.getPath());
        return Component.translatable(key);
    }

    public static ResourceKey<Level> getCurrentEdumiaDimensionOfFallback(Level level) {
        Level dimension = level;
        return dimension instanceof EdumiaDimensionType ? level.dimension() : WORLD_KEY;
    }
}
