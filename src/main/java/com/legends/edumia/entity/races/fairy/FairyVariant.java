package com.legends.edumia.entity.races.fairy;

import java.util.Arrays;
import java.util.Comparator;

public enum FairyVariant {
    PURPLE_HEAR_FEMALE(0);
//    PURPLE_HEAR_MAN(1)

    private static final FairyVariant[] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt(FairyVariant::getId)).toArray(FairyVariant[]::new);

    private final int id;

    FairyVariant(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static FairyVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
