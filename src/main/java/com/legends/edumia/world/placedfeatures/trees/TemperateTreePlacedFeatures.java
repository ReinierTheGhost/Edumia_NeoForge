package com.legends.edumia.world.placedfeatures.trees;

import com.legends.edumia.Edumia;
import com.legends.edumia.blocks.blocksets.ModNatureBlocks;
import com.legends.edumia.blocks.blocksets.WoodBlockSets;
import com.legends.edumia.world.congiguredfeatures.trees.TemperateTreeConfiguredFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class TemperateTreePlacedFeatures {

    public static final ResourceKey<PlacedFeature> SAKURA_BUSH = registerKey("sakura/sakura_bush");
    public static final ResourceKey<PlacedFeature> SAKURA_TREE_MEDIUM = registerKey("sakura/sakura_tree_medium");
    public static final ResourceKey<PlacedFeature> SAKURA_TREE_BIG = registerKey("sakura/sakura_tree_big");

    public static final ResourceKey<PlacedFeature> AZALEA_BUSH = registerKey("azalea/azalea_bush");

    public static final ResourceKey<PlacedFeature> CEDER_KEY = registerKey("ceder/ceder_tree");
    public static final ResourceKey<PlacedFeature> LARGE_CEDER_KEY = registerKey("ceder/large_ceder_tree");

    public static final ResourceKey<PlacedFeature> CYPRESS_KEY = registerKey("cypress/cypress_tree");

    public static final ResourceKey<PlacedFeature> ASPEN_KEY = registerKey("aspen/aspen_tree");
    public static final ResourceKey<PlacedFeature> ASPEN_2_KEY = registerKey("aspen/aspen_2_tree");

    public static final ResourceKey<PlacedFeature> LIST_BIRCH_TREE = registerKey("list/birch/birch_tree");
    public static final ResourceKey<PlacedFeature> BIRCH_PLACED_TREE_KEY = registerKey("birch/birch_tree");
    public static final ResourceKey<PlacedFeature> SPARSE_BIRCH_PLACED_TREE_KEY = registerKey("birch/sparse_birch_tree");
    public static final ResourceKey<PlacedFeature> RARE_BIRCH_PLACED_TREE_KEY = registerKey("birch/rare_birch_tree");
    public static final ResourceKey<PlacedFeature> VERY_RARE_BIRCH_PLACED_TREE_KEY = registerKey("birch/super_rare_birch_tree");
    public static final ResourceKey<PlacedFeature> MEGA_BIRCH_PLACED_COMMON_TREE_KEY = registerKey("birch/mega_birch_common_tree");
    public static final ResourceKey<PlacedFeature> MEGA_BIRCH_PLACED_TREE_KEY = registerKey("birch/mega_birch_tree");

    public static final ResourceKey<PlacedFeature> BLACKTHORN_PLACED_TREE_KEY = registerKey("blackthorn/blackthorn_tree");
    public static final ResourceKey<PlacedFeature> COMMON_BLACKTHORN_PLACED_TREE_KEY = registerKey("blackthorn/common_blackthorn_tree");
    public static final ResourceKey<PlacedFeature> RARE_BLACKTHORN_PLACED_TREE_KEY = registerKey("blackthorn/rare_blackthorn_tree");

    public static final ResourceKey<PlacedFeature> WHITE_ASH_PLACED_TREE_KEY = registerKey("white_ash/white_ash_tree");
    public static final ResourceKey<PlacedFeature> SMALL_WHITE_ASH_PLACED_TREE_KEY = registerKey("white_ash/small_white_ash_tree");
    public static final ResourceKey<PlacedFeature> WHITE_ASH_BUSH_PLACED_TREE_KEY = registerKey("white_ash/white_ash_bush");
    public static final ResourceKey<PlacedFeature> MEGA_WHITE_ASH_PLACED_TREE_KEY = registerKey("white_ash/mega_white_ash_tree");

    public static final ResourceKey<PlacedFeature> MAPLE_PLACED_TREE_KEY = registerKey("maple/maple_tree");

    public static final ResourceKey<PlacedFeature> HOLLY_TREE_KEY = registerKey("holly/holly_tree");
    public static final ResourceKey<PlacedFeature> HOLLY_BEES_TREE_KEY = registerKey("holly/holly_tree_bees");

    public static final ResourceKey<PlacedFeature> WILLOW_PLACED_TREE_KEY = registerKey("willow/willow_tree");
    public static final ResourceKey<PlacedFeature> GENSAI_SAKURA_GROVE_TREES = registerKey("sakura/gensai_sakura_grove_trees");


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
        register(context, LIST_BIRCH_TREE, configuredFeatureRegistryEntryLookup.getOrThrow(TemperateTreeConfiguredFeatures.BIRCH_TREE_KEY),
                List.of());
        register(context, BIRCH_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(TemperateTreeConfiguredFeatures.BIRCH_TREE_KEY),
                VegetationPlacements.treePlacement(scarceTree,
                        Blocks.BIRCH_SAPLING));
        register(context, SPARSE_BIRCH_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(TemperateTreeConfiguredFeatures.BIRCH_TREE_KEY),
                VegetationPlacements.treePlacement(rareTree,
                        Blocks.BIRCH_SAPLING));
        register(context, RARE_BIRCH_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(TemperateTreeConfiguredFeatures.BIRCH_TREE_KEY),
                VegetationPlacements.treePlacement(veryRareTree,
                        Blocks.BIRCH_SAPLING));
        register(context, VERY_RARE_BIRCH_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(TemperateTreeConfiguredFeatures.BIRCH_TREE_KEY),
                VegetationPlacements.treePlacement(superRareTree,
                        Blocks.BIRCH_SAPLING));
        register(context, MEGA_BIRCH_PLACED_COMMON_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(TemperateTreeConfiguredFeatures.BIRCH_TREE_KEY),
                VegetationPlacements.treePlacement(uncommonTree,
                        Blocks.BIRCH_SAPLING));
        register(context, MEGA_BIRCH_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(TemperateTreeConfiguredFeatures.MEGA_BIRCH_TREE_KEY),
                VegetationPlacements.treePlacement(megaTree,
                        Blocks.BIRCH_SAPLING));

        register(context, BLACKTHORN_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(TemperateTreeConfiguredFeatures.BLACKTHORN_TREE_KEY),
                VegetationPlacements.treePlacement(frequentTree,
                        WoodBlockSets.BLACKTHORN.sapling().get()));
        register(context, COMMON_BLACKTHORN_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(TemperateTreeConfiguredFeatures.BLACKTHORN_TREE_KEY),
                VegetationPlacements.treePlacement(abundantTree,
                        WoodBlockSets.BLACKTHORN.sapling().get()));
        register(context, RARE_BLACKTHORN_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(TemperateTreeConfiguredFeatures.BLACKTHORN_TREE_KEY),
                VegetationPlacements.treePlacement(rareTree,
                        WoodBlockSets.BLACKTHORN.sapling().get()));

        register(context, WHITE_ASH_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(TemperateTreeConfiguredFeatures.WHITE_ASH_TREE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(13, 0.2f, 2),
                        WoodBlockSets.WHITE_ASH.sapling().get()));
        register(context, SMALL_WHITE_ASH_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(TemperateTreeConfiguredFeatures.SMALL_WHITE_ASH_TREE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(4, 0.25f, 1),
                        WoodBlockSets.WHITE_ASH.sapling().get()));
        register(context, MEGA_WHITE_ASH_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(TemperateTreeConfiguredFeatures.MEGA_WHITE_ASH_TREE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(8, 0.2f, 1),
                        WoodBlockSets.WHITE_ASH.sapling().get()));
        register(context, WHITE_ASH_BUSH_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(TemperateTreeConfiguredFeatures.WHITE_ASH_BUSH_KEY),
                VegetationPlacements.treePlacement(commonTree, WoodBlockSets.WHITE_ASH.sapling().get()));

        register(context, MAPLE_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(TemperateTreeConfiguredFeatures.MAPLE_TREE_KEY),
                VegetationPlacements.treePlacement(superRareTree,
                        WoodBlockSets.MAPLE.sapling().get()));

        register(context, WILLOW_PLACED_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(TemperateTreeConfiguredFeatures.WILLOW_TREE_KEY),
                VegetationPlacements.treePlacement(uncommonTree,
                        WoodBlockSets.WILLOW.sapling().get()));



        register(context, SAKURA_BUSH, configuredFeatureRegistryEntryLookup.getOrThrow(TemperateTreeConfiguredFeatures.SAKURA_BUSH),
                List.of());

        register(context, SAKURA_TREE_MEDIUM, configuredFeatureRegistryEntryLookup.getOrThrow(TemperateTreeConfiguredFeatures.SAKURA_TREE_MEDIUM),
                List.of());

        register(context, SAKURA_TREE_BIG, configuredFeatureRegistryEntryLookup.getOrThrow(TemperateTreeConfiguredFeatures.SAKURA_TREE_BIG),
                List.of());

        register(context, AZALEA_BUSH, configuredFeatureRegistryEntryLookup.getOrThrow(TemperateTreeConfiguredFeatures.AZALEA_BUSH),
                List.of());

        register(context, CEDER_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(TemperateTreeConfiguredFeatures.CEDER_KEY),
                List.of());

        register(context, LARGE_CEDER_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(TemperateTreeConfiguredFeatures.LARGE_CEDER_KEY),
                List.of());

        register(context, CYPRESS_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(TemperateTreeConfiguredFeatures.CYPRESS_KEY),
                List.of());

        register(context, ASPEN_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(TemperateTreeConfiguredFeatures.ASPEN_KEY),
                List.of());
        register(context, ASPEN_2_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(TemperateTreeConfiguredFeatures.ASPEN_2_KEY),
                List.of());

        register(context, HOLLY_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(TemperateTreeConfiguredFeatures.HOLLY_TREE_KEY),
                List.of());

        register(context, HOLLY_BEES_TREE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(TemperateTreeConfiguredFeatures.HOLLY_BEES_TREE_KEY),
                List.of());


        register(context, GENSAI_SAKURA_GROVE_TREES,
                configuredFeatureRegistryEntryLookup.getOrThrow(TemperateTreeConfiguredFeatures.GENSAI_SAKURA_GROVE_TREES),
                List.of(CountPlacement.of(ConstantInt.of(128)),
                        InSquarePlacement.spread(), NoiseBasedCountPlacement.of(8, 202, 0.25),
                        HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE), RarityFilter.onAverageOnceEvery(100),
                        BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.FERN.defaultBlockState(), BlockPos.ZERO)),
                        BiomeFilter.biome()));


    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "tree/temperate/" + name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
