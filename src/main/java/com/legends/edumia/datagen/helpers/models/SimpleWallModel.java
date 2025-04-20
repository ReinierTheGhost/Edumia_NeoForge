package com.legends.edumia.datagen.helpers.models;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallBlock;

import java.util.ArrayList;
import java.util.List;

public class SimpleWallModel {
    public record Wall(Block block, WallBlock wall) {}
    public static List<Wall> blocks = new ArrayList<>() {
        {

        }
    };
}
