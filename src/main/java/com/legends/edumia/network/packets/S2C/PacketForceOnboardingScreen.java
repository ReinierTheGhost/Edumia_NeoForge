package com.legends.edumia.network.packets.S2C;

import com.legends.edumia.Edumia;
import com.legends.edumia.client.gui.races.selection.FactionSelectionScreen;
import com.legends.edumia.network.contexts.ClientPacketContext;
import com.legends.edumia.network.packets.ServerToClientPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class PacketForceOnboardingScreen extends ServerToClientPacket<PacketForceOnboardingScreen> {
    public static final Type<PacketForceOnboardingScreen> ID = new Type<>(ResourceLocation
            .fromNamespaceAndPath(Edumia.MOD_ID, "packet_force_onboarding_screen"));
    public static final StreamCodec<RegistryFriendlyByteBuf, PacketForceOnboardingScreen> CODEC = StreamCodec.composite(
            ByteBufCodecs.FLOAT, p -> p.delayOnTeleportationConfirm,
            PacketForceOnboardingScreen::new
    );
    private final float delayOnTeleportationConfirm;

    public PacketForceOnboardingScreen(float delayOnTeleportationConfirm) {
        this.delayOnTeleportationConfirm = delayOnTeleportationConfirm;
    }

    @Override
    public Type<PacketForceOnboardingScreen> type() {
        return ID;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, PacketForceOnboardingScreen> streamCodec() {
        return CODEC;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void process(ClientPacketContext context) {
        float delay = delayOnTeleportationConfirm;
        if (context.player().hasInfiniteMaterials())
            delay = 0;
        Minecraft client = Minecraft.getInstance();
        client.setScreen(new FactionSelectionScreen(delay));
    }
}
