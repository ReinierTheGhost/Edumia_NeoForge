package com.legends.edumia.datagen.helpers.models;

import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class SimpleCrystalBlockModel {

    public record Crystals(Block crystal) {}

    public static List<Crystals> blocks = new ArrayList<>() {
        {
//            add(new Crystals(BlockLoader.HIGH_ELVEN_CRYSTAL));
        }
    };
}
