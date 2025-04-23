package com.legends.edumia.datagen.helpers.loot_tables;

import com.legends.edumia.core.blocksets.StoneSets;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class CobbleDrops {
    public record CobbleDrop(Block block, Block drop) {}
    public static List<CobbleDrop> blocks = new ArrayList<>() {
        {
            add(new CobbleDrop(StoneSets.DARK_HIGH_ELVEN_ROCK.block().get(), StoneSets.DARK_HIGH_ELVEN_COBBLESTONE.block().get()));
            add(new CobbleDrop(StoneSets.LIGHT_HIGH_ELVEN_ROCK.block().get(), StoneSets.LIGHT_HIGH_ELVEN_COBBLESTONE.block().get()));
            add(new CobbleDrop(StoneSets.HIGH_ELVEN_ROCK.block().get(), StoneSets.HIGH_ELVEN_COBBLESTONE.block().get()));
        }
    };
}
