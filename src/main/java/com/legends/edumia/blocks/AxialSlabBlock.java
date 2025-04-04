package com.legends.edumia.blocks;


import com.legends.edumia.utils.EdumiaLog;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import javax.swing.text.html.BlockView;
import java.util.Iterator;
import java.util.function.Supplier;

public class AxialSlabBlock extends EdumiaSlabBlock{
    public static final VoxelShape NORTH_SHAPE = Block.box(0.0, 0.0, 0.0,
            16.0, 16.0, 8.0);
    public static final VoxelShape SOUTH_SHAPE = Block.box(0.0, 0.0, 8.0,
            16.0, 16.0, 16.0);
    public static final VoxelShape WEST_SHAPE = Block.box(0.0, 0.0, 0.0,
            8.0, 16.0, 16.0);
    public static final VoxelShape EAST_SHAPE = Block.box(8.0, 0.0, 0.0,
            16.0, 16.0, 16.0);
    public AxialSlabBlock(Block block) {
        super(block);
        Direction.Axis defaultAxis = this.getSlabAxisProperty().getPossibleValues().contains(Direction.Axis.Y)
                ? Direction.Axis.Y : Direction.Axis.X;
        this.registerDefaultState(this.defaultBlockState().setValue(this.getSlabAxisProperty(), defaultAxis));
    }

    public AxialSlabBlock(Supplier<Block> block){
        this(block.get());
    }

    protected EnumProperty<Direction.Axis> getSlabAxisProperty(){
        return EdumiaBlockStates.SLAB_AXIS;
    }

    protected boolean canDoubleSlabBeWatterLogged(){
        return  false;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(new Property[]{this.getSlabAxisProperty()});
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        SlabType slabType = state.getValue(TYPE);
        if (slabType == SlabType.DOUBLE){
            return Shapes.block();
        }else {
            boolean top = slabType == SlabType.TOP;
            Direction.Axis axis = state.getValue(this.getSlabAxisProperty());
            if (axis == Direction.Axis.Y){
                return top ? SlabBlock.TOP_AABB : SlabBlock.BOTTOM_AABB;
            }else if (axis == Direction.Axis.X){
                return top ? EAST_SHAPE : WEST_SHAPE;
            } else if (axis == Direction.Axis.Z){
                return top ? SOUTH_SHAPE : NORTH_SHAPE;
            } else{
                return Shapes.block();
            }
        }
    }

    private static Direction.Axis getSlabAxis(BlockState state){
        Block block = state.getBlock();
        if (block instanceof SlabBlock){
            Iterator var2 = state.getProperties().iterator();

            Property prop;
            do {
                if (!var2.hasNext()){
                    if (block.getClass() == SlabBlock.class){
                        return Direction.Axis.Y;
                    }

                    EdumiaLog.warn("Unknown SlabBlock subclass: %s with no axis-based property. Assuming axis = Y",
                            new Object[]{block.getClass().toString()});
                    return Direction.Axis.Y;
                }

                prop = (Property) var2.next();
            }while (!(prop instanceof EnumProperty) || (prop).getValueClass() != Direction.Axis.class);

            return (Direction.Axis)state.getValue(prop);
        }else {
            throw new IllegalArgumentException("this method should only get called on known instances of SlabBlock");
        }
    }

    protected boolean isSameSlab(SlabBlock otherSlab){
        return otherSlab == this;
    }

    protected final boolean isSameSlab(BlockState otherBlockState){
        Block otherBlock = otherBlockState.getBlock();
        return otherBlock instanceof SlabBlock && this.isSameSlab((SlabBlock) otherBlock);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        AxialSlabPlacement placement = this.getSlabPlacmentState(context);
        return ((this.defaultBlockState().setValue(this.getSlabAxisProperty(), placement.axis))
                .setValue(TYPE, placement.type)).setValue(WATERLOGGED, placement.waterlogged);
    }

