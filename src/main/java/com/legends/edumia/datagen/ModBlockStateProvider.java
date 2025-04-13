package com.legends.edumia.datagen;

import com.legends.edumia.Edumia;
import com.legends.edumia.core.BlockLoader;
import com.legends.edumia.datagen.custom.ModModelProvider;
import com.legends.edumia.datagen.custom.models.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends ModModelProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Edumia.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
//        blockWithItem(BlockLoader.WHITE_SAND);
        for (Block block : SimpleBlockModel.blocks){
            blockWithItem(block);
        }

        for (SimplePillarModel.Pillar block : SimplePillarModel.blocks){
            logBlock(block.base());
            blockItem(block.base());
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

        for (SimpleGlassModel.Glass block : SimpleGlassModel.blocks){
            paneBlockWithRenderType(block.pane(), blockTexture(block.block()),
                    ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "block/fine_glass_pane_top"), "translucent");
        }

        for (IronBarsBlock block : SimplePaperWallModel.blocks){
            paneBlockWithRenderType(block, blockTexture(block),
                    ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "block/" + name(block) + "_top"), "translucent");
        }

        for (Block block : SimpleLeavesModel.blocks){
            leavesBlock(block);
        }

        for (SimpleFenceModel.Fence block : SimpleFenceModel.blocks){
            fenceBlock(block.fence(), blockTexture(block.block()));
        }

        for (SimpleFenceGateModel.FenceGate block : SimpleFenceGateModel.blocks){
            fenceGateBlock(block.fenceGate(), blockTexture(block.block()));
            blockItem(block.fenceGate());
        }

        for (SimpleWoodBlockModel.WoodBlocks blocks : SimpleWoodBlockModel.blocks){
            woodBlock(blocks.wood(), blockTexture(blocks.texture()));
            blockItem(blocks.wood());
        }

        for (SimpleButtonModel.Button button : SimpleButtonModel.blocks){
            buttonBlock(button.button(), blockTexture(button.block()));
        }

        for (SimplePressurePlateModel.PressurePlate pressurePlate: SimplePressurePlateModel.blocks){
            pressurePlateBlock(pressurePlate.pressurePlate(), blockTexture(pressurePlate.block()));
            blockItem(pressurePlate.pressurePlate());
        }

        for (DoorBlock block : SimpleDoorModel.blocks){
            doorBlockWithRenderType(block,
                    ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "block/" +  name(block) + "_bottom"),
                    ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "block/" +  name(block) + "_top"),
                    "cutout");
        }

        for (TrapDoorBlock block : SimpleTrapDoorModel.blocks){
            trapdoorBlockWithRenderType(block, blockTexture(block), true, "cutout");
            blockItem(block, "_bottom");
        }

        for (Block block : SimpleMultiFaceBlockModel.blocks){
            cubeBottomTop(block);
            blockItem(block);
        }

        for (SimpleMultiFaceStairModel.Stair block : SimpleMultiFaceStairModel.blocks){
            stairsBlock(block.stairs(), blockTexture(block.block()), modLoc("block/" + name(block.block())+ "_bottom"), modLoc("block/" + name(block.block()) + "_top"));
            blockItem(block.stairs());
        }

        for (SimpleMultiFaceSlabModel.Slab block : SimpleMultiFaceSlabModel.blocks){
            axisSlab(block.slab(), blockTexture(block.block()), blockTexture(block.block()), modLoc("block/" + name(block.block())+ "_bottom"), modLoc("block/" + name(block.block()) + "_top"));
            blockItem(block.slab());
        }

        for (SimpleLayerModel.Layer block : SimpleLayerModel.blocks){
            layer(block.layer(), block.texture());
            blockItem(block.layer(), "_height2");
        }

        for (SimpleSlabModel.Slab block : SimpleSlabModel.blocks){
            slabBlock(block.slab(), blockTexture(block.block()), blockTexture(block.block()));
            blockItem(block.slab());
        }

        for (Block block : SimpleSaplingModel.blocks){
            crossBlock(block);
        }

        for (Block block : SimpleFlowerModel.blocks){
            crossBlock(block);
        }
        for (SimplePottedFlowerModel.Potted block : SimplePottedFlowerModel.blocks){
            pottedFlower(block.block(), block.flower());
        }
        for (Block block : SimpleTallFlowerModel.blocks){
            tallFlower(block);
        }

        for (SimpleReedsModel.Reed block : SimpleReedsModel.blocks){
            reeds(block.reed());
        }
        slateBlock(BlockLoader.BROWN_SANDSTONE_SLATES.get());
        blockItem(BlockLoader.BROWN_SANDSTONE_SLATES.get());
        blockColum(BlockLoader.CHISELED_ANDESITE.get());
        blockColum(BlockLoader.CHISELED_GRANITE.get());
        blockColum(BlockLoader.CHISELED_DRIPSTONE.get());
        blockColum(BlockLoader.CHISELED_DIORITE.get());
        blockColum(BlockLoader.DIRTY_CHALK.get());

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

    private void leavesBlock(Block block) {
        simpleBlockWithItem(block,
                models().singleTexture(BuiltInRegistries.BLOCK.getKey(block).getPath(),
                        ResourceLocation.parse("minecraft:block/leaves"),
                        "all", blockTexture(block)).renderType("cutout"));
    }

}
