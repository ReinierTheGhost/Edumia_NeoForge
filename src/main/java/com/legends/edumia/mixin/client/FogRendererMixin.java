package com.legends.edumia.mixin.client;

import com.legends.edumia.world.biomes.EdumiaBiomeFogData;
import com.legends.edumia.world.dimension.ModDimensions;
import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Camera;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

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
                Optional<ResourceKey<Biome>> biomeResourceKey = localPlayer.level().getBiome(localPlayer.getOnPos()).unwrapKey();
                if(biomeResourceKey.isPresent() && EdumiaBiomeFogData.DATA.containsKey(biomeResourceKey.get())){
                    EdumiaBiomeFogData fogData = EdumiaBiomeFogData.DATA.get(biomeResourceKey.get());

                    if (fogStartMultiplier < fogData.fogStart) {
                        fogStartMultiplier = Math.min(fogStartMultiplier + (tickDelta * TICK_SPEED), fogData.fogStart);
                    } else if (fogStartMultiplier > fogData.fogStart) {
                        fogStartMultiplier = Math.max(fogStartMultiplier - (tickDelta * TICK_SPEED), fogData.fogStart);
                    }

                    if (fogEndMultiplier < fogData.fogEnd) {
                        fogEndMultiplier = Math.min(fogEndMultiplier + (tickDelta * TICK_SPEED), fogData.fogEnd);
                    } else if (fogEndMultiplier > fogData.fogEnd) {
                        fogEndMultiplier = Math.max(fogEndMultiplier - (tickDelta * TICK_SPEED), fogData.fogEnd);
                    }
                } else {
                    fogEndMultiplier = Math.min(fogEndMultiplier + (tickDelta * TICK_SPEED), 1);
                    fogStartMultiplier = Math.min(fogStartMultiplier + (tickDelta * TICK_SPEED), 1);
                }

                float f = Mth.clamp(viewDistance / 10.0F, 4.0F, 64.0F);
                float fogStart = (viewDistance - f) * fogStartMultiplier;
                float fogEnd = viewDistance * fogEndMultiplier;

                RenderSystem.setShaderFogStart(fogStart);
                RenderSystem.setShaderFogEnd(fogEnd);
                RenderSystem.setShaderFogShape(FogShape.SPHERE);
            }
        }
    }
}