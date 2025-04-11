package com.legends.edumia.world.treegrowers;

import com.legends.edumia.world.congiguredfeatures.trees.TropicalTreeConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class MangoTreeGrower  extends AbstractTreeGrower {
    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean bees) {
        if (random.nextInt(3) == 0){
            return TropicalTreeConfiguredFeatures.MANGO;
        }else {
            return TropicalTreeConfiguredFeatures.MANGO_TWO;
        }

    }
}
