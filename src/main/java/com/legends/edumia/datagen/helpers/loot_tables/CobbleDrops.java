package com.legends.edumia.datagen.helpers.loot_tables;

import com.legends.edumia.core.blocksets.StoneSets;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class CobbleDrops {
    public record CobbleDrop(Block block, Block drop) {}
    public static List<CobbleDrop> blocks = new ArrayList<>() {
        {
            add(new CobbleDrop(StoneSets.DUSKEN_AELORIAN_ROCK.block().get(), StoneSets.DUSKEN_AELORIAN_COBBLESTONE.block().get()));
            add(new CobbleDrop(StoneSets.PALE_AELORIAN_ROCK.block().get(), StoneSets.PALE_AELORIAN_COBBLESTONE.block().get()));
            add(new CobbleDrop(StoneSets.AELORIAN_ROCK.block().get(), StoneSets.AELORIAN_COBBLESTONE.block().get()));
        }
    };
}
