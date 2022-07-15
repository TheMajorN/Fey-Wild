package com.themajorn.feywild.client.entity.renderers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.themajorn.feywild.FeyWild;
import com.themajorn.feywild.client.entity.models.AlmirajModel;
import com.themajorn.feywild.client.entity.models.BlinkDogModel;
import com.themajorn.feywild.common.entities.AlmirajEntity;
import com.themajorn.feywild.common.entities.BlinkDogEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

public class BlinkDogRenderer extends GeoEntityRenderer<BlinkDogEntity> {
    public BlinkDogRenderer(EntityRendererManager renderManager) {
        super(renderManager, new BlinkDogModel());
        this.shadowRadius = 0.4F;
    }

    @Override
    public ResourceLocation getTextureLocation(BlinkDogEntity instance) {
        return new ResourceLocation(FeyWild.MOD_ID, "textures/entities/blink_dog.png");
    }

    @Override
    public RenderType getRenderType(BlinkDogEntity animatable, float partialTicks, MatrixStack stack, @Nullable IRenderTypeBuffer renderTypeBuffer, @Nullable IVertexBuilder vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }

}
