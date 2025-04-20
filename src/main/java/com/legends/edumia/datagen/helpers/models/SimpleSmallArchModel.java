package com.legends.edumia.datagen.helpers.models;

import com.legends.edumia.blocks.ArchSmall;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class SimpleSmallArchModel {

    public record Arch(Block texture, ArchSmall arch) {}

    public static List<Arch> blocks = new ArrayList<>() {
        {
        }
    };
}
