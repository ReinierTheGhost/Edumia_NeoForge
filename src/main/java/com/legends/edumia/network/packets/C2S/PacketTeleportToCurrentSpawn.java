package com.legends.edumia.network.packets.C2S;

import com.legends.edumia.Edumia;
import com.legends.edumia.network.contexts.ServerPacketContext;
import com.legends.edumia.network.packets.ClientToServerPacket;
import com.legends.edumia.resources.StateSaverAndLoader;
import com.legends.edumia.resources.persistent_datas.PlayerData;
import com.legends.edumia.utils.EdumiaLog;
import com.legends.edumia.world.dimension.ModDimensions;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

public class PacketTeleportToCurrentSpawn  extends ClientToServerPacket<PacketTeleportToCurrentSpawn> {
    public static final Type<PacketTeleportToCurrentSpawn> ID = new Type<>(ResourceLocation
            .fromNamespaceAndPath(Edumia.MOD_ID, "packet_teleport_current_spawn"));
    public static final StreamCodec<RegistryFriendlyByteBuf, PacketTeleportToCurrentSpawn> CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL, p -> p.welcomeNeeded,
            PacketTeleportToCurrentSpawn::new
    );
    private Boolean welcomeNeeded;

    public PacketTeleportToCurrentSpawn(boolean welcomeNeeded){
        this.welcomeNeeded = welcomeNeeded;
    }
    @Override
    public Type<PacketTeleportToCurrentSpawn> type() {
        return ID;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, PacketTeleportToCurrentSpawn> streamCodec() {
        return CODEC;
    }



    @Override
    public void process(ServerPacketContext context) {
        try{
            context.player().getServer().execute(() -> {
                PlayerData data = StateSaverAndLoader.getPlayerState(context.player());
                if(data != null){
                    if(data.hasAffilition()){
                        Vec3 spawnCoordinates = data.getSpawnMiddleEarthCoordinate(context.player().level());
                        if(spawnCoordinates != null)
                            ModDimensions.teleportPlayerToEdumia(context.player(), new Vec3(spawnCoordinates.x, spawnCoordinates.y, spawnCoordinates.z), true, welcomeNeeded);
                    }
                }
            });
        } catch (Exception e){
            EdumiaLog.logError("TeleportToMeSpawnRequestPacket::Apply - Tried applying the teleport to me request packet",e);
        }
    }
}
