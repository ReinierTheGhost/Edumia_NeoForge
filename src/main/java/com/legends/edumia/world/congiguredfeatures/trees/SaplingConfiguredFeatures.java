package com.legends.edumia.world.congiguredfeatures.trees;

import com.legends.edumia.Edumia;
import com.legends.edumia.world.placedfeatures.trees.TropicalTreePlacedFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

public class SaplingConfiguredFeatures {


    public static final ResourceKey<ConfiguredFeature<?, ?>> PALM_TREES = registerKey("tree/palm/palm_trees");



    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
        HolderGetter<PlacedFeature> placed = context.lookup(Registries.PLACED_FEATURE);
        Holder.Reference<PlacedFeature> palm = placed.getOrThrow(TropicalTreePlacedFeatures.BIG_PARASOL_PALM);
        Holder.Reference<PlacedFeature> palm2 = placed.getOrThrow(TropicalTreePlacedFeatures.SMALL_PARASOL_PALM);
        Holder.Reference<PlacedFeature> palm3 = placed.getOrThrow(TropicalTreePlacedFeatures.RANDOM_PALM);


        register(context, PALM_TREES, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(
                new WeightedPlacedFeature(palm, 0.4f),
                new WeightedPlacedFeature(palm2, 0.4f)),
                palm3));
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
