package com.legends.edumia.resources.datas.factions;

import com.legends.edumia.utils.IdentifierUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Faction {

    private final ResourceLocation id;
    private List<ResourceLocation> subFactions = null;

    public Faction(String id, Integer factionSelectionOrderIndex, Boolean joinable, String disposition, String factionType,
                   Optional<ResourceLocation> parentFaction, Optional<List<ResourceLocation>> newSubFactions,
                   Optional<CompoundTag> npcs, Optional<CompoundTag> bannerDataNbt, Optional<CompoundTag> spawnsNbt,
                   Optional<List<String>> joinCommands, Optional<List<String>> leaveCommands) {
        this.id = IdentifierUtil.getIdentifierFromString(id);

        if(newSubFactions.isPresent()){
            this.subFactions = new ArrayList<>();
            this.subFactions.addAll(newSubFactions.get());
        }
    }

    public List<ResourceLocation> getSubFactions(){
        return subFactions;
    }


    public ResourceLocation getId() {
        return id;
    }
}
