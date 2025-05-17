package com.legends.edumia.entity.races.ogre;

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

public class OgreRenderer  extends HumanoidMobRenderer<OgreEntity, MainOgreModel<OgreEntity>> {


    private static final String PATH = "textures/entities/ogres/";
    public OgreRenderer(EntityRendererProvider.Context context) {
        super(context, new MainOgreModel<>(context.bakeLayer(EdumiaEntityModelLayers.OGRE)), 0.5f);
        this.addLayer(new HumanoidArmorLayer<>(this, new MainOgreModel<>(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)),
                new MainOgreModel<>(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), context.getModelManager()));
    }

    public static final Map<OgreVariant, String> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(OgreVariant.class), (resourceLocation) -> {
                resourceLocation.put(OgreVariant.RED_MALE, PATH + "male1.png");

            });

    @Override
    public ResourceLocation getTextureLocation(OgreEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, LOCATION_BY_VARIANT.get(entity.getVariant()));
    }

    @Override
    public void render(OgreEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer,
                       int packedLight) {
        poseStack.scale(1f, 1f, 1f);
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
