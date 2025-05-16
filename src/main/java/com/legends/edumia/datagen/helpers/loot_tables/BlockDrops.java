package com.legends.edumia.datagen.helpers.loot_tables;


import com.legends.edumia.core.blocksets.ModNatureBlocks;
import com.legends.edumia.core.BlockLoader;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class BlockDrops {
    public static List<Block> blocks = new ArrayList<>() {
        {

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
            add(BlockLoader.TEST_SAPLING.get());

        }
    };
}
