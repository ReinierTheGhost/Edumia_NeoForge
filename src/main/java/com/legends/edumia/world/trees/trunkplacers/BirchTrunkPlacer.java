package com.legends.edumia.world.trees.trunkplacers;

import com.google.common.collect.ImmutableList;
import com.legends.edumia.world.trees.EdumiaTrunkPlacerTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;


import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class BirchTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<BirchTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(pineTrunkPlacerInstance ->
            trunkPlacerParts(pineTrunkPlacerInstance).apply(pineTrunkPlacerInstance, BirchTrunkPlacer::new));

    public BirchTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return EdumiaTrunkPlacerTypes.BIRCH_TREE_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> replacer,
                                                          RandomSource random, int height, BlockPos startPos, TreeConfiguration config) {
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);

        setDirtAt(world, replacer, random, startPos.below(), config);

        java.util.Random rand = new java.util.Random();

        int branchHeightRange = 5 - 4 + 1;
        int branchHeight =  random.nextInt(branchHeightRange) + 4;

        for(int i = 0; i < height; i++) {
            placeLog(world, replacer, random, startPos.above(i), config);

            if (height > 7) {
                if(i % 2 == 0 && random.nextBoolean()) {
                    if(random.nextFloat() > 0.25f) {
                        for (int x = 1; x < 2; x++) {
                            replacer.accept(startPos.above(height - branchHeight).relative(direction, x), ((BlockState)
                                    Function.identity().apply(config.trunkProvider.getState(random, startPos).setValue(RotatedPillarBlock.AXIS, direction.getAxis()))));
                        }
                    }
                }
            }
        }

        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(startPos.above(height), 0, false));
    }
}
