package com.legends.edumia.core;

import com.legends.edumia.Edumia;
import com.legends.edumia.core.blocksets.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
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

                        entries.accept(BlockLoader.BRICK_PILLAR.get());
                        entries.accept(BlockLoader.STONE_PILLAR.get());
                        entries.accept(BlockLoader.SANDSTONE_PILLAR.get());
                        entries.accept(BlockLoader.CHISELED_ANDESITE.get());
                        entries.accept(BlockLoader.CHISELED_DRIPSTONE.get());
                        entries.accept(BlockLoader.CHISELED_DIORITE.get());
                        entries.accept(BlockLoader.CHISELED_GRANITE.get());
                        entries.accept(BlockLoader.DIRTY_CHALK.get());

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

                                entries.accept(BlockLoader.BROWN_SANDSTONE_SLATES.get());

                                entries.accept(BlockLoader.VOLCANIC_DIRT.get());
                                entries.accept(BlockLoader.VOLCANIC_DIRT_PATH.get());
                                entries.accept(BlockLoader.HIGH_ELVEN_CRYSTAL.get());
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
                                    entries.accept(wood.beam().get());
                                }

                                for (WoodBlockSets.SimpleVanillaBlocks wood: WoodBlockSets.beams){
                                    entries.accept(wood.beam().get());
                                    entries.accept(wood.woodStairs().get());
                                    entries.accept(wood.woodSlab().get());
                                    entries.accept(wood.woodFence().get());
                                    entries.accept(wood.woodWall().get());
                                }
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
                                    if (set.sapling() != null){
                                        entries.accept(set.sapling().get());
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

                                entries.accept(ModNatureBlocks.PAPYRUS.get());
                                entries.accept(ModNatureBlocks.REEDS.get());
                                entries.accept(ModNatureBlocks.DRIED_REEDS.get());


                            }).build());

    public static final Supplier<CreativeModeTab>  UTILITY_GROUP =
            CREATIVE_MODE_TAB.register("edumia_utilities",() ->
                    CreativeModeTab.builder().icon(() -> new ItemStack(WoodBlockSets.DRAGON_BLOOD.door().get()))
                            .title(Component.translatable("creativetab.edumia_utilities"))
                            .displayItems((displayParameters, entries) -> {
                                for (WoodBlockSets.SimpleBlockSet wood: WoodBlockSets.sets){
                                    entries.accept(wood.door().get());
                                    entries.accept(wood.trapdoor().get());
                                    entries.accept(wood.pressurePlate().get());
                                    entries.accept(wood.button().get());
                                }
                            }).build());

    public static final Supplier<CreativeModeTab>  WEAPON_GROUP = CREATIVE_MODE_TAB.register("edumia_weapons",() ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ItemLoader.ABOMINABLE_BLADE.get()))
                    .title(Component.translatable("creativetab.edumia_weapons"))
                    .displayItems((displayParameters, entries) -> {
                        entries.accept(ItemLoader.ABOMINABLE_BLADE.get());
                    }).build());

    public static final Supplier<CreativeModeTab>  MATERIAL_GROUP = CREATIVE_MODE_TAB.register("edumia_materials",() ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ItemLoader.GENSAI_STEEL.get()))
                    .title(Component.translatable("creativetab.edumia_materials"))
                    .displayItems((displayParameters, entries) -> {
                        entries.accept(ItemLoader.GENSAI_STEEL.get());
                    }).build());

    public static final Supplier<CreativeModeTab>  TOOL_GROUP = CREATIVE_MODE_TAB.register("edumia_tools",() ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ItemLoader.GENSAI_AXE.get()))
                    .title(Component.translatable("creativetab.edumia_tools"))
                    .displayItems((displayParameters, entries) -> {
                        entries.accept(ItemLoader.GENSAI_AXE.get());
                        entries.accept(ItemLoader.GENSAI_HOE.get());
                        entries.accept(ItemLoader.GENSAI_PICKAXE.get());
                        entries.accept(ItemLoader.GENSAI_SHOVEL.get());
                    }).build());

    public static final Supplier<CreativeModeTab>  LEGENDS_GROUP =
            CREATIVE_MODE_TAB.register("special_items",() ->
                    CreativeModeTab.builder().icon(() -> new ItemStack(ItemLoader.LEGENDS_COIN.get()))
                            .title(Component.translatable("creativetab.edumia_rewards"))
                            .displayItems((displayParameters, entries) -> {
                                entries.accept(ItemLoader.LEGENDS_COIN.get());
                                entries.accept(BlockLoader.TEST_SAPLING.get());
                            }).build());

    public static final Supplier<CreativeModeTab>  EDUMIA_GEMS = CREATIVE_MODE_TAB.register("edumia_gems",() ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ItemLoader.GEM_PERFECT_AMBER.get()))
                    .title(Component.translatable("creativetab.edumia_gems"))
                    .displayItems((displayParameters, entries) -> {
                        entries.accept(ItemLoader.GEM_FINE_AMBER.get());
                        entries.accept(ItemLoader.GEM_FLAWED_AMBER.get());
                        entries.accept(ItemLoader.GEM_FLAWLESS_AMBER.get());
                        entries.accept(ItemLoader.GEM_PERFECT_AMBER.get());
                        entries.accept(ItemLoader.GEM_ROUGH_AMBER.get());
                        entries.accept(ItemLoader.GEM_FINE_AMETHYST.get());
                        entries.accept(ItemLoader.GEM_FLAWED_AMETHYST.get());
                        entries.accept(ItemLoader.GEM_FLAWLESS_AMETHYST.get());
                        entries.accept(ItemLoader.GEM_PERFECT_AMETHYST.get());
                        entries.accept(ItemLoader.GEM_ROUGH_AMETHYST.get());
                        entries.accept(ItemLoader.GEM_FINE_JADE.get());
                        entries.accept(ItemLoader.GEM_FLAWED_JADE.get());
                        entries.accept(ItemLoader.GEM_FLAWLESS_JADE.get());
                        entries.accept(ItemLoader.GEM_PERFECT_JADE.get());
                        entries.accept(ItemLoader.GEM_ROUGH_JADE.get());
                        entries.accept(ItemLoader.GEM_FINE_JASPER.get());
                        entries.accept(ItemLoader.GEM_FLAWED_JASPER.get());
                        entries.accept(ItemLoader.GEM_FLAWLESS_JASPER.get());
                        entries.accept(ItemLoader.GEM_PERFECT_JASPER.get());
                        entries.accept(ItemLoader.GEM_ROUGH_JASPER.get());
                        entries.accept(ItemLoader.GEM_FINE_RUBY.get());
                        entries.accept(ItemLoader.GEM_FLAWED_RUBY.get());
                        entries.accept(ItemLoader.GEM_FLAWLESS_RUBY.get());
                        entries.accept(ItemLoader.GEM_PERFECT_RUBY.get());
                        entries.accept(ItemLoader.GEM_ROUGH_RUBY.get());
                        entries.accept(ItemLoader.GEM_FINE_SAPPHIRE.get());
                        entries.accept(ItemLoader.GEM_FLAWED_SAPPHIRE.get());
                        entries.accept(ItemLoader.GEM_FLAWLESS_SAPPHIRE.get());
                        entries.accept(ItemLoader.GEM_PERFECT_SAPPHIRE.get());
                        entries.accept(ItemLoader.GEM_ROUGH_SAPPHIRE.get());
                        entries.accept(ItemLoader.GEM_FINE_TOPAZ.get());
                        entries.accept(ItemLoader.GEM_FLAWED_TOPAZ.get());
                        entries.accept(ItemLoader.GEM_FLAWLESS_TOPAZ.get());
                        entries.accept(ItemLoader.GEM_PERFECT_TOPAZ.get());
                        entries.accept(ItemLoader.GEM_ROUGH_TOPAZ.get());
                    }).build());

    public static final Supplier<CreativeModeTab>  FOOD_GROUP = CREATIVE_MODE_TAB.register("edumia_food",() ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ItemLoader.TEA_HIBISCUS_PETALS.get()))
                    .title(Component.translatable("creativetab.edumia_food"))
                    .displayItems((displayParameters, entries) -> {
                        entries.accept(ItemLoader.TEA_SAKURA_PETALS.get());
                        entries.accept(ItemLoader.TEA_MINT_LEAVES.get());
                        entries.accept(ItemLoader.TEA_LEAF.get());
                        entries.accept(ItemLoader.TEA_LILY_PETALS.get());
                        entries.accept(ItemLoader.TEA_WHITE_JADE_PETALS.get());
                        entries.accept(ItemLoader.TEA_HIBISCUS_PETALS.get());
                        entries.accept(ItemLoader.TEA_JASMINE_PETALS.get());
                        entries.accept(ItemLoader.TEA_CINNAMON_STICK.get());
                        entries.accept(ItemLoader.TEA_WHITE_DRAGON_PETALS.get());

                        entries.accept(ItemLoader.BROCCOLI.get());
                        entries.accept(ItemLoader.PAPRIKA_GREEN.get());
                        entries.accept(ItemLoader.RAMEN.get());
                        entries.accept(ItemLoader.RAMEN_BEEF.get());
                        entries.accept(ItemLoader.RAMEN_PORK.get());
                        entries.accept(ItemLoader.RAMEN_SHRIMPS.get());
                        entries.accept(ItemLoader.RAMEN_VEGI.get());
                        entries.accept(ItemLoader.RED_GRAPES.get());
                        entries.accept(ItemLoader.RICE.get());
                        entries.accept(ItemLoader.RICE_BALL.get());
                        entries.accept(ItemLoader.SPINACH.get());
                        entries.accept(ItemLoader.TOMATO.get());
                        entries.accept(ItemLoader.CHEESE.get());

                        entries.accept(ItemLoader.LETTUCE.get());
                        entries.accept(ItemLoader.BANANA.get());
                        entries.accept(ItemLoader.BANANA_BREAD.get());
                        entries.accept(ItemLoader.MANGO.get());
                        entries.accept(ItemLoader.DATE.get());
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
