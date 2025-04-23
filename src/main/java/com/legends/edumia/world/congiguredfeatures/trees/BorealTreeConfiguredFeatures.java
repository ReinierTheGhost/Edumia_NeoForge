package com.legends.edumia.world.congiguredfeatures.trees;

import com.legends.edumia.Edumia;
import com.legends.edumia.core.blocksets.WoodBlockSets;
import com.legends.edumia.world.trees.foliageplacer.FirFoliagePlacer;
import com.legends.edumia.world.trees.foliageplacer.SilverSpruceFoliagePlacer;
import com.legends.edumia.world.trees.trunkplacers.SpruceTrunkPlacer;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.BiasedToBottomInt;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BushFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaPineFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.PineFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.OptionalInt;

public class BorealTreeConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> LARCH_KEY = registerKey("larch/larch_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LARCH_TREE_KEY = registerKey("larch/larch_tree_2");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_LARCH_KEY = registerKey("larch/big_larch_tree");

    public static final ResourceKey<ConfiguredFeature<?, ?>> FIR_KEY = registerKey("fir/fir_tree");

    public static final ResourceKey<ConfiguredFeature<?, ?>> PINE_TREE_KEY = registerKey("pine/pine_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEAD_PINE_TREE_KEY = registerKey("pine/dead_pine_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRUCE_TREE_KEY = registerKey("spruce/spruce_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRUCE_BUSH_TREE_KEY = registerKey("spruce/spruce_bush_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEGA_SPRUCE_TREE_KEY = registerKey("spruce/mega_spruce_tree");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SILVER_SPRUCE_TREE_KEY = registerKey("silver_spruce/silver_spruce_tree");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
        HolderGetter<PlacedFeature> placed = context.lookup(Registries.PLACED_FEATURE);
        BeehiveDecorator beehive05TreeDecorator = new BeehiveDecorator(0.05f);

        register(context, LARCH_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.LARCH.log().get()),
                new StraightTrunkPlacer(8, 8, 0),
                BlockStateProvider.simple(WoodBlockSets.LARCH.leaves().get()),
                new SpruceFoliagePlacer(UniformInt.of(2, 3), ConstantInt.of(1), UniformInt.of(1, 3)),
                new TwoLayersFeatureSize(1, 0, 1))
                .ignoreVines().build());

        register(context, LARCH_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.LARCH.log().get()),
                new StraightTrunkPlacer(11, 2 , 1),
                BlockStateProvider.simple(WoodBlockSets.LARCH.leaves().get()),
                new SpruceFoliagePlacer(ConstantInt.of(3), UniformInt.of(0, 2), UniformInt.of(2, 3)),
                new TwoLayersFeatureSize(1, 0, 2))
                .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());

        register(context, BIG_LARCH_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.LARCH.log().get()),
                new StraightTrunkPlacer(8, 4, 13),
                BlockStateProvider.simple(WoodBlockSets.LARCH.leaves().get()),
                new MegaPineFoliagePlacer(BiasedToBottomInt.of(1, 1), UniformInt.of(1, 3), UniformInt.of(12, 22)),
                new TwoLayersFeatureSize(1, 0, 1, OptionalInt.of(0)))
                .dirt(BlockStateProvider.simple(Blocks.COARSE_DIRT))
                .ignoreVines().forceDirt().build());

        register(context, PINE_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.PINE.log().get()),
                new SpruceTrunkPlacer(14, 3),
                BlockStateProvider.simple(WoodBlockSets.PINE.leaves().get()),
                new PineFoliagePlacer(ConstantInt.of(1), ConstantInt.of(1), UniformInt.of(4, 4)),
                new TwoLayersFeatureSize(1, 0, 2))
                .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());
        register(context, DEAD_PINE_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.PINE.log().get()),
                new SpruceTrunkPlacer(14, 3),
                BlockStateProvider.simple(Blocks.AIR),
                new PineFoliagePlacer(ConstantInt.of(1), ConstantInt.of(1), ConstantInt.of(1)),
                new TwoLayersFeatureSize(1, 0, 2))
                .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());

        register(context, SPRUCE_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.SPRUCE_LOG),
                new StraightTrunkPlacer(14, 2, 0),
                BlockStateProvider.simple(Blocks.SPRUCE_LEAVES),
                new SpruceFoliagePlacer(ConstantInt.of(3), UniformInt.of(0, 1), ConstantInt.of(2)),
                new TwoLayersFeatureSize(1, 0, 2))
                .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());
        register(context, SPRUCE_BUSH_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.SPRUCE_LOG),
                new StraightTrunkPlacer(1, 0, 0),
                BlockStateProvider.simple(Blocks.SPRUCE_LEAVES),
                new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2),
                new TwoLayersFeatureSize(0, 0, 0)).build());
        register(context, MEGA_SPRUCE_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.SPRUCE_LOG),
                new StraightTrunkPlacer(8, 3, 10),
                BlockStateProvider.simple(Blocks.SPRUCE_LEAVES),
                new MegaPineFoliagePlacer(BiasedToBottomInt.of(0, 1), UniformInt.of(1, 3),
                        UniformInt.of(12, 17)),
                new TwoLayersFeatureSize(1, 0, 0, OptionalInt.of(0)))
                .dirt(BlockStateProvider.simple(Blocks.COARSE_DIRT)).build());

        register(context, SILVER_SPRUCE_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.SILVER_SPRUCE.log().get()),
                new StraightTrunkPlacer(8, 7, 0),
                BlockStateProvider.simple(WoodBlockSets.SILVER_SPRUCE.leaves().get()),
                new SilverSpruceFoliagePlacer(ConstantInt.of(2), UniformInt.of(0, 2), UniformInt.of(7, 11)),
                new TwoLayersFeatureSize(1, 0, 1))
                .dirt(BlockStateProvider.simple(Blocks.GRASS_BLOCK)).build());

        register(context, FIR_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.FIR.log().get()),
                new StraightTrunkPlacer(6, 7, 0),
                BlockStateProvider.simple(WoodBlockSets.FIR.leaves().get()),
                new FirFoliagePlacer(UniformInt.of(2, 3), ConstantInt.of(2),
                        UniformInt.of(7, 11)),
                new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());

    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "tree/boreal/" + name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext< ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature,
                                                                                          FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
