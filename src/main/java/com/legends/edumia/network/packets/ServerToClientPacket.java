package com.legends.edumia.network.packets;

import com.legends.edumia.network.contexts.ClientPacketContext;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public abstract class ServerToClientPacket <T extends ServerToClientPacket<T>> implements CustomPacketPayload {
    @Override
    public abstract Type<T> type();

    public abstract StreamCodec<RegistryFriendlyByteBuf, T> streamCodec();
    public abstract void process(ClientPacketContext context);
}
