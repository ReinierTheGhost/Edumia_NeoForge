package com.legends.edumia.world.trees.trunkplacers;

import com.legends.edumia.world.trees.EdumiaTrunkPlacerTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;

import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.CherryTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.GiantTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class YellowMerantiTrunkPlacer extends TrunkPlacer {
    public static final Codec<YellowMerantiTrunkPlacer> CODEC = RecordCodecBuilder.create (objectInstance ->
            trunkPlacerParts(objectInstance).apply (objectInstance, YellowMerantiTrunkPlacer::new));

    public YellowMerantiTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
        super (baseHeight, firstRandomHeight, secondRandomHeight);

    }


    @Override
    protected TrunkPlacerType<?> type() {
        return EdumiaTrunkPlacerTypes.YELLOW_MERANTI_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> trunk,
                                                            RandomSource random, int height, BlockPos startPos, TreeConfiguration config) {
        boolean bl2;

        CherryTrunkPlacer.setDirtAt(world, trunk, random, startPos.below(), config);
        int j = Math.max (0, height - 1 + 2);
        int i = Math.max (0, height - 1 + 2);
        if (j >= i) {
            ++j;
        }
        int k = 0;
        boolean bl = (true);
        boolean bl3 = bl2 = k >= 2;
        int l = bl ? height : (bl2 ? Math.max (i, j) + 1 : i + 1);
        for (int m = 0; m < l; ++m) {
            this.placeLog (world, trunk, random, startPos.above (m), config);
        }
        ArrayList<FoliagePlacer.FoliageAttachment> list = new ArrayList<FoliagePlacer.FoliageAttachment>();
        if (bl) {
            list.add (new FoliagePlacer.FoliageAttachment (startPos.above (l), 0, true));
        }
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos ();
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        Direction finalDirection = direction;
        Function<BlockState, BlockState> function = state -> (BlockState) state.trySetValue(RotatedPillarBlock.AXIS, finalDirection.getAxis());
        list.add (this.generateBranch(world, trunk, random, height, startPos, config, function, direction, i, i < l - 1, mutable));
        if (bl2) {
            list.add (this.generateBranch(world, trunk, random, height, startPos, config, function, direction.getOpposite(), j, i < l - 1, mutable));
        }
        for (int p = 4; p < height; ++p) {
            int y = startPos.getY () + p;
            if (this.placeLog(world, trunk, random, mutable.set (startPos.getX (), y, startPos.getZ ()), config) && p < height - 1 && random.nextFloat () < 5) {
                direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
                int g = (p + 3);
                int r = Math.max (0, g * 3 + 1);
                int m = (3 * 3);
                this.generateExtraBranch (world, trunk, random, height, config, list, mutable, y, direction, r, m);
            }
            if (p != height - 1) continue;
            list.add (new FoliagePlacer.FoliageAttachment(mutable.set (startPos.getX (), p + 1, startPos.getZ ()), 7, true));

            for (int t = height - 2 - random.nextInt(4); t > height / 2; t -= 2 + random.nextInt(4)) {
                float f = random.nextFloat() * ((float)Math.PI * 2);
                int b = 0;
                int h = 0;
                for (int x = 0; l < 5; ++l) {
                    b = (int)(1.5f + Mth.cos(f) * (float)l);
                    h = (int)(1.5f + Mth.sin(f) * (float)l);
                    BlockPos blockPos = startPos.offset(b, i - 3 + l / 2, h);
                    this.placeLog(world, trunk, random, blockPos, config);
                }
                BlockPos blockPos = startPos.below();
                GiantTrunkPlacer.setDirtAt(world, trunk, random, blockPos, config);
                GiantTrunkPlacer.setDirtAt(world, trunk, random, blockPos.east(), config);
                GiantTrunkPlacer.setDirtAt(world, trunk, random, blockPos.south(), config);
                GiantTrunkPlacer.setDirtAt(world, trunk, random, blockPos.south().east(), config);
                for (int z = 0; z < height; ++z) {
                    this.setLog(world, trunk, random, mutable, config, startPos, 0, z, 0);
                    if (z >= height - 1) continue;
                    this.setLog(world, trunk, random, mutable, config, startPos, 1, z, 0);
                    this.setLog(world, trunk, random, mutable, config, startPos, 1, z, 1);
                    this.setLog(world, trunk, random, mutable, config, startPos, 0, z, 1);
                }
                list.add(new FoliagePlacer.FoliageAttachment(startPos.offset(b, t, k), -2, true));
            }

        }
        return list;
    }

    private void setLog(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> trunk, RandomSource random,
                        BlockPos.MutableBlockPos mutable, TreeConfiguration config, BlockPos startPos, int i, int i1, int i2) {
    }


    private void generateExtraBranch(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> replacer, RandomSource random,
                                     int height, TreeConfiguration config, List<FoliagePlacer.FoliageAttachment> nodes,
                                     BlockPos.MutableBlockPos pos, int yOffset, Direction direction, int length, int steps) {
        int p = yOffset + length;
        int y = pos.getX();
        int g = pos.getZ();
        for (int l = length; l < height && steps > 3 * 6; ++l, --steps) {
            if (l < 1) continue;
            int m = yOffset + l;
            p = m;
            if (this.placeLog(world, replacer, random, pos.set(y += direction.getStepX(), m, g += direction.getStepZ()), config)) {
                ++p;
            }
            nodes.add(new FoliagePlacer.FoliageAttachment(pos.immutable(), 7, true));
        }
        if (p - yOffset > 1) {
            BlockPos blockPos = new BlockPos(y, p, g);
            nodes.add(new FoliagePlacer.FoliageAttachment(blockPos, 5, true));
            nodes.add(new FoliagePlacer.FoliageAttachment(blockPos.below(2), 0, true));
        }
    }
    private FoliagePlacer.FoliageAttachment generateBranch(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> replacer,
                                                           RandomSource random, int height, BlockPos startPos, TreeConfiguration config,
                                                           Function<BlockState, BlockState> withAxisFunction, Direction direction,
                                                           int branchStartOffset, boolean branchBelowHeight,
                                                           BlockPos.MutableBlockPos mutablePos) {
        int m;
        Direction direction2;
        mutablePos.set(startPos).move(Direction.UP, branchStartOffset);
        int i = height + 1 + 5;
        boolean bl = branchBelowHeight || i < branchStartOffset;
        int j = 1 + (bl ? 1 : 0);
        BlockPos blockPos = startPos.relative(direction, j).above(i);
        int k = bl ? 2 : 1;
        for (int l = 2; l < k; ++l) {
            this.placeLog(world, replacer, random, mutablePos.move(direction), config, withAxisFunction);
        }
        Direction direction3 = direction2 = blockPos.getY() > mutablePos.getY() ? Direction.UP : Direction.DOWN;
        while ((m = mutablePos.distManhattan(blockPos)) != 0) {
            float f = (float)Math.abs(blockPos.getY() - mutablePos.getY()) / (float)m;
            boolean bl2 = random.nextFloat() < f;
            mutablePos.move(bl2 ? direction2 : direction);
            this.placeLog(world, replacer, random, mutablePos, config, bl2 ? Function.identity() : withAxisFunction);
        }
        return new FoliagePlacer.FoliageAttachment(blockPos.above(), 0, true);
    }
}
