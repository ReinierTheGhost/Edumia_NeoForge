package com.legends.edumia.world.trees;

import com.legends.edumia.Edumia;
import com.legends.edumia.world.trees.trunkplacers.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class EdumiaTrunkPlacerTypes {

    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS =
            DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, Edumia.MOD_ID);
    public static final Supplier<TrunkPlacerType<BoughsTrunkPlacer>> BOUGHS_TRUNK_PLACER =
            TRUNK_PLACERS.register("boughs_trunk_placer", () -> new TrunkPlacerType<>(BoughsTrunkPlacer.CODEC));

    public static final Supplier<TrunkPlacerType<CederTrunkPlacer>> CEDER_TRUNK_PLACER =
            TRUNK_PLACERS.register("ceder_trunk_placer", () -> new TrunkPlacerType<>(CederTrunkPlacer.CODEC));

    public static final Supplier<TrunkPlacerType<EdumiaGiantTrunkPlacer>> EDUMIA_GIANT_TRUNK_PLACER =
            TRUNK_PLACERS.register("edumia_giant_trunk_placer", () -> new TrunkPlacerType<>(EdumiaGiantTrunkPlacer.CODEC));

    public static final Supplier<TrunkPlacerType<GoldenOakTrunkPlacer>> GOLDEN_OAK_TRUNK_PLACER =
            TRUNK_PLACERS.register("golden_oak_trunk_placer", () -> new TrunkPlacerType<>(GoldenOakTrunkPlacer.CODEC));

    public static final Supplier<TrunkPlacerType<DeadTrunkPlacer>> DEATH_TRUNK_PLACER =
            TRUNK_PLACERS.register("death_trunk_placer", () -> new TrunkPlacerType<>(DeadTrunkPlacer.CODEC));

    public static final Supplier<TrunkPlacerType<DesertTrunkPlacer>> DESERT_TRUNK_PLACER =
            TRUNK_PLACERS.register("desert_trunk_placer", () -> new TrunkPlacerType<>(DesertTrunkPlacer.CODEC));
    public static final Supplier<TrunkPlacerType<OakTrunkPlacer>> OAK_TRUNK_PLACER =
            TRUNK_PLACERS.register("oak_trunk_placer", () -> new TrunkPlacerType<>(OakTrunkPlacer.CODEC));

    public static final Supplier<TrunkPlacerType<PartyTreeTrunkPlacer>> PARTY_TREE_TRUNK_PLACER =
            TRUNK_PLACERS.register("party_tree_trunk_placer", () -> new TrunkPlacerType<>(PartyTreeTrunkPlacer.CODEC));

    public static final Supplier<TrunkPlacerType<SmallRedwoodTrunkPlacer>> SMALL_REDWOOD_TRUNK_PLACER =
            TRUNK_PLACERS.register("small_redwood_trunk_placer", () -> new TrunkPlacerType<>(SmallRedwoodTrunkPlacer.CODEC));
    public static final Supplier<TrunkPlacerType<MediumRedwoodTrunkPlacer>> MEDIUM_REDWOOD_TRUNK_PLACER =
            TRUNK_PLACERS.register("medium_redwood_trunk_placer", () -> new TrunkPlacerType<>(MediumRedwoodTrunkPlacer.CODEC));

    public static final Supplier<TrunkPlacerType<PalmTrunkPlacer>> PALM_TRUNK_PLACER =
            TRUNK_PLACERS.register("palm_trunk_placer", () -> new TrunkPlacerType<>(PalmTrunkPlacer.CODEC));
    public static final Supplier<TrunkPlacerType<CrossTrunkPlacer>> CROSS_TRUNK_PLACER =
            TRUNK_PLACERS.register("cross_trunk_placer", () -> new TrunkPlacerType<>(CrossTrunkPlacer.CODEC));

    public static final Supplier<TrunkPlacerType<FingerTrunkPlacer>> FINGER_TRUNK_PLACER =
            TRUNK_PLACERS.register("finger_trunk_placer", () -> new TrunkPlacerType<>(FingerTrunkPlacer.CODEC));
    public static final Supplier<TrunkPlacerType<DragonBloodTrunkPlacer>> DRAGON_BLOOD_TRUNK_PLACER =
            TRUNK_PLACERS.register("dragon_blood_trunk_placer", () -> new TrunkPlacerType<>(DragonBloodTrunkPlacer.CODEC));

    public static final Supplier<TrunkPlacerType<MahoganyTrunkPlacer>> MAHOGANY_TRUNK_PLACER =
            TRUNK_PLACERS.register("mahogany_trunk_placer", () -> new TrunkPlacerType<>(MahoganyTrunkPlacer.CODEC));

    public static final Supplier<TrunkPlacerType<ColossalTrunkPlacer>> COLOSSAL_TRUNK_PLACER =
            TRUNK_PLACERS.register("colossal_trunk_placer", () -> new TrunkPlacerType<>(ColossalTrunkPlacer.CODEC));

    public static final Supplier<TrunkPlacerType<PleodendronTrunkPlacer>> PLEODENDRON_TRUNK_PLACER =
            TRUNK_PLACERS.register("pleodendron_trunk_placer", () -> new TrunkPlacerType<>(PleodendronTrunkPlacer.CODEC));

    public static final Supplier<TrunkPlacerType<CitrusTrunkPlacer>> CITRUS_TRUNK_PLACER =
            TRUNK_PLACERS.register("citrus_trunk_placer", () -> new TrunkPlacerType<>(CitrusTrunkPlacer.CODEC));
    public static final Supplier<TrunkPlacerType<SlantedTrunkPlacer>> SLANTED_TRUNK_PLACER =
            TRUNK_PLACERS.register("slanted_trunk_placer", () -> new TrunkPlacerType<>(SlantedTrunkPlacer.CODEC));
    public static final Supplier<TrunkPlacerType<CanopyTrunkPlacer>> CANOPY_TRUNK_PLACER =
            TRUNK_PLACERS.register("canopy_trunk_placer", () -> new TrunkPlacerType<>(CanopyTrunkPlacer.CODEC));
    public static final Supplier<TrunkPlacerType<LargeTrunkPlacer>> LARGE_TRUNK_PLACER =
            TRUNK_PLACERS.register("large_trunk_placer", () -> new TrunkPlacerType<>(LargeTrunkPlacer.CODEC));
    public static final Supplier<TrunkPlacerType<SpruceTrunkPlacer>> SPRUCE_TRUNK_PLACER =
            TRUNK_PLACERS.register("spruce_trunk_placer", () -> new TrunkPlacerType<>(SpruceTrunkPlacer.CODEC));
    public static final Supplier<TrunkPlacerType<BirchTrunkPlacer>> BIRCH_TREE_TRUNK_PLACER =
            TRUNK_PLACERS.register("birch_tree_trunk_placer", () -> new TrunkPlacerType<>(BirchTrunkPlacer.CODEC));
    public static final Supplier<TrunkPlacerType<FracturedYellowMerantiTrunkPlacer>> FRACTURED_YELLOW_MERANTI_TRUNK_PLACER =
            TRUNK_PLACERS.register(
            "fractured_yellow_meranti_trunk_placer", () -> new TrunkPlacerType<>(FracturedYellowMerantiTrunkPlacer.CODEC));

    public static final Supplier<TrunkPlacerType<YellowMerantiTrunkPlacer>> YELLOW_MERANTI_TRUNK_PLACER =
            TRUNK_PLACERS.register("yellow_meranti_trunk_placer", () -> new TrunkPlacerType<>(YellowMerantiTrunkPlacer.CODEC));
    public static final Supplier<TrunkPlacerType<BaobabTrunkPlacer>> BAOBAB_TRUNK_PLACER =
            TRUNK_PLACERS.register("baobab_trunk_placer", () -> new TrunkPlacerType<>(BaobabTrunkPlacer.CODEC));

    public static final Supplier<TrunkPlacerType<AncientOakTrunkPlacer>> ANCIENT_OAK_TRUNK_PLACER =
            TRUNK_PLACERS.register("ancient_oak_trunk_placer", () -> new TrunkPlacerType<>(AncientOakTrunkPlacer.CODEC));

    public static final Supplier<TrunkPlacerType<ClusteredPalmTrunkPlacer>> CLUSTERED_PALM_TRUNK_PLACER =
            TRUNK_PLACERS.register("clustered_palm_trunk_placer",
                    () -> new TrunkPlacerType<>(ClusteredPalmTrunkPlacer.CODEC));


    public static void register(IEventBus eventBus) {
        TRUNK_PLACERS.register(eventBus);
    }


}
