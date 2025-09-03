package com.legends.edumia.world.biomes.surface;

import com.legends.edumia.world.placedfeatures.BeachPlacedFeatures;
import com.legends.edumia.world.placedfeatures.biomes.VolcanicBiomePlacedFeatures;
import com.legends.edumia.world.placedfeatures.ores.VanillaBlockOrePlacedFeatures;
import com.legends.edumia.world.placedfeatures.plants.FlowerPlacedFeatures;
import com.legends.edumia.world.placedfeatures.ModPlacedFeatures;
import com.legends.edumia.world.placedfeatures.TreePlacedFeatures;
import com.legends.edumia.world.placedfeatures.biomes.BiomePlacedFeatures;
import com.legends.edumia.world.placedfeatures.biomes.FairyBiomePlacedFeatures;
import com.legends.edumia.world.placedfeatures.biomes.OrcDesertPlacedFeatures;
import com.legends.edumia.world.placedfeatures.boulders.BoulderPlacedFeatures;
import com.legends.edumia.world.placedfeatures.crystrals.CrystalPlacedFeatures;
import com.legends.edumia.world.placedfeatures.plants.MushroomPlacedFeatures;
import com.legends.edumia.world.placedfeatures.plants.ReedsPlacedFeatures;
import com.legends.edumia.world.placedfeatures.trees.*;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

public class ModBiomeFeatures {

    // region SOIL
    public static void addDisks(List<ResourceKey<PlacedFeature>> ores) {
        ores.add(MiscOverworldPlacements.DISK_SAND);
        ores.add(MiscOverworldPlacements.DISK_CLAY);
        ores.add(MiscOverworldPlacements.DISK_GRAVEL);
    }

    public static void addRiverSand(List<ResourceKey<PlacedFeature>> ores) {
    }

    public static void addRiverDisks(List<ResourceKey<PlacedFeature>> ores) {
        ores.add(MiscOverworldPlacements.DISK_CLAY);
        ores.add(MiscOverworldPlacements.DISK_GRAVEL);
    }

    public static void addTerraSand(List<ResourceKey<PlacedFeature>> ores){
        ores.add(VolcanicBiomePlacedFeatures.TERRA_SAND);
    }
    // endregion

    // region TREES

