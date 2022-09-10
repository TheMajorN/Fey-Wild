package com.themajorn.feywild.client.entity.models;

import com.themajorn.feywild.FeyWild;
import com.themajorn.feywild.common.entities.quipper.QuipperEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class QuipperModel extends AnimatedGeoModel<QuipperEntity> {

    @Override
    public ResourceLocation getModelLocation(QuipperEntity object) {
        return new ResourceLocation(FeyWild.MOD_ID, "geo/quipper.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(QuipperEntity object) {
        return new ResourceLocation(FeyWild.MOD_ID, "textures/entities/quipper.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(QuipperEntity animatable) {
        return new ResourceLocation(FeyWild.MOD_ID, "animations/quipper.animation.json");
    }

}
