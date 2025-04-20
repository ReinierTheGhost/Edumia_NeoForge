package com.legends.edumia.resources.datas.races;

import com.legends.edumia.entity.NpcEntity;
import com.legends.edumia.entity.races.darkelves.DarkElfEntity;
import com.legends.edumia.entity.races.demon.DemonEntity;
import com.legends.edumia.entity.races.fairy.FairyEntity;
import com.legends.edumia.entity.races.gensai.GensaiEntity;
import com.legends.edumia.entity.races.highelves.HighElfEntity;
import com.legends.edumia.entity.races.human.BanditHumanEntity;
import com.legends.edumia.entity.races.human.HumanEntity;
import com.legends.edumia.entity.races.ogre.OgreEntity;
import com.legends.edumia.entity.races.orc.OrcEntity;
import com.legends.edumia.resources.datas.RaceType;
import com.legends.edumia.utils.ResourceLocationUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;


public class Race {
    private final ResourceLocation id;
    private final RaceType raceType;
    private final String translatableKey;

    public Race(String id, String raceTypeValue) {
        this.id = ResourceLocationUtil.getResourceLocationFromString(id);
        this.translatableKey = "race.".concat(this.id.toLanguageKey());

        this.raceType = RaceType.valueOf(raceTypeValue.toUpperCase());
    }

    public ResourceLocation getId(){
        return this.id;
    }

    public String getIdValue(){
        return this.id.toString();
    }
    public Component getFullName() {
        return Component.translatable(translatableKey);
    }

    public LivingEntity getModel(Level world) {
        NpcEntity entity;
        switch (raceType){
            case RaceType.HUMAN:
                entity = new HumanEntity(EntityType.VILLAGER, world);
                break;
            case RaceType.HIGH_ELF:
                entity = new HighElfEntity(EntityType.VILLAGER, world);
                break;
            case RaceType.DARK_ELF:
                entity = new DarkElfEntity(EntityType.VILLAGER, world);
                break;
            case RaceType.DEMON:
                entity = new DemonEntity(EntityType.VILLAGER, world);
                break;
            case RaceType.ORC:
                entity = new OrcEntity(EntityType.VILLAGER, world);
                break;
            case RaceType.OGRE:
                entity = new OgreEntity(EntityType.VILLAGER, world);
                break;
            case RaceType.FAIRY:
                entity = new FairyEntity(EntityType.VILLAGER, world);
                break;
            case RaceType.GENSAI:
                entity = new GensaiEntity(EntityType.VILLAGER, world);
                break;
            default:
                entity = new BanditHumanEntity(EntityType.VILLAGER, world);
                break;
        }
        entity.setNoAi(true);
        return entity;
    }
}
