package com.legends.edumia.blocks;


import com.legends.edumia.blocks.base.WaterloggedHorizontalDirectionalShape;
import com.legends.edumia.blocks.properties.ModWaterloggable;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ArrowSlit  extends WaterloggedHorizontalDirectionalShape implements ModWaterloggable {
    private static final VoxelShape EAST_FR = Block.box(0.0, 0.0, 9.0,
            1.0, 16.0, 13.0);
    private static final VoxelShape EAST_FL = Block.box(0.0, 0.0, 3.0,
            1.0, 16.0, 7.0);
    private static final VoxelShape EAST_SR = Block.box(0.0, 0.0, 13.0,
            8.0, 16.0, 16.0);
    private static final VoxelShape EAST_SL = Block.box(0.0, 0.0, 0.0,
            8.0, 16.0, 3.0);
    private static final VoxelShape EAST_SHAPE;
    private static final VoxelShape WEST_FR;
    private static final VoxelShape WEST_FL;
    private static final VoxelShape WEST_SR;
    private static final VoxelShape WEST_SL;
    private static final VoxelShape WEST_SHAPE;
    private static final VoxelShape NORTH_FR;
    private static final VoxelShape NORTH_FL;
    private static final VoxelShape NORTH_SR;
    private static final VoxelShape NORTH_SL;
    private static final VoxelShape NORTH_SHAPE;
    private static final VoxelShape SOUTH_FR;
    private static final VoxelShape SOUTH_FL;
    private static final VoxelShape SOUTH_SR;
    private static final VoxelShape SOUTH_SL;
    private static final VoxelShape SOUTH_SHAPE;

    public ArrowSlit(Properties properties) {
        super(properties);
        this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any())
                .setValue(DIRECTION, Direction.NORTH)).setValue(WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{DIRECTION, WATERLOGGED});
    }

    @Override
    public VoxelShape getShape(BlockState state) {
        switch ((Direction)state.getValue(DIRECTION)) {
            case NORTH:
            default:
                return NORTH_SHAPE;
            case SOUTH:
                return SOUTH_SHAPE;
            case WEST:
                return WEST_SHAPE;
            case EAST:
                return EAST_SHAPE;
        }
    }

    static {
        EAST_SHAPE = Shapes.or(Shapes.or(EAST_FR, EAST_FL), Shapes.or(EAST_SR, EAST_SL));
        WEST_FR = Block.box(15.0, 0.0, 9.0, 16.0, 16.0, 13.0);
        WEST_FL = Block.box(15.0, 0.0, 3.0, 16.0, 16.0, 7.0);
        WEST_SR = Block.box(8.0, 0.0, 13.0, 16.0, 16.0, 16.0);
        WEST_SL = Block.box(8.0, 0.0, 0.0, 16.0, 16.0, 3.0);
        WEST_SHAPE = Shapes.or(Shapes.or(WEST_FR, WEST_FL), Shapes.or(WEST_SR, WEST_SL));
        NORTH_FR = Block.box(9.0, 0.0, 15.0, 13.0, 16.0, 16.0);
        NORTH_FL = Block.box(3.0, 0.0, 15.0, 7.0, 16.0, 16.0);
        NORTH_SR = Block.box(13.0, 0.0, 8.0, 16.0, 16.0, 16.0);
        NORTH_SL = Block.box(0.0, 0.0, 8.0, 3.0, 16.0, 16.0);
        NORTH_SHAPE = Shapes.or(Shapes.or(NORTH_FR, NORTH_FL), Shapes.or(NORTH_SR, NORTH_SL));
        SOUTH_FR = Block.box(9.0, 0.0, 0.0, 13.0, 16.0, 1.0);
        SOUTH_FL = Block.box(3.0, 0.0, 0.0, 7.0, 16.0, 1.0);
        SOUTH_SR = Block.box(13.0, 0.0, 0.0, 16.0, 16.0, 8.0);
        SOUTH_SL = Block.box(0.0, 0.0, 0.0, 3.0, 16.0, 8.0);
        SOUTH_SHAPE = Shapes.or(Shapes.or(SOUTH_FR, SOUTH_FL), Shapes.or(SOUTH_SR, SOUTH_SL));
    }
}
