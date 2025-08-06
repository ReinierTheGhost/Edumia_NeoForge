package com.legends.edumia.world.placedfeatures.caves;

import com.legends.edumia.Edumia;
import com.legends.edumia.world.congiguredfeatures.caves.JungleCaveConfiguredFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class JungleCavePlacedFeatures {

    public static final ResourceKey<PlacedFeature> FLOOR = registerKey("floor");
    public static final ResourceKey<PlacedFeature> VEGETATION = registerKey("vegetation");
    public static final ResourceKey<PlacedFeature> CEILING_MOSS = registerKey("ceiling_moss");

    public static void boostrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, FLOOR, configuredFeatureRegistryEntryLookup.getOrThrow(JungleCaveConfiguredFeatures.FLOOR), List.of(
                CountOnEveryLayerPlacement.of(UniformInt.of(2, 4)),
                BlockPredicateFilter.forPredicate(
                        BlockPredicate.matchesBlocks(new BlockPos(0, -1, 0), Blocks.MUD, Blocks.MOSS_BLOCK)),
                BiomeFilter.biome()
        ));

        register(context, VEGETATION, configuredFeatureRegistryEntryLookup.getOrThrow(JungleCaveConfiguredFeatures.VEGETATION), List.of(
                CountOnEveryLayerPlacement.of(UniformInt.of(64, 128)),
                BlockPredicateFilter.forPredicate(
                        BlockPredicate.matchesBlocks(new BlockPos(0, -1, 0), Blocks.MUD, Blocks.MOSS_BLOCK)),
                BiomeFilter.biome()
        ));

        register(context, CEILING_MOSS, configuredFeatureRegistryEntryLookup.getOrThrow(JungleCaveConfiguredFeatures.CEILING_MOSS), List.of(
                CountPlacement.of(125),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(
                        UniformHeight.of(
                                VerticalAnchor.absolute(0), VerticalAnchor.absolute(256))),
        EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
        RandomOffsetPlacement.of(ConstantInt.of(0), ConstantInt.of(-1)),
        BiomeFilter.biome()
        ));
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "caves/jungle/" + name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
