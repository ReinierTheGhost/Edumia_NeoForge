package com.legends.edumia.world.congiguredfeatures;

import com.legends.edumia.Edumia;
import com.legends.edumia.world.congiguredfeatures.beach.BeachConfiguredFeatures;
import com.legends.edumia.world.congiguredfeatures.biomes.FairyBiomesConfiguredFeatures;
import com.legends.edumia.world.congiguredfeatures.boulders.BoulderConfiguredFeatures;
import com.legends.edumia.world.congiguredfeatures.crystals.CrystalConfiguresFeatures;
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
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.DeltaFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> NOTING = registerKey("noting");
    public static final ResourceKey<ConfiguredFeature<?, ?>> STONE = registerKey("stone");
    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_SAND = registerKey("red_sand");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SAND = registerKey("sand");

    public static final ResourceKey<ConfiguredFeature<?, ?>> WATER_DELTA = registerKey("water_delta");

    public static void boostrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
        register(context, NOTING, Feature.NO_OP, new NoneFeatureConfiguration());
        register(context, STONE, Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.STONE)));
        register(context, RED_SAND, Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.RED_SAND)));
        register(context, SAND, Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.SAND)));

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
