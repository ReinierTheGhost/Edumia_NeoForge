package com.legends.edumia.datagen.helpers.loot_tables;

import com.legends.edumia.blocks.blocksets.ModNatureBlocks;
import com.legends.edumia.blocks.blocksets.WoodBlockSets;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class LeavesDrops {
    public record LeavesDrop(Block block, Block drop) {}
    public static List<LeavesDrop> blocks = new ArrayList<>() {
        {
            add(new LeavesDrop(ModNatureBlocks.APPLE_LEAVES_GREEN.get(), WoodBlockSets.APPLE.sapling().get()));
            add(new LeavesDrop(ModNatureBlocks.PEAR_LEAVES_FRUIT.get(), WoodBlockSets.PEAR.sapling().get()));
            add(new LeavesDrop(ModNatureBlocks.CHERRY_LEAVES_FRUIT.get(), WoodBlockSets.CHERRY.sapling().get()));
            add(new LeavesDrop(ModNatureBlocks.BLACK_OAK_LEAVES.get(), WoodBlockSets.BLACK_OAK.sapling().get()));
            add(new LeavesDrop(ModNatureBlocks.GHOST_GUM_LEAVES.get(), WoodBlockSets.GHOST_GUM.sapling().get()));
            add(new LeavesDrop(ModNatureBlocks.HOLLY_LEAVES.get(), WoodBlockSets.HOLLY.sapling().get()));
            add(new LeavesDrop(ModNatureBlocks.MAPLE_LEAVES.get(), WoodBlockSets.MAPLE.sapling().get()));
        }
    };
}
