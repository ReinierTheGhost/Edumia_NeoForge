package com.legends.edumia.world.congiguredfeatures.trees;

import com.legends.edumia.Edumia;
import com.legends.edumia.world.trees.foliageplacer.OvalFoliagePlacer;
import com.legends.edumia.world.trees.trunkplacers.CanopyTrunkPlacer;
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
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;

import java.util.OptionalInt;

public class SubTropicalTreeConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> ACACIA = registerKey("acacia/acacia_tree");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
        register(context, ACACIA, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.ACACIA_LOG),
                new ForkingTrunkPlacer(2, 16, 16),
                BlockStateProvider.simple(Blocks.ACACIA_LEAVES),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(2),2),
                new TwoLayersFeatureSize(1, 1, 1, OptionalInt.of(0)))
                .ignoreVines().dirt(BlockStateProvider.simple(Blocks.STONE)).build());
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
