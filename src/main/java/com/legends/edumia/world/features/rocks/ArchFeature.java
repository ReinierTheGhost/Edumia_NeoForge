package com.legends.edumia.world.features.rocks;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class ArchFeature  extends Feature<ArchFeatureConfig>  {

    public ArchFeature(Codec<ArchFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<ArchFeatureConfig> featurePlaceContext) {
        return false;
    }
}
