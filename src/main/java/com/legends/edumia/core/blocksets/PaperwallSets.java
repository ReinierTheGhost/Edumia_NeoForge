package com.legends.edumia.core.blocksets;

import com.legends.edumia.Edumia;
import com.legends.edumia.core.ItemLoader;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StainedGlassPaneBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class PaperwallSets {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Edumia.MOD_ID);


    public static PaperwallSet BLACK_PAPERWALL = registerGlassSet("black_paperwall",  DyeColor.BLACK);
    public static PaperwallSet BLUE_PAPERWALL = registerGlassSet("blue_paperwall", DyeColor.BLUE);
    public static PaperwallSet BROWN_PAPERWALL = registerGlassSet("brown_paperwall",  DyeColor.BROWN);
    public static PaperwallSet CYAN_PAPERWALL = registerGlassSet("cyan_paperwall",  DyeColor.CYAN);
    public static PaperwallSet GRAY_PAPERWALL = registerGlassSet("gray_paperwall",  DyeColor.GRAY);
    public static PaperwallSet GREEN_PAPERWALL = registerGlassSet("green_paperwall",  DyeColor.GREEN);
    public static PaperwallSet LIGHT_BLUE_PAPERWALL = registerGlassSet("light_blue_paperwall",  DyeColor.LIGHT_BLUE);
    public static PaperwallSet LIGHT_GRAY_PAPERWALL = registerGlassSet("light_gray_paperwall",  DyeColor.LIGHT_GRAY);
    public static PaperwallSet LIME_PAPERWALL = registerGlassSet("lime_paperwall",  DyeColor.LIME);
    public static PaperwallSet MAGENTA_PAPERWALL = registerGlassSet("magenta_paperwall",  DyeColor.MAGENTA);
    public static PaperwallSet ORANGE_PAPERWALL = registerGlassSet("orange_paperwall",  DyeColor.ORANGE);
    public static PaperwallSet PINK_PAPERWALL = registerGlassSet("pink_paperwall",  DyeColor.PINK);
    public static PaperwallSet PURPLE_PAPERWALL = registerGlassSet("purple_paperwall",  DyeColor.PURPLE);
    public static PaperwallSet RED_PAPERWALL = registerGlassSet("red_paperwall",  DyeColor.RED);
    public static PaperwallSet WHITE_PAPERWALL = registerGlassSet("white_paperwall",  DyeColor.WHITE);
    public static PaperwallSet YELLOW_PAPERWALL = registerGlassSet("yellow_paperwall",  DyeColor.YELLOW);

    public static PaperwallSet[] paperwallSets = new PaperwallSet[]{
            BLACK_PAPERWALL,
            BLUE_PAPERWALL,
            BROWN_PAPERWALL,
            CYAN_PAPERWALL,
            GRAY_PAPERWALL,
            GREEN_PAPERWALL,
            LIGHT_BLUE_PAPERWALL,
            LIGHT_GRAY_PAPERWALL,
            LIME_PAPERWALL,
            MAGENTA_PAPERWALL,
            ORANGE_PAPERWALL,
            PINK_PAPERWALL,
            PURPLE_PAPERWALL,
            RED_PAPERWALL,
            WHITE_PAPERWALL,
            YELLOW_PAPERWALL,
    };

    public record PaperwallSet(DeferredBlock<StainedGlassPaneBlock> pane){


    }

    public static PaperwallSet registerGlassSet(String name, DyeColor color) {


        DeferredBlock<StainedGlassPaneBlock> pane = registerBlock(name, () -> new StainedGlassPaneBlock(color,
                    BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.HAT).strength(0.3f).sound(SoundType.WOOL)
                            .noOcclusion()));
        

        return new PaperwallSet(pane);
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
