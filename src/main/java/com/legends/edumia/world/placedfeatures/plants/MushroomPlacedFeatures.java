package com.legends.edumia.world.placedfeatures.plants;

import com.legends.edumia.Edumia;
import com.legends.edumia.world.congiguredfeatures.plants.FlowerConfiguredFeatures;
import com.legends.edumia.world.congiguredfeatures.plants.MushroomConfiguredFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class MushroomPlacedFeatures {
    public static final ResourceKey<PlacedFeature> PARASOL_MUSHROOM_1_KEY = registerKey("parasol_mushroom_01");
    public static final ResourceKey<PlacedFeature> PARASOL_MUSHROOM_2_KEY = registerKey("parasol_mushroom_02");
    public static final ResourceKey<PlacedFeature> PARASOL_MUSHROOM_TALL_KEY = registerKey("parasol_mushroom_tall");

    public static final ResourceKey<PlacedFeature> PATCH_PARASOL_MUSHROOM_1_KEY = registerKey("patch_parasol_mushroom_01");
    public static final ResourceKey<PlacedFeature> PATCH_PARASOL_MUSHROOM_2_KEY = registerKey("patch_parasol_mushroom_02");
    public static final ResourceKey<PlacedFeature> PATCH_PARASOL_MUSHROOM_TALL_KEY = registerKey("patch_parasol_mushroom_tall");

    public static void boostrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.lookup(Registries.CONFIGURED_FEATURE);
        register(context, PARASOL_MUSHROOM_1_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(MushroomConfiguredFeatures.PARASOL_MUSHROOM_1_KEY),
                List.of(CountPlacement.of(3),
                        InSquarePlacement.spread(), HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                        BiomeFilter.biome()));

        register(context, PARASOL_MUSHROOM_2_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(MushroomConfiguredFeatures.PARASOL_MUSHROOM_2_KEY),
                List.of(CountPlacement.of(3),
                        InSquarePlacement.spread(), HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                        BiomeFilter.biome()));

        register(context, PARASOL_MUSHROOM_TALL_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(MushroomConfiguredFeatures.PARASOL_MUSHROOM_TALL_KEY),
                List.of(CountPlacement.of(3),
                        InSquarePlacement.spread(), HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                        BiomeFilter.biome()));
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "mushrooms/" + name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}

