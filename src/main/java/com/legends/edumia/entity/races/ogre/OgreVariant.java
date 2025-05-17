package com.legends.edumia.entity.races.ogre;

import com.legends.edumia.entity.races.orc.OrcVariant;

import java.util.Arrays;
import java.util.Comparator;

public enum OgreVariant {
    RED_MALE(0);

    private static final OgreVariant[] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt(OgreVariant::getId)).toArray(OgreVariant[]::new);

    private final int id;

    OgreVariant(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static OgreVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
