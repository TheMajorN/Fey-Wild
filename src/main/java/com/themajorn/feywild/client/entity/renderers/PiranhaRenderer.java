package com.themajorn.feywild.client.entity.renderers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.themajorn.feywild.FeyWild;
import com.themajorn.feywild.client.entity.models.PiranhaModel;
import com.themajorn.feywild.common.entities.PiranhaEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

public class PiranhaRenderer extends GeoEntityRenderer<PiranhaEntity> {

    public PiranhaRenderer(EntityRendererManager renderManager) {
        super(renderManager, new PiranhaModel());
        this.shadowRadius = 0.3F;
    }

    @Override
    public ResourceLocation getTextureLocation(PiranhaEntity instance) {
        return new ResourceLocation(FeyWild.MOD_ID, "textures/entities/piranha.png");
    }

    @Override
    public RenderType getRenderType(PiranhaEntity animatable, float partialTicks, MatrixStack stack, @Nullable IRenderTypeBuffer renderTypeBuffer, @Nullable IVertexBuilder vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        stack.scale(0.8F, 0.8F, 0.8F);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }

}