    protected AxialSlabPlacement getSlabPlacmentState(BlockPlaceContext context) {
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = world.getBlockState(pos);
        Direction dir = context.getClickedFace();
        Direction.Axis axis = dir.getAxis();
        FluidState fluid = context.getLevel().getFluidState(pos);
        boolean waterlogged = fluid.getType() == Fluids.WATER;
        if (this.isSameSlab(state)){
            waterlogged &= this.canDoubleSlabBeWatterLogged();
            return AxialSlabPlacement.of(getSlabAxis(state), SlabType.DOUBLE, waterlogged);
        }else {
            BlockPos clickedPos = pos.relative(dir.getOpposite());
            BlockState clickedState = world.getBlockState(clickedPos);
            boolean sneaking = context.isSecondaryUseActive();
            if (sneaking){
                if (axis.isHorizontal()){
                    axis = Direction.Axis.Y;
                }else if (axis.isVertical() && (!isSingleSlab(clickedState) || !getSlabAxis(clickedState).isHorizontal())){
                    dir = context.getHorizontalDirection();
                    axis = dir.getAxis();
                }
            }else if (isSingleSlab(clickedState)){
                axis = getSlabAxis(clickedState);
            }

            Direction axisPosDir = Direction.get(Direction.AxisDirection.POSITIVE, axis);
            Direction axisNegDir = Direction.get(Direction.AxisDirection.NEGATIVE, axis);
            double relevantHitVerCoord = axis.choose(context.getClickLocation().x, context.getClickLocation().y,
                    context.getClickLocation().z);
            double relevantPosCoord = axis.choose(pos.getX(), pos.getY(), pos.getZ());
            return dir == axisNegDir || dir != axisPosDir && relevantHitVerCoord - relevantPosCoord > 0.5 ?
                    AxialSlabPlacement.of(axis, SlabType.TOP, waterlogged) : AxialSlabPlacement.of(axis, SlabType.BOTTOM, waterlogged);
        }
    }

    private static boolean isSingleSlab(BlockState state) {
        return state.getBlock() instanceof SlabBlock && state.getValue(TYPE) != SlabType.DOUBLE;
    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        return this.isSlabReplaceable(state, context);
    }

    private boolean isSlabReplaceable(BlockState state, BlockPlaceContext context) {
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        ItemStack itemStack = context.getItemInHand();
        boolean holdingSameSlab = false;
        if (itemStack.getItem() instanceof BlockItem){
            Block itemBlock = ((BlockItem)itemStack.getItem()).getBlock();
            if (itemBlock instanceof SlabBlock){
                holdingSameSlab = this.isSameSlab((SlabBlock) itemBlock);
            }
        }

        Direction dir = context.getClickedFace();
        boolean sneaking = context.isSecondaryUseActive();
        SlabType slabType = state.getValue(TYPE);
        Direction.Axis existingAxis = getSlabAxis(state);
        if (sneaking && existingAxis != Direction.Axis.Y){
            BlockPos offsetPos = pos.relative(dir);
            if (world.getBlockState(offsetPos).canBeReplaced(AxialSlabUseContext
                    .makeReplacementContext(context, offsetPos, dir))){
                return false;
            }
        }
        if (slabType != SlabType.DOUBLE && holdingSameSlab){
            if (context.replacingClickedOnBlock()){
                double relevantHitVecCoord = existingAxis.choose(context.getClickLocation().x, context.getClickLocation().y,
                        context.getClickLocation().z);
                double relevantPosCoord = existingAxis.choose(pos.getX(), pos.getY(), pos.getZ());
                boolean flag = relevantHitVecCoord - relevantPosCoord > 0.5;
                if (slabType == SlabType.BOTTOM){
                    return dir == Direction.get(Direction.AxisDirection.POSITIVE, existingAxis) || flag && dir.getAxis() != existingAxis;
                }else{
                    return dir == Direction.get(Direction.AxisDirection.NEGATIVE, existingAxis) || !flag && dir.getAxis() != existingAxis;
                }
            }else{
                return true;
            }
        } else{
            return false;
        }
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        SlabType slabType = state.getValue(TYPE);
        Direction.Axis axis = state.getValue(this.getSlabAxisProperty());
        Direction.AxisDirection axisDir = slabType == SlabType.BOTTOM ? Direction.AxisDirection.NEGATIVE : Direction.AxisDirection.POSITIVE;
        Direction dir = Direction.fromAxisAndDirection(axis, axisDir);
        Direction rotatedDir = rot.rotate(dir);
        Direction.Axis rotatedAxis = rotatedDir.getAxis();
        Direction.AxisDirection rotatedAxisDir = rotatedDir.getAxisDirection();
        if (this.getSlabAxisProperty().getValueClass().isInstance(rotatedAxis)){
            SlabType rotatedSlabType = slabType == SlabType.DOUBLE ? slabType : (rotatedAxisDir == Direction.AxisDirection.NEGATIVE ?
                    SlabType.BOTTOM : (rotatedAxisDir == Direction.AxisDirection.POSITIVE ? SlabType.TOP : slabType));
            return (state.setValue(this.getSlabAxisProperty(), rotatedAxis)).setValue(TYPE, rotatedSlabType);
        }else{
            return state;
        }
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        Direction.Axis axis = state.getValue(this.getSlabAxisProperty());
        SlabType type = state.getValue(TYPE);
        if (mirror == Mirror.LEFT_RIGHT && axis == Direction.Axis.Z || mirror == Mirror.FRONT_BACK && axis == Direction.Axis.X){
            if (type == SlabType.BOTTOM) {
                type = SlabType.TOP;
            } else if (type == SlabType.TOP){
                type = SlabType.BOTTOM;
            }
        }

        return state.setValue(TYPE, type);
    }

