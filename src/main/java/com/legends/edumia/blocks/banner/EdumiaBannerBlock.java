package com.legends.edumia.blocks.banner;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.properties.*;

public class EdumiaBannerBlock extends Block implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
    public static final BooleanProperty SMALL = BlockStateProperties.SHORT;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    private final BannerType type;
    public EdumiaBannerBlock(BannerType type,Properties properties) {
        super(properties);
        this.type = type;
    }
}
