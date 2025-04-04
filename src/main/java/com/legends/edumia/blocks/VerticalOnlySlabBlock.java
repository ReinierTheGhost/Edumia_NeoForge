package com.legends.edumia.blocks;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;

public class VerticalOnlySlabBlock extends  AxialSlabBlock{
    private static final BiMap<SlabBlock, VerticalOnlySlabBlock> VANILLA_SLAB_TO_VERTICAL_SLABS = HashBiMap.create();

    public VerticalOnlySlabBlock(Block block) {
        super(block);
    }

    public static VerticalOnlySlabBlock getVerticalSlabFor(SlabBlock vanillaSlab) {
        return VANILLA_SLAB_TO_VERTICAL_SLABS.get(vanillaSlab);
    }
}
