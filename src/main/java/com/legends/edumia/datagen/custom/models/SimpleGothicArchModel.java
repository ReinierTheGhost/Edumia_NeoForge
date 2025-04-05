package com.legends.edumia.datagen.custom.models;

import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class SimpleGothicArchModel {

    public record Arch(Block texture, Block arch) {}

    public static List<Arch> blocks = new ArrayList<>() {
        {
            //add(new Arch(BlockLoader.GREEN_BASALT, BlockLoader.GREEN_BASALT_GOTHIC_ARCH));
        }
    };
}
