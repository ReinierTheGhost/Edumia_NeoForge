package com.legends.edumia.world.trees.trunkplacers;

import com.legends.edumia.world.trees.EdumiaTrunkPlacerTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class BaobabTrunkPlacer extends TrunkPlacer {
    public static final Codec<BaobabTrunkPlacer> CODEC = RecordCodecBuilder.create(instance ->
            trunkPlacerParts(instance).apply(instance, BaobabTrunkPlacer::new));
    public BaobabTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
        super(baseHeight, firstRandomHeight, secondRandomHeight);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return EdumiaTrunkPlacerTypes.BAOBAB_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> trunkPlacer, RandomSource random, int height, BlockPos pos, TreeConfiguration config) {
        int trunkCircleWidth = 4;
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
        int xSlope = 5 + random.nextInt(10);
        if (random.nextBoolean()) xSlope *= -1;
        int zSlope = 5 + random.nextInt(10);
        if (random.nextBoolean()) zSlope *= -1;

        for (int y = 0; y < height; y++) {
            for (int dx = -trunkCircleWidth; dx <= trunkCircleWidth; dx++) {
                for (int dz = -trunkCircleWidth; dz <= trunkCircleWidth; dz++) {
                    if (dx * dx + dz * dz <= trunkCircleWidth * trunkCircleWidth) {
                        BlockPos trunkPos = pos.offset(dx, y, dz);
                        trunkPlacer.accept(trunkPos, config.trunkProvider.getState(random, trunkPos));
                    }
                }
            }
            if (y % xSlope == 0) {
                pos = pos.offset(xSlope > 0 ? 1 : -1, 0, 0);
            }
            if (y % zSlope == 0) {
                pos = pos.offset(0, 0, zSlope > 0 ? 1 : -1);
            }
        }
        // Branch generation
        for (int y = height - 1; y > (int)(height * 0.75F); y--) {
            int branches = 2 + random.nextInt(3);
            for (int b = 0; b < branches; b++) {
                float angle = random.nextFloat() * 3.1415927F * 2.0F;
                int length = Mth.randomBetweenInclusive(random, 4, 6);
                for (int l1 = trunkCircleWidth; l1 < trunkCircleWidth + length; l1++) {
                    int dx = (int)(1.5f + Math.cos(angle) * l1);
                    int dz = (int)(1.5F + Math.sin(angle) * l1);
                    mutablePos.set(pos.getX() + dx, pos.getY() + y - 3 +  (b / 2), pos.getZ() + dz);
                    if (TreeFeature.validTreePos(world, mutablePos)) {
                        trunkPlacer.accept(mutablePos, config.trunkProvider.getState(random, mutablePos));
                    }
                }
            }
        }

        for (int dx =  -trunkCircleWidth - 1; dx <= trunkCircleWidth + 1; dx++) {
            for (int dz = -trunkCircleWidth - 1; dz <= trunkCircleWidth + 1; dz++) {
                if (dx * dx + dz * dz <= trunkCircleWidth * trunkCircleWidth && random.nextInt(5) == 0) {
                    int topHeight = 2 + random.nextInt(3);
                    int m = height;
                    for (int l = 0; l < topHeight; l++) {
                        mutablePos.setWithOffset(pos, 0, m, 0);
                        if (TreeFeature.validTreePos(world, mutablePos)) {
                            trunkPlacer.accept(mutablePos, config.trunkProvider.getState(random, mutablePos));
                            m++;
                        }
                    }
                }
            }
        }

        return List.of(new FoliagePlacer.FoliageAttachment(pos.above(height), 0, false));
    }

    private void generateBranch(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> trunkPlacer, RandomSource random, int yStart, BlockPos pos, TreeConfiguration config, float angle, int length) {
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

        int x = pos.getX();
        int y = pos.getY() + yStart;
        int z = pos.getZ();

        for (int l = 0; l < length; l++) {
            int dx = (int)(Math.cos(angle) * l);
            int dz = (int)(Math.sin(angle) * l);
            mutablePos.set(x + dx, y + (l / 2), z + dz);
            if (TreeFeature.validTreePos(world, mutablePos)) {
                trunkPlacer.accept(mutablePos, config.trunkProvider.getState(random, mutablePos));
            }
        }
    }
}
