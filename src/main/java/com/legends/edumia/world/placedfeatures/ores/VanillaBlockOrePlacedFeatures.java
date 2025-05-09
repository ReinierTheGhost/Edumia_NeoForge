package com.legends.edumia.world.placedfeatures.ores;

import com.legends.edumia.Edumia;
import com.legends.edumia.world.congiguredfeatures.ores.VanillaBlockOreConfiguredFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.heightproviders.TrapezoidHeight;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class VanillaBlockOrePlacedFeatures {
    public static final ResourceKey<PlacedFeature> ANDESITE_ORE = registerKey("andesite_ore");
    public static final ResourceKey<PlacedFeature> BASALT_ORE = registerKey("basalt_ore");
    public static final ResourceKey<PlacedFeature> SMOOTH_BASALT_ORE = registerKey("smooth_basalt_ore");
    public static final ResourceKey<PlacedFeature> CALCITE_ORE = registerKey("calcite_ore");
    public static final ResourceKey<PlacedFeature> RARE_CALCITE_ORE = registerKey("rare_calcite_ore");
    public static final ResourceKey<PlacedFeature> COARSE_DIRT_ORE = registerKey("coarse_dirt_ore");
    public static final ResourceKey<PlacedFeature> DIORITE_ORE = registerKey("diorite_ore");
    public static final ResourceKey<PlacedFeature> DIRT_TO_GRASS_ORE = registerKey("dirt_to_grass_ore");
    public static final ResourceKey<PlacedFeature> DRIPSTONE_ORE = registerKey("dripstone_ore");
    public static final ResourceKey<PlacedFeature> GRANITE_ORE = registerKey("granite_ore");
    public static final ResourceKey<PlacedFeature> GRAVEL_ORE = registerKey("gravel_ore");
    public static final ResourceKey<PlacedFeature> ABUNDANT_MUD_ORE = registerKey("abundant_mud_ore");
    public static final ResourceKey<PlacedFeature> MUD_ORE = registerKey("mud_ore");
    public static final ResourceKey<PlacedFeature> PACKED_MUD_ORE = registerKey("packed_mud_ore");
    public static final ResourceKey<PlacedFeature> PODZOL_ORE = registerKey("podzol_ore");
    public static final ResourceKey<PlacedFeature> ABUNDANT_PODZOL_ORE = registerKey("abundant_podzol_ore");
    public static final ResourceKey<PlacedFeature> POWDER_SNOW_ORE = registerKey("powder_snow_ore");
    public static final ResourceKey<PlacedFeature> SAND_ORE = registerKey("sand_ore");
    public static final ResourceKey<PlacedFeature> SNOW_ORE = registerKey("snow_ore");
    public static final ResourceKey<PlacedFeature> SOUL_SAND_ORE = registerKey("soul_sand_ore");
    public static final ResourceKey<PlacedFeature> CALCITE_STONE_ORE = registerKey("calcite_to_stone_ore");
    public static final ResourceKey<PlacedFeature> GRASS_TO_STONE_ORE = registerKey("grass_to_stone_ore");
    public static final ResourceKey<PlacedFeature> GRASS_TO_GRANITE_ORE = registerKey("grass_to_granite_ore");
    public static final ResourceKey<PlacedFeature> TUFF_ORE = registerKey("tuff_ore");
    public static final ResourceKey<PlacedFeature> ABUNDANT_TUFF_ORE = registerKey("abundant_tuff_ore");

    public static void boostrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureRegistryEntryLookup = context.lookup(Registries.CONFIGURED_FEATURE);
        Holder.Reference<ConfiguredFeature<?, ?>> andesite = configuredFeatureRegistryEntryLookup.getOrThrow(VanillaBlockOreConfiguredFeatures.ANDESITE_ORE);
        Holder.Reference<ConfiguredFeature<?, ?>> basalt = configuredFeatureRegistryEntryLookup.getOrThrow(VanillaBlockOreConfiguredFeatures.BASALT_ORE);
        Holder.Reference<ConfiguredFeature<?, ?>> smoothBasalt = configuredFeatureRegistryEntryLookup.getOrThrow(VanillaBlockOreConfiguredFeatures.SMOOTH_BASALT_ORE);
        Holder.Reference<ConfiguredFeature<?, ?>> calcite = configuredFeatureRegistryEntryLookup.getOrThrow(VanillaBlockOreConfiguredFeatures.CALCITE_ORE);
        Holder.Reference<ConfiguredFeature<?, ?>> coarseDirt = configuredFeatureRegistryEntryLookup.getOrThrow(VanillaBlockOreConfiguredFeatures.COARSE_DIRT_ORE);
        Holder.Reference<ConfiguredFeature<?, ?>> diorite = configuredFeatureRegistryEntryLookup.getOrThrow(VanillaBlockOreConfiguredFeatures.DIORITE_ORE);
        Holder.Reference<ConfiguredFeature<?, ?>> dirtToGrass = configuredFeatureRegistryEntryLookup.getOrThrow(VanillaBlockOreConfiguredFeatures.DIRT_TO_GRASS_ORE);
        Holder.Reference<ConfiguredFeature<?, ?>> dripstone = configuredFeatureRegistryEntryLookup.getOrThrow(VanillaBlockOreConfiguredFeatures.DRIPSTONE_ORE);
        Holder.Reference<ConfiguredFeature<?, ?>> snowBlock = configuredFeatureRegistryEntryLookup.getOrThrow(VanillaBlockOreConfiguredFeatures.SNOW_BLOCK_ORE);
        Holder.Reference<ConfiguredFeature<?, ?>> soulSand = configuredFeatureRegistryEntryLookup.getOrThrow(VanillaBlockOreConfiguredFeatures.SOUL_SAND_ORE);
        Holder.Reference<ConfiguredFeature<?, ?>> granite = configuredFeatureRegistryEntryLookup.getOrThrow(VanillaBlockOreConfiguredFeatures.GRANITE_ORE);
        Holder.Reference<ConfiguredFeature<?, ?>> gravel = configuredFeatureRegistryEntryLookup.getOrThrow(VanillaBlockOreConfiguredFeatures.GRAVEL_ORE);
        Holder.Reference<ConfiguredFeature<?, ?>> mud = configuredFeatureRegistryEntryLookup.getOrThrow(VanillaBlockOreConfiguredFeatures.MUD_ORE);
        Holder.Reference<ConfiguredFeature<?, ?>> packedMud = configuredFeatureRegistryEntryLookup.getOrThrow(VanillaBlockOreConfiguredFeatures.PACKED_MUD_ORE);
        Holder.Reference<ConfiguredFeature<?, ?>> podzol = configuredFeatureRegistryEntryLookup.getOrThrow(VanillaBlockOreConfiguredFeatures.PODZOL_ORE);
        Holder.Reference<ConfiguredFeature<?, ?>> powderSnow = configuredFeatureRegistryEntryLookup.getOrThrow(VanillaBlockOreConfiguredFeatures.POWDER_SNOW_ORE);
        Holder.Reference<ConfiguredFeature<?, ?>> sand = configuredFeatureRegistryEntryLookup.getOrThrow(VanillaBlockOreConfiguredFeatures.SAND_ORE);
        Holder.Reference<ConfiguredFeature<?, ?>> calciteStone = configuredFeatureRegistryEntryLookup.getOrThrow(VanillaBlockOreConfiguredFeatures.CALCITE_STONE_ORE);
        Holder.Reference<ConfiguredFeature<?, ?>> grassStone = configuredFeatureRegistryEntryLookup.getOrThrow(VanillaBlockOreConfiguredFeatures.GRASS_TO_STONE_ORE);
        Holder.Reference<ConfiguredFeature<?, ?>> grassGranite = configuredFeatureRegistryEntryLookup.getOrThrow(VanillaBlockOreConfiguredFeatures.GRASS_TO_GRANITE_ORE);
        Holder.Reference<ConfiguredFeature<?, ?>> stoneTuff = configuredFeatureRegistryEntryLookup.getOrThrow(VanillaBlockOreConfiguredFeatures.TUFF_ORE);

        PlacementModifier abundant = PlacementUtils.countExtra(2, 0.5f, 1);
        PlacementModifier frequent = PlacementUtils.countExtra(1, 0.5f, 1);
        PlacementModifier veryCommon = RarityFilter.onAverageOnceEvery(1);
        PlacementModifier common = RarityFilter.onAverageOnceEvery(2);
        PlacementModifier uncommon = PlacementUtils.countExtra(0, 0.25f, 1);
        PlacementModifier rare = PlacementUtils.countExtra(0, 0.1f, 1);

        register(context, ANDESITE_ORE, andesite, List.of(common,
                InSquarePlacement.spread(),  PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        register(context, BASALT_ORE, basalt, List.of(uncommon,
                InSquarePlacement.spread(),  PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        register(context, SMOOTH_BASALT_ORE, smoothBasalt, List.of(frequent,
                InSquarePlacement.spread(),  PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        register(context, CALCITE_ORE, calcite, List.of(veryCommon,
                InSquarePlacement.spread(),  PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        register(context, RARE_CALCITE_ORE, calcite, List.of(uncommon,
                InSquarePlacement.spread(),  PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        register(context, COARSE_DIRT_ORE, coarseDirt, List.of(common,
                InSquarePlacement.spread(),  PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        register(context, DIORITE_ORE, diorite, List.of(abundant,
                InSquarePlacement.spread(),  PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        register(context, DIRT_TO_GRASS_ORE, dirtToGrass, List.of(abundant,
                InSquarePlacement.spread(),  PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        register(context, DRIPSTONE_ORE, dripstone, List.of(veryCommon,
                InSquarePlacement.spread(),  PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        register(context, GRANITE_ORE, granite, List.of(common,
                InSquarePlacement.spread(),  PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        register(context, GRAVEL_ORE, gravel, List.of(rare,
                InSquarePlacement.spread(),  PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        register(context, ABUNDANT_MUD_ORE, mud, List.of(abundant,
                InSquarePlacement.spread(),  PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        register(context, MUD_ORE, mud, List.of(common,
                InSquarePlacement.spread(),  PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        register(context, PACKED_MUD_ORE, packedMud, List.of(common,
                InSquarePlacement.spread(),  PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        register(context, PODZOL_ORE, podzol, List.of(common,
                InSquarePlacement.spread(),  PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        register(context, ABUNDANT_PODZOL_ORE, podzol, List.of(abundant,
                InSquarePlacement.spread(),  PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        register(context, POWDER_SNOW_ORE, powderSnow, List.of(veryCommon,
                InSquarePlacement.spread(),  PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        register(context, SAND_ORE, sand, List.of(common,
                InSquarePlacement.spread(),  PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        register(context, SNOW_ORE, snowBlock,  List.of(PlacementUtils.countExtra(3, 0.5f, 1),
                        HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(180), VerticalAnchor.absolute(460))),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        register(context, SOUL_SAND_ORE, soulSand, List.of(common,
                InSquarePlacement.spread(),  PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        register(context, CALCITE_STONE_ORE, calciteStone, List.of(abundant,
                InSquarePlacement.spread(),  PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        register(context, GRASS_TO_STONE_ORE, grassStone, List.of(veryCommon,
                InSquarePlacement.spread(),  PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        register(context, GRASS_TO_GRANITE_ORE, grassGranite, List.of(veryCommon,
                InSquarePlacement.spread(),  PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        register(context, TUFF_ORE, stoneTuff, List.of(veryCommon,
                InSquarePlacement.spread(),  PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        register(context, ABUNDANT_TUFF_ORE, stoneTuff, List.of(abundant,
                InSquarePlacement.spread(),  PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "ores/vanilla/" + name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
