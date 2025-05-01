package com.legends.edumia.world.placedfeatures.trees;

import com.legends.edumia.Edumia;
import com.legends.edumia.core.blocksets.WoodBlockSets;
import com.legends.edumia.world.congiguredfeatures.trees.BorealTreeConfiguredFeatures;
import com.legends.edumia.world.placedfeatures.ModPlacedFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class BorealTreePlacedFeatures {

    public static final ResourceKey<PlacedFeature> COMMON_LARCH_PLACED_TREE_KEY = registerKey("larch/abundant_larch_tree");
    public static final ResourceKey<PlacedFeature> LARCH_PLACED_TREE_KEY = registerKey("larch/larch_tree");
    public static final ResourceKey<PlacedFeature> SPARSE_LARCH_PLACED_TREE_KEY = registerKey("larch/sparse_larch_tree");

    public static final ResourceKey<PlacedFeature> ABUNDANT_PINE_PLACED_TREE_KEY = registerKey("pine/abundant_pine_tree");
    public static final ResourceKey<PlacedFeature> COMMON_PINE_PLACED_TREE_KEY = registerKey("pine/common_pine_tree");
    public static final ResourceKey<PlacedFeature> PINE_PLACED_TREE_KEY = registerKey("pine/pine_tree");
    public static final ResourceKey<PlacedFeature> DEAD_PINE_PLACED_TREE_KEY = registerKey("pine/dead_pine_tree");
    public static final ResourceKey<PlacedFeature> SPARSE_PINE_PLACED_TREE_KEY = registerKey("pine/sparse_pine_tree");
    public static final ResourceKey<PlacedFeature> FOOTHILLS_SPRUCE_PLACED_TREE_KEY = registerKey("spruce/foothills_spruce_tree");
    public static final ResourceKey<PlacedFeature> COMMON_SPRUCE_PLACED_TREE_KEY = registerKey("spruce/common_spruce_tree");
    public static final ResourceKey<PlacedFeature> SPRUCE_PLACED_TREE_KEY = registerKey("spruce/spruce_tree");
    public static final ResourceKey<PlacedFeature> MEGA_SPRUCE_TREE_LIST = registerKey("list/spruce/mega_spruce_tree");
    public static final ResourceKey<PlacedFeature> COMMON_SPRUCE_BUSH_PLACED_TREE_KEY = registerKey("spruce/common_spruce_bush_tree");
    public static final ResourceKey<PlacedFeature> SPRUCE_BUSH_PLACED_TREE_KEY = registerKey("spruce/spruce_bush_tree");
    public static final ResourceKey<PlacedFeature> SCARCE_SPRUCE_PLACED_TREE_KEY = registerKey("spruce/scarce_spruce_tree");
    public static final ResourceKey<PlacedFeature> RARE_SPRUCE_PLACED_TREE_KEY = registerKey("spruce/rare_spruce_tree");
    public static final ResourceKey<PlacedFeature> VERY_RARE_SPRUCE_PLACED_TREE_KEY = registerKey("spruce/very_rare_spruce_tree");

    public static final ResourceKey<PlacedFeature> SILVER_SPRUCE_TREE_LIST = registerKey("silver_spruce/silver_spruce_tree");

    public static final ResourceKey<PlacedFeature> LIST_FIR_TREE = registerKey("list/fir/fir_tree");


    public static void boostrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, LIST_FIR_TREE, configuredFeatureRegistryEntryLookup.getOrThrow(BorealTreeConfiguredFeatures.FIR_KEY),
                List.of());
        register(context, COMMON_LARCH_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(BorealTreeConfiguredFeatures.LARCH_TREE_KEY),
                VegetationPlacements.treePlacement(ModPlacedFeatures.frequentTree,
                        WoodBlockSets.PINE.sapling().get()));
        register(context, LARCH_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(BorealTreeConfiguredFeatures.LARCH_TREE_KEY),
                VegetationPlacements.treePlacement(ModPlacedFeatures.rareTree,
                        WoodBlockSets.PINE.sapling().get()));
        register(context, SPARSE_LARCH_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(BorealTreeConfiguredFeatures.LARCH_TREE_KEY),
                VegetationPlacements.treePlacement(ModPlacedFeatures.veryRareTree,
                        WoodBlockSets.PINE.sapling().get()));

        register(context, ABUNDANT_PINE_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(BorealTreeConfiguredFeatures.PINE_TREE_KEY),
                VegetationPlacements.treePlacement(ModPlacedFeatures.overflowing,
                        WoodBlockSets.PINE.sapling().get()));
        register(context, COMMON_PINE_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(BorealTreeConfiguredFeatures.PINE_TREE_KEY),
                VegetationPlacements.treePlacement(ModPlacedFeatures.frequentTree,
                        WoodBlockSets.PINE.sapling().get()));
        register(context, PINE_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(BorealTreeConfiguredFeatures.PINE_TREE_KEY),
                VegetationPlacements.treePlacement(ModPlacedFeatures.uncommonTree,
                        WoodBlockSets.PINE.sapling().get()));
        register(context, DEAD_PINE_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(BorealTreeConfiguredFeatures.DEAD_PINE_TREE_KEY),
                VegetationPlacements.treePlacement(ModPlacedFeatures.scarceTree,
                        WoodBlockSets.PINE.sapling().get()));
        register(context, SPARSE_PINE_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(BorealTreeConfiguredFeatures.PINE_TREE_KEY),
                VegetationPlacements.treePlacement(ModPlacedFeatures.rareTree,
                        WoodBlockSets.PINE.sapling().get()));

        register(context, FOOTHILLS_SPRUCE_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(BorealTreeConfiguredFeatures.SPRUCE_TREE_KEY),
                VegetationPlacements.treePlacement(ModPlacedFeatures.overflowing,
                        Blocks.SPRUCE_SAPLING));
        register(context, COMMON_SPRUCE_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(BorealTreeConfiguredFeatures.SPRUCE_TREE_KEY),
                VegetationPlacements.treePlacement(ModPlacedFeatures.frequentTree,
                        Blocks.SPRUCE_SAPLING));
        register(context, SPRUCE_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(BorealTreeConfiguredFeatures.SPRUCE_TREE_KEY),
                VegetationPlacements.treePlacement(ModPlacedFeatures.uncommonTree,
                        Blocks.SPRUCE_SAPLING));
        register(context, MEGA_SPRUCE_TREE_LIST, configuredFeatureRegistryEntryLookup.getOrThrow(BorealTreeConfiguredFeatures.MEGA_SPRUCE_TREE_KEY),
                List.of());
        register(context, SCARCE_SPRUCE_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(BorealTreeConfiguredFeatures.SPRUCE_TREE_KEY),
                VegetationPlacements.treePlacement(ModPlacedFeatures.scarceTree,
                        Blocks.SPRUCE_SAPLING));
        register(context, RARE_SPRUCE_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(BorealTreeConfiguredFeatures.SPRUCE_TREE_KEY),
                VegetationPlacements.treePlacement(ModPlacedFeatures.rareTree,
                        Blocks.SPRUCE_SAPLING));
        register(context, VERY_RARE_SPRUCE_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(BorealTreeConfiguredFeatures.SPRUCE_TREE_KEY),
                VegetationPlacements.treePlacement(ModPlacedFeatures.veryRareTree,
                        Blocks.SPRUCE_SAPLING));

        register(context, COMMON_SPRUCE_BUSH_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(BorealTreeConfiguredFeatures.SPRUCE_BUSH_TREE_KEY),
                VegetationPlacements.treePlacement(ModPlacedFeatures.frequentTree,
                        Blocks.SPRUCE_SAPLING));
        register(context, SPRUCE_BUSH_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(BorealTreeConfiguredFeatures.SPRUCE_BUSH_TREE_KEY),
                VegetationPlacements.treePlacement(ModPlacedFeatures.uncommonTree,
                        Blocks.SPRUCE_SAPLING));

        register(context, SILVER_SPRUCE_TREE_LIST, configuredFeatureRegistryEntryLookup.getOrThrow(BorealTreeConfiguredFeatures.SILVER_SPRUCE_TREE_KEY),
                List.of());
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "tree/boreal/" + name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
