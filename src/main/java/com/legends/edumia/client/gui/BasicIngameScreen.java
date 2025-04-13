package com.legends.edumia.client.gui;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.legends.edumia.client.EdumiaClientUtil;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.FormattedCharSequence;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.Iterator;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public abstract class BasicIngameScreen extends Screen {

    public final List<AbstractWidget> buttons = Lists.newArrayList();

    public final List<GuiEventListener> children = Lists.newArrayList();
    private final PoseStack pose = new PoseStack();
    public BasicIngameScreen(Component titleIn){
        super(titleIn);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    protected void addTextFieldAndSetFocused(EditBox textField){
        this.addTextField(textField);
        textField.setFocused(true);
        this.setInitialFocus(textField);
    }

    protected void addTextField(EditBox textFiled){
        this.children.add(textFiled);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.minecraft.player.isAlive() || this.minecraft.player.isRemoved()){
            this.minecraft.player.closeContainer();
            this.onClose();
        }
    }

    @Override
    public boolean keyPressed(int key, int scan, int param3) {
        if (super.keyPressed(key, scan, param3)){
            return true;
        }else {
            GuiEventListener focused = this.getFocused();
            if (focused instanceof EditBox){
                EditBox focusedTextField = (EditBox) focused;
                if (focusedTextField.canConsumeInput()){
                    return true;
                }
            }

            if (this.shouldCloseOnEsc() && this.isEscapeOrInventoryKey(key, scan)){
                this.minecraft.player.closeContainer();
                this.onClose();
                return true;
            }else {
                return false;
            }
        }
    }

    protected boolean isEscapeOrInventoryKey(int key, int scan){
        return key == 256 || this.minecraft.options.keyInventory.isActiveAndMatches(InputConstants.getKey(key, scan));
    }

    protected void removeButton(AbstractWidget widget){
        this.buttons.remove(widget);
        this.children.remove(widget);
    }

    protected void renderButtonTooltipIfHovered(GuiGraphics guiGraphics, Button button, FormattedText tooltip, int mouseX, int mouseY){
        this.renderButtonTooltipIfHovered(guiGraphics, button, ImmutableList.of(tooltip), mouseX, mouseY);
    }

    protected void renderButtonTooltipIfHovered(GuiGraphics matStack, Button button, List<FormattedText> tooltipLines, int mouseX, int mouseY){
        if (button.active && button.isHovered()){
            int stringWidth = 200;
            matStack.renderTooltip(this.font, EdumiaClientUtil.trimEachLineToWidth(tooltipLines, this.font, stringWidth), mouseX, mouseY);
        }
    }

    /**
     * this will add the button clicked sound to the buttons
     */
    public static void playButtonClick(){
        Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F));
    }

    public void drawCenteredStringNoShadow(GuiGraphics guiGraphics, Font fr, String text, int x, int y, int color){
        guiGraphics.drawString(this.font, text, (x - fr.width(text) / 2), y, color);
    }

    public void drawCenteredStringNoShadow(GuiGraphics guiGraphics, Font fr, Component text, int x, int y, int color){
        guiGraphics.drawString(this.font, text, (x - fr.width(text) / 2), y, color);
    }

    public void drawCenteredStringNoShadow(GuiGraphics guiGraphics, Font fr, FormattedCharSequence text, int x, int y, int color){
        guiGraphics.drawString(this.font, text, (x - fr.width(text) / 2), y, color);
    }


    protected int drawCenteredStringLinesWrappedToWidth(GuiGraphics guiGraphics, Font fr, FormattedText text, int wrapWidth, int x, int y, int color){
        List<FormattedCharSequence> loreLines = this.font.split(text, wrapWidth);

        for (Iterator var9 = loreLines.iterator(); var9.hasNext(); y += 9){
            FormattedCharSequence line = (FormattedCharSequence) var9.next();
            guiGraphics.drawString(this.font, line, (x - this.font.width(line) / 2), y, color);
            this.font.getClass();
        }

        return y;
    }

}