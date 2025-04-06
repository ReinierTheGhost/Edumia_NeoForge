package com.legends.edumia.datagen.custom.models;

import com.legends.edumia.blocks.ArchBlock;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class SimpleRoundArchModel {
    public record Arch(Block texture, ArchBlock arch) {}

    public static List<Arch> blocks = new ArrayList<>() {
        {
            //add(new Arch(BlockLoader.GREEN_BASALT, BlockLoader.GREEN_BASALT_ROUND_ARCH));
        }
    };
}
