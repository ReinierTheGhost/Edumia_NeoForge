package com.legends.edumia.world.congiguredfeatures.trees;

import com.legends.edumia.Edumia;
import com.legends.edumia.blocks.blocksets.ModNatureBlocks;
import com.legends.edumia.blocks.blocksets.WoodBlockSets;
import com.legends.edumia.world.congiguredfeatures.TreeConfiguredFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;

public class FruitTreeConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> APPLE_KEY = registerKey("apple/apple_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_APPLE_KEY = registerKey("apple/red_apple_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GREEN_APPLE_KEY = registerKey("apple/green_apple_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MIXED_APPLE_KEY = registerKey("apple/mixed_apple_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> APPLE_BEES_KEY = registerKey("apple/apple_tree_bees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_APPLE_BEES_KEY = registerKey("apple/red_apple_tree_bees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GREEN_APPLE_BEES_KEY = registerKey("apple/green_apple_tree_bees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MIXED_APPLE_BEES_KEY = registerKey("apple/mixed_apple_tree_bees");
    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context){
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
    }
    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Edumia.MOD_ID,"trees/fruit_trees/" + name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext< ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature,
                                                                                          FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
