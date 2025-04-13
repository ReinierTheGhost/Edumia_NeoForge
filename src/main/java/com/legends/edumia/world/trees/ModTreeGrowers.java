package com.legends.edumia.world.trees;

import com.legends.edumia.Edumia;
import com.legends.edumia.world.congiguredfeatures.TreeConfiguredFeatures;
import com.legends.edumia.world.congiguredfeatures.trees.BeechTreeConfiguredFeatures;
import com.legends.edumia.world.congiguredfeatures.trees.OakTreeConfiguredFeatures;
import com.legends.edumia.world.congiguredfeatures.trees.TemperateTreeConfiguredFeatures;
import com.legends.edumia.world.congiguredfeatures.trees.TropicalTreeConfiguredFeatures;
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


}
