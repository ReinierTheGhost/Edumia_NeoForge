package com.legends.edumia.entity.animals.dragonfly;

import com.legends.edumia.entity.animals.butterfly.ButterflyVariant;

import java.util.Arrays;
import java.util.Comparator;

public enum DragonflyVariant {
    BLUE_MORPHO(0),
    BROAD_TAILED_SHADOWDRAGON(1),
    GREEN_DARNER(2),
    YELLOW_WINGED_DARTER(3);

    private static final DragonflyVariant[] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt(DragonflyVariant::getId)).toArray(DragonflyVariant[]::new);

    private final int id;

    DragonflyVariant(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static DragonflyVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }

}
