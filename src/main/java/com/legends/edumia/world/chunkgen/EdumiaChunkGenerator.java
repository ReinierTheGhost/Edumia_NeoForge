package com.legends.edumia.world.chunkgen;

import com.legends.edumia.utils.noises.BlendedNoise;
import com.legends.edumia.utils.noises.SimplexNoise;
import com.legends.edumia.world.biomes.BlocksLayeringData;
import com.legends.edumia.world.biomes.EdumiaBiomeKeys;
import com.legends.edumia.world.biomes.surface.*;
import com.legends.edumia.world.chunkgen.map.EdumiaHeightMap;
import com.legends.edumia.world.map.EdumiaMapConfigs;
import com.legends.edumia.world.map.EdumiaMapRuntime;
import com.legends.edumia.world.map.EdumiaMapUtils;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.*;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.blending.Blender;
import net.minecraft.world.phys.Vec2;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Custom chunk generator for the Edumia world.
 * Handles terrain generation, biome mapping, and cave/surface structure creation.
 */
public class EdumiaChunkGenerator extends ChunkGenerator {
    public static final int MEDGON_LEVEL = -32;
    public static final int NURGON_LEVEL = 0;
    public static final int DEEPSLATE_LEVEL = 32;
    public static final int STONE_HEIGHT = 36;
    public static final int WATER_HEIGHT = 64;
    public static final int LAVA_HEIGHT = -60;
    public static final int HEIGHT = 27 + STONE_HEIGHT;
    public static final int DIRT_HEIGHT = 3 + HEIGHT;
    public static final int CAVE_NOISE = 5;

    EdumiaMapUtils edumiaMapUtils;
    EdumiaMapRuntime edumiaMapRuntime;

    public static final int mapMultiplier = (int) Math.pow(2, EdumiaMapConfigs.MAP_ITERATION + EdumiaMapConfigs.PIXEL_WEIGHT - 2);
    public static final Vec2 mountTitleist = new Vec2 (2752.5f, 505.2f).scale(mapMultiplier);
    private static final int CAVE_STRETCH_H = 60;
    private static final int SPAGHETTI_CAVE_STRETCH_H = 90;
    private static final int CAVE_STRETCH_V = 50;

    HolderGetter<Biome> biomeRegistry;
    public static final MapCodec<EdumiaChunkGenerator> CODEC = RecordCodecBuilder.mapCodec((instance) ->
            instance.group(RegistryOps.retrieveGetter(Registries.BIOME))
                    .apply(instance, instance.stable(EdumiaChunkGenerator::new)));

