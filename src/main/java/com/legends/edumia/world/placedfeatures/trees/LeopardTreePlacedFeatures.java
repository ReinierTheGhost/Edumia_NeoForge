package com.legends.edumia.world.placedfeatures.trees;

import com.legends.edumia.Edumia;
import com.legends.edumia.core.blocksets.WoodBlockSets;
import com.legends.edumia.world.congiguredfeatures.trees.LeopardTreeConfiguredFeatures;
import com.legends.edumia.world.congiguredfeatures.trees.MahoganyConfiguredFeatures;
import com.legends.edumia.world.placedfeatures.ModPlacedFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class LeopardTreePlacedFeatures {

    public static final ResourceKey<PlacedFeature> LEOPARD_1 = registerKey("leopard_1");
    public static final ResourceKey<PlacedFeature> LEOPARD_2 = registerKey("leopard_2");
    public static final ResourceKey<PlacedFeature> LEOPARD_3 = registerKey("leopard_3");
    public static final ResourceKey<PlacedFeature> LEOPARD_4 = registerKey("leopard_4");


    public static void boostrap(BootstrapContext<PlacedFeature> context) {

        var configuredFeatureRegistryEntryLookup = context.lookup(Registries.CONFIGURED_FEATURE);
        register(context, LEOPARD_1, configuredFeatureRegistryEntryLookup.getOrThrow(LeopardTreeConfiguredFeatures.LEOPARD_1),
                VegetationPlacements.treePlacement(ModPlacedFeatures.uncommonTree,
                        WoodBlockSets.GHOST_GUM.sapling().get()));

        register(context, LEOPARD_2, configuredFeatureRegistryEntryLookup.getOrThrow(LeopardTreeConfiguredFeatures.LEOPARD_2),
                VegetationPlacements.treePlacement(ModPlacedFeatures.uncommonTree,
                        WoodBlockSets.GHOST_GUM.sapling().get()));

        register(context, LEOPARD_3, configuredFeatureRegistryEntryLookup.getOrThrow(LeopardTreeConfiguredFeatures.LEOPARD_3),
                VegetationPlacements.treePlacement(ModPlacedFeatures.uncommonTree,
                        WoodBlockSets.GHOST_GUM.sapling().get()));

        register(context, LEOPARD_4, configuredFeatureRegistryEntryLookup.getOrThrow(LeopardTreeConfiguredFeatures.LEOPARD_4),
                VegetationPlacements.treePlacement(ModPlacedFeatures.uncommonTree,
                        WoodBlockSets.GHOST_GUM.sapling().get()));
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "tree/tropical/leopard/" + name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
