package com.legends.edumia.world.trees;

import com.legends.edumia.Edumia;
import com.legends.edumia.world.congiguredfeatures.TreeConfiguredFeatures;
import com.legends.edumia.world.congiguredfeatures.trees.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ModTreeGrowers {


    public static final TreeGrower APPLE = new TreeGrower(Edumia.MOD_ID + ":apple",
            Optional.empty(), Optional.of(TreeConfiguredFeatures.APPLE_KEY), Optional.empty());

    public static final TreeGrower ASPEN = new TreeGrower(Edumia.MOD_ID + ":aspen",
            Optional.empty(), Optional.of(TemperateTreeConfiguredFeatures.ASPEN_KEY), Optional.empty());

    public static final TreeGrower BANANA = new TreeGrower(Edumia.MOD_ID + ":banana",
            Optional.empty(), Optional.of(TropicalTreeConfiguredFeatures.BANANA), Optional.empty());

    public static final TreeGrower BLACK_OAK = new TreeGrower(Edumia.MOD_ID + ":black_oak",
            Optional.empty(), Optional.of(OakTreeConfiguredFeatures.BLACK_OAK_KEY), Optional.empty());

    public static final TreeGrower BLACKTHORN = new TreeGrower(Edumia.MOD_ID + ":blackthorn",
            Optional.empty(), Optional.of(TreeConfiguredFeatures.BLACKTHORN_KEY), Optional.empty());

    public static final TreeGrower CYPRESS = new TreeGrower(Edumia.MOD_ID + ":cypress",
            Optional.empty(), Optional.of(TemperateTreeConfiguredFeatures.CYPRESS_KEY), Optional.empty());

    public static final TreeGrower DRAGON_BLOOD = new TreeGrower(Edumia.MOD_ID + ":dragon_blood",
            Optional.empty(), Optional.of(TreeConfiguredFeatures.DRAGON_BLOOD_SMALL_KEY), Optional.empty());

    public static final TreeGrower FIR = new TreeGrower(Edumia.MOD_ID + ":fir",
            Optional.empty(), Optional.of(BorealTreeConfiguredFeatures.FIR_KEY), Optional.empty());

    public static final TreeGrower GHOST_GUM = new TreeGrower(Edumia.MOD_ID + ":ghost_gum",
            Optional.empty(), Optional.of(TreeConfiguredFeatures.GHOST_GUM_KEY), Optional.of(TreeConfiguredFeatures.GHOST_GUM_BEES_KEY));

    public static final TreeGrower GREEN_OAK = new TreeGrower(Edumia.MOD_ID + ":green_oak",
            Optional.empty(), Optional.of(OakTreeConfiguredFeatures.GREEN_OAK_KEY), Optional.empty());

    public static final TreeGrower HOLLY = new TreeGrower(Edumia.MOD_ID + ":holly",
            Optional.empty(), Optional.of(TemperateTreeConfiguredFeatures.HOLLY_TREE_KEY), Optional.of(TemperateTreeConfiguredFeatures.HOLLY_BEES_TREE_KEY));

    public static final TreeGrower MAHOGANY = new TreeGrower(Edumia.MOD_ID + ":mahogany",
            Optional.empty(), Optional.of(MahoganyConfiguredFeatures.MAHOGANY), Optional.empty());

    public static final TreeGrower MANGO = new TreeGrower(Edumia.MOD_ID + ":mango", 50f,
            Optional.empty(), Optional.empty(), Optional.of(TropicalTreeConfiguredFeatures.MANGO),
            Optional.of(TropicalTreeConfiguredFeatures.MANGO_TWO), Optional.empty(), Optional.empty());

    public static final TreeGrower PALM = new TreeGrower(Edumia.MOD_ID + ":palm",
            Optional.empty(), Optional.of(SaplingConfiguredFeatures.PALM_TREES), Optional.empty());

    public static final TreeGrower PINE = new TreeGrower(Edumia.MOD_ID + ":pine",
            Optional.empty(), Optional.of(TreeConfiguredFeatures.PINE_KEY), Optional.empty());

    public static final TreeGrower RED_OAK = new TreeGrower(Edumia.MOD_ID + ":red_oak",
            Optional.empty(), Optional.of(OakTreeConfiguredFeatures.RED_OAK_KEY), Optional.empty());

    public static final TreeGrower REDWOOD = new TreeGrower(Edumia.MOD_ID + ":redwood",
            Optional.empty(), Optional.of(TreeConfiguredFeatures.REDWOOD_KEY), Optional.empty());

    public static final TreeGrower SILVER_SPRUCE = new TreeGrower(Edumia.MOD_ID + ":silver_spruce",
            Optional.empty(), Optional.of(BorealTreeConfiguredFeatures.SILVER_SPRUCE_TREE_KEY), Optional.empty());

    public static final TreeGrower WHITE_ASH = new TreeGrower(Edumia.MOD_ID + ":white_ash",
            Optional.empty(), Optional.of(TreeConfiguredFeatures.WHITE_ASH_KEY), Optional.empty());

    public static final TreeGrower WILLOW = new TreeGrower(Edumia.MOD_ID + ":willow",
            Optional.empty(), Optional.of(TemperateTreeConfiguredFeatures.WILLOW_TREE_KEY), Optional.empty());

    public static final TreeGrower ALMOND = new TreeGrower(Edumia.MOD_ID + ":almond",
            Optional.empty(), Optional.of(FruitTreeConfiguredFeatures.ALMOND_KEY), Optional.empty());

    public static final TreeGrower TEST = new TreeGrower(Edumia.MOD_ID + ":test",
            Optional.empty(), Optional.of(MahoganyConfiguredFeatures.MAHOGANY_NBT), Optional.empty());


}
