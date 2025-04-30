package com.legends.edumia.world.spawners;

import com.legends.edumia.entity.EdumiaEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;

public class ModSpawnSettingsBuilder {

    public static void addFarmAnimals(MobSpawnSettings.Builder builder){
        builder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.SHEEP, 12, 4, 4));
        builder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.PIG, 10, 4, 4));
        builder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.CHICKEN, 10, 4, 4));
        builder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.COW, 8, 4, 4));
    }

    public static void addPlainAnimals(MobSpawnSettings.Builder builder){
        addFarmAnimals(builder);
        builder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.DONKEY, 1, 1, 3));
        builder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.HORSE, 5, 2, 6));
    }

    public static void addTropicalAnimals(MobSpawnSettings.Builder builder){
        builder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.OCELOT, 1, 1, 2));
        builder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.PARROT, 5, 3, 6));
    }


    public static void addHumans(MobSpawnSettings.Builder builder){
        builder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EdumiaEntities.HUMAN_CIVILIAN.get(), 10, 4, 4));
    }

    public static void addFairies(MobSpawnSettings.Builder builder){
        builder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EdumiaEntities.FAIRY_CIVILIAN.get(), 10, 4, 4));
    }
}


