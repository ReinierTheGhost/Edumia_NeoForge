package com.legends.edumia.datagen.custom.models;

import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class SimpleButtonModel {
    public record Button(Block block, Block button) {}
    public static List<Button> blocks = new ArrayList<>() {
        {
        }
    };
}
