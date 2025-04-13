package com.legends.edumia.client;

import com.legends.edumia.Edumia;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class EdumiaKeyHandler {
    public static final String KEY_CATEGORY_EDUMIA ="key.categories." + Edumia.MOD_ID;

    public static final String KEY_BIND_MENU = "key.edumia.menu";

    public static final KeyMapping MENU_KEY = new KeyMapping(KEY_BIND_MENU, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_M, KEY_CATEGORY_EDUMIA);


    public EdumiaKeyHandler(){
    }
}
