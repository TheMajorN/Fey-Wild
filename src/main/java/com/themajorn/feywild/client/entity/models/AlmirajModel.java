package com.themajorn.feywild.client.entity.models;

import com.themajorn.feywild.FeyWild;
import com.themajorn.feywild.common.entities.AlmirajEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AlmirajModel extends AnimatedGeoModel<AlmirajEntity> {
    @Override
    public ResourceLocation getModelLocation(AlmirajEntity object) {
        return new ResourceLocation(FeyWild.MOD_ID, "geo/almiraj.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(AlmirajEntity object) {
        return new ResourceLocation(FeyWild.MOD_ID, "textures/entities/almiraj.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(AlmirajEntity animatable) {
        return new ResourceLocation(FeyWild.MOD_ID, "animations/almiraj.animation.json");
    }
}
