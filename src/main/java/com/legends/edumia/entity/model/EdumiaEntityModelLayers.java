package com.legends.edumia.entity.model;

import com.legends.edumia.Edumia;
import com.legends.edumia.client.models.fairy.FairyModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@OnlyIn(Dist.CLIENT)
public final class EdumiaEntityModelLayers {
    public static final String MAIN = "main";
    public static final ModelLayerLocation FAIRY = create("fairy");

    // Helper to create layer locations
    private static ModelLayerLocation create(String name) {
        return new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, name), MAIN);
    }
}
