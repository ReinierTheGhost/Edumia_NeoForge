package com.legends.edumia.items;

import com.legends.edumia.EdumiaServerConfigs;
import com.legends.edumia.client.gui.ReturnConfirmationScreen;
import com.legends.edumia.network.packets.C2S.PacketOnboardingRequest;
import com.legends.edumia.world.dimension.ModDimensions;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;

public class WorldTeleporterItem extends Item {
    public WorldTeleporterItem(Item.Properties settings) {
        super(settings);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
//        if (world.isClientSide){
//            PacketDistributor.sendToServer(new PacketOnboardingRequest());
//        }
//        return InteractionResultHolder.success(user.getItemInHand(hand));

        float delay = EdumiaServerConfigs.DELAY_ON_TELEPORT_CONFIRMATION.get();
        if(ModDimensions.isInEdumia(world)) {
            if (!EdumiaServerConfigs.ENABLE_RETURN_TO_OVERWORLD.get()) {
                return super.use(world, user, hand);
            }
            Minecraft client = Minecraft.getInstance();
            client.setScreen(new ReturnConfirmationScreen(delay));
        }else if (!ModDimensions.isInEdumia(world)) {
            if (!user.isCreative()) {
                //user.getInventory().removeStack(user.getActiveHand().ordinal());
                user.getItemInHand(hand).shrink(1);
            }
            ModDimensions.teleportPlayerToEdumia(user);
        }
        return super.use(world, user, hand);
    }
}
