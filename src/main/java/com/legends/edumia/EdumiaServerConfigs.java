package com.legends.edumia;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(modid = Edumia.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class EdumiaServerConfigs {


    public static ModConfigSpec.BooleanValue ENABLE_KEEP_RACE_ON_DIMENSION_SWAP;
    public static ModConfigSpec.BooleanValue ENABLE_RETURN_TO_OVERWORLD;
    public static ModConfigSpec.BooleanValue ENABLE_FACTION_RESET;
    public static ModConfigSpec.ConfigValue<Integer> DELAY_ON_TELEPORT_CONFIRMATION;

    public static final ModConfigSpec SPEC;

    static {
        ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
        BUILDER.comment("Server Configs");
        BUILDER.comment("This file stores server/host configuration options for the Middle-earth mod.");
        BUILDER.comment("");

        BUILDER.push("World_Configurations");
        ENABLE_RETURN_TO_OVERWORLD =
                BUILDER.comment("Should players be able to return to the overworld by reusing the coin of legends?")
                        .comment("Accept values: boolean | Default: true")
                        .define("enableReturnToOverworld", true);
        ENABLE_KEEP_RACE_ON_DIMENSION_SWAP =
                BUILDER.comment("Should players keep their race when returning to the Overworld")
                        .comment("Accept values: boolean | Default: true")
                        .define("enableKeepRaceOnDimensionSwap", true);
        BUILDER.pop();

        BUILDER.push("Faction_Configurations");
        ENABLE_FACTION_RESET = BUILDER.comment("Enable or disable faction reset")
                .comment("Accept values: boolean | Default: true")
                .define("enableFactionReset", true);

        DELAY_ON_TELEPORT_CONFIRMATION =
                BUILDER.comment("Amount of time before teleporting")
                        .comment("Accept values: int | Default: 3")
                        .define("delayOnTeleportConfirmation", 3);
        BUILDER.pop();

        SPEC = BUILDER.build();
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {

    }
}
