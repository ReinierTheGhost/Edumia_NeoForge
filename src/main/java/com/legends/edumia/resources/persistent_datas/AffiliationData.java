package com.legends.edumia.resources.persistent_datas;

import com.legends.edumia.exceptions.FactionResourceLocationException;
import com.legends.edumia.resources.datas.Disposition;
import com.legends.edumia.resources.datas.factions.Faction;
import com.legends.edumia.resources.datas.factions.FactionLookup;
import com.legends.edumia.resources.datas.factions.data.SpawnData;
import com.legends.edumia.utils.LoggerUtil;
import com.legends.edumia.world.dimension.ModDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class AffiliationData {
    public Disposition disposition;
    public ResourceLocation faction;
    public ResourceLocation spawnId;

    public AffiliationData(String disposition, ResourceLocation factionId, ResourceLocation spawnId) {
        this.disposition = Disposition.valueOf(disposition);
        this.faction = factionId;
        this.spawnId = spawnId;
    }


    public Disposition getDisposition(){
        return disposition;
    }

    public Vec3 getSpawnMiddleEarthCoordinate(Level world){
        try{
            Faction foundFaction = FactionLookup.getFactionById(world,faction);
            SpawnData spawnData = foundFaction.getSpawnData().findSpawn(spawnId);
            BlockPos blockpos = spawnData.getBlockPos();
            if(!spawnData.isDynamic()){ // Return custom spawn coords
                return blockpos.getCenter();
            }
            int height = ModDimensions.getDimensionHeight(blockpos.getX(), blockpos.getZ()).y;
            blockpos = new BlockPos(blockpos.getX(), height, blockpos.getZ());
            return blockpos.getCenter();
        } catch (FactionResourceLocationException e){
            LoggerUtil.logError("AffiliationData::getSpawnMiddleEarthCoordinate - Faction couldn't be found <%s>".formatted(faction));
            return null;
        }
    }

    @Override
    public String toString() {
        return "Disposition=" + getDisposition().toString() + ";\nFaction=" + faction + ";\nSpawn=" + spawnId + ";";
    }
}
