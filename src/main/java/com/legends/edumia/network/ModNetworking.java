package com.legends.edumia.network;

import com.legends.edumia.Edumia;
import com.legends.edumia.network.connections.IConnectionToClient;
import com.legends.edumia.network.contexts.ServerPacketContext;
import com.legends.edumia.network.packets.C2S.PacketTeleportToDynamicWorldCoordinate;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

@EventBusSubscriber(modid = Edumia.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModNetworking {

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        var registrar = event.registrar("edumia_1.1.0-1.21.1-beta");
        registrar.playToServer(PacketTeleportToDynamicWorldCoordinate.ID,
                PacketTeleportToDynamicWorldCoordinate.CODEC,
                (packet, context) -> {
                    // Assuming `context.player()` gives you the `ServerPlayer`
            var player = context.player();
                    // Pass only the player to the packet's process method for simplicity
            packet.process(new ServerPacketContext((ServerPlayer) player, null));  // null as the connection, if not needed
        });
    }
}
