package com.legends.edumia.datagen;

import com.legends.edumia.Edumia;
import com.legends.edumia.resources.EdumiaFactions;
import com.legends.edumia.resources.EdumiaNpcs;
import com.legends.edumia.resources.EdumiaRaces;
import com.legends.edumia.world.biomes.surface.ModBiomes;
import com.legends.edumia.world.congiguredfeatures.ModConfiguredFeatures;
import com.legends.edumia.world.placedfeatures.ModPlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class WorldGenerator  extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::boostrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::boostrap)
            .add(Registries.BIOME, ModBiomes::bootstrap)
            .add(EdumiaRaces.RACE_KEY, EdumiaRaces::bootstrap)
            .add(EdumiaFactions.FACTION_KEY, EdumiaFactions::bootstrap)
            .add(EdumiaNpcs.NPC_KEY, EdumiaNpcs::bootstrap);

    public WorldGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(Edumia.MOD_ID));
    }
}
