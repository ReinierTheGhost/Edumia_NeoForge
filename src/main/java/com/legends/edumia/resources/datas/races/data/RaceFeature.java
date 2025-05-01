package com.legends.edumia.resources.datas.races.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;

public class RaceFeature {
    private final ResourceLocation id;
    private final String featureType; // e.g., "WINGS", "EARS", etc.

    public static final Codec<RaceFeature> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("id").forGetter(RaceFeature::getId),
            Codec.STRING.fieldOf("feature_type").forGetter(RaceFeature::getFeatureType)
    ).apply(instance, RaceFeature::new));


    public RaceFeature(ResourceLocation id, String featureType) {
        this.id = id;
        this.featureType = featureType;
    }

    public ResourceLocation getId() {
        return id;
    }

    public String getFeatureType() {
        return featureType;
    }

}
