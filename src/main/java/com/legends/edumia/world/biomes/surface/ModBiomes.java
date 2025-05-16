package com.legends.edumia.world.biomes.surface;


import com.legends.edumia.world.biomes.BiomeColorsDTO;
import com.legends.edumia.world.biomes.EdumiaBiomeKeys;
import com.legends.edumia.world.biomes.caves.ModCaveBiomes;
import com.legends.edumia.world.placedfeatures.biomes.OgreBiomePlacedFeatures;
import com.legends.edumia.world.placedfeatures.ocean.ReefPlacedFeatures;
import com.legends.edumia.world.placedfeatures.trees.TemperateTreePlacedFeatures;
import com.legends.edumia.world.placedfeatures.trees.TropicalTreePlacedFeatures;
import com.legends.edumia.world.spawners.ModSpawnSettingsBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ModBiomes {
    private static List<ResourceKey<PlacedFeature>> rawGeneration = new ArrayList<>();
    private static List<ResourceKey<PlacedFeature>> surfaceStructures = new ArrayList<>();

    private static List<ResourceKey<PlacedFeature>> vegetation = new ArrayList<>();
    private static ArrayList<ResourceKey<PlacedFeature>> undergroundOres = new ArrayList<>();
    private static ArrayList<ResourceKey<PlacedFeature>> topLayer = new ArrayList<>();

    public static void bootstrap(BootstrapContext<Biome> context) {
        ModCaveBiomes.bootstrap(context);
        createEdumiaValesBiome(context, EdumiaBiomeKeys.EDUMIA_VALES);
        createEdumiaSavannahBiome(context, EdumiaBiomeKeys.EDUMIA_SAVANNAH);
        createTestBiome(context, EdumiaBiomeKeys.DEAD_MARSHES);
        createTestBiome(context, EdumiaBiomeKeys.DEAD_MARSHES_WATER);
        createDarkElvenWoodBiome(context, EdumiaBiomeKeys.DARK_ELF_FOREST);



        createTundraBiome(context, EdumiaBiomeKeys.EDUMIA_TUNDRA);
        createFrozenOceanBiome(context, EdumiaBiomeKeys.FROZEN_OCEAN);
        createFrozenPond(context, EdumiaBiomeKeys.FROZEN_POND);
        createTaigaBiome(context, EdumiaBiomeKeys.TAIGA_FOREST, true);
        createTaigaBiome(context, EdumiaBiomeKeys.TUNDRA_BUSH, true);

        createOrcDesertBiome(context, EdumiaBiomeKeys.ORC_DESERT);
        createRedOrcDesertBiome(context, EdumiaBiomeKeys.RED_ORC_DESERT);

        createAvelionBiome(context, EdumiaBiomeKeys.AVELION_PLAINS, 0);
        createAvelionBiome(context, EdumiaBiomeKeys.AVELION_FOREST, 3);
        createAvelionBiome(context, EdumiaBiomeKeys.AVELION_HIDDEN_BLOSSOM, 4);
        createAvelionBiome(context, EdumiaBiomeKeys.AVELION_MEADOW, 5);
        createAvelionBiome(context, EdumiaBiomeKeys.AVELION_SANDY_SHORES, 2);
        createAvelionBiome(context, EdumiaBiomeKeys.AVELION_ROCKY_SHORES, 1);
        createEdumiaMountainBiome(context, EdumiaBiomeKeys.AVELION_FOOTHILLS, false);
        createEdumiaMountainBiome(context, EdumiaBiomeKeys.AVELION_MOUNTAIN_BASE, false);
        createEdumiaMountainBiome(context, EdumiaBiomeKeys.AVELION_MOUNTAIN, false);
        createEdumiaMountainBiome(context, EdumiaBiomeKeys.AVELION_MOUNTAIN_PEAKS, true);

        createOgreForestBiome(context, EdumiaBiomeKeys.OGRE_FOREST);
        createMyrwoodSwampBiome(context, EdumiaBiomeKeys.FAIRY_SWAMP);
        createRiverBiome(context, EdumiaBiomeKeys.GREAT_RIVER);


        createEdumiaMountainBiome(context, EdumiaBiomeKeys.EDUMIA_FOOTHILLS, false);
        createEdumiaMountainBiome(context, EdumiaBiomeKeys.EDUMIA_MOUNTAINS_BASE, false);
        createEdumiaMountainBiome(context, EdumiaBiomeKeys.EDUMIA_MOUNTAINS, false);
        createEdumiaMountainBiome(context, EdumiaBiomeKeys.EDUMIA_MOUNTAINS_PEAKS, true);


        createGensaiVolcanoBiome(context, EdumiaBiomeKeys.GENSAI_VOLCANO_PLAINS);
        createMountTitleistBiome(context, EdumiaBiomeKeys.MOUNT_TITLEIST_FOOT);
        createMountTitleistBiome(context, EdumiaBiomeKeys.MOUNT_TITLEIST);
        createMountTitleistBiome(context, EdumiaBiomeKeys.MOUNT_TITLEIST_PEAK);
        createMountTitleistBiome(context, EdumiaBiomeKeys.MOUNT_TITLEIST_CRATER);

        createGensaiBeachBiome(context, EdumiaBiomeKeys.GENSAI_BEACH);
        createGensaiBeachBiome(context, EdumiaBiomeKeys.GENSAI_ROCKY_COAST);
        createGensaiNorthernPlainsBiome(context, EdumiaBiomeKeys.GENSAI_NORTHERN_PLAINS, 0);
        createGensaiReefBiome(context, EdumiaBiomeKeys.GENSAI_REEF);
        createGensaiJungleBiome(context, EdumiaBiomeKeys.GENSAI_JUNGLE);
        createGensaiSakuraGroveBiome(context, EdumiaBiomeKeys.GENSAI_SAKURA_GROVE);

        createMirwoodForestBiome(context, EdumiaBiomeKeys.MYRWOOD_JUNGLE, 0);
        createMirwoodForestBiome(context, EdumiaBiomeKeys.MYRWOOD_JUNGLE_CLEARING, 1);
        createMirwoodForestBiome(context, EdumiaBiomeKeys.MYRWOOD_JUNGLE_PLATEAU, 0);

        createMirwoodMangroveBiome(context, EdumiaBiomeKeys.MYRWOOD_MANGROVE);
        createMirwoodMangroveBiome(context, EdumiaBiomeKeys.MYRWOOD_FLOODED_MANGROVE);
        createMirwoodMangroveBiome(context, EdumiaBiomeKeys.MYRWOOD_COAST);

        createDemonWastelandsBiome(context, EdumiaBiomeKeys.DEMON_WASTELANDS);

        createOasisBiome(context, EdumiaBiomeKeys.OASIS);
        createOceanBiome(context, EdumiaBiomeKeys.OCEAN);
        createOceanCoastBiome(context, EdumiaBiomeKeys.OCEAN_COAST);
        createPondBiome(context, EdumiaBiomeKeys.POND);
        createRiverBiome(context, EdumiaBiomeKeys.RIVER);
        createRiverBiome(context, EdumiaBiomeKeys.MOUNTAIN_STREAM);
        createWastePondBiome(context, EdumiaBiomeKeys.WASTE_POND);


    }
    public static void createMirwoodForestBiome(BootstrapContext<Biome> context, ResourceKey<Biome> biomeResourceKey, int step) {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        ModSpawnSettingsBuilder.addFairies(spawnSettings);
        ModSpawnSettingsBuilder.addButterfly(spawnSettings);
        ModSpawnSettingsBuilder.addTropicalAnimals(spawnSettings);
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE),
                context.lookup(Registries.CONFIGURED_CARVER));
    if (step == 0) {
        ModBiomeFeatures.addMyrwoodTrees(vegetation);
        ModBiomeFeatures.addJungleGrass(vegetation);
        ModBiomeFeatures.addParasolMushroom(vegetation);
    }else if(step == 1){
        ModBiomeFeatures.addJungleGrass(vegetation);
        ModBiomeFeatures.addHibiscus(vegetation);
    }


        registerBiome(context, biomeResourceKey, spawnSettings, generationSettings);
    }
    public static void createMirwoodMangroveBiome(BootstrapContext<Biome> context, ResourceKey<Biome> biomeResourceKey) {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        ModSpawnSettingsBuilder.addFairies(spawnSettings);
        ModSpawnSettingsBuilder.addTropicalAnimals(spawnSettings);
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE),
                context.lookup(Registries.CONFIGURED_CARVER));

        vegetation.add(VegetationPlacements.TREES_MANGROVE);
        vegetation.add(VegetationPlacements.PATCH_SUGAR_CANE_SWAMP);
        ModBiomeFeatures.addReeds(vegetation);
        ModBiomeFeatures.addPapyrus(vegetation);
        ModBiomeFeatures.addDisks(undergroundOres);


        registerBiome(context, biomeResourceKey, spawnSettings, generationSettings);
    }

    public static void createDemonWastelandsBiome(BootstrapContext<Biome> context, ResourceKey<Biome> biomeResourceKey) {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        //ModSpawnSettingsBuilder.addFarmAnimals(spawnSettings);
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE),
                context.lookup(Registries.CONFIGURED_CARVER));

        ModBiomeFeatures.addBurnedTrees(vegetation);

        ModBiomeFeatures.addGravelOre(vegetation);

        registerBiome(context, biomeResourceKey, spawnSettings, generationSettings);
    }
    public static void createEdumiaValesBiome(BootstrapContext<Biome> context, ResourceKey<Biome> biomeResourceKey) {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        //ModSpawnSettingsBuilder.addFarmAnimals(spawnSettings);
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE),
                context.lookup(Registries.CONFIGURED_CARVER));

        addDefaultVegetation(generationSettings);
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addOakBushes(vegetation);
        ModBiomeFeatures.addVeryRareBirchTrees(vegetation);
        ModBiomeFeatures.addSparseLarchTrees(vegetation);
        ModBiomeFeatures.addMapleTrees(vegetation);

        registerBiome(context, biomeResourceKey, spawnSettings, generationSettings);
    }

    public static void createEdumiaSavannahBiome(BootstrapContext<Biome> context, ResourceKey<Biome> biomeResourceKey) {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE),
                context.lookup(Registries.CONFIGURED_CARVER));

        ModBiomeFeatures.addAcaciaTrees(vegetation);
        addDefaultVegetation(generationSettings);
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addOakBushes(vegetation);

        registerBiome(context, biomeResourceKey, spawnSettings, generationSettings);
    }

    public static void createGensaiNorthernPlainsBiome(BootstrapContext<Biome> context, ResourceKey<Biome> biomeResourceKey, int key) {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(context.lookup(
                Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        if (key == 0) { //plains
            ModBiomeFeatures.addGravelOre(vegetation);
            ModBiomeFeatures.addOakBushes(vegetation);
            ModBiomeFeatures.addRareCypressTrees(vegetation);
            ModBiomeFeatures.addGrass(vegetation);
            vegetation.add(VegetationPlacements.FOREST_FLOWERS);
        }

        ModBiomeFeatures.addPackedMudOre(vegetation);
        ModBiomeFeatures.addBigStoneBoulder(vegetation);
        ModBiomeFeatures.addBigSandStoneBoulder(vegetation);
        registerBiome(context, biomeResourceKey, spawnSettings, generationSettings);
    }

    public static void createGensaiBeachBiome(BootstrapContext<Biome> context, ResourceKey<Biome> biomeResourceKey) {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();

        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(context.lookup(
                Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        vegetation.add(VegetationPlacements.PATCH_CACTUS_DESERT);
        ModBiomeFeatures.addGraniteBoulder(vegetation);
        ModBiomeFeatures.addSandStoneBoulders(vegetation);
        ModBiomeFeatures.addStoneBoulder(vegetation);
        ModBiomeFeatures.addWhiteSandLayers(topLayer);
        ModBiomeFeatures.addGravelOre(vegetation);

        ModBiomeFeatures.addPalmTrees(vegetation);

        registerBiome(context, biomeResourceKey, spawnSettings, generationSettings);
    }
    public static void createDarkElvenWoodBiome(BootstrapContext<Biome> context, ResourceKey<Biome> biomeResourceKey) {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        addDefaultVegetation(generationSettings);
        vegetation.add(VegetationPlacements.PATCH_TALL_GRASS_2);
        ModBiomeFeatures.addMossyBoulder(vegetation);
        ModBiomeFeatures.addGravelOre(vegetation);

        ModBiomeFeatures.addDarkElvenOakTrees(vegetation);


        registerBiome(context, biomeResourceKey, spawnSettings, generationSettings);
    }
    public static void createTundraBiome(BootstrapContext<Biome> context, ResourceKey<Biome> biomeResourceKey) {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        addTundraVegetation(generationSettings);
        ModBiomeFeatures.addSnowLayers(topLayer);



        registerBiome(context, biomeResourceKey, spawnSettings, generationSettings, -0.8f,  true);
    }
    public static void createFrozenOceanBiome(BootstrapContext<Biome> context, ResourceKey<Biome> biomeResourceKey) {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        //ModSpawnSettingsBuilder.addOceanAnimals(spawnSettings);
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        addOceanVegetation(generationSettings);

        registerBiome(context, biomeResourceKey, spawnSettings, generationSettings);
    }
    public static void createOceanBiome(BootstrapContext<Biome> context, ResourceKey<Biome> biomeResourceKey) {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        //ModSpawnSettingsBuilder.addOceanAnimals(spawnSettings);
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        addOceanVegetation(generationSettings);

        registerBiome(context, biomeResourceKey, spawnSettings, generationSettings);
    }
    public static void createFrozenPond(BootstrapContext<Biome> context, ResourceKey<Biome> biomeResourceKey) {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        ModBiomeFeatures.addDisks(undergroundOres);

        vegetation.add(AquaticPlacements.KELP_COLD);
        vegetation.add(AquaticPlacements.SEAGRASS_NORMAL);
        vegetation.add(VegetationPlacements.PATCH_GRASS_FOREST);

        registerBiome(context, biomeResourceKey, spawnSettings, generationSettings, -0.1f, true);
    }
    public static void createOrcDesertBiome(BootstrapContext<Biome> context, ResourceKey<Biome> biomeResourceKey) {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        addOrcDesertVegetation(generationSettings);
        vegetation.add(VegetationPlacements.PATCH_DEAD_BUSH_2);

        registerBiome(context, biomeResourceKey, spawnSettings, generationSettings, 0.8f, false);
    }

    public static void createRedOrcDesertBiome(BootstrapContext<Biome> context, ResourceKey<Biome> biomeResourceKey) {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        vegetation.add(VegetationPlacements.PATCH_DEAD_BUSH_2);
        ModBiomeFeatures.addSandPath(rawGeneration);

        registerBiome(context, biomeResourceKey, spawnSettings, generationSettings, 0.8f, false);
    }

    public static void createAvelionBiome(BootstrapContext<Biome> context, ResourceKey<Biome> biomeResourceKey, int step) {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        ModSpawnSettingsBuilder.addFarmAnimals(spawnSettings);
        ModSpawnSettingsBuilder.addPlainAnimals(spawnSettings);

        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        ModBiomeFeatures.addDisks(undergroundOres);
        ModBiomeFeatures.addGrass(vegetation);
        vegetation.add(VegetationPlacements.FLOWER_DEFAULT);
        vegetation.add(VegetationPlacements.BROWN_MUSHROOM_NORMAL);
        vegetation.add(VegetationPlacements.RED_MUSHROOM_NORMAL);
        vegetation.add(VegetationPlacements.PATCH_SUGAR_CANE);
        vegetation.add(VegetationPlacements.PATCH_PUMPKIN);
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addOakBushes(vegetation);
        ModBiomeFeatures.addLimestoneBoulder(vegetation);

        if (step == 0){
            ModSpawnSettingsBuilder.addHighElves(spawnSettings);
            ModBiomeFeatures.addVeryRareBirchTrees(vegetation);
            ModBiomeFeatures.addHeather(vegetation);

        }else if (step == 1){
            ModBiomeFeatures.addCalciteBoulder(vegetation);
            ModBiomeFeatures.addDioriteBoulder(vegetation);
        } else if (step == 2){
            ModBiomeFeatures.addDioriteBoulder(vegetation);
        }else if (step == 3){
            ModSpawnSettingsBuilder.addHighElves(spawnSettings);
            vegetation.add(VegetationPlacements.TREES_PLAINS);
            ModBiomeFeatures.addMegaBirchTrees(vegetation);
            ModBiomeFeatures.addRareBirchTrees(vegetation);
            ModBiomeFeatures.addRareMegaOakTrees(vegetation);
            ModBiomeFeatures.addCommonBeechTrees(vegetation);
            ModBiomeFeatures.addCommonOakTrees(vegetation);
        }else if (step == 4) {     //Blossom
            ModSpawnSettingsBuilder.addHighElves(spawnSettings);
            ModSpawnSettingsBuilder.addButterfly(spawnSettings);
            ModBiomeFeatures.addOakTrees(vegetation);
        }else if (step == 5){      //Meadow
            ModSpawnSettingsBuilder.addButterfly(spawnSettings);
            ModSpawnSettingsBuilder.addHighElves(spawnSettings);
            vegetation.add(VegetationPlacements.TREES_PLAINS);
        }


        ModBiomeFeatures.addElvenCrystal(undergroundOres);

        registerBiome(context, biomeResourceKey, spawnSettings, generationSettings);
    }
    public static void createGensaiSakuraGroveBiome(BootstrapContext<Biome> context, ResourceKey<Biome> biomeResourceKey) {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        vegetation.add(TemperateTreePlacedFeatures.GENSAI_SAKURA_GROVE_TREES);
        ModBiomeFeatures.addGensaiOrchid(vegetation);

        addSakuraGroveVegetation(generationSettings);


        registerBiome(context, biomeResourceKey, spawnSettings, generationSettings);
    }
    public static void createMyrwoodSwampBiome(BootstrapContext<Biome> context, ResourceKey<Biome> biomeResourceKey) {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        ModSpawnSettingsBuilder.addDragonfly(spawnSettings);
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        addDarkElvenVegetation(generationSettings);
        ModBiomeFeatures.addWaterDelta(vegetation);
        ModBiomeFeatures.addReedsFoliage(vegetation);
        ModBiomeFeatures.addMudOre(vegetation);

        ModBiomeFeatures.addWillowTrees(vegetation);

        registerBiome(context, biomeResourceKey, spawnSettings, generationSettings);
    }
    public static void createAvelionMountainBiome(BootstrapContext<Biome> context, ResourceKey<Biome> biomeResourceKey, boolean foothills) {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        float temperature = -0.6f;
        addNordicVegetation(generationSettings);
        ModBiomeFeatures.addTuffOre(vegetation);
        if(foothills) {
            temperature = 0.2f;
            ModBiomeFeatures.addGravelOre(vegetation);
            ModBiomeFeatures.addStoneGrassOre(vegetation);
            ModBiomeFeatures.addCommonLarchTrees(vegetation);
            ModBiomeFeatures.addAbundantPineTrees(vegetation);
            ModBiomeFeatures.addAbundantSpruceTrees(vegetation);
            ModBiomeFeatures.addCommonSpruceBushes(vegetation);
        } else {

        }
        registerBiome(context, biomeResourceKey, spawnSettings, generationSettings,temperature, true);
    }
    public static void createEdumiaMountainBiome(BootstrapContext<Biome> context, ResourceKey<Biome> biomeResourceKey, boolean peaks) {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        float temperature = 0.5f;

        if(peaks) {
            temperature = 0.3f;

        } else {
            addNordicVegetation(generationSettings);
            ModBiomeFeatures.addSparseLarchTrees(vegetation);
            ModBiomeFeatures.addSparsePineTrees(vegetation);
            ModBiomeFeatures.addScarceSpruceTrees(vegetation);
            ModBiomeFeatures.addSpruceBushes(vegetation);
        }
        registerBiome(context, biomeResourceKey, spawnSettings, generationSettings,temperature, true);
    }
    public static void createGensaiVolcanoBiome(BootstrapContext<Biome> context, ResourceKey<Biome> biomeResourceKey) {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        addVolcanoVegetation(generationSettings);
        ModBiomeFeatures.addLavaMagmaLake(generationSettings);

        registerBiome(context, biomeResourceKey, spawnSettings, generationSettings, 0.7f, false);
    }
    public static void createMountTitleistBiome(BootstrapContext<Biome> context, ResourceKey<Biome> biomeResourceKey) {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        addVolcanoVegetation(generationSettings);
        ModBiomeFeatures.addLavaMagmaLake(generationSettings);

        registerBiome(context, biomeResourceKey, spawnSettings, generationSettings, 0.7f, false);
    }
    public static void createOceanCoastBiome(BootstrapContext<Biome> context, ResourceKey<Biome> biomeResourceKey) {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        addOceanVegetation(generationSettings);

        registerBiome(context, biomeResourceKey, spawnSettings, generationSettings);
    }
    public static void createOasisBiome(BootstrapContext<Biome> context, ResourceKey<Biome> biomeResourceKey) {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        ModSpawnSettingsBuilder.addDragonfly(spawnSettings);
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        addOasisVegetation(generationSettings);

        registerBiome(context, biomeResourceKey, spawnSettings, generationSettings);
    }
    public static void createPondBiome(BootstrapContext<Biome> context, ResourceKey<Biome> biomeResourceKey) {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        ModSpawnSettingsBuilder.addDragonfly(spawnSettings);
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        addOceanVegetation(generationSettings);
        ModBiomeFeatures.addAbundantWaterDelta(vegetation);
        ModBiomeFeatures.addRiverSand(undergroundOres);
        ModBiomeFeatures.addWillowTrees(vegetation);
        ModBiomeFeatures.addReeds(vegetation);

        registerBiome(context, biomeResourceKey, spawnSettings, generationSettings);
    }
    public static void createRiverBiome(BootstrapContext<Biome> context, ResourceKey<Biome> biomeResourceKey) {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        ModBiomeFeatures.addRiverDisks(undergroundOres);
        vegetation.add(AquaticPlacements.KELP_COLD);
        vegetation.add(AquaticPlacements.SEAGRASS_NORMAL);
        vegetation.add(VegetationPlacements.PATCH_GRASS_FOREST);
        vegetation.add(VegetationPlacements.FLOWER_DEFAULT);
        vegetation.add(VegetationPlacements.PATCH_SUGAR_CANE);
        vegetation.add(VegetationPlacements.PATCH_PUMPKIN);
        ModBiomeFeatures.addReedsFoliage(vegetation);

        registerBiome(context, biomeResourceKey, spawnSettings, generationSettings);
    }
    public static void createGensaiReefBiome(BootstrapContext<Biome> context, ResourceKey<Biome> biomeResourceKey) {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        //addOceanVegetation(generationSettings);
        addReef(generationSettings);

        registerBiome(context, biomeResourceKey, spawnSettings, generationSettings);
    }
    public static void createTaigaBiome(BootstrapContext<Biome> context, ResourceKey<Biome> biomeResourceKey, boolean trees) {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        addNordicVegetation(generationSettings);
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addOakBushes(vegetation);
        ModBiomeFeatures.addSpruceBushes(vegetation);
        ModBiomeFeatures.addSnowLayers(topLayer);

        if(trees) {
            ModBiomeFeatures.addBirchTrees(vegetation);
            ModBiomeFeatures.addLarchTrees(vegetation);
            ModBiomeFeatures.addOakTrees(vegetation);
            ModBiomeFeatures.addCommonPineTrees(vegetation);
            ModBiomeFeatures.addSpruceTrees(vegetation);
        } else {
            ModBiomeFeatures.addLarchTrees(vegetation);
            ModBiomeFeatures.addSparsePineTrees(vegetation);
            ModBiomeFeatures.addRareSpruceTrees(vegetation);
        }
        registerBiome(context, biomeResourceKey, spawnSettings, generationSettings, -0.8f, true);
    }
    public static void createGensaiJungleBiome(BootstrapContext<Biome> context, ResourceKey<Biome> biomeResourceKey) {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        ModSpawnSettingsBuilder.addTropicalAnimals(spawnSettings);
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        vegetation.add(VegetationPlacements.PATCH_TALL_GRASS_2);
        vegetation.add(VegetationPlacements.PATCH_GRASS_PLAIN);
        vegetation.add(VegetationPlacements.BAMBOO_LIGHT);
        vegetation.add(TropicalTreePlacedFeatures.GENSAI_JUNGLE_TREES);


        registerBiome(context, biomeResourceKey, spawnSettings, generationSettings);
    }
    public static void createWastePondBiome(BootstrapContext<Biome> context, ResourceKey<Biome> biomeResourceKey) {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        ModBiomeFeatures.addDisks(undergroundOres);
        vegetation.add(AquaticPlacements.KELP_WARM);
        vegetation.add(AquaticPlacements.SEAGRASS_NORMAL);
        vegetation.add(VegetationPlacements.PATCH_GRASS_FOREST);
        vegetation.add(VegetationPlacements.PATCH_SUGAR_CANE);
        ModBiomeFeatures.addReedsFoliage(vegetation);

        registerBiome(context, biomeResourceKey, spawnSettings, generationSettings);
    }
    public static void createOgreForestBiome(BootstrapContext<Biome> context, ResourceKey<Biome> biomeResourceKey) {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        vegetation.add(OgreBiomePlacedFeatures.OGRE_FOREST_TREES);

        registerBiome(context, biomeResourceKey, spawnSettings, generationSettings,0.7f, 0.8f, true);
    }
    public static void createTestBiome(BootstrapContext<Biome> context, ResourceKey<Biome> biomeResourceKey) {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        //ModSpawnSettingsBuilder.addRiverAnimals(spawnSettings);
        //ModSpawnSettingsBuilder.addCamel(spawnSettings);
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        //addOasisVegetation(generationSettings);

        registerBiome(context, biomeResourceKey, spawnSettings, generationSettings);
    }


    public static void addDefaultVegetation(BiomeGenerationSettings.Builder generationSettings) {
        ModBiomeFeatures.addDisks(undergroundOres);
        vegetation.add(VegetationPlacements.PATCH_GRASS_FOREST);
        vegetation.add(VegetationPlacements.FLOWER_DEFAULT);
        vegetation.add(VegetationPlacements.BROWN_MUSHROOM_NORMAL);
        vegetation.add(VegetationPlacements.RED_MUSHROOM_NORMAL);
        vegetation.add(VegetationPlacements.PATCH_SUGAR_CANE);
        vegetation.add(VegetationPlacements.PATCH_PUMPKIN);
        ModBiomeFeatures.addStoneBoulder(vegetation);
    }
    public static void addNordicVegetation(BiomeGenerationSettings.Builder generationSettings) {
        ModBiomeFeatures.addDisks(undergroundOres);
        vegetation.add(VegetationPlacements.PATCH_LARGE_FERN);
        vegetation.add(VegetationPlacements.PATCH_GRASS_TAIGA);
        vegetation.add(VegetationPlacements.FLOWER_DEFAULT);
        vegetation.add(VegetationPlacements.BROWN_MUSHROOM_NORMAL);
        vegetation.add(VegetationPlacements.RED_MUSHROOM_NORMAL);
        vegetation.add(VegetationPlacements.PATCH_BERRY_RARE);
        ModBiomeFeatures.addAndesiteBoulder(vegetation);
    }
    public static void addTundraVegetation(BiomeGenerationSettings.Builder generationSettings) {
        ModBiomeFeatures.addDisks(undergroundOres);
    }

    public static void addOceanVegetation(BiomeGenerationSettings.Builder generationSettings) {
        ModBiomeFeatures.addDisks(undergroundOres);
        vegetation.add(AquaticPlacements.KELP_COLD);
        vegetation.add(AquaticPlacements.SEAGRASS_NORMAL);
        vegetation.add(VegetationPlacements.PATCH_GRASS_FOREST);
        vegetation.add(VegetationPlacements.FLOWER_DEFAULT);
        vegetation.add(VegetationPlacements.PATCH_SUGAR_CANE);
        vegetation.add(VegetationPlacements.PATCH_PUMPKIN);
    }
    public static void addOrcDesertVegetation(BiomeGenerationSettings.Builder generationSettings) {
        ModBiomeFeatures.addDisks(undergroundOres);
        vegetation.add(VegetationPlacements.PATCH_SUGAR_CANE);
        vegetation.add(VegetationPlacements.PATCH_GRASS_SAVANNA);
        ModBiomeFeatures.addSandStoneBoulders(vegetation);
    }
    public static void addSakuraGroveVegetation(BiomeGenerationSettings.Builder generationSettings) {
        ModBiomeFeatures.addDisks(undergroundOres);
        vegetation.add(VegetationPlacements.PATCH_GRASS_FOREST);
        vegetation.add(VegetationPlacements.FLOWER_MEADOW);
        vegetation.add(VegetationPlacements.BROWN_MUSHROOM_NORMAL);
        vegetation.add(VegetationPlacements.RED_MUSHROOM_NORMAL);
        vegetation.add(VegetationPlacements.PATCH_SUGAR_CANE);
        vegetation.add(VegetationPlacements.PATCH_PUMPKIN);
        vegetation.add(VegetationPlacements.TREES_PLAINS);
        ModBiomeFeatures.addGravelOre(vegetation);
        ModBiomeFeatures.addLimestoneBoulder(vegetation);
        ModBiomeFeatures.addWhiteAshBushes(vegetation);
        ModBiomeFeatures.addOakBushes(vegetation);
    }
    public static void addDarkElvenVegetation(BiomeGenerationSettings.Builder generationSettings) {
        ModBiomeFeatures.addDisks(undergroundOres);
        vegetation.add(VegetationPlacements.PATCH_GRASS_FOREST);
        vegetation.add(VegetationPlacements.PATCH_TALL_GRASS_2);
        vegetation.add(VegetationPlacements.PATCH_LARGE_FERN);
        vegetation.add(VegetationPlacements.PATCH_GRASS_TAIGA);
        vegetation.add(VegetationPlacements.BROWN_MUSHROOM_NORMAL);
        vegetation.add(VegetationPlacements.RED_MUSHROOM_NORMAL);
        vegetation.add(VegetationPlacements.PATCH_BERRY_RARE);
        ModBiomeFeatures.addOakBushes(vegetation);
        ModBiomeFeatures.addMossyBoulder(vegetation);
    }
    public static void addVolcanoVegetation(BiomeGenerationSettings.Builder generationSettings) {
        ModBiomeFeatures.addDisks(undergroundOres);
    }
    public static void addOasisVegetation(BiomeGenerationSettings.Builder generationSettings) {
        ModBiomeFeatures.addDisks(undergroundOres);
        vegetation.add(AquaticPlacements.KELP_WARM);
        vegetation.add(AquaticPlacements.SEAGRASS_WARM);
        vegetation.add(VegetationPlacements.PATCH_GRASS_SAVANNA);
        vegetation.add(VegetationPlacements.FLOWER_DEFAULT);
        vegetation.add(VegetationPlacements.PATCH_SUGAR_CANE);
        vegetation.add(VegetationPlacements.PATCH_PUMPKIN);

    }
    public static void addReef(BiomeGenerationSettings.Builder generationSettings) {
        vegetation.add(ReefPlacedFeatures.REEF_KEY);
    }


    public static void registerBiome(BootstrapContext<Biome> context, ResourceKey<Biome>  biomeResourceKey,  MobSpawnSettings.Builder spawnSettings,
                                    BiomeGenerationSettings.Builder generationSettings, float downfall) {
        registerBiome(context, biomeResourceKey, spawnSettings, generationSettings, 0.5f, downfall, true);
    }
    public static void registerBiome(BootstrapContext<Biome> context, ResourceKey<Biome>  biomeResourceKey,  MobSpawnSettings.Builder spawnSettings,
                                    BiomeGenerationSettings.Builder generationSettings, float temperature, boolean precipitation) {
        registerBiome(context, biomeResourceKey, spawnSettings, generationSettings, temperature, 0.5f, precipitation);
    }
    public static void registerBiome(BootstrapContext<Biome> context, ResourceKey<Biome>  biomeResourceKey,  MobSpawnSettings.Builder spawnSettings,
                                    BiomeGenerationSettings.Builder generationSettings) {
        registerBiome(context, biomeResourceKey, spawnSettings, generationSettings, 0.5f, 0.5f, true);
    }
    public static void registerBiome(BootstrapContext<Biome> context, ResourceKey<Biome>  biomeResourceKey, MobSpawnSettings.Builder spawnSettings,
                                    BiomeGenerationSettings.Builder generationSettings, float temperature, float downfall,
                                    boolean precipitation, boolean... removeDefaultOres) {
        if (removeDefaultOres.length == 0){
            undergroundOres.add(OrePlacements.ORE_DIRT);
            undergroundOres.add(OrePlacements.ORE_GRAVEL);
            undergroundOres.add(OrePlacements.ORE_GRANITE_UPPER);
            undergroundOres.add(OrePlacements.ORE_GRANITE_LOWER);
            undergroundOres.add(OrePlacements.ORE_DIORITE_UPPER);
            undergroundOres.add(OrePlacements.ORE_DIORITE_LOWER);
            undergroundOres.add(OrePlacements.ORE_ANDESITE_UPPER);
            undergroundOres.add(OrePlacements.ORE_ANDESITE_LOWER);
            undergroundOres.add(OrePlacements.ORE_TUFF);
        }

        undergroundOres.add(OrePlacements.ORE_COAL_UPPER);
        vegetation.add(CavePlacements.GLOW_LICHEN);

        BiomeDefaultFeatures.addSurfaceFreezing(generationSettings);

        surfaceStructures = surfaceStructures.stream().sorted(Comparator.comparing(a -> a.location().toString())).toList();
        vegetation = vegetation.stream().sorted(Comparator.comparing(a -> a.location().toString())).toList();
        for (int i = 0; i < vegetation.size() - 1; i++){
            if (vegetation.get(i).location().toString().equals(vegetation.get(i + 1).location().toString())){
                throw new IllegalStateException("Duplicate value in list for: " + vegetation.get(i).location().toString());
            }
        }
        for (ResourceKey<PlacedFeature> feature : rawGeneration){
            generationSettings.addFeature(GenerationStep.Decoration.RAW_GENERATION, feature);
        }
        for (ResourceKey<PlacedFeature> feature : surfaceStructures){
            generationSettings.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, feature);
        }
        for (ResourceKey<PlacedFeature> feature: vegetation) {
            generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, feature);
        }
        for (ResourceKey<PlacedFeature> feature: undergroundOres.stream().sorted(Comparator.comparing(a -> a.location().toString())).toList()) {
            generationSettings.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, feature);
        }
        for (ResourceKey<PlacedFeature> feature: topLayer.stream().sorted(Comparator.comparing(a -> a.location().toString())).toList()){
            generationSettings.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, feature);
        }

        BiomeColorsDTO biomeColorsDTO = MapBiomeData.getBiome(biomeResourceKey).getBiomeColors();

        Biome biome = (new Biome.BiomeBuilder())
                .hasPrecipitation(precipitation)
                .temperature(temperature)
                .downfall(downfall)
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .skyColor(biomeColorsDTO.skyColor)
                        .fogColor(biomeColorsDTO.fogColor)
                        .waterColor(biomeColorsDTO.waterColor)
                        .waterFogColor(biomeColorsDTO.waterFogColor)
                        .grassColorOverride(biomeColorsDTO.grassColor)
                        .foliageColorOverride(biomeColorsDTO.foliageColor)
                        .build())
                .mobSpawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();

        context.register(biomeResourceKey, biome);

        rawGeneration = new ArrayList<>();
        surfaceStructures = new ArrayList<>();
        vegetation = new ArrayList<>();
        undergroundOres = new ArrayList<>();
        topLayer = new ArrayList<>();
    }
}
