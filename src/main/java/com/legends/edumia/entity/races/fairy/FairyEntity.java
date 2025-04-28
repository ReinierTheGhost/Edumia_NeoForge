package com.legends.edumia.entity.races.fairy;

import com.legends.edumia.entity.NpcEntity;
import com.legends.edumia.resources.EdumiaFactions;
import com.legends.edumia.resources.EdumiaRaces;
import com.legends.edumia.resources.datas.npcs.data.NpcRank;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

public class FairyEntity extends NpcEntity {
    public FairyEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
        String name = this.getTypeName().getString();
        if (name.contains("civilian")){
            this.setRank(NpcRank.CIVILIAN);
        }
    }

    @Override
    protected ResourceLocation getFactionId() {
        return EdumiaFactions.FAIRIES.getId();
    }

    @Override
    protected ResourceLocation getRaceId() {
        return EdumiaRaces.FAIRY.getId();
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
        if (damageSource.getEntity() instanceof FairyEntity){
            return;
        }
        super.actuallyHurt(damageSource, damageAmount);
    }

    public FairyVariant getVariant(){
        return FairyVariant.byId(this.getId());
    }
}
