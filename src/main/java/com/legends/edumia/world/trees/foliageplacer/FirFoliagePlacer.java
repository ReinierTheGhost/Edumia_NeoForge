package com.legends.edumia.world.trees.foliageplacer;

import com.legends.edumia.world.trees.EdumiaFoliagePlacerTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import javax.swing.tree.TreeNode;

public class FirFoliagePlacer extends FoliagePlacer {
    public static final Codec<FirFoliagePlacer> CODEC = RecordCodecBuilder.create((instance) ->
            foliagePlacerParts(instance).and(IntProvider.codec(0, 24).fieldOf("foliae_height").forGetter((foliage) ->
                    foliage.heightSpread)).apply(instance, FirFoliagePlacer::new));
    private final IntProvider heightSpread;

    public FirFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider heightSpread) {
        super(radius, offset);
        this.heightSpread = heightSpread;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return EdumiaFoliagePlacerTypes.FIR_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader world, FoliageSetter leaves, RandomSource random, TreeConfiguration config, int trunkHeight,
                                 FoliageAttachment foliage, int foliageHeight, int radius, int offset) {
        int baseFoliageWidth = foliage.radiusOffset();
        this.placeLeavesRow(world, leaves, random, config, foliage.pos(), baseFoliageWidth, offset, foliage.doubleTrunk());
        this.placeLeavesRow(world, leaves, random, config, foliage.pos(), baseFoliageWidth, offset - 1, foliage.doubleTrunk());
        int leafBottom = offset - foliageHeight;
        int topY = offset - 2;
        int leafLayers = topY - leafBottom + 1;
        int sections = radius;
        int sectionHeight = Mth.ceil((float) leafLayers / (float) sections);
        int curSectionWidth = 1;
        int curSectionHeight = 0;
        for (int y = topY; y >= leafBottom; y--) {
            this.placeLeavesRow(world, leaves, random, config, foliage.pos(), baseFoliageWidth + curSectionWidth, y, foliage.doubleTrunk());
            curSectionHeight++;
            if (curSectionHeight >= sectionHeight) {
                curSectionWidth++;
                curSectionHeight = 0;
            }
        }
    }

    @Override
    public int foliageHeight(RandomSource random, int trunkHeight, TreeConfiguration config) {
        return Math.min(this.heightSpread.sample(random), trunkHeight - 1);
    }

    @Override
    public int foliageRadius(RandomSource random, int baseHeight) {
        return super.foliageRadius(random, baseHeight);
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        int taxicab = dx + dz;
        return taxicab > radius && radius > 0;
    }
}
