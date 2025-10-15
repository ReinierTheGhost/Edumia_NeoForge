package com.legends.edumia.world.biomes;


import com.legends.edumia.core.blocksets.SandBlockSets;
import com.legends.edumia.core.blocksets.StoneSets;
import com.legends.edumia.core.BlockLoader;
import net.minecraft.world.level.block.Blocks;

public class EdumiaBiomeDataConfigs {
    // region Slopes
    // defines the surface blocks (4 blocks depth)
    private static final int MAX_ANGLE = 90;

    public static SlopeMap ashenDirt = new SlopeMap()
            .addSlopeData(25, () -> BlockLoader.VOLCANIC_DIRT.get())
            .addSlopeData(32, () -> SandBlockSets.VOLCANIC_GRAVEL.block().get())
            .addSlopeData(MAX_ANGLE, () -> StoneSets.VOLCANIC_ROCK.block().get());



    public static SlopeMap gensaiVolcano = new SlopeMap()
            .addSlopeData(30, () -> StoneSets.VOLCANIC_ROCK.block().get())
            .addSlopeData(44, Blocks.BASALT)
            .addSlopeData(MAX_ANGLE, Blocks.BLACKSTONE);
    public static SlopeMap mistyMountains = new SlopeMap()
            .addSlopeData(13, Blocks.GRASS_BLOCK)
            .addSlopeData(16, Blocks.COARSE_DIRT)
            .addSlopeData(32, Blocks.STONE)
            .addSlopeData(48, Blocks.TUFF)
            .addSlopeData(MAX_ANGLE, Blocks.BASALT);

    public static SlopeMap edumiaMountainsBase = new SlopeMap()
            .addSlopeData(13, Blocks.GRASS_BLOCK)
            .addSlopeData(30, Blocks.GRASS_BLOCK)
            .addSlopeData(33, Blocks.COARSE_DIRT)
            .addSlopeData(34, Blocks.GRAVEL)
            .addSlopeData(37, Blocks.ANDESITE)
            .addSlopeData(40, () -> StoneSets.UMBRAROCK.block().get())
            .addSlopeData(45, Blocks.TUFF)
            .addSlopeData(47, () -> StoneSets.EATHERITE.block().get())
            .addSlopeData(51, () -> StoneSets.SKYSHALE.block().get())
            .addSlopeData(MAX_ANGLE, Blocks.COBBLED_DEEPSLATE);

    public static SlopeMap edumiaMountains = new SlopeMap()
            .addSlopeData(13, Blocks.GRASS_BLOCK)
            .addSlopeData(30, Blocks.GRASS_BLOCK)
            .addSlopeData(33, Blocks.COARSE_DIRT)
            .addSlopeData(34, Blocks.GRAVEL)
            .addSlopeData(37, Blocks.ANDESITE)
            .addSlopeData(40, () -> StoneSets.UMBRAROCK.block().get())
            .addSlopeData(45, Blocks.TUFF)
            .addSlopeData(55, () -> StoneSets.EATHERITE.block().get())
            .addSlopeData(67, () -> StoneSets.SKYSHALE.block().get())
            .addSlopeData(MAX_ANGLE, Blocks.COBBLED_DEEPSLATE);

    public static SlopeMap edumiaMountainsPeaks = new SlopeMap()
            .addSlopeData(25, Blocks.SNOW_BLOCK)
            .addSlopeData(MAX_ANGLE, Blocks.SNOW_BLOCK);

    public static SlopeMap ogreMountains = new SlopeMap()
            .addSlopeData(13, () -> SandBlockSets.WHITE_SAND.block().get())
            .addSlopeData(30, () -> SandBlockSets.WHITE_SAND.block().get())
            .addSlopeData(33, () -> StoneSets.LIMESTONE.block().get())
            .addSlopeData(34, () -> StoneSets.CHALK.block().get())
            .addSlopeData(37, () -> StoneSets.CACHOLONG.block().get())
            .addSlopeData(MAX_ANGLE, Blocks.CALCITE);

    public static SlopeMap grassPlains = new SlopeMap()
            .addSlopeData(25, Blocks.GRASS_BLOCK)
            .addSlopeData(36, Blocks.COARSE_DIRT)
            .addSlopeData(MAX_ANGLE, Blocks.STONE);

