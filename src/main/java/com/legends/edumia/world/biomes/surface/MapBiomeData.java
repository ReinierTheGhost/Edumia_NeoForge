package com.legends.edumia.world.biomes.surface;

import com.legends.edumia.world.biomes.BiomeColorsDTO;
import com.legends.edumia.world.biomes.EdumiaBiomeDataConfigs;
import com.legends.edumia.world.biomes.EdumiaBiomeKeys;
import com.legends.edumia.world.biomes.caves.CaveType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

import java.util.HashMap;

public class MapBiomeData {
    private static HashMap<ResourceKey<Biome>, BiomeData> biomes;

    public static final int defaultSky = 7907327;
    public static final int defaultFog = 12638463;
    public static final int defaultWater = 4159204;
    public static final int defaultWaterFog = 329011;
    public static final int defaultShoreWater = 4157124;
    public static final int defaultCoastWater = 4155044;
    public static final int defaultOceanWater = 3956102;
    public static final int defaultOceanWaterFog = 2309971;
    public static final int hillySky = 8233727;
    public static final int waterSky = 8103167;
    public static final int desertSky = 7254527;
    public static final int desertSkyFog = 12902399;

    private static void addBiome(BiomeData biome){
        biomes.put(biome.getBiomeResourceKey(), biome);
    }

    private static void ensureBiomesInitialized() {
        if (biomes == null) {
            loadBiomes();
        }
    }
    public static BiomeData getBiome(ResourceKey<Biome> biomeResourceKey){
        ensureBiomesInitialized();
        if (!biomes.containsKey(biomeResourceKey))
            throw new RuntimeException("Cannot find %s in the custom biome data pool".formatted(biomeResourceKey.location()));
        return biomes.get(biomeResourceKey);
    }

