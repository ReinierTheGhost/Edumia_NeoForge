package com.legends.edumia.mixin;

import com.legends.edumia.utils.EdumiaBannerPatterns;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.level.block.entity.BannerPatterns;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BannerPatterns.class)
public class BannerPatternRegisterMixin {
    @Inject(method = "bootstrap", at = @At(value = "TAIL", shift = At.Shift.BEFORE))
    private static void registerEdumiaBannerPatterns(BootstrapContext<BannerPattern> context, CallbackInfo ci) {
        EdumiaBannerPatterns.bootstrap(context);
    }
}
