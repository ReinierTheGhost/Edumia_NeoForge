package com.legends.edumia.entity.model;

import com.legends.edumia.Edumia;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public final class EdumiaEntityModelLayers {
    public static final String MAIN = "main";
    public static final ModelLayerLocation FAIRY = create("fairy");
    public static final ModelLayerLocation HUMAN = create("human");
    public static final ModelLayerLocation HIGHELVEN = create("highelven");
    public static final ModelLayerLocation DARKELVEN = create("darkelven");
    public static final ModelLayerLocation ORC = create("orc");
    public static final ModelLayerLocation OGRE = create("ogre");


    public static final ModelLayerLocation BUTTERFLY = create("butterfly");
    public static final ModelLayerLocation DRAGONFLY = create("dragonfly");

    // Helper to create layer locations
    private static ModelLayerLocation create(String name) {
        return new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, name), MAIN);
    }
}
