package com.legends.edumia.world.placedfeatures.trees;

import com.legends.edumia.Edumia;
import com.legends.edumia.core.blocksets.WoodBlockSets;
import com.legends.edumia.world.congiguredfeatures.trees.KapokTreeConfiguredFeatures;
import com.legends.edumia.world.congiguredfeatures.trees.SubTropicalTreeConfiguredFeatures;
import com.legends.edumia.world.placedfeatures.ModPlacedFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class KapokTreePlacedFeatures {
    public static final ResourceKey<PlacedFeature> KAPOK_1 = registerKey("kapok_1");
    public static final ResourceKey<PlacedFeature> KAPOK_2 = registerKey("kapok_2");
    public static final ResourceKey<PlacedFeature> KAPOK_3 = registerKey("kapok_3");
    public static final ResourceKey<PlacedFeature> KAPOK_4 = registerKey("kapok_4");
    public static final ResourceKey<PlacedFeature> KAPOK_5 = registerKey("kapok_5");
    public static final ResourceKey<PlacedFeature> KAPOK_6 = registerKey("kapok_6");
    public static final ResourceKey<PlacedFeature> KAPOK_7 = registerKey("kapok_7");
    public static final ResourceKey<PlacedFeature> KAPOK_8 = registerKey("kapok_8");
    public static final ResourceKey<PlacedFeature> KAPOK_9 = registerKey("kapok_9");
    public static final ResourceKey<PlacedFeature> KAPOK_10 = registerKey("kapok_10");


    public static void boostrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, KAPOK_1, configuredFeatureRegistryEntryLookup.getOrThrow(KapokTreeConfiguredFeatures.KAPOK_1),
                VegetationPlacements.treePlacement(ModPlacedFeatures.superRareTree,
                        WoodBlockSets.KAPOK.sapling().get()));

        register(context, KAPOK_2, configuredFeatureRegistryEntryLookup.getOrThrow(KapokTreeConfiguredFeatures.KAPOK_2),
                VegetationPlacements.treePlacement(ModPlacedFeatures.superRareTree,
                        WoodBlockSets.KAPOK.sapling().get()));

        register(context, KAPOK_3, configuredFeatureRegistryEntryLookup.getOrThrow(KapokTreeConfiguredFeatures.KAPOK_3),
                VegetationPlacements.treePlacement(ModPlacedFeatures.superRareTree,
                        WoodBlockSets.KAPOK.sapling().get()));

        register(context, KAPOK_4, configuredFeatureRegistryEntryLookup.getOrThrow(KapokTreeConfiguredFeatures.KAPOK_4),
                VegetationPlacements.treePlacement(ModPlacedFeatures.superRareTree,
                        WoodBlockSets.KAPOK.sapling().get()));

        register(context, KAPOK_5, configuredFeatureRegistryEntryLookup.getOrThrow(KapokTreeConfiguredFeatures.KAPOK_5),
                VegetationPlacements.treePlacement(ModPlacedFeatures.superRareTree,
                        WoodBlockSets.KAPOK.sapling().get()));

        register(context, KAPOK_6, configuredFeatureRegistryEntryLookup.getOrThrow(KapokTreeConfiguredFeatures.KAPOK_6),
                VegetationPlacements.treePlacement(ModPlacedFeatures.superRareTree,
                        WoodBlockSets.KAPOK.sapling().get()));

        register(context, KAPOK_7, configuredFeatureRegistryEntryLookup.getOrThrow(KapokTreeConfiguredFeatures.KAPOK_7),
                VegetationPlacements.treePlacement(ModPlacedFeatures.superRareTree,
                        WoodBlockSets.KAPOK.sapling().get()));

        register(context, KAPOK_8, configuredFeatureRegistryEntryLookup.getOrThrow(KapokTreeConfiguredFeatures.KAPOK_8),
                VegetationPlacements.treePlacement(ModPlacedFeatures.superRareTree,
                        WoodBlockSets.KAPOK.sapling().get()));

        register(context, KAPOK_9, configuredFeatureRegistryEntryLookup.getOrThrow(KapokTreeConfiguredFeatures.KAPOK_9),
                VegetationPlacements.treePlacement(ModPlacedFeatures.superRareTree,
                        WoodBlockSets.KAPOK.sapling().get()));

        register(context, KAPOK_10, configuredFeatureRegistryEntryLookup.getOrThrow(KapokTreeConfiguredFeatures.KAPOK_10),
                VegetationPlacements.treePlacement(ModPlacedFeatures.superRareTree,
                        WoodBlockSets.KAPOK.sapling().get()));


    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "tree/tropical/kapok/" + name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
