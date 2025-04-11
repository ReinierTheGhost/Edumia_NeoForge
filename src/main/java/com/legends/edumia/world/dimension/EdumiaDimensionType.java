package com.legends.edumia.world.dimension;

import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.worldgen.DimensionTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.storage.WritableLevelData;

import java.util.function.Supplier;

public abstract class EdumiaDimensionType extends Level {
    protected EdumiaDimensionType(WritableLevelData p_270739_, ResourceKey<Level> p_270683_, RegistryAccess p_270200_,
                                  Holder<DimensionType> p_270240_, Supplier<ProfilerFiller> p_270692_, boolean p_270904_,
                                  boolean p_270470_, long p_270248_, int p_270466_) {
        super(p_270739_, p_270683_, p_270200_, p_270240_, p_270692_, p_270904_, p_270470_, p_270248_, p_270466_);
    }
}
