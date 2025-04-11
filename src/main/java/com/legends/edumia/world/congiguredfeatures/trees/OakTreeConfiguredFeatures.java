package com.legends.edumia.world.congiguredfeatures.trees;

import com.google.common.collect.ImmutableList;
import com.legends.edumia.Edumia;
import com.legends.edumia.blocks.blocksets.ModNatureBlocks;
import com.legends.edumia.blocks.blocksets.WoodBlockSets;
import com.legends.edumia.world.trees.foliageplacer.ClusterFoliagePlacer;
import com.legends.edumia.world.trees.foliageplacer.OakFoliagePlacer;
import com.legends.edumia.world.trees.foliageplacer.OvalFoliagePlacer;
import com.legends.edumia.world.trees.trunkplacers.CanopyTrunkPlacer;
import com.legends.edumia.world.trees.trunkplacers.EdumiaGiantTrunkPlacer;
import com.legends.edumia.world.trees.trunkplacers.LargeTrunkPlacer;
import com.legends.edumia.world.trees.trunkplacers.OakTrunkPlacer;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BushFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class OakTreeConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> GREEN_OAK_KEY = registerKey("green/green_oak_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_OAK_KEY = registerKey("red/red_oak_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACK_OAK_KEY = registerKey("black/black_oak_tree");

    public static final ResourceKey<ConfiguredFeature<?, ?>> OAK_BUSH_TREE_KEY = registerKey("oak/oak_bush_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OAK_TREE_KEY = registerKey("oak/oak_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OAK_TREE_VINES_KEY = registerKey("oak/oak_vines_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEGA_OAK_TREE_KEY = registerKey("oak/mega_oak_tree");

    public static final ResourceKey<ConfiguredFeature<?, ?>> MEGA_DARK_OAK_TREE_KEY = registerKey("dark/mega_dark_oak_tree");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_RED_OAK_TREE_KEY = registerKey("red/small_red_oak_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_RED_OAK_TREE_KEY = registerKey("red/large_red_oak_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEGA_RED_OAK_TREE_KEY = registerKey("red/mega_red_oak_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> COLOSSAL_RED_OAK_TREE_KEY = registerKey("red/colossal_red_oak_tree");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_GREEN_OAK_TREE_KEY = registerKey("green/small_green_oak_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_GREEN_OAK_TREE_KEY = registerKey("green/large_green_oak_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEGA_GREEN_OAK_TREE_KEY = registerKey("green/mega_green_oak_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> COLOSSAL_GREEN_OAK_TREE_KEY = registerKey("green/colossal_green_oak_tree");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_BLACK_OAK_TREE_KEY = registerKey("black/small_black_oak_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_BLACK_OAK_TREE_KEY = registerKey("black/large_black_oak_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEGA_BLACK_OAK_TREE_KEY = registerKey("black/mega_black_oak_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> COLOSSAL_BLACK_OAK_TREE_KEY = registerKey("black/colossal_black_oak_tree");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context){
        HolderGetter<PlacedFeature> registryEntryLookup = context.lookup(Registries.PLACED_FEATURE);

        register(context, RED_OAK_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.RED_OAK.log().get()),
                new OakTrunkPlacer(4, 4, 0, WoodBlockSets.RED_OAK.wood().get().defaultBlockState(),
                        WoodBlockSets.RED_OAK.woodWall().get().defaultBlockState()),
                BlockStateProvider.simple(WoodBlockSets.RED_OAK.leaves().get()),
                new OakFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 3),

                new TwoLayersFeatureSize(1, 0, 2)).ignoreVines().build());

        register(context, BLACK_OAK_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.BLACK_OAK.log().get()),
                new OakTrunkPlacer(4, 4, 0, WoodBlockSets.BLACK_OAK.wood().get().defaultBlockState(),
                        WoodBlockSets.BLACK_OAK.woodWall().get().defaultBlockState()),
                BlockStateProvider.simple(ModNatureBlocks.BLACK_OAK_LEAVES.get()),
                new OakFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(1, 0, 2)).ignoreVines().build());

        register(context, GREEN_OAK_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.GREEN_OAK.log().get()),
                new OakTrunkPlacer(4, 4, 0, WoodBlockSets.GREEN_OAK.wood().get().defaultBlockState(),
                        WoodBlockSets.GREEN_OAK.woodWall().get().defaultBlockState()),
                BlockStateProvider.simple(WoodBlockSets.GREEN_OAK.leaves().get()),
                new OakFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 3),

                new TwoLayersFeatureSize(1, 0, 2)).ignoreVines().build());

        register(context, MEGA_DARK_OAK_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.DARK_OAK_LOG),
                new CanopyTrunkPlacer(21, 3, 1.8f, 0.55f, 6.1f, 3, 0.44f, -0.15f, 2, 0),
                BlockStateProvider.simple(Blocks.DARK_OAK_LEAVES),
                new OvalFoliagePlacer(2, ConstantInt.of(0), ConstantInt.of(3), 0.4f),
                new TwoLayersFeatureSize(1, 0, 2))
                .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());

        register(context, SMALL_RED_OAK_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.RED_OAK.wood().get()),
                new CanopyTrunkPlacer(7, 2, 0.9f, 0.87f, 3.2f, 1, 0.28f, -0.15f, 0, 0),
                BlockStateProvider.simple(WoodBlockSets.RED_OAK.leaves().get()),
                new OvalFoliagePlacer(2, ConstantInt.of(-1), ConstantInt.of(2), 0.3f),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(ImmutableList.of(new LeaveVineDecorator(0.1F)))
                .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());

        register(context, LARGE_RED_OAK_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.RED_OAK.wood().get()),
                new LargeTrunkPlacer(14, 2, 1.1f, 0.55f, 3.2f, 2, 0.28f),
                BlockStateProvider.simple(WoodBlockSets.RED_OAK.leaves().get()),
                new OvalFoliagePlacer(2, ConstantInt.of(-1), ConstantInt.of(3), 0.4f),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(ImmutableList.of(new LeaveVineDecorator(0.25F)))
                .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());

        register(context, MEGA_RED_OAK_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.RED_OAK.log().get()),
                new LargeTrunkPlacer(27, 3, 2.3f, 0.6f, 6.2f, 5, 0.25f),
                BlockStateProvider.simple(WoodBlockSets.RED_OAK.leaves().get()),
                new OvalFoliagePlacer(3, ConstantInt.of(-1), ConstantInt.of(4), 0.5f),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(ImmutableList.of(new LeaveVineDecorator(0.25F)))
                .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());

        register(context, COLOSSAL_RED_OAK_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.RED_OAK.log().get()),
                new EdumiaGiantTrunkPlacer(32, 24, 24, WoodBlockSets.RED_OAK.wood().get().defaultBlockState(),
                        WoodBlockSets.RED_OAK.strippedWood().get().defaultBlockState()),
                BlockStateProvider.simple(WoodBlockSets.RED_OAK.leaves().get()),
                new ClusterFoliagePlacer( ConstantInt.of(3), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(ImmutableList.of(new LeaveVineDecorator(0.25F)))
                .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());

        register(context, SMALL_GREEN_OAK_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.GREEN_OAK.wood().get()),
                new CanopyTrunkPlacer(7, 2, 0.9f, 0.87f, 3.2f, 1, 0.28f, -0.15f, 0, 0),
                BlockStateProvider.simple(WoodBlockSets.GREEN_OAK.leaves().get()),
                new OvalFoliagePlacer(2, ConstantInt.of(-1), ConstantInt.of(2), 0.3f),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(ImmutableList.of(new LeaveVineDecorator(0.1F)))
                .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());

        register(context, LARGE_GREEN_OAK_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.GREEN_OAK.wood().get()),
                new LargeTrunkPlacer(14, 2, 1.1f, 0.55f, 3.2f, 2, 0.28f),
                BlockStateProvider.simple(WoodBlockSets.GREEN_OAK.leaves().get()),
                new OvalFoliagePlacer(2, ConstantInt.of(-1), ConstantInt.of(3), 0.4f),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(ImmutableList.of(new LeaveVineDecorator(0.25F)))
                .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());

        register(context, MEGA_GREEN_OAK_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.GREEN_OAK.log().get()),
                new LargeTrunkPlacer(27, 3, 2.3f, 0.6f, 6.2f, 5, 0.25f),
                BlockStateProvider.simple(WoodBlockSets.GREEN_OAK.leaves().get()),
                new OvalFoliagePlacer(3, ConstantInt.of(-1), ConstantInt.of(4), 0.5f),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(ImmutableList.of(new LeaveVineDecorator(0.25F)))
                .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());

        register(context, COLOSSAL_GREEN_OAK_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.GREEN_OAK.log().get()),
                new EdumiaGiantTrunkPlacer(32, 24, 24, WoodBlockSets.GREEN_OAK.wood().get().defaultBlockState(),
                        WoodBlockSets.GREEN_OAK.strippedWood().get().defaultBlockState()),
                BlockStateProvider.simple(WoodBlockSets.GREEN_OAK.leaves().get()),
                new ClusterFoliagePlacer( ConstantInt.of(3), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(ImmutableList.of(new LeaveVineDecorator(0.25F)))
                .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());

        register(context, SMALL_BLACK_OAK_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.BLACK_OAK.wood().get()),
                new CanopyTrunkPlacer(7, 2, 0.9f, 0.87f, 3.2f, 1, 0.28f, -0.15f, 0, 0),
                BlockStateProvider.simple(ModNatureBlocks.BLACK_OAK_LEAVES.get()),
                new OvalFoliagePlacer(2, ConstantInt.of(-1), ConstantInt.of(2), 0.3f),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(ImmutableList.of(new LeaveVineDecorator(0.1F)))
                .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());

        register(context, LARGE_BLACK_OAK_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.BLACK_OAK.wood().get()),
                new LargeTrunkPlacer(14, 2, 1.1f, 0.55f, 3.2f, 2, 0.28f),
                BlockStateProvider.simple(ModNatureBlocks.BLACK_OAK_LEAVES.get()),
                new OvalFoliagePlacer(2, ConstantInt.of(-1), ConstantInt.of(3), 0.4f),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(ImmutableList.of(new LeaveVineDecorator(0.25F)))
                .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());

        register(context, MEGA_BLACK_OAK_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.BLACK_OAK.log().get()),
                new LargeTrunkPlacer(27, 3, 2.3f, 0.6f, 6.2f, 5, 0.25f),
                BlockStateProvider.simple(ModNatureBlocks.BLACK_OAK_LEAVES.get()),
                new OvalFoliagePlacer(3, ConstantInt.of(-1), ConstantInt.of(4), 0.5f),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(ImmutableList.of(new LeaveVineDecorator(0.25F)))
                .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());

        register(context, COLOSSAL_BLACK_OAK_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.BLACK_OAK.log().get()),
                new EdumiaGiantTrunkPlacer(32, 24, 24, WoodBlockSets.BLACK_OAK.wood().get().defaultBlockState(),
                        WoodBlockSets.BLACK_OAK.strippedWood().get().defaultBlockState()),
                BlockStateProvider.simple(ModNatureBlocks.BLACK_OAK_LEAVES.get()),
                new ClusterFoliagePlacer( ConstantInt.of(3), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(ImmutableList.of(new LeaveVineDecorator(0.25F)))
                .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());

        register(context, OAK_BUSH_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new StraightTrunkPlacer(1, 0, 0), BlockStateProvider.simple(Blocks.OAK_LEAVES),
                new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2),
                new TwoLayersFeatureSize(0, 0, 0)).build());


        register(context, OAK_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new CanopyTrunkPlacer(12, 2, 0.91f, 0.87f, 5.0f, 3, 0.42f, -0.1f, 1,1),
                BlockStateProvider.simple(Blocks.OAK_LEAVES),
                new OvalFoliagePlacer(2, ConstantInt.of(0), ConstantInt.of(3), 0.3f),
                new TwoLayersFeatureSize(1, 0, 2))
                .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());

        register(context, OAK_TREE_VINES_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new CanopyTrunkPlacer(10, 2, 0.91f, 0.87f, 5.0f, 3, 0.42f, -0.1f, 1,1),
                BlockStateProvider.simple(Blocks.OAK_LEAVES),
                new OvalFoliagePlacer(2, ConstantInt.of(0), ConstantInt.of(3), 0.3f),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(ImmutableList.of(new LeaveVineDecorator(0.25F)))
                .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());

        register(context, MEGA_OAK_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new CanopyTrunkPlacer(20, 3, 1.8f, 0.55f, 5.7f, 3, 0.38f, -0.15f, 2, 0),
                BlockStateProvider.simple(Blocks.OAK_LEAVES),
                new OvalFoliagePlacer(2, ConstantInt.of(0), ConstantInt.of(3), 0.4f),
                new TwoLayersFeatureSize(1, 0, 2))
                .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());

    }
    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Edumia.MOD_ID, "tree/oaks/" + name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext< ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature,
                                                                                          FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
