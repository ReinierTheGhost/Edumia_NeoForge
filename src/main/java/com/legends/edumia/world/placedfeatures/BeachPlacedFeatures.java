package com.legends.edumia.world.placedfeatures;

import com.legends.edumia.Edumia;
import com.legends.edumia.blocks.blocksets.SandBlockSets;
import com.legends.edumia.core.BlockLoader;
import com.legends.edumia.world.congiguredfeatures.beach.BeachConfiguredFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class BeachPlacedFeatures {

    public static final ResourceKey<PlacedFeature> WHITE_SAND_LAYER_FIRST = registerKey("beach/layers/white/first");
    public static final ResourceKey<PlacedFeature> WHITE_SAND_LAYER_SECOND = registerKey("beach/layers/white/second");
    public static final ResourceKey<PlacedFeature> WHITE_SAND_LAYER_THIRD = registerKey("beach/layers/white/third");

    public static void boostrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, WHITE_SAND_LAYER_FIRST, configuredFeatureRegistryEntryLookup.getOrThrow(BeachConfiguredFeatures.WHITE_SAND_LAYER_FIRST),
                List.of( CountPlacement.of(80),
                        CountPlacement.of(10),  // Note: having two counts is unusual; remove if not intended
                        InSquarePlacement.spread(),
                        HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                        BlockPredicateFilter.forPredicate(
                                BlockPredicate.allOf(
                                        // Checks if the block below is grass, snow, or blackstone
                                        BlockPredicate.matchesBlocks(new BlockPos(0, -1, 0),
                                                Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE, SandBlockSets.WHITE_SAND.block().get()),

                                        // Checks if the current position is air or snow
                                        BlockPredicate.matchesBlocks(new BlockPos(0, 0, 0),
                                                Blocks.AIR, SandBlockSets.WHITE_SAND.layer().get()),

                                        // Checks if any of the surrounding blocks (1 block away in each cardinal direction) are grass, snow, or blackstone
                                        BlockPredicate.anyOf(
                                                BlockPredicate.matchesBlocks(new BlockPos(1, 0, 0),
                                                        Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE, SandBlockSets.WHITE_SAND.block().get()),
                                                BlockPredicate.matchesBlocks(new BlockPos(0, 0, 1),
                                                        Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE, SandBlockSets.WHITE_SAND.block().get()),
                                                BlockPredicate.matchesBlocks(new BlockPos(-1, 0, 0),
                                                        Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE, SandBlockSets.WHITE_SAND.block().get()),
                                                BlockPredicate.matchesBlocks(new BlockPos(0, 0, -1),
                                                        Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE, SandBlockSets.WHITE_SAND.block().get())
                                        )
                                )
                        ),
                        BiomeFilter.biome()));

        register(context, WHITE_SAND_LAYER_SECOND, configuredFeatureRegistryEntryLookup.getOrThrow(BeachConfiguredFeatures.WHITE_SAND_LAYER_SECOND),
                List.of(CountPlacement.of(80),
                        CountPlacement.of(10),  // If two counts are not intentional, remove one
                        InSquarePlacement.spread(),
                        HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                        BlockPredicateFilter.forPredicate(
                                BlockPredicate.allOf(
                                        // First Predicate: `not` block with `any_of` surrounding conditions
                                        BlockPredicate.not(
                                                BlockPredicate.anyOf(
                                                        BlockPredicate.matchesBlocks(new BlockPos(1, 0, 0), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE, SandBlockSets.WHITE_SAND.block().get()),
                                                        BlockPredicate.matchesBlocks(new BlockPos(0, 0, 1), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE, SandBlockSets.WHITE_SAND.block().get()),
                                                        BlockPredicate.matchesBlocks(new BlockPos(-1, 0, 0), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE, SandBlockSets.WHITE_SAND.block().get()),
                                                        BlockPredicate.matchesBlocks(new BlockPos(0, 0, -1), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE, SandBlockSets.WHITE_SAND.block().get()),
                                                        BlockPredicate.matchesBlocks(new BlockPos(1, -1, 0), Blocks.AIR),
                                                        BlockPredicate.matchesBlocks(new BlockPos(0, -1, 1), Blocks.AIR),
                                                        BlockPredicate.matchesBlocks(new BlockPos(-1, -1, 0), Blocks.AIR),
                                                        BlockPredicate.matchesBlocks(new BlockPos(0, -1, -1), Blocks.AIR)
                                                )
                                        ),
                                        // Second Predicate: Block below must be grass_block, snow_block, or blackstone
                                        BlockPredicate.matchesBlocks(new BlockPos(0, -1, 0), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE, SandBlockSets.WHITE_SAND.block().get()),

                                        // Third Predicate: Block at the target position must be air or snow
                                        BlockPredicate.matchesBlocks(new BlockPos(0, 0, 0), Blocks.AIR, SandBlockSets.WHITE_SAND.layer().get()),

                                        // Fourth Predicate: `any_of` additional surrounding conditions
                                        BlockPredicate.anyOf(
                                                BlockPredicate.matchesBlocks(new BlockPos(2, 0, 0), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE, SandBlockSets.WHITE_SAND.block().get()),
                                                BlockPredicate.matchesBlocks(new BlockPos(0, 0, 2), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE, SandBlockSets.WHITE_SAND.block().get()),
                                                BlockPredicate.matchesBlocks(new BlockPos(-2, 0, 0), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE, SandBlockSets.WHITE_SAND.block().get()),
                                                BlockPredicate.matchesBlocks(new BlockPos(0, 0, -2), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE, SandBlockSets.WHITE_SAND.block().get()),
                                                BlockPredicate.matchesBlocks(new BlockPos(1, 0, 1), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE, SandBlockSets.WHITE_SAND.block().get()),
                                                BlockPredicate.matchesBlocks(new BlockPos(-1, 0, 1), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE, SandBlockSets.WHITE_SAND.block().get()),
                                                BlockPredicate.matchesBlocks(new BlockPos(1, 0, -1), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE, SandBlockSets.WHITE_SAND.block().get()),
                                                BlockPredicate.matchesBlocks(new BlockPos(-1, 0, -1), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE, SandBlockSets.WHITE_SAND.block().get())
                                        )
                                )
                        ),
                        BiomeFilter.biome()));

        register(context, WHITE_SAND_LAYER_THIRD, configuredFeatureRegistryEntryLookup.getOrThrow(BeachConfiguredFeatures.WHITE_SAND_LAYER_THIRD),
                List.of(
                        CountPlacement.of(80),
                        CountPlacement.of(10), // If two counts are not intentional, remove one
                        InSquarePlacement.spread(),
                        HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                        BlockPredicateFilter.forPredicate(
                                BlockPredicate.allOf(
                                        // First Predicate: Block at target position must be air or snow
                                        BlockPredicate.matchesBlocks(new BlockPos(0, 0, 0), Blocks.AIR, SandBlockSets.WHITE_SAND.layer().get()),

                                        // Second Predicate: `not` condition with `any_of` for surrounding blocks
                                        BlockPredicate.not(
                                                BlockPredicate.anyOf(
                                                        BlockPredicate.matchesBlocks(new BlockPos(1, 0, 0), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE, SandBlockSets.WHITE_SAND.block().get()),
                                                        BlockPredicate.matchesBlocks(new BlockPos(0, 0, 1), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE, SandBlockSets.WHITE_SAND.block().get()),
                                                        BlockPredicate.matchesBlocks(new BlockPos(-1, 0, 0), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE, SandBlockSets.WHITE_SAND.block().get()),
                                                        BlockPredicate.matchesBlocks(new BlockPos(0, 0, -1), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE, SandBlockSets.WHITE_SAND.block().get()),
                                                        BlockPredicate.matchesBlocks(new BlockPos(2, 0, 0), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE, SandBlockSets.WHITE_SAND.block().get()),
                                                        BlockPredicate.matchesBlocks(new BlockPos(0, 0, 2), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE, SandBlockSets.WHITE_SAND.block().get()),
                                                        BlockPredicate.matchesBlocks(new BlockPos(-2, 0, 0), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE, SandBlockSets.WHITE_SAND.block().get()),
                                                        BlockPredicate.matchesBlocks(new BlockPos(0, 0, -2), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE, SandBlockSets.WHITE_SAND.block().get()),
                                                        BlockPredicate.matchesBlocks(new BlockPos(1, 0, 1), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE, SandBlockSets.WHITE_SAND.block().get()),
                                                        BlockPredicate.matchesBlocks(new BlockPos(-1, 0, 1), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE, SandBlockSets.WHITE_SAND.block().get()),
                                                        BlockPredicate.matchesBlocks(new BlockPos(1, 0, -1), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE, SandBlockSets.WHITE_SAND.block().get()),
                                                        BlockPredicate.matchesBlocks(new BlockPos(-1, 0, -1), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE, SandBlockSets.WHITE_SAND.block().get()),
                                                        BlockPredicate.matchesBlocks(new BlockPos(1, -1, 0), Blocks.AIR),
                                                        BlockPredicate.matchesBlocks(new BlockPos(0, -1, 1), Blocks.AIR),
                                                        BlockPredicate.matchesBlocks(new BlockPos(-1, -1, 0), Blocks.AIR),
                                                        BlockPredicate.matchesBlocks(new BlockPos(0, -1, -1), Blocks.AIR)
                                                )
                                        ),

                                        // Third Predicate: Block below target position must be a specific block
                                        BlockPredicate.matchesBlocks(new BlockPos(0, -1, 0), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE, SandBlockSets.WHITE_SAND.block().get()),

                                        // Fourth Predicate: `any_of` conditions with additional neighboring checks
                                        BlockPredicate.anyOf(
                                                BlockPredicate.matchesBlocks(new BlockPos(3, 0, 0), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE, SandBlockSets.WHITE_SAND.block().get()),
                                                BlockPredicate.matchesBlocks(new BlockPos(0, 0, 3), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE, SandBlockSets.WHITE_SAND.block().get()),
                                                BlockPredicate.matchesBlocks(new BlockPos(-3, 0, 0), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE, SandBlockSets.WHITE_SAND.block().get()),
                                                BlockPredicate.matchesBlocks(new BlockPos(0, 0, -3), Blocks.GRASS_BLOCK, Blocks.SNOW_BLOCK, Blocks.BLACKSTONE, SandBlockSets.WHITE_SAND.block().get())
                                        )
                                )
                        ),
                        BiomeFilter.biome()));

    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
