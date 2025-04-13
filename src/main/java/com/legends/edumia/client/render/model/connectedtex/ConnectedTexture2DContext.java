package com.legends.edumia.client.render.model.connectedtex;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

public class ConnectedTexture2DContext {

    private final EnumSet<RelativePosition> relativePositions;

    public ConnectedTexture2DContext(Collection<RelativePosition> positions){
        this.relativePositions = EnumSet.copyOf(positions);
    }

    public boolean has(RelativePosition pos){
        return this.relativePositions.contains(pos);
    }

    @Override
    public String toString() {
        String s = "ConnectedTexture2DContext[";
        int added = 0;
        RelativePosition[] var3 = RelativePosition.values();
        int var4 = var3.length;

        for(int var5 = 0; var5 < var4; ++var5){
            RelativePosition rPos = var3[var5];
            if (this.has(rPos)){
                if (added > 0){
                    s = s + ", ";
                }

                s = s + rPos.name();
                ++added;
            }
        }

        s = s + "]";
        return s;
    }

    public Set<ConnectedTextureElement> getTextureElements(boolean includeBase){
        Set<ConnectedTextureElement> set = EnumSet.noneOf(ConnectedTextureElement.class);
        if (includeBase && this.has(RelativePosition.CENTRE)){
            set.add(ConnectedTextureElement.BASE);
        }

        boolean topLeft = this.has(RelativePosition.TOP_LEFT);
        boolean top = this.has(RelativePosition.TOP);
        boolean topRight = this.has(RelativePosition.TOP_RIGHT);
        boolean left = this.has(RelativePosition.LEFT);
        boolean right = this.has(RelativePosition.RIGHT);
        boolean bottomLeft = this.has(RelativePosition.BOTTOM_LEFT);
        boolean bottom = this.has(RelativePosition.BOTTOM);
        boolean bottomRight = this.has(RelativePosition.BOTTOM_RIGHT);
        if (!left){
            set.add(ConnectedTextureElement.SIDE_LEFT);
        }

        if (!right){
            set.add(ConnectedTextureElement.SIDE_RIGHT);
        }

        if (!top){
            set.add(ConnectedTextureElement.SIDE_TOP);
        }

        if (!bottom){
            set.add(ConnectedTextureElement.SIDE_BOTTOM);
        }

        if (!left && !top){
            set.add(ConnectedTextureElement.CORNER_TOPLEFT);
        }

        if (!right && !top){
            set.add(ConnectedTextureElement.CORNER_TOPRIGHT);
        }

        if (!left && !bottom){
            set.add(ConnectedTextureElement.CORNER_BOTTOMLEFT);
        }

        if (!right && !bottom){
            set.add(ConnectedTextureElement.CORNER_BOTTOMRIGHT);
        }

        if (left && top && !topLeft){
            set.add(ConnectedTextureElement.INVCORNER_TOPLEFT);
        }

        if (right && top && !topRight){
            set.add(ConnectedTextureElement.INVCORNER_TOPRIGHT);
        }

        if (left && bottom && !bottomLeft){
            set.add(ConnectedTextureElement.INVCORNER_BOTTOMLEFT);
        }

        if (left && bottom && !bottomRight){
            set.add(ConnectedTextureElement.INVCORNER_BOTTOMRIGHT);
        }

        return set;
    }

    public static enum RelativePosition {
        TOP_LEFT,
        TOP,
        TOP_RIGHT,
        LEFT,
        CENTRE,
        RIGHT,
        BOTTOM_LEFT,
        BOTTOM,
        BOTTOM_RIGHT;

        private RelativePosition(){

        }
    }
}
