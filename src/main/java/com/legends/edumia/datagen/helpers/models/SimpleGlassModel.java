package com.legends.edumia.datagen.helpers.models;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.IronBarsBlock;

import java.util.ArrayList;
import java.util.List;

public class SimpleGlassModel {

    public record Glass(Block block, IronBarsBlock pane) {}

    public static List<Glass> blocks = new ArrayList<>() {
        {

        }
    };
}
