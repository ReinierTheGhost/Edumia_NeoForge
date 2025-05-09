package com.legends.edumia.world.congiguredfeatures.plants;

import com.legends.edumia.Edumia;
import com.legends.edumia.core.blocksets.FlowerBlockSets;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;

public class MushroomConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> PARASOL_MUSHROOM_1_KEY = registerKey("parasol_mushroom_01");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PARASOL_MUSHROOM_2_KEY = registerKey("parasol_mushroom_02");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PARASOL_MUSHROOM_TALL_KEY = registerKey("parasol_mushroom_tall");

    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_PARASOL_MUSHROOM_1_KEY = registerKey("patch_parasol_mushroom_01");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_PARASOL_MUSHROOM_2_KEY = registerKey("patch_parasol_mushroom_02");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_PARASOL_MUSHROOM_TALL_KEY = registerKey("patch_parasol_mushroom_tall");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
        register(context, PARASOL_MUSHROOM_1_KEY, Feature.FLOWER, new RandomPatchConfiguration(8, 6, 2,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(
                        SimpleWeightedRandomList.<BlockState>builder().add(FlowerBlockSets.PARASOL_MUSHROOM_1.flower()
                                .get().defaultBlockState(), 1))))));

        register(context, PARASOL_MUSHROOM_2_KEY, Feature.FLOWER, new RandomPatchConfiguration(8, 6, 2,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(
                        SimpleWeightedRandomList.<BlockState>builder().add(FlowerBlockSets.PARASOL_MUSHROOM_2.flower()
                                .get().defaultBlockState(), 1))))));

        register(context, PARASOL_MUSHROOM_TALL_KEY, Feature.FLOWER, new RandomPatchConfiguration(8, 6, 2,
                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(
                        SimpleWeightedRandomList.<BlockState>builder().add(FlowerBlockSets.PARASOL_MUSHROOM_TALL.tallFlower()
                                .get().defaultBlockState(), 1))))));


    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "mushroom/" + name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext< ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature,
                                                                                          FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
