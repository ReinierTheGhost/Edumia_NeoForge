package com.legends.edumia.client.gui.races.selection;

import com.legends.edumia.client.gui.EdumiaMenuScreen;
import com.legends.edumia.client.gui.widget.ModWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.network.chat.Component;

public class RaceSelectionScreen extends EdumiaMenuScreen {

    private static final Component FACTION_SELECTION_TITLE = Component.translatable("screen.edumia.race_selection_screen");

//    private FactionSelectionController controller;
    private AbstractClientPlayer player;
    private ModelPart bannerField;
//    private SearchBarWidget searchBarWidget;
//    private PlayableNpcPreviewWidget playableNpcPreviewWidget;
//    private CycledSelectionWidget dispositionSelectionWidget;
//    private CycledSelectionWidget factionSelectionWidget;
//    private CycledSelectionWidget subfactionSelectionWidget;
//    public ButtonWidget factionRandomizerButton;
//    public TextBlockWidget raceListTextBlockWidget;
//    public TextBlockWidget factionDescriptionTextBlockWidget;

    // Map buttons
    public Button mapZoomInButton;
    public Button mapZoomOutButton;
    public Button mapFocusButton;
//    public FactionSelectionMapWidget mapWidget;
//    private CycledSelectionWidget raceCycledSelection;
//    private CycledSelectionWidget spawnPointCycledSelection;
    public Button spawnSelectionRandomizerButton;
    public Button spawnSelectionConfirmButton;
    private float initialDelay;
    public RaceSelectionScreen(float delay) {
        super(FACTION_SELECTION_TITLE);
        initialDelay = delay;
        ModWidget.enableFocus(false);
    }
}
