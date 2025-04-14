package com.legends.edumia.network.connections;

import com.legends.edumia.network.packets.ClientToServerPacket;

public interface IConnectionToServer {
    boolean isOnServer();

    <T extends ClientToServerPacket<T>> void sendPacketToServer(T packet);
}
