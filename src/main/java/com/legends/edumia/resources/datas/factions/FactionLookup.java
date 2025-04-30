package com.legends.edumia.resources.datas.factions;

import com.legends.edumia.exceptions.FactionResourceLocationException;
import com.legends.edumia.resources.EdumiaFactions;
import com.legends.edumia.resources.datas.Disposition;
import com.legends.edumia.resources.datas.FactionType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class FactionLookup {
    public static List<Faction> getAllFactions(Level world) {
        return world.registryAccess().registryOrThrow(EdumiaFactions.FACTION_KEY).stream().toList();
    }

    public static Faction getFactionById(Level world, ResourceLocation id) throws FactionResourceLocationException {
        Faction faction = world.registryAccess().registryOrThrow(EdumiaFactions.FACTION_KEY).get(id);
        if(faction == null)
            throw new FactionResourceLocationException();
        return faction;
    }

    public static HashMap<ResourceLocation, Faction> getFactionsByDisposition(Level world, Disposition disposition){
        Stream<Faction> factions = getAllJoinableFaction(world).stream();
        HashMap<ResourceLocation, Faction> foundFactions = new HashMap<>();

        for(Faction faction : factions.filter(x -> x.getDisposition() == disposition).toList()){
            if(faction.getFactionType() == FactionType.FACTION)
                foundFactions.put(faction.getId(), faction);
        }
        return foundFactions;
    }

    public static List<Faction> getAllJoinableFaction(Level world) {
        List<Faction> factions = getAllFactions(world);
        List<Faction> factionList = new ArrayList<>();
        for(Faction faction : factions) {
            if(!faction.isJoinable())
                continue;
            factionList.add(faction);
        }
        return factionList;
    }
}
