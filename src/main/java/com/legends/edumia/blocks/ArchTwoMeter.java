package com.legends.edumia.blocks;


import com.legends.edumia.blocks.directional.HalfDirectional;
import com.legends.edumia.blocks.properties.BlockVoxelShapes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.swing.text.html.BlockView;

public class ArchTwoMeter extends HalfDirectional {
    public ArchTwoMeter(Properties properties) {
        super(properties);
    }

    public VoxelShape getShape(BlockState state) {
        if (state.getValue(TYPE_UPDOWN) == Half.BOTTOM) {
            switch (state.getValue(DIRECTION)) {
                case NORTH:
                default:
                    return  BlockVoxelShapes.stairBottomShapes.get(0);
                case EAST:
                    return BlockVoxelShapes.stairBottomShapes.get(1);
                case SOUTH:
                    return BlockVoxelShapes.stairBottomShapes.get(2);
                case WEST:
                    return BlockVoxelShapes.stairBottomShapes.get(3);
            }
        } else {
            switch (state.getValue(DIRECTION)) {
                case NORTH:
                default:
                    return BlockVoxelShapes.stairTopShapes.get(0);
                case EAST:
                    return BlockVoxelShapes.stairTopShapes.get(1);
                case SOUTH:
                    return BlockVoxelShapes.stairTopShapes.get(2);
                case WEST:
                    return BlockVoxelShapes.stairTopShapes.get(3);
            }
        }
    }

    public @NotNull VoxelShape getOcclusionShape(BlockState state, BlockGetter world, BlockPos pos) {
        return Shapes.empty();
    }
}
