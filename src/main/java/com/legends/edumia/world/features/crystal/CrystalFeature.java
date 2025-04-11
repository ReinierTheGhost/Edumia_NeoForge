package com.legends.edumia.world.features.crystal;


import com.legends.edumia.blocks.CrystalBlock;
import com.legends.edumia.world.features.bouders.BoulderFeature;
import com.legends.edumia.world.features.bouders.BouldersFeatureConfig;
import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CrystalFeature extends Feature<CrystalFeatureConfig> {
    public static final CrystalFeature FEATURE = new CrystalFeature(CrystalFeatureConfig.CODEC);
    public CrystalFeature(Codec<CrystalFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<CrystalFeatureConfig> context) {
        BlockPos.MutableBlockPos movingPos = new BlockPos.MutableBlockPos();

        WorldGenLevel world = context.level();
        RandomSource random = context.random();
        BlockPos pos = context.origin();
        CrystalFeatureConfig config = context.config();
        java.util.Random rand = new java.util.Random();

        for (int l = 0; l < config.tries; l++){
            int x = pos.getX() - random.nextInt(config.xSpread) + random.nextInt(config.xSpread);
            int y = pos.getY() - random.nextInt(config.ySpread) + random.nextInt(config.ySpread);
            int z = pos.getZ() - random.nextInt(config.zSpread) + random.nextInt(config.zSpread);
            movingPos.set(x, y, z);
            if (world.isEmptyBlock(movingPos)) {
                BlockState baseState = config.stateProvider.getState(random, movingPos);
                List<Direction> dirs = Arrays.asList(Direction.values());
                Collections.shuffle(dirs, rand);
                Iterator var13 = dirs.iterator();

                while (var13.hasNext()){
                    Direction dir = (Direction) var13.next();
                    BlockState placeState = (BlockState) baseState.setValue(CrystalBlock.CRYSTAL_FACING, dir);
                    if (placeState.canSurvive(world, movingPos)){
                        world.setBlock(movingPos, placeState, 2);
                        break;
                    }
                }
            }
        }
        return true;
    }
}
