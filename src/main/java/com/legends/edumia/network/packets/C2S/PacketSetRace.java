package com.legends.edumia.network.packets.C2S;

import com.legends.edumia.Edumia;
import com.legends.edumia.network.contexts.ServerPacketContext;
import com.legends.edumia.network.packets.ClientToServerPacket;
import com.legends.edumia.resources.datas.races.RaceLookup;
import com.legends.edumia.resources.datas.races.RaceUtil;
import com.legends.edumia.utils.EdumiaLog;
import com.legends.edumia.utils.ResourceLocationUtil;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;

public class PacketSetRace extends ClientToServerPacket<PacketSetRace>
{
    public static final Type<PacketSetRace> ID = new Type<>(ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID,
            "packet_set_race"));

    public static final StreamCodec<RegistryFriendlyByteBuf, PacketSetRace> CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, p -> p.race,
            PacketSetRace::new
    );

    private final String race;


    public PacketSetRace(String race){
        this.race = race;
    }

    @Override
    public Type<PacketSetRace> type() {
        return ID;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, PacketSetRace> streamCodec() {
        return CODEC;
    }

    @Override
    public void process(ServerPacketContext context) {
        MinecraftServer server = context.player().getServer();
        server.execute(() -> {
            try{
                RaceUtil.updateRace(context.player(), RaceLookup.getRace(context.player().level(),
                        ResourceLocationUtil.getResourceLocationFromString(race)), true);
            } catch (Exception e){
                EdumiaLog.logError("PacketSetRace::Tried setting race for player.", e);
            }
        });
    }
}
