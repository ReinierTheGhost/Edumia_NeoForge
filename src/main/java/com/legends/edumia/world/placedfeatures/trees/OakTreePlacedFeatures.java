package com.legends.edumia.world.placedfeatures.trees;

import com.legends.edumia.Edumia;
import com.legends.edumia.core.blocksets.WoodBlockSets;
import com.legends.edumia.world.congiguredfeatures.trees.OakTreeConfiguredFeatures;
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

public class OakTreePlacedFeatures {

    public static final ResourceKey<PlacedFeature> MEGA_DARK_OAK_PLACED_TREE_KEY = registerKey("dark/mega_dark_oak_tree");
    public static final ResourceKey<PlacedFeature> MEGA_DARK_OAK_PLACED_COMMON_TREE_KEY = registerKey("dark/mega_dark_oak_common_tree");

    public static final ResourceKey<PlacedFeature> LIST_SMALL_RED_OAK_PLACED_TREE_KEY = registerKey("red/list_small_red_oak_tree");
    public static final ResourceKey<PlacedFeature> LIST_RED_OAK_PLACED_TREE_KEY = registerKey("red/list_red_oak_tree");
    public static final ResourceKey<PlacedFeature> LIST_LARGE_RED_OAK_PLACED_TREE_KEY = registerKey("red/list_large_red_oak_tree");
    public static final ResourceKey<PlacedFeature> LIST_MEGA_RED_OAK_PLACED_TREE_KEY = registerKey("red/list_mega_red_oak_tree");
    public static final ResourceKey<PlacedFeature> LIST_COLOSSAL_RED_OAK_PLACED_TREE_KEY = registerKey("red/list_colossal_red_oak_tree");

    public static final ResourceKey<PlacedFeature> SMALL_RED_OAK_PLACED_TREE_KEY = registerKey("red/small_red_oak_tree");
    public static final ResourceKey<PlacedFeature> RED_OAK_PLACED_TREE_KEY = registerKey("red/red_oak_tree");
    public static final ResourceKey<PlacedFeature> SPARSE_RED_OAK_PLACED_TREE_KEY = registerKey("red/sparse_red_oak_tree");
    public static final ResourceKey<PlacedFeature> MEGA_RED_OAK_PLACED_TREE_KEY = registerKey("red/mega_red_oak_tree");

    public static final ResourceKey<PlacedFeature> LIST_SMALL_GREEN_OAK_PLACED_TREE_KEY = registerKey("green/list_small_green_oak_tree");
    public static final ResourceKey<PlacedFeature> LIST_GREEN_OAK_PLACED_TREE_KEY = registerKey("green/list_green_oak_tree");
    public static final ResourceKey<PlacedFeature> LIST_LARGE_GREEN_OAK_PLACED_TREE_KEY = registerKey("green/list_large_green_oak_tree");
    public static final ResourceKey<PlacedFeature> LIST_MEGA_GREEN_OAK_PLACED_TREE_KEY = registerKey("green/list_mega_green_oak_tree");
    public static final ResourceKey<PlacedFeature> LIST_COLOSSAL_GREEN_OAK_PLACED_TREE_KEY = registerKey("green/list_colossal_green_oak_tree");

    public static final ResourceKey<PlacedFeature> SMALL_GREEN_OAK_PLACED_TREE_KEY = registerKey("green/small_green_oak_tree");
    public static final ResourceKey<PlacedFeature> GREEN_OAK_PLACED_TREE_KEY = registerKey("green/green_oak_tree");
    public static final ResourceKey<PlacedFeature> SPARSE_GREEN_OAK_PLACED_TREE_KEY = registerKey("green/sparse_green_oak_tree");
    public static final ResourceKey<PlacedFeature> MEGA_GREEN_OAK_PLACED_TREE_KEY = registerKey("green/mega_green_oak_tree");

