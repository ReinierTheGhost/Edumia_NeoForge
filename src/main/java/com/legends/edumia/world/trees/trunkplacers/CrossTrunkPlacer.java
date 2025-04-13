package com.legends.edumia.world.trees.trunkplacers;

import com.google.common.collect.Lists;
import com.legends.edumia.world.trees.EdumiaTrunkPlacerTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.OptionalInt;
import java.util.function.BiConsumer;

public class CrossTrunkPlacer extends TrunkPlacer
{
    public static final MapCodec<CrossTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            trunkPlacerParts(instance).apply(instance, CrossTrunkPlacer::new));



    public CrossTrunkPlacer(int baseHeight, int heightRandA, int heightRandB)
    {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type()
    {
        return EdumiaTrunkPlacerTypes.CROSS_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> trunk,
                                                            RandomSource random, int height, BlockPos startPos, TreeConfiguration config){
        setDirtAt(world, trunk, random, startPos.below(), config);
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
        int i = height - random.nextInt(6) - 1;
        int j = 6 - random.nextInt(6);
        int k = startPos.getX();
        int l = startPos.getZ();
        int m = startPos.getX();
        int n = startPos.getZ();
        int p = 6 - random.nextInt(3);
        OptionalInt optionalInt = OptionalInt.empty();
        OptionalInt optionalInt1 = OptionalInt.empty();
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        Direction direction1 = random.nextBoolean() ? direction.getCounterClockWise() : direction.getClockWise();

        for(int i1 = 0; i1 < height; ++i1) {
            int j1 = startPos.getY() + i1;
            int k1 = j1 - p;
            if (i1 >= i && j > 0) {
                k += direction.getStepX();
                l += direction.getStepZ();
                m -= direction.getStepX();
                n -= direction.getStepZ();
                --j;
            }
            if (placeLog(world, trunk, random, mutableBlockPos.set(k, j1, l), config)) {
                optionalInt = OptionalInt.of(j1 + 1);
            }
            if (i1 > p) {
                if (placeLog(world, trunk, random, mutableBlockPos.set(m, k1, n), config)) {
                    optionalInt1 = OptionalInt.of(k1 + 1);
                }
            }
        }

        if (optionalInt.isPresent()) {
            list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(k, optionalInt.getAsInt(), l), 1, false));
        }
        if (optionalInt1.isPresent()) {
            list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(m, optionalInt1.getAsInt(), n), 1, false));
        }

        k = startPos.getX();
        l = startPos.getZ();
        m = startPos.getX();
        n = startPos.getZ();
        p = 6 - random.nextInt(3);

        int j2 = i - random.nextInt(2) - 1;
        int k2 = 1 + random.nextInt(3);
        optionalInt = OptionalInt.empty();
        optionalInt1 = OptionalInt.empty();

        for(int l1 = j2; l1 < height && k2 > 0; --k2) {
            if (l1 >= 1) {
                int i2 = startPos.getY() + l1;
                int l2 = i2 - p;
                k += direction1.getStepX();
                l += direction1.getStepZ();
                m -= direction1.getStepX();
                n -= direction1.getStepZ();
                if (placeLog(world, trunk, random, mutableBlockPos.set(k, i2, l), config)) {
                    optionalInt = OptionalInt.of(i2 + 1);
                }
                if (l1 > p) {
                    if (placeLog(world, trunk, random, mutableBlockPos.set(m, l2, n), config)) {
                        optionalInt1 = OptionalInt.of(l2 + 1);
                    }
                }
            }

            ++l1;
        }

        if (optionalInt.isPresent()) {
            list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(k, optionalInt.getAsInt(), l), 0, false));
        }
        if (optionalInt1.isPresent()) {
            list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(m, optionalInt1.getAsInt(), n), 0, false));
        }

        return list;
    }
}
