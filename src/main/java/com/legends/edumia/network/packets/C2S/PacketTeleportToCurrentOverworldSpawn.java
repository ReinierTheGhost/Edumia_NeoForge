package com.legends.edumia.network.packets.C2S;

import com.legends.edumia.Edumia;
import com.legends.edumia.EdumiaServerConfigs;
import com.legends.edumia.items.WorldTeleporterItem;
import com.legends.edumia.network.contexts.ServerPacketContext;
import com.legends.edumia.network.packets.ClientToServerPacket;
import com.legends.edumia.resources.datas.races.RaceUtil;
import com.legends.edumia.utils.EdumiaLog;
import com.legends.edumia.world.dimension.ModDimensions;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;

public class PacketTeleportToCurrentOverworldSpawn  extends ClientToServerPacket<PacketTeleportToCurrentOverworldSpawn> {
    public static final Type<PacketTeleportToCurrentOverworldSpawn> ID = new Type<>(
            ResourceLocation.fromNamespaceAndPath(Edumia.MOD_ID, "packet_teleport_to_current_overworld_spawn"));
    public static final PacketTeleportToCurrentOverworldSpawn INSTANCE = new PacketTeleportToCurrentOverworldSpawn();
    public static final StreamCodec<RegistryFriendlyByteBuf, PacketTeleportToCurrentOverworldSpawn> CODEC = new StreamCodec<>() {
        @Override
        public PacketTeleportToCurrentOverworldSpawn decode(RegistryFriendlyByteBuf buf) {
            return INSTANCE;
        }

        @Override
        public void encode(RegistryFriendlyByteBuf buf, PacketTeleportToCurrentOverworldSpawn value) {
            // nothing to write
        }
    };

    @Override
    public Type<PacketTeleportToCurrentOverworldSpawn> type() {
        return ID;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, PacketTeleportToCurrentOverworldSpawn> streamCodec() {
        return CODEC;
    }



    @Override
    public void process(ServerPacketContext context) {
        try{
            context.player().getServer().execute(() -> {
                RaceUtil.reset(context.player());

                if(ModDimensions.isInEdumia(context.player().level())){
                    ModDimensions.teleportPlayerToOverworld(context.player());
                    RaceUtil.reset(context.player());
                    if(EdumiaServerConfigs.ENABLE_KEEP_RACE_ON_DIMENSION_SWAP.getAsBoolean()){
                        RaceUtil.initializeRace(context.player());
                    } else {
                        RaceUtil.reset(context.player());
                    }

                    if(!context.player().isCreative() && context.player().getMainHandItem().getItem() instanceof WorldTeleporterItem)
                        context.player().getItemInHand(InteractionHand.MAIN_HAND).shrink(1);
                }
            });
        } catch (Exception e){
            EdumiaLog.logError("PacketTeleportToCurrentOverworldSpawn::Apply - Tried applying the return to overworld packet",e);
        }
    }
}
