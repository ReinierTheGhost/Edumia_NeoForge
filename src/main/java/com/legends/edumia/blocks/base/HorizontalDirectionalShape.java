package com.legends.edumia.blocks.base;


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
import org.jetbrains.annotations.NotNull;

public abstract class HorizontalDirectionalShape extends Shape {
    public static final DirectionProperty DIRECTION;

    public HorizontalDirectionalShape(Properties builder) {
        super(builder);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(DIRECTION, rot.rotate(state.getValue(DIRECTION)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(DIRECTION)));
    }

    @Override
    @NotNull
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction facing = context.getHorizontalDirection().getOpposite();
        return (BlockState)super.getStateForPlacement(context).setValue(DIRECTION, facing);
    }

    @Override
    protected final void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{DIRECTION});
        this.addProperties(builder);
    }

    protected void addProperties(StateDefinition.Builder<Block, BlockState> builder) {
    }

    static {
        DIRECTION = BlockStateProperties.HORIZONTAL_FACING;
    }
}
