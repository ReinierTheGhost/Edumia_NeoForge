package com.legends.edumia.datagen;

import com.legends.edumia.Edumia;
import com.legends.edumia.core.blocksets.*;
import com.legends.edumia.datagen.helpers.resipes.BrickShape;
import com.legends.edumia.datagen.helpers.resipes.Cooking;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
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

    private final String modid;

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, String modid) {
        super(output, registries);
        this.modid = modid;
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
                pillar(recipeOutput, set.pillar(), set.block());
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

        for (WoodBlockSets.SimpleBlockSet set : WoodBlockSets.sets){
            slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, set.planksSlab(), set.planks());
            slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, set.woodSlab(), set.wood());
            slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, set.strippedWoodSlab(), set.strippedWood());

            stairBuilder(set.planksStairs(), Ingredient.of(set.planks()))
                    .unlockedBy("has_" + name(set.planks().get()), has(set.planks())).save(recipeOutput);
            stairBuilder(set.woodStairs(), Ingredient.of(set.wood()))
                    .unlockedBy("has_" + name(set.wood().get()), has(set.wood())).save(recipeOutput);
            stairBuilder(set.strippedWoodStairs(), Ingredient.of(set.strippedWood()))
                    .unlockedBy("has_" + name(set.strippedWood().get()), has(set.strippedWood())).save(recipeOutput);

            wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, set.woodWall(), set.wood());
            wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, set.strippedWoodWall(), set.strippedWood());

            fence(recipeOutput, set.planksFence(), set.planks());
            fence(recipeOutput, set.woodFence(), set.wood());
            fence(recipeOutput, set.strippedWoodFence(), set.strippedWood());

            fenceGate(recipeOutput, set.planksGate(), set.planks());


            pillar(recipeOutput, set.beam(), set.log());
            planksFromLogs(recipeOutput, set.planks(), set.logTag(), 4);
            woodFromLogs(recipeOutput, set.wood(), set.log());
            woodFromLogs(recipeOutput, set.strippedWood(), set.strippedLog());

            buttonBuilder(set.button(), Ingredient.of(set.planks()))
                    .unlockedBy("has_" + name(set.planks().get()), has(set.planks())).save(recipeOutput);
            pressurePlate(recipeOutput, set.pressurePlate(), set.planks());
            doorBuilder(set.door(), Ingredient.of(set.planks()))
                    .unlockedBy("has_" + name(set.planks().get()), has(set.planks())).save(recipeOutput);
            trapdoorBuilder(set.trapdoor(), Ingredient.of(set.planks()))
                    .unlockedBy("has_" + name(set.planks().get()), has(set.planks())).save(recipeOutput);
        }

        for (WoodBlockSets.SimpleVanillaBlocks set : WoodBlockSets.beams){
            pillar(recipeOutput, set.beam(), set.texture());
            stairBuilder(set.woodStairs(), Ingredient.of(set.wood()))
                    .unlockedBy("has_" + name(set.wood()), has(set.wood()))
                    .save(recipeOutput);
            wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, set.woodWall(), set.wood());
            fence(recipeOutput, set.woodFence(), set.wood());
            slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, set.woodSlab(), set.wood());
        }

        for (BrickShape.BrickRecipe set : BrickShape.blocks){
            brickShape(recipeOutput, RecipeCategory.BUILDING_BLOCKS, set.output(), set.input());
        }

        for (Cooking.CookingRecipe set : Cooking.blocks){
            smeltingSmooth(recipeOutput, set.output(), set.input());
        }

        for (ClayTilingSets.ClayTilingSet set : ClayTilingSets.sets){

            if (set != ClayTilingSets.CLAY_TILING){
                coloredBlockFromDye(recipeOutput, RecipeCategory.BUILDING_BLOCKS, set.block().get(),
                        ClayTilingSets.CLAY_TILING.block().get(), set.dye());
            }


            clayTiling(recipeOutput, RecipeCategory.BUILDING_BLOCKS, set.block().get(), set.terracotta());
            stairBuilder(set.stairs(), Ingredient.of(set.block()))
                    .unlockedBy("has_" + name(set.block().get()), has(set.block())).save(recipeOutput);
            slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, set.slab().get(), set.block().get());
            corner(recipeOutput, RecipeCategory.BUILDING_BLOCKS, set.corner().get(), set.block().get());

        }

        for (GlassSets.GlassSet set : GlassSets.glassSets){
            if (set != GlassSets.FINE_GLASS){
                coloredBlockFromDye(recipeOutput, RecipeCategory.BUILDING_BLOCKS, set.block().get(),
                        GlassSets.FINE_GLASS.block().get(), set.dye());
            }
            brickShape(recipeOutput, RecipeCategory.MISC, set.block(), set.glass());
            fineGlassPane(recipeOutput, set.pane().get(), set.block().get());
        }

        for (FlowerBlockSets.FlowerSet set : FlowerBlockSets.flowerSets){
            if (set != FlowerBlockSets.YELLOW_IRIS && set != FlowerBlockSets.HIBISCUS &&
                    set != FlowerBlockSets.FLAME_OF_THE_SOUTH && set != FlowerBlockSets.PARASOL_MUSHROOM_TALL){
                dyeFromFlower(recipeOutput, set.dye(), set.flower().get(), 1);
            }else {
                dyeFromFlower(recipeOutput, set.dye(), set.tallFlower().get(), 2);
            }

        }


    }

    protected static void fineGlassPane(RecipeOutput recipeOutput, ItemLike result, ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result, 16)
                .define('#', ingredient)
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_glass", has(ingredient))
                .save(recipeOutput);
    }

    protected static void smeltingSmooth(RecipeOutput recipeOutput, ItemLike result, ItemLike ingredient) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), RecipeCategory.BUILDING_BLOCKS, result, 0f, 200)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput);
    }
    protected static void fence(RecipeOutput recipeOutput, ItemLike fence, ItemLike material) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, fence, 3)
                .define('W', material)
                .define('#', Items.STICK)
                .pattern("W#W")
                .pattern("W#W")
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput);
    }

    protected static void fenceGate(RecipeOutput recipeOutput, ItemLike fence, ItemLike material) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, fence, 3)
                .define('W', material)
                .define('#', Items.STICK)
                .pattern("#W#")
                .pattern("#W#")
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput);
    }

    protected static void fenceGateNoStick(RecipeOutput recipeOutput, ItemLike fence, ItemLike material, ItemLike stick) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, fence, 3)
                .define('W', material)
                .define('#', stick)
                .pattern("#W#")
                .pattern("#W#")
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput);
    }

    protected static void fenceNoStick(RecipeOutput recipeOutput, ItemLike fence, ItemLike material, ItemLike stick) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, fence, 3)
                .define('W', material)
                .define('#', stick)
                .pattern("W#W")
                .pattern("W#W")
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput);
    }

    protected static void pillar(RecipeOutput recipeOutput, ItemLike output, ItemLike input) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, output, 3)
                .pattern("B")
                .pattern("B")
                .pattern("B")
                .define('B', input)
                .unlockedBy(getHasName(input), has(input))
                .save(recipeOutput);
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

    protected static void brickShape(RecipeOutput recipeOutput, RecipeCategory category, ItemLike packed, ItemLike unpacked) {
        ShapedRecipeBuilder.shaped(category, packed, 4)
                .define('#', unpacked)
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(unpacked), has(unpacked))
                .save(recipeOutput);
    }

    protected static void clayTiling(RecipeOutput recipeOutput, RecipeCategory category, ItemLike result, ItemLike ingredient){
        ShapedRecipeBuilder.shaped(category, result, 4)
                .define('#', ingredient)
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput);
    }

    protected void coloredBlockFromDye(RecipeOutput recipeOutput, RecipeCategory category, Block result, ItemLike ingredient, ItemLike dye){
        ShapedRecipeBuilder.shaped(category, result, 8)
                .define('#', ingredient)
                .define('D', dye)
                .pattern("###")
                .pattern("#D#")
                .pattern("###")
                .unlockedBy(getHasName(dye), has(dye))
                .save(recipeOutput, modLoc(name(result)) + "_from_dye");
    }

    protected static void corner(RecipeOutput recipeOutput, RecipeCategory category, ItemLike result, ItemLike ingredient){
        ShapedRecipeBuilder.shaped(category, result, 5)
                .define('#', ingredient)
                .pattern("###")
                .pattern("  #")
                .pattern("  #")
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(recipeOutput);
    }

    protected void dyeFromFlower(RecipeOutput recipeOutput, Item result, Block flower, int amount){
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, amount)
                .requires(flower)
                .unlockedBy(getHasName(flower), has(flower))
                .save(recipeOutput, modLoc(name(result)) + "_from_" + name(flower));
    }

    public ResourceLocation modLoc(String name) {
        return ResourceLocation.fromNamespaceAndPath(modid, name);
    }

    public  ResourceLocation key(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }

    public  ResourceLocation key(Item item) {
        return BuiltInRegistries.ITEM.getKey(item);
    }

    public  String name(Block block) {
        return key(block).getPath();
    }

    public  String name(Item item) {
        return key(item).getPath();
    }
}
