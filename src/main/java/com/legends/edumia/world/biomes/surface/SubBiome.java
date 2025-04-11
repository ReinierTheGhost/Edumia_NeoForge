package com.legends.edumia.world.biomes.surface;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

import java.util.ArrayList;
import java.util.Map;

public class SubBiome {
    public ArrayList<SubBiomeData> subBiomeData;
    private float additionalHeight;

    private float frequency;

    public SubBiome(){
        this(56);
    }
    public SubBiome(float additionalHeight){
        this(additionalHeight, 1);
    }

    public SubBiome(float additionalHeight, float frequency){
        this.subBiomeData = new ArrayList<>();
        this.additionalHeight = additionalHeight;
        this.frequency = frequency;
    }

    public SubBiome addSubBiomeData(float noiseMin, float noiseMax, ResourceKey<Biome> biome){
        return this.addSubBiomeData(noiseMin, noiseMax, biome, false);
    }

    public SubBiome addSubBiomeData(float noiseMin, float noiseMax, ResourceKey<Biome> biome, boolean additionalHeight) {
        SubBiomeData newSubBiomeData = new SubBiomeData(noiseMin, noiseMax, biome, additionalHeight);
        if (!subBiomeData.isEmpty()){
            for (SubBiomeData subBiomeData : subBiomeData){
                if (newSubBiomeData.noiseMax > subBiomeData.noiseMin && newSubBiomeData.noiseMin < subBiomeData.noiseMax){
                    throw new ArithmeticException("Sub biome conflicts in noise range");
                }
            }
        }
        subBiomeData.add(newSubBiomeData);
        return this;
    }

    public SubBiomeData getBiomeAtNoise(float value){
        for (SubBiomeData subBiomeData : subBiomeData){
            if (value >= subBiomeData.noiseMin && value < subBiomeData.noiseMax){
                return subBiomeData;
            }
        }
        return null;
    }
    public boolean containsSubBiome(ResourceKey<Biome> biomeResourceKey){
        for (SubBiomeData subBiomeData : subBiomeData){
            if (biomeResourceKey == subBiomeData.biome) return true;
        }
        return false;
    }

    public float getFrequency(){
        return frequency;
    }

    public float getAdditionalHeight(float value){
        SubBiomeData biomeData = getBiomeAtNoise(value);
        if (biomeData == null){
            return 0;
        } else {
            if (!biomeData.additionalHeight) return 0;
            float distanceLeft = recursiveFindClossestVoid(value, -1);
            float distanceRight = recursiveFindClossestVoid(value, 1);
            return Math.min(value - distanceLeft, value - distanceRight) * 56f;
        }
    }

    private float recursiveFindClossestVoid(float value, int direction) {
        SubBiomeData biomeData = getBiomeAtNoise(value);
        if (biomeData == null){
            return value;
        } else {
            if (!biomeData.additionalHeight) return value;
            else if (direction == -1){
                return recursiveFindClossestVoid(biomeData.noiseMin - 0.001f, direction);
            } else {
                return recursiveFindClossestVoid(biomeData.noiseMax, direction);
            }
        }
    }

    public class SubBiomeData{
        public float noiseMin;
        public float noiseMax;
        public ResourceKey<Biome> biome;
        public boolean additionalHeight;

        public SubBiomeData(float noiseMin, float noiseMax, ResourceKey<Biome> biome, boolean additionalHeight){
            this.noiseMin = noiseMin;
            this.noiseMax = noiseMax;
            this.biome = biome;
            this.additionalHeight = additionalHeight;
        }
    }
}
