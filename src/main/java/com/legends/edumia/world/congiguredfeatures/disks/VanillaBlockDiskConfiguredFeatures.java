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
import net.minecraft.world.level.levelgen.feature.configurations.UnderwaterMagmaConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedBlockStateProvider;
import org.checkerframework.checker.units.qual.C;

import java.util.List;

public class VanillaBlockDiskConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> ACID_YELLOW = registerKey("acid_yellow");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ACID_ORANGE = registerKey("acid_orange");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ACID_GRAVEL = registerKey("acid_gravel");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ACID_BLACK = registerKey("acid_black");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ACID_BASALT = registerKey("acid_basalt");

    public static final ResourceKey<ConfiguredFeature<?, ?>> COARSE_DIRT_DISK = registerKey("coarse_dirt_disk");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PODZOL_DISK = registerKey("podzol");



    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
        register(context, ACID_YELLOW, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(Blocks.YELLOW_TERRACOTTA),
                BlockPredicate.matchesBlocks(
                        List.of(
                                Blocks.GRAVEL, Blocks.SAND, Blocks.COARSE_DIRT, Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.RED_SAND,
                                Blocks.CALCITE, Blocks.BLACKSTONE, Blocks.BASALT)),
                ConstantInt.of(1), 0));

        register(context, ACID_ORANGE, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(Blocks.RED_SAND),
                BlockPredicate.matchesBlocks(
                        List.of(
                                Blocks.GRAVEL, Blocks.SAND, Blocks.COARSE_DIRT, Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.RED_SAND,
                                Blocks.CALCITE, Blocks.BLACKSTONE, Blocks.BASALT)),
                UniformInt.of(2, 3), 1));

        register(context, ACID_GRAVEL, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(Blocks.GRAVEL),
                BlockPredicate.matchesBlocks(
                        List.of(
                                Blocks.SAND, Blocks.COARSE_DIRT, Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.CALCITE)),
                UniformInt.of(4, 8), 4));

        register(context, ACID_BLACK, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(Blocks.BLACKSTONE),
                BlockPredicate.matchesBlocks(
                        List.of(
                                Blocks.GRAVEL, Blocks.SAND, Blocks.COARSE_DIRT, Blocks.DIRT, Blocks.GRASS_BLOCK,
                                Blocks.CALCITE, Blocks.SMOOTH_BASALT)),
                UniformInt.of(3, 4), 2));

        register(context, ACID_BASALT, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(Blocks.SMOOTH_BASALT),
                BlockPredicate.matchesBlocks(
                        List.of(
                                Blocks.GRAVEL, Blocks.SAND, Blocks.COARSE_DIRT, Blocks.DIRT, Blocks.GRASS_BLOCK,
                                Blocks.CALCITE)),
                UniformInt.of(4, 6), 3));

        register(context, COARSE_DIRT_DISK, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(Blocks.COARSE_DIRT),
                BlockPredicate.matchesBlocks(
                        List.of(Blocks.CALCITE)),
                UniformInt.of(6, 8), 4));

        register(context, PODZOL_DISK, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(Blocks.PODZOL),
                BlockPredicate.matchesBlocks(
                        List.of(Blocks.CALCITE, Blocks.COARSE_DIRT)),
                UniformInt.of(4, 6), 3));


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
