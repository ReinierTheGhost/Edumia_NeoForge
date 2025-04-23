package com.legends.edumia.world.congiguredfeatures.trees;

import com.legends.edumia.Edumia;
import com.legends.edumia.core.blocksets.WoodBlockSets;
import com.legends.edumia.world.trees.foliageplacer.ClusterFoliagePlacer;
import com.legends.edumia.world.trees.foliageplacer.OvalFoliagePlacer;
import com.legends.edumia.world.trees.trunkplacers.CanopyTrunkPlacer;
import com.legends.edumia.world.trees.trunkplacers.EdumiaGiantTrunkPlacer;
import com.legends.edumia.world.trees.trunkplacers.PartyTreeTrunkPlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class BeechTreeConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> PARTY_BEECH_KEY = registerKey("party_beech_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GIGA_BEECH_KEY = registerKey("giga_beech_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BEECH_KEY = registerKey("beech_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_BEECH_KEY = registerKey("big_beech_tree");
    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
        register(context, GIGA_BEECH_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.BEECH.log().get()),
                new EdumiaGiantTrunkPlacer(20, 20, 0, WoodBlockSets.BEECH.wood().get().defaultBlockState(),
                        WoodBlockSets.BEECH.woodWall().get().defaultBlockState()),
                BlockStateProvider.simple(WoodBlockSets.BEECH.leaves().get()),
                new ClusterFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, PARTY_BEECH_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.BEECH.log().get()),
                new PartyTreeTrunkPlacer(10, 14, 0, WoodBlockSets.BEECH.wood().get().defaultBlockState(),
                        WoodBlockSets.BEECH.woodWall().get().defaultBlockState()),
                BlockStateProvider.simple(WoodBlockSets.BEECH.leaves().get()),
                new ClusterFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1, 0, 0)).build());

        register(context, BEECH_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.BEECH.log().get()),
                new CanopyTrunkPlacer(12, 2, 0.91f, 0.87f, 5.0f, 3, 0.42f, -0.1f, 1,1),
                BlockStateProvider.simple(WoodBlockSets.BEECH.leaves().get()),
                new OvalFoliagePlacer(3, ConstantInt.of(0), ConstantInt.of(2), 0.4f),
                new TwoLayersFeatureSize(1, 0, 2)).ignoreVines().build());

        register(context, BIG_BEECH_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.BEECH.log().get()),
                new CanopyTrunkPlacer(18, 3, 1.0f, 0.67f, 5.2f, 3,
                        0.44f, -0.05f, 2, 1),
                BlockStateProvider.simple(WoodBlockSets.BEECH.leaves().get()),
                new OvalFoliagePlacer(2, ConstantInt.of(0), ConstantInt.of(3), 0.4f),
                new TwoLayersFeatureSize(1, 0, 2)).ignoreVines().build());
    }
    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID,"tree/beech/" + name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext< ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature,
                                                                                          FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
