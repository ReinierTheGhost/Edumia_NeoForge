package com.legends.edumia.world.trees.foliageplacer;

import com.legends.edumia.world.trees.EdumiaFoliagePlacerTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import javax.swing.tree.TreeNode;

public class MahoganyFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<MahoganyFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec((instance) ->
            foliagePlacerParts(instance).and(Codec.intRange(0, 16).fieldOf("foliage_height").forGetter((foliage) ->
                    foliage.foliageHeight)).apply(instance, MahoganyFoliagePlacer::new));
    private final int foliageHeight;

    public MahoganyFoliagePlacer(IntProvider radius, IntProvider offset, int height) {
        super(radius, offset);
        this.foliageHeight = height;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return EdumiaFoliagePlacerTypes.MAHOGANY_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader world, FoliageSetter placer, RandomSource random, TreeConfiguration config, int trunkHeight,
                                 FoliageAttachment treeNode, int foliageHeight, int radius, int offset) {
        int leafTop = offset;
        int leafBottom = offset - foliageHeight - treeNode.radiusOffset();

        for (int y = leafTop; y >= leafBottom; --y){
            int layerWidth = radius + treeNode.radiusOffset() - y;
            this.placeLeavesRowWithHangingLeavesBelow(world, placer, random, config, treeNode.pos(), layerWidth, y, treeNode.doubleTrunk(), 50.0f, 45.0f);
        }

    }

    @Override
    public int foliageHeight(RandomSource random, int trunkHeight, TreeConfiguration config) {
        return this.foliageHeight;
    }

    @Override
    public int foliageRadius(RandomSource random, int baseHeight) {
        return super.foliageRadius(random, baseHeight);
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int absX, int layerY, int absZ, int layerWidth, boolean giantTrunk) {
        int dSq = absX * absX + absZ * absZ;
        return dSq > layerWidth * layerWidth;
    }


}
