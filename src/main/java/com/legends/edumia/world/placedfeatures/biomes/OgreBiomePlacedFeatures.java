package com.legends.edumia.world.placedfeatures.biomes;

import com.legends.edumia.Edumia;
import com.legends.edumia.world.congiguredfeatures.biomes.OgreBiomeConfiguredFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
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

public class OgreBiomePlacedFeatures {
    public static final ResourceKey<PlacedFeature> OGRE_FOREST_TREES = registerKey("ogre/forest_trees");

    public static void boostrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, OGRE_FOREST_TREES,
                configuredFeatureRegistryEntryLookup.getOrThrow(OgreBiomeConfiguredFeatures.OGRE_FOREST_TREES),
                List.of(CountPlacement.of(new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder()
                                .add(ConstantInt.of(50), 90)
                                .add(ConstantInt.of(51), 10).build())),
                        InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0),
                        HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR), BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                BlockPredicate.matchesBlocks(new BlockPos(0, -1, 0),
                                        Blocks.GRASS_BLOCK, Blocks.DIRT,
                                        Blocks.PODZOL, Blocks.COARSE_DIRT, Blocks.ROOTED_DIRT))), BiomeFilter.biome()));

    }
    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "biomes/" + name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

}
