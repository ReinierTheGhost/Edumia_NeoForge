package com.legends.edumia.client.gui;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class OnboardingSelectionScreen extends Screen {
    private static final Component ONBOARDING_SELECTION_TITLE = Component.translatable("ui.edumia.onboarding_selection.title");
    public OnboardingSelectionScreen(float delay, boolean canResetCharacter) {
        super(ONBOARDING_SELECTION_TITLE);
    }
}
