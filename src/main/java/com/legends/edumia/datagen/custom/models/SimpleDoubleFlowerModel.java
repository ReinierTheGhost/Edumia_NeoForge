package com.legends.edumia.datagen.custom.models;

import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class SimpleDoubleFlowerModel {

    public record Flower(Block flower) {}

    public static List<Block> blocks = new ArrayList<>() {
        {
//            add(BlockLoader.YELLOW_IRIS);
//            add(BlockLoader.FLAME_OF_THE_SOUTH);
//            add(BlockLoader.HIBISCUS);

        }
    };
}
