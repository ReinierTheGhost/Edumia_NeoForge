package com.legends.edumia.world.congiguredfeatures.trees;

import com.google.common.collect.ImmutableList;
import com.legends.edumia.Edumia;
import com.legends.edumia.blocks.blocksets.ModNatureBlocks;
import com.legends.edumia.blocks.blocksets.WoodBlockSets;
import com.legends.edumia.utils.ModTags;
import com.legends.edumia.world.features.EdumiaFeatures;
import com.legends.edumia.world.features.treesnbt.TreeFromStructureNBTConfig;
import com.legends.edumia.world.trees.foliageplacer.*;
import com.legends.edumia.world.trees.treedecorators.HangingBranchDecorator;
import com.legends.edumia.world.trees.trunkplacers.*;
import com.legends.edumia.world.placedfeatures.trees.TropicalTreePlacedFeatures;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.TreePlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.BiasedToBottomInt;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleRandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaJungleFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.RandomSpreadFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AttachedToLeavesDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.CocoaDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TrunkVineDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.*;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RandomOffsetPlacement;
import org.checkerframework.checker.units.qual.C;

import java.util.List;
import java.util.Set;

public class TropicalTreeConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> BENDING_JUNGLE_TREE = registerKey("bending_jungle_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CANOPY_JUNGLE_TREE = registerKey("canopy_jungle_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_JUNGLE_TREE = registerKey("large_jungle_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEGA_JUNGLE_TREE = registerKey("mega_jungle_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEGA_JUNGLE_TREE_2 = registerKey("mega_jungle_tree_2");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEGA_JUNGLE_TREE_3 = registerKey("mega_jungle_tree_3");



    public static final ResourceKey<ConfiguredFeature<?, ?>> MAHOGANY_1 = registerKey("mahogany_1");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAHOGANY_2 = registerKey("mahogany_2");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAHOGANY_3 = registerKey("mahogany_3");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAHOGANY_4 = registerKey("mahogany_4");

    public static final ResourceKey<ConfiguredFeature<?, ?>> LEOPARD_1 = registerKey("leopard_1");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LEOPARD_2 = registerKey("leopard_2");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LEOPARD_3 = registerKey("leopard_3");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LEOPARD_4 = registerKey("leopard_4");

    public static final ResourceKey<ConfiguredFeature<?, ?>> MAHOGANY = registerKey("mahogany");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_MAHOGANY = registerKey("big_mahogany");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CANOPY_MAHOGANY_TREE = registerKey("canopy_mahogany_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_MAHOGANY_TREE = registerKey("large_mahogany_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAHOGANY_NBT = registerKey("mahogany_nbt");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAHOGANY_ROOTS_NBT = registerKey("mahogany_roots_nbt");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_PALM_TREE = registerKey("small_palm_tree");

    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_PARASOL_PALM = registerKey("big_parasol_palm");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_PARASOL_PALM = registerKey("small_parasol_palm");
    public static final ResourceKey<ConfiguredFeature<?, ?>> RANDOM_PALM = registerKey("random_palm");
    public static final ResourceKey<ConfiguredFeature<?, ?>> COCONUT_PALM = registerKey("coconut_palm");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PAPAYA_PALM = registerKey("papaya_palm");

    public static final ResourceKey<ConfiguredFeature<?, ?>> BANANA = registerKey("banana");

    public static final ResourceKey<ConfiguredFeature<?, ?>> MANGO = registerKey("mango");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MANGO_TWO = registerKey("mango_two");

    public static final ResourceKey<ConfiguredFeature<?, ?>> GENSAI_JUNGLE_TREES = registerKey("gensai_jungle_trees");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context){
        HolderGetter<ConfiguredFeature<?, ?>> holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);
        HolderGetter<PlacedFeature> registryEntryLookup = context.lookup(Registries.PLACED_FEATURE);
        Holder.Reference<PlacedFeature> jungle_bush = registryEntryLookup.getOrThrow(TreePlacements.JUNGLE_BUSH);
        Holder.Reference<PlacedFeature> jungle = registryEntryLookup.getOrThrow(TreePlacements.JUNGLE_TREE_CHECKED);
        Holder.Reference<PlacedFeature> mega_jungle_checked = registryEntryLookup.getOrThrow(TreePlacements.MEGA_JUNGLE_TREE_CHECKED);
        Holder.Reference<PlacedFeature> bending_jungle = registryEntryLookup.getOrThrow(TropicalTreePlacedFeatures.BENDING_JUNGLE_TREE);
        Holder.Reference<PlacedFeature> canopy_jungle = registryEntryLookup.getOrThrow(TropicalTreePlacedFeatures.CANOPY_JUNGLE_TREE);
        Holder.Reference<PlacedFeature> large_jungle = registryEntryLookup.getOrThrow(TropicalTreePlacedFeatures.LARGE_JUNGLE_TREE);
        Holder.Reference<PlacedFeature> mega_jungle = registryEntryLookup.getOrThrow(TropicalTreePlacedFeatures.MEGA_JUNGLE_TREE);

        Holder.Reference<PlacedFeature> mahogany = registryEntryLookup.getOrThrow(TropicalTreePlacedFeatures.MAHOGANY);
        Holder.Reference<PlacedFeature> big_mahogany = registryEntryLookup.getOrThrow(TropicalTreePlacedFeatures.BIG_MAHOGANY);
        Holder.Reference<PlacedFeature> canopy_mahogany = registryEntryLookup.getOrThrow(TropicalTreePlacedFeatures.CANOPY_MAHOGANY_TREE);
        Holder.Reference<PlacedFeature> large_mahogany = registryEntryLookup.getOrThrow(TropicalTreePlacedFeatures.LARGE_MAHOGANY_TREE);

        Holder.Reference<PlacedFeature> big_parasol = registryEntryLookup.getOrThrow(TropicalTreePlacedFeatures.BIG_PARASOL_PALM);
        Holder.Reference<PlacedFeature> small_parasol = registryEntryLookup.getOrThrow(TropicalTreePlacedFeatures.SMALL_PARASOL_PALM);
        Holder.Reference<PlacedFeature> random_palm = registryEntryLookup.getOrThrow(TropicalTreePlacedFeatures.RANDOM_PALM);
        Holder.Reference<PlacedFeature> coconut_palm = registryEntryLookup.getOrThrow(TropicalTreePlacedFeatures.COCONUT_PALM);
        Holder.Reference<PlacedFeature> papaya_palm = registryEntryLookup.getOrThrow(TropicalTreePlacedFeatures.PAPAYA_PALM);


        Holder.Reference<PlacedFeature> banana = registryEntryLookup.getOrThrow(TropicalTreePlacedFeatures.BANANA);

        Holder.Reference<PlacedFeature> mango = registryEntryLookup.getOrThrow(TropicalTreePlacedFeatures.MANGO);
        Holder.Reference<PlacedFeature> mango_two = registryEntryLookup.getOrThrow(TropicalTreePlacedFeatures.MANGO_TWO);





        register(context, BENDING_JUNGLE_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                new BendingTrunkPlacer(8, 6, 0, 8, UniformInt.of(1, 4)),
                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES),
                new RandomSpreadFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0),
                        ConstantInt.of(2),32),
                new TwoLayersFeatureSize(1, 0, 1))
                .decorators(ImmutableList.of(TrunkVineDecorator.INSTANCE, new LeaveVineDecorator(0.25f),
                        new CocoaDecorator(0.2f)))
                .ignoreVines().build());

        register(context, CANOPY_JUNGLE_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                new CanopyTrunkPlacer(12, 10, 2f, 0.87f, 5.0f, 3, 0.42f, -0.1f, 1,1),
                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES),
                new MegaJungleFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), 3),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(ImmutableList.of(TrunkVineDecorator.INSTANCE, new LeaveVineDecorator(0.25f)))
                .ignoreVines().build());

        register(context, LARGE_JUNGLE_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                new LargeTrunkPlacer(27, 3, 2.3f, 0.6f, 6.2f, 5, 0.25f),
                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES),
                new MegaJungleFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), 3),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(ImmutableList.of(TrunkVineDecorator.INSTANCE, new LeaveVineDecorator(0.25f)))
                .ignoreVines().build());

        register(context, MEGA_JUNGLE_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                new LargeTrunkPlacer(70, 16, 2.3f, 0.6f, 6.2f, 5, 0.25f),
                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                new OvalFoliagePlacer(3, ConstantInt.of(0), ConstantInt.of(3), 0.5f),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(ImmutableList.of(TrunkVineDecorator.INSTANCE, new LeaveVineDecorator(0.25f)))
                .ignoreVines().build());

        register(context, MEGA_JUNGLE_TREE_2, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                new FancyTrunkPlacer(24, 18, 12),
                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState().setValue(LeavesBlock.DISTANCE, 6)),
                new MegaJungleFoliagePlacer(ConstantInt.of(4), ConstantInt.of(2), 3),
                new TwoLayersFeatureSize(2, 3, 3))
                .decorators(ImmutableList.of(TrunkVineDecorator.INSTANCE, new LeaveVineDecorator(0.25f)))
                .ignoreVines().build());

        register(context, MEGA_JUNGLE_TREE_3, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                new FancyTrunkPlacer(20, 10, 10),
                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES),
                new MegaJungleFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), 2),
                new TwoLayersFeatureSize(1, 1, 2))
                .decorators(ImmutableList.of(TrunkVineDecorator.INSTANCE, new LeaveVineDecorator(0.25f)))
                .ignoreVines().build());

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
                ModTags.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(new LeaveVineDecorator(0.5f))
        ));

        register(context, MAHOGANY_ROOTS_NBT,  EdumiaFeatures.TREE_FROM_NBT.get(), new TreeFromStructureNBTConfig(
                Edumia.location("features/trees/mahogany/mahogany_tree_trunk1_roots"),
                Edumia.location("features/trees/mahogany/mahogany_tree_canopy1"),
                BiasedToBottomInt.of(5, 15),
                BlockStateProvider.simple(WoodBlockSets.MAHOGANY.log().get()),
                BlockStateProvider.simple(WoodBlockSets.MAHOGANY.leaves().get()),
                WoodBlockSets.MAHOGANY.log(),
                WoodBlockSets.MAHOGANY.leaves(),
                ModTags.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(new LeaveVineDecorator(0.5f)),
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
                ModTags.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(new LeaveVineDecorator(0.5f),
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
                ModTags.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(new LeaveVineDecorator(0.5f),
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
                ModTags.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(new LeaveVineDecorator(0.5f),
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
                ModTags.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(new LeaveVineDecorator(0.5f),
                TrunkVineDecorator.INSTANCE, new HangingBranchDecorator(0.25f,
                        BlockStateProvider.simple(WoodBlockSets.MAHOGANY.leaves().get().defaultBlockState()
                                .setValue(BlockStateProperties.PERSISTENT, true)),
                        BlockStateProvider.simple(WoodBlockSets.MAHOGANY.leaves().get().defaultBlockState()
                                .setValue(BlockStateProperties.PERSISTENT, true)))),
                Set.of(WoodBlockSets.MAHOGANY.woodFence().get(), WoodBlockSets.MAHOGANY.woodStairs().get(),
                        WoodBlockSets.MAHOGANY.woodSlab().get(), WoodBlockSets.MAHOGANY.leaves().get())
        ));

        register(context, LEOPARD_1,  EdumiaFeatures.TREE_FROM_NBT.get(), new TreeFromStructureNBTConfig(
                Edumia.location("features/trees/leopard/leopard_trunk_1"),
                Edumia.location("features/trees/leopard/leopard_canopy_1"),
                ConstantInt.of(8),
                BlockStateProvider.simple(WoodBlockSets.GHOST_GUM.wood().get()),
                BlockStateProvider.simple(ModNatureBlocks.GHOST_GUM_LEAVES.get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                Set.of(WoodBlockSets.GHOST_GUM.wood().get(), WoodBlockSets.GHOST_GUM.woodFence().get(), WoodBlockSets.GHOST_GUM.woodStairs().get(),
                        WoodBlockSets.GHOST_GUM.woodSlab().get()),
                ModNatureBlocks.GHOST_GUM_LEAVES.get(),
                ModTags.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(),
                Set.of(WoodBlockSets.GHOST_GUM.log().get(), WoodBlockSets.GHOST_GUM.woodFence().get(), WoodBlockSets.GHOST_GUM.woodStairs().get(),
                        WoodBlockSets.GHOST_GUM.woodSlab().get(), ModNatureBlocks.GHOST_GUM_LEAVES.get())
        ));

        register(context, LEOPARD_2,  EdumiaFeatures.TREE_FROM_NBT.get(), new TreeFromStructureNBTConfig(
                Edumia.location("features/trees/leopard/leopard_trunk_2"),
                Edumia.location("features/trees/leopard/leopard_canopy_2"),
                ConstantInt.of(4),
                BlockStateProvider.simple(WoodBlockSets.GHOST_GUM.wood().get()),
                BlockStateProvider.simple(ModNatureBlocks.GHOST_GUM_LEAVES.get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                Set.of(WoodBlockSets.GHOST_GUM.wood().get(), WoodBlockSets.GHOST_GUM.woodFence().get(), WoodBlockSets.GHOST_GUM.woodStairs().get(),
                        WoodBlockSets.GHOST_GUM.woodSlab().get()),
                ModNatureBlocks.GHOST_GUM_LEAVES.get(),
                ModTags.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(),
                Set.of(WoodBlockSets.GHOST_GUM.log().get(), WoodBlockSets.GHOST_GUM.woodFence().get(), WoodBlockSets.GHOST_GUM.woodStairs().get(),
                        WoodBlockSets.GHOST_GUM.woodSlab().get(), ModNatureBlocks.GHOST_GUM_LEAVES.get())
        ));

        register(context, LEOPARD_3,  EdumiaFeatures.TREE_FROM_NBT.get(), new TreeFromStructureNBTConfig(
                Edumia.location("features/trees/leopard/leopard_trunk_3"),
                Edumia.location("features/trees/leopard/leopard_canopy_3"),
                ConstantInt.of(5),
                BlockStateProvider.simple(WoodBlockSets.GHOST_GUM.wood().get()),
                BlockStateProvider.simple(ModNatureBlocks.GHOST_GUM_LEAVES.get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                Set.of(WoodBlockSets.GHOST_GUM.wood().get(), WoodBlockSets.GHOST_GUM.woodFence().get(), WoodBlockSets.GHOST_GUM.woodStairs().get(),
                        WoodBlockSets.GHOST_GUM.woodSlab().get()),
                ModNatureBlocks.GHOST_GUM_LEAVES.get(),
                ModTags.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(),
                Set.of(WoodBlockSets.GHOST_GUM.log().get(), WoodBlockSets.GHOST_GUM.woodFence().get(), WoodBlockSets.GHOST_GUM.woodStairs().get(),
                        WoodBlockSets.GHOST_GUM.woodSlab().get(), ModNatureBlocks.GHOST_GUM_LEAVES.get())
        ));

        register(context, LEOPARD_4,  EdumiaFeatures.TREE_FROM_NBT.get(), new TreeFromStructureNBTConfig(
                Edumia.location("features/trees/leopard/leopard_trunk_4"),
                Edumia.location("features/trees/leopard/leopard_canopy_4"),
                ConstantInt.of(4),
                BlockStateProvider.simple(WoodBlockSets.GHOST_GUM.wood().get()),
                BlockStateProvider.simple(ModNatureBlocks.GHOST_GUM_LEAVES.get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                Set.of(WoodBlockSets.GHOST_GUM.wood().get(), WoodBlockSets.GHOST_GUM.woodFence().get(), WoodBlockSets.GHOST_GUM.woodStairs().get(),
                        WoodBlockSets.GHOST_GUM.woodSlab().get()),
                ModNatureBlocks.GHOST_GUM_LEAVES.get(),
                ModTags.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(),
                Set.of(WoodBlockSets.GHOST_GUM.log().get(), WoodBlockSets.GHOST_GUM.woodFence().get(), WoodBlockSets.GHOST_GUM.woodStairs().get(),
                        WoodBlockSets.GHOST_GUM.woodSlab().get(), ModNatureBlocks.GHOST_GUM_LEAVES.get())
        ));

        register(context, SMALL_PALM_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.PALM.log().get()),
                new PleodendronTrunkPlacer(9, 6, 7),
                BlockStateProvider.simple(WoodBlockSets.PALM.leaves().get()),
                new PleodendronFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 2),
                new TwoLayersFeatureSize(1, 0, 1))
                .dirt(BlockStateProvider.simple(Blocks.ROOTED_DIRT)).ignoreVines().build());


        register(context, BIG_PARASOL_PALM, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.PALM.log().get()),
                new SlantedTrunkPlacer(9, 6, 7),
                BlockStateProvider.simple(WoodBlockSets.PALM.leaves().get()),
                new LargeParasolPalmFoliagePlacer(),
                new TwoLayersFeatureSize(1, 0, 1))
                .dirt(BlockStateProvider.simple(Blocks.ROOTED_DIRT)).ignoreVines().build());

        register(context, SMALL_PARASOL_PALM, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.PALM.log().get()),
                new SlantedTrunkPlacer(6, 4, 5),
                BlockStateProvider.simple(WoodBlockSets.PALM.leaves().get()),
                new ParasolPalmFoliagePlacer(),
                new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());

        register(context, RANDOM_PALM, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.PALM.log().get()),
                new PalmTrunkPlacer(10, 2, 0.02f, 0.07f, 0),
                BlockStateProvider.simple(WoodBlockSets.PALM.leaves().get()),
                new PalmFoliagePlacer(4, ConstantInt.of(0), ConstantInt.of(1), -0.3f, 0.3f),
                new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());

        register(context, COCONUT_PALM, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.PALM.log().get()),
                new PalmTrunkPlacer(10, 2, 0.02f, 0.07f, 0),
                BlockStateProvider.simple(WoodBlockSets.PALM.leaves().get()),
                new CoconutFoliagePlacer( ConstantInt.of(4), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());

        register(context, PAPAYA_PALM, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.PALM.log().get()),
                new PalmTrunkPlacer(10, 2, 0.02f, 0.07f, 0),
                BlockStateProvider.simple(WoodBlockSets.PALM.leaves().get()),
                new PapayaFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());

        register(context, BANANA, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.BANANA.log().get()),
                new StraightTrunkPlacer(4, 1, 0),
                BlockStateProvider.simple(WoodBlockSets.BANANA.leaves().get()),
                new BananaFoliagePlacer(0f, 0f),
                new TwoLayersFeatureSize(0, 1, 0)).build());

        register(context, MANGO, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.MANGO.log().get()),
                new StraightTrunkPlacer(4, 2, 0),
                BlockStateProvider.simple(WoodBlockSets.MANGO.leaves().get()),
                new TropicalFruitFoliagePlacer(),
                new TwoLayersFeatureSize(0, 1, 0)).build());

        register(context, MANGO_TWO, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(WoodBlockSets.MANGO.log().get()),
                new DesertTrunkPlacer(4, 2, 0, WoodBlockSets.MANGO.wood().get().defaultBlockState()),
                BlockStateProvider.simple(WoodBlockSets.MANGO.leaves().get()),
                new DesertFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 2),
                new TwoLayersFeatureSize(0, 1, 0)).build());


        register(context, GENSAI_JUNGLE_TREES, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(
                new WeightedPlacedFeature(mega_jungle_checked, 0.07f), new WeightedPlacedFeature(large_jungle, 0.08f),
                new WeightedPlacedFeature(canopy_jungle, 0.02f), new WeightedPlacedFeature(canopy_mahogany, 0.04f),
                new WeightedPlacedFeature(large_mahogany, 0.4f), new WeightedPlacedFeature(bending_jungle, 0.05f),
                new WeightedPlacedFeature(mega_jungle, 0.01f), new WeightedPlacedFeature(big_mahogany, 0.08f),
                new WeightedPlacedFeature(mahogany, 0.08f), new WeightedPlacedFeature(banana, 0.09f),
                new WeightedPlacedFeature(big_parasol, 0.03f), new WeightedPlacedFeature(small_parasol, 0.07f),
                new WeightedPlacedFeature(random_palm, 0.04f), new WeightedPlacedFeature(coconut_palm, 0.04f),
                new WeightedPlacedFeature(papaya_palm, 0.04f), new WeightedPlacedFeature(mango, 0.05F),
                new WeightedPlacedFeature(mango_two, 0.09f)), jungle_bush));

    }


    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Edumia.MOD_ID, "tree/tropical/" + name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext< ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature,
                                                                                          FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
