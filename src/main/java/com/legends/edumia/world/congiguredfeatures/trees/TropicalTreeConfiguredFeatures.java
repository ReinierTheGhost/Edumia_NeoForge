package com.legends.edumia.world.congiguredfeatures.trees;


import com.google.common.collect.ImmutableList;
import com.legends.edumia.Edumia;
import com.legends.edumia.core.blocksets.WoodBlockSets;
import com.legends.edumia.world.trees.foliageplacer.*;
import com.legends.edumia.world.trees.trunkplacers.*;
import com.legends.edumia.world.placedfeatures.trees.TropicalTreePlacedFeatures;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.TreePlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaJungleFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.RandomSpreadFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.CocoaDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TrunkVineDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.*;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import java.util.List;

public class TropicalTreeConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> BENDING_JUNGLE_TREE = registerKey("bending_jungle_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CANOPY_JUNGLE_TREE = registerKey("canopy_jungle_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_JUNGLE_TREE = registerKey("large_jungle_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEGA_JUNGLE_TREE = registerKey("mega_jungle_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEGA_JUNGLE_TREE_2 = registerKey("mega_jungle_tree_2");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEGA_JUNGLE_TREE_3 = registerKey("mega_jungle_tree_3");




    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_PALM_TREE = registerKey("small_palm_tree");

    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_PARASOL_PALM = registerKey("big_parasol_palm");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_PARASOL_PALM = registerKey("small_parasol_palm");
    public static final ResourceKey<ConfiguredFeature<?, ?>> RANDOM_PALM = registerKey("random_palm");
    public static final ResourceKey<ConfiguredFeature<?, ?>> COCONUT_PALM = registerKey("coconut_palm");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PAPAYA_PALM = registerKey("papaya_palm");

    public static final ResourceKey<ConfiguredFeature<?, ?>> BANANA = registerKey("banana");

    public static final ResourceKey<ConfiguredFeature<?, ?>> MANGO = registerKey("mango");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MANGO_TWO = registerKey("mango_two");

    public static final ResourceKey<ConfiguredFeature<?, ?>> GENSAI_JUNGLE_TREES = registerKey("gensai_jungle_trees");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
        HolderGetter<ConfiguredFeature<?, ?>> holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);
        HolderGetter<PlacedFeature> registryEntryLookup = context.lookup(Registries.PLACED_FEATURE);
        Holder.Reference<PlacedFeature> jungle_bush = registryEntryLookup.getOrThrow(TreePlacements.JUNGLE_BUSH);
        Holder.Reference<PlacedFeature> jungle = registryEntryLookup.getOrThrow(TreePlacements.JUNGLE_TREE_CHECKED);
        Holder.Reference<PlacedFeature> mega_jungle_checked = registryEntryLookup.getOrThrow(TreePlacements.MEGA_JUNGLE_TREE_CHECKED);
        Holder.Reference<PlacedFeature> bending_jungle = registryEntryLookup.getOrThrow(TropicalTreePlacedFeatures.BENDING_JUNGLE_TREE);
        Holder.Reference<PlacedFeature> canopy_jungle = registryEntryLookup.getOrThrow(TropicalTreePlacedFeatures.CANOPY_JUNGLE_TREE);
        Holder.Reference<PlacedFeature> large_jungle = registryEntryLookup.getOrThrow(TropicalTreePlacedFeatures.LARGE_JUNGLE_TREE);
        Holder.Reference<PlacedFeature> mega_jungle = registryEntryLookup.getOrThrow(TropicalTreePlacedFeatures.MEGA_JUNGLE_TREE);

        Holder.Reference<PlacedFeature> mahogany = registryEntryLookup.getOrThrow(TropicalTreePlacedFeatures.MAHOGANY);
        Holder.Reference<PlacedFeature> big_mahogany = registryEntryLookup.getOrThrow(TropicalTreePlacedFeatures.BIG_MAHOGANY);
        Holder.Reference<PlacedFeature> canopy_mahogany = registryEntryLookup.getOrThrow(TropicalTreePlacedFeatures.CANOPY_MAHOGANY_TREE);
        Holder.Reference<PlacedFeature> large_mahogany = registryEntryLookup.getOrThrow(TropicalTreePlacedFeatures.LARGE_MAHOGANY_TREE);

        Holder.Reference<PlacedFeature> big_parasol = registryEntryLookup.getOrThrow(TropicalTreePlacedFeatures.BIG_PARASOL_PALM);
        Holder.Reference<PlacedFeature> small_parasol = registryEntryLookup.getOrThrow(TropicalTreePlacedFeatures.SMALL_PARASOL_PALM);
        Holder.Reference<PlacedFeature> random_palm = registryEntryLookup.getOrThrow(TropicalTreePlacedFeatures.RANDOM_PALM);
        Holder.Reference<PlacedFeature> coconut_palm = registryEntryLookup.getOrThrow(TropicalTreePlacedFeatures.COCONUT_PALM);
        Holder.Reference<PlacedFeature> papaya_palm = registryEntryLookup.getOrThrow(TropicalTreePlacedFeatures.PAPAYA_PALM);


        Holder.Reference<PlacedFeature> banana = registryEntryLookup.getOrThrow(TropicalTreePlacedFeatures.BANANA);

        Holder.Reference<PlacedFeature> mango = registryEntryLookup.getOrThrow(TropicalTreePlacedFeatures.MANGO);
        Holder.Reference<PlacedFeature> mango_two = registryEntryLookup.getOrThrow(TropicalTreePlacedFeatures.MANGO_TWO);





        register(context, BENDING_JUNGLE_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                new BendingTrunkPlacer(8, 6, 0, 8, UniformInt.of(1, 4)),
                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES),
                new RandomSpreadFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0),
                        ConstantInt.of(2),32),
                new TwoLayersFeatureSize(1, 0, 1))
                .decorators(ImmutableList.of(TrunkVineDecorator.INSTANCE, new LeaveVineDecorator(0.25f),
                        new CocoaDecorator(0.2f)))
                .ignoreVines().build());

        register(context, CANOPY_JUNGLE_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                new CanopyTrunkPlacer(12, 10, 2f, 0.87f, 5.0f, 3, 0.42f, -0.1f, 1,1),
                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES),
                new MegaJungleFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), 3),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(ImmutableList.of(TrunkVineDecorator.INSTANCE, new LeaveVineDecorator(0.25f)))
                .ignoreVines().build());

        register(context, LARGE_JUNGLE_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                new LargeTrunkPlacer(27, 3, 2.3f, 0.6f, 6.2f, 5, 0.25f),
                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES),
                new MegaJungleFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), 3),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(ImmutableList.of(TrunkVineDecorator.INSTANCE, new LeaveVineDecorator(0.25f)))
                .ignoreVines().build());

        register(context, MEGA_JUNGLE_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                new LargeTrunkPlacer(70, 16, 2.3f, 0.6f, 6.2f, 5, 0.25f),
                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                new OvalFoliagePlacer(3, ConstantInt.of(0), ConstantInt.of(3), 0.5f),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(ImmutableList.of(TrunkVineDecorator.INSTANCE, new LeaveVineDecorator(0.25f)))
                .ignoreVines().build());

        register(context, MEGA_JUNGLE_TREE_2, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                new FancyTrunkPlacer(24, 18, 12),
                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState().setValue(LeavesBlock.DISTANCE, 6)),
                new MegaJungleFoliagePlacer(ConstantInt.of(4), ConstantInt.of(2), 3),
                new TwoLayersFeatureSize(2, 3, 3))
                .decorators(ImmutableList.of(TrunkVineDecorator.INSTANCE, new LeaveVineDecorator(0.25f)))
                .ignoreVines().build());

        register(context, MEGA_JUNGLE_TREE_3, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                new FancyTrunkPlacer(20, 10, 10),
                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES),
                new MegaJungleFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), 2),
                new TwoLayersFeatureSize(1, 1, 2))
                .decorators(ImmutableList.of(TrunkVineDecorator.INSTANCE, new LeaveVineDecorator(0.25f)))
                .ignoreVines().build());





        register(context, SMALL_PALM_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.PALM.log().get()),
                new PleodendronTrunkPlacer(9, 6, 7),
                BlockStateProvider.simple(WoodBlockSets.PALM.leaves().get()),
                new PleodendronFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 2),
                new TwoLayersFeatureSize(1, 0, 1))
                .dirt(BlockStateProvider.simple(Blocks.ROOTED_DIRT)).ignoreVines().build());


        register(context, BIG_PARASOL_PALM, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.PALM.log().get()),
                new SlantedTrunkPlacer(9, 6, 7),
                BlockStateProvider.simple(WoodBlockSets.PALM.leaves().get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                new LargeParasolPalmFoliagePlacer(),
                new TwoLayersFeatureSize(1, 0, 1))
                .dirt(BlockStateProvider.simple(Blocks.ROOTED_DIRT)).ignoreVines().build());

        register(context, SMALL_PARASOL_PALM, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.PALM.log().get()),
                new SlantedTrunkPlacer(6, 4, 5),
                BlockStateProvider.simple(WoodBlockSets.PALM.leaves().get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                new ParasolPalmFoliagePlacer(),
                new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());

        register(context, RANDOM_PALM, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.PALM.log().get()),
                new PalmTrunkPlacer(10, 2, 0.02f, 0.07f, 0),
                BlockStateProvider.simple(WoodBlockSets.PALM.leaves().get()),
                new PalmFoliagePlacer(4, ConstantInt.of(0), ConstantInt.of(1), -0.3f, 0.3f),
                new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());

        register(context, COCONUT_PALM, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.PALM.log().get()),
                new PalmTrunkPlacer(10, 2, 0.02f, 0.07f, 0),
                BlockStateProvider.simple(WoodBlockSets.PALM.leaves().get()),
                new CoconutFoliagePlacer( ConstantInt.of(4), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());

        register(context, PAPAYA_PALM, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.PALM.log().get()),
                new PalmTrunkPlacer(10, 2, 0.02f, 0.07f, 0),
                BlockStateProvider.simple(WoodBlockSets.PALM.leaves().get()),
                new PapayaFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());

        register(context, BANANA, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.BANANA.log().get()),
                new StraightTrunkPlacer(4, 1, 0),
                BlockStateProvider.simple(WoodBlockSets.BANANA.leaves().get()),
                new BananaFoliagePlacer(0f, 0f),
                new TwoLayersFeatureSize(0, 1, 0)).build());

        register(context, MANGO, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.MANGO.log().get()),
                new StraightTrunkPlacer(4, 2, 0),
                BlockStateProvider.simple(WoodBlockSets.MANGO.leaves().get()),
                new TropicalFruitFoliagePlacer(),
                new TwoLayersFeatureSize(0, 1, 0)).build());

        register(context, MANGO_TWO, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.MANGO.log().get()),
                new DesertTrunkPlacer(4, 2, 0, WoodBlockSets.MANGO.wood().get().defaultBlockState()),
                BlockStateProvider.simple(WoodBlockSets.MANGO.leaves().get()),
                new DesertFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 2),
                new TwoLayersFeatureSize(0, 1, 0)).build());


        register(context, GENSAI_JUNGLE_TREES, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(
                new WeightedPlacedFeature(mega_jungle_checked, 0.07f), new WeightedPlacedFeature(large_jungle, 0.08f),
                new WeightedPlacedFeature(canopy_jungle, 0.02f), new WeightedPlacedFeature(canopy_mahogany, 0.04f),
                new WeightedPlacedFeature(large_mahogany, 0.4f), new WeightedPlacedFeature(bending_jungle, 0.05f),
                new WeightedPlacedFeature(mega_jungle, 0.01f), new WeightedPlacedFeature(big_mahogany, 0.08f),
                new WeightedPlacedFeature(mahogany, 0.08f), new WeightedPlacedFeature(banana, 0.09f),
                new WeightedPlacedFeature(big_parasol, 0.03f), new WeightedPlacedFeature(small_parasol, 0.07f),
                new WeightedPlacedFeature(random_palm, 0.04f), new WeightedPlacedFeature(coconut_palm, 0.04f),
                new WeightedPlacedFeature(papaya_palm, 0.04f), new WeightedPlacedFeature(mango, 0.05F),
                new WeightedPlacedFeature(mango_two, 0.09f)), jungle_bush));

    }


    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "tree/tropical/" + name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext< ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature,
                                                                                          FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
