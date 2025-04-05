package com.legends.edumia.datagen;

import com.legends.edumia.Edumia;
import com.legends.edumia.core.BlockLoader;
import com.legends.edumia.core.ItemLoader;
import com.legends.edumia.datagen.custom.models.SimpleStairModel;
import com.legends.edumia.datagen.custom.models.SimpleWallModel;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallBlock;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

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

    }

    public void wallItem(WallBlock block, Block baseBlock) {
        this.withExistingParent(name(block), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID,
                        "block/" + name(baseBlock)));
    }

    public ResourceLocation key(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }

    public String name(Block block) {
        return key(block).getPath();
    }
}
