package com.legends.edumia.datagen.helpers.models;

import com.legends.edumia.blocks.blocksets.ModNatureBlocks;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class SimpleReedsModel {
    public record Reed(Block reed) {}

    public static List<Reed> blocks = new ArrayList<>() {
        {
            add(new Reed(ModNatureBlocks.REEDS.get()));
            add(new Reed(ModNatureBlocks.PAPYRUS.get()));
            add(new Reed(ModNatureBlocks.DRIED_REEDS.get()));
        }
    };
}
