package com.legends.edumia.datagen.custom.models;

import com.legends.edumia.core.BlockLoader;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class SimpleSaplingModel {
    public static List<Block> blocks = new ArrayList<>() {
        {
            add(BlockLoader.TEST_SAPLING.get());
        }
    };
}
