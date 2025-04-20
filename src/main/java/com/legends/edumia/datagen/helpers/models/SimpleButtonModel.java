package com.legends.edumia.datagen.helpers.models;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;

import java.util.ArrayList;
import java.util.List;

public class SimpleButtonModel {
    public record Button(Block block, ButtonBlock button) {}
    public static List<Button> blocks = new ArrayList<>() {
        {
        }
    };
}
