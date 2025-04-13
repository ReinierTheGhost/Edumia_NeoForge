package com.legends.edumia.world.trees.foliageplacer;

import com.legends.edumia.world.trees.EdumiaFoliagePlacerTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import javax.swing.tree.TreeNode;

public class CoconutFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<CoconutFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec((placer) ->
            foliagePlacerParts(placer).apply(placer, CoconutFoliagePlacer::new));

    public CoconutFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }


    @Override
    protected FoliagePlacerType<?> type() {
        return EdumiaFoliagePlacerTypes.COCONUT_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader world, FoliageSetter leaves, RandomSource random, TreeConfiguration config, int trunkHeight,
                                 FoliageAttachment foliage, int foliageHeight, int radius, int offset) {
        BlockPos startingPos = foliage.pos();

        tryPlaceLeaf(world, leaves, random, config, startingPos);

        generateLeaves(Direction.NORTH, startingPos, world, leaves, random, config);
        generateLeaves(Direction.EAST, startingPos, world, leaves, random, config);
        generateLeaves(Direction.SOUTH, startingPos, world, leaves, random, config);
        generateLeaves(Direction.WEST, startingPos, world, leaves, random, config);
    }

    @Override
    public int foliageHeight(RandomSource random, int trunkHeight, TreeConfiguration config) {
        return 0;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        return false;
    }

    private static void generateLeaves(Direction direction, BlockPos startingPos, LevelSimulatedReader world, FoliageSetter leaves,
                                       RandomSource random, TreeConfiguration config) {
        BlockPos.MutableBlockPos pos = startingPos.mutable();

        pos.move(direction);
        tryPlaceLeaf(world, leaves, random, config, pos);

        if (random.nextInt(2) == 0) {
            if (world.isStateAtPosition(pos.below(), BlockBehaviour.BlockStateBase::isAir)) {
                leaves.set(pos.below(), Blocks.COCOA.defaultBlockState());
            }
        }
        if (random.nextInt(2) == 0) {
            if (world.isStateAtPosition(pos.below().relative(direction.getCounterClockWise()), BlockBehaviour.BlockStateBase::isAir)) {
                leaves.set(pos.below().relative(direction.getCounterClockWise()), Blocks.COCOA.defaultBlockState());
            }
        }

        for (int i = 0; i < 2; i++) {
            pos.move(direction);
            tryPlaceLeaf(world, leaves, random, config, pos);
            pos.move(Direction.DOWN);
            tryPlaceLeaf(world, leaves, random, config, pos);
        }

        pos.set(startingPos);
        pos.move(direction).move(direction.getCounterClockWise());
        tryPlaceLeaf(world, leaves, random, config, pos);
        pos.move(Direction.DOWN).move(direction.getCounterClockWise());
        tryPlaceLeaf(world, leaves, random, config, pos);
        pos.move(direction);
        tryPlaceLeaf(world, leaves, random, config, pos.relative(direction.getClockWise()));
        for (int i = 0; i < 3; i++) {
            tryPlaceLeaf(world, leaves, random, config, pos);
            pos.move(Direction.DOWN);
        }
    }
}