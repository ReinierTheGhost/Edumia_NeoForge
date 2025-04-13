package com.legends.edumia.world.congiguredfeatures.trees;

import com.legends.edumia.Edumia;
import com.legends.edumia.core.TagLoader;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RandomOffsetPlacement;

import java.util.List;

public class RootsConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> JUNGLE_TREE_ROOTED_DIRT = registerKey("jungle_tree/rooted_dirt");
    public static final ResourceKey<ConfiguredFeature<?, ?>> JUNGLE_TREE_ROOTED_DIRT_1 = registerKey("jungle_tree/rooted_dirt/1");
    public static final ResourceKey<ConfiguredFeature<?, ?>> JUNGLE_TREE_ROOTED_DIRT_2 = registerKey("jungle_tree/rooted_dirt/2");
    public static final ResourceKey<ConfiguredFeature<?, ?>> JUNGLE_TREE_ROOTS_1 = registerKey("jungle_tree/roots/1");
    public static final ResourceKey<ConfiguredFeature<?, ?>> JUNGLE_TREE_ROOTS_2 = registerKey("jungle_tree/roots/2");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
        HolderGetter<ConfiguredFeature<?, ?>> holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);
        HolderGetter<PlacedFeature> registryEntryLookup = context.lookup(Registries.PLACED_FEATURE);

        register(context, JUNGLE_TREE_ROOTED_DIRT, Feature.RANDOM_PATCH, new RandomPatchConfiguration(
                3, 1, 1, PlacementUtils.inlinePlaced(
                        Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                                BlockStateProvider.simple(Blocks.ROOTED_DIRT)), BlockPredicateFilter.forPredicate(
                        BlockPredicate.allOf(BlockPredicate.matchesTag(TagLoader.Blocks.DIRT),
                                BlockPredicate.not(
                                        BlockPredicate.matchesTag(new Vec3i(0,1,0), TagLoader.Blocks.DIRT)))))));

        register(context, JUNGLE_TREE_ROOTED_DIRT_1, Feature.RANDOM_PATCH, new RandomPatchConfiguration(
                16, 2, 2, PlacementUtils.inlinePlaced(
                        Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                                BlockStateProvider.simple(Blocks.ROOTED_DIRT)), BlockPredicateFilter.forPredicate(
                        BlockPredicate.allOf(BlockPredicate.matchesTag(TagLoader.Blocks.DIRT),
                                BlockPredicate.not(
                                        BlockPredicate.matchesTag(new Vec3i(0,1,0), TagLoader.Blocks.DIRT)))))));

        register(context, JUNGLE_TREE_ROOTED_DIRT_2, Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(
                HolderSet.direct(PlacementUtils.inlinePlaced(
                        Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                                BlockStateProvider.simple(Blocks.ROOTED_DIRT)), CountPlacement.of(5),
                        RandomOffsetPlacement.of(UniformInt.of(-1,2), UniformInt.of(-2,2)),
                        BlockPredicateFilter.forPredicate(
                                BlockPredicate.allOf(
                                BlockPredicate.matchesTag(TagLoader.Blocks.DIRT),
                                BlockPredicate.not(
                                        BlockPredicate.matchesTag(new Vec3i(0,1,0), TagLoader.Blocks.DIRT))))
                ))
        ));

        register(context, JUNGLE_TREE_ROOTS_1, Feature.RANDOM_PATCH, new RandomPatchConfiguration(5, 1, 1,
                PlacementUtils.inlinePlaced(
                        Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(
                                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(
                                        Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                                                BlockStateProvider.simple(Blocks.MOSS_CARPET)),
                                        RandomOffsetPlacement.of(ConstantInt.of(0), ConstantInt.of(1)),
                                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                                BlockPredicate.replaceable(),
                                                BlockPredicate.matchesBlocks(new Vec3i(0, -1, 0),
                                                        Blocks.JUNGLE_WOOD)
                                        ))
                                ), 0.2f)
                        ),
                                PlacementUtils.inlinePlaced(
                                        Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                                                BlockStateProvider.simple(Blocks.JUNGLE_WOOD)),
                                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                                BlockPredicate.replaceable(),
                                                BlockPredicate.anyOf(
                                                        BlockPredicate.matchesTag(new Vec3i(0, -1, 0),
                                                                TagLoader.Blocks.DIRT),
                                                        BlockPredicate.matchesBlocks(new Vec3i(0, -1, 0),
                                                                Blocks.JUNGLE_WOOD)
                                                )
                                        )))),
                        BlockPredicateFilter.forPredicate(BlockPredicate.anyOf(
                                BlockPredicate.matchesBlocks(new Vec3i(1, 1, 0),
                                        Blocks.JUNGLE_WOOD, Blocks.JUNGLE_LOG),
                                BlockPredicate.matchesBlocks(new Vec3i(0, 1, 1),
                                        Blocks.JUNGLE_WOOD, Blocks.JUNGLE_LOG),
                                BlockPredicate.matchesBlocks(new Vec3i(-1, 1, 0),
                                        Blocks.JUNGLE_WOOD, Blocks.JUNGLE_LOG),
                                BlockPredicate.matchesBlocks(new Vec3i(0, 1, -1),
                                        Blocks.JUNGLE_WOOD, Blocks.JUNGLE_LOG)
                        )))));

        register(context, JUNGLE_TREE_ROOTS_2, Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(
                HolderSet.direct(PlacementUtils.inlinePlaced(
                        Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(
                                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(
                                        Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                                                BlockStateProvider.simple(Blocks.MOSS_CARPET)),
                                        RandomOffsetPlacement.of(ConstantInt.of(0), ConstantInt.of(1)),
                                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                                BlockPredicate.matchesBlocks(Blocks.AIR, Blocks.VINE),
                                                BlockPredicate.matchesBlocks(new Vec3i(0, -1, 0),
                                                        Blocks.JUNGLE_WOOD)))),
                                        0.05f)),
                        PlacementUtils.inlinePlaced(
                                Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.JUNGLE_WOOD)),
                                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.replaceable(),
                                        BlockPredicate.anyOf(BlockPredicate.matchesTag(
                                                        new Vec3i(0, -1, 0), TagLoader.Blocks.DIRT),
                                                BlockPredicate.matchesBlocks(
                                                        new Vec3i(0, -1, 0), Blocks.JUNGLE_WOOD))
                                        )))),
                        CountPlacement.of(26), RandomOffsetPlacement.of(UniformInt.of(-1, 2),
                                UniformInt.of(-2, 3)),
                        BlockPredicateFilter.forPredicate(BlockPredicate.anyOf(
                                BlockPredicate.matchesBlocks(new Vec3i(1, 2, 0),
                                        Blocks.JUNGLE_WOOD, Blocks.JUNGLE_LOG),
                                BlockPredicate.matchesBlocks(new Vec3i(0, 2, 1),
                                        Blocks.JUNGLE_WOOD, Blocks.JUNGLE_LOG),
                                BlockPredicate.matchesBlocks(new Vec3i(-1, 2, 0),
                                        Blocks.JUNGLE_WOOD, Blocks.JUNGLE_LOG),
                                BlockPredicate.matchesBlocks(new Vec3i(0, 2, -1),
                                        Blocks.JUNGLE_WOOD, Blocks.JUNGLE_LOG)
                        ))
                        ))));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "tree/roots/" +  name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext< ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature,
                                                                                          FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
