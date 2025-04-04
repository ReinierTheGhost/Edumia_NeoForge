package com.legends.edumia.blocks.trees;


import com.legends.edumia.blocks.EdumiaLeavesBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;

import javax.swing.text.html.BlockView;

public class BlackOakLeavesBlock extends EdumiaLeavesBlock {
    public static final BooleanProperty DOWN = BlockStateProperties.DOWN;


    public BlackOakLeavesBlock() {
        super();
        this.registerDefaultState(this.defaultBlockState().setValue(DOWN, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(new Property[]{DOWN});
    }

    private boolean hasDownState(LevelAccessor world, BlockPos pos){
        BlockPos belowPos = pos.below();
        BlockState belowState = world.getBlockState(belowPos);
        return Block.isFaceFull(belowState.getBlockSupportShape(world, belowPos), Direction.UP);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return super.getStateForPlacement(ctx).setValue(DOWN, this.hasDownState(ctx.getLevel(), ctx.getClickedPos()));
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world,
                                                BlockPos currentPos, BlockPos facingPos) {
        state = super.updateShape(state, facing, facingState, world, currentPos, facingPos);
        if (facing == Direction.DOWN){
            state = state.setValue(DOWN, this.hasDownState(world, currentPos));
        }

        return state;
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter world, BlockPos pos) {
        return state.getValue(DOWN) ? 15 : super.getLightBlock(state, world, pos);
    }
}
