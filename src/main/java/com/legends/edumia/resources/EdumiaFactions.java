package com.legends.edumia.resources;

import com.legends.edumia.Edumia;
import com.legends.edumia.resources.datas.Disposition;
import com.legends.edumia.resources.datas.FactionType;
import com.legends.edumia.resources.datas.factions.Faction;
import com.legends.edumia.resources.datas.factions.data.BannerData;
import com.legends.edumia.resources.datas.factions.data.SpawnData;
import com.legends.edumia.resources.datas.factions.data.SpawnDataHandler;
import com.legends.edumia.resources.datas.npcs.data.NpcRank;
import com.legends.edumia.utils.EdumiaBannerPatterns;
import com.legends.edumia.utils.EdumiaLog;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
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

    public static final Faction HIGH_ELVES;
    public static final Faction HUMAN;
    public static final Faction FAIRIES;
//    public static final Faction GENSAI;
    public static final Faction DARK_ELVES;
    public static final Faction ORCS;
//    public static final Faction DEMONS;
//    public static final Faction OGRES;


    @SubscribeEvent
    public static void onNewRegistry(DataPackRegistryEvent.NewRegistry event){
        EdumiaLog.logDebugMsg("Registering Dynamic Factions for " + Edumia.MOD_ID);
        event.dataPackRegistry(FACTION_KEY, Faction.CODEC, Faction.CODEC);
    }

    public static void bootstrap(BootstrapContext<Faction> context){
        HolderGetter<Faction> factionHolderGetter = context.lookup(FACTION_KEY);

        register(context, factionHolderGetter, HIGH_ELVES);
        register(context, factionHolderGetter, FAIRIES);
        register(context, factionHolderGetter, HUMAN);
        register(context, factionHolderGetter, DARK_ELVES);
        register(context, factionHolderGetter, ORCS);
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
        FAIRIES = new Faction("fairies", true, Disposition.NEUTRAL, FactionType.FACTION, null, null,
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
                new BannerData(DyeColor.GREEN, List.of(
                        new BannerData.BannerPatternWithColor(EdumiaBannerPatterns.FAIRIES, DyeColor.CYAN)
                )),
                new SpawnDataHandler(List.of(
                        new SpawnData(ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "fairy.glyndoril"),
                                new Vector2d(440, 540))
                )), List.of(), List.of()
        );
        // endregion

        // region [HUMAN]
        HUMAN = new Faction("human", true, Disposition.NEUTRAL, FactionType.FACTION, null, null,
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
                new BannerData(DyeColor.BLUE, List.of(
                        new BannerData.BannerPatternWithColor(EdumiaBannerPatterns.HUMANS, DyeColor.GRAY)
                )),
                new SpawnDataHandler(List.of(
                        new SpawnData(ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "human.stormgard"),
                                new Vector2d(1662, 1940))
                )), List.of(), List.of()
        );
        // endregion

        // region [HIGH_ELVES]
        HIGH_ELVES = new Faction("high_elves", true, Disposition.NEUTRAL, FactionType.FACTION, null, null,
                new HashMap<>(){{
                    put(NpcRank.CIVILIAN, List.of(
                            EdumiaNpcs.HIGHELVEN_CIVILIAN
                    ));
                    put(NpcRank.MILITIA, List.of(
                            EdumiaNpcs.HIGHELVEN_CIVILIAN
                    ));
                    put(NpcRank.SOLDIER, List.of(
                            EdumiaNpcs.HIGHELVEN_CIVILIAN
                    ));
                    put(NpcRank.KNIGHT, List.of(
                            EdumiaNpcs.HIGHELVEN_CIVILIAN
                    ));
                    put(NpcRank.VETERAN, List.of(
                            EdumiaNpcs.HIGHELVEN_CIVILIAN
                    ));
                    put(NpcRank.LEADER, List.of(
                            EdumiaNpcs.HIGHELVEN_CIVILIAN
                    ));
                }},
                new BannerData(DyeColor.ORANGE, List.of(
                        new BannerData.BannerPatternWithColor(EdumiaBannerPatterns.HIGH_ELVEN, DyeColor.YELLOW)
                )),
                new SpawnDataHandler(List.of(
                        new SpawnData(ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "highelves.elensar"),
                                new Vector2d(570, 1840))
                )), List.of(), List.of()
        );

        // endregion

        // region [DARK_ELVES]
        DARK_ELVES = new Faction("dark_elves", true, Disposition.NEUTRAL, FactionType.FACTION,
                null, null,
                new HashMap<>(){{
                    put(NpcRank.CIVILIAN, List.of(
                            EdumiaNpcs.DARKELVEN_CIVILIAN
                    ));
                    put(NpcRank.MILITIA, List.of(
                            EdumiaNpcs.DARKELVEN_CIVILIAN
                    ));
                    put(NpcRank.SOLDIER, List.of(
                            EdumiaNpcs.DARKELVEN_CIVILIAN
                    ));
                    put(NpcRank.KNIGHT, List.of(
                            EdumiaNpcs.DARKELVEN_CIVILIAN
                    ));
                    put(NpcRank.VETERAN, List.of(
                            EdumiaNpcs.DARKELVEN_CIVILIAN
                    ));
                    put(NpcRank.LEADER, List.of(
                            EdumiaNpcs.DARKELVEN_CIVILIAN
                    ));
                }},
                new BannerData(DyeColor.BLUE, List.of(
                        new BannerData.BannerPatternWithColor(EdumiaBannerPatterns.DARK_ELVEN, DyeColor.PURPLE)
                )),
                new SpawnDataHandler(List.of(
                        new SpawnData(ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "darkelven.nyxarith"),
                                new Vector2d(1980, 525))
                )), List.of(), List.of()
        );
        // endregion

        // region [ORCS]
        ORCS = new Faction("orcs", true, Disposition.NEUTRAL, FactionType.FACTION, null, null,
                new HashMap<>(){{
                    put(NpcRank.CIVILIAN, List.of(
                            EdumiaNpcs.ORC_CIVILIAN
                    ));
                    put(NpcRank.MILITIA, List.of(
                            EdumiaNpcs.ORC_CIVILIAN
                    ));
                    put(NpcRank.SOLDIER, List.of(
                            EdumiaNpcs.ORC_CIVILIAN
                    ));
                    put(NpcRank.KNIGHT, List.of(
                            EdumiaNpcs.ORC_CIVILIAN
                    ));
                    put(NpcRank.VETERAN, List.of(
                            EdumiaNpcs.ORC_CIVILIAN
                    ));
                    put(NpcRank.LEADER, List.of(
                            EdumiaNpcs.ORC_CIVILIAN
                    ));
                }},
                new BannerData(DyeColor.BROWN, List.of(
                        new BannerData.BannerPatternWithColor(EdumiaBannerPatterns.ORCS, DyeColor.WHITE)
                )),
                new SpawnDataHandler(List.of(
                        new SpawnData(ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "orc.gorakthul"),
                                new Vector2d(400, 1340))
                )), List.of(), List.of()
        );
        // endregion
    }
}
