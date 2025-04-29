package com.legends.edumia.client.gui.map;

import com.legends.edumia.Edumia;
import com.legends.edumia.EdumiaClientConfig;
import com.legends.edumia.client.EdumiaKeyHandler;
import com.legends.edumia.client.gui.EdumiaMasterMenuScreen;
import com.legends.edumia.client.gui.EdumiaMenuScreen;
import com.legends.edumia.client.gui.widget.ModWidget;
import com.legends.edumia.client.gui.widget.backgrounds.BackgroundContainerWidget;
import com.legends.edumia.client.gui.widget.backgrounds.types.BackgroundContainerTypes;
import com.legends.edumia.client.gui.widget.map.FullscreenToggeableMapWidget;
import com.legends.edumia.network.packets.C2S.PacketTeleportToDynamicWorldCoordinate;
import com.legends.edumia.utils.EdumiaLog;
import com.legends.edumia.utils.ModColors;
import com.legends.edumia.world.biomes.surface.MapBasedCustomBiome;
import com.legends.edumia.world.dimension.ModDimensions;
import com.legends.edumia.world.map.EdumiaMapConfigs;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.network.PacketDistributor;
import org.joml.Vector2d;
import org.joml.Vector2i;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class EdumiaNewMapScreen extends EdumiaMenuScreen {
    private static final ResourceLocation BACKGROUND_TEXTURE = ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID,"textures/gui/map_background.png");
    private static final ResourceLocation MAP_UI_TEXTURE = ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID,"textures/gui/new_map_ui.png");
    private static final ResourceLocation MAP_TEXTURE = ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID,"textures/edumia_map_3000.png");

    private static final Component MAP_TITLE_TEXT = Component.translatable("ui." + Edumia.MOD_ID + ".map_screen.map_title_text");
    private static final Vector2i NORMAL_BUTTON_SIZE = new Vector2i(15, 15);

    BackgroundContainerWidget backgroundContainerWidget;
    private FullscreenToggeableMapWidget mapWidget;
    private static final int WIDTH = 208;
    private static final int HEIGHT = 208;
    public static final int MARGIN = 5;
    private static boolean isFullscreen = true;

    private static int startX = 0;
    private static int endX = 0;
    private static int startY = 0;
    private static int endY = 0;

    private Button fullscreenButton;
    private Button overlayToggleButton;
    private Button zoomInButton;
    private Button zoomOutButton;
    private Button recenterButton;
    private Button backToMenuButton;

    private int mouseX, mouseY;
    AbstractClientPlayer player;


    public EdumiaNewMapScreen() {
        super(MAP_TITLE_TEXT);
        backgroundContainerWidget = new BackgroundContainerWidget(BackgroundContainerTypes.FULLSCREEN_MAP);
    }

    @Override
    protected void init() {
        Entity cameraEntity = this.minecraft.getCameraEntity();
        if (cameraEntity instanceof AbstractClientPlayer abstractClientPlayer){
            this.player = abstractClientPlayer;
        }else {
            EdumiaLog.logError("EdumiaMapScreen::Init:Couldn't find player");
        }

        mapWidget = new FullscreenToggeableMapWidget(WIDTH - (MARGIN * 2), HEIGHT - (MARGIN * 2));

        this.addRenderableWidget(new StringWidget(0, 0,
                this.width, 9, MAP_TITLE_TEXT, this.font));

        backToMenuButton = Button.builder(Component.translatable("ui." + Edumia.MOD_ID + ".map_screen.button.back_to_menu"),
                x ->
                    this.minecraft.setScreen(new EdumiaMasterMenuScreen())).build();
        backToMenuButton.setSize(NORMAL_BUTTON_SIZE.x,NORMAL_BUTTON_SIZE.y);
        addRenderableWidget(backToMenuButton);

        fullscreenButton = Button.builder(Component.translatable("ui." + Edumia.MOD_ID + ".map_screen.button.fullscreen_toggle"),
                x -> {
            isFullscreen = !isFullscreen;
        }).build();
        fullscreenButton.setSize(NORMAL_BUTTON_SIZE.x,NORMAL_BUTTON_SIZE.y);
        addRenderableWidget(fullscreenButton);

        overlayToggleButton = Button.builder(Component.translatable("ui." + Edumia.MOD_ID + ".map_screen.button.map_overlay_toggle"),
                x -> {
            mapWidget.setOverlayState(!mapWidget.isOverlayEnabled());
        }).build();
        overlayToggleButton.active = EdumiaClientConfig.ENABLE_MAP_OVERLAY.get();
        overlayToggleButton.setSize(NORMAL_BUTTON_SIZE.x,NORMAL_BUTTON_SIZE.y);
        addRenderableWidget(overlayToggleButton);

        recenterButton = Button.builder(Component.translatable("ui." + Edumia.MOD_ID + ".map_screen.button.recenter_on_player"),
                x -> {
                    Vector2d playerCoords = new Vector2d(player.position().x(), player.position().z());
                    playerCoords.x /= EdumiaMapConfigs.FULL_MAP_SIZE;
                    playerCoords.y /= EdumiaMapConfigs.FULL_MAP_SIZE;
                    mapWidget.instantCenterOnRatio(playerCoords);
                }).build();
        recenterButton.setSize(NORMAL_BUTTON_SIZE.x, NORMAL_BUTTON_SIZE.y);
        addRenderableWidget(recenterButton);

        zoomInButton = Button.builder(Component.translatable("ui." + Edumia.MOD_ID + ".map_screen.button.zoom_in"), x ->{
            mapWidget.zoomClick();
        }).build();
        zoomInButton.setSize(NORMAL_BUTTON_SIZE.x,NORMAL_BUTTON_SIZE.y);
        addRenderableWidget(zoomInButton);

        zoomOutButton = Button.builder(Component.translatable("ui." + Edumia.MOD_ID + ".map_screen.button.zoom_out"), x ->{
            mapWidget.dezoomClick();
        }).build();
        zoomOutButton.setSize(NORMAL_BUTTON_SIZE.x,NORMAL_BUTTON_SIZE.y);
        addRenderableWidget(zoomOutButton);
    }

    @Override
    public void render(GuiGraphics context, int mouseX, int mouseY, float partialTick) {
        if (isFullscreen){
            renderFullScreen(context);

        }else {
            renderNormal(context);
        }
        ModWidget.updateMouse(mouseX, mouseY);
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        showCursorInformationTooltip(context, mouseX, mouseY);

    }

    private void showCursorInformationTooltip(GuiGraphics context, int mouseX, int mouseY){
        if (player != null){
            Vector2d mapRatio = mapWidget.getCurrentMapRatio(mouseX, mouseY);
            if(mapRatio != null) {
                List<Component> texts = new ArrayList<>();
                texts.add(Component.translatable("ui." + Edumia.MOD_ID + ".map_screen.tooltip.coordinates_title")
                        .withStyle(ChatFormatting.UNDERLINE));
                double x = Math.round((mapRatio.x * EdumiaMapConfigs.FULL_MAP_SIZE) * 10) / 10.0;
                double z = Math.round((mapRatio.y * EdumiaMapConfigs.FULL_MAP_SIZE) * 10) / 10.0;
                texts.add(Component.translatable("ui." + Edumia.MOD_ID + ".map_screen.tooltip.coordinates_label")
                        .withStyle(ChatFormatting.GRAY)
                        .append(Component.translatable("ui." + Edumia.MOD_ID + ".map_screen.tooltip.coordinates_content", x, z)
                                .withStyle(ChatFormatting.WHITE)));

                MapBasedCustomBiome biome = mapWidget.getBiomeAt((int) (mapRatio.x * EdumiaMapConfigs.REGION_SIZE),
                        (int) (mapRatio.y * EdumiaMapConfigs.REGION_SIZE));
                texts.add(Component.translatable("ui." + Edumia.MOD_ID + ".map_screen.tooltip.biome_label")
                        .withStyle(ChatFormatting.GRAY)
                        .append(Component.translatable("ui." + Edumia.MOD_ID + ".map_screen.tooltip.biome_content",
                                Component.translatable(biome.getBiome().getBiomeResourceKey().location().toLanguageKey("biome")))
                                .withStyle(ChatFormatting.WHITE)));
                if(player.hasPermissions(2)){
                    texts.add(Component.translatable("ui." + Edumia.MOD_ID + ".map_screen.tooltip.teleport_keybind",
                            EdumiaKeyHandler.TELEPORT_KEY.getTranslatedKeyMessage().getString()).withStyle(ChatFormatting.ITALIC)
                            .withColor(ModColors.PENDING.color));
                }
                context.renderComponentTooltip(font, texts, mouseX, mouseY);
            }
        }
    }

    private void teleportToCursor(double mouseX, double mouseY) {
        if(!player.hasPermissions(2))
            return;
        Vector2d mapRatio = mapWidget.getCurrentMapRatio(mouseX, mouseY);
        if(mapRatio != null){
            double x = mapRatio.x * EdumiaMapConfigs.FULL_MAP_SIZE;
            double y = mapRatio.y * EdumiaMapConfigs.FULL_MAP_SIZE;

            PacketDistributor.sendToServer(new PacketTeleportToDynamicWorldCoordinate(x, y));
            this.onClose();
        }
    }

    private void renderFullScreen(GuiGraphics context){
        startX = MARGIN;
        endX = context.guiWidth() - MARGIN;
        startY = MARGIN;
        endY = context.guiHeight() - MARGIN;

        // TODO : Draw dynamic background : context.drawTexture(BACKGROUND_TEXTURE, startX, startY, 0, 0,  WIDTH, HEIGHT);
        backgroundContainerWidget.draw(context, 0, 0, context.guiWidth(), context.guiHeight());

        mapWidget.drawFullscreen(context, 17);
        drawFullscreenToggleButton(context);
        drawMapOverlayToggleButton(context);
        drawRecenterButton(context);
        drawBackToButton(context);
        drawZoomButtons(context);
        drawPlayer(context, player);
    }

    private void renderNormal(GuiGraphics context){
        int centerX = context.guiWidth() / 2;
        startX = centerX - (WIDTH / 2);
        endX = centerX + (WIDTH / 2);
        startY = (context.guiHeight() / 2) - (HEIGHT / 2);
        endY = startY + HEIGHT;


        context.blit(BACKGROUND_TEXTURE, startX, startY, 0, 0,  WIDTH, HEIGHT);
        mapWidget.drawCentered(context, centerX, startY + MARGIN);
        drawFullscreenToggleButton(context);
        drawMapOverlayToggleButton(context);
        drawRecenterButton(context);
        drawBackToButton(context);
        drawZoomButtons(context);
        drawPlayer(context, player);
    }

    private void drawPlayer(GuiGraphics context, AbstractClientPlayer player){
        if(!ModDimensions.isInEdumia(player.level()))
            return;
        Vector2d playerRatio = mapWidget.getMapPointFromWorldCoordinate(new Vector2d(player.position().x(), player.position().z()));
        int margin = (isFullscreen) ? 0 : MARGIN;
        double x = Math.max(startX + margin + 4, Math.min(endX - 4 - ((isFullscreen) ? NORMAL_BUTTON_SIZE.x : MARGIN), playerRatio.x));
        double y = Math.max(startY + margin + 4, Math.min(endY - 4 - margin, playerRatio.y));


        // TODO (?) : show the head?
        //PlayerSkinDrawer.draw(context, player.getSkinTextures(), (int)x, (int)y, 4);
        //PlayerSkinDrawer.draw(context, minecraft.getSkinProvider().getSkinTexturesSupplier(new GameProfile(UUID.fromString(this.uuid),this.name)).get(),x,y);

//        context.blit(MAP_UI_TEXTURE, (int)x- 4, (int) y- 4, 154, 1, 8, 8);
        context.blit(this.player.getSkin().texture(),
                (int) (x - 4),
                (int) (y - 4),
                8, 8, 8, 8, 64, 64);
    }
    private void drawBackToButton(GuiGraphics context){

        int overlayToggleButtonUvY = 1;
        if(!backToMenuButton.active)
            overlayToggleButtonUvY = 86;
        int x;
        int y;
        if (isFullscreen){
            x = 1;
            y = 1;
        } else {
            x = startX - NORMAL_BUTTON_SIZE.x;
            y = startY;
        }

        backToMenuButton.setPosition(x, y);
        context.blit(MAP_UI_TEXTURE, x, y, 103, overlayToggleButtonUvY, NORMAL_BUTTON_SIZE.x, NORMAL_BUTTON_SIZE.y);
    }

    private void drawFullscreenToggleButton(GuiGraphics context){
        int fullscreenToggleButtonUvY = ((ModWidget.isMouseOver(fullscreenButton) || fullscreenButton.isFocused()) ? 18 : 1);
        if(!fullscreenButton.active)
            fullscreenToggleButtonUvY = 35;
        if(isFullscreen){
            int x = backToMenuButton.x + NORMAL_BUTTON_SIZE.x;
            int y = backToMenuButton.getY();
            fullscreenButton.setPosition(x, y);
            context.blit(MAP_UI_TEXTURE, x, y, 35, fullscreenToggleButtonUvY, NORMAL_BUTTON_SIZE.x, NORMAL_BUTTON_SIZE.y);
        } else {
            fullscreenButton.setPosition(endX, startY);
            context.blit(MAP_UI_TEXTURE, endX, startY, 18, fullscreenToggleButtonUvY, NORMAL_BUTTON_SIZE.x, NORMAL_BUTTON_SIZE.y);
        }
    }

    private void drawMapOverlayToggleButton(GuiGraphics context){
        int overlayToggleButtonUvY = (ModWidget.isMouseOver(overlayToggleButton) || overlayToggleButton.isFocused()) ? 69 : 52;
        if(!overlayToggleButton.active)
            overlayToggleButtonUvY = 86;

        int x = fullscreenButton.getX() + NORMAL_BUTTON_SIZE.x;
        int y = fullscreenButton.getY();
        overlayToggleButton.setPosition(x, y);
        context.blit(MAP_UI_TEXTURE, x, y, 1, overlayToggleButtonUvY, NORMAL_BUTTON_SIZE.x, NORMAL_BUTTON_SIZE.y);
    }

    private void drawRecenterButton(GuiGraphics context){
        recenterButton.active = (ModDimensions.isInEdumia(player.level()));

        int recenterButtonUvY = (ModWidget.isMouseOver(recenterButton) || recenterButton.isFocused()) ? 18 : 1;
        if(!recenterButton.active)
            recenterButtonUvY = 35;

        int x = zoomInButton.getX();
        int y = zoomInButton.getY() - NORMAL_BUTTON_SIZE.y;
        recenterButton.setPosition(x, y);
        context.blit(MAP_UI_TEXTURE, x, y, 52, recenterButtonUvY, NORMAL_BUTTON_SIZE.x, NORMAL_BUTTON_SIZE.y);
    }



    private void drawZoomButtons(GuiGraphics context){
        int zoomInButtonUvX = 86;
        int zoomInButtonUvY;
        if (ModWidget.isMouseOver(zoomInButton)){
            zoomInButtonUvY = 18;
        }else {
            zoomInButtonUvY = 1;
        }
        zoomInButton.active = mapWidget.canZoomIn();
        if(!zoomInButton.active)
            zoomInButtonUvY = 35;

        int zoomOutButtonUvX = 69;
        int zoomOutButtonUvY;
        if (ModWidget.isMouseOver(zoomOutButton)){
            zoomOutButtonUvY = 18;
        } else {
            zoomOutButtonUvY = 1;
        }
        zoomOutButton.active = mapWidget.canZoomOut();
        if(!zoomOutButton.active)
            zoomOutButtonUvY = 35;

        if(isFullscreen){
            // Zoom out
            int x = context.guiWidth() - 1 - NORMAL_BUTTON_SIZE.x;
            int y = context.guiHeight() - 16 - NORMAL_BUTTON_SIZE.y;
            zoomOutButton.setPosition(x, y);
            context.blit(MAP_UI_TEXTURE, x, y, zoomOutButtonUvX, zoomOutButtonUvY, NORMAL_BUTTON_SIZE.x, NORMAL_BUTTON_SIZE.y);
            // Zoom in
            y -= NORMAL_BUTTON_SIZE.y;
            zoomInButton.setPosition(x, y);
            context.blit(MAP_UI_TEXTURE, x, y, zoomInButtonUvX, zoomInButtonUvY, NORMAL_BUTTON_SIZE.x, NORMAL_BUTTON_SIZE.y);
        } else {
            // Zoom out
            int y = endY - NORMAL_BUTTON_SIZE.y;
            zoomOutButton.setPosition(endX, y);
            context.blit(MAP_UI_TEXTURE, endX, y, zoomOutButtonUvX, zoomOutButtonUvY, NORMAL_BUTTON_SIZE.x, NORMAL_BUTTON_SIZE.y);
            // Zoom in
            y -= NORMAL_BUTTON_SIZE.y;
            zoomInButton.setPosition(endX, y);
            context.blit(MAP_UI_TEXTURE, endX, y, zoomInButtonUvX, zoomInButtonUvY, NORMAL_BUTTON_SIZE.x, NORMAL_BUTTON_SIZE.y);
        }
    }


    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if(EdumiaKeyHandler.TELEPORT_KEY.matches(keyCode, modifiers)){
            teleportToCursor(mouseX, mouseY);
            return true;
        }
        if(EdumiaKeyHandler.FULLSCREEN_KEY.matches(keyCode, modifiers)){
            isFullscreen = !isFullscreen;
        }
        if(keyCode == KeyEvent.VK_CODE_INPUT && !ModWidget.getFocusEnabled()){
            ModWidget.enableFocus(true);
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        mapWidget.mouseClicked(mouseX, mouseY, button);
        if(EdumiaKeyHandler.TELEPORT_KEY.matchesMouse(button)){
            teleportToCursor(mouseX, mouseY);
            return true;
        }
        if(EdumiaKeyHandler.FULLSCREEN_KEY.matchesMouse(button)){
            isFullscreen = !isFullscreen;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        mapWidget.mouseDragged(mouseX, mouseY, button, dragX, dragY);
        return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        mapWidget.mouseReleased(mouseX, mouseY, button);
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        mapWidget.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
        return super.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
    }
}