    @Override
    public boolean placeLiquid(LevelAccessor level, BlockPos pos, BlockState state, FluidState fluidState) {
        return this.canDoubleSlabBeWatterLogged() ? (new DefaultImplWaterLoggable()).placeLiquid(level, pos, state, fluidState)
                : super.placeLiquid(level, pos, state, fluidState);
    }

    @Override
    public boolean canPlaceLiquid(@Nullable Player player, BlockGetter level, BlockPos pos, BlockState blockState, Fluid fluid) {
        return this.canDoubleSlabBeWatterLogged() ? (new DefaultImplWaterLoggable()).canPlaceLiquid(player, level, pos, blockState, fluid)
                : super.canPlaceLiquid(player,level, pos, blockState, fluid);
    }

    private final class DefaultImplWaterLoggable implements SimpleWaterloggedBlock {

        private DefaultImplWaterLoggable(){

        }
    }
    protected static class AxialSlabUseContext extends BlockPlaceContext{
        protected AxialSlabUseContext(Level w, Player pl, InteractionHand h, ItemStack stack, BlockHitResult rayTrace) {
            super(w, pl, h, stack, rayTrace);
            BlockState state = this.getLevel().getBlockState(rayTrace.getBlockPos());
            if (state.getBlock() instanceof SlabBlock){
                SlabBlock slabBlock = (SlabBlock) state.getBlock();
                VerticalOnlySlabBlock verticalSlab = VerticalOnlySlabBlock.getVerticalSlabFor(slabBlock);
                if (verticalSlab != null){
                    this.replaceClicked = verticalSlab.canBeReplaced(state, this);
                }
            }
        }

        public AxialSlabUseContext(UseOnContext context){
            super(context);
        }

        public static AxialSlabUseContext makeReplacementContext(BlockPlaceContext context, BlockPos pos, Direction dir){
            Vec3 blockVec = new Vec3((double) pos.getX() + 0.5 + (double)dir.getStepY() * 0.5,
                    (double) pos.getY() + 0.5 + (double)dir.getStepX() * 0.5,
                    (double) pos.getZ() + 0.5 + (double)dir.getStepZ() * 0.5);
            BlockHitResult rayTrace = new BlockHitResult(blockVec, dir, pos, false);
            return new AxialSlabUseContext(context.getLevel(), context.getPlayer(), context.getHand(), context.getItemInHand(), rayTrace);
        }
    }

    public static class AxialSlabPlacement {
        public final Direction.Axis axis;
        public final SlabType type;
        public final boolean waterlogged;
        private AxialSlabPlacement(Direction.Axis ax, SlabType type, boolean water){
            this.axis = ax;
            this.type = type;
            this.waterlogged = water;
        }

        public static AxialSlabPlacement of(Direction.Axis ax, SlabType type, boolean water){
            return new AxialSlabPlacement(ax, type, water);
        }
    }
}
