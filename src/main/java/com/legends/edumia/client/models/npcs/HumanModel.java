package com.legends.edumia.client.models.npcs;

import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class HumanModel {


	public static LayerDefinition createBodyLayer(CubeDeformation deformation) {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild(PartNames.HAT, CubeListBuilder.create().texOffs(0, 32)
				.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 16.0F, 8.0F,
						deformation.extend(0.25f)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild(PartNames.BODY, CubeListBuilder.create().texOffs(16, 16)
				.addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, deformation),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition breast = body.addOrReplaceChild("breast", CubeListBuilder.create().texOffs(24, 0)
				.addBox(-3.0F, 2.0F, -4.0F, 6.0F, 3.0F, 2.0F, deformation),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition rightArm = partdefinition.addOrReplaceChild(PartNames.RIGHT_ARM, CubeListBuilder.create()
				.texOffs(40, 16)
				.addBox(-2.5F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, deformation),
				PartPose.offset(-7.0F, 2.5F, 0.0F));

		PartDefinition leftArm = partdefinition.addOrReplaceChild(PartNames.LEFT_ARM, CubeListBuilder.create()
				.texOffs(40, 16)
				.addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, deformation),
				PartPose.offset(17.0F, 2.5F, 0.0F));

		PartDefinition rightLeg = partdefinition.addOrReplaceChild(PartNames.RIGHT_LEG, CubeListBuilder.create()
				.texOffs(0, 16)
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



		return LayerDefinition.create(meshdefinition, 64, 64);
	}
}