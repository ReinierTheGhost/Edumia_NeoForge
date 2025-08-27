package com.legends.edumia.entity.races.gensai;

import java.util.Arrays;
import java.util.Comparator;

public enum GensaiVariant {
    MALE_1(0);

    private static final GensaiVariant[] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt(GensaiVariant::getId)).toArray(GensaiVariant[]::new);

    private final int id;

    GensaiVariant(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static GensaiVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
