package com.legends.edumia.datagen.custom.models;

import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class SimpleReedsModel {
    public record Reed(Block reed) {}

    public static List<Reed> blocks = new ArrayList<>() {
        {
//            add(new Reed(BlockLoader.REEDS));
//            add(new Reed(BlockLoader.PAPYRUS));
//            add(new Reed(BlockLoader.DRIED_REEDS));
        }
    };
}
