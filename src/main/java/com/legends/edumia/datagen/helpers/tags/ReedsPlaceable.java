package com.legends.edumia.datagen.helpers.tags;

import com.legends.edumia.core.blocksets.SandBlockSets;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.List;

public class ReedsPlaceable {
    public static List<Block> blocks = new ArrayList<>() {
        {
            add(Blocks.MUD);
            add(Blocks.SAND);
            add(Blocks.RED_SAND);
            add(Blocks.CLAY);
            add(Blocks.DIRT);
            add(Blocks.GRASS_BLOCK);
        }
    };
}
