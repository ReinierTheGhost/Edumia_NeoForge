package com.legends.edumia.resources.datas.factions;

import com.legends.edumia.resources.EdumiaFactions;
import com.legends.edumia.resources.datas.Disposition;
import com.legends.edumia.resources.datas.FactionType;
import com.legends.edumia.resources.datas.factions.data.SpawnDataHandler;
import com.legends.edumia.resources.datas.npcs.NpcData;
import com.legends.edumia.resources.datas.npcs.NpcDataLookup;
import com.legends.edumia.resources.datas.npcs.data.NPCRank;
import com.legends.edumia.resources.datas.npcs.data.NpcGearData;
import com.legends.edumia.resources.datas.races.Race;
import com.legends.edumia.resources.datas.races.RaceLookup;
import com.legends.edumia.utils.ResourceLocationUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

import java.util.*;

public class Faction {

    private final ResourceLocation id;
    private final Integer factionSelectionOrderIndex;
    private final String translatableKey;
    private final boolean joinable;
    private final Disposition disposition;
    private final FactionType factionType;

    private final HashMap<NPCRank, List<ResourceLocation>> npcDatasByRank;

    private final SpawnDataHandler spawnDataHandler;
    private List<ResourceLocation> subFactions = null;

    public List<Race> races = null;
    private List<Component> descriptions = null;
    private Component raceList = null;

    public Faction(String id, Integer factionSelectionOrderIndex, Boolean joinable, String disposition, String factionType,
                   Optional<ResourceLocation> parentFaction, Optional<List<ResourceLocation>> newSubFactions,
                   Optional<CompoundTag> npcs, Optional<CompoundTag> bannerDataNbt, Optional<CompoundTag> spawnsNbt,
                   Optional<List<String>> joinCommands, Optional<List<String>> leaveCommands) {
        this.id = ResourceLocationUtil.getResourceLocationFromString(id);

        this.factionSelectionOrderIndex = factionSelectionOrderIndex;
        this.translatableKey = "faction.".concat(this.id.toLanguageKey());
        this.joinable = joinable;
        this.disposition = Disposition.valueOf(disposition.toUpperCase());

        this.factionType = FactionType.valueOf(factionType.toUpperCase());

        if(newSubFactions.isPresent()){
            this.subFactions = new ArrayList<>();
            this.subFactions.addAll(newSubFactions.get());
        }

        this.npcDatasByRank = new HashMap<>();

        this.spawnDataHandler = new SpawnDataHandler(spawnsNbt);

        this.raceList = null;
        this.descriptions = null;
    }

    public List<ResourceLocation> getSubFactions(){
        return subFactions;
    }

    public Faction getSubFaction(Level level, int index) {
        if (level == null || this.subFactions == null || index >= this.subFactions.size())
            return null;
        return getSubfactionById(level, subFactions.get(index));
    }

    public Faction getSubfactionById(Level level, ResourceLocation id) {
        if (subFactions == null)
            return null;
        return level.registryAccess().registryOrThrow(EdumiaFactions.FACTION_KEY).get(id);
    }

    public ResourceLocation getId() {
        return id;
    }



    public Integer getFactionSelectionOrderIndex(){
        return this.factionSelectionOrderIndex;
    }
    public boolean isJoinable() {
        return joinable;
    }

    public Disposition getDisposition() {
        return disposition;
    }

    public FactionType getFactionType() {
        return factionType;
    }


    public SpawnDataHandler getSpawnData() {
        return spawnDataHandler;
    }

    public List<Race> getRaces(Level level) {
        if (races != null) return races;
        List<ResourceLocation> allRaceIds = new ArrayList<>();
        for (NPCRank rank : this.npcDatasByRank.keySet()){
            List<NpcData> datas = NpcDataLookup.getAllNpcDatas(level, this.npcDatasByRank.get(rank));
            for (NpcData data : datas){
                if (data != null)
                    allRaceIds.add(data.getRaceId());
            }
        }
        races = RaceLookup.getAllRaces(level, allRaceIds);
        return races;
    }

    public List<Component> getDescription() {
        if (descriptions == null){
            descriptions = new ArrayList<>();
            boolean hasDescription = true;
            String base = "description.edumia.%s.description_%s".formatted(id.getPath(), "%s");
            while (hasDescription){
                String langPath = base.formatted(descriptions.size());
                Component text = Component.translatable(langPath);
                if (!Objects.equals(text.getString(), langPath)){
                    descriptions.add(text);
                }else {
                    hasDescription = false;
                }
            }
        }
        return descriptions;
    }

    public Component getRaceListText(Level level) {
        if (raceList == null){
            StringBuilder raceListStringBuilder = new StringBuilder();
            if (races == null)
                races = getRaces(level);
            for (Race race : races){
                raceListStringBuilder.append(race.getFullName().getString());
                if (race != races.getLast())
                    raceListStringBuilder.append(", ");
            }
            raceList = Component.literal(raceListStringBuilder.toString());
        }
        return raceList;
    }

    public NpcGearData getPreviewGear(Level level, Race selectedRace) {
        if (selectedRace == null)
            return NpcGearData.Create();


        List<ResourceLocation> resourcesToUse = new ArrayList<>();

        List<NpcData> npcDataList = NpcDataLookup.getAllNpcDatasFromRace(level, resourcesToUse, selectedRace.getId());
        if (npcDataList.isEmpty())
            return NpcGearData.Create();
        Random random = new Random();
        NpcData foundNPCData = npcDataList.get(random.nextInt(0, npcDataList.size()));
        return foundNPCData.getGear();
    }
}
