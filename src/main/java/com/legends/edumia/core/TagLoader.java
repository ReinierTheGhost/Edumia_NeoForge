package com.legends.edumia.core;

import com.legends.edumia.Edumia;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BannerPatternTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BannerPattern;

public class TagLoader {
    public static class Blocks {

        public static final TagKey<Block> GRAVEL = createTag("gravel");
        public static final TagKey<Block> DIRT = createTag("dirt");

        public static final TagKey<Block> TREE_BRANCH_REPLACEABLE = createTag("tree_branch_replaceable");
        public static final TagKey<Block> VOLCANIC_PLANT_SURFACE = createTag("volcanic_plant_surface");
        public static final TagKey<Block> GROUND_MAHOGANY_SAPLING = createTag("mahogany_sapling");
        public static final TagKey<Block> REEDS_PLACEABLE_ON = createTag("reeds_placeable_on");
        public static final TagKey<Block> PILLARS = createTag("pillars");
        public static final TagKey<Block> ALMOND_LOGS = createTag("almond_logs");
        public static final TagKey<Block> APPLE_LOGS = createTag("apple_logs");
        public static final TagKey<Block> ASPEN_LOGS = createTag("aspen_logs");
        public static final TagKey<Block> BANANA_LOGS = createTag("banana_logs");
        public static final TagKey<Block> BEECH_LOGS = createTag("beech_logs");
        public static final TagKey<Block> BLACKTHORN_LOGS = createTag("blackthorn_logs");
        public static final TagKey<Block> BLACK_OAK_LOGS = createTag("black_oak_logs");
        public static final TagKey<Block> CEDAR_LOGS = createTag("cedar_logs");
        public static final TagKey<Block> CHARRED_LOGS = createTag("charred_logs");
        public static final TagKey<Block> CHERRY_LOGS = createTag("cherry_logs");
        public static final TagKey<Block> CYPRESS_LOGS = createTag("cypress_logs");
        public static final TagKey<Block> DRAGON_BLOOD_LOGS = createTag("dragon_blood_logs");
        public static final TagKey<Block> FIR_LOGS = createTag("fir_logs");
        public static final TagKey<Block> GHOST_GUM_LOGS = createTag("ghost_gum_logs");
        public static final TagKey<Block> GREEN_OAK_LOGS = createTag("green_oak_logs");
        public static final TagKey<Block> HOLLY_LOGS = createTag("holly_logs");
        public static final TagKey<Block> KAPOK_LOGS = createTag("kapok_logs");
        public static final TagKey<Block> LARCH_LOGS = createTag("larch_logs");
        public static final TagKey<Block> MAHOGANY_LOGS = createTag("mahogany_logs");
        public static final TagKey<Block> MANGO_LOGS = createTag("mango_logs");
        public static final TagKey<Block> MAPLE_LOGS = createTag("maple_logs");
        public static final TagKey<Block> OLIVE_LOGS = createTag("olive_logs");
        public static final TagKey<Block> PALM_LOGS = createTag("palm_logs");
        public static final TagKey<Block> PEAR_LOGS = createTag("pear_logs");
        public static final TagKey<Block> PINE_LOGS = createTag("pine_logs");
        public static final TagKey<Block> RED_OAK_LOGS = createTag("red_oak_logs");
        public static final TagKey<Block> REDWOOD_LOGS = createTag("redwood_logs");
        public static final TagKey<Block> SILVER_SPRUCE_LOGS = createTag("silver_spruce_logs");
        public static final TagKey<Block> WHITE_ASH_LOGS = createTag("white_ash_logs");
        public static final TagKey<Block> WILLOW_LOGS = createTag("willow_logs");
        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, name));
        }
    }

    public static class Items {

        public static final TagKey<Item> ALMOND_LOGS = createTag("almond_logs");
        public static final TagKey<Item> APPLE_LOGS = createTag("apple_logs");
        public static final TagKey<Item> ASPEN_LOGS = createTag("aspen_logs");
        public static final TagKey<Item> BANANA_LOGS = createTag("banana_logs");
        public static final TagKey<Item> BEECH_LOGS = createTag("beech_logs");
        public static final TagKey<Item> BLACKTHORN_LOGS = createTag("blackthorn_logs");
        public static final TagKey<Item> BLACK_OAK_LOGS = createTag("black_oak_logs");
        public static final TagKey<Item> CEDAR_LOGS = createTag("cedar_logs");
        public static final TagKey<Item> CHARRED_LOGS = createTag("charred_logs");
        public static final TagKey<Item> CHERRY_LOGS = createTag("cherry_logs");
        public static final TagKey<Item> CYPRESS_LOGS = createTag("cypress_logs");
        public static final TagKey<Item> DRAGON_BLOOD_LOGS = createTag("dragon_blood_logs");
        public static final TagKey<Item> FIR_LOGS = createTag("fir_logs");
        public static final TagKey<Item> GHOST_GUM_LOGS = createTag("ghost_gum_logs");
        public static final TagKey<Item> GREEN_OAK_LOGS = createTag("green_oak_logs");
        public static final TagKey<Item> HOLLY_LOGS = createTag("holly_logs");
        public static final TagKey<Item> KAPOK_LOGS = createTag("kapok_logs");
        public static final TagKey<Item> LARCH_LOGS = createTag("larch_logs");
        public static final TagKey<Item> MAHOGANY_LOGS = createTag("mahogany_logs");
        public static final TagKey<Item> MANGO_LOGS = createTag("mango_logs");
        public static final TagKey<Item> MAPLE_LOGS = createTag("maple_logs");
        public static final TagKey<Item> OLIVE_LOGS = createTag("olive_logs");
        public static final TagKey<Item> PALM_LOGS = createTag("palm_logs");
        public static final TagKey<Item> PEAR_LOGS = createTag("pear_logs");
        public static final TagKey<Item> PINE_LOGS = createTag("pine_logs");
        public static final TagKey<Item> RED_OAK_LOGS = createTag("red_oak_logs");
        public static final TagKey<Item> REDWOOD_LOGS = createTag("redwood_logs");
        public static final TagKey<Item> SILVER_SPRUCE_LOGS = createTag("silver_spruce_logs");
        public static final TagKey<Item> WHITE_ASH_LOGS = createTag("white_ash_logs");
        public static final TagKey<Item> WILLOW_LOGS = createTag("willow_logs");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, name));
        }
    }

    public static class BannerPatterns {
        public static final TagKey<BannerPattern> HIGH_ELVEN_PATTERN = createTag("high_elven_pattern");
        public static final TagKey<BannerPattern> DARK_ELVEN_PATTERN  = createTag("dark_elven_pattern");
        public static final TagKey<BannerPattern> DEMONS_PATTERN  = createTag("demons_pattern");
        public static final TagKey<BannerPattern> HUMANS_PATTERN  = createTag("humans_pattern");
        public static final TagKey<BannerPattern> FAIRIES_PATTERN  = createTag("fairies_pattern");
        public static final TagKey<BannerPattern> GENSAI_PATTERN  = createTag("gensai_pattern");
        public static final TagKey<BannerPattern> ORCS_PATTERN  = createTag("orcs_pattern");
        public static final TagKey<BannerPattern> OGRES_PATTERN  = createTag("ogres_pattern");

        private static TagKey<BannerPattern> createTag(String name) {
            return TagKey.create(Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID,"pattern_item/" + name));
        }
    }
}
