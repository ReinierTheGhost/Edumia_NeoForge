package com.legends.edumia.blocks.properties;



import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Arrays;
import java.util.List;

public class BlockVoxelShapes {

    public static final List<VoxelShape> stairTopShapes;
    public static final List<VoxelShape> stairBottomShapes;

    public BlockVoxelShapes() {
    }

    static {
        stairTopShapes = Arrays.asList(
                Shapes.or(Block.box(0.0, 0.0, 8.0, 16.0, 16.0, 16.0),
                        Block.box(0.0, 8.0, 0.0, 16.0, 16.0, 8.0)),
                Shapes.or(Block.box(0.0, 0.0, 0.0, 8.0, 16.0, 16.0),
                        Block.box(8.0, 8.0, 0.0, 16.0, 16.0, 16.0)),
                Shapes.or(Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 8.0),
                        Block.box(0.0, 8.0, 8.0, 16.0, 16.0, 16.0)),
                Shapes.or(Block.box(8.0, 0.0, 0.0, 16.0, 16.0, 16.0),
                        Block.box(0.0, 8.0, 0.0, 8.0, 16.0, 16.0)));
        stairBottomShapes = Arrays.asList(
                Shapes.or(Block.box(0.0, 0.0, 8.0, 16.0, 16.0, 16.0),
                        Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 8.0)),
                Shapes.or(Block.box(0.0, 0.0, 0.0, 8.0, 16.0, 16.0),
                        Block.box(8.0, 0.0, 0.0, 16.0, 8.0, 16.0)),
                Shapes.or(Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 8.0),
                        Block.box(0.0, 0.0, 8.0, 16.0, 8.0, 16.0)),
                Shapes.or(Block.box(8.0, 0.0, 0.0, 16.0, 16.0, 16.0),
                        Block.box(0.0, 0.0, 0.0, 8.0, 8.0, 16.0)));

    }
}
