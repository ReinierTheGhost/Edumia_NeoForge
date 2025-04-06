package com.legends.edumia.core;

import com.legends.edumia.Edumia;
import com.legends.edumia.blocks.blocksets.BuildingSets;
import com.legends.edumia.blocks.blocksets.ClayTilingSets;
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

    public static final Supplier<CreativeModeTab> EDUMIA_NATURAL_STONE_BLOCKS = CREATIVE_MODE_TAB.register("edumia_natural_stone_blocks",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(BlockLoader.WHITE_SAND))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "bismuth_items_tab"))
                    .title(Component.translatable("creativetab.edumia_natural_stone_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(BlockLoader.WHITE_SAND);

                    }).build());
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
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
//
//                        for (NotBrickBuildingSets.BuildSet set : NotBrickBuildingSets.buildSets) {
//                            entries.accept(set.block().get());
//                            entries.accept(set.slab().get());
//                            entries.accept(set.stair().get());
//                            if (set.wall() != null){
//                                entries.accept(set.wall().get());
//                            }
//                        }
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
}
