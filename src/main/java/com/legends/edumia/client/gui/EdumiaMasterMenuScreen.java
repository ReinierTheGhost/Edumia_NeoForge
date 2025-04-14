package com.legends.edumia.client.gui;

import com.legends.edumia.Edumia;
import com.legends.edumia.client.gui.map.EdumiaMapScreen;
import com.legends.edumia.client.gui.map.EdumiaNewMapScreen;
import com.legends.edumia.client.gui.widget.button.EdumiaMenuButton;
import com.legends.edumia.world.dimension.ModDimensions;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@OnlyIn(Dist.CLIENT)
@SuppressWarnings("unchecked")
public class EdumiaMasterMenuScreen extends BasicIngameScreen{
    private Button.CreateNarration createNarration = EdumiaMenuButton.DEFAULT_NARRATION;
    public static final ResourceLocation MENU_ICONS = ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "textures/gui/buttons/menu_icons.png");
    public static Class<? extends EdumiaMenuScreen> lastMenuScreen = null;

    public EdumiaMasterMenuScreen() {
        super(Component.literal("MENU"));
    }

    @Override
    public void init(){
        super.init();
        resetLastMenuScreen();
        int midX = this.width / 2;
        int midY = this.height / 2;
        int buttonGap = 10;
        int buttonSize = 32;
        this.addRenderableWidget(new EdumiaMenuButton(this.width / 2 - 58, this.height / 2 - 37, null,
                Component.translatable("gui.edumia.achievements"),
                2, 76, this.createNarration));

        this.addRenderableWidget(new EdumiaMenuButton(this.width / 2 - 16, this.height / 2 - 37, EdumiaMapScreen.class,
                Component.translatable("gui.edumia.map"),
                3, 77, this.createNarration));
        this.addRenderableWidget(new EdumiaMenuButton(this.width / 2 + 26, this.height / 2 - 37, EdumiaNewMapScreen.class,
                Component.translatable("gui.edumia.faction"),
                4, 70, this.createNarration));

        this.addRenderableWidget(new EdumiaMenuButton(this.width / 2 - 79, this.height / 2 + 5, null,
                Component.translatable("gui.edumia.parties"),
                6, 75, this.createNarration));
        this.addRenderableWidget(new EdumiaMenuButton(this.width / 2 - 37, this.height / 2 + 5, null,
                Component.translatable("gui.edumia.titles"),
                7, 74, this.createNarration));
        this.addRenderableWidget(new EdumiaMenuButton(this.width / 2 + 5, this.height / 2 + 5, null,
                Component.translatable("gui.edumia.banners"),
                5, 73, this.createNarration));
        this.addRenderableWidget(new EdumiaMenuButton(this.width / 2 + 47, this.height / 2 + 5, null,
                Component.translatable("gui.edumia.options"),
                1, 72, this.createNarration));
        List<EdumiaMenuButton> menuButtonsToArrange = new ArrayList();
        Iterator var6 = this.buttons.iterator();

        while (var6.hasNext()){
            AbstractWidget widget = (AbstractWidget) var6.next();
            if (widget instanceof EdumiaMenuButton){
                EdumiaMenuButton menuButton = (EdumiaMenuButton) widget;
                //menuButton.active = menuButton.canDisplayMenu();
                menuButtonsToArrange.add(menuButton);
            }
        }

        int numButtons = menuButtonsToArrange.size();
        int numTopRowButtons = (numButtons - 1) / 2 + 1;
        int numBtmRowButtons = numButtons - numTopRowButtons;
        int topRowLeft = midX - (numTopRowButtons * buttonSize + (numTopRowButtons - 1) * buttonGap) / 2;
        int btmRowLeft = midX - (numBtmRowButtons * buttonSize + (numBtmRowButtons - 1) * buttonGap) / 2;

        for (int l = 0; l < numButtons; ++l){
            EdumiaMenuButton button = (EdumiaMenuButton) menuButtonsToArrange.get(l);
            if (l < numTopRowButtons){
                button.x = topRowLeft + l * (buttonSize + buttonGap);
                button.y = midY - buttonGap / 2 - buttonSize;
            }else {
                button.x = btmRowLeft + (l - numTopRowButtons) * (buttonSize + buttonGap);
                button.y = midY + buttonGap / 2;
            }
        }
    }

    public static void resetLastMenuScreen() {
        lastMenuScreen = null;
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float tick) {
        this.renderBackground(guiGraphics, mouseX, mouseY, tick);
        Component dimensionName = ModDimensions.getDisplayName(ModDimensions.getCurrentEdumiaDimensionOfFallback(this.minecraft.level));
        Component title =  Component.translatable("gui.edumia.menu", new Object[]{dimensionName});
        guiGraphics.drawString(this.font, title, (this.width / 2 - this.font.width(title) / 2), (this.height / 2 - 80), 16777215);
        super.render(guiGraphics, mouseX, mouseY, tick);
        Iterator var7 = this.buttons.iterator();

        while (var7.hasNext()){
            AbstractWidget widget = (AbstractWidget) var7.next();
            if (widget instanceof EdumiaMenuButton){
                EdumiaMenuButton menuButton = (EdumiaMenuButton) widget;
                if (menuButton.isHovered() && menuButton.getMessage() != null){
                    guiGraphics.renderTooltip(this.font, menuButton.getMessage(), mouseX, mouseY);
                }
            }
        }
    }


    @Override
    public boolean keyPressed(int key, int scan, int param3) {
        Iterator var4 = this.buttons.iterator();

        while (var4.hasNext()){
            AbstractWidget widget = (AbstractWidget) var4.next();
            if (widget instanceof EdumiaMenuButton){
                EdumiaMenuButton menuButton = (EdumiaMenuButton) widget;
                if (menuButton.visible && menuButton.active && menuButton.menuKeyCode >= 0 && key == menuButton.menuKeyCode){
                    return true;
                }
            }
        }
        return super.keyPressed(key, scan, param3);
    }

    public static Screen openMenu(Player player){
        if (lastMenuScreen != null){
            try {
                return lastMenuScreen.newInstance();
            }catch (Exception var2){
                var2.printStackTrace();
            }
        }
        return new EdumiaMasterMenuScreen();
    }
}
