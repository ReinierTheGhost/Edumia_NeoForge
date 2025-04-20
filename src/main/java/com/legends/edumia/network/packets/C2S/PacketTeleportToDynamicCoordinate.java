package com.legends.edumia.network.packets.C2S;

import com.legends.edumia.Edumia;
import com.legends.edumia.network.contexts.ServerPacketContext;
import com.legends.edumia.network.packets.ClientToServerPacket;
import com.legends.edumia.world.dimension.ModDimensions;
import com.legends.edumia.world.map.EdumiaMapUtils;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector2d;

public class PacketTeleportToDynamicCoordinate  extends ClientToServerPacket<PacketTeleportToDynamicCoordinate> {
    public static final Type<PacketTeleportToDynamicCoordinate> ID = new Type<>(ResourceLocation
            .fromNamespaceAndPath(Edumia.MOD_ID, "packet_teleport_dynamic_spawn"));
    public static final StreamCodec<RegistryFriendlyByteBuf, PacketTeleportToDynamicCoordinate> CODEC = StreamCodec.composite(
            ByteBufCodecs.DOUBLE, p -> p.xCoordinate,
            ByteBufCodecs.DOUBLE, p -> p.zCoordinate,
            ByteBufCodecs.BOOL, p -> p.welcomeNeeded,
            PacketTeleportToDynamicCoordinate::new
    );
    private final double xCoordinate;
    private final double zCoordinate;
    private final boolean welcomeNeeded;

    public PacketTeleportToDynamicCoordinate(double xCoordinate, double zCoordinate, boolean welcomeNeeded){
        this.xCoordinate = xCoordinate;
        this.zCoordinate = zCoordinate;
        this.welcomeNeeded = welcomeNeeded;
    }
    @Override
    public Type<PacketTeleportToDynamicCoordinate> type() {
        return ID;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, PacketTeleportToDynamicCoordinate> streamCodec() {
        return CODEC;
    }

    @Override
    public void process(ServerPacketContext context) {
        context.player().getServer().execute(() -> {
            Vector2d worldCoordinate = EdumiaMapUtils.getInstance().getWorldCoordinateFromInitialMap(xCoordinate, zCoordinate);
            MinecraftServer server = context.player().getServer();
            Vec3 coordinates = new Vec3(worldCoordinate.x, ModDimensions.getDimensionHeight((int)worldCoordinate.x, (int)worldCoordinate.y).y, worldCoordinate.y);
            ModDimensions.teleportPlayerToEdumia(context.player(), coordinates, true, false);
        });
    }
}
