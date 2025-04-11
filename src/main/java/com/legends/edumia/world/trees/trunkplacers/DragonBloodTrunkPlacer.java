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

public class DragonBloodTrunkPlacer extends ExtendedTrunkPlacer{
    public static final Codec<DragonBloodTrunkPlacer> CODEC = RecordCodecBuilder.create((instance) ->
            baseCodecWithWood(instance).apply(instance, DragonBloodTrunkPlacer::new));
    public DragonBloodTrunkPlacer(int baseHeight, int heightRandA, int heightRandB, Optional<BlockStateProvider> woodProvider,
                                  Optional<BlockStateProvider> strippedLogProvider, Optional<BlockStateProvider> branchProvider) {
        super(baseHeight, heightRandA, heightRandB, woodProvider, strippedLogProvider, branchProvider);
    }

    public DragonBloodTrunkPlacer(int baseHeight, int heightRandA, int heightRandB, BlockState wood, BlockState roots){
        this(baseHeight, heightRandA, heightRandB, Optional.of(BlockStateProvider.simple(wood)), Optional.empty(),
                Optional.of(BlockStateProvider.simple(roots)));
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return EdumiaTrunkPlacerTypes.DRAGON_BLOOD_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> trunk,
                                                            RandomSource random, int height, BlockPos startPos, TreeConfiguration config) {
        setDirtAt(world, trunk, random, startPos.below(), config);

        List<FoliagePlacer.FoliageAttachment> foliage = new ArrayList<>();



        for (int i = 0; i <= height; i++){
            placeLog(world, trunk, random, startPos.above(i), config);
        }
        addLeafCanopy(world, random, startPos.above(height - 1), trunk, foliage, config);

        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                if (Math.abs(x) != Math.abs(z)) {
                    BlockPos.MutableBlockPos rootPos = (new BlockPos.MutableBlockPos()).setWithOffset(startPos, x,
                            random.nextInt(2), z);
                    int rootLength = 4 + random.nextInt(3);
                    this.growRootsDown(world, random, rootPos, rootLength, trunk, config);
                }
            }
        }

        return foliage;
    }

    private void addLeafCanopy(LevelSimulatedReader world, RandomSource rand, BlockPos pos, BiConsumer<BlockPos, BlockState> trunk,
                               List<FoliagePlacer.FoliageAttachment> foliage, TreeConfiguration config) {
        int leafStart = 2;
        int leafTop = leafStart + 2;
        int baseMaxRange = 3;
        int addMaxRange = rand.nextInt(2);
        int maxRange = baseMaxRange + addMaxRange;
        foliage.add(new FoliagePlacer.FoliageAttachment(pos.above(leafTop), addMaxRange, false));
        List<BlockPos> woodPositions = new ArrayList<>();
        for (int l = 0; l <= leafStart; l++) {
            BlockPos layerPos = pos.above(l);
            woodPositions.add(layerPos);
            addLateralOffsets(woodPositions, layerPos, l + 1);
            if (l > 0) {
                addDiagonalOffsets(woodPositions, layerPos, l);
                if (l == leafStart && addMaxRange > 0) {
                    addLateralOffsets(woodPositions, layerPos, l + 1);
                }
            }
        }
        woodPositions.forEach(woodPos -> placeWood(world, rand, woodPos, trunk, config, Direction.Axis.Y));
    }

    private static void addLateralOffsets(List<BlockPos> list, BlockPos midPos, int offset) {
        Direction.Plane.HORIZONTAL.forEach(dir -> list.add(midPos.relative(dir, offset)));
    }
    private static void addDiagonalOffsets(List<BlockPos> list, BlockPos midPos, int offset) {
        list.add(midPos.offset(-offset, 0, -offset));
        list.add(midPos.offset(-offset, 0, offset));
        list.add(midPos.offset(offset, 0, -offset));
        list.add(midPos.offset(offset, 0, offset));
    }
}
