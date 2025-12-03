package com.legends.edumia.world.trees.trunkplacers;

import com.google.common.collect.ImmutableList;
import com.legends.edumia.world.trees.EdumiaTrunkPlacerTypes;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

public class KapokTrunkPlacer extends ExtendedTrunkPlacer{
    public static final MapCodec<KapokTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec((instance) ->
            baseCodecWithWood(instance).apply(instance, KapokTrunkPlacer::new));

    private KapokTrunkPlacer(int baseHeight, int heightRandA, int heightRandB, Optional<BlockStateProvider> woodProvider, Optional<BlockStateProvider> strippedLogProvider, Optional<BlockStateProvider> branchProvider) {
        super(baseHeight, heightRandA, heightRandB, woodProvider, strippedLogProvider, branchProvider);
    }

    public KapokTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB, Optional.empty(), Optional.empty(), Optional.empty());
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return EdumiaTrunkPlacerTypes.KAPOK.get();
    }

    @Override
    public @NotNull List<FoliagePlacer.FoliageAttachment> placeTrunk(@NotNull LevelSimulatedReader world,
                                                                     @NotNull BiConsumer<BlockPos, BlockState> trunk,
                                                                     @NotNull RandomSource random, int height,
                                                                     @NotNull BlockPos startPos,
                                                                     @NotNull TreeConfiguration config) {
        for (int i = 0; i < height; i++){
            BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
            this.setLog(world, trunk, random, mutable, config, startPos, 1, i, 0);
            if (i >= height - 1) continue; // makes the log on the position of the sapling 1 higher than the rest
            this.setLog(world, trunk, random, mutable, config, startPos, 0, i, 0);
            if (i >= height - 5) continue;
            this.setLog(world, trunk, random, mutable, config, startPos, 0, i, 1);
            if (i >= (height / 2) + 4)
            this.setLog(world, trunk, random, mutable, config, startPos, 1, i, 1);
        }

        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(startPos.above(height), 0, true));
    }

    private void setLog(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> trunk, RandomSource random,
                        BlockPos.MutableBlockPos tmpPos, TreeConfiguration config, BlockPos startPos, int dx, int dy, int dz) {
        tmpPos.setWithOffset(startPos, dx, dy, dz);
        this.placeLogIfFree(world, trunk, random, tmpPos, config);
    }
}
