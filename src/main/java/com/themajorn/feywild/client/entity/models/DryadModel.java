package com.themajorn.feywild.client.entity.models;

import com.themajorn.feywild.FeyWild;
import com.themajorn.feywild.common.entities.DryadEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DryadModel extends AnimatedGeoModel<DryadEntity> {
    @Override
    public ResourceLocation getModelLocation(DryadEntity object) {
        return new ResourceLocation(FeyWild.MOD_ID, "geo/dryad.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(DryadEntity object) {
        return new ResourceLocation(FeyWild.MOD_ID, "textures/entities/dryad.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(DryadEntity animatable) {
        return new ResourceLocation(FeyWild.MOD_ID, "animations/dryad.animation.json");
    }
}
