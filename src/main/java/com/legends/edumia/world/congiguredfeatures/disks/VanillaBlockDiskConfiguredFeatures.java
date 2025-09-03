package com.legends.edumia.world.congiguredfeatures.disks;

import com.legends.edumia.Edumia;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedBlockStateProvider;

import java.util.List;

public class VanillaBlockDiskConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> YELLOW_ACID = registerKey("yellow_acid");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORANGE_ACID = registerKey("orange_acid");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
        register(context, YELLOW_ACID, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(Blocks.YELLOW_TERRACOTTA),
                BlockPredicate.matchesBlocks(
                        List.of(
                                Blocks.GRAVEL, Blocks.SAND, Blocks.COARSE_DIRT, Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.RED_SAND,
                                Blocks.CALCITE, Blocks.BLACKSTONE, Blocks.BASALT)),
                ConstantInt.of(1), 0));

        register(context, ORANGE_ACID, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(Blocks.RED_SAND),
                BlockPredicate.matchesBlocks(
                        List.of(
                                Blocks.GRAVEL, Blocks.SAND, Blocks.COARSE_DIRT, Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.RED_SAND,
                                Blocks.CALCITE, Blocks.BLACKSTONE, Blocks.BASALT)),
                UniformInt.of(2, 3), 1));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "disks/vanilla/" + name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext< ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature,
                                                                                          FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
