package com.legends.edumia.client.models.npcs;

import com.legends.edumia.client.models.EdumiaPartNames;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class OrcModel {

	public static LayerDefinition createBodyLayer(CubeDeformation deformation) {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(deformation, 0.0f);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild(PartNames.HEAD, CubeListBuilder.create().texOffs(0, 0)
				.addBox(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F,
						deformation)
		.texOffs(40, 5)
				.addBox(-4.0F, -3.0F, -7.0F, 8.0F, 3.0F, 2.0F,
						deformation), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition teeth = head.addOrReplaceChild(EdumiaPartNames.TEETH, CubeListBuilder.create().texOffs(60, 7)
				.addBox(-3.0F, -3.0F, -2.0F, 1.0F, 2.0F, 1.0F, deformation)
		.texOffs(60, 7)
				.addBox(2.0F, -3.0F, -2.0F, 1.0F, 2.0F, 1.0F, deformation),
				PartPose.offsetAndRotation(0.0F, -2.0F, -5.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition nose = head.addOrReplaceChild(PartNames.NOSE, CubeListBuilder.create().texOffs(30, 0)
				.addBox(-1.0F, -3.0F, -2.0F, 2.0F, 3.0F, 3.0F, deformation),
				PartPose.offsetAndRotation(0.0F, -4.0F, -5.0F, -0.3054F, 0.0F, 0.0F));

		PartDefinition hat = partdefinition.addOrReplaceChild(PartNames.HAT, CubeListBuilder.create().texOffs(0, 64)
				.addBox(-1.0F, -12.5F, -1.5F, 2.0F, 10.0F, 8.0F, deformation)
		.texOffs(26, 68)
				.addBox(-4.0F, -10.0F, -5.0F, 9.0F, 18.0F, 10.0F,
						deformation.extend(0.25f)), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition right_ear = head.addOrReplaceChild(EdumiaPartNames.RIGHT_EAR, CubeListBuilder.create().texOffs(0, 0)
				.addBox(-6.0F, -7.0F, -1.0F, 1.0F, 3.0F, 3.0F, deformation),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_ear = head.addOrReplaceChild(EdumiaPartNames.LEFT_EAR, CubeListBuilder.create()
				.texOffs(0, 0).mirror()
				.addBox(-6.0F, -7.0F, -1.0F, 1.0F, 3.0F, 3.0F, deformation)
				.mirror(false), PartPose.offset(11.0F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild(PartNames.BODY, CubeListBuilder.create().texOffs(0, 20)
				.addBox(-6.0F, 0.0F, -4.0F, 12.0F, 16.0F, 8.0F, deformation),
				PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition breast = body.addOrReplaceChild(EdumiaPartNames.BREAST, CubeListBuilder.create().texOffs(40, 21)
				.addBox(-5.0F, 3.0F, -6.0F, 10.0F, 3.0F, 2.0F, deformation),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition rightArm = partdefinition.addOrReplaceChild(PartNames.RIGHT_ARM, CubeListBuilder.create()
				.texOffs(20, 50).mirror()
				.addBox(-12.0F, -25.0F, -3.0F, 6.0F, 8.0F, 6.0F, deformation)
				.mirror(false)
		.texOffs(0, 49).mirror()
				.addBox(-11.5F, -17.0F, -2.5F, 5.0F, 10.0F, 5.0F, deformation
				).mirror(false), PartPose.offset(0.0F, 18.0F, 0.0F));

		PartDefinition leftArm = partdefinition.addOrReplaceChild(PartNames.LEFT_ARM, CubeListBuilder.create()
				.texOffs(20, 50)
				.addBox(-5.0F, -8.0F, -1.0F, 6.0F, 8.0F, 6.0F, deformation)
				.texOffs(0, 49)
				.addBox(-4.5F, 0.0F, -0.5F, 5.0F, 10.0F, 5.0F, deformation),
				PartPose.offset(11.0F, 1.0F, -2.0F));

		PartDefinition rightLeg = partdefinition.addOrReplaceChild(PartNames.RIGHT_LEG, CubeListBuilder.create()
				.texOffs(40, 28)
				.addBox(-6.0F, -16.0F, -3.0F, 6.0F, 16.0F, 6.0F, deformation),
				PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition leftLeg = partdefinition.addOrReplaceChild(PartNames.LEFT_LEG, CubeListBuilder.create()
				.texOffs(40, 28).mirror()
				.addBox(-6.0F, -16.0F, -3.0F, 6.0F, 16.0F, 6.0F, deformation)
				.mirror(false), PartPose.offset(6.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 96);
	}

}