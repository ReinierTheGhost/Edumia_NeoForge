package com.legends.edumia.world.trees.foliageplacer;

import com.legends.edumia.world.trees.EdumiaFoliagePlacerTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.material.Fluids;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

public class OvalFoliagePlacer extends FoliagePlacer {
    protected final int baseHeight;
    protected final IntProvider offset;
    protected final IntProvider baseRadius;
    protected final ArrayList<BlockState> leaves;
    protected final float extraSize;

    public static final Codec<OvalFoliagePlacer> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Codec.intRange(0,16).fieldOf("baseHeight").forGetter((trunkPlacer) -> {
                return trunkPlacer.baseHeight;
            }), IntProvider.codec(-16, 16).fieldOf("offset").forGetter((trunkPlacer) -> {
                return trunkPlacer.offset;
            }), IntProvider.codec(0, 16).fieldOf("baseRadius").forGetter((trunkPlacer) -> {
                return trunkPlacer.baseRadius;
            }), Codec.list(BlockState.CODEC).fieldOf("leaves").forGetter((trunkPlacer) -> {
                return trunkPlacer.leaves;
            }), Codec.floatRange(0.0f, 1.0f).fieldOf("extraSize").forGetter((trunkPlacer) -> {
                return trunkPlacer.extraSize;
            })).apply(instance, OvalFoliagePlacer::new));

    public OvalFoliagePlacer(int baseHeight, IntProvider offset, IntProvider baseRadius, List<BlockState> leaves, float extraRadius) {
        super(baseRadius, offset);
        this.baseHeight = baseHeight;
        this.offset = offset;
        this.baseRadius = baseRadius;
        this.leaves = new ArrayList<>(leaves);
        this.extraSize = extraRadius;
    }

    public OvalFoliagePlacer(int baseHeight, IntProvider offset, IntProvider baseRadius, float extraRadius) {
        this(baseHeight, offset, baseRadius, new ArrayList<>(), extraRadius);
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return EdumiaFoliagePlacerTypes.OVAL_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader world, FoliageSetter placer, RandomSource random, TreeConfiguration config, int trunkHeight,
                                 FoliageAttachment foliage, int foliageHeight, int radius, int offset) {
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        for(int x = -radius; x <= radius; ++x) {
            for(int z = -radius; z <= radius; ++z) {
                for(int y = -baseHeight; y <= baseHeight; ++y) {
                    if (!this.shouldSkipLocationSigned(random, x, offset + y, z, radius, false)) {
                        if (isPointInside(x, y, z, radius)) {
                            mutable.setWithOffset(foliage.pos(), x, offset + y, z);
                            placeLeavesBlock(world, placer, random, config, mutable, leaves);
                        }
                    }
                }
            }
        }
    }

    protected static boolean placeLeavesBlock(LevelSimulatedReader world, FoliageSetter placer, RandomSource random, TreeConfiguration config,
                                              BlockPos pos, List<BlockState> leaves) {
        if (!TreeFeature.validTreePos(world, pos)) {
            return false;
        } else {
            BlockState leavesBlock;
            if(leaves.isEmpty()) {
                leavesBlock = config.foliageProvider.getState(random, pos);
            } else {
                leavesBlock = leaves.get(random.nextIntBetweenInclusive(0, leaves.size() - 1));
            }

            if (leavesBlock.hasProperty(BlockStateProperties.WATERLOGGED)) {
                leavesBlock = leavesBlock.setValue(BlockStateProperties.WATERLOGGED, world.isFluidAtPosition(pos, (fluidState) -> fluidState.isSourceOfType(Fluids.WATER)));
            }

            placer.set(pos, leavesBlock);
            return true;
        }
    }

    private boolean isPointInside(int x, int y, int z, int radius) {
        float randomness = -0.35f + (float) (Math.random() * 0.7f);
        float squareRadius = (radius + extraSize + randomness) * (radius + extraSize + randomness);
        float squareHeight = (baseHeight + extraSize + randomness) * (baseHeight + extraSize + randomness);
        float deltaX = (float)(x*x) / squareRadius;
        float deltaY = (float)(y*y) / squareHeight;
        float deltaZ = (float)(z*z) / squareRadius;

        return (deltaX + deltaY + deltaZ <= 1);
    }

    @Override
    public int foliageHeight(RandomSource random, int trunkHeight, TreeConfiguration config) {
        return 4;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        if (dx + dz >= 7) {
            return true;
        } else {
            return dx * dx + dz * dz > (radius + extraSize) * (radius + extraSize);
        }
    }
}
