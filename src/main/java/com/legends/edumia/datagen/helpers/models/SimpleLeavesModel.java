package com.legends.edumia.datagen.helpers.models;


import com.legends.edumia.blocks.blocksets.ModNatureBlocks;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class SimpleLeavesModel {
    public static List<Block> blocks = new ArrayList<>() {
        {
            add(ModNatureBlocks.APPLE_LEAVES_RED.get());
            add(ModNatureBlocks.APPLE_LEAVES_GREEN.get());
            add(ModNatureBlocks.PEAR_LEAVES_FRUIT.get());
            add(ModNatureBlocks.CHERRY_LEAVES_FRUIT.get());
        }
    };
}
