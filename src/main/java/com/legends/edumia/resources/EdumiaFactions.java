package com.legends.edumia.resources;

import com.legends.edumia.Edumia;
import com.legends.edumia.resources.datas.Disposition;
import com.legends.edumia.resources.datas.FactionType;
import com.legends.edumia.resources.datas.factions.Faction;
import com.legends.edumia.resources.datas.factions.data.BannerData;
import com.legends.edumia.resources.datas.factions.data.SpawnData;
import com.legends.edumia.resources.datas.factions.data.SpawnDataHandler;
import com.legends.edumia.resources.datas.npcs.data.NpcRank;
import com.legends.edumia.utils.EdumiaLog;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BannerPatterns;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import org.joml.Vector2d;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@EventBusSubscriber(modid = Edumia.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class EdumiaFactions {
    public static final String PATH = "factions";
    public static final ResourceKey<Registry<Faction>> FACTION_KEY = key(PATH);

    public static final Faction FAIRIES;
    public static final Faction HUMAN;

    @SubscribeEvent
    public static void onNewRegistry(DataPackRegistryEvent.NewRegistry event){
        EdumiaLog.logDebugMsg("Registering Dynamic Factions for " + Edumia.MOD_ID);
        event.dataPackRegistry(FACTION_KEY, Faction.CODEC);
    }

    public static void bootstrap(BootstrapContext<Faction> context){
        HolderGetter<Faction> factionHolderGetter = context.lookup(FACTION_KEY);

        register(context, factionHolderGetter, FAIRIES);
        register(context, factionHolderGetter, HUMAN);
    }

    private static Faction register(BootstrapContext<Faction> context, HolderGetter<Faction> factionHolderGetter, Faction faction) {
        ResourceKey<Faction> factionResourceKey = of(faction.getName());
        String name = factionResourceKey.location().getPath();
        ResourceKey<Faction> factionKey = ResourceKey.create(FACTION_KEY, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, name));

        Optional<Holder.Reference<Faction>> optionalFaction = factionHolderGetter.get(factionResourceKey);
        optionalFaction.ifPresent(biomeReference -> context.register(factionKey, faction));

        return faction;
    }

    private static ResourceKey<Faction> of(String name) {
        return ResourceKey.create(FACTION_KEY, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, name));
    }

    private static <T> ResourceKey<Registry<T>> key(String name) {
        return ResourceKey.createRegistryKey(Edumia.location(name));
    }

    static {
        // region [FAIRIES]
        FAIRIES = new Faction("fairies", true, Disposition.GOOD, FactionType.FACTION, null, null,
                new HashMap<>(){{
                    put(NpcRank.CIVILIAN, List.of(
                            EdumiaNpcs.FAIRY_CIVILIAN
                    ));
                    put(NpcRank.MILITIA, List.of(
                            EdumiaNpcs.FAIRY_CIVILIAN
                    ));
                    put(NpcRank.SOLDIER, List.of(
                            EdumiaNpcs.FAIRY_CIVILIAN
                    ));
                    put(NpcRank.KNIGHT, List.of(
                            EdumiaNpcs.FAIRY_CIVILIAN
                    ));
                    put(NpcRank.VETERAN, List.of(
                            EdumiaNpcs.FAIRY_CIVILIAN
                    ));
                    put(NpcRank.LEADER, List.of(
                            EdumiaNpcs.FAIRY_CIVILIAN
                    ));
                }},
                new BannerData(DyeColor.WHITE, List.of(
                        new BannerData.BannerPatternWithColor(BannerPatterns.FLOWER, DyeColor.GREEN)
                )),
                new SpawnDataHandler(List.of(
                        new SpawnData(ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "fairy.glyndoril"),
                                new Vector2d(1945, 1785))
                )), List.of(), List.of()
        );
        // endregion

        HUMAN = new Faction("human", true, Disposition.GOOD, FactionType.FACTION, null, null,
                new HashMap<>(){{
                    put(NpcRank.CIVILIAN, List.of(
                            EdumiaNpcs.HUMAN_CIVILIAN
                    ));
                    put(NpcRank.MILITIA, List.of(
                            EdumiaNpcs.HUMAN_CIVILIAN
                    ));
                    put(NpcRank.SOLDIER, List.of(
                            EdumiaNpcs.HUMAN_CIVILIAN
                    ));
                    put(NpcRank.KNIGHT, List.of(
                            EdumiaNpcs.HUMAN_CIVILIAN
                    ));
                    put(NpcRank.VETERAN, List.of(
                            EdumiaNpcs.HUMAN_CIVILIAN
                    ));
                    put(NpcRank.LEADER, List.of(
                            EdumiaNpcs.HUMAN_CIVILIAN
                    ));
                }},
                new BannerData(DyeColor.BROWN, List.of(
                        new BannerData.BannerPatternWithColor(BannerPatterns.CROSS, DyeColor.RED)
                )),
                new SpawnDataHandler(List.of(
                        new SpawnData(ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "human.stormgard"),
                                new Vector2d(1000, 1000))
                )), List.of(), List.of()
        );
    }
}
