package com.legends.edumia.entity.races.human;

import com.google.common.collect.Maps;
import com.legends.edumia.Edumia;
import com.legends.edumia.entity.model.EdumiaEntityModelLayers;
import com.legends.edumia.entity.races.fairy.FairyEntity;
import com.legends.edumia.entity.races.fairy.FairyVariant;
import com.legends.edumia.entity.races.fairy.MainFairyModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
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
public class HumanRenderer  extends HumanoidMobRenderer<HumanEntity, MainHumanModel<HumanEntity>> {

    private static final String PATH = "textures/entities/human/";
    public HumanRenderer(EntityRendererProvider.Context context) {
        super(context, new MainHumanModel<>(context.bakeLayer(EdumiaEntityModelLayers.FAIRY)), 0.5f);
        this.addLayer(new HumanoidArmorLayer<>(this, new MainHumanModel<>(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)),
                new MainHumanModel<>(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), context.getModelManager()));
    }

    public static final Map<HumanVariant, String> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(HumanVariant.class), (resourceLocation) -> {
                resourceLocation.put(HumanVariant.FEMALE_1, PATH + "female1.png");
                resourceLocation.put(HumanVariant.MALE_1, PATH + "male1.png");
            });

    @Override
    public ResourceLocation getTextureLocation(HumanEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, LOCATION_BY_VARIANT.get(entity.getVariant()));
    }

    @Override
    public void render(HumanEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer,
                       int packedLight) {
        poseStack.scale(0.95f, 0.9f, 0.95f);
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
