package com.legends.edumia.world.congiguredfeatures.trees;

import com.google.common.collect.ImmutableList;
import com.legends.edumia.Edumia;
import com.legends.edumia.blocks.blocksets.StoneSets;
import com.legends.edumia.blocks.blocksets.WoodBlockSets;
import com.legends.edumia.core.BlockLoader;
import com.legends.edumia.world.trees.foliageplacer.EmptyFoliagePlacer;
import com.legends.edumia.world.trees.foliageplacer.OvalFoliagePlacer;
import com.legends.edumia.world.trees.trunkplacers.LargeTrunkPlacer;
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
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AlterGroundDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;

public class DeadTreeConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> BURNED_TREE = registerKey("burned_tree");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context){
        register(context, BURNED_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.CHARRED.log().get()),
                new LargeTrunkPlacer(13, 2, 1.2f, 0.67f, 6.0f, 3, 0.32f),
                BlockStateProvider.simple(Blocks.AIR),
                new EmptyFoliagePlacer(),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(
                        ImmutableList.of(
                                new AlterGroundDecorator(
                                        BlockStateProvider.simple(
                                                BlockLoader.VOLCANIC_DIRT.get()
                                        )
                                )
                        )
                ).build());
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Edumia.MOD_ID, "tree/dead/" + name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext< ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature,
                                                                                          FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
