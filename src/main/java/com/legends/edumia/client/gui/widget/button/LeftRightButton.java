package com.legends.edumia.client.gui.widget.button;

import com.legends.edumia.Edumia;
import com.legends.edumia.client.EdumiaClientUtil;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.lang.module.ResolutionException;

@OnlyIn(Dist.CLIENT)
public class LeftRightButton extends Button {
    private static final ResourceLocation LEFT_RIGHT_BUTTON_TEXTURE = ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "textures/gui/buttons/widgets.png");
    private static final int FULL_WIDTH = 120;
    private static final int FULL_HEIGHT = 20;
    private static final int ARROW_PART_WIDTH = 16;
    private final boolean isLeftHanded;
    public LeftRightButton(int xIn, int yIn, int widthIn, int heightIn, boolean left, Component text, OnPress onPress) {
        super(xIn, yIn, widthIn, heightIn, text, onPress, DEFAULT_NARRATION);
        this.isLeftHanded = left;
    }

//    @Override
//    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float f) {
//        Minecraft mc = Minecraft.getInstance();
//        Font font = mc.font;
//        guiGraphics.setColor(1.0f, 1.0f, 1.0f, this.alpha);
//        RenderSystem.enableBlend();
//        RenderSystem.defaultBlendFunc();
//        RenderSystem.enableDepthTest();
//        int yOffset = this.getTextureY();
//        int u0 = this.isLeftHanded ? 0 : 136;
//        int v0 = yOffset * 20;
//        guiGraphics.blit(LEFT_RIGHT_BUTTON_TEXTURE, this.getX(), this.getY(), u0, v0, this.width / 2, this.height);
//        guiGraphics.blit(LEFT_RIGHT_BUTTON_TEXTURE, this.getX() + this.width / 2, this.getY(), u0 + 120 - this.width / 2, v0, this.width /2, this.height);
//
//        int textColor = this.getFGColor();
//        int textX = this.isLeftHanded ? this.getX() + 16 + (this.width - 16) / 2 : this.getX() + (this.width - 16) / 2;
//        guiGraphics.drawCenteredString(font, this.getMessage(), textX, this.getY() + (this.height - 8) / 2, EdumiaClientUtil.getRBEForFontRendering(textColor, this.alpha));
//    }
}
