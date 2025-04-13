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

public class PinusFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<PinusFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec((instance) ->
            foliagePlacerParts(instance).and(IntProvider.codec(0, 24).fieldOf("foliage_height").forGetter((foliage) ->
                    foliage.heightSpread)).apply(instance, PinusFoliagePlacer::new));
    private final IntProvider heightSpread;
    public PinusFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider heightSpread) {
        super(radius, offset);
        this.heightSpread = heightSpread;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return EdumiaFoliagePlacerTypes.PINUS_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader world, FoliageSetter leaves, RandomSource random, TreeConfiguration config, int trunkHeight,
                                 FoliageAttachment foliage, int foliageHeight, int radius, int offset) {
        int baseFoliageWidth = foliage.radiusOffset();
        this.placeLeavesRow(world, leaves, random, config, foliage.pos(), baseFoliageWidth, offset, foliage.doubleTrunk());
        this.placeLeavesRow(world, leaves, random, config, foliage.pos(), baseFoliageWidth + 1, offset - 1, foliage.doubleTrunk());
        int y = offset - 3;

        while (y > offset - foliageHeight){
            int layerType = random.nextInt(3);
            if (layerType == 0){
                this.placeLeavesRow(world, leaves, random, config, foliage.pos(), baseFoliageWidth + 1, y, foliage.doubleTrunk());
                y -= 2;
            }else if (layerType == 1){
                --y;
                this.placeLeavesRow(world, leaves, random, config, foliage.pos(), baseFoliageWidth + radius - 2, y + 1, foliage.doubleTrunk());
                this.placeLeavesRow(world, leaves, random, config, foliage.pos(), baseFoliageWidth + radius - 1, y, foliage.doubleTrunk());
                this.placeLeavesRow(world, leaves, random, config, foliage.pos(), baseFoliageWidth + radius -2, y - 1, foliage.doubleTrunk());
                y -= 3;
            } else if (layerType == 2) {
                this.placeLeavesRow(world, leaves, random, config, foliage.pos(), baseFoliageWidth + radius - 1, y + 1, foliage.doubleTrunk());
                this.placeLeavesRow(world, leaves, random, config, foliage.pos(), baseFoliageWidth + radius, y, foliage.doubleTrunk());
                this.placeLeavesRow(world, leaves, random, config, foliage.pos(), baseFoliageWidth + radius -1, y - 1, foliage.doubleTrunk());
                y -= 3;
            }

        }
    }

    @Override
    public int foliageHeight(RandomSource random, int trunkHeight, TreeConfiguration config) {
        return this.heightSpread.sample(random);
    }

    @Override
    public int foliageRadius(RandomSource random, int baseHeight) {
        return super.foliageRadius(random, baseHeight);
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        return dx + dz > radius && radius > 0;
    }
}
