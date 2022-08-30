package com.ranafangirl.froggyfurniture.entity;

import java.util.List;

import com.ranafangirl.froggyfurniture.init.FroggyFurnitureEntityType;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class ChairEntity extends Entity {
    int lazyTimer;
    private BlockPos source;
    
    public ChairEntity(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
        this.lazyTimer = 0;
    }
    
    public ChairEntity(World level, BlockPos pos, double yOffset) {
        this(FroggyFurnitureEntityType.CHAIR.get(), level);
        this.source = pos;
        this.setPos(this.source.getX() + 0.5, this.source.getY() - 0.4, this.source.getZ() + 0.5);
    }
    
    public BlockPos getSource() {
        return source;
    }
    
    public void mountTo (PlayerEntity player) {
        player.xRot = this.xRot;
        player.yRot = this.yRot;
        player.startRiding(this);
    }
    
	@Override
	public void tick() {
		super.tick();
		if (lazyTimer < 40) {
			lazyTimer++;
		}
		if (getPassengers().size() < 1 && lazyTimer >= 40) {
			this.remove();
		}
	}
    
	public static ActionResultType create(World level, BlockPos pos, double yOffset, PlayerEntity player) {
        if (!level.isClientSide()) {
            List<ChairEntity> seats = level.getEntitiesOfClass(ChairEntity.class, new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1.0, pos.getY() + 1.0, pos.getZ() + 1.0));
            if (seats.isEmpty()) {
            	ChairEntity seat = new ChairEntity(level, pos, yOffset);
                level.addFreshEntity(seat);
                player.startRiding(seat, false);
            }
        }
		return ActionResultType.SUCCESS;
    }
    
	@Override
	protected void defineSynchedData() {		
	}

	@Override
	protected void readAdditionalSaveData(CompoundNBT compound) {		
	}

	@Override
	protected void addAdditionalSaveData(CompoundNBT compound) {		
	}

	@Override
	public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
	}
}
