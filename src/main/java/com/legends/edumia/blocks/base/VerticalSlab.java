package com.legends.edumia.blocks.base;


import com.legends.edumia.blocks.util.PlacementHelper;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.VoxelShape;

public class VerticalSlab extends WaterloggedHorizontalDirectionalShape {

    private BlockState parent = null;
    public static final IntegerProperty LAYERS = IntegerProperty.create("layer", 1, 4);
    public static final VoxelShape[] EAST_SHAPE = new VoxelShape[]{
            Block.box(0.0, 0.0, 0.0, 2.0, 16.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 4.0, 16.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 8.0, 16.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 12.0, 16.0, 16.0)};
    public static final VoxelShape[] WEST_SHAPE = new VoxelShape[]{
            Block.box(14.0, 0.0, 0.0, 16.0, 16.0, 16.0),
            Block.box(12.0, 0.0, 0.0, 16.0, 16.0, 16.0),
            Block.box(8.0, 0.0, 0.0, 16.0, 16.0, 16.0),
            Block.box(4.0, 0.0, 0.0, 16.0, 16.0, 16.0)};
    public static final VoxelShape[] SOUTH_SHAPE = new VoxelShape[]{
            Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 2.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 4.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 8.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 12.0)};
    public static final VoxelShape[] NORTH_SHAPE = new VoxelShape[]{
            Block.box(0.0, 0.0, 14.0, 16.0, 16.0, 16.0),
            Block.box(0.0, 0.0, 12.0, 16.0, 16.0, 16.0),
            Block.box(0.0, 0.0, 8.0, 16.0, 16.0, 16.0),
            Block.box(0.0, 0.0, 4.0, 16.0, 16.0, 16.0)};
    private Block fullBlock;

    public VerticalSlab(Properties props) {
        super(props);
        this.registerDefaultState(((this.stateDefinition.any()).setValue(DIRECTION, Direction.NORTH)).setValue(WATERLOGGED, false));
        this.fullBlock = getParent().getBlock();
    }

    @Override
    public VoxelShape getShape(BlockState state) {
        switch (state.getValue(DIRECTION)) {
            case NORTH:
            default:
                return NORTH_SHAPE[state.getValue(LAYERS) - 1];
            case SOUTH:
                return SOUTH_SHAPE[state.getValue(LAYERS) - 1];
            case WEST:
                return WEST_SHAPE[state.getValue(LAYERS) - 1];
            case EAST:
                return EAST_SHAPE[state.getValue(LAYERS) - 1];
        }
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        int i = (Integer)state.getValue(LAYERS);
        if (context.getItemInHand().getItem() == this.asItem() && i <= 4) {
            if (PlacementHelper.replacingClickedOnBlock(context)) {
                return context.getClickedFace() == state.getValue(DIRECTION);
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
        if (blockstate.getBlock() == this) {
            int i = (Integer)blockstate.getValue(LAYERS);
            return i == 4 ? this.fullBlock.defaultBlockState() : (BlockState)blockstate.setValue(LAYERS, Math.min(4, i + 1));
        } else {
            return super.getStateForPlacement(context);
        }
    }

    protected void addProperties(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{LAYERS});
    }

    public BlockState getParent() throws InitializationException {
        if (this.parent == null) {
            throw new InitializationException("Parent state is null");
        } else {
            return this.parent;
        }
    }
}