    public static SlopeMap calciteWaist = new SlopeMap()
            .addSlopeData(MAX_ANGLE, Blocks.CALCITE);
    public static SlopeMap tundra = new SlopeMap()
            .addSlopeData(30, Blocks.SNOW_BLOCK)
            .addSlopeData(MAX_ANGLE, Blocks.STONE);


    public static SlopeMap avelionRockShore = new SlopeMap()
            .addSlopeData(20, Blocks.GRASS_BLOCK)
            .addSlopeData(30, Blocks.COARSE_DIRT)
            .addSlopeData(35, () -> StoneSets.PALE_AELORIAN_ROCK.block().get())
            .addSlopeData(45, () -> StoneSets.AELORIAN_ROCK.block().get())
            .addSlopeData(55, () -> StoneSets.DUSKEN_AELORIAN_ROCK.block().get())
            .addSlopeData(MAX_ANGLE, () -> StoneSets.CACHOLONG.block().get());

    public static SlopeMap sandShores = new SlopeMap()
            .addSlopeData(27, Blocks.SAND)
            .addSlopeData(30, Blocks.SANDSTONE)
            .addSlopeData(36, Blocks.COARSE_DIRT)
            .addSlopeData(MAX_ANGLE, Blocks.STONE);

    public static SlopeMap whiteSandShores = new SlopeMap()
            .addSlopeData(27, () -> SandBlockSets.WHITE_SAND.block().get())
            .addSlopeData(30, SandBlockSets.WHITE_SAND.sandStone().get())
            .addSlopeData(36, Blocks.COARSE_DIRT)
            .addSlopeData(MAX_ANGLE, Blocks.STONE);

    public static SlopeMap river = new SlopeMap()
            .addSlopeData(30, Blocks.SAND)
            .addSlopeData(36, Blocks.DIRT)
            .addSlopeData(MAX_ANGLE, Blocks.STONE);

    public static SlopeMap mud = new SlopeMap()
            .addSlopeData(25, Blocks.MUD)
            .addSlopeData(32, Blocks.DIRT)
            .addSlopeData(MAX_ANGLE, Blocks.STONE);

    public static SlopeMap halfDesert = new SlopeMap()
            .addSlopeData(32, Blocks.GRASS_BLOCK)
            .addSlopeData(36, Blocks.SMOOTH_SANDSTONE)
            .addSlopeData(44, Blocks.SANDSTONE)
            .addSlopeData(56, Blocks.STONE)
            .addSlopeData(MAX_ANGLE, Blocks.TERRACOTTA);

    public static SlopeMap desert = new SlopeMap()
            .addSlopeData(32, Blocks.SAND)
            .addSlopeData(45, Blocks.SANDSTONE)
            .addSlopeData(60, Blocks.STONE)
            .addSlopeData(MAX_ANGLE, Blocks.TERRACOTTA);

    public static SlopeMap redDesert = new SlopeMap()
            .addSlopeData(32, Blocks.RED_SAND)
            .addSlopeData(45, Blocks.RED_SANDSTONE)
            .addSlopeData(60, Blocks.STONE)
            .addSlopeData(MAX_ANGLE, Blocks.TERRACOTTA);

    public static SlopeMap ocean = new SlopeMap()
            .addSlopeData(30, Blocks.GRAVEL)
            .addSlopeData(36, Blocks.SAND)
            .addSlopeData(MAX_ANGLE, Blocks.STONE);

    public static SlopeMap pond = new SlopeMap()
            .addSlopeData(20, Blocks.MUD)
            .addSlopeData(23,Blocks.GRASS_BLOCK)
            .addSlopeData(27, Blocks.SAND)
            .addSlopeData(33, Blocks.GRAVEL)
            .addSlopeData(MAX_ANGLE, Blocks.STONE);

    public static SlopeMap beach = new SlopeMap()
            .addSlopeData(MAX_ANGLE, Blocks.SAND);

    public static SlopeMap gensaiShores = new SlopeMap()
            .addSlopeData(26, Blocks.RED_SAND)
            .addSlopeData(MAX_ANGLE, Blocks.RED_SANDSTONE);

