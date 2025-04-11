package com.legends.edumia.world.map;

import com.legends.edumia.utils.LoggerUtil;
import com.legends.edumia.utils.resources.FileUtils;
import com.legends.edumia.world.biomes.surface.MapBasedBiomePool;
import com.legends.edumia.world.biomes.surface.MapBasedCustomBiome;
import net.minecraft.server.level.ServerPlayer;
import org.joml.Vector2i;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class EdumiaMapRuntime {
    private static EdumiaMapRuntime single_instance = null;
    HashMap<Vector2i, EdumiaMapRegion> regions;
    HashMap<UUID, Vector2i> regionByUuids;
    private BufferedImage edgeImage;
    private LoggerUtil loggerUtil;
    private EdumiaMapUtils edumiaMapUtils;

    private int latestValidationTick = 0;
    private int currentValidationBlockCount = 0;

    public static synchronized EdumiaMapRuntime getInstance()
    {
        if (single_instance == null)
            single_instance = new EdumiaMapRuntime();

        return single_instance;
    }

    public EdumiaMapRuntime() {
        regions = new HashMap<>();
        regionByUuids = new HashMap<>();
        edgeImage = EdumiaMapGeneration.getEdgeHeightImage();
        if (edgeImage == null){
            String path = EdumiaMapConfigs.BASE_HEIGHT_PATH + EdumiaMapConfigs.BASE_EDGE_IMAGE_NAME;
            BufferedImage image = FileUtils.getInstance().getRunImage(path);
            if (image != null){
                edgeImage = image;
            }
        }
        edumiaMapUtils = EdumiaMapUtils.getInstance();
    }

    public MapBasedCustomBiome getBiome(int posX, int posZ) {
        if(!edumiaMapUtils.isWorldCoordinateInBorder(posX, posZ)) return MapBasedBiomePool.defaultBiome;

        EdumiaMapRegion region = getRegionToUse(edumiaMapUtils.getRegionByWorldCoordinate(posX, posZ));
        if(region == null) return MapBasedBiomePool.defaultBiome;

        return region.getBiome(getImageCoordinates(posX, posZ));
    }

    private final Color OUT_OF_BORDER_COLOR = new Color(35, 48, 55);

    public Color getHeight(int posX, int posZ) {
        if(!edumiaMapUtils.isWorldCoordinateInBorder(posX, posZ)){
            return OUT_OF_BORDER_COLOR;
        }

        EdumiaMapRegion region = getRegionToUse(edumiaMapUtils.getRegionByWorldCoordinate(posX, posZ));
        if(region == null) return OUT_OF_BORDER_COLOR;

        Vector2i coords = getImageCoordinates(posX, posZ);
        return region.getHeightColor(coords);
    }

    public float getEdge(int posX, int posZ){
        posX /= edumiaMapUtils.ratioX;
        posZ /= edumiaMapUtils.ratioZ;
        if (!edumiaMapUtils.isWorldCoordinateInBorder(posX, posZ)) return -0.67f;

        if (edgeImage == null) return 1.001f;

        Color edgeColor = new Color(edgeImage.getRGB(posX, posZ));

        float average = (float) (edgeColor.getRed() + edgeColor.getGreen() + edgeColor.getBlue()) / 3;

        if (average > 0.01f){
            average *= 12;
        }

        return Math.max(0, 1 - (average / 255));
    }

    private Vector2i getImageCoordinates(int posX, int posZ){
        return new Vector2i(
            (int)((float)posX / EdumiaMapConfigs.PIXEL_WEIGHT % EdumiaMapConfigs.REGION_SIZE),
            (int)((float)posZ / EdumiaMapConfigs.PIXEL_WEIGHT % EdumiaMapConfigs.REGION_SIZE)
        );
    }

    private EdumiaMapRegion getRegionToUse(Vector2i regionCoordinate){
        purgeRegions();

        if(regions.get(regionCoordinate) != null) {
            return regions.get(regionCoordinate);
        }
        return regions.put(regionCoordinate, new EdumiaMapRegion(regionCoordinate));
    }

    private void purgeRegions() {
        // Block delay
        currentValidationBlockCount ++;
        if(currentValidationBlockCount < EdumiaMapConfigs.BIOME_VALIDATION_BLOCK_DELAY) return;
        currentValidationBlockCount = 0;

        // Tick delay
        int serverTick = edumiaMapUtils.getTick();
        if(serverTick - latestValidationTick < EdumiaMapConfigs.BIOME_VALIDATION_TICK_DELAY) return;
        latestValidationTick = serverTick;

        // Create purge array
        List<Vector2i> toPurge = new ArrayList<>();
        List<Vector2i> playerCoordinates = new ArrayList<>();
        for(ServerPlayer player : edumiaMapUtils.getPlayers()){
            playerCoordinates.add(new Vector2i(player.getBlockX(), player.getBlockZ()));
        }

        try{
            edumiaMapUtils.getPlayers();
            regions.forEach((key, value) -> {
                boolean hasPlayerInRange = false;
                for(Vector2i coordinate : playerCoordinates){
                    if(value.isInRange(coordinate)){
                        hasPlayerInRange = true;
                        break;
                    }
                }
                if(!hasPlayerInRange)
                    toPurge.add(key);
            });

            // Purging
            //loggerUtil.logDebugMsg("Purging [%s] regions (tick : %s)".formatted(toPurge.size(), serverTick));
            for (Vector2i region : toPurge){
                regions.remove(region);
            }
        } catch(Exception exception){
            loggerUtil.logError("%s : %s".formatted(toString(), exception.getMessage()));
        }
    }
}
