package com.legends.edumia.blocks;



import com.legends.edumia.blocks.base.WaterloggedBidirectionalShape;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.swing.text.html.BlockView;

public class ArchSmall extends WaterloggedBidirectionalShape {
    private static final VoxelShape SHAPE =
            Block.box(0.0, 8.0, 0.0, 16.0, 16.0, 16.0);

    public ArchSmall(Properties properties) {
        super(properties);
    }

    public VoxelShape getShape(BlockState state) {
        return SHAPE;
    }

    public VoxelShape getOcclusionShape(BlockState state, BlockView world, BlockPos pos) {
        return Shapes.empty();
    }
}
