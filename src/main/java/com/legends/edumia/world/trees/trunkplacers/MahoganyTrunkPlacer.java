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
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

public class MahoganyTrunkPlacer extends ExtendedTrunkPlacer {
    public static final Codec<MahoganyTrunkPlacer> CODEC = RecordCodecBuilder.create((instance) ->
            baseCodecWithWood(instance).apply(instance, MahoganyTrunkPlacer::new));
    protected MahoganyTrunkPlacer(int baseHeight, int heightRandA, int heightRandB,
                               Optional<BlockStateProvider> woodProvider,
                               Optional<BlockStateProvider> strippedLogProvider, Optional<BlockStateProvider> branchProvider) {
        super(baseHeight, heightRandA, heightRandB, woodProvider, strippedLogProvider, branchProvider);
    }

    public MahoganyTrunkPlacer(int baseHeight, int heightRandA, int heightRandB, BlockState wood, BlockState roots){
        this(baseHeight, heightRandA, heightRandB, Optional.of(BlockStateProvider.simple(wood)), Optional.empty(),
                Optional.of(BlockStateProvider.simple(roots)));
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return EdumiaTrunkPlacerTypes.MAHOGANY_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> trunk,
                                                            RandomSource random, int height, BlockPos startPos, TreeConfiguration config) {
        setDirtAt(world, trunk, random, startPos.below(), config);

        for (int y = 0; y < height; ++y){
            placeLog(world, trunk, random, startPos.above(y), config); //builds the trunk
        }

        List<FoliagePlacer.FoliageAttachment> foliage = new ArrayList<>();
        foliage.add(new FoliagePlacer.FoliageAttachment(startPos.above(height), 0, false)); // places the leaves on top of the trunk

        int i;

        for (i = height - 1; i > height / 2; i -= 1 + random.nextInt(3)){
            int branches = 1 + random.nextInt(3);

            for (int b = 0; b < branches; ++b) {
                float angle = random.nextFloat() * 3.1415927F * 2.0F;
                int length = Mth.nextInt(random, 4, 7);
                int leafLayerLessWidth = 1;
                BlockPos.MutableBlockPos branchPos = new BlockPos.MutableBlockPos();

                for (int l = 0; l < length; ++l){
                    int branchX = Math.round(0.5F + Mth.cos(angle) * (l + 1));
                    int branchZ = Math.round(0.5F + Mth.sin(angle) * (l + 1));
                    int branchY = i - 3 + l / 2;
                    BlockPos prevBranchPos = branchPos.immutable();
                    branchPos.setWithOffset(startPos, branchX, branchY, branchZ);
                    if (!branchPos.equals(prevBranchPos)){
                        placeWood(world, random, branchPos, trunk, config, Direction.Axis.Y);

                    }

                }
                foliage.add(new FoliagePlacer.FoliageAttachment(branchPos.above(), -leafLayerLessWidth, false));
            }

        }
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                if (Math.abs(x) != Math.abs(z)) {
                    BlockPos.MutableBlockPos rootPos = (new BlockPos.MutableBlockPos()).setWithOffset(startPos, x, random.nextInt(2), z);
                    int rootLength = 4 + random.nextInt(3);
                    this.growRootsDown(world, random, rootPos, rootLength, trunk, config);
                }
            }
        }

        return foliage;
    }
}
