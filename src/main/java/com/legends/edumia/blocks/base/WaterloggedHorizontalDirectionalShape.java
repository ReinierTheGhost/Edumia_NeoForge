package com.legends.edumia.blocks.base;


import com.legends.edumia.blocks.properties.ModWaterloggable;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public abstract class WaterloggedHorizontalDirectionalShape extends Shape implements ModWaterloggable {
    public static final DirectionProperty DIRECTION;

    public WaterloggedHorizontalDirectionalShape(Properties builder) {
        super(builder);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(DIRECTION, rot.rotate(state.getValue(DIRECTION)));
    }
    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.setValue(DIRECTION, mirrorIn.mirror(state.getValue(DIRECTION)));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction facing = context.getHorizontalDirection().getOpposite();
        FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
        return (BlockState)((BlockState)super.getStateForPlacement(context).setValue(DIRECTION, facing))
                .setValue(WATERLOGGED, fluid.getType() == Fluids.WATER);
    }

    public FluidState getFluidState(BlockState state) {
        return (Boolean)state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{DIRECTION, WATERLOGGED});
        this.addProperties(builder);
    }

    protected void addProperties(StateDefinition.Builder<Block, BlockState> builder) {
    }

    static {
        DIRECTION = BlockStateProperties.HORIZONTAL_FACING;
    }
}
