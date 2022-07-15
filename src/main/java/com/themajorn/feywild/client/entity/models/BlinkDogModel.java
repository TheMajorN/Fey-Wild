package com.themajorn.feywild.client.entity.models;

import com.themajorn.feywild.FeyWild;
import com.themajorn.feywild.common.entities.AlmirajEntity;
import com.themajorn.feywild.common.entities.BlinkDogEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BlinkDogModel  extends AnimatedGeoModel<BlinkDogEntity> {

    @Override
    public ResourceLocation getModelLocation(BlinkDogEntity object) {
        return new ResourceLocation(FeyWild.MOD_ID, "geo/blink_dog.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BlinkDogEntity object) {
        return new ResourceLocation(FeyWild.MOD_ID, "textures/entities/blink_dog.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BlinkDogEntity animatable) {
        return new ResourceLocation(FeyWild.MOD_ID, "animations/blink_dog.animation.json");
    }
}
