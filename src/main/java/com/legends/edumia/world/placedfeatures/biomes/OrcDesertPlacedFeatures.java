package com.legends.edumia.world.placedfeatures.biomes;

import com.legends.edumia.Edumia;
import com.legends.edumia.world.congiguredfeatures.ModConfiguredFeatures;
import com.legends.edumia.world.placedfeatures.ModPlacedFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class OrcDesertPlacedFeatures {

    public static final ResourceKey<PlacedFeature> SAND_PATH = registerKey("sand_path");

    public static void boostrap(BootstrapContext<PlacedFeature> context) {
        var holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);

        var registryEntryLookup = context.lookup(Registries.PLACED_FEATURE);

        Holder.Reference<PlacedFeature> noting = registryEntryLookup.getOrThrow(ModPlacedFeatures.NOTING);

        register(context, SAND_PATH, holdergetter.getOrThrow(ModConfiguredFeatures.STONE),
                List.of(CountPlacement.of(256), InSquarePlacement.spread(),
                        HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                        BiomeFilter.biome(),
                        CountPlacement.of(255),
                        RandomOffsetPlacement.of(UniformInt.of(-2, 2), UniformInt.of(-16, 0)),
                        BlockPredicateFilter.forPredicate(
                                BlockPredicate.allOf(
                                        BlockPredicate.matchesBlocks(Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.COARSE_DIRT, Blocks.MOSS_BLOCK,
                                                Blocks.ROOTED_DIRT, Blocks.RED_SAND),
                                        BlockPredicate.anyOf(
                                                BlockPredicate.matchesBlocks(new Vec3i(1, 5, 0), Blocks.DIRT, Blocks.STONE, Blocks.GRASS_BLOCK,
                                                        Blocks.COARSE_DIRT, Blocks.MOSS_BLOCK, Blocks.ROOTED_DIRT, Blocks.RED_SAND),
                                                BlockPredicate.matchesBlocks(new Vec3i(0, 5, 1), Blocks.DIRT, Blocks.STONE, Blocks.GRASS_BLOCK,
                                                        Blocks.COARSE_DIRT, Blocks.MOSS_BLOCK, Blocks.ROOTED_DIRT, Blocks.RED_SAND),
                                                BlockPredicate.matchesBlocks(new Vec3i(-1, 5, 0), Blocks.DIRT, Blocks.STONE, Blocks.GRASS_BLOCK,
                                                        Blocks.COARSE_DIRT, Blocks.MOSS_BLOCK, Blocks.ROOTED_DIRT, Blocks.RED_SAND),
                                                BlockPredicate.matchesBlocks(new Vec3i(0, 5, -1), Blocks.DIRT, Blocks.STONE, Blocks.GRASS_BLOCK,
                                                        Blocks.COARSE_DIRT, Blocks.MOSS_BLOCK, Blocks.ROOTED_DIRT, Blocks.RED_SAND),
                                                BlockPredicate.matchesBlocks(new Vec3i(1, 5, 1), Blocks.DIRT, Blocks.STONE, Blocks.GRASS_BLOCK,
                                                        Blocks.COARSE_DIRT, Blocks.MOSS_BLOCK, Blocks.ROOTED_DIRT, Blocks.RED_SAND),
                                                BlockPredicate.matchesBlocks(new Vec3i(-1, 5, 1), Blocks.DIRT, Blocks.STONE, Blocks.GRASS_BLOCK,
                                                        Blocks.COARSE_DIRT, Blocks.MOSS_BLOCK, Blocks.ROOTED_DIRT, Blocks.RED_SAND),
                                                BlockPredicate.matchesBlocks(new Vec3i(1, 5, -1), Blocks.DIRT, Blocks.STONE, Blocks.GRASS_BLOCK,
                                                        Blocks.COARSE_DIRT, Blocks.MOSS_BLOCK, Blocks.ROOTED_DIRT, Blocks.RED_SAND),
                                                BlockPredicate.matchesBlocks(new Vec3i(-1, 5, -1), Blocks.DIRT, Blocks.STONE, Blocks.GRASS_BLOCK,
                                                        Blocks.COARSE_DIRT, Blocks.MOSS_BLOCK, Blocks.ROOTED_DIRT, Blocks.RED_SAND)
                                        )))
                        ));


    }
    public static ResourceKey<PlacedFeature> registerKey(String name) {

        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "biomes/orc/" + name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
