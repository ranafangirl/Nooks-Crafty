package com.ranafangirl.froggyfurniture.client.render.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.ranafangirl.froggyfurniture.FroggyFurniture;
import com.ranafangirl.froggyfurniture.entity.ChairEntity;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EmptyRenderer extends EntityRenderer<ChairEntity> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(FroggyFurniture.MOD_ID, "textures/block/nothing.png");
    
    public EmptyRenderer(final EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }
    
    public ResourceLocation getTextureLocation(final ChairEntity entity) {
        return EmptyRenderer.TEXTURE;
    }
    
    public void render(final ChairEntity entityIn, final float entityYaw, final float partialTicks, final MatrixStack matrixStackIn, final IRenderTypeBuffer bufferIn, final int packedLightIn) {
        matrixStackIn.pushPose();
        matrixStackIn.translate(0.0, 0.15000000596046448, 0.0);
        matrixStackIn.translate(0.0, -1.4500000476837158, 0.0);
        matrixStackIn.popPose();
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }
}