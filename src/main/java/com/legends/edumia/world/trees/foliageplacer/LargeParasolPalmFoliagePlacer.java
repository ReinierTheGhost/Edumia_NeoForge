package com.legends.edumia.world.trees.foliageplacer;

import com.legends.edumia.world.trees.EdumiaFoliagePlacerTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import javax.swing.tree.TreeNode;

public class LargeParasolPalmFoliagePlacer extends FoliagePlacer {

    public static final MapCodec<LargeParasolPalmFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec((instance) ->
            foliagePlacerParts(instance).apply(instance, LargeParasolPalmFoliagePlacer::new));
    protected LargeParasolPalmFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    public LargeParasolPalmFoliagePlacer() {
        this(ConstantInt.of(0), ConstantInt.of(0));
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return EdumiaFoliagePlacerTypes.LARGE_PARASOL_PALM_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader world, FoliageSetter leaves, RandomSource random, TreeConfiguration config, int trunkHeight,
                                 FoliageAttachment foliage, int foliageHeight, int radius, int offset) {
        BlockPos pos = foliage.pos();

        // Place leaves at all three levels
        for (int y = -1; y <= 1; y++) {
            // Use a large enough radius to cover all leaf positions
            this.placeLeavesRow(world, leaves, random, config, pos, 7, y, foliage.doubleTrunk());
        }


//        generateLeaf(world, leaves, pos.offset( 0, -1,  -7), random, config);
//        generateLeaf(world, leaves, pos.offset( -1, -1,  -6), random, config);
//        generateLeaf(world, leaves, pos.offset( 1, -1,  -6), random, config);
//        generateLeaf(world, leaves, pos.offset( -5, -1,  -5), random, config);
//        generateLeaf(world, leaves, pos.offset( 5, -1,  -5), random, config);
//        generateLeaf(world, leaves, pos.offset( -6, -1,  -1), random, config);
//        //placeLog(world, leaves, pos.add( 0, 1,  -1), random, config);
//        generateLeaf(world, leaves, pos.offset( 6, -1,  -1), random, config);
//        generateLeaf(world, leaves, pos.offset( -7, -1,  0), random, config);
//        //placeLog(world, leaves, pos.add( -1, 1,  0), random, config);
//        //placeLog(world, leaves, pos.add( 0, 1,  0), random, config);
//        //placeLog(world, leaves, pos.add( 1, 1,  0), random, config);
//        generateLeaf(world, leaves, pos.offset(  7, -1,  0), random, config);
//        generateLeaf(world, leaves, pos.offset(  -6, -1,  1), random, config);
//        //placeLog(world, leaves, pos.add( i + 0, 1,  1), random, config);
//        generateLeaf(world, leaves, pos.offset( 6, -1,  1), random, config);
//        generateLeaf(world, leaves, pos.offset( -5, -1,  5), random, config);
//        generateLeaf(world, leaves, pos.offset( 5, -1,  5), random, config);
//        generateLeaf(world, leaves, pos.offset( -1, -1,  6), random, config);
//        generateLeaf(world, leaves, pos.offset( 1, -1,  6), random, config);
//        generateLeaf(world, leaves, pos.offset( 0, -1,  7), random, config);
//        generateLeaf(world, leaves, pos.offset( 0, 0,  -6), random, config);
//        generateLeaf(world, leaves, pos.offset( -4, 0,  -5), random, config);
//        generateLeaf(world, leaves, pos.offset( -1, 0,  -5), random, config);
//        generateLeaf(world, leaves, pos.offset( 1, 0,  -5), random, config);
//        generateLeaf(world, leaves, pos.offset( 4, 0,  -5), random, config);
//        generateLeaf(world, leaves, pos.offset( -5, 0,  -4), random, config);
//        generateLeaf(world, leaves, pos.offset( -3, 0,  -4), random, config);
//        generateLeaf(world, leaves, pos.offset( -1, 0,  -4), random, config);
//        generateLeaf(world, leaves, pos.offset( 1, 0,  -4), random, config);
//        generateLeaf(world, leaves, pos.offset( 3, 0,  -4), random, config);
//        generateLeaf(world, leaves, pos.offset( 5, 0,  -4), random, config);
//        generateLeaf(world, leaves, pos.offset( -4, 0,  -3), random, config);
//        generateLeaf(world, leaves, pos.offset( -2, 0,  -3), random, config);
//        generateLeaf(world, leaves, pos.offset( -1, 0,  -3), random, config);
//        generateLeaf(world, leaves, pos.offset( 1, 0,  -3), random, config);
//        generateLeaf(world, leaves, pos.offset( 2, 0,  -3), random, config);
//        generateLeaf(world, leaves, pos.offset( 4, 0,  -3), random, config);
//        generateLeaf(world, leaves, pos.offset( -3, 0,  -2), random, config);
//        generateLeaf(world, leaves, pos.offset( -1, 0,  -2), random, config);
//        generateLeaf(world, leaves, pos.offset( 1, 0,  -2), random, config);
//        generateLeaf(world, leaves, pos.offset( 3, 0,  -2), random, config);
//        generateLeaf(world, leaves, pos.offset( -5, 0,  -1), random, config);
//        generateLeaf(world, leaves, pos.offset( -4, 0,  -1), random, config);
//        generateLeaf(world, leaves, pos.offset( -3, 0,  -1), random, config);
//        generateLeaf(world, leaves, pos.offset( -2, 0,  -1), random, config);
//        generateLeaf(world, leaves, pos.offset( -1, 0,  -1), random, config);
//        generateLeaf(world, leaves, pos.offset( 0, 0,  -1), random, config);
//        generateLeaf(world, leaves, pos.offset( 1, 0,  -1), random, config);
//        generateLeaf(world, leaves, pos.offset( 2, 0,  -1), random, config);
//        generateLeaf(world, leaves, pos.offset( 3, 0,  -1), random, config);
//        generateLeaf(world, leaves, pos.offset( 4, 0,  -1), random, config);
//        generateLeaf(world, leaves, pos.offset( 5, 0,  -1), random, config);
//        generateLeaf(world, leaves, pos.offset( -6, 0,  0), random, config);
//        generateLeaf(world, leaves, pos.offset( -1, 0,  0), random, config);
//        generateLeaf(world, leaves, pos.offset( 0, 0,  0), random, config);
//        generateLeaf(world, leaves, pos.offset( 1, 0,  0), random, config);
//        generateLeaf(world, leaves, pos.offset( 6, 0,  0), random, config);
//        generateLeaf(world, leaves, pos.offset( -5, 0,  1), random, config);
//        generateLeaf(world, leaves, pos.offset( -4, 0,  1), random, config);
//        generateLeaf(world, leaves, pos.offset( -3, 0,  1), random, config);
//        generateLeaf(world, leaves, pos.offset( -2, 0,  1), random, config);
//        generateLeaf(world, leaves, pos.offset( -1, 0,  1), random, config);
//        generateLeaf(world, leaves, pos.offset( 0, 0,  1), random, config);
//        generateLeaf(world, leaves, pos.offset( 1, 0,  1), random, config);
//        generateLeaf(world, leaves, pos.offset( 2, 0,  1), random, config);
//        generateLeaf(world, leaves, pos.offset( 3, 0,  1), random, config);
//        generateLeaf(world, leaves, pos.offset( 4, 0,  1), random, config);
//        generateLeaf(world, leaves, pos.offset( 5, 0,  1), random, config);
//        generateLeaf(world, leaves, pos.offset( -3, 0,  2), random, config);
//        generateLeaf(world, leaves, pos.offset( -1, 0,  2), random, config);
//        generateLeaf(world, leaves, pos.offset( 1, 0,  2), random, config);
//        generateLeaf(world, leaves, pos.offset( 3, 0,  2), random, config);
//        generateLeaf(world, leaves, pos.offset( -4, 0,  3), random, config);
//        generateLeaf(world, leaves, pos.offset( -2, 0,  3), random, config);
//        generateLeaf(world, leaves, pos.offset( -1, 0,  3), random, config);
//        generateLeaf(world, leaves, pos.offset( 1, 0,  3), random, config);
//        generateLeaf(world, leaves, pos.offset( 2, 0,  3), random, config);
//        generateLeaf(world, leaves, pos.offset( 4, 0,  3), random, config);
//        generateLeaf(world, leaves, pos.offset( -5, 0,  4), random, config);
//        generateLeaf(world, leaves, pos.offset( -3, 0,  4), random, config);
//        generateLeaf(world, leaves, pos.offset( -1, 0,  4), random, config);
//        generateLeaf(world, leaves, pos.offset( 1, 0,  4), random, config);
//        generateLeaf(world, leaves, pos.offset( 3, 0,  4), random, config);
//        generateLeaf(world, leaves, pos.offset( 5, 0,  4), random, config);
//        generateLeaf(world, leaves, pos.offset( -4, 0,  5), random, config);
//        generateLeaf(world, leaves, pos.offset( -1, 0,  5), random, config);
//        generateLeaf(world, leaves, pos.offset( 1, 0,  5), random, config);
//        generateLeaf(world, leaves, pos.offset( 4, 0,  5), random, config);
//        generateLeaf(world, leaves, pos.offset( 0, 0,  6), random, config);
//        generateLeaf(world, leaves, pos.offset( 0, 1,  -5), random, config);
//        generateLeaf(world, leaves, pos.offset( -4, 1,  -4), random, config);
//        generateLeaf(world, leaves, pos.offset( 0, 1,  -4), random, config);
//        generateLeaf(world, leaves, pos.offset( 4, 1,  -4), random, config);
//        generateLeaf(world, leaves, pos.offset( -3, 1,  -3), random, config);
//        generateLeaf(world, leaves, pos.offset( 0, 1,  -3), random, config);
//        generateLeaf(world, leaves, pos.offset( 3, 1,  -3), random, config);
//        generateLeaf(world, leaves, pos.offset( -2, 1,  -2), random, config);
//        generateLeaf(world, leaves, pos.offset( 0, 1,  -2), random, config);
//        generateLeaf(world, leaves, pos.offset( 2, 1,  -2), random, config);
//        generateLeaf(world, leaves, pos.offset( -1, 1,  -1), random, config);
//        generateLeaf(world, leaves, pos.offset( 0, 1,  -1), random, config);
//        generateLeaf(world, leaves, pos.offset( 1, 1,  -1), random, config);
//        generateLeaf(world, leaves, pos.offset( -5, 1,  0), random, config);
//        generateLeaf(world, leaves, pos.offset( -4, 1,  0), random, config);
//        generateLeaf(world, leaves, pos.offset( -3, 1,  0), random, config);
//        generateLeaf(world, leaves, pos.offset( -2, 1,  0), random, config);
//        generateLeaf(world, leaves, pos.offset( -1, 1,  0), random, config);
//        generateLeaf(world, leaves, pos.offset( 1, 1,  0), random, config);
//        generateLeaf(world, leaves, pos.offset( 2, 1,  0), random, config);
//        generateLeaf(world, leaves, pos.offset( 3, 1,  0), random, config);
//        generateLeaf(world, leaves, pos.offset( 4, 1,  0), random, config);
//        generateLeaf(world, leaves, pos.offset( 5, 1,  0), random, config);
//        generateLeaf(world, leaves, pos.offset( -1, 1,  1), random, config);
//        generateLeaf(world, leaves, pos.offset( 0, 1,  1), random, config);
//        generateLeaf(world, leaves, pos.offset( 1, 1,  1), random, config);
//        generateLeaf(world, leaves, pos.offset( -2, 1,  2), random, config);
//        generateLeaf(world, leaves, pos.offset( 0, 1,  2), random, config);
//        generateLeaf(world, leaves, pos.offset( 2, 1,  2), random, config);
//        generateLeaf(world, leaves, pos.offset( -3, 1,  3), random, config);
//        generateLeaf(world, leaves, pos.offset( 0, 1,  3), random, config);
//        generateLeaf(world, leaves, pos.offset( 3, 1,  3), random, config);
//        generateLeaf(world, leaves, pos.offset( -4, 1,  4), random, config);
//        generateLeaf(world, leaves, pos.offset( 0, 1,  4), random, config);
//        generateLeaf(world, leaves, pos.offset( 4, 1,  4), random, config);
//        generateLeaf(world, leaves, pos.offset( 0, 1,  5), random, config);

    }

