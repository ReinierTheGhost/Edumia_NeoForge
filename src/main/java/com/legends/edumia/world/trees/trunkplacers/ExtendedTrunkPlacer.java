package com.legends.edumia.world.trees.trunkplacers;

import com.legends.edumia.world.features.EdumiaFeatures;
import com.mojang.datafixers.Products;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.material.Fluids;

import java.util.Optional;
import java.util.function.BiConsumer;

public abstract class ExtendedTrunkPlacer extends TrunkPlacer {

    protected final Optional<BlockStateProvider> woodProvider;

    protected final Optional<BlockStateProvider> strippedLogProvider;

    protected final Optional<BlockStateProvider> branchProvider;

    protected static <P extends ExtendedTrunkPlacer> Products.P6<RecordCodecBuilder.Mu<P>, Integer, Integer, Integer,
            Optional<BlockStateProvider>, Optional<BlockStateProvider>, Optional<BlockStateProvider>> baseCodecWithWood(
                    RecordCodecBuilder.Instance<P> instance) {
        return trunkPlacerParts(instance).and(instance.group(BlockStateProvider.CODEC
                .optionalFieldOf("wood_provider").forGetter(trunk -> trunk.woodProvider), BlockStateProvider.CODEC
                .optionalFieldOf("stripped_log_provider").forGetter(trunk -> trunk.strippedLogProvider), BlockStateProvider.CODEC
                .optionalFieldOf("branch_provider").forGetter(trunk -> trunk.branchProvider)));
    }
    public ExtendedTrunkPlacer(int baseHeight, int heightRandA, int heightRandB, Optional<BlockStateProvider> woodProvider,
                               Optional<BlockStateProvider> strippedLogProvider, Optional<BlockStateProvider> branchProvider) {
        super(baseHeight, heightRandA, heightRandB);
        this.woodProvider = woodProvider;
        this.strippedLogProvider = strippedLogProvider;
        this.branchProvider = branchProvider;
    }

    /**
     * to place logs with a rotation
     * @param world
     * @param random
     * @param pos
     * @param trunk
     * @param config
     * @param axis
     * @return
     */
    protected boolean placeLogWithAxis(LevelSimulatedReader world, RandomSource random, BlockPos pos, BiConsumer<BlockPos, BlockState> trunk,
                                       TreeConfiguration config, Direction.Axis axis){
        if (TreeFeature.validTreePos(world, pos)){
            BlockState logState = config.trunkProvider.getState(random, pos);
            if (logState.hasProperty(RotatedPillarBlock.AXIS)){
                logState = logState.setValue(RotatedPillarBlock.AXIS, axis);
            }

            trunk.accept(pos, logState);
            return  true;
        } else {
            return false;
        }
    }

    /**
     * To place wood blocks
     * @param world
     * @param random
     * @param pos
     * @param trunk
     * @param config
     * @param axis
     * @return
     */
    protected boolean placeWood(LevelSimulatedReader world, RandomSource random, BlockPos pos, BiConsumer<BlockPos, BlockState> trunk,
                                TreeConfiguration config, Direction.Axis axis){
        if (TreeFeature.validTreePos(world, pos)){
            BlockState woodState = (this.woodProvider.orElseThrow(() -> {
                return  new IllegalStateException("Wood blockstate provider is not set!");
            })).getState(random, pos);
            if (woodState.hasProperty(RotatedPillarBlock.AXIS)){
                woodState = woodState.setValue(RotatedPillarBlock.AXIS, axis);
            }

            trunk.accept(pos, woodState);
            return true;
        } else{
            return false;
        }
    }

    /**
     * To place stripped logs
     * @param world
     * @param random
     * @param pos
     * @param trunk
     * @param config
     * @param axis
     * @return
     */

    protected boolean placeStrippedLog(LevelSimulatedReader world, RandomSource random, BlockPos pos, BiConsumer<BlockPos, BlockState> trunk,
                                TreeConfiguration config, Direction.Axis axis){
        if (TreeFeature.validTreePos(world, pos)){
            BlockState strippedLogState = (this.strippedLogProvider.orElseThrow(() -> {
                return  new IllegalStateException("Stripped log blockstate provider is not set!");
            })).getState(random, pos);
            if (strippedLogState.hasProperty(RotatedPillarBlock.AXIS)){
                strippedLogState = strippedLogState.setValue(RotatedPillarBlock.AXIS, axis);
            }

            trunk.accept(pos, strippedLogState);
            return true;
        } else{
            return false;
        }
    }

