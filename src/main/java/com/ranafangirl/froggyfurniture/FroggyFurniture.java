package com.ranafangirl.froggyfurniture;

import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Nonnull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.ranafangirl.froggyfurniture.client.render.BaseTileEntityRenderer;
import com.ranafangirl.froggyfurniture.client.render.ModeledItemRenderer;
import com.ranafangirl.froggyfurniture.client.render.ModeledItemRenderer.ItemRenderInfo;
import com.ranafangirl.froggyfurniture.client.render.ModeledItemRenderer.ItemRenderInfo.OtherModel;
import com.ranafangirl.froggyfurniture.client.render.entity.EmptyRenderer;
import com.ranafangirl.froggyfurniture.entity.BalloonEntity;
import com.ranafangirl.froggyfurniture.init.FroggyFurnitureBlocks;
import com.ranafangirl.froggyfurniture.init.FroggyFurnitureEntityType;
import com.ranafangirl.froggyfurniture.init.FroggyFurnitureItems;
import com.ranafangirl.froggyfurniture.init.FroggyFurnitureTileEntityType;
import com.ranafangirl.froggyfurniture.loot.FroggyHatFromAbandonedMineshaftModifier;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@SuppressWarnings("deprecation")
@Mod("froggyfurniture")
public class FroggyFurniture {
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "froggyfurniture";

	public FroggyFurniture() {
		final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		FroggyFurnitureRegistry.init();
		MinecraftForge.EVENT_BUS.register((Object)this);
		bus.addListener(this::registerRendering);
	}

	private void registerRendering(final FMLClientSetupEvent event) {
		RenderTypeLookup.setRenderLayer					(FroggyFurnitureBlocks.		FROGGY_CHAIR.get(),		RenderType.cutout());
		RenderTypeLookup.setRenderLayer					(FroggyFurnitureBlocks.		LILY_PAD_TABLE.get(), 	RenderType.cutout());
		RenderingRegistry.registerEntityRenderingHandler(FroggyFurnitureEntityType.	CHAIR.get(), 			EmptyRenderer::new);
		ModeledItemRenderer.addItemRender				(FroggyFurnitureItems.		FROGGY_HAT.get());
	}

	public static void registerTileEntityRendering() {
		ClientRegistry.bindTileEntityRenderer(FroggyFurnitureTileEntityType.LOTUS_CABINET.get(), BaseTileEntityRenderer::new);
	}

	public static void registerEntityAttributes(final EntityAttributeCreationEvent event) {
		GlobalEntityTypeAttributes.put(FroggyFurnitureEntityType.BALLOON.get(), BalloonEntity.setCustomAttributes().build());
	}

	@Nonnull
	public static void registerModifierSerializers(final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
		event.getRegistry().registerAll(
				new FroggyHatFromAbandonedMineshaftModifier.Serializer().setRegistryName(new ResourceLocation(FroggyFurniture.MOD_ID, "froggy_hat_from_abandoned_mineshaft"))
				);
	}

	public static void modelBakeEvent(ModelBakeEvent event) {
		Map<ResourceLocation, IBakedModel> modelRegistry = event.getModelRegistry();

		for (ItemRenderInfo renderInfo : ModeledItemRenderer.getRenders()) {
			IBakedModel baseModel = modelRegistry.get(renderInfo.getBaseLocation());

			for (OtherModel otherModel : renderInfo.getTransforms().values()) {
				otherModel.setModel(modelRegistry.get(otherModel.getLocation()));
				modelRegistry.put(otherModel.getLocation(), otherModel.getModel());
			}

			IBakedModel bakedModel = new IBakedModel() {
				@Override
				public IBakedModel handlePerspective(ItemCameraTransforms.TransformType transformType, MatrixStack mat) {
					IBakedModel model = renderInfo.getTransforms().get(transformType).getModel();
					if(model == null) model = baseModel;
					return ForgeHooksClient.handlePerspective(model, transformType, mat);
				}

				@Override
				public List<BakedQuad> getQuads(BlockState state, Direction side, Random rand) {
					return baseModel.getQuads(state, side, rand);
				}

				@Override
				public boolean useAmbientOcclusion() {
					return baseModel.useAmbientOcclusion();
				}

				@Override
				public boolean isGui3d() {
					return baseModel.isGui3d();
				}
				@Override
				public boolean isCustomRenderer() {
					return baseModel.isCustomRenderer();
				}

				@Override
				public TextureAtlasSprite getParticleIcon() {
					return baseModel.getParticleIcon();
				}

				@Override
				public ItemOverrideList getOverrides() {
					return baseModel.getOverrides();
				}

				@Override
				public boolean usesBlockLight() {
					return false;
				}
			};
			modelRegistry.put(renderInfo.getBaseLocation(), bakedModel);
		}
	}
}