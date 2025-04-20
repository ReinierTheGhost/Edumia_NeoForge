package com.legends.edumia.client.gui.widget.backgrounds;

import com.legends.edumia.client.gui.widget.ModWidget;
import com.legends.edumia.client.gui.widget.UiDirections;
import com.legends.edumia.client.gui.widget.backgrounds.types.BackgroundContainerTypes;
import net.minecraft.client.gui.GuiGraphics;
import org.joml.Vector2i;

public class BackgroundContainerWidget  extends ModWidget {
    BackgroundContainerTypes type;
    public BackgroundContainerWidget(BackgroundContainerTypes type){
        this.type = type;
    }

    public void draw(GuiGraphics context, int startX, int startY, int sizeX, int sizeY){
        // TODO : Find a fix for looping texture efficiently

        int size = type.size;
        // NORTH WEST
        Vector2i uv = type.getUvForDirection(UiDirections.NORTH_WEST);
        context.blit(type.textureId, startX, startY, uv.x, uv.y, size, size);

        // NORTH EAST
        uv = type.getUvForDirection(UiDirections.NORTH_EAST);
        context.blit(type.textureId, startX + sizeX - size, startY, uv.x, uv.y, size, size);

        // SOUTH WEST
        uv = type.getUvForDirection(UiDirections.SOUTH_WEST);
        context.blit(type.textureId, startX, startY + sizeY - size, uv.x, uv.y, size, size);

        // SOUTH EAST
        uv = type.getUvForDirection(UiDirections.SOUTH_EAST);
        context.blit(type.textureId, startX + sizeX - size, startY + sizeY - size, uv.x, uv.y, size, size);

        // NORTH/SOUTH
        Vector2i newUv = type.getUvForDirection(UiDirections.NORTH);
        uv = type.getUvForDirection(UiDirections.SOUTH);
        for(int x = startX + size; x < startX + sizeX - (size * 2); x += size) {
            // NORTH
            context.blit(type.textureId, x, startY, newUv.x, newUv.y, size, size + 12);
            // SOUTH
            context.blit(type.textureId, x, startY + sizeY - size - 12, uv.x, uv.y, size, size + 12);
        }
        // NORTH
        context.blit(type.textureId, startX + sizeX - (size * 2), startY, newUv.x, newUv.y, size, size + 12);
        // SOUTH
        context.blit(type.textureId, startX + sizeX - (size * 2), startY + sizeY - size - 12, uv.x, uv.y, size, size + 12);

        // WEST/EAST
        uv = type.getUvForDirection(UiDirections.WEST);
        newUv = type.getUvForDirection(UiDirections.EAST);
        for(int y = startY + size; y < startY + sizeY - (size * 2); y += size) {
            // WEST
            context.blit(type.textureId, startX, y, uv.x, uv.y, size + 12, size);
            // EAST
            context.blit(type.textureId, startX + sizeX - size - 12, y, newUv.x, newUv.y, size + 12, size);
        }
        // WEST
        context.blit(type.textureId, startX, startY + sizeY - (size * 2), uv.x, uv.y, size + 12, size);
        // EAST
        context.blit(type.textureId, startX + sizeX - size - 12, startY + sizeY - (size * 2), newUv.x, newUv.y, size + 12, size);
    }
}
