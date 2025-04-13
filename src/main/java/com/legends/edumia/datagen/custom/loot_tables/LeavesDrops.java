package com.legends.edumia.datagen.custom.loot_tables;

import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class LeavesDrops {
    public record LeavesDrop(Block block, Block drop) {}
    public static List<LeavesDrop> blocks = new ArrayList<>() {
        {
        }
    };
}
