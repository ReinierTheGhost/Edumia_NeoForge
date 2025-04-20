package com.legends.edumia.resources.datas.npcs;

import com.legends.edumia.resources.datas.npcs.data.NpcGearData;
import com.legends.edumia.utils.ResourceLocationUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NpcData {

    private final ResourceLocation id;
    private final ResourceLocation raceID;
    private final List<NpcGearData> gearDatas;

    public NpcData(String id, String raceId, CompoundTag gearData) {
        this.id = ResourceLocationUtil.getResourceLocationFromString(id);
        this.raceID = ResourceLocationUtil.getResourceLocationFromString(raceId);

        ListTag npcGear = gearData.getList("pool", Tag.TAG_COMPOUND);
        List<NpcGearData> npcGearData = new ArrayList<>();
        for (int j = 0; j < npcGear.size(); j++){
            CompoundTag compound = npcGear.getCompound(j);
            npcGearData.add(NpcGearData.readNbt(compound));
        }
        this.gearDatas = npcGearData;
    }

    public ResourceLocation getRaceId() {
        return raceID;
    }

    public NpcGearData getGear() {
        if (gearDatas == null)
            return null;

        if (gearDatas.size() == 1)
            return gearDatas.getFirst();

        Random random = new Random();
        return gearDatas.get(random.nextInt(0, gearDatas.size()));
    }
}
