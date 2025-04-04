package com.legends.edumia.blocks;


import com.legends.edumia.core.TagLoader;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;

public class EdumiaPillarBlock extends RotatedPillarBlock {

    public static final BooleanProperty ABOVE = EdumiaBlockStates.PILLAR_ABOVE;
    public static final BooleanProperty BELOW = EdumiaBlockStates.PILLAR_BELOW;
    public EdumiaPillarBlock(BlockBehaviour.Properties properties) {
        super(properties);
        //this.registerDefaultState(this.defaultBlockState().setValue(ABOVE, false).setValue(BELOW, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(new Property[]{ABOVE});
        builder.add(new Property[]{BELOW});
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = super.getStateForPlacement(context);
        BlockPos pos = context.getClickedPos();
        LevelAccessor world = context.getLevel();
        Direction[] var5 = Direction.values();
        int var6 = var5.length;

        for (int var7 = 0; var7 < var6; ++var7){
            Direction dir = var5[var7];
            BlockPos facingPos = pos.relative(dir);
            state = this.checkAdjacentPillars(state, dir, world.getBlockState(facingPos), world, pos, facingPos);
        }

        return  state;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction dir, BlockState facingState, LevelAccessor level,
                                  BlockPos currentPos, BlockPos facingPos) {
        return this.checkAdjacentPillars(state, dir, facingState, level, currentPos, facingPos);
    }

    private BlockState checkAdjacentPillars(BlockState state, Direction dir, BlockState facingState, LevelAccessor world,
                                            BlockPos pos, BlockPos facingPos) {
        Direction.Axis pillarAxis = state.getValue(AXIS);
        if (dir.getAxis() == pillarAxis){
            Direction.AxisDirection axisDir = dir.getAxisDirection();
            boolean matchDir = false;
            if (facingState.is(TagLoader.Blocks.PILLARS)){
                if (facingState.hasProperty(AXIS)){
                    matchDir = facingState.getValue(AXIS) == pillarAxis;
                } else{
                    matchDir = true;
                }
            }
            if (axisDir == Direction.AxisDirection.POSITIVE){
                return state.setValue(ABOVE, matchDir);
            }

            if (axisDir == Direction.AxisDirection.NEGATIVE) {
                return state.setValue(BELOW, matchDir);
            }
        }
        return state;
    }
}
