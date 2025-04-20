package com.legends.edumia.datagen.helpers.resipes;

import com.legends.edumia.blocks.blocksets.BuildingSets;
import com.legends.edumia.blocks.blocksets.StoneSets;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class Cooking {
    public record CookingRecipe(Block input, Block output) {}
    public static List<CookingRecipe> blocks = new ArrayList<>() {
        {
            add(new CookingRecipe(StoneSets.HIGH_ELVEN_COBBLESTONE.block().get(), StoneSets.HIGH_ELVEN_ROCK.block().get()));
            add(new CookingRecipe(StoneSets.DARK_HIGH_ELVEN_COBBLESTONE.block().get(), StoneSets.DARK_HIGH_ELVEN_ROCK.block().get()));
            add(new CookingRecipe(StoneSets.LIGHT_HIGH_ELVEN_COBBLESTONE.block().get(), StoneSets.LIGHT_HIGH_ELVEN_ROCK.block().get()));
        }
    };
}
