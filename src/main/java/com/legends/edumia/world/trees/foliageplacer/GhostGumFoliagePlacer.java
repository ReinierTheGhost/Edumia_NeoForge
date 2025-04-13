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

public class GhostGumFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<GhostGumFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            foliagePlacerParts(instance).and(Codec.intRange(0, 16).fieldOf("height")
                        .forGetter((foliage) -> foliage.foliageHeight
                        )).apply(instance, GhostGumFoliagePlacer::new));

    private final int foliageHeight;


    public GhostGumFoliagePlacer(IntProvider radius, IntProvider offset, int height) {
        super(radius, offset);
        this.foliageHeight = height;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return EdumiaFoliagePlacerTypes.GHOST_GUM_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader world, FoliageSetter placer, RandomSource  random, TreeConfiguration config, int trunkHeight,
                                 FoliageAttachment treeNode, int foliageHeight, int radius, int offset) {
        for (int y = offset; y >= offset - foliageHeight; --y){
            int foliageExtraWidth = treeNode.radiusOffset();
            int layerWidth = radius + foliageExtraWidth -1;
            if (y > offset - foliageHeight){
                layerWidth -= y /2;
            }

            layerWidth = Math.max(layerWidth, 0);
            this.placeLeavesRow(world, placer, random, config, treeNode.pos(), layerWidth, y, treeNode.doubleTrunk());
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
    protected boolean shouldSkipLocation(RandomSource random, int absX, int layerY, int absZ, int radius, boolean giantTrunk) {
        int dch = absX + absZ;
        int cornerDCh = radius * 2;

        return dch >= cornerDCh || dch >= cornerDCh - 1 && (random.nextInt(3) == 0 || layerY == 0);
    }
}
