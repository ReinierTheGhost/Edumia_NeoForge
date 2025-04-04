package com.legends.edumia.datagen;

import com.legends.edumia.Edumia;
import com.legends.edumia.core.BlockLoader;
import com.legends.edumia.core.TagLoader;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Edumia.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(TagLoader.Blocks.REEDS_PLACEABLE_ON)
                .add(BlockLoader.WHITE_SAND.get());
    }
}
