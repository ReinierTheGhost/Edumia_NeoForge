package com.legends.edumia.world.trees.foliageplacer;

import com.legends.edumia.world.trees.EdumiaFoliagePlacerTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import javax.swing.tree.TreeNode;

public class StackedFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<StackedFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            foliagePlacerParts(instance).and(
                    Codec.intRange(1, 5).fieldOf("stacks").forGetter(placer -> placer.stacks))
            .apply(instance, StackedFoliagePlacer::new));
    private final int stacks;

    public StackedFoliagePlacer(IntProvider radius, IntProvider offset, int stacks) {
        super(radius, offset);
        this.stacks = stacks;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return EdumiaFoliagePlacerTypes.STACKED_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader world, FoliageSetter leaves, RandomSource random, TreeConfiguration config, int trunkHeight,
                                 FoliageAttachment foliage, int foliageHeight, int radius, int offset) {
        boolean flag = foliage.doubleTrunk();
        BlockPos blockPos = foliage.pos().above(offset);
        int r = radius + foliage.radiusOffset();
        int h = -stacks * 4;
        placeLeavesRow(world, leaves, random, config, blockPos, r - 2, h - 1, flag);
        placeLeavesRow(world, leaves, random, config, blockPos, r - 1, h, flag);

        for (int i = 0; i < stacks; i++) {
            int j = -4 * i;
            placeLeavesRow(world, leaves, random, config, blockPos, r, j - 3, flag);
            placeLeavesRow(world, leaves, random, config, blockPos, r, j - 2, flag);
            placeLeavesRow(world, leaves, random, config, blockPos, r, j - 1, flag);
            placeLeavesRow(world, leaves, random, config, blockPos, r - 1, j, flag);
        }

        placeLeavesRow(world, leaves, random, config, blockPos, r - 2, 1, flag);
    }

    @Override
    public int foliageHeight(RandomSource random, int trunkHeight, TreeConfiguration config) {
        return 0;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        return dx + dz > radius || dx > 2 || dz > 2;
    }
}
