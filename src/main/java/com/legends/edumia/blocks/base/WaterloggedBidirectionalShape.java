package com.legends.edumia.blocks.base;


import com.legends.edumia.blocks.properties.BidirectionalShape;
import com.legends.edumia.blocks.properties.ModWaterloggable;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unchecked")
public abstract class WaterloggedBidirectionalShape extends Shape implements ModWaterloggable {
    public static final EnumProperty DIRECTION = EnumProperty.create("direction", BidirectionalShape.class);

    public WaterloggedBidirectionalShape(Properties builder) {
        super(builder);
    }

    @Override
    public @NotNull BlockState rotate(BlockState state, Rotation rot) {
        return state.getValue(DIRECTION) == BidirectionalShape.NORTH_SOUTH ? state.setValue(DIRECTION, BidirectionalShape.EAST_WEST) :
                state.setValue(DIRECTION, BidirectionalShape.NORTH_SOUTH);
    }

    @Override
    public @NotNull BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state;
    }

    @NotNull
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BidirectionalShape facing = BidirectionalShape.EAST_WEST;
        if (context.getHorizontalDirection() == Direction.NORTH || context.getHorizontalDirection() == Direction.SOUTH) {
            facing = BidirectionalShape.NORTH_SOUTH;
        }

        FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
        return (super.getStateForPlacement(context).setValue(DIRECTION, facing)).setValue(WATERLOGGED, fluid.getType() ==
                Fluids.WATER);
    }

    public @NotNull FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected final void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{DIRECTION, WATERLOGGED});
        this.addProperties(builder);
    }

    protected void addProperties(StateDefinition.Builder<Block, BlockState> builder) {
    }
}
