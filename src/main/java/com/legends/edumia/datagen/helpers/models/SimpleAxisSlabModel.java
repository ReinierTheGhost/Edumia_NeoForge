package com.legends.edumia.datagen.helpers.models;


import com.legends.edumia.blocks.AxialSlabBlock;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class SimpleAxisSlabModel {
    public record Slab(Block block, AxialSlabBlock slab) {}

    public static List<Slab> blocks = new ArrayList<>() {
        {
//            add(new Slab(BlockLoader.BLUE_BRICK, BlockLoader.BLUE_BRICK_SLAB));
//            add(new Slab(BlockLoader.CRACKED_GREEN_BASALT, BlockLoader.CRACKED_GREEN_BASALT_SLAB));
//            add(new Slab(BlockLoader.HIGH_ELVEN_BRICK_TILING, BlockLoader.HIGH_ELVEN_BRICK_TILING_SLAB));
//            add(new Slab(BlockLoader.LIGHT_HIGH_ELVEN_BRICK_TILING, BlockLoader.LIGHT_HIGH_ELVEN_BRICK_TILING_SLAB));
//            add(new Slab(BlockLoader.DARK_HIGH_ELVEN_BRICK_TILING, BlockLoader.DARK_HIGH_ELVEN_BRICK_TILING_SLAB));

        }
    };
}
