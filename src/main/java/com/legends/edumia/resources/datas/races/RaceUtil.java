package com.legends.edumia.resources.datas.races;

import com.legends.edumia.resources.StateSaverAndLoader;
import com.legends.edumia.resources.datas.RaceType;
import com.legends.edumia.resources.datas.races.data.AttributeData;
import com.legends.edumia.resources.persistent_datas.PlayerData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class RaceUtil {
    public static void updateRace(Player player, Race race, boolean shouldHeal){
        PlayerData data = StateSaverAndLoader.getPlayerState(player);

        boolean havePreviousRace = data.getRace() != null;
        boolean raceExists = race != null;

        // [RESET]
        if(havePreviousRace){
            RaceLookup.getRace(player.level(), data.getRace()).reverseAttributes(player);
            data.setRace(null);
        }

        reset(player);

        // [SET]
        if(raceExists){
            race.applyAttributes(player);
            data.setRace(race.getId());
        }

        if(shouldHeal)
            player.heal(player.getMaxHealth());
    }

    public static Race getRace(Player player){
        PlayerData data = StateSaverAndLoader.getPlayerState(player);
        if(data == null) return null;
        return data.getRace(player.level());
    }

    public static RaceType getRaceType(Player player){
        Race race = getRace(player);
        if(race != null)
            return race.getRaceType();
        else
            return null;
    }

    public static void initializeRace(ServerPlayer player) {
        Race race = getRace(player);
        updateRace(player, race, false);
    }

    public static void reset(Player player) {
        AttributeData.reset(player);
    }
}
