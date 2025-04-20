package com.legends.edumia.datagen.helpers.models;

import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class SimplePottedFlowerModel {
    public record Potted(Block block, Block flower) {}
    public static List<Potted> blocks = new ArrayList<>() {
        {

        }
    };
}
