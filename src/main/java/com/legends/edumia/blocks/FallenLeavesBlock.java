package com.legends.edumia.blocks;



import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.swing.text.html.BlockView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class FallenLeavesBlock extends Block {
    public static final List<FallenLeavesBlock> ALL_FALLEN_LEAVES = new ArrayList<>();
    public static final Map<Block, FallenLeavesBlock> LEAVES_TO_FALLEN_LEAVES = new HashMap<>();
    private static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0);
    private final Block baseLeaveBlock;

    public FallenLeavesBlock(Block leaf, Properties settings) {
        super(settings);
        this.baseLeaveBlock = leaf;
        ALL_FALLEN_LEAVES.add(this);
        LEAVES_TO_FALLEN_LEAVES.put(this.baseLeaveBlock, this);
    }

    public FallenLeavesBlock(Block leaf){
        this(leaf, Properties.of().strength(0.1f).sound(SoundType.GRASS).noCollission().noOcclusion());
    }

    public FallenLeavesBlock(Supplier<Block> leaf){
        this(leaf.get());
    }

    public Block getBaseLeaveBlock() {
        return baseLeaveBlock;
    }

    public static FallenLeavesBlock getFallenLeavesFor(Block leafBlock){
        return LEAVES_TO_FALLEN_LEAVES.get(leafBlock);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        BlockPos belowPos = pos.below();
        return world.getBlockState(belowPos).isFaceSturdy(world, belowPos, Direction.UP) || world.getFluidState(belowPos).getType() == Fluids.WATER;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
        return facing == Direction.DOWN && !state.canSurvive(world, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, world, currentPos, facingPos);
    }
}
