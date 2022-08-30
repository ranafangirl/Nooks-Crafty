package com.ranafangirl.froggyfurniture.item;

import javax.annotation.Nullable;

import net.minecraft.item.Item;

public class ModeledItem extends Item {
    public ModeledItem(final Item.Properties properties) {
        super(properties);
    }
    
    public ModeledItem.RenderDimension handRendering() {
        return ModeledItem.RenderDimension.MODEL;
    }
    
    public ModeledItem.RenderDimension inventoryRendering() {
        return ModeledItem.RenderDimension.FLAT;
    }
    
    public ModeledItem.RenderDimension hatRendering() {
        return ModeledItem.RenderDimension.FLAT;
    }
    
    public ModeledItem.RenderDimension itemEntityRendering() {
        return ModeledItem.RenderDimension.FLAT;
    }
    
    public ModeledItem.RenderDimension itemFrameRendering() {
        return ModeledItem.RenderDimension.FLAT;
    }
    
    public enum RenderDimension
    {
        FLAT("FLAT", 0, (String)null), 
        MODEL("MODEL", 1, "model");
        
        private String string;
        
        private RenderDimension(@Nullable final String name, final int ordinal, final String string) {
            this.string = string;
        }
        
        @Override
        public String toString() {
            if (this.string != null) {
                return "_" + this.string;
            }
            return "";
        }
    }
}