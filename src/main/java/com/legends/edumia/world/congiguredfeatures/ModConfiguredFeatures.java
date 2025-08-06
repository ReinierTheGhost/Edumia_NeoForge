package com.legends.edumia.world.congiguredfeatures;

import com.legends.edumia.Edumia;
import com.legends.edumia.blocks.Layer;
import com.legends.edumia.world.congiguredfeatures.beach.BeachConfiguredFeatures;
import com.legends.edumia.world.congiguredfeatures.biomes.FairyBiomesConfiguredFeatures;
import com.legends.edumia.world.congiguredfeatures.boulders.BoulderConfiguredFeatures;
import com.legends.edumia.world.congiguredfeatures.caves.CaveConfiguredFeatures;
import com.legends.edumia.world.congiguredfeatures.caves.FungalCaveConfiguredFeatures;
import com.legends.edumia.world.congiguredfeatures.caves.JungleCaveConfiguredFeatures;
import com.legends.edumia.world.congiguredfeatures.crystals.CrystalConfiguresFeatures;
import com.legends.edumia.world.congiguredfeatures.ores.VanillaBlockOreConfiguredFeatures;
import com.legends.edumia.world.congiguredfeatures.plants.FlowerConfiguredFeatures;
import com.legends.edumia.world.congiguredfeatures.plants.MushroomConfiguredFeatures;
import com.legends.edumia.world.congiguredfeatures.plants.ReedsConfiguredFeatures;
import com.legends.edumia.world.congiguredfeatures.biomes.BiomeConfiguredFeatures;
import com.legends.edumia.world.congiguredfeatures.biomes.OgreBiomeConfiguredFeatures;
import com.legends.edumia.world.congiguredfeatures.ocean.ReefConfiguredFeatures;
import com.legends.edumia.world.congiguredfeatures.trees.*;
import com.legends.edumia.world.features.EdumiaFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.DeltaFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> NOTING = registerKey("noting");
    public static final ResourceKey<ConfiguredFeature<?, ?>> STONE = registerKey("stone");
    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_SAND = registerKey("red_sand");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SAND = registerKey("sand");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SNOW_LAYER_FIRST = registerKey("snow/layers/normal/first");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SNOW_LAYER_SECOND = registerKey("snow/layers/normal/second");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SNOW_LAYER_THIRD = registerKey("snow/layers/normal/third");

    public static final ResourceKey<ConfiguredFeature<?, ?>> WATER_DELTA = registerKey("water_delta");

    public static void boostrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
        register(context, NOTING, Feature.NO_OP, new NoneFeatureConfiguration());
        register(context, STONE, Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.STONE)));
        register(context, RED_SAND, Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.RED_SAND)));
        register(context, SAND, Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.SAND)));

        register(context, SNOW_LAYER_FIRST, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration( new WeightedStateProvider(
                SimpleWeightedRandomList.<BlockState>builder()
                        .add(Blocks.SNOW.defaultBlockState().setValue(Layer.LAYERS, 6), 5)
                        .add(Blocks.SNOW.defaultBlockState().setValue(Layer.LAYERS, 7), 4))));

        register(context, SNOW_LAYER_SECOND, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration( new WeightedStateProvider(
                SimpleWeightedRandomList.<BlockState>builder()
                        .add(Blocks.SNOW.defaultBlockState().setValue(Layer.LAYERS, 4), 1)
                        .add(Blocks.SNOW.defaultBlockState().setValue(Layer.LAYERS, 5), 1))));

        register(context, SNOW_LAYER_THIRD, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration( new WeightedStateProvider(
                SimpleWeightedRandomList.<BlockState>builder()
                        .add(Blocks.SNOW.defaultBlockState().setValue(Layer.LAYERS, 2), 4)
                        .add(Blocks.SNOW.defaultBlockState().setValue(Layer.LAYERS, 3), 3))));

        register(context, WATER_DELTA, EdumiaFeatures.DELTA_FEATURES.get(), new DeltaFeatureConfiguration(
                Blocks.WATER.defaultBlockState(), Blocks.GRASS_BLOCK.defaultBlockState(), UniformInt.of(3, 7), UniformInt.of(0,2)));

        BeechTreeConfiguredFeatures.bootstrap(context);
        TreeConfiguredFeatures.bootstrap(context);
        SaplingConfiguredFeatures.bootstrap(context);
        BushesConfiguredFeatures.bootstrap(context);
        BeachConfiguredFeatures.bootstrap(context);
        ReefConfiguredFeatures.bootstrap(context);
        OakTreeConfiguredFeatures.bootstrap(context);
        FlowerConfiguredFeatures.bootstrap(context);
        TropicalTreeConfiguredFeatures.bootstrap(context);
        TemperateTreeConfiguredFeatures.bootstrap(context);
        BorealTreeConfiguredFeatures.bootstrap(context);
        BoulderConfiguredFeatures.bootstrap(context);
        CrystalConfiguresFeatures.bootstrap(context);
        ReedsConfiguredFeatures.bootstrap(context);
        BiomeConfiguredFeatures.bootstrap(context);
        FruitTreeConfiguredFeatures.bootstrap(context);
        OgreBiomeConfiguredFeatures.bootstrap(context);
        RootsConfiguredFeatures.bootstrap(context);
        FairyBiomesConfiguredFeatures.bootstrap(context);
        JungleTreeConfiguredFeatures.bootstrap(context);
        KapokTreeConfiguredFeatures.bootstrap(context);
        MahoganyConfiguredFeatures.bootstrap(context);
        LeopardTreeConfiguredFeatures.bootstrap(context);
        PalmConfiguredFeatures.bootstrap(context);
        DeadTreeConfiguredFeatures.bootstrap(context);
        DiskConfiguredFeatures.boostrap(context);
        SubTropicalTreeConfiguredFeatures.bootstrap(context);
        MirwoodNutTreeConfiguredFeatures.bootstrap(context);
        MushroomConfiguredFeatures.bootstrap(context);
        VanillaBlockOreConfiguredFeatures.bootstrap(context);
        CaveConfiguredFeatures.bootstrap(context);
        JungleCaveConfiguredFeatures.bootstrap(context);
        FungalCaveConfiguredFeatures.bootstrap(context);
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext< ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature,
                                                                                          FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
