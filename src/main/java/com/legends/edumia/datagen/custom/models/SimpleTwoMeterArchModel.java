package com.legends.edumia.datagen.custom.models;

import com.legends.edumia.blocks.ArchTwoMeter;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class SimpleTwoMeterArchModel {
    public record Arch(Block texture, ArchTwoMeter arch) {}

    public static List<Arch> blocks = new ArrayList<>() {
        {
        }
    };
}
