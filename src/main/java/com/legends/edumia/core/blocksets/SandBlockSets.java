package com.legends.edumia.core.blocksets;

import com.legends.edumia.Edumia;
import com.legends.edumia.blocks.AxialSlabBlock;
import com.legends.edumia.blocks.Layer;
import com.legends.edumia.core.ItemLoader;
import net.minecraft.util.ColorRGBA;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class SandBlockSets {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Edumia.MOD_ID);

    public static SandSet WHITE_SAND = registerSandSets("white_sand", true, true, 15395562, null, SoundType.SAND);
    public static SandSet VOLCANIC_GRAVEL = registerSandSets("volcanic_gravel", true, false,-8356741, null, SoundType.GRAVEL);
    public static SandSet SAND = registerSandSets("sand", false, false,14406560, Blocks.SAND, SoundType.SAND);
    public static SandSet RED_SAND = registerSandSets("red_sand", false,false, 11098145, Blocks.RED_SAND,  SoundType.SAND);
    public static SandSet GAVEL = registerSandSets("gravel", false, false,-8356741, Blocks.GRAVEL, SoundType.GRAVEL);

    public static SandSet[] sandSets = new SandSet[]{
            SAND,
            RED_SAND,
            GAVEL,
            WHITE_SAND,
            VOLCANIC_GRAVEL,
    };

    public record SandSet(DeferredBlock<Block> block, DeferredBlock<SlabBlock> slab, DeferredBlock<Layer> layer,
                          DeferredBlock<Block> sandStone, DeferredBlock<AxialSlabBlock> sandstoneSlab,
                          DeferredBlock<StairBlock> sandstoneStairs, Block texture){

    }

    public static SandSet registerSandSets(String name, boolean isModBlock, boolean hasSandstone, int sandColor, Block copyOf, SoundType sound){
        DeferredBlock<Block> block;
        DeferredBlock<SlabBlock> slab;
        DeferredBlock<Layer> layer;
        DeferredBlock<Block> sandstone;
        DeferredBlock<AxialSlabBlock> sandstoneSlab;
        DeferredBlock<StairBlock> sandstoneStairs;
        Block texture;

        if (isModBlock){
            block = registerBlock(name, () -> new ColoredFallingBlock(new ColorRGBA(sandColor),
                            BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.SNARE)
                                    .strength(0.5F).sound(sound)));

            slab = registerBlock(name + "_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(block.get()).sound(sound)));

            layer = registerBlock(name + "_layer", () -> new Layer(BlockBehaviour.Properties.ofFullCopy(block.get()).sound(sound)));
            if (hasSandstone){
                sandstone = registerBlock(name + "stone", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND)
                        .instrument(NoteBlockInstrument.BASEDRUM).strength(0.8F).requiresCorrectToolForDrops()));

                sandstoneSlab = registerBlock(name + "stone_slab", () -> new AxialSlabBlock(sandstone));

                sandstoneStairs = registerBlock(name + "stone_stairs", () -> new StairBlock(block.get().defaultBlockState(),
                        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(0.8f).sound(SoundType.STONE).requiresCorrectToolForDrops()));
            }else {
                sandstone = null;
                sandstoneSlab = null;
                sandstoneStairs = null;
            }

            texture = null;
        } else {
            block = null;
            slab = registerBlock(name + "_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(copyOf).sound(sound)));
            layer = registerBlock(name + "_layer", () -> new Layer(BlockBehaviour.Properties.ofFullCopy(copyOf).sound(sound)));
            sandstone = null;
            sandstoneSlab = null;
            sandstoneStairs = null;
            texture = copyOf;
        }

        return new SandSet(block, slab, layer, sandstone, sandstoneSlab, sandstoneStairs, texture);
    }

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
