package com.legends.edumia.core.blocksets;

import com.legends.edumia.Edumia;
import com.legends.edumia.blocks.AxialSlabBlock;
import com.legends.edumia.blocks.trees.VariantSaplingBlock;
import com.legends.edumia.core.ItemLoader;
import com.legends.edumia.core.TagLoader;
import com.legends.edumia.world.trees.ModTreeGrowers;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.checkerframework.checker.units.qual.K;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;


public class WoodBlockSets {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Edumia.MOD_ID);
    public static final float WOOD_STRENGTH = 2f;
    public static final float PLATE_BUTTON_STRENGTH = 0.5f;
    public static final float LEAVES_STRENGTH = 0.1f;

    public static SimpleBlockSet ALMOND = registerWoodSet("almond", WOOD_STRENGTH, true, true,
            false, ModTreeGrowers.ALMOND, List.of(), TagLoader.Items.ALMOND_LOGS, TagLoader.Blocks.ALMOND_LOGS);
    public static SimpleBlockSet APPLE = registerWoodSet("apple", WOOD_STRENGTH, true, true,
            false, ModTreeGrowers.APPLE, List.of(), TagLoader.Items.APPLE_LOGS, TagLoader.Blocks.APPLE_LOGS);
    public static SimpleBlockSet ASPEN = registerWoodSet("aspen", WOOD_STRENGTH, true, true,
            false, ModTreeGrowers.ASPEN, List.of(), TagLoader.Items.ASPEN_LOGS, TagLoader.Blocks.ASPEN_LOGS);
    public static SimpleBlockSet BANANA = registerWoodSet("banana", WOOD_STRENGTH, true, true,
            false, ModTreeGrowers.BANANA, List.of(), TagLoader.Items.BANANA_LOGS, TagLoader.Blocks.BANANA_LOGS);
    public static SimpleBlockSet BEECH = registerWoodSet("beech", WOOD_STRENGTH, true, true,
            false, ModTreeGrowers.APPLE, List.of(), TagLoader.Items.BEECH_LOGS, TagLoader.Blocks.BEECH_LOGS);
    public static SimpleBlockSet BLACKTHORN = registerWoodSet("blackthorn", WOOD_STRENGTH, true, true,
            false, ModTreeGrowers.BLACKTHORN, List.of(), TagLoader.Items.BLACKTHORN_LOGS, TagLoader.Blocks.BLACKTHORN_LOGS);
    public static SimpleBlockSet BLACK_OAK = registerWoodSet("black_oak", WOOD_STRENGTH, false, true,
            false, ModTreeGrowers.BLACK_OAK, List.of(), TagLoader.Items.BLACK_OAK_LOGS, TagLoader.Blocks.BLACK_OAK_LOGS);

    public static SimpleBlockSet CEDAR = registerWoodSet("cedar", WOOD_STRENGTH, true, true,
            false, ModTreeGrowers.APPLE, List.of(), TagLoader.Items.CEDAR_LOGS, TagLoader.Blocks.CEDAR_LOGS);
    public static SimpleBlockSet CHARRED = registerWoodSet("charred", WOOD_STRENGTH, false, false,
            false, null, List.of(), TagLoader.Items.CHARRED_LOGS, TagLoader.Blocks.CHARRED_LOGS);
    public static SimpleBlockSet CHERRY = registerWoodSet("cherry", WOOD_STRENGTH, true, true,
            false, ModTreeGrowers.APPLE, List.of(), TagLoader.Items.CHERRY_LOGS, TagLoader.Blocks.CHERRY_LOGS);
    public static SimpleBlockSet CYPRESS = registerWoodSet("cypress", WOOD_STRENGTH, true, true,
            false, ModTreeGrowers.CYPRESS, List.of(), TagLoader.Items.CYPRESS_LOGS, TagLoader.Blocks.CYPRESS_LOGS);
    public static SimpleBlockSet DATE_PALM = registerWoodSet("date_palm", WOOD_STRENGTH, true, true,
            false, ModTreeGrowers.DATE_PALM, List.of(), TagLoader.Items.DATE_PALM_LOGS, TagLoader.Blocks.DATE_PALM_LOGS);
    public static SimpleBlockSet DRAGON_BLOOD = registerWoodSet("dragon_blood", WOOD_STRENGTH, true, true,
            false, ModTreeGrowers.DRAGON_BLOOD, List.of(), TagLoader.Items.DRAGON_BLOOD_LOGS, TagLoader.Blocks.DRAGON_BLOOD_LOGS);

    public static SimpleBlockSet FIR = registerWoodSet("fir", WOOD_STRENGTH, true, true,
            false, ModTreeGrowers.FIR, List.of(), TagLoader.Items.FIR_LOGS, TagLoader.Blocks.FIR_LOGS);
    public static SimpleBlockSet GHOST_GUM = registerWoodSet("ghost_gum", WOOD_STRENGTH, false, true,
            false, ModTreeGrowers.GHOST_GUM, List.of(), TagLoader.Items.GHOST_GUM_LOGS, TagLoader.Blocks.GHOST_GUM_LOGS);
    public static SimpleBlockSet GREEN_OAK = registerWoodSet("green_oak", WOOD_STRENGTH, true, true,
            false, ModTreeGrowers.GREEN_OAK, List.of(), TagLoader.Items.GREEN_OAK_LOGS, TagLoader.Blocks.GREEN_OAK_LOGS);
    public static SimpleBlockSet HOLLY = registerWoodSet("holly", WOOD_STRENGTH, false, true,
            false, ModTreeGrowers.HOLLY, List.of(), TagLoader.Items.HOLLY_LOGS, TagLoader.Blocks.HOLLY_LOGS);
    public static SimpleBlockSet JACARANDA = registerWoodSet("jacaranda", WOOD_STRENGTH, true, true,
            false, ModTreeGrowers.JACARANDA, List.of(), TagLoader.Items.JACARANDA_LOGS, TagLoader.Blocks.JACARANDA_LOGS);
    public static SimpleBlockSet KAPOK = registerWoodSet("kapok", WOOD_STRENGTH, true, true,
            false, ModTreeGrowers.KAPOK, List.of(), TagLoader.Items.KAPOK_LOGS, TagLoader.Blocks.KAPOK_LOGS);
    public static SimpleBlockSet LARCH = registerWoodSet("larch", WOOD_STRENGTH, true, true,
            false, ModTreeGrowers.APPLE, List.of(), TagLoader.Items.LARCH_LOGS, TagLoader.Blocks.LARCH_LOGS);
    public static SimpleBlockSet MAHOGANY = registerWoodSet("mahogany", WOOD_STRENGTH, true, true,
            false, ModTreeGrowers.MAHOGANY, List.of(), TagLoader.Items.MAHOGANY_LOGS, TagLoader.Blocks.MAHOGANY_LOGS);
    public static SimpleBlockSet MANGO = registerWoodSet("mango", WOOD_STRENGTH, true, true,
            false, ModTreeGrowers.MANGO, List.of(), TagLoader.Items.MANGO_LOGS, TagLoader.Blocks.MANGO_LOGS);
    public static SimpleBlockSet MAPLE = registerWoodSet("maple", WOOD_STRENGTH, false, true,
            false, ModTreeGrowers.APPLE, List.of(), TagLoader.Items.MAPLE_LOGS, TagLoader.Blocks.MAPLE_LOGS);
    public static SimpleBlockSet MIRWOODNUT = registerWoodSet("mirwoodnut", WOOD_STRENGTH, true, true,
            false, ModTreeGrowers.MIRWOODNUT, List.of(), TagLoader.Items.MIRWOODNUT_LOGS, TagLoader.Blocks.MIRWOODNUT_LOGS);
    // public static SimpleBlockSet OLIVE = registerWoodSet("olive", WOOD_STRENGTH, true, true,