    public static final ResourceKey<PlacedFeature> LIST_SMALL_BLACK_OAK_PLACED_TREE_KEY = registerKey("black/list_small_black_oak_tree");
    public static final ResourceKey<PlacedFeature> LIST_BLACK_OAK_PLACED_TREE_KEY = registerKey("black/list_black_oak_tree");
    public static final ResourceKey<PlacedFeature> LIST_LARGE_BLACK_OAK_PLACED_TREE_KEY = registerKey("black/list_large_black_oak_tree");
    public static final ResourceKey<PlacedFeature> LIST_MEGA_BLACK_OAK_PLACED_TREE_KEY = registerKey("black/list_mega_black_oak_tree");
    public static final ResourceKey<PlacedFeature> LIST_COLOSSAL_BLACK_OAK_PLACED_TREE_KEY = registerKey("black/list_colossal_black_oak_tree");

    public static final ResourceKey<PlacedFeature> SMALL_BLACK_OAK_PLACED_TREE_KEY = registerKey("black/small_black_oak_tree");
    public static final ResourceKey<PlacedFeature> BLACK_OAK_PLACED_TREE_KEY = registerKey("black/black_oak_tree");
    public static final ResourceKey<PlacedFeature> SPARSE_BLACK_OAK_PLACED_TREE_KEY = registerKey("black/sparse_black_oak_tree");
    public static final ResourceKey<PlacedFeature> MEGA_BLACK_OAK_PLACED_TREE_KEY = registerKey("black/mega_black_oak_tree");

    public static final ResourceKey<PlacedFeature> OAK_BUSH_PLACED_TREE_KEY = registerKey("oak/oak_bush_tree");
    public static final ResourceKey<PlacedFeature> OAK_BUSH_COMMON_PLACED_TREE_KEY = registerKey("oak/oak_bush_common_tree");
    public static final ResourceKey<PlacedFeature> COMMON_OAK_PLACED_TREE_KEY = registerKey("oak/common_oak_tree");
    public static final ResourceKey<PlacedFeature> OAK_PLACED_TREE_KEY = registerKey("oak/oak_tree");
    public static final ResourceKey<PlacedFeature> RARE_OAK_PLACED_TREE_KEY = registerKey("oak/rare_oak_tree");
    public static final ResourceKey<PlacedFeature> OAK_VINES_PLACED_TREE_KEY = registerKey("oak/oak_vines_tree");
    public static final ResourceKey<PlacedFeature> MEGA_OAK_PLACED_TREE_KEY = registerKey("oak/mega_oak_tree");
    public static final ResourceKey<PlacedFeature> RARE_MEGA_OAK_PLACED_TREE_KEY = registerKey("oak/rare_mega_oak_tree");
    public static final ResourceKey<PlacedFeature> VERY_RARE_MEGA_OAK_PLACED_TREE_KEY = registerKey("oak/very_rare_mega_oak_tree");
    public static final ResourceKey<PlacedFeature> MEGA_OAK_COMMON_PLACED_TREE_KEY = registerKey("oak/mega_oak_common_tree");

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

