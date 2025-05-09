package com.legends.edumia;

import com.legends.edumia.client.EdumiaKeyHandler;
import com.legends.edumia.client.gui.EdumiaMasterMenuScreen;
import com.legends.edumia.core.blocksets.GlassSets;
import com.legends.edumia.entity.EdumiaEntities;
import com.legends.edumia.entity.animals.butterfly.ButterflyRenderer;
import com.legends.edumia.entity.animals.dragonfly.DragonflyRenderer;
import com.legends.edumia.entity.races.darkelves.DarkElvenRenderer;
import com.legends.edumia.entity.races.fairy.FairyRenderer;
import com.legends.edumia.entity.races.highelves.HighElvenRenderer;
import com.legends.edumia.entity.races.human.HumanRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;

@SuppressWarnings("deprecation")
@EventBusSubscriber(modid = Edumia.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EdumiaClient {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(EdumiaEntities.FAIRY_CIVILIAN.get(), FairyRenderer::new);
        EntityRenderers.register(EdumiaEntities.HUMAN_CIVILIAN.get(), HumanRenderer::new);
        EntityRenderers.register(EdumiaEntities.HIGH_ELVEN_CIVILIAN.get(), HighElvenRenderer::new);
        EntityRenderers.register(EdumiaEntities.DARK_ELVEN_CIVILIAN.get(), DarkElvenRenderer::new);

        EntityRenderers.register(EdumiaEntities.BUTTERFLY.get(), ButterflyRenderer::new);
        EntityRenderers.register(EdumiaEntities.DRAGONFLY.get(), DragonflyRenderer::new);

        for (GlassSets.GlassSet set : GlassSets.glassSets) {
            ItemBlockRenderTypes.setRenderLayer(set.block().get(), RenderType.translucent());
        }
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
