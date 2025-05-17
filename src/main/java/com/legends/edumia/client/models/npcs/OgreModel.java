package com.legends.edumia.client.models.npcs;

import com.legends.edumia.client.models.EdumiaPartNames;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class OgreModel{

	public static LayerDefinition createBodyLayer(CubeDeformation deformation) {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild(PartNames.BODY, CubeListBuilder.create().texOffs(0, 0)
				.addBox(-5.0F, 0.0F, -4.0F, 10.0F, 14.0F, 7.0F, deformation),
				PartPose.offset(0.0F, -4.0F, 0.0F));

		body.addOrReplaceChild(EdumiaPartNames.BREAST, CubeListBuilder.create().texOffs(0, 90)
				.addBox(-4.0F, -1.5F, -1.5F, 8.0F, 3.0F, 3.0F, deformation),
				PartPose.offsetAndRotation(0.0F, 4.591F, -4.4166F, -0.0873F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild(PartNames.LEFT_LEG, CubeListBuilder.create().texOffs(46, 97)
				.addBox(-2.5F, 11.0F, -4.0F, 5.0F, 3.0F, 7.0F,
						deformation.extend(0.2f))
		.texOffs(27, 18)
				.addBox(-2.5F, 0.0F, -4.0F, 5.0F, 14.0F, 7.0F, deformation)
		.texOffs(104, 93)
				.addBox(-2.5F, 0.4F, -4.0F, 5.0F, 7.0F, 7.0F,
						deformation.extend(0.2f)),
				PartPose.offset(2.5F, 10.0F, -0.0F));

		partdefinition.addOrReplaceChild(PartNames.RIGHT_LEG, CubeListBuilder.create().texOffs(70, 97)
				.addBox(-2.5F, 11.0F, -4.0F, 5.0F, 3.0F, 7.0F,
						deformation.extend(0.2f))
		.texOffs(51, 18)
				.addBox(-2.5F, 0.0F, -4.0F, 5.0F, 14.0F, 7.0F, deformation)
		.texOffs(104, 76)
				.addBox(-2.5F, 0.4F, -4.0F, 5.0F, 7.0F, 7.0F,
						deformation.extend(0.2f)),
				PartPose.offset(-2.5F, 10.0F, -0.0F));

		partdefinition.addOrReplaceChild(PartNames.LEFT_ARM, CubeListBuilder.create().texOffs(2, 23)
				.addBox(0.0F, -2.0F, -3F, 5.0F, 14.0F, 5.0F, deformation)
		.texOffs(72, 109)
				.addBox(0.0F, -2.0F, -3F, 5.0F, 14.0F, 5.0F,
						deformation.extend(0.2f)),
				PartPose.offset(5.0F, -2.0F, -0.0F));

		partdefinition.addOrReplaceChild(PartNames.RIGHT_ARM, CubeListBuilder.create().texOffs(48, 109)
				.addBox(-5.0F, -2.0F, -3F, 5.0F, 14.0F, 5.0F,
						deformation.extend(0.2f))
		.texOffs(2, 44)
				.addBox(-5.0F, -2.0F, -3F, 5.0F, 14.0F, 5.0F, deformation),
				PartPose.offset(-5.0F, -2.0F, -0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild(PartNames.HEAD, CubeListBuilder.create().texOffs(34, 0)
				.addBox(-4.5F, -9.0F, -5.0F, 9.0F, 9.0F, 9.0F, deformation),
				PartPose.offset(0.0F, -4.0F, 0.0F));

		head.addOrReplaceChild(EdumiaPartNames.LEFT_EAR, CubeListBuilder.create().texOffs(51, 39)
				.addBox(4.5F, -5.9429F, 0.3571F, 3.0F, 5.0F, 0.0F, deformation),
				PartPose.offset(0.0F, -0.0571F, -0.3571F));

		head.addOrReplaceChild(EdumiaPartNames.RIGHT_EAR, CubeListBuilder.create().texOffs(57, 39)
				.addBox(-7.5F, -5.9429F, 0.3571F, 3.0F, 5.0F, 0.0F, deformation),
				PartPose.offset(0.0F, -0.0571F, -0.3571F));

		head.addOrReplaceChild(PartNames.LEFT_HORN, CubeListBuilder.create().texOffs(38, 40)
				.addBox(6.5F, -3.75F, -0.75F, 2.0F, 6.0F, 2.0F, deformation)
				.texOffs(27, 39)
				.addBox(4.5F, -0.75F, -1.75F, 2.0F, 3.0F, 3.0F, deformation),
				PartPose.offsetAndRotation(0.0F, -8.1929F, 0.5071F, -0.3927F, 0.0F, 0.0F));

		head.addOrReplaceChild(PartNames.RIGHT_HORN, CubeListBuilder.create().texOffs(39, 40)
				.addBox(-8.5F, -3.75F, -0.75F, 2.0F, 6.0F, 2.0F, deformation)
				.texOffs(27, 39)
				.addBox(-6.5F, -0.75F, -1.75F, 2.0F, 3.0F, 3.0F, deformation),
				PartPose.offsetAndRotation(0.0F, -8.25F, 0.15F, -0.3927F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild(PartNames.HAT, CubeListBuilder.create().texOffs(92, 0)
				.addBox(-4.5F, -9.0F, -5.0F, 9.0F, 9.0F, 9.0F,
						deformation.extend(0.25f)),
				PartPose.offset(0.0F, -4.0F, 0.0F));

		body.addOrReplaceChild(PartNames.JACKET, CubeListBuilder.create().texOffs(94, 107)
				.addBox(-5.0F, 0.0F, -4.0F, 10.0F, 14.0F, 7.0F,
						deformation.extend(0.2f)),
				PartPose.offset(0, 0, 0));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}
}