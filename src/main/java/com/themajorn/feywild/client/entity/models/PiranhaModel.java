package com.themajorn.feywild.client.entity.models;

import com.themajorn.feywild.FeyWild;
import com.themajorn.feywild.common.entities.AlmirajEntity;
import com.themajorn.feywild.common.entities.PiranhaEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PiranhaModel extends AnimatedGeoModel<PiranhaEntity> {

    @Override
    public ResourceLocation getModelLocation(PiranhaEntity object) {
        return new ResourceLocation(FeyWild.MOD_ID, "geo/piranha.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(PiranhaEntity object) {
        return new ResourceLocation(FeyWild.MOD_ID, "textures/entities/piranha.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(PiranhaEntity animatable) {
        return new ResourceLocation(FeyWild.MOD_ID, "animations/piranha.animation.json");
    }

}
