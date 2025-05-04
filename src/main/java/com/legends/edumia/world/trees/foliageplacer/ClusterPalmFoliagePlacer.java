package com.legends.edumia.world.trees.foliageplacer;

import com.legends.edumia.world.trees.EdumiaFoliagePlacerTypes;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class ClusterPalmFoliagePlacer  extends FoliagePlacer {

    public static final MapCodec<ClusterPalmFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec((instance) ->
            foliagePlacerParts(instance).apply(instance, ClusterPalmFoliagePlacer::new));

    public ClusterPalmFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    @Override
    protected @NotNull FoliagePlacerType<?> type() {
        return EdumiaFoliagePlacerTypes.CLUSTER_PALM_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader level, FoliageSetter leaves, RandomSource random,
                                 TreeConfiguration config, int maxFreeTreeHeight, FoliageAttachment foliage,
                                 int foliageHeight, int foliageRadius, int offset) {
        BlockPos pos = foliage.pos();
        BlockPos.MutableBlockPos mutablePos = pos.mutable();

        // Adjusted y range to move 1 block lower (-7 to 7 instead of -6 to 8)
        // Added +1 to X coordinate to move east
        for (int y = -7; y <= 7; y++) {
            for (int x = -5; x <= 11; x++) {
                for (int z = -5; z <= 11; z++) {
                    mutablePos.set(pos.getX() + (x - 7) + 1, pos.getY() + y, pos.getZ() + (z - 5));
                    if (!shouldSkipLocation(random, x, y, z, foliageRadius, false)) {
                        tryPlaceLeaf(level, leaves, random, config, mutablePos);

                    }
                }
            }
        }
    }

    @Override
    public int foliageHeight(@NotNull RandomSource random, int height, @NotNull TreeConfiguration config) {
        return 0;
    }


    @Override
    protected boolean shouldSkipLocation(@NotNull RandomSource random, int localX, int localY, int localZ, int range, boolean large) {

        int pattern = random.nextInt(1);
        switch (pattern) {
            case 0:
                return pattern0(localX, localY, localZ);
            case 1:
                return pattern1(localX, localY, localZ);
//            case 2:
//                return pattern2(localX, localY, localZ);
////            case 3:
////                return pattern3(localX, localY, localZ);
////            case 4:
////                return pattern4(localX, localY, localZ);
            default:
                return true;
        }


    }

    // Original pattern
    private boolean pattern0(int localX, int localY, int localZ) {
        if (localY == -4) { // NBT y=1
            return !((localX == 3 && localZ == 2) ||
                    (localX == 4 && localZ == 9) ||
                    (localX == 8 && localZ == 7) ||
                    (localX == 9 && localZ == 8));
        } else if (localY == -3) { // NBT y=2
            return !((localX == 3 && localZ == 2) ||
                    (localX == 3 && localZ == 3) ||
                    (localX == 4 && localZ == 7) ||
                    (localX == 4 && localZ == 8) ||
                    (localX == 7 && localZ == 2) ||
                    (localX == 7 && localZ == 6) ||
                    (localX == 7 && localZ == 7) ||
                    (localX == 8 && localZ == 1) ||
                    (localX == 8 && localZ == 2) ||
                    (localX == 8 && localZ == 7));
        } else if (localY == -2) { // NBT y=3
            return !((localX == 3 && localZ == 3) ||
                    (localX == 4 && (localZ == 3 || localZ == 4 || localZ == 7)) ||
                    (localX == 5 && (localZ == 6 || localZ == 7)) ||
                    (localX == 6 && localZ == 3) ||
                    (localX == 6 && localZ == 6) ||
                    (localX == 7 && (localZ == 2 || localZ == 3 || localZ == 6)));
        } else if (localY == -1) { // NBT y=4
            return !((localX == 0 && localZ == 5) ||
                    (localX == 1 && localZ == 5) ||
                    (localX == 2 && localZ == 5) ||
                    (localX == 4 && localZ == 4) ||
                    (localX == 5 && (localZ == 4 || localZ == 5 || localZ == 6)) ||
                    (localX == 6 && (localZ == 3 || localZ == 4)) ||
                    (localX == 10 && localZ == 6) ||
                    (localX == 11 && (localZ == 6 || localZ == 7)));
        } else if (localY == 0) { // NBT y=5
            return !((localX == 2 && (localZ == 5 || localZ == 6)) ||
                    (localX == 3 && localZ == 6) ||
                    (localX == 4 && (localZ >= 4 && localZ <= 6)) ||
                    (localX == 5 && (localZ >= 5 && localZ <= 7)) ||
                    (localX == 6 && (localZ >= 5 && localZ <= 7)) ||
                    (localX == 7 && (localZ == 5 || localZ == 7)) ||
                    (localX == 8 && localZ == 5) ||
                    (localX == 9 && (localZ == 5 || localZ == 6)) ||
                    (localX == 10 && localZ == 6));
        } else if (localY == 1) { // NBT y=6
            return !((localX == 3 && localZ == 3) ||
                    (localX == 4 && (localZ == 3 || localZ == 4 || localZ == 7 || localZ == 8)) ||
                    (localX == 5 && (localZ == 6 || localZ == 7)) ||
                    (localX == 6 && localZ == 5) ||
                    (localX == 7 && (localZ == 4 || localZ == 5 || localZ == 7 || localZ == 8)) ||
                    (localX == 8 && localZ == 8));
        } else if (localY == 2) { // NBT y=7
            return !((localX == 2 && localZ == 9) ||
                    (localX == 3 && (localZ == 1 || localZ == 2 || localZ == 3 || localZ == 8 || localZ == 9)) ||
                    (localX == 4 && localZ == 8) ||
                    (localX == 5 && localZ == 6) ||
                    (localX == 7 && localZ == 4) ||
                    (localX == 8 && (localZ == 4 || localZ == 8 || localZ == 9)) ||
                    (localX == 9 && localZ == 9));
        } else if (localY == 3) { // NBT y=8
            return !((localX == 5 && (localZ == 5 || localZ == 6)) ||
                    (localX == 8 && (localZ == 3 || localZ == 4)) ||
                    (localX == 9 && localZ == 3));
        } else if (localY == 4) { // NBT y=9
            return !((localX == 5 && localZ == 5) ||
                    (localX == 9 && localZ == 3));
        } else if (localY == 5) { // NBT y=10
            return !((localX == 5 && localZ == 5));
        }

        return true;
    }

    // More spread out pattern
    private boolean pattern1(int localX, int localY, int localZ) {
        //trunk is at 6 , 5
        if (localY == -5) { // NBT y=0
            return !((localX == 6 && localZ == 3)||
                    (localX == 6 && localZ == 2) ||
                    (localX == 5 && localZ == 7) ||
                    (localX == 4 && localZ == 7) ||
                    (localX == 10 && localZ == 8) );
        }
        else if (localY == -4) { // NBT y=1
            return !((localX == 6 && localZ == 3) ||
                    (localX == 6 && localZ == 4) ||
                    (localX == 5 && localZ == 7) ||
                    (localX == 5 && localZ == 6) ||
                    (localX == 2 && localZ == 2) ||
                    (localX == 9 && localZ == 8) ||
                    (localX == 10 && localZ == 8));
        }
        else if (localY == -3) { // NBT y=2
            return !((localX == 2 && localZ == 2) ||
                    (localX == 3 && localZ == 2) ||
                    (localX == 3 && localZ == 3) ||
                    (localX == 8 && localZ == 7) ||
                    (localX == 9 && localZ == 7) ||
                    (localX == 9 && localZ == 8) ||
                    (localX == 6 && localZ == 4)||
                    (localX == 6 && localZ == 6)||
                    (localX == 5 && localZ == 6));
        }
        else if (localY == -2) { // NBT y=3
            return !((localX == 3 && localZ == 3) ||
                    (localX == 3 && localZ == 4) ||
                    (localX == 3 && localZ == 7) ||
                    (localX == 3 && localZ == 8) ||
                    (localX == 4 && localZ == 4) ||
                    (localX == 4 && localZ == 7) ||
                    (localX == 5 && localZ == 4) ||
                    (localX == 6 && localZ == 4) ||
                    (localX == 6 && localZ == 5) ||
                    (localX == 7 && localZ == 3) ||
                    (localX == 7 && localZ == 6) ||
                    (localX == 8 && localZ == 2) ||
                    (localX == 8 && localZ == 3) ||
                    (localX == 8 && localZ == 6) ||
                    (localX == 8 && localZ == 7)||
                    (localX == 9 && localZ == 2)||
                    (localX == 10 && localZ == 2)||
                    (localX == 10 && localZ == 1));
        }
        else if (localY == -1) { // NBT y=4
            return !((localX == 1 && localZ == 3) ||
                    (localX == 3 && localZ == 4) ||
                    (localX == 3 && localZ == 5) ||
                    (localX == 4 && localZ == 0) ||
                    (localX == 4 && localZ == 1) ||
                    (localX == 4 && localZ == 5) ||
                    (localX == 4 && localZ == 6) ||
                    (localX == 4 && localZ == 7) ||
                    (localX == 5 && localZ == 1) ||
                    (localX == 5 && localZ == 2) ||
                    (localX == 5 && localZ == 3) ||
                    (localX == 5 && localZ == 4) ||
                    (localX == 5 && localZ == 5) ||
                    (localX == 5 && localZ == 6) ||
                    (localX == 6 && localZ == 4) ||
                    (localX == 6 && localZ == 5) ||
                    (localX == 7 && localZ == 3) ||
                    (localX == 7 && localZ == 4) ||
                    (localX == 7 && localZ == 5) ||
                    (localX == 7 && localZ == 6) ||
                    (localX == 8 && localZ == 5) ||
                    (localX == 9 && localZ == 5) ||
                    (localX == 10 && localZ == 5) ||
                    (localX == 11 && localZ == 5) ||
                    (localX == 11 && localZ == 6));
        }
        else if (localY == 0) { // NBT y=5
            return !((localX == 1 && localZ == 3) ||
                    (localX == 1 && localZ == 4) ||
                    (localX == 2 && localZ == 4) ||
                    (localX == 3 && localZ == 4) ||
                    (localX == 4 && localZ == 5) ||
                    (localX == 4 && localZ == 6) ||
                    (localX == 4 && localZ == 7) ||
                    (localX == 5 && localZ == 4) ||
                    (localX == 5 && localZ == 5) ||
                    (localX == 5 && localZ == 6) ||
                    (localX == 6 && localZ == 1) ||
                    (localX == 6 && localZ == 2) ||
                    (localX == 6 && localZ == 3) ||
                    (localX == 6 && localZ == 4) ||
                    (localX == 6 && localZ == 5) ||
                    (localX == 6 && localZ == 6) ||
                    (localX == 6 && localZ == 7) ||
                    (localX == 6 && localZ == 8) ||
                    (localX == 6 && localZ == 9) ||
                    (localX == 7 && localZ == 4) ||
                    (localX == 7 && localZ == 5) ||
                    (localX == 7 && localZ == 6));
        }
        else if (localY == 1) { // NBT y=6
            return !((localX == 3 && localZ == 5) ||
                    (localX == 3 && localZ == 7) ||
                    (localX == 3 && localZ == 8) ||
                    (localX == 4 && localZ == 5) ||
                    (localX == 4 && localZ == 7) ||
                    (localX == 5 && localZ == 3) ||
                    (localX == 5 && localZ == 4) ||
                    (localX == 6 && localZ == -1) ||
                    (localX == 6 && localZ == 0) ||
                    (localX == 6 && localZ == 1) ||
                    (localX == 6 && localZ == 5) ||
                    (localX == 6 && localZ == 6) ||
                    (localX == 6 && localZ == 7) ||
                    (localX == 6 && localZ == 9) ||
                    (localX == 6 && localZ == 10) ||
                    (localX == 6 && localZ == 11) ||
                    (localX == 6 && localZ == 12) ||
                    (localX == 7 && localZ == 4) ||
                    (localX == 7 && localZ == 6) ||
                    (localX == 8 && localZ == 3) ||
                    (localX == 8 && localZ == 4) ||
                    (localX == 8 && localZ == 6));
        }
        else if (localY == 2) { // NBT y=7
            return !((localX == 3 && localZ == 5) ||
                    (localX == 2 && localZ == 5) ||
                    (localX == 1 && localZ == 5) ||
                    (localX == 1 && localZ == 6) ||
                    (localX == 3 && localZ == 8) ||
                    (localX == 3 && localZ == 9) ||
                    (localX == 5 && localZ == 3) ||
                    (localX == 6 && localZ == 5) ||
                    (localX == 6 && localZ == 7) ||
                    (localX == 8 && localZ == 3) ||
                    (localX == 9 && localZ == 3) ||
                    (localX == 9 && localZ == 2) ||
                    (localX == 10 && localZ == 2) ||
                    (localX == 8 && localZ == 6));
        }
        else if (localY == 3) { // NBT y=8
            return !((localX == 6 && localZ == 5) ||
                    (localX == 8 && localZ == 6) ||
                    (localX == 8 && localZ == 7) ||
                    (localX == 9 && localZ == 7) ||
                    (localX == 9 && localZ == 8) ||
                    (localX == 10 && localZ == 8) ||
                    (localX == 5 && localZ == 3) ||
                    (localX == 4 && localZ == 3) ||
                    (localX == 4 && localZ == 2) ||
                    (localX == 6 && localZ == 7)||
                    (localX == 6 && localZ == 8)||
                    (localX == 10 && localZ == 2) ||
                    (localX == 6 && localZ == 9));
        }
        else if (localY == 4) { // NBT y=9
            return !((localX == 5 && localZ == 5) ||
                    (localX == 4 && localZ == 2) ||
                    (localX == 3 && localZ == 2) ||
                    (localX == 6 && localZ == 5)||
                    (localX == 6 && localZ == 9));
        }
        else if (localY == 5) { // NBT y=9
            return !((localX == 5 && localZ == 5));
        }

        return true;





        //pos: [ 3, 0, 8, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 4, 0, 8, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 5, 0, 3, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 5, 0, 4, ]
        //			state: 1
        //		},
        //		{
        //			pos: [ 9, 0, 9, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 1, 1, 3, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 4, 1, 7, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 4, 1, 8, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 5, 1, 4, ]
        //			state: 2
        //		},
        //		{
        //			pos: [ 5, 1, 5, ]
        //			state: 3
        //		},
        //		{
        //			pos: [ 8, 1, 9, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 9, 1, 9, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 1, 2, 3, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 2, 2, 3, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 2, 2, 4, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 4, 2, 7, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 5, 2, 5, ]
        //			state: 4
        //		},
        //		{
        //			pos: [ 5, 2, 7, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 7, 2, 8, ]
        //			state: 1
        //		},
        //		{
        //			pos: [ 8, 2, 8, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 8, 2, 9, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 2, 3, 4, ]
        //			state: 1
        //		},
        //		{
        //			pos: [ 2, 3, 5, ]
        //			state: 2
        //		},
        //		{
        //			pos: [ 2, 3, 8, ]
        //			state: 1
        //		},
        //		{
        //			pos: [ 2, 3, 9, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 3, 3, 1, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 3, 3, 5, ]
        //			state: 3
        //		},
        //		{
        //			pos: [ 3, 3, 8, ]
        //			state: 2
        //		},
        //		{
        //			pos: [ 4, 3, 5, ]
        //			state: 4
        //		},
        //		{
        //			pos: [ 5, 3, 5, ]
        //			state: 5
        //		},
        //		{
        //			pos: [ 5, 3, 6, ]
        //			state: 6
        //		},
        //		{
        //			pos: [ 6, 3, 4, ]
        //			state: 3
        //		},
        //		{
        //			pos: [ 6, 3, 7, ]
        //			state: 4
        //		},
        //		{
        //			pos: [ 7, 3, 3, ]
        //			state: 1
        //		},
        //		{
        //			pos: [ 7, 3, 4, ]
        //			state: 2
        //		},
        //		{
        //			pos: [ 7, 3, 7, ]
        //			state: 3
        //		},
        //		{
        //			pos: [ 7, 3, 8, ]
        //			state: 2
        //		},
        //		{
        //			pos: [ 8, 3, 3, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 9, 3, 2, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 9, 3, 3, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 0, 4, 4, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 2, 4, 5, ]
        //			state: 3
        //		},
        //		{
        //			pos: [ 2, 4, 6, ]
        //			state: 4
        //		},
        //		{
        //			pos: [ 3, 4, 1, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 3, 4, 2, ]
        //			state: 1
        //		},
        //		{
        //			pos: [ 3, 4, 6, ]
        //			state: 5
        //		},
        //		{
        //			pos: [ 3, 4, 7, ]
        //			state: 4
        //		},
        //		{
        //			pos: [ 3, 4, 8, ]
        //			state: 3
        //		},
        //		{
        //			pos: [ 4, 4, 2, ]
        //			state: 2
        //		},
        //		{
        //			pos: [ 4, 4, 3, ]
        //			state: 3
        //		},
        //		{
        //			pos: [ 4, 4, 4, ]
        //			state: 4
        //		},
        //		{
        //			pos: [ 4, 4, 5, ]
        //			state: 5
        //		},
        //		{
        //			pos: [ 4, 4, 6, ]
        //			state: 6
        //		},
        //		{
        //			pos: [ 4, 4, 7, ]
        //			state: 5
        //		},
        //		{
        //			pos: [ 5, 4, 5, ]
        //			state: 6
        //		},
        //		{
        //			pos: [ 5, 4, 6, ]
        //			state: 7
        //		},
        //		{
        //			pos: [ 6, 4, 4, ]
        //			state: 4
        //		},
        //		{
        //			pos: [ 6, 4, 5, ]
        //			state: 5
        //		},
        //		{
        //			pos: [ 6, 4, 6, ]
        //			state: 6
        //		},
        //		{
        //			pos: [ 6, 4, 7, ]
        //			state: 5
        //		},
        //		{
        //			pos: [ 7, 4, 6, ]
        //			state: 5
        //		},
        //		{
        //			pos: [ 8, 4, 6, ]
        //			state: 4
        //		},
        //		{
        //			pos: [ 9, 4, 6, ]
        //			state: 3
        //		},
        //		{
        //			pos: [ 10, 4, 6, ]
        //			state: 2
        //		},
        //		{
        //			pos: [ 10, 4, 7, ]
        //			state: 1
        //		},
        //		{
        //			pos: [ 0, 5, 4, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 0, 5, 5, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 1, 5, 5, ]
        //			state: 1
        //		},
        //		{
        //			pos: [ 2, 5, 5, ]
        //			state: 2
        //		},
        //		{
        //			pos: [ 3, 5, 6, ]
        //			state: 4
        //		},
        //		{
        //			pos: [ 3, 5, 7, ]
        //			state: 3
        //		},
        //		{
        //			pos: [ 3, 5, 8, ]
        //			state: 2
        //		},
        //		{
        //			pos: [ 4, 5, 5, ]
        //			state: 4
        //		},
        //		{
        //			pos: [ 4, 5, 6, ]
        //			state: 5
        //		},
        //		{
        //			pos: [ 4, 5, 7, ]
        //			state: 4
        //		},
        //		{
        //			pos: [ 5, 5, 2, ]
        //			state: 2
        //		},
        //		{
        //			pos: [ 5, 5, 3, ]
        //			state: 3
        //		},
        //		{
        //			pos: [ 5, 5, 4, ]
        //			state: 4
        //		},
        //		{
        //			pos: [ 5, 5, 5, ]
        //			state: 5
        //		},
        //		{
        //			pos: [ 5, 5, 6, ]
        //			state: 6
        //		},
        //		{
        //			pos: [ 5, 5, 7, ]
        //			state: 5
        //		},
        //		{
        //			pos: [ 5, 5, 8, ]
        //			state: 4
        //		},
        //		{
        //			pos: [ 5, 5, 9, ]
        //			state: 3
        //		},
        //		{
        //			pos: [ 5, 5, 10, ]
        //			state: 2
        //		},
        //		{
        //			pos: [ 6, 5, 5, ]
        //			state: 4
        //		},
        //		{
        //			pos: [ 6, 5, 6, ]
        //			state: 5
        //		},
        //		{
        //			pos: [ 6, 5, 7, ]
        //			state: 4
        //		},
        //		{
        //			pos: [ 2, 6, 6, ]
        //			state: 2
        //		},
        //		{
        //			pos: [ 2, 6, 8, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 2, 6, 9, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 3, 6, 6, ]
        //			state: 3
        //		},
        //		{
        //			pos: [ 3, 6, 8, ]
        //			state: 1
        //		},
        //		{
        //			pos: [ 4, 6, 4, ]
        //			state: 2
        //		},
        //		{
        //			pos: [ 4, 6, 5, ]
        //			state: 3
        //		},
        //		{
        //			pos: [ 5, 6, 0, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 5, 6, 1, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 5, 6, 2, ]
        //			state: 1
        //		},
        //		{
        //			pos: [ 5, 6, 6, ]
        //			state: 5
        //		},
        //		{
        //			pos: [ 5, 6, 7, ]
        //			state: 4
        //		},
        //		{
        //			pos: [ 5, 6, 8, ]
        //			state: 3
        //		},
        //		{
        //			pos: [ 5, 6, 10, ]
        //			state: 1
        //		},
        //		{
        //			pos: [ 5, 6, 11, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 6, 6, 5, ]
        //			state: 3
        //		},
        //		{
        //			pos: [ 6, 6, 7, ]
        //			state: 3
        //		},
        //		{
        //			pos: [ 7, 6, 4, ]
        //			state: 1
        //		},
        //		{
        //			pos: [ 7, 6, 5, ]
        //			state: 2
        //		},
        //		{
        //			pos: [ 7, 6, 7, ]
        //			state: 2
        //		},
        //		{
        //			pos: [ 0, 7, 6, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 0, 7, 7, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 1, 7, 6, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 2, 7, 6, ]
        //			state: 1
        //		},
        //		{
        //			pos: [ 2, 7, 9, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 2, 7, 10, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 4, 7, 4, ]
        //			state: 1
        //		},
        //		{
        //			pos: [ 5, 7, 6, ]
        //			state: 4
        //		},
        //		{
        //			pos: [ 5, 7, 8, ]
        //			state: 2
        //		},
        //		{
        //			pos: [ 7, 7, 4, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 7, 7, 7, ]
        //			state: 1
        //		},
        //		{
        //			pos: [ 8, 7, 3, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 8, 7, 4, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 9, 7, 3, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 3, 8, 3, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 3, 8, 4, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 4, 8, 4, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 5, 8, 6, ]
        //			state: 3
        //		},
        //		{
        //			pos: [ 5, 8, 8, ]
        //			state: 1
        //		},
        //		{
        //			pos: [ 5, 8, 9, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 5, 8, 10, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 7, 8, 7, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 7, 8, 8, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 8, 8, 8, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 8, 8, 9, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 9, 8, 3, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 9, 8, 9, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 2, 9, 3, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 3, 9, 3, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 4, 9, 6, ]
        //			state: 1
        //		},
        //		{
        //			pos: [ 5, 9, 6, ]
        //			state: 2
        //		},
        //		{
        //			pos: [ 5, 9, 10, ]
        //			state: 0
        //		},
        //		{
        //			pos: [ 4, 10, 6, ]
        //			state: 0
        //		},

    }

    // More dense pattern
    private boolean pattern2(int localX, int localY, int localZ) {
        if (localY == -4) {
            return !((localX == 3 && localZ == 2) ||
                    (localX == 4 && localZ == 8) ||
                    (localX == 5 && localZ == 5) ||
                    (localX == 7 && localZ == 7) ||
                    (localX == 8 && localZ == 4));
        } else if (localY == -3) {
            return !((localX == 3 && (localZ == 2 || localZ == 3)) ||
                    (localX == 4 && (localZ == 7 || localZ == 8)) ||
                    (localX == 6 && (localZ == 4 || localZ == 5)) ||
                    (localX == 8 && (localZ == 3 || localZ == 4)));
        } else if (localY == -2) {
            return !((localX == 2 && localZ == 3) ||
                    (localX == 3 && (localZ == 2 || localZ == 4)) ||
                    (localX == 5 && (localZ == 5 || localZ == 6)) ||
                    (localX == 7 && (localZ == 4 || localZ == 5 || localZ == 6)) ||
                    (localX == 8 && (localZ == 3 || localZ == 5)));
        } else if (localY == -1) {
            return !((localX == 1 && localZ == 5) ||
                    (localX == 3 && (localZ == 4 || localZ == 5)) ||
                    (localX == 5 && (localZ == 4 || localZ == 5 || localZ == 6)) ||
                    (localX == 7 && (localZ == 3 || localZ == 4 || localZ == 5)) ||
                    (localX == 9 && localZ == 5));
        } else if (localY == 0) {
            return !((localX == 3 && (localZ == 4 || localZ == 5 || localZ == 6)) ||
                    (localX == 4 && (localZ == 4 || localZ == 5 || localZ == 6)) ||
                    (localX == 5 && (localZ == 4 || localZ == 5 || localZ == 6)) ||
                    (localX == 6 && (localZ == 4 || localZ == 5 || localZ == 6)) ||
                    (localX == 7 && (localZ == 4 || localZ == 5 || localZ == 6)));
        } else if (localY == 1) {
            return !((localX == 3 && (localZ == 4 || localZ == 5)) ||
                    (localX == 4 && (localZ == 4 || localZ == 5 || localZ == 6)) ||
                    (localX == 6 && (localZ == 4 || localZ == 5 || localZ == 6)) ||
                    (localX == 8 && (localZ == 4 || localZ == 5)));
        } else if (localY == 2) {
            return !((localX == 3 && localZ == 5) ||
                    (localX == 4 && (localZ == 4 || localZ == 5)) ||
                    (localX == 5 && (localZ == 4 || localZ == 5 || localZ == 6)) ||
                    (localX == 6 && (localZ == 4 || localZ == 5)) ||
                    (localX == 7 && localZ == 5));
        } else if (localY == 3) {
            return !((localX == 4 && localZ == 5) ||
                    (localX == 5 && (localZ == 4 || localZ == 5 || localZ == 6)) ||
                    (localX == 6 && localZ == 5));
        } else if (localY == 4) {
            return !((localX == 5 && (localZ == 4 || localZ == 5 || localZ == 6)));
        } else if (localY == 5) {
            return !((localX == 5 && localZ == 5));
        }
        return true;
    }

    // Asymmetric pattern
    private boolean pattern3(int localX, int localY, int localZ) {
        if (localY == -4) {
            return !((localX == 2 && localZ == 4) ||
                    (localX == 4 && localZ == 7) ||
                    (localX == 7 && localZ == 5) ||
                    (localX == 9 && localZ == 6));
        } else if (localY == -3) {
            return !((localX == 3 && localZ == 4) ||
                    (localX == 5 && localZ == 6) ||
                    (localX == 7 && localZ == 4) ||
                    (localX == 8 && localZ == 7));
        }
        // ... continue with modified positions ...
        return true;
    }
    // Compact pattern
    private boolean pattern4(int localX, int localY, int localZ) {
        if (localY == -4) {
            return !((localX == 4 && localZ == 4) ||
                    (localX == 5 && localZ == 6) ||
                    (localX == 7 && localZ == 5) ||
                    (localX == 8 && localZ == 7));
        } else if (localY == -3) {
            return !((localX == 4 && (localZ == 4 || localZ == 5)) ||
                    (localX == 6 && (localZ == 5 || localZ == 6)) ||
                    (localX == 7 && (localZ == 4 || localZ == 5)));
        }
        // ... continue with modified positions ...
        return true;
    }
}
