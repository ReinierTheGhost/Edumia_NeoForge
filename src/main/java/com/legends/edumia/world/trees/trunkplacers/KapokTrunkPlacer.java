package com.legends.edumia.world.trees.trunkplacers;

import com.google.common.collect.ImmutableList;
import com.legends.edumia.world.trees.EdumiaTrunkPlacerTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

public class KapokTrunkPlacer extends ExtendedTrunkPlacer {

    protected final int baseHeight;
    protected final Optional<BlockStateProvider> woodProvider;
    protected final Optional<BlockStateProvider> branchProvider;
    protected final Optional<BlockStateProvider> slabProvider;
    protected final Optional<BlockStateProvider> stairsProvider;
    protected final Optional<BlockStateProvider> fenceProvider;

    public static final MapCodec<KapokTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
        return instance.group(
                Codec.intRange(0,90).fieldOf("base_height").forGetter((trunkPlacer) -> {
                    return trunkPlacer.baseHeight;
                            }),
                BlockStateProvider.CODEC.optionalFieldOf("wood_provider").forGetter((trunk) -> {
                        return trunk.woodProvider;
                }),
                BlockStateProvider.CODEC.optionalFieldOf("branch_provider").forGetter((trunk) -> {
                    return trunk.branchProvider;
                }),
                BlockStateProvider.CODEC.optionalFieldOf("slab_provider").forGetter((trunk) -> {
                    return trunk.slabProvider;
                }),
                BlockStateProvider.CODEC.optionalFieldOf("stairs_provider").forGetter((trunk) -> {
                    return trunk.stairsProvider;
                }),
                BlockStateProvider.CODEC.optionalFieldOf("fence_provider").forGetter((trunk) -> {
                    return trunk.fenceProvider;
                })).apply(instance, KapokTrunkPlacer::new);
    });

    public KapokTrunkPlacer(int baseHeight, Optional<BlockStateProvider> woodProvider, Optional<BlockStateProvider> branchProvider,
                            Optional<BlockStateProvider> slabProvider, Optional<BlockStateProvider> stairsProvider,
                            Optional<BlockStateProvider> fenceProvider) {
        super(baseHeight, 0, 0,
                woodProvider, Optional.empty(), branchProvider);
        this.baseHeight =  baseHeight;
        this.woodProvider = woodProvider;
        this.branchProvider = branchProvider;

        this.slabProvider = slabProvider;
        this.stairsProvider = stairsProvider;
        this.fenceProvider = fenceProvider;
    }

    public KapokTrunkPlacer(int baseHeight, BlockState woodProvider, BlockState branchProvider, BlockState slabProvider, BlockState stairsProvider) {
        this(baseHeight, Optional.of(BlockStateProvider.simple(woodProvider)), Optional.of(BlockStateProvider.simple(branchProvider)),
                Optional.of(BlockStateProvider.simple(slabProvider)), Optional.of(BlockStateProvider.simple(stairsProvider)), Optional.empty());
    }
    @Override
    protected TrunkPlacerType<?> type() {
        return EdumiaTrunkPlacerTypes.KAPOK.get();
    }

    @Override
    public @NotNull List<FoliagePlacer.FoliageAttachment> placeTrunk(@NotNull LevelSimulatedReader world,
                                                                     @NotNull BiConsumer<BlockPos, BlockState> trunk,
                                                                     @NotNull RandomSource random, int height,
                                                                     @NotNull BlockPos startPos,
                                                                     @NotNull TreeConfiguration config) {
        int rot = random.nextInt(4); // 0..3 → 0°, 90°, 180°, 270°
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        List<FoliagePlacer.FoliageAttachment> foliage = new ArrayList<>();
        int randVar = random.nextInt(0, 3);


            for (int i = 0; i < height; i++) {
                // region [Trunk]
                this.setLog(world, trunk, random, mutable, config, startPos, 0, i, -1, rot);
                if (i >= height - 1) continue;
                this.setLog(world, trunk, random, mutable, config, startPos, 0, i, 0, rot);
                if (i >= height - 5) continue;
                this.setLog(world, trunk, random, mutable, config, startPos, 1, i, 0, rot);
                if (i >= (height / 2) + 4) continue;
                this.setLog(world, trunk, random, mutable, config, startPos, 1, i, -1, rot);
                // endregion


            }
// ---- Root shape selection ----
        int shapeIndex = random.nextInt(ROOT_SHAPES.size());
        RootShape rootShape = ROOT_SHAPES.get(shapeIndex);

        // ---- Place roots for this shape ----
        for (RootRoot rootData : rootShape.roots()) {
            int[] r = rotated(rootData.dx(), rootData.dz(), rot);
            BlockPos.MutableBlockPos rootPos =
                    mutable.setWithOffset(startPos, r[0], rootData.dy(), r[1]);

            this.growRootsDown(world, random, rootPos, rootData.depth(), trunk, config);
        }

        // ---- Place decorations (slab / stairs) for this shape ----
        for (RootDecoration deco : rootShape.decorations()) {
            int[] r = rotated(deco.dx(), deco.dz(), rot);
            BlockPos.MutableBlockPos decoBasePos =
                    mutable.setWithOffset(startPos, r[0], deco.dy(), r[1]);

            placeDecoration(trunk, random, decoBasePos, deco, rot);
        }

        // You can add a foliage attachment back here if needed:
        // foliage.add(new FoliagePlacer.FoliageAttachment(startPos.above(height), 0, true));

        return foliage;
    }

    // ------------------------------------------------------------------------
    // Helpers
    // ------------------------------------------------------------------------

    private void setLog(LevelSimulatedReader world,
                        BiConsumer<BlockPos, BlockState> trunk,
                        RandomSource random,
                        BlockPos.MutableBlockPos tmpPos,
                        TreeConfiguration config,
                        BlockPos startPos,
                        int dx, int dy, int dz, int rot) {

        int[] r = rotated(dx, dz, rot);
        tmpPos.setWithOffset(startPos, r[0], dy, r[1]);
        this.placeLogIfFree(world, trunk, random, tmpPos, config);
    }

    private void placeDecoration(BiConsumer<BlockPos, BlockState> trunk,
                                 RandomSource random,
                                 BlockPos basePos,
                                 RootDecoration decoration,
                                 int rot) {

        switch (decoration.type()) {
            case SLAB -> slabProvider.ifPresent(provider -> {
                BlockState slab = provider.getState(random, basePos);
                trunk.accept(basePos, slab);
            });

            case STAIRS -> stairsProvider.ifPresent(provider -> {
                BlockState base = provider.getState(random, basePos);
                Direction facing = rotateDirection(decoration.initialFacing(), rot);

                if (base.hasProperty(StairBlock.FACING)) {
                    base = base.setValue(StairBlock.FACING, facing);
                }

                trunk.accept(basePos, base);
            });
        }
    }

    private Direction rotateDirection(Direction dir, int rot) {
        if (dir == null) {
            return Direction.NORTH;
        }
        return switch (rot) {
            case 0 -> dir;
            case 1 -> dir.getClockWise();
            case 2 -> dir.getOpposite();
            default -> dir.getCounterClockWise();
        };
    }

    private static final int[][] ROTATIONS = {
            {1, 0, 0, 1},   // 0°: (dx, dz) -> ( dx,  dz)
            {0, -1, 1, 0},  // 90°: (dx, dz) -> (-dz, dx)
            {-1, 0, 0, -1}, // 180°: (dx, dz) -> (-dx, -dz)
            {0, 1, -1, 0}   // 270°: (dx, dz) -> (dz, -dx)
    };

    private int[] rotated(int dx, int dz, int rotationIndex) {
        int[] r = ROTATIONS[rotationIndex];
        int rx = dx * r[0] + dz * r[1];
        int rz = dx * r[2] + dz * r[3];
        return new int[]{rx, rz};
    }

    // ------------------------------------------------------------------------
    // Root shape data
    // ------------------------------------------------------------------------

    // One root entry
    public record RootRoot(int dx, int dy, int dz, int depth) {}

    // One decoration entry
    public record RootDecoration(int dx, int dy, int dz,
                                 Type type,
                                 Direction initialFacing) {
        public enum Type {
            SLAB,
            STAIRS
        }
    }

    // A complete root shape (roots + decorations)
    public record RootShape(List<RootRoot> roots,
                            List<RootDecoration> decorations) {}

    // ---- Shape 0: original randVar == 0 layout ----
    private static final RootShape SHAPE_0 = new RootShape(
            List.of(
                    new RootRoot(2, 16, 0, 20),
                    new RootRoot(0, 14, 2, 18),
                    new RootRoot(0, 9, -1, 13),
                    new RootRoot(-1, 6, -1, 10),
                    new RootRoot(2, 5, -1, 9),
                    new RootRoot(1, 4, -1, 8),
                    new RootRoot(-2, 4, -1, 8),

                    new RootRoot(3, 3, -2, 7),
                    new RootRoot(-2, 3, -2, 7),
                    new RootRoot(-3, 3, -2, 7),
                    new RootRoot(-1, 3, 2, 7),
                    new RootRoot(2, 3, 2, 7),

                    new RootRoot(-1, 2, -2, 6),
                    new RootRoot(-2, 2, -3, 6),
                    new RootRoot(-4, 2, -2, 6),
                    new RootRoot(4, 2, -3, 6),

                    new RootRoot(-5, 1, -3, 5),
                    new RootRoot(5, 1, -3, 5),
                    new RootRoot(6, 1, -2, 5),
                    new RootRoot(3, 1, 3, 5),
                    new RootRoot(-1, 1, 1, 5),
                    new RootRoot(-2, 1, 3, 5),

                    new RootRoot(-1, 0, -3, 4),
                    new RootRoot(-1, 0, -4, 4),
                    new RootRoot(-4, 0, -3, 4),
                    new RootRoot(-6, 0, -4, 4),
                    new RootRoot(-1, 0, -3, 4), // duplicate kept as in original
                    new RootRoot(-3, 0, 4, 4),
                    new RootRoot(-4, 0, 5, 4),
                    new RootRoot(3, 0, 2, 4),
                    new RootRoot(3, 0, 4, 4),
                    new RootRoot(3, 0, 5, 4),
                    new RootRoot(7, 0, -1, 4),

                    new RootRoot(-5, -1, 6, 4),
                    new RootRoot(3, -1, 6, 4),
                    new RootRoot(4, -1, 2, 4),
                    new RootRoot(8, -1, 0, 4),
                    new RootRoot(-1, -1, -5, 4),
                    new RootRoot(-4, -1, -4, 4)
            ),
            List.of(
                    // Example decorations for SHAPE_0:

            )
    );

    // ---- Shape 1: original else-layout (randVar != 0) ----
    private static final RootShape SHAPE_1 = new RootShape(
            List.of(
                    new RootRoot(0, 15, 1, 20),
                    new RootRoot(2, 14, -1, 19),
                    new RootRoot(-1, 9, -1, 14),

                    new RootRoot(-1, 6, 1, 11),
                    new RootRoot(-1, 6, -2, 11),

                    new RootRoot(-1, 4, -3, 9),

                    new RootRoot(-1, 3, 0, 8),
                    new RootRoot(-2, 3, -3, 8),
                    new RootRoot(-2, 3, -4, 8),
                    new RootRoot(2, 3, -2, 8),

                    new RootRoot(-2, 2, -5, 7),
                    new RootRoot(-3, 2, -3, 7),
                    new RootRoot(-2, 2, -2, 7),
                    new RootRoot(-3, 2, 3, 7),
                    new RootRoot(-2, 2, 2, 7),

                    new RootRoot(0, 1, -7, 6),
                    new RootRoot(-1, 1, -6, 6),
                    new RootRoot(1, 1, -2, 6),
                    new RootRoot(3, 1, -3, 6),
                    new RootRoot(2, 1, 1, 6),
                    new RootRoot(-2, 1, 5, 6),
                    new RootRoot(-3, 1, 4, 6),
                    new RootRoot(-4, 1, 3, 6),
                    new RootRoot(-5, 1, 3, 6),

                    new RootRoot(-7, 0, 1, 5),
                    new RootRoot(-6, 0, 2, 5),
                    new RootRoot(2, 0, 2, 5),
                    new RootRoot(3, 0, -1, 5),
                    new RootRoot(4, 0, -4, 5),
                    new RootRoot(1, 0, -8, 5),
                    new RootRoot(-3, 0, -2, 5),
                    new RootRoot(-3, 0, -5, 5),
                    new RootRoot(-4, 0, -2, 5),
                    new RootRoot(-4, 0, -5, 5),
                    new RootRoot(2, 0, -8, 5),
                    new RootRoot(5, 0, -5, 5),
                    new RootRoot(-1, 0, 6, 5),

                    new RootRoot(3, -1, -8, 4),
                    new RootRoot(6, -1, -6, 4),
                    new RootRoot(0, -1, 7, 4),
                    new RootRoot(2, -1, 3, 4),
                    new RootRoot(-8, -1, 0, 4),
                    new RootRoot(-5, -1, -2, 4),
                    new RootRoot(-5, -1, -5, 4)
            ),
            List.of(

                    new RootDecoration(-1, 0, 6,
                            RootDecoration.Type.SLAB,
                            null),
                    new RootDecoration(5, 0, -5,
                            RootDecoration.Type.SLAB,
                            null),
                    new RootDecoration(2, 0, -8,
                            RootDecoration.Type.SLAB,
                            null),

                    // -3, 2, 4 -> stairs on top, initial facing EAST
                    new RootDecoration(0, 15, 1,
                            RootDecoration.Type.STAIRS,
                            Direction.NORTH)
            )
    );

    // All available shapes (easy to extend up to 10+)
    private static final List<RootShape> ROOT_SHAPES = List.of(
            SHAPE_0,
            SHAPE_1
            // Add more shapes here in the future
    );
}
