package com.legends.edumia.world.trees.trunkplacers;

import com.legends.edumia.world.trees.EdumiaTrunkPlacerTypes;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class ClusteredPalmTrunkPlacer  extends TrunkPlacer {
    public static final MapCodec<ClusteredPalmTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec((instance) ->
            trunkPlacerParts(instance).apply(instance, ClusteredPalmTrunkPlacer::new));

    public ClusteredPalmTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return EdumiaTrunkPlacerTypes.CLUSTERED_PALM_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter,
                                                            RandomSource random, int freeHeight, BlockPos pos, TreeConfiguration config) {
        List<FoliagePlacer.FoliageAttachment> foliagePositions = new ArrayList<>();

        // Number of trunks (4-6)
        int trunkCount = random.nextInt(3) + 7;

        // Generate trunk positions within 5x5 area
        List<TrunkData> trunks = new ArrayList<>();
        for (int i = 0; i < trunkCount; i++) {
            int x = random.nextInt(7) - 2; // -2 to 2
            int z = random.nextInt(7) - 2;

            // Calculate distance from center
            double distanceFromCenter = Math.sqrt(x * x + z * z);

            // Skip if too close to another trunk
            boolean tooClose = false;
            for (TrunkData existing : trunks) {
                if (Math.abs(existing.x - x) <= 1 && Math.abs(existing.z - z) <= 1) {
                    tooClose = true;
                    break;
                }
            }
            if (tooClose) {
                i--; // Try again
                continue;
            }

            // Height increases slightly for outer trunks
            int height = freeHeight - (int)(distanceFromCenter);
            height = Math.min(height, freeHeight + 4); // Max height

            // Bend factor increases with distance
            double bendFactor = distanceFromCenter * 0.8 + 1.0;

            trunks.add(new TrunkData(x, z, height, bendFactor));
        }

        // Place each trunk
        for (TrunkData trunk : trunks) {
            placeSingleTrunk(level, blockSetter, random, pos.offset(trunk.x, 0, trunk.z),
                    trunk.height, trunk.bendFactor, trunk.x, trunk.z, config, foliagePositions);
        }

        return foliagePositions;
    }

    private void placeSingleTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter,
                                  RandomSource random, BlockPos startPos, int height, double bendFactor,
                                  double dirX, double dirZ, TreeConfiguration config,
                                  List<FoliagePlacer.FoliageAttachment> foliagePositions) {
        BlockPos.MutableBlockPos currentPos = startPos.mutable();
        BlockPos.MutableBlockPos lastPos = startPos.mutable();

        // Calculate bend direction outward from center
        double length = Math.sqrt(dirX * dirX + dirZ * dirZ);
        if (length < 0.1) {
            double angle = random.nextDouble() * Math.PI * 2;
            dirX = Math.cos(angle);
            dirZ = Math.sin(angle);
        } else {
            dirX = dirX / length;
            dirZ = dirZ / length;
        }

        // Add slight randomization to bend direction
        double randomAngle = (random.nextDouble() - 0.5) * Math.PI / 6; // ±30 degrees
        double cosAngle = Math.cos(randomAngle);
        double sinAngle = Math.sin(randomAngle);
        double newXBend = dirX * cosAngle - dirZ * sinAngle;
        double newZBend = dirX * sinAngle + dirZ * cosAngle;
        dirX = newXBend;
        dirZ = newZBend;

        int lastXOffset = 0;
        int lastZOffset = 0;
        BlockPos lastBendPos = null;

        // Place trunk blocks up to height-1 (leaving room for final straight block)
        for (int y = 0; y < height - 1; y++) {
            double bendProgress = y / (double) (height - 1);
            double bendAmount = bendFactor * (bendProgress * bendProgress);

            int xOffset = (int) Math.round(dirX * bendAmount);
            int zOffset = (int) Math.round(dirZ * bendAmount);

            BlockPos placePos = currentPos.offset(xOffset, y, zOffset);

            if (TreeFeature.validTreePos(level, placePos)) {
                placeLog(level, blockSetter, random, placePos, config);

                // Place connecting block if position changed
                if (xOffset != lastXOffset || zOffset != lastZOffset) {
                    BlockPos connectPos = currentPos.offset(lastXOffset, y, lastZOffset);
                    if (TreeFeature.validTreePos(level, connectPos)) {
                        placeLog(level, blockSetter, random, connectPos, config);
                    }
                    lastBendPos = placePos;
                }
            }

            lastXOffset = xOffset;
            lastZOffset = zOffset;
        }

        // Place final straight block above the last bend
        if (lastBendPos != null) {
            BlockPos finalPos = lastBendPos.above(1);
            if (TreeFeature.validTreePos(level, finalPos)) {
                placeLog(level, blockSetter, random, finalPos, config);
                foliagePositions.add(new FoliagePlacer.FoliageAttachment(finalPos.above(), 0, false));
            }else {
                BlockPos straight = currentPos.offset(lastXOffset, height - 2, lastZOffset);
                placeLog(level, blockSetter, random, finalPos, config);
                foliagePositions.add(new FoliagePlacer.FoliageAttachment(straight.above(), 0, false));
            }

        } else {
            // If trunk didn't bend, use the final position
            BlockPos finalPos = currentPos.offset(lastXOffset, height - 1, lastZOffset);
            if (TreeFeature.validTreePos(level, finalPos)) {
                placeLog(level, blockSetter, random, finalPos, config);
                foliagePositions.add(new FoliagePlacer.FoliageAttachment(finalPos.above(), 0, false));
            }

        }
    }

    private static class TrunkData {
        final int x, z, height;
        final double bendFactor;

        TrunkData(int x, int z, int height, double bendFactor) {
            this.x = x;
            this.z = z;
            this.height = height;
            this.bendFactor = bendFactor;
        }
    }


}
