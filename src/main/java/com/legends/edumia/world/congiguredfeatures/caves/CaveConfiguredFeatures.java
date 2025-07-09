package com.legends.edumia.world.congiguredfeatures.caves;

import com.legends.edumia.Edumia;
import com.legends.edumia.core.TagLoader;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class CaveConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> DS_REDUCER_SMALL = registerKey("ds_reducer_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DS_REDUCER = registerKey("ds_reducer");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NOISE_REDUCER_SMALL = registerKey("noise_reducer_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NOISE_REDUCER = registerKey("noise_reducer");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
        RuleTest baseStoneRule = new TagMatchTest(TagLoader.Blocks.BASE_STONE_EDUMIA);
        RuleTest altStoneRule = new TagMatchTest(TagLoader.Blocks.ALT_STONE);


        register(context, DS_REDUCER_SMALL, Feature.ORE, new OreConfiguration(List.of(OreConfiguration.target(baseStoneRule,
                Blocks.DEEPSLATE.defaultBlockState())), 30, 0.0f));

        register(context, DS_REDUCER, Feature.ORE, new OreConfiguration(List.of(OreConfiguration.target(baseStoneRule,
                Blocks.DEEPSLATE.defaultBlockState())), 48, 0.0f));

        register(context, NOISE_REDUCER_SMALL, Feature.ORE, new OreConfiguration(List.of(OreConfiguration.target(altStoneRule,
                Blocks.STONE.defaultBlockState())), 30, 0.0f));

        register(context, NOISE_REDUCER, Feature.ORE, new OreConfiguration(List.of(OreConfiguration.target(altStoneRule,
                Blocks.STONE.defaultBlockState())), 48, 0.0f));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "caves/" + name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext< ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature,
                                                                                          FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