    protected boolean placeBranch(LevelSimulatedReader world, RandomSource random, BlockPos pos, BiConsumer<BlockPos, BlockState> trunk,
                                       TreeConfiguration config){
        if (TreeFeature.validTreePos(world, pos)){
            BlockState branchState = (this.branchProvider.orElseThrow(() -> {
                return  new IllegalStateException("Branch blockstate provider is not set!");
            })).getState(random, pos);
            if (branchState.hasProperty(BlockStateProperties.WATERLOGGED)){
                branchState = branchState.setValue(BlockStateProperties.WATERLOGGED, world.isStateAtPosition(pos, (state) ->{
                    return state.getFluidState().getType() == Fluids.WATER;
                }));
            }

            LevelAccessor worldProper;
            if (world instanceof LevelAccessor){
                worldProper = (LevelAccessor) world;
                branchState = EdumiaFeatures.getBlockStateInContext(branchState, worldProper, pos);
            }

            trunk.accept(pos, branchState);
            if (world instanceof LevelAccessor){
                worldProper = (LevelAccessor) world;
                this.updateNeighboursAfterGeneration(worldProper, pos);
            }

            return true;
        } else{
            return false;
        }
    }

    /**
     * to let roots grow downwards in to the ground
     * This method is a cut down of
     * {@linkplain #growRootsDownBranchingOut(LevelSimulatedReader, RandomSource, BlockPos.MutableBlockPos, int, Direction, int, BiConsumer, TreeConfiguration)}
     * @param world
     * @param random
     * @param rootPos
     * @param rootLength
     * @param trunk
     * @param config
     */
    protected void growRootsDown(LevelSimulatedReader world, RandomSource random, BlockPos.MutableBlockPos rootPos, int rootLength,
                                 BiConsumer<BlockPos, BlockState> trunk, TreeConfiguration config){
        this.growRootsDownBranchingOut(world, random, rootPos, rootLength, (Direction) null, 0, trunk, config);
    }

    /**
     * This Method is used to let roots grown down and branch outwards
     * @param world
     * @param random
     * @param rootPos
     * @param rootLength
     * @param outwardsDir
     * @param outwardsInterval
     * @param trunk
     * @param config
     */
    protected void growRootsDownBranchingOut(LevelSimulatedReader world, RandomSource random, BlockPos.MutableBlockPos rootPos, int rootLength,
                                             Direction outwardsDir, int outwardsInterval,
                                             BiConsumer<BlockPos, BlockState> trunk, TreeConfiguration config){
        int roots = 0;
        int outwardsStartAt = outwardsInterval > 0 ? random.nextInt(outwardsInterval) : 0;

        while (rootPos.getY() >= 0 && TreeFeature.validTreePos(world, rootPos) && this.placeBranch(world, random, rootPos, trunk, config)){
            if (outwardsDir != null && outwardsInterval > 0 && roots %  outwardsInterval == outwardsStartAt){
                rootPos.move(outwardsDir);
            }else {
                rootPos.move(Direction.DOWN);
            }

            ++roots;
            if (roots > rootLength){
                break;
            }
        }
    }

    /**
     *
     * @param world
     * @param random
     * @param rootPos
     * @param rootLength
     * @param outwardsDir
     * @param maxOut
     * @param trunk
     * @param config
     */
    protected void growRootsDownAndThanOut(LevelSimulatedReader world, RandomSource random, BlockPos.MutableBlockPos rootPos, int rootLength,
                                           Direction outwardsDir, int maxOut,
                                           BiConsumer<BlockPos, BlockState> trunk, TreeConfiguration config){
        int roots = 0;
        int numOut = 0;

        while (rootPos.getY() >= 0 && TreeFeature.validTreePos(world, rootPos) && this.placeBranch(world, random, rootPos, trunk, config)){
            if (world.isStateAtPosition(rootPos.below(), BlockBehaviour.BlockStateBase::canOcclude)){
                rootPos.move(outwardsDir);
                ++numOut;
                if (numOut > maxOut){
                    break;

                }
            }else{
                rootPos.move(Direction.DOWN);
            }

            ++roots;
            if (roots > rootLength){
                break;
            }
        }

    }


    private void updateNeighboursAfterGeneration(LevelAccessor world, BlockPos pos){
        Direction[] var3 = Direction.values();
        int var4 = var3.length;

        for (int var5 = 0; var5 < var4; ++var5){
            Direction dir = var3[var5];
            BlockPos adjacentPos = pos.relative(dir);
            BlockState state = world.getBlockState(adjacentPos);
            BlockState updateShape = EdumiaFeatures.getBlockStateInContext(state, world, adjacentPos);
            world.setBlock(adjacentPos, updateShape, 19);
        }
    }
}
