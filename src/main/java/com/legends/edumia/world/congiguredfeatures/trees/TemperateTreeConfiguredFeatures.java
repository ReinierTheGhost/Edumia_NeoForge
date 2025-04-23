package com.legends.edumia.world.congiguredfeatures.trees;

import com.google.common.collect.ImmutableList;
import com.legends.edumia.Edumia;

import com.legends.edumia.core.blocksets.ModNatureBlocks;
import com.legends.edumia.core.blocksets.WoodBlockSets;
import com.legends.edumia.world.trees.foliageplacer.*;
import com.legends.edumia.world.trees.trunkplacers.CanopyTrunkPlacer;
import com.legends.edumia.world.trees.trunkplacers.CederTrunkPlacer;
import com.legends.edumia.world.trees.trunkplacers.LargeTrunkPlacer;
import com.legends.edumia.world.placedfeatures.ModPlacedFeatures;
import com.legends.edumia.world.placedfeatures.trees.TemperateTreePlacedFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BushFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.PineFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;
import java.util.OptionalInt;

public class TemperateTreeConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> SAKURA_BUSH = registerKey("sakura/sakura_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SAKURA_TREE_MEDIUM = registerKey("sakura/sakura_tree_medium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SAKURA_TREE_BIG = registerKey("sakura/sakura_tree_big");

    public static final ResourceKey<ConfiguredFeature<?, ?>> AZALEA_BUSH = registerKey("azalea/azalea_bush");

    public static final ResourceKey<ConfiguredFeature<?, ?>> CEDER_KEY = registerKey("ceder/ceder_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_CEDER_KEY = registerKey("ceder/large_ceder_tree");

    public static final ResourceKey<ConfiguredFeature<?, ?>> CYPRESS_KEY = registerKey("cypress/cypress_tree");

    public static final ResourceKey<ConfiguredFeature<?, ?>> ASPEN_KEY = registerKey("aspen/aspen_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ASPEN_2_KEY = registerKey("aspen/aspen_2_tree");

    public static final ResourceKey<ConfiguredFeature<?, ?>> BIRCH_TREE_KEY = registerKey("birch/birch_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CLOUD_BIRCH_TREE_TALL_KEY = registerKey("birch/cloud_birch_tree_tall");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEGA_BIRCH_TREE_KEY = registerKey("birch/mega_birch_tree");


    public static final ResourceKey<ConfiguredFeature<?, ?>> WHITE_ASH_TREE_KEY = registerKey("white_ash/white_ash_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_WHITE_ASH_TREE_KEY = registerKey("white_ash/small_white_ash_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WHITE_ASH_BUSH_KEY = registerKey("white_ash/white_ash_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEGA_WHITE_ASH_TREE_KEY = registerKey("white_ash/mega_white_ashtree");

    public static final ResourceKey<ConfiguredFeature<?, ?>> MAPLE_TREE_KEY = registerKey("maple/maple_tree");

    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACKTHORN_TREE_KEY = registerKey("blackthorn/blackthorn_tree");

    public static final ResourceKey<ConfiguredFeature<?, ?>> HOLLY_TREE_KEY = registerKey("holly/holly_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HOLLY_BEES_TREE_KEY = registerKey("holly/holly_tree_bees");

    public static final ResourceKey<ConfiguredFeature<?, ?>> WILLOW_TREE_KEY = registerKey("willow/willow_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GENSAI_SAKURA_GROVE_TREES = registerKey("sakura/gensai_sakura_grove_trees");


    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
        HolderGetter<PlacedFeature> placed = context.lookup(Registries.PLACED_FEATURE);
        BeehiveDecorator beehive03TreeDecorator = new BeehiveDecorator(0.03f);
        BeehiveDecorator beehive05TreeDecorator = new BeehiveDecorator(0.05f);

        Holder.Reference<PlacedFeature> noting = placed.getOrThrow(ModPlacedFeatures.NOTING);

        Holder.Reference<PlacedFeature> sakura_bush = placed.getOrThrow(TemperateTreePlacedFeatures.SAKURA_BUSH);
        Holder.Reference<PlacedFeature> sakura_medium_tree = placed.getOrThrow(TemperateTreePlacedFeatures.SAKURA_TREE_MEDIUM);
        Holder.Reference<PlacedFeature> sakura_big_tree = placed.getOrThrow(TemperateTreePlacedFeatures.SAKURA_TREE_BIG);
        Holder.Reference<PlacedFeature> azalea_bush = placed.getOrThrow(TemperateTreePlacedFeatures.AZALEA_BUSH);
        Holder.Reference<PlacedFeature> ceder = placed.getOrThrow(TemperateTreePlacedFeatures.CEDER_KEY);
        Holder.Reference<PlacedFeature> ceder_big = placed.getOrThrow(TemperateTreePlacedFeatures.LARGE_CEDER_KEY);
        Holder.Reference<PlacedFeature> cypress = placed.getOrThrow(TemperateTreePlacedFeatures.CYPRESS_KEY);
        Holder.Reference<PlacedFeature> aspen = placed.getOrThrow(TemperateTreePlacedFeatures.ASPEN_KEY);
        Holder.Reference<PlacedFeature> aspen2 = placed.getOrThrow(TemperateTreePlacedFeatures.ASPEN_2_KEY);

        register(context, BIRCH_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.BIRCH_LOG),
                new CanopyTrunkPlacer(15, 2, 0.95f, 0.9f, 4.3f, 3, 0.37f,  0.025f,1,1),
                BlockStateProvider.simple(Blocks.BIRCH_LEAVES),
                new OvalFoliagePlacer(3, ConstantInt.of(0), ConstantInt.of(2), 0.4f),
                new TwoLayersFeatureSize(1, 0, 2))
                .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());

        register(context, CLOUD_BIRCH_TREE_TALL_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.BIRCH_LOG),
                new ForkingTrunkPlacer(5, 4, 8),
                BlockStateProvider.simple(Blocks.BIRCH_LEAVES),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(2), 2),
                new TwoLayersFeatureSize(1, 0, 2))
                .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK))
                .decorators(List.of(beehive03TreeDecorator)).build());

        register(context, MEGA_BIRCH_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.BIRCH_LOG),
                new CanopyTrunkPlacer(18, 3, 1.0f, 0.67f, 5.2f, 3, 0.44f, -0.05f, 2, 1),
                BlockStateProvider.simple(Blocks.BIRCH_LEAVES),
                new OvalFoliagePlacer(2, ConstantInt.of(0), ConstantInt.of(3), 0.4f),
                new TwoLayersFeatureSize(1, 0, 2))
                .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());

        register(context, WHITE_ASH_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.WHITE_ASH.log().get()),
                new CanopyTrunkPlacer(16, 2, 0.9f, 0.87f, 5.2f, 3, 0.45f, -0.15f, 0,1),
                BlockStateProvider.simple(WoodBlockSets.WHITE_ASH.leaves().get()),
                new OvalFoliagePlacer(2, ConstantInt.of(0), ConstantInt.of(3), 0.4f),
                new TwoLayersFeatureSize(1, 0, 2))
                .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());

        register(context, SMALL_WHITE_ASH_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.WHITE_ASH.log().get()),
                new CanopyTrunkPlacer(9, 2, 0.9f, 0.87f, 5.2f, 2, 0.45f, -0.15f, 0,1),
                BlockStateProvider.simple(WoodBlockSets.WHITE_ASH.leaves().get()),
                new OvalFoliagePlacer(2, ConstantInt.of(0), ConstantInt.of(3), 0.4f),
                new TwoLayersFeatureSize(1, 0, 2))
                .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());

        register(context, MEGA_WHITE_ASH_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.WHITE_ASH.log().get()),
                new CanopyTrunkPlacer(34, 3, 1.6f, 0.56f, 8.3f, 4, 0.48f, 0f, 2,1),
                BlockStateProvider.simple(WoodBlockSets.WHITE_ASH.leaves().get()),
                new OvalFoliagePlacer(3, ConstantInt.of(-1), ConstantInt.of(4), 0.7f),
                new TwoLayersFeatureSize(1, 0, 2))
                .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());

        register(context, WHITE_ASH_BUSH_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.WHITE_ASH.log().get()),
                new StraightTrunkPlacer(1, 0, 0), BlockStateProvider.simple(WoodBlockSets.WHITE_ASH.leaves().get()),
                new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2),
                new TwoLayersFeatureSize(0, 0, 0)).build());

        register(context, MAPLE_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.MAPLE.log().get()),
                new CanopyTrunkPlacer(11, 2, 0.91f, 0.87f, 5.1f, 2, 0.37f, -0.1f, 1,1),
                BlockStateProvider.simple(ModNatureBlocks.MAPLE_LEAVES.get()),
                new OvalFoliagePlacer(2, ConstantInt.of(0), ConstantInt.of(3), 0.3f),
                new TwoLayersFeatureSize(1, 0, 2))
                .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());

        register(context, BLACKTHORN_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.BLACKTHORN.log().get()),
                new CanopyTrunkPlacer(10, 2, 0.9f, 0.87f, 8.6f, 3, 0.42f, -0.15f, 0,1),
                BlockStateProvider.simple(WoodBlockSets.BLACKTHORN.leaves().get()),
                new OvalFoliagePlacer(2, ConstantInt.of(0), ConstantInt.of(2), 0.4f),
                new TwoLayersFeatureSize(1, 0, 2))
                .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());

        register(context, SAKURA_BUSH, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.CHERRY_LOG),
                new StraightTrunkPlacer(1, 0, 0),
                BlockStateProvider.simple(Blocks.CHERRY_LEAVES),
                new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2),
                new TwoLayersFeatureSize(0, 0, 0)
        ).forceDirt().build());

        register(context, SAKURA_TREE_MEDIUM, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.CHERRY_LOG),
                new FancyTrunkPlacer(3, 3, 6),
                BlockStateProvider.simple(Blocks.CHERRY_LEAVES),
                new FancyFoliagePlacer(ConstantInt.of(3), ConstantInt.of(4), 4),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(16))
        ).forceDirt().build());

        register(context, SAKURA_TREE_BIG, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.CHERRY_LOG),
                new FancyTrunkPlacer(12, 6, 10),
                BlockStateProvider.simple(Blocks.CHERRY_LEAVES),
                new FancyFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), 4),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(16))
        ).forceDirt().ignoreVines().decorators(List.of(beehive05TreeDecorator)).build());

        register(context, AZALEA_BUSH, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new StraightTrunkPlacer(1, 0, 0),
                new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.AZALEA_LEAVES.defaultBlockState(), 5)
                        .add(Blocks.FLOWERING_AZALEA_LEAVES.defaultBlockState(), 2)),
                new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2),
                new TwoLayersFeatureSize(1, 0, 1)
        ).forceDirt().build());

        register(context, LARGE_CEDER_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.CEDAR.log().get()),
                new CederTrunkPlacer(15, 15, 0, WoodBlockSets.CEDAR.wood().get().defaultBlockState(),
                        WoodBlockSets.CEDAR.woodWall().get().defaultBlockState()),

                BlockStateProvider.simple(WoodBlockSets.CEDAR.leaves().get()),
                new CederFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),

                new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());

        register(context, CEDER_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.CEDAR.log().get()),
                new CederTrunkPlacer(10, 6, 0, WoodBlockSets.CEDAR.wood().get().defaultBlockState(),
                        WoodBlockSets.CEDAR.woodWall().get().defaultBlockState()),
                BlockStateProvider.simple(WoodBlockSets.CEDAR.leaves().get()),
                new CederFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());

        register(context, ASPEN_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.ASPEN.log().get()),
                new StraightTrunkPlacer(8, 7, 0),
                BlockStateProvider.simple(WoodBlockSets.ASPEN.leaves().get()),
                new AspenFoliagePlacer(ConstantInt.of(2), ConstantInt.of(2), ConstantInt.of(2)),
                new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());

        register(context, ASPEN_2_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.ASPEN.log().get()),
                new StraightTrunkPlacer(4, 3, 6),
                new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(WoodBlockSets.ASPEN.leaves().get().defaultBlockState(), 4)
                        .add(Blocks.AIR.defaultBlockState(), 1)),
                new PineFoliagePlacer(UniformInt.of(2, 3), ConstantInt.of(1), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());

        register(context, CYPRESS_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.CYPRESS.log().get()),
                new StraightTrunkPlacer(8, 5, 0),
                BlockStateProvider.simple(WoodBlockSets.CYPRESS.leaves().get()),
                new CypressFoliagePlacer(UniformInt.of(1, 2), ConstantInt.of(1), UniformInt.of(1, 3)),
                new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());

        register(context, GENSAI_SAKURA_GROVE_TREES, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(
                new WeightedPlacedFeature(sakura_big_tree, 0.02f), new WeightedPlacedFeature(sakura_medium_tree, 0.08f),
                new WeightedPlacedFeature(sakura_bush, 0.4f), new WeightedPlacedFeature(azalea_bush, 0.3f),
                new WeightedPlacedFeature(ceder, 0.06f), new WeightedPlacedFeature(ceder_big, 0.04f),
                new WeightedPlacedFeature(cypress, 0.07f), new WeightedPlacedFeature(aspen, 0.07f),
                new WeightedPlacedFeature(aspen2, 0.04f)), noting));

        register(context, HOLLY_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.HOLLY.log().get()),
                new StraightTrunkPlacer(9, 5, 0),
                BlockStateProvider.simple(ModNatureBlocks.HOLLY_LEAVES.get()),
                new HollyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(2), UniformInt.of(1, 3)),
                new TwoLayersFeatureSize(1, 0, 1))
                .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());

        register(context, HOLLY_BEES_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.HOLLY.log().get()),
                new StraightTrunkPlacer(9, 5, 0),
                BlockStateProvider.simple(ModNatureBlocks.HOLLY_LEAVES.get()),
                new HollyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(2), UniformInt.of(1, 3)),
                new TwoLayersFeatureSize(1, 0, 1))
                .decorators(ImmutableList.of(new BeehiveDecorator(0.05f)))
                .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());


        register(context, WILLOW_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.WILLOW.log().get()),
                new LargeTrunkPlacer(13, 2, 1.2f, 0.67f, 6.0f, 3, 0.32f),
                BlockStateProvider.simple(WoodBlockSets.WILLOW.leaves().get()),
                new OvalFoliagePlacer(2, ConstantInt.of(-1), ConstantInt.of(3), 0.4f),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(ImmutableList.of(new LeaveVineDecorator(0.35F)))
                .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());


    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "tree/temperate/" + name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext< ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature,
                                                                                          FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

}
