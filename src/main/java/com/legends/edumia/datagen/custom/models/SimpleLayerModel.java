package com.legends.edumia.datagen.custom.models;


import net.minecraft.world.level.block.Block;


import java.util.ArrayList;
import java.util.List;

public class SimpleLayerModel {

    public record Layer(Block texture, Block layer) {}

    public static List<Layer> blocks = new ArrayList<>() {
        {
//            add(new Layer(BlockLoader.WHITE_SAND, BlockLoader.WHITE_SAND_LAYER));
//            add(new Layer(Blocks.SAND, BlockLoader.SAND_LAYER));
//            add(new Layer(Blocks.RED_SAND, BlockLoader.RED_SAND_LAYER));
        }
    };
}
