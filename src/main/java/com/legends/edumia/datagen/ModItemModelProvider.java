package com.legends.edumia.datagen;

import com.legends.edumia.Edumia;
import com.legends.edumia.core.ItemLoader;
import com.legends.edumia.datagen.custom.models.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.WallBlock;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Edumia.MOD_ID, existingFileHelper);
    }
    @Override
    protected void registerModels() {
        basicItem(ItemLoader.GENSAI_STEEL.get());
        for (SimpleWallModel.Wall block : SimpleWallModel.blocks){
            wallItem(block.wall(), block.block());
        }

        for (Block block : SimpleDoorModel.blocks){
            basicItem(block.asItem());
        }

        for (SimpleGlassModel.Glass block : SimpleGlassModel.blocks){
            genericBlockItem(block.pane(), block.block());
        }

        for (Block block : SimplePaperWallModel.blocks){
            genericBlockItem(block, block);
        }

        for (Block block : SimpleFlowerModel.blocks){
            genericBlockItem(block, block);
        }

        for (SimpleButtonModel.Button block : SimpleButtonModel.blocks){
            buttonItem(block.button(), block.block());
        }

        for (SimpleFenceModel.Fence block : SimpleFenceModel.blocks){
            fenceItem(block.fence(), block.block());
        }

    }

    public void wallItem(WallBlock block, Block baseBlock) {
        this.withExistingParent(name(block), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID,
                        "block/" + name(baseBlock)));
    }

    public void buttonItem(ButtonBlock block, Block baseBlock) {
        this.withExistingParent(name(block), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID,
                        "block/" + name(baseBlock)));
    }

    public void fenceItem(FenceBlock block, Block baseBlock) {
        this.withExistingParent(name(block), mcLoc("block/fence_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID,
                        "block/" + name(baseBlock)));
    }


    public void genericBlockItem(Block block, Block baseBlock) {
        this.withExistingParent(name(block), mcLoc("item/generated"))
                .texture("layer0",  ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID,
                        "block/" + name(baseBlock)));
    }


    public ResourceLocation key(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }

    public String name(Block block) {
        return key(block).getPath();
    }
}
