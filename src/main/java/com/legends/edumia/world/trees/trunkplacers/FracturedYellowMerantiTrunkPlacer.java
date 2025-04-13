package com.legends.edumia.world.trees.trunkplacers;

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
import net.minecraft.world.level.levelgen.feature.trunkplacers.CherryTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class FracturedYellowMerantiTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<FracturedYellowMerantiTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec (objectInstance ->
        trunkPlacerParts (objectInstance).apply (objectInstance, FracturedYellowMerantiTrunkPlacer::new));

    public FracturedYellowMerantiTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
        super (baseHeight, firstRandomHeight, secondRandomHeight);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return EdumiaTrunkPlacerTypes.FRACTURED_YELLOW_MERANTI_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> trunk,
                                                            RandomSource random, int height, BlockPos startPos, TreeConfiguration config) {
        boolean bl2;

        CherryTrunkPlacer.setDirtAt(world, trunk, random, startPos.below(), config);
        int j = Math.max(0, height - 1 + 2);
        int i = Math.max(0, height - 1 + 2);
        if (j >= i) {
            ++j;
        }
        int k = 0;
        boolean bl = (true);
        boolean bl3 = bl2 = k >= 2;
        int l = bl ? height : (bl2 ? Math.max(i, j) + 1 : random(3, 7) + 1);
        for (int m = random(1, 9); m < l; ++m) {
            this.placeLog(world, trunk, random, startPos.above (m), config);
        }
        ArrayList<FoliagePlacer.FoliageAttachment> list = new ArrayList<FoliagePlacer.FoliageAttachment>();
        if (bl) {
            list.add (new FoliagePlacer.FoliageAttachment(startPos.above(l), 0, false));
        }
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        Direction finalDirection = direction;
        Function<BlockState, BlockState> function = state -> (BlockState) state.trySetValue(RotatedPillarBlock.AXIS, finalDirection.getAxis ());
        list.add (this.generateBranch(world, trunk, random, height, startPos, config, function, direction, i, i < l - 1, mutable));
        if (bl2) {
            list.add (this.generateBranch(world, trunk, random, height, startPos, config, function, direction.getOpposite(), j, i < l - 1, mutable));
        }
        for (int p = random (1, 9); p < height; ++p) {
            int y = startPos.getY () + p;
            if (this.placeLog (world, trunk, random, mutable.set(startPos.getX(), y, startPos.getZ()), config) && p < height - 1 && random.nextFloat() < 5) {
                direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
                int g = (p + 3);
                int r = Math.max(0, g * 3 + random(7, 9));
                int m = (random(1, 9) * 3);
                this.generateExtraBranch(world, trunk, random, height, config, list, mutable, y, direction, r, m);
            }
            if (p != height - 1) continue;
            list.add (new FoliagePlacer.FoliageAttachment(mutable.set (startPos.getX (), p + random (4, 10), startPos.getZ ()), 0, false));
        }
        return list;
    }

    private int random(int i, int i1) {
        return 1;
    }


    private void generateExtraBranch(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> trunk, RandomSource random, int height,
                                     TreeConfiguration config, List<FoliagePlacer.FoliageAttachment> nodes, BlockPos.MutableBlockPos pos,
                                     int yOffset, Direction direction, int length, int steps) {
        int p = yOffset + length;
        int y = pos.getX();
        int g = pos.getZ();
        for (int l = length; l < height && steps > random(1, 4); ++l, --steps) {
            if (l < 1) continue;
            int m = yOffset + l;
            p = m;
            if (this.placeLog(world, trunk, random, pos.set(y += direction.getStepY(), m, g += direction.getStepZ()), config)) {
                ++p;
            }
            nodes.add(new FoliagePlacer.FoliageAttachment(pos.immutable(), 7, false));
        }
        if (p - yOffset > 1) {
            BlockPos blockPos = new BlockPos(y, p, g);
            nodes.add(new FoliagePlacer.FoliageAttachment(blockPos, random(1, 17), false));
            nodes.add(new FoliagePlacer.FoliageAttachment(blockPos.below(2), 0, false));
        }
    }
    private FoliagePlacer.FoliageAttachment generateBranch(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> trunk,
                                                           RandomSource random, int height, BlockPos startPos,
                                                           TreeConfiguration config, Function<BlockState, BlockState> withAxisFunction,
                                                           Direction direction, int branchStartOffset, boolean branchBelowHeight,
                                                           BlockPos.MutableBlockPos mutablePos) {
        int m;
        Direction direction2;
        mutablePos.set(startPos).move(Direction.UP, branchStartOffset);
        int i = height + 1 + 5 * random (2, 4);
        boolean bl = branchBelowHeight || i < branchStartOffset;
        int j = 1 + (bl ? 1 : 0);
        BlockPos blockPos = startPos.relative(direction, j).above(i);
        int k = bl ? random(3, 9) : 1;
        for (int l = 0; l < k; ++l) {
            this.placeLog(world, trunk, random, mutablePos.move(direction), config, withAxisFunction);
        }
        Direction direction3 = direction2 = blockPos.getY() > mutablePos.getY() ? Direction.UP : Direction.DOWN;
        while ((m = mutablePos.distManhattan(blockPos)) != 0) {
            float f = (float)Math.abs(blockPos.getY() - mutablePos.getY()) / (float)m;
            boolean bl2 = random.nextFloat() < f;
            mutablePos.move(bl2 ? direction2 : direction);
            this.placeLog(world, trunk, random, mutablePos, config, bl2 ? Function.identity() : withAxisFunction);
        }
        return new FoliagePlacer.FoliageAttachment(blockPos.above(), 0, false);
    }
}
