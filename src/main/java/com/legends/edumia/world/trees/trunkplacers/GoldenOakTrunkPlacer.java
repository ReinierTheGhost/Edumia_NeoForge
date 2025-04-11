package com.legends.edumia.world.trees.trunkplacers;

import com.google.common.collect.ImmutableList;
import com.legends.edumia.world.trees.EdumiaTrunkPlacerTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;


import java.util.List;
import java.util.function.BiConsumer;

public class GoldenOakTrunkPlacer extends TrunkPlacer {
    public static final Codec<GoldenOakTrunkPlacer> CODEC = RecordCodecBuilder.create((instance) ->
            trunkPlacerParts(instance).apply(instance, GoldenOakTrunkPlacer::new));

    public GoldenOakTrunkPlacer(int height, int heightRandA, int heightRandB) {
        super(height, heightRandA, heightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return EdumiaTrunkPlacerTypes.GOLDEN_OAK_TRUNK_PLACER.get();
    }


    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> trunk,
                                                            RandomSource random, int height, BlockPos startPos, TreeConfiguration config) {
        TrunkPlacer.setDirtAt(world, trunk, random, startPos.below(), config);
        for (int i = 0; i < height; ++i) {
            if (i > 4 && random.nextInt(3) > 0 && i < 9) {
                this.branch(world, random, trunk, startPos.getX(), startPos.getY() + i, startPos.getZ(), i / 4 - 1, config);
            }

            this.placeLog(world, trunk, random, startPos.above(i), config);
        }
        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(startPos.above(height), 0, false));
    }

    public void branch(LevelSimulatedReader world, RandomSource random, BiConsumer<BlockPos, BlockState> trunk, int i, int j, int k, int slant, TreeConfiguration config) {
        int directionX = random.nextInt(3) - 1;
        int directionZ = random.nextInt(3) - 1;

        for (int n = 0; n < random.nextInt(2) + 1; ++n) {
            i += directionX;
            j += slant;
            k += directionZ;
            this.placeLog(world, trunk, random, new BlockPos(i, j, k), config);
        }
    }
}
