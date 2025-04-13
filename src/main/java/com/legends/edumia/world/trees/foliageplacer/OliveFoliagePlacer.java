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

public class OliveFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<OliveFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            foliagePlacerParts(instance).apply(instance, OliveFoliagePlacer::new));
    public OliveFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return EdumiaFoliagePlacerTypes.OLIVE_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader world, FoliageSetter leaves, RandomSource random, TreeConfiguration config, int trunkHeight,
                                 FoliageAttachment foliage, int foliageHeight, int radius, int offset) {
        int leafStart = foliageHeight - (2 + random.nextInt(2));
        int leafTop = foliageHeight;

        for (int m = leafStart; m <= leafTop; m++) {
            int leafRange = 0;
            if (m == leafTop) {
                leafRange = 2;
            } else if (m == leafStart) {
                leafRange = 1;
            } else {
                leafRange = 3;
            }

            this.placeLeavesRow(world, leaves, random, config, foliage.pos(), leafRange, m, foliage.doubleTrunk());
        }
    }

    @Override
    public int foliageHeight(RandomSource random, int trunkHeight, TreeConfiguration config) {
        return 0;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        int dch = dx + dz;
        int cornerDCh = radius * 2;
       return random.nextInt(3) == 0 && (radius == dx || radius == dz) ||
               dch >= cornerDCh - 1 && (dx == 3 || dz == 3 || dx == -3 || dz == -3);
    }
}
