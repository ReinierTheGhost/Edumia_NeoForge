package com.legends.edumia.world.trees.treedecorators;

import com.legends.edumia.world.trees.EdumiaTreeDecoratorTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;


public class HangingBranchDecorator extends TreeDecorator {
    public static final Codec<HangingBranchDecorator> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(Codec.floatRange(0.0F, 1.0F).fieldOf("probability").forGetter(decorator -> decorator.probability),
                            BlockStateProvider.CODEC.fieldOf("head_provider").forGetter(decorator -> decorator.headProvider),
                            BlockStateProvider.CODEC.fieldOf("body_provider").forGetter(decorator -> decorator.bodyProvider))
                    .apply(instance, HangingBranchDecorator::new));
    private final float probability;
    private final BlockStateProvider headProvider;
    private final BlockStateProvider bodyProvider;

    public HangingBranchDecorator(float probability, BlockStateProvider headProvider, BlockStateProvider bodyProvider) {
        this.probability = probability;
        this.headProvider = headProvider;
        this.bodyProvider = bodyProvider;
    }
    @Override
    protected TreeDecoratorType<?> type() {
        return EdumiaTreeDecoratorTypes.HANGING_BRANCH_DECORATOR.get();
    }

    @Override
    public void place(Context generator) {
        for (BlockPos leafPos : Util.shuffledCopy(generator.leaves(), generator.random())) {
            BlockPos branchPos = leafPos.relative(Direction.DOWN);
            int height = generator.random().nextIntBetweenInclusive(1, 6);
            if (generator.isAir(branchPos) && generator.random().nextFloat() < this.probability) {
                placeBranchColumn(generator, branchPos, height);
            }
        }
    }

    private void placeBranchColumn(Context generator, BlockPos pPos, int pHeight) {
        for (int i = 0; i < pHeight; ++i) {
            BlockPos blockPos = pPos.relative(Direction.DOWN, i);
            if (!generator.isAir(blockPos.below())) {
                generator.setBlock(blockPos, headProvider.getState(generator.random(), blockPos));
                break;
            }
            generator.setBlock(blockPos, bodyProvider.getState(generator.random(), blockPos));
        }
    }
}
