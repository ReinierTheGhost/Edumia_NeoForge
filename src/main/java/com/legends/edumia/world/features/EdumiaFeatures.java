package com.legends.edumia.world.features;

import com.legends.edumia.Edumia;
import com.legends.edumia.core.TagLoader;
import com.legends.edumia.world.features.bouders.BoulderFeature;
import com.legends.edumia.world.features.bouders.BouldersFeatureConfig;
import com.legends.edumia.world.features.crystal.CrystalFeature;
import com.legends.edumia.world.features.crystal.CrystalFeatureConfig;
import com.legends.edumia.world.features.reeds.ReedsFeature;
import com.legends.edumia.world.features.reeds.ReedsFeatureConfig;
import com.legends.edumia.world.features.treesnbt.TreeFromStructureNBTConfig;
import com.legends.edumia.world.features.treesnbt.TreeFromStructureNBTFeature;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


import java.util.function.Supplier;

public class EdumiaFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(BuiltInRegistries.FEATURE, Edumia.MOD_ID);
    public static final DeferredHolder<Feature<?>, BoulderFeature> BOULDER = FEATURES.register("boulder", () -> new BoulderFeature(BouldersFeatureConfig.CODEC));
    public static final DeferredHolder<Feature<?>, CrystalFeature> CRYSTAL = FEATURES.register("crystal", () -> new CrystalFeature(CrystalFeatureConfig.CODEC));
    public static final DeferredHolder<Feature<?>, ReedsFeature> REEDS = FEATURES.register("reeds", () -> new ReedsFeature(ReedsFeatureConfig.CODEC));
    public static final DeferredHolder<Feature<?>, TreeFromStructureNBTFeature> TREE_FROM_NBT = FEATURES.register("tree_from_nbt",
            () -> new TreeFromStructureNBTFeature(TreeFromStructureNBTConfig.CODEC));



    public static BlockState getBlockStateInContext(BlockState state, LevelAccessor worldAccess, BlockPos pos){
        return Block.updateFromNeighbourShapes(state, worldAccess, pos);
    }

    // Returns the axis that a rotatable block should face based on a start and end position
    public static Direction.Axis getAxisBetween(BlockPos start, BlockPos end) {
        Direction.Axis axis = Direction.Axis.Y;
        int xOffset = Math.abs(end.getX() - start.getX());
        int zOffset = Math.abs(end.getZ() - start.getZ());
        int maxOffset = Math.max(xOffset, zOffset);

        if (maxOffset > 0) {
            if (xOffset == maxOffset) {
                axis = Direction.Axis.X;
            } else {
                axis = Direction.Axis.Z;
            }
        }

        return axis;
    }

    public static boolean isSurfaceBlock(LevelAccessor world, BlockPos pos){
        return isSurfaceBlock(world, pos, 0);
    }

    private static boolean isSurfaceBlock(LevelAccessor world, BlockPos pos, int recursion) {
        if (world.getBlockState(pos.above()).liquid()){
            return false;
        } else {
            BlockState state = world.getBlockState(pos);
            Block block = state.getBlock();


            if (!block.defaultBlockState().is(BlockTags.SAND) && !block.defaultBlockState().is(BlockTags.VALID_SPAWN)
                    && !block.defaultBlockState().is(TagLoader.Blocks.GRAVEL) && !block.defaultBlockState().is(TagLoader.Blocks.DIRT)){
                if (block.defaultBlockState().is(TagLoader.Blocks.VOLCANIC_PLANT_SURFACE)) {
                    return true;
                } else {
                    return block == Blocks.STONE && recursion <= 1 && isSurfaceBlock(world, pos.below(), recursion + 1);
                }
            } else {
                return true;
            }
        }
    }
    public static void setGrassToDirt(LevelSimulatedReader world, BlockPos groundPos) {
        if (world.isStateAtPosition(groundPos, (state) -> {
            return state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.MYCELIUM);
        })){
            TreeFeature.setBlockKnownShape((LevelWriter) world, groundPos, Blocks.DIRT.defaultBlockState());
        }
    }

    public static void setGrassToDirtBelow(LevelAccessor world, BlockPos pos){
        BlockPos belowPos = pos.below();
        BlockState belowState = world.getBlockState(belowPos);

        if (world.getBlockState(pos).is(Blocks.GRASS_BLOCK)){
            if (belowState.is(Blocks.DIRT) || belowState.is(Blocks.GRASS_BLOCK)){
                world.setBlock(belowPos, Blocks.DIRT.defaultBlockState(), 3);
            }
        }
    }

    public static void register(IEventBus eventBus) {
        FEATURES.register(eventBus);
    }
}
