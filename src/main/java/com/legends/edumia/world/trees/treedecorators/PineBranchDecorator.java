package com.legends.edumia.world.trees.treedecorators;


import com.legends.edumia.blocks.AxialSlabBlock;
import com.legends.edumia.blocks.EdumiaBlockStates;
import com.legends.edumia.world.trees.EdumiaTreeDecoratorTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class PineBranchDecorator extends TreeDecorator {

    public static final MapCodec<PineBranchDecorator> CODEC = RecordCodecBuilder.mapCodec((instance) ->
            instance.group(BlockStateProvider.CODEC.fieldOf("wood_provider").forGetter((deco) ->
                            deco.woodProvider),
                    Codec.floatRange(0.0f, 1.0f).fieldOf("probability").forGetter((deco) ->
                            deco.probability)
            ).apply(instance, PineBranchDecorator::new));

    private final BlockStateProvider woodProvider;
    private final float probability;

    protected PineBranchDecorator(BlockStateProvider woodProvider, float probability) {
        this.woodProvider = woodProvider;
        this.probability = probability;
    }

    public PineBranchDecorator(BlockState log, float probability){
        this(BlockStateProvider.simple(log), probability);
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return EdumiaTreeDecoratorTypes.PINE_BRANCH_DECORATOR.get();
    }

    @Override
    public void place(Context generator) {
        ObjectArrayList<BlockPos> list = generator.logs();
        RandomSource random = generator.random();
        LevelSimulatedReader world = generator.level();
        int baseY = (list.get(0)).getY();
        int trunkHeight = list.size();
        Direction[] lastDir = new Direction[1];
        list.stream().filter(pos ->{
            int diff = pos.getY() - baseY;
            return diff >= 3 && diff < trunkHeight - 3;
                }).forEach(pos -> {
                    if (random.nextFloat() < this.probability){
                        Direction dir = Direction.Plane.HORIZONTAL.getRandomDirection(random);
                        if (dir != lastDir[0]){
                            lastDir[0] = dir;
                            BlockPos branchPos = pos.offset(dir.getStepX(), 0, dir.getStepZ());
                            if (TreeFeature.isGrassOrDirt(world, branchPos)){
                                BlockState blockState = this.woodProvider.getState(random, branchPos);
                                if (blockState.getBlock() instanceof RotatedPillarBlock){
                                    blockState = blockState.setValue(RotatedPillarBlock.AXIS, dir.getAxis());
                                } else if (blockState.getBlock() instanceof AxialSlabBlock && blockState.hasProperty(EdumiaBlockStates.SLAB_AXIS)) {
                                    blockState = (blockState.setValue(EdumiaBlockStates.SLAB_AXIS, dir.getAxis())).setValue(SlabBlock.TYPE, dir.getAxisDirection() == Direction.AxisDirection.NEGATIVE ? SlabType.TOP : SlabType.BOTTOM);
                                    
                                }

                                generator.setBlock(branchPos, blockState);
                            }
                        }
                    }
        });
    }
}
