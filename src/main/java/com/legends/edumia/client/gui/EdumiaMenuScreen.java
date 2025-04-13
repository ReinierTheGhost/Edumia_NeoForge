package com.legends.edumia.client.gui;

import com.legends.edumia.client.EdumiaKeyHandler;
import com.legends.edumia.client.gui.widget.button.LeftRightButton;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class EdumiaMenuScreen extends BasicIngameScreen{
    public int xSize = 200;
    public int ySize = 256;
    public int guiLeft;
    public int guiTop;
    protected Button buttonMenuReturn;
    public EdumiaMenuScreen(Component titleIn) {
        super(titleIn);
    }

    @Override
    protected void init() {
        super.init();
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
        int buttonW = 100;
        int buttonH = 20;
        int buttonGap = 40;
//        this.buttonMenuReturn = (Button)this.addWidget(new LeftRightButton(0, this.guiTop + (this.ySize + buttonH) / 4, buttonW,
//                buttonH, true, Component.translatable("gui.edumia.menu.return"), (b) ->
//                this.minecraft.setScreen(new EdumiaMasterMenuScreen())
//        ));
//        this.buttonMenuReturn.x = Math.max(0, this.guiLeft = buttonGap - buttonW);
    }

    @Override
    public boolean keyPressed(int key, int scan, int param3) {
        if (EdumiaKeyHandler.MENU_KEY.matches(key, scan)){
            this.minecraft.setScreen(new EdumiaMasterMenuScreen());
            return true;
        }else {
            return super.keyPressed(key, scan, param3);
        }
    }
}
