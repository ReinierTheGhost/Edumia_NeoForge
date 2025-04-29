package com.legends.edumia;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(modid = Edumia.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class EdumiaClientConfig {

    public static final ModConfigSpec SPEC;

    public static ModConfigSpec.BooleanValue ENABLE_MAP_OVERLAY;



    static {
        ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
        BUILDER.comment("Client configs");
        BUILDER.comment("This file stores client configuration options for the Edumia mod.");
        BUILDER.comment("");
        BUILDER.push("GUI_configurations");
        ENABLE_MAP_OVERLAY =
                BUILDER.comment("Should you see the map overlay button in the Edumia map gui?")
                        .comment("Accept values: boolean | Default: true")
                        .define("enableMapOverlay", true);
        SPEC = BUILDER.build();
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {

    }
}
