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

public class DragonBloodFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<DragonBloodFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec((instance) ->
            foliagePlacerParts(instance).and(Codec.intRange(0, 16).fieldOf("foliage_heiht").forGetter((foliage) ->
                    foliage.foliageHeight)
            ).apply(instance, DragonBloodFoliagePlacer::new));

    private final int foliageHeight;
    public DragonBloodFoliagePlacer(IntProvider radius, IntProvider offset, int foliageHeight) {
        super(radius, offset);
        this.foliageHeight = foliageHeight;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return EdumiaFoliagePlacerTypes.DRAGON_BLOOD_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader world, FoliageSetter leaves, RandomSource random, TreeConfiguration config, int trunkHeight,
                                 FoliageAttachment foliage, int foliageHeight, int radius, int offset) {
        for (int y = offset; y >= offset - foliageHeight; y--) {
            int layerWidth = radius + foliage.radiusOffset() - y;
            this.placeLeavesRow(world, leaves, random, config, foliage.pos(), layerWidth, y, foliage.doubleTrunk());
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
    protected boolean shouldSkipLocation(RandomSource random, int absX, int layerY, int absZ, int layerWidth, boolean bool6) {


        if (layerY == 0) {
            return absX + absZ - 2 > layerWidth;
        }
        if (layerY == -1){
            return absX + absZ - 2 > layerWidth;
            //return  absX > layerWidth -2 && absZ > layerWidth -2 && layerWidth > 0;
        }
        if (layerY == -2){
            return absX + absZ - 3 > layerWidth;
        }

        return false;
    }
}
