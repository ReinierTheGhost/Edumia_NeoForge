package com.legends.edumia.mixin;


import com.legends.edumia.world.RandomTickScheduler;
import com.legends.edumia.world.dimension.ModDimensions;

import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.storage.WritableLevelData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

@Mixin(ServerLevel.class)
public abstract class ServerLevelMixin extends Level {
    protected ServerLevelMixin(WritableLevelData levelData, ResourceKey<Level> dimension, RegistryAccess registryAccess,
                               Holder<DimensionType> dimensionTypeRegistration, Supplier<ProfilerFiller> profiler
            , boolean isClientSide, boolean isDebug, long biomeZoomSeed, int maxChainedNeighborUpdates) {
        super(levelData, dimension, registryAccess, dimensionTypeRegistration, profiler, isClientSide, isDebug,
                biomeZoomSeed, maxChainedNeighborUpdates);
    }

    @Inject(method = "tickChunk", at = @At("HEAD"))
    private void tickScheduledRandomTicks(LevelChunk chunk, int randomTickSpeed, CallbackInfo ci) {
        ((RandomTickScheduler) chunk).getScheduledRandomTicks().removeIf(scheduledPos -> {
            chunk.getBlockState(scheduledPos).randomTick((ServerLevel) (Object) this, scheduledPos, this.random);
            return true;
        });
    }

    @Inject(method = "tickChunk", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/server/level/ServerLevel;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"), cancellable = true)
    private void tickChunk(LevelChunk chunk, int randomTickSpeed, CallbackInfo ci) {
        if(ModDimensions.isInEdumia(chunk.getLevel())) {
            ci.cancel();
        }
    }


}
