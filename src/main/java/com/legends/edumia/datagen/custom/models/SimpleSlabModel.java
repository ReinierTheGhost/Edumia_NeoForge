package com.legends.edumia.datagen.custom.models;

import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class SimpleSlabModel {
    public record Slab(Block block, Block slab) {}
    public static List<Slab> blocks = new ArrayList<>() {
        {

        }
    };
}
