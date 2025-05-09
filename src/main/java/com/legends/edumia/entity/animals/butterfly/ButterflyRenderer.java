package com.legends.edumia.entity.animals.butterfly;

import com.google.common.collect.Maps;
import com.legends.edumia.Edumia;
import com.legends.edumia.client.models.animals.ButterflyModel;
import com.legends.edumia.entity.model.EdumiaEntityModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class ButterflyRenderer extends MobRenderer<ButterflyEntity, ButterflyModel<ButterflyEntity>> {

    private static final String PATH = "textures/entities/animals/butterfly/";

    public ButterflyRenderer(EntityRendererProvider.Context context) {
        super(context, new ButterflyModel<>(context.bakeLayer(EdumiaEntityModelLayers.BUTTERFLY)), 0.25f);
    }

    public static final Map<ButterflyVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(ButterflyVariant.class), (resourceLocation) -> {
                resourceLocation.put(ButterflyVariant.BLUE_MORPHO,
                        ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID,PATH + "blue_morpho.png"));
                resourceLocation.put(ButterflyVariant.MONARCH,
                        ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID,PATH + "monarch.png"));
                resourceLocation.put(ButterflyVariant.PURPLE_EMPEROR,
                        ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID,PATH + "purple_emperor.png"));
                resourceLocation.put(ButterflyVariant.RED_ADMIRAL,
                        ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID,PATH + "red_admiral.png"));
                resourceLocation.put(ButterflyVariant.SULPHUR,
                        ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID,PATH + "sulphur.png"));
                resourceLocation.put(ButterflyVariant.SWALLOWTAIL,
                        ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID,PATH + "swallowtail.png"));
            });

    @Override
    public ResourceLocation getTextureLocation(ButterflyEntity entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    @Override
    public void render(ButterflyEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer,
                       int packedLight) {
        poseStack.scale(0.45f, 0.45f, 0.45f);
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
