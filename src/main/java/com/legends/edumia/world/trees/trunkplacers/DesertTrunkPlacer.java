package com.legends.edumia.world.trees.trunkplacers;

import com.legends.edumia.world.trees.EdumiaTrunkPlacerTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

public class DesertTrunkPlacer extends ExtendedTrunkPlacer {
    public static final Codec<DesertTrunkPlacer> CODEC = RecordCodecBuilder.create((instance) ->
            baseCodecWithWood(instance).apply(instance, DesertTrunkPlacer::new));
    protected DesertTrunkPlacer(int baseHeight, int heightRandA, int heightRandB, Optional<BlockStateProvider> woodProvider, Optional<BlockStateProvider> strippedLogProvider, Optional<BlockStateProvider> branchProvider) {
        super(baseHeight, heightRandA, heightRandB, woodProvider, strippedLogProvider, branchProvider);
    }

    public DesertTrunkPlacer(int baseHeight, int heightRandA, int heightRandB, BlockState wood){
        this(baseHeight, heightRandA, heightRandB, Optional.of(BlockStateProvider.simple(wood)), Optional.empty(), Optional.empty());
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return EdumiaTrunkPlacerTypes.DESERT_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> trunk,
                                                            RandomSource random, int height, BlockPos startPos, TreeConfiguration config) {
        validTreePos(world, startPos.below());

        for (int y = 0; y < height; ++y){
            placeLog(world, trunk, random, startPos.above(y), config);
        }

        List<FoliagePlacer.FoliageAttachment> foliage = new ArrayList<>();
        int trunkTopOffset = 0;
        int branches = 4;
        for (Direction branchDir : Direction.Plane.HORIZONTAL) {
            int branchLength = 1 + random.nextInt(3);
            int branchHeight = height - trunkTopOffset - 1 - random.nextInt(2);
            BlockPos.MutableBlockPos branchPos = (new BlockPos.MutableBlockPos()).setWithOffset(startPos, 0,
                    branchHeight, 0);
            for (int l = 0; l < branchLength; l++) {
                if (random.nextInt(3) != 0){
                    branchPos.move(Direction.UP);
                }

                if (random.nextInt(3) != 0){
                    branchPos.move(branchDir);
                }

                if (!placeLog(world, trunk, random, branchPos, config)) {
                    break;
                }
            }
            foliage.add(new FoliagePlacer.FoliageAttachment(branchPos.above(), 0, false));
        }

        return foliage;
    }
}
