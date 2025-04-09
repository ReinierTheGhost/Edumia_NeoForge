package com.legends.edumia.datagen.custom.loot_tables;


import com.legends.edumia.blocks.blocksets.ModNatureBlocks;
import com.legends.edumia.core.BlockLoader;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class BlockDrops {
    public static List<Block> blocks = new ArrayList<>() {
        {
//            add(BlockLoader.WHITE_SAND.get());
            add(ModNatureBlocks.APPLE_LEAVES_RED.get());
            add(ModNatureBlocks.APPLE_LEAVES_GREEN.get());
            add(ModNatureBlocks.PEAR_LEAVES_FRUIT.get());
            add(ModNatureBlocks.CHERRY_LEAVES_FRUIT.get());
            add(ModNatureBlocks.BLACK_OAK_LEAVES.get());
            add(ModNatureBlocks.GHOST_GUM_LEAVES.get());
            add(ModNatureBlocks.APPLE_LEAVES_RED.get());
            add(ModNatureBlocks.HOLLY_LEAVES.get());
            add(ModNatureBlocks.MAPLE_LEAVES.get());
        }
    };
}
