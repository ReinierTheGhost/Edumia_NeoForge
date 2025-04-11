package com.legends.edumia.world.congiguredfeatures.biomes;

import com.legends.edumia.Edumia;
import com.legends.edumia.world.placedfeatures.ModPlacedFeatures;
import com.legends.edumia.world.placedfeatures.trees.BorealTreePlacedFeatures;
import com.legends.edumia.world.placedfeatures.trees.TemperateTreePlacedFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

public class OgreBiomeConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> OGRE_FOREST_TREES = registerKey("forest_trees");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
        HolderGetter<PlacedFeature> registryEntryLookup = context.lookup(Registries.PLACED_FEATURE);
        Holder.Reference<PlacedFeature> noting = registryEntryLookup.getOrThrow(ModPlacedFeatures.NOTING);
        Holder.Reference<PlacedFeature> mega_spruce = registryEntryLookup.getOrThrow(BorealTreePlacedFeatures.MEGA_SPRUCE_TREE_LIST);
        Holder.Reference<PlacedFeature> sakura_bush = registryEntryLookup.getOrThrow(TemperateTreePlacedFeatures.SAKURA_BUSH);
        Holder.Reference<PlacedFeature> sakura_medium_tree = registryEntryLookup.getOrThrow(TemperateTreePlacedFeatures.SAKURA_TREE_MEDIUM);
        Holder.Reference<PlacedFeature> sakura_big_tree = registryEntryLookup.getOrThrow(TemperateTreePlacedFeatures.SAKURA_TREE_BIG);
        Holder.Reference<PlacedFeature> fir = registryEntryLookup.getOrThrow(BorealTreePlacedFeatures.LIST_FIR_TREE);
        Holder.Reference<PlacedFeature> birch = registryEntryLookup.getOrThrow(TemperateTreePlacedFeatures.LIST_BIRCH_TREE);


        register(context, OGRE_FOREST_TREES, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(
                new WeightedPlacedFeature(sakura_big_tree, 0.02f), new WeightedPlacedFeature(sakura_medium_tree, 0.08f),
                new WeightedPlacedFeature(sakura_bush, 0.04f), new WeightedPlacedFeature(birch, 0.03f),
                new WeightedPlacedFeature(fir, 0.06f), new WeightedPlacedFeature(mega_spruce, 0.04f),
                new WeightedPlacedFeature(noting, 0.2f)),
                noting));


    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "biome/ogre/" +name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext< ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature,
                                                                                          FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
