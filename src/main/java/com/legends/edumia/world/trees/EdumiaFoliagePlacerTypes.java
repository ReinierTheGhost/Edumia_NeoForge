package com.legends.edumia.world.trees;

import com.legends.edumia.Edumia;
import com.legends.edumia.world.trees.foliageplacer.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EdumiaFoliagePlacerTypes {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS =
            DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, Edumia.MOD_ID);
    public static final RegistryObject<FoliagePlacerType<GhostGumFoliagePlacer>> GHOST_GUM_FOLIAGE_PLACER =
            FOLIAGE_PLACERS.register("ghost_gum_foliage_placer",
                    () -> new FoliagePlacerType<>(GhostGumFoliagePlacer.CODEC));

    public static final RegistryObject<FoliagePlacerType<AspenFoliagePlacer>> ASPEN_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("aspen_foliage_placer",
            () -> new FoliagePlacerType<>(AspenFoliagePlacer.CODEC));

    public static final RegistryObject<FoliagePlacerType<BoughsFoliagePlacer>> BOUGHS_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("boughs_foliage_placer",
            () -> new FoliagePlacerType<>(BoughsFoliagePlacer.CODEC));

    public static final RegistryObject<FoliagePlacerType<CederFoliagePlacer>> CEDER_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("ceder_foliage_placer",
            () -> new FoliagePlacerType<>(CederFoliagePlacer.CODEC));

    public static final RegistryObject<FoliagePlacerType<EmptyFoliagePlacer>> EMPTY_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("empty_foliage_placer",
            () -> new FoliagePlacerType<>(EmptyFoliagePlacer.CODEC));

    public static final RegistryObject<FoliagePlacerType<ClusterFoliagePlacer>> CLUSTER_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("cluster_foliage_placer",
            () -> new FoliagePlacerType<>(ClusterFoliagePlacer.CODEC));

    public static final RegistryObject<FoliagePlacerType<CypressFoliagePlacer>> CYPRESS_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("cypress_foliage_placer",
            () -> new FoliagePlacerType<>(CypressFoliagePlacer.CODEC));
    public static final RegistryObject<FoliagePlacerType<GoldenOakFoliagePlacer>> GOLDEN_OAK_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("golden_oak_foliage_placer",
            () -> new FoliagePlacerType<>(GoldenOakFoliagePlacer.CODEC));
    public static final RegistryObject<FoliagePlacerType<DesertFoliagePlacer>> DESERT_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("desert_foliage_placer",
            () -> new FoliagePlacerType<>(DesertFoliagePlacer.CODEC));

    public static final RegistryObject<FoliagePlacerType<FirFoliagePlacer>> FIR_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("fir_foliage_placer",
            () -> new FoliagePlacerType<>(FirFoliagePlacer.CODEC));

    public static final RegistryObject<FoliagePlacerType<HollyFoliagePlacer>> HOLLY_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("holly_foliage_placer",
            () -> new FoliagePlacerType<>(HollyFoliagePlacer.CODEC));

    public static final RegistryObject<FoliagePlacerType<SilverSpruceFoliagePlacer>> SILVER_SPRUCE_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("silver_spruce_foliage_placer",
            () -> new FoliagePlacerType<>(SilverSpruceFoliagePlacer.CODEC));

    public static final RegistryObject<FoliagePlacerType<OakFoliagePlacer>> OAK_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("oak_foliage_placer",
            () -> new FoliagePlacerType<>(OakFoliagePlacer.CODEC));
    public static final RegistryObject<FoliagePlacerType<EdumiaPineFoliagePlacer>> PINE_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("edumia_pine_foliage_placer",
            () -> new FoliagePlacerType<>(EdumiaPineFoliagePlacer.CODEC));

    public static final RegistryObject<FoliagePlacerType<PinusFoliagePlacer>> PINUS_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("pinus_foliage_placer",
            () -> new FoliagePlacerType<>(PinusFoliagePlacer.CODEC));

    public static final RegistryObject<FoliagePlacerType<DragonBloodFoliagePlacer>> DRAGON_BLOOD_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("dragon_blood_foliage_placer",
            () -> new FoliagePlacerType<>(DragonBloodFoliagePlacer.CODEC));

    public static final RegistryObject<FoliagePlacerType<MahoganyFoliagePlacer>> MAHOGANY_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("mahogany_foliage_placer",
            () -> new FoliagePlacerType<>(MahoganyFoliagePlacer.CODEC));
    public static final RegistryObject<FoliagePlacerType<RedwoodFoliagePlacer>> REDWOOD_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("redwood_foliage_placer",
            () -> new FoliagePlacerType<>( RedwoodFoliagePlacer.CODEC));
    public static final RegistryObject<FoliagePlacerType<CitrusFoliagePlacer>> CITRUS_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("citrus_foliage_placer",
            () -> new FoliagePlacerType<>(CitrusFoliagePlacer.CODEC));
    public static final RegistryObject<FoliagePlacerType<PapayaFoliagePlacer>> PAPAYA_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("papaya_foliage_placer",
            () -> new FoliagePlacerType<>(PapayaFoliagePlacer.CODEC));
    public static final RegistryObject<FoliagePlacerType<PleodendronFoliagePlacer>> PLEODENDRON_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("pleodendron_foliage_placer",
            () -> new FoliagePlacerType<>(PleodendronFoliagePlacer.CODEC));
    public static final RegistryObject<FoliagePlacerType<WillowFoliagePlacer>> WILLOW_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("willow_foliage_placer",
            () -> new FoliagePlacerType<>(WillowFoliagePlacer.CODEC));
    public static final RegistryObject<FoliagePlacerType<CajoleFoliagePlacer>> CAJOLE_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("cajole_foliage_placer",
            () -> new FoliagePlacerType<>(CajoleFoliagePlacer.CODEC));
    public static final RegistryObject<FoliagePlacerType<DoubleDiskFoliagePlacer>> DOUBLE_DISK_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("double_disk_foliage_placer",
            () -> new FoliagePlacerType<>(DoubleDiskFoliagePlacer.CODEC));

    public static final RegistryObject<FoliagePlacerType<PointedFoliagePlacer>> POINTED_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("pointed_foliage_placer",
            () -> new FoliagePlacerType<>(PointedFoliagePlacer.CODEC));
    public static final RegistryObject<FoliagePlacerType<StackedFoliagePlacer>> STACKED_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("stacked_foliage_placer",
            () -> new FoliagePlacerType<>(StackedFoliagePlacer.CODEC));
    public static final RegistryObject<FoliagePlacerType<PalmFoliagePlacer>> PALM_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("palm_foliage_placer",
            () -> new FoliagePlacerType<>(PalmFoliagePlacer.CODEC));
    public static final RegistryObject<FoliagePlacerType<CoconutFoliagePlacer>> COCONUT_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("coconut_foliage_placer",
            () -> new FoliagePlacerType<>(CoconutFoliagePlacer.CODEC));
    public static final RegistryObject<FoliagePlacerType<BananaFoliagePlacer>> BANANA_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("banana_foliage_placer",
            () -> new FoliagePlacerType<>(BananaFoliagePlacer.CODEC));
    public static final RegistryObject<FoliagePlacerType<RandomPalmLeavesFoliagePlacer>> RANDOM_PALM_LEAVES_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("random_palm_leaves_foliage_placer",
            () -> new FoliagePlacerType<>(RandomPalmLeavesFoliagePlacer.CODEC));
    public static final RegistryObject<FoliagePlacerType<OvalFoliagePlacer>> OVAL_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("oval_foliage_placer",
            () -> new FoliagePlacerType<>(OvalFoliagePlacer.CODEC));

    public static final RegistryObject<FoliagePlacerType<ParasolPalmFoliagePlacer>> PARASOL_PALM_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("parasol_palm_foliage_placer",
            () -> new FoliagePlacerType<>(ParasolPalmFoliagePlacer.CODEC));
    public static final RegistryObject<FoliagePlacerType<LargeParasolPalmFoliagePlacer>> LARGE_PARASOL_PALM_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("large_parasol_palm_foliage_placer",
            () -> new FoliagePlacerType<>(LargeParasolPalmFoliagePlacer.CODEC));

    public static final RegistryObject<FoliagePlacerType<ColossalPalmFoliagePlacer>> COLOSSAL_PALM_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("colossal_palm_foliage_placer",
            () -> new FoliagePlacerType<>(ColossalPalmFoliagePlacer.CODEC));

    public static final RegistryObject<FoliagePlacerType<TropicalFruitFoliagePlacer>> TROPICAL_FRUIT_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("tropical_fruit_foliage_placer",
            () -> new FoliagePlacerType<>(TropicalFruitFoliagePlacer.CODEC));

    public static final RegistryObject<FoliagePlacerType<NewPalmFoliagePlacer>> NEW_PALM_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("new_palm_foliage_placer",
            () -> new FoliagePlacerType<>(NewPalmFoliagePlacer.CODEC));

    public static final RegistryObject<FoliagePlacerType<AlmondFoliagePlacer>> ALMOND_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("almond_foliage_placer",
            () -> new FoliagePlacerType<>(AlmondFoliagePlacer.CODEC));

    public static final RegistryObject<FoliagePlacerType<OliveFoliagePlacer>> OLIVE_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("olive_foliage_placer",
            () -> new FoliagePlacerType<>(OliveFoliagePlacer.CODEC));
    public static final RegistryObject<FoliagePlacerType<AshiraFoliagePlacer>> ASHIRA_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("ashira_foliage_placer",
            () -> new FoliagePlacerType<>(AshiraFoliagePlacer.CODEC));

    public static final RegistryObject<FoliagePlacerType<HeveaFoliagePlacer>> HEVEA_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("hevea_foliage_placer",
            () -> new FoliagePlacerType<>(HeveaFoliagePlacer.CODEC));

    public static void register(IEventBus eventBus) {
        FOLIAGE_PLACERS.register(eventBus);
    }
}
