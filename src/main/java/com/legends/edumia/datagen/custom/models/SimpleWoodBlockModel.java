package com.legends.edumia.datagen.custom.models;

import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class SimpleWoodBlockModel {
    public record WoodBlocks(Block texture, Block wood){}

    public static List<WoodBlocks> blocks = new ArrayList<>(){
        {

        }
    };
}
