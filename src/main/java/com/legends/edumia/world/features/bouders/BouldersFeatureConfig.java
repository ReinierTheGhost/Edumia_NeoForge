package com.legends.edumia.world.features.bouders;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class BouldersFeatureConfig implements FeatureConfiguration {
    public static final Codec<BouldersFeatureConfig> CODEC = RecordCodecBuilder.create((instance) ->
             instance.group(BlockStateProvider.CODEC.fieldOf("state_provider").forGetter((config) ->
                    config.stateProfider
                    ), Codec.INT.fieldOf("min_width").orElse(1).forGetter((config) ->
                        config.minWidth
                    ), Codec.INT.fieldOf("max_width").orElse(3).forGetter((config) ->
                        config.maxWidth
                    ), Codec.INT.fieldOf("height_check").orElse(3).forGetter((config) ->
                config.heightCheck
            )).apply(instance, BouldersFeatureConfig::new));

    public final BlockStateProvider stateProfider;
    public final int minWidth;
    public final int maxWidth;
    public final int heightCheck;

    public BouldersFeatureConfig(BlockStateProvider stateProfider, int minWidth, int maxWidth) {
        this(stateProfider, minWidth, maxWidth, 3);
    }
    public BouldersFeatureConfig(BlockStateProvider stateProfider, int minWidth, int maxWidth, int heightCheck) {
        this.stateProfider = stateProfider;
        this.minWidth = minWidth;
        this.maxWidth = maxWidth;
        this.heightCheck = heightCheck;
    }
}
