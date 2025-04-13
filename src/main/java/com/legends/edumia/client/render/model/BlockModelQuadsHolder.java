package com.legends.edumia.client.render.model;

import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.core.Direction;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;

public class BlockModelQuadsHolder {
    public final List<BakedQuad> generalQuads;

    public final Map<Direction, List<BakedQuad>> faceQuads;

    public BlockModelQuadsHolder(List<BakedQuad> generalQuads, Map<Direction, List<BakedQuad>> faceQuads) {
        this.generalQuads = generalQuads;
        this.faceQuads = faceQuads;
    }

    public List<BakedQuad> getQuads(Direction side){
        return side == null ? this.generalQuads : this.faceQuads.get(side);
    }

}
