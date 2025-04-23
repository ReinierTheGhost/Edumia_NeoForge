package com.legends.edumia.world.congiguredfeatures.trees;

import com.legends.edumia.Edumia;
import com.legends.edumia.core.blocksets.ModNatureBlocks;
import com.legends.edumia.core.blocksets.WoodBlockSets;
import com.legends.edumia.world.congiguredfeatures.TreeConfiguredFeatures;
import com.legends.edumia.world.trees.foliageplacer.AlmondFoliagePlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

public class FruitTreeConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> ALMOND_KEY = registerKey("almond/almond_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> APPLE_KEY = registerKey("apple/apple_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_APPLE_KEY = registerKey("apple/red_apple_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GREEN_APPLE_KEY = registerKey("apple/green_apple_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MIXED_APPLE_KEY = registerKey("apple/mixed_apple_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> APPLE_BEES_KEY = registerKey("apple/apple_tree_bees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_APPLE_BEES_KEY = registerKey("apple/red_apple_tree_bees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GREEN_APPLE_BEES_KEY = registerKey("apple/green_apple_tree_bees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MIXED_APPLE_BEES_KEY = registerKey("apple/mixed_apple_tree_bees");
    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
        register(context, APPLE_KEY, Feature.TREE, TreeConfiguredFeatures.buildClassicTree(
                WoodBlockSets.APPLE.log().get().defaultBlockState(),
                WoodBlockSets.APPLE.leaves().get().defaultBlockState(), 4, 3));

        register(context, RED_APPLE_KEY, Feature.TREE, TreeConfiguredFeatures.buildClassicTree(
                WoodBlockSets.APPLE.log().get().defaultBlockState(),
                ModNatureBlocks.APPLE_LEAVES_RED.get().defaultBlockState(), 4, 3));

        register(context, GREEN_APPLE_KEY, Feature.TREE, TreeConfiguredFeatures.buildClassicTree(
                WoodBlockSets.APPLE.log().get().defaultBlockState(),
                ModNatureBlocks.APPLE_LEAVES_GREEN.get().defaultBlockState(), 4, 3));

        register(context, MIXED_APPLE_KEY, Feature.TREE, TreeConfiguredFeatures.buildClassicTree(
                WoodBlockSets.APPLE.log().get().defaultBlockState(),
                new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                        .add(WoodBlockSets.APPLE.leaves().get().defaultBlockState(), 30)
                        .add(ModNatureBlocks.APPLE_LEAVES_GREEN.get().defaultBlockState(), 1)
                        .add(ModNatureBlocks.APPLE_LEAVES_RED.get().defaultBlockState(), 1)
                ), 4, 3));

        register(context, APPLE_BEES_KEY, Feature.TREE, TreeConfiguredFeatures.buildClassicTreeWithBees(
                WoodBlockSets.APPLE.log().get().defaultBlockState(),
                WoodBlockSets.APPLE.leaves().get().defaultBlockState(), 4, 3));

        register(context, RED_APPLE_BEES_KEY, Feature.TREE, TreeConfiguredFeatures.buildClassicTreeWithBees(
                WoodBlockSets.APPLE.log().get().defaultBlockState(),
                ModNatureBlocks.APPLE_LEAVES_RED.get().defaultBlockState(), 4, 3));

        register(context, GREEN_APPLE_BEES_KEY, Feature.TREE, TreeConfiguredFeatures.buildClassicTreeWithBees(
                WoodBlockSets.APPLE.log().get().defaultBlockState(),
                ModNatureBlocks.APPLE_LEAVES_GREEN.get().defaultBlockState(), 4, 3));

        register(context, MIXED_APPLE_BEES_KEY, Feature.TREE, TreeConfiguredFeatures.buildClassicTreeWithBees(
                WoodBlockSets.APPLE.log().get().defaultBlockState(),
                new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                        .add(WoodBlockSets.APPLE.leaves().get().defaultBlockState(), 30)
                        .add(ModNatureBlocks.APPLE_LEAVES_GREEN.get().defaultBlockState(), 1)
                        .add(ModNatureBlocks.APPLE_LEAVES_RED.get().defaultBlockState(), 1)
                ), 4, 3));

        register(context, ALMOND_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.ALMOND.log().get()),
                new StraightTrunkPlacer(4, 3, 2),
                BlockStateProvider.simple(WoodBlockSets.ALMOND.leaves().get()),
                new AlmondFoliagePlacer(),

                new TwoLayersFeatureSize(1, 0, 2)).build());
    }
    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID,"tree/fruit_trees/" + name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext< ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature,
                                                                                          FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
