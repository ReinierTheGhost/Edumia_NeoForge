package com.legends.edumia.resources.datas.races;

import com.legends.edumia.resources.EdumiaRaces;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class RaceLookup {
    public static List<Race> getAllRaces(Level world) {
        return world.registryAccess().registryOrThrow(EdumiaRaces.RACE_KEY).stream().toList();
    }

    public static List<Race> getAllRaces(Level world, List<ResourceLocation> ids) {
        Registry<Race> registry = world.registryAccess().registryOrThrow(EdumiaRaces.RACE_KEY);
        List<Race> list = new ArrayList<>();
        for(ResourceLocation id : ids){
            Race race = registry.get(id);
            if(!list.contains(race))
                list.add(race);
        }
        return list;
    }

    public static Race getRace(Level world, ResourceLocation identifier) {
        return world.registryAccess().registryOrThrow(EdumiaRaces.RACE_KEY).get(identifier);
    }
}
