package com.legends.edumia.world.trees.foliageplacer;

import com.legends.edumia.world.trees.EdumiaFoliagePlacerTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import javax.swing.tree.TreeNode;

public class PointedFoliagePlacer extends FoliagePlacer {
    public static final Codec<PointedFoliagePlacer> CODEC = RecordCodecBuilder.create(instance ->
            foliagePlacerParts(instance).and(
                    Codec.BOOL.fieldOf("tall").forGetter(placer -> placer.tall))
            .apply(instance, PointedFoliagePlacer::new));
    private final boolean tall;

    public PointedFoliagePlacer(IntProvider radius, IntProvider offset, boolean tall) {
        super(radius, offset);
        this.tall = tall;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return EdumiaFoliagePlacerTypes.POINTED_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader world, FoliageSetter leaves, RandomSource random, TreeConfiguration config, int trunkHeight,
                                 FoliageAttachment foliage, int foliageHeight, int radius, int offset) {
        BlockPos pos = foliage.pos();

        if (tall) {
            placeLeavesRow(world, leaves, random, config, pos, 0, 3, false);
            placeLeavesRow(world, leaves, random, config, pos, 0, 2, false);
            placeLeavesRow(world, leaves, random, config, pos, 0, 1, false);
            placeLeavesRow(world, leaves, random, config, pos, 1, 0, false);
            placeLeavesRow(world, leaves, random, config, pos, 1, -1, false);
            placeLeavesRow(world, leaves, random, config, pos, 1, -2, false);
            placeLeavesRow(world, leaves, random, config, pos, 1, -3, false);
            placeLeavesRow(world, leaves, random, config, pos, 2, -4, false);
            placeLeavesRow(world, leaves, random, config, pos, 2, -5, false);
            placeLeavesRow(world, leaves, random, config, pos, 2, -6, false);
            placeLeavesRow(world, leaves, random, config, pos, 2, -7, false);
            placeLeavesRow(world, leaves, random, config, pos, 1, -8, false);
            placeLeavesRow(world, leaves, random, config, pos, 1, -9, false);
            placeLeavesRow(world, leaves, random, config, pos, 1, -10, false);
        } else {
            if (random.nextBoolean()) placeLeavesRow(world, leaves, random, config, pos, 0, 2, false);
            placeLeavesRow(world, leaves, random, config, pos, 0, 1, false);
            placeLeavesRow(world, leaves, random, config, pos, 1, 0, false);
            placeLeavesRow(world, leaves, random, config, pos, 1, -1, false);
            placeLeavesRow(world, leaves, random, config, pos, 2, -2, false);
            placeLeavesRow(world, leaves, random, config, pos, 2, -3, false);
            placeLeavesRow(world, leaves, random, config, pos, 2, -4, false);
            placeLeavesRow(world, leaves, random, config, pos, 1, -5, false);
        }
    }

    @Override
    public int foliageHeight(RandomSource random, int trunkHeight, TreeConfiguration config) {
        return tall ? 4 : 2;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        if (tall && (y == -3 || y == -8)) return false;
        return dx * dx + dz * dz > radius * radius;
    }
}
