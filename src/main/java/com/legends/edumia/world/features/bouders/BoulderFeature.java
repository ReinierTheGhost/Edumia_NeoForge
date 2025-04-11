package com.legends.edumia.world.features.bouders;


import com.legends.edumia.world.features.EdumiaFeatures;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class BoulderFeature extends Feature<BouldersFeatureConfig> {

    public static final BoulderFeature FEATURE = new BoulderFeature(BouldersFeatureConfig.CODEC);

    public BoulderFeature(Codec<BouldersFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<BouldersFeatureConfig> context) {
        BlockPos pos = context.origin();
        WorldGenLevel world = context.level();
        RandomSource rand = context.random();

        BouldersFeatureConfig config = context.config();

        world.getBiome(pos);
        int boulderWidth = Mth.nextInt(rand, config.minWidth, config.maxWidth);

        int highestHeight = pos.getY();
        int lowestHeight = highestHeight;

        int spheres;
        int heightValue;

        for (int i = -boulderWidth; i <= boulderWidth; i++){
            for (spheres = -boulderWidth; spheres <= boulderWidth; spheres++){
                BlockPos heightPos = world.getHeightmapPos(Heightmap.Types.WORLD_SURFACE_WG, pos.offset(i, 0, spheres));
                if (!this.isSurfaceBlock(world, heightPos.below())){
                    return false;
                }

                heightValue = heightPos.getY();
                if (heightValue > highestHeight){
                    highestHeight = heightValue;
                }

                if (heightValue < lowestHeight){
                    lowestHeight = heightValue;
                }
            }
        }

        if (highestHeight - lowestHeight > config.heightCheck){
            return false;
        }else {
            BlockPos.MutableBlockPos movePos = new BlockPos.MutableBlockPos();
            spheres = Mth.nextInt(rand, 1, Math.max(1, boulderWidth));

            for (int l = 0; l < spheres; l++) {
                heightValue = Mth.nextInt(rand, -boulderWidth, boulderWidth);
                int zOffset = Mth.nextInt(rand, -boulderWidth, boulderWidth);
                BlockPos boulderPos = world.getHeightmapPos(Heightmap.Types.WORLD_SURFACE_WG, pos.offset(heightValue, 0, zOffset));
                int sphereWidth = Mth.nextInt(rand, config.minWidth, config.maxWidth);

                for (int i = -sphereWidth; i <= sphereWidth; i++) {
                    for (int j = -sphereWidth; j <= sphereWidth; j++) {
                        for (int k = -sphereWidth; k <= sphereWidth; k++) {
                            int dist = i * i + j * j + k * k;
                            if (dist < sphereWidth * sphereWidth || dist < (sphereWidth + 1) * (sphereWidth + 1) &&
                                    rand.nextInt(3) == 0) {
                                movePos.set(boulderPos.offset(i, j, k));

                                for (BlockPos below = movePos.below(); movePos.getY() >= 0 && !world.getBlockState(below)
                                        .isSolidRender(world, below); below = below.below()) {
                                    movePos.set(below);
                                }

                                world.setBlock(movePos, config.stateProfider.getState(rand, movePos), 3);
                                EdumiaFeatures.setGrassToDirtBelow(world, movePos);
                            }
                        }
                    }
                }
                return true;
            }
        }
        return true;
    }

    private boolean isSurfaceBlock(LevelAccessor world, BlockPos pos){
        return EdumiaFeatures.isSurfaceBlock(world, pos);
    }
}
