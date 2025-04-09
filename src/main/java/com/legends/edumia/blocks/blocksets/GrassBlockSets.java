package com.legends.edumia.blocks.blocksets;

import com.legends.edumia.Edumia;
import com.legends.edumia.core.ItemLoader;
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
public class GrassBlockSets {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Edumia.MOD_ID);

    public static GrassSet DRY_BUSH = registerGrassSets("dry_bush", false,false);
    public static GrassSet TALL_DRY_BUSH = registerGrassSets("tall_dry_bush", false,true);
    public static GrassSet TALL_DEAD_BUSH = registerGrassSets("tall_dead_bush", false,true);
    public static GrassSet ARID_GRASS = registerGrassSets("arid_grass", false,false);
    public static GrassSet BLACK_GRASS = registerGrassSets("black_grass", false,false);
    public static GrassSet FLAX_GRASS = registerGrassSets("flax_grass", false,false);
    public static GrassSet BEACH_GRASS = registerGrassSets("beach_grass", false,true);
    public static GrassSet TALL_BEACH_GRASS = registerGrassSets("tall_beach_grass", false,true);
    public static GrassSet FROSTED_GRASS = registerGrassSets("frosted_grass", false,false);
    public static GrassSet TALL_FROSTED_GRASS = registerGrassSets("tall_frosted_grass", false,true);

    public static GrassSet[] grassSets = new GrassSet[]{
            DRY_BUSH,
            TALL_DRY_BUSH,
            TALL_DEAD_BUSH,
            ARID_GRASS,
            BLACK_GRASS,
            FLAX_GRASS,
            BEACH_GRASS,
            TALL_BEACH_GRASS,
            FROSTED_GRASS,
            TALL_FROSTED_GRASS
    };

    public record GrassSet(DeferredBlock<TallGrassBlock> grass, DeferredBlock<Block> pottedGrass, DeferredBlock<DoublePlantBlock> tallGrass){}


    public static GrassSet registerGrassSets(String name, boolean hasPot, boolean isTall){
        DeferredBlock<TallGrassBlock> grass;
        DeferredBlock<Block> potted = null;
        DeferredBlock<DoublePlantBlock> tallGrass = null;
        if (isTall){
            grass = null;
            tallGrass = registerBlock(name, () -> new DoublePlantBlock(
                            BlockBehaviour.Properties.of()
                                    .mapColor(MapColor.PLANT)
                                    .replaceable()
                                    .noCollission()
                                    .instabreak()
                                    .sound(SoundType.GRASS)
                                    .offsetType(BlockBehaviour.OffsetType.XZ)
                                    .ignitedByLava()
                                    .pushReaction(PushReaction.DESTROY)));
        } else {
            grass = registerBlock(name, () -> new TallGrassBlock(
                            BlockBehaviour.Properties.of()
                                    .mapColor(MapColor.PLANT)
                                    .replaceable()
                                    .noCollission()
                                    .instabreak()
                                    .sound(SoundType.GRASS)
                                    .offsetType(BlockBehaviour.OffsetType.XYZ)
                                    .ignitedByLava()
                                    .pushReaction(PushReaction.DESTROY)));
            if (hasPot){
                potted = registerBlock("potted_" + name, () -> flowerPot(grass.get()));
            }
        }
        return new GrassSet(grass, potted, tallGrass);
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
