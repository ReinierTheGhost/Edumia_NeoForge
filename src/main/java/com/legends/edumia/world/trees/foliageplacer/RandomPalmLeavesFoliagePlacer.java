package com.legends.edumia.world.trees.foliageplacer;

import com.legends.edumia.world.trees.EdumiaFoliagePlacerTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import javax.swing.tree.TreeNode;

public class RandomPalmLeavesFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<RandomPalmLeavesFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec((placer) ->
            foliagePlacerParts(placer).apply(placer, RandomPalmLeavesFoliagePlacer::new));
    public RandomPalmLeavesFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    public RandomPalmLeavesFoliagePlacer() {
        this(ConstantInt.of(0), ConstantInt.of(0));
    }
    @Override
    protected FoliagePlacerType<?> type() {
        return EdumiaFoliagePlacerTypes.RANDOM_PALM_LEAVES_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader world, FoliageSetter leaves, RandomSource random, TreeConfiguration config, int trunkHeight,
                                 FoliageAttachment foliage, int foliageHeight, int radius, int offset){
        BlockPos leavePos = foliage.pos();

        int leafAngle = 0;
        while (leafAngle < 360) {
            leafAngle += 15 + random.nextInt(15);
            float angleR = (float)Math.toRadians(leafAngle);
            float sin = Mth.sin(angleR);
            float cos = Mth.cos(angleR);
            float angleY = random.nextFloat() * (float)Math.toRadians(30.0D);
            float cosY = Mth.cos(angleY);
            float sinY = Mth.sin(angleY);
            int i1 = foliage.pos().getX();
            int j1 = trunkHeight - 1;
            int k1 = foliage.pos().getZ();
            int jStart = j1;
            int branchLength = 5;
            for (int n = 1; n <= branchLength; ) {
                if (Math.floor((sinY * n)) != Math.floor((sinY * (n - 1)))) {
                    j1 = (int)(j1 + Math.signum(sinY));
                }else {
                    double dCos = Math.floor(Math.abs(cos * n)) - Math.floor(Math.abs(cos * (n - 1)));
                    double dSin = Math.floor(Math.abs(sin * n)) - Math.floor(Math.abs(sin * (n - 1)));
                    dCos = Math.abs(dCos);
                    dSin = Math.abs(dSin);
                    boolean cosOrSin = (dCos == dSin) ? random.nextBoolean() : ((dCos > dSin));
                    if (cosOrSin) {
                        i1 = (int)(i1 + Math.signum(cos));
                    }else {
                        k1 = (int)(k1 + Math.signum(sin));
                    }
                }
                generateLeaf(world, leaves, leavePos.offset(i1, j1, k1), random, config);

                if (n >= 5)
                    break;
                n++;
            }
        }
    }

    @Override
    public int foliageHeight(RandomSource random, int trunkHeight, TreeConfiguration config) {
        return 0;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        return false;
    }

    private void generateLeaf(LevelSimulatedReader seedReader, FoliageSetter leaves, BlockPos pos, RandomSource random, TreeConfiguration config) {
        if (TreeFeature.isAirOrLeaves(seedReader, pos)) {
            leaves.set(pos, config.foliageProvider.getState(random, pos));
        }
    }
}
