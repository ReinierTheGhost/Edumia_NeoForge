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

public class SilverSpruceFoliagePlacer extends FoliagePlacer {
    public static final Codec<SilverSpruceFoliagePlacer> CODEC = RecordCodecBuilder.create((instance) ->
            foliagePlacerParts(instance).and(IntProvider.codec(0, 24).fieldOf("trunk_height").forGetter((foliage) ->
                    foliage.trunkHeightSpread)
            ).apply(instance, SilverSpruceFoliagePlacer::new));

    private final IntProvider trunkHeightSpread;
    public SilverSpruceFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider trunkHeightSpread) {
        super(radius, offset);
        this.trunkHeightSpread = trunkHeightSpread;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return EdumiaFoliagePlacerTypes.SILVER_SPRUCE_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader world, FoliageSetter leaves, RandomSource random, TreeConfiguration config, int trunkHeight,
                                 FoliageAttachment foliage, int foliageHeight, int radius, int offset) {
        int layerWidth = 0;
        for (int y = offset; y >= offset - foliageHeight; y--) {
            if (y >= offset - 1) {
                layerWidth = 0;
            } else {
                layerWidth++;
                if (layerWidth > radius)
                    layerWidth = 1;
            }
            this.placeLeavesRow(world, leaves, random, config, foliage.pos(), layerWidth + foliage.radiusOffset(), y, foliage.doubleTrunk());
        }
    }

    @Override
    public int foliageHeight(RandomSource random, int trunkHeight, TreeConfiguration config) {
        return Math.max(3, trunkHeight - this.trunkHeightSpread.sample(random));
    }

    @Override
    public int foliageRadius(RandomSource random, int baseHeight) {
        return super.foliageRadius(random, baseHeight);
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        return dx + dz > radius;
    }
}
