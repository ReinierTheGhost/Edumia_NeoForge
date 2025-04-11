package com.legends.edumia.world.trees.foliageplacer;

import com.legends.edumia.world.trees.EdumiaFoliagePlacerTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;


public class AshiraFoliagePlacer extends FoliagePlacer {

    public static final Codec<AshiraFoliagePlacer> CODEC = Codec.unit(AshiraFoliagePlacer::new);



    public AshiraFoliagePlacer() {
        super(ConstantInt.of(0), ConstantInt.of(2));
    }

    @Override
    protected FoliagePlacerType<?> type() {

        return EdumiaFoliagePlacerTypes.ASHIRA_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader world, FoliageSetter leaves, RandomSource random, TreeConfiguration config, int trunkHeight,
                                 FoliageAttachment foliage, int foliageHeight, int radius, int offset) {

        //for loop to get the height
        for (int y = offset; y >= offset - foliageHeight; y--) {
            int layerWidth = 0;

            if (y <= offset - 2 && y >= offset - 4){
                layerWidth = 1;
            }

            if (y <= offset - 5 && y >= offset - 7){
                layerWidth = 2;
            }

            this.placeLeavesRow(world, leaves, random, config, foliage.pos(), layerWidth, y, foliage.doubleTrunk());
        }

    }



    @Override
    public int foliageHeight(RandomSource randomSource, int i, TreeConfiguration treeConfiguration) {
        return 8;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int absX, int layerY, int absZ, int layerWidth, boolean bool6) {

        int dSq = absX * absX + absZ * absZ;
//
//        if (layerY == -4){
//            if (dSq > (layerWidth - 1) * (layerWidth - 1))
//             return (dSq > (layerWidth + 1) * (layerWidth + 1));
//        }
        if (layerY != -2 && layerY != -4){
            if (dSq > (layerWidth - 1) * (layerWidth - 1))
                return (dSq > layerWidth * layerWidth);
        }

        if (layerY == -4){
            return absX + absZ - 1 > layerWidth;
        }

        return false;
    }
}
