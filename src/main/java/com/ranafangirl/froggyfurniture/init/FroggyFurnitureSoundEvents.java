package com.ranafangirl.froggyfurniture.init;

import java.util.function.Supplier;

import com.ranafangirl.froggyfurniture.FroggyFurniture;
import com.ranafangirl.froggyfurniture.FroggyFurnitureRegistry;

import net.minecraft.block.SoundType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class FroggyFurnitureSoundEvents {
    public static final RegistryObject<SoundEvent>
    FURNITURE_BREAK 			= buildSound(FroggyFurnitureRegistry.SOUNDS, "block.furniture_break"),
    FURNITURE_STEP				= buildSound(FroggyFurnitureRegistry.SOUNDS, "block.furniture_step"),
    FURNITURE_PLACE				= buildSound(FroggyFurnitureRegistry.SOUNDS, "block.furniture_place"),
    FURNITURE_HIT				= buildSound(FroggyFurnitureRegistry.SOUNDS, "block.furniture_hit"),
    FURNITURE_FALL				= buildSound(FroggyFurnitureRegistry.SOUNDS, "block.furniture_fall"),
    FURNITURE_OPEN				= buildSound(FroggyFurnitureRegistry.SOUNDS, "block.furniture_open"),
    FURNITURE_CLOSE				= buildSound(FroggyFurnitureRegistry.SOUNDS, "block.furniture_close"),
    FROGGY_HAT_SPEAK			= buildSound(FroggyFurnitureRegistry.SOUNDS, "entity.froggy_hat_speak"),
    FROGGY_HAT_SPEAK_IN_RAIN	= buildSound(FroggyFurnitureRegistry.SOUNDS, "entity.froggy_hat_speak_in_rain");
    
	public static final ForgeSoundType FURNITURE = new ForgeSoundType(1.0F, 1.0F,
    		(Supplier)FroggyFurnitureSoundEvents.FURNITURE_BREAK,
    		(Supplier)FroggyFurnitureSoundEvents.FURNITURE_STEP,
    		(Supplier)FroggyFurnitureSoundEvents.FURNITURE_PLACE,
    		(Supplier)FroggyFurnitureSoundEvents.FURNITURE_HIT,
    		(Supplier)FroggyFurnitureSoundEvents.FURNITURE_FALL);
    
    public static SoundType addSoundType(final float vol, final float pitch, final Supplier<SoundEvent> soundSupplier) {
        return (SoundType)new FroggyFurnitureSoundEvents.SoundTypeBetter(vol, pitch, (Supplier)soundSupplier);
    }
    
	public static RegistryObject<SoundEvent> buildSound(DeferredRegister<SoundEvent> register, String registryName) {	
		RegistryObject<SoundEvent> SOUND = register.register(registryName,() -> new SoundEvent(new ResourceLocation(FroggyFurniture.MOD_ID,registryName)));
		return SOUND;
	}
    
	public static class SoundTypeBetter extends SoundType{
		private final java.util.function.Supplier<SoundEvent> soundSupplier;
		
		@SuppressWarnings("deprecation")
		public SoundTypeBetter(float volumeIn, float pitchIn, java.util.function.Supplier<SoundEvent> sound) {
			super(volumeIn, pitchIn, null, null, null, null, null);
			soundSupplier = sound;
		}

		@Override
		public SoundEvent getBreakSound() {
			return soundSupplier.get();
		}
		@Override
		public SoundEvent getFallSound() {
			return soundSupplier.get();
		}
		@Override
		public SoundEvent getHitSound() {
			return soundSupplier.get();
		}
		@Override
		public SoundEvent getPlaceSound() {
			return soundSupplier.get();
		}
		@Override
		public SoundEvent getStepSound() {
			return soundSupplier.get();
		}
	}
}