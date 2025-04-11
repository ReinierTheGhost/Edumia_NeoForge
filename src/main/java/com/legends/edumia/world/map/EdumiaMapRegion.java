package com.legends.edumia.world.map;

import com.legends.edumia.utils.resources.FileUtils;
import com.legends.edumia.world.biomes.surface.MapBasedBiomePool;
import com.legends.edumia.world.biomes.surface.MapBasedCustomBiome;
import org.joml.Vector2i;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class EdumiaMapRegion {
    public final static int CALC_REGION_SIZE = EdumiaMapConfigs.REGION_SIZE * EdumiaMapConfigs.PIXEL_WEIGHT;
    public final Vector2i coordinate;
    private final BufferedImage biomeImage;
    private final BufferedImage heightImage;

    public EdumiaMapRegion(Vector2i coordinate){
        this.coordinate = coordinate;
        String biomePath = EdumiaMapConfigs.BIOME_PATH.formatted(EdumiaMapConfigs.MAP_ITERATION) + EdumiaMapConfigs.IMAGE_NAME.formatted(coordinate.x,coordinate.y);
        String heightPath = EdumiaMapConfigs.HEIGHT_PATH + EdumiaMapConfigs.IMAGE_NAME.formatted(coordinate.x, coordinate.y);
        biomeImage = FileUtils.getInstance().getRunImage(biomePath);
        heightImage = FileUtils.getInstance().getRunImage(heightPath);

        //LoggerUtil.getInstance().sendChat(biomePath);
        //LoggerUtil.getInstance().sendChat(heightPath);
    }

    public MapBasedCustomBiome getBiome(Vector2i imageCoordinates){
        try {
            if(biomeImage != null){
                return MapBasedBiomePool.getBiomeByColor(biomeImage.getRGB(imageCoordinates.x, imageCoordinates.y));
            }
        } catch (Exception exception){
            return MapBasedBiomePool.defaultBiome;
        }
        return MapBasedBiomePool.defaultBiome;
    }

    public Color getHeightColor(Vector2i imageCoordinates) {
        if(heightImage != null){
            return new Color(heightImage.getRGB(imageCoordinates.x, imageCoordinates.y));
        }
        return new Color(Math.abs(MapBasedBiomePool.defaultBiome.getHeight()), 1, 0);
    }

    public boolean isInRange(Vector2i playerCoord) {
        int middleCoordinateX = CALC_REGION_SIZE * (coordinate.x + 1) - CALC_REGION_SIZE / 2;
        int middleCoordinateZ = CALC_REGION_SIZE * (coordinate.y + 1) - CALC_REGION_SIZE / 2;
        double distance = calculateDistance(playerCoord.x, playerCoord.y, middleCoordinateX, middleCoordinateZ);
        //LoggerUtil.getInstance().logDebugMsg("IsInRange : [%s,%s] = [%s]".formatted(coordinate.x, coordinate.y, distance));
        return distance < (CALC_REGION_SIZE / 2) + EdumiaMapConfigs.BIOME_VALIDATION_DIST_CHECK;
    }

    private double calculateDistance(double x1, double y1, double x2, double y2) {
            return Point2D.distance(x1, y1, x2, y2);
    }
}
