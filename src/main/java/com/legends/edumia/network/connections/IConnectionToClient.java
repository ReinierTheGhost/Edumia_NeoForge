package com.legends.edumia.network.connections;

import com.legends.edumia.network.packets.ServerToClientPacket;
import net.minecraft.server.level.ServerPlayer;

public interface IConnectionToClient {
    <T extends ServerToClientPacket<T>> void sendPacketToClient(T packet, ServerPlayer player);

}
