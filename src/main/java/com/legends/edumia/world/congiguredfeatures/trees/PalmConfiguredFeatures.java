package com.legends.edumia.world.congiguredfeatures.trees;

import com.google.common.collect.ImmutableList;
import com.legends.edumia.Edumia;
import com.legends.edumia.blocks.blocksets.WoodBlockSets;
import com.legends.edumia.core.TagLoader;
import com.legends.edumia.utils.ModTags;
import com.legends.edumia.world.features.EdumiaFeatures;
import com.legends.edumia.world.features.treesnbt.TreeFromStructureNBTConfig;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.Set;

public class PalmConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> GROUND_PALM_1 = registerKey("ground_palm_1");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GROUND_PALM_2 = registerKey("ground_palm_2");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GROUND_PALM_3 = registerKey("ground_palm_3");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GROUND_PALM_4 = registerKey("ground_palm_4");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GROUND_PALM_5 = registerKey("ground_palm_5");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GROUND_PALM_6 = registerKey("ground_palm_6");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GROUND_PALM_7 = registerKey("ground_palm_7");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GROUND_PALM_8 = registerKey("ground_palm_8");

    public static final ResourceKey<ConfiguredFeature<?, ?>> GROUP_PALM_1 = registerKey("group_palm_1");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
        HolderGetter<ConfiguredFeature<?, ?>> holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);
        HolderGetter<PlacedFeature> registryEntryLookup = context.lookup(Registries.PLACED_FEATURE);

        register(context, GROUND_PALM_1,  EdumiaFeatures.TREE_FROM_NBT.get(), new TreeFromStructureNBTConfig(
                Edumia.location("features/trees/ground_trunk"),
                Edumia.location("features/trees/ground_palm/ground_palm_1"),
                ConstantInt.of(1),
                BlockStateProvider.simple(WoodBlockSets.PALM.wood().get()),
                BlockStateProvider.simple(WoodBlockSets.PALM.leaves().get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                Set.of(WoodBlockSets.PALM.wood().get()),
                WoodBlockSets.PALM.leaves().get(),
                TagLoader.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(),
                Set.of(WoodBlockSets.PALM.leaves().get())
        ));

        register(context, GROUND_PALM_2,  EdumiaFeatures.TREE_FROM_NBT.get(), new TreeFromStructureNBTConfig(
                Edumia.location("features/trees/ground_trunk"),
                Edumia.location("features/trees/ground_palm/ground_palm_2"),
                ConstantInt.of(1),
                BlockStateProvider.simple(WoodBlockSets.PALM.wood().get()),
                BlockStateProvider.simple(WoodBlockSets.PALM.leaves().get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                Set.of(WoodBlockSets.PALM.wood().get()),
                WoodBlockSets.PALM.leaves().get(),
                TagLoader.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(),
                Set.of(WoodBlockSets.PALM.leaves().get())
        ));

        register(context, GROUND_PALM_3,  EdumiaFeatures.TREE_FROM_NBT.get(), new TreeFromStructureNBTConfig(
                Edumia.location("features/trees/ground_trunk"),
                Edumia.location("features/trees/ground_palm/ground_palm_3"),
                ConstantInt.of(1),
                BlockStateProvider.simple(WoodBlockSets.PALM.wood().get()),
                BlockStateProvider.simple(WoodBlockSets.PALM.leaves().get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                Set.of(WoodBlockSets.PALM.wood().get()),
                WoodBlockSets.PALM.leaves().get(),
                TagLoader.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(),
                Set.of(WoodBlockSets.PALM.leaves().get())
        ));

        register(context, GROUND_PALM_4,  EdumiaFeatures.TREE_FROM_NBT.get(), new TreeFromStructureNBTConfig(
                Edumia.location("features/trees/ground_trunk"),
                Edumia.location("features/trees/ground_palm/ground_palm_4"),
                ConstantInt.of(1),
                BlockStateProvider.simple(WoodBlockSets.PALM.wood().get()),
                BlockStateProvider.simple(WoodBlockSets.PALM.leaves().get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                Set.of(WoodBlockSets.PALM.wood().get()),
                WoodBlockSets.PALM.leaves().get(),
                TagLoader.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(),
                Set.of(WoodBlockSets.PALM.leaves().get())
        ));

        register(context, GROUND_PALM_5,  EdumiaFeatures.TREE_FROM_NBT.get(), new TreeFromStructureNBTConfig(
                Edumia.location("features/trees/ground_trunk"),
                Edumia.location("features/trees/ground_palm/ground_palm_5"),
                ConstantInt.of(1),
                BlockStateProvider.simple(WoodBlockSets.PALM.wood().get()),
                BlockStateProvider.simple(WoodBlockSets.PALM.leaves().get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                Set.of(WoodBlockSets.PALM.wood().get()),
                WoodBlockSets.PALM.leaves().get(),
                TagLoader.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(),
                Set.of(WoodBlockSets.PALM.leaves().get())
        ));

        register(context, GROUND_PALM_6,  EdumiaFeatures.TREE_FROM_NBT.get(), new TreeFromStructureNBTConfig(
                Edumia.location("features/trees/ground_trunk"),
                Edumia.location("features/trees/ground_palm/ground_palm_6"),
                ConstantInt.of(1),
                BlockStateProvider.simple(WoodBlockSets.PALM.wood().get()),
                BlockStateProvider.simple(WoodBlockSets.PALM.leaves().get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                Set.of(WoodBlockSets.PALM.wood().get()),
                WoodBlockSets.PALM.leaves().get(),
                TagLoader.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(),
                Set.of(WoodBlockSets.PALM.leaves().get())
        ));

        register(context, GROUND_PALM_7,  EdumiaFeatures.TREE_FROM_NBT.get(), new TreeFromStructureNBTConfig(
                Edumia.location("features/trees/ground_trunk"),
                Edumia.location("features/trees/ground_palm/ground_palm_7"),
                ConstantInt.of(1),
                BlockStateProvider.simple(WoodBlockSets.PALM.wood().get()),
                BlockStateProvider.simple(WoodBlockSets.PALM.leaves().get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                Set.of(WoodBlockSets.PALM.wood().get()),
                WoodBlockSets.PALM.leaves().get(),
                TagLoader.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(),
                Set.of(WoodBlockSets.PALM.leaves().get())
        ));

        register(context, GROUND_PALM_8,  EdumiaFeatures.TREE_FROM_NBT.get(), new TreeFromStructureNBTConfig(
                Edumia.location("features/trees/ground_trunk"),
                Edumia.location("features/trees/ground_palm/ground_palm_8"),
                ConstantInt.of(1),
                BlockStateProvider.simple(WoodBlockSets.PALM.wood().get()),
                BlockStateProvider.simple(WoodBlockSets.PALM.leaves().get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                Set.of(WoodBlockSets.PALM.wood().get()),
                WoodBlockSets.PALM.leaves().get(),
                TagLoader.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(),
                Set.of(WoodBlockSets.PALM.leaves().get())
        ));

        register(context, GROUP_PALM_1,  EdumiaFeatures.TREE_FROM_NBT.get(), new TreeFromStructureNBTConfig(
                Edumia.location("features/trees/ground_trunk"),
                Edumia.location("features/trees/group_palm/group_palm_1"),
                ConstantInt.of(1),
                BlockStateProvider.simple(WoodBlockSets.PALM.wood().get()),
                BlockStateProvider.simple(WoodBlockSets.PALM.leaves().get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                Set.of(WoodBlockSets.PALM.wood().get()),
                WoodBlockSets.PALM.leaves().get(),
                TagLoader.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(),
                Set.of(WoodBlockSets.PALM.leaves().get())
        ));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "tree/tropical/palm_tree/" + name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext< ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature,
                                                                                          FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
