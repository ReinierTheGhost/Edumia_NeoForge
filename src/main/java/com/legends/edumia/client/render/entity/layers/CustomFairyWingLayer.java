package com.legends.edumia.client.render.entity.layers;

import com.google.common.collect.Maps;
import com.legends.edumia.Edumia;
import com.legends.edumia.client.models.FairyWingsModel;
import com.legends.edumia.entity.model.EdumiaEntityModelLayers;
import com.legends.edumia.resources.datas.RaceType;
import com.legends.edumia.resources.datas.races.Race;
import com.legends.edumia.resources.datas.races.RaceUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.Map;

public class CustomFairyWingLayer<T extends LivingEntity, M extends HumanoidModel<T>, A extends HumanoidModel<T>> extends RenderLayer<T, M> {
    private static final Map<String, ResourceLocation> ARMOR_LOCATION_CACHE = Maps.newHashMap();
    public static final ResourceLocation FAIRY_WINGS_TEXTURE = ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "textures/entities/fairies/wings/orange_wings.png");
    private final FairyWingsModel<T> wingsModel;



    public CustomFairyWingLayer(RenderLayerParent<T, M>  renderer, EntityModelSet modelSet) {
        super(renderer);
        this.wingsModel = new FairyWingsModel<>(modelSet.bakeLayer(EdumiaEntityModelLayers.FAIRY_WINGS));


    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, T livingEntity, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
// Check if entity should have fairy wings (you'll need to implement this check)
        if (!shouldHaveFairyWings(livingEntity)) {
            return;
        }

        poseStack.pushPose();

        // Copy the main model's pose to the wing model
        this.getParentModel().copyPropertiesTo(this.wingsModel);


        // Get the vertex consumer for rendering the wings
        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityTranslucent(FAIRY_WINGS_TEXTURE));

        // Render the wing model
        this.wingsModel.renderToBuffer(
                poseStack,
                vertexConsumer,
                packedLight,
                OverlayTexture.NO_OVERLAY
        );

        poseStack.popPose();

    }

    private boolean shouldHaveFairyWings(T entity) {
        if (entity instanceof Player player) {
            Race currentRace = RaceUtil.getRace(player);
            if (currentRace.getRaceType() == RaceType.FAIRY){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }

    }

}
