package com.legends.edumia.entity.animals.butterfly;

import java.util.Arrays;
import java.util.Comparator;

public enum ButterflyVariant {

    BLUE_MORPHO(0),
    MONARCH(1),
    PURPLE_EMPEROR(2),
    RED_ADMIRAL(3),
    SULPHUR(4),
    SWALLOWTAIL(5);

    private static final ButterflyVariant[] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt(ButterflyVariant::getId)).toArray(ButterflyVariant[]::new);

    private final int id;

    ButterflyVariant(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static ButterflyVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
