package com.legends.edumia.world.features.reeds;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class ReedsFeatureConfig implements FeatureConfiguration {
    public static final Codec<ReedsFeatureConfig> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(BlockStateProvider.CODEC.fieldOf("state_provider").forGetter((config) ->
                    config.stateProvider
            ), Codec.INT.fieldOf("tries").orElse(32).forGetter((config) ->
                    config.tries
            ), Codec.INT.fieldOf("xspread").orElse(5).forGetter((config) ->
                    config.xSpread
            ), Codec.INT.fieldOf("yspread").orElse(2).forGetter((config) ->
                    config.ySpread
            ), Codec.INT.fieldOf("zspread").orElse(5).forGetter((config) ->
                    config.zSpread
            ), Codec.FLOAT.fieldOf("fully_grown_change").orElse(0.75f).forGetter((config) ->
                    config.fullyGrownChange
            )).apply(instance, ReedsFeatureConfig::new));

    public final BlockStateProvider stateProvider;
    public final int tries;
    public final int xSpread;
    public final int ySpread;
    public final int zSpread;
    public final float fullyGrownChange;

    public ReedsFeatureConfig(BlockStateProvider stateProvider, int tries, int xSpread, int ySpread, int zSpread, float fullyGrownChange) {
        this.stateProvider = stateProvider;
        this.tries = tries;
        this.xSpread = xSpread;
        this.ySpread = ySpread;
        this.zSpread = zSpread;
        this.fullyGrownChange = fullyGrownChange;
    }
}
