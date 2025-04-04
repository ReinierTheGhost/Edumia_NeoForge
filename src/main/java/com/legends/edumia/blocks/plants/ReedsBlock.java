package com.legends.edumia.blocks.plants;


import com.legends.edumia.blocks.EdumiaBlockStates;
import com.legends.edumia.core.TagLoader;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import javax.swing.text.html.BlockView;
import java.util.Iterator;

public class ReedsBlock extends Block implements SimpleWaterloggedBlock, BonemealableBlock {

    public static final EnumProperty<Type> REEDS_TYPE = EdumiaBlockStates.REEDS_TYPE;

    public static final IntegerProperty AGE = BlockStateProperties.AGE_15;
    private static final int MAX_AGE = 15;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private static final VoxelShape SHAPE = Block.box(2.0, 0.0, 2.0, 14.0, 16.0, 14.0);
    private final boolean canReedGrow;
    private final boolean canPlaceByIce;

    protected ReedsBlock(Properties properties, boolean canGrow) {
        super(properties);
        this.registerDefaultState(((this.defaultBlockState().setValue(REEDS_TYPE, Type.ONE))
                .setValue(AGE, 0))
                .setValue(WATERLOGGED, false));
        this.canReedGrow = canGrow;
        this.canPlaceByIce = !canGrow;
    }

    protected ReedsBlock(boolean canGrow){
        this(Properties.of().noCollission().randomTicks().strength(0.0F).sound(SoundType.GRASS), canGrow);
    }

    public ReedsBlock(){
        this(true);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{REEDS_TYPE, AGE, WATERLOGGED});
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState placeState = this.defaultBlockState();
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState belowState = level.getBlockState(pos.below());
        if (belowState.getBlock() == this){
            Type reedType = belowState.getValue(REEDS_TYPE);
            if (reedType == Type.ONE){
                placeState = placeState.setValue(REEDS_TYPE, Type.TWO_TOP);
            } else if (reedType == Type.TWO_TOP) {
                placeState = placeState.setValue(REEDS_TYPE, Type.THREE_TOP);
            }
        }

        FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
        return placeState.setValue(WATERLOGGED, fluid.getTags() == Fluids.WATER);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world,
                                  BlockPos currentPos,
                                  BlockPos facingPos) {
        if (!state.canSurvive(world, currentPos)){
            world.scheduleTick(currentPos, this, 1);
        }else if (facing == Direction.UP){
            Type thisReedType = state.getValue(REEDS_TYPE);
            if (facingState.getBlock() == this){
                Type aboveReedType = facingState.getValue(REEDS_TYPE);

                if (thisReedType == Type.ONE && aboveReedType == Type.TWO_TOP){
                    return state.setValue(REEDS_TYPE, Type.TWO_BOTTOM);
                }

                if (thisReedType == Type.TWO_TOP && aboveReedType == Type.THREE_TOP){
                    return state.setValue(REEDS_TYPE, Type.THREE_MIDDLE);
                }

                if (thisReedType == Type.TWO_BOTTOM && aboveReedType == Type.THREE_MIDDLE){
                    return state.setValue(REEDS_TYPE, Type.THREE_BOTTOM);
                }

                if (thisReedType == Type.THREE_BOTTOM && aboveReedType == Type.TWO_TOP){
                    return state.setValue(REEDS_TYPE, Type.TWO_BOTTOM);
                }
            }else {
                if (thisReedType == Type.TWO_BOTTOM || thisReedType == Type.THREE_BOTTOM){
                    return state.setValue(REEDS_TYPE, Type.ONE);
                }

                if (thisReedType == Type.THREE_MIDDLE){
                    return state.setValue(REEDS_TYPE, Type.TWO_TOP);
                }
            }
        }

        if (state.getValue(WATERLOGGED)){
            world.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }

        return super.updateShape(state, facing, facingState, world, currentPos, facingPos);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        Type reedType = state.getValue(REEDS_TYPE);
        BlockPos belowPos = pos.below();
        BlockState belowState = world.getBlockState(belowPos);
        if (reedType != Type.ONE && reedType != Type.TWO_BOTTOM && reedType != Type.THREE_BOTTOM){
            if (reedType == Type.THREE_TOP && state.getValue(WATERLOGGED)){
                return false;
            } else {
                return belowState.getBlock() == this;
            }
        } else {
            if (belowState.is(TagLoader.Blocks.REEDS_PLACEABLE_ON)){
                if (world.getFluidState(pos).getType() == Fluids.WATER){
                    boolean canPotentiallyReachAir = false;
                    if(this.isAirOrReedsInAir(world, pos.above())){
                        canPotentiallyReachAir = true;
                    }else if (this.isWaterOrReedsInWater(world, pos.above()) && this.isAirOrReedsInAir(world, pos.above(2))){
                        canPotentiallyReachAir = true;
                    }

                    return canPotentiallyReachAir;
                }

                Iterator var7 = Direction.Plane.HORIZONTAL.iterator();

                while (var7.hasNext()){
                    Direction horizontalDir = (Direction)var7.next();
                    BlockState adjacentBelowState = world.getBlockState(belowPos.relative(horizontalDir));
                    FluidState fluid = adjacentBelowState.getFluidState();
                    if (fluid.is(FluidTags.WATER) || adjacentBelowState.getBlock() == Blocks.FROSTED_ICE ||
                            this.canPlaceByIce && adjacentBelowState.getProperties() == Blocks.ICE){
                        return true;
                    }
                }
            }

            return false;
        }
    }

    private boolean isAirOrReedsInAir(LevelReader world, BlockPos pos) {
        if (world.isEmptyBlock(pos)){
            return true;
        }else {
            BlockState state = world.getBlockState(pos);
            return state.getBlock() == this && !(Boolean)state.getValue(WATERLOGGED);
        }
    }

    private boolean isWaterOrReedsInWater(LevelReader world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        if (state.getFluidState().getType() == Fluids.WATER){
            return true;
        } else {
            return state.getBlock() == this && state.getValue(WATERLOGGED);
        }
    }


    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource rand) {
        if (!state.canSurvive(world, pos)){
            world.destroyBlock(pos, true);
        } else if (this.canReedGrow && this.canReedGrowUpwards(world, pos, state)) {
            int age = state.getValue(AGE);
//            if (ForgeHooks.onCropsGrowPre(world, pos, state, true)){
//                if (age == 15){
//                    this.growReedAbove(world, pos, state);
//                }else {
//                    world.setBlock(pos, state.with(AGE, age + 1), 4);
//                }
//
//                ForgeHooks.onCropsGrowPost(world, pos, state);
//            }

        }
    }


    private boolean canReedGrowUpwards(LevelReader world, BlockPos pos, BlockState state) {
        Type reedType = state.getValue(REEDS_TYPE);
        if (reedType == Type.ONE || reedType == Type.TWO_TOP){
            BlockPos abovePos = pos.above();
            if (world.getBlockState(abovePos).isAir()){
                return true;
            }

            BlockPos twoAbovePos = abovePos.above();
            if (world.getFluidState(abovePos).getType() == Fluids.WATER && world.getBlockState(twoAbovePos).isAir()){
                return true;
            }
        }

        return false;
    }

    private void growReedAbove(ServerLevel world, BlockPos pos, BlockState state) {
        BlockPos abovePos = pos.above();
        BlockState growAboveState = this.defaultBlockState().setValue(WATERLOGGED, world.getFluidState(abovePos).getType() == Fluids.WATER);
        Type reedType = state.getValue(REEDS_TYPE);
        if (reedType == Type.ONE){
            world.setBlockAndUpdate(pos.above(), growAboveState.setValue(REEDS_TYPE, Type.TWO_TOP));
        } else if (reedType == Type.TWO_TOP) {
            world.setBlockAndUpdate(pos.above(), growAboveState.setValue(REEDS_TYPE, Type.THREE_TOP));
        }

        BlockState updateStateHere = world.getBlockState(pos);
        world.setBlock(pos, updateStateHere.setValue(AGE, 0), 4);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }


    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return this.canReedGrow && this.canReedGrowUpwards(level, pos, state);
    }

    @Override
    public boolean isBonemealSuccess(Level world, RandomSource rand, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel world, RandomSource rand, BlockPos pos, BlockState state) {
        int age = state.getValue(AGE);
        age += Mth.nextInt(rand, 7, 15);
        if (age >= 15){
            this.growReedAbove(world, pos, state);
            int ageRemaining = age - 15;
            if (ageRemaining > 0){
                BlockPos abovePos = pos.above();
                BlockState aboveState = world.getBlockState(abovePos);
                if (this.canReedGrowUpwards(world, abovePos, aboveState)){
                    int aboveAge = aboveState.getValue(AGE);
                    aboveAge += ageRemaining;
                    world.setBlock(abovePos, aboveState.setValue(AGE, aboveAge), 4);
                }
            }
        } else {
            world.setBlock(pos, state.setValue(AGE, age), 4);
        }
    }

    public static enum Type implements StringRepresentable {
        ONE("1"),
        TWO_BOTTOM("2_bottom"),
        TWO_TOP("2_top"),
        THREE_BOTTOM("3_bottom"),
        THREE_MIDDLE("3_middle"),
        THREE_TOP("3_top");

        private final String typeName;

        private Type(String s){
            this.typeName = s;
        }

        @Override
        public String getSerializedName() {
            return this.typeName;
        }
    }
}
