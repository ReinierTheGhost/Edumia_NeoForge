package com.legends.edumia.blocks.blocksets;

import com.legends.edumia.Edumia;
import com.legends.edumia.blocks.AxialSlabBlock;
import com.legends.edumia.core.ItemLoader;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class StoneSets {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Edumia.MOD_ID);
    public static final float STONE_STRENGTH = 2.0f;
    public static StoneSet LIGHT_GRAY_STONE = registerStoneSet("light_gray_stone", STONE_STRENGTH);
    public static StoneSet BROWN_STONE = registerStoneSet("brown_stone", STONE_STRENGTH);
    public static StoneSet LIMESTONE = registerStoneSet("limestone", STONE_STRENGTH);
    public static StoneSet BLUE_STONE = registerStoneSet("blue_stone", STONE_STRENGTH);
    public static StoneSet YELLOW_STONE = registerStoneSet("yellow_stone", STONE_STRENGTH);
    public static StoneSet YELLOW_STONE_2 = registerStoneSet("yellow_stone_2", STONE_STRENGTH);
    public static StoneSet GREEN_BASALT = registerStoneSet("green_basalt", STONE_STRENGTH);
    public static StoneSet GREEN_BASALT_COBBLESTONE = registerStoneSet("green_basalt_cobblestone", STONE_STRENGTH);
    public static StoneSet GNEISS = registerStoneSet("gneiss", STONE_STRENGTH);
    public static StoneSet CYAN_STONE = registerStoneSet("cyan_stone", STONE_STRENGTH);
    public static StoneSet ORANGE_ROCK = registerStoneSet("orange_rock", STONE_STRENGTH);
    public static StoneSet RED_ROCK = registerStoneSet("red_rock", STONE_STRENGTH);
    public static StoneSet YELLOW_COBBLE = registerStoneSet("yellow_cobble", STONE_STRENGTH);
    public static StoneSet CHALK = registerStoneSet("chalk", STONE_STRENGTH);
    public static StoneSet HIGH_ELVEN_ROCK = registerStoneSet("high_elven_rock", STONE_STRENGTH);
    public static StoneSet DARK_HIGH_ELVEN_ROCK = registerStoneSet("dark_high_elven_rock", STONE_STRENGTH);
    public static StoneSet LIGHT_HIGH_ELVEN_ROCK = registerStoneSet("light_high_elven_rock", STONE_STRENGTH);
    public static StoneSet VOLCANIC_ROCK = registerStoneSet("volcanic_rock", STONE_STRENGTH);
    public static StoneSet HIGH_ELVEN_COBBLESTONE = registerStoneSet("high_elven_cobblestone", STONE_STRENGTH);
    public static StoneSet DARK_HIGH_ELVEN_COBBLESTONE = registerStoneSet("dark_high_elven_cobblestone", STONE_STRENGTH);
    public static StoneSet LIGHT_HIGH_ELVEN_COBBLESTONE = registerStoneSet("light_high_elven_cobblestone", STONE_STRENGTH);
    public static StoneSet MOSSY_HIGH_ELVEN_COBBLESTONE = registerStoneSet("mossy_high_elven_cobblestone", STONE_STRENGTH);
    public static StoneSet MOSSY_LIGHT_HIGH_ELVEN_COBBLESTONE = registerStoneSet("mossy_light_high_elven_cobblestone", STONE_STRENGTH);
    public static StoneSet MOSSY_DARK_HIGH_ELVEN_COBBLESTONE = registerStoneSet("mossy_dark_high_elven_cobblestone", STONE_STRENGTH);
    public static StoneSet CACHOLONG = registerStoneSet("cacholong", STONE_STRENGTH);
    public static StoneSet RED_GENSAI_STONE = registerStoneSet("red_gensai_stone", STONE_STRENGTH);

    public static StoneSet[] naturalSets = new StoneSet[]{
            RED_GENSAI_STONE,
            BLUE_STONE,
            LIMESTONE,
            BROWN_STONE,
            LIGHT_GRAY_STONE,
            YELLOW_STONE,
            YELLOW_STONE_2,
            GREEN_BASALT,
            GREEN_BASALT_COBBLESTONE,
            GNEISS,
            CYAN_STONE,
            ORANGE_ROCK,
            RED_ROCK,
            YELLOW_COBBLE,
            CHALK,
            HIGH_ELVEN_ROCK,
            DARK_HIGH_ELVEN_ROCK,
            LIGHT_HIGH_ELVEN_ROCK,
            VOLCANIC_ROCK,
            HIGH_ELVEN_COBBLESTONE,
            DARK_HIGH_ELVEN_COBBLESTONE,
            LIGHT_HIGH_ELVEN_COBBLESTONE,
            MOSSY_HIGH_ELVEN_COBBLESTONE,
            MOSSY_LIGHT_HIGH_ELVEN_COBBLESTONE,
            MOSSY_DARK_HIGH_ELVEN_COBBLESTONE,
            CACHOLONG,
    };



    public record StoneSet(DeferredBlock<Block> block, DeferredBlock<AxialSlabBlock> slab, DeferredBlock<StairBlock> stair,
                           DeferredBlock<WallBlock> wall){

    }

    public static StoneSet registerStoneSet(String name, float strength) {

        DeferredBlock<Block> stone = registerBlock(name,
                () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                .strength(strength).sound(SoundType.STONE).requiresCorrectToolForDrops()));

        DeferredBlock<AxialSlabBlock> slab = registerBlock(name + "_slab", () -> new AxialSlabBlock(stone));

        DeferredBlock<StairBlock> stairs = registerBlock(name + "_stairs",
                () -> new StairBlock(stone.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(stone.get())
                        .strength(strength).sound(SoundType.STONE).requiresCorrectToolForDrops()));

        DeferredBlock<WallBlock> wall = registerBlock(name + "_wall",
                () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(stone.get())
                .strength(strength).sound(SoundType.STONE).requiresCorrectToolForDrops()));

        return new StoneSet(stone, slab, stairs, wall);
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
