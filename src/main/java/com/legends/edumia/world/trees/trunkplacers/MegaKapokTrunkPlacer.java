package com.legends.edumia.world.trees.trunkplacers;

import com.google.common.collect.Lists;
import com.legends.edumia.world.trees.EdumiaTrunkPlacerTypes;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class MegaKapokTrunkPlacer extends  ExtraGiantTrunkPlacer{
    public static final MapCodec<MegaKapokTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(
            (instance) ->
                    trunkPlacerParts(instance).apply(instance, MegaKapokTrunkPlacer::new));

    public MegaKapokTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return EdumiaTrunkPlacerTypes.MEGA_KAPOK_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader world,
                                                            BiConsumer<BlockPos, BlockState> replacer,
                                                            RandomSource random, int height,
                                                            BlockPos startPos, TreeConfiguration config) {
        height += 28;
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();
        list.addAll(super.placeTrunk(world, replacer, random, height - 28, startPos, config));
        float angle = random.nextFloat() * 6.283185F;
        for (int i = 0; i < 5; i++) {
            float f = angle + 1.570796F * i + random.nextFloat() * 0.392699F - 0.196349F;
            int j = 0;
            int k = 0;
            int y = height - 20 + random.nextInt(4);
            for (int l = 0; l < 12; l++) {
                j = (int)(Mth.cos(f) * l);
                k = (int)(Mth.sin(f) * l);
                BlockPos blockPos = startPos.offset(j, y + (int)(l * 0.75D), k);
                placeLog(world, replacer, random, blockPos, config);
                placeLog(world, replacer, random, blockPos.east(), config);
                placeLog(world, replacer, random, blockPos.west(), config);
                placeLog(world, replacer, random, blockPos.above(), config);
                placeLog(world, replacer, random, blockPos.below(), config);
                placeLog(world, replacer, random, blockPos.north(), config);
                placeLog(world, replacer, random, blockPos.south(), config);
            }
            list.add(new FoliagePlacer.FoliageAttachment(startPos.offset(j, y + 10, k), -12, false));
        }
        return list;
    }
}
