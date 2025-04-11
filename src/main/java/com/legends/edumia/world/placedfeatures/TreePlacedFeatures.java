package com.legends.edumia.world.placedfeatures;

import com.legends.edumia.Edumia;
import com.legends.edumia.blocks.blocksets.ModNatureBlocks;
import com.legends.edumia.world.congiguredfeatures.trees.BeechTreeConfiguredFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class TreePlacedFeatures {

    public static final ResourceKey<PlacedFeature> COMMON_BEECH_PLACED_TREE_KEY = registerKey("tree/beech/common_beech_tree");
    public static final ResourceKey<PlacedFeature> BEECH_PLACED_TREE_KEY = registerKey("tree/beech/beech_tree_2");
    public static final ResourceKey<PlacedFeature> RARE_BEECH_PLACED_TREE_KEY = registerKey("tree/beech/rare_beech_tree");
    public static final ResourceKey<PlacedFeature> VERY_RARE_BEECH_PLACED_TREE_KEY = registerKey("tree/beech/very_rare_beech_tree");

    static PlacementModifier foothillsTree = PlacementUtils.countExtra(5, 0.5f, 1);
    static PlacementModifier abundantTree = PlacementUtils.countExtra(3, 0.5f, 1);
    static PlacementModifier frequentTree = PlacementUtils.countExtra(1, 0.5f, 1);
    static PlacementModifier commonTree = PlacementUtils.countExtra(1, 0.1f, 1);
    static PlacementModifier uncommonTree = PlacementUtils.countExtra(0, 0.5f, 1);
    static PlacementModifier scarceTree = PlacementUtils.countExtra(0, 0.25f, 1);
    static PlacementModifier rareTree = PlacementUtils.countExtra(0, 0.125f, 1);
    static PlacementModifier megaTree = PlacementUtils.countExtra(0, 0.1f, 1);
    static PlacementModifier veryRareTree = PlacementUtils.countExtra(0, 0.05f, 1);
    static PlacementModifier superRareTree = PlacementUtils.countExtra(0, 0.025f, 1);
    static PlacementModifier specialTree = PlacementUtils.countExtra(0, 0.01f, 1);

    public static final ResourceKey<PlacedFeature> TINY_BEECH_KEY = registerKey("tree/beech/tiny_beech_tree");
    public static final ResourceKey<PlacedFeature> BEECH_KEY = registerKey("tree/beech/beech_tree");
    public static final ResourceKey<PlacedFeature> BIG_BEECH_KEY = registerKey("tree/beech/big_beech_tree");



    public static void boostrap(BootstapContext<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, BEECH_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(BeechTreeConfiguredFeatures.BEECH_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.1f, 1),
                        ModNatureBlocks.BEECH_SAPLING.get()));
        register(context, BIG_BEECH_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(BeechTreeConfiguredFeatures.BIG_BEECH_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.1f, 1),
                        ModNatureBlocks.BEECH_SAPLING.get()));

        register(context, COMMON_BEECH_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(BeechTreeConfiguredFeatures.BEECH_KEY),
                VegetationPlacements.treePlacement(uncommonTree,
                        ModNatureBlocks.BEECH_SAPLING.get()));
        register(context, BEECH_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(BeechTreeConfiguredFeatures.BEECH_KEY),
                VegetationPlacements.treePlacement(rareTree,
                        ModNatureBlocks.BEECH_SAPLING.get()));
        register(context, RARE_BEECH_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(BeechTreeConfiguredFeatures.BEECH_KEY),
                VegetationPlacements.treePlacement(veryRareTree,
                        ModNatureBlocks.BEECH_SAPLING.get()));
        register(context, VERY_RARE_BEECH_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(BeechTreeConfiguredFeatures.BEECH_KEY),
                VegetationPlacements.treePlacement(superRareTree,
                        ModNatureBlocks.BEECH_SAPLING.get()));
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Edumia.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
