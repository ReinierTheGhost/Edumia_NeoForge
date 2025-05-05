package com.legends.edumia.world.placedfeatures.trees;

import com.legends.edumia.Edumia;
import com.legends.edumia.core.blocksets.WoodBlockSets;
import com.legends.edumia.world.congiguredfeatures.trees.KapokTreeConfiguredFeatures;
import com.legends.edumia.world.congiguredfeatures.trees.MahoganyConfiguredFeatures;
import com.legends.edumia.world.placedfeatures.ModPlacedFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class MahoganyTreePlacedFeatures {
    public static final ResourceKey<PlacedFeature> MAHOGANY_1 = registerKey("mahogany_1");
    public static final ResourceKey<PlacedFeature> MAHOGANY_2 = registerKey("mahogany_2");
    public static final ResourceKey<PlacedFeature> MAHOGANY_3 = registerKey("mahogany_3");
    public static final ResourceKey<PlacedFeature> MAHOGANY_4 = registerKey("mahogany_4");


    public static void boostrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, MAHOGANY_1, configuredFeatureRegistryEntryLookup.getOrThrow(MahoganyConfiguredFeatures.MAHOGANY_1),
                VegetationPlacements.treePlacement(ModPlacedFeatures.uncommonTree,
                        WoodBlockSets.MAHOGANY.sapling().get()));

        register(context, MAHOGANY_2, configuredFeatureRegistryEntryLookup.getOrThrow(MahoganyConfiguredFeatures.MAHOGANY_2),
                VegetationPlacements.treePlacement(ModPlacedFeatures.uncommonTree,
                        WoodBlockSets.MAHOGANY.sapling().get()));

        register(context, MAHOGANY_3, configuredFeatureRegistryEntryLookup.getOrThrow(MahoganyConfiguredFeatures.MAHOGANY_3),
                VegetationPlacements.treePlacement(ModPlacedFeatures.uncommonTree,
                        WoodBlockSets.MAHOGANY.sapling().get()));

        register(context, MAHOGANY_4, configuredFeatureRegistryEntryLookup.getOrThrow(MahoganyConfiguredFeatures.MAHOGANY_4),
                VegetationPlacements.treePlacement(ModPlacedFeatures.uncommonTree,
                        WoodBlockSets.MAHOGANY.sapling().get()));
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "tree/tropical/mahogany/" + name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
