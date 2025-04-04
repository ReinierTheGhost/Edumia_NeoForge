package com.legends.edumia.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EdumiaUtil {
    public static void unlockFinalField(Field f){
        try {
            f.setAccessible(true);
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(f, (f.getModifiers() & -3 & -5 | 1) & -17);
        } catch (SecurityException | IllegalAccessException | NoSuchFieldException var2){
            EdumiaLog.error("Error unlocking final field " + f.toString());
            var2.printStackTrace();
        }

    }

    public static boolean hasSolidSide(BlockGetter world, BlockPos pos, Direction side) {
        BlockState state = world.getBlockState(pos);
        return Block.isFaceFull(state.getBlockSupportShape(world, pos), side);
    }

    public static <E extends Enum<E>, T> Map<T, E> createKeyedEnumMap(E[] values, Function<E, T> keyGetter){
        return Arrays.stream(values).collect(Collectors.toMap(keyGetter, (type) ->
                type
        ));
    }
}
