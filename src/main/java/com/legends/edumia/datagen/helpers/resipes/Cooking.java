package com.legends.edumia.datagen.helpers.resipes;

import com.legends.edumia.core.blocksets.StoneSets;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class Cooking {
    public record CookingRecipe(Block input, Block output) {}
    public static List<CookingRecipe> blocks = new ArrayList<>() {
        {
            add(new CookingRecipe(StoneSets.AELORIAN_COBBLESTONE.block().get(), StoneSets.AELORIAN_ROCK.block().get()));
            add(new CookingRecipe(StoneSets.DUSKEN_AELORIAN_COBBLESTONE.block().get(), StoneSets.DUSKEN_AELORIAN_ROCK.block().get()));
            add(new CookingRecipe(StoneSets.PALE_AELORIAN_COBBLESTONE.block().get(), StoneSets.PALE_AELORIAN_ROCK.block().get()));
        }
    };
}
