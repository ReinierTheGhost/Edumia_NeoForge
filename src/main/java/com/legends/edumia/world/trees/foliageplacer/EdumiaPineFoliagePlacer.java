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

public class EdumiaPineFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<EdumiaPineFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec((instance) ->
            foliagePlacerParts(instance).and(IntProvider.codec(0, 24).fieldOf("foliage_height").forGetter((foliage) ->
                    foliage.heightSpread)
            ).apply(instance, EdumiaPineFoliagePlacer::new));


    private final IntProvider heightSpread;
    public EdumiaPineFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider heightSpread) {
        super(radius, offset);
        this.heightSpread = heightSpread;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return EdumiaFoliagePlacerTypes.PINE_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader world, FoliageSetter leaves, RandomSource random, TreeConfiguration config, int trunkHeight,
                                 FoliageAttachment foliage, int foliageHeight, int radius, int offset) {
        int layerWidth = random.nextInt(2);
        int nextMaxLayerWidth = 1;
        int nextStartingLayerWidth = 0;

        for (int y = offset; y >= offset - foliageHeight; --y){
            this.placeLeavesRow(world, leaves, random, config, foliage.pos(), layerWidth, y, foliage.doubleTrunk());
            if (layerWidth >= nextMaxLayerWidth){
                layerWidth = nextStartingLayerWidth;
                nextStartingLayerWidth = 1;
                nextMaxLayerWidth = Math.min(nextMaxLayerWidth + 1, radius);
            }else{
                ++layerWidth;
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
        return dx == radius && dz == radius && radius > 0;
    }
}
