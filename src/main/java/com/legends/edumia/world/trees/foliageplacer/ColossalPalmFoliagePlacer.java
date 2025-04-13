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
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import javax.swing.tree.TreeNode;

public class ColossalPalmFoliagePlacer extends FoliagePlacer {

    protected final BlockStateProvider nerfProvider;

    public static final MapCodec<ColossalPalmFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec((instance) ->
            foliagePlacerParts(instance).and(BlockStateProvider.CODEC.fieldOf("nerf_provider").forGetter(
                    nerf -> nerf.nerfProvider)).apply(instance, ColossalPalmFoliagePlacer::new));
    protected ColossalPalmFoliagePlacer(IntProvider radius, IntProvider offset, BlockStateProvider nerfProvider) {
        super(radius, offset);
        this.nerfProvider = nerfProvider;
    }

    public ColossalPalmFoliagePlacer(BlockStateProvider nerfProvider) {
        this(ConstantInt.of(0), ConstantInt.of(0), nerfProvider);

    }

    @Override
    protected FoliagePlacerType<?> type() {
        return EdumiaFoliagePlacerTypes.COLOSSAL_PALM_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader world, FoliageSetter leaves, RandomSource random, TreeConfiguration config,
                                 int trunkHeight,
                                 FoliageAttachment foliage, int foliageHeight, int radius, int offset) {
        BlockPos pos = foliage.pos();

        int leave = random.nextInt(10);
        //north
        generateNerf(world, leaves, pos.offset( 0, -1,  -1), random, config);
        generateNerf(world, leaves, pos.offset( 0, 0,  -1), random, config);

        generateNerf(world, leaves, pos.offset( 0, 1,  -2), random, config);
        generateNerf(world, leaves, pos.offset( 0, 2,  -2), random, config);

        generateNerf(world, leaves, pos.offset( 0, 2,  -3), random, config);
        generateNerf(world, leaves, pos.offset( 0, 3,  -3), random, config);
        generateNerf(world, leaves, pos.offset( 0, 4,  -3), random, config);

        generateNerf(world, leaves, pos.offset( 0, 5,  -4), random, config);

        generateNerf(world, leaves, pos.offset( 0, 6,  -5), random, config);
        generateNerf(world, leaves, pos.offset( 0, 6,  -6), random, config);
        generateNerf(world, leaves, pos.offset( 0, 6,  -7), random, config);

        generateNerf(world, leaves, pos.offset( 0, 7,  -8), random, config);
        generateNerf(world, leaves, pos.offset( 0, 7,  -9), random, config);
        generateNerf(world, leaves, pos.offset( 0, 7,  -10), random, config);
        generateNerf(world, leaves, pos.offset( 0, 7,  -11), random, config);

        generateNerf(world, leaves, pos.offset( 0, 8,  -12), random, config);
        generateNerf(world, leaves, pos.offset( 0, 8,  -13), random, config);
        generateNerf(world, leaves, pos.offset( 0, 8,  -14), random, config);
        generateNerf(world, leaves, pos.offset( 0, 8,  -15), random, config);

        generateNerf(world, leaves, pos.offset( 0, 9,  -16), random, config);
        generateNerf(world, leaves, pos.offset( 0, 9,  -17), random, config);
        generateNerf(world, leaves, pos.offset( 0, 9,  -18), random, config);
        generateNerf(world, leaves, pos.offset( 0, 9,  -19), random, config);
        generateNerf(world, leaves, pos.offset( 0, 9,  -20), random, config);
        generateNerf(world, leaves, pos.offset( 0, 9,  -21), random, config);
        generateNerf(world, leaves, pos.offset( 0, 9,  -22), random, config);

        generateNerf(world, leaves, pos.offset( 0, -2,  -2), random, config);
        generateNerf(world, leaves, pos.offset( 0, -2,  -3), random, config);
        generateNerf(world, leaves, pos.offset( 2, -2,  -3), random, config);
        generateNerf(world, leaves, pos.offset( 0, -2,  -4), random, config);
        generateNerf(world, leaves, pos.offset( 1, -2,  -4), random, config);
        generateNerf(world, leaves, pos.offset( 0, -2,  -5), random, config);
        generateNerf(world, leaves, pos.offset( 0, -2,  -6), random, config);
        generateNerf(world, leaves, pos.offset( 0, -2,  -7), random, config);

        generateNerf(world, leaves, pos.offset( 0, -1,  -8), random, config);
        generateNerf(world, leaves, pos.offset( 0, -1,  -9), random, config);
        generateNerf(world, leaves, pos.offset( 0, -1,  -10), random, config);
        generateNerf(world, leaves, pos.offset( 0, -1,  -11), random, config);
        generateNerf(world, leaves, pos.offset( 0, -1,  -12), random, config);
        generateNerf(world, leaves, pos.offset( 0, -1,  -13), random, config);
        generateNerf(world, leaves, pos.offset( 0, -1,  -14), random, config);
        generateNerf(world, leaves, pos.offset( 0, -1,  -15), random, config);

        generateNerf(world, leaves, pos.offset( 0, -2,  -16), random, config);
        generateNerf(world, leaves, pos.offset( 0, -2,  -17), random, config);
        generateNerf(world, leaves, pos.offset( 0, -2,  -18), random, config);

        generateNerf(world, leaves, pos.offset( 0, -3,  -18), random, config);
        generateNerf(world, leaves, pos.offset( 0, -3,  -19), random, config);
        generateNerf(world, leaves, pos.offset( 0, -3,  -20), random, config);
        generateNerf(world, leaves, pos.offset( 0, -3,  -21), random, config);
        generateNerf(world, leaves, pos.offset( 0, -3,  -22), random, config);
        generateNerf(world, leaves, pos.offset( 0, -3,  -23), random, config);

        generateNerf(world, leaves, pos.offset( 0, -4,  -22), random, config);
        generateNerf(world, leaves, pos.offset( 0, -4,  -23), random, config);
        generateNerf(world, leaves, pos.offset( 0, -4,  -24), random, config);
        generateNerf(world, leaves, pos.offset( 0, -4,  -25), random, config);
        generateNerf(world, leaves, pos.offset( 0, -4,  -26), random, config);

        generateNerf(world, leaves, pos.offset( 0, -5,  -26), random, config);
        generateNerf(world, leaves, pos.offset( 0, -5,  -27), random, config);
        generateNerf(world, leaves, pos.offset( 0, -5,  -28), random, config);

        generateNerf(world, leaves, pos.offset( 0, -6,  -29), random, config);
        generateNerf(world, leaves, pos.offset( 0, -6,  -30), random, config);

        //east
        generateLeaf(world, leaves, pos.offset( 1, 0,  0), random, config);
        generateLeaf(world, leaves, pos.offset( 2, 1,  0), random, config);
        generateLeaf(world, leaves, pos.offset( 2, 2,  0), random, config);

        //south
        generateLeaf(world, leaves, pos.offset( 0, -3,  2), random, config);
        generateLeaf(world, leaves, pos.offset( 0, -4,  2), random, config);
        generateLeaf(world, leaves, pos.offset( 0, -5,  2), random, config);
        generateLeaf(world, leaves, pos.offset( 0, -3,  3), random, config);


        //west
        generateLeaf(world, leaves, pos.offset( -2, -2,  0), random, config);
        generateLeaf(world, leaves, pos.offset( -2, -3,  0), random, config);



    }

    @Override
    public int foliageHeight(RandomSource random, int trunkHeight, TreeConfiguration config) {
        return 0;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        return false;
    }

    private void generateLeaf(LevelSimulatedReader seedReader, FoliageSetter leaves, BlockPos pos, RandomSource random,
                              TreeConfiguration config) {
        if (TreeFeature.isAirOrLeaves(seedReader, pos)) {
            leaves.set(pos, config.foliageProvider.getState(random, pos).setValue(LeavesBlock.PERSISTENT, true));
        }
    }

    private void generateNerf(LevelSimulatedReader seedReader, FoliageSetter leaves, BlockPos pos, RandomSource random,
                              TreeConfiguration config) {
        if (TreeFeature.isAirOrLeaves(seedReader, pos)) {
            leaves.set(pos, this.nerfProvider.getState(random, pos).setValue(LeavesBlock.PERSISTENT, true));
        }
    }
}