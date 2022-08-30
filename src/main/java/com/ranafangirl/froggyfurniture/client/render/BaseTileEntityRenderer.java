package com.ranafangirl.froggyfurniture.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.ranafangirl.froggyfurniture.tileentity.LotusCabinetTileEntity;

import net.minecraft.block.HorizontalBlock;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;

public class BaseTileEntityRenderer extends TileEntityRenderer<TileEntity> {
	private Model modelBase;
	private ResourceLocation texture;

	public BaseTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
	}

	public BaseTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn, Model model, ResourceLocation location) {
		super(rendererDispatcherIn);
		this.modelBase = model;
		this.texture = location;
	}	
	
	@Override
	public void render(TileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
		IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.modelBase.renderType(texture));
		float rot = 0;

		if (tileEntityIn instanceof LotusCabinetTileEntity) {
			switch (tileEntityIn.getBlockState().getValue(HorizontalBlock.FACING)) {
			case NORTH: default: 	rot = 0; break;
			case EAST: 				rot = 90; break;
			case SOUTH: 			rot = 180; break;
			case WEST: 				rot = 270; break;
			}			
		}

		matrixStackIn.pushPose();
		matrixStackIn.translate(0.5D, 0.0D, 0.5D);
		matrixStackIn.scale(getScale(), getScale(), getScale());
		matrixStackIn.translate(0.0D, 1.5D, 0.0D);
		matrixStackIn.mulPose(new Quaternion(180, rot, 0, true));
		modelBase.renderToBuffer(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn, 1, 1, 1, 1);
		matrixStackIn.popPose();
	}

	public float getScale() {
		return 1;
	}

	public void setTexture(ResourceLocation texture) {
		this.texture = texture;
	}

	public void setModel(Model modelBase) {
		this.modelBase = modelBase;
	}
}

