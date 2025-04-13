package com.legends.edumia.client.render.model;

import net.minecraft.ResourceLocationException;
import net.minecraft.resources.ResourceLocation;

public class BlockModelUtil {
    public BlockModelUtil(){

    }


    public static boolean validateTextureString(String s) {
        if (s.startsWith("#")){
            return true;
        }else {
            try {
                ResourceLocation.isValidNamespace(s);
                return true;
            } catch (ResourceLocationException var2) {
                return false;
            }
        }
    }
}
