package com.legends.edumia.world.placedfeatures.boulders;

import com.legends.edumia.Edumia;
import com.legends.edumia.world.congiguredfeatures.boulders.BoulderConfiguredFeatures;
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

public class BoulderPlacedFeatures {
    public static final ResourceKey<PlacedFeature> ANDESITE_BOULDER = registerKey("andesite_boulder");
    public static final ResourceKey<PlacedFeature> BIG_ANDESITE_BOULDER = registerKey("big_andesite_boulder");
    public static final ResourceKey<PlacedFeature> CALCITE_BOULDER = registerKey("calcite_boulder");
    public static final ResourceKey<PlacedFeature> BIG_CALCITE_BOULDER = registerKey("big_calcite_boulder");
    public static final ResourceKey<PlacedFeature> DIORITE_BOULDER = registerKey("diorite_boulder");
    public static final ResourceKey<PlacedFeature> BIG_DIORITE_BOULDER = registerKey("big_diorite_boulder");
    public static final ResourceKey<PlacedFeature> GRANITE_BOULDER = registerKey("granite_boulder");
    public static final ResourceKey<PlacedFeature> BIG_GRANITE_BOULDER = registerKey("big_granite_boulder");
    public static final ResourceKey<PlacedFeature> LIMESTONE_BOULDER = registerKey("limestone_boulder");
    public static final ResourceKey<PlacedFeature> BIG_LIMESTONE_BOULDER = registerKey("big_limestone_boulder");
    public static final ResourceKey<PlacedFeature> SANDSTONE_BOULDER = registerKey("sandstone_boulder");
    public static final ResourceKey<PlacedFeature> BIG_SANDSTONE_BOULDER = registerKey("big_sandstone_boulder");
    public static final ResourceKey<PlacedFeature> STONE_BOULDER = registerKey("stone_boulder");
    public static final ResourceKey<PlacedFeature> BIG_STONE_BOULDER = registerKey("big_stone_boulder");
    public static final ResourceKey<PlacedFeature> MOSSY_BOULDER = registerKey("mossy_boulder");
    public static final ResourceKey<PlacedFeature> BIG_MOSSY_BOULDER = registerKey("big_mossy_boulder");

