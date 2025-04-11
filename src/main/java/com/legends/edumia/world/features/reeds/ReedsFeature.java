package com.legends.edumia.world.features.reeds;


import com.legends.edumia.blocks.plants.ReedsBlock;
import com.legends.edumia.utils.EdumiaLog;
import com.legends.edumia.world.features.bouders.BoulderFeature;
import com.legends.edumia.world.features.bouders.BouldersFeatureConfig;
import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.material.Fluids;

public class ReedsFeature extends Feature<ReedsFeatureConfig> {
    public static final ReedsFeature FEATURE = new ReedsFeature(ReedsFeatureConfig.CODEC);
    public ReedsFeature(Codec<ReedsFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<ReedsFeatureConfig> context) {
        BlockPos.MutableBlockPos movingPos = new BlockPos.MutableBlockPos();

        BlockPos pos = context.origin();
        RandomSource random = context.random();
        ReedsFeatureConfig config = context.config();
        WorldGenLevel world = context.level();

        for (int l = 0; l < config.tries; l++){
            int x = pos.getX() - random.nextInt(config.xSpread) + random.nextInt(config.xSpread);
            int y = pos.getY() - random.nextInt(config.ySpread) + random.nextInt(config.ySpread);
            int z = pos.getZ() - random.nextInt(config.zSpread) + random.nextInt(config.zSpread);
            movingPos.set(x, y, z);

            Block reedBlock = config.stateProvider.getState(random, movingPos).getBlock();
            if (!(reedBlock instanceof ReedsBlock)){
                EdumiaLog.warn("Attempt to generate non-reed block in a reeds feature (block: %s, position: [%d %d %d])",
                new Object[]{reedBlock.getName(), x, y, z});
            }else {
                BlockState baseState = reedBlock.defaultBlockState();
                baseState = (BlockState) baseState.setValue(ReedsBlock.WATERLOGGED, this.isWaterLogged(world, movingPos));
                if (this.canReedsReplaceAt(world, movingPos) && baseState.canSurvive(world, movingPos)) {
                    boolean threeHigh = random.nextFloat() < config.fullyGrownChange;
                    boolean placeTreeHigh = false;
                    BlockPos abovePos;

                    if (threeHigh){
                        abovePos = movingPos.above();
                        BlockPos twoAbovePos = abovePos.above();
                        if ((world.isEmptyBlock(abovePos) || world.getFluidState(abovePos).getType() == Fluids.WATER)
                                && world.isEmptyBlock(twoAbovePos)){
                            this.placeAppropriateReedState(world, movingPos, baseState, ReedsBlock.Type.THREE_BOTTOM);
                            this.placeAppropriateReedState(world, abovePos, baseState, ReedsBlock.Type.THREE_MIDDLE);
                            this.placeAppropriateReedState(world, twoAbovePos, baseState, ReedsBlock.Type.THREE_TOP);
                            placeTreeHigh = true;
                        }
                    }

                    if (!placeTreeHigh){
                        abovePos = movingPos.above();
                        if (world.isEmptyBlock(abovePos) || world.getFluidState(abovePos).getType() == Fluids.WATER){
                            this.placeAppropriateReedState(world, movingPos, baseState, ReedsBlock.Type.TWO_BOTTOM);
                            this.placeAppropriateReedState(world, abovePos, baseState, ReedsBlock.Type.TWO_TOP);
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean canReedsReplaceAt(LevelAccessor world, BlockPos pos) {
        BlockState existingState = world.getBlockState(pos);
        return existingState.isAir() || existingState.canBeReplaced() || existingState.getBlock() == Blocks.WATER;
    }

    private void placeAppropriateReedState(LevelAccessor world, BlockPos pos, BlockState state, ReedsBlock.Type type){
        world.setBlock(pos, (BlockState)((BlockState)state.setValue(ReedsBlock.REEDS_TYPE, type))
                .setValue(ReedsBlock.WATERLOGGED, this.isWaterLogged(world, pos)), 2);
    }

    private boolean isWaterLogged(LevelAccessor world, BlockPos pos){
        return world.getFluidState(pos).getType() == Fluids.WATER;
    }
}
