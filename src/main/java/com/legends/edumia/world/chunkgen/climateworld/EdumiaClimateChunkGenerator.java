package com.legends.edumia.world.chunkgen.climateworld;

import com.legends.edumia.world.chunkgen.EdumiaChunkGenerator;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.RegistryOps;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.util.Mth;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.blending.Blender;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class EdumiaClimateChunkGenerator  extends ChunkGenerator {
    public static final MapCodec<EdumiaClimateChunkGenerator> CODEC = RecordCodecBuilder.mapCodec((
            instance) ->
            instance.group(BiomeSource.CODEC.fieldOf("biome_source")
                                    .forGetter((biomeSource) -> biomeSource.biomeSource),
                            Codec.LONG.fieldOf("seed").stable()
                                    .forGetter((seed) -> seed.seed),
                            NoiseGeneratorSettings.CODEC.fieldOf("settings")
                                    .forGetter((settings) -> settings.dimensionSettings),
                            Codec.BOOL.optionalFieldOf("instant_edumia", true)
                                    .forGetter((isInstantEdumia) -> isInstantEdumia.isInstantEdumia)
                    ).apply(instance, instance.stable(EdumiaClimateChunkGenerator::new)));

    private static final int CUBIC_SAMPLE_SIZE = 24;

    private static final float[] CUBIC_SAMPLE = (float[])Util.make(new float[13824], (array) -> {
        for(int i = 0; i < 24; ++i) {
            for(int j = 0; j < 24; ++j) {
                for(int k = 0; k < 24; ++k) {
                    array[i * 576 + j * 24 + k] = (float)computeContribution(j - 12, k - 12, i - 12);
                }
            }
        }
    });

    private static final int BIOME_SAMPLE_RADIUS = 6;

    private static final int BIOME_SAMPLE_WIDTH = 13;

    private static final float[] BIOME_SAMPLING_SIGNIFICANCE = (float[])Util.make(new float[169], (array) -> {
        for(int z = -6; z <= 6; ++z) {
            for(int x = -6; x <= 6; ++x) {
                float f = 10.0F / Mth.sqrt((float)(z * z + x * x) + 0.2F);
                array[z + 6 + (x + 6) * 13] = f;
            }
        }

    });

    private static final BlockState AIR = Blocks.AIR.defaultBlockState();;
    private final BlockState defaultBlock;
    private final BlockState defaultFluid;
    private final int seaLevel;

//    private final SharedSeedRandom randomSeed;
//    private final OctavesNoiseGenerator minLimitPerlinNoise;
//    private final OctavesNoiseGenerator maxLimitPerlinNoise;
//    private final OctavesNoiseGenerator mainPerlinNoise;
//    private final INoiseGenerator surfaceDepthNoise;
//    private final OctavesNoiseGenerator depthNoise;
    private final long seed;
    private final Holder<NoiseGeneratorSettings> dimensionSettings;
//    private final boolean isAmplified;
    private final int chunkGenHeight;
//    private final RoadGenerator roadGenerator;
    private boolean isInstantEdumia;

    public EdumiaClimateChunkGenerator(
            BiomeSource biomeSource,
            long seed,
            Holder<NoiseGeneratorSettings> settings,
            boolean isInstantEdumia
    ) {
        // call super with the biome source
        super(biomeSource);

        this.seed = seed;
        this.dimensionSettings = settings;
        this.isInstantEdumia = isInstantEdumia;

//        this.roadGenerator = new RoadGenerator();

        this.defaultBlock = settings.value().defaultBlock();
        this.defaultFluid = settings.value().defaultFluid();
        this.seaLevel = settings.value().seaLevel();
        this.chunkGenHeight = settings.value().noiseSettings().height();
    }

    public boolean isInstantEdumia() {
        return isInstantEdumia;
    }

//    public boolean isClassicBiomes() {
//        return this.biomeSource instanceof EdumiaBiomeProvider me
//                && me.isClassicBiomes();
//    }

    @Override
    protected MapCodec<? extends ChunkGenerator> codec() {
        return CODEC;
    }

    @Override
    public void applyBiomeDecoration(WorldGenLevel level, ChunkAccess chunk, StructureManager structureManager) {}


    @Override
    public CompletableFuture<ChunkAccess> fillFromNoise(Blender blender, RandomState randomState, StructureManager structureManager, ChunkAccess chunkAccess) {
        return CompletableFuture.completedFuture(chunkAccess);
    }

    @Override
    public int getBaseHeight(int i, int i1, Heightmap.Types types, LevelHeightAccessor levelHeightAccessor, RandomState randomState) {
        // stub: return sea level for now
        return this.seaLevel;
    }

    @Override
    public void buildSurface(WorldGenRegion worldGenRegion, StructureManager structureManager, RandomState randomState,
                             ChunkAccess chunkAccess) {

    }

    @Override
    public void applyCarvers(WorldGenRegion worldGenRegion, long l, RandomState randomState, BiomeManager biomeManager,
                             StructureManager structureManager, ChunkAccess chunkAccess, GenerationStep.Carving carving) {

    }



    @Override
    public void spawnOriginalMobs(WorldGenRegion worldGenRegion) {

    }

    @Override
    public int getGenDepth() {
        return this.chunkGenHeight;
    }



    @Override
    public int getSeaLevel() {
        return this.seaLevel;
    }

    @Override
    public int getMinY() {
        return 0;
    }



    @Override
    public NoiseColumn getBaseColumn(int i, int i1, LevelHeightAccessor levelHeightAccessor, RandomState randomState) {
        return null;
    }

    @Override
    public void addDebugScreenInfo(List<String> list, RandomState randomState, BlockPos blockPos) {

    }

    private static double computeContribution(int x, int y, int z) {
        double hSq = (double)(x * x + z * z);
        double v = (double)y + 0.5;
        double vSq = v * v;
        double d3 = Math.pow(Math.E, -(vSq / 16.0 + hSq / 16.0));
        double d4 = -v * Mth.fastInvSqrt(vSq / 2.0 + hSq / 2.0) / 2.0;
        return d4 * d3;
    }
}
