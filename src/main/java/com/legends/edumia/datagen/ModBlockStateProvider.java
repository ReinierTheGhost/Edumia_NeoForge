package com.legends.edumia.datagen;

import com.legends.edumia.Edumia;
import com.legends.edumia.core.BlockLoader;
import com.legends.edumia.datagen.custom.ModModelProvider;
import com.legends.edumia.datagen.custom.models.*;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Supplier;

public class ModBlockStateProvider  extends ModModelProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Edumia.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(BlockLoader.WHITE_SAND);
        for (Block block : SimpleBlockModel.blocks){
            blockWithItem(block);
        }
        for (SimpleStairModel.Stair block : SimpleStairModel.blocks){
            stairsBlock(block.stairs(), blockTexture(block.block()));
            blockItem(block.stairs());

        }
        for(SimpleWallModel.Wall block : SimpleWallModel.blocks){
            wallBlock(block.wall(), blockTexture(block.block()));
        }

        for (SimpleAxisSlabModel.Slab block : SimpleAxisSlabModel.blocks){
            axisSlab(block.slab(), blockTexture(block.block()), blockTexture(block.block()));
            blockItem(block.slab());
        }

        for (SimpleArrowSlitModel.ArrowSlit block : SimpleArrowSlitModel.blocks){
            arrowSlit(block.arrowSlit(), blockTexture(block.texture()));
            blockItem(block.arrowSlit());
        }

        for (SimpleBalustradeModels.Balustrades block : SimpleBalustradeModels.blocks){
            balustrade(block.balustrade(), blockTexture(block.texture()));
            blockItem(block.balustrade());
        }

        for (SimpleGothicArchModel.Arch block : SimpleGothicArchModel.blocks){
            gothicArch(block.arch(), blockTexture(block.texture()));
            blockItem(block.arch(), "_1");
        }

        for (SimpleRoundArchModel.Arch block : SimpleRoundArchModel.blocks){
            roundArch(block.arch(), blockTexture(block.texture()));
            blockItem(block.arch(), "_1");
        }

        for (SimpleSegmentalArchModel.Arch block : SimpleSegmentalArchModel.blocks){
            segmentalArch(block.arch(), blockTexture(block.texture()));
            blockItem(block.arch(), "_1");
        }

        for (SimpleSmallArchModel.Arch block : SimpleSmallArchModel.blocks){
            smallArch(block.arch(), blockTexture(block.texture()));
            blockItem(block.arch());
        }

        for (SimpleTwoMeterArchModel.Arch block : SimpleTwoMeterArchModel.blocks){
            twoMeterArch(block.arch(), blockTexture(block.texture()));
            blockItem(block.arch());
        }

        for (SimplePillarModels.Pillar block : SimplePillarModels.blocks){
            pillar(block.pillar());
            blockItem(block.pillar());
        }

        for (SimpleAxisPillarSlabModel.Slab block : SimpleAxisPillarSlabModel.blocks){
            axisSlab(block.slab(), blockTexture(block.block()), blockTexture(block.block()));
            blockItem(block.slab());
        }

        for (SimpleCornerBlockModel.Corner block : SimpleCornerBlockModel.blocks){
            verticalCorner(block.corner(), blockTexture(block.texture()));
            blockItem(block.corner(), "_4");
        }


    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockWithItem(Block block){
        simpleBlockWithItem(block, cubeAll(block));
    }

    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("edumia:block/" + deferredBlock.getId().getPath()));
    }
    private void blockItem(Block deferredBlock) {
        simpleBlockItem(deferredBlock, new ModelFile.UncheckedModelFile("edumia:block/" + name(deferredBlock)));
    }

    private void blockItem(DeferredBlock<?> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("edumia:block/" + deferredBlock.getId().getPath() + appendix));
    }
    private void blockItem(Block deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock, new ModelFile.UncheckedModelFile("edumia:block/" + name(deferredBlock) + appendix));
    }

}
