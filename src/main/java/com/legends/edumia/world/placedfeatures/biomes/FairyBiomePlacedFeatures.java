package com.legends.edumia.world.placedfeatures.biomes;

import com.legends.edumia.Edumia;
import com.legends.edumia.world.congiguredfeatures.biomes.FairyBiomesConfiguredFeatures;
import com.legends.edumia.world.placedfeatures.ModPlacedFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class FairyBiomePlacedFeatures {

    public static final ResourceKey<PlacedFeature> FAIRY_FOREST_TREES_LAYER_1 = registerKey("trees/layer_1");
    public static final ResourceKey<PlacedFeature> FAIRY_FOREST_TREES_LAYER_2 = registerKey("trees/layer_2");
    public static final ResourceKey<PlacedFeature> FAIRY_FOREST_TREES_LAYER_3 = registerKey("trees/layer_3");
    public static final ResourceKey<PlacedFeature> FAIRY_FOREST_TREES_LAYER_4 = registerKey("trees/layer_4");
    public static final ResourceKey<PlacedFeature> FAIRY_FOREST_TREES_LAYER_5 = registerKey("trees/layer_5");
    public static void boostrap(BootstrapContext<PlacedFeature> context) {
        var holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);

        var registryEntryLookup = context.lookup(Registries.PLACED_FEATURE);

        Holder.Reference<PlacedFeature> noting = registryEntryLookup.getOrThrow(ModPlacedFeatures.NOTING);

        register(context, FAIRY_FOREST_TREES_LAYER_1, holdergetter.getOrThrow(FairyBiomesConfiguredFeatures.FAIRY_FOREST_TREES_LAYER_4),
                List.of(CountPlacement.of(8), InSquarePlacement.spread(),
                        NoiseBasedCountPlacement.of(-1, 130.0d, 0.55d),
                        HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE),
                        SurfaceWaterDepthFilter.forMaxDepth(0),
                        BiomeFilter.biome()
                ));

        register(context, FAIRY_FOREST_TREES_LAYER_2, holdergetter.getOrThrow(FairyBiomesConfiguredFeatures.FAIRY_FOREST_TREES_LAYER_4),
                List.of(CountPlacement.of(10), InSquarePlacement.spread(),
                        NoiseBasedCountPlacement.of(1, 130.0d, 0.55d),
                        NoiseBasedCountPlacement.of(-1, 130.0d, 0.2d),
                        HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE),
                        SurfaceWaterDepthFilter.forMaxDepth(0),
                        BiomeFilter.biome()
                ));

        register(context, FAIRY_FOREST_TREES_LAYER_3, holdergetter.getOrThrow(FairyBiomesConfiguredFeatures.FAIRY_FOREST_TREES_LAYER_4),
                List.of(CountPlacement.of(12), InSquarePlacement.spread(),
                        NoiseBasedCountPlacement.of(1, 130.0d, 0.2d),
                        NoiseBasedCountPlacement.of(-1, 130.0d, -0.2d),
                        HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE),
                        SurfaceWaterDepthFilter.forMaxDepth(0),
                        BiomeFilter.biome()
                ));


        register(context, FAIRY_FOREST_TREES_LAYER_4, holdergetter.getOrThrow(FairyBiomesConfiguredFeatures.FAIRY_FOREST_TREES_LAYER_4),
                List.of(CountPlacement.of(12), InSquarePlacement.spread(),
                        NoiseBasedCountPlacement.of(1, 130.0d, -0.2d),
                        NoiseBasedCountPlacement.of(-1, 130.0d, -0.55d),
                        HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE),
                        SurfaceWaterDepthFilter.forMaxDepth(0),
                        BiomeFilter.biome()
                        ));

        register(context, FAIRY_FOREST_TREES_LAYER_5, holdergetter.getOrThrow(FairyBiomesConfiguredFeatures.FAIRY_FOREST_TREES_LAYER_4),
                List.of(CountPlacement.of(8), InSquarePlacement.spread(),
                        NoiseBasedCountPlacement.of(1, 130.0d, -0.55d),
                        HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE),
                        SurfaceWaterDepthFilter.forMaxDepth(0),
                        BiomeFilter.biome()
                ));

    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {

        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "biomes/fairy/" + name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
