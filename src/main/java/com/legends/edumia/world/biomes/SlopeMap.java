package com.legends.edumia.world.biomes;

import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.function.Supplier;

public class SlopeMap {
    public ArrayList<SlopeData> slopeDatas;

    public SlopeMap(SlopeMap slopeMap) {
        this.slopeDatas = new ArrayList<>(slopeMap.slopeDatas);
    }

    public SlopeMap() {
        this.slopeDatas = new ArrayList<>();
    }

    public SlopeMap addSlopeData(float angle, Supplier<Block> block) {
        return addSlopeData(angle, block.get());
    }
    public SlopeMap addSlopeData(float angle, Block block) {
        if(!slopeDatas.isEmpty()) {
            int last = slopeDatas.size() - 1;
            float newAngle = slopeDatas.get(last).angle;
            if (newAngle >= angle) {
                throw new ArithmeticException("Cannot add slope angle smaller than previous slope data");
            } else if(newAngle < 0 || newAngle > 90) {
                throw new ArithmeticException("The new slope cannot exceed slope angle boundaries (0 to 90 degrees)");
            }
        }
        slopeDatas.add(new SlopeData(angle, block));
        return this;
    }


    public Block getBlockAtAngle(float angle) {
        for(SlopeData slopeData : slopeDatas) {
            if(angle <= slopeData.angle) {
                return slopeData.block;
            }
        }
        int last = slopeDatas.size() - 1;
        throw new RuntimeException("The angle exceeds the maximal allowed slope of: " + slopeDatas.get(last).angle + " degrees");
    }

    public class SlopeData {
        public float angle; // Maximal angle to apply the block
        public Block block;

        public SlopeData(float angle, Block block) {
            this.angle = angle;
            this.block = block;
        }
    }
}
