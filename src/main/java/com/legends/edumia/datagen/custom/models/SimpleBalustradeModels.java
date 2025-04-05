package com.legends.edumia.datagen.custom.models;


import com.legends.edumia.blocks.Balustrade;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class SimpleBalustradeModels {
    public record Balustrades(Block texture, Balustrade balustrade) {}

    public static List<Balustrades> blocks = new ArrayList<>() {
        {
        }
    };
}
