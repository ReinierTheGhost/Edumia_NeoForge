package com.legends.edumia.datagen.custom.loot_tables;


import com.legends.edumia.blocks.blocksets.ModNatureBlocks;
import com.legends.edumia.core.BlockLoader;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class BlockDrops {
    public static List<Block> blocks = new ArrayList<>() {
        {
            add(ModNatureBlocks.APPLE_LEAVES_RED.get());
            add(ModNatureBlocks.APPLE_LEAVES_GREEN.get());
            add(ModNatureBlocks.PEAR_LEAVES_FRUIT.get());
            add(ModNatureBlocks.CHERRY_LEAVES_FRUIT.get());
            add(ModNatureBlocks.BLACK_OAK_LEAVES.get());
            add(ModNatureBlocks.GHOST_GUM_LEAVES.get());
            add(ModNatureBlocks.APPLE_LEAVES_RED.get());
            add(ModNatureBlocks.HOLLY_LEAVES.get());
            add(ModNatureBlocks.MAPLE_LEAVES.get());
            add(ModNatureBlocks.REEDS.get());
            add(ModNatureBlocks.PAPYRUS.get());
            add(ModNatureBlocks.DRIED_REEDS.get());
            add(BlockLoader.BROWN_SANDSTONE_SLATES.get());
            add(BlockLoader.CHISELED_ANDESITE.get());
            add(BlockLoader.CHISELED_DIORITE.get());
            add(BlockLoader.CHISELED_DRIPSTONE.get());
            add(BlockLoader.CHISELED_GRANITE.get());
            add(BlockLoader.BRICK_PILLAR.get());
            add(BlockLoader.DIRTY_CHALK.get());
            add(BlockLoader.HIGH_ELVEN_CRYSTAL.get());
            add(BlockLoader.SANDSTONE_PILLAR.get());
            add(BlockLoader.STONE_PILLAR.get());
            add(BlockLoader.VOLCANIC_DIRT.get());
        }
    };
}
