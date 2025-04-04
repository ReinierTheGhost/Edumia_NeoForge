package com.legends.edumia.core;

import com.legends.edumia.Edumia;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class CreativeTabLoader {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Edumia.MOD_ID);
    public static final Supplier<CreativeModeTab> EDUMIA_NATURAL_STONE_BLOCKS = CREATIVE_MODE_TAB.register("edumia_natural_stone_blocks",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(BlockLoader.WHITE_SAND))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "bismuth_items_tab"))
                    .title(Component.translatable("creativetab.edumia_natural_stone_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(BlockLoader.WHITE_SAND);

                    }).build());
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
