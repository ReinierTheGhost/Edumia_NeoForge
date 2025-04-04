package com.legends.edumia.blocks;


import com.legends.edumia.blocks.properties.ModWaterloggable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.swing.text.html.BlockView;

public class Balustrade extends RotatedPillarBlock implements ModWaterloggable {
    public static final VoxelShape field_196436_c = Block.box(2.0, 0.0, 2.0, 14.0, 4.0, 14.0);
    public static final VoxelShape field_196439_y = Block.box(3.0, 4.0, 4.0, 13.0, 5.0, 12.0);
    public static final VoxelShape field_196440_z = Block.box(4.0, 5.0, 6.0, 12.0, 10.0, 10.0);
    public static final VoxelShape field_196434_A = Block.box(0.0, 10.0, 3.0, 16.0, 16.0, 13.0);
    public static final VoxelShape field_196435_B = Block.box(4.0, 4.0, 3.0, 12.0, 5.0, 13.0);
    public static final VoxelShape field_196437_C = Block.box(6.0, 5.0, 4.0, 10.0, 10.0, 12.0);
    public static final VoxelShape field_196438_D = Block.box(3.0, 10.0, 0.0, 13.0, 16.0, 16.0);
    public static final VoxelShape X_AXIS_AABB;
    public static final VoxelShape Z_AXIS_AABB;
    public static final VoxelShape Y_BASE;
    public static final VoxelShape Y_LOWER;
    public static final VoxelShape Y_MIDDLE;
    public static final VoxelShape Y_TOP;
    public static final VoxelShape Y_AXIS_AABB;

    public Balustrade(Properties properties) {
        super(properties);
        this.registerDefaultState(((this.stateDefinition.any()).setValue(AXIS,
                Direction.Axis.Y)).setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape (BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        switch (state.getValue(AXIS)) {
            case X:
            default:
                return X_AXIS_AABB;
            case Y:
                return Y_AXIS_AABB;
            case Z:
                return Z_AXIS_AABB;
        }
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return (Boolean)state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{AXIS, WATERLOGGED});
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return (super.getStateForPlacement(context).setValue(AXIS, context.getClickedFace().getAxis()))
                .setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }

    static {
        X_AXIS_AABB = Shapes.or(field_196436_c, Shapes.or(field_196439_y, Shapes.or(field_196440_z, field_196434_A)));
        Z_AXIS_AABB = Shapes.or(field_196436_c, Shapes.or(field_196435_B, Shapes.or(field_196437_C, field_196438_D)));
        Y_BASE = Block.box(2.0, 0.0, 2.0, 14.0, 4.0, 14.0);
        Y_LOWER = Block.box(3.0, 4.0, 3.0, 13.0, 5.0, 13.0);
        Y_MIDDLE = Block.box(4.0, 5.0, 4.0, 12.0, 11.0, 12.0);
        Y_TOP = Block.box(2.0, 11.0, 2.0, 14.0, 16.0, 14.0);
        Y_AXIS_AABB = Shapes.or(Y_BASE, Shapes.or(Y_LOWER, Shapes.or(Y_MIDDLE, Y_TOP)));
    }

}
