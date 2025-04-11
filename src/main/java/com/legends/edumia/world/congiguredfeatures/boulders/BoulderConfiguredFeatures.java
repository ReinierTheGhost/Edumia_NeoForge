package com.legends.edumia.world.congiguredfeatures.boulders;

import com.legends.edumia.Edumia;
import com.legends.edumia.blocks.blocksets.StoneSets;
import com.legends.edumia.world.features.EdumiaFeatures;
import com.legends.edumia.world.features.bouders.BouldersFeatureConfig;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockPileConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;


public class BoulderConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> ANDESITE_BOULDER = registerKey("andesite_boulder");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_ANDESITE_BOULDER = registerKey("big_andesite_boulder");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CALCITE_BOULDER = registerKey("calcite_boulder");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_CALCITE_BOULDER = registerKey("big_calcite_boulder");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DIORITE_BOULDER = registerKey("diorite_boulder");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_DIORITE_BOULDER = registerKey("big_diorite_boulder");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GRANITE_BOULDER = registerKey("granite_boulder");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_GRANITE_BOULDER = registerKey("big_granite_boulder");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LIMESTONE_BOULDER = registerKey("limestone_boulder");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_LIMESTONE_BOULDER = registerKey("big_limestone_boulder");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SANDSTONE_BOULDER = registerKey("sandstone_boulder");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_SANDSTONE_BOULDER = registerKey("big_sandstone_boulder");
    public static final ResourceKey<ConfiguredFeature<?, ?>> STONE_BOULDER = registerKey("stone_boulder");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_STONE_BOULDER = registerKey("big_stone_boulder");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MOSSY_BOULDER = registerKey("mossy_boulder");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_MOSSY_BOULDER = registerKey("big_mossy_boulder");
    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> featureRegisterable) {
        register(featureRegisterable, ANDESITE_BOULDER, Feature.FOREST_ROCK,
                new BlockStateConfiguration(Blocks.ANDESITE.defaultBlockState()));
        register(featureRegisterable, CALCITE_BOULDER, Feature.FOREST_ROCK,
                new BlockStateConfiguration(Blocks.CALCITE.defaultBlockState()));
        register(featureRegisterable, DIORITE_BOULDER, Feature.FOREST_ROCK,
                new BlockStateConfiguration(Blocks.DIORITE.defaultBlockState()));
        register(featureRegisterable, GRANITE_BOULDER, Feature.FOREST_ROCK,
                new BlockStateConfiguration(Blocks.GRANITE.defaultBlockState()));
        register(featureRegisterable, LIMESTONE_BOULDER, Feature.FOREST_ROCK,
                new BlockStateConfiguration(StoneSets.LIMESTONE.block().get().defaultBlockState()));
        register(featureRegisterable, SANDSTONE_BOULDER, Feature.FOREST_ROCK,
                new BlockStateConfiguration(Blocks.SANDSTONE.defaultBlockState()));
        register(featureRegisterable, STONE_BOULDER, Feature.FOREST_ROCK,
                new BlockStateConfiguration(Blocks.STONE.defaultBlockState()));

        register(featureRegisterable, MOSSY_BOULDER, Feature.BLOCK_PILE,
                new BlockPileConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                        .add(Blocks.STONE.defaultBlockState(), 3)
                        .add(Blocks.ANDESITE.defaultBlockState(), 2)
                        .add(Blocks.STONE_SLAB.defaultBlockState().setValue(SlabBlock.TYPE, SlabType.BOTTOM)
                                .setValue(SlabBlock.WATERLOGGED, false), 1)
                        .add(Blocks.MOSSY_COBBLESTONE_SLAB.defaultBlockState().setValue(SlabBlock.TYPE, SlabType.BOTTOM)
                                .setValue(SlabBlock.WATERLOGGED, false), 2)
                        .add(Blocks.MOSSY_COBBLESTONE.defaultBlockState(), 6))));

        register(featureRegisterable, BIG_ANDESITE_BOULDER, EdumiaFeatures.BOULDER.get(),
                new BouldersFeatureConfig(BlockStateProvider.simple(Blocks.ANDESITE), 1,4, 3));

        register(featureRegisterable, BIG_CALCITE_BOULDER, EdumiaFeatures.BOULDER.get(),
                new BouldersFeatureConfig(BlockStateProvider.simple(Blocks.CALCITE), 1,4, 3));

        register(featureRegisterable, BIG_DIORITE_BOULDER, EdumiaFeatures.BOULDER.get(),
                new BouldersFeatureConfig(BlockStateProvider.simple(Blocks.DIORITE), 1,4, 3));

        register(featureRegisterable,  BIG_GRANITE_BOULDER, EdumiaFeatures.BOULDER.get(),
                new BouldersFeatureConfig(BlockStateProvider.simple(Blocks.GRANITE), 1,4, 3));

        register(featureRegisterable, BIG_LIMESTONE_BOULDER, EdumiaFeatures.BOULDER.get(),
                new BouldersFeatureConfig(BlockStateProvider.simple(StoneSets.LIMESTONE.block().get()), 1,4, 3));

        register(featureRegisterable,  BIG_SANDSTONE_BOULDER, EdumiaFeatures.BOULDER.get(),
                new BouldersFeatureConfig(BlockStateProvider.simple(Blocks.SANDSTONE), 1,4, 3));

        register(featureRegisterable,  BIG_STONE_BOULDER, EdumiaFeatures.BOULDER.get(),
                new BouldersFeatureConfig(BlockStateProvider.simple(Blocks.STONE), 1,4, 3));

        register(featureRegisterable,  BIG_MOSSY_BOULDER, EdumiaFeatures.BOULDER.get(),
                new BouldersFeatureConfig(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                        .add(Blocks.STONE.defaultBlockState(), 3)
                        .add(Blocks.ANDESITE.defaultBlockState(), 2)
                        .add(Blocks.STONE_SLAB.defaultBlockState().setValue(SlabBlock.TYPE, SlabType.BOTTOM)
                                .setValue(SlabBlock.WATERLOGGED, false), 1)
                        .add(Blocks.MOSSY_COBBLESTONE_SLAB.defaultBlockState().setValue(SlabBlock.TYPE, SlabType.BOTTOM)
                                .setValue(SlabBlock.WATERLOGGED, false), 2)
                        .add(Blocks.MOSSY_COBBLESTONE.defaultBlockState(), 6)), 1,4, 3));

    }



    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "builders/" + name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext< ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature,
                                                                                          FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
