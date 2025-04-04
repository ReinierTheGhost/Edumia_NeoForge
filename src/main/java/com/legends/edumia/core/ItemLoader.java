package com.legends.edumia.core;

import com.legends.edumia.Edumia;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemLoader {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Edumia.MOD_ID);
    public static final DeferredItem<Item> GENSAI_STEEL = ITEMS.register("gensai_steel",
            () -> new Item(new Item.Properties()));
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
