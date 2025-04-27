package com.legends.edumia.world.placedfeatures;

import com.legends.edumia.Edumia;
import com.legends.edumia.world.congiguredfeatures.FlowerConfiguredFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class FlowerPlacedFeatures {
    public static final ResourceKey<PlacedFeature> ASPHODEL_FLOWER_KEY = registerKey("asphodel");
    public static final ResourceKey<PlacedFeature> BLUE_DELPHINIUM_FLOWER_KEY = registerKey("blue_delphinium");
    public static final ResourceKey<PlacedFeature> BLUEBELL_FLOWER_KEY = registerKey("bluebell");
    public static final ResourceKey<PlacedFeature> CALLA_LILY_FLOWER_KEY = registerKey("calla_lily");
    public static final ResourceKey<PlacedFeature> CELSEMIUM_FLOWER_KEY = registerKey("celsemium");
    public static final ResourceKey<PlacedFeature> CHRYS_BLUE_FLOWER_KEY = registerKey("chrys_blue");
    public static final ResourceKey<PlacedFeature> CHRYS_ORANGE_FLOWER_KEY = registerKey("chrys_orange");
    public static final ResourceKey<PlacedFeature> CHRYS_PINK_FLOWER_KEY = registerKey("chrys_pink");
    public static final ResourceKey<PlacedFeature> CHRYS_WHITE_FLOWER_KEY = registerKey("chrys_white");
    public static final ResourceKey<PlacedFeature> CHRYS_YELLOW_FLOWER_KEY = registerKey("chrys_yellow");
    public static final ResourceKey<PlacedFeature> CROCUS_FLOWER_KEY = registerKey("crocus");
    public static final ResourceKey<PlacedFeature> DAISY_FLOWER_KEY = registerKey("daisy");
    public static final ResourceKey<PlacedFeature> DELPHINIUM_FLOWER_KEY = registerKey("delphinium");
    public static final ResourceKey<PlacedFeature> FLAX_FLOWER_KEY = registerKey("flax");
    public static final ResourceKey<PlacedFeature> PATCH_FLAX_FLOWER_KEY = registerKey("patch_flax_patch");
    public static final ResourceKey<PlacedFeature> FOXGLOVE_ORANGE_FLOWER_KEY = registerKey("foxglove_orange");
    public static final ResourceKey<PlacedFeature> FOXGLOVE_PINK_FLOWER_KEY = registerKey("foxglove_pink");
    public static final ResourceKey<PlacedFeature> FOXGLOVE_RED_FLOWER_KEY = registerKey("foxglove_red");
    public static final ResourceKey<PlacedFeature> FOXGLOVE_WHITE_FLOWER_KEY = registerKey("foxglove_white");
    public static final ResourceKey<PlacedFeature> GERBERA_RED_FLOWER_KEY = registerKey("gerbera_red");
    public static final ResourceKey<PlacedFeature> GERBERA_YELLOW_FLOWER_KEY = registerKey("gerbera_yellow");
    public static final ResourceKey<PlacedFeature> GENSAI_ORCHID_FLOWER_KEY = registerKey("gensai_orchid");
    public static final ResourceKey<PlacedFeature> HEATHER_BUSH_FLOWER_KEY = registerKey("heather_bush");
    public static final ResourceKey<PlacedFeature> LAVENDER_FLOWER_KEY = registerKey("lavender");
    public static final ResourceKey<PlacedFeature> MARIGOLD_FLOWER_KEY = registerKey("marigold");
    public static final ResourceKey<PlacedFeature> PINK_ANEMONE_FLOWER_KEY = registerKey("pink_anemone");
    public static final ResourceKey<PlacedFeature> SIMBELMYNE_FLOWER_KEY = registerKey("simbelmyne");
    public static final ResourceKey<PlacedFeature> TUBEROSE_FLOWER_KEY = registerKey("tuberose");
    public static final ResourceKey<PlacedFeature> YELLOW_IRIS_FLOWER_KEY = registerKey("yellow_iris");
    public static final ResourceKey<PlacedFeature> DESERT_FLAME_FLOWER_KEY = registerKey("desert_flame");
    public static final ResourceKey<PlacedFeature> HIBISCUS_FLOWER_KEY = registerKey("hibiscus");

    public static void boostrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.lookup(Registries.CONFIGURED_FEATURE);
        register(context, ASPHODEL_FLOWER_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(FlowerConfiguredFeatures.ASPHODEL_FLOWER_KEY),
                List.of(CountPlacement.of(3),
                        InSquarePlacement.spread(), HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                        BiomeFilter.biome()));

        register(context, BLUE_DELPHINIUM_FLOWER_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(FlowerConfiguredFeatures.BLUE_DELPHINIUM_FLOWER_KEY),
                List.of(CountPlacement.of(3),
                        InSquarePlacement.spread(), HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                        BiomeFilter.biome()));

        register(context, BLUEBELL_FLOWER_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(FlowerConfiguredFeatures.BLUEBELL_FLOWER_KEY),
                List.of(CountPlacement.of(3),
                        InSquarePlacement.spread(), HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                        BiomeFilter.biome()));

        register(context, CALLA_LILY_FLOWER_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(FlowerConfiguredFeatures.CALLA_LILY_FLOWER_KEY),
                List.of(CountPlacement.of(3),
                        InSquarePlacement.spread(), HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                        BiomeFilter.biome()));

        register(context, CELSEMIUM_FLOWER_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(FlowerConfiguredFeatures.CELSEMIUM_FLOWER_KEY),
                List.of(CountPlacement.of(3),
                        InSquarePlacement.spread(), HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                        BiomeFilter.biome()));

        register(context, CHRYS_BLUE_FLOWER_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(FlowerConfiguredFeatures.CHRYS_BLUE_FLOWER_KEY),
                List.of(CountPlacement.of(3),
                        InSquarePlacement.spread(), HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                        BiomeFilter.biome()));

        register(context, CHRYS_ORANGE_FLOWER_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(FlowerConfiguredFeatures.CELSEMIUM_FLOWER_KEY),
                List.of(CountPlacement.of(3),
                        InSquarePlacement.spread(), HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                        BiomeFilter.biome()));

        register(context, CHRYS_PINK_FLOWER_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(FlowerConfiguredFeatures.CELSEMIUM_FLOWER_KEY),
                List.of(CountPlacement.of(3),
                        InSquarePlacement.spread(), HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                        BiomeFilter.biome()));

        register(context, CHRYS_WHITE_FLOWER_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(FlowerConfiguredFeatures.CELSEMIUM_FLOWER_KEY),
                List.of(CountPlacement.of(3),
                        InSquarePlacement.spread(), HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                        BiomeFilter.biome()));

        register(context, CHRYS_YELLOW_FLOWER_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(FlowerConfiguredFeatures.CELSEMIUM_FLOWER_KEY),
                List.of(CountPlacement.of(3),
                        InSquarePlacement.spread(), HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                        BiomeFilter.biome()));

        register(context, CROCUS_FLOWER_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(FlowerConfiguredFeatures.CROCUS_FLOWER_KEY),
                List.of(CountPlacement.of(3),
                        InSquarePlacement.spread(), HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                        BiomeFilter.biome()));

        register(context, DAISY_FLOWER_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(FlowerConfiguredFeatures.DAISY_FLOWER_KEY),
                List.of(CountPlacement.of(3),
                        InSquarePlacement.spread(), HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                        BiomeFilter.biome()));

        register(context, DELPHINIUM_FLOWER_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(FlowerConfiguredFeatures.DELPHINIUM_FLOWER_KEY),
                List.of(CountPlacement.of(3),
                        InSquarePlacement.spread(), HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                        BiomeFilter.biome()));

        register(context, FLAX_FLOWER_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(FlowerConfiguredFeatures.FLAX_FLOWER_KEY),
                List.of(CountPlacement.of(3),
                        InSquarePlacement.spread(), HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                        BiomeFilter.biome()));

        register(context, PATCH_FLAX_FLOWER_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(FlowerConfiguredFeatures.PATCH_FLAX_FLOWER_KEY),
                List.of(ModPlacedFeatures.wildBushRarity,
                        InSquarePlacement.spread(), HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                        BiomeFilter.biome()));

        register(context, FOXGLOVE_ORANGE_FLOWER_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(FlowerConfiguredFeatures.FOXGLOVE_ORANGE_FLOWER_KEY),
                List.of(CountPlacement.of(3),
                        InSquarePlacement.spread(), HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                        BiomeFilter.biome()));

        register(context, FOXGLOVE_PINK_FLOWER_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(FlowerConfiguredFeatures.FOXGLOVE_PINK_FLOWER_KEY),
                List.of(CountPlacement.of(3),
                        InSquarePlacement.spread(), HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                        BiomeFilter.biome()));

        register(context, FOXGLOVE_RED_FLOWER_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(FlowerConfiguredFeatures.FOXGLOVE_RED_FLOWER_KEY),
                List.of(CountPlacement.of(3),
                        InSquarePlacement.spread(), HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                        BiomeFilter.biome()));

        register(context, FOXGLOVE_WHITE_FLOWER_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(FlowerConfiguredFeatures.FOXGLOVE_WHITE_FLOWER_KEY),
                List.of(CountPlacement.of(3),
                        InSquarePlacement.spread(), HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                        BiomeFilter.biome()));

        register(context, GERBERA_RED_FLOWER_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(FlowerConfiguredFeatures.GERBERA_RED_FLOWER_KEY),
                List.of(CountPlacement.of(3),
                        InSquarePlacement.spread(), HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                        BiomeFilter.biome()));

        register(context, GERBERA_YELLOW_FLOWER_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(FlowerConfiguredFeatures.GERBERA_YELLOW_FLOWER_KEY),
                List.of(CountPlacement.of(3),
                        InSquarePlacement.spread(), HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                        BiomeFilter.biome()));

        register(context, GENSAI_ORCHID_FLOWER_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(FlowerConfiguredFeatures.GENSAI_ORCHID_FLOWER_KEY),
                List.of(CountPlacement.of(3),
                        InSquarePlacement.spread(), HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                        BiomeFilter.biome()));

        register(context, HEATHER_BUSH_FLOWER_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(FlowerConfiguredFeatures.HEATHER_BUSH_FLOWER_KEY),
                List.of(CountPlacement.of(3),
                        InSquarePlacement.spread(), HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                        BiomeFilter.biome()));

        register(context, LAVENDER_FLOWER_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(FlowerConfiguredFeatures.LAVENDER_FLOWER_KEY),
                List.of(CountPlacement.of(3),
                        InSquarePlacement.spread(), HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                        BiomeFilter.biome()));

        register(context, MARIGOLD_FLOWER_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(FlowerConfiguredFeatures.MARIGOLD_FLOWER_KEY),
                List.of(CountPlacement.of(3),
                        InSquarePlacement.spread(), HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                        BiomeFilter.biome()));

        register(context, PINK_ANEMONE_FLOWER_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(FlowerConfiguredFeatures.PINK_ANEMONE_FLOWER_KEY),
                List.of(CountPlacement.of(3),
                        InSquarePlacement.spread(), HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                        BiomeFilter.biome()));

        register(context, SIMBELMYNE_FLOWER_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(FlowerConfiguredFeatures.SIMBELMYNE_FLOWER_KEY),
                List.of(CountPlacement.of(3),
                        InSquarePlacement.spread(), HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                        BiomeFilter.biome()));

        register(context, TUBEROSE_FLOWER_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(FlowerConfiguredFeatures.TUBEROSE_FLOWER_KEY),
                List.of(CountPlacement.of(3),
                        InSquarePlacement.spread(), HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                        BiomeFilter.biome()));

        register(context, YELLOW_IRIS_FLOWER_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(FlowerConfiguredFeatures.YELLOW_IRIS_FLOWER_KEY),
                List.of(CountPlacement.of(3),
                        InSquarePlacement.spread(), HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                        BiomeFilter.biome()));

        register(context, DESERT_FLAME_FLOWER_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(FlowerConfiguredFeatures.DESERT_FLAME_FLOWER_KEY),
                List.of(CountPlacement.of(3),
                        InSquarePlacement.spread(), HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                        BiomeFilter.biome()));

        register(context, HIBISCUS_FLOWER_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(FlowerConfiguredFeatures.HIBISCUS_FLOWER_KEY),
                List.of(CountPlacement.of(3),
                        InSquarePlacement.spread(), HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                        BiomeFilter.biome()));


    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "flowers/" + name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
