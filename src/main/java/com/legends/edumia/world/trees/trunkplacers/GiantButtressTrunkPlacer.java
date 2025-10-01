package com.legends.edumia.world.trees.trunkplacers;

import com.legends.edumia.world.trees.EdumiaTrunkPlacerTypes;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class GiantButtressTrunkPlacer  extends TrunkPlacer {
    public static final MapCodec<GiantButtressTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            trunkPlacerParts(instance
            ).and(
                    IntProvider.CODEC.fieldOf("buttress_radius").forGetter(p -> p.buttressRadius)
            ).and(
                    IntProvider.CODEC.fieldOf("core_size").forGetter(p -> p.coreSize) // 1..3 (1×1, 2×2, 3×3)
            ).apply(instance, GiantButtressTrunkPlacer::new)
    );

    private final IntProvider buttressRadius;
    private final IntProvider coreSize;

    public GiantButtressTrunkPlacer(int baseHeight, int randA, int randB, IntProvider buttressRadius, IntProvider coreSize) {
        super(baseHeight, randA, randB);
        this.buttressRadius = buttressRadius;
        this.coreSize = coreSize;
    }

    @Override
    public TrunkPlacerType<?> type() {
        return EdumiaTrunkPlacerTypes.GIANT_BUTTRESS.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> setter,
                                                            RandomSource rand, int height, BlockPos start, TreeConfiguration cfg) {
        int cs = Math.max(1, Math.min(3, coreSize.sample(rand)));
        int r = Math.max(2, buttressRadius.sample(rand));

        // 1) buttress flares around the base
        for (int ring = r; ring >= 1; ring--) {
            for (int dx = -ring; dx <= ring; dx++) {
                for (int dz = -ring; dz <= ring; dz++) {
                    int man = Math.abs(dx) + Math.abs(dz);
                    if (man == ring && rand.nextFloat() < (ring == r ? 1.0f : 0.55f)) {
                        placeLog(level, setter, rand, start.offset(dx, 0, dz), cfg);
                        if (rand.nextBoolean()) placeLog(level, setter, rand, start.offset(dx, 1, dz), cfg);
                    }
                }
            }
        }

        // 2) tall straight core (square cs×cs)
        BlockPos.MutableBlockPos p = start.mutable();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < cs; x++)
                for (int z = 0; z < cs; z++)
                    placeLog(level, setter, rand, p.offset(x, y, z), cfg);
        }

        // 3) foliage attachments in a plus around the top rim
        int attachY = height - 1;
        BlockPos top = start.above(attachY).offset(cs / 2, 0, cs / 2);
        List<FoliagePlacer.FoliageAttachment> attachments = new ArrayList<>();
        attachments.add(new FoliagePlacer.FoliageAttachment(top, 0, false));
        int arm = 2 + cs;
        attachments.add(new FoliagePlacer.FoliageAttachment(top.north(arm), 0, false));
        attachments.add(new FoliagePlacer.FoliageAttachment(top.south(arm), 0, false));
        attachments.add(new FoliagePlacer.FoliageAttachment(top.east(arm), 0, false));
        attachments.add(new FoliagePlacer.FoliageAttachment(top.west(arm), 0, false));
        return attachments;
    }
}