    public static SlopeMap whiteShores = new SlopeMap()
            .addSlopeData(26, () -> SandBlockSets.WHITE_SAND.block().get())
            .addSlopeData(MAX_ANGLE, () -> SandBlockSets.WHITE_SAND.block().get());
    // endregion

    // region Blocks Layering
    // excludes the surface blocks since it's handled by Slopes blocks

    public static BlocksLayeringData stoneLayers = new BlocksLayeringData()
            .addLayerData(1.0f, Blocks.STONE);

    public static BlocksLayeringData mudLayers = new BlocksLayeringData()
            .addLayerData(1.0f, Blocks.MUD);

    public static BlocksLayeringData volcanicRockLayers = new BlocksLayeringData()
            .addLayerData(1.0f, () -> StoneSets.VOLCANIC_ROCK.block().get());

    public static BlocksLayeringData frozenLayers = new BlocksLayeringData()
            .addLayerData(0.01f, Blocks.DIRT)
            .addLayerData(0.5f, Blocks.STONE)
            .addLayerData(0.5f, Blocks.FROSTED_ICE);


    public static BlocksLayeringData limeStoneLayers = new BlocksLayeringData()
            .addLayerData(0.5f, Blocks.STONE)
            .addLayerData(0.5f, Blocks.CALCITE);

    public static BlocksLayeringData sandstoneLayers = new BlocksLayeringData()
            .addLayerData(0.9f, Blocks.STONE)
            .addLayerData(0.1f, Blocks.SANDSTONE);
    public static BlocksLayeringData avelionLayers = new BlocksLayeringData()
            .addLayerData(0.3f, () -> StoneSets.PALE_AELORIAN_ROCK.block().get())
            .addLayerData(0.3f, () -> StoneSets.DUSKEN_AELORIAN_ROCK.block().get())
            .addLayerData(0.3f, () -> StoneSets.AELORIAN_ROCK.block().get())
            .addLayerData(0.1f, () -> StoneSets.CACHOLONG.block().get());

    public static BlocksLayeringData limestoneLayers = new BlocksLayeringData()
            .addLayerData(0.5f, Blocks.STONE)
            .addLayerData(0.5f, StoneSets.LIMESTONE.block().get());

    public static BlocksLayeringData redSandLayers = new BlocksLayeringData()
            .addLayerData(0.5f, Blocks.RED_SANDSTONE)
            .addLayerData(0.5f, Blocks.STONE);


    public static BlocksLayeringData mistyMountainsLayers = new BlocksLayeringData()
            .addLayerData(0.9f, Blocks.STONE)
            .addLayerData(0.1f, Blocks.TUFF);

    // endregion

    // region Biome Generation Data
    // Expansion weights
    private static final byte[] RIVER_WEIGHT = {2, 2};
    private static final byte[] OCEAN_WEIGHT = {2, 3};
    private static final byte[] MOUNTAIN_WEIGHT = {1, 4};
    private static final byte[] LAND_WEIGHT = {1, 4};

    // Noise Modifiers
    private static final double WATER_NOISE_MODIFIER = 0.4f;
    private static final double PLAINS_NOISE_MODIFIER = 0.33f;
    private static final double FOOTHILL_NOISE_MODIFIER = 0.6f;
    private static final double MOUNTAIN_NOISE_MODIFIER = 0.82f;
    private static final double MOUNTAIN_PEAKS_NOISE_MODIFIER = 1.5f;

    // Height Base Modifiers
    private static final double WATER_HEIGHT_MODIFIER = 0.2f;
    private static final double LAND_HEIGHT_MODIFIER = 0.3f;
    private static final double FOOTHILL_HEIGHT_MODIFIER = 0.38f;
    private static final double MOUNTAIN_HEIGHT_MODIFIER = 0.46f;
    private static final double MOUNTAIN_PEAKS_HEIGHT_MODIFIER = 0.55f;

