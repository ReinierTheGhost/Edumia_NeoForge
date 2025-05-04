package com.legends.edumia.utils;

import com.legends.edumia.Edumia;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BannerPattern;

public class EdumiaBannerPatterns {
    public static final ResourceKey<BannerPattern> HIGH_ELVEN = key("high_elven");
    public static final ResourceKey<BannerPattern> DARK_ELVEN = key("dark_elven");
    public static final ResourceKey<BannerPattern> DEMONS = key("demons");
    public static final ResourceKey<BannerPattern> HUMANS = key("humans");
    public static final ResourceKey<BannerPattern> FAIRIES = key("fairies");
    public static final ResourceKey<BannerPattern> GENSAI = key("gensai");
    public static final ResourceKey<BannerPattern> ORCS = key("orcs");
    public static final ResourceKey<BannerPattern> OGRES = key("ogres");

    private static ResourceKey<BannerPattern> key(String id) {
        return ResourceKey.create(Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, id));
    }

    public static void bootstrap(BootstrapContext<BannerPattern> context) {
        register(context, HIGH_ELVEN);
        register(context, DARK_ELVEN);
        register(context, DEMONS);
        register(context, HUMANS);
        register(context, FAIRIES);
        register(context, GENSAI);
        register(context, ORCS);
        register(context, OGRES);
    }

    public static void register(BootstrapContext<BannerPattern> context, ResourceKey<BannerPattern> resourceKey) {
        context.register(resourceKey, new BannerPattern(resourceKey.location(), "block.edumia.banner." + resourceKey.location().toShortLanguageKey()));
    }
}
