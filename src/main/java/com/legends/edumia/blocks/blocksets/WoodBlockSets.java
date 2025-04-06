package com.legends.edumia.blocks.blocksets;

import com.legends.edumia.Edumia;
import com.legends.edumia.blocks.AxialSlabBlock;
import com.legends.edumia.core.ItemLoader;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class WoodBlockSets {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Edumia.MOD_ID);
    public static final float WOOD_STRENGTH = 2f;
    public static final float PLATE_BUTTON_STRENGTH = 0.5f;
    public static final float LEAVES_STRENGTH = 0.1f;

    public static SimpleBlockSet APPLE = registerWoodSet("apple", WOOD_STRENGTH, true);
    public static SimpleBlockSet ASPEN = registerWoodSet("aspen", WOOD_STRENGTH, true);
    public static SimpleBlockSet BANANA = registerWoodSet("banana", WOOD_STRENGTH, true);
    public static SimpleBlockSet BEECH = registerWoodSet("beech", WOOD_STRENGTH, true);
    public static SimpleBlockSet BLACKTHORN = registerWoodSet("blackthorn", WOOD_STRENGTH, true);
    public static SimpleBlockSet BLACK_OAK = registerWoodSet("black_oak", WOOD_STRENGTH, false);

    public static SimpleBlockSet CEDAR = registerWoodSet("cedar", WOOD_STRENGTH, true);
    public static SimpleBlockSet CHARRED = registerWoodSet("charred", WOOD_STRENGTH, false);
    public static SimpleBlockSet CHERRY = registerWoodSet("cherry", WOOD_STRENGTH, true);
    public static SimpleBlockSet CYPRESS = registerWoodSet("cypress", WOOD_STRENGTH, true);
    public static SimpleBlockSet DRAGON_BLOOD = registerWoodSet("dragon_blood", WOOD_STRENGTH, true);

    public static SimpleBlockSet FIR = registerWoodSet("fir", WOOD_STRENGTH, true);
    public static SimpleBlockSet GHOST_GUM = registerWoodSet("ghost_gum", WOOD_STRENGTH, false);
    public static SimpleBlockSet GREEN_OAK = registerWoodSet("green_oak", WOOD_STRENGTH, true);
    public static SimpleBlockSet HOLLY = registerWoodSet("holly", WOOD_STRENGTH, false);
    public static SimpleBlockSet LARCH = registerWoodSet("larch", WOOD_STRENGTH, true);
    public static SimpleBlockSet MAHOGANY = registerWoodSet("mahogany", WOOD_STRENGTH, true);
    public static SimpleBlockSet MANGO = registerWoodSet("mango", WOOD_STRENGTH, true);
    public static SimpleBlockSet MAPLE = registerWoodSet("maple", WOOD_STRENGTH, false);
    public static SimpleBlockSet PALM = registerWoodSet("palm", WOOD_STRENGTH, true);
    public static SimpleBlockSet PEAR = registerWoodSet("pear", WOOD_STRENGTH, true);
    public static SimpleBlockSet PINE = registerWoodSet("pine", WOOD_STRENGTH, true);
    public static SimpleBlockSet RED_OAK = registerWoodSet("red_oak", WOOD_STRENGTH, true);
    public static SimpleBlockSet REDWOOD = registerWoodSet("redwood", WOOD_STRENGTH, true);
    public static SimpleBlockSet SILVER_SPRUCE = registerWoodSet("silver_spruce", WOOD_STRENGTH, true);
    public static SimpleBlockSet WHITE_ASH = registerWoodSet("white_ash", WOOD_STRENGTH, true);
    public static SimpleBlockSet WILLOW = registerWoodSet("willow", WOOD_STRENGTH, true);


    public static SimpleVanillaBlocks OAK = registerBeams("oak", WOOD_STRENGTH);
    public static SimpleVanillaBlocks ACACIA = registerBeams("acacia", WOOD_STRENGTH);
    public static SimpleVanillaBlocks BIRCH = registerBeams("birch", WOOD_STRENGTH);
    public static SimpleVanillaBlocks SPRUCE = registerBeams("spruce", WOOD_STRENGTH);
    public static SimpleVanillaBlocks DARK_OAK = registerBeams("dark_oak", WOOD_STRENGTH);
    public static SimpleVanillaBlocks JUNGLE = registerBeams("jungle", WOOD_STRENGTH);



    public static SimpleBlockSet[] sets = new SimpleBlockSet[] {
            APPLE,
            ASPEN,
            BANANA,
            BEECH,
            BLACK_OAK,
            BLACKTHORN,
            CEDAR,
            CHERRY,
            CYPRESS,
            CHARRED,
            DRAGON_BLOOD,
            FIR,
            GHOST_GUM,
            GREEN_OAK,
            HOLLY,
            LARCH,
            MAHOGANY,
            MANGO,
            MAPLE,
            PALM,
            PEAR,
            PINE,
            RED_OAK,
            REDWOOD,
            SILVER_SPRUCE,
            WHITE_ASH,
            WILLOW

    };

    public static SimpleVanillaBlocks[] beams = new SimpleVanillaBlocks[]{
            OAK,
            ACACIA,
            SPRUCE,
            BIRCH,
            JUNGLE,
            DARK_OAK,
    };

    public record SimpleBlockSet(DeferredBlock<Block> leaves, DeferredBlock<RotatedPillarBlock> log, DeferredBlock<RotatedPillarBlock> wood,
                                 DeferredBlock<StairBlock> woodStairs, DeferredBlock<AxialSlabBlock> woodSlab, DeferredBlock<WallBlock> woodWall,
                                 DeferredBlock<FenceBlock> woodFence, DeferredBlock<RotatedPillarBlock> strippedLog, DeferredBlock<RotatedPillarBlock> strippedWood,
                                 DeferredBlock<StairBlock> strippedWoodStairs, DeferredBlock<AxialSlabBlock> strippedWoodSlab,
                                 DeferredBlock<WallBlock> strippedWoodWall, DeferredBlock<FenceBlock> strippedWoodFence,
                                 DeferredBlock<Block> planks, DeferredBlock<AxialSlabBlock> planksSlab, DeferredBlock<StairBlock> planksStairs,
                                 DeferredBlock<FenceBlock> planksFence, DeferredBlock<FenceGateBlock> planksGate,
                                 DeferredBlock<PressurePlateBlock> pressurePlate, DeferredBlock<ButtonBlock> button, DeferredBlock<RotatedPillarBlock> beam,
                                 DeferredBlock<DoorBlock> door, DeferredBlock<TrapDoorBlock> trapdoor) {
    }

    public record SimpleVanillaBlocks(DeferredBlock<RotatedPillarBlock> beam){}

    private static SimpleVanillaBlocks registerBeams(String name, float strength){
        DeferredBlock<RotatedPillarBlock> beam = registerBlock(name + "_beam",
                () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(strength)
                .sound(SoundType.WOOD)));

         return new SimpleVanillaBlocks(beam);
    }


    private static SimpleBlockSet registerWoodSet(String name, float strength, boolean hasLeaves) {
        DeferredBlock<Block> leaves = null;
        if(hasLeaves) {
            leaves = registerBlock(name + "_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
                    .strength(LEAVES_STRENGTH).sound(SoundType.GRASS)));
        }
        DeferredBlock<RotatedPillarBlock> log = registerBlock(name + "_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(
                Blocks.OAK_LOG).strength(strength).sound(SoundType.WOOD)));

        DeferredBlock<RotatedPillarBlock> wood = registerBlock(name + "_wood", () -> new RotatedPillarBlock(
                BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(strength).sound(SoundType.WOOD)));

        DeferredBlock<StairBlock> woodStairs = registerBlock(name + "_wood_stairs",
                () -> new StairBlock(wood.get().defaultBlockState(),
                BlockBehaviour.Properties.ofFullCopy(wood.get()).strength(strength).sound(SoundType.WOOD)));
        DeferredBlock<AxialSlabBlock> woodSlab = registerBlock(name + "_wood_slab",
                () -> new AxialSlabBlock(wood.get()));
        DeferredBlock<WallBlock> woodWall = registerBlock(name + "_wood_wall",
                () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(wood.get())
                .strength(strength).sound(SoundType.WOOD)));
        DeferredBlock<FenceBlock> woodFence = registerBlock(name + "_wood_fence",
                () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(wood.get())
                .strength(strength).sound(SoundType.WOOD)));

        DeferredBlock<RotatedPillarBlock> strippedLog = registerBlock("stripped_" + name + "_log", () ->
                new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
                        .strength(strength).sound(SoundType.WOOD)));

        DeferredBlock<RotatedPillarBlock> strippedWood = registerBlock("stripped_" + name + "_wood", () ->
                new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)
                        .strength(strength).sound(SoundType.WOOD)));

        DeferredBlock<StairBlock> strippedWoodStairs = registerBlock("stripped_" + name + "_wood_stairs", () ->
                new StairBlock(wood.get().defaultBlockState(),
                BlockBehaviour.Properties.ofFullCopy(wood.get()).strength(strength).sound(SoundType.WOOD)));
        DeferredBlock<AxialSlabBlock> strippedWoodSlab = registerBlock("stripped_" + name + "_wood_slab", () ->
                new AxialSlabBlock(strippedWood.get()));
        DeferredBlock<WallBlock> strippedWoodWall = registerBlock("stripped_" + name + "_wood_wall", () ->
                new WallBlock(BlockBehaviour.Properties.ofFullCopy(wood.get()).strength(strength).sound(SoundType.WOOD)));
        DeferredBlock<FenceBlock> strippedWoodFence = registerBlock("stripped_" + name + "_wood_fence", () ->
                new FenceBlock(BlockBehaviour.Properties.ofFullCopy(wood.get()).strength(strength).sound(SoundType.WOOD)));

        DeferredBlock<Block> planks = registerBlock(name + "_planks", () ->
                new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(strength).sound(SoundType.WOOD)));

        DeferredBlock<AxialSlabBlock> slab = registerBlock(name + "_slab", () -> new AxialSlabBlock(planks));

        DeferredBlock<StairBlock> stairs = registerBlock(name + "_stairs", () -> new StairBlock(planks.get().defaultBlockState(),
                BlockBehaviour.Properties.ofFullCopy(planks.get()).strength(strength).sound(SoundType.WOOD)));

        DeferredBlock<FenceBlock> fence = registerBlock(name + "_fence", () ->
                new FenceBlock(BlockBehaviour.Properties.ofFullCopy(planks.get())
                .strength(strength).sound(SoundType.WOOD)));

        DeferredBlock<FenceGateBlock> gate = registerBlock(name + "_fence_gate", () ->
                new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.ofFullCopy(planks.get())
                .strength(strength).sound(SoundType.WOOD)));

        DeferredBlock<ButtonBlock> button = registerBlock(name + "_button", () ->
                new ButtonBlock(BlockSetType.OAK, 5, BlockBehaviour.Properties.ofFullCopy(planks.get())
                        .strength(PLATE_BUTTON_STRENGTH)
                .sound(SoundType.WOOD)));

        DeferredBlock<PressurePlateBlock> pressurePlate = registerBlock(name + "_pressure_plate", () ->
                new PressurePlateBlock(BlockSetType.OAK,
                BlockBehaviour.Properties.ofFullCopy(planks.get()).strength(PLATE_BUTTON_STRENGTH).sound(SoundType.WOOD)));

        DeferredBlock<RotatedPillarBlock> beam = registerBlock(name + "_beam", () ->
                new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(strength)
                .sound(SoundType.WOOD)));

        DeferredBlock<DoorBlock> door = registerBlock(name + "_door", () ->
                new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(planks.get())
                .noOcclusion()));

        DeferredBlock<TrapDoorBlock> trapdoor = registerBlock(name + "_trapdoor", () ->
                new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(planks.get()).noOcclusion()));

        return new SimpleBlockSet(leaves, log, wood, woodStairs, woodSlab, woodWall,woodFence,
                strippedLog, strippedWood, strippedWoodStairs, strippedWoodSlab, strippedWoodWall, strippedWoodFence, planks,
                slab, stairs, fence, gate, pressurePlate, button, beam, door, trapdoor);
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
