package com.legends.edumia.world.congiguredfeatures.trees;

import com.legends.edumia.Edumia;
import com.legends.edumia.core.TagLoader;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.DarkOakFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AttachedToLeavesDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.CocoaDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TrunkVineDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.DarkOakTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.MegaJungleTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.*;
import org.checkerframework.checker.units.qual.C;

import java.util.List;

public class JungleTreeConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> JUNGLE_TREE_1 = registerKey("jungle_tree/1");
    public static final ResourceKey<ConfiguredFeature<?, ?>> JUNGLE_TREE_2 = registerKey("jungle_tree/2");
    public static final ResourceKey<ConfiguredFeature<?, ?>> JUNGLE_TREE_3 = registerKey("jungle_tree/3");
    public static final ResourceKey<ConfiguredFeature<?, ?>> JUNGLE_TREE_4 = registerKey("jungle_tree/4");
    public static final ResourceKey<ConfiguredFeature<?, ?>> JUNGLE_TREE_5 = registerKey("jungle_tree/5");
    public static final ResourceKey<ConfiguredFeature<?, ?>> JUNGLE_TREE_6 = registerKey("jungle_tree/6");
    public static final ResourceKey<ConfiguredFeature<?, ?>> JUNGLE_TREE_7 = registerKey("jungle_tree/7");
    public static final ResourceKey<ConfiguredFeature<?, ?>> JUNGLE_TREE_8 = registerKey("jungle_tree/8");
    public static final ResourceKey<ConfiguredFeature<?, ?>> JUNGLE_TREE_9 = registerKey("jungle_tree/9");
    public static final ResourceKey<ConfiguredFeature<?, ?>> JUNGLE_TREE_BRANCH_1_NORTH = registerKey("jungle_tree/branch/1_north");
    public static final ResourceKey<ConfiguredFeature<?, ?>> JUNGLE_TREE_BRANCH_1_SOUTH = registerKey("jungle_tree/branch/1_south");
    public static final ResourceKey<ConfiguredFeature<?, ?>> JUNGLE_TREE_BRANCH_1_EAST = registerKey("jungle_tree/branch/1_east");
    public static final ResourceKey<ConfiguredFeature<?, ?>> JUNGLE_TREE_BRANCH_1_WEST = registerKey("jungle_tree/branch/1_west");
    public static final ResourceKey<ConfiguredFeature<?, ?>> JUNGLE_TREE_BRANCH_2_NORTH = registerKey("jungle_tree/branch/2_north");
    public static final ResourceKey<ConfiguredFeature<?, ?>> JUNGLE_TREE_BRANCH_2_SOUTH = registerKey("jungle_tree/branch/2_south");
    public static final ResourceKey<ConfiguredFeature<?, ?>> JUNGLE_TREE_BRANCH_2_EAST = registerKey("jungle_tree/branch/2_east");
    public static final ResourceKey<ConfiguredFeature<?, ?>> JUNGLE_TREE_BRANCH_2_WEST = registerKey("jungle_tree/branch/2_west");
    public static final ResourceKey<ConfiguredFeature<?, ?>> JUNGLE_TREE_BRANCH_3_NE = registerKey("jungle_tree/branch/3_ne");
    public static final ResourceKey<ConfiguredFeature<?, ?>> JUNGLE_TREE_BRANCH_3_NW = registerKey("jungle_tree/branch/3_nw");
    public static final ResourceKey<ConfiguredFeature<?, ?>> JUNGLE_TREE_BRANCH_3_SE = registerKey("jungle_tree/branch/3_se");
    public static final ResourceKey<ConfiguredFeature<?, ?>> JUNGLE_TREE_BRANCH_3_SW = registerKey("jungle_tree/branch/3_sw");
    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
        HolderGetter<ConfiguredFeature<?, ?>> holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);
        HolderGetter<PlacedFeature> registryEntryLookup = context.lookup(Registries.PLACED_FEATURE);
        TagKey<Block> branch = TagLoader.Blocks.TREE_BRANCH_REPLACEABLE;

        register(context, JUNGLE_TREE_1, Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                PlacementUtils.inlinePlaced(Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                        PlacementUtils.inlinePlaced(Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                                        PlacementUtils.inlinePlaced(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                                                        BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                                                        new StraightTrunkPlacer(6, 2, 2),
                                                        BlockStateProvider.simple(Blocks.AIR),
                                                        new AcaciaFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
                                                        new TwoLayersFeatureSize(1, 0, 0)).ignoreVines()
                                                        .dirt(BlockStateProvider.simple(Blocks.ROOTED_DIRT)).ignoreVines().build(),

                                                BlockPredicateFilter.forPredicate(BlockPredicate.replaceable())),

                                        PlacementUtils.inlinePlaced(
                                                holdergetter.getOrThrow(JUNGLE_TREE_BRANCH_1_NORTH),
                                                BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG)),
                                                EnvironmentScanPlacement.scanningFor(Direction.UP,
                                                        BlockPredicate.not(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG)),
                                                        12)),
                                        PlacementUtils.inlinePlaced(
                                                holdergetter.getOrThrow(JUNGLE_TREE_BRANCH_1_SOUTH),
                                                BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG)),
                                                EnvironmentScanPlacement.scanningFor(Direction.UP,
                                                        BlockPredicate.not(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG)),
                                                        12)),
                                        PlacementUtils.inlinePlaced(
                                                holdergetter.getOrThrow(JUNGLE_TREE_BRANCH_1_EAST),
                                                BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG)),
                                                EnvironmentScanPlacement.scanningFor(Direction.UP,
                                                        BlockPredicate.not(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG)),
                                                        12)),
                                        PlacementUtils.inlinePlaced(
                                                holdergetter.getOrThrow(JUNGLE_TREE_BRANCH_1_WEST),
                                                BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG)),
                                                EnvironmentScanPlacement.scanningFor(Direction.UP,
                                                        BlockPredicate.not(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG)),
                                                        12))

                                )),
                                BlockPredicateFilter.forPredicate(BlockPredicate.replaceable()),
                                CountPlacement.of(40)),
                        PlacementUtils.inlinePlaced(
                                holdergetter.getOrThrow(RootsConfiguredFeatures.JUNGLE_TREE_ROOTS_1),
                                BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG))),
                        PlacementUtils.inlinePlaced(
                                holdergetter.getOrThrow(RootsConfiguredFeatures.JUNGLE_TREE_ROOTED_DIRT),
                                BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG))))
                        ),
                        BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.JUNGLE_SAPLING.defaultBlockState(), Vec3i.ZERO)),
                        CountPlacement.of(48))))
        );

        register(context, JUNGLE_TREE_2, Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                PlacementUtils.inlinePlaced(Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                                PlacementUtils.inlinePlaced(Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                                                PlacementUtils.inlinePlaced(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                                                                BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                                                                new StraightTrunkPlacer(6, 2, 2),
                                                                BlockStateProvider.simple(Blocks.AIR),
                                                                new AcaciaFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
                                                                new TwoLayersFeatureSize(1, 0, 0)).ignoreVines()
                                                                .dirt(BlockStateProvider.simple(Blocks.ROOTED_DIRT)).build(),

                                                        BlockPredicateFilter.forPredicate(BlockPredicate.replaceable())),

                                                PlacementUtils.inlinePlaced(
                                                        holdergetter.getOrThrow(JUNGLE_TREE_BRANCH_2_NORTH),
                                                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG)),
                                                        EnvironmentScanPlacement.scanningFor(Direction.UP,
                                                                BlockPredicate.not(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG)),
                                                                12)),
                                                PlacementUtils.inlinePlaced(
                                                        holdergetter.getOrThrow(JUNGLE_TREE_BRANCH_2_SOUTH),
                                                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG)),
                                                        EnvironmentScanPlacement.scanningFor(Direction.UP,
                                                                BlockPredicate.not(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG)),
                                                                12)),
                                                PlacementUtils.inlinePlaced(
                                                        holdergetter.getOrThrow(JUNGLE_TREE_BRANCH_2_EAST),
                                                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG)),
                                                        EnvironmentScanPlacement.scanningFor(Direction.UP,
                                                                BlockPredicate.not(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG)),
                                                                12)),
                                                PlacementUtils.inlinePlaced(
                                                        holdergetter.getOrThrow(JUNGLE_TREE_BRANCH_2_WEST),
                                                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG)),
                                                        EnvironmentScanPlacement.scanningFor(Direction.UP,
                                                                BlockPredicate.not(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG)),
                                                                12))

                                        )),
                                        BlockPredicateFilter.forPredicate(BlockPredicate.replaceable()),
                                        CountPlacement.of(40)),
                                PlacementUtils.inlinePlaced(
                                        holdergetter.getOrThrow(RootsConfiguredFeatures.JUNGLE_TREE_ROOTS_1),
                                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG))),
                                PlacementUtils.inlinePlaced(
                                        holdergetter.getOrThrow(RootsConfiguredFeatures.JUNGLE_TREE_ROOTED_DIRT),
                                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG))))
                        ),
                        BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.JUNGLE_SAPLING.defaultBlockState(), Vec3i.ZERO)),
                        CountPlacement.of(48))))
        );

        register(context, JUNGLE_TREE_3, Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                PlacementUtils.inlinePlaced(Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                        PlacementUtils.inlinePlaced(Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                                PlacementUtils.inlinePlaced(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                                        BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                                        new StraightTrunkPlacer(8, 1, 2),
                                        BlockStateProvider.simple(Blocks.AIR),
                                        new AcaciaFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
                                        new TwoLayersFeatureSize(1, 0, 0)).ignoreVines()
                                        .dirt(BlockStateProvider.simple(Blocks.ROOTED_DIRT))
                                                .decorators(List.of(new CocoaDecorator(0.2f))).build(),
                                        BlockPredicateFilter.forPredicate(BlockPredicate.replaceable())),
                                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(JUNGLE_TREE_BRANCH_3_NE),
                                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG)),
                                        EnvironmentScanPlacement.scanningFor(Direction.UP,
                                                BlockPredicate.not(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG)),
                                                12)
                                        )

                                )),
                                BlockPredicateFilter.forPredicate(BlockPredicate.replaceable()),
                                CountPlacement.of(64)),
                                PlacementUtils.inlinePlaced(
                                        holdergetter.getOrThrow(RootsConfiguredFeatures.JUNGLE_TREE_ROOTS_1),
                                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG))),
                                PlacementUtils.inlinePlaced(
                                        holdergetter.getOrThrow(RootsConfiguredFeatures.JUNGLE_TREE_ROOTED_DIRT),
                                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG)))
                                )

                        ),
                        BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.JUNGLE_SAPLING.defaultBlockState(), Vec3i.ZERO)),
                        CountPlacement.of(48))))
        );

        register(context, JUNGLE_TREE_4, Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                PlacementUtils.inlinePlaced(Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                                PlacementUtils.inlinePlaced(Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                                                PlacementUtils.inlinePlaced(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                                                                BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                                                                new DarkOakTrunkPlacer(26, 1, 1),
                                                                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState()
                                                                        .setValue(LeavesBlock.DISTANCE, 1)),
                                                                new FancyFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), 3),
                                                                new TwoLayersFeatureSize(3, 1, 1)).ignoreVines()
                                                                .decorators(List.of(
                                                                        new AttachedToLeavesDecorator(0.4f, 0, 0,
                                                                                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState()
                                                                                        .setValue(LeavesBlock.DISTANCE, 2)),
                                                                                1, List.of(Direction.DOWN)),
                                                                        new AttachedToLeavesDecorator(0.6f, 0, 0,
                                                                                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState()
                                                                                        .setValue(LeavesBlock.DISTANCE, 2)),
                                                                                1, List.of(Direction.EAST)),
                                                                        new AttachedToLeavesDecorator(0.6f, 0, 0,
                                                                                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState()
                                                                                        .setValue(LeavesBlock.DISTANCE, 2)),
                                                                                1, List.of(Direction.SOUTH)),
                                                                        new AttachedToLeavesDecorator(0.6f, 0, 0,
                                                                                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState()
                                                                                        .setValue(LeavesBlock.DISTANCE, 2)),
                                                                                1, List.of(Direction.WEST)),
                                                                        new AttachedToLeavesDecorator(0.6f, 0, 0,
                                                                                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState()
                                                                                        .setValue(LeavesBlock.DISTANCE, 2)),
                                                                                1, List.of(Direction.NORTH)),
                                                                        new AttachedToLeavesDecorator(0.85f, 0, 0,
                                                                                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState()
                                                                                        .setValue(LeavesBlock.DISTANCE, 2)),
                                                                                1, List.of(Direction.UP)),
                                                                        new LeaveVineDecorator(0.15f), TrunkVineDecorator.INSTANCE
                                                                ))
                                                                .dirt(BlockStateProvider.simple(Blocks.ROOTED_DIRT)).ignoreVines().build(),
                                                        BlockPredicateFilter.forPredicate(BlockPredicate.replaceable())),
                                                PlacementUtils.inlinePlaced(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                                                                BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                                                                new MegaJungleTrunkPlacer(31, 2, 0),
                                                                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState()
                                                                        .setValue(LeavesBlock.DISTANCE, 1)),
                                                                new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 2),
                                                                new TwoLayersFeatureSize(3, 1, 1)).ignoreVines()
                                                                .decorators(List.of(
                                                                        new AttachedToLeavesDecorator(0.4f, 0, 0,
                                                                                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState()
                                                                                        .setValue(LeavesBlock.DISTANCE, 2)),
                                                                                1, List.of(Direction.DOWN)),
                                                                        new AttachedToLeavesDecorator(0.6f, 0, 0,
                                                                                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState()
                                                                                        .setValue(LeavesBlock.DISTANCE, 2)),
                                                                                1, List.of(Direction.EAST)),
                                                                        new AttachedToLeavesDecorator(0.6f, 0, 0,
                                                                                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState()
                                                                                        .setValue(LeavesBlock.DISTANCE, 2)),
                                                                                1, List.of(Direction.SOUTH)),
                                                                        new AttachedToLeavesDecorator(0.6f, 0, 0,
                                                                                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState()
                                                                                        .setValue(LeavesBlock.DISTANCE, 2)),
                                                                                1, List.of(Direction.WEST)),
                                                                        new AttachedToLeavesDecorator(0.6f, 0, 0,
                                                                                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState()
                                                                                        .setValue(LeavesBlock.DISTANCE, 2)),
                                                                                1, List.of(Direction.NORTH)),
                                                                        new AttachedToLeavesDecorator(0.8f, 0, 0,
                                                                                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState()
                                                                                        .setValue(LeavesBlock.DISTANCE, 2)),
                                                                                1, List.of(Direction.UP)),
                                                                        new LeaveVineDecorator(0.15f), TrunkVineDecorator.INSTANCE
                                                                ))
                                                                .dirt(BlockStateProvider.simple(Blocks.JUNGLE_LOG)).ignoreVines().build(),
                                                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG)),
                                                        RandomOffsetPlacement.of(ConstantInt.of(0), ConstantInt.of(14)),
                                                        BlockPredicateFilter.forPredicate(BlockPredicate.not(
                                                                BlockPredicate.matchesBlocks(new Vec3i(0, 14, 0), Blocks.JUNGLE_LOG))),
                                                        RandomOffsetPlacement.of(ConstantInt.of(0), ConstantInt.of(-8)),
                                                        CountPlacement.of(UniformInt.of(2, 3)))
                                        )),
                                        BlockPredicateFilter.forPredicate(BlockPredicate.replaceable()),
                                        CountPlacement.of(28)),
                                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(RootsConfiguredFeatures.JUNGLE_TREE_ROOTS_2),
                                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG))),
                                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(RootsConfiguredFeatures.JUNGLE_TREE_ROOTED_DIRT_2),
                                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG)))
                        )),
                        BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.JUNGLE_SAPLING.defaultBlockState(), Vec3i.ZERO)),
                        CountPlacement.of(48)))));

        register(context, JUNGLE_TREE_BRANCH_1_NORTH, Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                PlacementUtils.inlinePlaced(
                        Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                                PlacementUtils.inlinePlaced(
                                        Feature.BLOCK_COLUMN, new BlockColumnConfiguration(List.of(BlockColumnConfiguration.layer(
                                                ConstantInt.of(2), BlockStateProvider.simple(Blocks.JUNGLE_LOG)
                                        )), Direction.UP, BlockPredicate.matchesTag(branch), false),
                                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesTag(branch))),

                                PlacementUtils.inlinePlaced(
                                        Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                                                PlacementUtils.inlinePlaced(
                                                        Feature.BLOCK_COLUMN, new BlockColumnConfiguration(List.of(BlockColumnConfiguration.layer(
                                                                ConstantInt.of(2), BlockStateProvider.simple(Blocks.JUNGLE_LOG))),
                                                                Direction.UP, BlockPredicate.matchesTag(branch), false),
                                                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesTag(branch))
                                                ),
                                                PlacementUtils.inlinePlaced(
                                                        Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                                                                BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                                                                new StraightTrunkPlacer(3, 0, 0),
                                                                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState().setValue(
                                                                        LeavesBlock.DISTANCE, 1)),
                                                                new AcaciaFoliagePlacer(new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder()
                                                                        .add(ConstantInt.of(2), 1)
                                                                        .add(ConstantInt.of(3), 2).build()), ConstantInt.of(0)),
                                                                new TwoLayersFeatureSize(0,0,0)
                                                        ).ignoreVines().dirt(BlockStateProvider.simple(Blocks.AIR))
                                                                .decorators(List.of(new CocoaDecorator(0.025f),
                                                                        new AttachedToLeavesDecorator(0.25f, 0, 0,
                                                                                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState()
                                                                                        .setValue(LeavesBlock.DISTANCE, 2)),
                                                                                2, List.of(Direction.DOWN)))
                                                                ).build(),
                                                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG)),
                                                        CountPlacement.of(8),
                                                        RandomOffsetPlacement.of(UniformInt.of(-1, 0), ConstantInt.of(1)),
                                                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                                                BlockPredicate.matchesTag(branch),
                                                                BlockPredicate.matchesBlocks(new Vec3i(0,0,1), Blocks.JUNGLE_LOG),
                                                                BlockPredicate.not(
                                                                        BlockPredicate.matchesBlocks(new Vec3i(0, 1, 1), Blocks.JUNGLE_LOG)),
                                                                BlockPredicate.matchesTag(new Vec3i(0,0, -1),
                                                                        branch),
                                                                BlockPredicate.matchesTag(new Vec3i(1,0, 1),
                                                                        branch),
                                                                BlockPredicate.matchesTag(new Vec3i(-1,0, 1),
                                                                        branch)))))
                                        ),
                                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG)),
                                        CountPlacement.of(8),
                                        RandomOffsetPlacement.of(UniformInt.of(-1, 0), ConstantInt.of(1)),
                                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                                BlockPredicate.matchesTag(branch),
                                                BlockPredicate.matchesBlocks(new Vec3i(0,0,1), Blocks.JUNGLE_LOG),
                                                BlockPredicate.matchesTag(new Vec3i(0,0,-1),
                                                        branch),
                                                BlockPredicate.matchesTag(new Vec3i(1,0, 1),
                                                        branch),
                                                BlockPredicate.matchesTag(new Vec3i(-1,0, 1),
                                                        branch))),
                                        CountPlacement.of(28)))
                        ),
                        CountPlacement.of(16),
                        RandomOffsetPlacement.of(UniformInt.of(-1, 0), ConstantInt.of(-1)),
                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                BlockPredicate.matchesTag(branch),
                                BlockPredicate.matchesBlocks(new Vec3i(0,0,1), Blocks.JUNGLE_LOG),
                                BlockPredicate.matchesTag(new Vec3i(0,1, 1),
                                        branch),
                                BlockPredicate.matchesTag(new Vec3i(0,0, -1),
                                        branch),
                                BlockPredicate.matchesTag(new Vec3i(1,0, 0),
                                        branch),
                                BlockPredicate.matchesTag(new Vec3i(-1,0, 0),
                                        branch))),
                        CountPlacement.of(28)
                ))));

        register(context, JUNGLE_TREE_BRANCH_1_SOUTH, Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                PlacementUtils.inlinePlaced(
                        Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                                PlacementUtils.inlinePlaced(
                                        Feature.BLOCK_COLUMN, new BlockColumnConfiguration(List.of(BlockColumnConfiguration.layer(
                                                ConstantInt.of(2), BlockStateProvider.simple(Blocks.JUNGLE_LOG)
                                        )), Direction.UP, BlockPredicate.matchesTag(branch), false),
                                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesTag(branch))),

                                PlacementUtils.inlinePlaced(
                                        Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                                        PlacementUtils.inlinePlaced(
                                                Feature.BLOCK_COLUMN, new BlockColumnConfiguration(List.of(BlockColumnConfiguration.layer(
                                                        ConstantInt.of(2), BlockStateProvider.simple(Blocks.JUNGLE_LOG))),
                                                        Direction.UP, BlockPredicate.matchesTag(branch), false),
                                                BlockPredicateFilter.forPredicate(BlockPredicate.matchesTag(branch))
                                        ),
                                        PlacementUtils.inlinePlaced(
                                                Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                                                        BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                                                        new StraightTrunkPlacer(3, 0, 0),
                                                        BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState().setValue(
                                                                LeavesBlock.DISTANCE, 1)),
                                                        new AcaciaFoliagePlacer(new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder().add(ConstantInt.of(2), 1)
                                                                .add(ConstantInt.of(3), 2).build()), ConstantInt.of(0)),
                                                        new TwoLayersFeatureSize(0,0,0)
                                                ).ignoreVines().dirt(BlockStateProvider.simple(Blocks.AIR))
                                                        .decorators(List.of(new CocoaDecorator(0.025f),
                                                                new AttachedToLeavesDecorator(0.25f, 0, 0,
                                                                        BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState()
                                                                                .setValue(LeavesBlock.DISTANCE, 2)),
                                                                        2, List.of(Direction.DOWN)))
                                                        ).build(),
                                                BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG)),
                                                CountPlacement.of(8),
                                                RandomOffsetPlacement.of(UniformInt.of(0, 1), ConstantInt.of(1)),
                                                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                                        BlockPredicate.matchesTag(branch),
                                                        BlockPredicate.matchesBlocks(new Vec3i(0,0,-1), Blocks.JUNGLE_LOG),
                                                        BlockPredicate.not(
                                                                BlockPredicate.matchesBlocks(new Vec3i(0, 1, -1), Blocks.JUNGLE_LOG)),
                                                        BlockPredicate.matchesTag(new Vec3i(0,0, 1),
                                                                branch),
                                                        BlockPredicate.matchesTag(new Vec3i(1,0, -1),
                                                                branch),
                                                        BlockPredicate.matchesTag(new Vec3i(-1,0, -1),
                                                                branch)))))
                                        ),
                                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG)),
                                        CountPlacement.of(8),
                                        RandomOffsetPlacement.of(UniformInt.of(0, 1), ConstantInt.of(1)),
                                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                                BlockPredicate.matchesTag(branch),
                                                BlockPredicate.matchesBlocks(new Vec3i(0,0,-1), Blocks.JUNGLE_LOG),
                                                BlockPredicate.matchesTag(new Vec3i(0,0,1),
                                                        branch),
                                                BlockPredicate.matchesTag(new Vec3i(1,0, -1),
                                                        branch),
                                                BlockPredicate.matchesTag(new Vec3i(-1,0, -1),
                                                        branch))),
                                        CountPlacement.of(28)))
                        ),
                        CountPlacement.of(16),
                        RandomOffsetPlacement.of(UniformInt.of(0, 1), ConstantInt.of(-1)),
                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                BlockPredicate.matchesTag(branch),
                                BlockPredicate.matchesBlocks(new Vec3i(0,0,-1), Blocks.JUNGLE_LOG),
                                BlockPredicate.matchesTag(new Vec3i(0,1, -1),
                                        branch),
                                BlockPredicate.matchesTag(new Vec3i(0,0, 1),
                                        branch),
                                BlockPredicate.matchesTag(new Vec3i(1,0, 0),
                                        branch),
                                BlockPredicate.matchesTag(new Vec3i(-1,0, 0),
                                        branch))),
                        CountPlacement.of(28)
                ))));

        register(context, JUNGLE_TREE_BRANCH_1_EAST, Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                PlacementUtils.inlinePlaced(
                        Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                                PlacementUtils.inlinePlaced(
                                        Feature.BLOCK_COLUMN, new BlockColumnConfiguration(List.of(BlockColumnConfiguration.layer(
                                                ConstantInt.of(2), BlockStateProvider.simple(Blocks.JUNGLE_LOG)
                                        )), Direction.UP, BlockPredicate.matchesTag(branch), false),
                                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesTag(branch))),

                                PlacementUtils.inlinePlaced(
                                        Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                                                PlacementUtils.inlinePlaced(
                                                        Feature.BLOCK_COLUMN, new BlockColumnConfiguration(List.of(BlockColumnConfiguration.layer(
                                                                ConstantInt.of(2), BlockStateProvider.simple(Blocks.JUNGLE_LOG))),
                                                                Direction.UP, BlockPredicate.matchesTag(branch), false),
                                                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesTag(branch))
                                                ),
                                                PlacementUtils.inlinePlaced(
                                                        Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                                                                BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                                                                new StraightTrunkPlacer(3, 0, 0),
                                                                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState().setValue(
                                                                        LeavesBlock.DISTANCE, 1)),
                                                                new AcaciaFoliagePlacer(new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder().add(ConstantInt.of(2), 1)
                                                                        .add(ConstantInt.of(3), 2).build()), ConstantInt.of(0)),
                                                                new TwoLayersFeatureSize(0,0,0)
                                                        ).ignoreVines().dirt(BlockStateProvider.simple(Blocks.AIR))
                                                                .decorators(List.of(new CocoaDecorator(0.025f),
                                                                        new AttachedToLeavesDecorator(0.25f, 0, 0,
                                                                                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState()
                                                                                        .setValue(LeavesBlock.DISTANCE, 2)),
                                                                                2, List.of(Direction.DOWN)))
                                                                ).build(),
                                                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG)),
                                                        CountPlacement.of(8),
                                                        RandomOffsetPlacement.of(UniformInt.of(0, 1), ConstantInt.of(1)),
                                                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                                                BlockPredicate.matchesTag(branch),
                                                                BlockPredicate.matchesBlocks(new Vec3i(-1,0,0), Blocks.JUNGLE_LOG),
                                                                BlockPredicate.not(
                                                                        BlockPredicate.matchesBlocks(new Vec3i(-1, 1, 0), Blocks.JUNGLE_LOG)),
                                                                BlockPredicate.matchesTag(new Vec3i(1,0, 0),
                                                                        branch),
                                                                BlockPredicate.matchesTag(new Vec3i(-1,0, 1),
                                                                        branch),
                                                                BlockPredicate.matchesTag(new Vec3i(-1,0, -1),
                                                                        branch)))))
                                        ),
                                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG)),
                                        CountPlacement.of(8),
                                        RandomOffsetPlacement.of(UniformInt.of(0, 1), ConstantInt.of(1)),
                                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                                BlockPredicate.matchesTag(branch),
                                                BlockPredicate.matchesBlocks(new Vec3i(-1,0,0), Blocks.JUNGLE_LOG),
                                                BlockPredicate.matchesTag(new Vec3i(1,0,0),
                                                        branch),
                                                BlockPredicate.matchesTag(new Vec3i(-1,0, 1),
                                                        branch),
                                                BlockPredicate.matchesTag(new Vec3i(-1,0, -1),
                                                        branch))),
                                        CountPlacement.of(28)))
                        ),
                        CountPlacement.of(8),
                        RandomOffsetPlacement.of(UniformInt.of(0, 1), ConstantInt.of(-1)),
                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                BlockPredicate.matchesTag(branch),
                                BlockPredicate.matchesBlocks(new Vec3i(-1,0,0), Blocks.JUNGLE_LOG),
                                BlockPredicate.matchesTag(new Vec3i(-1,1, 0),
                                        branch),
                                BlockPredicate.matchesTag(new Vec3i(1,0, 0),
                                        branch),
                                BlockPredicate.matchesTag(new Vec3i(0,0, 1),
                                        branch),
                                BlockPredicate.matchesTag(new Vec3i(0,0, -1),
                                        branch))),
                        CountPlacement.of(28)
                ))));

        register(context, JUNGLE_TREE_BRANCH_1_WEST, Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                PlacementUtils.inlinePlaced(
                        Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                                PlacementUtils.inlinePlaced(
                                        Feature.BLOCK_COLUMN, new BlockColumnConfiguration(List.of(BlockColumnConfiguration.layer(
                                                ConstantInt.of(2), BlockStateProvider.simple(Blocks.JUNGLE_LOG)
                                        )), Direction.UP, BlockPredicate.matchesTag(branch), false),
                                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesTag(branch))),

                                PlacementUtils.inlinePlaced(
                                        Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                                                PlacementUtils.inlinePlaced(
                                                        Feature.BLOCK_COLUMN, new BlockColumnConfiguration(List.of(BlockColumnConfiguration.layer(
                                                                ConstantInt.of(2), BlockStateProvider.simple(Blocks.JUNGLE_LOG))),
                                                                Direction.UP, BlockPredicate.matchesTag(branch), false),
                                                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesTag(branch))
                                                ),
                                                PlacementUtils.inlinePlaced(
                                                        Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                                                                BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                                                                new StraightTrunkPlacer(3, 0, 0),
                                                                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState().setValue(
                                                                        LeavesBlock.DISTANCE, 1)),
                                                                new AcaciaFoliagePlacer(new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder().add(ConstantInt.of(2), 1)
                                                                        .add(ConstantInt.of(3), 2).build()), ConstantInt.of(0)),
                                                                new TwoLayersFeatureSize(0,0,0)
                                                        ).ignoreVines().dirt(BlockStateProvider.simple(Blocks.AIR))
                                                                .decorators(List.of(new CocoaDecorator(0.025f),
                                                                        new AttachedToLeavesDecorator(0.25f, 0, 0,
                                                                                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState()
                                                                                        .setValue(LeavesBlock.DISTANCE, 2)),
                                                                                2, List.of(Direction.DOWN)))
                                                                ).build(),
                                                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG)),
                                                        CountPlacement.of(8),
                                                        RandomOffsetPlacement.of(UniformInt.of(-1, 0), ConstantInt.of(1)),
                                                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                                                BlockPredicate.matchesTag(branch),
                                                                BlockPredicate.matchesBlocks(new Vec3i(1,0,0), Blocks.JUNGLE_LOG),
                                                                BlockPredicate.not(
                                                                        BlockPredicate.matchesBlocks(new Vec3i(1, 1, 0),
                                                                                Blocks.JUNGLE_LOG)),
                                                                BlockPredicate.matchesTag(new Vec3i(-1,0, 0),
                                                                        branch),
                                                                BlockPredicate.matchesTag(new Vec3i(1,0, 1),
                                                                        branch),
                                                                BlockPredicate.matchesTag(new Vec3i(1,0, -1),
                                                                        branch)))))
                                        ),
                                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG)),
                                        CountPlacement.of(8),
                                        RandomOffsetPlacement.of(UniformInt.of(-1, 0), ConstantInt.of(1)),
                                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                                BlockPredicate.matchesTag(branch),
                                                BlockPredicate.matchesBlocks(new Vec3i(1,0,0), Blocks.JUNGLE_LOG),
                                                BlockPredicate.matchesTag(new Vec3i(-1,0,0),
                                                        branch),
                                                BlockPredicate.matchesTag(new Vec3i(1,0, 1),
                                                        branch),
                                                BlockPredicate.matchesTag(new Vec3i(1,0, -1),
                                                        branch))),
                                        CountPlacement.of(28)))
                        ),
                        CountPlacement.of(16),
                        RandomOffsetPlacement.of(UniformInt.of(-1, 0), ConstantInt.of(-1)),
                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                BlockPredicate.matchesTag(branch),
                                BlockPredicate.matchesBlocks(new Vec3i(1,0,0), Blocks.JUNGLE_LOG),
                                BlockPredicate.matchesTag(new Vec3i(1,1, 0),
                                        branch),
                                BlockPredicate.matchesTag(new Vec3i(-1,0, 0),
                                        branch),
                                BlockPredicate.matchesTag(new Vec3i(0,0, 1),
                                        branch),
                                BlockPredicate.matchesTag(new Vec3i(0,0, -1),
                                        branch))),
                        CountPlacement.of(28)
                ))));

        register(context, JUNGLE_TREE_BRANCH_2_NORTH, Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                PlacementUtils.inlinePlaced(
                        Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                                PlacementUtils.inlinePlaced(
                                        Feature.BLOCK_COLUMN, new BlockColumnConfiguration(List.of(BlockColumnConfiguration.layer(
                                                ConstantInt.of(2), BlockStateProvider.simple(Blocks.JUNGLE_LOG)
                                        )), Direction.UP, BlockPredicate.matchesTag(branch), false),
                                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesTag(branch))),

                                PlacementUtils.inlinePlaced(
                                        Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                                                PlacementUtils.inlinePlaced(
                                                        Feature.BLOCK_COLUMN, new BlockColumnConfiguration(List.of(BlockColumnConfiguration.layer(
                                                                ConstantInt.of(2), BlockStateProvider.simple(Blocks.JUNGLE_LOG))),
                                                                Direction.UP, BlockPredicate.matchesTag(branch), false),
                                                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesTag(branch))
                                                ),
                                                PlacementUtils.inlinePlaced(
                                                        Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                                                                BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                                                                new StraightTrunkPlacer(3, 0, 0),
                                                                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState().setValue(
                                                                        LeavesBlock.DISTANCE, 1)),
                                                                new AcaciaFoliagePlacer(new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder()
                                                                        .add(ConstantInt.of(2), 1)
                                                                        .add(ConstantInt.of(3), 2).build()), ConstantInt.of(0)),
                                                                new TwoLayersFeatureSize(0,0,0)
                                                        ).ignoreVines().dirt(BlockStateProvider.simple(Blocks.AIR))
                                                                .decorators(List.of(new CocoaDecorator(0.025f),
                                                                        new LeaveVineDecorator(0.2f),
                                                                        new AttachedToLeavesDecorator(0.25f, 0, 0,
                                                                                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState()
                                                                                        .setValue(LeavesBlock.DISTANCE, 2)),
                                                                                2, List.of(Direction.DOWN)))
                                                                ).build(),
                                                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG)),
                                                        CountPlacement.of(8),
                                                        RandomOffsetPlacement.of(UniformInt.of(-1, 0), ConstantInt.of(1)),
                                                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                                                BlockPredicate.matchesTag(branch),
                                                                BlockPredicate.matchesBlocks(new Vec3i(0,0,1), Blocks.JUNGLE_LOG),
                                                                BlockPredicate.not(
                                                                        BlockPredicate.matchesBlocks(new Vec3i(0, 1, 1), Blocks.JUNGLE_LOG)),
                                                                BlockPredicate.matchesTag(new Vec3i(0,0, -1),
                                                                        branch),
                                                                BlockPredicate.matchesTag(new Vec3i(1,0, 1),
                                                                        branch),
                                                                BlockPredicate.matchesTag(new Vec3i(-1,0, 1),
                                                                        branch)))))
                                        ),
                                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG)),
                                        CountPlacement.of(8),
                                        RandomOffsetPlacement.of(UniformInt.of(-1, 0), ConstantInt.of(1)),
                                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                                BlockPredicate.matchesTag(branch),
                                                BlockPredicate.matchesBlocks(new Vec3i(0,0,1), Blocks.JUNGLE_LOG),
                                                BlockPredicate.matchesTag(new Vec3i(0,0,-1),
                                                        branch),
                                                BlockPredicate.matchesTag(new Vec3i(1,0, 1),
                                                        branch),
                                                BlockPredicate.matchesTag(new Vec3i(-1,0, 1),
                                                        branch))),
                                        CountPlacement.of(28)))
                        ),
                        CountPlacement.of(16),
                        RandomOffsetPlacement.of(UniformInt.of(-1, 0), ConstantInt.of(-1)),
                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                BlockPredicate.matchesTag(branch),
                                BlockPredicate.matchesBlocks(new Vec3i(0,0,1), Blocks.JUNGLE_LOG),
                                BlockPredicate.matchesTag(new Vec3i(0,1, 1),
                                        branch),
                                BlockPredicate.matchesTag(new Vec3i(0,0, -1),
                                        branch),
                                BlockPredicate.matchesTag(new Vec3i(1,0, 0),
                                        branch),
                                BlockPredicate.matchesTag(new Vec3i(-1,0, 0),
                                        branch))),
                        CountPlacement.of(28)
                ))));

        register(context, JUNGLE_TREE_BRANCH_2_SOUTH, Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                PlacementUtils.inlinePlaced(
                        Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                                PlacementUtils.inlinePlaced(
                                        Feature.BLOCK_COLUMN, new BlockColumnConfiguration(List.of(BlockColumnConfiguration.layer(
                                                ConstantInt.of(2), BlockStateProvider.simple(Blocks.JUNGLE_LOG)
                                        )), Direction.UP, BlockPredicate.matchesTag(branch), false),
                                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesTag(branch))),

                                PlacementUtils.inlinePlaced(
                                        Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                                                PlacementUtils.inlinePlaced(
                                                        Feature.BLOCK_COLUMN, new BlockColumnConfiguration(List.of(BlockColumnConfiguration.layer(
                                                                ConstantInt.of(2), BlockStateProvider.simple(Blocks.JUNGLE_LOG))),
                                                                Direction.UP, BlockPredicate.matchesTag(branch), false),
                                                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesTag(branch))
                                                ),
                                                PlacementUtils.inlinePlaced(
                                                        Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                                                                BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                                                                new StraightTrunkPlacer(3, 0, 0),
                                                                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState().setValue(
                                                                        LeavesBlock.DISTANCE, 1)),
                                                                new AcaciaFoliagePlacer(new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder().add(ConstantInt.of(2), 1)
                                                                        .add(ConstantInt.of(3), 2).build()), ConstantInt.of(0)),
                                                                new TwoLayersFeatureSize(0,0,0)
                                                        ).ignoreVines().dirt(BlockStateProvider.simple(Blocks.AIR))
                                                                .decorators(List.of(new CocoaDecorator(0.025f),
                                                                        new LeaveVineDecorator(0.2f),
                                                                        new AttachedToLeavesDecorator(0.25f, 0, 0,
                                                                                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState()
                                                                                        .setValue(LeavesBlock.DISTANCE, 2)),
                                                                                2, List.of(Direction.DOWN)))
                                                                ).build(),
                                                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG)),
                                                        CountPlacement.of(8),
                                                        RandomOffsetPlacement.of(UniformInt.of(0, 1), ConstantInt.of(1)),
                                                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                                                BlockPredicate.matchesTag(branch),
                                                                BlockPredicate.matchesBlocks(new Vec3i(0,0,-1), Blocks.JUNGLE_LOG),
                                                                BlockPredicate.not(
                                                                        BlockPredicate.matchesBlocks(new Vec3i(0, 1, -1), Blocks.JUNGLE_LOG)),
                                                                BlockPredicate.matchesTag(new Vec3i(0,0, 1),
                                                                        branch),
                                                                BlockPredicate.matchesTag(new Vec3i(1,0, -1),
                                                                        branch),
                                                                BlockPredicate.matchesTag(new Vec3i(-1,0, -1),
                                                                        branch)))))
                                        ),
                                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG)),
                                        CountPlacement.of(8),
                                        RandomOffsetPlacement.of(UniformInt.of(0, 1), ConstantInt.of(1)),
                                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                                BlockPredicate.matchesTag(branch),
                                                BlockPredicate.matchesBlocks(new Vec3i(0,0,-1), Blocks.JUNGLE_LOG),
                                                BlockPredicate.matchesTag(new Vec3i(0,0,1),
                                                        branch),
                                                BlockPredicate.matchesTag(new Vec3i(1,0, -1),
                                                        branch),
                                                BlockPredicate.matchesTag(new Vec3i(-1,0, -1),
                                                        branch))),
                                        CountPlacement.of(28)))
                        ),
                        CountPlacement.of(16),
                        RandomOffsetPlacement.of(UniformInt.of(0, 1), ConstantInt.of(-1)),
                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                BlockPredicate.matchesTag(branch),
                                BlockPredicate.matchesBlocks(new Vec3i(0,0,-1), Blocks.JUNGLE_LOG),
                                BlockPredicate.matchesTag(new Vec3i(0,1, -1),
                                        branch),
                                BlockPredicate.matchesTag(new Vec3i(0,0, 1),
                                        branch),
                                BlockPredicate.matchesTag(new Vec3i(1,0, 0),
                                        branch),
                                BlockPredicate.matchesTag(new Vec3i(-1,0, 0),
                                        branch))),
                        CountPlacement.of(28)
                ))));

        register(context, JUNGLE_TREE_BRANCH_2_EAST, Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                PlacementUtils.inlinePlaced(
                        Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                                PlacementUtils.inlinePlaced(
                                        Feature.BLOCK_COLUMN, new BlockColumnConfiguration(List.of(BlockColumnConfiguration.layer(
                                                ConstantInt.of(2), BlockStateProvider.simple(Blocks.JUNGLE_LOG)
                                        )), Direction.UP, BlockPredicate.matchesTag(branch), false),
                                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesTag(branch))),

                                PlacementUtils.inlinePlaced(
                                        Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                                                PlacementUtils.inlinePlaced(
                                                        Feature.BLOCK_COLUMN, new BlockColumnConfiguration(List.of(BlockColumnConfiguration.layer(
                                                                ConstantInt.of(2), BlockStateProvider.simple(Blocks.JUNGLE_LOG))),
                                                                Direction.UP, BlockPredicate.matchesTag(branch), false),
                                                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesTag(branch))
                                                ),
                                                PlacementUtils.inlinePlaced(
                                                        Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                                                                BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                                                                new StraightTrunkPlacer(3, 0, 0),
                                                                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState().setValue(
                                                                        LeavesBlock.DISTANCE, 1)),
                                                                new AcaciaFoliagePlacer(new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder().add(ConstantInt.of(2), 1)
                                                                        .add(ConstantInt.of(3), 2).build()), ConstantInt.of(0)),
                                                                new TwoLayersFeatureSize(0,0,0)
                                                        ).ignoreVines().dirt(BlockStateProvider.simple(Blocks.AIR))
                                                                .decorators(List.of(new CocoaDecorator(0.025f),
                                                                        new LeaveVineDecorator(0.2f),
                                                                        new AttachedToLeavesDecorator(0.25f, 0, 0,
                                                                                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState()
                                                                                        .setValue(LeavesBlock.DISTANCE, 2)),
                                                                                2, List.of(Direction.DOWN)))
                                                                ).build(),
                                                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG)),
                                                        CountPlacement.of(8),
                                                        RandomOffsetPlacement.of(UniformInt.of(0, 1), ConstantInt.of(1)),
                                                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                                                BlockPredicate.matchesTag(branch),
                                                                BlockPredicate.matchesBlocks(new Vec3i(-1,0,0), Blocks.JUNGLE_LOG),
                                                                BlockPredicate.not(
                                                                        BlockPredicate.matchesBlocks(new Vec3i(-1, 1, 0), Blocks.JUNGLE_LOG)),
                                                                BlockPredicate.matchesTag(new Vec3i(1,0, 0),
                                                                        branch),
                                                                BlockPredicate.matchesTag(new Vec3i(-1,0, 1),
                                                                        branch),
                                                                BlockPredicate.matchesTag(new Vec3i(-1,0, -1),
                                                                        branch)))))
                                        ),
                                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG)),
                                        CountPlacement.of(8),
                                        RandomOffsetPlacement.of(UniformInt.of(0, 1), ConstantInt.of(1)),
                                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                                BlockPredicate.matchesTag(branch),
                                                BlockPredicate.matchesBlocks(new Vec3i(-1,0,0), Blocks.JUNGLE_LOG),
                                                BlockPredicate.matchesTag(new Vec3i(1,0,0),
                                                        branch),
                                                BlockPredicate.matchesTag(new Vec3i(-1,0, 1),
                                                        branch),
                                                BlockPredicate.matchesTag(new Vec3i(-1,0, -1),
                                                        branch))),
                                        CountPlacement.of(28)))
                        ),
                        CountPlacement.of(8),
                        RandomOffsetPlacement.of(UniformInt.of(0, 1), ConstantInt.of(-1)),
                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                BlockPredicate.matchesTag(branch),
                                BlockPredicate.matchesBlocks(new Vec3i(-1,0,0), Blocks.JUNGLE_LOG),
                                BlockPredicate.matchesTag(new Vec3i(-1,1, 0),
                                        branch),
                                BlockPredicate.matchesTag(new Vec3i(1,0, 0),
                                        branch),
                                BlockPredicate.matchesTag(new Vec3i(0,0, 1),
                                        branch),
                                BlockPredicate.matchesTag(new Vec3i(0,0, -1),
                                        branch))),
                        CountPlacement.of(28)
                ))));

        register(context, JUNGLE_TREE_BRANCH_2_WEST, Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                PlacementUtils.inlinePlaced(
                        Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                                PlacementUtils.inlinePlaced(
                                        Feature.BLOCK_COLUMN, new BlockColumnConfiguration(List.of(BlockColumnConfiguration.layer(
                                                ConstantInt.of(2), BlockStateProvider.simple(Blocks.JUNGLE_LOG)
                                        )), Direction.UP, BlockPredicate.matchesTag(branch), false),
                                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesTag(branch))),

                                PlacementUtils.inlinePlaced(
                                        Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                                                PlacementUtils.inlinePlaced(
                                                        Feature.BLOCK_COLUMN, new BlockColumnConfiguration(List.of(BlockColumnConfiguration.layer(
                                                                ConstantInt.of(2), BlockStateProvider.simple(Blocks.JUNGLE_LOG))),
                                                                Direction.UP, BlockPredicate.matchesTag(branch), false),
                                                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesTag(branch))
                                                ),
                                                PlacementUtils.inlinePlaced(
                                                        Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                                                                BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                                                                new StraightTrunkPlacer(3, 0, 0),
                                                                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState().setValue(
                                                                        LeavesBlock.DISTANCE, 1)),
                                                                new AcaciaFoliagePlacer(new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder().add(ConstantInt.of(2), 1)
                                                                        .add(ConstantInt.of(3), 2).build()), ConstantInt.of(0)),
                                                                new TwoLayersFeatureSize(0,0,0)
                                                        ).ignoreVines().dirt(BlockStateProvider.simple(Blocks.AIR))
                                                                .decorators(List.of(new CocoaDecorator(0.025f),
                                                                        new LeaveVineDecorator(0.2f),
                                                                        new AttachedToLeavesDecorator(0.25f, 0, 0,
                                                                                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState()
                                                                                        .setValue(LeavesBlock.DISTANCE, 2)),
                                                                                2, List.of(Direction.DOWN)))
                                                                ).build(),
                                                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG)),
                                                        CountPlacement.of(8),
                                                        RandomOffsetPlacement.of(UniformInt.of(-1, 0), ConstantInt.of(1)),
                                                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                                                BlockPredicate.matchesTag(branch),
                                                                BlockPredicate.matchesBlocks(new Vec3i(1,0,0), Blocks.JUNGLE_LOG),
                                                                BlockPredicate.not(
                                                                        BlockPredicate.matchesBlocks(new Vec3i(1, 1, 0),
                                                                                Blocks.JUNGLE_LOG)),
                                                                BlockPredicate.matchesTag(new Vec3i(-1,0, 0),
                                                                        branch),
                                                                BlockPredicate.matchesTag(new Vec3i(1,0, 1),
                                                                        branch),
                                                                BlockPredicate.matchesTag(new Vec3i(1,0, -1),
                                                                        branch)))))
                                        ),
                                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG)),
                                        CountPlacement.of(8),
                                        RandomOffsetPlacement.of(UniformInt.of(-1, 0), ConstantInt.of(1)),
                                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                                BlockPredicate.matchesTag(branch),
                                                BlockPredicate.matchesBlocks(new Vec3i(1,0,0), Blocks.JUNGLE_LOG),
                                                BlockPredicate.matchesTag(new Vec3i(-1,0,0),
                                                        branch),
                                                BlockPredicate.matchesTag(new Vec3i(1,0, 1),
                                                        branch),
                                                BlockPredicate.matchesTag(new Vec3i(1,0, -1),
                                                        branch))),
                                        CountPlacement.of(28)))
                        ),
                        CountPlacement.of(16),
                        RandomOffsetPlacement.of(UniformInt.of(-1, 0), ConstantInt.of(-1)),
                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                BlockPredicate.matchesTag(branch),
                                BlockPredicate.matchesBlocks(new Vec3i(1,0,0), Blocks.JUNGLE_LOG),
                                BlockPredicate.matchesTag(new Vec3i(1,1, 0),
                                        branch),
                                BlockPredicate.matchesTag(new Vec3i(-1,0, 0),
                                        branch),
                                BlockPredicate.matchesTag(new Vec3i(0,0, 1),
                                        branch),
                                BlockPredicate.matchesTag(new Vec3i(0,0, -1),
                                        branch))),
                        CountPlacement.of(28)
                ))));

        register(context, JUNGLE_TREE_BRANCH_3_NE, Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                PlacementUtils.inlinePlaced(Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                        PlacementUtils.inlinePlaced(Feature.BLOCK_COLUMN, new BlockColumnConfiguration(List.of(BlockColumnConfiguration.layer(
                                        ConstantInt.of(2), BlockStateProvider.simple(Blocks.JUNGLE_LOG))),
                                        Direction.UP, BlockPredicate.matchesTag(branch), false),
                                BlockPredicateFilter.forPredicate(BlockPredicate.matchesTag(branch))),
                        PlacementUtils.inlinePlaced(Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                                PlacementUtils.inlinePlaced(Feature.BLOCK_COLUMN, new BlockColumnConfiguration(List.of(BlockColumnConfiguration.layer(
                                        ConstantInt.of(2), BlockStateProvider.simple(Blocks.JUNGLE_LOG))),
                                                Direction.UP, BlockPredicate.matchesTag(branch), false),
                                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesTag(branch))),
                                PlacementUtils.inlinePlaced(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(
                                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                                                BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                                                new StraightTrunkPlacer(3, 0, 0),
                                                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES),
                                                new DarkOakFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0)),
                                                new TwoLayersFeatureSize(0,0,0)
                                        ).decorators(List.of(
                                                new AttachedToLeavesDecorator(0.25f, 0, 0,
                                                        BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState()
                                                                .setValue(LeavesBlock.DISTANCE, 2)),
                                                        2, List.of(Direction.DOWN))
                                        )).dirt(BlockStateProvider.simple(Blocks.AIR)).ignoreVines().build()), 0.5f)
                                ),
                                        PlacementUtils.inlinePlaced(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                                                BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                                                new StraightTrunkPlacer(3, 0, 0),
                                                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES),
                                                new AcaciaFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0)),
                                                new TwoLayersFeatureSize(0,0,0)).decorators(List.of(
                                                new AttachedToLeavesDecorator(0.25f, 0, 0,
                                                        BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState()
                                                                .setValue(LeavesBlock.DISTANCE, 2)),
                                                        2, List.of(Direction.DOWN))
                                        )).dirt(BlockStateProvider.simple(Blocks.AIR)).ignoreVines().build())),
                                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG)),
                                        CountPlacement.of(8),
                                        RandomOffsetPlacement.of(new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder()
                                                .add(ConstantInt.of(1), 1)
                                                .add(ConstantInt.of(-1), 1).build()), ConstantInt.of(-1)),
                                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                                BlockPredicate.matchesTag(branch),
                                                BlockPredicate.matchesBlocks(new Vec3i(-1, 0, 1), Blocks.JUNGLE_LOG),
                                                BlockPredicate.not(
                                                        BlockPredicate.matchesBlocks(new Vec3i(-1,1,1), Blocks.JUNGLE_LOG)),
                                                BlockPredicate.not(
                                                        BlockPredicate.matchesBlocks(new Vec3i(-2,0,2), Blocks.JUNGLE_LOG)),
                                                BlockPredicate.not(
                                                        BlockPredicate.matchesBlocks(new Vec3i(1,0,1), Blocks.JUNGLE_LOG)),
                                                BlockPredicate.not(
                                                        BlockPredicate.matchesBlocks(new Vec3i(-1,0,-1), Blocks.JUNGLE_LOG)),
                                                BlockPredicate.matchesTag(new Vec3i(1,0,-1), branch),
                                                BlockPredicate.matchesTag(new Vec3i(-1,0,0), branch),
                                                BlockPredicate.matchesTag(new Vec3i(0,0,1), branch)
                                        ))

                                )
                                )),
                                BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.JUNGLE_LOG)),
                                CountPlacement.of(8),
                                RandomOffsetPlacement.of(new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder()
                                        .add(ConstantInt.of(1), 1)
                                        .add(ConstantInt.of(-1), 1).build()), ConstantInt.of(-1)),
                                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                        BlockPredicate.matchesTag(branch),
                                        BlockPredicate.matchesBlocks(new Vec3i(-1, 0, 1), Blocks.JUNGLE_LOG),
                                        BlockPredicate.not(
                                                BlockPredicate.matchesBlocks(new Vec3i(-2, 0, 2), Blocks.JUNGLE_LOG)),
                                        BlockPredicate.not(
                                                BlockPredicate.matchesBlocks(new Vec3i(1, 0, 1), Blocks.JUNGLE_LOG)),
                                        BlockPredicate.not(
                                                BlockPredicate.matchesBlocks(new Vec3i(-1, 0, -1), Blocks.JUNGLE_LOG)),
                                        BlockPredicate.matchesTag(new Vec3i(-1, 0, 0), branch),
                                        BlockPredicate.matchesTag(new Vec3i(0, 0, 1), branch)
                                )),
                                CountPlacement.of(28)
                                )
                )),
                        CountPlacement.of(16),
                        RandomOffsetPlacement.of(new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder()
                                .add(ConstantInt.of(1), 1)
                                .add(ConstantInt.of(-1), 1).build()), ConstantInt.of(-1)),
                        BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                BlockPredicate.matchesTag(branch),
                                BlockPredicate.matchesBlocks(new Vec3i(-1, 0, 1)),
                                BlockPredicate.matchesTag(new Vec3i(-1, 1, 1), branch),
                                BlockPredicate.not(BlockPredicate.matchesBlocks(new Vec3i(1, 0, 1), Blocks.JUNGLE_LOG)),
                                BlockPredicate.not(BlockPredicate.matchesBlocks(new Vec3i(-1, 0, -1), Blocks.JUNGLE_LOG)),
                                BlockPredicate.matchesTag(new Vec3i(0, 0, 1), branch),
                                BlockPredicate.matchesTag(new Vec3i(-1, 0, 0), branch)
                        )),
                        CountPlacement.of(28))))
        );
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "tree/tropical/jungle_trees/" + name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext< ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature,
                                                                                          FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }


}
