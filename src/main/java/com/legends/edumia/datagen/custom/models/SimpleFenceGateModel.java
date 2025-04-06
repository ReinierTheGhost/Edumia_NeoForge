package com.legends.edumia.datagen.custom.models;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceGateBlock;

import java.util.ArrayList;
import java.util.List;

public class SimpleFenceGateModel {
    public record FenceGate(Block block, FenceGateBlock fenceGate) {}
    public static List<FenceGate> blocks = new ArrayList<>() {
        {
        }
    };
}
