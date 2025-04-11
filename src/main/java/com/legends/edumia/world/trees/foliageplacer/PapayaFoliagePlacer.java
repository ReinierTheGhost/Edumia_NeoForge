package com.legends.edumia.world.trees.foliageplacer;

import com.legends.edumia.world.trees.EdumiaFoliagePlacerTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedRW;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import javax.swing.tree.TreeNode;

public class PapayaFoliagePlacer extends FoliagePlacer {
    private static final Direction[] DIRECTIONS = new Direction[] { Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST };
    public static final Codec<PapayaFoliagePlacer> CODEC = RecordCodecBuilder.create((instance) ->
        foliagePlacerParts(instance).apply(instance, PapayaFoliagePlacer::new));
    public PapayaFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return EdumiaFoliagePlacerTypes.PAPAYA_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader world, FoliageSetter leaves, RandomSource random, TreeConfiguration config, int trunkHeight,
                                 FoliageAttachment foliage, int foliageHeight, int radius, int offset) {
        // Top + shape
        this.placeLeavesRow(world, leaves, random, config, foliage.pos(), 1, 1, foliage.doubleTrunk());

        BlockPos origin = foliage.pos();
        // Center leaves
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                for (int y = -1; y <= 0; y++) {
                    if (y == -1 && (Math.abs(x) == 1 && Math.abs(z) == 1) && random.nextBoolean()) {
                        continue;
                    }

                    BlockPos local = origin.offset(x, y, z);
                    set(world, local, random, config);
                }
            }
        }

        // Arms
        for (Direction direction : DIRECTIONS) {
            set(world, origin.relative(direction, 2), random, config);
            set(world, origin.relative(direction, 3), random, config);
            set(world, origin.relative(direction, 3).below(), random, config);
            set(world, origin.relative(direction, 4).below(), random, config);
        }
    }

    private static void set(LevelSimulatedReader world, BlockPos pos, RandomSource random, TreeConfiguration config) {
        if (TreeFeature.isAirOrLeaves(world, pos)) {
            ((LevelSimulatedRW)world).setBlock(pos, config.foliageProvider.getState(random, pos), 19);
        }
    }

    @Override
    public int foliageHeight(RandomSource random, int trunkHeight, TreeConfiguration config) {
        return 0;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        return radius != 0 && dx == radius && dz == radius;
    }

}