    /**
     * Main constructor for the custom chunk generator.
     * Initializes the biome source and internal map runtime utilities.
     *
     * @param biomeRegistry HolderGetter to access biomes from the registry.
     */
    public EdumiaChunkGenerator(HolderGetter<Biome> biomeRegistry) {
        super(new ModBiomeSource(
                new ArrayList<>(Arrays.asList(
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.OCEAN),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.MOUNTAIN_STREAM),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.EDUMIA_VALES),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.EDUMIA_SAVANNAH),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.GENSAI_BEACH),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.GENSAI_ROCKY_COAST),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.DEAD_MARSHES),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.DEAD_MARSHES_WATER),

                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.DEMON_WASTELANDS),

                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.DARK_ELF_FOREST),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.GREAT_RIVER),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.EDUMIA_TUNDRA),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.FROZEN_OCEAN),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.FROZEN_POND),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.ORC_DESERT),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.RED_ORC_DESERT),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.AVELION_PLAINS),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.AVELION_FOREST),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.AVELION_HIDDEN_BLOSSOM),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.AVELION_MEADOW),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.AVELION_SANDY_SHORES),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.AVELION_ROCKY_SHORES),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.AVELION_FOOTHILLS),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.AVELION_MOUNTAIN_BASE),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.AVELION_MOUNTAIN),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.AVELION_MOUNTAIN_PEAKS),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.OGRE_FOREST),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.GENSAI_SAKURA_GROVE),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.OASIS),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.POND),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.FAIRY_SWAMP),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.EDUMIA_FOOTHILLS),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.EDUMIA_MOUNTAINS_BASE),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.EDUMIA_MOUNTAINS_PEAKS),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.EDUMIA_MOUNTAINS),

                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.GENSAI_VOLCANO_PLAINS),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.MOUNT_TITLEIST_CRATER),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.MOUNT_TITLEIST),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.MOUNT_TITLEIST_PEAK),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.MOUNT_TITLEIST_FOOT),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.OCEAN_COAST),

                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.RIVER),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.GENSAI_REEF),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.TAIGA_FOREST),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.TUNDRA_BUSH),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.GENSAI_JUNGLE),

                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.WASTE_POND),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.MYRWOOD_JUNGLE),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.MYRWOOD_JUNGLE_CLEARING),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.MYRWOOD_JUNGLE_PLATEAU),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.MYRWOOD_MANGROVE),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.MYRWOOD_FLOODED_MANGROVE),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.MYRWOOD_COAST),

                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.BASIC_CAVE),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.LUSH_CAVE),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.DRIPSTONE_CAVE),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.MUD_CAVE),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.FUNGUS_CAVE),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.MISTIC_CAVE),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.BASALT_CAVE),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.MAGMA_CAVE),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.DRY_CAVE),
                        biomeRegistry.getOrThrow(EdumiaBiomeKeys.ICE_CAVE)
                )))
        );

        this.biomeRegistry = biomeRegistry;

        this.edumiaMapUtils = EdumiaMapUtils.getInstance();
        this.edumiaMapRuntime = EdumiaMapRuntime.getInstance();

    }

    /**
     * Returns the codec used to serialize this chunk generator.
     *
     * @return MapCodec for this generator.
     */
    @Override
    protected MapCodec<? extends ChunkGenerator> codec() {
        return (MapCodec<? extends ChunkGenerator>) CODEC;
    }

    /**
     * Empty implementation of the carver application step.
     * You may add custom carver logic (like caves, tunnels) here later if needed.
     */
    @Override
    public void applyCarvers(WorldGenRegion chunkRegion, long seed, RandomState noiseConfig,
                             BiomeManager biomeAccess, StructureManager structureAccessor,
                             ChunkAccess chunk2, GenerationStep.Carving carverStep) {

    }

    /**
     * Core terrain generation logic for Edumia.
     * Sets base terrain blocks, handles biome-based height/slope logic, and places surface features.
     *
     * @param region         The region of the world being generated.
     * @param structures     Structure manager (unused in this logic).
     * @param noiseConfig    The world noise configuration.
     * @param chunk          The chunk to populate.
     */
    @Override
    public void buildSurface(WorldGenRegion region, StructureManager structures, RandomState noiseConfig, ChunkAccess chunk) {
        int bottomY = chunk.getMinBuildHeight();
        for(int x = 0; x < 16; x++) {
            for(int z = 0; z < 16; z++) {
                int posX = (chunk.getPos().x * 16) + x;
                int posZ = (chunk.getPos().z * 16) + z;
                // Fetch biome-specific height and surface data
                MapBasedCustomBiome customHeightBiomeHeightData = null;
                if(edumiaMapUtils.isWorldCoordinateInBorder(posX, posZ)) {
                    Holder<Biome> biome = region.getBiome(new BlockPos(posX, chunk.getMaxBuildHeight(), posZ));
                    customHeightBiomeHeightData = MapBasedBiomePool.getBiome(biome, posX, posZ);
                }
                if (customHeightBiomeHeightData == null){
                    customHeightBiomeHeightData = MapBasedBiomePool.defaultBiome;
                }

                // Get the terrain height from the heightmap
                float height = EdumiaHeightMap.getHeight(posX, posZ);

                // Apply cave noise blending for vertical cave structure generation
                float caveBlendNoise = (float) ((2 * CAVE_NOISE * BlendedNoise.noise((double) posX / 24,  (double) posZ / 24)) - CAVE_NOISE);
                // Compute terrain slope to decide surface block types (grass, stone, etc.)
                float slopeAngle = getTerrainSlope(height, posX, posZ);

                int waterHeight = customHeightBiomeHeightData.getWaterHeight();

                ResourceKey<Biome> biomeResourceKey = customHeightBiomeHeightData.getBiomeKey();
                if (SubBiomes.isSubBiome(biomeResourceKey)) {
                    SubBiome subBiome = SubBiomes.getSubBiomeFromChild(biomeResourceKey);
                    if (subBiome != null){
                        double perlin = ModBiomeSource.getSubBiomeNoise(posX, posZ, subBiome.getFrequency());
                        double additionalHeight = Math.max(subBiome.getAdditionalHeight((float) perlin) - 1, 0);
                        additionalHeight *= EdumiaMapRuntime.getInstance().getEdge(posX, posZ);
                        height += (float) additionalHeight;
                    }
                } else if(biomeResourceKey == EdumiaBiomeKeys.MOUNT_TITLEIST_CRATER) {
                    float percentage = (float) Math.sqrt(mountTitleist.distanceToSqr(new Vec2(posX, posZ))) / 42;
                    percentage = Math.min(1, Math.max(0.0f, percentage));
                    percentage = (float) Math.pow(percentage, 2.47f);
                    height = height * percentage;
                    height -= (1 - percentage) * getNoisyHeight(posX, posZ) * 8;
                } else if(biomeResourceKey == EdumiaBiomeKeys.MYRWOOD_MANGROVE || biomeResourceKey == EdumiaBiomeKeys.MYRWOOD_FLOODED_MANGROVE) {
                    float oldHeight = height;
                    height = getMarshesHeight(posX, posZ, height);
                    float percentage = Math.min(EdumiaHeightMap.getImageNoiseModifier(posX, posZ), 0.3f) / 0.3f;
                    height = EdumiaHeightMap.lerp(height, oldHeight, percentage);
                }

                chunk.setBlockState(chunk.getPos().getBlockAt(x, bottomY, z), Blocks.BEDROCK.defaultBlockState(), false);
                for(int y = bottomY + 1; y <= LAVA_HEIGHT; y++) {

                    chunk.setBlockState(chunk.getPos().getBlockAt(x, y, z), Blocks.LAVA.defaultBlockState(), false);
                }

                for(int y = bottomY + 1; y < MEDGON_LEVEL + caveBlendNoise; y++) {
                    trySetBlock(chunk, chunk.getPos().getBlockAt(x, y, z), Blocks.BLACKSTONE.defaultBlockState());
                }
                if(Math.random() < 0.5f) chunk.setBlockState(chunk.getPos().getBlockAt(x, chunk.getMinBuildHeight() + 1, z),
                        Blocks.BEDROCK.defaultBlockState(), false);

                for(int y = MEDGON_LEVEL + (int) caveBlendNoise; y < NURGON_LEVEL + caveBlendNoise; y++) {
                    trySetBlock(chunk, chunk.getPos().getBlockAt(x, y, z), Blocks.TUFF.defaultBlockState());
                }
                for(int y = NURGON_LEVEL + (int) caveBlendNoise; y < DEEPSLATE_LEVEL + caveBlendNoise; y++) {
                    trySetBlock(chunk, chunk.getPos().getBlockAt(x, y, z), Blocks.DEEPSLATE.defaultBlockState());
                }

                float dirtHeight = HEIGHT + height - 1;
                int currentHeight = DEEPSLATE_LEVEL + (int) caveBlendNoise;
                int totalLayersHeight = (int) (dirtHeight - currentHeight);
                for(BlocksLayeringData.LayerData layerData : customHeightBiomeHeightData.getBiome().getBlocksLayering().layers) {
                    int blocks = (int) (totalLayersHeight * layerData.percentage);
                    for(int y = 0; y <= blocks; y++) {
                        trySetBlock(chunk, chunk.getPos().getBlockAt(x, currentHeight++, z), layerData.block.defaultBlockState());
                    }
                }
                chunk.setBlockState(chunk.getPos().getBlockAt(x, (int) (HEIGHT + height - 2), z), customHeightBiomeHeightData.getBiome().getBlocksLayering().layers.getFirst().block.defaultBlockState(), false);
                BlockState surfaceBlock = customHeightBiomeHeightData.getBiome().getSlopeMap().slopeDatas.getFirst().block.defaultBlockState();
                BlockState underSurfaceBlock;


                if(DIRT_HEIGHT + height < waterHeight && surfaceBlock == Blocks.GRASS_BLOCK.defaultBlockState()) {
                    surfaceBlock = Blocks.DIRT.defaultBlockState();
                    underSurfaceBlock = surfaceBlock;
                } else {
                    surfaceBlock = customHeightBiomeHeightData.getBiome().getSlopeMap().getBlockAtAngle(slopeAngle).defaultBlockState();
                    if(surfaceBlock == Blocks.GRASS_BLOCK.defaultBlockState()) underSurfaceBlock = Blocks.DIRT.defaultBlockState();
                    else underSurfaceBlock = surfaceBlock;
                }

                chunk.setBlockState(chunk.getPos().getBlockAt(x, (int) (HEIGHT + height - 1), z), underSurfaceBlock, false);
                for(int y = (int) (HEIGHT + height); y < DIRT_HEIGHT + height; y++) {
                    chunk.setBlockState(chunk.getPos().getBlockAt(x, y, z), underSurfaceBlock, false);
                }
                chunk.setBlockState(chunk.getPos().getBlockAt(x, (int) (DIRT_HEIGHT + height), z), surfaceBlock, false);

                if(biomeResourceKey == EdumiaBiomeKeys.MOUNT_TITLEIST_CRATER) {
                    for(int y = (int) (DIRT_HEIGHT + height + 1); y <= 100; y++) {
                        chunk.setBlockState(chunk.getPos().getBlockAt(x, y, z), Blocks.LAVA.defaultBlockState(), false);
                    }
                    if (DIRT_HEIGHT + height < 110){
                        chunk.setBlockState(chunk.getPos().getBlockAt(x, (int) (DIRT_HEIGHT + height), z), Blocks.MAGMA_BLOCK.defaultBlockState(), false);
                    }
                } else {
                    for(int y = (int) (DIRT_HEIGHT + height + 1); y <= waterHeight; y++) {
                        chunk.setBlockState(chunk.getPos().getBlockAt(x, y, z), Blocks.WATER.defaultBlockState(), false);
                    }
                }


                //ProceduralStructures.generateStructures(customHeightBiomeHeightData, chunk, posX, (int) (DIRT_HEIGHT + height), posZ);
            }
        }
    }

    /**
     * Calculates the terrain slope at a given location.
     * Used to determine which surface block to apply (e.g., grass vs. stone on steep slopes).
     *
     * @param height Current terrain height.
     * @param x      X-coordinate.
     * @param z      Z-coordinate.
     * @return Slope angle in degrees.
     */
    private float getTerrainSlope(float height, int x, int z) {
        int offset = 3;
        float eastHeight = EdumiaHeightMap.getHeight(x + offset, z);
        float southHeight = EdumiaHeightMap.getHeight(x, z + offset);

        float eastSlope = Math.abs((eastHeight - height) / offset);
        float southSlope = Math.abs((southHeight - height) / offset);
        float highestSlope = (eastSlope + southSlope) / 2;

        return (float) Math.toDegrees(Math.atan(highestSlope));
    }

    /**
     * Attempts to place a block only if the cave noise conditions allow it.
     * Prevents block placement in areas that represent underground caves.
     *
     * @param chunk      The chunk to modify.
     * @param blockPos   The position of the block.
     * @param blockState The block to attempt to place.
     */
    private void trySetBlock(ChunkAccess chunk, BlockPos blockPos, BlockState blockState) {
        float noise = 0;
        if(blockPos.getY() < WATER_HEIGHT) {
            noise =(float) SimplexNoise.noise(
                    (float) blockPos.getX() / CAVE_STRETCH_H, Math.tan((float) blockPos.getY() / CAVE_STRETCH_V), (float) blockPos.getZ() / CAVE_STRETCH_H);
            noise += 0.5f * (float) SimplexNoise.noise(
                    (float) blockPos.getX() / (CAVE_STRETCH_H * 0.5f), (float) blockPos.getY() / (CAVE_STRETCH_V * 0.5f), (float) blockPos.getZ() / (CAVE_STRETCH_H * 0.5f));
            noise = noise / (1 + 0.5f);
        }
        float noise3 = (float) SimplexNoise.noise((float) blockPos.getX() / 90, (float) blockPos.getY() / 60, (float) blockPos.getZ() / 90);
        float miniNoise = (float) SimplexNoise.noise((float) blockPos.getX() / 40, (float) blockPos.getY() / 30, (float) blockPos.getZ() / 40);

        float spaghettiNoise = Math.abs((float) SimplexNoise.noise(
                (float)blockPos.getX() / (SPAGHETTI_CAVE_STRETCH_H * 1.5f), (float) blockPos.getY() / CAVE_STRETCH_V,
                (float)blockPos.getZ() /(SPAGHETTI_CAVE_STRETCH_H * 1.5f), 57142));
        float spaghettiNoise2 = Math.abs((float) SimplexNoise.noise(
                (float)(98153 + blockPos.getZ()) / SPAGHETTI_CAVE_STRETCH_H, (float) blockPos.getY() / CAVE_STRETCH_V,
                (float)blockPos.getX() / SPAGHETTI_CAVE_STRETCH_H, 0));
        float spaghettiNoise3 = Math.abs((float) SimplexNoise.noise(
                (float)(1243624 + blockPos.getZ()) / (SPAGHETTI_CAVE_STRETCH_H * 0.5f), (float) blockPos.getY() / CAVE_STRETCH_V,
                (float)blockPos.getX() /(SPAGHETTI_CAVE_STRETCH_H * 0.5f), 0));
        float combinedSpaghettiNoise = Math.abs(spaghettiNoise) + Math.abs(spaghettiNoise2) + Math.abs(spaghettiNoise3);
        combinedSpaghettiNoise /= 3;

        if(noise < 0.4f && noise3 < 0.75f && miniNoise < 0.8f && combinedSpaghettiNoise > 0.09f) {
            chunk.setBlockState(blockPos, blockState, false);
        }
    }

    /**
     * Specialized height function for Dead Marshes biome.
     * Introduces marsh-like undulations using blended noise.
     *
     * @param x      X-coordinate.
     * @param z      Z-coordinate.
     * @param height Base height.
     * @return Modified height for marsh generation.
     */
    public static float getMarshesHeight(int x, int z, float height) {
        height = -2 + (2.0f * (float) BlendedNoise.noise((double) x / 19,  (double) z / 19));
        height += (float) BlendedNoise.noise((double) x / 11,  (double) z / 11);
        return height;
    }

    /**
     * Adds vertical noise to Mount Titleist crater terrain.
     * Used to simulate volcanic crater depth variations.
     *
     * @param x X-coordinate.
     * @param z Z-coordinate.
     * @return Noisy terrain height for crater deformation.
     */
    public static float getNoisyHeight(int x, int z){
        float height = -2 + (4.0f * (float) BlendedNoise.noise((double) x / 8, (double) z / 8));
        height += 2 + (float) BlendedNoise.noise((double) x / 4, (double) z / 4);
        return height;
    }
    
    @Override
    public void applyBiomeDecoration(WorldGenLevel world, ChunkAccess chunk, StructureManager structureAccessor) {
        super.applyBiomeDecoration(world, chunk, structureAccessor);
    }

    @Override
    public void spawnOriginalMobs(WorldGenRegion region) {
        ChunkPos chunkPos = region.getCenter();
        Holder<Biome> registryEntry = region.getBiome(chunkPos.getWorldPosition().atY(region.getMaxBuildHeight() - 1));
        WorldgenRandom chunkRandom = new WorldgenRandom(new LegacyRandomSource(RandomSupport.generateUniqueSeed()));
        chunkRandom.setDecorationSeed(region.getSeed(), chunkPos.getMinBlockX(), chunkPos.getMinBlockZ());
        NaturalSpawner.spawnMobsForChunkGeneration(region, registryEntry, chunkPos, chunkRandom);
    }

    @Override
    public int getGenDepth() {
        return 384;
    }

    @Override
    public CompletableFuture<ChunkAccess> fillFromNoise(Blender blender, RandomState randomState, StructureManager structureManager, ChunkAccess chunk)  {
        return CompletableFuture.completedFuture(chunk);
    }

    @Override
    public int getSeaLevel() {
        return WATER_HEIGHT;
    }

    @Override
    public int getMinY() {
        return -4;
    }

    @Override
    public int getBaseHeight(int x, int z, Heightmap.Types heightmap, LevelHeightAccessor world, RandomState noiseConfig) {
        float worldHeight = 1 + DIRT_HEIGHT + EdumiaHeightMap.getHeight(x, z);
        return (int)worldHeight;
    }

    @Override
    public NoiseColumn getBaseColumn(int x, int z, LevelHeightAccessor world, RandomState noiseConfig) {
        return new NoiseColumn(0, new BlockState[0]);
    }

    @Override
    public void addDebugScreenInfo(List<String> text, RandomState noiseConfig, BlockPos pos) {
    }
}
