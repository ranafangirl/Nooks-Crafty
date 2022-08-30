package com.ranafangirl.froggyfurniture.tileentity;

import com.ranafangirl.froggyfurniture.block.CabinetBlock;
import com.ranafangirl.froggyfurniture.init.FroggyFurnitureBlocks;
import com.ranafangirl.froggyfurniture.init.FroggyFurnitureSoundEvents;
import com.ranafangirl.froggyfurniture.init.FroggyFurnitureTileEntityType;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class LotusCabinetTileEntity extends LockableLootTileEntity {
    private NonNullList<ItemStack> items;
    private int openCount;
    
    private LotusCabinetTileEntity(final TileEntityType<?> entity) {
        super(entity);
        this.items = NonNullList.withSize(27, ItemStack.EMPTY);
    }
    
    public LotusCabinetTileEntity() {
        this(FroggyFurnitureTileEntityType.LOTUS_CABINET.get());
    }
    
    public CompoundNBT save(final CompoundNBT compound) {
        super.save(compound);
        if (!this.trySaveLootTable(compound)) {
            ItemStackHelper.saveAllItems(compound, this.items);
        }
        return compound;
    }
    
    public void load(final BlockState state, final CompoundNBT compound) {
        super.load(state, compound);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(compound)) {
            ItemStackHelper.loadAllItems(compound, this.items);
        }
    }
    
    public int getContainerSize() {
        return 27;
    }
    
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }
    
    protected void setItems(final NonNullList<ItemStack> stack) {
        this.items = stack;
    }
    
    protected ITextComponent getDefaultName() {
        return (ITextComponent)new TranslationTextComponent("container.lotus_cabinet");
    }
    
    protected Container createMenu(final int i, final PlayerInventory inventory) {
        return (Container)ChestContainer.threeRows(i, inventory, (IInventory)this);
    }
    
    public void startOpen(final PlayerEntity entity) {
        if (!entity.isSpectator()) {
            if (this.openCount < 0) {
                this.openCount = 0;
            }
            ++this.openCount;
            final BlockState blockstate = this.getBlockState();
            final boolean flag = (boolean)blockstate.getValue(CabinetBlock.OPEN);
            if (!flag) {
                this.playSound(blockstate, (SoundEvent)FroggyFurnitureSoundEvents.FURNITURE_OPEN.get());
                this.updateBlockState(blockstate, true);
            }
            this.scheduleRecheck();
        }
    }
    
    private void scheduleRecheck() {
        this.level.getBlockTicks().scheduleTick(this.getBlockPos(), this.getBlockState().getBlock(), 5);
    }
    
    public void recheckOpen() {
        final int i = this.worldPosition.getX();
        final int j = this.worldPosition.getY();
        final int k = this.worldPosition.getZ();
        this.openCount = ChestTileEntity.getOpenCount(this.level, (LockableTileEntity)this, i, j, k);
        if (this.openCount > 0) {
            this.scheduleRecheck();
        }
        else {
            final BlockState blockstate = this.getBlockState();
            if (!blockstate.is(FroggyFurnitureBlocks.LOTUS_CABINET.get())) {
                this.setRemoved();
                return;
            }
            final boolean flag = blockstate.getValue(CabinetBlock.OPEN);
            if (flag) {
                this.playSound(blockstate, FroggyFurnitureSoundEvents.FURNITURE_CLOSE.get());
                this.updateBlockState(blockstate, false);
            }
        }
    }
    
    public void stopOpen(final PlayerEntity entity) {
        if (!entity.isSpectator()) {
            --this.openCount;
        }
    }
    
    private void updateBlockState(final BlockState state, final boolean b) {
        this.level.setBlock(this.getBlockPos(), state.setValue(CabinetBlock.OPEN, b), 3);
    }
    
    private void playSound(final BlockState state, final SoundEvent event) {
        final Vector3i vector3i = (state.getValue(CabinetBlock.FACING)).getNormal();
        final double d0 = this.worldPosition.getX() + 0.5 + vector3i.getX() / 2.0;
        final double d2 = this.worldPosition.getY() + 0.5 + vector3i.getY() / 2.0;
        final double d3 = this.worldPosition.getZ() + 0.5 + vector3i.getZ() / 2.0;
        this.level.playSound((PlayerEntity)null, d0, d2, d3, event, SoundCategory.BLOCKS, 0.5f, this.level.random.nextFloat() * 0.1f + 0.9f);
    }
}