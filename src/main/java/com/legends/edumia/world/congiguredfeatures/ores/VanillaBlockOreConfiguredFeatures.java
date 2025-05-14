package com.legends.edumia.world.congiguredfeatures.ores;

import com.legends.edumia.Edumia;
import com.legends.edumia.core.TagLoader;
import com.legends.edumia.core.blocksets.StoneSets;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class VanillaBlockOreConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> ANDESITE_ORE = registerKey("andesite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BASALT_ORE = registerKey("basalt_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMOOTH_BASALT_ORE = registerKey("smooth_basalt_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CALCITE_ORE = registerKey("calcite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> COARSE_DIRT_ORE = registerKey("coarse_dirt_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DIORITE_ORE = registerKey("diorite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DIRT_TO_GRASS_ORE = registerKey("dirt_to_grass_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DRIPSTONE_ORE = registerKey("dripstone_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GRAVEL_ORE = registerKey("gravel_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GRANITE_ORE = registerKey("granite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MUD_ORE = registerKey("mud_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PACKED_MUD_ORE = registerKey("packed_mud_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PODZOL_ORE = registerKey("podzol_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> POWDER_SNOW_ORE = registerKey("powder_snow_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SAND_ORE = registerKey("sand_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SNOW_BLOCK_ORE = registerKey("snow_block_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SOUL_SAND_ORE = registerKey("soul_sand_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CALCITE_STONE_ORE = registerKey("calcite_stone_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GRASS_TO_STONE_ORE = registerKey("grass_to_stone_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GRASS_TO_GRANITE_ORE = registerKey("grass_to_granite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TUFF_ORE = registerKey("stone_tuff_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CLAY_ORE = registerKey("clay_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SAND_BEACH_ORE = registerKey("sand_beach_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SANDSTONE_ORE = registerKey("sandstone_ore");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
        RuleTest dirtTest = new TagMatchTest(BlockTags.DIRT);
        RuleTest volcanicStoneTest = new BlockMatchTest(StoneSets.VOLCANIC_ROCK.block().get());
        RuleTest stoneTest = new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD);
        RuleTest sandTest = new TagMatchTest(BlockTags.SAND);
        RuleTest soulGravelTest = new TagMatchTest(TagLoader.Blocks.SOIL_GRAVEL);
        RuleTest stoneBlockTest = new BlockMatchTest(Blocks.STONE);

        List<OreConfiguration.TargetBlockState> calciteList = List.of(
                OreConfiguration.target(stoneTest, Blocks.CALCITE.defaultBlockState()),
                OreConfiguration.target(dirtTest, Blocks.CALCITE.defaultBlockState()));
        List<OreConfiguration.TargetBlockState> powderSnowList = List.of(
                OreConfiguration.target(dirtTest, Blocks.POWDER_SNOW.defaultBlockState()),
                OreConfiguration.target(new TagMatchTest(BlockTags.SNOW), Blocks.POWDER_SNOW.defaultBlockState()));



        register(context, ANDESITE_ORE, Feature.ORE,
                new OreConfiguration(stoneTest, Blocks.ANDESITE.defaultBlockState(), 64, 0.4f));


        register(context, BASALT_ORE, Feature.ORE,
                new OreConfiguration(volcanicStoneTest, Blocks.BASALT.defaultBlockState(), 64, 0.4f));

        register(context, SMOOTH_BASALT_ORE, Feature.ORE,
                new OreConfiguration(stoneTest, Blocks.SMOOTH_BASALT.defaultBlockState(), 64, 0.4f));

        register(context, CALCITE_ORE, Feature.ORE,
                new OreConfiguration(calciteList, 64, 0.2f));

        register(context, COARSE_DIRT_ORE, Feature.ORE,
                new OreConfiguration(dirtTest, Blocks.COARSE_DIRT.defaultBlockState(), 48, 0.4f));

        register(context, DIORITE_ORE, Feature.ORE,
                new OreConfiguration(stoneTest, Blocks.DIORITE.defaultBlockState(), 64, 0.4f));

        register(context, DIRT_TO_GRASS_ORE, Feature.ORE,
                new OreConfiguration(dirtTest, Blocks.GRASS_BLOCK.defaultBlockState(), 64, 0.2f));


        register(context, DRIPSTONE_ORE, Feature.ORE,
                new OreConfiguration(stoneTest, Blocks.DRIPSTONE_BLOCK.defaultBlockState(), 64, 0.25f));


        register(context, GRAVEL_ORE, Feature.ORE,
                new OreConfiguration(dirtTest, Blocks.GRAVEL.defaultBlockState(), 48, 0.4f));


        register(context, GRANITE_ORE, Feature.ORE,
                new OreConfiguration(stoneTest, Blocks.GRANITE.defaultBlockState(), 64, 0.4f));


        register(context, MUD_ORE, Feature.ORE,
                new OreConfiguration(dirtTest, Blocks.MUD.defaultBlockState(), 48, 0.4f));
        register(context, PACKED_MUD_ORE, Feature.ORE,
                new OreConfiguration(dirtTest, Blocks.PACKED_MUD.defaultBlockState(), 48, 0.4f));

        register(context, PODZOL_ORE, Feature.ORE,
                new OreConfiguration(dirtTest, Blocks.PODZOL.defaultBlockState(), 48, 0.4f));

        register(context, POWDER_SNOW_ORE, Feature.ORE,
                new OreConfiguration(powderSnowList, 41));

        register(context, SAND_ORE, Feature.ORE,
                new OreConfiguration(dirtTest, Blocks.SAND.defaultBlockState(), 48, 0.4f));

        register(context, SNOW_BLOCK_ORE, Feature.ORE,
                new OreConfiguration(stoneTest, Blocks.SNOW_BLOCK.defaultBlockState(), 64, 0.5f));

        register(context, SOUL_SAND_ORE, Feature.ORE,
                new OreConfiguration(dirtTest, Blocks.SOUL_SAND.defaultBlockState(), 32, 0.4f));

        register(context, CALCITE_STONE_ORE, Feature.ORE,
                new OreConfiguration(stoneTest, Blocks.STONE.defaultBlockState(), 64, 0.4f));
        register(context, GRASS_TO_STONE_ORE, Feature.ORE,
                new OreConfiguration(dirtTest, Blocks.STONE.defaultBlockState(), 64, 0.25f));
        register(context, GRASS_TO_GRANITE_ORE, Feature.ORE,
                new OreConfiguration(dirtTest, Blocks.GRANITE.defaultBlockState(), 64, 0.25f));

        register(context, TUFF_ORE, Feature.ORE,
                new OreConfiguration(stoneTest, Blocks.TUFF.defaultBlockState(), 48, 0.25f));

        register(context, CLAY_ORE, Feature.ORE,
                new OreConfiguration(sandTest, Blocks.CLAY.defaultBlockState(), 50, 0f));

        register(context, SAND_BEACH_ORE, Feature.ORE,
                new OreConfiguration(soulGravelTest, Blocks.SAND.defaultBlockState(), 50, 0f));

        register(context, SANDSTONE_ORE, Feature.ORE,
                new OreConfiguration(stoneBlockTest, Blocks.SANDSTONE.defaultBlockState(), 64, 0f));

    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "ores/vanilla/" + name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext< ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature,
                                                                                          FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
