package com.legends.edumia.client.gui.widget.backgrounds.types;

import com.legends.edumia.Edumia;
import com.legends.edumia.client.gui.widget.UiDirections;
import net.minecraft.resources.ResourceLocation;
import org.joml.Vector2i;

public enum BackgroundContainerTypes {
    FULLSCREEN_MAP(ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID,"textures/gui/map_background.png"), 5, 1, 209);
    public final ResourceLocation textureId;

    public final int size;
    private final int uvStartX;
    private final int uvStartY;
    BackgroundContainerTypes(ResourceLocation texture, int size, int uvStartX, int uvStartY){
        this.textureId = texture;
        this.size = size;
        this.uvStartX = uvStartX;
        this.uvStartY = uvStartY;
    }

    public Vector2i getUvForDirection(UiDirections direction){
        return switch (direction) {
            case NORTH_WEST -> new Vector2i(uvStartX, uvStartY);
            case NORTH -> new Vector2i(uvStartX + size + 2, uvStartY);
            case NORTH_EAST -> new Vector2i(uvStartX + ((size + 2) * 2), uvStartY);
            case WEST -> new Vector2i(uvStartX , uvStartY + size + 2);
            case NONE -> new Vector2i(uvStartX + size + 2, uvStartY + size + 2);
            case EAST -> new Vector2i(uvStartX + ((size + 2) * 2), uvStartY + size + 2);
            case SOUTH_WEST -> new Vector2i(uvStartX, uvStartY + ((size + 2) * 2));
            case SOUTH -> new Vector2i(uvStartX + size + 2, uvStartY + ((size + 2) * 2));
            case SOUTH_EAST -> new Vector2i(uvStartX + ((size + 2) * 2), uvStartY + ((size + 2) * 2));
        };
    }
}
