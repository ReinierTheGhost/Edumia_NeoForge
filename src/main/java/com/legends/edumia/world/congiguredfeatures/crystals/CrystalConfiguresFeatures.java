package com.legends.edumia.world.congiguredfeatures.crystals;

import com.legends.edumia.Edumia;
import com.legends.edumia.core.BlockLoader;
import com.legends.edumia.world.features.EdumiaFeatures;
import com.legends.edumia.world.features.crystal.CrystalFeatureConfig;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class CrystalConfiguresFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> ELVEN_CRYSTAL = registerKey("elven_crystal");
    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> featureRegisterable) {
        register(featureRegisterable, ELVEN_CRYSTAL, EdumiaFeatures.CRYSTAL.get(),
                new CrystalFeatureConfig(BlockStateProvider.simple(BlockLoader.HIGH_ELVEN_CRYSTAL.get()), 64,6, 4, 6));

    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "crystals/" + name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext< ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature,
                                                                                          FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

}
