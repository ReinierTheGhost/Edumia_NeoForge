package com.legends.edumia.datagen.helpers.models;

import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class SimpleCornerBlockModel {

    public record Corner(Block texture, Block corner) {}

    public static List<Corner> blocks = new ArrayList<>() {
        {

        }
    };
}
