package com.legends.edumia.world.biomes.surface;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class SubBiomes {
    public static HashMap<ResourceKey<Biome>, SubBiome> subBiomesMap;

    public static void loadSubBiomes(){
        subBiomesMap = new HashMap<>();
    }

    public static boolean isSubBiome(ResourceKey<Biome> biomeResourceKey) {
        AtomicBoolean containsBiome = new AtomicBoolean(false);
        subBiomesMap.forEach((key, value) -> {
            if (value.containsSubBiome(biomeResourceKey)){
                containsBiome.set(true);
            }
        });
        return containsBiome.get();
    }

    public static SubBiome getSubBiome(ResourceKey<Biome> biomeResourceKey){
        return subBiomesMap.get(biomeResourceKey);
    }

    public static SubBiome getSubBiomeFromChild(ResourceKey<Biome> biomeResourceKey){
        for (Map.Entry<ResourceKey<Biome>, SubBiome> entry : subBiomesMap.entrySet()) {
            if (entry.getValue().containsSubBiome(biomeResourceKey)){
                return entry.getValue();
            }
        }
        return null;
    }
}
