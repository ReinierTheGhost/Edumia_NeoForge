package com.legends.edumia.blocks;


import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class EdumiaSlabBlock extends SlabBlock {


    private final Block modelBlock;

    public EdumiaSlabBlock(Block block) {
        super(Properties.ofFullCopy(block));
        this.modelBlock = block;
    }

    public EdumiaSlabBlock(Supplier<Block> block){
        this(block.get());
    }

    public Block getModelBlock(){
        return this.modelBlock;
    }


    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
        this.modelBlock.animateTick(state, level, pos, rand);
    }
}
