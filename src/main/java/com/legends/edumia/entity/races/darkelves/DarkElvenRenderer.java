package com.legends.edumia.entity.races.darkelves;

import com.google.common.collect.Maps;
import com.legends.edumia.Edumia;
import com.legends.edumia.entity.model.EdumiaEntityModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class DarkElvenRenderer extends HumanoidMobRenderer<DarkElfEntity, DarkElvenModel<DarkElfEntity>> {

    private static final String PATH = "textures/entities/darkelven/";
    public DarkElvenRenderer(EntityRendererProvider.Context context) {
        super(context, new DarkElvenModel<>(context.bakeLayer(EdumiaEntityModelLayers.DARKELVEN)), 0.5f);
        this.addLayer(new HumanoidArmorLayer<>(this, new DarkElvenModel<>(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)),
                new DarkElvenModel<>(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), context.getModelManager()));
    }

    public static final Map<DarkElvenVariant, String> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(DarkElvenVariant.class), (resourceLocation) -> {
                resourceLocation.put(DarkElvenVariant.WHITE_HEAR_MALE, PATH + "male1.png");

            });

    @Override
    public ResourceLocation getTextureLocation(DarkElfEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, LOCATION_BY_VARIANT.get(entity.getVariant()));
    }

    @Override
    public void render(DarkElfEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer,
                       int packedLight) {
        poseStack.scale(0.95f, 0.95f, 0.95f);
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
