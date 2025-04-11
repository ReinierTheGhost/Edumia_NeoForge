package com.legends.edumia.world.trees.foliageplacer;

import com.legends.edumia.world.trees.EdumiaFoliagePlacerTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import javax.swing.tree.TreeNode;

public class ClusterFoliagePlacer extends FoliagePlacer {
    public static final Codec<ClusterFoliagePlacer> CODEC = RecordCodecBuilder.create((instance)->
            foliagePlacerParts(instance).apply(instance, ClusterFoliagePlacer::new));
    public ClusterFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return EdumiaFoliagePlacerTypes.CLUSTER_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader world, FoliageSetter leaves, RandomSource random, TreeConfiguration config, int trunkHeight,
                                 FoliageAttachment foliage, int foliageHeight, int radius, int offset) {
        int sphereWidth = radius + foliage.radiusOffset();
        int leafTop = sphereWidth;
        int leafBottom = -sphereWidth;

        for (int y = leafTop; y >= leafBottom; --y){
            this.placeLeavesRow(world, leaves, random, config, foliage.pos(), sphereWidth, y, foliage.doubleTrunk());
        }
    }

    @Override
    public int foliageHeight(RandomSource random, int trunkHeight, TreeConfiguration config) {
        return 0;
    }

    @Override
    public int foliageRadius(RandomSource random, int baseHeight) {
        return super.foliageRadius(random, baseHeight);
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int absX, int layerY, int absZ, int layerWidth, boolean giantTrunk) {
        int dy = layerY;
        int dSq = absX * absX + dy * dy + absZ * absZ;
        if (dSq >= (layerWidth - 1) * (layerWidth - 1))
            return (dSq >= layerWidth * layerWidth || random.nextInt(3) == 0);
        return false;
    }
}
