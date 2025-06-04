package com.legends.edumia.client.models.npcs;

import com.legends.edumia.client.models.EdumiaPartNames;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class DemonModel {

	public static LayerDefinition createBodyLayer(CubeDeformation deformation) {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild(PartNames.HAT, CubeListBuilder.create().texOffs(44, 22)
				.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 16.0F, 8.0F,
						deformation.extend(0.25F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild(PartNames.BODY, CubeListBuilder.create().texOffs(32, 0)
				.addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, deformation),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		body.addOrReplaceChild(EdumiaPartNames.BREAST, CubeListBuilder.create().texOffs(56, 8)
				.addBox(-3.0F, 2.0F, -4.0F, 6.0F, 3.0F, 2.0F, deformation),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail01 = body.addOrReplaceChild("tail01", CubeListBuilder.create().texOffs(0, 57)
				.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 4.0F, deformation),
				PartPose.offsetAndRotation(0.0F, 9.1F, 1.3F, -0.8727F, 0.0F, 0.0F));

		PartDefinition tail02 = tail01.addOrReplaceChild("tail02", CubeListBuilder.create().texOffs(0, 57)
				.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 4.0F, deformation),
				PartPose.offsetAndRotation(0.0F, 0.0F, 3.8F, 0.1396F, 0.0F, 0.0F));

		PartDefinition tail03 = tail02.addOrReplaceChild("tail03", CubeListBuilder.create().texOffs(12, 57)
				.addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 5.0F, deformation),
				PartPose.offsetAndRotation(0.0F, 0.0F, 2.9F, 0.0698F, 0.0F, 0.0F));

		PartDefinition tail04 = tail03.addOrReplaceChild("tail04", CubeListBuilder.create().texOffs(12, 57)
				.addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 5.0F, deformation),
				PartPose.offsetAndRotation(0.0F, 0.0F, 4.9F, 0.1396F, 0.0F, 0.0F));

		tail04.addOrReplaceChild("tail05", CubeListBuilder.create().texOffs(12, 57)
				.addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 5.0F, deformation),
				PartPose.offsetAndRotation(0.0F, 0.0F, 4.9F, 0.2269F, 0.0F, 0.0F));

		PartDefinition lWing01 = body.addOrReplaceChild("lWing01", CubeListBuilder.create().texOffs(30, 30)
				.addBox(-1.0F, -1.5F, 0.0F, 2.0F, 3.0F, 5.0F, deformation),
				PartPose.offsetAndRotation(2.5F, 2.1F, 1.4F, 0.2731F, 0.5236F, 0.0F));

		PartDefinition lWing02 = lWing01.addOrReplaceChild("lWing02", CubeListBuilder.create().texOffs(12, 31)
				.addBox(-1.0F, -1.2F, 0.0F, 2.0F, 2.0F, 8.0F, deformation),
				PartPose.offsetAndRotation(0.0F, 0.2F, 4.3F, 0.5236F, 0.0F, 0.0F));

		PartDefinition lWing03 = lWing02.addOrReplaceChild("lWing03", CubeListBuilder.create().texOffs(0, 31)
				.addBox(-1.0F, -0.7F, -1.0F, 2.0F, 8.0F, 2.0F, deformation),
				PartPose.offsetAndRotation(0.0F, -0.5F, 7.9F, 0.2094F, 0.0F, 0.0F));

		lWing03.addOrReplaceChild("lWing04", CubeListBuilder.create().texOffs(8, 32)
				.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 8.0F, 1.0F, deformation),
				PartPose.offsetAndRotation(0.0F, 7.0F, 0.0F, -0.4189F, 0.0F, 0.0F));

		lWing02.addOrReplaceChild("lWingMembrane", CubeListBuilder.create().texOffs(0, 6)
				.addBox(0.0F, -0.6F, -1.7F, 0.0F, 14.0F, 11.0F, deformation),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0911F, 0.0F, 0.0F));

		PartDefinition rWing01 = body.addOrReplaceChild("rWing01", CubeListBuilder.create().texOffs(30, 30)
				.addBox(-1.0F, -1.5F, 0.0F, 2.0F, 3.0F, 5.0F, deformation),
				PartPose.offsetAndRotation(-2.5F, 2.1F, 1.4F, 0.2731F, -0.5236F, 0.0F));

		PartDefinition rWing02 = rWing01.addOrReplaceChild("rWing02", CubeListBuilder.create().texOffs(12, 31)
				.addBox(-1.0F, -1.2F, 0.0F, 2.0F, 2.0F, 8.0F, deformation),
				PartPose.offsetAndRotation(0.0F, 0.2F, 4.3F, 0.5236F, 0.0F, 0.0F));

		PartDefinition rWing03 = rWing02.addOrReplaceChild("rWing03", CubeListBuilder.create().texOffs(0, 31)
				.addBox(-1.0F, -0.7F, -1.0F, 2.0F, 8.0F, 2.0F, deformation),
				PartPose.offsetAndRotation(0.0F, -0.5F, 7.9F, 0.2094F, 0.0F, 0.0F));

		PartDefinition rWing04 = rWing03.addOrReplaceChild("rWing04", CubeListBuilder.create().texOffs(8, 32)
				.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 8.0F, 1.0F, deformation),
				PartPose.offsetAndRotation(0.0F, 7.0F, 0.0F, -0.4189F, 0.0F, 0.0F));

		rWing02.addOrReplaceChild("rWingMembrane", CubeListBuilder.create().texOffs(0, 6)
				.addBox(0.0F, -0.6F, -1.7F, 0.0F, 14.0F, 11.0F, deformation),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0911F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild(PartNames.RIGHT_ARM, CubeListBuilder.create().texOffs(16, 63)
				.addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, deformation),
				PartPose.offset(-7.0F, 2.5F, 0.0F));

		partdefinition.addOrReplaceChild(PartNames.LEFT_ARM, CubeListBuilder.create().texOffs(16, 63).mirror()
				.addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, deformation)
				.mirror(false), PartPose.offset(7.0F, 2.5F, 0.0F));

		partdefinition.addOrReplaceChild(PartNames.RIGHT_LEG, CubeListBuilder.create().texOffs(0, 66).mirror()
				.addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, deformation)
				.mirror(false), PartPose.offset(-1.9F, 12.0F, 0.0F));

		partdefinition.addOrReplaceChild(PartNames.LEFT_LEG, CubeListBuilder.create().texOffs(0, 66)
				.addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, deformation),
				PartPose.offset(1.9F, 12.0F, 0.0F));

		partdefinition.addOrReplaceChild(PartNames.HEAD, CubeListBuilder.create().texOffs(0, 0)
				.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, deformation),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

}