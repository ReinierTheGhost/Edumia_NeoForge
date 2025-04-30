package com.legends.edumia.events;

import com.legends.edumia.Edumia;
import com.legends.edumia.client.models.fairy.FairyModel;
import com.legends.edumia.entity.EdumiaEntities;
import com.legends.edumia.entity.model.EdumiaEntityModelLayers;
import com.legends.edumia.entity.races.fairy.FairyEntity;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = Edumia.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(EdumiaEntityModelLayers.FAIRY, () -> FairyModel.createBodyLayer(CubeDeformation.NONE));
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(EdumiaEntities.FAIRY_CIVILIAN.get(), FairyEntity.setSoldierAttribute().build());
        event.put(EdumiaEntities.HUMAN_CIVILIAN.get(), FairyEntity.setSoldierAttribute().build());
    }
}
