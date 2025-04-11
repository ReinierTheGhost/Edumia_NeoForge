package com.legends.edumia.world.trees.trunkplacers;

import com.legends.edumia.world.features.EdumiaFeatures;
import com.legends.edumia.world.trees.EdumiaTrunkPlacerTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedRW;
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

public class CitrusTrunkPlacer extends ExtendedTrunkPlacer{
    public static final Codec<CitrusTrunkPlacer> CODEC = RecordCodecBuilder.create(instance ->
        trunkPlacerParts(instance)
                .apply(instance, CitrusTrunkPlacer::new));

    public CitrusTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB, Optional.empty(), Optional.empty(), Optional.empty());
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return EdumiaTrunkPlacerTypes.CITRUS_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> trunk,
                                                            RandomSource random, int height, BlockPos startPos, TreeConfiguration config) {
        ArrayList<FoliagePlacer.FoliageAttachment> leafNodes = new ArrayList<>();

        // Set grass to dirt
        setDirtAt(world, trunk, random, startPos.below(), config);

        // Place trunk
        for (int i = 0; i < height; ++i) {
            placeLog(world, trunk, random, startPos.above(i), config);
        }

        // Add center leaf cluster
        leafNodes.add(new FoliagePlacer.FoliageAttachment(startPos.above(height - 1), 1, false));

        growBranches((LevelSimulatedRW) world, trunk, random, startPos.above(height - 4), config, leafNodes);

        return leafNodes;
    }

    private void growBranches(LevelSimulatedRW world, BiConsumer<BlockPos, BlockState> acceptor, RandomSource random, BlockPos startPos,
                              TreeConfiguration config, List<FoliagePlacer.FoliageAttachment> leafNodes) {
        int count = random.nextInt(3) + 1;
        double thetaOffset = random.nextDouble() * 2 * Math.PI;

        // Place 1-3 branches
        for (int i = 0; i < count; i++) {
            // Get angle of this branch
            double theta = (((double) i / count) * 2 * Math.PI) + thetaOffset;

            // Add a random offset to the theta
            theta += random.nextDouble() * Math.PI * 0.15;

            // Make branches 3-4 blocks long
            int dist = random.nextInt(3) == 0 ? 4 : 3;

            for (int j = 1; j <= dist; j++) {
                int x = (int) (Math.cos(theta) * j);
                int y = j == dist ? 1 : 0; // Make branch go up
                int z = (int) (Math.sin(theta) * j);
                BlockPos local = startPos.offset(x, y, z);

                // Get axis based on position
                Direction.Axis axis = EdumiaFeatures.getAxisBetween(startPos, local);

                // Place branch and add to logs
                acceptor.accept(local, config.trunkProvider.getState(random, local).setValue(RotatedPillarBlock.AXIS, axis));

                // Add leaves around the branch
                if (j == dist) {
                    leafNodes.add(new FoliagePlacer.FoliageAttachment(local, 0, false));
                }
            }
        }
    }
}
