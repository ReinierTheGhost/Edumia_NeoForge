package com.legends.edumia.datagen.helpers.models;

import com.legends.edumia.blocks.ArchBlock;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class SimpleSegmentalArchModel {
    public record Arch(Block texture, ArchBlock arch) {}

    public static List<Arch> blocks = new ArrayList<>() {
        {
            //add(new Arch(BlockLoader.GREEN_BASALT, BlockLoader.GREEN_BASALT_SEGMENTAL_ARCH));
        }
    };
}
