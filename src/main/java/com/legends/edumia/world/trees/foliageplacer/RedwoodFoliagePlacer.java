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

public class RedwoodFoliagePlacer extends FoliagePlacer {
    public static final Codec<RedwoodFoliagePlacer> CODEC = RecordCodecBuilder.create(instance -> foliagePlacerParts(instance).and(
                    instance.group(IntProvider.codec(0, 15).fieldOf("mid_segments").forGetter(placer -> placer.midSegments),
                            Codec.intRange(1, 3).fieldOf("trunk_width").forGetter(placer -> placer.trunkWidth)))
            .apply(instance, RedwoodFoliagePlacer::new));
    private final IntProvider midSegments;
    private final int trunkWidth;

    public RedwoodFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider midStages, int trunkWidth) {
        super(radius, offset);
        this.midSegments = midStages;
        this.trunkWidth = trunkWidth;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return EdumiaFoliagePlacerTypes.REDWOOD_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader world, FoliageSetter leaves, RandomSource random, TreeConfiguration config, int trunkHeight,
                                 FoliageAttachment foliage, int foliageHeight, int radius, int offset) {
        BlockPos pos = foliage.pos();

        if (trunkWidth == 1) createNormal(world, leaves, random, config, pos);
        if (trunkWidth == 2) createMega(world, leaves, random, config, pos);
        if (trunkWidth == 3) createUltra(world, leaves, random, config, pos);
    }

    private void createNormal(LevelSimulatedReader world, FoliageSetter leaves, RandomSource random, TreeConfiguration config, BlockPos pos) {
        int y = -4;

        placeLeavesRow(world, leaves, random, config, pos, 0, 1, false);
        placeLeavesRow(world, leaves, random, config, pos, 0, 0, false);
        placeLeavesRow(world, leaves, random, config, pos, 1, -1, false);
        placeLeavesRow(world, leaves, random, config, pos, 1, -2, false);

        for (int s = 1; s <= midSegments.sample(random); s++) {
            placeLeavesRow(world, leaves, random, config, pos, 1, y, false);
            placeLeavesRow(world, leaves, random, config, pos, 2, y - 1, false);
            y -= 3;
        }

        placeLeavesRow(world, leaves, random, config, pos, 1, y, false);
    }

    private void createMega(LevelSimulatedReader world, FoliageSetter leaves, RandomSource random, TreeConfiguration config, BlockPos pos) {
        int y = -6;

        placeLeavesRow(world, leaves, random, config, pos, 0, 1, true);
        placeLeavesRow(world, leaves, random, config, pos, 0, 0, true);
        placeLeavesRow(world, leaves, random, config, pos, 1, -1, true);
        placeLeavesRow(world, leaves, random, config, pos, 1, -2, true);
        placeLeavesRow(world, leaves, random, config, pos, 2, -3, true);
        placeLeavesRow(world, leaves, random, config, pos, 1, -4, true);

        for (int s = 1; s <= midSegments.sample(random); s++) {
            placeLeavesRow(world, leaves, random, config, pos, 2, y, true);
            placeLeavesRow(world, leaves, random, config, pos, 3, y - 1, true);
            placeLeavesRow(world, leaves, random, config, pos, 1, y - 2, true);
            y -= 4;
        }

        placeLeavesRow(world, leaves, random, config, pos, 1, y, true);
        placeLeavesRow(world, leaves, random, config, pos, 2, y - 1, true);
    }

    private void createUltra(LevelSimulatedReader world, FoliageSetter leaves, RandomSource random, TreeConfiguration config, BlockPos pos) {
        boolean variant = random.nextBoolean();
        int y = -10;

        placeLeavesRow(world, leaves, random, config, pos, 0, 2, false);
        placeLeavesRow(world, leaves, random, config, pos, 0, 1, false);
        placeLeavesRow(world, leaves, random, config, pos, 1, 0, false);
        placeLeavesRow(world, leaves, random, config, pos, 1, -1, false);
        placeLeavesRow(world, leaves, random, config, pos, 1, -2, false);
        placeLeavesRow(world, leaves, random, config, pos, 2, -3, false);
        placeLeavesRow(world, leaves, random, config, pos, 2, -4, false);
        placeLeavesRow(world, leaves, random, config, pos, variant ? 2 : 3, -5, false);
        placeLeavesRow(world, leaves, random, config, pos, variant ? 3 : 2, -6, false);
        placeLeavesRow(world, leaves, random, config, pos, variant ? 3 : 2, -7, false);
        placeLeavesRow(world, leaves, random, config, pos, variant ? 2 : 3, -8, false);

        for (int s = 1; s <= midSegments.sample(random); s++) {
            int i = random.nextInt(2);
            placeLeavesRow(world, leaves, random, config, pos, i + 1, y, false);
            placeLeavesRow(world, leaves, random, config, pos, i + 3, y - 1, false);
            placeLeavesRow(world, leaves, random, config, pos, i + 4, y - 2, false);
            placeLeavesRow(world, leaves, random, config, pos, i + 2 + random.nextInt(2), y - 3, false);
            y -= 5;
        }

        placeLeavesRow(world, leaves, random, config, pos, 3, y, false);
        placeLeavesRow(world, leaves, random, config, pos, 4, y - 1, false);
        placeLeavesRow(world, leaves, random, config, pos, 2, y - 2, false);

        placeLeavesRow(world, leaves, random, config, pos, 2, y - 4, false);
        placeLeavesRow(world, leaves, random, config, pos, 3, y - 5, false);
    }

    @Override
    public int foliageHeight(RandomSource random, int trunkHeight, TreeConfiguration config) {
        return 2;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int x, int y, int z, int range, boolean large) {
        return x * x + z * z > range * range;
    }
}
