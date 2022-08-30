package com.ranafangirl.froggyfurniture.item;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ranafangirl.froggyfurniture.init.FroggyFurnitureItems;
import com.ranafangirl.froggyfurniture.init.FroggyFurnitureSoundEvents;
import com.ranafangirl.froggyfurniture.util.ItemUtil;

import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.minecart.ChestMinecartEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class FroggyHatItem extends ModeledItem {
    private EquipmentSlotType slot;
    private TranslationTextComponent translation;
    
    public FroggyHatItem(final EquipmentSlotType slot, final TranslationTextComponent translation, final ItemGroup tab) {
        super(new Item.Properties().tab(tab).stacksTo(1));
        this.slot = slot;
        this.translation = translation;
    }
    
    public EquipmentSlotType getEquipmentSlot(final ItemStack stack) {
        return this.slot;
    }
    
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(final ItemStack stack, @Nullable final World worldIn, final List<ITextComponent> tooltip, final ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        ItemUtil.addText(tooltip, this.translation.getString(), TextFormatting.BLUE);
    }
    
    public void inventoryTick(final ItemStack stack, final World worldIn, final Entity entityIn, final int itemSlot, final boolean isSelected) {
        final Random rand = new Random();
        if (rand.nextInt(35) == 1 && (this.checkBlock(entityIn.blockPosition(), worldIn) || this.checkEntity(entityIn.blockPosition(), worldIn))) {
            final PlayerEntity player = (PlayerEntity)entityIn;
            if (player.getItemBySlot(EquipmentSlotType.HEAD).getItem() == FroggyFurnitureItems.FROGGY_HAT.get()) {
                if (!worldIn.isRaining()) {
                    entityIn.playSound(FroggyFurnitureSoundEvents.FROGGY_HAT_SPEAK.get(), 1.0F, 1.0F);
                }
                else {
                    entityIn.playSound(FroggyFurnitureSoundEvents.FROGGY_HAT_SPEAK_IN_RAIN.get(), 1.0F, 1.0F);
                }
            }
        }
        super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
    }
    
    public boolean checkBlock(final BlockPos pos, final World world) {
        for (final BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-10, -10, -10), pos.offset(10, 10, 10))) {
            if (world.getBlockState(blockpos).is(Blocks.CHEST)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean checkEntity(final BlockPos pos, final World worldIn) {
        final AxisAlignedBB aabb = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
        final AxisAlignedBB bounds = aabb.move(pos).inflate(10.0, 10.0, 10.0);
        final List<Entity> entities = worldIn.getEntitiesOfClass(ChestMinecartEntity.class, bounds);
        return entities.size() > 0;
    }
    
    public ModeledItem.RenderDimension handRendering() {
        return ModeledItem.RenderDimension.FLAT;
    }
    
    public ModeledItem.RenderDimension hatRendering() {
        return ModeledItem.RenderDimension.MODEL;
    }
}