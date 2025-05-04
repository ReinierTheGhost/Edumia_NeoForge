package com.legends.edumia.utils;

import net.minecraft.world.level.material.MapColor;

public enum CustomDyeColor {
    TRANSLUCENT("translucent", -1, MapColor.NONE, -1, true); // The -1 values are placeholders

    private final String name;
    private final int textColor;
    private final MapColor mapColor;
    private final float[] colors;
    private final boolean isTranslucent;

    CustomDyeColor(String name, int textColor, MapColor mapColor, int colorValue, boolean isTranslucent) {
        this.name = name;
        this.textColor = textColor;
        this.mapColor = mapColor;
        this.colors = calcColors(colorValue);
        this.isTranslucent = isTranslucent;
    }

    private static float[] calcColors(int color) {
        // Calculate RGB values
        return new float[]{
                (float)(color >> 16 & 255) / 255.0F,
                (float)(color >> 8 & 255) / 255.0F,
                (float)(color & 255) / 255.0F
        };
    }

}
