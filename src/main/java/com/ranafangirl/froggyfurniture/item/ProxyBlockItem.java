package com.ranafangirl.froggyfurniture.item;

import java.util.List;

import javax.annotation.Nullable;

import com.ranafangirl.froggyfurniture.util.ItemUtil;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ProxyBlockItem extends BlockItem {
    public String description;
    
    public ProxyBlockItem(final Block blockIn, final ItemGroup group) {
        super(blockIn, new Item.Properties().tab(group));
        this.description = "";
    }
    
    public void addInformation(final ItemStack stack, @Nullable final World worldIn, final List<ITextComponent> tooltip, final ITooltipFlag flagIn) {
        if (!this.description.equals("")) {
            ItemUtil.addText(tooltip, this.description, TextFormatting.GREEN);
        }
    }
}