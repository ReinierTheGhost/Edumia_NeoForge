package com.legends.edumia.datagen.custom;

import com.legends.edumia.blocks.ArrowSlit;
import com.legends.edumia.blocks.AxialSlabBlock;
import com.legends.edumia.blocks.Balustrade;
import com.legends.edumia.blocks.EdumiaBlockStates;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
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

    @SuppressWarnings({"unused", "unchecked"})
    protected <T extends BlockModelBuilder> T texBotTopPar(String name, String parent, ResourceLocation texture, ResourceLocation bottom, ResourceLocation top) {
        return (T) withExistingParent(name, parent)
                .texture("texture", texture)
                .texture("bottom", bottom)
                .texture("top", top)
                .texture("particle", texture);
    }

    @SuppressWarnings({"unused", "unchecked"})
    protected <T extends BlockModelBuilder> T sideTopPar(String name, String parent, ResourceLocation side, ResourceLocation top) {
        return (T) withExistingParent(name, parent)
                .texture("side", side)
                .texture("top", top)
                .texture("particle", side);
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
