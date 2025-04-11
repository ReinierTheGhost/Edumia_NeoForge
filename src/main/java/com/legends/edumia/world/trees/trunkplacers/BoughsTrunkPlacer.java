package com.legends.edumia.world.trees.trunkplacers;

import com.legends.edumia.world.trees.EdumiaTrunkPlacerTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;


import java.util.*;
import java.util.function.BiConsumer;

public class BoughsTrunkPlacer extends ExtendedTrunkPlacer {
    public static final Codec<BoughsTrunkPlacer> CODEC = RecordCodecBuilder.create((instance) ->
            baseCodecWithWood(instance).apply(instance, BoughsTrunkPlacer::new));
    public BoughsTrunkPlacer(int baseHeight, int heightRandA, int heightRandB, Optional<BlockStateProvider> woodProvider,
                             Optional<BlockStateProvider> strippedLogProvider, Optional<BlockStateProvider> branchProvider) {
        super(baseHeight, heightRandA, heightRandB, woodProvider, strippedLogProvider, branchProvider);
    }

    public BoughsTrunkPlacer(int baseHeight, int heightRandA, int heightRandB, BlockState wood, BlockState branch){
        this(baseHeight, heightRandA, heightRandB, Optional.of(BlockStateProvider.simple(wood)),
                Optional.empty(), Optional.of(BlockStateProvider.simple(branch)));
    }



    @Override
    protected TrunkPlacerType<?> type() {
        return EdumiaTrunkPlacerTypes.BOUGHS_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> replacer,
                                                            RandomSource random, int height, BlockPos startPos, TreeConfiguration config) {
        validTreePos(world, startPos.below());

        int branchMinHeight;
        for (branchMinHeight = 0; branchMinHeight < height; ++branchMinHeight){
            placeLog(world, replacer, random, startPos.above(branchMinHeight), config);
        }

        branchMinHeight = (int) ((float) height * 0.6f);
        int branchMaxHeight = height - 1;
        this.placeWood(world, random, startPos.above(branchMaxHeight), replacer, config, Direction.Axis.Y);
        List<FoliagePlacer.FoliageAttachment> foliage = new ArrayList<>();
        int deg = 0;

        int rootUp;
        for (int y = branchMaxHeight; y >= branchMinHeight; --y) {
            int branches = 1 + random.nextInt(2);

            for (rootUp = 0; rootUp < branches; ++rootUp){
                deg += 50 + random.nextInt(70);
                float angle = (float) Math.toRadians(deg);
                float cos = Mth.cos(angle);
                float sin = Mth.sin(angle);
                float angleY = random.nextFloat() * (float)Math.toRadians(40.0D);
                float cosY = Mth.cos(angleY);
                float sinY = Mth.sin(angleY);
                int length = 4 + random.nextInt(6);
                BlockPos.MutableBlockPos branchPos = (new BlockPos.MutableBlockPos()).setWithOffset(startPos, 0, y, 0);
                Direction.Axis branchAxis = Direction.fromYRot((deg + 90)).getAxis();

                for (int l = 0; l < length; ++l){
                    if (Math.floor((cos * l)) != Math.floor((cos * (l - 1)))){
                        branchPos.move((int) Math.signum(cos),0, 0);
                    }
                    if (Math.floor((sin * l)) != Math.floor((sin * (l - 1)))){
                        branchPos.move(0, 0, (int) Math.signum(sin));
                    }

                    if (Math.floor((sinY * l)) != Math.floor((sinY * (l - 1)))){
                        branchPos.move(0, (int) Math.signum(sinY), 0);
                    }

                    if (Math.floor((cosY * l)) != Math.floor((cosY * (l - 1)))){
                        branchPos.move(0, (int) Math.signum(cosY), 0);
                    }

                    if (branchPos.getX() != startPos.getX() || branchPos.getY() != startPos.getY() ||
                            branchPos.getZ() > startPos.getZ() + branchMaxHeight){
                        if (!TreeFeature.validTreePos(world, startPos)){
                            break;
                        }

                        this.placeWood(world, random, branchPos, replacer, config, branchAxis);

                    }
                }

                foliage.add(new FoliagePlacer.FoliageAttachment(branchPos.above(2), random.nextInt(2), false));
            }
        }

        Iterator var25 = Direction.Plane.HORIZONTAL.iterator();

        while (var25.hasNext()) {
            Direction dir = (Direction) var25.next();
            rootUp = random.nextInt(3);
            int rootLength = 3 + rootUp + random.nextInt(3);
            int maxOut = 1;
            if (rootUp >= 2 && random.nextBoolean()){
                ++maxOut;
            }

            BlockPos.MutableBlockPos rootPos = (new BlockPos.MutableBlockPos()).setWithOffset(startPos, dir.getStepX(), rootUp, dir.getStepZ());
            this.growRootsDownAndThanOut(world, random, rootPos, rootLength, dir, maxOut, replacer, config);
        }

        return foliage;
    }
}
