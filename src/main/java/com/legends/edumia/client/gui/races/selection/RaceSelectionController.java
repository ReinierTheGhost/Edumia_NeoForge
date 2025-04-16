package com.legends.edumia.client.gui.races.selection;

import com.legends.edumia.resources.datas.Disposition;
import com.legends.edumia.resources.datas.factions.Faction;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.SpawnData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RaceSelectionController {
    private Map<Disposition, List<Faction>> factions = null;
    /**
     * Identifier and if the spawn data is from the dynamic pool. True(Dynamic) : False(Custom)
     */
    private List<SpawnData> spawns;
//    private List<Race> races = new ArrayList();

    private int currentDispositionIndex;
    private int currentFactionIndex;
    private int currentRaceIndex;
    private int currentSubFactionIndex;
    private int currentSpawnIndex;
    private AbstractClientPlayer player;
    private RaceSelectionScreen screen;
    public boolean mapFocusToggle = true;
    List<Disposition> dispositionsWithContent = new ArrayList<>();

    public void setFactionId(ResourceLocation id) {
        for(Disposition disp : factions.keySet()){
            for(Faction fac : factions.get(disp)){
                boolean foundFaction = false;
                int subfactionIndex = -1;
                if(fac.getId() == id){
                    foundFaction = true;
                } else {
                    List<ResourceLocation> subfactions = fac.getSubFactions();
                    if(subfactions != null && !subfactions.isEmpty()){
                        if(subfactions.contains(id)){
                            subfactionIndex = fac.getSubFactions().indexOf(id);
                            foundFaction = true;
                        }
                    }
                }
                if(foundFaction){
                    currentDispositionIndex = dispositionsWithContent.indexOf(disp);
                    currentFactionIndex = factions.get(disp).stream().toList().indexOf(fac);
                    if(subfactionIndex >= 0)
                        currentSubFactionIndex = subfactionIndex;
                    processFaction();
                    return;
                }
            }
        }
    }

    private void processFaction() {
//        processSubfaction();
//        processSpawnList(0);
//        processRace();
    }

}
