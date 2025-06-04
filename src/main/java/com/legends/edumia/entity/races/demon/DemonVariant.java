package com.legends.edumia.entity.races.demon;


import java.util.Arrays;
import java.util.Comparator;

public enum DemonVariant {
    RED_MALE(0);

    private static final DemonVariant[] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt(DemonVariant::getId)).toArray(DemonVariant[]::new);

    private final int id;

    DemonVariant(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static DemonVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
