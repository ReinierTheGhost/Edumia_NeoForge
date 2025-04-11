package com.legends.edumia.world.biomes.surface;

import com.legends.edumia.world.biomes.BiomeColorsDTO;
import com.legends.edumia.world.biomes.BlocksLayeringData;
import com.legends.edumia.world.biomes.SlopeMap;
import com.legends.edumia.world.biomes.caves.CaveType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

/**
 * Custom biome data used during runtime to determine what to generate
 */
public class BiomeData {
    private final ResourceKey<Biome> biomeResourceKey;
    private final SlopeMap slopeMap;
    private final BlocksLayeringData blocksLayering;
    private final BiomeColorsDTO biomeColors;
    private CaveType caveType;

    public BiomeData(ResourceKey<Biome> biomeResourceKey, SlopeMap slopeMap, BlocksLayeringData blocksLayering, BiomeColorsDTO biomeColors) {
        this.biomeResourceKey = biomeResourceKey;
        this.slopeMap = slopeMap;
        this.blocksLayering = blocksLayering;
        this.biomeColors = biomeColors;
    }

    public BiomeData(ResourceKey<Biome> biomeResourceKey, SlopeMap slopeMap, BlocksLayeringData blocksLayering, BiomeColorsDTO biomeColors, CaveType caveType) {
        this(biomeResourceKey, slopeMap, blocksLayering, biomeColors);
        this.caveType = caveType;
    }

    public ResourceKey<Biome> getBiomeResourceKey() {
        return this.biomeResourceKey;
    }

    public SlopeMap getSlopeMap() {
        return slopeMap;
    }

    public BlocksLayeringData getBlocksLayering() {
        return blocksLayering;
    }

    public CaveType getCaveType() {
        return caveType;
    }

    public BiomeColorsDTO getBiomeColors() {
        return biomeColors;
    }
}
