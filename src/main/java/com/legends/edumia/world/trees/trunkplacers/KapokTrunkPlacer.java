package com.legends.edumia.world.trees.trunkplacers;

import com.legends.edumia.world.trees.EdumiaTrunkPlacerTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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

public class KapokTrunkPlacer  extends TrunkPlacer {
    // --- nested specs to keep the top-level codec small ---
    public static record BranchSpec(float startFrac, int baseCount, int per10, IntProvider length) {
        public static final MapCodec<BranchSpec> CODEC = RecordCodecBuilder.mapCodec(i ->
                i.group(
                        Codec.floatRange(0f, 1f).fieldOf("start_frac").forGetter(BranchSpec::startFrac),
                        Codec.INT.fieldOf("base_count").forGetter(BranchSpec::baseCount),
                        Codec.INT.fieldOf("per10").forGetter(BranchSpec::per10),
                        IntProvider.CODEC.fieldOf("length").forGetter(BranchSpec::length)
                ).apply(i, BranchSpec::new)
        );
    }

    public static record ButtressSpec(float rPer10, float hPer10) {
        public static final MapCodec<ButtressSpec> CODEC = RecordCodecBuilder.mapCodec(i ->
                i.group(
                        Codec.FLOAT.fieldOf("r_per10").forGetter(ButtressSpec::rPer10),
                        Codec.FLOAT.fieldOf("h_per10").forGetter(ButtressSpec::hPer10)
                ).apply(i, ButtressSpec::new)
        );
    }

    public static final MapCodec<KapokTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(i ->
            trunkPlacerParts(i) // (baseHeight, randA, randB)
                    .and(IntProvider.CODEC.fieldOf("core_size").forGetter(p -> p.coreSize))
                    .and(BranchSpec.CODEC.fieldOf("branches").forGetter(p -> p.branches))
                    .and(ButtressSpec.CODEC.fieldOf("buttress").forGetter(p -> p.buttress))
                    .apply(i, KapokTrunkPlacer::new)
    );

    private final IntProvider coreSize;
    private final BranchSpec branches;
    private final ButtressSpec buttress;

    public KapokTrunkPlacer(int baseHeight, int randA, int randB,
                                    IntProvider coreSize,
                                    BranchSpec branches,
                                    ButtressSpec buttress) {
        super(baseHeight, randA, randB);
        this.coreSize = coreSize;
        this.branches = branches;
        this.buttress  = buttress;
    }

    @Override public TrunkPlacerType<?> type() { return EdumiaTrunkPlacerTypes.KAPOK.get(); }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level,
                                                            BiConsumer<BlockPos, BlockState> setter, RandomSource rand, int height,
                                                            BlockPos start, TreeConfiguration cfg) {

        List<FoliagePlacer.FoliageAttachment> foliage = new ArrayList<>();

        // straight core (cs × cs)
        int cs = Math.max(1, Math.min(3, coreSize.sample(rand)));
        for (int y = 0; y < height; y++)
            for (int dx = 0; dx < cs; dx++)
                for (int dz = 0; dz < cs; dz++)
                    placeLog(level, setter, rand, start.offset(dx, y, dz), cfg);

        // buttress fins scale with height
        int r = Math.max(1, Math.round((height / 10f) * buttress.rPer10()));
        int h = Math.max(1, Math.round((height / 10f) * buttress.hPer10()));
        makeButtress(level, setter, rand, start, cfg, cs, r, h);

        // branches near the top, count scales with height
        int startY = Math.max(2, Math.round(height * branches.startFrac()));
        int count  = Math.max(0, branches.baseCount() + (height / 10) * Math.max(0, branches.per10()));
        Direction[] dirs = Direction.Plane.HORIZONTAL.stream().toArray(Direction[]::new);

        for (int i = 0; i < count; i++) {
            Direction dir = dirs[rand.nextInt(dirs.length)];
            int len = Math.max(3, branches.length().sample(rand));
            int y = startY + rand.nextInt(Math.max(1, height - startY - 2));
            BlockPos.MutableBlockPos b = start.offset(cs / 2, y, cs / 2).mutable();
            for (int step = 0; step < len; step++) {
                b.move(dir);
                if (step > 0 && step % 3 == 0 && rand.nextBoolean()) b.move(Direction.UP);
                placeLog(level, setter, rand, b, cfg);
            }
            foliage.add(new FoliagePlacer.FoliageAttachment(b, 0, false));
        }

        // crown center
        foliage.add(new FoliagePlacer.FoliageAttachment(start.above(height - 1).offset(cs / 2, 0, cs / 2), 0, false));
        return foliage;
    }

    private void makeButtress(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> set,
                              RandomSource rand, BlockPos base, TreeConfiguration cfg,
                              int cs, int radius, int height) {
        Direction[] around = Direction.Plane.HORIZONTAL.stream().toArray(Direction[]::new);
        int fins = 4 + rand.nextInt(3);
        for (int i = 0; i < fins; i++) {
            Direction dir = around[(i * around.length) / fins];
            BlockPos.MutableBlockPos p = base.mutable().move(dir, cs);
            for (int step = 1; step <= radius; step++) {
                int colH = Math.max(1, height - (step - 1));
                for (int y = 0; y < colH; y++) set.accept(p.offset(0, y, 0), cfg.trunkProvider.getState(rand, p));
                p.move(dir);
            }
        }
    }
}
