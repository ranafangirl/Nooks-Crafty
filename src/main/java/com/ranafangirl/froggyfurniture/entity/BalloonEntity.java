package com.ranafangirl.froggyfurniture.entity;

import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import javax.annotation.Nullable;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.world.World;
import net.minecraft.entity.EntityType;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.datasync.DataSerializers;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import net.minecraft.network.datasync.DataParameter;
import software.bernie.geckolib3.core.IAnimatable;
import net.minecraft.entity.passive.AmbientEntity;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class BalloonEntity extends AmbientEntity implements IAnimatable {
	public static final DataParameter<Integer> COLOR = EntityDataManager.defineId(BalloonEntity.class, DataSerializers.INT);
	private AnimationFactory factory;

	public BalloonEntity(final EntityType<? extends AmbientEntity> type, final World worldIn) {
		super(type, worldIn);
		this.factory = new AnimationFactory((IAnimatable)this);
	}

	public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
		return MobEntity.createLivingAttributes().add(Attributes.MOVEMENT_SPEED, 1.0).add(Attributes.MAX_HEALTH, 1.0);
	}

	public void addAdditionalSaveData(final CompoundNBT nbt) {
		super.addAdditionalSaveData(nbt);
		if (this.entityData != null) {
			nbt.putInt("Color", this.entityData.get(BalloonEntity.COLOR));
		}
	}

	public void readAdditionalSaveData(final CompoundNBT nbt) {
		super.readAdditionalSaveData(nbt);
		if (nbt.contains("Color")) {
			this.setColor(nbt.getInt("Color"));
		}
		else {
			this.setColor(this.random.nextInt(4));
		}
	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.getEntityData().define(BalloonEntity.COLOR, this.random.nextInt(4));
	}

	public int getColor() {
		return this.entityData.get(BalloonEntity.COLOR);
	}

	public void setColor(final int mate) {
		if (this.entityData != null) {
			this.entityData.set(BalloonEntity.COLOR, mate);
		}
	}

	@Nullable
	public ILivingEntityData finalizeSpawn(final IServerWorld level, final DifficultyInstance instance, final SpawnReason reason, @Nullable ILivingEntityData data, @Nullable final CompoundNBT nbt) {
		data = super.finalizeSpawn(level, instance, reason, data, nbt);
		return data;
	}

	protected boolean isMovementNoisy() {
		return false;
	}

	private <E extends IAnimatable> PlayState predicate(final AnimationEvent<E> event) {
		event.getController().setAnimation(new AnimationBuilder().addAnimation("balloon_float", Boolean.valueOf(true)));
		return PlayState.CONTINUE;
	}

	public void registerControllers(final AnimationData data) {
		data.addAnimationController(new AnimationController(this, "controller", 0.0f, this::predicate));
	}

	public AnimationFactory getFactory() {
		return this.factory;
	}
}