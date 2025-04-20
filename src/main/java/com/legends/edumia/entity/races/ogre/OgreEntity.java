package com.legends.edumia.entity.races.ogre;

import com.legends.edumia.entity.NpcEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

public class OgreEntity extends NpcEntity {
    public OgreEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }
}
