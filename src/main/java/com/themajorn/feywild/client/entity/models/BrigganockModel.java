package com.themajorn.feywild.client.entity.models;

import com.themajorn.feywild.FeyWild;
import com.themajorn.feywild.common.entities.brigganock.BrigganockEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BrigganockModel extends AnimatedGeoModel<BrigganockEntity> {
    @Override
    public ResourceLocation getModelLocation(BrigganockEntity object) {
        return new ResourceLocation(FeyWild.MOD_ID, "geo/brigganock.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BrigganockEntity object) {
        return new ResourceLocation(FeyWild.MOD_ID, "textures/entities/brigganock.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BrigganockEntity animatable) {
        return new ResourceLocation(FeyWild.MOD_ID, "animations/brigganock.animation.json");
    }
}
