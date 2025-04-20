package com.legends.edumia.datagen.helpers.models;

import net.minecraft.world.level.block.RotatedPillarBlock;

import java.util.ArrayList;
import java.util.List;

public class SimplePillarModel {
    public record Pillar(RotatedPillarBlock base) {}

    public static List<Pillar> blocks = new ArrayList<>() {
        {

        }
    };
}
