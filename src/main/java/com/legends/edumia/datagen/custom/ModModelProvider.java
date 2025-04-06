package com.legends.edumia.datagen.custom;

import com.legends.edumia.Edumia;
import com.legends.edumia.blocks.*;
import com.legends.edumia.blocks.properties.ArchShape;
import com.legends.edumia.blocks.properties.BidirectionalShape;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public abstract class ModModelProvider extends BlockStateProvider {
    public ModModelProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
    }

    public void axisSlab(AxialSlabBlock block, ResourceLocation doubleslab, ResourceLocation texture) {
        axisSlab(block, doubleslab, texture, texture, texture);
    }
    private void axisSlab(AxialSlabBlock block, ResourceLocation doubleslab, ResourceLocation side, ResourceLocation bottom, ResourceLocation top) {
        axisSlab(block, models().slab(name(block), side, bottom, top), models().slabTop(name(block) + "_top", side, bottom, top), models().getExistingFile(doubleslab));
    }
    private void axisSlab(AxialSlabBlock block, ModelFile bottom, ModelFile top, ModelFile doubleSlab){
        getVariantBuilder(block)
                .partialState().with(AxialSlabBlock.TYPE, SlabType.BOTTOM).with(EdumiaBlockStates.SLAB_AXIS, Direction.Axis.X)
                .addModels(new ConfiguredModel(bottom, 90, 90, true))

                .partialState().with(AxialSlabBlock.TYPE, SlabType.DOUBLE).with(EdumiaBlockStates.SLAB_AXIS, Direction.Axis.X)
                .addModels(new ConfiguredModel(doubleSlab, 90, 90, true))

                .partialState().with(AxialSlabBlock.TYPE, SlabType.TOP).with(EdumiaBlockStates.SLAB_AXIS, Direction.Axis.X)
                .addModels(new ConfiguredModel(top, 90, 90, true))

                .partialState().with(AxialSlabBlock.TYPE, SlabType.BOTTOM).with(EdumiaBlockStates.SLAB_AXIS, Direction.Axis.Y)
                .addModels(new ConfiguredModel(bottom))

                .partialState().with(AxialSlabBlock.TYPE, SlabType.DOUBLE).with(EdumiaBlockStates.SLAB_AXIS, Direction.Axis.Y)
                .addModels(new ConfiguredModel(doubleSlab))

                .partialState().with(AxialSlabBlock.TYPE, SlabType.TOP).with(EdumiaBlockStates.SLAB_AXIS, Direction.Axis.Y)
                .addModels(new ConfiguredModel(top))

                .partialState().with(AxialSlabBlock.TYPE, SlabType.BOTTOM).with(EdumiaBlockStates.SLAB_AXIS, Direction.Axis.Z)
                .addModels(new ConfiguredModel(bottom, 270, 0, true))

                .partialState().with(AxialSlabBlock.TYPE, SlabType.DOUBLE).with(EdumiaBlockStates.SLAB_AXIS, Direction.Axis.Z)
                .addModels(new ConfiguredModel(doubleSlab, 270, 0, true))

                .partialState().with(AxialSlabBlock.TYPE, SlabType.TOP).with(EdumiaBlockStates.SLAB_AXIS, Direction.Axis.Z)
                .addModels(new ConfiguredModel(top, 270, 0, true));
    }

    public void arrowSlit(ArrowSlit block, ResourceLocation arrowslit){
        arrowSlit(block, arrowSlit(name(block), arrowslit, arrowslit, arrowslit));
    }
    private  <T extends BlockModelBuilder> T arrowSlit(String name, ResourceLocation texture, ResourceLocation bottom, ResourceLocation top) {
        return texBotTopPar(name, "block/template/template_arrowslit", texture, bottom, top);
    }
    private void arrowSlit(ArrowSlit block, ModelFile arrowslit){
        getVariantBuilder(block)
                .partialState().with(ArrowSlit.DIRECTION, Direction.EAST)
                .addModels(new ConfiguredModel(arrowslit, 0, 90, true))
                .partialState().with(ArrowSlit.DIRECTION, Direction.NORTH)
                .addModels(new ConfiguredModel(arrowslit, 0, 0, true))
                .partialState().with(ArrowSlit.DIRECTION, Direction.SOUTH)
                .addModels(new ConfiguredModel(arrowslit, 0, 180, true))
                .partialState().with(ArrowSlit.DIRECTION, Direction.WEST)
                .addModels(new ConfiguredModel(arrowslit, 0, 270, true));
    }

    public void balustrade(Balustrade block, ResourceLocation balustrade){
        balustrade(block, balustrade(name(block), balustrade, balustrade), balustradeBase(name(block) + "_base", balustrade, balustrade));
    }
    private  <T extends BlockModelBuilder> T balustradeBase(String name, ResourceLocation side,  ResourceLocation top) {
        return sideTopPar(name, "block/template/parent_balustrade_base", side, top);
    }
    private  <T extends BlockModelBuilder> T balustrade(String name, ResourceLocation side,  ResourceLocation top) {
        return sideTopPar(name, "block/template/parent_balustrade", side, top);
    }
    private void balustrade(Balustrade block, ModelFile balustrade, ModelFile base){
        getVariantBuilder(block)
                .partialState().with(Balustrade.AXIS, Direction.Axis.X)
                .addModels(new ConfiguredModel(balustrade, 0, 90, true))
                .partialState().with(Balustrade.AXIS, Direction.Axis.Y)
                .addModels(new ConfiguredModel(base, 0, 0, true))
                .partialState().with(Balustrade.AXIS, Direction.Axis.Z)
                .addModels(new ConfiguredModel(balustrade, 0, 180, true));
    }

    public void gothicArch(ArchBlock block, ResourceLocation texture){
        arch(block, gothicArchOne(name(block) +"_1", texture),
                gothicArchTwo(name(block) + "_2", texture),
                gothicArchThree(name(block) + "_3", texture),
                gothicArchThreeMiddle(name(block) + "_3_middle", texture));
    }
    private  <T extends BlockModelBuilder> T gothicArchOne(String name, ResourceLocation side) {
        return texPar(name, "block/template/smooth_gothic_arch_1", side);
    }
    private  <T extends BlockModelBuilder> T gothicArchTwo(String name, ResourceLocation side) {
        return texPar(name, "block/template/smooth_gothic_arch_2", side);
    }
    private  <T extends BlockModelBuilder> T gothicArchThree(String name, ResourceLocation side) {
        return texPar(name, "block/template/smooth_gothic_arch_3", side);
    }
    private  <T extends BlockModelBuilder> T gothicArchThreeMiddle(String name, ResourceLocation side) {
        return texPar(name, "block/template/smooth_gothic_arch_3_middle", side);
    }

    public void roundArch(ArchBlock block, ResourceLocation texture){
        arch(block, roundArchOne(name(block) +"_1", texture),
                roundArchTwo(name(block) + "_2", texture),
                roundArchThree(name(block) + "_3", texture),
                roundArchThreeMiddle(name(block) + "_3_middle", texture));
    }
    private  <T extends BlockModelBuilder> T roundArchOne(String name, ResourceLocation side) {
        return texPar(name, "block/template/smooth_round_arch_1", side);
    }
    private  <T extends BlockModelBuilder> T roundArchTwo(String name, ResourceLocation side) {
        return texPar(name, "block/template/smooth_round_arch_2", side);
    }
    private  <T extends BlockModelBuilder> T roundArchThree(String name, ResourceLocation side) {
        return texPar(name, "block/template/smooth_round_arch_3", side);
    }
    private  <T extends BlockModelBuilder> T roundArchThreeMiddle(String name, ResourceLocation side) {
        return texPar(name, "block/template/smooth_round_arch_3_middle", side);
    }

    public void segmentalArch(ArchBlock block, ResourceLocation texture){
        arch(block, segmentalArchOne(name(block) +"_1", texture),
                segmentalArchTwo(name(block) + "_2", texture),
                segmentalArchThree(name(block) + "_3", texture),
                segmentalArchThreeMiddle(name(block) + "_3_middle", texture));
    }
    private  <T extends BlockModelBuilder> T segmentalArchOne(String name, ResourceLocation side) {
        return texPar(name, "block/template/smooth_segmental_arch_1", side);
    }
    private  <T extends BlockModelBuilder> T segmentalArchTwo(String name, ResourceLocation side) {
        return texPar(name, "block/template/smooth_segmental_arch_2", side);
    }
    private  <T extends BlockModelBuilder> T segmentalArchThree(String name, ResourceLocation side) {
        return texPar(name, "block/template/smooth_segmental_arch_3", side);
    }
    private  <T extends BlockModelBuilder> T segmentalArchThreeMiddle(String name, ResourceLocation side) {
        return texPar(name, "block/template/smooth_segmental_arch_3_middle", side);
    }
    private void arch(ArchBlock block, ModelFile one, ModelFile two, ModelFile tree, ModelFile treeMiddle){
        getVariantBuilder(block)
                .partialState().with(ArchBlock.FORM, ArchShape.ONE).with(ArchBlock.FACING, Direction.EAST)
                .addModels(new ConfiguredModel(one, 0, 0, false))
                .partialState().with(ArchBlock.FORM, ArchShape.TWO).with(ArchBlock.FACING, Direction.EAST)
                .addModels(new ConfiguredModel(two, 0, 180, false))
                .partialState().with(ArchBlock.FORM, ArchShape.THREE).with(ArchBlock.FACING, Direction.EAST)
                .addModels(new ConfiguredModel(tree, 0, 180, false))
                .partialState().with(ArchBlock.FORM, ArchShape.THREE_MIDDLE).with(ArchBlock.FACING, Direction.EAST)
                .addModels(new ConfiguredModel(treeMiddle, 0, 180, false))

                .partialState().with(ArchBlock.FORM, ArchShape.ONE).with(ArchBlock.FACING, Direction.NORTH)
                .addModels(new ConfiguredModel(one, 0, 270, false))
                .partialState().with(ArchBlock.FORM, ArchShape.TWO).with(ArchBlock.FACING, Direction.NORTH)
                .addModels(new ConfiguredModel(two, 0, 90, false))
                .partialState().with(ArchBlock.FORM, ArchShape.THREE).with(ArchBlock.FACING, Direction.NORTH)
                .addModels(new ConfiguredModel(tree, 0, 90, false))
                .partialState().with(ArchBlock.FORM, ArchShape.THREE_MIDDLE).with(ArchBlock.FACING, Direction.NORTH)
                .addModels(new ConfiguredModel(treeMiddle, 0, 90, false))

                .partialState().with(ArchBlock.FORM, ArchShape.ONE).with(ArchBlock.FACING, Direction.SOUTH)
                .addModels(new ConfiguredModel(one, 0, 180, false))
                .partialState().with(ArchBlock.FORM, ArchShape.TWO).with(ArchBlock.FACING, Direction.SOUTH)
                .addModels(new ConfiguredModel(two, 0, 270, false))
                .partialState().with(ArchBlock.FORM, ArchShape.THREE).with(ArchBlock.FACING, Direction.SOUTH)
                .addModels(new ConfiguredModel(tree, 0, 270, false))
                .partialState().with(ArchBlock.FORM, ArchShape.THREE_MIDDLE).with(ArchBlock.FACING, Direction.SOUTH)
                .addModels(new ConfiguredModel(treeMiddle, 0, 270, false))

                .partialState().with(ArchBlock.FORM, ArchShape.ONE).with(ArchBlock.FACING, Direction.WEST)
                .addModels(new ConfiguredModel(one, 0, 90, false))
                .partialState().with(ArchBlock.FORM, ArchShape.TWO).with(ArchBlock.FACING, Direction.WEST)
                .addModels(new ConfiguredModel(two, 0, 0, false))
                .partialState().with(ArchBlock.FORM, ArchShape.THREE).with(ArchBlock.FACING, Direction.WEST)
                .addModels(new ConfiguredModel(tree, 0, 0, false))
                .partialState().with(ArchBlock.FORM, ArchShape.THREE_MIDDLE).with(ArchBlock.FACING, Direction.WEST)
                .addModels(new ConfiguredModel(treeMiddle, 0, 0, false));
    }

    public void twoMeterArch(ArchTwoMeter block, ResourceLocation texture){
        twoMeterArch(block, twoMeterArch(name(block), texture));
    }
    private <T extends BlockModelBuilder> T twoMeterArch(String name, ResourceLocation side){
        return sidePar(name, "block/template/arch_twometer_shape", side);
    }
    private void twoMeterArch(ArchTwoMeter block, ModelFile model){
        getVariantBuilder(block)
                .partialState().with(ArchTwoMeter.TYPE_UPDOWN, Half.BOTTOM).with(ArchTwoMeter.DIRECTION, Direction.EAST)
                .addModels(new ConfiguredModel(model, 180, 90, true))
                .partialState().with(ArchTwoMeter.TYPE_UPDOWN, Half.TOP).with(ArchTwoMeter.DIRECTION, Direction.EAST)
                .addModels(new ConfiguredModel(model, 0, 270, true))
                .partialState().with(ArchTwoMeter.TYPE_UPDOWN, Half.BOTTOM).with(ArchTwoMeter.DIRECTION, Direction.NORTH)
                .addModels(new ConfiguredModel(model, 180, 0, true))
                .partialState().with(ArchTwoMeter.TYPE_UPDOWN, Half.TOP).with(ArchTwoMeter.DIRECTION, Direction.NORTH)
                .addModels(new ConfiguredModel(model, 0, 180, true))
                .partialState().with(ArchTwoMeter.TYPE_UPDOWN, Half.BOTTOM).with(ArchTwoMeter.DIRECTION, Direction.SOUTH)
                .addModels(new ConfiguredModel(model, 180, 180, true))
                .partialState().with(ArchTwoMeter.TYPE_UPDOWN, Half.TOP).with(ArchTwoMeter.DIRECTION, Direction.SOUTH)
                .addModels(new ConfiguredModel(model, 0, 0, true))
                .partialState().with(ArchTwoMeter.TYPE_UPDOWN, Half.BOTTOM).with(ArchTwoMeter.DIRECTION, Direction.WEST)
                .addModels(new ConfiguredModel(model, 180, 270, true))
                .partialState().with(ArchTwoMeter.TYPE_UPDOWN, Half.TOP).with(ArchTwoMeter.DIRECTION, Direction.WEST)
                .addModels(new ConfiguredModel(model, 0, 90, true));
    }

    public void smallArch(ArchSmall block, ResourceLocation texture){
        smallArch(block, smallArch(name(block), texture, texture));
    }
    private <T extends  BlockModelBuilder> T smallArch(String name, ResourceLocation texture, ResourceLocation top){
        return texTopPar(name, "block/template/arch_small_parent", texture, top);
    }
    @SuppressWarnings("unchecked")
    private void smallArch(ArchSmall block, ModelFile model){
        getVariantBuilder(block)
                .partialState().with(ArchSmall.DIRECTION, BidirectionalShape.NORTH_SOUTH)
                .addModels(new ConfiguredModel(model))
                .partialState().with(ArchSmall.DIRECTION, BidirectionalShape.EAST_WEST)
                .addModels(new ConfiguredModel(model, 0, 90, false));
    }

    public void pillar(Block block){
        pillar(block, models().cubeColumn(name(block), blockTexture(block),
                        ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "block/" + name(block) + "_face")),
                models().cubeColumn(name(block) + "_top",
                        ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "block/" + name(block) + "_top"),
                        ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "block/" + name(block) + "_face")),
                models().cubeColumn(name(block)+ "_bottom",
                        ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "block/" + name(block) + "_bottom"),
                        ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "block/" + name(block) + "_face")),
                models().cubeColumn(name(block) + "_middle",
                        ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "block/" + name(block) + "_middle"),
                        ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "block/" + name(block) + "_face")));
    }
    private void pillar(Block block, ModelFile model, ModelFile top, ModelFile bottom, ModelFile middle){
        getVariantBuilder(block)
                .partialState().with(EdumiaPillarBlock.ABOVE, false).with(EdumiaPillarBlock.AXIS, Direction.Axis.X)
                .with(EdumiaPillarBlock.BELOW, false)
                .addModels(new ConfiguredModel(model, 90, 90, false))
                .partialState().with(EdumiaPillarBlock.ABOVE, false).with(EdumiaPillarBlock.AXIS, Direction.Axis.X)
                .with(EdumiaPillarBlock.BELOW, true)
                .addModels(new ConfiguredModel(top, 90, 90, false))
                .partialState().with(EdumiaPillarBlock.ABOVE, true).with(EdumiaPillarBlock.AXIS, Direction.Axis.X)
                .with(EdumiaPillarBlock.BELOW, false)
                .addModels(new ConfiguredModel(bottom, 90, 90, false))
                .partialState().with(EdumiaPillarBlock.ABOVE, true).with(EdumiaPillarBlock.AXIS, Direction.Axis.X)
                .with(EdumiaPillarBlock.BELOW, true)
                .addModels(new ConfiguredModel(middle, 90, 90, false))

                .partialState().with(EdumiaPillarBlock.ABOVE, false).with(EdumiaPillarBlock.AXIS, Direction.Axis.Y)
                .with(EdumiaPillarBlock.BELOW, false)
                .addModels(new ConfiguredModel(model))
                .partialState().with(EdumiaPillarBlock.ABOVE, false).with(EdumiaPillarBlock.AXIS, Direction.Axis.Y)
                .with(EdumiaPillarBlock.BELOW, true)
                .addModels(new ConfiguredModel(top))
                .partialState().with(EdumiaPillarBlock.ABOVE, true).with(EdumiaPillarBlock.AXIS, Direction.Axis.Y)
                .with(EdumiaPillarBlock.BELOW, false)
                .addModels(new ConfiguredModel(bottom))
                .partialState().with(EdumiaPillarBlock.ABOVE, true).with(EdumiaPillarBlock.AXIS, Direction.Axis.Y)
                .with(EdumiaPillarBlock.BELOW, true)
                .addModels(new ConfiguredModel(middle))

                .partialState().with(EdumiaPillarBlock.ABOVE, false).with(EdumiaPillarBlock.AXIS, Direction.Axis.Z)
                .with(EdumiaPillarBlock.BELOW, false)
                .addModels(new ConfiguredModel(model, 270, 0, false))
                .partialState().with(EdumiaPillarBlock.ABOVE, false).with(EdumiaPillarBlock.AXIS, Direction.Axis.Z)
                .with(EdumiaPillarBlock.BELOW, true)
                .addModels(new ConfiguredModel(top, 270, 0, false))
                .partialState().with(EdumiaPillarBlock.ABOVE, true).with(EdumiaPillarBlock.AXIS, Direction.Axis.Z)
                .with(EdumiaPillarBlock.BELOW, false)
                .addModels(new ConfiguredModel(bottom, 270, 0, false))
                .partialState().with(EdumiaPillarBlock.ABOVE, true).with(EdumiaPillarBlock.AXIS, Direction.Axis.Z)
                .with(EdumiaPillarBlock.BELOW, true)
                .addModels(new ConfiguredModel(middle, 270, 0, false));
    }

    public void verticalCorner(Block block, ResourceLocation texture){
        verticalCorner(block,
                verticalCornerOne(name(block) + "_1", texture),
                verticalCornerTwo(name(block) + "_2", texture),
                verticalCornerFour(name(block) + "_4", texture),
                verticalCornerSix(name(block) + "_6", texture));
    }
    private  <T extends BlockModelBuilder> T verticalCornerOne(String name, ResourceLocation texture) {
        return directionalPar(name, "block/template/template_vertical_corner_1", texture);
    }
    private  <T extends BlockModelBuilder> T verticalCornerTwo(String name, ResourceLocation texture) {
        return directionalPar(name, "block/template/template_vertical_corner_2", texture);
    }
    private  <T extends BlockModelBuilder> T verticalCornerFour(String name, ResourceLocation texture) {
        return directionalPar(name, "block/template/template_vertical_corner_4", texture);
    }
    private  <T extends BlockModelBuilder> T verticalCornerSix(String name, ResourceLocation texture) {
        return directionalPar(name, "block/template/template_vertical_corner_6", texture);
    }
    private void verticalCorner(Block block, ModelFile one, ModelFile two, ModelFile three, ModelFile four){
        getVariantBuilder(block)
                .partialState().with(VerticalCorner.DIRECTION, Direction.EAST).with(VerticalCorner.LAYERS, 1)
                .addModels(new ConfiguredModel(one, 0, 90, true))
                .partialState().with(VerticalCorner.DIRECTION, Direction.EAST).with(VerticalCorner.LAYERS, 2)
                .addModels(new ConfiguredModel(two, 0, 90, true))
                .partialState().with(VerticalCorner.DIRECTION, Direction.EAST).with(VerticalCorner.LAYERS, 3)
                .addModels(new ConfiguredModel(three, 0, 90, true))
                .partialState().with(VerticalCorner.DIRECTION, Direction.EAST).with(VerticalCorner.LAYERS, 4)
                .addModels(new ConfiguredModel(four, 0, 90, true))

                .partialState().with(VerticalCorner.DIRECTION, Direction.NORTH).with(VerticalCorner.LAYERS, 1)
                .addModels(new ConfiguredModel(one, 0, 0, true))
                .partialState().with(VerticalCorner.DIRECTION, Direction.NORTH).with(VerticalCorner.LAYERS, 2)
                .addModels(new ConfiguredModel(two, 0, 0, true))
                .partialState().with(VerticalCorner.DIRECTION, Direction.NORTH).with(VerticalCorner.LAYERS, 3)
                .addModels(new ConfiguredModel(three, 0, 0, true))
                .partialState().with(VerticalCorner.DIRECTION, Direction.NORTH).with(VerticalCorner.LAYERS, 4)
                .addModels(new ConfiguredModel(four, 0, 0, true))

                .partialState().with(VerticalCorner.DIRECTION, Direction.SOUTH).with(VerticalCorner.LAYERS, 1)
                .addModels(new ConfiguredModel(one, 0, 180, true))
                .partialState().with(VerticalCorner.DIRECTION, Direction.SOUTH).with(VerticalCorner.LAYERS, 2)
                .addModels(new ConfiguredModel(two, 0, 180, true))
                .partialState().with(VerticalCorner.DIRECTION, Direction.SOUTH).with(VerticalCorner.LAYERS, 3)
                .addModels(new ConfiguredModel(three, 0, 180, true))
                .partialState().with(VerticalCorner.DIRECTION, Direction.SOUTH).with(VerticalCorner.LAYERS, 4)
                .addModels(new ConfiguredModel(four, 0, 180, true))

                .partialState().with(VerticalCorner.DIRECTION, Direction.WEST).with(VerticalCorner.LAYERS, 1)
                .addModels(new ConfiguredModel(one, 0, 270, true))
                .partialState().with(VerticalCorner.DIRECTION, Direction.WEST).with(VerticalCorner.LAYERS, 2)
                .addModels(new ConfiguredModel(two, 0, 270, true))
                .partialState().with(VerticalCorner.DIRECTION, Direction.WEST).with(VerticalCorner.LAYERS, 3)
                .addModels(new ConfiguredModel(three, 0, 270, true))
                .partialState().with(VerticalCorner.DIRECTION, Direction.WEST).with(VerticalCorner.LAYERS, 4)
                .addModels(new ConfiguredModel(four, 0, 270, true));
    }
    @SuppressWarnings("unchecked")
    protected <T extends BlockModelBuilder> T texBotTopPar(String name, String parent, ResourceLocation texture, ResourceLocation bottom, ResourceLocation top) {
        return (T) withExistingParent(name, parent)
                .texture("texture", texture)
                .texture("bottom", bottom)
                .texture("top", top)
                .texture("particle", texture);
    }

    @SuppressWarnings("unchecked")
    protected <T extends BlockModelBuilder> T directionalPar(String name, String parent, ResourceLocation texture) {
        return (T) withExistingParent(name, parent)
                .texture("east", texture)
                .texture("north", texture)
                .texture("south", texture)
                .texture("west", texture)
                .texture("bottom", texture)
                .texture("top", texture)
                .texture("particle", texture);
    }

    @SuppressWarnings("unchecked")
    protected <T extends BlockModelBuilder> T texTopPar(String name, String parent, ResourceLocation texture, ResourceLocation top) {
        return (T) withExistingParent(name, parent)
                .texture("texture", texture)
                .texture("top", top)
                .texture("particle", texture);
    }

    @SuppressWarnings("unchecked")
    protected <T extends BlockModelBuilder> T sideTopPar(String name, String parent, ResourceLocation side, ResourceLocation top) {
        return (T) withExistingParent(name, parent)
                .texture("side", side)
                .texture("top", top)
                .texture("particle", side);
    }

    @SuppressWarnings("unchecked")
    protected <T extends BlockModelBuilder> T texPar(String name, String parent, ResourceLocation texture) {
        return (T) withExistingParent(name, parent)
                .texture("texture", texture)
                .texture("particle", texture);
    }

    @SuppressWarnings("unchecked")
    protected <T extends BlockModelBuilder> T sidePar(String name, String parent, ResourceLocation texture) {
        return (T) withExistingParent(name, parent)
                .texture("side", texture)
                .texture("particle", texture);
    }

    @SuppressWarnings("unchecked")
    public <T extends BlockModelBuilder> T withExistingParent(String name, String parent) {
        return (T) models().withExistingParent(name, modLoc(parent));
    }

    public ResourceLocation key(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }

    public String name(Block block) {
        return key(block).getPath();
    }
}
