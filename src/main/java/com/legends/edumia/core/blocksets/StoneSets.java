package com.legends.edumia.core.blocksets;

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
    public static StoneSet PALEGRIT = registerStoneSet("palegrit", STONE_STRENGTH);
    public static StoneSet UMBRA_ROCK = registerStoneSet("umbra_rock", STONE_STRENGTH);
    public static StoneSet LIMESTONE = registerStoneSet("limestone", STONE_STRENGTH);
    public static StoneSet SKY_SHALE = registerStoneSet("sky_shale", STONE_STRENGTH);
    public static StoneSet SUNSTONE = registerStoneSet("sunstone", STONE_STRENGTH);
    public static StoneSet GLEAMSTONE = registerStoneSet("gleamstone", STONE_STRENGTH);
    public static StoneSet GREEN_BASALT = registerStoneSet("green_basalt", STONE_STRENGTH);
    public static StoneSet GREEN_BASALT_COBBLESTONE = registerStoneSet("green_basalt_cobblestone", STONE_STRENGTH);
    public static StoneSet GNEISS = registerStoneSet("gneiss", STONE_STRENGTH);
    public static StoneSet EATHERITE = registerStoneSet("eatherite", STONE_STRENGTH);
    public static StoneSet CINDERRITE = registerStoneSet("cinderrite", STONE_STRENGTH);
    public static StoneSet BLOODSTONE = registerStoneSet("bloodstone", STONE_STRENGTH);
    public static StoneSet SUNSTONE_COBBLE = registerStoneSet("sunstone_cobble", STONE_STRENGTH);
    public static StoneSet CHALK = registerStoneSet("chalk", STONE_STRENGTH);
    public static StoneSet AELORIAN_ROCK = registerStoneSet("aelorian_rock", STONE_STRENGTH);
    public static StoneSet DUSKEN_AELORIAN_ROCK = registerStoneSet("dusken_aelorian_rock", STONE_STRENGTH);
    public static StoneSet PALE_AELORIAN_ROCK = registerStoneSet("pale_aelorian_rock", STONE_STRENGTH);
    public static StoneSet VOLCANIC_ROCK = registerStoneSet("volcanic_rock", STONE_STRENGTH);
    public static StoneSet AELORIAN_COBBLESTONE = registerStoneSet("aelorian_cobblestone", STONE_STRENGTH);
    public static StoneSet DUSKEN_AELORIAN_COBBLESTONE = registerStoneSet("dusken_aelorian_cobblestone", STONE_STRENGTH);
    public static StoneSet PALE_AELORIAN_COBBLESTONE = registerStoneSet("pale_aelorian_cobblestone", STONE_STRENGTH);
    public static StoneSet MOSSY_AELORIAN_COBBLESTONE = registerStoneSet("mossy_aelorian_cobblestone", STONE_STRENGTH);
    public static StoneSet MOSSY_PALE_AELORIAN_COBBLESTONE = registerStoneSet("mossy_pale_aelorian_cobblestone", STONE_STRENGTH);
    public static StoneSet MOSSY_DUSKEN_AELORIAN_COBBLESTONE = registerStoneSet("mossy_dusken_aelorian_cobblestone", STONE_STRENGTH);
    public static StoneSet CACHOLONG = registerStoneSet("cacholong", STONE_STRENGTH);
    public static StoneSet CRIMSON_GENSITE = registerStoneSet("crimson_gensite", STONE_STRENGTH);

    public static StoneSet[] naturalSets = new StoneSet[]{
            CRIMSON_GENSITE,
            SKY_SHALE,
            LIMESTONE,
            UMBRA_ROCK,
            PALEGRIT,
            SUNSTONE,
            GLEAMSTONE,
            GREEN_BASALT,
            GREEN_BASALT_COBBLESTONE,
            GNEISS,
            EATHERITE,
            CINDERRITE,
            BLOODSTONE,
            SUNSTONE_COBBLE,
            CHALK,
            AELORIAN_ROCK,
            DUSKEN_AELORIAN_ROCK,
            PALE_AELORIAN_ROCK,
            VOLCANIC_ROCK,
            AELORIAN_COBBLESTONE,
            DUSKEN_AELORIAN_COBBLESTONE,
            PALE_AELORIAN_COBBLESTONE,
            MOSSY_AELORIAN_COBBLESTONE,
            MOSSY_PALE_AELORIAN_COBBLESTONE,
            MOSSY_DUSKEN_AELORIAN_COBBLESTONE,
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
