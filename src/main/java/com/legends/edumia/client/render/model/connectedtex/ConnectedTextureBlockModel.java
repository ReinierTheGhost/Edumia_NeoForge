package com.legends.edumia.client.render.model.connectedtex;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mojang.math.Transformation;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.SimpleBakedModel;
import net.neoforged.neoforge.client.RenderTypeGroup;

import java.util.List;

public class ConnectedTextureBlockModel extends SimpleBakedModel {
    private final ConnectedTextureUnbakedModel.DeferredConnectedTextureModelBakery deferredConnectedTextureModelBakery;
    private final Transformation blockstateRotation;
    private final ConnectedTexture3DContext itemRenderContext;
    private final ConnectedTexture3DContext.BlockConnectionType connectionType;


    public ConnectedTextureBlockModel(boolean ambOcc, boolean sideLight, boolean g3d, TextureAtlasSprite partTex, ItemTransforms camaraTransform, ItemOverrides overrides,
                                      ConnectedTextureUnbakedModel.DeferredConnectedTextureModelBakery deferredConnectedTextureModelBakery, Transformation rotation, ConnectedTexture3DContext itemRenderContext, ConnectedTexture3DContext.BlockConnectionType connectionType) {
        super(ImmutableList.of(), ImmutableMap.of(), ambOcc, sideLight, g3d, partTex, camaraTransform, overrides, RenderTypeGroup.EMPTY);
        this.deferredConnectedTextureModelBakery = deferredConnectedTextureModelBakery;
        blockstateRotation = rotation;
        this.itemRenderContext = itemRenderContext;
        this.connectionType = connectionType;
    }
}
