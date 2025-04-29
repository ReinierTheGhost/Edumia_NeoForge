package com.legends.edumia.handlers;

import com.legends.edumia.EdumiaServerConfigs;
import com.legends.edumia.client.gui.OnboardingSelectionScreen;
import com.legends.edumia.client.gui.races.selection.FactionSelectionScreen;
import com.legends.edumia.network.contexts.ClientPacketContext;
import com.legends.edumia.utils.EdumiaLog;
import com.legends.edumia.world.dimension.ModDimensions;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.Level;

public class OnboardingScreenHandler {
    public static void handle(ClientPacketContext context, boolean havePlayerData, float delay){
        try{
            Level world = context.player().level();
            if(ModDimensions.isInOverworld(world)){
                Minecraft client = Minecraft.getInstance();
                if(!havePlayerData){
                    client.setScreen(new FactionSelectionScreen(delay));
                } else {
                    client.setScreen(new OnboardingSelectionScreen(delay, EdumiaServerConfigs.ENABLE_FACTION_RESET.getAsBoolean()));
                }
            }
        } catch (Exception e){
            EdumiaLog.logError("OnboardingDetailParsedPacket::Apply - trying to fetch the client data and show appropriate context.",e);
        }
    }
}
