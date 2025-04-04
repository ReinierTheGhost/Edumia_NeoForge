package com.legends.edumia.blocks;


import com.legends.edumia.blocks.base.WaterloggedShape;
import com.legends.edumia.blocks.util.PlacementHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.swing.text.html.BlockView;

public class Layer extends WaterloggedShape {
    public static final IntegerProperty LAYERS = BlockStateProperties.LAYERS;
    private static final VoxelShape[] BOTTOM_SHAPE;
    public static final VoxelShape SPECIAL_FULL_SHAPE_COLLISION;

    public Layer(Properties props) {
        super(props);
        this.registerDefaultState(((this.getStateDefinition().any()).setValue(LAYERS, 1)).setValue(WATERLOGGED, false));
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    @Override
    public VoxelShape getShape(BlockState state) {
        return BOTTOM_SHAPE[state.getValue(LAYERS) - 1];
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return state.getValue(LAYERS) == 8 ? SPECIAL_FULL_SHAPE_COLLISION : BOTTOM_SHAPE[state.getValue(LAYERS) - 1];
    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        if (!PlacementHelper.replacingClickedOnBlock(context)) {
            return false;
        } else {
            ItemStack item = context.getItemInHand();
            if (item.getItem() == this.asItem() && (Integer)state.getValue(LAYERS) != 8) {
                return context.getClickedFace() == Direction.UP;
            } else {
                return false;
            }
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = context.getLevel().getBlockState(context.getClickedPos());
        if (state.getBlock() == this) {
            int i = state.getValue(LAYERS);
            return state.setValue(LAYERS, Math.min(8, i + 1));
        } else {
            FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
            BlockState state2 = this.defaultBlockState().setValue(WATERLOGGED, fluid.getType() == Fluids.WATER);
            return state2;
        }
    }

    @Override
    protected void addProperties(StateDefinition.Builder<Block, BlockState> container) {
        container.add(new Property[]{LAYERS});
    }

    static {
        BOTTOM_SHAPE = new VoxelShape[]{
                Block.box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
                Block.box(0.0, 0.0, 0.0, 16.0, 4.0, 16.0),
                Block.box(0.0, 0.0, 0.0, 16.0, 6.0, 16.0),
                Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
                Block.box(0.0, 0.0, 0.0, 16.0, 10.0, 16.0),
                Block.box(0.0, 0.0, 0.0, 16.0, 12.0, 16.0),
                Block.box(0.0, 0.0, 0.0, 16.0, 14.0, 16.0),
                Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)};
        SPECIAL_FULL_SHAPE_COLLISION = Block.box(0.0, 2.0, 0.0, 16.0, 16.0, 16.0);
    }
}