    public static BiomeGenerationData landModifier = new BiomeGenerationData().expansionWeight(LAND_WEIGHT)
            .noiseModifier(0.46f).heightModifier(0.33f);
    public static BiomeGenerationData plainsModifier = new BiomeGenerationData().expansionWeight(LAND_WEIGHT)
            .noiseModifier(PLAINS_NOISE_MODIFIER).heightModifier(LAND_HEIGHT_MODIFIER);
    public static BiomeGenerationData riverModifier = new BiomeGenerationData().expansionWeight(RIVER_WEIGHT)
            .noiseModifier(WATER_NOISE_MODIFIER).heightModifier(WATER_HEIGHT_MODIFIER);
    public static BiomeGenerationData acidBasinModifier = new BiomeGenerationData().expansionWeight(RIVER_WEIGHT)
            .noiseModifier(WATER_NOISE_MODIFIER).heightModifier(-1.9);
    public static BiomeGenerationData mangroveModifier = new BiomeGenerationData().expansionWeight(RIVER_WEIGHT)
            .noiseModifier(WATER_NOISE_MODIFIER).heightModifier(0);

    public static BiomeGenerationData smallRiverModifier = new BiomeGenerationData().expansionWeight(RIVER_WEIGHT)
            .noiseModifier(0.05f).heightModifier(0.05f);
    public static BiomeGenerationData oceanModifier = new BiomeGenerationData().expansionWeight(OCEAN_WEIGHT)
            .noiseModifier(WATER_NOISE_MODIFIER).heightModifier(WATER_HEIGHT_MODIFIER);
    public static BiomeGenerationData lakeModifier = new BiomeGenerationData().expansionWeight(OCEAN_WEIGHT)
            .noiseModifier(WATER_NOISE_MODIFIER).heightModifier(WATER_HEIGHT_MODIFIER);
    public static BiomeGenerationData foothillModifier = new BiomeGenerationData().expansionWeight(MOUNTAIN_WEIGHT)
            .noiseModifier(FOOTHILL_NOISE_MODIFIER).heightModifier(FOOTHILL_HEIGHT_MODIFIER);
    public static BiomeGenerationData hillPlainModifier = new BiomeGenerationData().expansionWeight(LAND_WEIGHT)
            .noiseModifier(FOOTHILL_NOISE_MODIFIER).heightModifier(FOOTHILL_HEIGHT_MODIFIER);
    public static BiomeGenerationData mountainModifier = new BiomeGenerationData().expansionWeight(MOUNTAIN_WEIGHT)
            .noiseModifier(MOUNTAIN_NOISE_MODIFIER).heightModifier(MOUNTAIN_HEIGHT_MODIFIER);
    public static BiomeGenerationData woodlandModifier = new BiomeGenerationData().expansionWeight(LAND_WEIGHT)
            .noiseModifier(0.7f).heightModifier(0.4f);
    public static BiomeGenerationData demonicMountainModifier = new BiomeGenerationData().expansionWeight(MOUNTAIN_WEIGHT)
            .noiseModifier(1.47f).heightModifier(MOUNTAIN_HEIGHT_MODIFIER);
    public static BiomeGenerationData emModifier = new BiomeGenerationData().expansionWeight(MOUNTAIN_WEIGHT)
            .noiseModifier(0.7f).heightModifier(0.46f);
    public static BiomeGenerationData emPeaksModifier = new BiomeGenerationData().expansionWeight(MOUNTAIN_WEIGHT)
            .noiseModifier(1.47f).heightModifier(0.6f);
    public static BiomeGenerationData darkWoodModifier = new BiomeGenerationData().expansionWeight(LAND_WEIGHT)
            .noiseModifier(0.51f).heightModifier(0.4f);
    public static BiomeGenerationData badLandModifier = new BiomeGenerationData().expansionWeight(LAND_WEIGHT)
            .noiseModifier(1.2f).heightModifier(0.67f);
    public static BiomeGenerationData mountTitleistModifier = new BiomeGenerationData().expansionWeight(MOUNTAIN_WEIGHT)
            .noiseModifier(1.47f).heightModifier(MOUNTAIN_HEIGHT_MODIFIER);
    public static BiomeGenerationData myrwoodModifier = new BiomeGenerationData().expansionWeight(LAND_WEIGHT).noiseModifier(0.51f).heightModifier(0.4f);


}
