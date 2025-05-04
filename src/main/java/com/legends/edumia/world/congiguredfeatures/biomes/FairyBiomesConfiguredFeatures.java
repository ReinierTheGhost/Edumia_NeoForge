package com.legends.edumia.world.congiguredfeatures.biomes;

import com.legends.edumia.Edumia;
import com.legends.edumia.world.congiguredfeatures.trees.*;
import com.legends.edumia.world.placedfeatures.ModPlacedFeatures;
import com.legends.edumia.world.placedfeatures.trees.TropicalTreePlacedFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

public class FairyBiomesConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> FAIRY_FOREST_TREES_LAYER_1 = registerKey("trees/layer_1");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FAIRY_FOREST_TREES_LAYER_2 = registerKey("trees/layer_2");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FAIRY_FOREST_TREES_LAYER_3 = registerKey("trees/layer_3");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FAIRY_FOREST_TREES_LAYER_4 = registerKey("trees/layer_4");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FAIRY_FOREST_TREES_LAYER_5 = registerKey("trees/layer_5");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
        var holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);
        var registryEntryLookup = context.lookup(Registries.PLACED_FEATURE);

        Holder.Reference<PlacedFeature> noting = registryEntryLookup.getOrThrow(ModPlacedFeatures.NOTING);
        Holder.Reference<PlacedFeature> banana = registryEntryLookup.getOrThrow(TropicalTreePlacedFeatures.BANANA);

        register(context, FAIRY_FOREST_TREES_LAYER_4, Feature.RANDOM_SELECTOR,
                new RandomFeatureConfiguration(List.of(
//                        new WeightedPlacedFeature(
//                                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(JungleTreeConfiguredFeatures.JUNGLE_TREE_4)), 0.15f),
                        new WeightedPlacedFeature(
                                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(LeopardTreeConfiguredFeatures.LEOPARD_1)), 0.10f),
                        new WeightedPlacedFeature(
                                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(KapokTreeConfiguredFeatures.KAPOK_1)), 0.02f),
                        new WeightedPlacedFeature(
                                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(MirwoodNutTreeConfiguredFeatures.MIRWOODNUT_1)), 0.05f),
                        new WeightedPlacedFeature(
                                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(MahoganyConfiguredFeatures.MAHOGANY_1)), 0.10f),
                        new WeightedPlacedFeature(
                                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(PalmConfiguredFeatures.GROUND_PALM_1)), 0.20f),
                        new WeightedPlacedFeature(
                                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(PalmConfiguredFeatures.GROUP_PALM_1)), 0.15f),



                        new WeightedPlacedFeature(
                                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(LeopardTreeConfiguredFeatures.LEOPARD_2)), 0.10f),
                        new WeightedPlacedFeature(
                                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(KapokTreeConfiguredFeatures.KAPOK_2)), 0.02f),
                        new WeightedPlacedFeature(
                                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(MahoganyConfiguredFeatures.MAHOGANY_2)), 0.10f),
                        new WeightedPlacedFeature(
                                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(PalmConfiguredFeatures.GROUND_PALM_2)), 0.20f),


                        new WeightedPlacedFeature(
                                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(LeopardTreeConfiguredFeatures.LEOPARD_3)), 0.10f),
                        new WeightedPlacedFeature(
                                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(KapokTreeConfiguredFeatures.KAPOK_3)), 0.02f),
                        new WeightedPlacedFeature(
                                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(MahoganyConfiguredFeatures.MAHOGANY_3)), 0.10f),
                        new WeightedPlacedFeature(
                                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(PalmConfiguredFeatures.GROUND_PALM_3)), 0.20f),


                        new WeightedPlacedFeature(
                                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(LeopardTreeConfiguredFeatures.LEOPARD_4)), 0.10f),
                        new WeightedPlacedFeature(
                                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(KapokTreeConfiguredFeatures.KAPOK_4)), 0.02f),
                        new WeightedPlacedFeature(
                                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(MahoganyConfiguredFeatures.MAHOGANY_4)), 0.10f),
                        new WeightedPlacedFeature(
                                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(PalmConfiguredFeatures.GROUND_PALM_4)), 0.20f),


                        new WeightedPlacedFeature(
                                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(KapokTreeConfiguredFeatures.KAPOK_5)), 0.02f),
                        new WeightedPlacedFeature(
                                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(PalmConfiguredFeatures.GROUND_PALM_5)), 0.20f),

                        new WeightedPlacedFeature(
                                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(KapokTreeConfiguredFeatures.KAPOK_6)), 0.02f),
                        new WeightedPlacedFeature(
                                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(PalmConfiguredFeatures.GROUND_PALM_6)), 0.20f),

                        new WeightedPlacedFeature(
                                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(KapokTreeConfiguredFeatures.KAPOK_7)), 0.02f),
                        new WeightedPlacedFeature(
                                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(PalmConfiguredFeatures.GROUND_PALM_7)), 0.20f),

                        new WeightedPlacedFeature(
                                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(KapokTreeConfiguredFeatures.KAPOK_8)), 0.02f),
                        new WeightedPlacedFeature(
                                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(PalmConfiguredFeatures.GROUND_PALM_8)), 0.20f),

                        new WeightedPlacedFeature(
                                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(KapokTreeConfiguredFeatures.KAPOK_9)), 0.02f),

                        new WeightedPlacedFeature(
                                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(KapokTreeConfiguredFeatures.KAPOK_10)), 0.02f)



                ), noting));

    }
    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "biome/fairy/" +name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext< ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature,
                                                                                          FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