    @Override
    public int foliageHeight(RandomSource random, int trunkHeight, TreeConfiguration config) {
        return 0;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        // Create sets of valid coordinates for each y level
        // Skip center block at y=1
        if (y == 1 && dx == 0 && dz == 0) {
            return true;
        }

        if (y == -1) {
            // Check if the coordinate pair matches any of the y=-1 positions
            return !((dx == 0 && dz == -7) ||
                    (Math.abs(dx) == 1 && dz == -6) ||
                    (Math.abs(dx) == 5 && dz == -5) ||
                    (Math.abs(dx) == 6 && dz == -1) ||
                    (Math.abs(dx) == 7 && dz == 0) ||
                    (Math.abs(dx) == 6 && dz == 1) ||
                    (Math.abs(dx) == 5 && dz == 5) ||
                    (Math.abs(dx) == 1 && dz == 6) ||
                    (dx == 0 && dz == 7));
        }
        else if (y == 0) {
            // Similar pattern for y=0 level
            return !((dx == 0 && Math.abs(dz) == 6) ||
                    ((Math.abs(dx) == 1 || Math.abs(dx) == 4) && Math.abs(dz) == 5) ||
                    ((Math.abs(dx) == 1 || Math.abs(dx) == 3 || Math.abs(dx) == 5) && Math.abs(dz) == 4) ||
                    ((Math.abs(dx) == 1 || Math.abs(dx) == 2 || Math.abs(dx) == 4) && Math.abs(dz) == 3) ||
                    ((Math.abs(dx) == 1 || Math.abs(dx) == 3) && Math.abs(dz) == 2) ||
                    (Math.abs(dx) <= 5 && Math.abs(dz) == 1) ||
                    (Math.abs(dx) <= 6 && dz == 0));
        }
        else if (y == 1) {
            // Pattern for y=1 level
            return !((dx == 0 && Math.abs(dz) == 5) ||
                    ((Math.abs(dx) == 4 || dx == 0) && Math.abs(dz) == 4) ||
                    ((Math.abs(dx) == 3 || dx == 0) && Math.abs(dz) == 3) ||
                    ((Math.abs(dx) == 2 || dx == 0) && Math.abs(dz) == 2) ||
                    (Math.abs(dx) <= 1 && Math.abs(dz) == 1) ||
                    (Math.abs(dx) <= 5 && dz == 0));
        }
        return true;

    }

    private void generateLeaf(LevelSimulatedReader seedReader, FoliageSetter leaves, BlockPos pos, RandomSource random, TreeConfiguration config) {
        if (TreeFeature.isAirOrLeaves(seedReader, pos)) {
            leaves.set(pos, config.foliageProvider.getState(random, pos).setValue(LeavesBlock.PERSISTENT, true));
        }
    }
}
