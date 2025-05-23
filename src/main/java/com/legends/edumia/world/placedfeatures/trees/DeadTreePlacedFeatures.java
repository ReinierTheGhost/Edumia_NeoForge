package com.legends.edumia.world.placedfeatures.trees;

import com.legends.edumia.Edumia;
import com.legends.edumia.core.BlockLoader;
import com.legends.edumia.world.congiguredfeatures.trees.DeadTreeConfiguredFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class DeadTreePlacedFeatures {

    public static final ResourceKey<PlacedFeature> BURNED_TREE = registerKey("burned_tree");

    public static void boostrap(BootstrapContext<PlacedFeature> context) {

        var configuredFeatureRegistryEntryLookup = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, BURNED_TREE, configuredFeatureRegistryEntryLookup.getOrThrow(DeadTreeConfiguredFeatures.BURNED_TREE),
                List.of(CountPlacement.of(4), InSquarePlacement.spread(),
                        NoiseBasedCountPlacement.of(1,156, 0.3),
                        NoiseBasedCountPlacement.of(1, 30, -0.5),
                        BlockPredicateFilter.forPredicate(
                                BlockPredicate.matchesBlocks(new BlockPos(0, -1, 0),
                                        BlockLoader.VOLCANIC_DIRT.get())),
                        BiomeFilter.biome()));
    }
    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "tree/dead/" + name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

}
