package com.legends.edumia.resources.datas;

import com.legends.edumia.Edumia;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;

public enum FactionType {
    FACTION,
    SUBFACTION;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

    public MutableComponent getName(){
        return Component.translatable("faction_type.".concat(ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID,
                toString()).toLanguageKey()));
    }
}