    public static void bootstrap(BootstrapContext<PlacedFeature> featureRegisterable) {
        HolderGetter<ConfiguredFeature<?, ?>> registryEntryLookup = featureRegisterable.lookup(Registries.CONFIGURED_FEATURE);
        Holder.Reference<ConfiguredFeature<?, ?>> andesite = registryEntryLookup.getOrThrow(BoulderConfiguredFeatures.ANDESITE_BOULDER);
        Holder.Reference<ConfiguredFeature<?, ?>> big_andesite = registryEntryLookup.getOrThrow(BoulderConfiguredFeatures.BIG_ANDESITE_BOULDER);

        Holder.Reference<ConfiguredFeature<?, ?>> calcite = registryEntryLookup.getOrThrow(BoulderConfiguredFeatures.CALCITE_BOULDER);
        Holder.Reference<ConfiguredFeature<?, ?>> big_calcite = registryEntryLookup.getOrThrow(BoulderConfiguredFeatures.BIG_CALCITE_BOULDER);

        Holder.Reference<ConfiguredFeature<?, ?>> diorite = registryEntryLookup.getOrThrow(BoulderConfiguredFeatures.DIORITE_BOULDER);
        Holder.Reference<ConfiguredFeature<?, ?>> big_diorite = registryEntryLookup.getOrThrow(BoulderConfiguredFeatures.BIG_DIORITE_BOULDER);

        Holder.Reference<ConfiguredFeature<?, ?>> granite = registryEntryLookup.getOrThrow(BoulderConfiguredFeatures.GRANITE_BOULDER);
        Holder.Reference<ConfiguredFeature<?, ?>> big_granite = registryEntryLookup.getOrThrow(BoulderConfiguredFeatures.BIG_GRANITE_BOULDER);

        Holder.Reference<ConfiguredFeature<?, ?>> limestone = registryEntryLookup.getOrThrow(BoulderConfiguredFeatures.LIMESTONE_BOULDER);
        Holder.Reference<ConfiguredFeature<?, ?>> big_limestone = registryEntryLookup.getOrThrow(BoulderConfiguredFeatures.BIG_LIMESTONE_BOULDER);

        Holder.Reference<ConfiguredFeature<?, ?>> sandStone = registryEntryLookup.getOrThrow(BoulderConfiguredFeatures.SANDSTONE_BOULDER);
        Holder.Reference<ConfiguredFeature<?, ?>> big_sandStone = registryEntryLookup.getOrThrow(BoulderConfiguredFeatures.BIG_SANDSTONE_BOULDER);

        Holder.Reference<ConfiguredFeature<?, ?>> stone = registryEntryLookup.getOrThrow(BoulderConfiguredFeatures.STONE_BOULDER);
        Holder.Reference<ConfiguredFeature<?, ?>> big_stone = registryEntryLookup.getOrThrow(BoulderConfiguredFeatures.BIG_STONE_BOULDER);

        Holder.Reference<ConfiguredFeature<?, ?>> mossy = registryEntryLookup.getOrThrow(BoulderConfiguredFeatures.MOSSY_BOULDER);
        Holder.Reference<ConfiguredFeature<?, ?>> big_mossy = registryEntryLookup.getOrThrow(BoulderConfiguredFeatures.BIG_MOSSY_BOULDER);

        PlacementModifier common = PlacementUtils.countExtra(0, 0.5f, 1);
        PlacementModifier placeChance = PlacementUtils.countExtra(0, 0.25f, 1);

        PlacementUtils.register(featureRegisterable, ANDESITE_BOULDER, andesite, placeChance,
                InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
        PlacementUtils.register(featureRegisterable, CALCITE_BOULDER, calcite, placeChance,
                InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
        PlacementUtils.register(featureRegisterable, DIORITE_BOULDER, diorite, placeChance,
                InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
        PlacementUtils.register(featureRegisterable, GRANITE_BOULDER, granite, placeChance,
                InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
        PlacementUtils.register(featureRegisterable, LIMESTONE_BOULDER, limestone, placeChance,
                InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
        PlacementUtils.register(featureRegisterable, STONE_BOULDER, stone, placeChance,
                InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
        PlacementUtils.register(featureRegisterable, SANDSTONE_BOULDER, sandStone, placeChance,
                InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
        PlacementUtils.register(featureRegisterable, MOSSY_BOULDER, mossy, placeChance,
                InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

        PlacementUtils.register(featureRegisterable, BIG_ANDESITE_BOULDER, big_andesite,
                PlacementUtils.countExtra(0, 1.0F/ 16, 4),
                InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome());

        PlacementUtils.register(featureRegisterable, BIG_DIORITE_BOULDER, big_diorite,
                PlacementUtils.countExtra(0, 1.0F/ 16, 4),
                InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome());
        PlacementUtils.register(featureRegisterable, BIG_CALCITE_BOULDER, big_calcite,
                PlacementUtils.countExtra(0, 1.0F/ 16, 4),
                InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome());
        PlacementUtils.register(featureRegisterable, BIG_GRANITE_BOULDER, big_granite,
                PlacementUtils.countExtra(0, 1.0F/ 16, 4),
                InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome());
        PlacementUtils.register(featureRegisterable, BIG_LIMESTONE_BOULDER, big_limestone,
                PlacementUtils.countExtra(0, 1.0F/ 16, 4),
                InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome());

        PlacementUtils.register(featureRegisterable, BIG_SANDSTONE_BOULDER, big_sandStone,
                PlacementUtils.countExtra(0, 1.0F/ 16, 4),
                InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome());

        PlacementUtils.register(featureRegisterable, BIG_STONE_BOULDER, big_stone,
                PlacementUtils.countExtra(0, 1.0F/ 16, 4),
                InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome());

        PlacementUtils.register(featureRegisterable, BIG_MOSSY_BOULDER, big_mossy,
                PlacementUtils.countExtra(0, 1.0F/ 16, 4),
                InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome());
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "boulders/" + name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
