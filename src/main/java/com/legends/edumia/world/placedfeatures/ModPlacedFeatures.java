package com.legends.edumia.world.placedfeatures;

import com.legends.edumia.Edumia;
import com.legends.edumia.core.blocksets.SandBlockSets;
import com.legends.edumia.world.congiguredfeatures.ModConfiguredFeatures;
import com.legends.edumia.world.congiguredfeatures.beach.BeachConfiguredFeatures;
import com.legends.edumia.world.placedfeatures.biomes.BiomePlacedFeatures;
import com.legends.edumia.world.placedfeatures.biomes.FairyBiomePlacedFeatures;
import com.legends.edumia.world.placedfeatures.biomes.OgreBiomePlacedFeatures;
import com.legends.edumia.world.placedfeatures.biomes.OrcDesertPlacedFeatures;
import com.legends.edumia.world.placedfeatures.boulders.BoulderPlacedFeatures;
import com.legends.edumia.world.placedfeatures.caves.CavePlacedFeatures;
import com.legends.edumia.world.placedfeatures.caves.JungleCavePlacedFeatures;
import com.legends.edumia.world.placedfeatures.crystrals.CrystalPlacedFeatures;
import com.legends.edumia.world.placedfeatures.ocean.ReefPlacedFeatures;
import com.legends.edumia.world.placedfeatures.ores.VanillaBlockOrePlacedFeatures;
import com.legends.edumia.world.placedfeatures.plants.FlowerPlacedFeatures;
import com.legends.edumia.world.placedfeatures.plants.MushroomPlacedFeatures;
import com.legends.edumia.world.placedfeatures.plants.ReedsPlacedFeatures;
import com.legends.edumia.world.placedfeatures.trees.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> NOTING = registerKey("noting");
    public static final ResourceKey<PlacedFeature> WATER_DELTA = registerKey("water_delta");
    public static final ResourceKey<PlacedFeature> ABUNDANT_WATER_DELTA = registerKey("abundant_water_delta");

    public static final ResourceKey<PlacedFeature> SNOW_LAYER_FIRST = registerKey("snow/layers/normal/first");
    public static final ResourceKey<PlacedFeature> SNOW_LAYER_SECOND = registerKey("snow/layers/normal/second");
    public static final ResourceKey<PlacedFeature> SNOW_LAYER_THIRD = registerKey("snow/layers/normal/third");

    public  static PlacementModifier overflowing = PlacementUtils.countExtra(5, 0.5f, 1);
    public static PlacementModifier abundant = PlacementUtils.countExtra(4, 0.5f, 1);
    public static PlacementModifier common = PlacementUtils.countExtra(2, 0.5f, 1);
    public static PlacementModifier uncommon = PlacementUtils.countExtra(1, 0.2f, 1);
    public static PlacementModifier sparse = PlacementUtils.countExtra(0, 0.5f, 1);
    public static PlacementModifier occasional = PlacementUtils.countExtra(0, 0.25f, 1);
    public static PlacementModifier scarce = PlacementUtils.countExtra(0, 0.2f, 1);
    public static PlacementModifier rare = PlacementUtils.countExtra(0, 0.1f, 1);
    public static PlacementModifier veryRare = PlacementUtils.countExtra(0, 0.05f, 1);
    public static PlacementModifier wildBushRarity = PlacementUtils.countExtra(0, 0.01f, 1);

    public static PlacementModifier abundantTree = PlacementUtils.countExtra(3, 0.5f, 1);
    public static PlacementModifier frequentTree = PlacementUtils.countExtra(1, 0.5f, 1);
    public static PlacementModifier commonTree = PlacementUtils.countExtra(1, 0.1f, 1);
    public static PlacementModifier uncommonTree = PlacementUtils.countExtra(0, 0.5f, 1);
    public static PlacementModifier scarceTree = PlacementUtils.countExtra(0, 0.25f, 1);
    public static PlacementModifier rareTree = PlacementUtils.countExtra(0, 0.125f, 1);
    public static PlacementModifier megaTree = PlacementUtils.countExtra(0, 0.1f, 1);
    public static PlacementModifier veryRareTree = PlacementUtils.countExtra(0, 0.05f, 1);
    public static PlacementModifier superRareTree = PlacementUtils.countExtra(0, 0.025f, 1);
    public static PlacementModifier specialTree = PlacementUtils.countExtra(0, 0.01f, 1);

    public static void boostrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureRegistryEntryLookup = context.lookup(Registries.CONFIGURED_FEATURE);

        Holder.Reference<ConfiguredFeature<?, ?>> waterDelta = configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.WATER_DELTA);

        register(context, NOTING, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.NOTING),
                List.of());

        register(context, WATER_DELTA, waterDelta, List.of(common, InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,  BiomeFilter.biome()));

        register(context, ABUNDANT_WATER_DELTA, waterDelta, List.of(abundant, InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,  BiomeFilter.biome()));

        register(context, SNOW_LAYER_FIRST, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.SNOW_LAYER_FIRST),
                List.of( CountPlacement.of(80),
                        CountPlacement.of(10),  // Note: having two counts is unusual; remove if not intended
                        InSquarePlacement.spread(),
                        HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                        BlockPredicateFilter.forPredicate(
                                BlockPredicate.allOf(
                                        // Checks if the block below is grass, snow, or blackstone
                                        BlockPredicate.matchesBlocks(new BlockPos(0, -1, 0),
                                                Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE),

                                        // Checks if the current position is air or snow
                                        BlockPredicate.matchesBlocks(new BlockPos(0, 0, 0),
                                                Blocks.AIR, Blocks.SNOW),

                                        // Checks if any of the surrounding blocks (1 block away in each cardinal direction) are grass, snow, or blackstone
                                        BlockPredicate.anyOf(
                                                BlockPredicate.matchesBlocks(new BlockPos(1, 0, 0),
                                                        Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE),
                                                BlockPredicate.matchesBlocks(new BlockPos(0, 0, 1),
                                                        Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE),
                                                BlockPredicate.matchesBlocks(new BlockPos(-1, 0, 0),
                                                        Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE),
                                                BlockPredicate.matchesBlocks(new BlockPos(0, 0, -1),
                                                        Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE)
                                        )
                                )
                        ),
                        BiomeFilter.biome()));

        register(context, SNOW_LAYER_SECOND, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.SNOW_LAYER_SECOND),
                List.of(CountPlacement.of(80),
                        CountPlacement.of(10),  // If two counts are not intentional, remove one
                        InSquarePlacement.spread(),
                        HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                        BlockPredicateFilter.forPredicate(
                                BlockPredicate.allOf(
                                        // First Predicate: `not` block with `any_of` surrounding conditions
                                        BlockPredicate.not(
                                                BlockPredicate.anyOf(
                                                        BlockPredicate.matchesBlocks(new BlockPos(1, 0, 0), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE),
                                                        BlockPredicate.matchesBlocks(new BlockPos(0, 0, 1), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE),
                                                        BlockPredicate.matchesBlocks(new BlockPos(-1, 0, 0), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE),
                                                        BlockPredicate.matchesBlocks(new BlockPos(0, 0, -1), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE),
                                                        BlockPredicate.matchesBlocks(new BlockPos(1, -1, 0), Blocks.AIR),
                                                        BlockPredicate.matchesBlocks(new BlockPos(0, -1, 1), Blocks.AIR),
                                                        BlockPredicate.matchesBlocks(new BlockPos(-1, -1, 0), Blocks.AIR),
                                                        BlockPredicate.matchesBlocks(new BlockPos(0, -1, -1), Blocks.AIR)
                                                )
                                        ),
                                        // Second Predicate: Block below must be grass_block, snow_block, or blackstone
                                        BlockPredicate.matchesBlocks(new BlockPos(0, -1, 0), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE),

                                        // Third Predicate: Block at the target position must be air or snow
                                        BlockPredicate.matchesBlocks(new BlockPos(0, 0, 0), Blocks.AIR, Blocks.SNOW),

                                        // Fourth Predicate: `any_of` additional surrounding conditions
                                        BlockPredicate.anyOf(
                                                BlockPredicate.matchesBlocks(new BlockPos(2, 0, 0), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE),
                                                BlockPredicate.matchesBlocks(new BlockPos(0, 0, 2), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE),
                                                BlockPredicate.matchesBlocks(new BlockPos(-2, 0, 0), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE),
                                                BlockPredicate.matchesBlocks(new BlockPos(0, 0, -2), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE),
                                                BlockPredicate.matchesBlocks(new BlockPos(1, 0, 1), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE),
                                                BlockPredicate.matchesBlocks(new BlockPos(-1, 0, 1), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE),
                                                BlockPredicate.matchesBlocks(new BlockPos(1, 0, -1), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE),
                                                BlockPredicate.matchesBlocks(new BlockPos(-1, 0, -1), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE)
                                        )
                                )
                        ),
                        BiomeFilter.biome()));

        register(context, SNOW_LAYER_THIRD, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.SNOW_LAYER_THIRD),
                List.of(
                        CountPlacement.of(80),
                        CountPlacement.of(10), // If two counts are not intentional, remove one
                        InSquarePlacement.spread(),
                        HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                        BlockPredicateFilter.forPredicate(
                                BlockPredicate.allOf(
                                        // First Predicate: Block at target position must be air or snow
                                        BlockPredicate.matchesBlocks(new BlockPos(0, 0, 0), Blocks.AIR, Blocks.SNOW),

                                        // Second Predicate: `not` condition with `any_of` for surrounding blocks
                                        BlockPredicate.not(
                                                BlockPredicate.anyOf(
                                                        BlockPredicate.matchesBlocks(new BlockPos(1, 0, 0), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE),
                                                        BlockPredicate.matchesBlocks(new BlockPos(0, 0, 1), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE),
                                                        BlockPredicate.matchesBlocks(new BlockPos(-1, 0, 0), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE),
                                                        BlockPredicate.matchesBlocks(new BlockPos(0, 0, -1), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE),
                                                        BlockPredicate.matchesBlocks(new BlockPos(2, 0, 0), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE),
                                                        BlockPredicate.matchesBlocks(new BlockPos(0, 0, 2), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE),
                                                        BlockPredicate.matchesBlocks(new BlockPos(-2, 0, 0), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE),
                                                        BlockPredicate.matchesBlocks(new BlockPos(0, 0, -2), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE),
                                                        BlockPredicate.matchesBlocks(new BlockPos(1, 0, 1), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE),
                                                        BlockPredicate.matchesBlocks(new BlockPos(-1, 0, 1), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE),
                                                        BlockPredicate.matchesBlocks(new BlockPos(1, 0, -1), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE),
                                                        BlockPredicate.matchesBlocks(new BlockPos(-1, 0, -1), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE),
                                                        BlockPredicate.matchesBlocks(new BlockPos(1, -1, 0), Blocks.AIR),
                                                        BlockPredicate.matchesBlocks(new BlockPos(0, -1, 1), Blocks.AIR),
                                                        BlockPredicate.matchesBlocks(new BlockPos(-1, -1, 0), Blocks.AIR),
                                                        BlockPredicate.matchesBlocks(new BlockPos(0, -1, -1), Blocks.AIR)
                                                )
                                        ),

                                        // Third Predicate: Block below target position must be a specific block
                                        BlockPredicate.matchesBlocks(new BlockPos(0, -1, 0), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE),

                                        // Fourth Predicate: `any_of` conditions with additional neighboring checks
                                        BlockPredicate.anyOf(
                                                BlockPredicate.matchesBlocks(new BlockPos(3, 0, 0), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE),
                                                BlockPredicate.matchesBlocks(new BlockPos(0, 0, 3), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE),
                                                BlockPredicate.matchesBlocks(new BlockPos(-3, 0, 0), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE),
                                                BlockPredicate.matchesBlocks(new BlockPos(0, 0, -3), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE)
                                        )
                                )
                        ),
                        BiomeFilter.biome()));

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
        SubTropicalTreePlacedFeatures.boostrap(context);
        KapokTreePlacedFeatures.boostrap(context);
        MyrwoodnutTreePlacedFeatures.boostrap(context);
        PalmTreePlacedFeatures.boostrap(context);
        MahoganyTreePlacedFeatures.boostrap(context);
        LeopardTreePlacedFeatures.boostrap(context);
        MushroomPlacedFeatures.boostrap(context);
        VanillaBlockOrePlacedFeatures.boostrap(context);
        CavePlacedFeatures.boostrap(context);
        JungleCavePlacedFeatures.boostrap(context);
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
