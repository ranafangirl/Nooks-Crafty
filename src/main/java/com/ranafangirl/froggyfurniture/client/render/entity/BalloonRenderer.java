package com.ranafangirl.froggyfurniture.client.render.entity;

import com.ranafangirl.froggyfurniture.client.render.model.BalloonModel;
import com.ranafangirl.froggyfurniture.entity.BalloonEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class BalloonRenderer extends GeoEntityRenderer<BalloonEntity> {
	public BalloonRenderer(final EntityRendererManager renderManager) {
        super(renderManager, new BalloonModel());
    }
}