package com.legends.edumia.core;

import com.legends.edumia.Edumia;
import com.legends.edumia.blocks.blocksets.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class CreativeTabLoader {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Edumia.MOD_ID);


    public static final Supplier<CreativeModeTab> EDUMIA_BUILDING_BLOCKS = CREATIVE_MODE_TAB.register("edumia_building_blocks",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(BuildingSets.GREEN_BASALT_BRICKS.block().get()))
                    .title(Component.translatable("creativetab.edumia_building_blocks"))
                    .displayItems((displayParameters, entries) -> {
                        for (BuildingSets.BuildSet set : BuildingSets.buildSets) {
                            entries.accept(set.block().get());
                            entries.accept(set.slab().get());
                            entries.accept(set.stair().get());
                            entries.accept(set.wall().get());
                            entries.accept(set.arrowSlit().get());
                            if (set.pillar() != null){
                                entries.accept(set.pillar().get());
                                entries.accept(set.pillarSlab().get());
                            }
                            entries.accept(set.smallArch().get());
                            entries.accept(set.twoMeterArch().get());
                            entries.accept(set.roundArch().get());
                            entries.accept(set.segmentalArch().get());
                            entries.accept(set.gothicArch().get());
                            entries.accept(set.balustrade().get());
                        }

                        for (ClayTilingSets.ClayTilingSet set : ClayTilingSets.sets) {
                            entries.accept(set.block().get());
                            entries.accept(set.slab().get());
                            entries.accept(set.stairs().get());
                            entries.accept(set.corner().get());
                        }

                        for (NotBrickBuildingSets.BuildSet set : NotBrickBuildingSets.buildSets) {
                            entries.accept(set.block().get());
                            entries.accept(set.slab().get());
                            entries.accept(set.stair().get());
                            if (set.wall() != null){
                                entries.accept(set.wall().get());
                            }
                        }

//                        entries.accept(BlockLoader.BRICK_PILLAR.get());
//                        entries.accept(BlockLoader.STONE_PILLAR.get());
//                        entries.accept(BlockLoader.SANDSTONE_PILLAR.get());
//
//                        entries.accept(BlockLoader.BLUE_BRICK.get());
//                        entries.accept(BlockLoader.BLUE_BRICK_SLAB.get());
//                        entries.accept(BlockLoader.BLUE_BRICK_STAIRS.get());
//                        entries.accept(BlockLoader.BLUE_BRICK_WALL.get());
//
//                        entries.accept(BlockLoader.CHISELED_HIGH_ELVEN_BRICKS.get());
//
//                        entries.accept(BlockLoader.HIGH_ELVEN_BRICK_TILING.get());
//                        entries.accept(BlockLoader.HIGH_ELVEN_BRICK_TILING_STAIRS.get());
//                        entries.accept(BlockLoader.HIGH_ELVEN_BRICK_TILING_SLAB.get());
//
//                        entries.accept(BlockLoader.CHISELED_LIGHT_HIGH_ELVEN_BRICKS.get());
//
//                        entries.accept(BlockLoader.LIGHT_HIGH_ELVEN_BRICK_TILING.get());
//                        entries.accept(BlockLoader.LIGHT_HIGH_ELVEN_BRICK_TILING_SLAB.get());
//                        entries.accept(BlockLoader.LIGHT_HIGH_ELVEN_BRICK_TILING_STAIRS.get());
//
//                        entries.accept(BlockLoader.CHISELED_DARK_HIGH_ELVEN_BRICKS.get());
//
//                        entries.accept(BlockLoader.DARK_HIGH_ELVEN_BRICK_TILING.get());
//                        entries.accept(BlockLoader.DARK_HIGH_ELVEN_BRICK_TILING_STAIRS.get());
//                        entries.accept(BlockLoader.DARK_HIGH_ELVEN_BRICK_TILING_SLAB.get());
//
//                        entries.accept(BlockLoader.CRACKED_GREEN_BASALT.get());
//                        entries.accept(BlockLoader.CRACKED_GREEN_BASALT_SLAB.get());
//                        entries.accept(BlockLoader.CRACKED_GREEN_BASALT_STAIRS.get());
//                        entries.accept(BlockLoader.CRACKED_GREEN_BASALT_WALL.get());
//
//                        entries.accept(BlockLoader.CHISELED_BASALT.get());
//
//                        entries.accept(BlockLoader.CARVED_BROWN_SANDSTONE_BRICKS.get());
//
//                        entries.accept(BlockLoader.CARVED_CACHOLONG_BRICKS.get());
//
//                        entries.accept(BlockLoader.CARVED_BLACK_DEMON_BRICKS.get());
//
//                        entries.accept(BlockLoader.CARVED_BLUE_BRICKS.get());
//
//                        entries.accept(BlockLoader.CARVED_DEMON_BASALT_BRICKS.get());
//
//                        entries.accept(BlockLoader.CARVED_VOLCANIC_DEMON_BRICKS.get());
//
//                        entries.accept(BlockLoader.CARVED_RED_GENSAI_BRICKS.get());
//
//                        entries.accept(BlockLoader.CHISELED_ANDESITE.get());
//                        entries.accept(BlockLoader.CHISELED_DRIPSTONE.get());
//                        entries.accept(BlockLoader.CHISELED_DIORITE.get());
//                        entries.accept(BlockLoader.CHISELED_GRANITE.get());
//                        entries.accept(BlockLoader.DIRTY_CHALK.get());

                    }).build());

    public static final Supplier<CreativeModeTab>  EDUMIA_GLASS_BLOCKS = CREATIVE_MODE_TAB.register("edumia_glass_blocks",() ->
            CreativeModeTab.builder().icon(() -> new ItemStack(GlassSets.FINE_GLASS.block().get()))
                    .title(Component.translatable("creativetab.edumia_glass_blocks"))
                    .displayItems((displayParameters, entries) -> {
                        for (GlassSets.GlassSet item: GlassSets.glassSets){
                            entries.accept(item.pane().get());
                            entries.accept(item.block().get());
                        }

                        for (PaperwallSets.PaperwallSet set : PaperwallSets.paperwallSets){
                            entries.accept(set.pane().get());
                        }
                    }).build());

    public static final Supplier<CreativeModeTab>  EDUMIA_NATURAL_STONE_BLOCKS =
            CREATIVE_MODE_TAB.register("edumia_natural_stone_blocks",() ->
                    CreativeModeTab.builder().icon(() -> new ItemStack(SandBlockSets.WHITE_SAND.block().get()))
                            .title(Component.translatable("creativetab.edumia_natural_stone_blocks"))
                            .displayItems((displayParameters, entries) -> {
                                for (StoneSets.StoneSet item: StoneSets.naturalSets){
                                    entries.accept(item.block().get());
                                    entries.accept(item.slab().get());
                                    entries.accept(item.stair().get());
                                    entries.accept(item.wall().get());
                                }

                                for (SandBlockSets.SandSet item : SandBlockSets.sandSets){
                                    if(item.block() != null && item.sandStone() != null && item.sandstoneSlab() != null && item.sandstoneStairs() != null) {
                                        entries.accept(item.block().get());
                                        entries.accept(item.sandStone().get());
                                        entries.accept(item.sandstoneSlab().get());
                                        entries.accept(item.sandstoneStairs().get());
                                    }
                                    entries.accept(item.layer());
                                    entries.accept(item.slab());
                                }

//                                entries.accept(BlockLoader.CRACKED_GREEN_BASALT.get());
//                                entries.accept(BlockLoader.CRACKED_GREEN_BASALT_SLAB.get());
//                                entries.accept(BlockLoader.CRACKED_GREEN_BASALT_STAIRS.get());
//                                entries.accept(BlockLoader.CRACKED_GREEN_BASALT_WALL.get());
//
//                                entries.accept(BlockLoader.CHISELED_BASALT.get());
//
//                                entries.accept(BlockLoader.BROWN_SANDSTONE_SLATES.get());

////
//                                entries.accept(BlockLoader.SAND_LAYER.get());
//                                entries.accept(BlockLoader.RED_SAND_LAYER.get());
//                                entries.accept(BlockLoader.WHITE_SAND.get());
//                                entries.accept(BlockLoader.WHITE_SAND_LAYER.get());
//
//                                entries.accept(BlockLoader.VOLCANIC_DIRT.get());
//                                entries.accept(BlockLoader.VOLCANIC_DIRT_PATH.get());
//                                entries.accept(BlockLoader.VOLCANIC_GRAVEL.get());
                            }).build());

    public static final Supplier<CreativeModeTab>  EDUMIA_WOOD_BLOCKS =
            CREATIVE_MODE_TAB.register("edumia_wood_blocks",() ->
                    CreativeModeTab.builder().icon(() -> new ItemStack(WoodBlockSets.APPLE.planks().get()))
                            .title(Component.translatable("creativetab.edumia_wood_blocks"))
                            .displayItems((displayParameters, entries) -> {
                                for (WoodBlockSets.SimpleBlockSet wood: WoodBlockSets.sets){
                                    entries.accept(wood.log().get());
                                    entries.accept(wood.wood().get());
                                    entries.accept(wood.woodStairs().get());
                                    entries.accept(wood.woodSlab().get());
                                    entries.accept(wood.woodWall().get());
                                    entries.accept(wood.woodFence().get());
                                    entries.accept(wood.strippedLog().get());
                                    entries.accept(wood.strippedWood().get());
                                    entries.accept(wood.strippedWoodStairs().get());
                                    entries.accept(wood.strippedWoodSlab().get());
                                    entries.accept(wood.strippedWoodWall().get());
                                    entries.accept(wood.strippedWoodFence().get());
                                    entries.accept(wood.planks().get());
                                    entries.accept(wood.planksSlab().get());
                                    entries.accept(wood.planksStairs().get());
                                    entries.accept(wood.planksSlab().get());
                                    entries.accept(wood.planksFence().get());
                                    entries.accept(wood.planksGate().get());
                                    entries.accept(wood.pressurePlate().get());
                                    entries.accept(wood.button().get());
                                    entries.accept(wood.beam().get());
                                }

                                for (WoodBlockSets.SimpleVanillaBlocks wood: WoodBlockSets.beams){
                                    entries.accept(wood.beam().get());
                                }

//                                entries.accept(ModNatureBlocks.JUNGLE_WOOD_FENCE.get());
//                                entries.accept(ModNatureBlocks.JUNGLE_WOOD_WALL.get());
//                                entries.accept(ModNatureBlocks.JUNGLE_WOOD_SLAB.get());
//                                entries.accept(ModNatureBlocks.JUNGLE_WOOD_STAIRS.get());
                            }).build());

    public static final Supplier<CreativeModeTab>  EDUMIA_PLANTS =
            CREATIVE_MODE_TAB.register("edumia_plants",() ->
                    CreativeModeTab.builder().icon(() -> new ItemStack(WoodBlockSets.APPLE.leaves()))
                            .title(Component.translatable("creativetab.edumia_plants"))
                            .displayItems((displayParameters, entries) -> {
                                for (WoodBlockSets.SimpleBlockSet set : WoodBlockSets.sets){
                                    if (set.leaves() != null){
                                        entries.accept(set.leaves().get());
                                    }
                                }
                                entries.accept(ModNatureBlocks.GHOST_GUM_LEAVES.get());
                                entries.accept(ModNatureBlocks.HOLLY_LEAVES.get());
                                entries.accept(ModNatureBlocks.MAPLE_LEAVES.get());
                                entries.accept(ModNatureBlocks.BLACK_OAK_LEAVES.get());
                                entries.accept(ModNatureBlocks.APPLE_LEAVES_RED.get());
                                entries.accept(ModNatureBlocks.APPLE_LEAVES_GREEN.get());
                                entries.accept(ModNatureBlocks.PEAR_LEAVES_FRUIT.get());
                                entries.accept(ModNatureBlocks.CHERRY_LEAVES_FRUIT.get());

                                for (FlowerBlockSets.FlowerSet set : FlowerBlockSets.flowerSets){
                                    if (set.tallFlower() == null){
                                        entries.accept(set.flower().get());
                                    }else {
                                        entries.accept(set.tallFlower().get());
                                    }
                                }

                                for (GrassBlockSets.GrassSet set : GrassBlockSets.grassSets){
                                    if (set.tallGrass() == null){
                                        entries.accept(set.grass());
                                    }else {
                                        entries.accept(set.tallGrass());
                                    }
                                }
//                                entries.accept(ModNatureBlocks.TEST_SAPLING.get());
//                                entries.accept(ModNatureBlocks.APPLE_SAPLING.get());
//                                entries.accept(ModNatureBlocks.BANANA_SAPLING.get());
//                                entries.accept(ModNatureBlocks.ASPEN_SAPLING.get());
//                                entries.accept(ModNatureBlocks.GHOST_GUM_SAPLING.get());
//
//                                entries.accept(ModNatureBlocks.BEECH_SAPLING.get());
//                                entries.accept(ModNatureBlocks.BLACKTHORN_SAPLING.get());
//                                entries.accept(ModNatureBlocks.BLACK_OAK_SAPLING.get());
//                                entries.accept(ModNatureBlocks.CEDAR_SAPLING.get());
//                                entries.accept(ModNatureBlocks.CHERRY_SAPLING.get());
//                                entries.accept(ModNatureBlocks.CYPRESS_SAPLING.get());
//                                entries.accept(ModNatureBlocks.DRAGON_BLOOD_SAPLING.get());
//                                entries.accept(ModNatureBlocks.FIR_SAPLING.get());
//                                entries.accept(ModNatureBlocks.GREEN_OAK_SAPLING.get());
//                                entries.accept(ModNatureBlocks.HOLLY_SAPLING.get());
//                                entries.accept(ModNatureBlocks.LARCH_SAPLING.get());
//                                entries.accept(ModNatureBlocks.MAHOGANY_SAPLING.get());
//                                entries.accept(ModNatureBlocks.MAPLE_SAPLING.get());
//                                entries.accept(ModNatureBlocks.MANGO_SAPLING.get());
//
//
//                                entries.accept(ModNatureBlocks.PEAR_SAPLING.get());
//
//                                entries.accept(ModNatureBlocks.PALM_SAPLING.get());
//                                entries.accept(ModNatureBlocks.PINE_SAPLING.get());
//                                entries.accept(ModNatureBlocks.RED_OAK_SAPLING.get());
//                                entries.accept(ModNatureBlocks.REDWOOD_SAPLING.get());
//                                entries.accept(ModNatureBlocks.SILVER_SPRUCE_SAPLING.get());
//                                entries.accept(ModNatureBlocks.WHITE_ASH_SAPLING.get());
//                                entries.accept(ModNatureBlocks.WILLOW_SAPLING.get());
//                                entries.accept(BlockLoader.PAPYRUS.get());
//                                entries.accept(BlockLoader.REEDS.get());
//                                entries.accept(BlockLoader.DRIED_REEDS.get());
//
//                                entries.accept(BlockLoader.DRY_BUSH.get());
//                                entries.accept(BlockLoader.TALL_DRY_BUSH.get());
//                                entries.accept(BlockLoader.TALL_DEAD_BUSH.get());
//
//                                entries.accept(BlockLoader.ARID_GRASS.get());
//                                entries.accept(BlockLoader.BLACK_GRASS.get());
//                                entries.accept(BlockLoader.FLAX_GRASS.get());
//                                entries.accept(BlockLoader.BEACH_GRASS.get());
//                                entries.accept(BlockLoader.TALL_BEACH_GRASS.get());
//                                entries.accept(BlockLoader.FROSTED_GRASS.get());
//                                entries.accept(BlockLoader.TALL_FROSTED_GRASS.get());
//
//                                entries.accept(BlockLoader.PARASOL_MUSHROOM_1.get());
//                                entries.accept(BlockLoader.PARASOL_MUSHROOM_2.get());
//                                entries.accept(BlockLoader.PARASOL_MUSHROOM_TALL.get());

//                             flowers
//                                entries.accept(BlockLoader.ASPHODEL.get());
//                                entries.accept(BlockLoader.BLUE_DELPHINIUM.get());
//                                entries.accept(BlockLoader.BLUEBELL.get());
//                                entries.accept(BlockLoader.CALLA_LILY.get());
//                                entries.accept(BlockLoader.CELSEMIUM.get());
//                                entries.accept(BlockLoader.CHRYS_BLUE.get());
//                                entries.accept(BlockLoader.CHRYS_ORANGE.get());
//                                entries.accept(BlockLoader.CHRYS_PINK.get());
//                                entries.accept(BlockLoader.CHRYS_WHITE.get());
//                                entries.accept(BlockLoader.CHRYS_YELLOW.get());
//                                entries.accept(BlockLoader.CROCUS.get());
//                                entries.accept(BlockLoader.DAISY.get());
//                                entries.accept(BlockLoader.DELPHINIUM.get());
//                                entries.accept(BlockLoader.FLAX_FLOWERS.get());
//                                entries.accept(BlockLoader.FOXGLOVE_ORANGE.get());
//                                entries.accept(BlockLoader.FOXGLOVE_PINK.get());
//                                entries.accept(BlockLoader.FOXGLOVE_RED.get());
//                                entries.accept(BlockLoader.FOXGLOVE_WHITE.get());
//                                entries.accept(BlockLoader.GERBERA_RED.get());
//                                entries.accept(BlockLoader.GENSAI_ORCHID.get());
//                                entries.accept(BlockLoader.GERBERA_YELLOW.get());
//                                entries.accept(BlockLoader.HEATHER_BUSH.get());
//                                entries.accept(BlockLoader.LAVENDER.get());
//                                entries.accept(BlockLoader.MARIGOLD.get());
//                                entries.accept(BlockLoader.PINK_ANEMONE.get());
//                                entries.accept(BlockLoader.SIMBLELMYNE.get());
//                                entries.accept(BlockLoader.TUBEROSE.get());
//                                entries.accept(BlockLoader.YELLOW_IRIS.get());
//                                entries.accept(BlockLoader.FLAME_OF_THE_SOUTH.get());
//                                entries.accept(BlockLoader.HIBISCUS.get());
                            }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
