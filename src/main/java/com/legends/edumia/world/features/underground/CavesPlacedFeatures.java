package com.legends.edumia.world.features.underground;


import com.legends.edumia.Edumia;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class CavesPlacedFeatures {
    public static final int MAX_MISTIC_ORE_HEIGHT = -55;

    public static void bootstrap(BootstrapContext<PlacedFeature> featureRegisterable) {

    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, name));
    }


}
