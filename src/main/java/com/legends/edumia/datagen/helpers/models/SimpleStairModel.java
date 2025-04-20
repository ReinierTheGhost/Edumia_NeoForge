package com.legends.edumia.datagen.helpers.models;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StairBlock;

import java.util.ArrayList;
import java.util.List;

public class SimpleStairModel {
    public record Stair(Block block, StairBlock stairs) {}
    public static List<Stair> blocks = new ArrayList<>() {
        {

        }
    };
}
