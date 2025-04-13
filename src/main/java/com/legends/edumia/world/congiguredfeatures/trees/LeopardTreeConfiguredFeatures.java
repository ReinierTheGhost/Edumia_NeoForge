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
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.Set;

public class LeopardTreeConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> LEOPARD_1 = registerKey("leopard_1");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LEOPARD_2 = registerKey("leopard_2");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LEOPARD_3 = registerKey("leopard_3");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LEOPARD_4 = registerKey("leopard_4");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
        HolderGetter<ConfiguredFeature<?, ?>> holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);
        HolderGetter<PlacedFeature> registryEntryLookup = context.lookup(Registries.PLACED_FEATURE);
        register(context, LEOPARD_1,  EdumiaFeatures.TREE_FROM_NBT.get(), new TreeFromStructureNBTConfig(
                Edumia.location("features/trees/leopard/leopard_trunk_1"),
                Edumia.location("features/trees/leopard/leopard_canopy_1"),
                ConstantInt.of(8),
                BlockStateProvider.simple(WoodBlockSets.GHOST_GUM.wood().get()),
                BlockStateProvider.simple(ModNatureBlocks.GHOST_GUM_LEAVES.get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                Set.of(WoodBlockSets.GHOST_GUM.wood().get(), WoodBlockSets.GHOST_GUM.woodFence().get(), WoodBlockSets.GHOST_GUM.woodStairs().get(),
                        WoodBlockSets.GHOST_GUM.woodSlab().get()),
                ModNatureBlocks.GHOST_GUM_LEAVES.get(),
                TagLoader.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(),
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
                TagLoader.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(),
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
                TagLoader.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(),
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
                TagLoader.Blocks.GROUND_MAHOGANY_SAPLING, 5, ImmutableList.of(),
                Set.of(WoodBlockSets.GHOST_GUM.log().get(), WoodBlockSets.GHOST_GUM.woodFence().get(), WoodBlockSets.GHOST_GUM.woodStairs().get(),
                        WoodBlockSets.GHOST_GUM.woodSlab().get(), ModNatureBlocks.GHOST_GUM_LEAVES.get())
        ));

    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "tree/tropical/leopard_tree/" + name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext< ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature,
                                                                                          FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
