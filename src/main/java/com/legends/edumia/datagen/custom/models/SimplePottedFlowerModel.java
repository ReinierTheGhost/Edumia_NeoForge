package com.legends.edumia.datagen.custom.models;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;

import java.util.ArrayList;
import java.util.List;

public class SimplePottedFlowerModel {
    public record Potted(Block block, Block flower) {}
    public static List<Potted> blocks = new ArrayList<>() {
        {

        }
    };
}
