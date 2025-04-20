package com.legends.edumia.client.gui.races.selection;

import com.legends.edumia.resources.datas.Disposition;
import com.legends.edumia.resources.datas.factions.Faction;
import com.legends.edumia.resources.datas.factions.FactionLookup;
import com.legends.edumia.resources.datas.factions.data.SpawnData;
import com.legends.edumia.resources.datas.factions.data.SpawnDataHandler;
import com.legends.edumia.resources.datas.npcs.data.NpcGearData;
import com.legends.edumia.resources.datas.races.Race;
import com.legends.edumia.utils.EdumiaLog;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

import java.util.*;

public class FactionSelectionController {
    private Map<Disposition, List<Faction>> factions = null;
    /**
     * Identifier and if the spawn data is from the dynamic pool. True(Dynamic) : False(Custom)
     */
    private List<SpawnData> spawns;
    private List<Race> races = new ArrayList<>();

    private int currentDispositionIndex;
    private int currentFactionIndex;
    private int currentRaceIndex;
    private int currentSubFactionIndex;
    private int currentSpawnIndex;
    private AbstractClientPlayer player;
    private FactionSelectionScreen screen;
    public boolean mapFocusToggle = true;
    List<Disposition> dispositionsWithContent = new ArrayList<>();
    private float currentDelay;

    public FactionSelectionController(FactionSelectionScreen screen, AbstractClientPlayer player, float delay){
        this.player = player;
        this.screen = screen;
        this.currentDelay = delay;
        if (this.currentDelay == 0)
            screen.enableConfirm();

        factions = new HashMap<>();
        addFactionsByDisposition(Disposition.GOOD);
        addFactionsByDisposition(Disposition.NEUTRAL);
        addFactionsByDisposition(Disposition.EVIL);

        if (getCurrentlySelectedFaction() == null){
            if (!factions.get(Disposition.EVIL).isEmpty()){
                currentDispositionIndex = 2;
            }else if (!factions.get(Disposition.NEUTRAL).isEmpty()){
                currentDispositionIndex = 1;
            }
        }

        if (getCurrentlySelectedFaction() == null){
            EdumiaLog.logError("FactionSelectionController::No factions Available!");
            throw  new RuntimeException();
        }
        processSpawnList(0);
        processRace();
    }

    private void addFactionsByDisposition(Disposition disposition){
        List<Faction> foundFaction = new ArrayList<>(FactionLookup.getFactionsByDisposition(player.level(),
                disposition).values().stream().toList());
        foundFaction.sort(Comparator.comparingInt(Faction::getFactionSelectionOrderIndex));
        factions.put(disposition, foundFaction);
        if (!factions.get(disposition).isEmpty()){
            dispositionsWithContent.add(disposition);
        }
    }

    private void processSpawnList(int index){
        Faction currentFaction = getCurrentlySelectedFaction();
        if (currentFaction == null) return;
        SpawnDataHandler foundSpawnDataHandler = currentFaction.getSpawnData();
        if (foundSpawnDataHandler == null) return;
        spawns = foundSpawnDataHandler.getSpawnList();
        currentSpawnIndex = index;
        currentRaceIndex = 0;
        setSpawnIndex(currentSpawnIndex);
    }

    private void processRace(){
        races = null;
        Faction currentFaction = getCurrentlySelectedFaction();
        if (currentFaction == null) return;
        races = currentFaction.getRaces(player.level());
        screen.updateEquipment();
        screen.reassignTexts(getRaceListText(), getCurrentFactionDispositions());
    }

    public List<Component> getCurrentFactionDispositions() {
        Faction faction = getCurrentlySelectedFaction();
        if (faction != null){
            return faction.getDescription();
        }
        return null;
    }

    public List<Component> getRaceListText() {
        Faction faction = getCurrentlySelectedFaction();
        if (faction != null){
            return List.of(faction.getRaceListText(player.level()));
        }
        return null;
    }

    public void setSpawnIndex(int index) {
        if (!haveSpawns()){
            currentSpawnIndex = 0;
            return;
        }
        if (index != currentSpawnIndex || spawns.isEmpty())
            currentSpawnIndex = Math.min(spawns.size() - 1, Math.max(0, index));
        if (screen.mapWidget != null){
            screen.mapWidget.updateSelectedSpawn(index);
        }

    }

    public boolean haveSpawns() {
        return spawns != null && !spawns.isEmpty();
    }

    private Disposition getCurrentDisposition() {
        if (currentDispositionIndex >= dispositionsWithContent.size())
            currentDispositionIndex = 0;
        else if (currentDispositionIndex < 0)
            currentDispositionIndex = dispositionsWithContent.size() - 1;
        return dispositionsWithContent.get(currentDispositionIndex);
    }

    private Faction getCurrentFaction() {
        Disposition disposition = getCurrentDisposition();
        return (!factions.get(disposition).isEmpty()) ? factions.get(disposition).get(currentDispositionIndex) : null;
    }

    public Faction getCurrentSubfaction() {
        Faction faction = getCurrentFaction();
        if (faction == null) return null;
        return faction.getSubFaction(player.level(), currentDispositionIndex);
    }

    public Faction getCurrentlySelectedFaction() {
        Faction faction = getCurrentFaction();
        Faction subFaction = getCurrentSubfaction();
        if (subFaction != null)
            faction = subFaction;

        return faction;
    }





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

    public Map<Disposition, List<Faction>> getFactions() {
        return factions;
    }

    public NpcGearData getCurrentPreview(Level level) {
        Faction currentFaction = getCurrentlySelectedFaction();
        races = currentFaction.getRaces(level);
        NpcGearData data = currentFaction.getPreviewGear(level, races.get(currentFactionIndex));
        return data;
    }

    public Race getCurrentRace() {
        if (this.races != null){
            return races.get(currentRaceIndex);
        }
        return null;
    }
}
