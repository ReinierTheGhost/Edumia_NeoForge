package com.legends.edumia.datagen.custom.models;

import com.legends.edumia.blocks.AxialSlabBlock;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class SimpleAxisPillarSlabModel {

    public record Slab(Block block, AxialSlabBlock slab) {}

    public static List<Slab> blocks = new ArrayList<>() {
        {


        }
    };
}
