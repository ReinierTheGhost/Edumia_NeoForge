package com.legends.edumia.entity.animals.dragonfly;

import com.google.common.collect.Maps;
import com.legends.edumia.Edumia;
import com.legends.edumia.client.models.animals.DragonflyModel;
import com.legends.edumia.entity.model.EdumiaEntityModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class DragonflyRenderer  extends MobRenderer<DragonflyEntity, DragonflyModel<DragonflyEntity>> {
    private static final String PATH = "textures/entities/animals/dragonfly/";

    public DragonflyRenderer(EntityRendererProvider.Context context) {
        super(context, new DragonflyModel<>(context.bakeLayer(EdumiaEntityModelLayers.DRAGONFLY)), 0.25f);
    }

    public static final Map<DragonflyVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(DragonflyVariant.class), (resourceLocation) -> {
                resourceLocation.put(DragonflyVariant.BLUE_MORPHO,
                        ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID,PATH + "blue_dasher.png"));
                resourceLocation.put(DragonflyVariant.BROAD_TAILED_SHADOWDRAGON,
                        ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID,PATH + "broad_tailed_shadowdragon.png"));
                resourceLocation.put(DragonflyVariant.GREEN_DARNER,
                        ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID,PATH + "green_darner.png"));
                resourceLocation.put(DragonflyVariant.YELLOW_WINGED_DARTER,
                        ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID,PATH + "yellow_winged_darter.png"));
            });

    @Override
    public ResourceLocation getTextureLocation(DragonflyEntity entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    @Override
    public void render(DragonflyEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer,
                       int packedLight) {
        poseStack.scale(0.45f, 0.45f, 0.45f);
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
