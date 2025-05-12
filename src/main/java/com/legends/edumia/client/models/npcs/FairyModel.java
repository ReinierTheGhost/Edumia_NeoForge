package com.legends.edumia.client.models.npcs;

import com.legends.edumia.client.models.EdumiaPartNames;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;



public class FairyModel {

	public static LayerDefinition createBodyLayer(CubeDeformation deformation) {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(deformation, 0.0f);
		PartDefinition partdefinition = meshdefinition.getRoot();


		partdefinition.addOrReplaceChild(PartNames.HAT, CubeListBuilder.create().texOffs(0, 30)
				.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F,
						deformation.extend(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition rightArm = partdefinition.addOrReplaceChild(PartNames.RIGHT_ARM,
				CubeListBuilder.create().texOffs(16, 46)
				.addBox(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F,
								deformation), PartPose.offset(-5.0F, 2.5F, 0.0F));

		PartDefinition leftArm = partdefinition.addOrReplaceChild(PartNames.LEFT_ARM,
				CubeListBuilder.create().texOffs(0, 46)
				.addBox(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F,
						deformation), PartPose.offset(5.0F, 2.5F, 0.0F));


		PartDefinition body = partdefinition.addOrReplaceChild(PartNames.BODY,
				CubeListBuilder.create().texOffs(32, 30)
				.addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F,
						deformation), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition breast = body.addOrReplaceChild(EdumiaPartNames.BREAST, CubeListBuilder.create().texOffs(0, 0)
				.addBox(-7.0F, -1.0F, -0.8F, 8.0F, 2.0F, 2.0F, deformation),
				PartPose.offsetAndRotation(3.0F, 3.0F, -2.0F, -0.8727F, 0.0F, 0.0F));


		PartDefinition rightLeg = partdefinition.addOrReplaceChild(PartNames.RIGHT_LEG,
				CubeListBuilder.create().texOffs(32, 46)
						.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F,
								deformation), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition leftLeg = partdefinition.addOrReplaceChild(PartNames.LEFT_LEG,
				CubeListBuilder.create().texOffs(48, 46)
						.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F,
								deformation), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild(PartNames.HEAD,
				CubeListBuilder.create().texOffs(84, 43)
						.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 16.0F, 8.0F,
								new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leftWing = body.addOrReplaceChild(PartNames.LEFT_WING, CubeListBuilder.create().texOffs(61, -23)
				.addBox(0.0F, -18.0F, 0.0F, 0.0F, 34.0F, 23.0F, deformation),
				PartPose.offsetAndRotation(1.0F, 8.0F, 2.0F, 0.0F, 0.8727F, 0.0F));

		PartDefinition rightWing = body.addOrReplaceChild(PartNames.RIGHT_WING, CubeListBuilder.create().texOffs(61, -23)
				.addBox(0.0F, -18.0F, 0.0F, 0.0F, 34.0F, 23.0F, deformation),
				PartPose.offsetAndRotation(-1.0F, 8.0F, 2.0F, 0.0F, -0.8727F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}
}