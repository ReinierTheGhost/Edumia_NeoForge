package com.legends.edumia.world.biomes.surface;


import com.legends.edumia.world.biomes.EdumiaBiomeDataConfigs;
import com.legends.edumia.world.biomes.EdumiaBiomeKeys;
import com.legends.edumia.world.biomes.caves.CaveType;
import com.legends.edumia.world.chunkgen.map.EdumiaHeightMap;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.biome.BiomeData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Converts PNG pixel color to a BiomeKey reference.
 */
public class MapBasedBiomePool {

    private static HashMap<Color, MapBasedCustomBiome> biomeHashMap;
    public static List<ResourceKey<Biome>> coastalBiomes = new ArrayList<>();
    public static List<ResourceKey<Biome>> waterBiomes = new ArrayList<>();
    public static List<ResourceKey<Biome>> riverbiomes = new ArrayList<>();
    public static List<ResourceKey<Biome>> frozenBiomes = new ArrayList<>();
    public static List<ResourceKey<Biome>> wastePondBiomes = new ArrayList<>();
    public static List<ResourceKey<Biome>> mirkwoodSwampBiomes = new ArrayList<>();
    public static List<ResourceKey<Biome>> oasisBiomes = new ArrayList<>();
    public static List<ResourceKey<Biome>> anduinWaterBiomes = new ArrayList<>();
    public static List<ResourceKey<Biome>> deadMarshesBiomes = new ArrayList<>();

    public static Color DEFAULT_COLOR = new Color(0x375ac3);

    public static MapBasedCustomBiome defaultBiome;
    public static MapBasedCustomBiome oceanCoast;
    public static MapBasedCustomBiome frozenPond;
    public static MapBasedCustomBiome oasis;
    public static MapBasedCustomBiome pond;
    public static MapBasedCustomBiome greatRiver;
    public static MapBasedCustomBiome wastePond;
    public static MapBasedCustomBiome mirkwoodSwamp;
    public static MapBasedCustomBiome deadMarshes;
    public static MapBasedCustomBiome deadMarshesWater;

    public static void add(Color color, MapBasedCustomBiome biome) {
        biomeHashMap.put(color, biome);
    }

    public static MapBasedCustomBiome getBiomeByColor(Integer rgb){
        if(biomeHashMap.containsKey(new Color(rgb)))
            return biomeHashMap.get(new Color(rgb));
        throw new RuntimeException("EdumiaBiomes::No registered biome has %s for color".formatted(rgb));
    }


