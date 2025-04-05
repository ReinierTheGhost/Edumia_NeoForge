package com.legends.edumia.datagen.custom.models;

import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class SimpleFallenLeavesBlockModel {

    public record FallenLeaves(Block texture, Block leaves){}

    public static List<FallenLeaves> blocks = new ArrayList<>(){
        {

        }
    };
}