    public static void loadBiomes(){
        biomes = new HashMap<>();
        SubBiomes.loadSubBiomes();
        loadGenericPonds();
        loadGenericRivers();
        loadGenericOceans();

        addBiome(new BiomeData(EdumiaBiomeKeys.EDUMIA_VALES, EdumiaBiomeDataConfigs.grassPlains, EdumiaBiomeDataConfigs.stoneLayers,
                new BiomeColorsDTO(defaultSky, defaultFog, defaultWater, defaultWaterFog, 8703593, 8703593)));
        addBiome(new BiomeData(EdumiaBiomeKeys.EDUMIA_SAVANNAH, EdumiaBiomeDataConfigs.grassPlains, EdumiaBiomeDataConfigs.stoneLayers,
                new BiomeColorsDTO(desertSky, desertSkyFog, 5406139, 1120828, 12305028, 10860366)));

        addBiome(new BiomeData(EdumiaBiomeKeys.GENSAI_BEACH, EdumiaBiomeDataConfigs.gensaiShores, EdumiaBiomeDataConfigs.redSandLayers,
                new BiomeColorsDTO(desertSky, defaultFog, 5212644, 333363, 12107900, 10860366)));
        addBiome(new BiomeData(EdumiaBiomeKeys.GENSAI_ROCKY_COAST, EdumiaBiomeDataConfigs.gensaiShores, EdumiaBiomeDataConfigs.redSandLayers,
                new BiomeColorsDTO(desertSky, defaultFog, 5212644, 333363, 12107900, 10860366)));

        addBiome(new BiomeData(EdumiaBiomeKeys.DEAD_MARSHES, EdumiaBiomeDataConfigs.pond, EdumiaBiomeDataConfigs.stoneLayers,
                new BiomeColorsDTO(11908531, 7108218, 3289373, 198924, 6115374, 5794902)));
        addBiome(new BiomeData(EdumiaBiomeKeys.DEAD_MARSHES_WATER, EdumiaBiomeDataConfigs.pond, EdumiaBiomeDataConfigs.stoneLayers,
                new BiomeColorsDTO(11908531, 7108218, 3289373, 198924, 6115374, 5794902)));

        addBiome(new BiomeData(EdumiaBiomeKeys.DARK_ELF_FOREST, EdumiaBiomeDataConfigs.grassPlains, EdumiaBiomeDataConfigs.stoneLayers,
                new BiomeColorsDTO(7972607, defaultFog, 4293787, 338483, 3559947, 1789719)));

        addBiome(new BiomeData(EdumiaBiomeKeys.EDUMIA_TUNDRA, EdumiaBiomeDataConfigs.tundra, EdumiaBiomeDataConfigs.frozenLayers,
                new BiomeColorsDTO(8364543, 10335206, 3823818, 66852, 3494723, 4478280)));



        addBiome(new BiomeData(EdumiaBiomeKeys.ORC_DESERT, EdumiaBiomeDataConfigs.desert, EdumiaBiomeDataConfigs.sandstoneLayers,
                new BiomeColorsDTO(desertSky, desertSkyFog, 5407446, 1120828, 13419633, 9615182)));

        addBiome(new BiomeData(EdumiaBiomeKeys.RED_ORC_DESERT, EdumiaBiomeDataConfigs.redDesert, EdumiaBiomeDataConfigs.sandstoneLayers,
                new BiomeColorsDTO(desertSky, desertSkyFog, 5407446, 1120828, 13419633, 9615182)));

        // Avelion
        addBiome(new BiomeData(EdumiaBiomeKeys.AVELION_PLAINS, EdumiaBiomeDataConfigs.grassPlains, EdumiaBiomeDataConfigs.avelionLayers,
                new BiomeColorsDTO(8827134, 12771327, defaultWater, defaultWaterFog, 7385448, 6593880),
                CaveType.AVELION));
        addBiome(new BiomeData(EdumiaBiomeKeys.AVELION_FOREST, EdumiaBiomeDataConfigs.grassPlains, EdumiaBiomeDataConfigs.avelionLayers,
                new BiomeColorsDTO(8827134, 12771327, defaultWater, defaultWaterFog, 6331736, 6067536),
                CaveType.AVELION));
        addBiome(new BiomeData(EdumiaBiomeKeys.AVELION_HIDDEN_BLOSSOM, EdumiaBiomeDataConfigs.grassPlains, EdumiaBiomeDataConfigs.avelionLayers,
                new BiomeColorsDTO(8827134, 12771327, defaultWater, defaultWaterFog, 7584106, 6593880),
                CaveType.AVELION));
        addBiome(new BiomeData(EdumiaBiomeKeys.AVELION_MEADOW, EdumiaBiomeDataConfigs.grassPlains, EdumiaBiomeDataConfigs.avelionLayers,
                new BiomeColorsDTO(8827134, 12771327, defaultWater, defaultWaterFog, 7584106, 6725722),
                CaveType.AVELION));
        addBiome(new BiomeData(EdumiaBiomeKeys.AVELION_SANDY_SHORES, EdumiaBiomeDataConfigs.beach, EdumiaBiomeDataConfigs.avelionLayers,
                new BiomeColorsDTO(8827134, 12771327, defaultWater, defaultWaterFog, 9090684, 8168815)));
        addBiome(new BiomeData(EdumiaBiomeKeys.AVELION_ROCKY_SHORES, EdumiaBiomeDataConfigs.avelionRockShore, EdumiaBiomeDataConfigs.avelionLayers,
                new BiomeColorsDTO(8827134, 12771327, defaultWater, defaultWaterFog, 9090684, 8168815)));

        addBiome(new BiomeData(EdumiaBiomeKeys.OGRE_FOREST, EdumiaBiomeDataConfigs.grassPlains, EdumiaBiomeDataConfigs.limestoneLayers,
                new BiomeColorsDTO(defaultSky, defaultFog, defaultWater, defaultWaterFog, 8240485, 7909996)));

        addBiome(new BiomeData(EdumiaBiomeKeys.GENSAI_SAKURA_GROVE, EdumiaBiomeDataConfigs.grassPlains, EdumiaBiomeDataConfigs.limestoneLayers,
                new BiomeColorsDTO(defaultSky, 13748853, defaultWater, defaultWaterFog, 12961832, 6989412)));

        addBiome(new BiomeData(EdumiaBiomeKeys.AVELION_FOOTHILLS, EdumiaBiomeDataConfigs.avelionRockShore, EdumiaBiomeDataConfigs.avelionLayers,
                new BiomeColorsDTO(hillySky, defaultFog, defaultWater, defaultWaterFog, 7777673, 7316862)));
        addBiome(new BiomeData(EdumiaBiomeKeys.AVELION_MOUNTAIN_BASE, EdumiaBiomeDataConfigs.avelionRockShore, EdumiaBiomeDataConfigs.avelionLayers,
                new BiomeColorsDTO(hillySky, defaultFog, defaultWater, defaultWaterFog, 7777673, 7316862)));
        addBiome(new BiomeData(EdumiaBiomeKeys.AVELION_MOUNTAIN, EdumiaBiomeDataConfigs.avelionRockShore, EdumiaBiomeDataConfigs.avelionLayers,
                new BiomeColorsDTO(hillySky, defaultFog, defaultWater, defaultWaterFog, 7777673, 7316862)));
        addBiome(new BiomeData(EdumiaBiomeKeys.AVELION_MOUNTAIN_PEAKS, EdumiaBiomeDataConfigs.edumiaMountainsPeaks, EdumiaBiomeDataConfigs.avelionLayers,
                new BiomeColorsDTO(hillySky, defaultFog, defaultWater, defaultWaterFog, 7777673, 7316862)));


        addBiome(new BiomeData(EdumiaBiomeKeys.EDUMIA_FOOTHILLS, EdumiaBiomeDataConfigs.edumiaMountainsBase, EdumiaBiomeDataConfigs.mistyMountainsLayers,
                new BiomeColorsDTO(hillySky, defaultFog, defaultWater, defaultWaterFog, 7777673, 7316862)));
        addBiome(new BiomeData(EdumiaBiomeKeys.EDUMIA_MOUNTAINS_BASE, EdumiaBiomeDataConfigs.edumiaMountainsBase, EdumiaBiomeDataConfigs.mistyMountainsLayers,
                new BiomeColorsDTO(hillySky, defaultFog, defaultWater, defaultWaterFog, 7777673, 7316862)));
        addBiome(new BiomeData(EdumiaBiomeKeys.EDUMIA_MOUNTAINS, EdumiaBiomeDataConfigs.edumiaMountains, EdumiaBiomeDataConfigs.mistyMountainsLayers,
                new BiomeColorsDTO(hillySky, defaultFog, defaultWater, defaultWaterFog, 7777673, 7316862)));
        addBiome(new BiomeData(EdumiaBiomeKeys.EDUMIA_MOUNTAINS_PEAKS, EdumiaBiomeDataConfigs.edumiaMountainsPeaks, EdumiaBiomeDataConfigs.mistyMountainsLayers,
                new BiomeColorsDTO(hillySky, defaultFog, defaultWater, defaultWaterFog, 7777673, 7316862)));

        addBiome(new BiomeData(EdumiaBiomeKeys.DEMON_WASTELANDS, EdumiaBiomeDataConfigs.ashenDirt, EdumiaBiomeDataConfigs.volcanicRockLayers,
                new BiomeColorsDTO(5460048, 4999240, 5860962, 731161, 6252369, 4735297)));

        addBiome(new BiomeData(EdumiaBiomeKeys.GENSAI_VOLCANO_PLAINS, EdumiaBiomeDataConfigs.gensaiVolcano, EdumiaBiomeDataConfigs.volcanicRockLayers,
                new BiomeColorsDTO(5460048, 4999240, 5860962, 731161, 6252369, 4735297)));
        addBiome(new BiomeData(EdumiaBiomeKeys.MOUNT_TITLEIST_FOOT, EdumiaBiomeDataConfigs.gensaiVolcano, EdumiaBiomeDataConfigs.volcanicRockLayers,
                new BiomeColorsDTO(5460048, 4999240, 5860962, 731161, 6252369, 4735297)));
        addBiome(new BiomeData(EdumiaBiomeKeys.MOUNT_TITLEIST, EdumiaBiomeDataConfigs.gensaiVolcano, EdumiaBiomeDataConfigs.volcanicRockLayers,
                new BiomeColorsDTO(5460048, 4999240, 5860962, 731161, 6252369, 4735297)));
        addBiome(new BiomeData(EdumiaBiomeKeys.MOUNT_TITLEIST_PEAK, EdumiaBiomeDataConfigs.gensaiVolcano, EdumiaBiomeDataConfigs.volcanicRockLayers,
                new BiomeColorsDTO(5460048, 4999240, 5860962, 731161, 6252369, 4735297)));
        addBiome(new BiomeData(EdumiaBiomeKeys.MOUNT_TITLEIST_CRATER, EdumiaBiomeDataConfigs.gensaiVolcano, EdumiaBiomeDataConfigs.volcanicRockLayers,
                new BiomeColorsDTO(5460048, 4999240, 5860962, 731161, 6252369, 4735297)));

        addBiome(new BiomeData(EdumiaBiomeKeys.MYRWOOD_JUNGLE, EdumiaBiomeDataConfigs.grassPlains, EdumiaBiomeDataConfigs.stoneLayers,
                new BiomeColorsDTO(10599654, 10664914, 7856375, 329011, 7514630, 7451654)));
        addBiome(new BiomeData(EdumiaBiomeKeys.MYRWOOD_JUNGLE_CLEARING, EdumiaBiomeDataConfigs.grassPlains, EdumiaBiomeDataConfigs.stoneLayers,
                new BiomeColorsDTO(10599654, 10664914, 7856375, 329011, 7514630, 7451654)));
        addBiome(new BiomeData(EdumiaBiomeKeys.MYRWOOD_JUNGLE_PLATEAU, EdumiaBiomeDataConfigs.grassPlains, EdumiaBiomeDataConfigs.stoneLayers,
                new BiomeColorsDTO(10599654, 10664914, 7856375, 329011, 7514630, 7451654)));

        addBiome(new BiomeData(EdumiaBiomeKeys.MYRWOOD_MANGROVE, EdumiaBiomeDataConfigs.pond, EdumiaBiomeDataConfigs.stoneLayers,
                new BiomeColorsDTO(10599654, 10664914, 7856375, 329011, 7514630, 7451654)));
        addBiome(new BiomeData(EdumiaBiomeKeys.MYRWOOD_FLOODED_MANGROVE, EdumiaBiomeDataConfigs.pond, EdumiaBiomeDataConfigs.stoneLayers,
                new BiomeColorsDTO(10599654, 10664914, 7856375, 329011, 7514630, 7451654)));
        addBiome(new BiomeData(EdumiaBiomeKeys.MYRWOOD_COAST, EdumiaBiomeDataConfigs.pond, EdumiaBiomeDataConfigs.stoneLayers,
                new BiomeColorsDTO(10599654, 10664914, 7856375, 329011, 7514630, 7451654)));

        addBiome(new BiomeData(EdumiaBiomeKeys.TAIGA_FOREST, EdumiaBiomeDataConfigs.grassPlains, EdumiaBiomeDataConfigs.stoneLayers,
                new BiomeColorsDTO(7508201, 10863086, defaultWater, defaultWaterFog, 8302697, 7252827)));

        addBiome(new BiomeData(EdumiaBiomeKeys.GENSAI_JUNGLE, EdumiaBiomeDataConfigs.grassPlains, EdumiaBiomeDataConfigs.stoneLayers,
                new BiomeColorsDTO(6785744, 10004675, 4421513, 402733, 2311707, 2050588)));


    }