    public static void addAcaciaTrees(List<ResourceKey<PlacedFeature>> vegetation){
        vegetation.add(SubTropicalTreePlacedFeatures.ACACIA);
    }
    public static void addBurnedTrees(List<ResourceKey<PlacedFeature>> vegetation){
        vegetation.add(DeadTreePlacedFeatures.BURNED_TREE);
    }
    public static void addCommonBeechTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(TreePlacedFeatures.COMMON_BEECH_PLACED_TREE_KEY);
    }
    public static void addBeechTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(TreePlacedFeatures.BEECH_PLACED_TREE_KEY);
    }
    public static void addRareBeechTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(TreePlacedFeatures.RARE_BEECH_PLACED_TREE_KEY);
    }
    public static void addVeryRareBeechTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(TreePlacedFeatures.VERY_RARE_BEECH_PLACED_TREE_KEY);
    }
    public static void addBirchTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(TemperateTreePlacedFeatures.BIRCH_PLACED_TREE_KEY);
    }
    public static void addSparseBirchTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(TemperateTreePlacedFeatures.SPARSE_BIRCH_PLACED_TREE_KEY);
    }
    public static void addRareBirchTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(TemperateTreePlacedFeatures.RARE_BIRCH_PLACED_TREE_KEY);
    }
    public static void addVeryRareBirchTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(TemperateTreePlacedFeatures.VERY_RARE_BIRCH_PLACED_TREE_KEY);
    }
    public static void addMegaBirchTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(TemperateTreePlacedFeatures.MEGA_BIRCH_PLACED_TREE_KEY);
    }
    public static void addMegaBirchCommonTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(TemperateTreePlacedFeatures.MEGA_BIRCH_PLACED_COMMON_TREE_KEY);
    }
    public static void addMegaDarkOakTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(OakTreePlacedFeatures.MEGA_DARK_OAK_PLACED_TREE_KEY);
    }
    public static void addMegaDarkOakCommonTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(OakTreePlacedFeatures.MEGA_DARK_OAK_PLACED_COMMON_TREE_KEY);
    }
    public static void addCommonLarchTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(BorealTreePlacedFeatures.COMMON_LARCH_PLACED_TREE_KEY);
    }
    public static void addLarchTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(BorealTreePlacedFeatures.LARCH_PLACED_TREE_KEY);
    }
    public static void addSparseLarchTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(BorealTreePlacedFeatures.SPARSE_LARCH_PLACED_TREE_KEY);
    }
    public static void addBlackthornTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(TemperateTreePlacedFeatures.BLACKTHORN_PLACED_TREE_KEY);
    }
    public static void addCommonBlackthornTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(TemperateTreePlacedFeatures.COMMON_BLACKTHORN_PLACED_TREE_KEY);
    }
    public static void addRareBlackthornTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(TemperateTreePlacedFeatures.RARE_BLACKTHORN_PLACED_TREE_KEY);
    }
    public static void addWhiteAshTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(TemperateTreePlacedFeatures.WHITE_ASH_PLACED_TREE_KEY);
    }
    public static void addSmallWhiteAshTress(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(TemperateTreePlacedFeatures.SMALL_WHITE_ASH_PLACED_TREE_KEY);
    }
    public static void addMegaWhiteAshTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(TemperateTreePlacedFeatures.MEGA_WHITE_ASH_PLACED_TREE_KEY);
    }
    public static void addWhiteAshBushes(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(TemperateTreePlacedFeatures.WHITE_ASH_BUSH_PLACED_TREE_KEY);
    }
    public static void addMapleTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(TemperateTreePlacedFeatures.MAPLE_PLACED_TREE_KEY);
    }

    public static void addDarkElvenOakTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(BiomePlacedFeatures.DARK_ELVEN_OAK_TREES_KEY);
    }
    public static void addSmallRedOakTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(OakTreePlacedFeatures.SMALL_RED_OAK_PLACED_TREE_KEY);
    }
    public static void addRedOakTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(OakTreePlacedFeatures.RED_OAK_PLACED_TREE_KEY);
    }
    public static void addSparseRedOakTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(OakTreePlacedFeatures.SPARSE_RED_OAK_PLACED_TREE_KEY);
    }
    public static void addMegaRedOakTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(OakTreePlacedFeatures.MEGA_RED_OAK_PLACED_TREE_KEY);
    }

    public static void addSmallGreenOakTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(OakTreePlacedFeatures.SMALL_GREEN_OAK_PLACED_TREE_KEY);
    }
    public static void addGreenOakTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(OakTreePlacedFeatures.GREEN_OAK_PLACED_TREE_KEY);
    }
    public static void addSparseGreenOakTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(OakTreePlacedFeatures.SPARSE_GREEN_OAK_PLACED_TREE_KEY);
    }
    public static void addMegaGreenOakTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(OakTreePlacedFeatures.MEGA_GREEN_OAK_PLACED_TREE_KEY);
    }

    public static void addSmallBlackOakTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(OakTreePlacedFeatures.SMALL_BLACK_OAK_PLACED_TREE_KEY);
    }
    public static void addBlackOakTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(OakTreePlacedFeatures.BLACK_OAK_PLACED_TREE_KEY);
    }
    public static void addSparseBlackOakTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(OakTreePlacedFeatures.SPARSE_BLACK_OAK_PLACED_TREE_KEY);
    }
    public static void addMegaBlackOakTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(OakTreePlacedFeatures.MEGA_BLACK_OAK_PLACED_TREE_KEY);
    }

    public static void addCommonOakBush(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(OakTreePlacedFeatures.OAK_BUSH_COMMON_PLACED_TREE_KEY);
    }
    public static void addOakBushes(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(OakTreePlacedFeatures.OAK_BUSH_PLACED_TREE_KEY);
    }

    public static void addMyrwoodTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(FairyBiomePlacedFeatures.FAIRY_FOREST_TREES_LAYER_1);
        vegetation.add(FairyBiomePlacedFeatures.FAIRY_FOREST_TREES_LAYER_2);
        vegetation.add(FairyBiomePlacedFeatures.FAIRY_FOREST_TREES_LAYER_3);
        vegetation.add(FairyBiomePlacedFeatures.FAIRY_FOREST_TREES_LAYER_4);
        vegetation.add(FairyBiomePlacedFeatures.FAIRY_FOREST_TREES_LAYER_5);
    }

    public static void addCommonOakTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(OakTreePlacedFeatures.COMMON_OAK_PLACED_TREE_KEY);
    }
    public static void addOakTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(OakTreePlacedFeatures.OAK_PLACED_TREE_KEY);
    }
    public static void addRareOakTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(OakTreePlacedFeatures.RARE_OAK_PLACED_TREE_KEY);
    }
    public static void addOakVinesTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(OakTreePlacedFeatures.OAK_VINES_PLACED_TREE_KEY);
    }
    public static void addMegaOakTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(OakTreePlacedFeatures.MEGA_OAK_PLACED_TREE_KEY);
    }
    public static void addMegaOakCommonTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(OakTreePlacedFeatures.MEGA_OAK_COMMON_PLACED_TREE_KEY);
    }
    public static void addRareMegaOakTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(OakTreePlacedFeatures.RARE_MEGA_OAK_PLACED_TREE_KEY);
    }
    public static void addVeryRareMegaOakTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(OakTreePlacedFeatures.VERY_RARE_MEGA_OAK_PLACED_TREE_KEY);
    }

    public static void addPalmTrees(List<ResourceKey<PlacedFeature>> vegetation) {

    }

    public static void addAbundantPineTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(BorealTreePlacedFeatures.ABUNDANT_PINE_PLACED_TREE_KEY);
    }
    public static void addCommonPineTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(BorealTreePlacedFeatures.COMMON_PINE_PLACED_TREE_KEY);
    }
    public static void addPineTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(BorealTreePlacedFeatures.PINE_PLACED_TREE_KEY);
    }
    public static void addDeadPineTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(BorealTreePlacedFeatures.DEAD_PINE_PLACED_TREE_KEY);
    }

    public static void addAbundantSpruceTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(BorealTreePlacedFeatures.FOOTHILLS_SPRUCE_PLACED_TREE_KEY);
    }
    public static void addCommonSpruceTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(BorealTreePlacedFeatures.COMMON_SPRUCE_PLACED_TREE_KEY);
    }
    public static void addSpruceTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(BorealTreePlacedFeatures.SPRUCE_PLACED_TREE_KEY);
    }
    public static void addSparsePineTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(BorealTreePlacedFeatures.SPARSE_PINE_PLACED_TREE_KEY);
    }
    public static void addScarceSpruceTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(BorealTreePlacedFeatures.SCARCE_SPRUCE_PLACED_TREE_KEY);
    }
    public static void addRareSpruceTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(BorealTreePlacedFeatures.RARE_SPRUCE_PLACED_TREE_KEY);
    }
    public static void addCommonSpruceBushes(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(BorealTreePlacedFeatures.COMMON_SPRUCE_BUSH_PLACED_TREE_KEY);
    }
    public static void addSpruceBushes(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(BorealTreePlacedFeatures.SPRUCE_BUSH_PLACED_TREE_KEY);
    }
    public static void addVeryRareSpruceTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(BorealTreePlacedFeatures.VERY_RARE_SPRUCE_PLACED_TREE_KEY);
    }
    public static void addWillowTrees(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(TemperateTreePlacedFeatures.WILLOW_PLACED_TREE_KEY);
    }

    public static void addRareCypressTrees(List<ResourceKey<PlacedFeature>> vegetation){
        vegetation.add(TemperateTreePlacedFeatures.RARE_CYPRESS_KEY);
    }
    // endregion TREES

    // region PLANTS
    public static void addReeds(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(ReedsPlacedFeatures.REEDS);
    }
    public static void addPapyrus(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(ReedsPlacedFeatures.PAPYRUS);
    }

    // endregion

    public static void addElvenCrystal(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(CrystalPlacedFeatures.ELVEN_CRYSTAL);
    }

    // region WATER
    public static void addWaterDelta(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(ModPlacedFeatures.WATER_DELTA);
    }

    public static void addAbundantWaterDelta(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(ModPlacedFeatures.ABUNDANT_WATER_DELTA);
    }

    public static void addLake(List<ResourceKey<PlacedFeature>> vegetation){
        vegetation.add(VolcanicBiomePlacedFeatures.LAKE);
    }
    // endregion


    // region BOULDERS
    public static void addAndesiteBoulder(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(BoulderPlacedFeatures.ANDESITE_BOULDER);
        vegetation.add(BoulderPlacedFeatures.BIG_ANDESITE_BOULDER);
    }
    public static void addCalciteBoulder(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(BoulderPlacedFeatures.CALCITE_BOULDER);
        vegetation.add(BoulderPlacedFeatures.BIG_CALCITE_BOULDER);
    }
    public static void addDioriteBoulder(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(BoulderPlacedFeatures.DIORITE_BOULDER);
        vegetation.add(BoulderPlacedFeatures.BIG_DIORITE_BOULDER);
    }
    public static void addGraniteBoulder(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(BoulderPlacedFeatures.GRANITE_BOULDER);
        vegetation.add(BoulderPlacedFeatures.BIG_GRANITE_BOULDER);
    }
    public static void addLimestoneBoulder(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(BoulderPlacedFeatures.LIMESTONE_BOULDER);
        vegetation.add(BoulderPlacedFeatures.BIG_LIMESTONE_BOULDER);
    }
    public static void addMirkwoodRoots(List<ResourceKey<PlacedFeature>> vegetation) {
    }
    public static void addSandStoneBoulders(List<ResourceKey<PlacedFeature>> vegetation) {
        addSmallSandStoneBoulders(vegetation);
        addBigSandStoneBoulder(vegetation);
    }

    public static void addBigSandStoneBoulder(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(BoulderPlacedFeatures.BIG_SANDSTONE_BOULDER);
    }

    public static void addSmallSandStoneBoulders(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(BoulderPlacedFeatures.SANDSTONE_BOULDER);
    }
    public static void addStoneBoulder(List<ResourceKey<PlacedFeature>> vegetation) {
        addBigStoneBoulder(vegetation);
        addSmallStoneBoulder(vegetation);
    }

    public static void addBigStoneBoulder(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(BoulderPlacedFeatures.BIG_STONE_BOULDER);
    }

    public static void addSmallStoneBoulder(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(BoulderPlacedFeatures.STONE_BOULDER);
    }
    public static void addMossyBoulder(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(BoulderPlacedFeatures.MOSSY_BOULDER);
        vegetation.add(BoulderPlacedFeatures.BIG_MOSSY_BOULDER);
    }
    // endregion

    // region FOLIAGE
    public static void addHeather(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(FlowerPlacedFeatures.HEATHER_BUSH_FLOWER_KEY);
    }
    public static void addLavender(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(FlowerPlacedFeatures.LAVENDER_FLOWER_KEY);
    }


    public static void addHibiscus(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(FlowerPlacedFeatures.HIBISCUS_FLOWER_KEY);
    }

    public static void addGensaiOrchid(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(FlowerPlacedFeatures.GENSAI_ORCHID_FLOWER_KEY);
    }
    public static void addReedsFoliage(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(ReedsPlacedFeatures.REEDS);
    }

    public static void addGrass(List<ResourceKey<PlacedFeature>> vegetation){
        vegetation.add(VegetationPlacements.GRASS_BONEMEAL);
        vegetation.add(VegetationPlacements.PATCH_GRASS_PLAIN);
    }

    public static void addJungleGrass(List<ResourceKey<PlacedFeature>> vegetation){
        vegetation.add(VegetationPlacements.GRASS_BONEMEAL);
        vegetation.add(VegetationPlacements.PATCH_GRASS_JUNGLE);
    }
    // endregion

    // region MUSHROOMS
    public static void addParasolMushroom(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(MushroomPlacedFeatures.PARASOL_MUSHROOM_1_KEY);
        vegetation.add(MushroomPlacedFeatures.PARASOL_MUSHROOM_2_KEY);
        vegetation.add(MushroomPlacedFeatures.PARASOL_MUSHROOM_TALL_KEY);
    }
    // endregion

    // region WILD CROPS

    // endregion

    // region ORES

    public static void addAndesiteOre(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(VanillaBlockOrePlacedFeatures.ANDESITE_ORE);
    }
    public static void addDioriteOre(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(VanillaBlockOrePlacedFeatures.DIORITE_ORE);
    }
    public static void addGraniteOre(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(VanillaBlockOrePlacedFeatures.GRANITE_ORE);
    }

    public static void addBasaltOre(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(VanillaBlockOrePlacedFeatures.BASALT_ORE);
    }

    public static void addSmoothBasaltOre(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(VanillaBlockOrePlacedFeatures.SMOOTH_BASALT_ORE);
    }

    public static void addCalciteOre(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(VanillaBlockOrePlacedFeatures.CALCITE_ORE);
    }

    public static void addRareCalciteOre(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(VanillaBlockOrePlacedFeatures.RARE_CALCITE_ORE);
    }

    public static void addCoarseDirtOre(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(VanillaBlockOrePlacedFeatures.COARSE_DIRT_ORE);
    }
    public static void addDirtToGrassOre(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(VanillaBlockOrePlacedFeatures.DIRT_TO_GRASS_ORE);
    }
    public static void addDripstoneOre(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(VanillaBlockOrePlacedFeatures.DRIPSTONE_ORE);
    }
    public static void addAbandonedMud(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(VanillaBlockOrePlacedFeatures.ABUNDANT_MUD_ORE);
    }

    public static void addPackedMudOre(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(VanillaBlockOrePlacedFeatures.PACKED_MUD_ORE);
    }


    public static void addGravelOre(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(VanillaBlockOrePlacedFeatures.GRAVEL_ORE);
    }
    public static void addStoneGrassOre(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(VanillaBlockOrePlacedFeatures.GRASS_TO_STONE_ORE);
    }
    public static void addMudOre(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(VanillaBlockOrePlacedFeatures.MUD_ORE);
    }
    public static void addPodzolOre(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(VanillaBlockOrePlacedFeatures.PODZOL_ORE);
    }
    public static void addPowderSnowOre(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(VanillaBlockOrePlacedFeatures.POWDER_SNOW_ORE);
    }
    public static void addTuffOre(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(VanillaBlockOrePlacedFeatures.TUFF_ORE);
    }

    public static void addSandPath(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(OrcDesertPlacedFeatures.SAND_PATH);
    }
    // endregion

    // region MISC
    public static void addLavaMagmaLake(BiomeGenerationSettings.Builder generationSettings) {
    }

    public static void addWhiteSandLayers(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(BeachPlacedFeatures.WHITE_SAND_LAYER_FIRST);
        vegetation.add(BeachPlacedFeatures.WHITE_SAND_LAYER_SECOND);
        vegetation.add(BeachPlacedFeatures.WHITE_SAND_LAYER_THIRD);
    }

    public static void addSnowLayers(List<ResourceKey<PlacedFeature>> vegetation) {
        vegetation.add(ModPlacedFeatures.SNOW_LAYER_FIRST);
        vegetation.add(ModPlacedFeatures.SNOW_LAYER_SECOND);
        vegetation.add(ModPlacedFeatures.SNOW_LAYER_THIRD);
    }

    public static void addAcidDisks(List<ResourceKey<PlacedFeature>> vegetation){
        vegetation.add(VolcanicBiomePlacedFeatures.YELLOW_ACID);
        vegetation.add(VolcanicBiomePlacedFeatures.ORANGE_ACID);
    }
    // endregion


}
