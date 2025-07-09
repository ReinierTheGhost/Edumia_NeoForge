package com.legends.edumia.world.congiguredfeatures.caves;

import com.legends.edumia.Edumia;
import com.legends.edumia.core.TagLoader;
import com.legends.edumia.world.trees.foliageplacer.RandomPalmLeavesFoliagePlacer;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DripstoneThickness;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BushFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.CherryFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.RandomSpreadFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.CherryTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;
import java.util.OptionalInt;

public class JungleCaveConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> LOLLIPOP_TREE = registerKey("lollipop_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CHERRY_TREE = registerKey("cherry_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> AZALEA_BUSH = registerKey("azalea_bush");

    public static final ResourceKey<ConfiguredFeature<?, ?>> POINTED_DRIPSTONE = registerKey("pointed_dripstone");

    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES = registerKey("trees");

    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOOR = registerKey("floor");

    public static final ResourceKey<ConfiguredFeature<?, ?>> MOSS_PATCH = registerKey("moss_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MUDDY_ROOTS_ORE = registerKey("muddy_roots_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> LOW_FOLIAGE = registerKey("low_foliage");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HIGH_FOLIAGE = registerKey("high_foliage");

    public static final ResourceKey<ConfiguredFeature<?, ?>> LICHEN = registerKey("lichen");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
        var holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);
        var registryEntryLookup = context.lookup(Registries.PLACED_FEATURE);

        RuleTest targetRule = new TagMatchTest(TagLoader.Blocks.UNDERGROUND_JUNGLE_REPLACEABLE);

        BlockState muddyRoots = Blocks.MUDDY_MANGROVE_ROOTS.defaultBlockState();

        OreConfiguration.TargetBlockState target = OreConfiguration.target(targetRule, muddyRoots);

        MultifaceBlock multifaceblock = (MultifaceBlock)Blocks.GLOW_LICHEN;

        // region [TREES]
        register(context, LOLLIPOP_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.MANGROVE_LOG),
                new StraightTrunkPlacer(6, 1, 0),
                new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                        .add(Blocks.AZALEA_LEAVES.defaultBlockState(), 3)
                        .add(Blocks.FLOWERING_AZALEA_LEAVES.defaultBlockState(), 1)
                        .add(Blocks.JUNGLE_LEAVES.defaultBlockState(), 2).build()),
                new RandomSpreadFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), ConstantInt.of(3), 250),
                new TwoLayersFeatureSize(1, 0, 1))
                .dirt(BlockStateProvider.simple(Blocks.MUD))
                .decorators(List.of(new BeehiveDecorator(0.02f))).build());

        register(context, CHERRY_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.CHERRY_LOG),
                new CherryTrunkPlacer(8, 1, 0, UniformInt.of(2, 3), UniformInt.of(2, 4),
                        UniformInt.of(-4, -1), UniformInt.of(-1, 1)),
                BlockStateProvider.simple(Blocks.CHERRY_LEAVES),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0), ConstantInt.of(4),
                        0.25f,0.25f, 0.16666667f, 0.33333334f),
                new TwoLayersFeatureSize(1, 0, 2)
        ).dirt(BlockStateProvider.simple(Blocks.MOSS_BLOCK)).decorators(List.of(new BeehiveDecorator(0.02f))).build());

        register(context, AZALEA_BUSH, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                 BlockStateProvider.simple(Blocks.FLOWERING_AZALEA_LEAVES.defaultBlockState()
                         .setValue(LeavesBlock.PERSISTENT, true).setValue(LeavesBlock.DISTANCE, 3)),
                new StraightTrunkPlacer(1, 0, 0),
                BlockStateProvider.simple(Blocks.FLOWERING_AZALEA_LEAVES.defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true).setValue(LeavesBlock.DISTANCE, 3)),
                new BushFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0), 1),
                new TwoLayersFeatureSize(0, 1, 1, OptionalInt.of(0))
        ).dirt(BlockStateProvider.simple(Blocks.MOSS_BLOCK)).build());

        // endregion

        // region [RANDOM_SELECTORS]
        register(context, POINTED_DRIPSTONE, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(
                        Feature.BLOCK_COLUMN, new BlockColumnConfiguration(List.of(BlockColumnConfiguration.layer(
                                        ConstantInt.of(1),
                                        BlockStateProvider.simple(Blocks.POINTED_DRIPSTONE.defaultBlockState().
                                                setValue(PointedDripstoneBlock.THICKNESS, DripstoneThickness.FRUSTUM))),
                                BlockColumnConfiguration.layer(
                                        ConstantInt.of(1),
                                        BlockStateProvider.simple(Blocks.POINTED_DRIPSTONE))), Direction.UP,
                                BlockPredicate.matchesBlocks(Blocks.AIR), true)), 0.3f),
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(
                        Feature.BLOCK_COLUMN, new BlockColumnConfiguration(List.of(BlockColumnConfiguration.layer(
                                        ConstantInt.of(1),
                                        BlockStateProvider.simple(Blocks.POINTED_DRIPSTONE.defaultBlockState().
                                                setValue(PointedDripstoneBlock.THICKNESS, DripstoneThickness.MIDDLE))),
                                BlockColumnConfiguration.layer(
                                        ConstantInt.of(1),
                                        BlockStateProvider.simple(Blocks.POINTED_DRIPSTONE.defaultBlockState().
                                                setValue(PointedDripstoneBlock.THICKNESS, DripstoneThickness.FRUSTUM))),
                                BlockColumnConfiguration.layer(
                                        ConstantInt.of(1),
                                        BlockStateProvider.simple(Blocks.POINTED_DRIPSTONE))), Direction.UP,
                                BlockPredicate.matchesBlocks(Blocks.AIR), true)), 0.08f)),
                PlacementUtils.inlinePlaced(
                Feature.BLOCK_COLUMN, new BlockColumnConfiguration(List.of(BlockColumnConfiguration.layer(
                                ConstantInt.of(1),
                                BlockStateProvider.simple(Blocks.POINTED_DRIPSTONE))), Direction.UP,
                                BlockPredicate.matchesBlocks(Blocks.AIR), true))));

        register(context, TREES, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(holdergetter.getOrThrow(CHERRY_TREE)), 0.2f)
        ), PlacementUtils.inlinePlaced(holdergetter.getOrThrow(LOLLIPOP_TREE))));

        register(context, FLOOR, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(
                new WeightedPlacedFeature(PlacementUtils.inlinePlaced(holdergetter.getOrThrow(MUDDY_ROOTS_ORE)), 0.35f)
        ), PlacementUtils.inlinePlaced(holdergetter.getOrThrow(MOSS_PATCH))));
        // endregion

        // region[VEGETATION_PATCH]
        register(context, MOSS_PATCH, Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(
                TagLoader.Blocks.UNDERGROUND_JUNGLE_REPLACEABLE,
                BlockStateProvider.simple(Blocks.MOSS_BLOCK),
                PlacementUtils.inlinePlaced(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.MOSS_CARPET))),
                CaveSurface.FLOOR, ConstantInt.of(1), 0f, 2, 0.5f, UniformInt.of(1, 3),
                0.4f));
        // endregion

        // region[ORE]
        register(context, MUDDY_ROOTS_ORE, Feature.ORE, new OreConfiguration(List.of(target), 16, 0));
        // endregion

        // region[SIMPLE_BLOCK]
        register(context, LOW_FOLIAGE, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                        .add(Blocks.FERN.defaultBlockState(), 8)
                        .add(Blocks.SHORT_GRASS.defaultBlockState(), 8)
                        .add(Blocks.RED_MUSHROOM.defaultBlockState(), 12)
                        .add(Blocks.BROWN_MUSHROOM.defaultBlockState(), 12)
                        .add(Blocks.LARGE_FERN.defaultBlockState(), 2)
                        .add(Blocks.TALL_GRASS.defaultBlockState(), 2)
                        .add(Blocks.MOSS_CARPET.defaultBlockState(), 12)
                        .add(Blocks.AZALEA.defaultBlockState(), 4)
                        .add(Blocks.FLOWERING_AZALEA.defaultBlockState(), 4)
                        .add(Blocks.PEONY.defaultBlockState(), 1)
                        .add(Blocks.ROSE_BUSH.defaultBlockState(), 1)
                        .add(Blocks.PITCHER_PLANT.defaultBlockState(), 1)
                        .add(Blocks.TORCHFLOWER.defaultBlockState(), 2).build())));
        // endregion

        // region[MULTIFACE_GROWTH]
        register(context, LICHEN, Feature.MULTIFACE_GROWTH,
                new MultifaceGrowthConfiguration(multifaceblock, 1, true,false, false,
                        0f,
                        HolderSet.direct(
                        Block::builtInRegistryHolder,
                        Blocks.MUD,
                        Blocks.MOSS_BLOCK,
                        Blocks.MUDDY_MANGROVE_ROOTS,
                        Blocks.DEEPSLATE,
                        Blocks.STONE
                )));
        // endregion
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "caves/jungle/" + name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext< ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature,
                                                                                          FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
