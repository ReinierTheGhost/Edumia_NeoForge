package com.legends.edumia.datagen.helpers.models;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;

import java.util.ArrayList;
import java.util.List;

public class SimpleSlabModel {
    public record Slab(Block block, SlabBlock slab) {}
    public static List<Slab> blocks = new ArrayList<>() {
        {

        }
    };
}
