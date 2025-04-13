package com.legends.edumia.world.trees.foliageplacer;

import com.legends.edumia.world.trees.EdumiaFoliagePlacerTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import javax.swing.tree.TreeNode;

public class CajoleFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<CajoleFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            foliagePlacerParts(instance).apply(instance, CajoleFoliagePlacer::new));
    public CajoleFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return EdumiaFoliagePlacerTypes.CAJOLE_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader world, FoliageSetter leaves, RandomSource random, TreeConfiguration config, int trunkHeight,
                                 FoliageAttachment foliage, int foliageHeight, int radius, int offset) {
        boolean flag = foliage.doubleTrunk();
        BlockPos pos = foliage.pos().above(offset);

        placeLeavesRow(world, leaves, random, config, pos, 0, -2, flag);
        placeLeavesRow(world, leaves, random, config, pos, radius + foliage.radiusOffset() - 1, -1, flag);
        placeLeavesRow(world, leaves, random, config, pos, radius - 1, 0, flag);
        placeLeavesRow(world, leaves, random, config, pos, radius + foliage.radiusOffset() - 1, 0, flag);
        placeLeavesRow(world, leaves, random, config, pos, 0, 1, flag);
    }

    @Override
    public int foliageHeight(RandomSource random, int trunkHeight, TreeConfiguration config) {
        return 0;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        if (y == 0) return (dx > 1 || dz > 1) && dx != 0 && dz != 0;
        return dx == radius && dz == radius && radius > 0;
    }
}
