package com.legends.edumia.world.trees.foliageplacer;

import com.legends.edumia.world.trees.EdumiaFoliagePlacerTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import javax.swing.tree.TreeNode;

public class WillowFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<WillowFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            foliagePlacerParts(instance)
                    .apply(instance, WillowFoliagePlacer::new));
    public WillowFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return EdumiaFoliagePlacerTypes.WILLOW_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader world, FoliageSetter leaves, RandomSource random, TreeConfiguration config,
                                 int trunkHeight, FoliageAttachment foliage, int foliageHeight, int radius, int offset) {
        int i = foliage.doubleTrunk() ? foliageHeight : 1 + random.nextInt(2);

        for (int j = offset; j >= offset - i; --j) {
            int k = radius + foliage.radiusOffset() + 1 - j;
            placeLeavesRowWithHangingLeavesBelow(world, leaves, random, config, foliage.pos(), k, j, foliage.doubleTrunk(),
                    0.3F, 0.8F);
        }
    }

    @Override
    public int foliageHeight(RandomSource random, int trunkHeight, TreeConfiguration config) {
        return 2;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        return dx * dx + dz * dz > radius * radius;
    }
}
