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

public class TropicalFruitFoliagePlacer  extends FoliagePlacer {
    public static final MapCodec<TropicalFruitFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            foliagePlacerParts(instance)
                    .apply(instance, TropicalFruitFoliagePlacer::new));

    private TropicalFruitFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    public TropicalFruitFoliagePlacer() {
        this(ConstantInt.of(0), ConstantInt.of(0));
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return EdumiaFoliagePlacerTypes.TROPICAL_FRUIT_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader world, FoliageSetter placer, RandomSource random, TreeConfiguration config, int trunkHeight,
                                 FoliageAttachment foliage, int foliageHeight, int radius, int offset) {
        int leafStart = offset - 3 + random.nextInt(2);
        int leafTop = offset;

        for (int y = leafStart; y <= leafTop; y++){
            int leafRange = (y == leafTop) ? 2 : ((y == leafStart) ? 1 : 3);

            placeLeavesRow(world, placer, random, config, foliage.pos(), leafRange, y, foliage.doubleTrunk());
        }
    }

    @Override
    public int foliageHeight(RandomSource random, int trunkHeight, TreeConfiguration config) {
        return 0;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        int dCh = dx + dz;
        if (dCh <= 4 && ((dx < radius && dz < radius) || random.nextInt(3) != 0)){
            return false;
        }else{
            return true;
        }
    }
}
