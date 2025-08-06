package com.legends.edumia.world.congiguredfeatures.caves;

import com.legends.edumia.Edumia;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

public class FungalCaveConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> BROWN_MEDIUM = registerKey("brown_medium");

    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_MEDIUM = registerKey("red_medium");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
        //region [Tools]
        var holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);
        //endregion

        //region [Trees]
        register(context, BROWN_MEDIUM, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.MUSHROOM_STEM.defaultBlockState()),
                new StraightTrunkPlacer(3, 1, 0),
                BlockStateProvider.simple(Blocks.BROWN_MUSHROOM_BLOCK.defaultBlockState()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2),
                new TwoLayersFeatureSize(1, 0, 1)
        ).build());

        register(context, RED_MEDIUM, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.MUSHROOM_STEM.defaultBlockState()),
                new StraightTrunkPlacer(3, 1, 0),
                BlockStateProvider.simple(Blocks.RED_MUSHROOM_BLOCK.defaultBlockState()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2),
                new TwoLayersFeatureSize(1, 0, 1)
        ).build());
        //endregion
    }



    // region [register]
    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "caves/fungal/" + name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext< ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature,
                                                                                          FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
    // endregion
}
