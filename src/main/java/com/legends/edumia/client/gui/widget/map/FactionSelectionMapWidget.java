package com.legends.edumia.client.gui.widget.map;

import com.legends.edumia.client.gui.races.selection.FactionSelectionController;
import com.legends.edumia.resources.datas.factions.data.SpawnDataHandler;

public class FactionSelectionMapWidget extends MapWidget{
    MapMarkerWidget[] spawnMapMarkers;

    FactionSelectionController controller;
    public FactionSelectionMapWidget(FactionSelectionController controller, int mapWidth, int mapHeight) {
        super(mapWidth, mapHeight);
        this.controller = controller;
        final int[] maxSpawnCount = {0};
        this.controller.getFactions().values().forEach(factionList -> factionList.forEach(faction -> {
            SpawnDataHandler spawnDataHandler = faction.getSpawnData();
            if (spawnDataHandler != null && spawnDataHandler.getSpawnList() != null){
                int count = spawnDataHandler.getSpawnList().size();
                if (count > maxSpawnCount[0]){
                    maxSpawnCount[0] = count;
                }
            }
        }));
        spawnMapMarkers = new MapMarkerWidget[maxSpawnCount[0]];
    }

    public void updateSelectedSpawn(int index) {
        for (int i = 0; i < spawnMapMarkers.length; i++){
            this.spawnMapMarkers[i].setSelected(i == index);
        }
    }
}
