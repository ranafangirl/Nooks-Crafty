package com.ranafangirl.froggyfurniture.init;

import com.ranafangirl.froggyfurniture.FroggyFurnitureRegistry;
import com.ranafangirl.froggyfurniture.item.FroggyHatItem;
import com.ranafangirl.froggyfurniture.item.ProxyBlockItem;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;

public class FroggyFurnitureItems {
	public static final RegistryObject<Item>
	FROGGY_HAT		= FroggyFurnitureRegistry.ITEMS.register("froggy_hat",		() -> new FroggyHatItem(EquipmentSlotType.HEAD, FroggyFurnitureTextTranslations.FROGGY_HAT_DESC, 	ItemGroup.TAB_COMBAT)),
	FROGGY_CHAIR	= FroggyFurnitureRegistry.ITEMS.register("froggy_chair",	() -> new ProxyBlockItem(FroggyFurnitureBlocks.FROGGY_CHAIR.get(),									ItemGroup.TAB_DECORATIONS)),
	LILY_PAD_TABLE	= FroggyFurnitureRegistry.ITEMS.register("lily_pad_table",	() -> new ProxyBlockItem(FroggyFurnitureBlocks.LILY_PAD_TABLE.get(),								ItemGroup.TAB_DECORATIONS)),
	LOTUS_CABINET	= FroggyFurnitureRegistry.ITEMS.register("lotus_cabinet",	() -> new ProxyBlockItem(FroggyFurnitureBlocks.LOTUS_CABINET.get(),									ItemGroup.TAB_DECORATIONS));
}
