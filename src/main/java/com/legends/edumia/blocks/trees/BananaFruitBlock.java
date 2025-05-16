package com.legends.edumia.blocks.trees;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.common.util.TriState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BananaFruitBlock  extends HorizontalDirectionalBlock implements BonemealableBlock {
    public static final MapCodec<BananaFruitBlock> CODEC = simpleCodec(BananaFruitBlock::new);
    public static final int MAX_AGE = 2;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_2;
    protected static final int AGE_0_WIDTH = 2;
    protected static final int AGE_0_HEIGHT = 4;
    protected static final int AGE_0_HALFWIDTH = 1;
    protected static final int AGE_1_WIDTH = 3;
    protected static final int AGE_1_HEIGHT = 8;
    protected static final int AGE_1_HALFWIDTH = 1;
    protected static final int AGE_2_WIDTH = 4;
    protected static final int AGE_2_HEIGHT = 12;
    protected static final int AGE_2_HALFWIDTH = 2;


    protected static final VoxelShape[] EAST_AABB = new VoxelShape[]{
            Block.box(7.0, 11.0, 7.0, 9.0, 15.0, 9.0),       // Age 0 (2x4x2)
            Block.box(6.5, 7.0, 6.5, 9.5, 15.0, 9.5),        // Age 1 (3x8x3)
            Block.box(6.0, 3.0, 6.0, 10.0, 15.0, 10.0)       // Age 2 (4x12x4)
    };

    protected static final VoxelShape[] WEST_AABB = new VoxelShape[]{
            Block.box(7.0, 11.0, 7.0, 9.0, 15.0, 9.0),
            Block.box(6.5, 7.0, 6.5, 9.5, 15.0, 9.5),
            Block.box(6.0, 3.0, 6.0, 10.0, 15.0, 10.0)
    };

    protected static final VoxelShape[] NORTH_AABB = new VoxelShape[]{
            Block.box(7.0, 11.0, 7.0, 9.0, 15.0, 9.0),
            Block.box(6.5, 7.0, 6.5, 9.5, 15.0, 9.5),
            Block.box(6.0, 3.0, 6.0, 10.0, 15.0, 10.0)
    };

    protected static final VoxelShape[] SOUTH_AABB = new VoxelShape[]{
            Block.box(7.0, 11.0, 7.0, 9.0, 15.0, 9.0),
            Block.box(6.5, 7.0, 6.5, 9.5, 15.0, 9.5),
            Block.box(6.0, 3.0, 6.0, 10.0, 15.0, 10.0)
    };

    protected BananaFruitBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(AGE, Integer.valueOf(0)));
    }

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return CODEC;
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < 2;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (true) {
            int age = state.getValue(AGE);
            if (age < 2 && CommonHooks.canCropGrow(level, pos, state, level.random.nextInt(5) == 0)) {
                level.setBlock(pos, state.setValue(AGE, Integer.valueOf(age + 1)), 2);
                CommonHooks.fireCropGrowPost(level, pos, state);
            }
        }
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockState blockstate = level.getBlockState(pos.relative(state.getValue(FACING)));
        TriState soilDecision = blockstate.canSustainPlant(level, pos.relative(state.getValue(FACING)), state.getValue(FACING).getOpposite(), state);
        if (!soilDecision.isDefault()) return soilDecision.isTrue();
        return blockstate.is(BlockTags.JUNGLE_LOGS);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        int age = state.getValue(AGE);
        switch (state.getValue(FACING)) {
            case SOUTH:
                return SOUTH_AABB[age];
            case NORTH:
            default:
                return NORTH_AABB[age];
            case WEST:
                return WEST_AABB[age];
            case EAST:
                return EAST_AABB[age];
        }

    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockstate = this.defaultBlockState();
        LevelReader levelreader = context.getLevel();
        BlockPos blockpos = context.getClickedPos();

        for (Direction direction : context.getNearestLookingDirections()) {
            if (direction.getAxis().isHorizontal()) {
                blockstate = blockstate.setValue(FACING, direction);
                if (blockstate.canSurvive(levelreader, blockpos)) {
                    return blockstate;
                }
            }
        }

        return null;
    }

    @Override
    protected @NotNull BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        return direction == state.getValue(FACING) && !state.canSurvive(level, pos)
                ? Blocks.AIR.defaultBlockState()
                : super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return state.getValue(AGE) < 2;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        level.setBlock(pos, state.setValue(AGE, Integer.valueOf(state.getValue(AGE) + 1)), 2);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return false;
    }
}
