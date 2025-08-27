package com.legends.edumia.entity.races.gensai;

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

public class GensaiRenderer  extends HumanoidMobRenderer<GensaiEntity, MainGensaiModel<GensaiEntity>> {

    private static final String PATH = "textures/entities/gensai/";
    public GensaiRenderer(EntityRendererProvider.Context context) {
        super(context, new MainGensaiModel<>(context.bakeLayer(EdumiaEntityModelLayers.GENSAI)), 0.5f);
        this.addLayer(new HumanoidArmorLayer<>(this, new MainGensaiModel<>(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)),
                new MainGensaiModel<>(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), context.getModelManager()));
    }

    public static final Map<GensaiVariant, String> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(GensaiVariant.class), (resourceLocation) -> {
                resourceLocation.put(GensaiVariant.MALE_1, PATH + "male1.png");
            });

    @Override
    public ResourceLocation getTextureLocation(GensaiEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, LOCATION_BY_VARIANT.get(entity.getVariant()));
    }

    @Override
    public void render(GensaiEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer,
                       int packedLight) {
        poseStack.scale(0.95f, 0.95f, 0.95f);
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
