package com.legends.edumia.network.packets.S2C;

import com.legends.edumia.Edumia;
import com.legends.edumia.client.gui.ReturnConfirmationScreen;
import com.legends.edumia.handlers.OnboardingScreenHandler;
import com.legends.edumia.network.contexts.ClientPacketContext;
import com.legends.edumia.network.packets.ServerToClientPacket;
import com.legends.edumia.world.dimension.ModDimensions;
import com.mojang.authlib.minecraft.client.MinecraftClient;
import net.minecraft.client.Minecraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class PacketOnboardingResult  extends ServerToClientPacket<PacketOnboardingResult> {
    public static final Type<PacketOnboardingResult> ID = new Type<>(ResourceLocation
            .fromNamespaceAndPath(Edumia.MOD_ID, "packet_onboarding_result"));
    public static final StreamCodec<RegistryFriendlyByteBuf, PacketOnboardingResult> CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL, p -> p.havePlayerData,
            ByteBufCodecs.BOOL, p -> p.canChangeFaction,
            ByteBufCodecs.BOOL, p -> p.canReturnToOverworld,
            ByteBufCodecs.FLOAT, p -> p.delayOnTeleportationConfirm,
            PacketOnboardingResult::new
    );

    private final boolean havePlayerData;
    private final boolean canChangeFaction;
    private final boolean canReturnToOverworld;
    private final float delayOnTeleportationConfirm;

    public PacketOnboardingResult(boolean havePlayerData, boolean canChangeFaction, boolean canReturnToOverworld, float delayOnTeleportationConfirm) {
        this.havePlayerData = havePlayerData;
        this.canChangeFaction = canChangeFaction;
        this.canReturnToOverworld = canReturnToOverworld;
        this.delayOnTeleportationConfirm = delayOnTeleportationConfirm;
    }

    @Override
    public Type<PacketOnboardingResult> type() {
        return ID;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, PacketOnboardingResult> streamCodec() {
        return CODEC;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void process(ClientPacketContext context) {
        float delay = delayOnTeleportationConfirm;
        if(context.player().hasInfiniteMaterials())
            delay = 0;
        if(ModDimensions.isInEdumia(context.player().level())){
            if(!canReturnToOverworld){
                return;
            }
            Minecraft client = Minecraft.getInstance();
            client.setScreen(new ReturnConfirmationScreen(delay));
        } else if(ModDimensions.isInOverworld(context.player().level())){
            OnboardingScreenHandler.handle(context, havePlayerData, delay);
        }
    }
}
