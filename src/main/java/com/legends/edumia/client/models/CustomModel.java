package com.legends.edumia.client.models;
// Made with Blockbench 4.12.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class CustomModel {

	public static LayerDefinition createBodyLayer() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition root = modelData.getRoot();

		root.addOrReplaceChild(PartNames.BODY, CubeListBuilder.create().texOffs(-8, -2).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		root.addOrReplaceChild(PartNames.RIGHT_ARM, CubeListBuilder.create().texOffs(-4, -2).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, 2.5F, 0.0F));

		root.addOrReplaceChild(PartNames.LEFT_ARM, CubeListBuilder.create().texOffs(-4, -2).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, 2.5F, 0.0F));

		root.addOrReplaceChild(PartNames.RIGHT_LEG, CubeListBuilder.create().texOffs(-4, -2).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		root.addOrReplaceChild(PartNames.LEFT_LEG, CubeListBuilder.create().texOffs(-4, -2).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F, 0.0F));

		root.addOrReplaceChild(PartNames.HEAD, CubeListBuilder.create().texOffs(-12, -6).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(modelData, 64, 64);
	}


}