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


public class NotBrickBuildingSets {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Edumia.MOD_ID);

    public static final float STONE_STRENGTH = 2.0f;

    public static BuildSet POLISHED_DRIPSTONE = registerBuildingSet("polished_dripstone", STONE_STRENGTH, false);
    public static BuildSet HIGH_ELVEN_BRICK_TILING = registerBuildingSet("high_elven_brick_tiling", STONE_STRENGTH, false);
    public static BuildSet DARK_HIGH_ELVEN_BRICK_TILING = registerBuildingSet("dark_high_elven_brick_tiling", STONE_STRENGTH, false);
    public static BuildSet LIGHT_HIGH_ELVEN_BRICK_TILING = registerBuildingSet("light_high_elven_brick_tiling", STONE_STRENGTH, false);
    public static BuildSet CRACKED_GREEN_BASALT = registerBuildingSet("cracked_green_basalt", STONE_STRENGTH, true);

    public static BuildSet[] buildSets = new BuildSet[]{
            POLISHED_DRIPSTONE,
            HIGH_ELVEN_BRICK_TILING,
            DARK_HIGH_ELVEN_BRICK_TILING,
            LIGHT_HIGH_ELVEN_BRICK_TILING,
            CRACKED_GREEN_BASALT
    };

    public record BuildSet(DeferredBlock<Block> block, DeferredBlock<AxialSlabBlock> slab, DeferredBlock<StairBlock> stair, DeferredBlock<WallBlock> wall){
    }

    public static BuildSet registerBuildingSet(String name, float strength, boolean hasWall){

        DeferredBlock<Block> stone = registerBlock(name, () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                .strength(strength).sound(SoundType.STONE).requiresCorrectToolForDrops()));


        DeferredBlock<AxialSlabBlock> slab = registerBlock(name + "_slab", () -> new AxialSlabBlock(stone));

        DeferredBlock<StairBlock> stairs = registerBlock(name + "_stairs", () -> new StairBlock(stone.get().defaultBlockState(),
                BlockBehaviour.Properties.ofFullCopy(stone.get()).strength(strength).sound(SoundType.STONE).requiresCorrectToolForDrops()));

        DeferredBlock<WallBlock> wall = null;
        if (hasWall){
            wall = registerBlock(name + "_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(stone.get())
                    .strength(strength).sound(SoundType.STONE).requiresCorrectToolForDrops()));
        }




        return new BuildSet(stone, slab, stairs, wall);
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