    public static void loadBiomes() {
        biomeHashMap = new HashMap<>();

        SubBiomes.loadSubBiomes();
        defaultBiome = new MapBasedCustomBiome(EdumiaBiomeKeys.OCEAN, -17, EdumiaBiomeDataConfigs.oceanModifier);
        add(DEFAULT_COLOR, defaultBiome);

        oceanCoast = new MapBasedCustomBiome(EdumiaBiomeKeys.OCEAN_COAST, -9, EdumiaBiomeDataConfigs.oceanModifier);
        add(new Color(75, 106, 199), oceanCoast);

        // Ponds
        frozenPond = new MapBasedCustomBiome( EdumiaBiomeKeys.FROZEN_POND,-10, EdumiaBiomeDataConfigs.riverModifier);
        add(new Color(104, 168, 222), frozenPond);
        oasis = new MapBasedCustomBiome(EdumiaBiomeKeys.OASIS, -10, EdumiaBiomeDataConfigs.riverModifier);
        add(new Color(104, 168, 222), oasis);
        pond = new MapBasedCustomBiome(EdumiaBiomeKeys.POND, -10, EdumiaBiomeDataConfigs.riverModifier);
        add(new Color(110, 154, 218), pond);
        mirkwoodSwamp = new MapBasedCustomBiome(EdumiaBiomeKeys.FAIRY_SWAMP, -10, EdumiaBiomeDataConfigs.riverModifier);
        add(new Color(28, 107, 86), mirkwoodSwamp);
        greatRiver = new MapBasedCustomBiome(EdumiaBiomeKeys.GREAT_RIVER, -10, EdumiaBiomeDataConfigs.riverModifier);
        add(new Color(99, 138, 186), greatRiver);
        wastePond = new MapBasedCustomBiome(EdumiaBiomeKeys.WASTE_POND, -10, EdumiaBiomeDataConfigs.riverModifier);
        add(new Color(75, 108, 143), wastePond);
        deadMarshes = new MapBasedCustomBiome(EdumiaBiomeKeys.DEAD_MARSHES, 0, EdumiaBiomeDataConfigs.plainsModifier.noiseModifier(0f));
        add(new Color(48, 94, 66), deadMarshes);
        deadMarshesWater = new MapBasedCustomBiome(EdumiaBiomeKeys.DEAD_MARSHES_WATER, -10, EdumiaBiomeDataConfigs.riverModifier);
        add(new Color(90, 124, 161), deadMarshesWater);
        // Water Biomes :e

        add(new Color(90, 120, 240), new MapBasedCustomBiome(EdumiaBiomeKeys.MOUNTAIN_STREAM, 37, EdumiaBiomeDataConfigs.emModifier.heightModifier(0.23f)));




        add(new Color(101, 123, 243), new MapBasedCustomBiome(EdumiaBiomeKeys.FROZEN_OCEAN, -12, EdumiaBiomeDataConfigs.oceanModifier));
        add(new Color(75, 106, 199), new MapBasedCustomBiome(EdumiaBiomeKeys.OCEAN_COAST, -11, EdumiaBiomeDataConfigs.oceanModifier));
        add(new Color(83, 129, 186), new MapBasedCustomBiome(EdumiaBiomeKeys.RIVER, -6, EdumiaBiomeDataConfigs.smallRiverModifier));

        add(new Color(255, 0, 110), new MapBasedCustomBiome(EdumiaBiomeKeys.GENSAI_REEF, -8, EdumiaBiomeDataConfigs.oceanModifier));


        // Land Biomes :
        add(new Color(156, 207, 113), new MapBasedCustomBiome(EdumiaBiomeKeys.EDUMIA_VALES, 4, EdumiaBiomeDataConfigs.landModifier));
        add(new Color(234, 222, 117), new MapBasedCustomBiome(EdumiaBiomeKeys.GENSAI_BEACH, 0,
                EdumiaBiomeDataConfigs.landModifier.heightModifier(0.97f).heightModifier(0.1f).noiseModifier(0.05f)));

        add(new Color(35, 36, 36), new MapBasedCustomBiome(EdumiaBiomeKeys.DEMON_WASTELANDS, 10, EdumiaBiomeDataConfigs.landModifier));

        add(new Color(54, 75, 12), new MapBasedCustomBiome(EdumiaBiomeKeys.DARK_ELF_FOREST, 6, EdumiaBiomeDataConfigs.landModifier));
        add(new Color(236, 236, 236), new MapBasedCustomBiome(EdumiaBiomeKeys.EDUMIA_TUNDRA, 14, EdumiaBiomeDataConfigs.landModifier));
        add(new Color(237, 229, 145), new MapBasedCustomBiome(EdumiaBiomeKeys.ORC_DESERT, 9, EdumiaBiomeDataConfigs.landModifier));
        add(new Color(255, 0, 0), new MapBasedCustomBiome(EdumiaBiomeKeys.RED_ORC_DESERT, 10, EdumiaBiomeDataConfigs.landModifier));
        add(new Color(67, 193, 125), new MapBasedCustomBiome(EdumiaBiomeKeys.AVELION_PLAINS, 4, EdumiaBiomeDataConfigs.landModifier));
        add(new Color(92, 42, 235), new MapBasedCustomBiome(EdumiaBiomeKeys.OGRE_FOREST, 4, EdumiaBiomeDataConfigs.landModifier));
        add(new Color(92, 42, 109), new MapBasedCustomBiome(EdumiaBiomeKeys.GENSAI_SAKURA_GROVE, 6, EdumiaBiomeDataConfigs.landModifier));
        add(new Color(132, 137, 124), new MapBasedCustomBiome(EdumiaBiomeKeys.EDUMIA_FOOTHILLS, 37, EdumiaBiomeDataConfigs.emModifier.heightModifier(0.23f)));
        add(new Color(143, 142, 142), new MapBasedCustomBiome(EdumiaBiomeKeys.EDUMIA_MOUNTAINS_BASE, 53, EdumiaBiomeDataConfigs.emModifier));
        add(new Color(129, 129, 129), new MapBasedCustomBiome(EdumiaBiomeKeys.EDUMIA_MOUNTAINS, 82, EdumiaBiomeDataConfigs.emModifier));
        add(new Color(185, 183, 183), new MapBasedCustomBiome(EdumiaBiomeKeys.EDUMIA_MOUNTAINS_PEAKS, 100, EdumiaBiomeDataConfigs.emPeaksModifier));
        add(new Color(54, 114, 12), new MapBasedCustomBiome(EdumiaBiomeKeys.FAIRY_FOREST, 7, EdumiaBiomeDataConfigs.landModifier));


        add(new Color(56, 36, 36), new MapBasedCustomBiome(EdumiaBiomeKeys.GENSAI_VOLCANO_PLAINS, 6, EdumiaBiomeDataConfigs.landModifier));
        add(new Color(45, 42, 42), new MapBasedCustomBiome(EdumiaBiomeKeys.MOUNT_TITLEIST_FOOT, 36, EdumiaBiomeDataConfigs.mountTitleistModifier));
        add(new Color(36, 31, 31), new MapBasedCustomBiome(EdumiaBiomeKeys.MOUNT_TITLEIST, 73, EdumiaBiomeDataConfigs.mountTitleistModifier));
        add(new Color(26, 23, 23), new MapBasedCustomBiome(EdumiaBiomeKeys.MOUNT_TITLEIST_PEAK, 96, EdumiaBiomeDataConfigs.mountTitleistModifier
                ));
        add(new Color(96, 39, 13), new MapBasedCustomBiome(EdumiaBiomeKeys.MOUNT_TITLEIST_CRATER, 90, EdumiaBiomeDataConfigs.mountTitleistModifier.heightModifier(0.36f).noiseModifier(1.0f).expansionWeight(new byte[]{2, 3})));



        add(new Color(121, 186, 111), new MapBasedCustomBiome(EdumiaBiomeKeys.TAIGA_FOREST, 4, EdumiaBiomeDataConfigs.landModifier));
        add(new Color(48, 109, 42), new MapBasedCustomBiome(EdumiaBiomeKeys.GENSAI_JUNGLE, 7, EdumiaBiomeDataConfigs.landModifier));
        add(new Color(222, 227, 191), new MapBasedCustomBiome(EdumiaBiomeKeys.AVELION_SANDY_SHORES, 0,
                EdumiaBiomeDataConfigs.landModifier.heightModifier(0.97f).heightModifier(0.1f).noiseModifier(0.05)));
        add(new Color(74, 213, 138), new MapBasedCustomBiome(EdumiaBiomeKeys.AVELION_ROCKY_SHORES,30,
                EdumiaBiomeDataConfigs.landModifier.heightModifier(0.76f)));
        add(new Color(180, 126, 119), new MapBasedCustomBiome(EdumiaBiomeKeys.AVELION_FOOTHILLS,35,
                EdumiaBiomeDataConfigs.mountainModifier.heightModifier(0.23f)));
        add(new Color(180, 136, 119), new MapBasedCustomBiome(EdumiaBiomeKeys.AVELION_MOUNTAIN_BASE,56,
                EdumiaBiomeDataConfigs.mountainModifier.heightModifier(0.24f).noiseModifier(1.21f)));
        add(new Color(206, 156, 138), new MapBasedCustomBiome(EdumiaBiomeKeys.AVELION_MOUNTAIN,79,
                EdumiaBiomeDataConfigs.mountainModifier.heightModifier(0.34f)));
        add(new Color(237, 179, 158), new MapBasedCustomBiome(EdumiaBiomeKeys.AVELION_MOUNTAIN_PEAKS,106,
                EdumiaBiomeDataConfigs.mountainModifier.heightModifier(0.39f)));


        riverbiomes.add(EdumiaBiomeKeys.RIVER);
        riverbiomes.add(EdumiaBiomeKeys.FROZEN_RIVER);

        waterBiomes.add(EdumiaBiomeKeys.DEAD_MARSHES_WATER);
        waterBiomes.add(EdumiaBiomeKeys.FROZEN_POND);
        waterBiomes.add(EdumiaBiomeKeys.FROZEN_OCEAN);
        waterBiomes.add(EdumiaBiomeKeys.OCEAN);
        waterBiomes.add(EdumiaBiomeKeys.OCEAN_COAST);
        waterBiomes.add(EdumiaBiomeKeys.RIVER);
        waterBiomes.add(EdumiaBiomeKeys.GENSAI_REEF);
        waterBiomes.add(EdumiaBiomeKeys.AVELION_ROCKY_SHORES);
        waterBiomes.add(EdumiaBiomeKeys.AVELION_SANDY_SHORES);

        anduinWaterBiomes.add(EdumiaBiomeKeys.GREAT_RIVER);


        frozenBiomes.add(EdumiaBiomeKeys.EDUMIA_TUNDRA);



        oasisBiomes.add(EdumiaBiomeKeys.ORC_DESERT);
        oasisBiomes.add(EdumiaBiomeKeys.RED_ORC_DESERT);


        wastePondBiomes.add(EdumiaBiomeKeys.GENSAI_VOLCANO_PLAINS);

        deadMarshesBiomes.add(EdumiaBiomeKeys.DEAD_MARSHES);
    }

   public static MapBasedCustomBiome getBiome(Holder<Biome> biome, int posX, int posZ){
        MapBasedCustomBiome foundBiome = null;
        if (biome.unwrapKey().isPresent()){
            ResourceLocation biomeId = biome.unwrapKey().get().location();
            foundBiome = biomeHashMap.values().stream().filter(
                    b -> b.getBiomeKey().location().equals(biomeId)
            ).findFirst().orElse(defaultBiome);
        }

        if (foundBiome != null){
            MapBasedCustomBiome colorBasedBiome = EdumiaHeightMap.getBiomeFromMap(posX, posZ);
            if (colorBasedBiome.getBiomeKey() == foundBiome.getBiomeKey()){
                return colorBasedBiome;
            }
        }

        return foundBiome;
   }
}
