package com.legends.edumia.world.trees.trunkplacers;

import com.google.common.collect.ImmutableList;
import com.legends.edumia.world.trees.EdumiaTrunkPlacerTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

public class DeadTrunkPlacer extends ExtendedTrunkPlacer {
    public static final MapCodec<DeadTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec((instance) ->
            baseCodecWithWood(instance).apply(instance, DeadTrunkPlacer::new));
    protected DeadTrunkPlacer(int baseHeight, int heightRandA, int heightRandB, Optional<BlockStateProvider> woodProvider, Optional<BlockStateProvider> strippedLogProvider, Optional<BlockStateProvider> branchProvider) {
        super(baseHeight, heightRandA, heightRandB, woodProvider, strippedLogProvider, branchProvider);
    }

    public DeadTrunkPlacer(int baseHeight, int heightRandA, int heightRandB, BlockState wood, BlockState branch){
        this(baseHeight, heightRandA, heightRandB, Optional.of(BlockStateProvider.simple(wood)), Optional.empty(), Optional.of(BlockStateProvider.simple(branch)));
    }



    @Override
    protected TrunkPlacerType<?> type() {
        return EdumiaTrunkPlacerTypes.DEATH_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> trunk,
                                                            RandomSource random, int height, BlockPos startPos, TreeConfiguration config) {
        validTreePos(world, startPos.below());

        for (int y = 0; y < height; ++y){
            BlockPos trunkPos = startPos.above(y);
            placeLog(world, trunk, random, trunkPos, config);
            if (random.nextInt(6) == 0){
                this.placeRandomSurroundingBranch(world, random, trunkPos, trunk, config);
            }
        }

        if (height >= 3){
            BlockPos.MutableBlockPos branchPos = new BlockPos.MutableBlockPos();
            Iterator var16 = Direction.Plane.HORIZONTAL.iterator();

            while (var16.hasNext()){
                Direction dir = (Direction) var16.next();
                int branchLength = 2 + random.nextInt(4);
                int branchOut = 0;
                int branchUp = height - random.nextInt(3);

                for (int l = 0; l < branchLength; ++l){
                    if (random.nextInt(4) == 0){
                        ++branchOut;
                    }

                    if (l > 0 && random.nextInt(3) != 0){
                        ++branchUp;
                    }

                    if (branchOut > 2 && branchUp == 0){
                        ++branchUp;
                    }

                    branchPos.set(startPos.above(branchUp).relative(dir, branchOut));
                    this.placeWood(world, random, branchPos, trunk, config, Direction.Axis.Y);
                    if (random.nextInt(8) == 0 && world.isStateAtPosition(branchPos, BlockBehaviour.BlockStateBase::isAir)){
                        this.placeRandomSurroundingBranch(world, random, branchPos, trunk, config);
                    }
                }
            }
        }

        return ImmutableList.of();
    }

    private boolean placeRandomSurroundingBranch(LevelSimulatedReader world, RandomSource random, BlockPos pos, BiConsumer<BlockPos,
            BlockState> trunk, TreeConfiguration config){
        return this.placeBranch(world, random, pos.relative(Direction.Plane.HORIZONTAL.getRandomDirection(random)), trunk, config);
    }
}
