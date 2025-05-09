package com.legends.edumia.entity.races.highelves;

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

public class HighElvenRenderer  extends HumanoidMobRenderer<HighElfEntity, HighElvenModel<HighElfEntity>> {

    private static final String PATH = "textures/entities/highelven/";
    public HighElvenRenderer(EntityRendererProvider.Context context) {
        super(context, new HighElvenModel<>(context.bakeLayer(EdumiaEntityModelLayers.HIGHELVEN)), 0.5f);
        this.addLayer(new HumanoidArmorLayer<>(this, new HighElvenModel<>(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)),
                new HighElvenModel<>(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), context.getModelManager()));
    }

    public static final Map<HighElvenVariant, String> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(HighElvenVariant.class), (resourceLocation) -> {
                resourceLocation.put(HighElvenVariant.BLOND_HEAR_FEMALE, PATH + "female1.png");
                resourceLocation.put(HighElvenVariant.RED_HEAR_MALE, PATH + "male1.png");

            });

    @Override
    public ResourceLocation getTextureLocation(HighElfEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, LOCATION_BY_VARIANT.get(entity.getVariant()));
    }

    @Override
    public void render(HighElfEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer,
                       int packedLight) {
        poseStack.scale(0.95f, 0.95f, 0.95f);
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
