package com.legends.edumia.entity.races.darkelves;

import java.util.Arrays;
import java.util.Comparator;

public enum DarkElvenVariant {
    WHITE_HEAR_MALE(0);

    private static final DarkElvenVariant[] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt(DarkElvenVariant::getId)).toArray(DarkElvenVariant[]::new);

    private final int id;

    DarkElvenVariant(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static DarkElvenVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
