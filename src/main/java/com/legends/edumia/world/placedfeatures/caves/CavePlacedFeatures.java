package com.legends.edumia.world.placedfeatures.caves;

import com.legends.edumia.Edumia;
import com.legends.edumia.core.TagLoader;
import com.legends.edumia.world.congiguredfeatures.caves.CaveConfiguredFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class CavePlacedFeatures {

    public static final ResourceKey<PlacedFeature> DS_REDUCER_SMALL = registerKey("ds_reducer_small");
    public static final ResourceKey<PlacedFeature> DS_REDUCER = registerKey("ds_reducer");
    public static final ResourceKey<PlacedFeature> NOISE_REDUCER_SMALL = registerKey("noise_reducer_small");
    public static final ResourceKey<PlacedFeature> NOISE_REDUCER = registerKey("noise_reducer");

    public static void boostrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, DS_REDUCER_SMALL, configuredFeatureRegistryEntryLookup.getOrThrow(CaveConfiguredFeatures.DS_REDUCER_SMALL),
                List.of(CountPlacement.of(40), CountPlacement.of(40), InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(200)),
                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(new BlockPos(0, 0, 0),
                                Blocks.DEEPSLATE_COAL_ORE, Blocks.DEEPSLATE_COPPER_ORE, Blocks.DEEPSLATE_IRON_ORE,
                                Blocks.DEEPSLATE_GOLD_ORE, Blocks.DEEPSLATE_DIAMOND_ORE, Blocks.DEEPSLATE_REDSTONE_ORE,
                                Blocks.DEEPSLATE_LAPIS_ORE)),
                        BiomeFilter.biome()));

        register(context, DS_REDUCER, configuredFeatureRegistryEntryLookup.getOrThrow(CaveConfiguredFeatures.DS_REDUCER),
                List.of(CountPlacement.of(50), CountPlacement.of(25), InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(200)),
                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(new BlockPos(0, 0, 0),
                                Blocks.DEEPSLATE_COAL_ORE, Blocks.DEEPSLATE_COPPER_ORE, Blocks.DEEPSLATE_IRON_ORE,
                                Blocks.DEEPSLATE_GOLD_ORE, Blocks.DEEPSLATE_DIAMOND_ORE, Blocks.DEEPSLATE_REDSTONE_ORE,
                                Blocks.DEEPSLATE_LAPIS_ORE)),
                        BiomeFilter.biome()));

        register(context, NOISE_REDUCER_SMALL, configuredFeatureRegistryEntryLookup.getOrThrow(CaveConfiguredFeatures.NOISE_REDUCER_SMALL),
                List.of(CountPlacement.of(50), CountPlacement.of(40), InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(200)),
                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesTag(TagLoader.Blocks.STONE_ORES),
                                BlockPredicate.matchesTag(TagLoader.Blocks.DEEPSLATE_ORES))),
                        BiomeFilter.biome()));

        register(context, NOISE_REDUCER, configuredFeatureRegistryEntryLookup.getOrThrow(CaveConfiguredFeatures.NOISE_REDUCER),
                List.of(CountPlacement.of(30), CountPlacement.of(25), InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(200)),
                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesTag(TagLoader.Blocks.STONE_ORES),
                                BlockPredicate.matchesTag(TagLoader.Blocks.DEEPSLATE_ORES))),
                        BiomeFilter.biome()));
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "caves/" + name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
