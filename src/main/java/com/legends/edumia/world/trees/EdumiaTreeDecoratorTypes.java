package com.legends.edumia.world.trees;

import com.legends.edumia.Edumia;
import com.legends.edumia.world.trees.treedecorators.PineBranchDecorator;
import com.legends.edumia.world.trees.treedecorators.HangingBranchDecorator;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;


public class EdumiaTreeDecoratorTypes {

    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATORS =
            DeferredRegister.create(Registries.TREE_DECORATOR_TYPE, Edumia.MOD_ID);
    public static final RegistryObject<TreeDecoratorType<PineBranchDecorator>> PINE_BRANCH_DECORATOR =
            TREE_DECORATORS.register("pine_branch_decorator", () -> new TreeDecoratorType<>(PineBranchDecorator.CODEC));
    public static final RegistryObject<TreeDecoratorType<HangingBranchDecorator>> HANGING_BRANCH_DECORATOR =
            TREE_DECORATORS.register("hanging_branch_decorator", () -> new TreeDecoratorType<>(HangingBranchDecorator.CODEC));

    public static void register(IEventBus eventBus) {
        TREE_DECORATORS.register(eventBus);
    }
}
