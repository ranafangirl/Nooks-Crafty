package com.ranafangirl.froggyfurniture.util;

import java.util.List;
import java.util.logging.Level;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

public class ItemUtil {
    public static List<ITextComponent> addText(final List<ITextComponent> tooltip, final String text, final TextFormatting colour) {
        tooltip.add((ITextComponent)new StringTextComponent(colour + text));
        return tooltip;
    }
    
    public static ActionResult<ItemStack> startUsingInstantly(final Level level, final PlayerEntity player, final Hand hand) {
        player.startUsingItem(hand);
        return (ActionResult.consume(player.getItemInHand(hand)));
    }
}