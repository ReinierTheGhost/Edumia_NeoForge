package com.legends.edumia.client.models.npcs;

import com.legends.edumia.client.models.EdumiaPartNames;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class GensaiModel {


	public static LayerDefinition createBodyLayer(CubeDeformation deformation) {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(deformation, 0.0f);
		PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild(PartNames.HAT, CubeListBuilder.create().texOffs(96, 0)
                        .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 16.0F, 8.0F, deformation.extend(0.25F)),
                PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild(PartNames.BODY, CubeListBuilder.create().texOffs(0, 16)
                .addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, deformation),
                PartPose.offset(0.0F, 0.0F, 0.0F));

		body.addOrReplaceChild(EdumiaPartNames.BREAST, CubeListBuilder.create().texOffs(70, 0)
                .addBox(-3.0F, 2.0F, -4.0F, 6.0F, 3.0F, 2.0F, deformation),
                PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition coracoidLeft = body.addOrReplaceChild("coracoidLeft", CubeListBuilder.create().texOffs(60, 35)
                .addBox(0.0F, -1.5F, -1.5F, 5.0F, 3.0F, 3.0F, deformation),
                PartPose.offsetAndRotation(1.5F, 3.5F, 2.5F, 0.0436F, -0.5236F, 0.0F));

		PartDefinition humerusLeft = coracoidLeft.addOrReplaceChild("humerusLeft", CubeListBuilder.create().texOffs(60, 28)
                .addBox(-0.1F, -3.1F, -2.0F, 7.0F, 3.0F, 4.0F, deformation),
                PartPose.offsetAndRotation(4.7F, 1.4F, 0.1F, 0.0F, 0.1745F, 0.0F));

		PartDefinition ulnaLeft = humerusLeft.addOrReplaceChild("ulnaLeft", CubeListBuilder.create().texOffs(46, 16)
                .addBox(0.0F, -3.5F, -1.5F, 9.0F, 3.0F, 3.0F, deformation),
                PartPose.offsetAndRotation(6.5F, 0.2F, 0.1F, 0.0F, 0.0873F, 0.0F));

		PartDefinition carpalsLeft = ulnaLeft.addOrReplaceChild("carpalsLeft", CubeListBuilder.create().texOffs(54, 60)
                .addBox(0.0F, -3.0F, -1.0F, 5.0F, 2.0F, 2.0F, deformation),
                PartPose.offsetAndRotation(8.5F, 0.0F, 0.0F, 0.0F, 0.0873F, 0.0F));

		carpalsLeft.addOrReplaceChild("feathersPrimaryLeft", CubeListBuilder.create().texOffs(22, 42)
                .addBox(0.0F, -4.1F, -0.5F, 11.0F, 11.0F, 0.0F, deformation),
                PartPose.offset(0.0F, 0.0F, 0.0F));

		ulnaLeft.addOrReplaceChild("feathersSecondaryLeft", CubeListBuilder.create().texOffs(24, 16)
                .addBox(-2.0F, -2.0F, -0.5F, 11.0F, 12.0F, 0.0F, deformation),
                PartPose.offset(0.0F, 1.0F, 0.0F));

		humerusLeft.addOrReplaceChild("feathersTertiaryLeft", CubeListBuilder.create().texOffs(24, 28)
                .addBox(0.0F, -2.0F, -0.5F, 10.0F, 14.0F, 0.0F, deformation),
                PartPose.offset(0.0F, 1.5F, 1.0F));

		coracoidLeft.addOrReplaceChild("feathersCoracoidLeft", CubeListBuilder.create().texOffs(60, 41)
                .addBox(0.0F, -2.0F, -1.0F, 6.0F, 8.0F, 0.0F, deformation),
                PartPose.offset(0.4F, 2.0F, 1.0F));

		PartDefinition coracoidRight = body.addOrReplaceChild("coracoidRight", CubeListBuilder.create().texOffs(38, 60)
                .addBox(-5.0F, -1.5F, -1.5F, 5.0F, 3.0F, 3.0F, deformation),
                PartPose.offsetAndRotation(-1.5F, 3.5F, 2.5F, 0.0436F, 0.5236F, 0.0F));

		PartDefinition humerusRight = coracoidRight.addOrReplaceChild("humerusRight", CubeListBuilder.create().texOffs(0, 55)
                .addBox(-6.9F, -3.1F, -2.0F, 7.0F, 3.0F, 4.0F, deformation),
                PartPose.offsetAndRotation(-4.7F, 1.4F, 0.1F, 0.0F, -0.1745F, 0.0F));

		PartDefinition ulnaRight = humerusRight.addOrReplaceChild("ulnaRight", CubeListBuilder.create().texOffs(46, 22)
                .addBox(-9.0F, -3.5F, -1.5F, 9.0F, 3.0F, 3.0F, deformation),
                PartPose.offsetAndRotation(-6.5F, 0.2F, 0.1F, 0.0F, -0.0873F, 0.0F));

		PartDefinition carpalsRight = ulnaRight.addOrReplaceChild("carpalsRight", CubeListBuilder.create().texOffs(0, 62)
                .addBox(-5.0F, -3.0F, -1.0F, 5.0F, 2.0F, 2.0F, deformation),
                PartPose.offsetAndRotation(-8.5F, 0.0F, 0.0F, 0.0F, -0.0873F, 0.0F));

		carpalsRight.addOrReplaceChild("feathersPrimaryRight", CubeListBuilder.create().texOffs(0, 44)
                .addBox(-11.0F, -4.1F, -0.5F, 11.0F, 11.0F, 0.0F, deformation),
                PartPose.offset(0.0F, 0.0F, 0.0F));

		ulnaRight.addOrReplaceChild("feathersSecondaryRight", CubeListBuilder.create().texOffs(0, 32)
                .addBox(-9.0F, -2.0F, -0.5F, 11.0F, 12.0F, 0.0F, deformation),
                PartPose.offset(0.0F, 1.0F, 0.0F));

		humerusRight.addOrReplaceChild("feathersTertiaryRight", CubeListBuilder.create().texOffs(32, 0)
                .addBox(-10.0F, -2.0F, -0.5F, 10.0F, 14.0F, 0.0F, deformation),
                PartPose.offset(0.0F, 1.5F, 1.0F));

		coracoidRight.addOrReplaceChild("feathersCoracoidRight", CubeListBuilder.create().texOffs(60, 49)
                .addBox(-6.0F, -2.0F, -1.0F, 6.0F, 8.0F, 0.0F, deformation),
                PartPose.offset(-0.4F, 2.0F, 1.0F));

		partdefinition.addOrReplaceChild(PartNames.RIGHT_ARM, CubeListBuilder.create().texOffs(44, 44)
                .addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, deformation),
                PartPose.offset(-7.0F, 2.5F, 0.0F));

		partdefinition.addOrReplaceChild(PartNames.LEFT_ARM, CubeListBuilder.create().texOffs(44, 28)
                .addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, deformation),
                PartPose.offset(7.0F, 2.5F, 0.0F));

		partdefinition.addOrReplaceChild(PartNames.RIGHT_LEG, CubeListBuilder.create().texOffs(52, 0)
                .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, deformation),
                PartPose.offset(-1.9F, 12.0F, 0.0F));

		partdefinition.addOrReplaceChild(PartNames.LEFT_LEG, CubeListBuilder.create().texOffs(22, 53)
                .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, deformation),
                PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild(PartNames.HEAD, CubeListBuilder.create().texOffs(0, 0)
                .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, deformation),
                PartPose.offset(0.0F, 0.0F, 0.0F));



		return LayerDefinition.create(meshdefinition, 128, 128);
	}
}