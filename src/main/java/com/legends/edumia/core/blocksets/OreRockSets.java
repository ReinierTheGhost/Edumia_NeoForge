package com.legends.edumia.core.blocksets;

import com.legends.edumia.Edumia;
import com.legends.edumia.core.ItemLoader;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.Supplier;

public class OreRockSets {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Edumia.MOD_ID);

    public static final float STONE_STRENGTH = 2.0f;
    public static final float DEEPSLATE_STRENGTH = 2.5f;
    public static final float STRONG_STONE_STRENGTH = 3.0f;
    public static final float HEAVY_STONE_STRENGTH = 4.0f;

    public static OreRockSet STONE = registerOreSet("", STONE_STRENGTH,
            List.of(ORES.TIN_ORE, ORES.SILVER_ORE), Blocks.STONE);

    public static OreRockSet DEEPSLATE = registerOreSet("deepslate_", DEEPSLATE_STRENGTH,
            List.of(ORES.TIN_ORE, ORES.SILVER_ORE), Blocks.DEEPSLATE);

    public static OreRockSet[] sets = new OreRockSet[] {
            STONE,
            DEEPSLATE,
    };


    public record OreRockSet(DeferredBlock<Block> coal_ore, DeferredBlock<Block> copper_ore, DeferredBlock<Block> tin_ore,
                             DeferredBlock<Block> silver_ore, DeferredBlock<Block> gold_ore,
                             DeferredBlock<Block> iron_ore, Block origin) {
    }

    public static OreRockSet registerOreSet(String rockName, float strength_mult, List<ORES> ores, Block origin) {

        DeferredBlock<Block> coal_ore = null;
        DeferredBlock<Block> copper_ore = null;
        DeferredBlock<Block> tin_ore = null;
        DeferredBlock<Block> silver_ore = null;
        DeferredBlock<Block> gold_ore = null;
        DeferredBlock<Block> iron_ore = null;

        if(ores.contains(ORES.COAL_ORE)){
            coal_ore = registerBlock(
                    rockName + "coal_ore", () -> new DropExperienceBlock(UniformInt.of(0, 2),
                            BlockBehaviour.Properties.ofFullCopy(Blocks.COAL_ORE).strength(
                            STONE_STRENGTH * strength_mult, 3*strength_mult).requiresCorrectToolForDrops()));
        }

        if(ores.contains(ORES.COPPER_ORE)) {
            copper_ore = registerBlock(
                    rockName + "copper_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE).strength(
                            STONE_STRENGTH * strength_mult, 3*strength_mult).requiresCorrectToolForDrops()));
        }

        if(ores.contains(ORES.TIN_ORE)) {
            tin_ore = registerBlock(
                    rockName + "tin_ore" ,() ->  new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE).strength(
                            STONE_STRENGTH * strength_mult, 3*strength_mult).requiresCorrectToolForDrops()));
        }

        if(ores.contains(ORES.SILVER_ORE)) {
            silver_ore = registerBlock(
                    rockName + "silver_ore", () ->  new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE).strength(
                            STONE_STRENGTH * strength_mult, 3*strength_mult).requiresCorrectToolForDrops()));
        }

        if(ores.contains(ORES.GOLD_ORE)) {
            gold_ore = registerBlock(
                    rockName + "gold_ore", () ->  new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_ORE).strength(
                            STONE_STRENGTH * strength_mult, 3*strength_mult).requiresCorrectToolForDrops()));
        }

        if(ores.contains(ORES.IRON_ORE)) {
            iron_ore = registerBlock(
                    rockName + "iron_ore", () ->  new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE).strength(
                            STONE_STRENGTH * strength_mult, 3*strength_mult).requiresCorrectToolForDrops()));
        }


        return new OreRockSet(coal_ore, copper_ore, tin_ore, silver_ore, gold_ore, iron_ore, origin);
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

    enum ORES{
        COAL_ORE,
        COPPER_ORE,
        TIN_ORE,
        SILVER_ORE,
        GOLD_ORE,
        IRON_ORE,
        ;
    }

}
