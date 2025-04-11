package com.legends.edumia.world.trees.foliageplacer;

import com.legends.edumia.world.trees.EdumiaFoliagePlacerTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import javax.swing.tree.TreeNode;

public class CypressFoliagePlacer extends FoliagePlacer {
    public static final Codec<CypressFoliagePlacer> CODEC = RecordCodecBuilder.create((instance) ->
           foliagePlacerParts(instance).and(IntProvider.codec(0, 24).fieldOf("trunk_height").forGetter((foliage) ->
                   foliage.trunkHeightSpread)).apply(instance, CypressFoliagePlacer::new));
    private final IntProvider trunkHeightSpread;

    public CypressFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider trunkHeightSpread) {
        super(radius, offset);
        this.trunkHeightSpread = trunkHeightSpread;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return EdumiaFoliagePlacerTypes.CYPRESS_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader world, FoliageSetter leaves, RandomSource random, TreeConfiguration config, int trunkHeight,
                                 FoliageAttachment foliage, int foliageHeight, int radius, int offset){
        int topCrossY = offset - 2;
        int bottomCrossesHighestY = offset - foliageHeight + random.nextInt(3);
        for (int y = offset; y >= offset - foliageHeight; y--) {
            int layerWidth = foliage.radiusOffset();
            if (y < topCrossY && y > bottomCrossesHighestY){
                ++layerWidth;
            }

            this.placeLeavesRow(world, leaves, random, config, foliage.pos(), layerWidth, y, foliage.doubleTrunk());
            if (y == topCrossY || y <= bottomCrossesHighestY){
                for (Direction dir : Direction.Plane.HORIZONTAL){
                    this.placeLeavesRow(world, leaves, random, config, foliage.pos().relative(dir), layerWidth, y, foliage.doubleTrunk());
                }

            }
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
        return false;
    }
}
