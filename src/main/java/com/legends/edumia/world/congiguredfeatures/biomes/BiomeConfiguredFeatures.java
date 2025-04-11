package com.legends.edumia.world.congiguredfeatures.biomes;

import com.legends.edumia.Edumia;

import com.legends.edumia.world.placedfeatures.ModPlacedFeatures;
import com.legends.edumia.world.placedfeatures.trees.BorealTreePlacedFeatures;
import com.legends.edumia.world.placedfeatures.trees.OakTreePlacedFeatures;
import com.legends.edumia.world.placedfeatures.trees.TemperateTreePlacedFeatures;
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

public class BiomeConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> DARK_ELVEN_OAK_TREES_KEY = registerKey("dark_elven/trees");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
        HolderGetter<PlacedFeature> registryEntryLookup = context.lookup(Registries.PLACED_FEATURE);

        Holder.Reference<PlacedFeature> normal_green_oak = registryEntryLookup.getOrThrow(OakTreePlacedFeatures.LIST_GREEN_OAK_PLACED_TREE_KEY);
        Holder.Reference<PlacedFeature> normal_red_oak = registryEntryLookup.getOrThrow(OakTreePlacedFeatures.LIST_RED_OAK_PLACED_TREE_KEY);
        Holder.Reference<PlacedFeature> normal_black_oak = registryEntryLookup.getOrThrow(OakTreePlacedFeatures.LIST_BLACK_OAK_PLACED_TREE_KEY);

        Holder.Reference<PlacedFeature> noting = registryEntryLookup.getOrThrow(ModPlacedFeatures.NOTING);
        Holder.Reference<PlacedFeature> small_green_oak = registryEntryLookup.getOrThrow(OakTreePlacedFeatures.LIST_SMALL_GREEN_OAK_PLACED_TREE_KEY);
        Holder.Reference<PlacedFeature> small_red_oak = registryEntryLookup.getOrThrow(OakTreePlacedFeatures.LIST_SMALL_RED_OAK_PLACED_TREE_KEY);
        Holder.Reference<PlacedFeature> small_black_oak = registryEntryLookup.getOrThrow(OakTreePlacedFeatures.LIST_SMALL_BLACK_OAK_PLACED_TREE_KEY);

        Holder.Reference<PlacedFeature> large_green_oak = registryEntryLookup.getOrThrow(OakTreePlacedFeatures.LIST_LARGE_GREEN_OAK_PLACED_TREE_KEY);
        Holder.Reference<PlacedFeature> large_red_oak = registryEntryLookup.getOrThrow(OakTreePlacedFeatures.LIST_LARGE_RED_OAK_PLACED_TREE_KEY);
        Holder.Reference<PlacedFeature> large_black_oak = registryEntryLookup.getOrThrow(OakTreePlacedFeatures.LIST_LARGE_BLACK_OAK_PLACED_TREE_KEY);

        Holder.Reference<PlacedFeature> mega_green_oak = registryEntryLookup.getOrThrow(OakTreePlacedFeatures.LIST_MEGA_GREEN_OAK_PLACED_TREE_KEY);
        Holder.Reference<PlacedFeature> mega_red_oak = registryEntryLookup.getOrThrow(OakTreePlacedFeatures.LIST_MEGA_RED_OAK_PLACED_TREE_KEY);
        Holder.Reference<PlacedFeature> mega_black_oak = registryEntryLookup.getOrThrow(OakTreePlacedFeatures.LIST_MEGA_BLACK_OAK_PLACED_TREE_KEY);

        Holder.Reference<PlacedFeature> silver_spruce = registryEntryLookup.getOrThrow(BorealTreePlacedFeatures.SILVER_SPRUCE_TREE_LIST);
        Holder.Reference<PlacedFeature> holly = registryEntryLookup.getOrThrow(TemperateTreePlacedFeatures.HOLLY_TREE_KEY);
        Holder.Reference<PlacedFeature> holly_with_bees = registryEntryLookup.getOrThrow(TemperateTreePlacedFeatures.HOLLY_BEES_TREE_KEY);

        Holder.Reference<PlacedFeature> colossal_green_oak = registryEntryLookup.getOrThrow(OakTreePlacedFeatures.LIST_COLOSSAL_GREEN_OAK_PLACED_TREE_KEY);
        Holder.Reference<PlacedFeature> colossal_red_oak = registryEntryLookup.getOrThrow(OakTreePlacedFeatures.LIST_COLOSSAL_RED_OAK_PLACED_TREE_KEY);
        Holder.Reference<PlacedFeature> colossal_black_oak = registryEntryLookup.getOrThrow(OakTreePlacedFeatures.LIST_MEGA_BLACK_OAK_PLACED_TREE_KEY);

        register(context, DARK_ELVEN_OAK_TREES_KEY, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(
                // Red Oak
                new WeightedPlacedFeature(mega_red_oak, 0.038f),   // 10% of 38%
                new WeightedPlacedFeature(large_red_oak, 0.076f),  // 20% of 38%
                new WeightedPlacedFeature(small_red_oak, 0.133f),  // 35% of 38%
                new WeightedPlacedFeature(normal_red_oak, 0.133f), // 35% of 38%

                // Green Oak
                new WeightedPlacedFeature(mega_green_oak, 0.038f),   // 10% of 38%
                new WeightedPlacedFeature(large_green_oak, 0.076f),  // 20% of 38%
                new WeightedPlacedFeature(small_green_oak, 0.133f),  // 35% of 38%
                new WeightedPlacedFeature(normal_green_oak, 0.133f), // 35% of 38%

                // Black Oak
                new WeightedPlacedFeature(mega_black_oak, 0.019f),   // 10% of 19%
                new WeightedPlacedFeature(large_black_oak, 0.038f),  // 20% of 19%
                new WeightedPlacedFeature(small_black_oak, 0.0665f), // 35% of 19%
                new WeightedPlacedFeature(normal_black_oak, 0.0665f), // 35% of 19%

                // Silver Spruce
                new WeightedPlacedFeature(silver_spruce, 0.025f), // 2.5%

                // Holly Tree
                new WeightedPlacedFeature(holly, 0.0125f),       // 50% of 2.5%
                new WeightedPlacedFeature(holly_with_bees, 0.0125f)     // 50% of 2.5%
        ),noting));
    }
    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "biome/" +name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext< ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature,
                                                                                          FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
