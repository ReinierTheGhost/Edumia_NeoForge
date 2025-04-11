package com.legends.edumia.world;

import net.minecraft.core.BlockPos;

import java.util.List;

public interface RandomTickScheduler {

    void scheduleRandomTick(BlockPos pos);


    List<BlockPos> getScheduledRandomTicks();
}
