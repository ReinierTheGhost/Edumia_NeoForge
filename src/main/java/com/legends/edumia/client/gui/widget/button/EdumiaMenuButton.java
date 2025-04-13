package com.legends.edumia.client.gui.widget.button;

import com.legends.edumia.client.gui.EdumiaMasterMenuScreen;
import com.legends.edumia.client.gui.EdumiaMenuScreen;
import com.legends.edumia.client.gui.map.EdumiaMapScreen;
import com.legends.edumia.utils.EdumiaLog;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EdumiaMenuButton extends Button {
    public static final CreateNarration DEFAULT_NARRATION = (p_253298_) -> {
        return p_253298_.get();
    };

    private final Class<? extends EdumiaMenuScreen> menuScreenClass;
    private final int verticalIconNumber;
    public final int menuKeyCode;
    public EdumiaMenuButton(int xIn, int yIn, Class<? extends EdumiaMenuScreen> cls, Component text, int vertNumber, int key,
                            CreateNarration narration) {
        super(xIn, yIn, 32, 32, text, (button) ->{
            ((EdumiaMenuButton) button).openMenuScreen();
        }, narration);
        this.menuScreenClass = cls;
        this.verticalIconNumber = vertNumber;
        this.menuKeyCode = key;
    }


    public void openMenuScreen(){
        if (this.menuScreenClass != null){
            try {
                EdumiaMenuScreen screen = (EdumiaMenuScreen) this.menuScreenClass.newInstance();
                Minecraft.getInstance().setScreen(screen);
                EdumiaMasterMenuScreen.lastMenuScreen = screen.getClass();
            }catch (Exception var2){
                EdumiaLog.error("Error opening menu button screen");
                var2.printStackTrace();
            }
        }

    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float f) {
        Minecraft minecraft = Minecraft.getInstance();
        Font fr = minecraft.font;
        RenderSystem.clearColor(1.0f, 1.0f, 1.0f, this.alpha);
        guiGraphics.blit(EdumiaMasterMenuScreen.MENU_ICONS, this.x, this.y,
                (this.active ? 0 : this.width * 2) + (this.isHovered() ? this.width : 0),
                this.verticalIconNumber * this.height, this.width, this.height);
    }

}
