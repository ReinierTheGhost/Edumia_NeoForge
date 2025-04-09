package com.legends.edumia.datagen;

import com.legends.edumia.blocks.blocksets.*;
import com.legends.edumia.core.BlockLoader;
import com.legends.edumia.datagen.custom.loot_tables.*;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {

    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {

        for (Block block : BlockDrops.blocks){
            dropSelf(block);
        }

        for (Block block : SilkTouchDrops.blocks){
            dropWhenSilkTouch(block);
        }

        for (Block block : LeavesDrops.blocks){
            dropSelf(block);
        }

        for (Block block : DoorDrops.blocks){
            add(block, b -> createDoorTable(block));
        }
        for (Block block : PottedFlowerDrops.blocks){
            dropPottedContents(block);
        }

    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {

        List<Block> allBlocks = new ArrayList<>();

        // Add blocks from BlockLoader
        BlockLoader.BLOCKS.getEntries()
                .stream()
                .map(Holder::value)
                .forEach(allBlocks::add);

        // Add blocks from BuildingSets
        BuildingSets.BLOCKS.getEntries()
                .stream()
                .map(Holder::value)
                .forEach(allBlocks::add);

        ClayTilingSets.BLOCKS.getEntries()
                .stream()
                .map(Holder::value)
                .forEach(allBlocks::add);

        GlassSets.BLOCKS.getEntries()
                .stream()
                .map(Holder::value)
                .forEach(allBlocks::add);

        StoneSets.BLOCKS.getEntries()
                .stream()
                .map(Holder::value)
                .forEach(allBlocks::add);

        WoodBlockSets.BLOCKS.getEntries()
                .stream()
                .map(Holder::value)
                .forEach(allBlocks::add);

        PaperwallSets.BLOCKS.getEntries()
                .stream()
                .map(Holder::value)
                .forEach(allBlocks::add);

        NotBrickBuildingSets.BLOCKS.getEntries()
                .stream()
                .map(Holder::value)
                .forEach(allBlocks::add);

        ModNatureBlocks.BLOCKS.getEntries()
                .stream()
                .map(Holder::value)
                .forEach(allBlocks::add);

        SandBlockSets.BLOCKS.getEntries()
                .stream()
                .map(Holder::value)
                .forEach(allBlocks::add);

        FlowerBlockSets.BLOCKS.getEntries()
                .stream()
                .map(Holder::value)
                .forEach(allBlocks::add);

        return allBlocks;
    }

}
