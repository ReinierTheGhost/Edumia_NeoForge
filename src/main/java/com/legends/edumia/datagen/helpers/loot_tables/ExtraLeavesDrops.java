package com.legends.edumia.datagen.helpers.loot_tables;

import com.legends.edumia.core.blocksets.ModNatureBlocks;
import com.legends.edumia.core.blocksets.WoodBlockSets;
import com.legends.edumia.core.ItemLoader;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class ExtraLeavesDrops {
    public record LeavesDrop(Block block, Block drop, Item extra) {}
    public static List<LeavesDrop> blocks = new ArrayList<>() {
        {
            add(new LeavesDrop(WoodBlockSets.MANGO.leaves().get(), WoodBlockSets.MANGO.sapling().get(), ItemLoader.MANGO.get()));
            add(new LeavesDrop(ModNatureBlocks.APPLE_LEAVES_RED.get(), WoodBlockSets.APPLE.sapling().get(), Items.APPLE));
        }
    };
}
