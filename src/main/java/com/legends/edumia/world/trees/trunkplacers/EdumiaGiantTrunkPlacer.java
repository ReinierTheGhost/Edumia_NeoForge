package com.legends.edumia.world.trees.trunkplacers;

import com.legends.edumia.world.features.EdumiaFeatures;
import com.legends.edumia.world.trees.EdumiaTrunkPlacerTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedRW;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class EdumiaGiantTrunkPlacer extends ExtendedTrunkPlacer {
    public static final Codec<EdumiaGiantTrunkPlacer> CODEC = RecordCodecBuilder.create((instance) ->
            baseCodecWithWood(instance).apply(instance, EdumiaGiantTrunkPlacer::new));
    private final boolean generateLeaves = true;
    protected EdumiaGiantTrunkPlacer(int baseHeight, int heightRandA, int heightRandB, Optional<BlockStateProvider> woodProvider,
                                     Optional<BlockStateProvider> strippedLogProvider, Optional<BlockStateProvider> branchProvider) {
        super(baseHeight, heightRandA, heightRandB, woodProvider, strippedLogProvider, branchProvider);
    }

    public EdumiaGiantTrunkPlacer(int baseHeight, int heightRandA, int heightRandB, BlockState wood, BlockState strippedLog){
        this(baseHeight, heightRandA, heightRandB, Optional.of(BlockStateProvider.simple(wood)),
                Optional.of(BlockStateProvider.simple(strippedLog)),
                Optional.empty());
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return EdumiaTrunkPlacerTypes.EDUMIA_GIANT_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> trunk,
                                                            RandomSource random, int height, BlockPos startPos, TreeConfiguration config){
        int trunkRadiusMin = (int) ((float) height * 0.125F);
        int trunkRadiusMax = trunkRadiusMin + 4;
        int xSlope = Mth.nextInt(random, 4, 18) * (random.nextBoolean() ? -1 : 1);
        int zSlope = Mth.nextInt(random, 4, 18) * (random.nextBoolean() ? -1 : 1);
        List<FoliagePlacer.FoliageAttachment> foliage = new ArrayList<>();
        BlockPos.MutableBlockPos offsetCentrePos = (new BlockPos.MutableBlockPos()).set(startPos);
        Set<BlockPos> strippedLogTrunkPositions = new HashSet<>();
        BlockPos.MutableBlockPos movingPos = new BlockPos.MutableBlockPos();


        for (int y = 0; y < height; ++y){
            float heightF = (float) y / (float) height;
            int width = trunkRadiusMax - (int) (heightF * (float) (trunkRadiusMax - trunkRadiusMin));

            for (int x = -width; x <= width; ++x){
                for (int z = -width; z <= width; ++z){
                    movingPos.set(offsetCentrePos).move(x, y, z);
                    if (x * x + z * z < width * width) {
                        if (this.placeStrippedLog(world, random, movingPos, trunk, config, Direction.Axis.Y)){
                            strippedLogTrunkPositions.add(movingPos.immutable());
                        }

                        if (y == 0){
                            EdumiaFeatures.setGrassToDirt(world, movingPos.below());
                            BlockPos.MutableBlockPos woodBelowPos = (new BlockPos.MutableBlockPos()).set(movingPos.below());
                            int woodBelow = 0;
                            int maxWoodBelow = 6 + random.nextInt(5);

                            while (woodBelowPos.getY() >= 0 && this.placeStrippedLog(world, random, woodBelowPos, trunk, config, Direction.Axis.Y)){
                                strippedLogTrunkPositions.add(woodBelowPos.immutable());
                                EdumiaFeatures.setGrassToDirt(world, woodBelowPos.below());
                                woodBelowPos.move(Direction.DOWN);
                                ++woodBelow;
                                if (woodBelow > maxWoodBelow){
                                    break;
                                }
                            }
                        }
                    }
                }

            }

            if (y % xSlope == 0){
                if (xSlope > 0){
                    offsetCentrePos.move(Direction.EAST);
                }else if (xSlope < 0){
                    offsetCentrePos.move(Direction.WEST);
                }
            }

            if (y % zSlope == 0){
                if (zSlope > 0){
                    offsetCentrePos.move(Direction.SOUTH);
                } else if (zSlope < 0) {
                    offsetCentrePos.move(Direction.NORTH);
                }
            }
        }

        Predicate<BlockState> notWood = (state) ->
                !state.is(BlockTags.LOGS);

        LevelSimulatedRW newWorld = (LevelSimulatedRW) world;
        for (BlockPos strippedPos : strippedLogTrunkPositions) {
            for (Direction checkDir : Direction.values()) {
                if (world.isStateAtPosition(strippedPos.relative(checkDir), notWood)){
                    newWorld.setBlock(strippedPos, (this.woodProvider.get()).getState(random, strippedPos), 19);
                    break;
                }
            }
        }

        int angle = 0;

        while (angle < 360){
            angle += 10 + random.nextInt(20);
            float angleR = (float) Math.toRadians(angle);
            float sin = Mth.sin(angleR);
            float cos = Mth.cos(angleR);
            int boughLength = 12 + random.nextInt(10);
            int boughThickness = Math.round(boughLength / 25.0F * 1.5F);
            int boughBaseHeight = Mth.floor(height * (0.9F + random.nextFloat() * 0.1F));
            int boughHeight = 3 + random.nextInt(4);

            for (int l = 0; l < boughLength; ++l){
                int x = Math.round(cos * l);
                int z = Math.round(sin * l);
                int y = boughBaseHeight + Math.round((float) l / (float) boughLength * (float) boughHeight);
                int range = boughThickness - Math.round((float) l / (float) boughLength * boughThickness * 0.5F);

                for (int x1 = -range; x1 <= range; ++x1){
                    for (int y1 = -range; y1 <= range; y1++) {
                        for (int z1 = -range; z1 <= range; z1++) {
                            movingPos.set(offsetCentrePos).move(x + x1, y + y1, z + z1);
                            this.placeWood(world, random, movingPos, trunk, config, Direction.Axis.Y);
                        }
                    }
                }

                int branch_angle = angle + random.nextInt(360);
                float branch_angleR = (float) Math.toRadians(branch_angle);
                float branch_sin = Mth.sin(branch_angleR);
                float branch_cos = Mth.cos(branch_angleR);
                int branchLength = 7 + random.nextInt(6);
                int branchHeight = random.nextInt(6);

                for (int l1 = 0; l1 < branchLength; l1++) {
                    int x1 = x + Math.round(branch_cos * l1);
                    int z1 = z + Math.round(branch_sin * l1);
                    int y1 = y + Math.round((float) l1 / (float) branchLength * (float) branchHeight);
                    for (int y2 = 0; y2 >= -1; y2--) {
                        movingPos.set(offsetCentrePos).move(x1, y1 + y2, z1);
                        this.placeWood(world, random, movingPos, trunk, config, Direction.Axis.Y);
                    }

                    if (l1 == branchLength - 1) {
                        BlockPos foliagePos = offsetCentrePos.immutable().offset(x1, y1, z1);
                        foliage.add(new FoliagePlacer.FoliageAttachment(foliagePos, 0, false));
                    }
                }
            }
        }
        return foliage;
    }
}
