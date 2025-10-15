package com.legends.edumia.world.placedfeatures.biomes;

import com.legends.edumia.Edumia;
import com.legends.edumia.world.congiguredfeatures.ModConfiguredFeatures;
import com.legends.edumia.world.congiguredfeatures.SimpleBlockConfiguredFeatures;
import com.legends.edumia.world.congiguredfeatures.disks.VanillaBlockDiskConfiguredFeatures;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.material.Fluids;

import java.util.List;

public class VolcanicBiomePlacedFeatures {
    public static final ResourceKey<PlacedFeature> LAKE = registerKey("lake");
    public static final ResourceKey<PlacedFeature> TERRA_SAND = registerKey("terra_sand");
    public static final ResourceKey<PlacedFeature> ACID_YELLOW = registerKey("acid_yellow");
    public static final ResourceKey<PlacedFeature> ACID_ORANGE = registerKey("acid_orange");
    public static final ResourceKey<PlacedFeature> ACID_GRAVEL = registerKey("acid_gravel");
    public static final ResourceKey<PlacedFeature> ACID_BLACK = registerKey("acid_black");
    public static final ResourceKey<PlacedFeature> ACID_BASALT = registerKey("acid_basalt");

    public static final ResourceKey<PlacedFeature> COARSE_DIRT_DISK = registerKey("coarse_dirt_disk");
    public static final ResourceKey<PlacedFeature> PODZOL_DISK = registerKey("podzol");
    public static final ResourceKey<PlacedFeature> MAGMA = registerKey("magma");

    public static void boostrap(BootstrapContext<PlacedFeature> context) {
        // region [HELPERS]
        var holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);

        var registryEntryLookup = context.lookup(Registries.PLACED_FEATURE);

        BlockPredicate ringMustNotMatch = BlockPredicate.not(BlockPredicate.anyOf(
                airBasaltSandBlackLavaAt(new Vec3i(-1, 0,  0)),
                airBasaltSandBlackLavaAt(new Vec3i( 1, 0,  0)),
                airBasaltSandBlackLavaAt(new Vec3i( 0, 0, -1)),
                airBasaltSandBlackLavaAt(new Vec3i( 0, 0,  1)),
                airBasaltSandBlackLavaAt(new Vec3i(-1, 0, -1)),
                airBasaltSandBlackLavaAt(new Vec3i( 1, 0,  1)),
                airBasaltSandBlackLavaAt(new Vec3i( 1, 0, -1)),
                airBasaltSandBlackLavaAt(new Vec3i(-1, 0,  1))
        ));

        BlockPredicate centerIsYellowTerracottaOrMagma = BlockPredicate.matchesBlocks(new Vec3i(0, 0, 0),
                Blocks.YELLOW_TERRACOTTA, Blocks.MAGMA_BLOCK
        );

        BlockPredicate yellowTerracotta = BlockPredicate.matchesBlocks(Blocks.YELLOW_TERRACOTTA);

        BlockPredicate ringAboveMustNotMatch = BlockPredicate.not(BlockPredicate.anyOf(
                BlockPredicate.matchesBlocks(new Vec3i(-1, 1,  0), Blocks.YELLOW_TERRACOTTA, Blocks.RED_SAND, Blocks.BLACKSTONE, Blocks.CALCITE, Blocks.GRASS_BLOCK, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.GRAVEL, Blocks.SMOOTH_BASALT, Blocks.LAVA),
                BlockPredicate.matchesBlocks(new Vec3i( 1, 1,  0), Blocks.YELLOW_TERRACOTTA, Blocks.RED_SAND, Blocks.BLACKSTONE, Blocks.CALCITE, Blocks.GRASS_BLOCK, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.GRAVEL, Blocks.SMOOTH_BASALT, Blocks.LAVA),
                BlockPredicate.matchesBlocks(new Vec3i( 0, 1, -1), Blocks.YELLOW_TERRACOTTA, Blocks.RED_SAND, Blocks.BLACKSTONE, Blocks.CALCITE, Blocks.GRASS_BLOCK, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.GRAVEL, Blocks.SMOOTH_BASALT, Blocks.LAVA),
                BlockPredicate.matchesBlocks(new Vec3i( 0, 1,  1), Blocks.YELLOW_TERRACOTTA, Blocks.RED_SAND, Blocks.BLACKSTONE, Blocks.CALCITE, Blocks.GRASS_BLOCK, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.GRAVEL, Blocks.SMOOTH_BASALT, Blocks.LAVA),
                BlockPredicate.matchesBlocks(new Vec3i(-1, 1, -1), Blocks.YELLOW_TERRACOTTA, Blocks.RED_SAND, Blocks.BLACKSTONE, Blocks.CALCITE, Blocks.GRASS_BLOCK, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.GRAVEL, Blocks.SMOOTH_BASALT, Blocks.LAVA),
                BlockPredicate.matchesBlocks(new Vec3i(-1, 1,  1), Blocks.YELLOW_TERRACOTTA, Blocks.RED_SAND, Blocks.BLACKSTONE, Blocks.CALCITE, Blocks.GRASS_BLOCK, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.GRAVEL, Blocks.SMOOTH_BASALT, Blocks.LAVA),
                BlockPredicate.matchesBlocks(new Vec3i( 1, 1, -1), Blocks.YELLOW_TERRACOTTA, Blocks.RED_SAND, Blocks.BLACKSTONE, Blocks.CALCITE, Blocks.GRASS_BLOCK, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.GRAVEL, Blocks.SMOOTH_BASALT, Blocks.LAVA),
                BlockPredicate.matchesBlocks(new Vec3i( 1, 1,  1), Blocks.YELLOW_TERRACOTTA, Blocks.RED_SAND, Blocks.BLACKSTONE, Blocks.CALCITE, Blocks.GRASS_BLOCK, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.GRAVEL, Blocks.SMOOTH_BASALT, Blocks.LAVA)
        ));

