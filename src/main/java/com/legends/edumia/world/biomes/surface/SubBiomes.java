package com.legends.edumia.world.biomes.surface;

import com.legends.edumia.world.biomes.EdumiaBiomeKeys;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class SubBiomes {
    public static HashMap<ResourceKey<Biome>, SubBiome> subBiomesMap;

    public static void loadSubBiomes(){
        subBiomesMap = new HashMap<>();
        subBiomesMap.put(EdumiaBiomeKeys.AVELION_PLAINS, new SubBiome(56, 1.25f)
                .addSubBiomeData(-1.0f, -0.31f, EdumiaBiomeKeys.AVELION_MEADOW)
                .addSubBiomeData(0.22f, 0.44f, EdumiaBiomeKeys.AVELION_FOREST)
                .addSubBiomeData(0.44f, 2.0f, EdumiaBiomeKeys.AVELION_HIDDEN_BLOSSOM));

        subBiomesMap.put(EdumiaBiomeKeys.MYRWOOD_JUNGLE, new SubBiome(96)
                .addSubBiomeData(-1.0f, -0.35f, EdumiaBiomeKeys.MYRWOOD_JUNGLE_PLATEAU)
                .addSubBiomeData(0.44f, 2f, EdumiaBiomeKeys.MYRWOOD_JUNGLE_CLEARING, true));
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
