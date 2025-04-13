package com.legends.edumia.world.trees.foliageplacer;


import com.legends.edumia.world.trees.EdumiaFoliagePlacerTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import javax.swing.tree.TreeNode;

public class AlmondFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<AlmondFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            foliagePlacerParts(instance).apply(instance, AlmondFoliagePlacer::new));
    protected AlmondFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    public AlmondFoliagePlacer() {
        this(ConstantInt.of(0), ConstantInt.of(0));
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return EdumiaFoliagePlacerTypes.ALMOND_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader world, FoliageSetter placer, RandomSource random, TreeConfiguration config, int trunkHeight,
                                 FoliageAttachment foliage, int foliageHeight, int radius, int offset) {
        int leafStart = foliageHeight - 3;
        int leafTop = foliageHeight;
        for (int m = leafStart; m <= leafTop; m++) {
            int leafRange = 0;
            int maxRange = 2;
            int j2 = leafTop - m;
            if (j2 == 0) {
                leafRange = 1;
            } else if (j2 == 1) {
                leafRange = 2;
            } else if (j2 == 2) {
                leafRange = 2;
            } else {
                leafRange = 1;
            }

            this.placeLeavesRow(world, placer, random, config, foliage.pos(), leafRange, m, foliage.doubleTrunk());
        }

    }

    @Override
    public int foliageHeight(RandomSource random, int trunkHeight, TreeConfiguration config) {
        return 0;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int absX, int y, int absZ, int radius, boolean giantTrunk) {
        int dch = absX + absZ;
        int cornerDCh = radius * 2;

        return dch >= cornerDCh && (y == 0 || y == -3 || y == -2) || dch >= cornerDCh - 1 && (y == -1) ;
    }
}
