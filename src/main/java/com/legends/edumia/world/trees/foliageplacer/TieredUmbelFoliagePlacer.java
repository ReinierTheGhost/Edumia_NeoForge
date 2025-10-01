package com.legends.edumia.world.trees.foliageplacer;

import com.legends.edumia.world.trees.EdumiaFoliagePlacerTypes;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class TieredUmbelFoliagePlacer  extends FoliagePlacer {
    public static final MapCodec<TieredUmbelFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec((instance) ->
            foliagePlacerParts(instance).and(
                    IntProvider.CODEC.fieldOf("tiers").forGetter((foliage) -> foliage.tiers)
//            ).and(
//                    IntProvider.CODEC.fieldOf("radius").forGetter((foliage) -> foliage.radius)
            ).apply(instance, TieredUmbelFoliagePlacer::new)
    );

    private final IntProvider tiers;   // 1..3
    private final IntProvider radius;  // base radius

    public TieredUmbelFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider tiers) {
        super(radius, offset);
        this.tiers = tiers;
        this.radius = radius;
    }

    @Override
    public FoliagePlacerType<?> type() {
        return EdumiaFoliagePlacerTypes.TIERED_UMBEL.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader world, FoliageSetter placer, RandomSource random, TreeConfiguration config, int trunkHeight,
                                 FoliageAttachment treeNode, int foliageHeight, int foliageRadius, int offset) {
        int t = Math.max(1, Math.min(3, tiers.sample(random)));
        int baseR = Math.max(2, radius.sample(random));
        BlockPos center = treeNode.pos();

        for (int k = 0; k < t; k++) {
            int r = (int)Math.max(2, baseR - k);       // shrink per tier
            int y = k == 0 ? 0 : -k;                   // steps downward
            BlockPos layer = center.offset(0, y + offset, 0);

            for (int dx = -r; dx <= r; dx++) {
                for (int dz = -r; dz <= r; dz++) {
                    double d = Math.sqrt(dx*dx + dz*dz);
                    if (d <= r + (random.nextFloat() * 0.35)) {
                        tryPlaceLeaf(world, placer, random, config, layer.offset(dx, 0, dz));
                        // frayed rim “drips”
                        if (Math.abs(d - r) < 1.2 && random.nextFloat() < 0.4f)
                            tryPlaceLeaf(world, placer, random, config, layer.offset(dx, -1, dz));
                    }
                }
            }
        }
    }

    @Override
    public int foliageHeight(RandomSource rand, int trunkHeight, TreeConfiguration cfg) {
        return 2;
    }
    @Override
    protected boolean shouldSkipLocation(RandomSource r, int dx, int y, int dz, int radius, boolean giant) {
        return false;
    }
}
