package com.legends.edumia.resources;

import com.legends.edumia.Edumia;
import com.legends.edumia.resources.datas.RaceType;
import com.legends.edumia.resources.datas.races.Race;
import com.legends.edumia.resources.datas.races.data.AttributeData;
import com.legends.edumia.utils.LoggerUtil;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import net.neoforged.neoforge.registries.NewRegistryEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@EventBusSubscriber(modid = Edumia.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class EdumiaRaces {
    public static final String PATH = "races";
    public static final ResourceKey<Registry<Race>> RACE_KEY =
            ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, PATH));

    public final static Race FAIRY;


    @SubscribeEvent
    public static void onNewRegistry(DataPackRegistryEvent.NewRegistry event) {
        LoggerUtil.logDebugMsg("Registering Dynamic Races for " + Edumia.MOD_ID);
        event.dataPackRegistry(RACE_KEY, Race.CODEC);
    }


    public static void bootstrap(BootstrapContext<Race> context) {
        HolderGetter<Race> raceRegistryEntryLookup = context.lookup(RACE_KEY);
        // Registering all races
        register(context, raceRegistryEntryLookup, FAIRY);
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
        FAIRY = new Race(ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "fairy"), RaceType.FAIRY,
                new AttributeData(new HashMap<>(){{
                    put(Attributes.SCALE, 0.81);
                    put(Attributes.MAX_HEALTH, 22.0);
                    put(Attributes.ATTACK_DAMAGE, 1.0);
                    put(Attributes.ENTITY_INTERACTION_RANGE, 2.75);
                    put(Attributes.MOVEMENT_SPEED, 0.1);
                    put(Attributes.FALL_DAMAGE_MULTIPLIER, 0.75);
                }}), List.of(), List.of());
    }
}
