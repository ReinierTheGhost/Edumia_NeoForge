package com.legends.edumia.network.contexts;

import com.legends.edumia.network.connections.IConnectionToServer;
import net.minecraft.world.entity.player.Player;

public record ClientPacketContext(Player player, IConnectionToServer connection) {
}
