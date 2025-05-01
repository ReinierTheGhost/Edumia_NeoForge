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

public class ParasolPalmFoliagePlacer extends FoliagePlacer {

    public static final MapCodec<ParasolPalmFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec((instance) ->
            foliagePlacerParts(instance).apply(instance, ParasolPalmFoliagePlacer::new));
    protected ParasolPalmFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    public ParasolPalmFoliagePlacer() {
        this(ConstantInt.of(0), ConstantInt.of(0));
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return EdumiaFoliagePlacerTypes.PARASOL_PALM_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader world, FoliageSetter leaves, RandomSource random, TreeConfiguration config, int trunkHeight,
                                 FoliageAttachment foliage, int foliageHeight, int radius, int offset) {
        BlockPos pos = foliage.pos();

        // Place leaves for all layers
        this.placeLeavesRow(world, leaves, random, config, pos, 4, 0, foliage.doubleTrunk());
        this.placeLeavesRow(world, leaves, random, config, pos, 4, -1, foliage.doubleTrunk());
        this.placeLeavesRow(world, leaves, random, config, pos, 4, -2, foliage.doubleTrunk());

    }

    @Override
    public int foliageHeight(RandomSource random, int trunkHeight, TreeConfiguration config) {
        return 0;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        if (y == 0) {
            // Only place leaf at center (0,0)
            return !(dx == 0 && dz == 0);
        }
        else if (y == -1) {
            // Cross pattern with diagonal leaves
            return !(
                    // Cross pattern
                    (dx == 0 && Math.abs(dz) <= 3) ||
                            (dz == 0 && Math.abs(dx) <= 3) ||
                            // Diagonal leaves
                            (Math.abs(dx) == 1 && Math.abs(dz) == 1) ||
                            (Math.abs(dx) == 2 && Math.abs(dz) == 2)
            );
        }
        else if (y == -2) {
            // Outer edges and corners
            return !(
                    // Edges
                    (dx == 0 && Math.abs(dz) == 4) ||
                            (dz == 0 && Math.abs(dx) == 4) ||
                            // Corners
                            (Math.abs(dx) == 3 && Math.abs(dz) == 3)
            );
        }

        return true;

    }

    private void generateLeaf(LevelSimulatedReader seedReader, FoliageSetter leaves, BlockPos pos, RandomSource random,
                              TreeConfiguration config) {
        if (TreeFeature.isAirOrLeaves(seedReader, pos)) {
            leaves.set(pos, config.foliageProvider.getState(random, pos).setValue(LeavesBlock.PERSISTENT, true));
        }
    }
}
