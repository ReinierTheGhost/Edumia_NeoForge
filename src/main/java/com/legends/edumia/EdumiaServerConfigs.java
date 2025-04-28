package com.legends.edumia;

import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(modid = Edumia.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class EdumiaServerConfigs {



    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static ModConfigSpec.BooleanValue ENABLE_RETURN_TO_OVERWORLD =
            BUILDER.comment("Should players be able to return to the overworld by reusing the coin of legends? ")
                    .define("enableReturnToOverworld", true);
    public static ModConfigSpec.BooleanValue ENABLE_FACTION_RESET
            = BUILDER.comment("Enable or disable faction reset").define("enableFactionReset", true);
    public static ModConfigSpec.IntValue DELAY_ON_TELEPORT_CONFIRMATION =
            (ModConfigSpec.IntValue) BUILDER.comment("Amount of time before teleporting").define("delayOnTeleportConfirmation", 3);

    static final ModConfigSpec SPEC = BUILDER.build();
}
