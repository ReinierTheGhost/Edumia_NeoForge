package com.legends.edumia.network.packets.C2S;

import com.legends.edumia.Edumia;
import com.legends.edumia.EdumiaServerConfigs;
import com.legends.edumia.network.contexts.ServerPacketContext;
import com.legends.edumia.network.packets.ClientToServerPacket;
import com.legends.edumia.network.packets.S2C.PacketOnboardingResult;
import com.legends.edumia.resources.StateSaverAndLoader;
import com.legends.edumia.utils.LoggerUtil;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;

public class PacketOnboardingRequest extends ClientToServerPacket<PacketOnboardingRequest> {

    public static final Type<PacketOnboardingRequest> ID = new Type<>(ResourceLocation
            .fromNamespaceAndPath(Edumia.MOD_ID, "packet_onboarding_request"));
    public static final PacketOnboardingRequest INSTANCE = new PacketOnboardingRequest();
    public static final StreamCodec<RegistryFriendlyByteBuf, PacketOnboardingRequest> CODEC = StreamCodec.unit(INSTANCE);

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
        try{
            context.player().getServer().execute(() -> {
                ServerPlayer player = context.player();

                PacketOnboardingResult newPacket = new PacketOnboardingResult(
                        StateSaverAndLoader.getPlayerState(context.player()).hasAffilition(),
                        EdumiaServerConfigs.ENABLE_FACTION_RESET.get(),
                        EdumiaServerConfigs.ENABLE_RETURN_TO_OVERWORLD.get(),
                        EdumiaServerConfigs.DELAY_ON_TELEPORT_CONFIRMATION.get()
                );
                PacketDistributor.sendToPlayer(player, newPacket);
            });
        } catch(Exception e){
            LoggerUtil.logError("OnboardingDetailFetchingPacket::Apply - Tried sending packet with data", e);
        }
    }
}
