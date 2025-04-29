package com.legends.edumia.network;

import com.legends.edumia.Edumia;
import com.legends.edumia.network.contexts.ClientPacketContext;
import com.legends.edumia.network.contexts.ServerPacketContext;
import com.legends.edumia.network.packets.C2S.*;
import com.legends.edumia.network.packets.S2C.PacketForceOnboardingScreen;
import com.legends.edumia.network.packets.S2C.PacketOnboardingResult;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

@EventBusSubscriber(modid = Edumia.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModNetworking {

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        var registrar = event.registrar("edumia_1.1.0-1.21.1-beta");

        registrar.playToServer(
                PacketOnboardingRequest.ID,
                PacketOnboardingRequest.CODEC,
                (packet, context) -> {
                    var player = context.player();
                    packet.process(new ServerPacketContext((ServerPlayer) player, null));
                }
        );

        registrar.playToServer(
                PacketSetAffiliation.ID,
                PacketSetAffiliation.CODEC,
                (packet, context) -> {
                    var player = context.player();
                    packet.process(new ServerPacketContext((ServerPlayer) player, null));
                }
        );

        registrar.playToServer(
                PacketSetRace.ID,
                PacketSetRace.CODEC,
                (packet, context) -> {
                    var player = context.player();
                    packet.process(new ServerPacketContext((ServerPlayer) player, null));
                }
        );

        registrar.playToServer(
                PacketSetSpawnData.ID,
                PacketSetSpawnData.CODEC,
                (packet, context) -> {
                    var player = context.player();
                    packet.process(new ServerPacketContext((ServerPlayer) player, null));
                }
        );

        registrar.playToServer(
                PacketTeleportToCurrentOverworldSpawn.ID,
                PacketTeleportToCurrentOverworldSpawn.CODEC,
                (packet, context) -> {
                    var player = context.player();
                    packet.process(new ServerPacketContext((ServerPlayer) player, null));
                }
        );

        registrar.playToServer(
                PacketTeleportToCurrentSpawn.ID,
                PacketTeleportToCurrentSpawn.CODEC,
                (packet, context) -> {
                    var player = context.player();
                    packet.process(new ServerPacketContext((ServerPlayer) player, null));
                }
        );

        registrar.playToServer(
                PacketTeleportToCustomCoordinate.ID,
                PacketTeleportToCustomCoordinate.CODEC,
                (packet, context) -> {
                    var player = context.player();
                    packet.process(new ServerPacketContext((ServerPlayer) player, null));
                }
        );

        registrar.playToServer(
                PacketTeleportToDynamicCoordinate.ID,
                PacketTeleportToDynamicCoordinate.CODEC,
                (packet, context) -> {
                    var player = context.player();
                    packet.process(new ServerPacketContext((ServerPlayer) player, null));
                }
        );

        registrar.playToServer(PacketTeleportToDynamicWorldCoordinate.ID,
                PacketTeleportToDynamicWorldCoordinate.CODEC,
                (packet, context) -> {
                    // Assuming `context.player()` gives you the `ServerPlayer`
            var player = context.player();
                    // Pass only the player to the packet's process method for simplicity
            packet.process(new ServerPacketContext((ServerPlayer) player, null));  // null as the connection, if not needed
        });



        registrar.playToClient(
                PacketForceOnboardingScreen.ID,
                PacketForceOnboardingScreen.CODEC,
                (packet, context) -> {
                    var player = context.player();
                    packet.process(new ClientPacketContext(player, null));
                }
        );

        registrar.playToClient(
                PacketOnboardingResult.ID,
                PacketOnboardingResult.CODEC,
                (packet, context) -> {
                    var player = context.player();
                    packet.process(new ClientPacketContext(player, null));
                }
        );


    }

}