    public static void boostrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, MEGA_DARK_OAK_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(OakTreeConfiguredFeatures.MEGA_DARK_OAK_TREE_KEY),
                VegetationPlacements.treePlacement(frequentTree,
                        Blocks.DARK_OAK_SAPLING));
        register(context, MEGA_DARK_OAK_PLACED_COMMON_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(OakTreeConfiguredFeatures.MEGA_DARK_OAK_TREE_KEY),
                VegetationPlacements.treePlacement(abundantTree,
                        Blocks.DARK_OAK_SAPLING));

        register(context, LIST_SMALL_RED_OAK_PLACED_TREE_KEY,
                configuredFeatureRegistryEntryLookup.getOrThrow(OakTreeConfiguredFeatures.SMALL_RED_OAK_TREE_KEY),
                List.of());
        register(context, LIST_RED_OAK_PLACED_TREE_KEY,
                configuredFeatureRegistryEntryLookup.getOrThrow(OakTreeConfiguredFeatures.RED_OAK_KEY),
                List.of());
        register(context, LIST_LARGE_RED_OAK_PLACED_TREE_KEY,
                configuredFeatureRegistryEntryLookup.getOrThrow(OakTreeConfiguredFeatures.LARGE_RED_OAK_TREE_KEY),
                List.of());
        register(context, LIST_MEGA_RED_OAK_PLACED_TREE_KEY,
                configuredFeatureRegistryEntryLookup.getOrThrow(OakTreeConfiguredFeatures.MEGA_RED_OAK_TREE_KEY),
                List.of());
        register(context, LIST_COLOSSAL_RED_OAK_PLACED_TREE_KEY,
                configuredFeatureRegistryEntryLookup.getOrThrow(OakTreeConfiguredFeatures.COLOSSAL_RED_OAK_TREE_KEY),
                List.of());

        register(context, SMALL_RED_OAK_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(OakTreeConfiguredFeatures.SMALL_RED_OAK_TREE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(15, 0.25f, 2),
                        WoodBlockSets.RED_OAK.sapling().get()));
        register(context, SPARSE_RED_OAK_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(OakTreeConfiguredFeatures.LARGE_RED_OAK_TREE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.1f, 1),
                        WoodBlockSets.RED_OAK.sapling().get()));
        register(context, RED_OAK_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(OakTreeConfiguredFeatures.RED_OAK_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(21, 0.2f, 2),
                        WoodBlockSets.RED_OAK.sapling().get()));
        register(context, MEGA_RED_OAK_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(OakTreeConfiguredFeatures.MEGA_RED_OAK_TREE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(18, 0.2f, 1),
                        WoodBlockSets.RED_OAK.sapling().get()));


        register(context, LIST_SMALL_GREEN_OAK_PLACED_TREE_KEY,
                configuredFeatureRegistryEntryLookup.getOrThrow(OakTreeConfiguredFeatures.SMALL_GREEN_OAK_TREE_KEY),
                List.of());
        register(context, LIST_GREEN_OAK_PLACED_TREE_KEY,
                configuredFeatureRegistryEntryLookup.getOrThrow(OakTreeConfiguredFeatures.GREEN_OAK_KEY),
                List.of());
        register(context, LIST_LARGE_GREEN_OAK_PLACED_TREE_KEY,
                configuredFeatureRegistryEntryLookup.getOrThrow(OakTreeConfiguredFeatures.LARGE_GREEN_OAK_TREE_KEY),
                List.of());
        register(context, LIST_MEGA_GREEN_OAK_PLACED_TREE_KEY,
                configuredFeatureRegistryEntryLookup.getOrThrow(OakTreeConfiguredFeatures.MEGA_GREEN_OAK_TREE_KEY),
                List.of());
        register(context, LIST_COLOSSAL_GREEN_OAK_PLACED_TREE_KEY,
                configuredFeatureRegistryEntryLookup.getOrThrow(OakTreeConfiguredFeatures.COLOSSAL_GREEN_OAK_TREE_KEY),
                List.of());

        register(context, SMALL_GREEN_OAK_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(OakTreeConfiguredFeatures.SMALL_GREEN_OAK_TREE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(15, 0.25f, 2),
                        WoodBlockSets.GREEN_OAK.sapling().get()));
        register(context, SPARSE_GREEN_OAK_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(OakTreeConfiguredFeatures.LARGE_GREEN_OAK_TREE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.1f, 1),
                        WoodBlockSets.GREEN_OAK.sapling().get()));
        register(context, GREEN_OAK_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(OakTreeConfiguredFeatures.GREEN_OAK_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(21, 0.2f, 2),
                        WoodBlockSets.GREEN_OAK.sapling().get()));
        register(context, MEGA_GREEN_OAK_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(OakTreeConfiguredFeatures.MEGA_GREEN_OAK_TREE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(18, 0.2f, 1),
                        WoodBlockSets.GREEN_OAK.sapling().get()));

        register(context, LIST_SMALL_BLACK_OAK_PLACED_TREE_KEY,
                configuredFeatureRegistryEntryLookup.getOrThrow(OakTreeConfiguredFeatures.SMALL_BLACK_OAK_TREE_KEY),
                List.of());
        register(context, LIST_BLACK_OAK_PLACED_TREE_KEY,
                configuredFeatureRegistryEntryLookup.getOrThrow(OakTreeConfiguredFeatures.BLACK_OAK_KEY),
                List.of());
        register(context, LIST_LARGE_BLACK_OAK_PLACED_TREE_KEY,
                configuredFeatureRegistryEntryLookup.getOrThrow(OakTreeConfiguredFeatures.LARGE_BLACK_OAK_TREE_KEY),
                List.of());
        register(context, LIST_MEGA_BLACK_OAK_PLACED_TREE_KEY,
                configuredFeatureRegistryEntryLookup.getOrThrow(OakTreeConfiguredFeatures.MEGA_BLACK_OAK_TREE_KEY),
                List.of());
        register(context, LIST_COLOSSAL_BLACK_OAK_PLACED_TREE_KEY,
                configuredFeatureRegistryEntryLookup.getOrThrow(OakTreeConfiguredFeatures.COLOSSAL_BLACK_OAK_TREE_KEY),
                List.of());

        register(context, SMALL_BLACK_OAK_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(OakTreeConfiguredFeatures.SMALL_BLACK_OAK_TREE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(15, 0.25f, 2),
                        WoodBlockSets.BLACK_OAK.sapling().get()));
        register(context, SPARSE_BLACK_OAK_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(OakTreeConfiguredFeatures.LARGE_BLACK_OAK_TREE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.1f, 1),
                        WoodBlockSets.BLACK_OAK.sapling().get()));
        register(context, BLACK_OAK_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(OakTreeConfiguredFeatures.BLACK_OAK_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(21, 0.2f, 2),
                        WoodBlockSets.BLACK_OAK.sapling().get()));
        register(context, MEGA_BLACK_OAK_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(OakTreeConfiguredFeatures.MEGA_BLACK_OAK_TREE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(18, 0.2f, 1),
                        WoodBlockSets.BLACK_OAK.sapling().get()));

        register(context, OAK_BUSH_COMMON_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(OakTreeConfiguredFeatures.OAK_BUSH_TREE_KEY),
                VegetationPlacements.treePlacement(frequentTree,
                        Blocks.OAK_SAPLING));
        register(context, OAK_BUSH_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(OakTreeConfiguredFeatures.OAK_BUSH_TREE_KEY),
                VegetationPlacements.treePlacement(uncommonTree,
                        Blocks.OAK_SAPLING));
        register(context, COMMON_OAK_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(OakTreeConfiguredFeatures.OAK_TREE_KEY),
                VegetationPlacements.treePlacement(uncommonTree,
                        Blocks.OAK_SAPLING));
        register(context, OAK_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(OakTreeConfiguredFeatures.OAK_TREE_KEY),
                VegetationPlacements.treePlacement(rareTree,
                        Blocks.OAK_SAPLING));
        register(context, RARE_OAK_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(OakTreeConfiguredFeatures.OAK_TREE_KEY),
                VegetationPlacements.treePlacement(veryRareTree,
                        Blocks.OAK_SAPLING));
        register(context, OAK_VINES_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(OakTreeConfiguredFeatures.OAK_TREE_VINES_KEY),
                VegetationPlacements.treePlacement(frequentTree,
                        Blocks.OAK_SAPLING));
        register(context, MEGA_OAK_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(OakTreeConfiguredFeatures.MEGA_OAK_TREE_KEY),
                VegetationPlacements.treePlacement(megaTree,
                        Blocks.OAK_SAPLING));
        register(context, MEGA_OAK_COMMON_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(OakTreeConfiguredFeatures.MEGA_OAK_TREE_KEY),
                VegetationPlacements.treePlacement(abundantTree,
                        Blocks.OAK_SAPLING));
        register(context, RARE_MEGA_OAK_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(OakTreeConfiguredFeatures.MEGA_OAK_TREE_KEY),
                VegetationPlacements.treePlacement(veryRareTree,
                        Blocks.OAK_SAPLING));
        register(context, VERY_RARE_MEGA_OAK_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(OakTreeConfiguredFeatures.MEGA_OAK_TREE_KEY),
                VegetationPlacements.treePlacement(specialTree,
                        Blocks.OAK_SAPLING));



    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "tree/oaks/" + name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
