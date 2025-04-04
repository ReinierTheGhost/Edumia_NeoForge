package com.legends.edumia.blocks.base;


import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.swing.text.html.BlockView;

public abstract class Shape extends Block {
    public Shape(Properties properties) {
        super(properties);
    }


    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return this.getShape(state);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return !this.hasCollision ? Shapes.empty() : this.getShape(state);
    }

    @Override
    public VoxelShape getInteractionShape(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return !this.hasCollision ? Shapes.empty() : this.getShape(state);
    }

    public abstract VoxelShape getShape(BlockState var1);
}
