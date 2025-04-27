package com.legends.edumia.world.placedfeatures;

import com.legends.edumia.Edumia;
import com.legends.edumia.world.congiguredfeatures.ModConfiguredFeatures;
import com.legends.edumia.world.placedfeatures.biomes.BiomePlacedFeatures;
import com.legends.edumia.world.placedfeatures.biomes.FairyBiomePlacedFeatures;
import com.legends.edumia.world.placedfeatures.biomes.OgreBiomePlacedFeatures;
import com.legends.edumia.world.placedfeatures.biomes.OrcDesertPlacedFeatures;
import com.legends.edumia.world.placedfeatures.boulders.BoulderPlacedFeatures;
import com.legends.edumia.world.placedfeatures.crystrals.CrystalPlacedFeatures;
import com.legends.edumia.world.placedfeatures.ocean.ReefPlacedFeatures;
import com.legends.edumia.world.placedfeatures.plants.ReedsPlacedFeatures;
import com.legends.edumia.world.placedfeatures.trees.*;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> NOTING = registerKey("noting");
    public static final ResourceKey<PlacedFeature> WATER_DELTA = registerKey("water_delta");
    public static final ResourceKey<PlacedFeature> ABUNDANT_WATER_DELTA = registerKey("abundant_water_delta");

    static PlacementModifier overflowing = PlacementUtils.countExtra(5, 0.5f, 1);
    static PlacementModifier abundant = PlacementUtils.countExtra(4, 0.5f, 1);
    static PlacementModifier common = PlacementUtils.countExtra(2, 0.5f, 1);
    static PlacementModifier uncommon = PlacementUtils.countExtra(1, 0.2f, 1);
    static PlacementModifier sparse = PlacementUtils.countExtra(0, 0.5f, 1);
    static PlacementModifier occasional = PlacementUtils.countExtra(0, 0.25f, 1);
    static PlacementModifier scarce = PlacementUtils.countExtra(0, 0.2f, 1);
    static PlacementModifier rare = PlacementUtils.countExtra(0, 0.1f, 1);
    static PlacementModifier veryRare = PlacementUtils.countExtra(0, 0.05f, 1);
    static PlacementModifier wildBushRarity = PlacementUtils.countExtra(0, 0.01f, 1);

    public static void boostrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureRegistryEntryLookup = context.lookup(Registries.CONFIGURED_FEATURE);

        Holder.Reference<ConfiguredFeature<?, ?>> waterDelta = configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.WATER_DELTA);

        register(context, NOTING, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.NOTING),
                List.of());

        register(context, WATER_DELTA, waterDelta, List.of(common, InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,  BiomeFilter.biome()));

        register(context, ABUNDANT_WATER_DELTA, waterDelta, List.of(abundant, InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,  BiomeFilter.biome()));

        TreePlacedFeatures.boostrap(context);
        BeachPlacedFeatures.boostrap(context);
        ReefPlacedFeatures.boostrap(context);
        FlowerPlacedFeatures.boostrap(context);
        TropicalTreePlacedFeatures.boostrap(context);
        TemperateTreePlacedFeatures.boostrap(context);
        BorealTreePlacedFeatures.boostrap(context);
        BoulderPlacedFeatures.bootstrap(context);
        CrystalPlacedFeatures.bootstrap(context);
        ReedsPlacedFeatures.boostrap(context);
        OakTreePlacedFeatures.boostrap(context);
        BiomePlacedFeatures.boostrap(context);
        OgreBiomePlacedFeatures.boostrap(context);
        FairyBiomePlacedFeatures.boostrap(context);
        OrcDesertPlacedFeatures.boostrap(context);
        DeadTreePlacedFeatures.boostrap(context);
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
