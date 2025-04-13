package com.legends.edumia.core;

import com.legends.edumia.Edumia;
import com.legends.edumia.blocks.CrystalBlock;
import com.legends.edumia.blocks.EdumiaPillarBlock;
import com.legends.edumia.world.trees.ModTreeGrowers;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;
public class BlockLoader {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Edumia.MOD_ID);

    public static final DeferredBlock<Block> HIGH_ELVEN_CRYSTAL = registerBlock("high_elven_crystal", () ->
            new CrystalBlock(12, 2, DyeColor.LIGHT_BLUE));

    public static final DeferredBlock<Block> VOLCANIC_DIRT = registerBlock("volcanic_dirt", () ->
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT)));
    public static final DeferredBlock<Block> VOLCANIC_DIRT_PATH = registerBlock("volcanic_dirt_path", () ->
            new DirtPathBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT_PATH)));


    public static final DeferredBlock<Block> BRICK_PILLAR = registerBlock("brick_pillar", () ->
            new EdumiaPillarBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> STONE_PILLAR = registerBlock("stone_pillar", () ->
            new EdumiaPillarBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops()
                    .strength(1.5f, 6f)));
    public static final DeferredBlock<Block> SANDSTONE_PILLAR = registerBlock("sandstone_pillar", () ->
            new EdumiaPillarBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops()
                    .strength(1.5f, 6f)));


    public static final DeferredBlock<Block> BROWN_SANDSTONE_SLATES = registerBlock("brown_sandstone_slates", () ->
            new RotatedPillarBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops()
                    .strength(1.5f, 6f)));

    public static final DeferredBlock<Block> CHISELED_ANDESITE = registerBlock("chiseled_andesite", () ->
            new RotatedPillarBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops()
                    .strength(2f, 6f)));

    public static final DeferredBlock<Block> CHISELED_DIORITE = registerBlock("chiseled_diorite", () ->
            new RotatedPillarBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops()
                    .strength(2f, 6f)));

    public static final DeferredBlock<Block> CHISELED_GRANITE = registerBlock("chiseled_granite", () ->
            new RotatedPillarBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops()
                    .strength(2f, 6f)));

    public static final DeferredBlock<Block> CHISELED_DRIPSTONE = registerBlock("chiseled_dripstone", () ->
            new RotatedPillarBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops()
                    .strength(2f, 6f)));

    public static final DeferredBlock<Block> DIRTY_CHALK = registerBlock("dirty_chalk", () ->
            new RotatedPillarBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops()
                    .strength(2f, 6f)));

    public static final DeferredBlock<Block> TEST_SAPLING = registerBlock("test_sapling", () ->
            new SaplingBlock(ModTreeGrowers.TEST, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ItemLoader.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
