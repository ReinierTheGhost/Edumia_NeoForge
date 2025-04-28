package com.legends.edumia.client.gui;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ReturnConfirmationScreen extends Screen {
    private static final Component RETURN_CONFIRMATION_TITLE = Component.translatable("ui.edumia.return_confirmation.title");
    public ReturnConfirmationScreen(float delay) {
        super(RETURN_CONFIRMATION_TITLE);
    }
}
