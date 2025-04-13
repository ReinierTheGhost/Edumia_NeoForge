package com.legends.edumia.world.trees.foliageplacer;

import com.legends.edumia.world.trees.EdumiaFoliagePlacerTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import javax.swing.tree.TreeNode;

public class NewPalmFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<NewPalmFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec((placer) ->
            foliagePlacerParts(placer).apply(placer, NewPalmFoliagePlacer::new));
    public NewPalmFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return EdumiaFoliagePlacerTypes.NEW_PALM_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader world, FoliageSetter leaves, RandomSource random, TreeConfiguration config, int trunkHeight,
                                 FoliageAttachment foliage, int foliageHeight, int radius, int offset) {
        BlockPos pos = foliage.pos();

        generateLeaf(world, leaves, pos.offset(2, -2, 0), random, config);
        generateLeaf(world, leaves, pos.offset(-2, -2, 0), random, config);
        generateLeaf(world, leaves, pos.offset(0, -2, 2), random, config);
        generateLeaf(world, leaves, pos.offset(0, -2, -2), random, config);

        generateLeaf(world, leaves, pos.offset(2, -1, 0), random, config);
        generateLeaf(world, leaves, pos.offset(-2, -1, 0), random, config);
        generateLeaf(world, leaves, pos.offset(0, -1, 2), random, config);
        generateLeaf(world, leaves, pos.offset(0, -1, -2), random, config);
        generateLeaf(world, leaves, pos.offset(1, -1, 0), random, config);
        generateLeaf(world, leaves, pos.offset(-1, -1, 0), random, config);
        generateLeaf(world, leaves, pos.offset(0, -1, 1), random, config);
        generateLeaf(world, leaves, pos.offset(0, -1, -1), random, config);

        generateLeaf(world, leaves, pos.offset(1, 0, 0), random, config);
        generateLeaf(world, leaves, pos.offset(-1, 0, 0), random, config);
        generateLeaf(world, leaves, pos.offset(0, 0, 1), random, config);
        generateLeaf(world, leaves, pos.offset(0, 0, -1), random, config);
        generateLeaf(world, leaves, pos.offset(1, 0, 1), random, config);
        generateLeaf(world, leaves, pos.offset(-1, 0, -1), random, config);
        generateLeaf(world, leaves, pos.offset(-1, 0, 1), random, config);
        generateLeaf(world, leaves, pos.offset(1, 0, -1), random, config);
        generateLeaf(world, leaves, pos.offset(2, 0, 2), random, config);
        generateLeaf(world, leaves, pos.offset(-2, 0, -2), random, config);
        generateLeaf(world, leaves, pos.offset(2, 0, -2), random, config);
        generateLeaf(world, leaves, pos.offset(-2, 0, 2), random, config);

        generateLeaf(world, leaves, pos.offset(2, 1, 2), random, config);
        generateLeaf(world, leaves, pos.offset(-2, 1, -2), random, config);
        generateLeaf(world, leaves, pos.offset(-2, 1, 2), random, config);
        generateLeaf(world, leaves, pos.offset(1, 1, -1), random, config);
        generateLeaf(world, leaves, pos.offset(-1, 1, 1), random, config);
        generateLeaf(world, leaves, pos.offset(1, 1, 1), random, config);
        generateLeaf(world, leaves, pos.offset(-1, 1, -1), random, config);
        generateLeaf(world, leaves, pos.offset(0, 1, 0), random, config);
        generateLeaf(world, leaves, pos.offset(-2, 1, 1), random, config);
        generateLeaf(world, leaves, pos.offset(2, 1, 1), random, config);
        generateLeaf(world, leaves, pos.offset(-2, 1, -1), random, config);
        generateLeaf(world, leaves, pos.offset(2, 1, -1), random, config);
        generateLeaf(world, leaves, pos.offset(-1, 1, 2), random, config);
        generateLeaf(world, leaves, pos.offset(1, 1, 2), random, config);
        generateLeaf(world, leaves, pos.offset(-1, 1, -2), random, config);
        generateLeaf(world, leaves, pos.offset(1, 1, -2), random, config);

        generateLeaf(world, leaves, pos.offset(1, 2, 1), random, config);
        generateLeaf(world, leaves, pos.offset(-1, 2, -1), random, config);
        generateLeaf(world, leaves, pos.offset(-1, 2, 1), random, config);
        generateLeaf(world, leaves, pos.offset(1, 2, -1), random, config);
    }

    private void generateLeaf(LevelSimulatedReader seedReader, FoliageSetter leaves, BlockPos pos, RandomSource random,
                              TreeConfiguration config) {
        if (TreeFeature.isAirOrLeaves(seedReader, pos)) {
            leaves.set(pos, config.foliageProvider.getState(random, pos).setValue(LeavesBlock.PERSISTENT, true));
        }
    }

    @Override
    public int foliageHeight(RandomSource random, int trunkHeight, TreeConfiguration config) {
        return 0;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        return false;
    }
}
