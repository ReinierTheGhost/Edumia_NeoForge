package com.legends.edumia.client.gui.races.selection;

import com.legends.edumia.Edumia;
import com.legends.edumia.client.gui.EdumiaMenuScreen;
import com.legends.edumia.client.gui.widget.ModWidget;
import com.legends.edumia.client.gui.widget.PlayableNpcPreviewWidget;
import com.legends.edumia.client.gui.widget.button.SearchBarWidget;
import com.legends.edumia.client.gui.widget.map.FactionSelectionMapWidget;
import com.legends.edumia.resources.datas.factions.Faction;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class FactionSelectionScreen extends EdumiaMenuScreen {

    private static final ResourceLocation FACTION_SELECTION_UI = ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID,"textures/gui/faction_selection.png");
    private static final ResourceLocation FACTION_SELECTION_BANNER_UI = ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID,"textures/gui/faction_selection_banner.png");
    private static final ResourceLocation FACTION_SELECTION_BUTTONS = ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID,"textures/gui/faction_selection_buttons.png");
    private static final ResourceLocation MAP_SELECTION = ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID,"textures/gui/faction_selection_map.png");
    private static final Component FACTION_SELECTION_TITLE = Component.translatable("screen.edumia.faction_selection_screen");
    private static final int MINIMAL_MARGIN = 4;
    private FactionSelectionController controller;
    private AbstractClientPlayer player;
    private ModelPart bannerField;
    private SearchBarWidget searchBarWidget;
    private PlayableNpcPreviewWidget playableNpcPreviewWidget;
//    private CycledSelectionWidget dispositionSelectionWidget;
//    private CycledSelectionWidget factionSelectionWidget;
//    private CycledSelectionWidget subfactionSelectionWidget;
    public Button factionRandomizerButton;
//    public TextBlockWidget raceListTextBlockWidget;
//    public TextBlockWidget factionDescriptionTextBlockWidget;

    // Map buttons
    public Button mapZoomInButton;
    public Button mapZoomOutButton;
    public Button mapFocusButton;
    public FactionSelectionMapWidget mapWidget;
//    private CycledSelectionWidget raceCycledSelection;
//    private CycledSelectionWidget spawnPointCycledSelection;
    public Button spawnSelectionRandomizerButton;
    public Button spawnSelectionConfirmButton;
    private float initialDelay;
    public FactionSelectionScreen(float delay) {
        super(FACTION_SELECTION_TITLE);
        initialDelay = delay;
        ModWidget.enableFocus(false);
    }

    public void enableConfirm() {
        if (spawnSelectionConfirmButton != null){
            spawnSelectionConfirmButton.active = true;
        }
    }

    public void updateEquipment() {
        if (player == null || controller == null) return;

        Faction faction = controller.getCurrentlySelectedFaction();

        if (faction != null)
            playableNpcPreviewWidget.updateEntity(controller.getCurrentPreview(player.level()), controller.getCurrentRace(), player.level());
    }

    public void reassignTexts(List<Component> races, List<Component> dispositions) {

    }
}
