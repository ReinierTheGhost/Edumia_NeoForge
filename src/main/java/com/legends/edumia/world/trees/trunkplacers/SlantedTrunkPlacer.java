package com.legends.edumia.world.trees.trunkplacers;

import com.google.common.collect.ImmutableList;
import com.legends.edumia.world.trees.EdumiaTrunkPlacerTypes;
import com.mojang.serialization.Codec;
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

public class SlantedTrunkPlacer  extends TrunkPlacer {
    public static final Codec<SlantedTrunkPlacer> CODEC =
            RecordCodecBuilder.create((placer) -> trunkPlacerParts(placer).apply(placer, SlantedTrunkPlacer::new));

    public SlantedTrunkPlacer(int pBaseHeight, int pHeightRandA, int pHeightRandB) {
        super(pBaseHeight, pHeightRandA, pHeightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return EdumiaTrunkPlacerTypes.SLANTED_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> trunk,
                                                            RandomSource random, int height, BlockPos startPos, TreeConfiguration config) {
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        BlockPos.MutableBlockPos mutableBlockPos = startPos.mutable();
        placeLog(world, trunk, random, mutableBlockPos.relative(direction.getOpposite()), config, (state) ->
                state.setValue(RotatedPillarBlock.AXIS, direction.getAxis()));
        placeLog(world, trunk, random, mutableBlockPos.relative(random.nextInt(2) == 0 ? direction.getClockWise() :
                direction.getCounterClockWise()), config);
        for (int i = 0; i < height; i++) {
            if (random.nextFloat() < 0.4F && i > 2) {
                mutableBlockPos.move(direction);
            }
            placeLog(world, trunk, random, mutableBlockPos, config);
            mutableBlockPos.move(Direction.UP);
        }

        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(mutableBlockPos, 0, false));
    }
}
