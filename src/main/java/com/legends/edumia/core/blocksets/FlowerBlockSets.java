package com.legends.edumia.core.blocksets;

import com.legends.edumia.Edumia;
import com.legends.edumia.core.ItemLoader;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

@SuppressWarnings("deprecation")
public class FlowerBlockSets {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Edumia.MOD_ID);

    public static FlowerSet ASPHODEL = registerFlowerSets("asphodel", MobEffects.SATURATION,false);
    public static FlowerSet BLUE_DELPHINIUM = registerFlowerSets("blue_delphinium", MobEffects.SATURATION,false);
    public static FlowerSet BLUEBELL = registerFlowerSets("bluebell", MobEffects.SATURATION,false);
    public static FlowerSet CALLA_LILY = registerFlowerSets("calla_lily", MobEffects.SATURATION,false);
    public static FlowerSet CELSEMIUM = registerFlowerSets("celsemium", MobEffects.SATURATION,false);
    public static FlowerSet CHRYS_BLUE = registerFlowerSets("chrys_blue", MobEffects.SATURATION,false);
    public static FlowerSet CHRYS_ORANGE = registerFlowerSets("chrys_orange", MobEffects.SATURATION,false);
    public static FlowerSet CHRYS_PINK = registerFlowerSets("chrys_pink", MobEffects.SATURATION,false);
    public static FlowerSet CHRYS_WHITE = registerFlowerSets("chrys_white", MobEffects.SATURATION,false);
    public static FlowerSet CHRYS_YELLOW = registerFlowerSets("chrys_yellow", MobEffects.SATURATION,false);
    public static FlowerSet CROCUS = registerFlowerSets("crocus", MobEffects.SATURATION,false);
    public static FlowerSet DAISY = registerFlowerSets("daisy", MobEffects.SATURATION,false);
    public static FlowerSet DELPHINIUM = registerFlowerSets("delphinium", MobEffects.SATURATION,false);
    public static FlowerSet FLAX_FLOWERS = registerFlowerSets("flax_flowers", MobEffects.SATURATION,false);
    public static FlowerSet FOXGLOVE_ORANGE = registerFlowerSets("foxglove_orange", MobEffects.SATURATION,false);
    public static FlowerSet FOXGLOVE_PINK = registerFlowerSets("foxglove_pink", MobEffects.SATURATION,false);
    public static FlowerSet FOXGLOVE_RED = registerFlowerSets("foxglove_red", MobEffects.SATURATION,false);
    public static FlowerSet FOXGLOVE_WHITE = registerFlowerSets("foxglove_white", MobEffects.SATURATION,false);
    public static FlowerSet GERBERA_RED = registerFlowerSets("gerbera_red", MobEffects.SATURATION,false);
    public static FlowerSet GENSAI_ORCHID = registerFlowerSets("gensai_orchid", MobEffects.SATURATION,false);
    public static FlowerSet GERBERA_YELLOW = registerFlowerSets("gerbera_yellow", MobEffects.SATURATION,false);
    public static FlowerSet HEATHER_BUSH = registerFlowerSets("heather_bush", MobEffects.SATURATION,false);
    public static FlowerSet LAVENDER = registerFlowerSets("lavender", MobEffects.SATURATION,false);
    public static FlowerSet MARIGOLD = registerFlowerSets("marigold", MobEffects.SATURATION,false);
    public static FlowerSet PINK_ANEMONE = registerFlowerSets("pink_anemone", MobEffects.SATURATION,false);
    public static FlowerSet SIMBLELMYNE = registerFlowerSets("simbelmyne", MobEffects.SATURATION,false);
    public static FlowerSet TUBEROSE = registerFlowerSets("tuberose", MobEffects.SATURATION,false);

    public static FlowerSet YELLOW_IRIS = registerFlowerSets("yellow_iris", MobEffects.SATURATION,true);
    public static FlowerSet FLAME_OF_THE_SOUTH = registerFlowerSets("flame_of_the_south", MobEffects.SATURATION,true);
    public static FlowerSet HIBISCUS = registerFlowerSets("hibiscus", MobEffects.SATURATION,true);

    public static FlowerSet PARASOL_MUSHROOM_1 = registerFlowerSets("parasol_mushroom_01", MobEffects.SATURATION, false);
    public static FlowerSet PARASOL_MUSHROOM_2 = registerFlowerSets("parasol_mushroom_02", MobEffects.SATURATION, false);
    public static FlowerSet parasol_mushroom_tall = registerFlowerSets("parasol_mushroom_tall", MobEffects.SATURATION, true);

    public static FlowerSet[] flowerSets = new FlowerSet[]{
            ASPHODEL,
            BLUE_DELPHINIUM,
            BLUEBELL,
            CALLA_LILY,
            CELSEMIUM,
            CHRYS_BLUE,
            CHRYS_ORANGE,
            CHRYS_PINK,
            CHRYS_WHITE,
            CHRYS_YELLOW,
            CROCUS,
            DAISY,
            DELPHINIUM,
            FLAX_FLOWERS,
            FOXGLOVE_ORANGE,
            FOXGLOVE_PINK,
            FOXGLOVE_RED,
            FOXGLOVE_WHITE,
            GERBERA_RED,
            GENSAI_ORCHID,
            GERBERA_YELLOW,
            HEATHER_BUSH,
            LAVENDER,
            MARIGOLD,
            PINK_ANEMONE,
            SIMBLELMYNE,
            TUBEROSE,
            YELLOW_IRIS,
            FLAME_OF_THE_SOUTH,
            HIBISCUS,
            PARASOL_MUSHROOM_1,
            PARASOL_MUSHROOM_2,
            parasol_mushroom_tall
    };

    public record FlowerSet(DeferredBlock<FlowerBlock> flower, DeferredBlock<Block> pottedFlower, DeferredBlock<TallFlowerBlock> tallFlower){}


    public static FlowerSet registerFlowerSets(String name, Holder<MobEffect> effect, boolean isTallFlower){
        DeferredBlock<FlowerBlock> flower;
        DeferredBlock<Block> pottedFlower;
        DeferredBlock<TallFlowerBlock> tallFlower;

        if (isTallFlower){
            flower = null;
            pottedFlower = null;
            tallFlower = registerBlock(name, () -> new TallFlowerBlock(BlockBehaviour.Properties
                    .of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS)
                    .offsetType(BlockBehaviour.OffsetType.XZ).ignitedByLava().pushReaction(PushReaction.DESTROY)));
        }else {
            flower = registerBlock(name, () -> new FlowerBlock(effect, 7, BlockBehaviour.Properties.ofFullCopy(Blocks.ALLIUM)
                    .noOcclusion().noCollission()));
            pottedFlower = registerBlock("potted_" + name, () -> flowerPot(flower.get()));


            tallFlower = null;
        }

        return new FlowerSet(flower, pottedFlower, tallFlower);
    }

    private static Block flowerPot(Block potted) {
        return new FlowerPotBlock(potted, BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY));
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
