package com.legends.edumia.world.trees.foliageplacer;

import com.legends.edumia.world.trees.EdumiaFoliagePlacerTypes;
import com.mojang.serialization.Codec;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class HeveaFoliagePlacer extends FoliagePlacer {
    public static final Codec<HeveaFoliagePlacer> CODEC = Codec.unit(HeveaFoliagePlacer::new);


    /**
     * constructor, since this tree always has the same foliage the constructor is empty,
     * The super has a 0 for its radius, and a 2 for the offset.
     * This tree has always 3 blocks above the trunk.
     * The default or foliage placers is always 1 block above the trunk.
     * So for offset you should always do 1 + offset for the end result you want
     */
    public HeveaFoliagePlacer() {
        super(ConstantInt.of(0), ConstantInt.of(2));
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return EdumiaFoliagePlacerTypes.HEVEA_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader world, FoliageSetter leaves, RandomSource random, TreeConfiguration config, int trunkHeight,
                                 FoliageAttachment foliage, int foliageHeight, int radius, int offset) {

        /**
         * for loop int y has the initial value of offset, which is 2 in this case
         * Ass long as y is bigger or equal (>=) to offset min foliage height, the loop will continue;
         * after the code block has been run, y will be deccresed by 1 (y--)
         */
        for (int y = offset; y >= offset - foliageHeight; y--) {
            /**
             * int layerWidth is being initialised here.
             * it is responsible for how big the radius of the leaf squares are
             */
            int layerWidth = 0;


            /**
             * this if statement checks if the leaves are between the first layer and the fourth, from bottom to top
             * If this is true the layerWith will be 1 meaning you will have a square of 3 * 3 blocks
             * If not it stays 0
             */
            if (y <= offset - 2 && y >= offset - 5) {
                layerWidth = 1;
            }

            /**
             * this will place the square of leaves
             */
            this.placeLeavesRow(world, leaves, random, config, foliage.pos(), layerWidth, y, foliage.doubleTrunk());
        }

    }

    @Override
    public int foliageHeight(RandomSource randomSource, int i, TreeConfiguration treeConfiguration) {
        return 6;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int absX, int layerY, int absZ, int layerWidth, boolean bool6) {

        /**
         * int for the corner block calculation
         */
        int dSq = absX * absX + absZ * absZ;

        /**
         * Checks if the layer is between the third and fourth layer form bottom to top
         * if so it checks if dSq is bigger layerWidth - 1 * layerWith - 1
         * if so it will return a true if dsq > layerWidth * layerWidth
         * resulting in that the corner blocks of those two layers are not placed because they are invalid for placement
         */
        if (layerY == 0 || layerY == -1){
            if (dSq > (layerWidth - 1) * (layerWidth - 1))
                return (dSq > layerWidth * layerWidth);
        }
        return false;
    }
}
