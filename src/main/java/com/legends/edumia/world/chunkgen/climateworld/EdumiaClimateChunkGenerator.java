package com.legends.edumia.world.chunkgen.climateworld;

import com.legends.edumia.world.chunkgen.EdumiaChunkGenerator;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.RegistryOps;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.blending.Blender;

import java.util.List;
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
                                    .forGetter((settings) -> settings.dimensionSettings)
                            )
                    .apply(instance, instance.stable(EdumiaClimateChunkGenerator::new)));

    private final Holder<NoiseGeneratorSettings> dimensionSettings;
    private final long seed;

    public EdumiaClimateChunkGenerator(BiomeSource biomeSource, long seed, Holder<NoiseGeneratorSettings> dimensionSettings) {
        super(biomeSource);
        this.dimensionSettings = dimensionSettings;
        this.seed = seed;
    }

    @Override
    protected MapCodec<? extends ChunkGenerator> codec() {
        return null;
    }

    @Override
    public void applyCarvers(WorldGenRegion worldGenRegion, long l, RandomState randomState, BiomeManager biomeManager, StructureManager structureManager, ChunkAccess chunkAccess, GenerationStep.Carving carving) {

    }

    @Override
    public void buildSurface(WorldGenRegion worldGenRegion, StructureManager structureManager, RandomState randomState, ChunkAccess chunkAccess) {

    }

    @Override
    public void spawnOriginalMobs(WorldGenRegion worldGenRegion) {

    }

    @Override
    public int getGenDepth() {
        return 0;
    }

    @Override
    public CompletableFuture<ChunkAccess> fillFromNoise(Blender blender, RandomState randomState, StructureManager structureManager, ChunkAccess chunkAccess) {
        return null;
    }

    @Override
    public int getSeaLevel() {
        return 0;
    }

    @Override
    public int getMinY() {
        return 0;
    }

    @Override
    public int getBaseHeight(int i, int i1, Heightmap.Types types, LevelHeightAccessor levelHeightAccessor, RandomState randomState) {
        return 0;
    }

    @Override
    public NoiseColumn getBaseColumn(int i, int i1, LevelHeightAccessor levelHeightAccessor, RandomState randomState) {
        return null;
    }

    @Override
    public void addDebugScreenInfo(List<String> list, RandomState randomState, BlockPos blockPos) {

    }
}
