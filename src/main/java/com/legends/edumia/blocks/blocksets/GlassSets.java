package com.legends.edumia.blocks.blocksets;

import com.legends.edumia.Edumia;
import com.legends.edumia.core.ItemLoader;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class GlassSets {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Edumia.MOD_ID);
    public static GlassSet FINE_GLASS = registerGlassSet("fine_glass", false, null);
    public static GlassSet BLACK_FINE_GLASS = registerGlassSet("black_fine_glass", true, DyeColor.BLACK);
    public static GlassSet BLUE_FINE_GLASS = registerGlassSet("blue_fine_glass", true, DyeColor.BLUE);
    public static GlassSet BROWN_FINE_GLASS = registerGlassSet("brown_fine_glass", true, DyeColor.BROWN);
    public static GlassSet CYAN_FINE_GLASS = registerGlassSet("cyan_fine_glass", true, DyeColor.CYAN);
    public static GlassSet GRAY_FINE_GLASS = registerGlassSet("gray_fine_glass", true, DyeColor.GRAY);
    public static GlassSet GREEN_FINE_GLASS = registerGlassSet("green_fine_glass", true, DyeColor.GREEN);
    public static GlassSet LIGHT_BLUE_FINE_GLASS = registerGlassSet("light_blue_fine_glass", true, DyeColor.LIGHT_BLUE);
    public static GlassSet LIGHT_GRAY_FINE_GLASS = registerGlassSet("light_gray_fine_glass", true, DyeColor.LIGHT_GRAY);
    public static GlassSet LIME_FINE_GLASS = registerGlassSet("lime_fine_glass", true, DyeColor.LIME);
    public static GlassSet MAGENTA_FINE_GLASS = registerGlassSet("magenta_fine_glass", true, DyeColor.MAGENTA);
    public static GlassSet ORANGE_FINE_GLASS = registerGlassSet("orange_fine_glass", true, DyeColor.ORANGE);
    public static GlassSet PINK_FINE_GLASS = registerGlassSet("pink_fine_glass", true, DyeColor.PINK);
    public static GlassSet PURPLE_FINE_GLASS = registerGlassSet("purple_fine_glass", true, DyeColor.PURPLE);
    public static GlassSet RED_FINE_GLASS = registerGlassSet("red_fine_glass", true, DyeColor.RED);
    public static GlassSet WHITE_FINE_GLASS = registerGlassSet("white_fine_glass", true, DyeColor.WHITE);
    public static GlassSet YELLOW_FINE_GLASS = registerGlassSet("yellow_fine_glass", true, DyeColor.YELLOW);



    public static GlassSet[] glassSets = new GlassSet[]{
            FINE_GLASS,
            BLACK_FINE_GLASS,
            BLUE_FINE_GLASS,
            BROWN_FINE_GLASS,
            CYAN_FINE_GLASS,
            GRAY_FINE_GLASS,
            GREEN_FINE_GLASS,
            LIGHT_BLUE_FINE_GLASS,
            LIGHT_GRAY_FINE_GLASS,
            LIME_FINE_GLASS,
            MAGENTA_FINE_GLASS,
            ORANGE_FINE_GLASS,
            PINK_FINE_GLASS,
            PURPLE_FINE_GLASS,
            RED_FINE_GLASS,
            WHITE_FINE_GLASS,
            YELLOW_FINE_GLASS,

    };

    public record GlassSet(DeferredBlock<Block> block, DeferredBlock<IronBarsBlock> pane){

    }

    public static GlassSet registerGlassSet(String name, boolean stained, DyeColor color) {
        DeferredBlock<Block> block = null;
        DeferredBlock<IronBarsBlock> pane = null;
        if (stained) {
            block = registerBlock(name, () -> new StainedGlassBlock(color,
                    BlockBehaviour.Properties.of()
                            .instrument(NoteBlockInstrument.HAT).strength(0.3F).sound(SoundType.GLASS).noOcclusion()
                            .isValidSpawn(Blocks::never)));

            pane = registerBlock(name + "_pane", () -> new StainedGlassPaneBlock(color,
                    BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.HAT).strength(0.3f).sound(SoundType.GLASS)
                            .noOcclusion()));
    } else{
            block = registerBlock(name, () -> new TransparentBlock(BlockBehaviour.Properties.of()
                    .instrument(NoteBlockInstrument.HAT).strength(0.3F).sound(SoundType.GLASS)
                    .noOcclusion().isValidSpawn(Blocks::never)));

            pane = registerBlock(name + "_pane", () -> new IronBarsBlock(
                    BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.HAT).strength(0.3f).sound(SoundType.GLASS)
                            .noOcclusion()));
        }

        return new GlassSet(block, pane);
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
