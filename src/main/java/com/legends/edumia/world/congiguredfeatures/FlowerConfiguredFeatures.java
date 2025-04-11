package com.legends.edumia.world.congiguredfeatures;

import com.legends.edumia.Edumia;
import com.legends.edumia.core.BlockLoader;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;

public class FlowerConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> ASPHODEL_FLOWER_KEY = registerKey("asphodel");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_DELPHINIUM_FLOWER_KEY = registerKey("blue_delphinium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUEBELL_FLOWER_KEY = registerKey("bluebell");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CALLA_LILY_FLOWER_KEY = registerKey("calla_lily");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CELSEMIUM_FLOWER_KEY = registerKey("celsemium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CHRYS_BLUE_FLOWER_KEY = registerKey("chrys_blue");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CHRYS_ORANGE_FLOWER_KEY = registerKey("chrys_orange");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CHRYS_PINK_FLOWER_KEY = registerKey("chrys_pink");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CHRYS_WHITE_FLOWER_KEY = registerKey("chrys_white");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CHRYS_YELLOW_FLOWER_KEY = registerKey("chrys_yellow");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CROCUS_FLOWER_KEY = registerKey("crocus");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DAISY_FLOWER_KEY = registerKey("daisy");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DELPHINIUM_FLOWER_KEY = registerKey("delphinium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLAX_FLOWER_KEY = registerKey("flax");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FOXGLOVE_ORANGE_FLOWER_KEY = registerKey("foxglove_orange");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FOXGLOVE_PINK_FLOWER_KEY = registerKey("foxglove_pink");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FOXGLOVE_RED_FLOWER_KEY = registerKey("foxglove_red");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FOXGLOVE_WHITE_FLOWER_KEY = registerKey("foxglove_white");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GERBERA_RED_FLOWER_KEY = registerKey("gerbera_red");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GERBERA_YELLOW_FLOWER_KEY = registerKey("gerbera_yellow");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GENSAI_ORCHID_FLOWER_KEY = registerKey("gensai_orchid");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HEATHER_BUSH_FLOWER_KEY = registerKey("heather_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAVENDER_FLOWER_KEY = registerKey("lavender");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MARIGOLD_FLOWER_KEY = registerKey("marigold");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PINK_ANEMONE_FLOWER_KEY = registerKey("pink_anemone");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SIMBELMYNE_FLOWER_KEY = registerKey("simbelmyne");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TUBEROSE_FLOWER_KEY = registerKey("tuberose");
    public static final ResourceKey<ConfiguredFeature<?, ?>> YELLOW_IRIS_FLOWER_KEY = registerKey("yellow_iris");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DESERT_FLAME_FLOWER_KEY = registerKey("desert_flame");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HIBISCUS_FLOWER_KEY = registerKey("hibiscus");


    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context){
        register(context, ASPHODEL_FLOWER_KEY, Feature.FLOWER, new RandomPatchConfiguration(8, 6, 2,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(
                        SimpleWeightedRandomList.<BlockState>builder().add(BlockLoader.ASPHODEL.get().defaultBlockState(), 1))))));

        register(context, BLUE_DELPHINIUM_FLOWER_KEY, Feature.FLOWER, new RandomPatchConfiguration(8, 6, 2,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(
                        SimpleWeightedRandomList.<BlockState>builder().add(BlockLoader.BLUE_DELPHINIUM.get().defaultBlockState(), 1))))));

        register(context, BLUEBELL_FLOWER_KEY, Feature.FLOWER, new RandomPatchConfiguration(8, 6, 2,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(
                        SimpleWeightedRandomList.<BlockState>builder().add(BlockLoader.BLUEBELL.get().defaultBlockState(), 1))))));

        register(context, CALLA_LILY_FLOWER_KEY, Feature.FLOWER, new RandomPatchConfiguration(8, 6, 2,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(
                        SimpleWeightedRandomList.<BlockState>builder().add(BlockLoader.CALLA_LILY.get().defaultBlockState(), 1))))));

        register(context, CELSEMIUM_FLOWER_KEY, Feature.FLOWER, new RandomPatchConfiguration(8, 6, 2,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(
                        SimpleWeightedRandomList.<BlockState>builder().add(BlockLoader.CELSEMIUM.get().defaultBlockState(), 1))))));

        register(context, CHRYS_BLUE_FLOWER_KEY, Feature.FLOWER, new RandomPatchConfiguration(8, 6, 2,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(
                        SimpleWeightedRandomList.<BlockState>builder().add(BlockLoader.CHRYS_BLUE.get().defaultBlockState(), 1))))));

        register(context, CHRYS_ORANGE_FLOWER_KEY, Feature.FLOWER, new RandomPatchConfiguration(8, 6, 2,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(
                        SimpleWeightedRandomList.<BlockState>builder().add(BlockLoader.CHRYS_ORANGE.get().defaultBlockState(), 1))))));

        register(context, CHRYS_PINK_FLOWER_KEY, Feature.FLOWER, new RandomPatchConfiguration(8, 6, 2,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(
                        SimpleWeightedRandomList.<BlockState>builder().add(BlockLoader.CHRYS_PINK.get().defaultBlockState(), 1))))));

        register(context, CHRYS_WHITE_FLOWER_KEY, Feature.FLOWER, new RandomPatchConfiguration(8, 6, 2,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(
                        SimpleWeightedRandomList.<BlockState>builder().add(BlockLoader.CHRYS_WHITE.get().defaultBlockState(), 1))))));

        register(context, CHRYS_YELLOW_FLOWER_KEY, Feature.FLOWER, new RandomPatchConfiguration(8, 6, 2,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(
                        SimpleWeightedRandomList.<BlockState>builder().add(BlockLoader.CHRYS_YELLOW.get().defaultBlockState(), 1))))));

        register(context, CROCUS_FLOWER_KEY, Feature.FLOWER, new RandomPatchConfiguration(8, 6, 2,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(
                        SimpleWeightedRandomList.<BlockState>builder().add(BlockLoader.CROCUS.get().defaultBlockState(), 1))))));

        register(context, DAISY_FLOWER_KEY, Feature.FLOWER, new RandomPatchConfiguration(8, 6, 2,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(
                        SimpleWeightedRandomList.<BlockState>builder().add(BlockLoader.DAISY.get().defaultBlockState(), 1))))));

        register(context, DELPHINIUM_FLOWER_KEY, Feature.FLOWER, new RandomPatchConfiguration(8, 6, 2,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(
                        SimpleWeightedRandomList.<BlockState>builder().add(BlockLoader.DELPHINIUM.get().defaultBlockState(), 1))))));

        register(context, FLAX_FLOWER_KEY, Feature.FLOWER, new RandomPatchConfiguration(8, 6, 2,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(
                        SimpleWeightedRandomList.<BlockState>builder().add(BlockLoader.FLAX_FLOWERS.get().defaultBlockState(), 1))))));

        register(context, FOXGLOVE_ORANGE_FLOWER_KEY, Feature.FLOWER, new RandomPatchConfiguration(8, 6, 2,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(
                        SimpleWeightedRandomList.<BlockState>builder().add(BlockLoader.FOXGLOVE_ORANGE.get().defaultBlockState(), 1))))));

        register(context, FOXGLOVE_PINK_FLOWER_KEY, Feature.FLOWER, new RandomPatchConfiguration(8, 6, 2,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(
                        SimpleWeightedRandomList.<BlockState>builder().add(BlockLoader.FOXGLOVE_PINK.get().defaultBlockState(), 1))))));

        register(context, FOXGLOVE_RED_FLOWER_KEY, Feature.FLOWER, new RandomPatchConfiguration(8, 6, 2,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(
                        SimpleWeightedRandomList.<BlockState>builder().add(BlockLoader.FOXGLOVE_RED.get().defaultBlockState(), 1))))));

        register(context, FOXGLOVE_WHITE_FLOWER_KEY, Feature.FLOWER, new RandomPatchConfiguration(8, 6, 2,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(
                        SimpleWeightedRandomList.<BlockState>builder().add(BlockLoader.FOXGLOVE_WHITE.get().defaultBlockState(), 1))))));

        register(context, GERBERA_RED_FLOWER_KEY, Feature.FLOWER, new RandomPatchConfiguration(8, 6, 2,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(
                        SimpleWeightedRandomList.<BlockState>builder().add(BlockLoader.GERBERA_RED.get().defaultBlockState(), 1))))));

        register(context, GERBERA_YELLOW_FLOWER_KEY, Feature.FLOWER, new RandomPatchConfiguration(8, 6, 2,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(
                        SimpleWeightedRandomList.<BlockState>builder().add(BlockLoader.GERBERA_YELLOW.get().defaultBlockState(), 1))))));

        register(context, GENSAI_ORCHID_FLOWER_KEY, Feature.FLOWER, new RandomPatchConfiguration(8, 6, 2,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(
                        SimpleWeightedRandomList.<BlockState>builder().add(BlockLoader.GENSAI_ORCHID.get().defaultBlockState(), 1))))));

        register(context, HEATHER_BUSH_FLOWER_KEY, Feature.FLOWER, new RandomPatchConfiguration(8, 6, 2,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(
                        SimpleWeightedRandomList.<BlockState>builder().add(BlockLoader.HEATHER_BUSH.get().defaultBlockState(), 1))))));

        register(context, LAVENDER_FLOWER_KEY, Feature.FLOWER, new RandomPatchConfiguration(8, 6, 2,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(
                        SimpleWeightedRandomList.<BlockState>builder().add(BlockLoader.LAVENDER.get().defaultBlockState(), 1))))));

        register(context, MARIGOLD_FLOWER_KEY, Feature.FLOWER, new RandomPatchConfiguration(8, 6, 2,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(
                        SimpleWeightedRandomList.<BlockState>builder().add(BlockLoader.MARIGOLD.get().defaultBlockState(), 1))))));

        register(context, PINK_ANEMONE_FLOWER_KEY, Feature.FLOWER, new RandomPatchConfiguration(8, 6, 2,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(
                        SimpleWeightedRandomList.<BlockState>builder().add(BlockLoader.PINK_ANEMONE.get().defaultBlockState(), 1))))));

        register(context, SIMBELMYNE_FLOWER_KEY, Feature.FLOWER, new RandomPatchConfiguration(8, 6, 2,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(
                        SimpleWeightedRandomList.<BlockState>builder().add(BlockLoader.SIMBLELMYNE.get().defaultBlockState(), 1))))));

        register(context, TUBEROSE_FLOWER_KEY, Feature.FLOWER, new RandomPatchConfiguration(8, 6, 2,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(
                        SimpleWeightedRandomList.<BlockState>builder().add(BlockLoader.TUBEROSE.get().defaultBlockState(), 1))))));

        register(context, YELLOW_IRIS_FLOWER_KEY, Feature.FLOWER, new RandomPatchConfiguration(8, 6, 2,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(
                        SimpleWeightedRandomList.<BlockState>builder().add(BlockLoader.YELLOW_IRIS.get().defaultBlockState(), 1))))));

        register(context, DESERT_FLAME_FLOWER_KEY, Feature.FLOWER, new RandomPatchConfiguration(8, 6, 2,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(
                        SimpleWeightedRandomList.<BlockState>builder().add(BlockLoader.FLAME_OF_THE_SOUTH.get().defaultBlockState(), 1))))));

        register(context, HIBISCUS_FLOWER_KEY, Feature.FLOWER, new RandomPatchConfiguration(8, 6, 2,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(
                        SimpleWeightedRandomList.<BlockState>builder().add(BlockLoader.HIBISCUS.get().defaultBlockState(), 1))))));
    }


    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Edumia.MOD_ID, "flowers/" + name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext< ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature,
                                                                                          FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
