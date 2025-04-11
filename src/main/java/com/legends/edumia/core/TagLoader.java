package com.legends.edumia.core;

import com.legends.edumia.Edumia;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class TagLoader {
    public static class Blocks {

        public static final TagKey<Block> GRAVEL = createTag("gravel");
        public static final TagKey<Block> DIRT = createTag("dirt");

        public static final TagKey<Block> TREE_BRANCH_REPLACEABLE = createTag("tree_branch_replaceable");
        public static final TagKey<Block> VOLCANIC_PLANT_SURFACE = createTag("volcanic_plant_surface");
        public static final TagKey<Block> GROUND_MAHOGANY_SAPLING = createTag("mahogany_sapling");
        public static final TagKey<Block> REEDS_PLACEABLE_ON = createTag("reeds_placeable_on");
        public static final TagKey<Block> PILLARS = createTag("pillars");
        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, name));
        }
    }

    public static class Items {
        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, name));
        }
    }
}
