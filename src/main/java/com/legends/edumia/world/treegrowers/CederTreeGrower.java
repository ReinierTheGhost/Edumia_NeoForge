package com.legends.edumia.world.treegrowers;

import com.legends.edumia.world.congiguredfeatures.trees.TemperateTreeConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class CederTreeGrower extends AbstractTreeGrower {
    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean bees) {
        if (random.nextInt(4) == 0){
            return TemperateTreeConfiguredFeatures.LARGE_CEDER_KEY;
        }else {
            return TemperateTreeConfiguredFeatures.CEDER_KEY;
        }

    }
}
