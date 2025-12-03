package com.legends.edumia.world.trees.trunkplacers;

import com.google.common.collect.ImmutableList;
import com.legends.edumia.world.biomes.EdumiaBiomeKeys;
import com.legends.edumia.world.biomes.surface.ModBiomes;
import com.legends.edumia.world.trees.EdumiaTrunkPlacerTypes;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class ExtraGiantTrunkPlacer extends TrunkPlacer {

    public static final MapCodec<ExtraGiantTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(
            (instance) ->
                    trunkPlacerParts(instance).apply(instance, ExtraGiantTrunkPlacer::new));

    public ExtraGiantTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return EdumiaTrunkPlacerTypes.EXTRA_GIANT_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader world,
                                                            BiConsumer<BlockPos, BlockState> replacer,
                                                            RandomSource random,
                                                            int height,
                                                            BlockPos startPos,
                                                            TreeConfiguration config) {
        height += 28;

        BlockPos blockPos = startPos.below();
        for (int x = -4; x <= 4; x++) {
            for (int z = -4; z <= 4; z++) {
                if ((x * x + z * z) <= Math.pow(4.5D, 2.0D)) {
                    fixedSetToDirt(world, replacer, random, blockPos.offset(x, 0, z), config);
                    fixedSetToDirt(world, replacer, random, blockPos.offset(x, -1, z), config);
                    fixedSetToDirt(world, replacer, random, blockPos.offset(x, -2, z), config);
                    fixedSetToDirt(world, replacer, random, blockPos.offset(x, -3, z), config);
                }
            }
        }
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        for (int i = 0; i < height; i++) {
            setLog(world, replacer, random, mutable, config, startPos, 0, i, 1);
            setLog(world, replacer, random, mutable, config, startPos, -1, i, 0);
            setLog(world, replacer, random, mutable, config, startPos, 0, i, 0);
            setLog(world, replacer, random, mutable, config, startPos, 1, i, 0);
            setLog(world, replacer, random, mutable, config, startPos, 0, i, -1);
            if (i < height - 1) {
                setLog(world, replacer, random, mutable, config, startPos, -1, i, 1);
                setLog(world, replacer, random, mutable, config, startPos, 1, i, 1);
                setLog(world, replacer, random, mutable, config, startPos, -1, i, -1);
                setLog(world, replacer, random, mutable, config, startPos, 1, i, -1);
            }
            if (i == 0) {
                setLog(world, replacer, random, mutable, config, startPos, -3, i, 1);
                setLog(world, replacer, random, mutable, config, startPos, -3, i, 0);
                setLog(world, replacer, random, mutable, config, startPos, -3, i, -1);
                setLog(world, replacer, random, mutable, config, startPos, 3, i, 1);
                setLog(world, replacer, random, mutable, config, startPos, 3, i, 0);
                setLog(world, replacer, random, mutable, config, startPos, 3, i, -1);
                setLog(world, replacer, random, mutable, config, startPos, -1, i, 3);
                setLog(world, replacer, random, mutable, config, startPos, 0, i, 3);
                setLog(world, replacer, random, mutable, config, startPos, 1, i, 3);
                setLog(world, replacer, random, mutable, config, startPos, -1, i, -3);
                setLog(world, replacer, random, mutable, config, startPos, 0, i, -3);
                setLog(world, replacer, random, mutable, config, startPos, 1, i, -3);
                setLog(world, replacer, random, mutable, config, startPos, -2, i, 2);
                setLog(world, replacer, random, mutable, config, startPos, 2, i, 2);
                setLog(world, replacer, random, mutable, config, startPos, -2, i, -2);
                setLog(world, replacer, random, mutable, config, startPos, 2, i, -2);
            }
            if (i == 1) {
                setLog(world, replacer, random, mutable, config, startPos, 0, i, 3);
                setLog(world, replacer, random, mutable, config, startPos, 3, i, 0);
                setLog(world, replacer, random, mutable, config, startPos, 0, i, -3);
                setLog(world, replacer, random, mutable, config, startPos, -3, i, 0);
            }
            if (i <= 3) {
                setLog(world, replacer, random, mutable, config, startPos, -2, i, 1);
                setLog(world, replacer, random, mutable, config, startPos, -2, i, 0);
                setLog(world, replacer, random, mutable, config, startPos, -2, i, -1);
                setLog(world, replacer, random, mutable, config, startPos, 2, i, 1);
                setLog(world, replacer, random, mutable, config, startPos, 2, i, 0);
                setLog(world, replacer, random, mutable, config, startPos, 2, i, -1);
                setLog(world, replacer, random, mutable, config, startPos, 1, i, -2);
                setLog(world, replacer, random, mutable, config, startPos, 0, i, -2);
                setLog(world, replacer, random, mutable, config, startPos, -1, i, -2);
                setLog(world, replacer, random, mutable, config, startPos, 1, i, 2);
                setLog(world, replacer, random, mutable, config, startPos, 0, i, 2);
                setLog(world, replacer, random, mutable, config, startPos, -1, i, 2);
            }
            if (i <= 5) {
                setLog(world, replacer, random, mutable, config, startPos, 0, i, 2);
                setLog(world, replacer, random, mutable, config, startPos, 2, i, 0);
                setLog(world, replacer, random, mutable, config, startPos, 0, i, -2);
                setLog(world, replacer, random, mutable, config, startPos, -2, i, 0);
            }
        }
        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(startPos.above(height), 0, true));
    }

    private void setLog(LevelSimulatedReader world,
                        BiConsumer<BlockPos, BlockState> replacer,
                        RandomSource random, BlockPos.MutableBlockPos tmpPos,
                        TreeConfiguration config, BlockPos startPos, int dx, int dy, int dz) {
        tmpPos.setWithOffset((Vec3i)startPos, dx, dy, dz);
        placeLogIfFree(world, replacer, random, tmpPos, config);
    }

    protected static void fixedSetToDirt(LevelSimulatedReader world,
                                         BiConsumer<BlockPos, BlockState> replacer,
                                         RandomSource random, BlockPos pos, TreeConfiguration config) {
        if (world instanceof ServerLevelAccessor) {
            ServerLevelAccessor serverWorld = (ServerLevelAccessor)world;
            Holder<Biome> biomeEntry = serverWorld.getBiome(pos);
            ResourceKey<Biome> biomeKey = biomeEntry.unwrapKey().orElse(null);
            if (biomeKey != null && (biomeKey
                    .equals(EdumiaBiomeKeys.MYRWOOD_JUNGLE) || biomeKey
                    .equals(Biomes.MANGROVE_SWAMP))) {
                replacer.accept(pos, Blocks.MUD.defaultBlockState());
                return;
            }
        }
        if (config.forceDirt || canGenerate(world, pos))
            replacer.accept(pos, config.dirtProvider.getState(random, pos));
    }

    private static boolean canGenerate(LevelSimulatedReader world, BlockPos pos) {
        return world.isStateAtPosition(pos, state ->
                (Feature.isDirt(state) || !state.isRedstoneConductor((BlockGetter) world, pos)));
    }
}
