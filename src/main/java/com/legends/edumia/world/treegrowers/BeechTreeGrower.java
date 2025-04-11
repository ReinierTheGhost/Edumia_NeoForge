package com.legends.edumia.world.treegrowers;

import com.legends.edumia.world.congiguredfeatures.trees.BeechTreeConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class BeechTreeGrower extends AbstractTreeGrower {
    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean bees) {
        int nextInt = random.nextInt(10);
        if (nextInt == 0 || nextInt == 1){
            return BeechTreeConfiguredFeatures.BIG_BEECH_KEY;
        } else if (nextInt == 2){
            return BeechTreeConfiguredFeatures.PARTY_BEECH_KEY;
        } else if (nextInt == 3){
            return BeechTreeConfiguredFeatures.GIGA_BEECH_KEY;
        }else {
            return BeechTreeConfiguredFeatures.BEECH_KEY;
        }
    }
}
