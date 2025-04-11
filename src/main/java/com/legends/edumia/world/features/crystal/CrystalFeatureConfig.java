package com.legends.edumia.world.features.crystal;


import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class CrystalFeatureConfig implements FeatureConfiguration {
    public static final Codec<CrystalFeatureConfig> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(BlockStateProvider.CODEC.fieldOf("state_provider").forGetter((config) ->
                    config.stateProvider
            ), Codec.INT.fieldOf("tries").orElse(64).forGetter((config) ->
                    config.tries
            ), Codec.INT.fieldOf("xspread").orElse(6).forGetter((config) ->
                    config.xSpread
            ), Codec.INT.fieldOf("yspread").orElse(4).forGetter((config) ->
                    config.ySpread
            ), Codec.INT.fieldOf("zspread").orElse(6).forGetter((config) ->
                    config.zSpread
            )).apply(instance, CrystalFeatureConfig::new));

    public final BlockStateProvider stateProvider;
    public final int tries;
    public final int xSpread;
    public final int ySpread;
    public final int zSpread;

    public CrystalFeatureConfig(BlockStateProvider stateProvider, int tries, int xSpread, int ySpread, int zSpread) {
        this.stateProvider = stateProvider;
        this.tries = tries;
        this.xSpread = xSpread;
        this.ySpread = ySpread;
        this.zSpread = zSpread;
    }
}
