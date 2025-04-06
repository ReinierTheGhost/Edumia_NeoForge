package com.legends.edumia.datagen.custom.models;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;

import java.util.ArrayList;
import java.util.List;

public class SimpleWoodBlockModel {
    public record WoodBlocks(Block texture, RotatedPillarBlock wood){}

    public static List<WoodBlocks> blocks = new ArrayList<>(){
        {

        }
    };
}
