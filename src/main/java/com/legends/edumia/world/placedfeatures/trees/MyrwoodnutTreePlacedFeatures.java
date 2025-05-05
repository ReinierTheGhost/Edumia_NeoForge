package com.legends.edumia.world.placedfeatures.trees;

import com.legends.edumia.Edumia;
import com.legends.edumia.core.blocksets.WoodBlockSets;
import com.legends.edumia.world.congiguredfeatures.trees.KapokTreeConfiguredFeatures;
import com.legends.edumia.world.congiguredfeatures.trees.MirwoodNutTreeConfiguredFeatures;
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

public class MyrwoodnutTreePlacedFeatures {

    public static final ResourceKey<PlacedFeature> MIRWOODNUT_1 = registerKey("mirwoodnut_1");
    public static final ResourceKey<PlacedFeature> MIRWOODNUT_2 = registerKey("mirwoodnut_2");
    public static final ResourceKey<PlacedFeature> MIRWOODNUT_3 = registerKey("mirwoodnut_3");
    public static final ResourceKey<PlacedFeature> MIRWOODNUT_4 = registerKey("mirwoodnut_4");
    public static final ResourceKey<PlacedFeature> MIRWOODNUT_5 = registerKey("mirwoodnut_5");
    public static final ResourceKey<PlacedFeature> MIRWOODNUT_6 = registerKey("mirwoodnut_6");
    public static final ResourceKey<PlacedFeature> MIRWOODNUT_7 = registerKey("mirwoodnut_7");
    public static final ResourceKey<PlacedFeature> MIRWOODNUT_8 = registerKey("mirwoodnut_8");
    public static final ResourceKey<PlacedFeature> MIRWOODNUT_9 = registerKey("mirwoodnut_9");
    public static final ResourceKey<PlacedFeature> MIRWOODNUT_10 = registerKey("mirwoodnut_10");

    public static void boostrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, MIRWOODNUT_1, configuredFeatureRegistryEntryLookup.getOrThrow(MirwoodNutTreeConfiguredFeatures.MIRWOODNUT_1),
                VegetationPlacements.treePlacement(ModPlacedFeatures.rareTree,
                        WoodBlockSets.MIRWOODNUT.sapling().get()));

        register(context, MIRWOODNUT_2, configuredFeatureRegistryEntryLookup.getOrThrow(MirwoodNutTreeConfiguredFeatures.MIRWOODNUT_2),
                VegetationPlacements.treePlacement(ModPlacedFeatures.rareTree,
                        WoodBlockSets.MIRWOODNUT.sapling().get()));

        register(context, MIRWOODNUT_3, configuredFeatureRegistryEntryLookup.getOrThrow(MirwoodNutTreeConfiguredFeatures.MIRWOODNUT_3),
                VegetationPlacements.treePlacement(ModPlacedFeatures.rareTree,
                        WoodBlockSets.MIRWOODNUT.sapling().get()));

        register(context, MIRWOODNUT_4, configuredFeatureRegistryEntryLookup.getOrThrow(MirwoodNutTreeConfiguredFeatures.MIRWOODNUT_4),
                VegetationPlacements.treePlacement(ModPlacedFeatures.rareTree,
                        WoodBlockSets.MIRWOODNUT.sapling().get()));

        register(context, MIRWOODNUT_5, configuredFeatureRegistryEntryLookup.getOrThrow(MirwoodNutTreeConfiguredFeatures.MIRWOODNUT_5),
                VegetationPlacements.treePlacement(ModPlacedFeatures.rareTree,
                        WoodBlockSets.MIRWOODNUT.sapling().get()));

        register(context, MIRWOODNUT_6, configuredFeatureRegistryEntryLookup.getOrThrow(MirwoodNutTreeConfiguredFeatures.MIRWOODNUT_6),
                VegetationPlacements.treePlacement(ModPlacedFeatures.rareTree,
                        WoodBlockSets.MIRWOODNUT.sapling().get()));

        register(context, MIRWOODNUT_7, configuredFeatureRegistryEntryLookup.getOrThrow(MirwoodNutTreeConfiguredFeatures.MIRWOODNUT_7),
                VegetationPlacements.treePlacement(ModPlacedFeatures.rareTree,
                        WoodBlockSets.MIRWOODNUT.sapling().get()));

        register(context, MIRWOODNUT_8, configuredFeatureRegistryEntryLookup.getOrThrow(MirwoodNutTreeConfiguredFeatures.MIRWOODNUT_8),
                VegetationPlacements.treePlacement(ModPlacedFeatures.rareTree,
                        WoodBlockSets.MIRWOODNUT.sapling().get()));

        register(context, MIRWOODNUT_9, configuredFeatureRegistryEntryLookup.getOrThrow(MirwoodNutTreeConfiguredFeatures.MIRWOODNUT_9),
                VegetationPlacements.treePlacement(ModPlacedFeatures.rareTree,
                        WoodBlockSets.MIRWOODNUT.sapling().get()));

        register(context, MIRWOODNUT_10, configuredFeatureRegistryEntryLookup.getOrThrow(MirwoodNutTreeConfiguredFeatures.MIRWOODNUT_10),
                VegetationPlacements.treePlacement(ModPlacedFeatures.rareTree,
                        WoodBlockSets.MIRWOODNUT.sapling().get()));


    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "tree/tropical/mirwoodnut_tree/" + name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
