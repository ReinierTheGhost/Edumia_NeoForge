package com.legends.edumia.datagen;

import com.legends.edumia.core.blocksets.*;
import com.legends.edumia.core.BlockLoader;
import com.legends.edumia.datagen.helpers.loot_tables.*;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    private final float[] LEAVES_STICK_DROP_CHANCE = new float[]{0.02f, 0.022222223f, 0.025f, 0.033333335f, 0.1f};

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

        for (LeavesDrops.LeavesDrop block : LeavesDrops.blocks){
            this.add(block.block(),
                    b -> createLeavesDrops(block.block(), block.drop(), NORMAL_LEAVES_SAPLING_CHANCES));
        }
        for (ExtraLeavesDrops.LeavesDrop block : ExtraLeavesDrops.blocks){
            this.add(block.block(),
                    b -> createExtraLeaveDrops(block.block(), block.drop(), block.extra(), NORMAL_LEAVES_SAPLING_CHANCES));
        }

        for (Block block : DoorDrops.blocks){
            add(block, b -> createDoorTable(block));
        }
        for (Block block : PottedFlowerDrops.blocks){
            dropPottedContents(block);
        }

        dropOther(BlockLoader.VOLCANIC_DIRT_PATH.get(), BlockLoader.VOLCANIC_DIRT.get());

        for (CobbleDrops.CobbleDrop block : CobbleDrops.blocks){
            add(block.block(), b ->
                    createCobbleDrop(block.block(), block.drop()));
        }

    }

    protected LootTable.Builder createExtraLeaveDrops(Block block, Block sapling, Item extraDrop, float... chances){
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createLeavesDrops(block, sapling, chances)
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .when(this.doesNotHaveShearsOrSilkTouch()).add(((LootPoolSingletonContainer.Builder)
                                this.applyExplosionCondition(block, LootItem.lootTableItem(extraDrop)))
                                .when(BonusLevelTableCondition.bonusLevelFlatChance(
                                        registrylookup.getOrThrow(Enchantments.FORTUNE),
                                        new float[]{0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F}))));
    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    protected LootTable.Builder createCobbleDrop(Block block, Block cobble) {
        return this.createSilkTouchDispatchTable(block, this.applyExplosionDecay(block,
                LootItem.lootTableItem(cobble.asItem())));
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

        GrassBlockSets.BLOCKS.getEntries()
                .stream()
                .map(Holder::value)
                .forEach(allBlocks::add);

        return allBlocks;
    }

    private LootItemCondition.Builder hasShearsOrSilkTouch() {
        return HAS_SHEARS.or(this.hasSilkTouch());
    }

    private LootItemCondition.Builder doesNotHaveShearsOrSilkTouch() {
        return this.hasShearsOrSilkTouch().invert();
    }

}
