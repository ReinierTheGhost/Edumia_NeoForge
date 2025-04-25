package com.legends.edumia.world.biomes;


import com.legends.edumia.Edumia;
import com.legends.edumia.utils.LoggerUtil;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;


public class EdumiaBiomeKeys extends Biomes {
    // region SURFACE


    public static final ResourceKey<Biome> DEMON_WASTELANDS = EdumiaBiomeKeys.register("demon_wastelands");
    public static final ResourceKey<Biome> EDUMIA_VALES = EdumiaBiomeKeys.register("edumia_vales");


    public static final ResourceKey<Biome> GENSAI_BEACH = EdumiaBiomeKeys.register("gensai_beach");

    public static final ResourceKey<Biome> DEAD_MARSHES = EdumiaBiomeKeys.register("dead_marshes");
    public static final ResourceKey<Biome> DEAD_MARSHES_WATER = EdumiaBiomeKeys.register("dead_marshes_water");


    public static final ResourceKey<Biome> DARK_ELF_FOREST = EdumiaBiomeKeys.register("dark_elf_forest");
    public static final ResourceKey<Biome> EDUMIA_TUNDRA = EdumiaBiomeKeys.register("edumia_tundra");


    public static final ResourceKey<Biome> ORC_DESERT = EdumiaBiomeKeys.register("orc_desert");
    public static final ResourceKey<Biome> RED_ORC_DESERT = EdumiaBiomeKeys.register("red_orc_desert");

    public static final ResourceKey<Biome> AVELION_PLAINS = EdumiaBiomeKeys.register("avelion_plains");
    public static final ResourceKey<Biome> AVELION_FOREST = EdumiaBiomeKeys.register("avelion_forest");
    public static final ResourceKey<Biome> AVELION_HIDDEN_BLOSSOM = EdumiaBiomeKeys.register("avelion_hidden_blossom");
    public static final ResourceKey<Biome> AVELION_MEADOW = EdumiaBiomeKeys.register("avelion_meadow");
    public static final ResourceKey<Biome> AVELION_SANDY_SHORES = EdumiaBiomeKeys.register("avelion_sandy_shores");
    public static final ResourceKey<Biome> AVELION_ROCKY_SHORES = EdumiaBiomeKeys.register("avelion_rocky_shores");
    public static final ResourceKey<Biome> AVELION_FOOTHILLS = EdumiaBiomeKeys.register("avelion_foothills");
    public static final ResourceKey<Biome> AVELION_MOUNTAIN_BASE = EdumiaBiomeKeys.register("avelion_mountain_base");
    public static final ResourceKey<Biome> AVELION_MOUNTAIN = EdumiaBiomeKeys.register("avelion_mountain");
    public static final ResourceKey<Biome> AVELION_MOUNTAIN_PEAKS = EdumiaBiomeKeys.register("avelion_mountain_peaks");


    public static final ResourceKey<Biome> OGRE_FOREST = EdumiaBiomeKeys.register("ogre_forest");

    public static final ResourceKey<Biome> FAIRY_FOREST = EdumiaBiomeKeys.register("fairy_forest");

    public static final ResourceKey<Biome> EDUMIA_FOOTHILLS = EdumiaBiomeKeys.register("edumia_foothills");
    public static final ResourceKey<Biome> EDUMIA_MOUNTAINS_BASE = EdumiaBiomeKeys.register("edumia_mountains_base");
    public static final ResourceKey<Biome> EDUMIA_MOUNTAINS = EdumiaBiomeKeys.register("edumia_mountains");
    public static final ResourceKey<Biome> EDUMIA_MOUNTAINS_PEAKS = EdumiaBiomeKeys.register("edumia_mountains_peaks");

    public static final ResourceKey<Biome> GENSAI_VOLCANO_PLAINS = EdumiaBiomeKeys.register("gensai_volcano_plains");
    public static final ResourceKey<Biome> MOUNT_TITLEIST_CRATER = EdumiaBiomeKeys.register("mount_titleist_crater");
    public static final ResourceKey<Biome> MOUNT_TITLEIST_FOOT = EdumiaBiomeKeys.register("mount_titleist_foot");
    public static final ResourceKey<Biome> MOUNT_TITLEIST = EdumiaBiomeKeys.register("mount_titleist");
    public static final ResourceKey<Biome> MOUNT_TITLEIST_PEAK = EdumiaBiomeKeys.register("mount_titleist_peak");

    public static final ResourceKey<Biome> GENSAI_REEF = EdumiaBiomeKeys.register("gensai_reef");
    public static final ResourceKey<Biome> GENSAI_JUNGLE = EdumiaBiomeKeys.register("gensai_jungle");
    public static final ResourceKey<Biome> GENSAI_SAKURA_GROVE = EdumiaBiomeKeys.register("gensai_sakura_grove");

    public static final ResourceKey<Biome> TAIGA_FOREST = EdumiaBiomeKeys.register("taiga_forest");


    public static final ResourceKey<Biome> MOUNTAIN_STREAM = EdumiaBiomeKeys.register("mountain_stream");

    public static final ResourceKey<Biome> OCEAN = EdumiaBiomeKeys.register("ocean");
    public static final ResourceKey<Biome> OCEAN_COAST = EdumiaBiomeKeys.register("ocean_coast");
    public static final ResourceKey<Biome> RIVER = EdumiaBiomeKeys.register("edumia_river");
    public static final ResourceKey<Biome> OASIS = EdumiaBiomeKeys.register("oasis");
    public static final ResourceKey<Biome> POND = EdumiaBiomeKeys.register("pond");
    public static final ResourceKey<Biome> FAIRY_SWAMP = EdumiaBiomeKeys.register("fairy_swamp");
    public static final ResourceKey<Biome> GREAT_RIVER = EdumiaBiomeKeys.register("great_river");
    public static final ResourceKey<Biome> WASTE_POND = EdumiaBiomeKeys.register("waste_pond");
    public static final ResourceKey<Biome> FROZEN_OCEAN = EdumiaBiomeKeys.register("frozen_ocean");
    public static final ResourceKey<Biome> FROZEN_POND = EdumiaBiomeKeys.register("frozen_pond");
    // endregion

    // region CAVES
    public static final ResourceKey<Biome> BASIC_CAVE = EdumiaBiomeKeys.register("basic_cave");
    public static final ResourceKey<Biome> LUSH_CAVE = EdumiaBiomeKeys.register("lush_cave");
    public static final ResourceKey<Biome> DRIPSTONE_CAVE = EdumiaBiomeKeys.register("dripstone_cave");
    public static final ResourceKey<Biome> MUD_CAVE = EdumiaBiomeKeys.register("mud_cave");
    public static final ResourceKey<Biome> FUNGUS_CAVE = EdumiaBiomeKeys.register("fungus_cave");
    public static final ResourceKey<Biome> BASALT_CAVE = EdumiaBiomeKeys.register("basalt_cave");
    public static final ResourceKey<Biome> MAGMA_CAVE = EdumiaBiomeKeys.register("magma_cave");
    public static final ResourceKey<Biome> MISTIC_CAVE = EdumiaBiomeKeys.register("mithril_cave");
    public static final ResourceKey<Biome> DRY_CAVE = EdumiaBiomeKeys.register("dry_cave");
    public static final ResourceKey<Biome> ICE_CAVE = EdumiaBiomeKeys.register("ice_cave");

    // endregion

    private static ResourceKey<Biome> register(String name) {
        return ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, name));
    }

    public static void registerModBiomes() {
        LoggerUtil.logDebugMsg("Registering ModBiomes for " + Edumia.MOD_ID);
    }
}
