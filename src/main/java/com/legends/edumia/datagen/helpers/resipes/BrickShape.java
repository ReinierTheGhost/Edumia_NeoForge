package com.legends.edumia.datagen.helpers.resipes;

import com.legends.edumia.core.blocksets.BuildingSets;
import com.legends.edumia.core.blocksets.StoneSets;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.List;

public class BrickShape {

    public record BrickRecipe(Block input, Block output) {}
    public static List<BrickRecipe> blocks = new ArrayList<>() {
        {
            add(new BrickRecipe(StoneSets.HIGH_ELVEN_ROCK.block().get(), BuildingSets.HIGH_ELVEN.block().get()));
            add(new BrickRecipe(StoneSets.DARK_HIGH_ELVEN_ROCK.block().get(), BuildingSets.DARK_HIGH_ELVEN.block().get()));
            add(new BrickRecipe(StoneSets.LIGHT_HIGH_ELVEN_ROCK.block().get(), BuildingSets.LIGHT_HIGH_ELVEN.block().get()));
            add(new BrickRecipe(StoneSets.RED_GENSAI_STONE.block().get(), BuildingSets.RED_GENSAI_BRICK.block().get()));
            add(new BrickRecipe(StoneSets.YELLOW_STONE_2.block().get(), BuildingSets.YELLOW_STONE_BRICKS.block().get()));
            add(new BrickRecipe(StoneSets.YELLOW_STONE.block().get(), BuildingSets.YELLOW_STONE_SMALL_BRICKS.block().get()));
            add(new BrickRecipe(StoneSets.LIMESTONE.block().get(), BuildingSets.LIMESTONE_BRICKS.block().get()));
            add(new BrickRecipe(StoneSets.CHALK.block().get(), BuildingSets.CHALK.block().get()));
            add(new BrickRecipe(StoneSets.CACHOLONG.block().get(), BuildingSets.CACHOLONG_BRICKS.block().get()));
            add(new BrickRecipe(StoneSets.VOLCANIC_ROCK.block().get(), BuildingSets.VOLCANIC_DEMON_BRICKS.block().get()));
            add(new BrickRecipe(Blocks.ANDESITE, BuildingSets.ANDESITE_BRICKS.block().get()));
            add(new BrickRecipe(Blocks.GRANITE, BuildingSets.GRANITE_BRICKS.block().get()));
            add(new BrickRecipe(Blocks.DRIPSTONE_BLOCK, BuildingSets.DRIPSTONE_BRICKS.block().get()));
            add(new BrickRecipe(Blocks.DIORITE, BuildingSets.DIORITE_BRICKS.block().get()));

        }
    };
}
