package com.legends.edumia.world.trees.foliageplacer;

import com.legends.edumia.world.trees.EdumiaFoliagePlacerTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import javax.swing.tree.TreeNode;

public class AspenFoliagePlacer extends FoliagePlacer {

    public static final Codec<AspenFoliagePlacer> CODEC = RecordCodecBuilder.create(instance ->
            foliagePlacerParts(instance).and(IntProvider.codec(0, 16).fieldOf("trunk_height")
                    .forGetter((foliage) -> foliage.trunkHeightSpread
                    )).apply(instance, AspenFoliagePlacer::new));

    private final IntProvider trunkHeightSpread;
    public AspenFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider trunkHeightSpread) {
        super(radius, offset);
        this.trunkHeightSpread = trunkHeightSpread;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return EdumiaFoliagePlacerTypes.ASPEN_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader world, FoliageSetter leaves, RandomSource random, TreeConfiguration config, int trunkHeight,
                                 FoliageAttachment foliage, int foliageHeight, int radius, int offset) {
        int leafTop = offset;
        int leafBottom = offset - foliageHeight;

        for (int y = offset; y >= leafBottom; --y){
            int baseLayerWidth = radius;
            if (y >= leafTop - 1){
                baseLayerWidth = radius - 2;
            } else if (y >= leafTop - 3 || y <= leafBottom + 1 || random.nextInt(4) == 0) {
                baseLayerWidth = radius - 1;
            }

            int layerWidth = baseLayerWidth + foliage.radiusOffset();
            int branches = 4 + random.nextInt(5);

            for (int b = 0; b < branches; ++b){
                BlockPos.MutableBlockPos movingPos = (new BlockPos.MutableBlockPos()).setWithOffset(foliage.pos(), 0, y, 0);
                int origX = movingPos.getX();
                int origZ = movingPos.getZ();
                int length = 4 + random.nextInt(8);

                for (int l = 0; l < length && Math.abs(origX - movingPos.getX()) <= layerWidth && Math.abs(origZ - movingPos.getZ()) <= layerWidth; ++l){
                    this.doPlaceLeafBlock((LevelAccessor) world, random, config, movingPos, leaves);
                    Direction randDir = Direction.Plane.HORIZONTAL.getRandomDirection(random);
                    movingPos.move(randDir);
                }

            }
        }

    }

    private void doPlaceLeafBlock(LevelAccessor world, RandomSource random, TreeConfiguration config, BlockPos.MutableBlockPos movingPos,
                                  FoliageSetter leaves){
        if (TreeFeature.validTreePos(world, movingPos)){
            world.setBlock(movingPos, config.foliageProvider.getState(random, movingPos), 19);
            leaves.isSet(movingPos.immutable());
        }
    }

    @Override
    public int foliageHeight(RandomSource random, int trunkHeight, TreeConfiguration config) {
        return Math.max(4, trunkHeight - trunkHeightSpread.sample(random));
    }

    @Override
    public int foliageRadius(RandomSource random, int baseHeight) {
        return super.foliageRadius(random, baseHeight);
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        return false;
    }

}
