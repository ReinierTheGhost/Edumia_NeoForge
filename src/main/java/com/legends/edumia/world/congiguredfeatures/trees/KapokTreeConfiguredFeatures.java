package com.legends.edumia.world.congiguredfeatures.trees;

import com.google.common.collect.ImmutableList;
import com.legends.edumia.Edumia;
import com.legends.edumia.blocks.blocksets.ModNatureBlocks;
import com.legends.edumia.blocks.blocksets.WoodBlockSets;
import com.legends.edumia.core.TagLoader;
import com.legends.edumia.world.features.EdumiaFeatures;
import com.legends.edumia.world.features.treesnbt.TreeFromStructureNBTConfig;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TrunkVineDecorator;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.Set;

public class KapokTreeConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> KAPOK_1 = registerKey("kapok_1");
    public static final ResourceKey<ConfiguredFeature<?, ?>> KAPOK_2 = registerKey("kapok_2");
    public static final ResourceKey<ConfiguredFeature<?, ?>> KAPOK_3 = registerKey("kapok_3");
    public static final ResourceKey<ConfiguredFeature<?, ?>> KAPOK_4 = registerKey("kapok_4");
    public static final ResourceKey<ConfiguredFeature<?, ?>> KAPOK_5 = registerKey("kapok_5");
    public static final ResourceKey<ConfiguredFeature<?, ?>> KAPOK_6 = registerKey("kapok_6");
    public static final ResourceKey<ConfiguredFeature<?, ?>> KAPOK_7 = registerKey("kapok_7");
    public static final ResourceKey<ConfiguredFeature<?, ?>> KAPOK_8 = registerKey("kapok_8");
    public static final ResourceKey<ConfiguredFeature<?, ?>> KAPOK_9 = registerKey("kapok_9");
    public static final ResourceKey<ConfiguredFeature<?, ?>> KAPOK_10 = registerKey("kapok_10");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
        HolderGetter<ConfiguredFeature<?, ?>> holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);
        HolderGetter<PlacedFeature> registryEntryLookup = context.lookup(Registries.PLACED_FEATURE);
        
        register(context, KAPOK_1,  EdumiaFeatures.TREE_FROM_NBT.get(), new TreeFromStructureNBTConfig(
                Edumia.location("features/trees/kapok/kapok_trunk_1"),
                Edumia.location("features/trees/kapok/kapok_canopy_1"),
                ConstantInt.of(28),
                BlockStateProvider.simple(Blocks.JUNGLE_WOOD),
                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                Set.of(Blocks.JUNGLE_WOOD, WoodBlockSets.BLACK_OAK.woodFence().get(), WoodBlockSets.JUNGLE.woodStairs().get(),
                        WoodBlockSets.JUNGLE.woodSlab().get()),
                Blocks.JUNGLE_LEAVES,
                TagLoader.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(new LeaveVineDecorator(0.5f),
                TrunkVineDecorator.INSTANCE),
                Set.of(WoodBlockSets.JUNGLE.woodFence().get(), WoodBlockSets.JUNGLE.woodStairs().get(),
                        WoodBlockSets.JUNGLE.woodSlab().get(), WoodBlockSets.BANANA.leaves().get(), Blocks.MANGROVE_ROOTS, Blocks.JUNGLE_LEAVES)
        ));

        register(context, KAPOK_2,  EdumiaFeatures.TREE_FROM_NBT.get(), new TreeFromStructureNBTConfig(
                Edumia.location("features/trees/kapok/kapok_trunk_2"),
                Edumia.location("features/trees/kapok/kapok_canopy_2"),
                ConstantInt.of(13),
                BlockStateProvider.simple(Blocks.JUNGLE_WOOD),
                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                Set.of(Blocks.JUNGLE_WOOD, WoodBlockSets.JUNGLE.woodFence().get(), WoodBlockSets.JUNGLE.woodStairs().get(),
                        WoodBlockSets.JUNGLE.woodSlab().get()),
                Blocks.JUNGLE_LEAVES,
                TagLoader.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(new LeaveVineDecorator(0.5f),
                TrunkVineDecorator.INSTANCE),
                Set.of(WoodBlockSets.JUNGLE.woodFence().get(), WoodBlockSets.JUNGLE.woodStairs().get(),
                        WoodBlockSets.JUNGLE.woodSlab().get(), WoodBlockSets.BANANA.leaves().get(), Blocks.MANGROVE_ROOTS, Blocks.JUNGLE_LEAVES)
        ));

        register(context, KAPOK_3,  EdumiaFeatures.TREE_FROM_NBT.get(), new TreeFromStructureNBTConfig(
                Edumia.location("features/trees/kapok/kapok_trunk_3"),
                Edumia.location("features/trees/kapok/kapok_canopy_3"),
                ConstantInt.of(12),
                BlockStateProvider.simple(Blocks.JUNGLE_WOOD),
                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                Set.of(Blocks.JUNGLE_WOOD, WoodBlockSets.JUNGLE.woodFence().get(), WoodBlockSets.JUNGLE.woodStairs().get(),
                        WoodBlockSets.JUNGLE.woodSlab().get()),
                Blocks.JUNGLE_LEAVES,
                TagLoader.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(new LeaveVineDecorator(0.5f),
                TrunkVineDecorator.INSTANCE),
                Set.of(WoodBlockSets.JUNGLE.woodFence().get(), WoodBlockSets.JUNGLE.woodStairs().get(),
                        WoodBlockSets.JUNGLE.woodSlab().get(), WoodBlockSets.BANANA.leaves().get(), Blocks.MANGROVE_ROOTS, Blocks.JUNGLE_LEAVES)
        ));

        register(context, KAPOK_4,  EdumiaFeatures.TREE_FROM_NBT.get(), new TreeFromStructureNBTConfig(
                Edumia.location("features/trees/kapok/kapok_trunk_4"),
                Edumia.location("features/trees/kapok/kapok_canopy_4"),
                ConstantInt.of(27),
                BlockStateProvider.simple(Blocks.JUNGLE_WOOD),
                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                Set.of(Blocks.JUNGLE_WOOD, WoodBlockSets.JUNGLE.woodFence().get(), WoodBlockSets.JUNGLE.woodStairs().get(),
                        WoodBlockSets.JUNGLE.woodSlab().get()),
                Blocks.JUNGLE_LEAVES,
                TagLoader.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(new LeaveVineDecorator(0.5f),
                TrunkVineDecorator.INSTANCE),
                Set.of(WoodBlockSets.JUNGLE.woodFence().get(), WoodBlockSets.JUNGLE.woodStairs().get(),
                        WoodBlockSets.JUNGLE.woodSlab().get(), WoodBlockSets.BANANA.leaves().get(), Blocks.MANGROVE_ROOTS, Blocks.JUNGLE_LEAVES)
        ));

        register(context, KAPOK_5,  EdumiaFeatures.TREE_FROM_NBT.get(), new TreeFromStructureNBTConfig(
                Edumia.location("features/trees/kapok/kapok_trunk_5"),
                Edumia.location("features/trees/kapok/kapok_canopy_5"),
                ConstantInt.of(11),
                BlockStateProvider.simple(Blocks.JUNGLE_WOOD),
                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                Set.of(Blocks.JUNGLE_WOOD, WoodBlockSets.JUNGLE.woodFence().get(), WoodBlockSets.JUNGLE.woodStairs().get(),
                        WoodBlockSets.JUNGLE.woodSlab().get()),
                Blocks.JUNGLE_LEAVES,
                TagLoader.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(new LeaveVineDecorator(0.5f),
                TrunkVineDecorator.INSTANCE),
                Set.of(Blocks.JUNGLE_LOG, WoodBlockSets.JUNGLE.woodFence().get(), WoodBlockSets.JUNGLE.woodStairs().get(),
                        WoodBlockSets.JUNGLE.woodSlab().get(), WoodBlockSets.BANANA.leaves().get(), Blocks.MANGROVE_ROOTS, Blocks.JUNGLE_LEAVES)
        ));

        register(context, KAPOK_6,  EdumiaFeatures.TREE_FROM_NBT.get(), new TreeFromStructureNBTConfig(
                Edumia.location("features/trees/kapok/kapok_trunk_6"),
                Edumia.location("features/trees/kapok/kapok_canopy_6"),
                ConstantInt.of(21),
                BlockStateProvider.simple(Blocks.JUNGLE_WOOD),
                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                Set.of(Blocks.JUNGLE_WOOD, WoodBlockSets.JUNGLE.woodFence().get(), WoodBlockSets.JUNGLE.woodStairs().get(),
                        WoodBlockSets.JUNGLE.woodSlab().get()),
                Blocks.JUNGLE_LEAVES,
                TagLoader.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(new LeaveVineDecorator(0.5f),
                TrunkVineDecorator.INSTANCE),
                Set.of(Blocks.JUNGLE_LOG, WoodBlockSets.JUNGLE.woodFence().get(), WoodBlockSets.JUNGLE.woodStairs().get(),
                        WoodBlockSets.JUNGLE.woodSlab().get(), WoodBlockSets.BANANA.leaves().get(), Blocks.MANGROVE_ROOTS, Blocks.JUNGLE_LEAVES)
        ));

        register(context, KAPOK_7,  EdumiaFeatures.TREE_FROM_NBT.get(), new TreeFromStructureNBTConfig(
                Edumia.location("features/trees/kapok/kapok_trunk_7"),
                Edumia.location("features/trees/kapok/kapok_canopy_7"),
                ConstantInt.of(21),
                BlockStateProvider.simple(Blocks.JUNGLE_WOOD),
                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                Set.of(Blocks.JUNGLE_WOOD, WoodBlockSets.JUNGLE.woodFence().get(), WoodBlockSets.JUNGLE.woodStairs().get(),
                        WoodBlockSets.JUNGLE.woodSlab().get()),
                Blocks.JUNGLE_LEAVES,
                TagLoader.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(new LeaveVineDecorator(0.5f),
                TrunkVineDecorator.INSTANCE),
                Set.of(Blocks.JUNGLE_LOG, WoodBlockSets.JUNGLE.woodFence().get(), WoodBlockSets.JUNGLE.woodStairs().get(),
                        WoodBlockSets.JUNGLE.woodSlab().get(), WoodBlockSets.BANANA.leaves().get(), Blocks.MANGROVE_ROOTS, Blocks.JUNGLE_LEAVES)
        ));

        register(context, KAPOK_8,  EdumiaFeatures.TREE_FROM_NBT.get(), new TreeFromStructureNBTConfig(
                Edumia.location("features/trees/kapok/kapok_trunk_8"),
                Edumia.location("features/trees/kapok/kapok_canopy_8"),
                ConstantInt.of(17),
                BlockStateProvider.simple(Blocks.JUNGLE_WOOD),
                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                Set.of(Blocks.JUNGLE_WOOD, WoodBlockSets.JUNGLE.woodFence().get(), WoodBlockSets.JUNGLE.woodStairs().get(),
                        WoodBlockSets.JUNGLE.woodSlab().get()),
                Blocks.JUNGLE_LEAVES,
                TagLoader.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(new LeaveVineDecorator(0.5f),
                TrunkVineDecorator.INSTANCE),
                Set.of(Blocks.JUNGLE_LOG, WoodBlockSets.JUNGLE.woodFence().get(), WoodBlockSets.JUNGLE.woodStairs().get(),
                        WoodBlockSets.JUNGLE.woodSlab().get(), WoodBlockSets.BANANA.leaves().get(), Blocks.MANGROVE_ROOTS, Blocks.JUNGLE_LEAVES)
        ));

        register(context, KAPOK_9,  EdumiaFeatures.TREE_FROM_NBT.get(), new TreeFromStructureNBTConfig(
                Edumia.location("features/trees/kapok/kapok_trunk_9"),
                Edumia.location("features/trees/kapok/kapok_canopy_9"),
                ConstantInt.of(29),
                BlockStateProvider.simple(Blocks.JUNGLE_WOOD),
                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                Set.of(Blocks.JUNGLE_WOOD, WoodBlockSets.JUNGLE.woodFence().get(), WoodBlockSets.JUNGLE.woodStairs().get(),
                        WoodBlockSets.JUNGLE.woodSlab().get()),
                Blocks.JUNGLE_LEAVES,
                TagLoader.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(new LeaveVineDecorator(0.5f),
                TrunkVineDecorator.INSTANCE),
                Set.of(Blocks.JUNGLE_LOG, WoodBlockSets.JUNGLE.woodFence().get(), WoodBlockSets.JUNGLE.woodStairs().get(),
                        WoodBlockSets.JUNGLE.woodSlab().get(), WoodBlockSets.BANANA.leaves().get(), Blocks.MANGROVE_ROOTS, Blocks.JUNGLE_LEAVES)
        ));

        register(context, KAPOK_10,  EdumiaFeatures.TREE_FROM_NBT.get(), new TreeFromStructureNBTConfig(
                Edumia.location("features/trees/kapok/kapok_trunk_10"),
                Edumia.location("features/trees/kapok/kapok_canopy_10"),
                ConstantInt.of(28),
                BlockStateProvider.simple(Blocks.JUNGLE_WOOD),
                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                Set.of(Blocks.JUNGLE_WOOD, WoodBlockSets.JUNGLE.woodFence().get(), WoodBlockSets.JUNGLE.woodStairs().get(),
                        WoodBlockSets.JUNGLE.woodSlab().get()),
                Blocks.JUNGLE_LEAVES,
                TagLoader.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(new LeaveVineDecorator(0.5f),
                TrunkVineDecorator.INSTANCE),
                Set.of(Blocks.JUNGLE_LOG, WoodBlockSets.JUNGLE.woodFence().get(), WoodBlockSets.JUNGLE.woodStairs().get(),
                        WoodBlockSets.JUNGLE.woodSlab().get(), WoodBlockSets.BANANA.leaves().get(), Blocks.MANGROVE_ROOTS, Blocks.JUNGLE_LEAVES)
        ));

    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "tree/tropical/kapok_tree/" + name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext< ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature,
                                                                                          FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
