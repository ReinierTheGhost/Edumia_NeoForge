package com.legends.edumia.datagen;

import com.legends.edumia.Edumia;
import com.legends.edumia.core.ItemLoader;
import com.legends.edumia.datagen.helpers.models.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.WallBlock;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Edumia.MOD_ID, existingFileHelper);
    }
    @Override
    protected void registerModels() {
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

        for (Block block : SimpleTallFlowerModel.blocks){
            genericBlockItem(block, block, "_top");
        }

        for (SimpleButtonModel.Button block : SimpleButtonModel.blocks){
            buttonItem(block.button(), block.block());
        }

        for (SimpleFenceModel.Fence block : SimpleFenceModel.blocks){
            fenceItem(block.fence(), block.block());
        }

        for (SimpleReedsModel.Reed block : SimpleReedsModel.blocks){
            genericBlockItemTexture(block.reed(), block.reed());
        }

        for (Item item : SimpleBasicItemModel.items){
            basicItem(item);
        }

        for (Block item : SimpleSaplingModel.blocks){
            saplingItem(item);
        }

        withExistingParent(ItemLoader.FAIRY_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ItemLoader.HUMAN_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ItemLoader.HIGH_ELVEN_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ItemLoader.DARK_ELVEN_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));

        withExistingParent(ItemLoader.BUTTERFLY_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ItemLoader.DRAGONFLY_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        genericBannerPattern(ItemLoader.HIGH_ELVEN_BANNER_PATTERN.get());
        genericBannerPattern(ItemLoader.DARK_ELVEN_BANNER_PATTERN.get());
        genericBannerPattern(ItemLoader.HUMAN_BANNER_PATTERN.get());
        genericBannerPattern(ItemLoader.DEMON_BANNER_PATTERN.get());
        genericBannerPattern(ItemLoader.FAIRY_BANNER_PATTERN.get());
        genericBannerPattern(ItemLoader.GENSAI_BANNER_PATTERN.get());
        genericBannerPattern(ItemLoader.OGRE_BANNER_PATTERN.get());
        genericBannerPattern(ItemLoader.ORC_BANNER_PATTERN.get());
    }

    public void wallItem(WallBlock block, Block baseBlock) {
        ResourceLocation name = this.key(baseBlock);
        this.withExistingParent(name(block), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.fromNamespaceAndPath(name.getNamespace(),
                        "block/" + name(baseBlock)));
    }

    public void buttonItem(ButtonBlock block, Block baseBlock) {
        this.withExistingParent(name(block), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID,
                        "block/" + name(baseBlock)));
    }

    public void fenceItem(FenceBlock block, Block baseBlock) {
        ResourceLocation name = this.key(baseBlock);
        this.withExistingParent(name(block), mcLoc("block/fence_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(name.getNamespace(),
                        "block/" + name(baseBlock)));
    }


    public void genericBlockItem(Block block, Block baseBlock) {
        this.withExistingParent(name(block), mcLoc("item/generated"))
                .texture("layer0",  ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID,
                        "block/" + name(baseBlock)));
    }

    public void genericBlockItem(Block block, Block baseBlock, String appendix) {
        this.withExistingParent(name(block), mcLoc("item/generated"))
                .texture("layer0",  ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID,
                        "block/" + name(baseBlock) + appendix));
    }

    public void genericBlockItemTexture(Block block, Block baseBlock) {
        this.withExistingParent(name(block), mcLoc("item/generated"))
                .texture("layer0",  ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID,
                        "item/" + name(baseBlock)));
    }
    public void genericBannerPattern(Item item) {
        this.withExistingParent(name(item), mcLoc("item/generated"))
                .texture("layer0",  ResourceLocation.withDefaultNamespace(
                        "item/creeper_banner_pattern"));
    }
    public void genericBlockItemTexture(Block block, Block baseBlock, String appendix) {
        this.withExistingParent(name(block), mcLoc("item/generated"))
                .texture("layer0",  ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID,
                        "item/" + name(baseBlock) + appendix));
    }

    private ItemModelBuilder saplingItem(Block item) {
        return withExistingParent(name(item),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID,"block/" + name(item)));
    }

    public ResourceLocation key(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }

    public ResourceLocation key(Item item) {
        return BuiltInRegistries.ITEM.getKey(item);
    }

    public String name(Item item) {
        return key(item).getPath();
    }

    public String name(Block block) {
        return key(block).getPath();
    }
}
