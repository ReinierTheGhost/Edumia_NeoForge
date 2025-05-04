package com.legends.edumia.world.placedfeatures.trees;

import com.legends.edumia.Edumia;
import com.legends.edumia.core.blocksets.WoodBlockSets;
import com.legends.edumia.world.congiguredfeatures.trees.MahoganyConfiguredFeatures;
import com.legends.edumia.world.congiguredfeatures.trees.PalmConfiguredFeatures;
import com.legends.edumia.world.congiguredfeatures.trees.SubTropicalTreeConfiguredFeatures;
import com.legends.edumia.world.congiguredfeatures.trees.TropicalTreeConfiguredFeatures;
import com.legends.edumia.world.placedfeatures.ModPlacedFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class TropicalTreePlacedFeatures {
    public static final ResourceKey<PlacedFeature> BENDING_JUNGLE_TREE = registerKey("bending_jungle_tree");
    public static final ResourceKey<PlacedFeature> CANOPY_JUNGLE_TREE = registerKey("canopy_jungle_tree");
    public static final ResourceKey<PlacedFeature> LARGE_JUNGLE_TREE = registerKey("large_jungle_tree");
    public static final ResourceKey<PlacedFeature> MEGA_JUNGLE_TREE = registerKey("mega_jungle_tree");

    public static final ResourceKey<PlacedFeature> MAHOGANY = registerKey("mahogany");
    public static final ResourceKey<PlacedFeature> BIG_MAHOGANY = registerKey("big_mahogany");
    public static final ResourceKey<PlacedFeature> CANOPY_MAHOGANY_TREE = registerKey("canopy_mahogany_tree");
    public static final ResourceKey<PlacedFeature> LARGE_MAHOGANY_TREE = registerKey("large_mahogany_tree");

    public static final ResourceKey<PlacedFeature> GENSAI_JUNGLE_TREES = registerKey("gensai_jungle_trees");

    public static final ResourceKey<PlacedFeature> SMALL_PALM_TREE = registerKey("small_palm_tree");

    public static final ResourceKey<PlacedFeature> BIG_PARASOL_PALM = registerKey("big_parasol_palm");
    public static final ResourceKey<PlacedFeature> SMALL_PARASOL_PALM = registerKey("small_parasol_palm");
    public static final ResourceKey<PlacedFeature> RANDOM_PALM = registerKey("random_palm");


    public static final ResourceKey<PlacedFeature> BANANA = registerKey("banana");
    public static final ResourceKey<PlacedFeature> MANGO = registerKey("mango");
    public static final ResourceKey<PlacedFeature> MANGO_TWO = registerKey("mango_two");
    public static final ResourceKey<PlacedFeature> COCONUT_PALM = registerKey("coconut_palm");
    public static final ResourceKey<PlacedFeature> PAPAYA_PALM = registerKey("papaya_palm");
    public static final ResourceKey<PlacedFeature> GROUP_PALMS = registerKey("group_palms");

    public static void boostrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, BENDING_JUNGLE_TREE,
                configuredFeatureRegistryEntryLookup.getOrThrow(TropicalTreeConfiguredFeatures.BENDING_JUNGLE_TREE),
                List.of());

        register(context, CANOPY_JUNGLE_TREE,
                configuredFeatureRegistryEntryLookup.getOrThrow(TropicalTreeConfiguredFeatures.CANOPY_JUNGLE_TREE),
                List.of());

        register(context, LARGE_JUNGLE_TREE,
                configuredFeatureRegistryEntryLookup.getOrThrow(TropicalTreeConfiguredFeatures.LARGE_JUNGLE_TREE),
                List.of());

        register(context, MEGA_JUNGLE_TREE,
                configuredFeatureRegistryEntryLookup.getOrThrow(TropicalTreeConfiguredFeatures.MEGA_JUNGLE_TREE),
                List.of());

        register(context, MAHOGANY,
                configuredFeatureRegistryEntryLookup.getOrThrow(MahoganyConfiguredFeatures.MAHOGANY),
                List.of());

        register(context, BIG_MAHOGANY,
                configuredFeatureRegistryEntryLookup.getOrThrow(MahoganyConfiguredFeatures.BIG_MAHOGANY),
                List.of());

        register(context, CANOPY_MAHOGANY_TREE,
                configuredFeatureRegistryEntryLookup.getOrThrow(MahoganyConfiguredFeatures.CANOPY_MAHOGANY_TREE),
                List.of());

        register(context, LARGE_MAHOGANY_TREE,
                configuredFeatureRegistryEntryLookup.getOrThrow(MahoganyConfiguredFeatures.LARGE_MAHOGANY_TREE),
                List.of());

        register(context, BIG_PARASOL_PALM,
                configuredFeatureRegistryEntryLookup.getOrThrow(TropicalTreeConfiguredFeatures.BIG_PARASOL_PALM),
                List.of());
        register(context, SMALL_PARASOL_PALM,
                configuredFeatureRegistryEntryLookup.getOrThrow(TropicalTreeConfiguredFeatures.SMALL_PARASOL_PALM),
                List.of());
        register(context, RANDOM_PALM,
                configuredFeatureRegistryEntryLookup.getOrThrow(TropicalTreeConfiguredFeatures.RANDOM_PALM),
                List.of());
        register(context, COCONUT_PALM,
                configuredFeatureRegistryEntryLookup.getOrThrow(TropicalTreeConfiguredFeatures.RANDOM_PALM),
                List.of());
        register(context, PAPAYA_PALM,
                configuredFeatureRegistryEntryLookup.getOrThrow(TropicalTreeConfiguredFeatures.RANDOM_PALM),
                List.of());

        register(context, BANANA,
                configuredFeatureRegistryEntryLookup.getOrThrow(TropicalTreeConfiguredFeatures.BANANA),
                List.of());

        register(context, MANGO,
                configuredFeatureRegistryEntryLookup.getOrThrow(TropicalTreeConfiguredFeatures.MANGO),
                List.of());

        register(context, MANGO_TWO,
                configuredFeatureRegistryEntryLookup.getOrThrow(TropicalTreeConfiguredFeatures.MANGO_TWO),
                List.of());

//        register(context, GROUP_PALMS,
//                configuredFeatureRegistryEntryLookup.getOrThrow(PalmConfiguredFeatures.GROUND_PALM_1),
//                VegetationPlacements.treePlacement(ModPlacedFeatures.uncommonTree,
//                        WoodBlockSets.KAPOK.sapling().get()));


        register(context, GENSAI_JUNGLE_TREES,
                configuredFeatureRegistryEntryLookup.getOrThrow(TropicalTreeConfiguredFeatures.GENSAI_JUNGLE_TREES),
                List.of(CountPlacement.of(new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder().add(ConstantInt.of(50), 90)
                        .add(ConstantInt.of(51), 10).build())),
                        InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0),
                        HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR), BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                BlockPredicate.matchesBlocks(new BlockPos(0, -1, 0), Blocks.GRASS_BLOCK,
                                        Blocks.DIRT, Blocks.PODZOL, Blocks.COARSE_DIRT, Blocks.ROOTED_DIRT))), BiomeFilter.biome()));



    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "tree/tropical/" + name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
