package com.legends.edumia.entity.races.human;

import com.legends.edumia.entity.races.fairy.FairyVariant;

import java.util.Arrays;
import java.util.Comparator;

public enum HumanVariant {

    FEMALE_1(0),
    MALE_1(1);

    private static final HumanVariant[] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt(HumanVariant::getId)).toArray(HumanVariant[]::new);

    private final int id;

    HumanVariant(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static HumanVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