    private static void loadGenericPonds(){
        addBiome(new BiomeData(EdumiaBiomeKeys.POND, EdumiaBiomeDataConfigs.pond, EdumiaBiomeDataConfigs.stoneLayers,
                new BiomeColorsDTO(waterSky, defaultFog, 4290786, defaultWaterFog, 7583083, 6592339)));
        addBiome(new BiomeData(EdumiaBiomeKeys.OASIS, EdumiaBiomeDataConfigs.beach, EdumiaBiomeDataConfigs.sandstoneLayers,
                new BiomeColorsDTO(desertSky, desertSkyFog, 5407446, 1120828, 7253092, 6592350)));
        addBiome(new BiomeData(EdumiaBiomeKeys.WASTE_POND, EdumiaBiomeDataConfigs.ashenDirt, EdumiaBiomeDataConfigs.stoneLayers,
                new BiomeColorsDTO(8163746, 10926783, 5860963, 863008, 4020033, 2695710)));
        addBiome(new BiomeData(EdumiaBiomeKeys.FAIRY_SWAMP, EdumiaBiomeDataConfigs.pond, EdumiaBiomeDataConfigs.stoneLayers,
                new BiomeColorsDTO(6981536, 8821922, 7111535, 1458241, 4546876, 4284215)));
        addBiome(new BiomeData(EdumiaBiomeKeys.FROZEN_POND, EdumiaBiomeDataConfigs.beach, EdumiaBiomeDataConfigs.limestoneLayers,
                new BiomeColorsDTO(8628223, 10599910, 3750089, 263470, 3494723, 4478280)));
    }

