package com.legends.edumia.resources;

import com.legends.edumia.Edumia;
import com.legends.edumia.resources.datas.npcs.NpcData;
import com.legends.edumia.resources.datas.npcs.data.NpcGearData;
import com.legends.edumia.utils.EdumiaLog;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;

import java.util.List;
import java.util.Optional;

@EventBusSubscriber(modid = Edumia.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class EdumiaNpcs {
    public static final String PATH = "npcs";
    public static final ResourceKey<Registry<NpcData>> NPC_KEY =
            ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, PATH));

    public static final NpcData FAIRY_CIVILIAN;
    public static final NpcData HUMAN_CIVILIAN;
    public static final NpcData HIGHELVEN_CIVILIAN;
    public static final NpcData DARKELVEN_CIVILIAN;

    @SubscribeEvent
    public static void onNewRegistry(DataPackRegistryEvent.NewRegistry event){
        EdumiaLog.logDebugMsg("Registering Dynamic Npcs for " + Edumia.MOD_ID);
        event.dataPackRegistry(NPC_KEY, NpcData.CODEC, NpcData.CODEC);
    }

    public static void bootstrap(BootstrapContext<NpcData> context) {
        HolderGetter<NpcData> npcRegistryEntryLookup = context.lookup(NPC_KEY);
        // [RACE / GENERIC]
        register(context, npcRegistryEntryLookup, HIGHELVEN_CIVILIAN);
        register(context, npcRegistryEntryLookup, FAIRY_CIVILIAN);
        register(context, npcRegistryEntryLookup, HUMAN_CIVILIAN);
        register(context, npcRegistryEntryLookup, DARKELVEN_CIVILIAN);
        // [GONDOR]
//        registerAll(context, npcRegistryEntryLookup, GondorianNpcDataPool.fetchAll());
    }

    private static void registerAll(BootstrapContext<NpcData> context, HolderGetter<NpcData> npcRegistryEntryLookup, List<NpcData> npcDatas) {
        for(NpcData data : npcDatas){
            register(context, npcRegistryEntryLookup, data);
        }
    }

    public static NpcData register(BootstrapContext<NpcData> context, HolderGetter<NpcData> npcRegistryEntryLookup, NpcData npcData) {
        ResourceKey<NpcData> npcRegistryKey = of(npcData.getName());
        String name = npcRegistryKey.location().getPath();
        ResourceKey<NpcData> npcKey = ResourceKey.create(NPC_KEY, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID,name));

        Optional<Holder.Reference<NpcData>> optionalNpc = npcRegistryEntryLookup.get(npcRegistryKey);
        optionalNpc.ifPresent(npcReference -> context.register(npcKey, npcData));

        return npcData;
    }

    private static ResourceKey<NpcData> of(String name) {
        return ResourceKey.create(NPC_KEY, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, name));
    }

    static {

        DARKELVEN_CIVILIAN = new NpcData(ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "darkelven.civilian"),
                EdumiaRaces.DARKELVES, List.of(
                NpcGearData.create()
        ));

        HIGHELVEN_CIVILIAN = new NpcData(ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "highelven.civilian"),
                EdumiaRaces.HIGHELVES, List.of(
                NpcGearData.create()
        ));

        FAIRY_CIVILIAN = new NpcData(ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "fairy.civilian"),
                EdumiaRaces.FAIRY, List.of(
                NpcGearData.create()
        ));

        HUMAN_CIVILIAN = new NpcData(ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "human.civilian"),
                EdumiaRaces.HUMAN, List.of(
                NpcGearData.create()
        ));


    }
}
