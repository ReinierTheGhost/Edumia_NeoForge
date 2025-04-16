package com.legends.edumia.client.gui.widget.button;

import com.legends.edumia.Edumia;
import com.legends.edumia.client.gui.races.selection.RaceSelectionController;
import com.legends.edumia.client.gui.widget.ModWidget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.joml.Vector2d;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchBarWidget extends ModWidget{
    private static final ResourceLocation SEARCH_WIDGET = ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "textures/gui/buttons/search_widget.png");
    private static final List<Integer> KEYS_TO_IGNORE = List.of(260, 262, 264, 263, 265, 266, 267, 268, 269);
    private static final int MINIMAL_MARGIN = 4;
    static final int SEARCH_BAR_PANEL_X = 102;
    static final int SEARCH_BAR_PANEL_Y = 18;
    public static final int TOTAL_WIDTH = SEARCH_BAR_PANEL_X;
    public Button searchBarToggleButton;
    private boolean searchResultToggle;
    private boolean searchBarToggle;
    private int currentSearchInputIndex;
    private String searchBarInput = "";
    public Button screenClick;
    HashMap<ResourceLocation, Component> pool;
    List<Button> buttons;
    RaceSelectionController controller;
    private final int maximumShownLength;
    private int currentAmount;
    private int currentlyShownEntries;
    private int currentOffsetIndex = 0;
    private int currentSearchResultHeight;
    private Vector2d searchResultPanelStarts = new Vector2d();
    Font textRenderer;

    int endY = 0;

    public SearchBarWidget(HashMap<ResourceLocation, Component> newPool, RaceSelectionController controller) {
        this.controller = controller;
        this.maximumShownLength = SEARCH_BAR_PANEL_X - 14 - MARGIN;
        searchBarToggle = false;
        searchResultToggle = false;
        setButtons();
        pool = newPool;
        buttons = new ArrayList<>();
        for (ResourceLocation id : newPool.keySet()) {
            buttons.add(
                    Button.builder(
                            newPool.get(id), x -> onPress(id)
                    ).build());
            buttons.getLast().active = false;
        }
        textRenderer = Minecraft.getInstance().font;
    }

    private void onPress(ResourceLocation id) {
        controller.setFactionId(id);
    }

    private void setButtons() {
        Button.OnPress searchBarInputToggle = button -> {
            if (!searchBarToggle)
                searchBarToggle = true;
        };
        searchBarToggleButton = Button.builder(Component.translatable("ui.edumia.search.toggle_button"), searchBarInputToggle).build();

        // Screen click
//        Button.OnPress screenClickAction = button -> {
//            clickOnScreen();
//        };
//        screenClick = Button.builder(Component.translatable("ui.edumia.search.screen_click_button"), screenClickAction).build();
    }


}
