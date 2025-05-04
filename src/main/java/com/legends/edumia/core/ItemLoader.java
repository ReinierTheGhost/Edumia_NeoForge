package com.legends.edumia.core;

import com.legends.edumia.Edumia;
import com.legends.edumia.entity.EdumiaEntities;
import com.legends.edumia.items.EdumiaFoodComponent;
import com.legends.edumia.items.WorldTeleporterItem;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemLoader {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Edumia.MOD_ID);

    public static final DeferredItem<Item> ABOMINABLE_BLADE = ITEMS.register("abominable_blade",
            () -> new SwordItem(Tiers.IRON, new Item.Properties().attributes(SwordItem.createAttributes(Tiers.IRON, 3, -2.4F))));

    public static final DeferredItem<Item> BAMBOO_STAFF = ITEMS.register("bamboo_staff",
            () -> new SwordItem(Tiers.IRON, new Item.Properties().attributes(SwordItem.createAttributes(Tiers.IRON, 5, -5F))));
    public static final DeferredItem<Item> AIR_SCEPTRE = ITEMS.register("air_sceptre",
            () -> new SwordItem(Tiers.IRON, new Item.Properties().attributes(SwordItem.createAttributes(Tiers.IRON, 5, -5F))));

    public static final DeferredItem<Item> GENSAI_AXE = ITEMS.register("gensai_axe",
            () -> new AxeItem(Tiers.IRON,  new Item.Properties().attributes(AxeItem.createAttributes(Tiers.IRON, 3, -2.4f))));
    public static final DeferredItem<Item> GENSAI_HOE = ITEMS.register("gensai_hoe",
            () -> new HoeItem(Tiers.IRON,  new Item.Properties().attributes(AxeItem.createAttributes(Tiers.IRON, 3, -2.4f))));
    public static final DeferredItem<Item> GENSAI_PICKAXE = ITEMS.register("gensai_pickaxe",
            () -> new PickaxeItem(Tiers.IRON,  new Item.Properties().attributes(AxeItem.createAttributes(Tiers.IRON, 3, -2.4f))));
    public static final DeferredItem<Item> GENSAI_SHOVEL = ITEMS.register("gensai_shovel",
            () -> new ShovelItem(Tiers.IRON,  new Item.Properties().attributes(AxeItem.createAttributes(Tiers.IRON, 3, -2.4f))));

    public static final DeferredItem<Item> GENSAI_STEEL = ITEMS.register("gensai_steel",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> LEGENDS_COIN = ITEMS.register("legends_coin",
            () -> new WorldTeleporterItem(new Item.Properties().stacksTo(1)));

    //    public static final RegistryObject<Item> ATLAS = ITEMS.register("atlas",
//            () -> new EdumiaMapItem(new Item.Properties().stacksTo(1)));
    //gems
    public static final DeferredItem<Item> GEM_FINE_AMBER = ITEMS.register("gem_fine_amber",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GEM_FLAWED_AMBER = ITEMS.register("gem_flawed_amber",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GEM_FLAWLESS_AMBER = ITEMS.register("gem_flawless_amber",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GEM_PERFECT_AMBER = ITEMS.register("gem_perfect_amber",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GEM_ROUGH_AMBER = ITEMS.register("gem_rough_amber",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GEM_FINE_AMETHYST = ITEMS.register("gem_fine_amethyst",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GEM_FLAWED_AMETHYST = ITEMS.register("gem_flawed_amethyst",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GEM_FLAWLESS_AMETHYST = ITEMS.register("gem_flawless_amethyst",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GEM_PERFECT_AMETHYST = ITEMS.register("gem_perfect_amethyst",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GEM_ROUGH_AMETHYST = ITEMS.register("gem_rough_amethyst",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GEM_FINE_JADE = ITEMS.register("gem_fine_jade",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GEM_FLAWED_JADE = ITEMS.register("gem_flawed_jade",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GEM_FLAWLESS_JADE = ITEMS.register("gem_flawless_jade",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GEM_PERFECT_JADE = ITEMS.register("gem_perfect_jade",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GEM_ROUGH_JADE = ITEMS.register("gem_rough_jade",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GEM_FINE_JASPER = ITEMS.register("gem_fine_jasper",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GEM_FLAWED_JASPER = ITEMS.register("gem_flawed_jasper",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GEM_FLAWLESS_JASPER = ITEMS.register("gem_flawless_jasper",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GEM_PERFECT_JASPER = ITEMS.register("gem_perfect_jasper",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GEM_ROUGH_JASPER = ITEMS.register("gem_rough_jasper",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GEM_FINE_RUBY = ITEMS.register("gem_fine_ruby",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GEM_FLAWED_RUBY = ITEMS.register("gem_flawed_ruby",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GEM_FLAWLESS_RUBY = ITEMS.register("gem_flawless_ruby",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GEM_PERFECT_RUBY = ITEMS.register("gem_perfect_ruby",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GEM_ROUGH_RUBY = ITEMS.register("gem_rough_ruby",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GEM_FINE_SAPPHIRE = ITEMS.register("gem_fine_sapphire",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GEM_FLAWED_SAPPHIRE = ITEMS.register("gem_flawed_sapphire",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GEM_FLAWLESS_SAPPHIRE = ITEMS.register("gem_flawless_sapphire",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GEM_PERFECT_SAPPHIRE = ITEMS.register("gem_perfect_sapphire",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GEM_ROUGH_SAPPHIRE = ITEMS.register("gem_rough_sapphire",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GEM_FINE_TOPAZ = ITEMS.register("gem_fine_topaz",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GEM_FLAWED_TOPAZ = ITEMS.register("gem_flawed_topaz",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GEM_FLAWLESS_TOPAZ = ITEMS.register("gem_flawless_topaz",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GEM_PERFECT_TOPAZ = ITEMS.register("gem_perfect_topaz",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GEM_ROUGH_TOPAZ = ITEMS.register("gem_rough_topaz",
            () -> new Item(new Item.Properties()));


    /**
     * food Items
     */

    public static final DeferredItem<Item> TEA_SAKURA_PETALS = ITEMS.register("tea_sakura_petals",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TEA_MINT_LEAVES = ITEMS.register("tea_mint_leaves",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TEA_LEAF = ITEMS.register("tea_leaf",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TEA_LILY_PETALS = ITEMS.register("tea_lily_petals",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TEA_WHITE_JADE_PETALS = ITEMS.register("tea_white_jade_petals",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TEA_HIBISCUS_PETALS = ITEMS.register("tea_hibiscus_petals",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TEA_JASMINE_PETALS = ITEMS.register("tea_jasmine_petals",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TEA_CINNAMON_STICK = ITEMS.register("tea_cinnamon_stick",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TEA_WHITE_DRAGON_PETALS = ITEMS.register("tea_white_dragon_petals",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BROCCOLI = ITEMS.register("broccoli",
            () -> new Item(new Item.Properties().food(EdumiaFoodComponent.BROCCOLI)));
    public static final DeferredItem<Item> PAPRIKA_GREEN = ITEMS.register("paprika_green",
            () -> new Item(new Item.Properties().food(EdumiaFoodComponent.PAPRIKA)));
    public static final DeferredItem<Item> RAMEN = ITEMS.register("ramen",
            () -> new Item(new Item.Properties().food(EdumiaFoodComponent.RAMEN)));
    public static final DeferredItem<Item> RAMEN_BEEF = ITEMS.register("ramen_beef",
            () -> new Item(new Item.Properties().food(EdumiaFoodComponent.RAMEN_BEEF)));
    public static final DeferredItem<Item> RAMEN_PORK = ITEMS.register("ramen_pork",
            () -> new Item(new Item.Properties().food(EdumiaFoodComponent.RAMEN_PORK)));
    public static final DeferredItem<Item> RAMEN_SHRIMPS = ITEMS.register("ramen_shrimps",
            () -> new Item(new Item.Properties().food(EdumiaFoodComponent.RAMEN_SHRIMPS)));
    public static final DeferredItem<Item> RAMEN_VEGI = ITEMS.register("ramen_vegi",
            () -> new Item(new Item.Properties().food(EdumiaFoodComponent.RAMEN_VEGI)));
    public static final DeferredItem<Item> RED_GRAPES = ITEMS.register("red_grapes",
            () -> new Item(new Item.Properties().food(EdumiaFoodComponent.RED_GRAPES)));
    public static final DeferredItem<Item> RICE = ITEMS.register("rice",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RICE_BALL = ITEMS.register("rice_ball",
            () -> new Item(new Item.Properties().food(EdumiaFoodComponent.RICE_BALL)));
    public static final DeferredItem<Item> SPINACH = ITEMS.register("spinach",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TOMATO = ITEMS.register("tomato",
            () -> new Item(new Item.Properties().food(EdumiaFoodComponent.TOMATO)));
    public static final DeferredItem<Item> CHEESE = ITEMS.register("cheese",
            () -> new Item(new Item.Properties().food(EdumiaFoodComponent.CHEESE)));
    public static final DeferredItem<Item> LETTUCE = ITEMS.register("lettuce",
            () -> new Item(new Item.Properties().food(EdumiaFoodComponent.LETTUCE)));
    public static final DeferredItem<Item> BANANA = ITEMS.register("banana",
            () -> new Item(new Item.Properties().food(EdumiaFoodComponent.BANANA)));
    public static final DeferredItem<Item> BANANA_BREAD = ITEMS.register("banana_bread",
            () -> new Item(new Item.Properties().food(EdumiaFoodComponent.BANANA_BREAD)));
    public static final DeferredItem<Item> MANGO = ITEMS.register("mango",
            () -> new Item(new Item.Properties().food(EdumiaFoodComponent.MANGO)));
    public static final DeferredItem<Item> DATE = ITEMS.register("date",
            () -> new Item(new Item.Properties().food(EdumiaFoodComponent.DATE)));
    public static final DeferredItem<Item> CHERRY = ITEMS.register("cherry",
            () -> new Item(new Item.Properties().food(EdumiaFoodComponent.CHERRY)));
    public static final DeferredItem<Item> PEAR = ITEMS.register("pear",
            () -> new Item(new Item.Properties().food(EdumiaFoodComponent.PEAR)));
    public static final DeferredItem<Item> GREEN_APPLE = ITEMS.register("green_apple",
            () -> new Item(new Item.Properties().food(Foods.APPLE)));

    public static final DeferredItem<Item> FAIRY_SPAWN_EGG = ITEMS.register("fairy_spawn_egg",
            () -> new DeferredSpawnEggItem(EdumiaEntities.FAIRY_CIVILIAN, 0x31afaf, 0xffac00,
                    new Item.Properties()));

    public static final DeferredItem<Item> HUMAN_SPAWN_EGG = ITEMS.register("human_spawn_egg",
            () -> new DeferredSpawnEggItem(EdumiaEntities.HUMAN_CIVILIAN, 0x7A5D4A, 0x7C3D12,
                    new Item.Properties()));

    public static final DeferredItem<Item> HIGH_ELVEN_SPAWN_EGG = ITEMS.register("high_elven_spawn_egg",
            () -> new DeferredSpawnEggItem(EdumiaEntities.HIGH_ELVEN_CIVILIAN, 0x40AFE8, 0x11FFCF,
                    new Item.Properties()));

    public static final DeferredItem<Item> HIGH_ELVEN_BANNER_PATTERN = ITEMS.register("high_elven_banner_pattern",
            () -> new BannerPatternItem(TagLoader.BannerPatterns.HIGH_ELVEN_PATTERN, new Item.Properties()
                    .stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> DARK_ELVEN_BANNER_PATTERN = ITEMS.register("dark_elven_banner_pattern",
            () -> new BannerPatternItem(TagLoader.BannerPatterns.DARK_ELVEN_PATTERN, new Item.Properties()
                    .stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> DEMON_BANNER_PATTERN = ITEMS.register("demon_banner_pattern",
            () -> new BannerPatternItem(TagLoader.BannerPatterns.DEMONS_PATTERN, new Item.Properties()
                    .stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> HUMAN_BANNER_PATTERN = ITEMS.register("human_banner_pattern",
            () -> new BannerPatternItem(TagLoader.BannerPatterns.HUMANS_PATTERN, new Item.Properties()
                    .stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> FAIRY_BANNER_PATTERN = ITEMS.register("fairy_banner_pattern",
            () -> new BannerPatternItem(TagLoader.BannerPatterns.FAIRIES_PATTERN, new Item.Properties()
                    .stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> GENSAI_BANNER_PATTERN = ITEMS.register("gensai_banner_pattern",
            () -> new BannerPatternItem(TagLoader.BannerPatterns.GENSAI_PATTERN, new Item.Properties()
                    .stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> ORC_BANNER_PATTERN = ITEMS.register("orc_banner_pattern",
            () -> new BannerPatternItem(TagLoader.BannerPatterns.ORCS_PATTERN, new Item.Properties()
                    .stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> OGRE_BANNER_PATTERN = ITEMS.register("ogre_banner_pattern",
            () -> new BannerPatternItem(TagLoader.BannerPatterns.OGRES_PATTERN, new Item.Properties()
                    .stacksTo(1).rarity(Rarity.RARE)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
