package com.legends.edumia.datagen.custom.models;

import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class SimpleGlassModel {

    public record Glass(Block block, Block pane) {}

    public static List<Glass> blocks = new ArrayList<>() {
        {

        }
    };
}
