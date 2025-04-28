package com.legends.edumia.events;

import com.legends.edumia.Edumia;
import com.legends.edumia.client.models.fairy.FairyModel;
import com.legends.edumia.entity.model.EdumiaEntityModelLayers;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = Edumia.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(EdumiaEntityModelLayers.FAIRY, FairyModel::createBodyLayer);
    }

}
