package com.legends.edumia.world.chunkgen;

import com.legends.edumia.Edumia;
import com.mojang.serialization.Codec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModChunkGenerators {
    public static final DeferredRegister<Codec<? extends ChunkGenerator>> GENERATORS =
            DeferredRegister.create((net.minecraft.resources.ResourceKey<? extends net.minecraft.core.Registry<Codec<? extends ChunkGenerator>>>) Registries.CHUNK_GENERATOR, Edumia.MOD_ID);

    public static void register(IEventBus modEventBus) {
        GENERATORS.register("edumia", () -> EdumiaChunkGenerator.CODEC);

        GENERATORS.register(modEventBus);
    }
}
