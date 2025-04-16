package com.legends.edumia.utils;

import com.legends.edumia.Edumia;
import net.minecraft.resources.ResourceLocation;

public class IdentifierUtil {
    public static ResourceLocation getIdentifierFromString(String id){
        if(id.contains(":") && id.split(":").length == 2){
            return ResourceLocation.fromNamespaceAndPath(id.split(":")[0], id.split(":")[1]);
        } else {
            return ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, id);
        }
    }
}
