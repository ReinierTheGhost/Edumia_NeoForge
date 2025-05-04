package com.legends.edumia.datagen;

import com.legends.edumia.Edumia;
import com.legends.edumia.core.blocksets.WoodBlockSets;
import com.legends.edumia.core.TagLoader;
import com.legends.edumia.datagen.helpers.tags.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
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
                .add(ReedsPlaceable.blocks.toArray(new Block[0]));

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(MineablePickaxe.blocks.toArray(new Block[0]));

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(RequiresStoneTool.blocks.toArray(new Block[0]));

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(RequiresIronTool.blocks.toArray(new Block[0]));

        tag(BlockTags.WALLS)
                .add(Walls.walls.toArray(new Block[0]));

        tag(BlockTags.BUTTONS)
                .add(Buttons.buttons.toArray(new Block[0]));

        tag(BlockTags.FENCE_GATES)
                .add(FenceGates.fenceGates.toArray(new Block[0]));

        tag(BlockTags.FENCES)
                .add(Fences.fences.toArray(new Block[0]));

        tag(TagLoader.Blocks.PILLARS)
                .add(Pillar.pillars.toArray(new Block[0]));

        tag(BlockTags.LOGS)
                .add(Logs.logs.toArray(new Block[0]));
        tag(BlockTags.LEAVES)
                .add(Leaves.leaves.toArray(new Block[0]));

        tag(TagLoader.Blocks.GROUND_MAHOGANY_SAPLING)
                .add(Blocks.DIRT);
        tag(BlockTags.JUNGLE_LOGS)
                .add(WoodBlockSets.MIRWOODNUT.log().get())
                .add(WoodBlockSets.MIRWOODNUT.wood().get());

        for (WoodBlockSets.SimpleBlockSet set : WoodBlockSets.sets){
            tag(set.logBlockTag())
                    .add(set.log().get())
                    .add(set.wood().get())
                    .add(set.strippedLog().get())
                    .add(set.strippedWood().get());
        }
    }
}
