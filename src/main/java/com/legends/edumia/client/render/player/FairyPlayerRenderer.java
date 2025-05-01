package com.legends.edumia.client.render.player;

import com.legends.edumia.client.render.entity.layers.CustomFairyWingLayer;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;

public class FairyPlayerRenderer  extends LivingEntityRenderer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>>
{
    public FairyPlayerRenderer(EntityRendererProvider.Context context) {
        super(context, new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER), false), 0.5F);

        this.addLayer(new CustomFairyWingLayer<>(this, context.getModelSet()));
    }


    @Override
    public ResourceLocation getTextureLocation(AbstractClientPlayer entity) {
        return CustomFairyWingLayer.FAIRY_WINGS_TEXTURE;
    }
}
