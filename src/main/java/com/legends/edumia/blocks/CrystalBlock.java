package com.legends.edumia.blocks;

import com.legends.edumia.utils.EdumiaUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import javax.swing.text.html.BlockView;
import java.util.Collection;
import java.util.function.ToIntFunction;

public class CrystalBlock extends Block implements SimpleWaterloggedBlock, BeaconBeamBlock {
    public static final EnumProperty<Direction> CRYSTAL_FACING = BlockStateProperties.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final VoxelShape SHAPE_UP = Block.box(2.0, 0.0, 2.0, 14.0, 15.0, 14.0);
    private static final VoxelShape SHAPE_DOWN= Block.box(2.0, 1.0, 2.0, 14.0, 16.0, 14.0);;
    private static final VoxelShape SHAPE_WEST= Block.box(1.0, 2.0, 2.0, 16.0, 14.0, 14.0);;
    private static final VoxelShape SHAPE_EAST = Block.box(0.0, 2.0, 2.0, 15.0, 14.0, 14.0);;
    private static final VoxelShape SHAPE_NORTH= Block.box(2.0, 2.0, 1.0, 14.0, 14.0, 16.0);;
    private static final VoxelShape SHAPE_SOUTH= Block.box(2.0, 2.0, 0.0, 14.0, 14.0, 15.0);;
    private final DyeColor beaconBeamColor;

    public CrystalBlock(Properties settings, DyeColor color) {
        super(settings);
        this.registerDefaultState((this.defaultBlockState().setValue(CRYSTAL_FACING, Direction.UP).setValue(WATERLOGGED, false)));
        this.beaconBeamColor = color;
    }

    public CrystalBlock(int light, int harvestLvl, DyeColor color){
        this(Properties.of().requiresCorrectToolForDrops().strength(3.0f, 3.0f).noOcclusion().lightLevel(constantLight(light)).sound(SoundType.GLASS), color);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{CRYSTAL_FACING, WATERLOGGED});
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        Direction crystalFacing = state.getValue(CRYSTAL_FACING);
        switch (crystalFacing){
            case UP:
            default:
                return SHAPE_UP;
            case DOWN:
                return SHAPE_DOWN;
            case WEST:
                return SHAPE_WEST;
            case EAST:
                return SHAPE_EAST;
            case NORTH:
                return SHAPE_NORTH;
            case SOUTH:
                return SHAPE_SOUTH;
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
        return (this.defaultBlockState().setValue(CRYSTAL_FACING, context.getClickedFace())).setValue(WATERLOGGED, fluid.getType() == Fluids.WATER);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)){
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }

        Direction crystalFacing = state.getValue(CRYSTAL_FACING);
        return direction == crystalFacing.getOpposite() && !this.canSurvive(state, world, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        Direction crystalFacing = state.getValue(CRYSTAL_FACING);
        BlockPos supportPos = pos.relative(crystalFacing.getOpposite());
        return EdumiaUtil.hasSolidSide(world, supportPos, crystalFacing);
    }


    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(CRYSTAL_FACING, rotation.rotate(state.getValue(CRYSTAL_FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(CRYSTAL_FACING)));
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public DyeColor getColor() {
        return this.beaconBeamColor;
    }

    public static ToIntFunction<BlockState> constantLight(int light){
        return (state) ->
                light;
    }
}
