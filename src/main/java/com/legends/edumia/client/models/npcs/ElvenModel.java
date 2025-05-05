package com.legends.edumia.client.models.npcs;


import com.legends.edumia.client.models.EdumiaPartNames;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class ElvenModel{

	public static LayerDefinition createBodyLayer(CubeDeformation deformation) {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild(PartNames.BODY, CubeListBuilder.create()
						.texOffs(16, 16)
						.addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, deformation),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition breast = body.addOrReplaceChild(EdumiaPartNames.BREAST, CubeListBuilder.create().texOffs(34, 1)
				.addBox(-3.0F, 2.0F, -4.0F, 6.0F, 3.0F, 2.0F, deformation),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition rightArm = partdefinition.addOrReplaceChild(PartNames.RIGHT_ARM, CubeListBuilder.create()
						.texOffs(32, 48)
				.addBox(-2F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, deformation),
				PartPose.offset(-5.0F, 2.5F, 0.0F));

		PartDefinition leftArm = partdefinition.addOrReplaceChild(PartNames.LEFT_ARM, CubeListBuilder.create()
						.texOffs(40, 16)
				.addBox(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, deformation),
				PartPose.offset(5.0F, 2.5F, 0.0F));

		PartDefinition rightLeg = partdefinition.addOrReplaceChild(PartNames.RIGHT_LEG, CubeListBuilder.create()
				.texOffs(16, 48)
				.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, deformation),
				PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition leftLeg = partdefinition.addOrReplaceChild(PartNames.LEFT_LEG, CubeListBuilder.create()
				.texOffs(0, 16)
				.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, deformation),
				PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild(PartNames.HEAD, CubeListBuilder.create()
				.texOffs(0, 0)
				.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, deformation),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild(PartNames.HAT, CubeListBuilder.create().texOffs(54, 0)
				.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 20.0F, 8.0F,
						new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition rightEar = head.addOrReplaceChild(EdumiaPartNames.RIGHT_EAR, CubeListBuilder.create().texOffs(2, 2)
				.addBox(0.0F, 0.0F, -1.0F, 1.0F, 2.0F, 2.0F, deformation),
				PartPose.offsetAndRotation(-5.0F, -4.0F, -0.25F, -0.7F, 0.0F, -0.35F));

		PartDefinition bone2 = rightEar.addOrReplaceChild(EdumiaPartNames.RIGHT_EAR, CubeListBuilder.create().texOffs(3, 3)
				.addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, deformation),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leftEar = head.addOrReplaceChild(EdumiaPartNames.LEFT_EAR, CubeListBuilder.create().texOffs(2, 2)
				.addBox(0.0F, 0.0F, -1.0F, 1.0F, 2.0F, 2.0F, deformation),
				PartPose.offsetAndRotation(4.0F, -4.0F, -0.25F, -0.7F, 0.0F, 0.35F));

		PartDefinition bone = leftEar.addOrReplaceChild(EdumiaPartNames.LEFT_EAR, CubeListBuilder.create().texOffs(3, 3)
				.addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, deformation),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 86, 64);
	}

}