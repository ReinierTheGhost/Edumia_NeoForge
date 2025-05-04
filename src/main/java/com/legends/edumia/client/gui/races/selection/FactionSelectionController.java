package com.legends.edumia.client.gui.races.selection;

import com.legends.edumia.network.packets.C2S.*;
import com.legends.edumia.resources.datas.Disposition;
import com.legends.edumia.resources.datas.FactionType;
import com.legends.edumia.resources.datas.factions.Faction;
import com.legends.edumia.resources.datas.factions.FactionLookup;
import com.legends.edumia.resources.datas.factions.data.SpawnData;
import com.legends.edumia.resources.datas.factions.data.SpawnDataHandler;
import com.legends.edumia.resources.datas.npcs.data.NpcGearData;
import com.legends.edumia.resources.datas.races.Race;
import com.legends.edumia.utils.EdumiaLog;
import net.minecraft.ChatFormatting;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.PacketDistributor;
import org.joml.Vector2d;
import org.joml.Vector2i;

import java.util.*;
import java.util.stream.Collectors;

public class FactionSelectionController {
    private Map<Disposition, List<Faction>> factions = null;
    /**
     * ResourceLocation and if the spawn data is from the dynamic pool. True(Dynamic) : False(Custom)
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
        if(this.currentDelay == 0)
            screen.enableConfirm();

        factions = new HashMap<>();
        addFactionsByDisposition(Disposition.GOOD);
        addFactionsByDisposition(Disposition.NEUTRAL);
        addFactionsByDisposition(Disposition.EVIL);

        if(getCurrentlySelectedFaction() == null){
            if(!factions.get(Disposition.EVIL).isEmpty()){
                currentDispositionIndex = 2;
            } else if(!factions.get(Disposition.NEUTRAL).isEmpty()){
                currentDispositionIndex = 1;
            }
        }
        if(getCurrentlySelectedFaction() == null){
            EdumiaLog.logError("FactionSelectionController::No faction available!");
            throw new RuntimeException();
        }
        processSpawnList(0);
        processRace();
    }


    private void addFactionsByDisposition(Disposition disposition) {
        List<Faction> foundFaction = new ArrayList<>(FactionLookup.getFactionsByDisposition(player.level(),
                disposition).values().stream().toList());
        foundFaction.sort(Comparator.comparingInt(Faction::getFactionSelectionOrderIndex));
        factions.put(disposition, foundFaction);
        if(!factions.get(disposition).isEmpty())
            dispositionsWithContent.add(disposition);
    }

    private void processSpawnList(int index) {
        Faction currentFaction = getCurrentlySelectedFaction();
        if(currentFaction == null) return;
        SpawnDataHandler foundSpawnDataHandler = currentFaction.getSpawnData();
        if(foundSpawnDataHandler == null) return;
        spawns = foundSpawnDataHandler.getSpawnList();
        currentSpawnIndex = index;
        currentRaceIndex = 0;
        setSpawnIndex(currentSpawnIndex);
    }

    private void processRace() {
        races = null;
        Faction currentFaction = getCurrentlySelectedFaction();
        if(currentFaction == null) return;
        races = currentFaction.getRaces(player.level());
        screen.updateEquipment();
        screen.reassignTexts(getRaceListText(), getCurrentFactionDescriptions());
    }

    public void randomizeSpawn(int tentativeLeft) {
        Faction currentFaction = getCurrentlySelectedFaction();
        if(currentFaction == null)
            return;
        SpawnDataHandler spawnDataHandler = currentFaction.getSpawnData();
        if(spawnDataHandler == null || spawnDataHandler.getSpawnList().isEmpty())
            return;
        Random random = new Random();
        for(int i = 0; i < tentativeLeft; i++){
            int newSpawnIndex = random.nextInt(spawnDataHandler.getSpawnList().size());
            if(newSpawnIndex != currentSpawnIndex){
                processSpawnList(newSpawnIndex);
                return;
            }
        }
        processSpawnList(random.nextInt(spawnDataHandler.getSpawnList().size()));
    }

    public int randomizeFaction(int tentativeLeft){
        Random random = new Random();
        // Disposition randomizer
        currentDispositionIndex = random.nextInt(dispositionsWithContent.size());
        Disposition disposition = dispositionsWithContent.get(currentDispositionIndex);

        // Recursive trigger
        if(factions.get(disposition) == null || factions.get(disposition).isEmpty()){
            if(tentativeLeft > 0){
                return tentativeLeft + randomizeFaction(tentativeLeft - 1);
            }
        }

        // Faction randomizer
        currentFactionIndex =
                (factions.get(disposition) == null || factions.get(disposition).isEmpty())
                        ? 0
                        : random.nextInt(factions.get(disposition).size());
        Faction faction =
                (factions.get(disposition) == null || factions.get(disposition).isEmpty() || currentFactionIndex >= factions.get(disposition).size())
                        ? null
                        : factions.get(disposition).get(currentFactionIndex);

        // Subfaction randomizer
        currentSubFactionIndex =
                (faction == null || faction.getSubFactions() == null || faction.getSubFactions().isEmpty())
                        ? 0
                        : random.nextInt(faction.getSubFactions().size());
        processSpawnList(0);
        processRace();
        return 0;
    }

    public void dispositionUpdate(boolean add) {
        if(factions.isEmpty())
            return;
        if(add){
            currentDispositionIndex++;
        }
        else{
            currentDispositionIndex --;
        }
        currentDispositionIndex = dispositionsWithContent.indexOf(getCurrentDisposition());

        currentFactionIndex = 0;
        currentSubFactionIndex = 0;
        currentRaceIndex = 0;
        processFaction();
    }

    private int getDispositionsIndex(Disposition disposition){
        return factions.keySet().stream().toList().indexOf(disposition);
    }

    public void factionUpdate(boolean add) {
        if(add){
            currentFactionIndex++;
            if(currentFactionIndex >= factions.get(getCurrentDisposition()).size())
                currentFactionIndex = 0;
        }
        else{
            currentFactionIndex--;
            if(currentFactionIndex < 0)
                currentFactionIndex = factions.get(getCurrentDisposition()).size() - 1;
        }

        currentSubFactionIndex = 0;
        currentRaceIndex = 0;
        processFaction();
    }

    private void processFaction() {
        processSubfaction();
        processSpawnList(0);
        processRace();
    }
    public void subfactionUpdate(boolean add){
        if(add){
            currentSubFactionIndex++;
            if(currentSubFactionIndex >= getCurrentFaction().getSubFactions().size())
                currentSubFactionIndex = 0;
        }
        else{
            currentSubFactionIndex--;
            if(currentSubFactionIndex < 0)
                currentSubFactionIndex = getCurrentFaction().getSubFactions().size() - 1;
        }
        processSubfaction();

        screen.reassignTexts(getRaceListText(), getCurrentFactionDescriptions());
        currentRaceIndex = 0;

    }

    private void processSubfaction() {
        if(getCurrentlySelectedFaction().getFactionType() == FactionType.SUBFACTION){
            screen.reassignTexts(getRaceListText(), getCurrentFactionDescriptions());
            setSpawnIndex(0);
        }
        processSpawnList(0);
        processRace();
    }

    public void spawnIndexUpdate(boolean add){
        if(add){
            currentSpawnIndex++;
            if(currentSpawnIndex >= spawns.size())
                currentSpawnIndex = 0;
        }
        else{
            currentSpawnIndex--;
            if(currentSpawnIndex < 0)
                currentSpawnIndex = spawns.size() - 1;
        }
        setSpawnIndex(currentSpawnIndex);
    }

    public void raceIndexUpdate(boolean add) {
        if(add){
            currentRaceIndex++;
            if(currentRaceIndex >= races.size())
                currentRaceIndex = 0;
        }
        else{
            currentRaceIndex--;
            if(currentRaceIndex < 0)
                currentRaceIndex = races.size() - 1;
        }
    }

    public Race getCurrentRace() {
        if(this.races != null){
            return races.get(currentRaceIndex);
        }
        return null;
    }

    public String getCurrentRaceKey() {
        if(getCurrentRace() != null)
            return getCurrentRace().getTranslatableKey();
        return "ooops"; // TODO : translatale
    }

    public boolean haveManyRaces(){
        if(races == null){
            return false;
        }
        return races.size() > 1;
    }

    public boolean haveSpawns(){
        return spawns != null && !spawns.isEmpty();
    }

    private ResourceLocation getCurrentSpawnResourceLocation(){
        if(!haveSpawns())
            processSpawnList(0);
        if(!haveSpawns())
            return null;
        return spawns.get(currentSpawnIndex).getIdentifier();
    }
    public String getCurrentSpawnKey(){
        if(haveSpawns()){
            ResourceLocation spawnId = getCurrentSpawnResourceLocation();
            return SpawnDataHandler.getTranslatableKey(spawnId);
        }
        return "spawn.me.none";
    }
    public boolean haveManySpawns(){
        if(!haveSpawns()){
            return false;
        }
        return spawns.size() > 1;
    }


    public void confirmSpawnSelection(AbstractClientPlayer player){
        Faction faction = getCurrentlySelectedFaction();
        if(faction == null || !haveSpawns() || !canConfirm()) return;

        SpawnData spawn = spawns.get(currentSpawnIndex);
        Vec3 coordinate = spawn.getCoordinates();
        if(spawn.isDynamic()){
            PacketDistributor.sendToServer(new PacketTeleportToDynamicCoordinate(coordinate.x(), coordinate.z(), true));
        } else {
            PacketDistributor.sendToServer(new PacketTeleportToCustomCoordinate(coordinate.x(), coordinate.y(), coordinate.z(), true));
        }

        PacketDistributor.sendToServer(new PacketSetRace(races.get(currentRaceIndex).getId().toString()));
        PacketDistributor.sendToServer(new PacketSetAffiliation(getCurrentDisposition().name(), getCurrentlySelectedFaction().getId().toString(), spawn.getIdentifier().toString()));
        if(player != null){
            BlockPos overworldBlockPos = player.blockPosition();
            PacketDistributor.sendToServer(new PacketSetSpawnData(overworldBlockPos.getX(), overworldBlockPos.getY(), overworldBlockPos.getZ()));
        }
        screen.onClose();
    }

    public Disposition getCurrentDisposition(){
        if(currentDispositionIndex >= dispositionsWithContent.size())
            currentDispositionIndex = 0;
        else if(currentDispositionIndex < 0)
            currentDispositionIndex = dispositionsWithContent.size() - 1;
        return dispositionsWithContent.get(currentDispositionIndex);
    }

    public Faction getCurrentFaction(){
        Disposition disposition = getCurrentDisposition();
        return (!factions.get(disposition).isEmpty()) ? factions.get(disposition).get(currentFactionIndex) : null;
    }

    public Faction getCurrentSubfaction(){
        Faction faction = getCurrentFaction();
        if(faction == null) return null;
        return faction.getSubfaction(player.level(), currentSubFactionIndex);
    }

    public Faction getCurrentlySelectedFaction(){
        Faction faction = getCurrentFaction();
        Faction subfaction = getCurrentSubfaction();
        if(subfaction != null)
            faction = subfaction;

        return faction;
    }

    public boolean haveSubfaction(){
        return getCurrentSubfaction() != null;
    }

    public int getCurrentDispositionFactionCount(){
        return factions.get(getCurrentDisposition()).size();
    }

    // Todo : possibily remove this method to have a more precise getter
    public Map<Disposition, List<Faction>> getFactions() {
        return factions;
    }

    public NpcGearData getCurrentPreview(Level world) {
        Faction currentFaction = getCurrentlySelectedFaction();
        races = currentFaction.getRaces(world);
        NpcGearData data = currentFaction.getPreviewGear(world, races.get(currentRaceIndex));
        return data;
    }

    public SpawnDataHandler getCurrentSpawnDataHandler(){
        Faction faction = getCurrentlySelectedFaction();
        return (faction != null) ? faction.getSpawnData() : null;
    }

    public void setSpawnIndex(int index) {
        if(!haveSpawns()){
            currentSpawnIndex = 0;
            return;
        }
        if(index != currentSpawnIndex ||  spawns.isEmpty())
            currentSpawnIndex = Math.min(spawns.size() - 1, Math.max(0, index));
        if(screen.mapWidget != null){
            screen.mapWidget.updateSelectedSpawn(index);
            if(mapFocusToggle){
                SpawnData spawnData = spawns.get(currentSpawnIndex);
                if(spawnData != null){
                    BlockPos blockPos = spawns.get(currentSpawnIndex).getBlockPos();
                    Vector2i point = new Vector2i(blockPos.getX(), blockPos.getZ());
                    screen.mapWidget.moveTo(point, new Vector2d(3.5, 45.0));
                }
            }
        }
    }

    public int getCurrentSpawnIndex() {
        return currentSpawnIndex;
    }

    public HashMap<ResourceLocation, Component> getSearchBarPool(Level world) {
        HashMap<ResourceLocation, Component> pool = new HashMap<>();
        for(List<Faction> factionsByDisposition : factions.values()){
            for(Faction faction : factionsByDisposition){
                pool.put(faction.getId(), faction.tryGetShortName());
                if(faction.getFactionType() == FactionType.FACTION && faction.getSubFactions() != null){
                    for(ResourceLocation identifier : faction.getSubFactions()){
                        Faction subfaction = faction.getSubfactionById(world, identifier);
                        pool.put(subfaction.getId(), subfaction.tryGetShortName());
                    }
                }
            }
        }
        return pool;
    }

    public void toggleMapFocus() {
        mapFocusToggle = !mapFocusToggle;
        if(!mapFocusToggle){
            screen.mapWidget.clearFocus();
        }
    }

    public List<Component> getCurrentFactionDescriptions() {
        Faction faction = getCurrentlySelectedFaction();
        if(faction != null){
            return faction.getDescription().stream()
                    .map(text -> text.copy().withStyle(ChatFormatting.WHITE))
                    .collect(Collectors.toList())
                    ;
        }
        return null;
    }

    public List<Component> getRaceListText() {
        Faction faction = getCurrentlySelectedFaction();
        if(faction != null){
            return List.of(faction.getRaceListText(player.level()));
        }
        return null;
    }

    public boolean canConfirm(){
        return this.currentDelay == 0;
    }
    public float getDelayRounded(){
        return (Math.round(this.currentDelay * 10f) /10f);
    }
    public void reduceDelay(float delta) {
        if(this.currentDelay > 0){
            this.currentDelay = Math.max(0, this.currentDelay - delta);
            if(this.currentDelay == 0)
                screen.enableConfirm();
        }
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
}
