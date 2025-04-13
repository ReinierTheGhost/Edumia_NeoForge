package com.legends.edumia.mixin;

import net.minecraft.world.level.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(ChunkGenerator.class)
public class ChunkGeneratorMixin {

    @Debug(export = true)
    @ModifyArgs(method = "applyBiomeDecoration", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/level/ChunkPos;rangeClosed(Lnet/minecraft/world/level/ChunkPos;I)Ljava/util/stream/Stream;"))
    private void generateFeatures(Args args) {
        args.set(1, 4);
    }
}
