package com.legends.edumia;

import com.legends.edumia.client.EdumiaKeyHandler;
import com.legends.edumia.client.gui.EdumiaMasterMenuScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;

@EventBusSubscriber(modid = Edumia.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EdumiaClient {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
    }

    @EventBusSubscriber(modid = Edumia.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            int key = event.getKey();
            int scancode = event.getScanCode();
            int action = event.getAction();
            Minecraft mc = Minecraft.getInstance();
            if (EdumiaKeyHandler.MENU_KEY.matches(key, scancode) && mc.screen == null && mc.player != null){
                Screen menuScreen = EdumiaMasterMenuScreen.openMenu(mc.player);
                if (menuScreen != null){
                    mc.setScreen(menuScreen);
                }
            }
        }
    }
    @EventBusSubscriber(modid = Edumia.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(EdumiaKeyHandler.MENU_KEY);
        }
    }


}
