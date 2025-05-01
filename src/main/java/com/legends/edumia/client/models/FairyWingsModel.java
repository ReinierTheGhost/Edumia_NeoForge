package com.legends.edumia.client.models;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class FairyWingsModel<T extends LivingEntity> extends HumanoidModel<T> {

	private final ModelPart leftWing;
	private final ModelPart rightWing;

	public FairyWingsModel(ModelPart root) {
		super(root);
		this.leftWing = root.getChild("left_wing");
		this.rightWing = root.getChild("right_wing");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition leftWing = partdefinition.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(0, -23).addBox(0.0F, -18.0F, 0.0F, 0.0F, 34.0F, 23.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 8.0F, 2.0F, 0.0F, 0.8727F, 0.0F));

		PartDefinition rightWing = partdefinition.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(0, -23).addBox(0.0F, -18.0F, 0.0F, 0.0F, 34.0F, 23.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 8.0F, 2.0F, 0.0F, -0.8727F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		// Add wing animation here
		float wingFlap = (float) Math.sin(ageInTicks * 0.3F) * 0.5F;
		this.leftWing.zRot = -wingFlap;
		this.rightWing.zRot = wingFlap;

	}
}