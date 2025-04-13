package com.legends.edumia.client.render.model.scatter;

public class ScatterPositionContext {

    private final long positionHash;

    public ScatterPositionContext(long positionHash) {
        this.positionHash = positionHash;
    }

    public static ScatterPositionContext newEmptyContext(){
        return new ScatterPositionContext(0L);
    }
}
