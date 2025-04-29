package com.legends.edumia.resources.datas.npcs.data;

import com.legends.edumia.utils.EdumiaLog;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EquipmentSlot;

import java.util.HashMap;

public class NpcGearData {
    public final HashMap<EquipmentSlot, NpcGearSlotData> gears;

    public NpcGearData(){
        gears = new HashMap<>();
    }
    public static NpcGearData create() {
        return new NpcGearData();
    }
    public NpcGearData(CompoundTag gearNbt) {
        this.gears = new HashMap<>();
        addSlot(gearNbt, EquipmentSlot.HEAD);
        addSlot(gearNbt, EquipmentSlot.CHEST);
        addSlot(gearNbt, EquipmentSlot.LEGS);
        addSlot(gearNbt, EquipmentSlot.FEET);
        addSlot(gearNbt, EquipmentSlot.MAINHAND);
        addSlot(gearNbt, EquipmentSlot.OFFHAND);
    }

    private void addSlot(CompoundTag gearNbt, EquipmentSlot equipmentSlot) {
        if(gearNbt.get(equipmentSlot.getName()) != null){
            CompoundTag slotNbt = gearNbt.getCompound(equipmentSlot.getName());
//            this.gears.put(equipmentSlot, NpcGearSlotData.readNbt(slotNbt));
        }
    }

    public NpcGearData add(EquipmentSlot slot, NpcGearSlotData slotData){
        if(gears.containsKey(slot)) {
            EdumiaLog.logError("NpcGearData::Overwriting slotData - %s".formatted(slot.getName()));
        }
        gears.put(slot, slotData);
        return this;
    }

    public static NpcGearData Create() {
        return NpcGearData.create();
    }

//    public ItemStack get(EquipmentSlot slot) {
//        if(!gears.containsKey(slot))
//            return new ItemStack(Items.AIR);
//        return gears.get(slot).getItemStack();
//    }

    public static CompoundTag createNbt(NpcGearData gearData){
        CompoundTag nbt = new CompoundTag();
        for(EquipmentSlot slot : gearData.gears.keySet()){
//            nbt.put(slot.getName().toLowerCase(), NpcGearSlotData.createNbt(gearData.gears.get(slot)));
        }
        return nbt;
    }
    public static NpcGearData readNbt(CompoundTag nbt){
        return new NpcGearData(nbt);
    }
}
