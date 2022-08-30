package com.ranafangirl.froggyfurniture.init;

import com.ranafangirl.froggyfurniture.FroggyFurniture;
import com.ranafangirl.froggyfurniture.FroggyFurnitureRegistry;
import com.ranafangirl.froggyfurniture.entity.BalloonEntity;
import com.ranafangirl.froggyfurniture.entity.ChairEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

public class FroggyFurnitureEntityType {
    public static RegistryObject<EntityType<ChairEntity>>	CHAIR	= FroggyFurnitureRegistry.ENTITY_TYPE.register("chair"	, () -> EntityType.Builder.<ChairEntity>of(ChairEntity	::new, EntityClassification.MISC).fireImmune()				.sized(1.0F, 1.0F).build(new ResourceLocation(FroggyFurniture.MOD_ID, "chair"	).toString()));
    public static RegistryObject<EntityType<BalloonEntity>>	BALLOON	= FroggyFurnitureRegistry.ENTITY_TYPE.register("balloon", () -> EntityType.Builder.				of(BalloonEntity::new, EntityClassification.MISC).canSpawnFarFromPlayer()	.sized(1.0F, 1.0F).build(new ResourceLocation(FroggyFurniture.MOD_ID, "balloon"	).toString()));
}
