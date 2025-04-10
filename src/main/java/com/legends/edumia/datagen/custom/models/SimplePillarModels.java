package com.legends.edumia.datagen.custom.models;

import com.legends.edumia.core.BlockLoader;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class SimplePillarModels {
    public record Pillar(Block pillar) {}

    public static List<Pillar> blocks = new ArrayList<>() {
        {
            add(new Pillar(BlockLoader.BRICK_PILLAR.get()));
            add(new Pillar(BlockLoader.STONE_PILLAR.get()));
            add(new Pillar(BlockLoader.SANDSTONE_PILLAR.get()));
        }
    };
}
