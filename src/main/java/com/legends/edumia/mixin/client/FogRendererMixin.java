package com.legends.edumia.mixin.client;

import com.legends.edumia.world.dimension.ModDimensions;
import net.minecraft.client.Camera;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@OnlyIn(Dist.CLIENT)
@Mixin(FogRenderer.class)

public class FogRendererMixin {
    private static final float TICK_SPEED = 0.01f;
    private static float fogStartMultiplier = 0.25f;
    private static float fogEndMultiplier = 1f;

    @Inject(method = "setupFog", at = @At("TAIL"))
    private static void setupFog(Camera camera, FogRenderer.FogMode fogMode, float viewDistance, boolean thickFog, float tickDelta, CallbackInfo ci) {
        Entity entity =  camera.getEntity();
        if(entity instanceof LocalPlayer localPlayer) {
            if (
                    !localPlayer.hasEffect(MobEffects.DARKNESS) &&
                    !localPlayer.hasEffect(MobEffects.BLINDNESS) &&
                    !localPlayer.isUnderWater() &&
                    ModDimensions.isInEdumia(localPlayer.level())){

            }

        }
    }
}
