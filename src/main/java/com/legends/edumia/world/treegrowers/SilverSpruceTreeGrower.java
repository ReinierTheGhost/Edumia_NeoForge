package com.legends.edumia.world.treegrowers;

import com.legends.edumia.world.congiguredfeatures.trees.BorealTreeConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class SilverSpruceTreeGrower extends AbstractTreeGrower {
    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean bees) {
        return BorealTreeConfiguredFeatures.SILVER_SPRUCE_TREE_KEY;
    }
}
