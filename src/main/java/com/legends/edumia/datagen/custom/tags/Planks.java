package com.legends.edumia.datagen.custom.tags;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class Planks {
    public static List<Block> planks = new ArrayList<>() {
        {

        }
    };

    public static List<Item> getItemPlanks() {
        ArrayList<Item> newList = new ArrayList<>();
        for (Block block : planks) {
            newList.add(block.asItem());
        }
        return newList;
    }
}
