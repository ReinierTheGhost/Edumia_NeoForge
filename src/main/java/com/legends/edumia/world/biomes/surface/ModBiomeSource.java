package com.legends.edumia.world.biomes.surface;


import com.legends.edumia.utils.noises.BlendedNoise;
import com.legends.edumia.utils.noises.SimplexNoise;
import com.legends.edumia.world.biomes.EdumiaBiomeKeys;
import com.legends.edumia.world.biomes.caves.CaveType;
import com.legends.edumia.world.biomes.caves.ModCaveBiomes;
import com.legends.edumia.world.chunkgen.EdumiaChunkGenerator;
import com.legends.edumia.world.chunkgen.map.EdumiaHeightMap;
import com.legends.edumia.world.features.underground.CavesPlacedFeatures;
import com.legends.edumia.world.map.EdumiaMapRuntime;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.phys.Vec2;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Stream;
@SuppressWarnings("unchecked")
public class ModBiomeSource extends BiomeSource {

    public static final Codec<ModBiomeSource> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Codec.list(Biome.CODEC).fieldOf("biomes").forGetter((biomeSource) -> biomeSource.biomes)).apply(instance, ModBiomeSource::new));

    private final List<Holder<Biome>> biomes;
    private static final int CAVE_NOISE = 360;
    private static final int CAVE_OFFSET = 7220;
    public static final int SUB_BIOME_NOISE = 256;
    public static final int SUB_BIOME_OFFSET = 8240;
    private EdumiaMapRuntime edumiaMapRuntime;
    public ModBiomeSource(List<Holder<Biome>> biomes) {
        this.biomes = biomes;
        edumiaMapRuntime = EdumiaMapRuntime.getInstance();
    }



    @Override
    protected @NotNull MapCodec<? extends BiomeSource> codec() {
        return (MapCodec<? extends BiomeSource>) CODEC;
    }

    @Override
    protected @NotNull Stream<Holder<Biome>> collectPossibleBiomes() {
        return biomes.stream();
    }

    private ResourceKey<Biome> getCaveBiome(int x, int z, BiomeData surfaceBiome) {
        x += EdumiaHeightMap.getSeed();
        z += EdumiaHeightMap.getSeed();
        float temperature = (float) SimplexNoise.noise((double) x / CAVE_NOISE,  (double) z / CAVE_NOISE);
        float humidity = (float) SimplexNoise.noise((double) (x + CAVE_OFFSET) / CAVE_NOISE, (double)(z + CAVE_OFFSET) / CAVE_NOISE);
        return ModCaveBiomes.getBiome(new Vec2(temperature, humidity), surfaceBiome);
    }

    public static double getSubBiomeNoise(int x, int z, float frequency){
        x +=EdumiaHeightMap.getSeed();
        z += EdumiaHeightMap.getSeed();
        float noiseFrequency = (SUB_BIOME_NOISE * frequency);
        double perlin = 1 * BlendedNoise.noise((double) x / noiseFrequency, (double) z / noiseFrequency);
        perlin += 0.5f * BlendedNoise.noise((double) x * 2 / noiseFrequency, (double) z * 2 / noiseFrequency);
        perlin = perlin / (1 + 0.5f); // 2 octaves
        return perlin;
    }

    private ResourceKey<Biome> getSubBiome(int x, int z, BiomeData surfaceBiome){
        SubBiome subBiome = SubBiomes.getSubBiome(surfaceBiome.getBiomeResourceKey());
        if (subBiome != null){
            double perlin = getSubBiomeNoise(x, z, subBiome.getFrequency());
            SubBiome.SubBiomeData biomeData = SubBiomes.subBiomesMap.get(surfaceBiome.getBiomeResourceKey()).getBiomeAtNoise((float) perlin);
            if (biomeData != null) return biomeData.biome;
        }
        return surfaceBiome.getBiomeResourceKey();
    }

    @Override
    public Holder<Biome> getNoiseBiome(int x, int y, int z, Climate.Sampler noise) {
        int i = QuartPos.toBlock(x);
        int j = QuartPos.toBlock(y);
        int k = QuartPos.toBlock(z);

        MapBasedCustomBiome biomeHeightData = edumiaMapRuntime.getBiome(i, k);
        
        if (biomeHeightData == null) {
            return biomes.get(0);
        }

        BiomeData biome = biomeHeightData.getBiome();
        ResourceKey<Biome> processedBiome;

        float height = EdumiaChunkGenerator.DIRT_HEIGHT + EdumiaHeightMap.getHeight(i, k);
        if(j <= CavesPlacedFeatures.MAX_MISTIC_ORE_HEIGHT && biome.getCaveType() == CaveType.MISTIES) {
            processedBiome = EdumiaBiomeKeys.MISTIC_CAVE;
        }else if (j < (height -16)){
            processedBiome = getCaveBiome(i, k, biome);
        }
        else if (!MapBasedBiomePool.waterBiomes.contains(biome.getBiomeResourceKey())){
            SubBiome subBiome = SubBiomes.getSubBiome(biomeHeightData.getBiomeKey());
            if (subBiome != null){
                double perlin = ModBiomeSource.getSubBiomeNoise(i, k, subBiome.getFrequency());
                double additionalHeight = subBiome.getAdditionalHeight((float) perlin);
                additionalHeight *= EdumiaMapRuntime.getInstance().getEdge(i, k);
                height += (float) additionalHeight;
            }

            if(j <= CavesPlacedFeatures.MAX_MISTIC_ORE_HEIGHT && biome.getCaveType() == CaveType.MISTIES) {
                processedBiome = EdumiaBiomeKeys.MISTIC_CAVE;
            }else if(biome == MapBasedBiomePool.mangrove.getBiome() || biome == MapBasedBiomePool.floodedMangrove.getBiome()) {
                height = EdumiaChunkGenerator.DIRT_HEIGHT + EdumiaChunkGenerator.getMarshesHeight(i, k, height);
                if(j < (height - 20))
                    processedBiome = getCaveBiome(i, k, biome);
                else if(height < EdumiaChunkGenerator.WATER_HEIGHT)
                    processedBiome = MapBasedBiomePool.floodedMangrove.getBiomeKey();
                else
                    processedBiome = MapBasedBiomePool.mangrove.getBiomeKey();
            } else if(height <= biomeHeightData.getWaterHeight() + 1.25f) { // TODO : This is really rough, need to be re dynamic
                if (MapBasedBiomePool.coastalBiomes.contains(biome.getBiomeResourceKey())) {
                    processedBiome = MapBasedBiomePool.oceanCoast.getBiomeKey();
                } else if (MapBasedBiomePool.wastePondBiomes.contains(biome.getBiomeResourceKey())) {
                    processedBiome = MapBasedBiomePool.wastePond.getBiomeKey();
                } else if (MapBasedBiomePool.myrwoodSwampBiomes.contains(biome.getBiomeResourceKey())) {
                    processedBiome = MapBasedBiomePool.myrwoodSwamp.getBiomeKey();
                } else if (MapBasedBiomePool.oasisBiomes.contains(biome.getBiomeResourceKey())) {
                    processedBiome = MapBasedBiomePool.oasis.getBiomeKey();
                } else if (MapBasedBiomePool.frozenBiomes.contains(biome.getBiomeResourceKey())) {
                    processedBiome = MapBasedBiomePool.frozenPond.getBiomeKey();
                } else if (MapBasedBiomePool.anduinWaterBiomes.contains(biome.getBiomeResourceKey())) {
                    processedBiome = MapBasedBiomePool.greatRiver.getBiomeKey();
                } else {
                    processedBiome = MapBasedBiomePool.pond.getBiomeKey();
                }
            } else {
                processedBiome = getSubBiome(i, k, biome);
            }
        } else processedBiome = biome.getBiomeResourceKey();

        return biomes.stream().filter(
                        b -> b.unwrapKey().get().toString().equalsIgnoreCase(processedBiome.toString()))
                .findFirst().get();
    }
}
