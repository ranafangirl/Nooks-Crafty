package com.ranafangirl.froggyfurniture.client.render.model;

import com.ranafangirl.froggyfurniture.FroggyFurniture;
import com.ranafangirl.froggyfurniture.entity.BalloonEntity;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BalloonModel extends AnimatedGeoModel<BalloonEntity> {
	protected static final ResourceLocation RED		= new ResourceLocation("froggyfurniture", "textures/entity/balloon/red_balloon.png");;
	protected static final ResourceLocation YELLOW	= new ResourceLocation("froggyfurniture", "textures/entity/balloon/yellow_balloon.png");
	protected static final ResourceLocation GREEN	= new ResourceLocation("froggyfurniture", "textures/entity/balloon/green_balloon.png");
	protected static final ResourceLocation BLUE	= new ResourceLocation("froggyfurniture", "textures/entity/balloon/blue_balloon.png");
	protected static final ResourceLocation PINK	= new ResourceLocation("froggyfurniture", "textures/entity/balloon/pink_balloon.png");

	public ResourceLocation getTextureLocation(final BalloonEntity entity) {
		return this.getEntityTypeLocation(entity);
	}

	public ResourceLocation getEntityTypeLocation(final BalloonEntity entity) {
		switch (entity.getColor()) {
		case 1:
			return BalloonModel.RED;
		case 2:
			return BalloonModel.YELLOW;
		case 3: 
			return BalloonModel.GREEN;
		case 4:
			return BalloonModel.BLUE;
		case 5:
			return BalloonModel.PINK;
		default:
			return BalloonModel.RED;
		}
	}

	public ResourceLocation getModelLocation(final BalloonEntity object) {
		return new ResourceLocation(FroggyFurniture.MOD_ID, "models/entity/balloon.geo.json");
	}

	public ResourceLocation getAnimationFileLocation(final BalloonEntity object) {
		return new ResourceLocation(FroggyFurniture.MOD_ID, "animations/balloon.json");
	}
}