package com.legends.edumia.world.congiguredfeatures.trees;

import com.google.common.collect.ImmutableList;
import com.legends.edumia.Edumia;
import com.legends.edumia.core.blocksets.WoodBlockSets;
import com.legends.edumia.core.TagLoader;
import com.legends.edumia.world.features.EdumiaFeatures;
import com.legends.edumia.world.features.treesnbt.TreeFromStructureNBTConfig;
import com.legends.edumia.world.trees.foliageplacer.MahoganyFoliagePlacer;
import com.legends.edumia.world.trees.treedecorators.HangingBranchDecorator;
import com.legends.edumia.world.trees.trunkplacers.CanopyTrunkPlacer;
import com.legends.edumia.world.trees.trunkplacers.LargeTrunkPlacer;
import com.legends.edumia.world.trees.trunkplacers.MahoganyTrunkPlacer;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.BiasedToBottomInt;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TrunkVineDecorator;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;
import java.util.Set;

public class MahoganyConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> MAHOGANY_1 = registerKey("mahogany_1");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAHOGANY_2 = registerKey("mahogany_2");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAHOGANY_3 = registerKey("mahogany_3");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAHOGANY_4 = registerKey("mahogany_4");



    public static final ResourceKey<ConfiguredFeature<?, ?>> MAHOGANY = registerKey("mahogany");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_MAHOGANY = registerKey("big_mahogany");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CANOPY_MAHOGANY_TREE = registerKey("canopy_mahogany_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_MAHOGANY_TREE = registerKey("large_mahogany_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAHOGANY_NBT = registerKey("mahogany_nbt");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAHOGANY_ROOTS_NBT = registerKey("mahogany_roots_nbt");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
        HolderGetter<ConfiguredFeature<?, ?>> holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);
        HolderGetter<PlacedFeature> registryEntryLookup = context.lookup(Registries.PLACED_FEATURE);

        register(context, MAHOGANY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.MAHOGANY.log().get()),
                new MahoganyTrunkPlacer(8, 6, 0, WoodBlockSets.MAHOGANY.wood().get().defaultBlockState(),
                        WoodBlockSets.MAHOGANY.woodWall().get().defaultBlockState()),
                BlockStateProvider.simple(WoodBlockSets.MAHOGANY.leaves().get()),
                new MahoganyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 2),
                new TwoLayersFeatureSize(0, 1, 0))
                .decorators(List.of(new LeaveVineDecorator(0.25f),
                        new HangingBranchDecorator(0.25F,
                                BlockStateProvider.simple(WoodBlockSets.MAHOGANY.leaves().get().defaultBlockState()
                                        .setValue(BlockStateProperties.PERSISTENT, true)),
                                BlockStateProvider.simple(WoodBlockSets.MAHOGANY.leaves().get().defaultBlockState()
                                        .setValue(BlockStateProperties.PERSISTENT, true))
                        ))).build());

        register(context, BIG_MAHOGANY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.MAHOGANY.log().get()),
                new MahoganyTrunkPlacer(16, 12, 0, WoodBlockSets.MAHOGANY.wood().get().defaultBlockState(),
                        WoodBlockSets.MAHOGANY.woodWall().get().defaultBlockState()),
                BlockStateProvider.simple(WoodBlockSets.MAHOGANY.leaves().get()),
                new MahoganyFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(0, 1, 0))
                .decorators(List.of(new LeaveVineDecorator(0.25f),
                        new HangingBranchDecorator(0.25F,
                                BlockStateProvider.simple(WoodBlockSets.MAHOGANY.leaves().get().defaultBlockState()
                                        .setValue(BlockStateProperties.PERSISTENT, true)),
                                BlockStateProvider.simple(WoodBlockSets.MAHOGANY.leaves().get().defaultBlockState()
                                        .setValue(BlockStateProperties.PERSISTENT, true))
                        ))).build());

        register(context, CANOPY_MAHOGANY_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.MAHOGANY.log().get()),
                new CanopyTrunkPlacer(12, 10, 2f, 0.87f, 5.0f, 3, 0.42f, -0.1f, 1,1),
                BlockStateProvider.simple(WoodBlockSets.MAHOGANY.leaves().get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new MahoganyFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), 3),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(ImmutableList.of(TrunkVineDecorator.INSTANCE, new LeaveVineDecorator(0.25f),
                        new HangingBranchDecorator(0.25F,
                                BlockStateProvider.simple(WoodBlockSets.MAHOGANY.leaves().get().defaultBlockState()
                                        .setValue(BlockStateProperties.PERSISTENT, true)),
                                BlockStateProvider.simple(WoodBlockSets.MAHOGANY.leaves().get().defaultBlockState()
                                        .setValue(BlockStateProperties.PERSISTENT, true))
                        ))).ignoreVines().build());

        register(context, LARGE_MAHOGANY_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.MAHOGANY.log().get()),
                new LargeTrunkPlacer(27, 3, 2.3f, 0.6f, 6.2f, 5, 0.25f),
                BlockStateProvider.simple(WoodBlockSets.MAHOGANY.leaves().get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new MahoganyFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), 3),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(ImmutableList.of(TrunkVineDecorator.INSTANCE, new LeaveVineDecorator(0.25f),
                        new HangingBranchDecorator(0.25F,
                                BlockStateProvider.simple(WoodBlockSets.MAHOGANY.leaves().get().defaultBlockState()
                                        .setValue(BlockStateProperties.PERSISTENT, true)),
                                BlockStateProvider.simple(WoodBlockSets.MAHOGANY.leaves().get().defaultBlockState()
                                        .setValue(BlockStateProperties.PERSISTENT, true))
                        ))).ignoreVines().build());

        register(context, MAHOGANY_NBT,  EdumiaFeatures.TREE_FROM_NBT.get(), new TreeFromStructureNBTConfig(
                Edumia.location("features/trees/mahogany/mahogany_tree_trunk1"),
                Edumia.location("features/trees/mahogany/mahogany_tree_canopy1"),
                BiasedToBottomInt.of(5, 15),
                BlockStateProvider.simple(WoodBlockSets.MAHOGANY.log().get()),
                BlockStateProvider.simple(WoodBlockSets.MAHOGANY.leaves().get()),
                WoodBlockSets.MAHOGANY.log(),
                WoodBlockSets.MAHOGANY.leaves(),
                TagLoader.Blocks.GROUND_MAHOGANY_SAPLING,
                5,
                ImmutableList.of(new LeaveVineDecorator(0.5f))
        ));

        register(context, MAHOGANY_ROOTS_NBT,  EdumiaFeatures.TREE_FROM_NBT.get(), new TreeFromStructureNBTConfig(
                Edumia.location("features/trees/mahogany/mahogany_tree_trunk1_roots"),
                Edumia.location("features/trees/mahogany/mahogany_tree_canopy1"),
                BiasedToBottomInt.of(5, 15),
                BlockStateProvider.simple(WoodBlockSets.MAHOGANY.log().get()),
                BlockStateProvider.simple(WoodBlockSets.MAHOGANY.leaves().get()),
                WoodBlockSets.MAHOGANY.log(),
                WoodBlockSets.MAHOGANY.leaves(),
                TagLoader.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(new LeaveVineDecorator(0.5f)),
                Set.of(WoodBlockSets.MAHOGANY.woodSlab().get(), WoodBlockSets.MAHOGANY.woodStairs().get())
        ));

        register(context, MAHOGANY_1,  EdumiaFeatures.TREE_FROM_NBT.get(), new TreeFromStructureNBTConfig(
                Edumia.location("features/trees/mahogany/mahogany_trunk_1"),
                Edumia.location("features/trees/mahogany/mahogany_canopy_1"),
                ConstantInt.of(5),
                BlockStateProvider.simple(WoodBlockSets.MAHOGANY.wood().get()),
                BlockStateProvider.simple(WoodBlockSets.MAHOGANY.leaves().get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                Set.of(WoodBlockSets.MAHOGANY.wood().get(), WoodBlockSets.MAHOGANY.woodFence().get(), WoodBlockSets.MAHOGANY.woodStairs().get(),
                        WoodBlockSets.MAHOGANY.woodSlab().get()),
                WoodBlockSets.MAHOGANY.leaves().get(),
                TagLoader.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(new LeaveVineDecorator(0.5f),
                TrunkVineDecorator.INSTANCE),
                Set.of(WoodBlockSets.MAHOGANY.woodFence().get(), WoodBlockSets.MAHOGANY.woodStairs().get(),
                        WoodBlockSets.MAHOGANY.woodSlab().get(), WoodBlockSets.MAHOGANY.leaves().get())
        ));

        register(context, MAHOGANY_2,  EdumiaFeatures.TREE_FROM_NBT.get(), new TreeFromStructureNBTConfig(
                Edumia.location("features/trees/mahogany/mahogany_trunk_2"),
                Edumia.location("features/trees/mahogany/mahogany_canopy_2"),
                ConstantInt.of(8),
                BlockStateProvider.simple(WoodBlockSets.MAHOGANY.wood().get()),
                BlockStateProvider.simple(WoodBlockSets.MAHOGANY.leaves().get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                Set.of(WoodBlockSets.MAHOGANY.wood().get(), WoodBlockSets.MAHOGANY.woodFence().get(), WoodBlockSets.MAHOGANY.woodStairs().get(),
                        WoodBlockSets.MAHOGANY.woodSlab().get()),
                WoodBlockSets.MAHOGANY.leaves().get(),
                TagLoader.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(new LeaveVineDecorator(0.5f),
                TrunkVineDecorator.INSTANCE),
                Set.of(WoodBlockSets.MAHOGANY.woodFence().get(), WoodBlockSets.MAHOGANY.woodStairs().get(),
                        WoodBlockSets.MAHOGANY.woodSlab().get(), WoodBlockSets.MAHOGANY.leaves().get())
        ));

        register(context, MAHOGANY_3,  EdumiaFeatures.TREE_FROM_NBT.get(), new TreeFromStructureNBTConfig(
                Edumia.location("features/trees/mahogany/mahogany_trunk_3"),
                Edumia.location("features/trees/mahogany/mahogany_canopy_3"),
                ConstantInt.of(4),
                BlockStateProvider.simple(WoodBlockSets.MAHOGANY.wood().get()),
                BlockStateProvider.simple(WoodBlockSets.MAHOGANY.leaves().get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                Set.of(WoodBlockSets.MAHOGANY.wood().get(), WoodBlockSets.MAHOGANY.woodFence().get(), WoodBlockSets.MAHOGANY.woodStairs().get(),
                        WoodBlockSets.MAHOGANY.woodSlab().get()),
                WoodBlockSets.MAHOGANY.leaves().get(),
                TagLoader.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(new LeaveVineDecorator(0.5f),
                TrunkVineDecorator.INSTANCE),
                Set.of(WoodBlockSets.MAHOGANY.woodFence().get(), WoodBlockSets.MAHOGANY.woodStairs().get(),
                        WoodBlockSets.MAHOGANY.woodSlab().get(), WoodBlockSets.MAHOGANY.leaves().get())
        ));

        register(context, MAHOGANY_4,  EdumiaFeatures.TREE_FROM_NBT.get(), new TreeFromStructureNBTConfig(
                Edumia.location("features/trees/mahogany/mahogany_trunk_4"),
                Edumia.location("features/trees/mahogany/mahogany_canopy_4"),
                ConstantInt.of(5),
                BlockStateProvider.simple(WoodBlockSets.MAHOGANY.wood().get()),
                BlockStateProvider.simple(WoodBlockSets.MAHOGANY.leaves().get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                Set.of(WoodBlockSets.MAHOGANY.wood().get(), WoodBlockSets.MAHOGANY.woodFence().get(), WoodBlockSets.MAHOGANY.woodStairs().get(),
                        WoodBlockSets.MAHOGANY.woodSlab().get()),
                WoodBlockSets.MAHOGANY.leaves().get(),
                TagLoader.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(new LeaveVineDecorator(0.5f),
                TrunkVineDecorator.INSTANCE, new HangingBranchDecorator(0.25f,
                        BlockStateProvider.simple(WoodBlockSets.MAHOGANY.leaves().get().defaultBlockState()
                                .setValue(BlockStateProperties.PERSISTENT, true)),
                        BlockStateProvider.simple(WoodBlockSets.MAHOGANY.leaves().get().defaultBlockState()
                                .setValue(BlockStateProperties.PERSISTENT, true)))),
                Set.of(WoodBlockSets.MAHOGANY.woodFence().get(), WoodBlockSets.MAHOGANY.woodStairs().get(),
                        WoodBlockSets.MAHOGANY.woodSlab().get(), WoodBlockSets.MAHOGANY.leaves().get())
        ));

    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "tree/tropical/mahogany_tree/" + name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext< ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature,
                                                                                          FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
