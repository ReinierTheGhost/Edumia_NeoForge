package com.legends.edumia.world.placedfeatures.crystrals;

import com.legends.edumia.Edumia;
import com.legends.edumia.world.congiguredfeatures.crystals.CrystalConfiguresFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class CrystalPlacedFeatures {
    public static final ResourceKey<PlacedFeature> ELVEN_CRYSTAL = registerKey("elven_crystal");


    public static void bootstrap(BootstapContext<PlacedFeature> featureRegisterable) {
        HolderGetter<ConfiguredFeature<?, ?>> registryEntryLookup = featureRegisterable.lookup(Registries.CONFIGURED_FEATURE);


        PlacementUtils.register(featureRegisterable, ELVEN_CRYSTAL, registryEntryLookup.getOrThrow(CrystalConfiguresFeatures.ELVEN_CRYSTAL),
                CountPlacement.of(2), InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(5), VerticalAnchor.absolute(41)), BiomeFilter.biome());
    }
    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Edumia.MOD_ID, "crystals/" + name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

}
