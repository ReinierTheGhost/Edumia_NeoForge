package com.legends.edumia.client.render.model.scatter;

import com.legends.edumia.client.render.model.BlockModelQuadsHolder;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.SimpleBakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScatterBlockModel extends SimpleBakedModel {

    private final List<BlockModelQuadsHolder> scatterVariantModels;
    public ScatterBlockModel(List<BlockModelQuadsHolder> scatterVariantModels, boolean ambOcc, boolean sideLight, boolean g3d, TextureAtlasSprite partTex, ItemTransforms transform, ItemOverrides overrides) {
        super(new ArrayList<>(), new HashMap<>(), ambOcc, sideLight, g3d, partTex, transform, overrides);
        this.scatterVariantModels = scatterVariantModels;
        if (this.scatterVariantModels.isEmpty()){
            throw new IllegalArgumentException("Model variant list cannot be empty!");
        }
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, RandomSource rand) {
        return this.getQuads(state, side, rand, ScatterPositionContext.newEmptyContext());
    }

    public List<BakedQuad> getQuads(BlockState state, Direction side, RandomSource rand, ScatterPositionContext scatterPositionContext) {
        return this.getQuads(state, side, rand);
    }
}
