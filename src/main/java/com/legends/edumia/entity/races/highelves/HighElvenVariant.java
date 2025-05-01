package com.legends.edumia.entity.races.highelves;

import java.util.Arrays;
import java.util.Comparator;

public enum HighElvenVariant {
    BLOND_HEAR_FEMALE(0),
    RED_HEAR_MALE(1);
//    PURPLE_HEAR_MAN(1)

    private static final HighElvenVariant[] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt(HighElvenVariant::getId)).toArray(HighElvenVariant[]::new);

    private final int id;

    HighElvenVariant(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static HighElvenVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
