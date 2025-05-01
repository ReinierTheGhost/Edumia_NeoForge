package com.legends.edumia.entity.races.highelves;

import com.legends.edumia.entity.NpcEntity;
import com.legends.edumia.resources.EdumiaFactions;
import com.legends.edumia.resources.EdumiaRaces;
import com.legends.edumia.resources.datas.npcs.data.NpcRank;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

public class HighElfEntity  extends NpcEntity {

    public HighElfEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
        String name = this.getTypeName().getString();
        if (name.contains("civilian")){
            this.setRank(NpcRank.CIVILIAN);
        }
    }

    @Override
    protected ResourceLocation getFactionId() {
        return EdumiaFactions.HIGH_ELVES.getId();
    }

    @Override
    protected ResourceLocation getRaceId() {
        return EdumiaRaces.HIGHELVES.getId();
    }


    @Override
    public @Nullable SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType,
                                                  @Nullable SpawnGroupData spawnGroupData) {
        spawnGroupData = super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
        RandomSource random = level.getRandom();
        this.populateDefaultEquipmentSlots(random, difficulty);
        return spawnGroupData;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        int i = 2;
        initNeutralTargetSelector(i);
    }

    @Override
    protected void actuallyHurt(DamageSource damageSource, float damageAmount) {
        if (damageSource.getEntity() instanceof HighElfEntity){
            return;
        }
        super.actuallyHurt(damageSource, damageAmount);
    }

    public HighElvenVariant getVariant(){
        return HighElvenVariant.byId(this.getId());
    }



    public static AttributeSupplier.Builder setSoldierAttribute() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 22.0)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.ATTACK_SPEED, 1.0)
                .add(Attributes.ENTITY_INTERACTION_RANGE, 2.75)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.FOLLOW_RANGE, 48.0)
                .add(Attributes.FALL_DAMAGE_MULTIPLIER, 0.75)
                .add(Attributes.SCALE, 1.05);
    }
}
