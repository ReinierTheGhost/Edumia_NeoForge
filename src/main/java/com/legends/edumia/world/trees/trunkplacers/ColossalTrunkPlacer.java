package com.legends.edumia.world.trees.trunkplacers;

import com.google.common.collect.ImmutableList;
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
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

public class ColossalTrunkPlacer extends ExtendedTrunkPlacer{
    public static final Codec<ColossalTrunkPlacer> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(Codec.intRange(0, 60).fieldOf("base_height").forGetter(placer -> placer.baseHeight),
                            Codec.intRange(0, 24).fieldOf("height_rand_a").forGetter(placer -> placer.heightRandA),
                            Codec.intRange(0, 24).fieldOf("height_rand_b").forGetter(placer -> placer.heightRandB))
                    .apply(instance, ColossalTrunkPlacer::new));
    protected final int baseHeight;
    protected final int heightRandA;
    protected final int heightRandB;
    public ColossalTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB, Optional.empty(), Optional.empty(), Optional.empty());
        this.baseHeight = baseHeight;
        this.heightRandA = heightRandA;
        this.heightRandB = heightRandB;
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return EdumiaTrunkPlacerTypes.COLOSSAL_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> trunk,
                                                            RandomSource random, int height, BlockPos startPos, TreeConfiguration config) {
        for (int i = 1; i <= 2; i++) {
            BlockPos dirtPos = startPos.relative(Direction.DOWN, i);
            setDirtAt(world, trunk, random, dirtPos, config);
            setDirtAt(world, trunk, random, dirtPos.east(), config);
            setDirtAt(world, trunk, random, dirtPos.east().east(), config);
            setDirtAt(world, trunk, random, dirtPos.south(), config);
            setDirtAt(world, trunk, random, dirtPos.south().east(), config);
            setDirtAt(world, trunk, random, dirtPos.south().east().east(), config);
            setDirtAt(world, trunk, random, dirtPos.south().south(), config);
            setDirtAt(world, trunk, random, dirtPos.south().south().east(), config);
            setDirtAt(world, trunk, random, dirtPos.south().south().east().east(), config);
            setDirtAt(world, trunk, random, dirtPos.south().west(), config);
            setDirtAt(world, trunk, random, dirtPos.north().east(), config);
            setDirtAt(world, trunk, random, dirtPos.south().south().south().east(), config);
            setDirtAt(world, trunk, random, dirtPos.south().east().east().east(), config);
        }
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();

        int root = 3 + random.nextInt(5);
        int base = root + 4 + random.nextInt(5);
        int top = 3 + random.nextInt(3);
        for (int i = 0; i < height; ++i) {
            if (i < root) {
                placeLogIfFreeWithOffset(world, trunk, random, mutableBlockPos, config, startPos, 1, i, -1);
                placeLogIfFreeWithOffset(world, trunk, random, mutableBlockPos, config, startPos, -1, i, 1);
                placeLogIfFreeWithOffset(world, trunk, random, mutableBlockPos, config, startPos, 1, i, 3);
                placeLogIfFreeWithOffset(world, trunk, random, mutableBlockPos, config, startPos, 3, i, 1);
            }
            if (i < base) {
                placeLogIfFreeWithOffset(world, trunk, random, mutableBlockPos, config, startPos, 0, i, 0);
                placeLogIfFreeWithOffset(world, trunk, random, mutableBlockPos, config, startPos, 2, i, 0);
                placeLogIfFreeWithOffset(world, trunk, random, mutableBlockPos, config, startPos, 0, i, 2);
                placeLogIfFreeWithOffset(world, trunk, random, mutableBlockPos, config, startPos, 2, i, 2);
            }
            if (i < height - top) {
                placeLogIfFreeWithOffset(world, trunk, random, mutableBlockPos, config, startPos, 1, i, 0);
                placeLogIfFreeWithOffset(world, trunk, random, mutableBlockPos, config, startPos, 0, i, 1);
                placeLogIfFreeWithOffset(world, trunk, random, mutableBlockPos, config, startPos, 2, i, 1);
                placeLogIfFreeWithOffset(world, trunk, random, mutableBlockPos, config, startPos, 1, i, 2);
            }
            placeLogIfFreeWithOffset(world, trunk, random, mutableBlockPos, config, startPos, 1, i, 1);
        }

        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(startPos.above(height).south().east(), 0, false));
    }

    private void placeLogIfFreeWithOffset(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> trunk, RandomSource random,
                                          BlockPos.MutableBlockPos startPos, TreeConfiguration config, BlockPos origin, int x, int y, int z) {
        startPos.setWithOffset(origin, x, y, z);
        this.placeLogIfFree(world, trunk, random, startPos, config);
    }
}
