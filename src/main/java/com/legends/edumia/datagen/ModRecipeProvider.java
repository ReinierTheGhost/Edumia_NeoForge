package com.legends.edumia.datagen;

import com.legends.edumia.Edumia;
import com.legends.edumia.blocks.blocksets.BuildingSets;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        super.buildRecipes(recipeOutput);
        for (BuildingSets.BuildSet set : BuildingSets.buildSets){
            slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, set.slab(), set.block());
            stairBuilder(set.stair(), Ingredient.of(set.block()))
                    .unlockedBy("has_" + name(set.block().get()), has(set.block())).save(recipeOutput);
            wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, set.wall(), set.block());

            if (set.pillar() != null && set.pillarSlab() != null){
                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, set.pillar(), 3)
                        .pattern("B")
                        .pattern("B")
                        .pattern("B")
                        .define('B', set.block())
                        .unlockedBy("has_" + name(set.block().get()), has(set.block())).save(recipeOutput);
                slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, set.pillarSlab(), set.pillar());
            }

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, set.twoMeterArch(), 4)
                    .pattern("BBB")
                    .pattern(" BB")
                    .pattern("  B")
                    .define('B', set.block())
                    .unlockedBy("has_" + name(set.block().get()), has(set.block())).save(recipeOutput);

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, set.smallArch(), 4)
                    .pattern("BBB")
                    .pattern("B B")
                    .define('B', set.block())
                    .unlockedBy("has_" + name(set.block().get()), has(set.block())).save(recipeOutput);

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, set.arrowSlit(), 4)
                    .pattern("B B")
                    .pattern("B B")
                    .pattern("B B")
                    .define('B', set.block())
                    .unlockedBy("has_" + name(set.block().get()), has(set.block())).save(recipeOutput);

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, set.balustrade())
                    .pattern("BBB")
                    .pattern(" B ")
                    .pattern("BBB")
                    .define('B', set.block())
                    .unlockedBy("has_" + name(set.block().get()), has(set.block())).save(recipeOutput);

            if (set.chiseled() != null){
                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, set.chiseled())
                        .pattern("BB")
                        .pattern("BB")
                        .define('B', set.block())
                        .unlockedBy("has_" + name(set.block().get()), has(set.block())).save(recipeOutput);
            }


        }
    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer,
                                                                       AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory,
                                                                       ItemLike pResult, float pExperience, int pCookingTime, String pGroup,
                                                                       String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer,
                            factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, Edumia.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }

    public ResourceLocation key(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }

    public String name(Block block) {
        return key(block).getPath();
    }
}
