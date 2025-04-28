package com.legends.edumia.resources.datas;

import com.legends.edumia.resources.StateSaverAndLoader;
import com.legends.edumia.resources.persistent_datas.PlayerData;
import net.minecraft.world.entity.player.Player;

public class DispositionUtil {
    public static Disposition getDisposition(Player player) {
        PlayerData data = StateSaverAndLoader.getPlayerState(player);
        if (data == null) return null;
        return data.getCurrentDisposition();
    }
}
