package com.legends.edumia.world.trees.trunkplacers;


import com.legends.edumia.world.features.EdumiaFeatures;
import com.legends.edumia.world.trees.EdumiaTrunkPlacerTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

public class PleodendronTrunkPlacer extends ExtendedTrunkPlacer{
    public static final MapCodec<PleodendronTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
        trunkPlacerParts(instance)
                .apply(instance, PleodendronTrunkPlacer::new));


    public PleodendronTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB, Optional.empty(), Optional.empty(),
                Optional.empty());
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return EdumiaTrunkPlacerTypes.PLEODENDRON_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> trunk,
                                                            RandomSource random, int height, BlockPos startPos, TreeConfiguration config) {
        setDirtAt(world, trunk, random, startPos.below(), config);
        List<FoliagePlacer.FoliageAttachment> leafNodes = new ArrayList<>();

        for (int i = 0; i < height; ++i) {
            placeLog(world, trunk, random, startPos.above(i), config);
        }

        leafNodes.add(new FoliagePlacer.FoliageAttachment(startPos.above(height + 1), -1, false));

        for (int i = 5; i < height - 4; ++i) {
            if (random.nextInt(4) == 0) {
                growBranches(world, trunk, random, startPos.above(i), config, leafNodes);
            }
        }

        return leafNodes;
    }

    private void growBranches(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> trunk, RandomSource random,
                              BlockPos startPos, TreeConfiguration config, List<FoliagePlacer.FoliageAttachment> leafNodes) {
        int count = random.nextInt(2) + 1;
        double thetaOffset = random.nextDouble() * 2 * Math.PI;

        // Place 1-2 branches
        for (int i = 0; i < count; i++) {
            // Get angle of this branch
            double theta = (((double) i / count) * 2 * Math.PI) + thetaOffset;

            // Add a random offset to the theta
            theta += random.nextDouble() * Math.PI * 0.15;

            // Make branches 3-4 blocks long
            int dist = random.nextInt(3) == 0 ? 4 : 3;

            for (int j = 1; j <= dist; j++) {
                int x = (int) (Math.cos(theta) * j);
                int z = (int) (Math.sin(theta) * j);
                BlockPos local = startPos.offset(x, 0, z);

                // Get axis based on position
                Direction.Axis axis = EdumiaFeatures.getAxisBetween(startPos, local);

                // Place branch and add to logs
                trunk.accept(local, config.trunkProvider.getState(random, local).setValue(RotatedPillarBlock.AXIS, axis));

                // Add leaves around the branch
                if (j == dist) {
                    leafNodes.add(new FoliagePlacer.FoliageAttachment(local.above(1), -2, false));
                }
            }
        }
    }
}
