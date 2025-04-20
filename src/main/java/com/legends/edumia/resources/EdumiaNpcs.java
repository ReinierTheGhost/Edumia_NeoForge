package com.legends.edumia.resources;

import com.legends.edumia.Edumia;
import com.legends.edumia.resources.datas.npcs.NpcData;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public class EdumiaNpcs {
    public static final String PATH = "npcs";
    public static final ResourceKey<Registry<NpcData>> NPC_KEY =
            ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, PATH));
}
