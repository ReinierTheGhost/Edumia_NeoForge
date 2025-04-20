package com.legends.edumia.datagen.helpers.models;

import com.legends.edumia.blocks.AxialSlabBlock;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class SimpleMultiFaceSlabModel {
    public record Slab(Block block, AxialSlabBlock slab) {}
    public static List<Slab> blocks = new ArrayList<>() {
        {

        }
    };
}