    private static void loadGenericRivers(){
        addBiome(new BiomeData(EdumiaBiomeKeys.RIVER, EdumiaBiomeDataConfigs.river, EdumiaBiomeDataConfigs.stoneLayers,
                new BiomeColorsDTO(waterSky, defaultFog, 4290790, defaultWaterFog, 7583083, 6592339)));
        addBiome(new BiomeData(EdumiaBiomeKeys.GREAT_RIVER, EdumiaBiomeDataConfigs.river, EdumiaBiomeDataConfigs.stoneLayers,
                new BiomeColorsDTO(waterSky, 12638463, 6853316, 6853316, 10995507, 7181907)));
        addBiome(new BiomeData(EdumiaBiomeKeys.MOUNTAIN_STREAM, EdumiaBiomeDataConfigs.river, EdumiaBiomeDataConfigs.stoneLayers,
                new BiomeColorsDTO(waterSky, defaultFog, 4290786, defaultWaterFog, 7583083, 6592339)));
    }

    private static void loadGenericOceans(){
        addBiome(new BiomeData(EdumiaBiomeKeys.OCEAN, EdumiaBiomeDataConfigs.ocean, EdumiaBiomeDataConfigs.stoneLayers,
                new BiomeColorsDTO(
                waterSky, defaultFog, defaultOceanWater, defaultOceanWaterFog, 7576434, 6588506)));
        addBiome(new BiomeData(EdumiaBiomeKeys.OCEAN_COAST, EdumiaBiomeDataConfigs.ocean, EdumiaBiomeDataConfigs.stoneLayers,
                new BiomeColorsDTO(8104447, defaultFog, defaultCoastWater, defaultOceanWaterFog, 7971954, 6590810)));
        addBiome(new BiomeData(EdumiaBiomeKeys.GENSAI_REEF, EdumiaBiomeDataConfigs.beach, EdumiaBiomeDataConfigs.limestoneLayers,
                new BiomeColorsDTO(waterSky, 12638463, 4159204, defaultWaterFog, 10995507, 7181907)));
        addBiome(new BiomeData(EdumiaBiomeKeys.FROZEN_OCEAN, EdumiaBiomeDataConfigs.beach, EdumiaBiomeDataConfigs.limestoneLayers,
                new BiomeColorsDTO(8628223, 10599910, 3750089, 263470, 3494723, 4478280)));
    }
}
