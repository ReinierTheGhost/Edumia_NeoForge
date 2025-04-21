package com.legends.edumia.network.packets.C2S;

import com.legends.edumia.Edumia;
import com.legends.edumia.items.WorldTeleporterItem;
import com.legends.edumia.network.contexts.ServerPacketContext;
import com.legends.edumia.network.packets.ClientToServerPacket;
import com.legends.edumia.resources.datas.factions.Faction;
import com.legends.edumia.resources.datas.factions.FactionLookup;
import com.legends.edumia.resources.datas.factions.FactionUtil;
import com.legends.edumia.utils.LoggerUtil;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.InteractionHand;

public class PacketSetAffiliation  extends ClientToServerPacket<PacketSetAffiliation>
{
    public static final CustomPacketPayload.Type<PacketSetAffiliation> ID =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "packet_set_affiliation"));

    public static final StreamCodec<RegistryFriendlyByteBuf, PacketSetAffiliation> CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, p -> p.dispositionName,
            ByteBufCodecs.STRING_UTF8, p -> p.factionName,
            ByteBufCodecs.STRING_UTF8, p -> p.spawnName,
            PacketSetAffiliation::new
    );

    private final String dispositionName;
    private final String factionName;
    private final String spawnName;


    public PacketSetAffiliation(String dispositionName, String factionName, String spawnName){
        this.dispositionName = dispositionName;
        this.factionName = factionName;
        this.spawnName = spawnName;
    }

    @Override
    public Type<PacketSetAffiliation> type() {
        return ID;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, PacketSetAffiliation> streamCodec() {
        return CODEC;
    }

    @Override
    public void process(ServerPacketContext context) {
        MinecraftServer server = context.player().getServer();
        server.execute(() -> {
            try{
                ResourceLocation factionId = ResourceLocation.parse(factionName);
                Faction faction = FactionLookup.getFactionById(context.player().level(), factionId);
                ResourceLocation spawnId = ResourceLocation.parse(spawnName);
                FactionUtil.updateFaction(context.player(), faction, spawnId);
                if(!context.player().isCreative() && context.player().getMainHandItem().getItem() instanceof WorldTeleporterItem)
                    context.player().getItemInHand(InteractionHand.MAIN_HAND).shrink(1);
            } catch (Exception e){
                LoggerUtil.logError("AffiliationPacket::Tried getting affiliation packet and couldn't fetch any.", e);
            }
        });
    }
}
