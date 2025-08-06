package com.legends.edumia.world.biomes.caves;


import com.legends.edumia.world.biomes.BiomeColorsDTO;
import com.legends.edumia.world.biomes.EdumiaBiomeKeys;
import com.legends.edumia.world.biomes.surface.BiomeData;
import com.legends.edumia.world.placedfeatures.caves.CavePlacedFeatures;
import com.legends.edumia.world.placedfeatures.caves.JungleCavePlacedFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.phys.Vec2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ModCaveBiomes {
    public static final int defaultSky = 7907327;
    public static final int defaultFog = 12638463;
    public static final int defaultWater = 4159204;
    public static final int defaultWaterFog = 329011;

    private static List<ResourceKey<PlacedFeature>> vegetation = new ArrayList<>();
    private static List<ResourceKey<PlacedFeature>> undergroundOres = new ArrayList<>();
    private static List<ResourceKey<PlacedFeature>> undergroundDecoration = new ArrayList<>();

    public static CaveBiomesMap defaultCaves = new CaveBiomesMap();
    public static CaveBiomesMap ashCaves = new CaveBiomesMap();
    public static CaveBiomesMap forodCaves = new CaveBiomesMap();
    public static CaveBiomesMap haradCaves = new CaveBiomesMap();
    public static CaveBiomesMap avelionCaves = new CaveBiomesMap();
    public static CaveBiomesMap jungleCaves = new CaveBiomesMap();

    public static void init() {
        defaultCaves.addCave(new CaveBiomeDTO(EdumiaBiomeKeys.LUSH_CAVE, new Vec2(-1.0f,0f)));
        defaultCaves.addCave(new CaveBiomeDTO(EdumiaBiomeKeys.DRIPSTONE_CAVE, new Vec2(1.0f,0f)));
        defaultCaves.addCave(new CaveBiomeDTO(EdumiaBiomeKeys.MUD_CAVE, new Vec2(1.0f,1.0f)));
        defaultCaves.addCave(new CaveBiomeDTO(EdumiaBiomeKeys.FUNGUS_CAVE, new Vec2(0f,-1.0f)));
        defaultCaves.addCave(new CaveBiomeDTO(EdumiaBiomeKeys.BASIC_CAVE, new Vec2(0.0f,0.8f)));

        ashCaves.addCave(new CaveBiomeDTO(EdumiaBiomeKeys.DRIPSTONE_CAVE, new Vec2(1.0f,0.5f)));
        ashCaves.addCave(new CaveBiomeDTO(EdumiaBiomeKeys.BASALT_CAVE, new Vec2(-1.0f,0.5f)));
        ashCaves.addCave(new CaveBiomeDTO(EdumiaBiomeKeys.MAGMA_CAVE, new Vec2(0.0f,-1.0f)));

        haradCaves.addCave(new CaveBiomeDTO(EdumiaBiomeKeys.DRIPSTONE_CAVE, new Vec2(1.0f,0f)));
        haradCaves.addCave(new CaveBiomeDTO(EdumiaBiomeKeys.DRY_CAVE, new Vec2(0.0f,0f)));
        haradCaves.addCave(new CaveBiomeDTO(EdumiaBiomeKeys.BASIC_CAVE, new Vec2(-1.0f,0f)));

        forodCaves.addCave(new CaveBiomeDTO(EdumiaBiomeKeys.ICE_CAVE, new Vec2(-0.5f,0f)));
        forodCaves.addCave(new CaveBiomeDTO(EdumiaBiomeKeys.DRIPSTONE_CAVE, new Vec2(1.0f,0f)));

        avelionCaves.addCave(new CaveBiomeDTO(EdumiaBiomeKeys.BASIC_CAVE, new Vec2(0.0f, 1.0f)));

        jungleCaves.addCave(new CaveBiomeDTO(EdumiaBiomeKeys.JUNGLE_CAVE, new Vec2(1.0f, 0f)));
    }

    public static ResourceKey<Biome> getBiome(Vec2 coordinates, BiomeData surfaceBiome) {
        if (surfaceBiome.getCaveType() != null)
            return switch (surfaceBiome.getCaveType()) {
                case ASHEN -> ashCaves.getClosestBiome(coordinates);
                case HARAD -> haradCaves.getClosestBiome(coordinates);
                case FOROD -> forodCaves.getClosestBiome(coordinates);
                case AVELION -> avelionCaves.getClosestBiome(coordinates);
                case JUNGLE -> jungleCaves.getClosestBiome(coordinates);
                default -> defaultCaves.getClosestBiome(coordinates);
            };
            return defaultCaves.getClosestBiome(coordinates);
    }

    public static void bootstrap(BootstrapContext<Biome> context) {
        context.register(EdumiaBiomeKeys.BASIC_CAVE, createBasicCave(context, new BiomeColorsDTO(
                defaultSky, defaultFog, defaultWater, defaultWaterFog, 10338918, 10604137)));
        context.register(EdumiaBiomeKeys.LUSH_CAVE, createBasicCave(context, new BiomeColorsDTO(
                defaultSky, defaultFog, defaultWater, defaultWaterFog, 8703593, 8703593)));
        context.register(EdumiaBiomeKeys.DRIPSTONE_CAVE, createBasicCave(context, new BiomeColorsDTO(
                defaultSky, defaultFog, defaultWater, defaultWaterFog, 10338918, 10604137)));
        context.register(EdumiaBiomeKeys.MUD_CAVE, createBasicCave(context, new BiomeColorsDTO(
                defaultSky, defaultFog, defaultWater, defaultWaterFog, 7435337, 7905386)));
        context.register(EdumiaBiomeKeys.FUNGUS_CAVE, createBasicCave(context, new BiomeColorsDTO(
                defaultSky, defaultFog, defaultWater, defaultWaterFog, 5869935, 6263141)));

        context.register(EdumiaBiomeKeys.MISTIC_CAVE, createBasicCave(context, new BiomeColorsDTO(
                defaultSky, defaultFog, defaultWater, defaultWaterFog, 10338918, 10604137)));

        context.register(EdumiaBiomeKeys.BASALT_CAVE, createBasicCave(context, new BiomeColorsDTO(
                4142646, 3090215, 6450777, 1513734, 3550502, 2169880)));
        context.register(EdumiaBiomeKeys.MAGMA_CAVE, createBasicCave(context, new BiomeColorsDTO(
                4142646, 3090215, 6450777, 1513734, 3550502, 2169880)));

        context.register(EdumiaBiomeKeys.DRY_CAVE, createBasicCave(context, new BiomeColorsDTO(
                defaultSky, defaultFog, defaultWater, defaultWaterFog, 10928742, 11259497)));
        context.register(EdumiaBiomeKeys.ICE_CAVE, createBasicCave(context, new BiomeColorsDTO(
                defaultSky, defaultFog, defaultWater, defaultWaterFog, 11121530, 10990723)));

        context.register(EdumiaBiomeKeys.JUNGLE_CAVE, createJungleCave(context, new BiomeColorsDTO(
                8103167, 12638463, 4570768, 329011, 11121530, 8896601)));
    }

    public static Biome createBasicCave(BootstrapContext<Biome> context, BiomeColorsDTO biomeColors) {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE),
                context.lookup(Registries.CONFIGURED_CARVER));

        addBasicFeatures(generationSettings, true);

        return createBiome(biomeColors, spawnSettings, generationSettings, 0.5f, true);
    }

    public static Biome createJungleCave(BootstrapContext<Biome> context, BiomeColorsDTO biomeColors) {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE),
                context.lookup(Registries.CONFIGURED_CARVER));

        vegetation.add(CavePlacedFeatures.NOISE_REDUCER);
        vegetation.add(CavePlacedFeatures.NOISE_REDUCER_SMALL);
        vegetation.add(JungleCavePlacedFeatures.VEGETATION);
        vegetation.add(JungleCavePlacedFeatures.FLOOR);
        vegetation.add(JungleCavePlacedFeatures.CEILING_MOSS);
        addBasicFeatures(generationSettings, true);

        return createBiome(biomeColors, spawnSettings, generationSettings, 0.5f, true);
    }



    private static void addBasicFeatures(BiomeGenerationSettings.Builder generationSettings, boolean vanillaRocks) {



        undergroundOres.add(MiscOverworldPlacements.SPRING_WATER);

        undergroundOres.add(OrePlacements.ORE_GRAVEL);

        if(vanillaRocks) {
            undergroundOres.add(OrePlacements.ORE_DIRT);
            undergroundOres.add(OrePlacements.ORE_GRANITE_UPPER);
            undergroundOres.add(OrePlacements.ORE_GRANITE_LOWER);
            undergroundOres.add(OrePlacements.ORE_DIORITE_UPPER);
            undergroundOres.add(OrePlacements.ORE_DIORITE_LOWER);
            undergroundOres.add(OrePlacements.ORE_ANDESITE_UPPER);
            undergroundOres.add(OrePlacements.ORE_ANDESITE_LOWER);
        }
        undergroundOres.add(OrePlacements.ORE_TUFF);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CavePlacements.GLOW_LICHEN);
        BiomeDefaultFeatures.addSurfaceFreezing(generationSettings);
    }

    public static Biome createBiome(BiomeColorsDTO biomeColors, MobSpawnSettings.Builder spawnSettings, BiomeGenerationSettings.Builder generationSettings, float temperature, boolean precipitation) {

        undergroundOres = undergroundOres.stream().sorted(Comparator.comparing(a -> a.location().toString())).toList();

        undergroundDecoration = undergroundDecoration.stream().sorted(Comparator.comparing(a -> a.location().toString())).toList();

        vegetation = vegetation.stream().sorted(Comparator.comparing(a -> a.location().toString())).toList();
        for (int i = 0; i < vegetation.size() - 1; i++){
            if (vegetation.get(i).location().toString().equals(vegetation.get(i + 1).location().toString())){
                throw new IllegalStateException("Duplicate value in list for: " + vegetation.get(i).location().toString());
            }
        }
        for (ResourceKey<PlacedFeature> feature: undergroundOres) {
            generationSettings.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, feature);
        }
        for (ResourceKey<PlacedFeature> feature : undergroundDecoration){
            generationSettings.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, feature);
        }
        for (ResourceKey<PlacedFeature> feature: vegetation) {
            generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, feature);
        }

        Biome biome = (new Biome.BiomeBuilder())
                .hasPrecipitation(precipitation)
                .temperature(temperature)
                .downfall(0.5F)
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .skyColor(biomeColors.skyColor)
                        .fogColor(biomeColors.fogColor)
                        .waterColor(biomeColors.waterColor)
                        .waterFogColor(biomeColors.waterFogColor)
                        .grassColorOverride(biomeColors.grassColor)
                        .foliageColorOverride(biomeColors.foliageColor)
                        .build())
                .mobSpawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();
        undergroundOres = new ArrayList<>();
        undergroundDecoration = new ArrayList<>();
        vegetation = new ArrayList<>();
        return biome;
    }
}
