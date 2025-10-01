package com.legends.edumia.world.trees.saplings;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class ModSaplingGenerator {
    private final ResourceKey<ConfiguredFeature<?, ?>> treeKey;

    public ModSaplingGenerator(ResourceKey<ConfiguredFeature<?, ?>> treeKey) {
        this.treeKey = treeKey;
    }

    protected ResourceKey<ConfiguredFeature<?, ?>> getTreeFeature(RandomSource random, boolean bees) {
        return treeKey;
    }
}
