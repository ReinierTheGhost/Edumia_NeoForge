package com.legends.edumia.client.gui.widget.map;

import com.legends.edumia.client.gui.widget.ModWidget;
import net.minecraft.client.gui.components.Button;

import java.awt.geom.Rectangle2D;

public class MapMarkerWidget extends ModWidget {

    private boolean isSelected;

    public MapMarkerWidget(String name, Button.OnPress onPress, Rectangle2D border){
        super();

        isSelected = false;
    }
    public void setSelected(boolean state) {
        isSelected = state;
    }
}
