package com.legends.edumia.events;

import com.legends.edumia.Edumia;
import com.legends.edumia.client.models.animals.ButterflyModel;
import com.legends.edumia.client.models.animals.DragonflyModel;
import com.legends.edumia.client.models.npcs.*;
import com.legends.edumia.entity.EdumiaEntities;
import com.legends.edumia.entity.animals.butterfly.ButterflyEntity;
import com.legends.edumia.entity.animals.dragonfly.DragonflyEntity;
import com.legends.edumia.entity.model.EdumiaEntityModelLayers;
import com.legends.edumia.entity.races.darkelves.DarkElfEntity;
import com.legends.edumia.entity.races.fairy.FairyEntity;
import com.legends.edumia.entity.races.highelves.HighElfEntity;
import com.legends.edumia.entity.races.human.HumanEntity;
import com.legends.edumia.entity.races.ogre.OgreEntity;
import com.legends.edumia.entity.races.orc.OrcEntity;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

@EventBusSubscriber(modid = Edumia.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(EdumiaEntityModelLayers.FAIRY, () -> FairyModel.createBodyLayer(CubeDeformation.NONE));
        event.registerLayerDefinition(EdumiaEntityModelLayers.HUMAN, () -> HumanModel.createBodyLayer(CubeDeformation.NONE));
        event.registerLayerDefinition(EdumiaEntityModelLayers.HIGHELVEN, () -> ElvenModel.createBodyLayer(CubeDeformation.NONE));
        event.registerLayerDefinition(EdumiaEntityModelLayers.DARKELVEN, () -> ElvenModel.createBodyLayer(CubeDeformation.NONE));
        event.registerLayerDefinition(EdumiaEntityModelLayers.ORC, () -> OrcModel.createBodyLayer(CubeDeformation.NONE));
        event.registerLayerDefinition(EdumiaEntityModelLayers.OGRE, () -> OgreModel.createBodyLayer(CubeDeformation.NONE));

        event.registerLayerDefinition(EdumiaEntityModelLayers.BUTTERFLY, ButterflyModel::createBodyLayer);
        event.registerLayerDefinition(EdumiaEntityModelLayers.DRAGONFLY, DragonflyModel::createBodyLayer);
    }



    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(EdumiaEntities.FAIRY_CIVILIAN.get(), FairyEntity.setSoldierAttribute().build());
        event.put(EdumiaEntities.HUMAN_CIVILIAN.get(), HumanEntity.setSoldierAttribute().build());
        event.put(EdumiaEntities.HIGH_ELVEN_CIVILIAN.get(), HighElfEntity.setSoldierAttribute().build());
        event.put(EdumiaEntities.DARK_ELVEN_CIVILIAN.get(), DarkElfEntity.setSoldierAttribute().build());
        event.put(EdumiaEntities.ORC_CIVILIAN.get(), OrcEntity.setSoldierAttribute().build());
        event.put(EdumiaEntities.OGRE_CIVILIAN.get(), OgreEntity.setSoldierAttribute().build());

        event.put(EdumiaEntities.BUTTERFLY.get(), ButterflyEntity.createAttributes().build());
        event.put(EdumiaEntities.DRAGONFLY.get(), DragonflyEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(EdumiaEntities.FAIRY_CIVILIAN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Mob::checkMobSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(EdumiaEntities.HUMAN_CIVILIAN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Mob::checkMobSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(EdumiaEntities.HIGH_ELVEN_CIVILIAN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Mob::checkMobSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(EdumiaEntities.DARK_ELVEN_CIVILIAN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Mob::checkMobSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(EdumiaEntities.ORC_CIVILIAN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Mob::checkMobSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(EdumiaEntities.OGRE_CIVILIAN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Mob::checkMobSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

        event.register(EdumiaEntities.BUTTERFLY.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Mob::checkMobSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(EdumiaEntities.DRAGONFLY.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Mob::checkMobSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }
}
