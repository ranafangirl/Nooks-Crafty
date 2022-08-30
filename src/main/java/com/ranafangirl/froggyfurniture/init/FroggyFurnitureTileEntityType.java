package com.ranafangirl.froggyfurniture.init;

import com.ranafangirl.froggyfurniture.FroggyFurnitureRegistry;
import com.ranafangirl.froggyfurniture.tileentity.LotusCabinetTileEntity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class FroggyFurnitureTileEntityType<T extends TileEntity> extends ForgeRegistryEntry<TileEntityType<?>> {
	public static final RegistryObject<TileEntityType<LotusCabinetTileEntity>>LOTUS_CABINET = FroggyFurnitureRegistry.TILE_ENTITY_TYPE.register("lotus_cabinet", () -> TileEntityType.Builder.of(LotusCabinetTileEntity::new, FroggyFurnitureBlocks.LOTUS_CABINET.get()).build(null));
	}