        BlockPredicate mustNotMatchWater = BlockPredicate.not(BlockPredicate.anyOf(
                BlockPredicate.matchesFluids(new Vec3i(-1, 0,  0), Fluids.WATER),
                BlockPredicate.matchesFluids(new Vec3i( 1, 0,  0), Fluids.WATER),
                BlockPredicate.matchesFluids(new Vec3i( 0, 0, -1), Fluids.WATER),
                BlockPredicate.matchesFluids(new Vec3i( 0, 0,  1), Fluids.WATER),
                BlockPredicate.matchesFluids(new Vec3i(-1, 0, -1), Fluids.WATER),
                BlockPredicate.matchesFluids(new Vec3i(-1, 0,  1), Fluids.WATER),
                BlockPredicate.matchesFluids(new Vec3i( 1, 0, 1), Fluids.WATER),
                BlockPredicate.matchesFluids(new Vec3i( 1, 0,  -1), Fluids.WATER),
                BlockPredicate.matchesFluids(new Vec3i( 0, 1,  0), Fluids.WATER)
        ));

        BlockPredicate target = BlockPredicate.allOf(
                ringMustNotMatch,
                centerIsYellowTerracottaOrMagma,
                ringAboveMustNotMatch
        );

        BlockPredicate terraSand = BlockPredicate.allOf(
                mustNotMatchWater,
                yellowTerracotta
        );



        // endregion

        register(context, LAKE, holdergetter.getOrThrow(SimpleBlockConfiguredFeatures.WATER), List.of(
                CountPlacement.of(20), CountPlacement.of(30), InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG), BiomeFilter.biome(),
                CountPlacement.of(UniformInt.of(5, 5)), RandomOffsetPlacement.of(UniformInt.of(-4, 4), ConstantInt.of(0)),
                EnvironmentScanPlacement.scanningFor(Direction.DOWN, target, 6)
        ));

        register(context, TERRA_SAND, holdergetter.getOrThrow(SimpleBlockConfiguredFeatures.RED_SAND), List.of(
                CountPlacement.of(20), CountPlacement.of(30), InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                EnvironmentScanPlacement.scanningFor(Direction.DOWN, terraSand, 8), BiomeFilter.biome()
        ));

        register(context, ACID_YELLOW, holdergetter.getOrThrow(VanillaBlockDiskConfiguredFeatures.ACID_YELLOW), List.of(
                CountPlacement.of(188), CountPlacement.of(6), InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(63), VerticalAnchor.absolute(63)),
                BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)), BiomeFilter.biome()
        ));

        register(context, ACID_ORANGE, holdergetter.getOrThrow(VanillaBlockDiskConfiguredFeatures.ACID_ORANGE), List.of(
                CountPlacement.of(4), CountPlacement.of(82), InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(62), VerticalAnchor.absolute(64)),
                BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)), BiomeFilter.biome()
        ));

        register(context, ACID_GRAVEL, holdergetter.getOrThrow(VanillaBlockDiskConfiguredFeatures.ACID_GRAVEL), List.of(
                CountPlacement.of(256), InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(62), VerticalAnchor.absolute(64)),
                BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)), BiomeFilter.biome()
        ));

        register(context, ACID_BLACK, holdergetter.getOrThrow(VanillaBlockDiskConfiguredFeatures.ACID_BLACK), List.of(
                CountPlacement.of(228), InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(62), VerticalAnchor.absolute(64)),
                BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)), BiomeFilter.biome()
        ));

        register(context, ACID_BASALT, holdergetter.getOrThrow(VanillaBlockDiskConfiguredFeatures.ACID_BASALT), List.of(
                CountPlacement.of(228), InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(62), VerticalAnchor.absolute(64)),
                BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)), BiomeFilter.biome()
        ));


        register(context, COARSE_DIRT_DISK, holdergetter.getOrThrow(VanillaBlockDiskConfiguredFeatures.COARSE_DIRT_DISK), List.of(
                CountPlacement.of(128), InSquarePlacement.spread(),
                NoiseBasedCountPlacement.of(1, 175, 1),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(66), VerticalAnchor.belowTop(100)),
                BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.CALCITE)), BiomeFilter.biome()
        ));

        register(context, PODZOL_DISK, holdergetter.getOrThrow(VanillaBlockDiskConfiguredFeatures.PODZOL_DISK), List.of(
                CountPlacement.of(128), InSquarePlacement.spread(),
                NoiseBasedCountPlacement.of(1, 175, 1),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(70), VerticalAnchor.belowTop(100)),
                BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.CALCITE, Blocks.COARSE_DIRT)), BiomeFilter.biome()
        ));

        register(context, MAGMA, holdergetter.getOrThrow(ModConfiguredFeatures.MAGMA), List.of(
                CountPlacement.of(UniformInt.of(2, 3)), InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR),
                BiomeFilter.biome()
        ));
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {

        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "biomes/volcanic/" + name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    // === BlockPredicates voor environment_scan ===
    // "matching_blocks" helpers
    public static BlockPredicate airBasaltSandBlackLavaAt(Vec3i off) {
        return BlockPredicate.matchesBlocks(off,
                Blocks.SMOOTH_BASALT, Blocks.RED_SAND, Blocks.BLACKSTONE, Blocks.AIR, Blocks.LAVA
        );
    }
}
