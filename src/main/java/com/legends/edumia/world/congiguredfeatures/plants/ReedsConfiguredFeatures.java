package com.legends.edumia.world.congiguredfeatures.plants;

import com.legends.edumia.Edumia;
import com.legends.edumia.core.blocksets.ModNatureBlocks;
import com.legends.edumia.world.features.EdumiaFeatures;
import com.legends.edumia.world.features.reeds.ReedsFeatureConfig;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;

public class ReedsConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> REEDS = registerKey("reeds");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PAPYRUS = registerKey("papyrus");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
        register(context, REEDS, EdumiaFeatures.REEDS.get(), new ReedsFeatureConfig(new WeightedStateProvider(
                SimpleWeightedRandomList.<BlockState>builder().add(ModNatureBlocks.REEDS.get().defaultBlockState(),900)
                        .add(ModNatureBlocks.DRIED_REEDS.get().defaultBlockState(), 100)),
                32, 5, 2, 5, 0.75f));

        register(context, PAPYRUS, EdumiaFeatures.REEDS.get(), new ReedsFeatureConfig(new WeightedStateProvider(
                SimpleWeightedRandomList.<BlockState>builder().add(ModNatureBlocks.PAPYRUS.get().defaultBlockState(),900)),
                32, 5, 2, 5, 0.75f));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext< ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature,
                                                                                          FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
