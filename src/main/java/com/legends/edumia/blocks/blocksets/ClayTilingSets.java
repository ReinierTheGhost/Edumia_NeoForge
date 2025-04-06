package com.legends.edumia.blocks.blocksets;

import com.legends.edumia.Edumia;
import com.legends.edumia.blocks.AxialSlabBlock;
import com.legends.edumia.blocks.VerticalCorner;
import com.legends.edumia.core.ItemLoader;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class ClayTilingSets {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Edumia.MOD_ID);
    public static final float CLAY_STRENGTH = 1.25f;
    public static final float CLAY_RESISTANCE = 4.2f;
    public static ClayTilingSet CLAY_TILING = registerClaySet("", CLAY_STRENGTH, CLAY_RESISTANCE);
    public static ClayTilingSet BLACK_CLAY_TILING = registerClaySet("black_", CLAY_STRENGTH, CLAY_RESISTANCE);
    public static ClayTilingSet BLUE_CLAY_TILING = registerClaySet("blue_", CLAY_STRENGTH, CLAY_RESISTANCE);
    public static ClayTilingSet BROWN_CLAY_TILING = registerClaySet("brown_", CLAY_STRENGTH, CLAY_RESISTANCE);
    public static ClayTilingSet CYAN_CLAY_TILING = registerClaySet("cyan_", CLAY_STRENGTH, CLAY_RESISTANCE);
    public static ClayTilingSet GRAY_CLAY_TILING = registerClaySet("gray_", CLAY_STRENGTH, CLAY_RESISTANCE);
    public static ClayTilingSet GREEN_CLAY_TILING = registerClaySet("green_", CLAY_STRENGTH, CLAY_RESISTANCE);
    public static ClayTilingSet LIGHT_BLUE_CLAY_TILING = registerClaySet("light_blue_", CLAY_STRENGTH, CLAY_RESISTANCE);
    public static ClayTilingSet LIGHT_GRAY_CLAY_TILING = registerClaySet("light_gray_", CLAY_STRENGTH, CLAY_RESISTANCE);
    public static ClayTilingSet LIME_CLAY_TILING = registerClaySet("lime_", CLAY_STRENGTH, CLAY_RESISTANCE);
    public static ClayTilingSet MAGENTA_CLAY_TILING = registerClaySet("magenta_", CLAY_STRENGTH, CLAY_RESISTANCE);
    public static ClayTilingSet ORANGE_CLAY_TILING = registerClaySet("orange_", CLAY_STRENGTH, CLAY_RESISTANCE);
    public static ClayTilingSet PINK_CLAY_TILING = registerClaySet("pink_", CLAY_STRENGTH, CLAY_RESISTANCE);
    public static ClayTilingSet PURPLE_CLAY_TILING = registerClaySet("purple_", CLAY_STRENGTH, CLAY_RESISTANCE);
    public static ClayTilingSet RED_CLAY_TILING = registerClaySet("red_", CLAY_STRENGTH, CLAY_RESISTANCE);
    public static ClayTilingSet WHITE_CLAY_TILING = registerClaySet("white_", CLAY_STRENGTH, CLAY_RESISTANCE);
    public static ClayTilingSet YELLOW_CLAY_TILING = registerClaySet("yellow_", CLAY_STRENGTH, CLAY_RESISTANCE);




    public static ClayTilingSet[] sets = new ClayTilingSet[] {
            CLAY_TILING,
            BLACK_CLAY_TILING,
            BLUE_CLAY_TILING,
            BROWN_CLAY_TILING,
            CYAN_CLAY_TILING,
            GRAY_CLAY_TILING,
            GREEN_CLAY_TILING,
            LIGHT_BLUE_CLAY_TILING,
            LIGHT_GRAY_CLAY_TILING,
            LIME_CLAY_TILING,
            MAGENTA_CLAY_TILING,
            ORANGE_CLAY_TILING,
            PINK_CLAY_TILING,
            PURPLE_CLAY_TILING,
            RED_CLAY_TILING,
            WHITE_CLAY_TILING,
            YELLOW_CLAY_TILING
    };

    public record ClayTilingSet(DeferredBlock<Block> block, DeferredBlock<AxialSlabBlock> slab, DeferredBlock<StairBlock> stairs, DeferredBlock<Block> corner) {
    }

    public static ClayTilingSet registerClaySet(String name, float strength, float resistance) {

        DeferredBlock<Block> block = registerBlock(name + "clay_tiling",
                () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                .strength(strength, resistance).sound(SoundType.STONE).requiresCorrectToolForDrops()));


        DeferredBlock<AxialSlabBlock> slab = registerBlock(name + "clay_tiling_slab", () -> new AxialSlabBlock(block));

        DeferredBlock<StairBlock> stairs = registerBlock(name + "clay_tiling_stairs",
                () -> new StairBlock(block.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(block.get())
                        .strength(strength, resistance).sound(SoundType.STONE).requiresCorrectToolForDrops()));

        DeferredBlock<Block> corner = registerBlock(name +"clay_tiling_vertical_corner",
                () -> new VerticalCorner(BlockBehaviour.Properties.ofFullCopy(block.get())
                        .strength(strength, resistance).requiresCorrectToolForDrops()));

        return new ClayTilingSet(block, slab, stairs, corner);
    }
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBuildingBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBuildingBlockItem(String name, DeferredBlock<T> block) {
        ItemLoader.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
