package com.legends.edumia.items;


//import com.legends.edumia.world.dimension.ModDimensions;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class WorldTeleporterItem extends Item {
    public WorldTeleporterItem(Item.Properties settings) {
        super(settings);
    }

//    @Override
//    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
//        if(!ModDimensions.isInEdumia(world)) {
//            if (!user.isCreative()) {
//                //user.getInventory().removeStack(user.getActiveHand().ordinal());
//                user.getItemInHand(hand).shrink(1);
//            }
//            ModDimensions.teleportPlayerToME(user);
//        }
//        return super.use(world, user, hand);
//    }
}
