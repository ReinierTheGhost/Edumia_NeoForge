package com.legends.edumia.world.trees.trunkplacers;

import com.legends.edumia.world.trees.EdumiaTrunkPlacerTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
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

public class PartyTreeTrunkPlacer extends ExtendedTrunkPlacer {
    public static final MapCodec<PartyTreeTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec((instance) ->
            baseCodecWithWood(instance).apply(instance, PartyTreeTrunkPlacer::new));

    private PartyTreeTrunkPlacer(int baseHeight, int heightRandA, int heightRandB, Optional<BlockStateProvider> woodProvider,
                                 Optional<BlockStateProvider> strippedLogProvider, Optional<BlockStateProvider> branchProvider) {
        super(baseHeight, heightRandA, heightRandB, woodProvider, strippedLogProvider, branchProvider);
    }

    public PartyTreeTrunkPlacer(int baseHeight, int heightRandA, int heightRandB, BlockState wood, BlockState branch) {
        this(baseHeight, heightRandA, heightRandB, Optional.of(BlockStateProvider.simple(wood)), Optional.empty(),
                Optional.of(BlockStateProvider.simple(branch)));
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return EdumiaTrunkPlacerTypes.PARTY_TREE_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> trunk,
                                                            RandomSource random, int height, BlockPos startPos, TreeConfiguration config) {
        List<FoliagePlacer.FoliageAttachment> foliage = new ArrayList<>();
        int trunkWidth = 1;
        float trunkPitch = (float)Math.toRadians(Mth.nextFloat(random, 65.0F, 90.0F));
        float trunkYaw = (float)Math.toRadians((random.nextFloat() * 360.0F));
        BlockPos.MutableBlockPos movingPos = new BlockPos.MutableBlockPos();

        for (int y = 0; y < height; y++) {
            BlockPos offsetCentrePos = getOffsetCentrePos(startPos, y, trunkPitch, trunkYaw);

            for (int x = -trunkWidth; x <= trunkWidth; x++) {
                for (int z = -trunkWidth; z <= trunkWidth; z++) {
                    movingPos.setWithOffset(offsetCentrePos, x, 0, z);

                    placeWood(world, random, movingPos, trunk, config, Direction.Axis.Y);
//                    if (y == 0) {
//                        EdumiaTrunkPlacerTypes.setGrassToDirt(world, movingPos.down());
//                        BlockPos.Mutable woodBelowPos = (new BlockPos.Mutable()).set(movingPos.down());
//                        int woodBelow = 0;
//                        int maxWoodBelow = 6 + random.nextInt(3);
//
//                        while (woodBelowPos.getY() >= 0) {
//                            if (placeWood(world, random, woodBelowPos, trunk, config, Direction.Axis.Y)) {
//                                EdumiaTrunkPlacerTypes.setGrassToDirt(world, woodBelowPos.down());
//                                woodBelowPos.move(Direction.DOWN);
//                                woodBelow++;
//                                if (woodBelow > maxWoodBelow)
//                                    break;
//                            }
//                        }
//                    }
                }
            }
        }
        int angle = 0;
        while (angle < 360) {
            angle += 30 + random.nextInt(35);
            float angleR = (float)Math.toRadians(angle);
            float sin = Mth.sin(angleR);
            float cos = Mth.cos(angleR);
            int boughLength = 6 + random.nextInt(6);
            int boughThickness = Math.round(boughLength * 0.05F);
            int boughBaseHeight = Mth.floor(Mth.nextFloat(random, 0.65F, 0.95F) * height);
            int boughHeight = 2 + random.nextInt(4);
            BlockPos offsetCentrePos = getOffsetCentrePos(startPos, boughBaseHeight, trunkPitch, trunkYaw);

            for (int i = 0; i < boughLength; i++) {
                int x = Math.round(cos * i);
                int z = Math.round(sin * i);
                int j = Math.round((float) i / (float) boughLength * (float) boughHeight);
                int range = boughThickness - Math.round((float) i / (float) boughLength * (float) boughThickness * 0.5F);

                for (int x1 = -range; x1 <= range; x1++) {
                    for (int y1 = -range; y1 <= range; y1++) {
                        for (int z1 = -range; z1 <= range; z1++) {
                            movingPos.set(offsetCentrePos).move(x + x1, j + y1, z + z1);
                            this.placeWood(world, random, movingPos, trunk, config, Direction.Axis.Y);
                        }
                    }
                }

                int branch_angle = angle + Mth.nextInt(random, -60, 60);
                float branch_angleR = (float)Math.toRadians(branch_angle);
                float branch_sin = Mth.sin(branch_angleR);
                float branch_cos = Mth.cos(branch_angleR);
                int branchLength = 5 + random.nextInt(4);
                int branchHeight = random.nextInt(4);

                for (int l1 = 0; l1 < branchLength; l1++) {
                    int k = x + Math.round(branch_cos * l1);
                    int z1 = z + Math.round(branch_sin * l1);
                    int y1 = j + Math.round((float) l1 /(float) branchLength * (float) branchHeight);

                    for (int y2 = 0; y2 >= 0; y2--) {
                        movingPos.set(offsetCentrePos).move(k, y1 + y2, z1);
                        this.placeLogWithAxis(world, random, movingPos, trunk, config,
                                Direction.fromYRot(branch_angle).getClockWise().getAxis());
                    }
                    if (l1 == branchLength - 1 || random.nextInt(6) == 0) {
                        int size = (random.nextInt(6) == 0) ? 1 : 0;
                        foliage.add(new FoliagePlacer.FoliageAttachment(offsetCentrePos.immutable().offset(k, y1 + 1, z1),
                                size, false));
                    }
                }
            }
        }
        foliage.add(new FoliagePlacer.FoliageAttachment(getOffsetCentrePos(startPos, height, trunkPitch, trunkYaw), 1, false));
        int roots = 5 + random.nextInt(5);

//        for (int l = 0; l < roots; l++) {
//            int rootUpY = 0 + random.nextInt(4);
//            BlockPos.Mutable rootPos = (new BlockPos.Mutable()).set(getOffsetCentrePos(startPos, rootUpY, trunkPitch, trunkYaw));
//            int rootLength = 2 + random.nextInt(4);
//            Direction rootDir = Direction.Type.HORIZONTAL.random(random);
//            rootPos.move(rootDir, trunkWidth + 1);
//            rootPos.move(rootDir.rotateYClockwise(), MathHelper.nextInt(random, -trunkWidth, trunkWidth));
//
//            for (int l1 = 0; l1 < rootLength; l1++) {
//                BlockPos.Mutable dropDownPos = (new BlockPos.Mutable()).set(rootPos);
//                int woodDropped = 0;
//
//                while (dropDownPos.getY() >= 0) {
//                    BlockPos checkAbovePos = dropDownPos.up();
//                    boolean branch = (woodDropped <= 1 && !world.testBlockState(checkAbovePos, AbstractBlock.AbstractBlockState::isOpaque));
//                    boolean placedBlock = branch ? placeBranch(world, random, dropDownPos, trunk, config) : placeWood(world, random, dropDownPos, trunk, config, Direction.Axis.Y);
//                    if (placedBlock) {
////                        if (!branch) {
////                            EdumiaTrunkPlacerTypes.setGrassToDirt(world, dropDownPos.down());
////                        }
//
//                        dropDownPos.move(Direction.DOWN);
//                        woodDropped++;
//                        if (woodDropped > 5) {
//                            break;
//                        }
//                    }
//                }
//
//                rootPos.move(Direction.DOWN);
//                if (random.nextBoolean()) {
//                    rootPos.move(rootDir);
//                }
//            }
//        }
        return foliage;
    }



    private BlockPos getOffsetCentrePos(BlockPos startPos, int trunkHeightHere, float trunkPitch, float trunkYaw) {
        float upFrac = Mth.sin(trunkPitch) * trunkHeightHere;
        float outFrac = Mth.cos(trunkPitch) * trunkHeightHere;
        int offX = Math.round(outFrac * Mth.cos(trunkYaw));
        int offZ = Math.round(outFrac * Mth.sin(trunkYaw));
        return startPos.offset(offX, Math.round(upFrac), offZ);
    }

    private boolean isSolidWood(BlockState state) {
        return (state.canOcclude() && state.is(BlockTags.LOGS));
    }


}
