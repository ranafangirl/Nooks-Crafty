package com.ranafangirl.froggyfurniture.init;

import com.ranafangirl.froggyfurniture.FroggyFurnitureRegistry;
import com.ranafangirl.froggyfurniture.block.ChairBlock;
import com.ranafangirl.froggyfurniture.block.TableBlock;
import com.ranafangirl.froggyfurniture.block.CabinetBlock;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.fml.RegistryObject;

public class FroggyFurnitureBlocks {
	public static RegistryObject<Block>
	FROGGY_CHAIR	= FroggyFurnitureRegistry.BLOCKS.register("froggy_chair"	, () -> new ChairBlock	(AbstractBlock.Properties.of((Material)Material.WOOD, (MaterialColor)MaterialColor.COLOR_LIGHT_GREEN).harvestLevel(1).strength(3.0F, 2.5F).sound((SoundType)FroggyFurnitureSoundEvents.FURNITURE).noOcclusion(), 0.0f)),
	LILY_PAD_TABLE	= FroggyFurnitureRegistry.BLOCKS.register("lily_pad_table"	, () -> new TableBlock	(AbstractBlock.Properties.of((Material)Material.WOOD, (MaterialColor)MaterialColor.COLOR_LIGHT_GREEN).harvestLevel(1).strength(3.0F, 2.5F).sound((SoundType)FroggyFurnitureSoundEvents.FURNITURE).noOcclusion())),
	LOTUS_CABINET	= FroggyFurnitureRegistry.BLOCKS.register("lotus_cabinet"	, () -> new CabinetBlock	(AbstractBlock.Properties.of((Material)Material.WOOD, (MaterialColor)MaterialColor.COLOR_LIGHT_GREEN).harvestLevel(1).strength(3.0F, 2.5F).sound((SoundType)FroggyFurnitureSoundEvents.FURNITURE).noOcclusion()));
}