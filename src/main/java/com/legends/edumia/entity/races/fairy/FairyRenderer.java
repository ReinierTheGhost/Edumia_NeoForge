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
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class FairyRenderer extends HumanoidMobRenderer<FairyEntity, MainFairyModel<FairyEntity>> {

    private static final String PATH = "textures/entities/fairies/";
    public FairyRenderer(EntityRendererProvider.Context context) {
        super(context, new MainFairyModel<>(context.bakeLayer(EdumiaEntityModelLayers.FAIRY)), 0.5f);
        this.addLayer(new HumanoidArmorLayer<>(this, new MainFairyModel<>(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)),
                new MainFairyModel<>(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), context.getModelManager()));
    }

    public static final Map<FairyVariant, String> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(FairyVariant.class), (resourceLocation) -> {
                resourceLocation.put(FairyVariant.PURPLE_HEAR_FEMALE, PATH + "female1.png");
                resourceLocation.put(FairyVariant.ORANGE_WING_FEMALE, PATH + "female2.png");
            });

    @Override
    public ResourceLocation getTextureLocation(FairyEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, LOCATION_BY_VARIANT.get(entity.getVariant()));
    }

    @Override
    public void render(FairyEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer,
                       int packedLight) {
        poseStack.scale(0.95f, 0.9f, 0.95f);
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
