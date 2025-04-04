package com.legends.edumia.blocks;


import com.legends.edumia.blocks.plants.ReedsBlock;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class EdumiaBlockStates {
    public static final EnumProperty<ReedsBlock.Type> REEDS_TYPE = EnumProperty.create("type", ReedsBlock.Type.class);
    public static final EnumProperty<Direction.Axis> SLAB_AXIS = BlockStateProperties.AXIS;
    public static final BooleanProperty PILLAR_ABOVE = BooleanProperty.create("above");
    public static final BooleanProperty PILLAR_BELOW = BooleanProperty.create("below");

    public static final BooleanProperty GATE_OPEN = BooleanProperty.create("open");
}
