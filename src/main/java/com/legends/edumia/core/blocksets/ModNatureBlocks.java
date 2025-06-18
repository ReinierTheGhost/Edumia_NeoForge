package com.legends.edumia.core.blocksets;

import com.legends.edumia.Edumia;
import com.legends.edumia.blocks.EdumiaLeavesBlock;
import com.legends.edumia.blocks.plants.DriedReedsBlock;
import com.legends.edumia.blocks.plants.ReedsBlock;
import com.legends.edumia.blocks.trees.BlackOakLeavesBlock;
import com.legends.edumia.core.ItemLoader;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.legends.edumia.core.blocksets.WoodBlockSets.LEAVES_STRENGTH;

@SuppressWarnings("deprecation")
public class ModNatureBlocks {

    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Edumia.MOD_ID);

    public static final DeferredBlock<Block> GHOST_GUM_LEAVES = ModNatureBlocks.registerBlock("ghost_gum_leaves", () ->
            new EdumiaLeavesBlock());
    public static final DeferredBlock<Block> HOLLY_LEAVES = ModNatureBlocks.registerBlock("holly_leaves", () ->
            new EdumiaLeavesBlock());
    public static final DeferredBlock<Block> MAPLE_LEAVES = ModNatureBlocks.registerBlock("maple_leaves", () ->
            new EdumiaLeavesBlock());
    public static final DeferredBlock<Block> BLACK_OAK_LEAVES = ModNatureBlocks.registerBlock("black_oak_leaves", () ->
            new BlackOakLeavesBlock());
    public static final DeferredBlock<Block> APPLE_LEAVES_RED = ModNatureBlocks.registerBlock("apple_leaves_red", () ->
            new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
                    .strength(LEAVES_STRENGTH).sound(SoundType.GRASS)));
    public static final DeferredBlock<Block> APPLE_LEAVES_GREEN = ModNatureBlocks.registerBlock("apple_leaves_green", () ->
            new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
                    .strength(LEAVES_STRENGTH).sound(SoundType.GRASS)));
    public static final DeferredBlock<Block> PEAR_LEAVES_FRUIT = ModNatureBlocks.registerBlock("pear_leaves_fruit", () ->
            new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
                    .strength(LEAVES_STRENGTH).sound(SoundType.GRASS)));
    public static final DeferredBlock<Block> CHERRY_LEAVES_FRUIT = ModNatureBlocks.registerBlock("cherry_leaves_fruit", () ->
            new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
                    .strength(LEAVES_STRENGTH).sound(SoundType.GRASS)));

    public static final DeferredBlock<Block> REEDS = registerBlock("reeds", () -> new ReedsBlock());

    public static final DeferredBlock<Block> PAPYRUS = registerBlock("papyrus", () -> new ReedsBlock());

    public static final DeferredBlock<Block> DRIED_REEDS = registerBlock("dried_reeds", () -> new DriedReedsBlock());


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
