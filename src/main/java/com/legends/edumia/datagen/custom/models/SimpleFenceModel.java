package com.legends.edumia.datagen.custom.models;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;

import java.util.ArrayList;
import java.util.List;

public class SimpleFenceModel {
    public record Fence(Block block, FenceBlock fence) {}
    public static List<Fence> blocks = new ArrayList<>() {
        {
        }
    };
}
