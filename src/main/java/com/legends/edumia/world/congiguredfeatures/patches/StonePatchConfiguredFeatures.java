package com.legends.edumia.world.congiguredfeatures.patches;

import com.legends.edumia.Edumia;
import com.legends.edumia.core.TagLoader;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class StonePatchConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> CALCITE_LOWER = registerKey("calcite_lower");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CALCITE_UPPER = registerKey("calcite_upper");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
//        register(context, CALCITE_LOWER, Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(TagLoader.Blocks.CALCITE,
//                BlockStateProvider.simple(Blocks.CALCITE),
//
//        ));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "patch/stone/" +name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext< ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature,
                                                                                          FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
