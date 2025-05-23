package com.legends.edumia.client.gui.widget.map;

import com.legends.edumia.Edumia;
import com.legends.edumia.client.gui.widget.ModWidget;
import com.legends.edumia.client.gui.widget.UiDirections;
import com.legends.edumia.client.gui.widget.map.types.MapArrowType;
import com.legends.edumia.client.gui.widget.map.types.MapMarkerType;
import com.legends.edumia.utils.ModColors;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.joml.Vector2d;
import org.joml.Vector2i;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MapMarkerWidget extends ModWidget {

    private static final ResourceLocation MAP_MARKERS = ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID,
            "textures/gui/widget/map_markers.png");
    private static final ResourceLocation MAP_ARROWS = ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID,
            "textures/gui/widget/map_arrows.png");

    private Button markerButton;
    private MapMarkerType type;
    private MapArrowType arrowType;
    private UiDirections arrowDirection;
    private Vector2i runtimeStartCoordinates = null;
    private boolean isArrow;
    private boolean isSelected;
    private static Component TITLE;
    private List<Component> content;
    private List<MapMarkerWidget> childs = new ArrayList<>();
    private Rectangle2D borders;
    public MapMarkerWidget(String name, Button.OnPress onPress, Rectangle2D borders) {
        super();
        markerButton = Button.builder(Component.nullToEmpty(name), onPress).build();
        type = MapMarkerType.NONE;
        arrowType = MapArrowType.NORMAL;
        isArrow = false;
        isSelected = false;
        this.borders = borders;
    }

    public void setType(MapMarkerType type){
        this.type = type;
    }

    public Button getButton() {
        return markerButton;
    }

    public void computeFromWorldPosition(MapWidget mapWidget, Vector2d worldPosition){
        Vector2d uvs = mapWidget.getMapPointFromWorldCoordinate(worldPosition);
        computeCentered(mapWidget, uvs);
    }

    public void computeFromMapPosition(MapWidget mapWidget, Vector2d mapPosition){
        Vector2d uvs = mapWidget.getMapPointFromMapCoordinate(mapPosition);
        computeCentered(mapWidget, uvs);
    }

    private void computeCentered(MapWidget mapWidget, Vector2d centerUvs){
        if(centerUvs == null) return;
        UiDirections outOfBoundDirection = mapWidget.isOutsideBounds(centerUvs,
                type.size.x /2, type.size.y / 2, borders);

        centerUvs.x -= type.size.x / 2.0;
        centerUvs.y -= type.size.y / 2.0;
        if(outOfBoundDirection == UiDirections.NONE) {
            computeMarker(centerUvs);
            return;
        }
        computeArrow(mapWidget, outOfBoundDirection, centerUvs);
    }

    protected void computeArrow(MapWidget mapWidget, UiDirections direction, Vector2d starts) {
        isArrow = true;

        int sizeX = arrowType.size.x;
        int sizeY = arrowType.size.y;

        // Hardcoded, since values are uneven, some randomness can happen
        int minX = (int) (mapWidget.startX + borders.getX() - (sizeX / 2) + 1);
        int minY = (int) (mapWidget.startY + borders.getY() - (sizeX / 2) + 1);
        int maxX = (int) (mapWidget.startX + borders.getX() + borders.getWidth() - sizeX - (sizeX / 4));
        int maxY = (int) (mapWidget.startY + borders.getY() + borders.getHeight() - sizeY - (sizeY / 4));

        runtimeStartCoordinates = new Vector2i(
                Math.min(maxX, Math.max(minX, (int) starts.x)),
                Math.min(maxY, Math.max(minY, (int) starts.y)));
        arrowDirection = direction;
    }

    protected void computeMarker(Vector2d starts) {
        isArrow = false;

        runtimeStartCoordinates = new Vector2i((int) starts.x, (int) starts.y);
    }

    public void draw(GuiGraphics context) {
        Vector2i drawStart = new Vector2i(runtimeStartCoordinates.x, runtimeStartCoordinates.y);
        Vector2i drawSize = type.size;
        Vector2i drawUvs = type.uvs;

        Vector2i focusedStart = drawStart;
        Vector2i focusedSize = type.size;
        Vector2i focusedUvs = type.focusedUvs;

        Vector2i buttonSize = type.interactableSize;

        // Only change to arrow if it's an arrow
        if(isArrow){
            // Update start
            Vector2i size = arrowType.size;
            drawStart.x += (drawSize.x / 2) - (size.x / 2);
            drawStart.y += (drawSize.y / 2) - (size.y / 2);
            // Update size
            drawSize = size;
            // Update uvs
            drawUvs = arrowType.getUvs(arrowDirection);
            drawUvs.x = 1;
            // Update focused to be arrow specific
            focusedStart = new Vector2i(drawStart.x, drawStart.y);
            focusedSize = size;
            focusedUvs = arrowType.getFocusedUvs(arrowDirection);
            // Update button size
            buttonSize = size;
        }

        Vector2i buttonStartCoordinates = new Vector2i(drawStart.x, drawStart.y);
        buttonStartCoordinates.x += drawSize.x / 2 - buttonSize.x / 2;
        buttonStartCoordinates.y += drawSize.y / 2 - buttonSize.y / 2;

        markerButton.setRectangle(buttonSize.x, buttonSize.y, buttonStartCoordinates.x, buttonStartCoordinates.y);
        if(!markerButton.active)
            activateButton(true);

        boolean isFocused = markerButton.isFocused();
        boolean mouseIsOver = isMouseOver(buttonSize.x, buttonSize.y, buttonStartCoordinates.x, buttonStartCoordinates.y);

        boolean forceSelectedVisual = false;
        for(MapMarkerWidget markerWidget : childs){
            if(markerWidget.isSelected){
                forceSelectedVisual = true;
                break;
            }
        }

        if(isFocused || (isSelected || forceSelectedVisual) || mouseIsOver){
            if(isArrow)
                drawUvs = arrowType.getHoveredUvs(arrowDirection);
            else
                drawUvs = type.hoveredUvs;
            if(!getFocusEnabled())
                isFocused = false;
        }
        if(mouseIsOver){
            int maxChildDisplay = 2;
            List<Component> texts = new ArrayList<>();
            boolean haveChilds = childs != null && !childs.isEmpty();
            if(haveChilds)
                texts.add(TITLE);
            texts.addAll(getContent());
            if(haveChilds){
                for(int i = 0; i < childs.size() && i < maxChildDisplay; i++){
                    texts.addAll(childs.get(i).getContent());
                }
                if(childs.size() > maxChildDisplay){
                    texts.add(Component.translatable("widget.edumia.marker.more").withStyle(ChatFormatting.BLUE));
                }
            }
            context.renderTooltip(client.font, texts, Optional.empty(), drawStart.x + (drawSize.x / 2), drawStart.y + (drawSize.y / 2));
        }

        // draw marker or arrow
        context.blit((isArrow) ? MAP_ARROWS : MAP_MARKERS,
                drawStart.x, drawStart.y,
                drawUvs.x, drawUvs.y,
                drawSize.x, drawSize.y
        );

        // draw marker or arrow (FOCUSED)
        if(isFocused || ((isSelected || forceSelectedVisual) && getFocusEnabled())){
            context.blit((isArrow) ? MAP_ARROWS : MAP_MARKERS,
                    focusedStart.x, focusedStart.y,
                    focusedUvs.x, focusedUvs.y,
                    focusedSize.x, focusedSize.y
            );
        }
    }

    private List<Component> getContent() {
        if(isSelected){
            List<Component> modifiedList = new ArrayList<>();
            modifiedList.add(
                    Component.translatable("widget.edumia.marker.selected_title_container.before")
                            .append(content.get(0).copy().withColor(ModColors.SUCCESS.color))
                            .append(Component.translatable("widget.edumia.marker.selected_title_container.after")));
            for(int i = 1; i < content.size(); i++){
                modifiedList.add(content.get(i));
            }
            return modifiedList;
        }
        return this.content;
    }

    public static void setTitle(Component title){
        TITLE = title;
    }
    public void setContent(List<Component> content){
        this.content = content;
    }

    public void activateButton(boolean state){
        this.markerButton.active = state;
    }

    public Vector2i getCenterCoordinates() {
        Vector2i startCoordinates = runtimeStartCoordinates;
        Vector2i size = (isArrow) ? arrowType.size : type.size;

        startCoordinates.x += size.x / 2;
        startCoordinates.y += size.y / 2;
        return startCoordinates;
    }

    public void assignNewCenter(Vector2i newCenter) {
        Vector2i size = (isArrow) ? arrowType.size : type.size;
        this.runtimeStartCoordinates.x = newCenter.x - (size.x / 2);
        this.runtimeStartCoordinates.y = newCenter.y - (size.y / 2);
    }

    public void updateMarkerType(MapMarkerType mapMarkerType) {
        if(this.isArrow) return;

        this.type = mapMarkerType;
        Vector2i center = getCenterCoordinates();
        this.runtimeStartCoordinates = new Vector2i(center.x - mapMarkerType.size.x / 2, center.y - mapMarkerType.size.y / 2);
    }
    public void clearChild() {
        this.childs.clear();
    }
    public void addChild(MapMarkerWidget mapMarker) {
        this.childs.add(mapMarker);
    }

    public void setSelected(boolean state) {
        this.isSelected = state;
    }
}
