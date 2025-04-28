package com.legends.edumia.client.models.fairy;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;

public class FairyModel<T extends Mob>
		extends HumanoidModel<T> {

	private final ModelPart body;
	private final ModelPart rightArm;
	private final ModelPart leftArm;
	private final ModelPart rightLeg;
	private final ModelPart leftLeg;
	private final ModelPart head;
	private final ModelPart leftWing;
	private final ModelPart rightWing;

	public FairyModel(ModelPart root) {
		super(root);
		this.body = root.getChild("body");
		this.rightArm = root.getChild("rightArm");
		this.leftArm = root.getChild("leftArm");
		this.rightLeg = root.getChild("rightLeg");
		this.leftLeg = root.getChild("leftLeg");
		this.head = root.getChild("head");
		this.leftWing = root.getChild("leftWing");
		this.rightWing = root.getChild("rightWing");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(32, 30).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition breast = body.addOrReplaceChild("breast", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -1.0F, -0.8F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 3.0F, -2.0F, -0.8727F, 0.0F, 0.0F));

		PartDefinition rightArm = partdefinition.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(16, 46).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, 2.5F, 0.0F));

		PartDefinition leftArm = partdefinition.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(0, 46).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, 2.5F, 0.0F));

		PartDefinition rightLeg = partdefinition.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(32, 46).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition leftLeg = partdefinition.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(48, 46).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 30)
				.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(84, 43)
				.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 16.0F, 8.0F, new CubeDeformation(0.25F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leftWing = partdefinition.addOrReplaceChild("leftWing", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition upperLeft = leftWing.addOrReplaceChild("upperLeft", CubeListBuilder.create().texOffs(61, -23).addBox(0.0F, -27.0F, 0.0F, 0.0F, 34.0F, 23.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -7.0F, 2.0F, 0.0F, 0.8727F, 0.0F));

		PartDefinition rightWing = partdefinition.addOrReplaceChild("rightWing", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition upperRight = rightWing.addOrReplaceChild("upperRight", CubeListBuilder.create().texOffs(61, -23).addBox(0.0F, -27.0F, 0.0F, 0.0F, 34.0F, 23.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -7.0F, 2.0F, 0.0F, -0.8727F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}


	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
		body.render(poseStack, buffer, packedLight, packedOverlay, color);
		rightArm.render(poseStack, buffer, packedLight, packedOverlay, color);
		leftArm.render(poseStack, buffer, packedLight, packedOverlay, color);
		rightLeg.render(poseStack, buffer, packedLight, packedOverlay, color);
		leftLeg.render(poseStack, buffer, packedLight, packedOverlay, color);
		head.render(poseStack, buffer, packedLight, packedOverlay, color);
		leftWing.render(poseStack, buffer, packedLight, packedOverlay, color);
		rightWing.render(poseStack, buffer, packedLight, packedOverlay, color);
	}
}