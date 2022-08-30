package com.ranafangirl.froggyfurniture;

import com.ranafangirl.froggyfurniture.init.FroggyFurnitureBlocks;
import com.ranafangirl.froggyfurniture.init.FroggyFurnitureEntityType;
import com.ranafangirl.froggyfurniture.init.FroggyFurnitureItems;
import com.ranafangirl.froggyfurniture.init.FroggyFurnitureSoundEvents;
import com.ranafangirl.froggyfurniture.init.FroggyFurnitureTileEntityType;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings("rawtypes")
public class FroggyFurnitureRegistry {
    public static final DeferredRegister<Block>				BLOCKS				= DeferredRegister.create(ForgeRegistries.BLOCKS,			"froggyfurniture");
    public static final DeferredRegister<Item>				ITEMS				= DeferredRegister.create(ForgeRegistries.ITEMS,			"froggyfurniture");
    public static final DeferredRegister<SoundEvent>		SOUNDS				= DeferredRegister.create(ForgeRegistries.SOUND_EVENTS,		"froggyfurniture");;
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPE	= DeferredRegister.create(ForgeRegistries.TILE_ENTITIES,	"froggyfurniture");;
    public static final DeferredRegister<EntityType<?>>		ENTITY_TYPE			= DeferredRegister.create(ForgeRegistries.ENTITIES,			"froggyfurniture");
    public static final DeferredRegister<ContainerType<?>>	CONTAINER_TYPE		= DeferredRegister.create(ForgeRegistries.CONTAINERS,		"froggyfurniture");
    public static FroggyFurnitureBlocks			Blocks;
    public static FroggyFurnitureItems			Items;
    public static FroggyFurnitureSoundEvents	Sounds;
	public static FroggyFurnitureTileEntityType TileEntityType;
    public static FroggyFurnitureEntityType		EntityType;
    
    public static void init() {
        FroggyFurnitureRegistry.Blocks			= new FroggyFurnitureBlocks();
        FroggyFurnitureRegistry.Items			= new FroggyFurnitureItems();
        FroggyFurnitureRegistry.Sounds			= new FroggyFurnitureSoundEvents();
        FroggyFurnitureRegistry.TileEntityType	= new FroggyFurnitureTileEntityType();
        FroggyFurnitureRegistry.EntityType		= new FroggyFurnitureEntityType();
        FroggyFurnitureRegistry.BLOCKS			.register(FMLJavaModLoadingContext.get().getModEventBus());
        FroggyFurnitureRegistry.ITEMS			.register(FMLJavaModLoadingContext.get().getModEventBus());
        FroggyFurnitureRegistry.SOUNDS			.register(FMLJavaModLoadingContext.get().getModEventBus());
        FroggyFurnitureRegistry.TILE_ENTITY_TYPE.register(FMLJavaModLoadingContext.get().getModEventBus());
        FroggyFurnitureRegistry.ENTITY_TYPE		.register(FMLJavaModLoadingContext.get().getModEventBus());
        FroggyFurnitureRegistry.CONTAINER_TYPE	.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}