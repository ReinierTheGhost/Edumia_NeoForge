package com.legends.edumia.network.contexts;

import com.legends.edumia.network.connections.IConnectionToClient;
import net.minecraft.server.level.ServerPlayer;

public record ServerPacketContext(ServerPlayer player, IConnectionToClient connection) {
}
