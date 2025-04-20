package com.legends.edumia.entity.races.human;

import com.legends.edumia.entity.NpcEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.level.Level;

public class BanditHumanEntity extends NpcEntity {
    public BanditHumanEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }
}
