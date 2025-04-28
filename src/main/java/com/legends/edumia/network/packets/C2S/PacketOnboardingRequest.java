package com.legends.edumia.network.packets.C2S;

import com.legends.edumia.network.contexts.ServerPacketContext;
import com.legends.edumia.network.packets.ClientToServerPacket;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

public class PacketOnboardingRequest extends ClientToServerPacket<PacketOnboardingRequest> {


    @Override
    public Type<PacketOnboardingRequest> type() {
        return null;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, PacketOnboardingRequest> streamCodec() {
        return null;
    }

    @Override
    public void process(ServerPacketContext context) {

    }
}
