package com.legends.edumia.world.biomes;


import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.function.Supplier;

public class BlocksLayeringData {
    public ArrayList<LayerData> layers;

    public BlocksLayeringData(BlocksLayeringData blocksLayeringData) {
        this.layers = new ArrayList<>(blocksLayeringData.layers);
    }

    public BlocksLayeringData() {
        this.layers = new ArrayList<>();
    }

    public BlocksLayeringData addLayerData(float percentage, Supplier<Block> block) {
        return addLayerData(percentage, block.get());
    }
    public BlocksLayeringData addLayerData(float percentage, Block block) {
        if (percentage <= 0.0f || percentage > 1.0f) {
            throw new ArithmeticException("Cannot add a layer with a percentage that isn't between 0.0f and 1.0f");
        }
        layers.add(new LayerData(percentage, block));
        return this;
    }

    public class LayerData {
        public float percentage;
        public Block block;

        public LayerData(float percentage, Block block) {
            this.percentage = percentage;
            this.block = block;
        }
    }
}
