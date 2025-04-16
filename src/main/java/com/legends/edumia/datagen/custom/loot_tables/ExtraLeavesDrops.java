package com.legends.edumia.datagen.custom.loot_tables;

import com.legends.edumia.blocks.blocksets.WoodBlockSets;
import com.legends.edumia.core.ItemLoader;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class ExtraLeavesDrops {
    public record LeavesDrop(Block block, Block drop, Item extra) {}
    public static List<LeavesDrop> blocks = new ArrayList<>() {
        {
            add(new LeavesDrop(WoodBlockSets.MANGO.leaves().get(), WoodBlockSets.MANGO.sapling().get(), ItemLoader.MANGO.get()));
        }
    };
}
