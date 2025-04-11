package com.legends.edumia.world.biomes.surface;

import com.legends.edumia.world.biomes.BiomeGenerationData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

public class MapBasedCustomBiome {
    public static final int DEFAULT_WATER_HEIGHT = 64;
    private final ResourceKey<Biome> biomeResourceKey;
    private final byte height;
    private final byte waterHeight;
    private final BiomeGenerationData biomeGenerationData;

    public MapBasedCustomBiome(ResourceKey<Biome> key, int height, BiomeGenerationData data) {
        this.biomeResourceKey = key;
        this.height = (byte) height;
        this.waterHeight = DEFAULT_WATER_HEIGHT;
        this.biomeGenerationData = data;
    }

    public MapBasedCustomBiome(ResourceKey<Biome> key, int height, int waterHeight, BiomeGenerationData data) {
        this.biomeResourceKey = key;
        this.height = (byte) height;
        this.waterHeight = (byte) waterHeight;
        this.biomeGenerationData = data;
    }

    public ResourceKey<Biome> getBiomeKey() {
        return biomeResourceKey;
    }

    public BiomeData getBiome(){
        return MapBiomeData.getBiome(biomeResourceKey);
    }

    public byte getHeight() {
        return height;
    }

    public byte getWaterHeight() {
        return waterHeight;
    }

    public BiomeGenerationData getBiomeData() {
        return biomeGenerationData;
    }
}
