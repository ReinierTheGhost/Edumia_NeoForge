package com.legends.edumia.network.packets.C2S;

import com.legends.edumia.Edumia;
import com.legends.edumia.network.contexts.ServerPacketContext;
import com.legends.edumia.network.packets.ClientToServerPacket;
import com.legends.edumia.world.dimension.ModDimensions;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

public class PacketTeleportToCustomCoordinate  extends ClientToServerPacket<PacketTeleportToCustomCoordinate> {
    public static final Type<PacketTeleportToCustomCoordinate> ID = new Type<>(ResourceLocation
            .fromNamespaceAndPath(Edumia.MOD_ID, "packet_teleport_custom_spawn"));
    public static final StreamCodec<RegistryFriendlyByteBuf
            , PacketTeleportToCustomCoordinate> CODEC = StreamCodec.composite(
            ByteBufCodecs.DOUBLE, p -> p.xCoordinate,
            ByteBufCodecs.DOUBLE, p -> p.yCoordinate,
            ByteBufCodecs.DOUBLE, p -> p.zCoordinate,
            ByteBufCodecs.BOOL, p -> p.welcomeNeeded,
            PacketTeleportToCustomCoordinate::new
    );
    private final double xCoordinate;
    private final double yCoordinate;
    private final double zCoordinate;
    private final boolean welcomeNeeded;

    public PacketTeleportToCustomCoordinate(Double xCoordinate, Double yCoordinate, Double zCoordinate, boolean welcomeNeeded){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.zCoordinate = zCoordinate;
        this.welcomeNeeded = welcomeNeeded;
    }
    @Override
    public Type<PacketTeleportToCustomCoordinate> type() {
        return ID;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, PacketTeleportToCustomCoordinate> streamCodec() {
        return CODEC;
    }

    @Override
    public void process(ServerPacketContext context) {
        context.player().getServer().execute(() -> {
            Vec3 coordinates = new Vec3(xCoordinate, yCoordinate, zCoordinate);
            ModDimensions.teleportPlayerToEdumia(context.player(), coordinates, true, welcomeNeeded);
        });
    }
}
