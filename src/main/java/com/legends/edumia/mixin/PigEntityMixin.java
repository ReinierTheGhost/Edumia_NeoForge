package com.legends.edumia.mixin;

import com.legends.edumia.world.dimension.ModDimensions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.animal.Pig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Pig.class)
public class PigEntityMixin {
    @Inject(at = @At(value = "HEAD"), method = "thunderHit", cancellable = true)
    private void thunderHit(ServerLevel world, LightningBolt lightning, CallbackInfo callBackInfo) {
        if(ModDimensions.isInEdumia(world)) {
            callBackInfo.cancel();
        }
    }
}
