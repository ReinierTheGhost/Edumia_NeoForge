package com.legends.edumia.blocks.base;


import com.legends.edumia.blocks.properties.ModWaterloggable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public abstract class WaterloggedShape extends Shape implements ModWaterloggable {
    public WaterloggedShape(Properties properties) {
        super(properties);
    }
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
        BlockState base = super.getStateForPlacement(context);
        if (base == null) {
            base = this.defaultBlockState();
        }

        return base.setValue(WATERLOGGED, fluid.getType() == Fluids.WATER);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected final void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{WATERLOGGED});
        this.addProperties(builder);
    }

    protected void addProperties(StateDefinition.Builder<Block, BlockState> builder) {
    }

}
