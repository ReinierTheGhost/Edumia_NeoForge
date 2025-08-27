package com.legends.edumia.resources;

import com.legends.edumia.Edumia;
import com.legends.edumia.resources.datas.RaceType;
import com.legends.edumia.resources.datas.races.Race;
import com.legends.edumia.resources.datas.races.data.AttributeData;
import com.legends.edumia.utils.EdumiaLog;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@EventBusSubscriber(modid = Edumia.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class EdumiaRaces {
    public static final String PATH = "races";
    public static final ResourceKey<Registry<Race>> RACE_KEY =
            ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, PATH));

    public final static Race FAIRY;
    public final static Race HUMAN;
    public final static Race HIGHELVES;
    public final static Race DARKELVES;
    public final static Race ORC;
    public final static Race OGRE;
    public final static Race DEMON;
    public final static Race GENSAI;


    @SubscribeEvent
    public static void onNewRegistry(DataPackRegistryEvent.NewRegistry event) {
        EdumiaLog.logDebugMsg("Registering Dynamic Races for " + Edumia.MOD_ID);
        event.dataPackRegistry(RACE_KEY, Race.CODEC, Race.CODEC);
    }


    public static void bootstrap(BootstrapContext<Race> context) {
        HolderGetter<Race> raceRegistryEntryLookup = context.lookup(RACE_KEY);
        // Registering all races
        register(context, raceRegistryEntryLookup, DARKELVES);
        register(context, raceRegistryEntryLookup, HIGHELVES);
        register(context, raceRegistryEntryLookup, FAIRY);
        register(context, raceRegistryEntryLookup, HUMAN);
        register(context, raceRegistryEntryLookup, ORC);
        register(context, raceRegistryEntryLookup, OGRE);
        register(context, raceRegistryEntryLookup, DEMON);
        register(context, raceRegistryEntryLookup, GENSAI);
    }

    private static Race register(BootstrapContext<Race> context, HolderGetter<Race> raceRegistryEntryLookup, Race race) {
        ResourceKey<Race> raceRegistryKey = of(race.getId().getPath());
        String name = raceRegistryKey.location().getPath();
        ResourceKey<Race> newRace = ResourceKey.create(RACE_KEY,ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID,name));

        Optional<Holder.Reference<Race>> optionalRace = raceRegistryEntryLookup.get(raceRegistryKey);
        optionalRace.ifPresent(raceReference -> context.register(newRace, race));

        return race;
    }
    private static ResourceKey<Race> of(String name) {
        return ResourceKey.create(RACE_KEY, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, name));
    }


    static {

        DARKELVES = new Race(ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "darkelves"), RaceType.DARK_ELF,
                new AttributeData(new HashMap<>(){{
                    put(Attributes.SCALE, 1.05);
                    put(Attributes.MAX_HEALTH, 22.0);
                    put(Attributes.ATTACK_DAMAGE, 1.0);
                    put(Attributes.ENTITY_INTERACTION_RANGE, 2.75);
                    put(Attributes.MOVEMENT_SPEED, 0.1);
                    put(Attributes.FALL_DAMAGE_MULTIPLIER, 0.75);
                }}), List.of(), List.of(), List.of());

        HIGHELVES = new Race(ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "highelves"), RaceType.HIGH_ELF,
                new AttributeData(new HashMap<>(){{
                    put(Attributes.SCALE, 1.05);
                    put(Attributes.MAX_HEALTH, 22.0);
                    put(Attributes.ATTACK_DAMAGE, 1.0);
                    put(Attributes.ENTITY_INTERACTION_RANGE, 2.75);
                    put(Attributes.MOVEMENT_SPEED, 0.1);
                    put(Attributes.FALL_DAMAGE_MULTIPLIER, 0.75);
                }}), List.of(), List.of(), List.of());

        FAIRY = new Race(ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "fairy"), RaceType.FAIRY,
                new AttributeData(new HashMap<>(){{
                    put(Attributes.SCALE, 0.81);
                    put(Attributes.MAX_HEALTH, 22.0);
                    put(Attributes.ATTACK_DAMAGE, 1.0);
                    put(Attributes.ENTITY_INTERACTION_RANGE, 2.75);
                    put(Attributes.MOVEMENT_SPEED, 0.1);
                    put(Attributes.FALL_DAMAGE_MULTIPLIER, 0.75);
                }}), List.of(), List.of(), List.of());

        HUMAN = new Race(ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "human"), RaceType.HUMAN,
                new AttributeData(new HashMap<>(){{
                    put(Attributes.SCALE, 1.0);
                    put(Attributes.MAX_HEALTH, 22.0);
                    put(Attributes.ATTACK_DAMAGE, 1.0);
                    put(Attributes.ENTITY_INTERACTION_RANGE, 2.75);
                    put(Attributes.MOVEMENT_SPEED, 0.1);
                }}), List.of(), List.of(), List.of());

        ORC = new Race(ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "orc"), RaceType.ORC,
                new AttributeData(new HashMap<>(){{
                    put(Attributes.SCALE, 1.4);
                    put(Attributes.MAX_HEALTH, 22.0);
                    put(Attributes.ATTACK_DAMAGE, 1.0);
                    put(Attributes.ENTITY_INTERACTION_RANGE, 2.75);
                    put(Attributes.MOVEMENT_SPEED, 0.1);
                }}), List.of(), List.of(), List.of());

        OGRE = new Race(ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "ogre"), RaceType.OGRE,
                new AttributeData(new HashMap<>(){{
                    put(Attributes.SCALE, 1.25);
                    put(Attributes.MAX_HEALTH, 22.0);
                    put(Attributes.ATTACK_DAMAGE, 1.0);
                    put(Attributes.ENTITY_INTERACTION_RANGE, 2.75);
                    put(Attributes.MOVEMENT_SPEED, 0.1);
                }}), List.of(), List.of(), List.of());

        DEMON = new Race(ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "demon"), RaceType.DEMON,
                new AttributeData(new HashMap<>(){{
                    put(Attributes.SCALE, 1.20);
                    put(Attributes.MAX_HEALTH, 22.0);
                    put(Attributes.ATTACK_DAMAGE, 1.0);
                    put(Attributes.ENTITY_INTERACTION_RANGE, 2.75);
                    put(Attributes.MOVEMENT_SPEED, 0.1);
                }}), List.of(), List.of(), List.of());

        GENSAI = new Race(ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "gensai"), RaceType.GENSAI,
                new AttributeData(new HashMap<>(){{
                    put(Attributes.SCALE, 1.0);
                    put(Attributes.MAX_HEALTH, 22.0);
                    put(Attributes.ATTACK_DAMAGE, 1.0);
                    put(Attributes.ENTITY_INTERACTION_RANGE, 2.75);
                    put(Attributes.MOVEMENT_SPEED, 0.1);
                    put(Attributes.FALL_DAMAGE_MULTIPLIER, 1.0);
                }}), List.of(), List.of(), List.of());
    }
}
