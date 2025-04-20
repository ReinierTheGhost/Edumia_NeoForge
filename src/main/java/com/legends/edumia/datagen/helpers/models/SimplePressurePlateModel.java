package com.legends.edumia.datagen.helpers.models;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PressurePlateBlock;

import java.util.ArrayList;
import java.util.List;

public class SimplePressurePlateModel {
    public record PressurePlate(Block block, PressurePlateBlock pressurePlate) {}
    public static List<PressurePlate> blocks = new ArrayList<>() {
        {
        }
    };
}