//         false, ModTreeGrowers.APPLE, List.of(), TagLoader.Items.OLIVE_LOGS, TagLoader.Blocks.OLIVE_LOGS);
    public static SimpleBlockSet PALM = registerWoodSet("palm", WOOD_STRENGTH, true, true,
            false, ModTreeGrowers.PALM, List.of(), TagLoader.Items.PALM_LOGS, TagLoader.Blocks.PALM_LOGS);
    public static SimpleBlockSet PEAR = registerWoodSet("pear", WOOD_STRENGTH, true, true,
            false, ModTreeGrowers.APPLE, List.of(), TagLoader.Items.PEAR_LOGS, TagLoader.Blocks.PEAR_LOGS);
    public static SimpleBlockSet PINE = registerWoodSet("pine", WOOD_STRENGTH, true, true,
            false, ModTreeGrowers.PINE, List.of(), TagLoader.Items.PINE_LOGS, TagLoader.Blocks.PINE_LOGS);
    public static SimpleBlockSet RED_OAK = registerWoodSet("red_oak", WOOD_STRENGTH, true, true,
            false, ModTreeGrowers.RED_OAK, List.of(), TagLoader.Items.RED_OAK_LOGS, TagLoader.Blocks.RED_OAK_LOGS);
    public static SimpleBlockSet REDWOOD = registerWoodSet("redwood", WOOD_STRENGTH, true, true,
            false, ModTreeGrowers.REDWOOD, List.of(), TagLoader.Items.REDWOOD_LOGS, TagLoader.Blocks.REDWOOD_LOGS);
    public static SimpleBlockSet SILVER_SPRUCE = registerWoodSet("silver_spruce", WOOD_STRENGTH, true, true,
            false, ModTreeGrowers.SILVER_SPRUCE, List.of(), TagLoader.Items.SILVER_SPRUCE_LOGS, TagLoader.Blocks.SILVER_SPRUCE_LOGS);
    public static SimpleBlockSet WHITE_ASH = registerWoodSet("white_ash", WOOD_STRENGTH, true, true,
            false, ModTreeGrowers.WHITE_ASH, List.of(), TagLoader.Items.WHITE_ASH_LOGS, TagLoader.Blocks.WHITE_ASH_LOGS);
    public static SimpleBlockSet WILLOW = registerWoodSet("willow", WOOD_STRENGTH, true, true,
            false, ModTreeGrowers.WILLOW, List.of(), TagLoader.Items.WILLOW_LOGS, TagLoader.Blocks.WILLOW_LOGS);


    public static SimpleVanillaBlocks OAK = registerBeams("oak", WOOD_STRENGTH, Blocks.OAK_WOOD, Blocks.OAK_LOG, Blocks.OAK_WOOD);
    public static SimpleVanillaBlocks ACACIA = registerBeams("acacia", WOOD_STRENGTH, Blocks.ACACIA_WOOD, Blocks.ACACIA_LOG, Blocks.ACACIA_WOOD);
    public static SimpleVanillaBlocks BIRCH = registerBeams("birch", WOOD_STRENGTH, Blocks.BIRCH_WOOD, Blocks.BIRCH_LOG, Blocks.BIRCH_WOOD);
    public static SimpleVanillaBlocks SPRUCE = registerBeams("spruce", WOOD_STRENGTH, Blocks.SPRUCE_WOOD, Blocks.SPRUCE_LOG, Blocks.SPRUCE_WOOD);
    public static SimpleVanillaBlocks DARK_OAK = registerBeams("dark_oak", WOOD_STRENGTH, Blocks.DARK_OAK_WOOD, Blocks.DARK_OAK_LOG, Blocks.DARK_OAK_WOOD);
    public static SimpleVanillaBlocks JUNGLE = registerBeams("jungle", WOOD_STRENGTH, Blocks.JUNGLE_WOOD, Blocks.JUNGLE_LOG, Blocks.JUNGLE_WOOD);



    public static SimpleBlockSet[] sets = new SimpleBlockSet[] {
            ALMOND,
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
            DATE_PALM,
            DRAGON_BLOOD,
            FIR,
            GHOST_GUM,
            GREEN_OAK,
            HOLLY,
            JACARANDA,
            KAPOK,
            LARCH,
            MAHOGANY,
            MANGO,
            MAPLE,
            MIRWOODNUT,
//            OLIVE,
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

    public record SimpleBlockSet(DeferredBlock<SaplingBlock> sapling, DeferredBlock<Block> leaves, DeferredBlock<RotatedPillarBlock> log, DeferredBlock<RotatedPillarBlock> wood,
                                 DeferredBlock<StairBlock> woodStairs, DeferredBlock<AxialSlabBlock> woodSlab, DeferredBlock<WallBlock> woodWall,
                                 DeferredBlock<FenceBlock> woodFence, DeferredBlock<RotatedPillarBlock> strippedLog, DeferredBlock<RotatedPillarBlock> strippedWood,
                                 DeferredBlock<StairBlock> strippedWoodStairs, DeferredBlock<AxialSlabBlock> strippedWoodSlab,
                                 DeferredBlock<WallBlock> strippedWoodWall, DeferredBlock<FenceBlock> strippedWoodFence,
                                 DeferredBlock<Block> planks, DeferredBlock<AxialSlabBlock> planksSlab, DeferredBlock<StairBlock> planksStairs,
                                 DeferredBlock<FenceBlock> planksFence, DeferredBlock<FenceGateBlock> planksGate,
                                 DeferredBlock<PressurePlateBlock> pressurePlate, DeferredBlock<ButtonBlock> button, DeferredBlock<RotatedPillarBlock> beam,
                                 DeferredBlock<DoorBlock> door, DeferredBlock<TrapDoorBlock> trapdoor, TagKey<Item> logTag, TagKey<Block> logBlockTag) {
    }

    public record SimpleVanillaBlocks(DeferredBlock<RotatedPillarBlock> beam, DeferredBlock<StairBlock> woodStairs, DeferredBlock<AxialSlabBlock> woodSlab, DeferredBlock<WallBlock> woodWall,
                                      DeferredBlock<FenceBlock> woodFence, Block texture, Block wood){}

    private static SimpleVanillaBlocks registerBeams(String name, float strength, Block copyOf, Block texture, Block wood){

        DeferredBlock<RotatedPillarBlock> beam = registerBlock(name + "_beam",
                () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(strength)
                .sound(SoundType.WOOD)));

        DeferredBlock<StairBlock> woodStairs = registerBlock(name + "_wood_stairs",
                () -> new StairBlock(copyOf.defaultBlockState(),
                        BlockBehaviour.Properties.ofFullCopy(copyOf).strength(strength).sound(SoundType.WOOD)));
        DeferredBlock<AxialSlabBlock> woodSlab = registerBlock(name + "_wood_slab",
                () -> new AxialSlabBlock(copyOf));
        DeferredBlock<WallBlock> woodWall = registerBlock(name + "_wood_wall",
                () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(copyOf)
                        .strength(strength).sound(SoundType.WOOD)));
        DeferredBlock<FenceBlock> woodFence = registerBlock(name + "_wood_fence",
                () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(copyOf)
                        .strength(strength).sound(SoundType.WOOD)));



         return new SimpleVanillaBlocks(beam, woodStairs, woodSlab, woodWall, woodFence, texture, wood);
    }


    private static SimpleBlockSet registerWoodSet(String name, float strength, boolean hasLeaves, boolean hasSapling,
                                                  boolean hasVariantSapling, TreeGrower treeGrower,
                                                  List<ResourceKey<ConfiguredFeature<?, ?>>> treeFeatures, TagKey<Item> logTag, TagKey<Block> logBlockTag) {
        DeferredBlock<SaplingBlock> sapling = null;
        if (hasSapling){
            sapling = registerBlock(name + "_sapling", () -> new SaplingBlock(treeGrower, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
        }
        if (hasVariantSapling){
            sapling = registerVariantSapling(name + "_sapling", treeFeatures);
        }
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

        return new SimpleBlockSet(sapling, leaves, log, wood, woodStairs, woodSlab, woodWall,woodFence,
                strippedLog, strippedWood, strippedWoodStairs, strippedWoodSlab, strippedWoodWall, strippedWoodFence, planks,
                slab, stairs, fence, gate, pressurePlate, button, beam, door, trapdoor, logTag, logBlockTag);
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    public static DeferredBlock<SaplingBlock> registerVariantSapling(String name, List<ResourceKey<ConfiguredFeature<?, ?>>> treeFeatures) {
        List<TreeGrower> saplingGenerators = new ArrayList<>();
        for(ResourceKey<ConfiguredFeature<?,?>> treeFeature : treeFeatures) {
            saplingGenerators.add(new TreeGrower(name, Optional.empty(), Optional.ofNullable(treeFeature),
                    Optional.empty()));
        }

        SaplingBlock saplingBlock = new VariantSaplingBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING), saplingGenerators);

        DeferredBlock<SaplingBlock> resultBlock = registerBlock(name, () -> saplingBlock);
        return resultBlock;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ItemLoader.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
