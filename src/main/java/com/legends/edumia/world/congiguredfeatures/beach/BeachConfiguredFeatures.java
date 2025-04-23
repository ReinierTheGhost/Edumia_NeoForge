package com.legends.edumia.world.congiguredfeatures.beach;

import com.legends.edumia.Edumia;
import com.legends.edumia.blocks.Layer;
import com.legends.edumia.core.blocksets.SandBlockSets;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;


public class BeachConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> WHITE_SAND_LAYER_FIRST = registerKey("layers/white/first");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WHITE_SAND_LAYER_SECOND = registerKey("layers/white/second");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WHITE_SAND_LAYER_THIRD = registerKey("layers/white/third");
    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
        register(context, WHITE_SAND_LAYER_FIRST, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration( new WeightedStateProvider(
                SimpleWeightedRandomList.<BlockState>builder()
                        .add(SandBlockSets.WHITE_SAND.layer().get().defaultBlockState().setValue(Layer.LAYERS, 6), 5)
                        .add(SandBlockSets.WHITE_SAND.layer().get().defaultBlockState().setValue(Layer.LAYERS, 7), 4))));

        register(context, WHITE_SAND_LAYER_SECOND, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration( new WeightedStateProvider(
                SimpleWeightedRandomList.<BlockState>builder()
                        .add(SandBlockSets.WHITE_SAND.layer().get().defaultBlockState().setValue(Layer.LAYERS, 4), 1)
                        .add(SandBlockSets.WHITE_SAND.layer().get().defaultBlockState().setValue(Layer.LAYERS, 5), 1))));

        register(context, WHITE_SAND_LAYER_THIRD, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration( new WeightedStateProvider(
                SimpleWeightedRandomList.<BlockState>builder()
                        .add(SandBlockSets.WHITE_SAND.layer().get().defaultBlockState().setValue(Layer.LAYERS, 2), 4)
                        .add(SandBlockSets.WHITE_SAND.layer().get().defaultBlockState().setValue(Layer.LAYERS, 3), 3))));


    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "beach/" + name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext< ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature,
                                                                                          FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
