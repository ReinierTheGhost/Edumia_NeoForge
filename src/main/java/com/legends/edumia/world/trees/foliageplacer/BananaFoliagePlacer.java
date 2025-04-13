package com.legends.edumia.world.trees.foliageplacer;

import com.legends.edumia.world.trees.EdumiaFoliagePlacerTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CocoaBlock;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import javax.swing.tree.TreeNode;

public class BananaFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<BananaFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec((instance) ->
            instance.group(Codec.FLOAT.fieldOf("banana_chance").orElse(0.0F).forGetter((fp) ->
                                    fp.bananaChance),
                            Codec.FLOAT.fieldOf("extra_banana_chance").orElse(0.25F).forGetter((fp) ->
                                    fp.extraBananaChance))
                    .apply(instance, BananaFoliagePlacer::new));
    private final float bananaChance;
    private final float extraBananaChance;

    public BananaFoliagePlacer(float bananaChance, float extraBananaChance) {
        super(ConstantInt.of(0), ConstantInt.of(0));
        this.bananaChance = bananaChance;
        this.extraBananaChance = extraBananaChance;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return EdumiaFoliagePlacerTypes.BANANA_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader world, FoliageSetter leaves, RandomSource random, TreeConfiguration config, int trunkHeight,
                                 FoliageAttachment foliage, int foliageHeight, int radius, int offset) {
        BlockPos leafPos = foliage.pos();
            int[] leave = new int[4];
            for (int l = 0; l < 4; l++)
                leave[l] = 1 + random.nextInt(3);
            for (int m = 0; m < 4; m++) {

                Direction dir = Direction.from2DDataValue(m + 2);
                for (int l1 = 0; l1 < leave[m]; l1++){
                    this.generateLeaf(world, leaves, leafPos.offset(dir.getStepX(),-2 + l1, dir.getStepZ()),
                            random, config);
                }
                for (int l1 = -1; l1 < 1; l1++){
                    this.generateLeaf(world, leaves, leafPos.offset( dir.getStepX() * 2, -2 + leave[m] + l1,
                                     dir.getStepZ()  * 2),
                            random, config);

            }

        }



        if (this.bananaChance > 0.0F) {
            BlockPos datePos = leafPos.below().relative(Direction.Plane.HORIZONTAL.getRandomDirection(random));
            if (random.nextDouble() <= this.bananaChance) {
                leaves.set(datePos, Blocks.COCOA.defaultBlockState().setValue(CocoaBlock.AGE, Mth.nextInt(random, 0, 2)));
                if (random.nextDouble() <= this.extraBananaChance) { //Chance for 2nd date
                    datePos = leafPos.above().relative(Direction.Plane.HORIZONTAL.getRandomDirection(random));
                    leaves.set(datePos, Blocks.COCOA.defaultBlockState().setValue(CocoaBlock.AGE, Mth.nextInt(random, 0, 2)));
                }
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
