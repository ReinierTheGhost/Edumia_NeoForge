package com.legends.edumia.entity.races.fairy;

import com.google.common.collect.Maps;
import com.legends.edumia.Edumia;
import com.legends.edumia.client.models.fairy.FairyModel;
import com.legends.edumia.entity.model.EdumiaEntityModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class FairyRenderer extends HumanoidMobRenderer<FairyEntity, FairyModel<FairyEntity>> {

    private static final String PATH = "textures/entities/fairies/";
    public FairyRenderer(EntityRendererProvider.Context context, FairyModel<FairyEntity> model, float shadowRadius) {
        super(context, new FairyModel<>(context.bakeLayer(EdumiaEntityModelLayers.FAIRY)), 0.5f);
        this.addLayer(new HumanoidArmorLayer<>(this, new FairyModel<>(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)),
                new FairyModel<>(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), context.getModelManager()));
    }

    public static final Map<FairyVariant, String> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(FairyVariant.class), (resourceLocation) -> {
                resourceLocation.put(FairyVariant.PURPLE_HEAR_FEMALE, PATH + "female1.png");
            });

    @Override
    public ResourceLocation getTextureLocation(FairyEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, LOCATION_BY_VARIANT.get(entity.getVariant()));
    }

    @Override
    public void render(FairyEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer,
                       int packedLight) {
        poseStack.scale(0.95f, 1.0f, 0.95f);
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
