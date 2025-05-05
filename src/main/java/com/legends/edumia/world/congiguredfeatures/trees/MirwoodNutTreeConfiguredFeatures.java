package com.legends.edumia.world.congiguredfeatures.trees;

import com.google.common.collect.ImmutableList;
import com.legends.edumia.Edumia;
import com.legends.edumia.core.TagLoader;
import com.legends.edumia.core.blocksets.WoodBlockSets;
import com.legends.edumia.world.features.EdumiaFeatures;
import com.legends.edumia.world.features.treesnbt.TreeFromStructureNBTConfig;
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
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.CocoaDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TrunkVineDecorator;

import java.util.Collection;
import java.util.Set;

public class MirwoodNutTreeConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> MIRWOODNUT_1 = registerKey("mirwoodnut_1");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MIRWOODNUT_2 = registerKey("mirwoodnut_2");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MIRWOODNUT_3 = registerKey("mirwoodnut_3");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MIRWOODNUT_4 = registerKey("mirwoodnut_4");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MIRWOODNUT_5 = registerKey("mirwoodnut_5");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MIRWOODNUT_6 = registerKey("mirwoodnut_6");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MIRWOODNUT_7 = registerKey("mirwoodnut_7");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MIRWOODNUT_8 = registerKey("mirwoodnut_8");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MIRWOODNUT_9 = registerKey("mirwoodnut_9");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MIRWOODNUT_10 = registerKey("mirwoodnut_10");


    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
        register(context, MIRWOODNUT_1,  EdumiaFeatures.TREE_FROM_NBT.get(), new TreeFromStructureNBTConfig(
                Edumia.location("features/trees/mirwoodnut/mirwoodnut_trunk_1"),
                Edumia.location("features/trees/mirwoodnut/mirwoodnut_canopy_1"),
                ConstantInt.of(13),
                BlockStateProvider.simple(WoodBlockSets.MIRWOODNUT.wood().get()),
                BlockStateProvider.simple(WoodBlockSets.MIRWOODNUT.leaves().get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                Set.of(WoodBlockSets.MIRWOODNUT.wood().get(), WoodBlockSets.MIRWOODNUT.woodFence().get(), WoodBlockSets.MIRWOODNUT.woodStairs().get(),
                        WoodBlockSets.MIRWOODNUT.woodSlab().get()),
                Blocks.GREEN_WOOL,
                TagLoader.Blocks.GROUND_MAHOGANY_SAPLING,
                5,
                ImmutableList.of(new LeaveVineDecorator(0.5f),
                TrunkVineDecorator.INSTANCE, new CocoaDecorator(0.5f)),
                Set.of(WoodBlockSets.MIRWOODNUT.woodFence().get(), WoodBlockSets.MIRWOODNUT.woodStairs().get(),
                        WoodBlockSets.MIRWOODNUT.woodSlab().get(), WoodBlockSets.BANANA.leaves().get(),
                        Blocks.MANGROVE_ROOTS, WoodBlockSets.MIRWOODNUT.leaves().get())
        ));
        register(context, MIRWOODNUT_2,  EdumiaFeatures.TREE_FROM_NBT.get(), new TreeFromStructureNBTConfig(
                Edumia.location("features/trees/mirwoodnut/mirwoodnut_trunk_2"),
                Edumia.location("features/trees/mirwoodnut/mirwoodnut_canopy_2"),
                ConstantInt.of(25),
                BlockStateProvider.simple(WoodBlockSets.MIRWOODNUT.wood().get()),
                BlockStateProvider.simple(WoodBlockSets.MIRWOODNUT.leaves().get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                Set.of(WoodBlockSets.MIRWOODNUT.wood().get(), WoodBlockSets.MIRWOODNUT.woodFence().get(), WoodBlockSets.MIRWOODNUT.woodStairs().get(),
                        WoodBlockSets.MIRWOODNUT.woodSlab().get()),
                Blocks.GREEN_WOOL,
                TagLoader.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(new LeaveVineDecorator(0.5f),
                TrunkVineDecorator.INSTANCE, new CocoaDecorator(0.5f)),
                Set.of(WoodBlockSets.MIRWOODNUT.woodFence().get(), WoodBlockSets.MIRWOODNUT.woodStairs().get(),
                        WoodBlockSets.MIRWOODNUT.woodSlab().get(), WoodBlockSets.BANANA.leaves().get(), Blocks.MANGROVE_ROOTS, WoodBlockSets.MIRWOODNUT.leaves().get())
        ));
        register(context, MIRWOODNUT_3,  EdumiaFeatures.TREE_FROM_NBT.get(), new TreeFromStructureNBTConfig(
                Edumia.location("features/trees/mirwoodnut/mirwoodnut_trunk_3"),
                Edumia.location("features/trees/mirwoodnut/mirwoodnut_canopy_3"),
                ConstantInt.of(5),
                BlockStateProvider.simple(WoodBlockSets.MIRWOODNUT.wood().get()),
                BlockStateProvider.simple(WoodBlockSets.MIRWOODNUT.leaves().get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                Set.of(WoodBlockSets.MIRWOODNUT.wood().get(), WoodBlockSets.MIRWOODNUT.woodFence().get(), WoodBlockSets.MIRWOODNUT.woodStairs().get(),
                        WoodBlockSets.MIRWOODNUT.woodSlab().get()),
                Blocks.GREEN_WOOL,
                TagLoader.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(new LeaveVineDecorator(0.5f),
                TrunkVineDecorator.INSTANCE, new CocoaDecorator(0.5f)),
                Set.of(WoodBlockSets.MIRWOODNUT.woodFence().get(), WoodBlockSets.MIRWOODNUT.woodStairs().get(),
                        WoodBlockSets.MIRWOODNUT.woodSlab().get(), WoodBlockSets.BANANA.leaves().get(), Blocks.MANGROVE_ROOTS, WoodBlockSets.MIRWOODNUT.leaves().get())
        ));
        register(context, MIRWOODNUT_4,  EdumiaFeatures.TREE_FROM_NBT.get(), new TreeFromStructureNBTConfig(
                Edumia.location("features/trees/mirwoodnut/mirwoodnut_trunk_4"),
                Edumia.location("features/trees/mirwoodnut/mirwoodnut_canopy_4"),
                ConstantInt.of(18),
                BlockStateProvider.simple(WoodBlockSets.MIRWOODNUT.wood().get()),
                BlockStateProvider.simple(WoodBlockSets.MIRWOODNUT.leaves().get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                Set.of(WoodBlockSets.MIRWOODNUT.wood().get(), WoodBlockSets.MIRWOODNUT.woodFence().get(), WoodBlockSets.MIRWOODNUT.woodStairs().get(),
                        WoodBlockSets.MIRWOODNUT.woodSlab().get()),
                Blocks.GREEN_WOOL,
                TagLoader.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(new LeaveVineDecorator(0.5f),
                TrunkVineDecorator.INSTANCE, new CocoaDecorator(0.5f)),
                Set.of(WoodBlockSets.MIRWOODNUT.woodFence().get(), WoodBlockSets.MIRWOODNUT.woodStairs().get(),
                        WoodBlockSets.MIRWOODNUT.woodSlab().get(), WoodBlockSets.BANANA.leaves().get(), Blocks.MANGROVE_ROOTS, WoodBlockSets.MIRWOODNUT.leaves().get())
        ));
        register(context, MIRWOODNUT_5,  EdumiaFeatures.TREE_FROM_NBT.get(), new TreeFromStructureNBTConfig(
                Edumia.location("features/trees/mirwoodnut/mirwoodnut_trunk_5"),
                Edumia.location("features/trees/mirwoodnut/mirwoodnut_canopy_5"),
                ConstantInt.of(11),
                BlockStateProvider.simple(WoodBlockSets.MIRWOODNUT.wood().get()),
                BlockStateProvider.simple(WoodBlockSets.MIRWOODNUT.leaves().get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                Set.of(WoodBlockSets.MIRWOODNUT.wood().get(), WoodBlockSets.MIRWOODNUT.woodFence().get(), WoodBlockSets.MIRWOODNUT.woodStairs().get(),
                        WoodBlockSets.MIRWOODNUT.woodSlab().get()),
                Blocks.GREEN_WOOL,
                TagLoader.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(new LeaveVineDecorator(0.5f),
                TrunkVineDecorator.INSTANCE, new CocoaDecorator(0.5f)),
                Set.of(WoodBlockSets.MIRWOODNUT.woodFence().get(), WoodBlockSets.MIRWOODNUT.woodStairs().get(),
                        WoodBlockSets.MIRWOODNUT.woodSlab().get(), WoodBlockSets.BANANA.leaves().get(), Blocks.MANGROVE_ROOTS, WoodBlockSets.MIRWOODNUT.leaves().get())
        ));
        register(context, MIRWOODNUT_6,  EdumiaFeatures.TREE_FROM_NBT.get(), new TreeFromStructureNBTConfig(
                Edumia.location("features/trees/mirwoodnut/mirwoodnut_trunk_6"),
                Edumia.location("features/trees/mirwoodnut/mirwoodnut_canopy_6"),
                ConstantInt.of(22),
                BlockStateProvider.simple(WoodBlockSets.MIRWOODNUT.wood().get()),
                BlockStateProvider.simple(WoodBlockSets.MIRWOODNUT.leaves().get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                Set.of(WoodBlockSets.MIRWOODNUT.wood().get(), WoodBlockSets.MIRWOODNUT.woodFence().get(), WoodBlockSets.MIRWOODNUT.woodStairs().get(),
                        WoodBlockSets.MIRWOODNUT.woodSlab().get()),
                Blocks.GREEN_WOOL,
                TagLoader.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(new LeaveVineDecorator(0.5f),
                TrunkVineDecorator.INSTANCE, new CocoaDecorator(0.5f)),
                Set.of(WoodBlockSets.MIRWOODNUT.woodFence().get(), WoodBlockSets.MIRWOODNUT.woodStairs().get(),
                        WoodBlockSets.MIRWOODNUT.woodSlab().get(), WoodBlockSets.BANANA.leaves().get(), Blocks.MANGROVE_ROOTS, WoodBlockSets.MIRWOODNUT.leaves().get())
        ));
        register(context, MIRWOODNUT_7,  EdumiaFeatures.TREE_FROM_NBT.get(), new TreeFromStructureNBTConfig(
                Edumia.location("features/trees/mirwoodnut/mirwoodnut_trunk_7"),
                Edumia.location("features/trees/mirwoodnut/mirwoodnut_canopy_7"),
                ConstantInt.of(10),
                BlockStateProvider.simple(WoodBlockSets.MIRWOODNUT.wood().get()),
                BlockStateProvider.simple(WoodBlockSets.MIRWOODNUT.leaves().get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                Set.of(WoodBlockSets.MIRWOODNUT.wood().get(), WoodBlockSets.MIRWOODNUT.woodFence().get(), WoodBlockSets.MIRWOODNUT.woodStairs().get(),
                        WoodBlockSets.MIRWOODNUT.woodSlab().get()),
                Blocks.GREEN_WOOL,
                TagLoader.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(new LeaveVineDecorator(0.5f),
                TrunkVineDecorator.INSTANCE, new CocoaDecorator(0.5f)),
                Set.of(WoodBlockSets.MIRWOODNUT.woodFence().get(), WoodBlockSets.MIRWOODNUT.woodStairs().get(),
                        WoodBlockSets.MIRWOODNUT.woodSlab().get(), WoodBlockSets.BANANA.leaves().get(), Blocks.MANGROVE_ROOTS, WoodBlockSets.MIRWOODNUT.leaves().get())
        ));
        register(context, MIRWOODNUT_8,  EdumiaFeatures.TREE_FROM_NBT.get(), new TreeFromStructureNBTConfig(
                Edumia.location("features/trees/mirwoodnut/mirwoodnut_trunk_8"),
                Edumia.location("features/trees/mirwoodnut/mirwoodnut_canopy_8"),
                ConstantInt.of(24),
                BlockStateProvider.simple(WoodBlockSets.MIRWOODNUT.wood().get()),
                BlockStateProvider.simple(WoodBlockSets.MIRWOODNUT.leaves().get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                Set.of(WoodBlockSets.MIRWOODNUT.wood().get(), WoodBlockSets.MIRWOODNUT.woodFence().get(), WoodBlockSets.MIRWOODNUT.woodStairs().get(),
                        WoodBlockSets.MIRWOODNUT.woodSlab().get()),
                Blocks.GREEN_WOOL,
                TagLoader.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(new LeaveVineDecorator(0.5f),
                TrunkVineDecorator.INSTANCE, new CocoaDecorator(0.5f)),
                Set.of(WoodBlockSets.MIRWOODNUT.woodFence().get(), WoodBlockSets.MIRWOODNUT.woodStairs().get(),
                        WoodBlockSets.MIRWOODNUT.woodSlab().get(), WoodBlockSets.BANANA.leaves().get(), Blocks.MANGROVE_ROOTS, WoodBlockSets.MIRWOODNUT.leaves().get())
        ));
        register(context, MIRWOODNUT_9,  EdumiaFeatures.TREE_FROM_NBT.get(), new TreeFromStructureNBTConfig(
                Edumia.location("features/trees/mirwoodnut/mirwoodnut_trunk_9"),
                Edumia.location("features/trees/mirwoodnut/mirwoodnut_canopy_9"),
                ConstantInt.of(16),
                BlockStateProvider.simple(WoodBlockSets.MIRWOODNUT.wood().get()),
                BlockStateProvider.simple(WoodBlockSets.MIRWOODNUT.leaves().get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                Set.of(WoodBlockSets.MIRWOODNUT.wood().get(), WoodBlockSets.MIRWOODNUT.woodFence().get(), WoodBlockSets.MIRWOODNUT.woodStairs().get(),
                        WoodBlockSets.MIRWOODNUT.woodSlab().get()),
                Blocks.GREEN_WOOL,
                TagLoader.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(new LeaveVineDecorator(0.5f),
                TrunkVineDecorator.INSTANCE, new CocoaDecorator(0.5f)),
                Set.of(WoodBlockSets.MIRWOODNUT.woodFence().get(), WoodBlockSets.MIRWOODNUT.woodStairs().get(),
                        WoodBlockSets.MIRWOODNUT.woodSlab().get(), WoodBlockSets.BANANA.leaves().get(), Blocks.MANGROVE_ROOTS, WoodBlockSets.MIRWOODNUT.leaves().get())
        ));
        register(context, MIRWOODNUT_10,  EdumiaFeatures.TREE_FROM_NBT.get(), new TreeFromStructureNBTConfig(
                Edumia.location("features/trees/mirwoodnut/mirwoodnut_trunk_10"),
                Edumia.location("features/trees/mirwoodnut/mirwoodnut_canopy_10"),
                ConstantInt.of(21),
                BlockStateProvider.simple(WoodBlockSets.MIRWOODNUT.wood().get()),
                BlockStateProvider.simple(WoodBlockSets.MIRWOODNUT.leaves().get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                Set.of(WoodBlockSets.MIRWOODNUT.wood().get(), WoodBlockSets.MIRWOODNUT.woodFence().get(), WoodBlockSets.MIRWOODNUT.woodStairs().get(),
                        WoodBlockSets.MIRWOODNUT.woodSlab().get()),
                Blocks.GREEN_WOOL,
                TagLoader.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(new LeaveVineDecorator(0.5f),
                TrunkVineDecorator.INSTANCE, new CocoaDecorator(0.5f)),
                Set.of(WoodBlockSets.MIRWOODNUT.woodFence().get(), WoodBlockSets.MIRWOODNUT.woodStairs().get(),
                        WoodBlockSets.MIRWOODNUT.woodSlab().get(), WoodBlockSets.BANANA.leaves().get(), Blocks.MANGROVE_ROOTS, WoodBlockSets.MIRWOODNUT.leaves().get())
        ));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "tree/tropical/mirwoodnut_tree/" + name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext< ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature,
                                                                                          FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
