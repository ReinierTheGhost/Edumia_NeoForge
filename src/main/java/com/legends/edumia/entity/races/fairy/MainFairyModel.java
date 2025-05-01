package com.legends.edumia.entity.races.fairy;

import com.legends.edumia.items.weapons.ranged.CustomBowWeaponItem;
import com.legends.edumia.items.weapons.ranged.CustomLongbowWeaponItem;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.joml.Vector3f;

public class MainFairyModel<T extends Mob>
        extends HumanoidModel<T> {

    public MainFairyModel(ModelPart root) {
        super(root);
    }

    @Override
    public void prepareMobModel(T entity, float limbSwing, float limbSwingAmount, float partialTick) {
        this.rightArmPose = ArmPose.EMPTY;
        this.leftArmPose = ArmPose.EMPTY;
        ItemStack itemStack = (entity).getItemInHand(InteractionHand.MAIN_HAND);
        if ((itemStack.is(Items.BOW) || itemStack.getItem() instanceof CustomLongbowWeaponItem|| itemStack.getItem() instanceof CustomBowWeaponItem &&(entity).isAggressive())){
            if (entity.getMainArm() == HumanoidArm.RIGHT){
                this.rightArmPose = ArmPose.BOW_AND_ARROW;
            }else {
                this.leftArmPose = ArmPose.BOW_AND_ARROW;
            }
        }
        super.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTick);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

    }


}
